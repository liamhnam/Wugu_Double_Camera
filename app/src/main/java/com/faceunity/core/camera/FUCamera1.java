package com.faceunity.core.camera;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.listener.OnFUCameraListener;
import com.faceunity.core.utils.CameraUtils;
import com.faceunity.core.utils.FULogger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001b\u0018\u0000 22\u00020\u0001:\u00012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0010¢\u0006\u0002\b\u001aJ\r\u0010\u001b\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\bH\u0010¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\bH\u0010¢\u0006\u0002\b J5\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0018H\u0010¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\u0016H\u0010¢\u0006\u0002\b)J\b\u0010*\u001a\u00020\u0016H\u0002J\b\u0010+\u001a\u00020\u0016H\u0016J\u0015\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\bH\u0010¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\bH\u0010¢\u0006\u0002\b0J\b\u00101\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014¨\u00063"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCamera1;", "Lcom/faceunity/core/camera/BaseCamera;", "cameraListener", "Lcom/faceunity/core/listener/OnFUCameraListener;", "(Lcom/faceunity/core/listener/OnFUCameraListener;)V", "mCamera", "Landroid/hardware/Camera;", "mExposureCompensation", "", "mFUCameraDataPool", "Lcom/faceunity/core/camera/FUCameraDataPool;", "getMFUCameraDataPool", "()Lcom/faceunity/core/camera/FUCameraDataPool;", "mFUCameraDataPool$delegate", "Lkotlin/Lazy;", "mPreviewCallback", "Landroid/hardware/Camera$PreviewCallback;", "mPreviewCallbackBufferArray", "", "", "[[B", "changeResolution", "", "cameraWidth", "", "cameraHeight", "changeResolution$fu_core_all_featureRelease", "closeCamera", "closeCamera$fu_core_all_featureRelease", "getExposureCompensation", "getExposureCompensation$fu_core_all_featureRelease", "getZoom", "getZoom$fu_core_all_featureRelease", "handleFocus", "viewWidth", "viewHeight", "rawX", "rawY", "areaSize", "handleFocus$fu_core_all_featureRelease", "initCameraInfo", "initCameraInfo$fu_core_all_featureRelease", "logCameraParameters", "openCamera", "setExposureCompensation", "value", "setExposureCompensation$fu_core_all_featureRelease", "setZoom", "setZoom$fu_core_all_featureRelease", "startPreview", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCamera1 extends BaseCamera {
    private static final float EXPOSURE_COMPENSATION = 0.5f;
    private final OnFUCameraListener cameraListener;
    private Camera mCamera;
    private float mExposureCompensation;

    private final Lazy mFUCameraDataPool;
    private final Camera.PreviewCallback mPreviewCallback;
    private byte[][] mPreviewCallbackBufferArray;

    public final FUCameraDataPool getMFUCameraDataPool() {
        return (FUCameraDataPool) this.mFUCameraDataPool.getValue();
    }

    private final void logCameraParameters() {
    }

    public FUCamera1(OnFUCameraListener cameraListener) {
        Intrinsics.checkParameterIsNotNull(cameraListener, "cameraListener");
        this.cameraListener = cameraListener;
        this.mExposureCompensation = 0.5f;
        this.mFUCameraDataPool = LazyKt.lazy(new Function0<FUCameraDataPool>() {
            {
                super(0);
            }

            @Override
            public final FUCameraDataPool invoke() {
                return new FUCameraDataPool(new OnFUCameraListener() {
                    @Override
                    public void onPreviewFrame(FUCameraPreviewData previewData) {
                        Intrinsics.checkParameterIsNotNull(previewData, "previewData");
                        if (FUCamera1$mFUCameraDataPool$2.this.this$0.getMIsStopPreview()) {
                            return;
                        }
                        FUCamera1$mFUCameraDataPool$2.this.this$0.cameraListener.onPreviewFrame(previewData);
                    }
                });
            }
        });
        this.mPreviewCallback = new Camera.PreviewCallback() {
            @Override
            public final void onPreviewFrame(byte[] bArr, Camera camera) {
                Camera camera2 = this.this$0.mCamera;
                if (camera2 == null) {
                    Intrinsics.throwNpe();
                }
                camera2.addCallbackBuffer(bArr);
                if (this.this$0.getMIsStopPreview() || bArr == null) {
                    return;
                }
                this.this$0.getMFUCameraDataPool().updateCPUData(new FUCameraPreviewData(bArr, this.this$0.getMCameraFacing(), this.this$0.getMCameraOrientation(), this.this$0.getMCameraWidth(), this.this$0.getMCameraHeight()));
            }
        };
    }

    @Override
    public void initCameraInfo$fu_core_all_featureRelease() {
        int numberOfCameras = Camera.getNumberOfCameras();
        if (numberOfCameras <= 0) {
            FULogger.m294e(BaseCamera.TAG, "No camera");
            return;
        }
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                setMFrontCameraId(i);
                setMFrontCameraOrientation(cameraInfo.orientation);
            } else if (cameraInfo.facing == 0) {
                setMBackCameraId(i);
                setMBackCameraOrientation(cameraInfo.orientation);
            }
        }
        setMCameraOrientation(getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? getMFrontCameraOrientation() : getMBackCameraOrientation());
    }

    @Override
    public void openCamera() {
        if (this.mCamera != null) {
            return;
        }
        try {
            int mFrontCameraId = getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? getMFrontCameraId() : getMBackCameraId();
            Camera cameraOpen = Camera.open(mFrontCameraId);
            this.mCamera = cameraOpen;
            if (cameraOpen == null) {
                throw new RuntimeException("No camera");
            }
            this.mExposureCompensation = 0.5f;
            CameraUtils cameraUtils = CameraUtils.INSTANCE;
            Context mContext$fu_core_all_featureRelease = FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease();
            Camera camera = this.mCamera;
            if (camera == null) {
                Intrinsics.throwNpe();
            }
            cameraUtils.setCameraDisplayOrientation(mContext$fu_core_all_featureRelease, mFrontCameraId, camera);
            Camera camera2 = this.mCamera;
            if (camera2 == null) {
                Intrinsics.throwNpe();
            }
            Camera.Parameters parameters = camera2.getParameters();
            Intrinsics.checkExpressionValueIsNotNull(parameters, "mCamera!!.parameters");
            CameraUtils.INSTANCE.setFocusModes(parameters);
            CameraUtils.INSTANCE.chooseFrameRate(parameters, getMIsHighestRate());
            int[] iArrChoosePreviewSize = CameraUtils.INSTANCE.choosePreviewSize(parameters, getMCameraWidth(), getMCameraHeight());
            setMCameraWidth$fu_core_all_featureRelease(iArrChoosePreviewSize[0]);
            setMCameraHeight$fu_core_all_featureRelease(iArrChoosePreviewSize[1]);
            parameters.setPreviewFormat(17);
            CameraUtils.INSTANCE.setParameters(this.mCamera, parameters);
            logCameraParameters();
            getMFUCameraDataPool().startBackgroundHandle();
            startPreview();
        } catch (Exception e) {
            e.printStackTrace();
            FULogger.m294e(BaseCamera.TAG, "openCamera:" + e.getMessage());
        }
    }

    @Override
    public void startPreview() {
        if (getMCameraTexId() == 0 || this.mCamera == null || getMIsPreviewing()) {
            return;
        }
        try {
            Camera camera = this.mCamera;
            if (camera == null) {
                Intrinsics.throwNpe();
            }
            camera.stopPreview();
            if (this.mPreviewCallbackBufferArray == null) {
                byte[][] bArr = new byte[3][];
                for (int i = 0; i < 3; i++) {
                    bArr[i] = new byte[((getMCameraWidth() * getMCameraHeight()) * ImageFormat.getBitsPerPixel(17)) / 8];
                }
                this.mPreviewCallbackBufferArray = bArr;
            }
            Camera camera2 = this.mCamera;
            if (camera2 == null) {
                Intrinsics.throwNpe();
            }
            camera2.setPreviewCallbackWithBuffer(this.mPreviewCallback);
            byte[][] bArr2 = this.mPreviewCallbackBufferArray;
            if (bArr2 == null) {
                Intrinsics.throwNpe();
            }
            for (byte[] bArr3 : bArr2) {
                Camera camera3 = this.mCamera;
                if (camera3 == null) {
                    Intrinsics.throwNpe();
                }
                camera3.addCallbackBuffer(bArr3);
            }
            setMSurfaceTexture(new SurfaceTexture(getMCameraTexId()));
            SurfaceTexture mSurfaceTexture = getMSurfaceTexture();
            if (mSurfaceTexture != null) {
                mSurfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
                    @Override
                    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
                        FUCamera1.this.getMFUCameraDataPool().updateGPUData();
                    }
                });
            }
            Camera camera4 = this.mCamera;
            if (camera4 == null) {
                Intrinsics.throwNpe();
            }
            camera4.setPreviewTexture(getMSurfaceTexture());
            Camera camera5 = this.mCamera;
            if (camera5 == null) {
                Intrinsics.throwNpe();
            }
            camera5.startPreview();
            setMIsPreviewing(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeCamera$fu_core_all_featureRelease() {
        setMIsPreviewing(false);
        try {
            Camera camera = this.mCamera;
            if (camera != null) {
                if (camera == null) {
                    Intrinsics.throwNpe();
                }
                camera.stopPreview();
                Camera camera2 = this.mCamera;
                if (camera2 == null) {
                    Intrinsics.throwNpe();
                }
                camera2.setPreviewTexture(null);
                Camera camera3 = this.mCamera;
                if (camera3 == null) {
                    Intrinsics.throwNpe();
                }
                camera3.setPreviewCallbackWithBuffer(null);
                Camera camera4 = this.mCamera;
                if (camera4 == null) {
                    Intrinsics.throwNpe();
                }
                camera4.release();
                this.mCamera = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        SurfaceTexture mSurfaceTexture = getMSurfaceTexture();
        if (mSurfaceTexture != null) {
            mSurfaceTexture.release();
        }
        setMSurfaceTexture(null);
        getMFUCameraDataPool().stopBackgroundHandle();
    }

    @Override
    public void handleFocus$fu_core_all_featureRelease(int viewWidth, int viewHeight, float rawX, float rawY, int areaSize) {
        CameraUtils.INSTANCE.handleFocusMetering(this.mCamera, rawX, rawY, viewWidth, viewHeight, getMCameraWidth(), getMCameraHeight(), areaSize, getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? 1 : 0);
    }

    @Override
    public float getMExposureCompensation() {
        return this.mExposureCompensation;
    }

    @Override
    public void setExposureCompensation$fu_core_all_featureRelease(float value) {
        this.mExposureCompensation = value;
        CameraUtils.INSTANCE.setExposureCompensation(this.mCamera, value);
    }

    @Override
    public void setZoom$fu_core_all_featureRelease(float value) {
        CameraUtils.INSTANCE.setZoom(this.mCamera, value);
    }

    @Override
    public float getZoom$fu_core_all_featureRelease() {
        Camera.Parameters parameters;
        Camera camera = this.mCamera;
        if (camera == null || (parameters = camera.getParameters()) == null) {
            return 1.0f;
        }
        return parameters.getZoom();
    }

    @Override
    public void changeResolution$fu_core_all_featureRelease(int cameraWidth, int cameraHeight) {
        setMIsStopPreview(true);
        this.mPreviewCallbackBufferArray = null;
        closeCamera$fu_core_all_featureRelease();
        openCamera();
        startPreview();
        setMIsStopPreview(false);
    }
}
