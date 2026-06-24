package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;

public class Listener4Assist {
    private AssistExtend assistExtend;
    Listener4Callback callback;
    private final SparseArray<Listener4Model> modelList = new SparseArray<>();
    Listener4Model singleTaskModel;

    public interface AssistExtend {
        boolean dispatchBlockEnd(DownloadTask downloadTask, int i, Listener4Model listener4Model);

        boolean dispatchFetchProgress(DownloadTask downloadTask, int i, long j, Listener4Model listener4Model);

        boolean dispatchInfoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Model listener4Model);

        boolean dispatchTaskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Model listener4Model);

        Listener4Model inspectAddModel(Listener4Model listener4Model);
    }

    public interface Listener4Callback {
        void blockEnd(DownloadTask downloadTask, int i, BlockInfo blockInfo);

        void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Model listener4Model);

        void progress(DownloadTask downloadTask, long j);

        void progressBlock(DownloadTask downloadTask, int i, long j);

        void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Model listener4Model);
    }

    public void setCallback(Listener4Callback listener4Callback) {
        this.callback = listener4Callback;
    }

    public void setAssistExtend(AssistExtend assistExtend) {
        this.assistExtend = assistExtend;
    }

    private synchronized Listener4Model addAndGetModel(BreakpointInfo breakpointInfo) {
        Listener4Model listener4Model;
        SparseArray sparseArray = new SparseArray();
        int blockCount = breakpointInfo.getBlockCount();
        for (int i = 0; i < blockCount; i++) {
            sparseArray.put(i, Long.valueOf(breakpointInfo.getBlock(i).getCurrentOffset()));
        }
        listener4Model = new Listener4Model(breakpointInfo, breakpointInfo.getTotalOffset(), sparseArray);
        AssistExtend assistExtend = this.assistExtend;
        if (assistExtend != null) {
            listener4Model = assistExtend.inspectAddModel(listener4Model);
        }
        Listener4Model listener4Model2 = this.singleTaskModel;
        if (listener4Model2 == null || listener4Model2.info.getId() == breakpointInfo.getId()) {
            this.singleTaskModel = listener4Model;
        } else {
            this.modelList.put(breakpointInfo.getId(), listener4Model);
        }
        return listener4Model;
    }

    public Listener4Model getSingleTaskModel() {
        return this.singleTaskModel;
    }

    public Listener4Model findModel(int i) {
        Listener4Model listener4Model = this.singleTaskModel;
        if (listener4Model != null && listener4Model.info.getId() == i) {
            return this.singleTaskModel;
        }
        return this.modelList.get(i);
    }

    public void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z) {
        Listener4Callback listener4Callback;
        Listener4Model listener4ModelAddAndGetModel = addAndGetModel(breakpointInfo);
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchInfoReady(downloadTask, breakpointInfo, z, listener4ModelAddAndGetModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.infoReady(downloadTask, breakpointInfo, z, listener4ModelAddAndGetModel);
        }
    }

    public void fetchProgress(DownloadTask downloadTask, int i, long j) {
        Listener4Callback listener4Callback;
        Listener4Model listener4ModelFindModel = findModel(downloadTask.getId());
        if (listener4ModelFindModel == null) {
            return;
        }
        long jLongValue = listener4ModelFindModel.blockCurrentOffsetMap.get(i).longValue() + j;
        listener4ModelFindModel.blockCurrentOffsetMap.put(i, Long.valueOf(jLongValue));
        listener4ModelFindModel.currentOffset += j;
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchFetchProgress(downloadTask, i, j, listener4ModelFindModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.progressBlock(downloadTask, i, jLongValue);
            this.callback.progress(downloadTask, listener4ModelFindModel.currentOffset);
        }
    }

    public void fetchEnd(DownloadTask downloadTask, int i) {
        Listener4Callback listener4Callback;
        Listener4Model listener4ModelFindModel = findModel(downloadTask.getId());
        if (listener4ModelFindModel == null) {
            return;
        }
        AssistExtend assistExtend = this.assistExtend;
        if ((assistExtend == null || !assistExtend.dispatchBlockEnd(downloadTask, i, listener4ModelFindModel)) && (listener4Callback = this.callback) != null) {
            listener4Callback.blockEnd(downloadTask, i, listener4ModelFindModel.info.getBlock(i));
        }
    }

    public synchronized void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        Listener4Model listener4Model;
        int id = downloadTask.getId();
        Listener4Model listener4Model2 = this.singleTaskModel;
        if (listener4Model2 != null && listener4Model2.info.getId() == id) {
            listener4Model = this.singleTaskModel;
            this.singleTaskModel = null;
        } else {
            Listener4Model listener4Model3 = this.modelList.get(id);
            this.modelList.remove(id);
            listener4Model = listener4Model3;
        }
        if (listener4Model == null) {
            listener4Model = new Listener4Model(new BreakpointInfo(downloadTask.getId(), downloadTask.getUrl(), downloadTask.getParentFile(), downloadTask.getFilename()), 0L, new SparseArray());
            AssistExtend assistExtend = this.assistExtend;
            if (assistExtend != null) {
                listener4Model = assistExtend.inspectAddModel(listener4Model);
            }
        }
        AssistExtend assistExtend2 = this.assistExtend;
        if (assistExtend2 == null || !assistExtend2.dispatchTaskEnd(downloadTask, endCause, exc, listener4Model)) {
            Listener4Callback listener4Callback = this.callback;
            if (listener4Callback != null) {
                listener4Callback.taskEnd(downloadTask, endCause, exc, listener4Model);
            }
        }
    }

    public static class Listener4Model {
        SparseArray<Long> blockCurrentOffsetMap;
        long currentOffset;
        BreakpointInfo info;

        Listener4Model(BreakpointInfo breakpointInfo, long j, SparseArray<Long> sparseArray) {
            this.info = breakpointInfo;
            this.currentOffset = j;
            this.blockCurrentOffsetMap = sparseArray;
        }

        SparseArray<Long> getBlockCurrentOffsetMap() {
            return this.blockCurrentOffsetMap;
        }

        public long getCurrentOffset() {
            return this.currentOffset;
        }

        public long getBlockCurrentOffset(int i) {
            return this.blockCurrentOffsetMap.get(i).longValue();
        }

        public SparseArray<Long> cloneBlockCurrentOffsetMap() {
            return this.blockCurrentOffsetMap.clone();
        }

        public BreakpointInfo getInfo() {
            return this.info;
        }
    }
}
