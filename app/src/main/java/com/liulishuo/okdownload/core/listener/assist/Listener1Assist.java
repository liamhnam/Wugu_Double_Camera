package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.concurrent.atomic.AtomicLong;

public class Listener1Assist {
    private Listener1Callback callback;
    private final SparseArray<Listener1Model> modelList = new SparseArray<>();
    private Listener1Model singleTaskModel;

    public interface Listener1Callback {
        void connected(DownloadTask downloadTask, int i, long j, long j2);

        void progress(DownloadTask downloadTask, long j, long j2);

        void retry(DownloadTask downloadTask, ResumeFailedCause resumeFailedCause);

        void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener1Model listener1Model);

        void taskStart(DownloadTask downloadTask, Listener1Model listener1Model);
    }

    public void setCallback(Listener1Callback listener1Callback) {
        this.callback = listener1Callback;
    }

    public void taskStart(DownloadTask downloadTask) {
        int id = downloadTask.getId();
        Listener1Model listener1Model = new Listener1Model(id);
        synchronized (this) {
            if (this.singleTaskModel == null) {
                this.singleTaskModel = listener1Model;
            } else {
                this.modelList.put(id, listener1Model);
            }
        }
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.taskStart(downloadTask, listener1Model);
        }
    }

    public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        Listener1Model listener1Model;
        int id = downloadTask.getId();
        synchronized (this) {
            Listener1Model listener1Model2 = this.singleTaskModel;
            if (listener1Model2 != null && listener1Model2.f2073id == id) {
                listener1Model = this.singleTaskModel;
                this.singleTaskModel = null;
            } else {
                Listener1Model listener1Model3 = this.modelList.get(id);
                this.modelList.remove(id);
                listener1Model = listener1Model3;
            }
        }
        if (listener1Model == null) {
            listener1Model = new Listener1Model(downloadTask.getId());
        }
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.taskEnd(downloadTask, endCause, exc, listener1Model);
        }
    }

    public Listener1Model getSingleTaskModel() {
        return this.singleTaskModel;
    }

    public Listener1Model findModel(int i) {
        Listener1Model listener1Model = this.singleTaskModel;
        return (listener1Model == null || listener1Model.f2073id != i) ? this.modelList.get(i) : this.singleTaskModel;
    }

    public void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
        Listener1Callback listener1Callback;
        Listener1Model listener1ModelAssignModelIfNeed = assignModelIfNeed(downloadTask.getId(), breakpointInfo);
        if (listener1ModelAssignModelIfNeed == null) {
            return;
        }
        if (listener1ModelAssignModelIfNeed.isStarted && (listener1Callback = this.callback) != null) {
            listener1Callback.retry(downloadTask, resumeFailedCause);
        }
        listener1ModelAssignModelIfNeed.isStarted = true;
        listener1ModelAssignModelIfNeed.isFromResumed = false;
        listener1ModelAssignModelIfNeed.isFirstConnect = true;
    }

    public void downloadFromBreakpoint(int i, BreakpointInfo breakpointInfo) {
        Listener1Model listener1ModelAssignModelIfNeed = assignModelIfNeed(i, breakpointInfo);
        if (listener1ModelAssignModelIfNeed == null) {
            return;
        }
        listener1ModelAssignModelIfNeed.isStarted = true;
        listener1ModelAssignModelIfNeed.isFromResumed = true;
        listener1ModelAssignModelIfNeed.isFirstConnect = true;
    }

    private Listener1Model assignModelIfNeed(int i, BreakpointInfo breakpointInfo) {
        Listener1Model listener1ModelFindModel = findModel(i);
        if (listener1ModelFindModel == null) {
            return null;
        }
        listener1ModelFindModel.blockCount = breakpointInfo.getBlockCount();
        listener1ModelFindModel.totalLength = breakpointInfo.getTotalLength();
        listener1ModelFindModel.currentOffset.set(breakpointInfo.getTotalOffset());
        return listener1ModelFindModel;
    }

    public void connectEnd(DownloadTask downloadTask) {
        Listener1Model listener1ModelFindModel = findModel(downloadTask.getId());
        if (listener1ModelFindModel == null) {
            return;
        }
        if (listener1ModelFindModel.isFromResumed && listener1ModelFindModel.isFirstConnect) {
            listener1ModelFindModel.isFirstConnect = false;
        }
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.connected(downloadTask, listener1ModelFindModel.blockCount, listener1ModelFindModel.currentOffset.get(), listener1ModelFindModel.totalLength);
        }
    }

    public void fetchProgress(DownloadTask downloadTask, long j) {
        Listener1Model listener1ModelFindModel = findModel(downloadTask.getId());
        if (listener1ModelFindModel == null) {
            return;
        }
        listener1ModelFindModel.currentOffset.addAndGet(j);
        Listener1Callback listener1Callback = this.callback;
        if (listener1Callback != null) {
            listener1Callback.progress(downloadTask, listener1ModelFindModel.currentOffset.get(), listener1ModelFindModel.totalLength);
        }
    }

    public static class Listener1Model {
        int blockCount;
        final AtomicLong currentOffset = new AtomicLong();

        final int f2073id;
        volatile boolean isFirstConnect;
        boolean isFromResumed;
        boolean isStarted;
        long totalLength;

        Listener1Model(int i) {
            this.f2073id = i;
        }

        public long getTotalLength() {
            return this.totalLength;
        }

        public int getId() {
            return this.f2073id;
        }
    }
}
