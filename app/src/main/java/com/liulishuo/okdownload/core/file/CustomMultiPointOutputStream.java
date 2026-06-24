package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import java.io.IOException;

public class CustomMultiPointOutputStream extends MultiPointOutputStream {
    private static final String TAG = "CustomMultiPointOutputStream";
    private final DownloadTask task;

    CustomMultiPointOutputStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore, Runnable runnable) {
        super(downloadTask, breakpointInfo, downloadStore);
        this.task = downloadTask;
    }

    public CustomMultiPointOutputStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore) {
        this(downloadTask, breakpointInfo, downloadStore, null);
    }

    @Override
    public synchronized void close(int i) throws IOException {
        DownloadOutputStream downloadOutputStream = this.outputStreamMap.get(i);
        if (downloadOutputStream != null) {
            downloadOutputStream.close();
            synchronized (this.noSyncLengthMap) {
                this.outputStreamMap.remove(i);
                this.noSyncLengthMap.remove(i);
            }
            Util.m694d(TAG, "OutputStream close task[" + this.task.getId() + "] block[" + i + "]");
        }
    }
}
