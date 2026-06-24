package com.faceunity.core.renderer;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.infe.IPhotoRenderer;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.media.video.VideoRecordHelper;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.utils.BitmapUtils;
import com.faceunity.core.utils.FileUtils;
import com.faceunity.core.utils.GlUtil;
import com.faceunity.core.utils.LimitFpsUtil;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\"\u0010\u0018\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0014J\u001c\u0010\u001b\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m1293d2 = {"Lcom/faceunity/core/renderer/PhotoRenderer;", "Lcom/faceunity/core/renderer/BaseFURenderer;", "Lcom/faceunity/core/infe/IPhotoRenderer;", "gLSurfaceView", "Landroid/opengl/GLSurfaceView;", "photoPath", "", "glRendererListener", "Lcom/faceunity/core/listener/OnGlRendererListener;", "(Landroid/opengl/GLSurfaceView;Ljava/lang/String;Lcom/faceunity/core/listener/OnGlRendererListener;)V", "requestPhotoHeight", "", "requestPhotoWidth", "buildFURenderInputData", "Lcom/faceunity/core/entity/FURenderInputData;", "drawRenderFrame", "", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "onDestroy", "onPause", "onResume", "prepareRender", "", "surfaceChanged", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "surfaceCreated", "config", "Ljavax/microedition/khronos/egl/EGLConfig;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class PhotoRenderer extends BaseFURenderer implements IPhotoRenderer {
    private final String photoPath;
    private final int requestPhotoHeight;
    private final int requestPhotoWidth;

    public PhotoRenderer(GLSurfaceView gLSurfaceView, String photoPath, OnGlRendererListener onGlRendererListener) {
        super(gLSurfaceView, onGlRendererListener);
        Intrinsics.checkParameterIsNotNull(photoPath, "photoPath");
        this.photoPath = photoPath;
        this.requestPhotoWidth = 1080;
        this.requestPhotoHeight = VideoRecordHelper.MAX_VIDEO_LENGTH;
        FURenderInputData currentFURenderInputData = getCurrentFURenderInputData();
        getCurrentFURenderInputData().setTexture(new FURenderInputData.FUTexture(FUInputTextureEnum.FU_ADM_FLAG_COMMON_TEXTURE, 0));
        getCurrentFURenderInputData().setImageBuffer(new FURenderInputData.FUImageBuffer(FUInputBufferEnum.FU_FORMAT_NV21_BUFFER, null, null, null, 14, null));
        FURenderInputData.FURenderConfig renderConfig = currentFURenderInputData.getRenderConfig();
        renderConfig.setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE);
        renderConfig.setCameraFacing(CameraFacingEnum.CAMERA_BACK);
        renderConfig.setInputTextureMatrix(FUTransformMatrixEnum.CCROT0);
        renderConfig.setInputBufferMatrix(FUTransformMatrixEnum.CCROT0);
        setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE);
        if (gLSurfaceView != null) {
            gLSurfaceView.setEGLContextClientVersion(GlUtil.getSupportGlVersion(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease()));
        }
        if (gLSurfaceView != null) {
            gLSurfaceView.setRenderer(this);
        }
        if (gLSurfaceView != null) {
            gLSurfaceView.setRenderMode(0);
        }
    }

    @Override
    public void onResume() {
        GLSurfaceView gLSurfaceView;
        if (getIsActivityPause() && (gLSurfaceView = getGLSurfaceView()) != null) {
            gLSurfaceView.onResume();
        }
        setActivityPause(false);
    }

    @Override
    public void onPause() {
        setActivityPause(true);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        GLSurfaceView gLSurfaceView = getGLSurfaceView();
        if (gLSurfaceView != null) {
            gLSurfaceView.queueEvent(new Runnable() {
                @Override
                public final void run() {
                    PhotoRenderer.this.destroyGlSurface();
                    countDownLatch.countDown();
                }
            });
        }
        try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        GLSurfaceView gLSurfaceView2 = getGLSurfaceView();
        if (gLSurfaceView2 != null) {
            gLSurfaceView2.onPause();
        }
    }

    @Override
    public void onDestroy() {
        setGlRendererListener(null);
        setGLSurfaceView(null);
    }

    @Override
    protected void surfaceCreated(GL10 gl, EGLConfig config) {
        Bitmap bitmapLoadBitmapFromExternal = FileUtils.loadBitmapFromExternal(this.photoPath, this.requestPhotoWidth, this.requestPhotoHeight);
        if (bitmapLoadBitmapFromExternal != null) {
            setOriginalTextId(GlUtil.createImageTexture(bitmapLoadBitmapFromExternal));
            setOriginalWidth(bitmapLoadBitmapFromExternal.getWidth());
            setOriginalHeight(bitmapLoadBitmapFromExternal.getHeight());
            FURenderInputData currentFURenderInputData = getCurrentFURenderInputData();
            currentFURenderInputData.setWidth(getOriginalWidth());
            currentFURenderInputData.setHeight(getOriginalHeight());
            FURenderInputData.FUImageBuffer imageBuffer = currentFURenderInputData.getImageBuffer();
            if (imageBuffer != null) {
                imageBuffer.setBuffer(BitmapUtils.getNV21$default(BitmapUtils.INSTANCE, getOriginalWidth(), getOriginalHeight(), bitmapLoadBitmapFromExternal, false, 8, null));
            }
            FURenderInputData.FUTexture texture = currentFURenderInputData.getTexture();
            if (texture != null) {
                texture.setTexId(getOriginalTextId());
            }
            LimitFpsUtil.setTargetFps(30);
        }
    }

    @Override
    protected void surfaceChanged(GL10 gl, int width, int height) {
        float[] fArrChangeMvpMatrixInside = GlUtil.changeMvpMatrixInside(width, height, getOriginalWidth(), getOriginalHeight());
        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixInside, "GlUtil.changeMvpMatrixIn…originalHeight.toFloat())");
        setDefaultFUMvpMatrix(fArrChangeMvpMatrixInside);
        float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(90.0f, 160.0f, getOriginalHeight(), getOriginalWidth());
        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr… originalWidth.toFloat())");
        setSmallViewMatrix(fArrChangeMvpMatrixCrop);
        float[] defaultFUMvpMatrix = getDefaultFUMvpMatrix();
        float[] fArrCopyOf = Arrays.copyOf(defaultFUMvpMatrix, defaultFUMvpMatrix.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        setOriginMvpMatrix(fArrCopyOf);
        Matrix.scaleM(getOriginMvpMatrix(), 0, 1.0f, -1.0f, 1.0f);
    }

    @Override
    protected boolean prepareRender(GL10 gl) {
        return getProgramTexture2d() != null;
    }

    @Override
    protected FURenderInputData buildFURenderInputData() {
        return getCurrentFURenderInputData();
    }

    @Override
    protected void drawRenderFrame(GL10 gl) {
        if (getFaceUnity2DTexId() > 0 && getRenderSwitch()) {
            ProgramTexture2d programTexture2d = getProgramTexture2d();
            if (programTexture2d == null) {
                Intrinsics.throwNpe();
            }
            programTexture2d.drawFrame(getFaceUnity2DTexId(), getCurrentFUTexMatrix(), getCurrentFUMvpMatrix());
        } else if (getOriginalTextId() > 0) {
            ProgramTexture2d programTexture2d2 = getProgramTexture2d();
            if (programTexture2d2 == null) {
                Intrinsics.throwNpe();
            }
            programTexture2d2.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getOriginMvpMatrix());
        }
        if (getDrawSmallViewport()) {
            GLES20.glViewport(getSmallViewportX(), getSmallViewportY(), getSmallViewportWidth(), getSmallViewportHeight());
            ProgramTexture2d programTexture2d3 = getProgramTexture2d();
            if (programTexture2d3 == null) {
                Intrinsics.throwNpe();
            }
            programTexture2d3.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getSmallViewMatrix());
            GLES20.glViewport(0, 0, getSurfaceViewWidth(), getSurfaceViewHeight());
        }
    }
}
