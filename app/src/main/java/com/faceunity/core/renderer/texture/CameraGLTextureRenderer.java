package com.faceunity.core.renderer.texture;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.EGLConfig;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.camera.FUCamera;
import com.faceunity.core.camera.FUCameraPreviewData;
import com.faceunity.core.entity.FUCameraConfig;
import com.faceunity.core.entity.FURenderFrameData;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.glview.GLTextureView;
import com.faceunity.core.infe.ICameraRenderer;
import com.faceunity.core.listener.OnFUCameraListener;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.media.photo.OnPhotoRecordingListener;
import com.faceunity.core.media.photo.PhotoRecordHelper;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.program.ProgramTextureOES;
import com.faceunity.core.utils.DecimalUtils;
import com.faceunity.core.utils.GlUtil;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0002.F\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010:\u001a\u00020;H\u0014J\b\u0010<\u001a\u00020=H\u0002J\b\u0010>\u001a\u00020=H\u0016J\b\u0010?\u001a\u00020=H\u0002J\b\u0010@\u001a\u00020=H\u0014J\b\u0010A\u001a\u00020=H\u0002J\b\u0010B\u001a\u00020=H\u0014J\u0010\u0010C\u001a\u00020=2\u0006\u0010D\u001a\u00020\u0011H\u0016J\r\u0010E\u001a\u00020FH\u0002¢\u0006\u0002\u0010GJ\b\u0010H\u001a\u00020=H\u0016J\b\u0010I\u001a\u00020=H\u0016J\b\u0010J\u001a\u00020=H\u0016J\u0018\u0010K\u001a\u00020=2\u0006\u0010L\u001a\u00020;2\u0006\u0010M\u001a\u00020NH\u0014J\b\u0010O\u001a\u00020=H\u0016J \u0010P\u001a\u00020=2\u0006\u0010Q\u001a\u00020\u001a2\u0006\u0010R\u001a\u00020\u001a2\u0006\u0010S\u001a\u00020\u001aH\u0016J\b\u0010T\u001a\u00020\u0011H\u0014J\b\u0010U\u001a\u00020=H\u0016J\u0010\u0010V\u001a\u00020=2\u0006\u0010W\u001a\u00020\u0016H\u0016J\u0018\u0010X\u001a\u00020=2\u0006\u0010Y\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020\u001aH\u0014J\u0012\u0010[\u001a\u00020=2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0014J\b\u0010^\u001a\u00020=H\u0016J\b\u0010_\u001a\u00020=H\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010'\u001a\n )*\u0004\u0018\u00010(0(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\"\u001a\u0004\b*\u0010+R\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/R\u001b\u00100\u001a\u0002018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b4\u0010\"\u001a\u0004\b2\u00103R\u001a\u00105\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109¨\u0006`"}, m1293d2 = {"Lcom/faceunity/core/renderer/texture/CameraGLTextureRenderer;", "Lcom/faceunity/core/renderer/texture/BaseFUTextureRenderer;", "Lcom/faceunity/core/infe/ICameraRenderer;", "gLTextureView", "Lcom/faceunity/core/glview/GLTextureView;", "cameraConfig", "Lcom/faceunity/core/entity/FUCameraConfig;", "glRendererListener", "Lcom/faceunity/core/listener/OnGlRendererListener;", "(Lcom/faceunity/core/glview/GLTextureView;Lcom/faceunity/core/entity/FUCameraConfig;Lcom/faceunity/core/listener/OnGlRendererListener;)V", "fUCamera", "Lcom/faceunity/core/camera/FUCamera;", "getFUCamera", "()Lcom/faceunity/core/camera/FUCamera;", "setFUCamera", "(Lcom/faceunity/core/camera/FUCamera;)V", "isCameraPreviewFrame", "", "()Z", "setCameraPreviewFrame", "(Z)V", "mCacheBitmap", "Landroid/graphics/Bitmap;", "mCacheBitmapMvpMatrix", "", "mCacheBitmapTexId", "", "mFURenderInputDataLock", "", "mOnPhotoRecordingListener", "Lcom/faceunity/core/media/photo/OnPhotoRecordingListener;", "getMOnPhotoRecordingListener", "()Lcom/faceunity/core/media/photo/OnPhotoRecordingListener;", "mOnPhotoRecordingListener$delegate", "Lkotlin/Lazy;", "mPhotoRecordHelper", "Lcom/faceunity/core/media/photo/PhotoRecordHelper;", "mProgramTextureOES", "Lcom/faceunity/core/program/ProgramTextureOES;", "mSensor", "Landroid/hardware/Sensor;", "kotlin.jvm.PlatformType", "getMSensor", "()Landroid/hardware/Sensor;", "mSensor$delegate", "mSensorEventListener", "com/faceunity/core/renderer/texture/CameraGLTextureRenderer$mSensorEventListener$1", "Lcom/faceunity/core/renderer/texture/CameraGLTextureRenderer$mSensorEventListener$1;", "mSensorManager", "Landroid/hardware/SensorManager;", "getMSensorManager", "()Landroid/hardware/SensorManager;", "mSensorManager$delegate", "openCameraIgnoreFrame", "getOpenCameraIgnoreFrame", "()I", "setOpenCameraIgnoreFrame", "(I)V", "buildFURenderInputData", "Lcom/faceunity/core/entity/FURenderInputData;", "cacheLastBitmap", "", "closeCamera", "deleteCacheBitmapTexId", "destroyGlSurface", "drawCacheBitmap", "drawRenderFrame", "drawSmallViewport", "isShow", "getFUCameraListener", "com/faceunity/core/renderer/texture/CameraGLTextureRenderer$getFUCameraListener$1", "()Lcom/faceunity/core/renderer/texture/CameraGLTextureRenderer$getFUCameraListener$1;", "hideImageTexture", "onDestroy", "onPause", "onRenderBefore", "input", "fuRenderFrameData", "Lcom/faceunity/core/entity/FURenderFrameData;", "onResume", "onTouchEvent", "x", "y", "action", "prepareRender", "reopenCamera", "showImageTexture", "bitmap", "surfaceChanged", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "surfaceCreated", "config", "Landroid/opengl/EGLConfig;", "switchCamera", "updateTexImage", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class CameraGLTextureRenderer extends BaseFUTextureRenderer implements ICameraRenderer {
    private final FUCameraConfig cameraConfig;
    private FUCamera fUCamera;
    private volatile boolean isCameraPreviewFrame;
    private Bitmap mCacheBitmap;
    private float[] mCacheBitmapMvpMatrix;
    private int mCacheBitmapTexId;
    private final Object mFURenderInputDataLock;

    private final Lazy mOnPhotoRecordingListener;
    private final PhotoRecordHelper mPhotoRecordHelper;
    private ProgramTextureOES mProgramTextureOES;

    private final Lazy mSensor;
    private final CameraGLTextureRenderer$mSensorEventListener$1 mSensorEventListener;

    private final Lazy mSensorManager;
    private int openCameraIgnoreFrame;

    private final OnPhotoRecordingListener getMOnPhotoRecordingListener() {
        return (OnPhotoRecordingListener) this.mOnPhotoRecordingListener.getValue();
    }

    private final Sensor getMSensor() {
        return (Sensor) this.mSensor.getValue();
    }

    public final SensorManager getMSensorManager() {
        return (SensorManager) this.mSensorManager.getValue();
    }

    public CameraGLTextureRenderer(GLTextureView gLTextureView, FUCameraConfig cameraConfig, OnGlRendererListener onGlRendererListener) {
        super(gLTextureView, onGlRendererListener);
        Intrinsics.checkParameterIsNotNull(cameraConfig, "cameraConfig");
        this.cameraConfig = cameraConfig;
        this.fUCamera = FUCamera.INSTANCE.getInstance();
        this.mSensorManager = LazyKt.lazy(new Function0<SensorManager>() {
            @Override
            public final SensorManager invoke() {
                Object systemService = FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease().getSystemService("sensor");
                if (systemService != null) {
                    return (SensorManager) systemService;
                }
                throw new TypeCastException("null cannot be cast to non-null type android.hardware.SensorManager");
            }
        });
        this.mSensor = LazyKt.lazy(new Function0<Sensor>() {
            {
                super(0);
            }

            @Override
            public final Sensor invoke() {
                return this.this$0.getMSensorManager().getDefaultSensor(1);
            }
        });
        this.mFURenderInputDataLock = new Object();
        setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_CAMERA);
        setInputTextureType(FUInputTextureEnum.FU_ADM_FLAG_EXTERNAL_OES_TEXTURE);
        setInputBufferType(FUInputBufferEnum.FU_FORMAT_NV21_BUFFER);
        if (gLTextureView != null) {
            gLTextureView.setEGLContextClientVersion(2);
        }
        if (gLTextureView != null) {
            gLTextureView.setRenderer(this);
        }
        if (gLTextureView != null) {
            gLTextureView.setRenderMode(0);
        }
        this.mSensorEventListener = new SensorEventListener() {
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event == null) {
                    Intrinsics.throwNpe();
                }
                Sensor sensor = event.sensor;
                Intrinsics.checkExpressionValueIsNotNull(sensor, "event!!.sensor");
                if (sensor.getType() == 1) {
                    int i = 0;
                    float f = event.values[0];
                    float f2 = event.values[1];
                    float f3 = 3;
                    if (Math.abs(f) > f3 || Math.abs(f2) > f3) {
                        CameraGLTextureRenderer cameraGLTextureRenderer = this.this$0;
                        if (Math.abs(f) <= Math.abs(f2)) {
                            i = f2 > ((float) 0) ? 90 : 270;
                        } else if (f <= 0) {
                            i = 180;
                        }
                        cameraGLTextureRenderer.setDeviceOrientation(i);
                    }
                }
            }
        };
        float[] texture_matrix = getTEXTURE_MATRIX();
        float[] fArrCopyOf = Arrays.copyOf(texture_matrix, texture_matrix.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        this.mCacheBitmapMvpMatrix = fArrCopyOf;
        this.mOnPhotoRecordingListener = LazyKt.lazy(new Function0<OnPhotoRecordingListener>() {
            {
                super(0);
            }

            @Override
            public final OnPhotoRecordingListener invoke() {
                return new OnPhotoRecordingListener() {
                    @Override
                    public final void onRecordSuccess(Bitmap bitmap) {
                        CameraGLTextureRenderer$mOnPhotoRecordingListener$2.this.this$0.mCacheBitmap = bitmap;
                    }
                };
            }
        });
        this.mPhotoRecordHelper = new PhotoRecordHelper(getMOnPhotoRecordingListener());
    }

    public final FUCamera getFUCamera() {
        return this.fUCamera;
    }

    public final void setFUCamera(FUCamera fUCamera) {
        Intrinsics.checkParameterIsNotNull(fUCamera, "<set-?>");
        this.fUCamera = fUCamera;
    }

    public final boolean getIsCameraPreviewFrame() {
        return this.isCameraPreviewFrame;
    }

    public final void setCameraPreviewFrame(boolean z) {
        this.isCameraPreviewFrame = z;
    }

    private final C10271 getFUCameraListener() {
        return new OnFUCameraListener() {
            @Override
            public void onPreviewFrame(FUCameraPreviewData previewData) {
                Intrinsics.checkParameterIsNotNull(previewData, "previewData");
                synchronized (CameraGLTextureRenderer.this.mFURenderInputDataLock) {
                    if (CameraGLTextureRenderer.this.getOriginalWidth() != previewData.getWidth() || CameraGLTextureRenderer.this.getOriginalHeight() != previewData.getHeight()) {
                        CameraGLTextureRenderer.this.setOriginalWidth(previewData.getWidth());
                        CameraGLTextureRenderer.this.setOriginalHeight(previewData.getHeight());
                        CameraGLTextureRenderer cameraGLTextureRenderer = CameraGLTextureRenderer.this;
                        float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(cameraGLTextureRenderer.getSurfaceViewWidth(), CameraGLTextureRenderer.this.getSurfaceViewHeight(), CameraGLTextureRenderer.this.getOriginalHeight(), CameraGLTextureRenderer.this.getOriginalWidth());
                        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr… originalWidth.toFloat())");
                        cameraGLTextureRenderer.setDefaultFUMvpMatrix(fArrChangeMvpMatrixCrop);
                        CameraGLTextureRenderer cameraGLTextureRenderer2 = CameraGLTextureRenderer.this;
                        float[] fArrChangeMvpMatrixCrop2 = GlUtil.changeMvpMatrixCrop(90.0f, 160.0f, cameraGLTextureRenderer2.getOriginalHeight(), CameraGLTextureRenderer.this.getOriginalWidth());
                        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop2, "GlUtil.changeMvpMatrixCr… originalWidth.toFloat())");
                        cameraGLTextureRenderer2.setSmallViewMatrix(fArrChangeMvpMatrixCrop2);
                    }
                    CameraGLTextureRenderer.this.cameraConfig.cameraFacing = previewData.getCameraFacing();
                    CameraGLTextureRenderer.this.cameraConfig.cameraHeight = previewData.getHeight();
                    CameraGLTextureRenderer.this.cameraConfig.cameraWidth = previewData.getWidth();
                    CameraGLTextureRenderer cameraGLTextureRenderer3 = CameraGLTextureRenderer.this;
                    FURenderInputData fURenderInputData = new FURenderInputData(CameraGLTextureRenderer.this.getOriginalWidth(), CameraGLTextureRenderer.this.getOriginalHeight());
                    fURenderInputData.setImageBuffer(new FURenderInputData.FUImageBuffer(CameraGLTextureRenderer.this.getInputBufferType(), previewData.getBuffer(), null, null, 12, null));
                    fURenderInputData.setTexture(new FURenderInputData.FUTexture(CameraGLTextureRenderer.this.getInputTextureType(), CameraGLTextureRenderer.this.getOriginalTextId()));
                    FURenderInputData.FURenderConfig renderConfig = fURenderInputData.getRenderConfig();
                    renderConfig.setExternalInputType(CameraGLTextureRenderer.this.getExternalInputType());
                    renderConfig.setInputOrientation(previewData.getCameraOrientation());
                    renderConfig.setDeviceOrientation(CameraGLTextureRenderer.this.getDeviceOrientation());
                    renderConfig.setCameraFacing(previewData.getCameraFacing());
                    if (renderConfig.getCameraFacing() == CameraFacingEnum.CAMERA_FRONT) {
                        CameraGLTextureRenderer cameraGLTextureRenderer4 = CameraGLTextureRenderer.this;
                        float[] fArrCopyArray = DecimalUtils.copyArray(cameraGLTextureRenderer4.getCAMERA_TEXTURE_MATRIX());
                        Intrinsics.checkExpressionValueIsNotNull(fArrCopyArray, "DecimalUtils.copyArray(CAMERA_TEXTURE_MATRIX)");
                        cameraGLTextureRenderer4.setOriginTexMatrix(fArrCopyArray);
                        renderConfig.setInputTextureMatrix(FUTransformMatrixEnum.CCROT90_FLIPHORIZONTAL);
                        renderConfig.setInputBufferMatrix(FUTransformMatrixEnum.CCROT90_FLIPHORIZONTAL);
                    } else {
                        CameraGLTextureRenderer cameraGLTextureRenderer5 = CameraGLTextureRenderer.this;
                        float[] fArrCopyArray2 = DecimalUtils.copyArray(cameraGLTextureRenderer5.getCAMERA_TEXTURE_MATRIX_BACK());
                        Intrinsics.checkExpressionValueIsNotNull(fArrCopyArray2, "DecimalUtils.copyArray(CAMERA_TEXTURE_MATRIX_BACK)");
                        cameraGLTextureRenderer5.setOriginTexMatrix(fArrCopyArray2);
                        renderConfig.setInputTextureMatrix(FUTransformMatrixEnum.CCROT270);
                        renderConfig.setInputBufferMatrix(FUTransformMatrixEnum.CCROT270);
                    }
                    cameraGLTextureRenderer3.setCurrentFURenderInputData(fURenderInputData);
                    CameraGLTextureRenderer.this.setCameraPreviewFrame(true);
                    Unit unit = Unit.INSTANCE;
                }
                GLTextureView gLTextureView = CameraGLTextureRenderer.this.getGLTextureView();
                if (gLTextureView != null) {
                    gLTextureView.requestRender();
                }
            }
        };
    }

    @Override
    public void onResume() {
        GLTextureView gLTextureView;
        getMSensorManager().registerListener(this.mSensorEventListener, getMSensor(), 3);
        if (getIsActivityPause() && (gLTextureView = getGLTextureView()) != null) {
            gLTextureView.onResume();
        }
        setActivityPause(false);
    }

    @Override
    public void onPause() {
        setActivityPause(true);
        getMSensorManager().unregisterListener(this.mSensorEventListener);
        this.fUCamera.closeCamera();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        GLTextureView gLTextureView = getGLTextureView();
        if (gLTextureView != null) {
            gLTextureView.queueEvent(new Runnable() {
                @Override
                public final void run() {
                    CameraGLTextureRenderer.this.cacheLastBitmap();
                    CameraGLTextureRenderer.this.destroyGlSurface();
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        GLTextureView gLTextureView2 = getGLTextureView();
        if (gLTextureView2 != null) {
            gLTextureView2.onPause();
        }
    }

    @Override
    public void onDestroy() {
        this.mCacheBitmap = null;
        setGlRendererListener(null);
        setGLTextureView(null);
    }

    @Override
    protected void surfaceCreated(EGLConfig config) {
        setOriginalTextId(GlUtil.createTextureObject(36197));
        this.mProgramTextureOES = new ProgramTextureOES();
        this.isCameraPreviewFrame = false;
        this.fUCamera.openCamera(this.cameraConfig, getOriginalTextId(), getFUCameraListener());
    }

    @Override
    protected void surfaceChanged(int width, int height) {
        float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(width, height, getOriginalHeight(), getOriginalWidth());
        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr… originalWidth.toFloat())");
        setDefaultFUMvpMatrix(fArrChangeMvpMatrixCrop);
    }

    @Override
    protected void updateTexImage() {
        SurfaceTexture surfaceTexture = this.fUCamera.getSurfaceTexture();
        if (surfaceTexture != null) {
            try {
                surfaceTexture.updateTexImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected boolean prepareRender() {
        if (this.isCameraPreviewFrame) {
            return (this.mProgramTextureOES == null || getProgramTexture2d() == null) ? false : true;
        }
        drawCacheBitmap();
        return false;
    }

    @Override
    protected FURenderInputData buildFURenderInputData() {
        FURenderInputData currentFURenderInputData;
        synchronized (this.mFURenderInputDataLock) {
            getCurrentFURenderInputData().clone();
            int i = this.openCameraIgnoreFrame;
            if (i > 0) {
                this.openCameraIgnoreFrame = i - 1;
                getCurrentFURenderInputData().setImageBuffer(null);
                getCurrentFURenderInputData().setTexture(null);
            }
            currentFURenderInputData = getCurrentFURenderInputData();
        }
        return currentFURenderInputData;
    }

    @Override
    protected void onRenderBefore(FURenderInputData input, FURenderFrameData fuRenderFrameData) {
        Intrinsics.checkParameterIsNotNull(input, "input");
        Intrinsics.checkParameterIsNotNull(fuRenderFrameData, "fuRenderFrameData");
        FURenderInputData.FUImageBuffer imageBuffer = input.getImageBuffer();
        if ((imageBuffer != null ? imageBuffer.getInputBufferType() : null) == FUInputBufferEnum.FU_FORMAT_YUV_BUFFER && input.getRenderConfig().getIsNeedBufferReturn()) {
            float[] texture_matrix_ccro_flipv_0 = getTEXTURE_MATRIX_CCRO_FLIPV_0();
            float[] fArrCopyOf = Arrays.copyOf(texture_matrix_ccro_flipv_0, texture_matrix_ccro_flipv_0.length);
            Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
            fuRenderFrameData.setTexMatrix(fArrCopyOf);
            input.getRenderConfig().setOutputMatrix(FUTransformMatrixEnum.CCROT0_FLIPVERTICAL);
            input.getRenderConfig().setOutputMatrixEnable(true);
        }
    }

    @Override
    protected void drawRenderFrame() {
        if (getFaceUnity2DTexId() > 0 && getRenderSwitch()) {
            ProgramTexture2d programTexture2d = getProgramTexture2d();
            if (programTexture2d == null) {
                Intrinsics.throwNpe();
            }
            programTexture2d.drawFrame(getFaceUnity2DTexId(), getCurrentFUTexMatrix(), getCurrentFUMvpMatrix());
        } else if (getOriginalTextId() > 0) {
            ProgramTextureOES programTextureOES = this.mProgramTextureOES;
            if (programTextureOES == null) {
                Intrinsics.throwNpe();
            }
            programTextureOES.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getDefaultFUMvpMatrix());
        }
        if (getDrawSmallViewport()) {
            GLES20.glViewport(getSmallViewportX(), getSmallViewportY(), getSmallViewportWidth(), getSmallViewportHeight());
            ProgramTextureOES programTextureOES2 = this.mProgramTextureOES;
            if (programTextureOES2 == null) {
                Intrinsics.throwNpe();
            }
            programTextureOES2.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getSmallViewMatrix());
            GLES20.glViewport(0, 0, getSurfaceViewWidth(), getSurfaceViewHeight());
        }
    }

    @Override
    protected void destroyGlSurface() {
        ProgramTextureOES programTextureOES = this.mProgramTextureOES;
        if (programTextureOES != null) {
            programTextureOES.release();
            this.mProgramTextureOES = null;
        }
        deleteCacheBitmapTexId();
        super.destroyGlSurface();
    }

    @Override
    public void showImageTexture(Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        drawImageTexture(bitmap);
    }

    @Override
    public void hideImageTexture() {
        dismissImageTexture();
    }

    @Override
    public void drawSmallViewport(boolean isShow) {
        setDrawSmallViewport(isShow);
    }

    @Override
    public void onTouchEvent(int x, int y, int action) {
        if (getDrawSmallViewport()) {
            if (action != 2) {
                if (action == 0) {
                    setTouchX(x);
                    setTouchY(y);
                    return;
                }
                if (action == 1) {
                    setSmallViewportX(getSmallViewportX() < getSurfaceViewWidth() / 2 ? getSmallViewportHorizontalPadding() : (getSurfaceViewWidth() - getSmallViewportHorizontalPadding()) - getSmallViewportWidth());
                    setTouchX(0);
                    setTouchY(0);
                    return;
                }
                return;
            }
            if (x < getSmallViewportHorizontalPadding() || x > getSurfaceViewWidth() - getSmallViewportHorizontalPadding() || y < getSmallViewportTopPadding() || y > getSurfaceViewHeight() - getSmallViewportBottomPadding()) {
                return;
            }
            int touchX = getTouchX();
            int touchY = getTouchY();
            setTouchX(x);
            setTouchY(y);
            int smallViewportX = getSmallViewportX() + (x - touchX);
            int smallViewportY = getSmallViewportY() - (y - touchY);
            if (smallViewportX < getSmallViewportHorizontalPadding() || getSmallViewportWidth() + smallViewportX > getSurfaceViewWidth() - getSmallViewportHorizontalPadding() || (getSurfaceViewHeight() - smallViewportY) - getSmallViewportHeight() < getSmallViewportTopPadding() || smallViewportY < getSmallViewportBottomPadding()) {
                return;
            }
            setSmallViewportX(smallViewportX);
            setSmallViewportY(smallViewportY);
        }
    }

    @Override
    public void reopenCamera() {
        this.fUCamera.openCamera(this.cameraConfig, getOriginalTextId(), getFUCameraListener());
    }

    @Override
    public void closeCamera() {
        this.fUCamera.closeCamera();
    }

    public final int getOpenCameraIgnoreFrame() {
        return this.openCameraIgnoreFrame;
    }

    public final void setOpenCameraIgnoreFrame(int i) {
        this.openCameraIgnoreFrame = i;
    }

    @Override
    public void switchCamera() {
        this.openCameraIgnoreFrame = 2;
        this.fUCamera.switchCamera();
    }

    public final void cacheLastBitmap() {
        if (getCurrentFURenderOutputData() != null) {
            FURenderOutputData currentFURenderOutputData = getCurrentFURenderOutputData();
            if (currentFURenderOutputData == null) {
                Intrinsics.throwNpe();
            }
            if (currentFURenderOutputData.getTexture() != null) {
                PhotoRecordHelper photoRecordHelper = this.mPhotoRecordHelper;
                int faceUnity2DTexId = getFaceUnity2DTexId();
                float[] currentFUTexMatrix = getCurrentFUTexMatrix();
                float[] texture_matrix = getTEXTURE_MATRIX();
                FURenderOutputData currentFURenderOutputData2 = getCurrentFURenderOutputData();
                if (currentFURenderOutputData2 == null) {
                    Intrinsics.throwNpe();
                }
                FURenderOutputData.FUTexture texture = currentFURenderOutputData2.getTexture();
                if (texture == null) {
                    Intrinsics.throwNpe();
                }
                int width = texture.getWidth();
                FURenderOutputData currentFURenderOutputData3 = getCurrentFURenderOutputData();
                if (currentFURenderOutputData3 == null) {
                    Intrinsics.throwNpe();
                }
                FURenderOutputData.FUTexture texture2 = currentFURenderOutputData3.getTexture();
                if (texture2 == null) {
                    Intrinsics.throwNpe();
                }
                photoRecordHelper.sendRecordingData(faceUnity2DTexId, currentFUTexMatrix, texture_matrix, width, texture2.getHeight());
            }
        }
    }

    private final void drawCacheBitmap() {
        Bitmap bitmap = this.mCacheBitmap;
        if (bitmap != null) {
            deleteCacheBitmapTexId();
            this.mCacheBitmapTexId = GlUtil.createImageTexture(bitmap);
            float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(getSurfaceViewWidth(), getSurfaceViewHeight(), bitmap.getWidth(), bitmap.getHeight());
            Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr…t(), it.height.toFloat())");
            this.mCacheBitmapMvpMatrix = fArrChangeMvpMatrixCrop;
            Matrix.scaleM(fArrChangeMvpMatrixCrop, 0, 1.0f, -1.0f, 1.0f);
            if (this.mCacheBitmapTexId > 0) {
                GLES20.glClear(16640);
                ProgramTexture2d programTexture2d = getProgramTexture2d();
                if (programTexture2d != null) {
                    programTexture2d.drawFrame(this.mCacheBitmapTexId, getTEXTURE_MATRIX(), this.mCacheBitmapMvpMatrix);
                }
            }
        }
    }

    private final void deleteCacheBitmapTexId() {
        int i = this.mCacheBitmapTexId;
        if (i > 0) {
            GlUtil.deleteTextures(new int[]{i});
            this.mCacheBitmapTexId = 0;
        }
    }
}
