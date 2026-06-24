package com.liulishuo.okdownload.core.listener.assist;

import android.util.SparseArray;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;

public class Listener4SpeedAssistExtend implements Listener4Assist.AssistExtend {
    private Listener4SpeedCallback callback;

    public interface Listener4SpeedCallback {
        void blockEnd(DownloadTask downloadTask, int i, BlockInfo blockInfo, SpeedCalculator speedCalculator);

        void infoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4SpeedModel listener4SpeedModel);

        void progress(DownloadTask downloadTask, long j, SpeedCalculator speedCalculator);

        void progressBlock(DownloadTask downloadTask, int i, long j, SpeedCalculator speedCalculator);

        void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, SpeedCalculator speedCalculator);
    }

    public void setCallback(Listener4SpeedCallback listener4SpeedCallback) {
        this.callback = listener4SpeedCallback;
    }

    @Override
    public Listener4Assist.Listener4Model inspectAddModel(Listener4Assist.Listener4Model listener4Model) {
        return new Listener4SpeedModel(listener4Model);
    }

    @Override
    public boolean dispatchInfoReady(DownloadTask downloadTask, BreakpointInfo breakpointInfo, boolean z, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.infoReady(downloadTask, breakpointInfo, z, (Listener4SpeedModel) listener4Model);
        return true;
    }

    @Override
    public boolean dispatchFetchProgress(DownloadTask downloadTask, int i, long j, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).downloading(j);
        listener4SpeedModel.taskSpeed.downloading(j);
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.progressBlock(downloadTask, i, listener4Model.blockCurrentOffsetMap.get(i).longValue(), listener4SpeedModel.getBlockSpeed(i));
        this.callback.progress(downloadTask, listener4Model.currentOffset, listener4SpeedModel.taskSpeed);
        return true;
    }

    @Override
    public boolean dispatchBlockEnd(DownloadTask downloadTask, int i, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.blockSpeeds.get(i).endTask();
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.blockEnd(downloadTask, i, listener4Model.info.getBlock(i), listener4SpeedModel.getBlockSpeed(i));
        return true;
    }

    @Override
    public boolean dispatchTaskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener4Assist.Listener4Model listener4Model) {
        Listener4SpeedModel listener4SpeedModel = (Listener4SpeedModel) listener4Model;
        listener4SpeedModel.taskSpeed.endTask();
        Listener4SpeedCallback listener4SpeedCallback = this.callback;
        if (listener4SpeedCallback == null) {
            return true;
        }
        listener4SpeedCallback.taskEnd(downloadTask, endCause, exc, listener4SpeedModel.taskSpeed);
        return true;
    }

    public static class Listener4SpeedModel extends Listener4Assist.Listener4Model {
        final SparseArray<SpeedCalculator> blockSpeeds;
        final SpeedCalculator taskSpeed;

        public SpeedCalculator getTaskSpeed() {
            return this.taskSpeed;
        }

        public SpeedCalculator getBlockSpeed(int i) {
            return this.blockSpeeds.get(i);
        }

        Listener4SpeedModel(Listener4Assist.Listener4Model listener4Model, SpeedCalculator speedCalculator, SparseArray<SpeedCalculator> sparseArray) {
            super(listener4Model.info, listener4Model.currentOffset, listener4Model.blockCurrentOffsetMap);
            this.taskSpeed = speedCalculator;
            this.blockSpeeds = sparseArray;
        }

        public Listener4SpeedModel(Listener4Assist.Listener4Model listener4Model) {
            super(listener4Model.info, listener4Model.currentOffset, listener4Model.blockCurrentOffsetMap);
            this.taskSpeed = new SpeedCalculator();
            this.blockSpeeds = new SparseArray<>();
            int blockCount = this.info.getBlockCount();
            for (int i = 0; i < blockCount; i++) {
                this.blockSpeeds.put(i, new SpeedCalculator());
            }
        }
    }
}
