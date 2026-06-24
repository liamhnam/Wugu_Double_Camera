package com.wugu.facebeauty;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.text.TextUtils;
import com.faceunity.core.controller.makeup.MakeupParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUCameraConfig;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.entity.FURenderFrameData;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUAITypeEnum;
import com.faceunity.core.enumeration.FUFaceBeautyMultiModePropertyEnum;
import com.faceunity.core.enumeration.FUFaceBeautyPropertyModeEnum;
import com.faceunity.core.faceunity.FUAIKit;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.media.photo.OnPhotoRecordingListener;
import com.faceunity.core.media.photo.PhotoRecordHelper;
import com.faceunity.core.media.video.OnVideoRecordingListener;
import com.faceunity.core.media.video.VideoRecordHelper;
import com.faceunity.core.model.animationFilter.AnimationFilter;
import com.faceunity.core.model.bgSegGreen.BgSegGreen;
import com.faceunity.core.model.facebeauty.FaceBeauty;
import com.faceunity.core.model.makeup.Makeup;
import com.faceunity.core.model.prop.Prop;
import com.faceunity.core.model.prop.arMask.ARMask;
import com.faceunity.core.model.prop.faceWarp.FaceWarp;
import com.faceunity.core.model.prop.sticker.Sticker;
import com.faceunity.core.program.ProgramTexture2dWithAlpha;
import com.faceunity.core.renderer.CameraRenderer;
import com.faceunity.core.utils.BitmapUtils;
import com.faceunity.core.utils.FileUtils;
import com.faceunity.core.utils.GlUtil;
import com.wugu.facebeauty.SurfaceRender;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SurfaceRender {
    private BgSegGreen bgSegGreen;
    private ITakePhotoListener captureListener;
    private int compressionRatio;
    private Prop currentProp;
    private int frameHeight;
    private int frameWidth;
    private final Boolean isAsFrontCamera;
    private BeautifyItem mBeautyItem;
    private BitmapRender mBitmapRender;
    private CameraRenderer mCameraRenderer;
    private GLSurfaceView mGlSurfaceView;
    private Makeup mMakeupMode;
    private PhotoRecordHelper mPhotoRecordHelper;
    private int mVideoHeight;
    private VideoRecordHelper mVideoRecordHelper;
    private int mVideoWidth;
    private volatile int recordingCount;
    private String tempFolder;
    private ThreadPoolExecutor threadPoolExecutor;
    private final FURenderKit mFURenderKit = FURenderKit.getInstance();
    private final FaceBeauty mFaceBeauty = new FaceBeauty(new FUBundleData(DemoConfig.BUNDLE_FACE_BEAUTIFICATION));
    private final AnimationFilter mAnimFilter = new AnimationFilter(new FUBundleData(DemoConfig.BUNDLE_ANIMATION_FILTER));
    private String lastMakeupName = "non";
    private final FUAIKit mFUAIKit = FUAIKit.getInstance();
    private final OnPhotoRecordingListener mOnPhotoRecordingListener = new OnPhotoRecordingListener() {
        @Override
        public final void onRecordSuccess(Bitmap bitmap) {
            this.f$0.onReadBitmap(bitmap);
        }
    };
    private volatile Boolean isTakePhoto = false;
    private volatile Boolean isStartRecording = false;
    private final OnVideoRecordingListener mOnVideoRecordingListener = new OnVideoRecordingListener() {
        @Override
        public void onPrepared() {
        }

        @Override
        public void onProcess(Long l) {
        }

        @Override
        public void onFinish(File file) {
            SurfaceRender.this.captureListener.recordFinish(file.getAbsolutePath());
        }
    };
    private final OnGlRendererListener mOnGlRendererListener = new OnGlRendererListener() {
        private int height;
        private int width;
        private int surfaceWidth = 0;
        private int surfaceHeight = 0;

        @Override
        public void onDrawFrameAfter() {
        }

        @Override
        public void onSurfaceCreated() {
            SurfaceRender.this.configureFURenderKit();
            if (SurfaceRender.this.captureListener != null) {
                SurfaceRender.this.captureListener.onSurfaceCreated();
            }
        }

        @Override
        public void onSurfaceChanged(int i, int i2) {
            this.surfaceWidth = i;
            this.surfaceHeight = i2;
        }

        @Override
        public void onRenderBefore(FURenderInputData fURenderInputData) {
            this.width = fURenderInputData.getWidth();
            this.height = fURenderInputData.getHeight();
        }

        @Override
        public void onRenderAfter(FURenderOutputData fURenderOutputData, FURenderFrameData fURenderFrameData) {
            SurfaceRender.this.mVideoWidth = fURenderOutputData.getTexture().getWidth();
            SurfaceRender.this.mVideoHeight = fURenderOutputData.getTexture().getHeight();
            if (SurfaceRender.this.mCameraRenderer != null) {
                float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(this.surfaceWidth, this.surfaceHeight, this.width, this.height);
                Matrix.rotateM(fArrChangeMvpMatrixCrop, 0, 90.0f, 0.0f, 0.0f, 1.0f);
                fURenderFrameData.setMvpMatrix(fArrChangeMvpMatrixCrop);
            }
            recordingData(fURenderOutputData, fURenderFrameData.getTexMatrix());
        }

        @Override
        public void onSurfaceDestroy() {
            SurfaceRender.this.mFURenderKit.release();
        }

        private void recordingData(FURenderOutputData fURenderOutputData, float[] fArr) {
            if (fURenderOutputData == null || fURenderOutputData.getTexture() == null || fURenderOutputData.getTexture().getTexId() <= 0) {
                return;
            }
            if (SurfaceRender.this.mVideoRecordHelper != null) {
                if (SurfaceRender.this.isStartRecording.booleanValue()) {
                    SurfaceRender.this.mVideoRecordHelper.frameAvailableSoon(fURenderOutputData.getTexture().getTexId(), fArr, GlUtil.IDENTITY_MATRIX);
                }
            } else {
                if (SurfaceRender.this.isStartRecording.booleanValue()) {
                    SurfaceRender.this.saveBitmap(fURenderOutputData.getTexture().getTexId(), fArr, GlUtil.IDENTITY_MATRIX, fURenderOutputData.getTexture().getWidth(), fURenderOutputData.getTexture().getHeight());
                }
                if (SurfaceRender.this.isTakePhoto.booleanValue()) {
                    SurfaceRender.this.compoundMp4();
                }
            }
            if (SurfaceRender.this.isTakePhoto.booleanValue()) {
                SurfaceRender.this.isTakePhoto = false;
                SurfaceRender.this.mPhotoRecordHelper.sendRecordingData(fURenderOutputData.getTexture().getTexId(), fArr, GlUtil.IDENTITY_MATRIX, fURenderOutputData.getTexture().getWidth(), fURenderOutputData.getTexture().getHeight());
            }
        }
    };
    private int currentBitFolder = 0;
    private int lastBitFolder = 0;
    public HashMap<String, MakeupSetParam> makeupSetMapping = new C20553();

    interface MakeupSetParam {
        void setValue(Makeup makeup, Object obj);
    }

    static void lambda$setFaceBeautyParam$0() {
    }

    public SurfaceRender(GLSurfaceView gLSurfaceView, int i, int i2, BeautifyItem beautifyItem, boolean z, int i3, ITakePhotoListener iTakePhotoListener) {
        this.compressionRatio = 4;
        this.mGlSurfaceView = gLSurfaceView;
        this.mBeautyItem = beautifyItem;
        this.captureListener = iTakePhotoListener;
        this.isAsFrontCamera = Boolean.valueOf(z);
        this.compressionRatio = i3;
        initCameraRender(i, i2);
    }

    public SurfaceRender(GLSurfaceView gLSurfaceView, Bitmap bitmap, BeautifyItem beautifyItem, boolean z, int i, String str, ThreadPoolExecutor threadPoolExecutor, ITakePhotoListener iTakePhotoListener) {
        this.compressionRatio = 4;
        this.mGlSurfaceView = gLSurfaceView;
        this.mBeautyItem = beautifyItem;
        this.captureListener = iTakePhotoListener;
        this.tempFolder = str;
        this.isAsFrontCamera = Boolean.valueOf(z);
        this.threadPoolExecutor = threadPoolExecutor;
        this.compressionRatio = i;
        initBitmapRender(bitmap);
    }

    public void closeCamera() {
        this.mCameraRenderer.closeCamera();
    }

    public void reopenCamera() {
        this.mCameraRenderer.reopenCamera();
    }

    public void onResume() {
        CameraRenderer cameraRenderer = this.mCameraRenderer;
        if (cameraRenderer != null) {
            cameraRenderer.onResume();
        }
        BitmapRender bitmapRender = this.mBitmapRender;
        if (bitmapRender != null) {
            bitmapRender.onResume();
        }
    }

    public void onPause() {
        CameraRenderer cameraRenderer = this.mCameraRenderer;
        if (cameraRenderer != null) {
            cameraRenderer.onPause();
        }
        BitmapRender bitmapRender = this.mBitmapRender;
        if (bitmapRender != null) {
            bitmapRender.onPause();
        }
    }

    public void onDestroy() {
        CameraRenderer cameraRenderer = this.mCameraRenderer;
        if (cameraRenderer != null) {
            cameraRenderer.onDestroy();
        }
        BitmapRender bitmapRender = this.mBitmapRender;
        if (bitmapRender != null) {
            bitmapRender.onDestroy();
        }
        this.mFURenderKit.release();
        this.captureListener = null;
        this.mGlSurfaceView = null;
        this.threadPoolExecutor = null;
        this.bgSegGreen = null;
        this.mVideoRecordHelper = null;
    }

    public void renderBitmap(Bitmap bitmap) {
        BitmapRender bitmapRender = this.mBitmapRender;
        if (bitmapRender != null) {
            bitmapRender.renderBitmap(bitmap);
        }
    }

    public void setBeautyLevel(BeautifyItem beautifyItem) {
        this.mBeautyItem = beautifyItem;
        setFaceBeautyParam();
    }

    public void setFrameWH(int i, int i2) {
        this.frameWidth = i;
        this.frameHeight = i2;
    }

    public void setEffect(int i, String str) {
        Prop sticker = null;
        if (TextUtils.isEmpty(str)) {
            this.mFURenderKit.getPropContainer().removeAllProp();
            this.currentProp = null;
            return;
        }
        String strReplace = str.replace("file:///android_asset/", "");
        if (i == 3) {
            sticker = new Sticker(new FUBundleData(strReplace));
        } else if (i == 4) {
            sticker = new ARMask(new FUBundleData(strReplace));
        } else if (i == 5) {
            sticker = new FaceWarp(new FUBundleData(strReplace));
        }
        this.mFURenderKit.getPropContainer().replaceProp(this.currentProp, sticker);
        this.currentProp = sticker;
    }

    public void takePhoto() {
        this.isTakePhoto = true;
        this.isStartRecording = false;
        VideoRecordHelper videoRecordHelper = this.mVideoRecordHelper;
        if (videoRecordHelper != null) {
            videoRecordHelper.stopRecording();
        }
    }

    public void setExposure(float f) {
        CameraRenderer cameraRenderer = this.mCameraRenderer;
        if (cameraRenderer != null) {
            cameraRenderer.getFUCamera().setExposureCompensation(Math.max(0.0f, Math.min(f, 1.0f)));
        }
    }

    public void setZoom(float f) {
        CameraRenderer cameraRenderer = this.mCameraRenderer;
        if (cameraRenderer != null) {
            cameraRenderer.getFUCamera().setZoomValue(Math.max(1.0f, Math.min(f, 2.0f)));
        }
    }

    private void initCameraRender(int i, int i2) {
        this.mCameraRenderer = new CameraRenderer(this.mGlSurfaceView, getCameraConfig(i, i2), this.mOnGlRendererListener);
        this.mPhotoRecordHelper = new PhotoRecordHelper(this.mOnPhotoRecordingListener);
        this.mVideoRecordHelper = new VideoRecordHelper(this.mGlSurfaceView.getContext(), this.mOnVideoRecordingListener);
    }

    private void initBitmapRender(Bitmap bitmap) {
        this.mBitmapRender = new BitmapRender(bitmap, this.mGlSurfaceView, this.mOnGlRendererListener);
        this.mPhotoRecordHelper = new PhotoRecordHelper(this.mOnPhotoRecordingListener);
    }

    public void onReadBitmap(Bitmap bitmap) {
        if (this.mCameraRenderer != null) {
            Bitmap bitmapRotateBitmap = BitmapUtils.INSTANCE.rotateBitmap(bitmap, 270);
            ITakePhotoListener iTakePhotoListener = this.captureListener;
            if (iTakePhotoListener != null) {
                iTakePhotoListener.captureFinish(bitmapRotateBitmap);
            }
            bitmap.recycle();
            return;
        }
        ITakePhotoListener iTakePhotoListener2 = this.captureListener;
        if (iTakePhotoListener2 != null) {
            iTakePhotoListener2.captureFinish(bitmap);
        }
    }

    private FUCameraConfig getCameraConfig(int i, int i2) {
        FUCameraConfig fUCameraConfig = new FUCameraConfig();
        fUCameraConfig.setCameraWidth(i);
        fUCameraConfig.setCameraHeight(i2);
        fUCameraConfig.setCameraFacing(this.isAsFrontCamera.booleanValue() ? CameraFacingEnum.CAMERA_FRONT : CameraFacingEnum.CAMERA_BACK);
        return fUCameraConfig;
    }

    public void configureFURenderKit() {
        this.mFaceBeauty.addPropertyMode(FUFaceBeautyMultiModePropertyEnum.REMOVE_POUCH_INTENSITY, FUFaceBeautyPropertyModeEnum.MODE2);
        if (this.mBeautyItem != null) {
            setFaceBeautyParam();
        }
        this.mFUAIKit.setMaxFaces(3);
        this.mFUAIKit.setFaceDelayLeaveEnable(false);
        this.mFUAIKit.loadAIProcessor(DemoConfig.BUNDLE_AI_FACE, FUAITypeEnum.FUAITYPE_FACEPROCESSOR);
        this.mFUAIKit.faceProcessorSetFaceLandmarkQuality(1);
    }

    private void setFaceBeautyParam() {
        if (TextUtils.isEmpty(this.mBeautyItem.filterParams.filterName)) {
            return;
        }
        this.mFaceBeauty.setFilterName(this.mBeautyItem.filterParams.filterName);
        this.mFaceBeauty.setFilterIntensity(this.mBeautyItem.filterParams.filterIntensity);
        this.mFaceBeauty.setBlurType(2);
        this.mFaceBeauty.setEnableBlurUseMask(false);
        this.mFaceBeauty.setBlurIntensity(this.mBeautyItem.beautyParams.blur);
        this.mFaceBeauty.setColorIntensity(this.mBeautyItem.beautyParams.white);
        this.mFaceBeauty.setRedIntensity(this.mBeautyItem.beautyParams.red);
        this.mFaceBeauty.setEyeBrightIntensity(this.mBeautyItem.beautyParams.eyeBright);
        this.mFaceBeauty.setToothIntensity(this.mBeautyItem.beautyParams.tooth);
        this.mFaceBeauty.setClarityIntensity(this.mBeautyItem.beautyParams.clarity);
        this.mFaceBeauty.setFaceShape(4);
        this.mFaceBeauty.setEyeEnlargingIntensity(this.mBeautyItem.beautyParams.eyeBig);
        this.mFaceBeauty.setEyeCircleIntensity(this.mBeautyItem.beautyParams.eyeCircle);
        this.mFaceBeauty.setNoseIntensity(this.mBeautyItem.beautyParams.noseThin);
        this.mFaceBeauty.setCheekThinningIntensity(this.mBeautyItem.beautyParams.faceThin);
        this.mFaceBeauty.setFaceThreeIntensity(this.mBeautyItem.beautyParams.faceThree);
        this.mFaceBeauty.setEnableSkinDetect(true);
        this.mFaceBeauty.setNonSkinBlurIntensity(1.0d);
        if (this.mBeautyItem.filterParams.filterType == 2) {
            this.mAnimFilter.setStyle(this.mBeautyItem.filterParams.animationFilterIndex);
        } else {
            this.mAnimFilter.setStyle(-1);
        }
        String str = this.mBeautyItem.makeupParams.makeupBundlePath;
        if (!TextUtils.isEmpty(str)) {
            if (!this.lastMakeupName.equals(str)) {
                if (this.mBeautyItem.makeupParams.makeupType == 0) {
                    this.mMakeupMode = new Makeup(new FUBundleData(str));
                } else {
                    setSubMakeup();
                }
                this.mMakeupMode.setCurrentFilterScale(0.68d);
                this.lastMakeupName = str;
            }
            this.mMakeupMode.setMakeupIntensity(this.mBeautyItem.makeupParams.intensity);
            this.mFURenderKit.addMakeupLoadListener(new Runnable() {
                @Override
                public final void run() {
                    SurfaceRender.lambda$setFaceBeautyParam$0();
                }
            });
        } else {
            this.lastMakeupName = "non";
            this.mMakeupMode = null;
        }
        this.mFURenderKit.setFaceBeauty(this.mFaceBeauty);
        this.mFURenderKit.setAnimationFilter(this.mAnimFilter);
        this.mFURenderKit.setMakeup(this.mMakeupMode);
    }

    public void startRecord() {
        this.isStartRecording = true;
        this.recordingCount = 1;
        this.currentBitFolder++;
        new File(this.tempFolder + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.currentBitFolder).mkdirs();
        VideoRecordHelper videoRecordHelper = this.mVideoRecordHelper;
        if (videoRecordHelper != null) {
            videoRecordHelper.startRecording(this.mGlSurfaceView, this.mVideoWidth, this.mVideoHeight);
        }
    }

    public void endRecord() {
        this.isStartRecording = false;
        VideoRecordHelper videoRecordHelper = this.mVideoRecordHelper;
        if (videoRecordHelper != null) {
            videoRecordHelper.stopRecording();
        }
    }

    public void saveBitmap(int i, float[] fArr, float[] fArr2, final int i2, final int i3) {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glActiveTexture(33984);
        GLES20.glTexImage2D(3553, 0, 6408, i2, i3, 0, 6408, 5121, null);
        int[] iArr2 = new int[1];
        GLES20.glGenFramebuffers(1, iArr2, 0);
        GLES20.glBindFramebuffer(36160, iArr2[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr[0], 0);
        int[] iArr3 = new int[4];
        GLES20.glGetIntegerv(2978, iArr3, 0);
        GLES20.glViewport(0, 0, i2, i3);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        new ProgramTexture2dWithAlpha().drawFrame(i, fArr, fArr2);
        final ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2 * i3 * 4);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        GLES20.glFinish();
        GLES20.glReadPixels(0, 0, i2, i3, 6408, 5121, byteBufferAllocateDirect);
        GlUtil.checkGlError("glReadPixels");
        byteBufferAllocateDirect.rewind();
        GLES20.glViewport(iArr3[0], iArr3[1], iArr3[2], iArr3[3]);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glDeleteTextures(1, iArr, 0);
        GLES20.glDeleteFramebuffers(1, iArr2, 0);
        this.threadPoolExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1732lambda$saveBitmap$1$comwugufacebeautySurfaceRender(i2, i3, byteBufferAllocateDirect);
            }
        });
    }

    void m1732lambda$saveBitmap$1$comwugufacebeautySurfaceRender(int i, int i2, ByteBuffer byteBuffer) {
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        bitmapCreateBitmap.copyPixelsFromBuffer(byteBuffer);
        Bitmap ratioBitmap = getRatioBitmap(bitmapCreateBitmap);
        android.graphics.Matrix matrix = new android.graphics.Matrix();
        int i3 = this.frameWidth;
        float width = i3 > 0 ? (i3 / ratioBitmap.getWidth()) / this.compressionRatio : 0.25f;
        matrix.setScale(width, width);
        matrix.postScale(1.0f, -1.0f);
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(ratioBitmap, 0, 0, getClosetNum(ratioBitmap.getWidth(), width), getClosetNum(ratioBitmap.getHeight(), width), matrix, false);
        ratioBitmap.recycle();
        try {
            this.recordingCount++;
            FileOutputStream fileOutputStream = new FileOutputStream(this.tempFolder + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.currentBitFolder + MqttTopic.TOPIC_LEVEL_SEPARATOR + String.format(Locale.CHINA, "%03d.jpg", Integer.valueOf(this.recordingCount)));
            bitmapCreateBitmap2.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmapCreateBitmap2.recycle();
    }

    private Bitmap getRatioBitmap(Bitmap bitmap) {
        int i;
        if (bitmap == null) {
            return null;
        }
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        float f = width;
        float f2 = height;
        float f3 = f / f2;
        float f4 = this.frameWidth / this.frameHeight;
        if (Math.abs(f3 - f4) < 0.1d) {
            return bitmap;
        }
        int i2 = 0;
        if (f3 > f4) {
            int i3 = (int) (f2 * f4);
            i2 = (width - i3) / 2;
            width = i3;
            i = 0;
        } else {
            int i4 = (int) (f / f4);
            i = (height - i4) / 2;
            height = i4;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, i2, i, width, height);
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    private int getClosetNum(int i, float f) {
        for (int i2 = i; i2 > 0; i2--) {
            if (Math.round(i2 * f) % 2 == 0) {
                return i2;
            }
        }
        return i;
    }

    public void compoundMp4() {
        int i = this.lastBitFolder;
        int i2 = this.currentBitFolder;
        if (i == i2) {
            return;
        }
        this.lastBitFolder = i2;
        this.captureListener.recordFinish(this.tempFolder + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.currentBitFolder);
    }

    private void setSubMakeup() {
        Makeup makeup = new Makeup(new FUBundleData(DemoConfig.BUNDLE_FACE_MAKEUP));
        this.mMakeupMode = makeup;
        makeup.setCombinedConfig(new FUBundleData(this.mBeautyItem.makeupParams.makeupBundlePath));
        if (TextUtils.isEmpty(this.mBeautyItem.makeupParams.makeupJsonPath)) {
            return;
        }
        for (Map.Entry<String, Object> entry : getLocalParams(this.mBeautyItem.makeupParams.makeupJsonPath).entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            if (value instanceof double[]) {
                double[] dArr = (double[]) value;
                if (dArr.length > 4) {
                    int length = dArr.length / 4;
                    for (int i = 0; i < length; i++) {
                        if (i == 0) {
                            if (this.makeupSetMapping.containsKey(key)) {
                                this.makeupSetMapping.get(key).setValue(this.mMakeupMode, value);
                            }
                        } else {
                            int i2 = i + 1;
                            if (this.makeupSetMapping.containsKey(key + i2)) {
                                this.makeupSetMapping.get(key + i2).setValue(this.mMakeupMode, value);
                            }
                        }
                    }
                }
            }
            if (this.makeupSetMapping.containsKey(key)) {
                this.makeupSetMapping.get(key).setValue(this.mMakeupMode, value);
            }
        }
    }

    private LinkedHashMap<String, Object> getLocalParams(String str) {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(32);
        Double dValueOf = Double.valueOf(0.0d);
        linkedHashMap.put("makeup_intensity_lip", dValueOf);
        linkedHashMap.put("makeup_intensity_eyeLiner", dValueOf);
        linkedHashMap.put("makeup_intensity_blusher", dValueOf);
        linkedHashMap.put("makeup_intensity_pupil", dValueOf);
        linkedHashMap.put("makeup_intensity_eyeBrow", dValueOf);
        linkedHashMap.put("makeup_intensity_eye", dValueOf);
        linkedHashMap.put("makeup_intensity_eyelash", dValueOf);
        linkedHashMap.put(MakeupParam.FOUNDATION_INTENSITY, dValueOf);
        linkedHashMap.put(MakeupParam.HIGHLIGHT_INTENSITY, dValueOf);
        linkedHashMap.put(MakeupParam.SHADOW_INTENSITY, dValueOf);
        FileUtils fileUtils = FileUtils.INSTANCE;
        for (Map.Entry<String, Object> entry : FileUtils.loadParamsFromLocal(this.mGlSurfaceView.getContext().getApplicationContext(), str).entrySet()) {
            if (entry.getKey().startsWith("tex_")) {
                if ((entry.getValue() instanceof String) && ((String) entry.getValue()).contains(".bundle")) {
                    linkedHashMap.put(entry.getKey(), DemoConfig.MAKEUP_RESOURCE_ITEM_BUNDLE_DIR + entry.getValue());
                }
            } else {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return linkedHashMap;
    }

    public FUColorRGBData buildFUColorRGBData(Object obj) {
        if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            if (dArr.length == 4) {
                return new FUColorRGBData(dArr[0] * 255.0d, dArr[1] * 255.0d, dArr[2] * 255.0d, dArr[3] * 255.0d);
            }
        }
        return new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
    }

    class C20553 extends HashMap<String, MakeupSetParam> {
        C20553() {
            put(MakeupParam.LIP_TYPE, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setLipType(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.IS_TWO_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEnableTwoLipColor(((Integer) obj).intValue() == 1);
                }
            });
            put(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_ENABLE, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setLipHighLightEnable(((Integer) obj).intValue() == 1);
                }
            });
            put(MakeupParam.MAKEUP_LIP_HIGH_LIGHT_STRENGTH, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setLipHighLightStrength(((Double) obj).doubleValue());
                }
            });
            put(MakeupParam.BROW_WARP, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEnableBrowWarp(((Double) obj).doubleValue() == 1.0d);
                }
            });
            put(MakeupParam.MAKEUP_MACHINE_LEVEL, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setMachineLevel(((Double) obj).doubleValue() == 1.0d);
                }
            });
            put(MakeupParam.BROW_WARP_TYPE, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setBrowWarpType(((Integer) obj).intValue());
                }
            });
            put("makeup_intensity", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setMakeupIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_lip", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setLipIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_eyeLiner", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeLineIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_blusher", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setBlusherIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_pupil", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setPupilIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_eyeBrow", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeBrowIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_eye", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeShadowIntensity(((Double) obj).doubleValue());
                }
            });
            put("makeup_intensity_eyelash", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeLashIntensity(((Double) obj).doubleValue());
                }
            });
            put(MakeupParam.FOUNDATION_INTENSITY, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setFoundationIntensity(((Double) obj).doubleValue());
                }
            });
            put(MakeupParam.HIGHLIGHT_INTENSITY, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setHeightLightIntensity(((Double) obj).doubleValue());
                }
            });
            put(MakeupParam.SHADOW_INTENSITY, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setShadowIntensity(((Double) obj).doubleValue());
                }
            });
            put(MakeupParam.TEX_LIP, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$18(makeup, obj);
                }
            });
            put("tex_brow", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$19(makeup, obj);
                }
            });
            put("tex_eye", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$20(makeup, obj);
                }
            });
            put(MakeupParam.TEX_EYE_SHADOW2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$21(makeup, obj);
                }
            });
            put(MakeupParam.TEX_EYE_SHADOW3, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$22(makeup, obj);
                }
            });
            put(MakeupParam.TEX_EYE_SHADOW4, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$23(makeup, obj);
                }
            });
            put("tex_pupil", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$24(makeup, obj);
                }
            });
            put("tex_eyeLash", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$25(makeup, obj);
                }
            });
            put("tex_eyeLiner", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$26(makeup, obj);
                }
            });
            put("tex_blusher", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$27(makeup, obj);
                }
            });
            put(MakeupParam.TEX_BLUSHER2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$28(makeup, obj);
                }
            });
            put(MakeupParam.TEX_FOUNDATION, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$29(makeup, obj);
                }
            });
            put("tex_highlight", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$30(makeup, obj);
                }
            });
            put(MakeupParam.TEX_SHADOW, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    SurfaceRender.C20553.lambda$new$31(makeup, obj);
                }
            });
            put("makeup_lip_color", new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1733lambda$new$32$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_LIP_COLOR_V2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1734lambda$new$33$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_LIP_COLOR2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1735lambda$new$34$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_LINER_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1736lambda$new$35$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_LASH_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1737lambda$new$36$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_BLUSHER_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1738lambda$new$37$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_BLUSHER_COLOR2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1739lambda$new$38$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_FOUNDATION_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1740lambda$new$39$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_HIGH_LIGHT_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1741lambda$new$40$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_SHADOW_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1742lambda$new$41$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_BROW_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1743lambda$new$42$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_PUPIL_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1744lambda$new$43$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1745lambda$new$44$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1746lambda$new$45$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR3, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1747lambda$new$46$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.MAKEUP_EYE_SHADOW_COLOR4, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    this.f$0.m1748lambda$new$47$comwugufacebeautySurfaceRender$3(makeup, obj);
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_SHADOW, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeShadowTexBlend(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_SHADOW2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeShadowTexBlend2(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_SHADOW3, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeShadowTexBlend3(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_SHADOW4, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeShadowTexBlend4(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_LASH, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeLashTexBlend(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_EYE_LINER, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setEyeLinerTexBlend(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_BLUSHER, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setBlusherTexBlend(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_BLUSHER2, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setBlusherTexBlend2(((Integer) obj).intValue());
                }
            });
            put(MakeupParam.BLEND_TEX_PUPIL, new MakeupSetParam() {
                @Override
                public final void setValue(Makeup makeup, Object obj) {
                    makeup.setPupilTexBlend(((Integer) obj).intValue());
                }
            });
        }

        static void lambda$new$18(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setLipBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$19(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeBrowBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$20(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeShadowBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$21(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeShadowBundle2(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$22(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeShadowBundle3(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$23(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeShadowBundle4(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$24(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setPupilBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$25(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeLashBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$26(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setEyeLinerBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$27(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setBlusherBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$28(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setBlusherBundle2(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$29(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setFoundationBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$30(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setHighLightBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        static void lambda$new$31(Makeup makeup, Object obj) {
            String str = (String) obj;
            makeup.setShadowBundle(str.endsWith(".bundle") ? new FUBundleData(str) : null);
        }

        void m1733lambda$new$32$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setLipColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1734lambda$new$33$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setLipColorV2(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1735lambda$new$34$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setLipColor2(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1736lambda$new$35$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeLinerColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1737lambda$new$36$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeLashColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1738lambda$new$37$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setBlusherColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1739lambda$new$38$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setBlusherColor2(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1740lambda$new$39$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setFoundationColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1741lambda$new$40$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setHighLightColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1742lambda$new$41$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setShadowColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1743lambda$new$42$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeBrowColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1744lambda$new$43$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setPupilColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1745lambda$new$44$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeShadowColor(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1746lambda$new$45$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeShadowColor2(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1747lambda$new$46$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeShadowColor3(SurfaceRender.this.buildFUColorRGBData(obj));
        }

        void m1748lambda$new$47$comwugufacebeautySurfaceRender$3(Makeup makeup, Object obj) {
            makeup.setEyeShadowColor4(SurfaceRender.this.buildFUColorRGBData(obj));
        }
    }
}
