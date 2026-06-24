package com.faceunity.core.camera;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.faceunity.core.listener.OnFUCameraListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0006\u0010\u0015\u001a\u00020\u0014J\u0006\u0010\u0016\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0012J\u0006\u0010\u0019\u001a\u00020\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCameraDataPool;", "", "listener", "Lcom/faceunity/core/listener/OnFUCameraListener;", "(Lcom/faceunity/core/listener/OnFUCameraListener;)V", "getListener", "()Lcom/faceunity/core/listener/OnFUCameraListener;", "mBackgroundHandler", "Lcom/faceunity/core/camera/FUCameraDataPool$BackgroundHandler;", "mBackgroundThread", "Landroid/os/HandlerThread;", "mBackgroundThreadLock", "mDelayedTime", "", "mDuration", "mFUCameraCPUTime", "mFUCameraGPUTime", "mFUCameraPreviewData", "Lcom/faceunity/core/camera/FUCameraPreviewData;", "callbackData", "", "startBackgroundHandle", "stopBackgroundHandle", "updateCPUData", "data", "updateGPUData", "BackgroundHandler", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCameraDataPool {
    public static final int MSG_WHAT = 10;
    private final OnFUCameraListener listener;
    private BackgroundHandler mBackgroundHandler;
    private HandlerThread mBackgroundThread;
    private final Object mBackgroundThreadLock;
    private final long mDelayedTime;
    private final long mDuration;
    private volatile long mFUCameraCPUTime;
    private volatile long mFUCameraGPUTime;
    private volatile FUCameraPreviewData mFUCameraPreviewData;

    public FUCameraDataPool(OnFUCameraListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        this.listener = listener;
        this.mDuration = 8000000L;
        this.mDelayedTime = 8000000 / ((long) DurationKt.NANOS_IN_MILLIS);
        this.mBackgroundThreadLock = new Object();
    }

    public final OnFUCameraListener getListener() {
        return this.listener;
    }

    public final void updateCPUData(FUCameraPreviewData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (this.mFUCameraPreviewData == null) {
            this.mFUCameraCPUTime = System.nanoTime();
            this.mFUCameraPreviewData = data;
            BackgroundHandler backgroundHandler = this.mBackgroundHandler;
            if (backgroundHandler != null) {
                backgroundHandler.removeMessages(10);
            }
            callbackData();
            return;
        }
        this.mFUCameraCPUTime = System.nanoTime();
        this.mFUCameraPreviewData = data;
        if (this.mFUCameraCPUTime - this.mFUCameraGPUTime < this.mDuration) {
            BackgroundHandler backgroundHandler2 = this.mBackgroundHandler;
            if (backgroundHandler2 != null) {
                backgroundHandler2.removeMessages(10);
            }
            callbackData();
        }
    }

    public final void updateGPUData() {
        if (this.mFUCameraPreviewData == null) {
            return;
        }
        this.mFUCameraGPUTime = System.nanoTime();
        if (this.mFUCameraGPUTime - this.mFUCameraCPUTime < this.mDuration) {
            BackgroundHandler backgroundHandler = this.mBackgroundHandler;
            if (backgroundHandler != null) {
                backgroundHandler.removeMessages(10);
            }
            callbackData();
            return;
        }
        BackgroundHandler backgroundHandler2 = this.mBackgroundHandler;
        if (backgroundHandler2 != null) {
            backgroundHandler2.removeMessages(10);
        }
        BackgroundHandler backgroundHandler3 = this.mBackgroundHandler;
        if (backgroundHandler3 != null) {
            backgroundHandler3.sendEmptyMessageDelayed(10, this.mDelayedTime);
        }
    }

    public final void callbackData() {
        FUCameraPreviewData fUCameraPreviewData = this.mFUCameraPreviewData;
        if (fUCameraPreviewData != null) {
            this.listener.onPreviewFrame(fUCameraPreviewData);
        }
    }

    public final void startBackgroundHandle() {
        synchronized (this.mBackgroundThreadLock) {
            if (this.mBackgroundThread == null) {
                HandlerThread handlerThread = new HandlerThread("FUCamera1DataPool");
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Intrinsics.checkExpressionValueIsNotNull(looper, "this.looper");
                this.mBackgroundHandler = new BackgroundHandler(looper, this);
                this.mBackgroundThread = handlerThread;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void stopBackgroundHandle() {
        synchronized (this.mBackgroundThreadLock) {
            BackgroundHandler backgroundHandler = this.mBackgroundHandler;
            if (backgroundHandler != null) {
                backgroundHandler.removeCallbacksAndMessages(0);
            }
            HandlerThread handlerThread = this.mBackgroundThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
            this.mBackgroundHandler = null;
            this.mBackgroundThread = null;
            this.mFUCameraPreviewData = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCameraDataPool$BackgroundHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "dataLopper", "Lcom/faceunity/core/camera/FUCameraDataPool;", "(Landroid/os/Looper;Lcom/faceunity/core/camera/FUCameraDataPool;)V", "getDataLopper", "()Lcom/faceunity/core/camera/FUCameraDataPool;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    private static final class BackgroundHandler extends Handler {
        private final FUCameraDataPool dataLopper;

        public BackgroundHandler(Looper looper, FUCameraDataPool dataLopper) {
            super(looper);
            Intrinsics.checkParameterIsNotNull(looper, "looper");
            Intrinsics.checkParameterIsNotNull(dataLopper, "dataLopper");
            this.dataLopper = dataLopper;
        }

        public final FUCameraDataPool getDataLopper() {
            return this.dataLopper;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg == null || msg.what != 10) {
                return;
            }
            this.dataLopper.callbackData();
        }
    }
}
