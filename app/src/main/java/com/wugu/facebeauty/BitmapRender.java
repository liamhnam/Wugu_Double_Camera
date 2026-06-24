package com.wugu.facebeauty;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import com.faceunity.core.context.FUApplication;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputBufferEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.infe.IPhotoRenderer;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.renderer.BaseFURenderer;
import com.faceunity.core.utils.BitmapUtils;
import com.faceunity.core.utils.GlUtil;
import com.faceunity.core.utils.LimitFpsUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BitmapRender extends BaseFURenderer implements IPhotoRenderer, GLSurfaceView.Renderer {
    private Bitmap bitmap;

    @Override
    public void surfaceChanged(GL10 gl10, int i, int i2) {
    }

    public BitmapRender(Bitmap bitmap, GLSurfaceView gLSurfaceView, OnGlRendererListener onGlRendererListener) {
        super(gLSurfaceView, onGlRendererListener);
        this.bitmap = bitmap;
        init();
    }

    private void init() {
        getCurrentFURenderInputData().setTexture(new FURenderInputData.FUTexture(FUInputTextureEnum.FU_ADM_FLAG_COMMON_TEXTURE, 0));
        getCurrentFURenderInputData().setImageBuffer(new FURenderInputData.FUImageBuffer(FUInputBufferEnum.FU_FORMAT_NV21_BUFFER));
        getCurrentFURenderInputData().getRenderConfig().setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE);
        getCurrentFURenderInputData().getRenderConfig().setCameraFacing(CameraFacingEnum.CAMERA_BACK);
        getCurrentFURenderInputData().getRenderConfig().setInputTextureMatrix(FUTransformMatrixEnum.CCROT0);
        getCurrentFURenderInputData().getRenderConfig().setInputBufferMatrix(FUTransformMatrixEnum.CCROT0);
        setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE);
        getGLSurfaceView().setEGLContextClientVersion(GlUtil.getSupportGlVersion(FUApplication.getApplication()));
        getGLSurfaceView().setRenderer(this);
        getGLSurfaceView().setRenderMode(0);
    }

    public void renderBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        setOriginalTextId(GlUtil.createImageTexture(bitmap));
        setOriginalWidth(bitmap.getWidth());
        setOriginalHeight(bitmap.getHeight());
        getCurrentFURenderInputData().setWidth(getOriginalWidth());
        getCurrentFURenderInputData().setHeight(getOriginalHeight());
        getCurrentFURenderInputData().getImageBuffer().setBuffer(BitmapUtils.INSTANCE.getNV21(getOriginalWidth(), getOriginalHeight(), bitmap, false));
        getCurrentFURenderInputData().getTexture().setTexId(getOriginalTextId());
    }

    @Override
    public void onResume() {
        if (getIsActivityPause()) {
            getGLSurfaceView().onResume();
        }
        setActivityPause(false);
    }

    @Override
    public void onPause() {
        setActivityPause(true);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        getGLSurfaceView().queueEvent(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1730lambda$onPause$0$comwugufacebeautyBitmapRender(countDownLatch);
            }
        });
        try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        getGLSurfaceView().onPause();
    }

    void m1730lambda$onPause$0$comwugufacebeautyBitmapRender(CountDownLatch countDownLatch) {
        destroyGlSurface();
        countDownLatch.countDown();
    }

    @Override
    public void onDestroy() {
        setGlRendererListener(null);
        setGLSurfaceView(null);
    }

    @Override
    public void surfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        int width = this.bitmap.getWidth();
        int height = this.bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        setOriginalTextId(GlUtil.createImageTexture(this.bitmap));
        setOriginalWidth(this.bitmap.getWidth());
        setOriginalHeight(this.bitmap.getHeight());
        getCurrentFURenderInputData().setWidth(getOriginalWidth());
        getCurrentFURenderInputData().setHeight(getOriginalHeight());
        try {
            getCurrentFURenderInputData().getImageBuffer().setBuffer(BitmapUtils.INSTANCE.getNV21(getOriginalWidth(), getOriginalHeight(), this.bitmap, true));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        getCurrentFURenderInputData().getTexture().setTexId(getOriginalTextId());
        LimitFpsUtil.setTargetFps(15);
    }

    @Override
    public boolean prepareRender(GL10 gl10) {
        return getProgramTexture2d() != null;
    }

    @Override
    public FURenderInputData buildFURenderInputData() {
        return getCurrentFURenderInputData();
    }

    @Override
    public void drawRenderFrame(GL10 gl10) {
        if (getFaceUnity2DTexId() > 0 && getRenderSwitch()) {
            getProgramTexture2d().drawFrame(getFaceUnity2DTexId(), getCurrentFUTexMatrix(), getCurrentFUMvpMatrix());
        } else if (getOriginalTextId() > 0) {
            getProgramTexture2d().drawFrame(getOriginalTextId(), getOriginTexMatrix(), getOriginMvpMatrix());
        }
        if (getDrawSmallViewport()) {
            GLES20.glViewport(getSmallViewportX(), getSmallViewportY(), getSmallViewportWidth(), getSmallViewportHeight());
            getProgramTexture2d().drawFrame(getOriginalTextId(), getOriginTexMatrix(), getSmallViewMatrix());
            GLES20.glViewport(0, 0, getSurfaceViewWidth(), getSurfaceViewHeight());
        }
    }
}
