package com.faceunity.core.camera;

import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.util.Range;
import android.util.Size;
import android.view.Surface;
import androidx.room.RoomDatabase;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.listener.OnFUCameraListener;
import com.faceunity.core.media.video.VideoRecordHelper;
import com.faceunity.core.utils.CameraUtils;
import com.faceunity.core.utils.FULogger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\t*\u0002\u00124\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001d\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020?2\u0006\u0010M\u001a\u00020?H\u0010¢\u0006\u0002\bNJ\r\u0010O\u001a\u00020KH\u0010¢\u0006\u0002\bPJ\r\u0010Q\u001a\u00020EH\u0010¢\u0006\u0002\bRJ\r\u0010S\u001a\u00020EH\u0010¢\u0006\u0002\bTJ\"\u0010U\u001a\u0004\u0018\u00010V2\u0006\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020E2\u0006\u0010Y\u001a\u00020EH\u0002J5\u0010Z\u001a\u00020K2\u0006\u0010[\u001a\u00020?2\u0006\u0010\\\u001a\u00020?2\u0006\u0010]\u001a\u00020E2\u0006\u0010^\u001a\u00020E2\u0006\u0010_\u001a\u00020?H\u0010¢\u0006\u0002\b`J\r\u0010a\u001a\u00020KH\u0010¢\u0006\u0002\bbJ\b\u0010c\u001a\u00020dH\u0002J\b\u0010e\u001a\u00020KH\u0002J\b\u0010f\u001a\u00020KH\u0017J\u0015\u0010g\u001a\u00020K2\u0006\u0010h\u001a\u00020EH\u0010¢\u0006\u0002\biJ\u0015\u0010j\u001a\u00020K2\u0006\u0010h\u001a\u00020EH\u0010¢\u0006\u0002\bkJ\b\u0010l\u001a\u00020KH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b(\u0010)R\u001c\u0010,\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\nR\u0010\u0010/\u001a\u0004\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0004\n\u0002\u00105R$\u00106\u001a\n\u0012\u0004\u0012\u000208\u0018\u000107X\u0086\u000e¢\u0006\u0010\n\u0002\u0010=\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001a\u0010>\u001a\u00020?X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010D\u001a\u00020EX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010I¨\u0006m"}, m1293d2 = {"Lcom/faceunity/core/camera/FUCamera2;", "Lcom/faceunity/core/camera/BaseCamera;", "cameraListener", "Lcom/faceunity/core/listener/OnFUCameraListener;", "(Lcom/faceunity/core/listener/OnFUCameraListener;)V", "mBackCameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "getMBackCameraCharacteristics", "()Landroid/hardware/camera2/CameraCharacteristics;", "setMBackCameraCharacteristics", "(Landroid/hardware/camera2/CameraCharacteristics;)V", "mCameraCaptureSession", "Landroid/hardware/camera2/CameraCaptureSession;", "getMCameraCaptureSession", "()Landroid/hardware/camera2/CameraCaptureSession;", "setMCameraCaptureSession", "(Landroid/hardware/camera2/CameraCaptureSession;)V", "mCameraCaptureSessionStateCallback", "com/faceunity/core/camera/FUCamera2$mCameraCaptureSessionStateCallback$1", "Lcom/faceunity/core/camera/FUCamera2$mCameraCaptureSessionStateCallback$1;", "mCameraDevice", "Landroid/hardware/camera2/CameraDevice;", "getMCameraDevice", "()Landroid/hardware/camera2/CameraDevice;", "setMCameraDevice", "(Landroid/hardware/camera2/CameraDevice;)V", "mCameraManager", "Landroid/hardware/camera2/CameraManager;", "mCaptureCallback", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "getMCaptureCallback", "()Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "mCaptureRequestBuilder", "Landroid/hardware/camera2/CaptureRequest$Builder;", "getMCaptureRequestBuilder", "()Landroid/hardware/camera2/CaptureRequest$Builder;", "setMCaptureRequestBuilder", "(Landroid/hardware/camera2/CaptureRequest$Builder;)V", "mFUCameraDataPool", "Lcom/faceunity/core/camera/FUCameraDataPool;", "getMFUCameraDataPool", "()Lcom/faceunity/core/camera/FUCameraDataPool;", "mFUCameraDataPool$delegate", "Lkotlin/Lazy;", "mFrontCameraCharacteristics", "getMFrontCameraCharacteristics", "setMFrontCameraCharacteristics", "mImageReader", "Landroid/media/ImageReader;", "mOnImageAvailableListener", "Landroid/media/ImageReader$OnImageAvailableListener;", "mStateCallback", "com/faceunity/core/camera/FUCamera2$mStateCallback$1", "Lcom/faceunity/core/camera/FUCamera2$mStateCallback$1;", "mYuvDataBufferArray", "", "", "getMYuvDataBufferArray", "()[[B", "setMYuvDataBufferArray", "([[B)V", "[[B", "mYuvDataBufferPosition", "", "getMYuvDataBufferPosition", "()I", "setMYuvDataBufferPosition", "(I)V", "mZoomValue", "", "getMZoomValue", "()F", "setMZoomValue", "(F)V", "changeResolution", "", "cameraWidth", "cameraHeight", "changeResolution$fu_core_all_featureRelease", "closeCamera", "closeCamera$fu_core_all_featureRelease", "getExposureCompensation", "getExposureCompensation$fu_core_all_featureRelease", "getZoom", "getZoom$fu_core_all_featureRelease", "getZoomRect", "Landroid/graphics/Rect;", "cameraCharacteristics", "zoomLevel", "maxDigitalZoom", "handleFocus", "viewWidth", "viewHeight", "rawX", "rawY", "areaSize", "handleFocus$fu_core_all_featureRelease", "initCameraInfo", "initCameraInfo$fu_core_all_featureRelease", "isMeteringAreaAFSupported", "", "logCameraParameters", "openCamera", "setExposureCompensation", "value", "setExposureCompensation$fu_core_all_featureRelease", "setZoom", "setZoom$fu_core_all_featureRelease", "startPreview", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUCamera2 extends BaseCamera {
    private final OnFUCameraListener cameraListener;
    private CameraCharacteristics mBackCameraCharacteristics;
    private CameraCaptureSession mCameraCaptureSession;
    private final FUCamera2$mCameraCaptureSessionStateCallback$1 mCameraCaptureSessionStateCallback;
    private CameraDevice mCameraDevice;
    private CameraManager mCameraManager;
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;
    private CaptureRequest.Builder mCaptureRequestBuilder;

    private final Lazy mFUCameraDataPool;
    private CameraCharacteristics mFrontCameraCharacteristics;
    private ImageReader mImageReader;
    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener;
    private final FUCamera2$mStateCallback$1 mStateCallback;
    private byte[][] mYuvDataBufferArray;
    private int mYuvDataBufferPosition;
    private float mZoomValue;

    public final FUCameraDataPool getMFUCameraDataPool() {
        return (FUCameraDataPool) this.mFUCameraDataPool.getValue();
    }

    public final void logCameraParameters() {
    }

    public FUCamera2(OnFUCameraListener cameraListener) {
        Intrinsics.checkParameterIsNotNull(cameraListener, "cameraListener");
        this.cameraListener = cameraListener;
        this.mZoomValue = 1.0f;
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
                        if (FUCamera2$mFUCameraDataPool$2.this.this$0.getMIsStopPreview()) {
                            return;
                        }
                        FUCamera2$mFUCameraDataPool$2.this.this$0.cameraListener.onPreviewFrame(previewData);
                    }
                });
            }
        });
        this.mOnImageAvailableListener = new ImageReader.OnImageAvailableListener() {
            @Override
            public final void onImageAvailable(ImageReader imageReader) {
                if (imageReader != null) {
                    try {
                        Image imageAcquireLatestImage = imageReader.acquireLatestImage();
                        if (imageAcquireLatestImage != null) {
                            byte[] bArr = null;
                            if (!this.this$0.getMIsStopPreview()) {
                                byte[][] mYuvDataBufferArray = this.this$0.getMYuvDataBufferArray();
                                if (mYuvDataBufferArray == null) {
                                    Intrinsics.throwNpe();
                                }
                                bArr = mYuvDataBufferArray[this.this$0.getMYuvDataBufferPosition()];
                                FUCamera2 fUCamera2 = this.this$0;
                                fUCamera2.setMYuvDataBufferPosition(fUCamera2.getMYuvDataBufferPosition() + 1);
                                int mYuvDataBufferPosition = fUCamera2.getMYuvDataBufferPosition();
                                byte[][] mYuvDataBufferArray2 = this.this$0.getMYuvDataBufferArray();
                                if (mYuvDataBufferArray2 == null) {
                                    Intrinsics.throwNpe();
                                }
                                fUCamera2.setMYuvDataBufferPosition(mYuvDataBufferPosition % mYuvDataBufferArray2.length);
                                CameraUtils.INSTANCE.YUV420ToNV21(imageAcquireLatestImage, bArr);
                            }
                            byte[] bArr2 = bArr;
                            imageAcquireLatestImage.close();
                            if (bArr2 != null) {
                                this.this$0.getMFUCameraDataPool().updateCPUData(new FUCameraPreviewData(bArr2, this.this$0.getMCameraFacing(), this.this$0.getMCameraOrientation(), this.this$0.getMCameraWidth(), this.this$0.getMCameraHeight()));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.mStateCallback = new CameraDevice.StateCallback() {
            @Override
            public void onOpened(CameraDevice camera) {
                Intrinsics.checkParameterIsNotNull(camera, "camera");
                this.this$0.setMCameraDevice(camera);
                this.this$0.logCameraParameters();
                this.this$0.startPreview();
            }

            @Override
            public void onDisconnected(CameraDevice camera) {
                Intrinsics.checkParameterIsNotNull(camera, "camera");
                camera.close();
                this.this$0.setMCameraDevice(null);
            }

            @Override
            public void onError(CameraDevice camera, int error) {
                Intrinsics.checkParameterIsNotNull(camera, "camera");
                camera.close();
                this.this$0.setMCameraDevice(null);
            }
        };
        this.mCameraCaptureSessionStateCallback = new CameraCaptureSession.StateCallback() {
            @Override
            public void onConfigureFailed(CameraCaptureSession session) {
                Intrinsics.checkParameterIsNotNull(session, "session");
                this.this$0.setMIsPreviewing(false);
            }

            @Override
            public void onConfigured(CameraCaptureSession session) {
                Intrinsics.checkParameterIsNotNull(session, "session");
                this.this$0.setMIsPreviewing(true);
                this.this$0.setMCameraCaptureSession(session);
                try {
                    CaptureRequest.Builder mCaptureRequestBuilder = this.this$0.getMCaptureRequestBuilder();
                    if (mCaptureRequestBuilder == null) {
                        Intrinsics.throwNpe();
                    }
                    session.setRepeatingRequest(mCaptureRequestBuilder.build(), this.this$0.getMCaptureCallback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        this.mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
            @Override
            public void onCaptureSequenceCompleted(CameraCaptureSession session, int sequenceId, long frameNumber) {
                Intrinsics.checkParameterIsNotNull(session, "session");
                super.onCaptureSequenceCompleted(session, sequenceId, frameNumber);
                CaptureRequest.Builder mCaptureRequestBuilder = this.this$0.getMCaptureRequestBuilder();
                if (mCaptureRequestBuilder == null) {
                    Intrinsics.throwNpe();
                }
                mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
                CaptureRequest.Builder mCaptureRequestBuilder2 = this.this$0.getMCaptureRequestBuilder();
                if (mCaptureRequestBuilder2 == null) {
                    Intrinsics.throwNpe();
                }
                mCaptureRequestBuilder2.set(CaptureRequest.CONTROL_AF_TRIGGER, 2);
                CaptureRequest.Builder mCaptureRequestBuilder3 = this.this$0.getMCaptureRequestBuilder();
                if (mCaptureRequestBuilder3 == null) {
                    Intrinsics.throwNpe();
                }
                mCaptureRequestBuilder3.set(CaptureRequest.CONTROL_AF_TRIGGER, null);
            }
        };
    }

    public final CameraCharacteristics getMFrontCameraCharacteristics() {
        return this.mFrontCameraCharacteristics;
    }

    public final void setMFrontCameraCharacteristics(CameraCharacteristics cameraCharacteristics) {
        this.mFrontCameraCharacteristics = cameraCharacteristics;
    }

    public final CameraCharacteristics getMBackCameraCharacteristics() {
        return this.mBackCameraCharacteristics;
    }

    public final void setMBackCameraCharacteristics(CameraCharacteristics cameraCharacteristics) {
        this.mBackCameraCharacteristics = cameraCharacteristics;
    }

    public final CaptureRequest.Builder getMCaptureRequestBuilder() {
        return this.mCaptureRequestBuilder;
    }

    public final void setMCaptureRequestBuilder(CaptureRequest.Builder builder) {
        this.mCaptureRequestBuilder = builder;
    }

    public final CameraDevice getMCameraDevice() {
        return this.mCameraDevice;
    }

    public final void setMCameraDevice(CameraDevice cameraDevice) {
        this.mCameraDevice = cameraDevice;
    }

    public final CameraCaptureSession getMCameraCaptureSession() {
        return this.mCameraCaptureSession;
    }

    public final void setMCameraCaptureSession(CameraCaptureSession cameraCaptureSession) {
        this.mCameraCaptureSession = cameraCaptureSession;
    }

    public final byte[][] getMYuvDataBufferArray() {
        return this.mYuvDataBufferArray;
    }

    public final void setMYuvDataBufferArray(byte[][] bArr) {
        this.mYuvDataBufferArray = bArr;
    }

    public final int getMYuvDataBufferPosition() {
        return this.mYuvDataBufferPosition;
    }

    public final void setMYuvDataBufferPosition(int i) {
        this.mYuvDataBufferPosition = i;
    }

    public final float getMZoomValue() {
        return this.mZoomValue;
    }

    public final void setMZoomValue(float f) {
        this.mZoomValue = f;
    }

    @Override
    public void initCameraInfo$fu_core_all_featureRelease() throws CameraAccessException {
        Object systemService = FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease().getSystemService("camera");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.hardware.camera2.CameraManager");
        }
        this.mCameraManager = (CameraManager) systemService;
        setMFrontCameraId(1);
        setMBackCameraId(0);
        CameraManager cameraManager = this.mCameraManager;
        if (cameraManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCameraManager");
        }
        String[] ids = cameraManager.getCameraIdList();
        Intrinsics.checkExpressionValueIsNotNull(ids, "ids");
        if (ids.length == 0) {
            FULogger.m294e(BaseCamera.TAG, "No camera");
            return;
        }
        for (String str : ids) {
            if (Intrinsics.areEqual(str, String.valueOf(getMFrontCameraId()))) {
                CameraManager cameraManager2 = this.mCameraManager;
                if (cameraManager2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCameraManager");
                }
                CameraCharacteristics cameraCharacteristics = cameraManager2.getCameraCharacteristics(str);
                this.mFrontCameraCharacteristics = cameraCharacteristics;
                if (cameraCharacteristics == null) {
                    Intrinsics.throwNpe();
                }
                Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
                setMFrontCameraOrientation(num != null ? num.intValue() : 270);
            } else if (Intrinsics.areEqual(str, String.valueOf(getMBackCameraId()))) {
                CameraManager cameraManager3 = this.mCameraManager;
                if (cameraManager3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mCameraManager");
                }
                CameraCharacteristics cameraCharacteristics2 = cameraManager3.getCameraCharacteristics(str);
                this.mBackCameraCharacteristics = cameraCharacteristics2;
                if (cameraCharacteristics2 == null) {
                    Intrinsics.throwNpe();
                }
                Integer num2 = (Integer) cameraCharacteristics2.get(CameraCharacteristics.SENSOR_ORIENTATION);
                setMBackCameraOrientation(num2 != null ? num2.intValue() : 90);
            }
        }
        setMCameraOrientation(getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? getMFrontCameraOrientation() : getMBackCameraOrientation());
    }

    @Override
    public void openCamera() {
        if (this.mCameraDevice != null) {
            return;
        }
        try {
            int mFrontCameraId = getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? getMFrontCameraId() : getMBackCameraId();
            CameraManager cameraManager = this.mCameraManager;
            if (cameraManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCameraManager");
            }
            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraManager.getCameraCharacteristics(String.valueOf(mFrontCameraId)).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap != null) {
                Size[] outputSizes = streamConfigurationMap.getOutputSizes(SurfaceTexture.class);
                CameraUtils cameraUtils = CameraUtils.INSTANCE;
                Intrinsics.checkExpressionValueIsNotNull(outputSizes, "outputSizes");
                Size sizeChooseOptimalSize = cameraUtils.chooseOptimalSize(outputSizes, getMCameraWidth(), getMCameraHeight(), VideoRecordHelper.MAX_VIDEO_LENGTH, 1080, new Size(getMCameraWidth(), getMCameraHeight()));
                setMCameraWidth$fu_core_all_featureRelease(sizeChooseOptimalSize.getWidth());
                setMCameraHeight$fu_core_all_featureRelease(sizeChooseOptimalSize.getHeight());
            }
            byte[][] bArr = new byte[3][];
            for (int i = 0; i < 3; i++) {
                bArr[i] = new byte[((getMCameraWidth() * getMCameraHeight()) * ImageFormat.getBitsPerPixel(35)) / 8];
            }
            this.mYuvDataBufferArray = bArr;
            ImageReader imageReaderNewInstance = ImageReader.newInstance(getMCameraWidth(), getMCameraHeight(), 35, 3);
            this.mImageReader = imageReaderNewInstance;
            if (imageReaderNewInstance == null) {
                Intrinsics.throwNpe();
            }
            imageReaderNewInstance.setOnImageAvailableListener(this.mOnImageAvailableListener, null);
            getMFUCameraDataPool().startBackgroundHandle();
            CameraManager cameraManager2 = this.mCameraManager;
            if (cameraManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mCameraManager");
            }
            cameraManager2.openCamera(String.valueOf(mFrontCameraId), this.mStateCallback, (Handler) null);
        } catch (CameraAccessException e) {
            this.mCameraDevice = null;
            e.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    @Override
    public void startPreview() {
        if (getMCameraTexId() == 0 || this.mCameraDevice == null || getMIsPreviewing()) {
            return;
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(getMCameraTexId());
        surfaceTexture.setDefaultBufferSize(getMCameraWidth(), getMCameraHeight());
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                this.this$0.getMFUCameraDataPool().updateGPUData();
            }
        });
        setMSurfaceTexture(surfaceTexture);
        try {
            Range<Integer> bestRange = CameraUtils.INSTANCE.getBestRange(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), String.valueOf(getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT ? getMFrontCameraId() : getMBackCameraId()), getMIsHighestRate());
            CameraDevice cameraDevice = this.mCameraDevice;
            if (cameraDevice == null) {
                Intrinsics.throwNpe();
            }
            CaptureRequest.Builder builderCreateCaptureRequest = cameraDevice.createCaptureRequest(1);
            if (bestRange != null) {
                builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, bestRange);
            }
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_MODE, 1);
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_LOCK, false);
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AE_MODE, 1);
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AF_MODE, 3);
            builderCreateCaptureRequest.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            Surface surface = new Surface(getMSurfaceTexture());
            builderCreateCaptureRequest.addTarget(surface);
            ImageReader imageReader = this.mImageReader;
            if (imageReader == null) {
                Intrinsics.throwNpe();
            }
            Surface surface2 = imageReader.getSurface();
            builderCreateCaptureRequest.addTarget(surface2);
            this.mCaptureRequestBuilder = builderCreateCaptureRequest;
            CameraDevice cameraDevice2 = this.mCameraDevice;
            if (cameraDevice2 == null) {
                Intrinsics.throwNpe();
            }
            cameraDevice2.createCaptureSession(CollectionsKt.arrayListOf(surface2, surface), this.mCameraCaptureSessionStateCallback, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeCamera$fu_core_all_featureRelease() {
        setMIsPreviewing(false);
        CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
        if (cameraCaptureSession != null) {
            if (cameraCaptureSession == null) {
                Intrinsics.throwNpe();
            }
            cameraCaptureSession.close();
            this.mCameraCaptureSession = null;
        }
        CameraDevice cameraDevice = this.mCameraDevice;
        if (cameraDevice != null) {
            if (cameraDevice == null) {
                Intrinsics.throwNpe();
            }
            cameraDevice.close();
            this.mCameraDevice = null;
        }
        ImageReader imageReader = this.mImageReader;
        if (imageReader != null) {
            if (imageReader == null) {
                Intrinsics.throwNpe();
            }
            imageReader.close();
            this.mImageReader = null;
        }
        SurfaceTexture mSurfaceTexture = getMSurfaceTexture();
        if (mSurfaceTexture != null) {
            mSurfaceTexture.release();
        }
        setMSurfaceTexture(null);
        getMFUCameraDataPool().stopBackgroundHandle();
    }

    public final CameraCaptureSession.CaptureCallback getMCaptureCallback() {
        return this.mCaptureCallback;
    }

    @Override
    public void handleFocus$fu_core_all_featureRelease(int viewWidth, int viewHeight, float rawX, float rawY, int areaSize) {
        CameraCharacteristics cameraCharacteristics;
        if (this.mCameraCaptureSession == null) {
            return;
        }
        if (!isMeteringAreaAFSupported()) {
            FULogger.m294e(BaseCamera.TAG, "handleFocus not supported");
            return;
        }
        if (getMCameraFacing() != CameraFacingEnum.CAMERA_FRONT ? (cameraCharacteristics = this.mBackCameraCharacteristics) == null : (cameraCharacteristics = this.mFrontCameraCharacteristics) == null) {
            Intrinsics.throwNpe();
        }
        Rect rect = (Rect) cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        float f = rawX / viewWidth;
        if (rect == null) {
            Intrinsics.throwNpe();
        }
        int iHeight = (int) (f * rect.height());
        int iWidth = (int) ((rawY / viewHeight) * rect.width());
        if (getMCameraOrientation() == 90) {
            iHeight = rect.height() - iHeight;
        }
        int i = areaSize / 2;
        int i2 = i * 2;
        MeteringRectangle meteringRectangle = new MeteringRectangle(RangesKt.coerceAtLeast(iWidth - i, 0), RangesKt.coerceAtLeast(iHeight - i, 0), i2, i2, RoomDatabase.MAX_BIND_PARAMETER_CNT);
        try {
            CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
            if (cameraCaptureSession == null) {
                Intrinsics.throwNpe();
            }
            cameraCaptureSession.stopRepeating();
            CaptureRequest.Builder builder = this.mCaptureRequestBuilder;
            if (builder == null) {
                Intrinsics.throwNpe();
            }
            builder.set(CaptureRequest.CONTROL_AF_TRIGGER, 0);
            CaptureRequest.Builder builder2 = this.mCaptureRequestBuilder;
            if (builder2 == null) {
                Intrinsics.throwNpe();
            }
            builder2.set(CaptureRequest.CONTROL_AF_MODE, 0);
            MeteringRectangle[] meteringRectangleArr = {meteringRectangle};
            CaptureRequest.Builder builder3 = this.mCaptureRequestBuilder;
            if (builder3 == null) {
                Intrinsics.throwNpe();
            }
            builder3.set(CaptureRequest.CONTROL_AF_REGIONS, meteringRectangleArr);
            CaptureRequest.Builder builder4 = this.mCaptureRequestBuilder;
            if (builder4 == null) {
                Intrinsics.throwNpe();
            }
            builder4.set(CaptureRequest.CONTROL_AF_MODE, 1);
            CaptureRequest.Builder builder5 = this.mCaptureRequestBuilder;
            if (builder5 == null) {
                Intrinsics.throwNpe();
            }
            builder5.set(CaptureRequest.CONTROL_AF_TRIGGER, 1);
            CameraCaptureSession cameraCaptureSession2 = this.mCameraCaptureSession;
            if (cameraCaptureSession2 == null) {
                Intrinsics.throwNpe();
            }
            CaptureRequest.Builder builder6 = this.mCaptureRequestBuilder;
            if (builder6 == null) {
                Intrinsics.throwNpe();
            }
            cameraCaptureSession2.setRepeatingRequest(builder6.build(), this.mCaptureCallback, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public float getMExposureCompensation() {
        CameraCharacteristics cameraCharacteristics;
        int iIntValue;
        int iIntValue2;
        Integer num;
        if (getMCameraFacing() != CameraFacingEnum.CAMERA_FRONT ? (cameraCharacteristics = this.mBackCameraCharacteristics) == null : (cameraCharacteristics = this.mFrontCameraCharacteristics) == null) {
            Intrinsics.throwNpe();
        }
        Range range = (Range) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        if (range != null) {
            Object lower = range.getLower();
            Intrinsics.checkExpressionValueIsNotNull(lower, "range.lower");
            iIntValue = ((Number) lower).intValue();
            Object upper = range.getUpper();
            Intrinsics.checkExpressionValueIsNotNull(upper, "range.upper");
            iIntValue2 = ((Number) upper).intValue();
        } else {
            iIntValue = -1;
            iIntValue2 = 1;
        }
        CaptureRequest.Builder builder = this.mCaptureRequestBuilder;
        return (((builder == null || (num = (Integer) builder.get(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION)) == null) ? 0 : num.intValue()) - iIntValue) / (iIntValue2 - iIntValue);
    }

    @Override
    public void setExposureCompensation$fu_core_all_featureRelease(float value) {
        CameraCharacteristics cameraCharacteristics;
        if (this.mCameraCaptureSession == null) {
            return;
        }
        if (getMCameraFacing() != CameraFacingEnum.CAMERA_FRONT ? (cameraCharacteristics = this.mBackCameraCharacteristics) == null : (cameraCharacteristics = this.mFrontCameraCharacteristics) == null) {
            Intrinsics.throwNpe();
        }
        Range range = (Range) cameraCharacteristics.get(CameraCharacteristics.CONTROL_AE_COMPENSATION_RANGE);
        if (range != null) {
            Integer min = (Integer) range.getLower();
            int iIntValue = ((Integer) range.getUpper()).intValue();
            Intrinsics.checkExpressionValueIsNotNull(min, "min");
            int iIntValue2 = (int) ((value * (iIntValue - min.intValue())) + min.intValue());
            CaptureRequest.Builder builder = this.mCaptureRequestBuilder;
            if (builder == null) {
                Intrinsics.throwNpe();
            }
            builder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION, Integer.valueOf(iIntValue2));
            try {
                CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
                if (cameraCaptureSession == null) {
                    Intrinsics.throwNpe();
                }
                CaptureRequest.Builder builder2 = this.mCaptureRequestBuilder;
                if (builder2 == null) {
                    Intrinsics.throwNpe();
                }
                cameraCaptureSession.setRepeatingRequest(builder2.build(), this.mCaptureCallback, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setZoom$fu_core_all_featureRelease(float value) {
        CameraCharacteristics cameraCharacteristics;
        if (this.mCameraCaptureSession == null) {
            return;
        }
        try {
            if (getMCameraFacing() == CameraFacingEnum.CAMERA_FRONT) {
                cameraCharacteristics = this.mFrontCameraCharacteristics;
                if (cameraCharacteristics == null) {
                    Intrinsics.throwNpe();
                }
            } else {
                cameraCharacteristics = this.mBackCameraCharacteristics;
                if (cameraCharacteristics == null) {
                    Intrinsics.throwNpe();
                }
            }
            Float maxZoom = (Float) cameraCharacteristics.get(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM);
            Intrinsics.checkExpressionValueIsNotNull(maxZoom, "maxZoom");
            if (value > maxZoom.floatValue()) {
                value = maxZoom.floatValue();
            } else if (value < 0) {
                value = 0.0f;
            }
            this.mZoomValue = value;
            Rect zoomRect = getZoomRect(cameraCharacteristics, value, maxZoom.floatValue());
            if (zoomRect != null) {
                CaptureRequest.Builder builder = this.mCaptureRequestBuilder;
                if (builder == null) {
                    Intrinsics.throwNpe();
                }
                builder.set(CaptureRequest.SCALER_CROP_REGION, zoomRect);
            }
            CameraCaptureSession cameraCaptureSession = this.mCameraCaptureSession;
            if (cameraCaptureSession == null) {
                Intrinsics.throwNpe();
            }
            CaptureRequest.Builder builder2 = this.mCaptureRequestBuilder;
            if (builder2 == null) {
                Intrinsics.throwNpe();
            }
            cameraCaptureSession.setRepeatingRequest(builder2.build(), this.mCaptureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float getZoom$fu_core_all_featureRelease() {
        return this.mZoomValue;
    }

    private final Rect getZoomRect(CameraCharacteristics cameraCharacteristics, float zoomLevel, float maxDigitalZoom) {
        Object obj = cameraCharacteristics.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        Intrinsics.checkExpressionValueIsNotNull(obj, "cameraCharacteristics.ge…R_INFO_ACTIVE_ARRAY_SIZE)");
        Rect rect = (Rect) obj;
        int iWidth = rect.width() - ((int) (rect.width() / maxDigitalZoom));
        int iHeight = rect.height() - ((int) (rect.height() / maxDigitalZoom));
        float f = iWidth;
        float f2 = 1;
        float f3 = zoomLevel - f2;
        float f4 = maxDigitalZoom - f2;
        int i = (int) (((f * f3) / f4) / 2.0f);
        int i2 = (int) (((iHeight * f3) / f4) / 2.0f);
        return new Rect(i, i2, rect.width() - i, rect.height() - i2);
    }

    @Override
    public void changeResolution$fu_core_all_featureRelease(int cameraWidth, int cameraHeight) {
        setMIsStopPreview(true);
        this.mYuvDataBufferArray = null;
        closeCamera$fu_core_all_featureRelease();
        openCamera();
        startPreview();
        setMIsStopPreview(false);
    }

    private final boolean isMeteringAreaAFSupported() {
        CameraCharacteristics cameraCharacteristics;
        if (getMCameraFacing() != CameraFacingEnum.CAMERA_FRONT ? (cameraCharacteristics = this.mBackCameraCharacteristics) == null : (cameraCharacteristics = this.mFrontCameraCharacteristics) == null) {
            Intrinsics.throwNpe();
        }
        Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.CONTROL_MAX_REGIONS_AF);
        return num != null && num.intValue() >= 1;
    }
}
