package com.liulishuo.okdownload.core.file;

import android.net.Uri;
import android.os.SystemClock;
import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

public class MultiPointOutputStream {
    private static final ExecutorService FILE_IO_EXECUTOR = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkDownload file io", false));
    private static final String TAG = "MultiPointOutputStream";
    final AtomicLong allNoSyncLength;
    final StreamsState doneState;
    private volatile boolean firstOutputStream;
    private final int flushBufferSize;
    private final BreakpointInfo info;
    private final boolean isPreAllocateLength;
    final AtomicLong lastSyncTimestamp;
    List<Integer> noMoreStreamList;
    final SparseArray<AtomicLong> noSyncLengthMap;
    final SparseArray<DownloadOutputStream> outputStreamMap;
    final SparseArray<Thread> parkedRunBlockThreadMap;
    private String path;
    volatile Thread runSyncThread;
    StreamsState state;
    private final DownloadStore store;
    private final boolean supportSeek;
    private final int syncBufferIntervalMills;
    private final int syncBufferSize;
    IOException syncException;
    volatile Future syncFuture;
    private final Runnable syncRunnable;
    private final DownloadTask task;

    MultiPointOutputStream(final DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore, Runnable runnable) {
        this.outputStreamMap = new SparseArray<>();
        this.noSyncLengthMap = new SparseArray<>();
        this.allNoSyncLength = new AtomicLong();
        this.lastSyncTimestamp = new AtomicLong();
        this.parkedRunBlockThreadMap = new SparseArray<>();
        this.doneState = new StreamsState();
        this.state = new StreamsState();
        this.firstOutputStream = true;
        this.task = downloadTask;
        this.flushBufferSize = downloadTask.getFlushBufferSize();
        this.syncBufferSize = downloadTask.getSyncBufferSize();
        this.syncBufferIntervalMills = downloadTask.getSyncBufferIntervalMills();
        this.info = breakpointInfo;
        this.store = downloadStore;
        this.supportSeek = OkDownload.with().outputStreamFactory().supportSeek();
        this.isPreAllocateLength = OkDownload.with().processFileStrategy().isPreAllocateLength(downloadTask);
        this.noMoreStreamList = new ArrayList();
        if (runnable == null) {
            this.syncRunnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        MultiPointOutputStream.this.runSync();
                    } catch (IOException e) {
                        MultiPointOutputStream.this.syncException = e;
                        Util.m697w(MultiPointOutputStream.TAG, "Sync to breakpoint-store for task[" + downloadTask.getId() + "] failed with cause: " + e);
                    }
                }
            };
        } else {
            this.syncRunnable = runnable;
        }
        File file = downloadTask.getFile();
        if (file != null) {
            this.path = file.getAbsolutePath();
        }
    }

    public MultiPointOutputStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore) {
        this(downloadTask, breakpointInfo, downloadStore, null);
    }

    public void write(int i, byte[] bArr, int i2) throws IOException {
        outputStream(i).write(bArr, 0, i2);
        long j = i2;
        this.allNoSyncLength.addAndGet(j);
        this.noSyncLengthMap.get(i).addAndGet(j);
        inspectAndPersist();
    }

    public void cancelAsync() {
        FILE_IO_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                MultiPointOutputStream.this.cancel();
            }
        });
    }

    public void cancel() {
        SparseArray<DownloadOutputStream> sparseArrayClone;
        SparseArray<DownloadOutputStream> sparseArrayClone2;
        int i = 0;
        try {
            if (this.allNoSyncLength.get() <= 0) {
                synchronized (this) {
                    sparseArrayClone2 = this.outputStreamMap.clone();
                }
                int size = sparseArrayClone2.size();
                while (i < size) {
                    try {
                        close(sparseArrayClone2.keyAt(i));
                    } catch (IOException e) {
                        Util.m694d(TAG, "OutputStream close failed task[" + this.task.getId() + "] block[" + i + "]" + e);
                    }
                    i++;
                }
                this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
                return;
            }
            SparseArray<DownloadOutputStream> sparseArrayClone3 = this.outputStreamMap.clone();
            int size2 = sparseArrayClone3.size();
            for (int i2 = 0; i2 < size2; i2++) {
                this.noMoreStreamList.add(Integer.valueOf(sparseArrayClone3.keyAt(i2)));
            }
            if (this.syncFuture != null && !this.syncFuture.isDone()) {
                inspectValidPath();
                OkDownload.with().processFileStrategy().getFileLock().increaseLock(this.path);
                try {
                    ensureSync(true, -1);
                    OkDownload.with().processFileStrategy().getFileLock().decreaseLock(this.path);
                } catch (Throwable th) {
                    OkDownload.with().processFileStrategy().getFileLock().decreaseLock(this.path);
                    throw th;
                }
            }
            synchronized (this) {
                sparseArrayClone = this.outputStreamMap.clone();
            }
            int size3 = sparseArrayClone.size();
            while (i < size3) {
                try {
                    close(sparseArrayClone.keyAt(i));
                } catch (IOException e2) {
                    Util.m694d(TAG, "OutputStream close failed task[" + this.task.getId() + "] block[" + i + "]" + e2);
                }
                i++;
            }
            this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
        } catch (Throwable th2) {
            synchronized (this) {
                SparseArray<DownloadOutputStream> sparseArrayClone4 = this.outputStreamMap.clone();
                int size4 = sparseArrayClone4.size();
                while (i < size4) {
                    try {
                        close(sparseArrayClone4.keyAt(i));
                    } catch (IOException e3) {
                        Util.m694d(TAG, "OutputStream close failed task[" + this.task.getId() + "] block[" + i + "]" + e3);
                    }
                    i++;
                }
                this.store.onTaskEnd(this.task.getId(), EndCause.CANCELED, null);
                throw th2;
            }
        }
    }

    public void done(int i) throws IOException {
        this.noMoreStreamList.add(Integer.valueOf(i));
        try {
            IOException iOException = this.syncException;
            if (iOException != null) {
                throw iOException;
            }
            if (this.syncFuture != null && !this.syncFuture.isDone()) {
                AtomicLong atomicLong = this.noSyncLengthMap.get(i);
                if (atomicLong != null && atomicLong.get() > 0) {
                    inspectStreamState(this.doneState);
                    ensureSync(this.doneState.isNoMoreStream, i);
                }
            } else if (this.syncFuture == null) {
                Util.m694d(TAG, "OutputStream done but no need to ensure sync, because the sync job not run yet. task[" + this.task.getId() + "] block[" + i + "]");
            } else {
                Util.m694d(TAG, "OutputStream done but no need to ensure sync, because the syncFuture.isDone[" + this.syncFuture.isDone() + "] task[" + this.task.getId() + "] block[" + i + "]");
            }
        } finally {
            close(i);
        }
    }

    void ensureSync(boolean z, int i) {
        if (this.syncFuture == null || this.syncFuture.isDone()) {
            return;
        }
        if (!z) {
            this.parkedRunBlockThreadMap.put(i, Thread.currentThread());
        }
        if (this.runSyncThread != null) {
            unparkThread(this.runSyncThread);
        } else {
            while (!isRunSyncThreadValid()) {
                parkThread(25L);
            }
            unparkThread(this.runSyncThread);
        }
        if (z) {
            unparkThread(this.runSyncThread);
            try {
                this.syncFuture.get();
                return;
            } catch (InterruptedException | ExecutionException unused) {
                return;
            }
        }
        parkThread();
    }

    boolean isRunSyncThreadValid() {
        return this.runSyncThread != null;
    }

    public void inspectComplete(int i) throws IOException {
        BlockInfo block = this.info.getBlock(i);
        if (!Util.isCorrectFull(block.getCurrentOffset(), block.getContentLength())) {
            throw new IOException("The current offset on block-info isn't update correct, " + block.getCurrentOffset() + " != " + block.getContentLength() + " on " + i);
        }
    }

    void inspectAndPersist() throws IOException {
        IOException iOException = this.syncException;
        if (iOException != null) {
            throw iOException;
        }
        if (this.syncFuture == null) {
            synchronized (this.syncRunnable) {
                if (this.syncFuture == null) {
                    this.syncFuture = executeSyncRunnableAsync();
                }
            }
        }
    }

    synchronized void close(int i) throws IOException {
        DownloadOutputStream downloadOutputStream = this.outputStreamMap.get(i);
        if (downloadOutputStream != null) {
            downloadOutputStream.close();
            this.outputStreamMap.remove(i);
            Util.m694d(TAG, "OutputStream close task[" + this.task.getId() + "] block[" + i + "]");
        }
    }

    void parkThread(long j) {
        LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(j));
    }

    void parkThread() {
        LockSupport.park();
    }

    void unparkThread(Thread thread) {
        LockSupport.unpark(thread);
    }

    Future executeSyncRunnableAsync() {
        return FILE_IO_EXECUTOR.submit(this.syncRunnable);
    }

    void inspectStreamState(StreamsState streamsState) {
        streamsState.newNoMoreStreamBlockList.clear();
        SparseArray<DownloadOutputStream> sparseArrayClone = this.outputStreamMap.clone();
        int size = sparseArrayClone.size();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            int iKeyAt = sparseArrayClone.keyAt(i);
            if (!this.noMoreStreamList.contains(Integer.valueOf(iKeyAt))) {
                z = false;
            } else if (!streamsState.noMoreStreamBlockList.contains(Integer.valueOf(iKeyAt))) {
                streamsState.noMoreStreamBlockList.add(Integer.valueOf(iKeyAt));
                streamsState.newNoMoreStreamBlockList.add(Integer.valueOf(iKeyAt));
            }
        }
        streamsState.isNoMoreStream = z;
    }

    static class StreamsState {
        boolean isNoMoreStream;
        List<Integer> noMoreStreamBlockList = new ArrayList();
        List<Integer> newNoMoreStreamBlockList = new ArrayList();

        StreamsState() {
        }

        boolean isStreamsEndOrChanged() {
            return this.isNoMoreStream || this.newNoMoreStreamBlockList.size() > 0;
        }
    }

    void runSync() throws IOException {
        int i;
        Util.m694d(TAG, "OutputStream start flush looper task[" + this.task.getId() + "] with syncBufferIntervalMills[" + this.syncBufferIntervalMills + "] syncBufferSize[" + this.syncBufferSize + "]");
        this.runSyncThread = Thread.currentThread();
        long nextParkMillisecond = this.syncBufferIntervalMills;
        flushProcess();
        while (true) {
            parkThread(nextParkMillisecond);
            inspectStreamState(this.state);
            if (this.state.isStreamsEndOrChanged()) {
                Util.m694d(TAG, "runSync state change isNoMoreStream[" + this.state.isNoMoreStream + "] newNoMoreStreamBlockList[" + this.state.newNoMoreStreamBlockList + "]");
                if (this.allNoSyncLength.get() > 0) {
                    flushProcess();
                }
                for (Integer num : this.state.newNoMoreStreamBlockList) {
                    Thread thread = this.parkedRunBlockThreadMap.get(num.intValue());
                    this.parkedRunBlockThreadMap.remove(num.intValue());
                    if (thread != null) {
                        unparkThread(thread);
                    }
                }
                if (this.state.isNoMoreStream) {
                    break;
                }
            } else {
                if (isNoNeedFlushForLength()) {
                    i = this.syncBufferIntervalMills;
                } else {
                    nextParkMillisecond = getNextParkMillisecond();
                    if (nextParkMillisecond <= 0) {
                        flushProcess();
                        i = this.syncBufferIntervalMills;
                    }
                }
                nextParkMillisecond = i;
            }
        }
        int size = this.parkedRunBlockThreadMap.size();
        for (int i2 = 0; i2 < size; i2++) {
            Thread threadValueAt = this.parkedRunBlockThreadMap.valueAt(i2);
            if (threadValueAt != null) {
                unparkThread(threadValueAt);
            }
        }
        this.parkedRunBlockThreadMap.clear();
    }

    boolean isNoNeedFlushForLength() {
        return this.allNoSyncLength.get() < ((long) this.syncBufferSize);
    }

    long getNextParkMillisecond() {
        return ((long) this.syncBufferIntervalMills) - (now() - this.lastSyncTimestamp.get());
    }

    long now() {
        return SystemClock.uptimeMillis();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void flushProcess() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.liulishuo.okdownload.core.file.MultiPointOutputStream.flushProcess():void");
    }

    synchronized DownloadOutputStream outputStream(int i) throws IOException {
        DownloadOutputStream downloadOutputStream;
        Uri uri;
        downloadOutputStream = this.outputStreamMap.get(i);
        if (downloadOutputStream == null) {
            boolean zIsUriFileScheme = Util.isUriFileScheme(this.task.getUri());
            if (zIsUriFileScheme) {
                File file = this.task.getFile();
                if (file == null) {
                    throw new FileNotFoundException("Filename is not ready!");
                }
                File parentFile = this.task.getParentFile();
                if (!parentFile.exists() && !parentFile.mkdirs()) {
                    throw new IOException("Create parent folder failed!");
                }
                if (file.createNewFile()) {
                    Util.m694d(TAG, "Create new file: " + file.getName());
                }
                uri = Uri.fromFile(file);
            } else {
                uri = this.task.getUri();
            }
            DownloadOutputStream downloadOutputStreamCreate = OkDownload.with().outputStreamFactory().create(OkDownload.with().context(), uri, this.flushBufferSize);
            if (this.supportSeek) {
                long rangeLeft = this.info.getBlock(i).getRangeLeft();
                if (rangeLeft > 0) {
                    downloadOutputStreamCreate.seek(rangeLeft);
                    Util.m694d(TAG, "Create output stream write from (" + this.task.getId() + ") block(" + i + ") " + rangeLeft);
                }
            }
            if (!this.info.isChunked() && this.firstOutputStream && this.isPreAllocateLength) {
                long totalLength = this.info.getTotalLength();
                if (zIsUriFileScheme) {
                    File file2 = this.task.getFile();
                    long length = totalLength - file2.length();
                    if (length > 0) {
                        inspectFreeSpace(file2.getAbsolutePath(), length);
                        downloadOutputStreamCreate.setLength(totalLength);
                    }
                } else {
                    downloadOutputStreamCreate.setLength(totalLength);
                }
            }
            synchronized (this.noSyncLengthMap) {
                this.outputStreamMap.put(i, downloadOutputStreamCreate);
                this.noSyncLengthMap.put(i, new AtomicLong());
            }
            this.firstOutputStream = false;
            downloadOutputStream = downloadOutputStreamCreate;
        }
        return downloadOutputStream;
    }

    void inspectFreeSpace(String str, long j) throws PreAllocateException {
        long freeSpaceBytes = Util.getFreeSpaceBytes(str);
        if (freeSpaceBytes < j) {
            throw new PreAllocateException(j, freeSpaceBytes);
        }
    }

    private void inspectValidPath() {
        if (this.path != null || this.task.getFile() == null) {
            return;
        }
        this.path = this.task.getFile().getAbsolutePath();
    }
}
