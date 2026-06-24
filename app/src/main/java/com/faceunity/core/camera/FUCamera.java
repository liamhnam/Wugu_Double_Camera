package com.faceunity.core.camera;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.entity.FUCameraConfig;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.CameraTypeEnum;
import com.faceunity.core.infe.IFaceUnityCamera;
import com.faceunity.core.listener.OnFUCameraListener;
import com.faceunity.core.utils.FULogger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000q\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016*\u0001\u000f\u0018\u0000 B2\u00020\u0001:\u0001BB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0012H\u0016J\b\u0010!\u001a\u00020\u001eH\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\u0012H\u0002J\n\u0010$\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010'\u001a\u00020\u0012H\u0016J\b\u0010(\u001a\u00020\u0012H\u0016J\b\u0010)\u001a\u00020*H\u0016J\n\u0010+\u001a\u0004\u0018\u00010\u001aH\u0016J\n\u0010,\u001a\u0004\u0018\u00010-H\u0016J0\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020*2\u0006\u00102\u001a\u00020*2\u0006\u00103\u001a\u00020\u0012H\u0016J\u0018\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u0012H\u0002J\"\u00107\u001a\u00020\u001e2\u0006\u00105\u001a\u00020\u00182\u0006\u00106\u001a\u00020\u00122\b\u00108\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u00109\u001a\u00020\u001eH\u0016J\u0010\u0010:\u001a\u00020\u001e2\u0006\u0010;\u001a\u00020*H\u0016J\u0010\u0010<\u001a\u00020\u001e2\u0006\u0010;\u001a\u00020*H\u0016J\b\u0010=\u001a\u00020\u001eH\u0002J\b\u0010>\u001a\u00020\u001eH\u0002J\b\u0010?\u001a\u00020\u001eH\u0002J\b\u0010@\u001a\u00020\u001eH\u0002J\b\u0010A\u001a\u00020\u001eH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCamera;", "Lcom/faceunity/core/infe/IFaceUnityCamera;", "()V", "currentPreviewData", "Lcom/faceunity/core/camera/FUCameraPreviewData;", "isCameraOpen", "", "isFPSLoop", "isNeedFPSLoop", "isSwitchCamera", "mBackgroundHandler", "Landroid/os/Handler;", "mBackgroundHandlerThread", "Landroid/os/HandlerThread;", "mCameraListener", "com/faceunity/core/camera/FUCamera$mCameraListener$1", "Lcom/faceunity/core/camera/FUCamera$mCameraListener$1;", "mFPSNumber", "", "mFPSThread", "Ljava/lang/Thread;", "mFPSThreadLock", "", "mFUCameraConfig", "Lcom/faceunity/core/entity/FUCameraConfig;", "mFaceUnityCamera", "Lcom/faceunity/core/camera/BaseCamera;", "mOnCameraListener", "Lcom/faceunity/core/listener/OnFUCameraListener;", "changeResolution", "", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "closeCamera", "doSendPreviewFrame", "fps", "getCameraByte", "getCameraFacing", "Lcom/faceunity/core/enumeration/CameraFacingEnum;", "getCameraHeight", "getCameraWidth", "getExposureCompensation", "", "getFaceUnityCamera", "getSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "handleFocus", "viewWidth", "viewHeight", "rawX", "rawY", "areaSize", "initFUCamera", "config", "texId", "openCamera", "onCameraListener", "releaseCamera", "setExposureCompensation", "value", "setZoomValue", "startBackgroundThread", "startFPSLooper", "stopBackgroundThread", "stopFPSLooper", "switchCamera", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCamera implements IFaceUnityCamera {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FUCamera INSTANCE = null;
    public static final String TAG = "KIT_FaceUnityCamera";
    private FUCameraPreviewData currentPreviewData;
    private volatile boolean isCameraOpen;
    private boolean isFPSLoop;
    private boolean isNeedFPSLoop;
    private volatile boolean isSwitchCamera;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundHandlerThread;
    private final FUCamera$mCameraListener$1 mCameraListener;
    private int mFPSNumber;
    private Thread mFPSThread;
    private final Object mFPSThreadLock;
    private FUCameraConfig mFUCameraConfig;
    private BaseCamera mFaceUnityCamera;
    private OnFUCameraListener mOnCameraListener;

    @JvmStatic
    public static final FUCamera getInstance() {
        return INSTANCE.getInstance();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCamera$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/camera/FUCamera;", "TAG", "", "getInstance", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FUCamera getInstance() {
            if (FUCamera.INSTANCE == null) {
                synchronized (this) {
                    if (FUCamera.INSTANCE == null) {
                        FUCamera.INSTANCE = new FUCamera(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FUCamera fUCamera = FUCamera.INSTANCE;
            if (fUCamera == null) {
                Intrinsics.throwNpe();
            }
            return fUCamera;
        }
    }

    private FUCamera() {
        this.mFPSThreadLock = new Object();
        this.mCameraListener = new OnFUCameraListener() {
            @Override
            public void onPreviewFrame(FUCameraPreviewData previewData) {
                Intrinsics.checkParameterIsNotNull(previewData, "previewData");
                if (!this.this$0.isCameraOpen) {
                    this.this$0.isCameraOpen = true;
                }
                this.this$0.currentPreviewData = previewData;
                if (this.this$0.mFPSNumber > 0) {
                    if (this.this$0.isFPSLoop || !this.this$0.isNeedFPSLoop) {
                        return;
                    }
                    this.this$0.startFPSLooper();
                    return;
                }
                FULogger.m296t(FUCamera.TAG, "onPreviewFrame");
                OnFUCameraListener onFUCameraListener = this.this$0.mOnCameraListener;
                if (onFUCameraListener != null) {
                    onFUCameraListener.onPreviewFrame(previewData);
                }
            }
        };
    }

    public FUCamera(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override
    public void openCamera(final FUCameraConfig config, final int texId, final OnFUCameraListener onCameraListener) {
        Intrinsics.checkParameterIsNotNull(config, "config");
        startBackgroundThread();
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    BaseCamera baseCamera;
                    try {
                        FULogger.m295i(FUCamera.TAG, "openCamera");
                        FUCamera.this.isNeedFPSLoop = true;
                        FUCamera.this.mFUCameraConfig = config;
                        FUCamera.this.mOnCameraListener = onCameraListener;
                        if (FUCamera.this.isCameraOpen && (baseCamera = FUCamera.this.mFaceUnityCamera) != null) {
                            baseCamera.closeCamera$fu_core_all_featureRelease();
                        }
                        FUCamera fUCamera = FUCamera.this;
                        fUCamera.mFaceUnityCamera = fUCamera.initFUCamera(config, texId);
                        BaseCamera baseCamera2 = FUCamera.this.mFaceUnityCamera;
                        if (baseCamera2 != null) {
                            baseCamera2.openCamera();
                        }
                        FUCamera.this.isCameraOpen = true;
                    } catch (Exception e) {
                        Log.e(FUCamera.TAG, "camera open error", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void closeCamera() {
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    try {
                        FULogger.m295i(FUCamera.TAG, "closeCamera");
                        FUCamera.this.stopFPSLooper();
                        FUCamera.this.mFUCameraConfig = null;
                        FUCamera.this.mOnCameraListener = null;
                        FUCamera.this.currentPreviewData = null;
                        if (FUCamera.this.isCameraOpen) {
                            BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                            if (baseCamera != null) {
                                baseCamera.closeCamera$fu_core_all_featureRelease();
                            }
                            FUCamera.this.mFaceUnityCamera = null;
                            FUCamera.this.isCameraOpen = false;
                        }
                    } catch (Exception e) {
                        Log.e(FUCamera.TAG, "camera close error", e);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void switchCamera() {
        if (this.isSwitchCamera) {
            FULogger.m294e(TAG, "switchCamera so frequently");
            return;
        }
        this.isSwitchCamera = true;
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    FULogger.m295i(FUCamera.TAG, "switchCamera");
                    BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera != null) {
                        baseCamera.switchCamera();
                    }
                    FUCamera.this.isCameraOpen = true;
                    FUCamera.this.isSwitchCamera = false;
                }
            });
        }
    }

    @Override
    public void releaseCamera() {
        FULogger.m295i(TAG, "releaseCamera");
        stopBackgroundThread();
    }

    public final BaseCamera initFUCamera(FUCameraConfig config, int texId) {
        FUCamera2 fUCamera2;
        if (config.cameraType == CameraTypeEnum.CAMERA1) {
            fUCamera2 = new FUCamera1(this.mCameraListener);
        } else {
            fUCamera2 = new FUCamera2(this.mCameraListener);
        }
        this.mFPSNumber = config.cameraFPS;
        fUCamera2.setMCameraTexId(texId);
        fUCamera2.setMCameraFacing$fu_core_all_featureRelease(config.cameraFacing);
        fUCamera2.setMCameraHeight$fu_core_all_featureRelease(config.cameraHeight);
        fUCamera2.setMCameraWidth$fu_core_all_featureRelease(config.cameraWidth);
        fUCamera2.setMIsHighestRate$fu_core_all_featureRelease(config.isHighestRate);
        fUCamera2.initCameraInfo$fu_core_all_featureRelease();
        return fUCamera2;
    }

    private final void startBackgroundThread() {
        if (this.mBackgroundHandler == null) {
            HandlerThread handlerThread = new HandlerThread("KIT_FaceUnityCamera-CAMERA", 10);
            this.mBackgroundHandlerThread = handlerThread;
            handlerThread.start();
            HandlerThread handlerThread2 = this.mBackgroundHandlerThread;
            if (handlerThread2 == null) {
                Intrinsics.throwNpe();
            }
            this.mBackgroundHandler = new Handler(handlerThread2.getLooper());
        }
    }

    private final void stopBackgroundThread() {
        HandlerThread handlerThread = this.mBackgroundHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        this.mBackgroundHandlerThread = null;
        this.mBackgroundHandler = null;
    }

    @Override
    public CameraFacingEnum getCameraFacing() {
        FUCameraPreviewData fUCameraPreviewData = this.currentPreviewData;
        if (fUCameraPreviewData != null) {
            return fUCameraPreviewData.getCameraFacing();
        }
        return null;
    }

    @Override
    public int getCameraWidth() {
        FUCameraPreviewData fUCameraPreviewData = this.currentPreviewData;
        if (fUCameraPreviewData != null) {
            return fUCameraPreviewData.getWidth();
        }
        return 0;
    }

    @Override
    public int getCameraHeight() {
        FUCameraPreviewData fUCameraPreviewData = this.currentPreviewData;
        if (fUCameraPreviewData != null) {
            return fUCameraPreviewData.getHeight();
        }
        return 0;
    }

    @Override
    public FUCameraPreviewData getCurrentPreviewData() {
        return this.currentPreviewData;
    }

    @Override
    public SurfaceTexture getSurfaceTexture() {
        BaseCamera baseCamera = this.mFaceUnityCamera;
        if (baseCamera != null) {
            return baseCamera.getMSurfaceTexture();
        }
        return null;
    }

    @Override
    public void changeResolution(final int width, final int height) {
        FULogger.m295i(TAG, "changeResolution  width:" + width + "   height:" + height);
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera != null) {
                        baseCamera.setMCameraWidth$fu_core_all_featureRelease(width);
                    }
                    BaseCamera baseCamera2 = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera2 != null) {
                        baseCamera2.setMCameraHeight$fu_core_all_featureRelease(height);
                    }
                    BaseCamera baseCamera3 = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera3 != null) {
                        baseCamera3.changeResolution$fu_core_all_featureRelease(width, height);
                    }
                }
            });
        }
    }

    @Override
    public void handleFocus(final int viewWidth, final int viewHeight, final float rawX, final float rawY, final int areaSize) {
        FULogger.m295i(TAG, "handleFocus   viewWidth:" + viewWidth + "   viewHeight:" + viewHeight + "   rawX:" + rawX + "  rawY:" + rawY + "  areaSize:" + areaSize);
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera != null) {
                        baseCamera.handleFocus$fu_core_all_featureRelease(viewWidth, viewHeight, rawX, rawY, areaSize);
                    }
                }
            });
        }
    }

    @Override
    public float getExposureCompensation() {
        FULogger.m295i(TAG, "getExposureCompensation");
        BaseCamera baseCamera = this.mFaceUnityCamera;
        if (baseCamera != null) {
            return baseCamera.getMExposureCompensation();
        }
        return 0.0f;
    }

    @Override
    public void setExposureCompensation(final float value) {
        FULogger.m295i(TAG, "setExposureCompensation  value:" + value);
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera != null) {
                        baseCamera.setExposureCompensation$fu_core_all_featureRelease(value);
                    }
                }
            });
        }
    }

    @Override
    public void setZoomValue(final float value) {
        Handler handler = this.mBackgroundHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    BaseCamera baseCamera = FUCamera.this.mFaceUnityCamera;
                    if (baseCamera != null) {
                        baseCamera.setZoom$fu_core_all_featureRelease(value);
                    }
                }
            });
        }
    }

    @Override
    public BaseCamera getMFaceUnityCamera() {
        return this.mFaceUnityCamera;
    }

    public final void startFPSLooper() {
        FULogger.m295i(TAG, "startFPSLooper");
        synchronized (this.mFPSThreadLock) {
            this.isFPSLoop = true;
            if (this.mFPSThread == null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public final void run() {
                        FUCamera fUCamera = this.this$0;
                        fUCamera.doSendPreviewFrame(fUCamera.mFPSNumber);
                    }
                });
                this.mFPSThread = thread;
                thread.start();
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void stopFPSLooper() {
        FULogger.m295i(TAG, "stopFPSLooper");
        synchronized (this.mFPSThreadLock) {
            this.isFPSLoop = false;
            Thread thread = this.mFPSThread;
            if (thread != null) {
                thread.interrupt();
            }
            this.mFPSThread = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void doSendPreviewFrame(int fps) {
        long jCoerceAtLeast = ((long) 1000) / ((long) RangesKt.coerceAtLeast(10, RangesKt.coerceAtMost(100, fps)));
        boolean z = true;
        long jCurrentTimeMillis = 0;
        while (this.isFPSLoop) {
            if (z) {
                z = false;
            } else {
                try {
                    long jCurrentTimeMillis2 = jCoerceAtLeast - (System.currentTimeMillis() - jCurrentTimeMillis);
                    if (jCurrentTimeMillis2 > 0) {
                        Thread.sleep(jCurrentTimeMillis2);
                    }
                } catch (InterruptedException unused) {
                }
            }
            jCurrentTimeMillis = System.currentTimeMillis();
            if (this.currentPreviewData != null && this.isFPSLoop) {
                FULogger.m296t(TAG, "onPreviewFrame");
                OnFUCameraListener onFUCameraListener = this.mOnCameraListener;
                if (onFUCameraListener != null) {
                    FUCameraPreviewData fUCameraPreviewData = this.currentPreviewData;
                    if (fUCameraPreviewData == null) {
                        Intrinsics.throwNpe();
                    }
                    onFUCameraListener.onPreviewFrame(fUCameraPreviewData);
                }
            }
        }
    }
}
