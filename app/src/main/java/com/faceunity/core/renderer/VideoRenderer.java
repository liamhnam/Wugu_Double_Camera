package com.faceunity.core.renderer;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.Surface;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.entity.FURenderInputData;
import com.faceunity.core.entity.FURenderOutputData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.enumeration.FUInputTextureEnum;
import com.faceunity.core.enumeration.FUTransformMatrixEnum;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.infe.IVideoRenderer;
import com.faceunity.core.listener.OnGlRendererListener;
import com.faceunity.core.listener.OnVideoPlayListener;
import com.faceunity.core.media.photo.OnPhotoRecordingListener;
import com.faceunity.core.media.photo.PhotoRecordHelper;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.program.ProgramTextureOES;
import com.faceunity.core.utils.FULogger;
import com.faceunity.core.utils.GlUtil;
import com.faceunity.core.utils.LimitFpsUtil;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0099\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000*\u0001 \u0018\u00002\u00020\u00012\u00020\u0002B!\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB)\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB3\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b¢\u0006\u0002\u0010\u000eJ\b\u0010:\u001a\u00020;H\u0002J\b\u0010<\u001a\u00020=H\u0014J\b\u0010>\u001a\u00020;H\u0002J\b\u0010?\u001a\u00020;H\u0002J\b\u0010@\u001a\u00020;H\u0002J\b\u0010A\u001a\u00020;H\u0002J\b\u0010B\u001a\u00020;H\u0014J\b\u0010C\u001a\u00020;H\u0002J\u0012\u0010D\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010FH\u0014J\u0006\u0010G\u001a\u000208J\b\u0010H\u001a\u00020;H\u0016J\b\u0010I\u001a\u00020;H\u0016J\b\u0010J\u001a\u00020;H\u0016J\u0006\u0010K\u001a\u00020;J\u0012\u0010L\u001a\u00020\u000b2\b\u0010E\u001a\u0004\u0018\u00010FH\u0014J\b\u0010M\u001a\u00020;H\u0002J\u000e\u0010N\u001a\u00020;2\u0006\u0010O\u001a\u00020)J\b\u0010P\u001a\u00020\u000bH\u0002J\u0012\u0010Q\u001a\u00020;2\b\u0010R\u001a\u0004\u0018\u00010)H\u0016J\b\u0010S\u001a\u00020;H\u0002J\b\u0010T\u001a\u00020;H\u0002J\"\u0010U\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010F2\u0006\u0010V\u001a\u00020\u00102\u0006\u0010W\u001a\u00020\u0010H\u0014J\u001c\u0010X\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0014R\u0014\u0010\u000f\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u0010X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0004\n\u0002\u0010!R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006["}, m1293d2 = {"Lcom/faceunity/core/renderer/VideoRenderer;", "Lcom/faceunity/core/renderer/BaseFURenderer;", "Lcom/faceunity/core/infe/IVideoRenderer;", "gLSurfaceView", "Landroid/opengl/GLSurfaceView;", "videoPath", "", "glRendererListener", "Lcom/faceunity/core/listener/OnGlRendererListener;", "(Landroid/opengl/GLSurfaceView;Ljava/lang/String;Lcom/faceunity/core/listener/OnGlRendererListener;)V", "openUnDrawMode", "", "(Landroid/opengl/GLSurfaceView;Ljava/lang/String;Lcom/faceunity/core/listener/OnGlRendererListener;Z)V", "identicalDrawTextureMode", "(Landroid/opengl/GLSurfaceView;Ljava/lang/String;Lcom/faceunity/core/listener/OnGlRendererListener;ZZ)V", "drawCacheBitmapCacheBitmap", "", "getDrawCacheBitmapCacheBitmap", "()I", "drawCacheBitmapUnCacheBitmap", "getDrawCacheBitmapUnCacheBitmap", "drawNormal", "getDrawNormal", "filterCacheBitmap", "getFilterCacheBitmap", "isShowVideoCacheFrame", "setShowVideoCacheFrame", "(I)V", "mCacheBitmap", "Landroid/graphics/Bitmap;", "mCacheBitmapTexId", "mMediaEventListener", "com/faceunity/core/renderer/VideoRenderer$mMediaEventListener$1", "Lcom/faceunity/core/renderer/VideoRenderer$mMediaEventListener$1;", "mOnPhotoRecordingListener", "Lcom/faceunity/core/media/photo/OnPhotoRecordingListener;", "getMOnPhotoRecordingListener", "()Lcom/faceunity/core/media/photo/OnPhotoRecordingListener;", "mOnPhotoRecordingListener$delegate", "Lkotlin/Lazy;", "mOnVideoPlayListener", "Lcom/faceunity/core/listener/OnVideoPlayListener;", "mPhotoRecordHelper", "Lcom/faceunity/core/media/photo/PhotoRecordHelper;", "mPlayerHandler", "Landroid/os/Handler;", "mProgramTextureOes", "Lcom/faceunity/core/program/ProgramTextureOES;", "mRenderVideoUnDrawTextureListener", "mSimpleExoPlayer", "Lcom/google/android/exoplayer2/SimpleExoPlayer;", "mSurface", "Landroid/view/Surface;", "mSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "videoDuration", "", "videoOrientation", "analysisVideo", "", "buildFURenderInputData", "Lcom/faceunity/core/entity/FURenderInputData;", "cacheBitmap", "createMediaPlayer", "createSurfaceTexture", "deleteCacheBitmapTexId", "destroyGlSurface", "drawCacheBitmap", "drawRenderFrame", "gl", "Ljavax/microedition/khronos/opengles/GL10;", "getDuration", "onDestroy", "onPause", "onResume", "pauseMediaPlayer", "prepareRender", "releaseMediaPlayer", "renderVideoUnDrawTexture", "renderVideoUnDrawTextureListener", "showCacheBitmapLogic", "startMediaPlayer", "listener", "startPlayerThread", "stopPlayerThread", "surfaceChanged", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "surfaceCreated", "config", "Ljavax/microedition/khronos/egl/EGLConfig;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class VideoRenderer extends BaseFURenderer implements IVideoRenderer {
    private final int drawCacheBitmapCacheBitmap;
    private final int drawCacheBitmapUnCacheBitmap;
    private final int drawNormal;
    private final int filterCacheBitmap;
    private final boolean identicalDrawTextureMode;
    private volatile int isShowVideoCacheFrame;
    private Bitmap mCacheBitmap;
    private int mCacheBitmapTexId;
    private final VideoRenderer$mMediaEventListener$1 mMediaEventListener;

    private final Lazy mOnPhotoRecordingListener;
    private OnVideoPlayListener mOnVideoPlayListener;
    private final PhotoRecordHelper mPhotoRecordHelper;
    private Handler mPlayerHandler;
    private ProgramTextureOES mProgramTextureOes;
    private OnVideoPlayListener mRenderVideoUnDrawTextureListener;
    private SimpleExoPlayer mSimpleExoPlayer;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private final boolean openUnDrawMode;
    private long videoDuration;
    private int videoOrientation;
    private final String videoPath;

    private final OnPhotoRecordingListener getMOnPhotoRecordingListener() {
        return (OnPhotoRecordingListener) this.mOnPhotoRecordingListener.getValue();
    }

    public VideoRenderer(GLSurfaceView gLSurfaceView, String str, OnGlRendererListener onGlRendererListener, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(gLSurfaceView, str, onGlRendererListener, (i & 8) != 0 ? false : z, (i & 16) != 0 ? false : z2);
    }

    public VideoRenderer(final GLSurfaceView gLSurfaceView, String videoPath, OnGlRendererListener glRendererListener, boolean z, boolean z2) {
        super(gLSurfaceView, glRendererListener);
        Intrinsics.checkParameterIsNotNull(videoPath, "videoPath");
        Intrinsics.checkParameterIsNotNull(glRendererListener, "glRendererListener");
        this.videoPath = videoPath;
        this.openUnDrawMode = z;
        this.identicalDrawTextureMode = z2;
        FURenderInputData currentFURenderInputData = getCurrentFURenderInputData();
        getCurrentFURenderInputData().setTexture(new FURenderInputData.FUTexture(FUInputTextureEnum.FU_ADM_FLAG_EXTERNAL_OES_TEXTURE, 0));
        FURenderInputData.FURenderConfig renderConfig = currentFURenderInputData.getRenderConfig();
        renderConfig.setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO);
        renderConfig.setCameraFacing(CameraFacingEnum.CAMERA_BACK);
        renderConfig.setInputBufferMatrix(FUTransformMatrixEnum.CCROT0);
        renderConfig.setInputTextureMatrix(FUTransformMatrixEnum.CCROT0);
        setExternalInputType(FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO);
        if (gLSurfaceView != null) {
            gLSurfaceView.setEGLContextClientVersion(GlUtil.getSupportGlVersion(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease()));
        }
        if (gLSurfaceView != null) {
            gLSurfaceView.setRenderer(this);
        }
        if (gLSurfaceView != null) {
            gLSurfaceView.setRenderMode(0);
        }
        this.drawCacheBitmapUnCacheBitmap = 99;
        this.drawCacheBitmapCacheBitmap = 100;
        this.filterCacheBitmap = 5;
        this.isShowVideoCacheFrame = this.drawNormal;
        this.mMediaEventListener = new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                GLSurfaceView gLSurfaceView2;
                if (playbackState == 3) {
                    if (!playWhenReady || (gLSurfaceView2 = gLSurfaceView) == null) {
                        return;
                    }
                    gLSurfaceView2.requestRender();
                    return;
                }
                if (playbackState != 4) {
                    return;
                }
                OnVideoPlayListener onVideoPlayListener = this.this$0.mOnVideoPlayListener;
                if (onVideoPlayListener != null) {
                    onVideoPlayListener.onPlayFinish();
                }
                OnVideoPlayListener onVideoPlayListener2 = this.this$0.mRenderVideoUnDrawTextureListener;
                if (onVideoPlayListener2 != null) {
                    onVideoPlayListener2.onPlayFinish();
                }
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                FULogger.m294e(this.this$0.getTAG(), "onPlayerError:" + error.getMessage() + ' ');
                int i = error.type;
                String str = i != 0 ? i != 1 ? "其他异常" : "解码异常" : "数据源异常";
                OnVideoPlayListener onVideoPlayListener = this.this$0.mOnVideoPlayListener;
                if (onVideoPlayListener != null) {
                    onVideoPlayListener.onError(str);
                }
                OnVideoPlayListener onVideoPlayListener2 = this.this$0.mRenderVideoUnDrawTextureListener;
                if (onVideoPlayListener2 != null) {
                    onVideoPlayListener2.onError(str);
                }
            }
        };
        this.mOnPhotoRecordingListener = LazyKt.lazy(new Function0<OnPhotoRecordingListener>() {
            {
                super(0);
            }

            @Override
            public final OnPhotoRecordingListener invoke() {
                return new OnPhotoRecordingListener() {
                    @Override
                    public final void onRecordSuccess(Bitmap bitmap) {
                        VideoRenderer$mOnPhotoRecordingListener$2.this.this$0.mCacheBitmap = bitmap;
                    }
                };
            }
        });
        this.mPhotoRecordHelper = new PhotoRecordHelper(getMOnPhotoRecordingListener());
    }

    public VideoRenderer(GLSurfaceView gLSurfaceView, String videoPath, OnGlRendererListener glRendererListener) {
        this(gLSurfaceView, videoPath, glRendererListener, false, false);
        Intrinsics.checkParameterIsNotNull(videoPath, "videoPath");
        Intrinsics.checkParameterIsNotNull(glRendererListener, "glRendererListener");
    }

    public VideoRenderer(GLSurfaceView gLSurfaceView, String videoPath, OnGlRendererListener glRendererListener, boolean z) {
        this(gLSurfaceView, videoPath, glRendererListener, z, false);
        Intrinsics.checkParameterIsNotNull(videoPath, "videoPath");
        Intrinsics.checkParameterIsNotNull(glRendererListener, "glRendererListener");
    }

    @Override
    public void onResume() {
        GLSurfaceView gLSurfaceView;
        startPlayerThread();
        if (getIsActivityPause() && (gLSurfaceView = getGLSurfaceView()) != null) {
            gLSurfaceView.onResume();
        }
        setActivityPause(false);
    }

    @Override
    public void onPause() {
        setActivityPause(true);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.mPlayerHandler;
        if (handler2 != null) {
            handler2.post(new Runnable() {
                @Override
                public final void run() {
                    VideoRenderer.this.releaseMediaPlayer();
                    GLSurfaceView gLSurfaceView = VideoRenderer.this.getGLSurfaceView();
                    if (gLSurfaceView != null) {
                        gLSurfaceView.queueEvent(new Runnable() {
                            @Override
                            public final void run() {
                                VideoRenderer.this.destroyGlSurface();
                                countDownLatch.countDown();
                            }
                        });
                    }
                }
            });
        }
        try {
            countDownLatch.await(500L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        GLSurfaceView gLSurfaceView = getGLSurfaceView();
        if (gLSurfaceView != null) {
            gLSurfaceView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        stopPlayerThread();
        setGlRendererListener(null);
        setGLSurfaceView(null);
    }

    public final int getDrawNormal() {
        return this.drawNormal;
    }

    public final int getDrawCacheBitmapUnCacheBitmap() {
        return this.drawCacheBitmapUnCacheBitmap;
    }

    public final int getDrawCacheBitmapCacheBitmap() {
        return this.drawCacheBitmapCacheBitmap;
    }

    public final int getFilterCacheBitmap() {
        return this.filterCacheBitmap;
    }

    public final int getIsShowVideoCacheFrame() {
        return this.isShowVideoCacheFrame;
    }

    public final void setShowVideoCacheFrame(int i) {
        this.isShowVideoCacheFrame = i;
    }

    @Override
    protected void surfaceCreated(GL10 gl, EGLConfig config) throws IOException {
        if (this.identicalDrawTextureMode) {
            setProgramTexture2d(new ProgramTexture2d(this.identicalDrawTextureMode));
        }
        setOriginalTextId(GlUtil.createTextureObject(36197));
        FURenderInputData.FUTexture texture = getCurrentFURenderInputData().getTexture();
        if (texture != null) {
            texture.setTexId(getOriginalTextId());
        }
        this.mProgramTextureOes = new ProgramTextureOES();
        createSurfaceTexture();
        analysisVideo();
        LimitFpsUtil.setTargetFps(30);
        this.isShowVideoCacheFrame = this.drawNormal;
    }

    @Override
    protected void surfaceChanged(GL10 gl, int width, int height) {
        float[] fArrChangeMvpMatrixInside;
        int i = this.videoOrientation;
        if (i == 0 || i == 180) {
            fArrChangeMvpMatrixInside = GlUtil.changeMvpMatrixInside(width, height, getOriginalWidth(), getOriginalHeight());
            Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixInside, "GlUtil.changeMvpMatrixIn…t.toFloat()\n            )");
        } else {
            fArrChangeMvpMatrixInside = GlUtil.changeMvpMatrixInside(width, height, getOriginalHeight(), getOriginalWidth());
            Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixInside, "GlUtil.changeMvpMatrixIn…h.toFloat()\n            )");
        }
        setOriginMvpMatrix(fArrChangeMvpMatrixInside);
        float[] fArrChangeMvpMatrixCrop = GlUtil.changeMvpMatrixCrop(90.0f, 160.0f, getOriginalHeight(), getOriginalWidth());
        Intrinsics.checkExpressionValueIsNotNull(fArrChangeMvpMatrixCrop, "GlUtil.changeMvpMatrixCr… originalWidth.toFloat())");
        setSmallViewMatrix(fArrChangeMvpMatrixCrop);
        float[] originMvpMatrix = getOriginMvpMatrix();
        float[] fArrCopyOf = Arrays.copyOf(originMvpMatrix, originMvpMatrix.length);
        Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
        setDefaultFUMvpMatrix(fArrCopyOf);
        int i2 = this.videoOrientation;
        if (i2 == 90) {
            Matrix.rotateM(getDefaultFUMvpMatrix(), 0, 270.0f, 0.0f, 0.0f, 1.0f);
        } else if (i2 == 180) {
            Matrix.rotateM(getDefaultFUMvpMatrix(), 0, 180.0f, 0.0f, 0.0f, 1.0f);
        } else {
            if (i2 != 270) {
                return;
            }
            Matrix.rotateM(getDefaultFUMvpMatrix(), 0, 90.0f, 0.0f, 0.0f, 1.0f);
        }
    }

    @Override
    protected boolean prepareRender(GL10 gl) {
        if (this.mSurfaceTexture == null || getProgramTexture2d() == null) {
            return false;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture == null) {
            Intrinsics.throwNpe();
        }
        surfaceTexture.updateTexImage();
        SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
        if (surfaceTexture2 == null) {
            Intrinsics.throwNpe();
        }
        surfaceTexture2.getTransformMatrix(getOriginTexMatrix());
        return true;
    }

    @Override
    protected FURenderInputData buildFURenderInputData() {
        return getCurrentFURenderInputData();
    }

    @Override
    protected void drawRenderFrame(GL10 gl) {
        if (showCacheBitmapLogic()) {
            return;
        }
        if (getFaceUnity2DTexId() > 0 && getRenderSwitch()) {
            if (this.identicalDrawTextureMode) {
                float[] originTexMatrix = getOriginTexMatrix();
                float[] fArrCopyOf = Arrays.copyOf(originTexMatrix, originTexMatrix.length);
                Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                Matrix.multiplyMM(fArrCopyOf, 0, getTEXTURE_MATRIX_CCRO_FLIPV_0_LLQ(), 0, fArrCopyOf, 0);
                ProgramTexture2d programTexture2d = getProgramTexture2d();
                if (programTexture2d == null) {
                    Intrinsics.throwNpe();
                }
                programTexture2d.drawFrame(getFaceUnity2DTexId(), fArrCopyOf, getOriginMvpMatrix());
            } else {
                ProgramTexture2d programTexture2d2 = getProgramTexture2d();
                if (programTexture2d2 == null) {
                    Intrinsics.throwNpe();
                }
                programTexture2d2.drawFrame(getFaceUnity2DTexId(), getCurrentFUTexMatrix(), getCurrentFUMvpMatrix());
            }
        } else if (getOriginalTextId() > 0) {
            ProgramTextureOES programTextureOES = this.mProgramTextureOes;
            if (programTextureOES == null) {
                Intrinsics.throwNpe();
            }
            programTextureOES.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getOriginMvpMatrix());
        }
        if (getDrawSmallViewport()) {
            GLES20.glViewport(getSmallViewportX(), getSmallViewportY(), getSmallViewportWidth(), getSmallViewportHeight());
            ProgramTextureOES programTextureOES2 = this.mProgramTextureOes;
            if (programTextureOES2 == null) {
                Intrinsics.throwNpe();
            }
            programTextureOES2.drawFrame(getOriginalTextId(), getOriginTexMatrix(), getSmallViewMatrix());
            GLES20.glViewport(0, 0, getSurfaceViewWidth(), getSurfaceViewHeight());
        }
    }

    private final boolean showCacheBitmapLogic() {
        if (!this.openUnDrawMode) {
            return false;
        }
        if (this.isShowVideoCacheFrame >= this.drawCacheBitmapUnCacheBitmap) {
            if (this.isShowVideoCacheFrame == this.drawCacheBitmapCacheBitmap) {
                cacheBitmap();
                this.isShowVideoCacheFrame = this.drawCacheBitmapUnCacheBitmap;
            }
            drawCacheBitmap();
            return true;
        }
        int i = this.drawNormal + 1;
        int i2 = this.filterCacheBitmap;
        int i3 = this.isShowVideoCacheFrame;
        if (i > i3 || i2 < i3) {
            return false;
        }
        this.isShowVideoCacheFrame--;
        drawCacheBitmap();
        return true;
    }

    private final void createSurfaceTexture() {
        SurfaceTexture surfaceTexture = new SurfaceTexture(getOriginalTextId());
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                GLSurfaceView gLSurfaceView = VideoRenderer.this.getGLSurfaceView();
                if (gLSurfaceView != null) {
                    gLSurfaceView.requestRender();
                }
            }
        });
        this.mSurface = new Surface(this.mSurfaceTexture);
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    SimpleExoPlayer simpleExoPlayer = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.setVideoSurface(VideoRenderer.this.mSurface);
                    }
                }
            });
        }
    }

    private final void analysisVideo() throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(this.videoPath);
                String strExtractMetadata = mediaMetadataRetriever.extractMetadata(18);
                Intrinsics.checkExpressionValueIsNotNull(strExtractMetadata, "mediaMetadataRetriever.e…METADATA_KEY_VIDEO_WIDTH)");
                setOriginalWidth(Integer.parseInt(strExtractMetadata));
                String strExtractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                Intrinsics.checkExpressionValueIsNotNull(strExtractMetadata2, "mediaMetadataRetriever.e…ETADATA_KEY_VIDEO_HEIGHT)");
                setOriginalHeight(Integer.parseInt(strExtractMetadata2));
                String strExtractMetadata3 = mediaMetadataRetriever.extractMetadata(24);
                Intrinsics.checkExpressionValueIsNotNull(strExtractMetadata3, "mediaMetadataRetriever.e…ADATA_KEY_VIDEO_ROTATION)");
                this.videoOrientation = Integer.parseInt(strExtractMetadata3);
                String strExtractMetadata4 = mediaMetadataRetriever.extractMetadata(9);
                Intrinsics.checkExpressionValueIsNotNull(strExtractMetadata4, "mediaMetadataRetriever.e…er.METADATA_KEY_DURATION)");
                this.videoDuration = Long.parseLong(strExtractMetadata4);
                FURenderInputData currentFURenderInputData = getCurrentFURenderInputData();
                currentFURenderInputData.setWidth(getOriginalWidth());
                currentFURenderInputData.setHeight(getOriginalHeight());
                currentFURenderInputData.getRenderConfig().setInputOrientation(this.videoOrientation);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    @Override
    protected void destroyGlSurface() {
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        deleteCacheBitmapTexId();
        super.destroyGlSurface();
    }

    public final void createMediaPlayer() {
        SimpleExoPlayer simpleExoPlayerBuild = new SimpleExoPlayer.Builder(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease()).build();
        this.mSimpleExoPlayer = simpleExoPlayerBuild;
        if (simpleExoPlayerBuild == null) {
            Intrinsics.throwNpe();
        }
        simpleExoPlayerBuild.addListener(this.mMediaEventListener);
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer == null) {
            Intrinsics.throwNpe();
        }
        simpleExoPlayer.setPlayWhenReady(false);
        String userAgent = Util.getUserAgent(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease().getPackageName());
        Intrinsics.checkExpressionValueIsNotNull(userAgent, "Util.getUserAgent(FURend…ger.mContext.packageName)");
        ProgressiveMediaSource progressiveMediaSourceCreateMediaSource = new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), userAgent)).createMediaSource(Uri.fromFile(new File(this.videoPath)));
        Intrinsics.checkExpressionValueIsNotNull(progressiveMediaSourceCreateMediaSource, "mediaSourceFactory.createMediaSource(uri)");
        ProgressiveMediaSource progressiveMediaSource = progressiveMediaSourceCreateMediaSource;
        SimpleExoPlayer simpleExoPlayer2 = this.mSimpleExoPlayer;
        if (simpleExoPlayer2 == null) {
            Intrinsics.throwNpe();
        }
        simpleExoPlayer2.prepare(progressiveMediaSource);
    }

    public final void releaseMediaPlayer() {
        this.mOnVideoPlayListener = null;
        this.mRenderVideoUnDrawTextureListener = null;
        this.videoDuration = 0L;
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            if (simpleExoPlayer == null) {
                Intrinsics.throwNpe();
            }
            simpleExoPlayer.stop(true);
            SimpleExoPlayer simpleExoPlayer2 = this.mSimpleExoPlayer;
            if (simpleExoPlayer2 == null) {
                Intrinsics.throwNpe();
            }
            simpleExoPlayer2.release();
            this.mSimpleExoPlayer = null;
        }
    }

    @Override
    public void startMediaPlayer(OnVideoPlayListener listener) {
        this.mOnVideoPlayListener = listener;
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    if (VideoRenderer.this.openUnDrawMode) {
                        VideoRenderer videoRenderer = VideoRenderer.this;
                        videoRenderer.setShowVideoCacheFrame(videoRenderer.getDrawNormal());
                    }
                    SimpleExoPlayer simpleExoPlayer = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.setVolume(1.0f);
                    }
                    SimpleExoPlayer simpleExoPlayer2 = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer2 != null) {
                        simpleExoPlayer2.seekTo(0L);
                    }
                    SimpleExoPlayer simpleExoPlayer3 = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer3 != null) {
                        simpleExoPlayer3.setPlayWhenReady(true);
                    }
                }
            });
        }
    }

    public final void pauseMediaPlayer() {
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    SimpleExoPlayer simpleExoPlayer = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer != null) {
                        simpleExoPlayer.setPlayWhenReady(false);
                    }
                    SimpleExoPlayer simpleExoPlayer2 = VideoRenderer.this.mSimpleExoPlayer;
                    if (simpleExoPlayer2 != null) {
                        simpleExoPlayer2.seekTo(0L);
                    }
                    if (VideoRenderer.this.openUnDrawMode) {
                        VideoRenderer videoRenderer = VideoRenderer.this;
                        videoRenderer.setShowVideoCacheFrame(videoRenderer.getFilterCacheBitmap());
                    }
                }
            });
        }
    }

    private final void startPlayerThread() {
        if (this.mPlayerHandler == null) {
            HandlerThread handlerThread = new HandlerThread("exo_player");
            handlerThread.start();
            this.mPlayerHandler = new Handler(handlerThread.getLooper());
        }
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.post(new Runnable() {
                @Override
                public final void run() {
                    VideoRenderer.this.createMediaPlayer();
                }
            });
        }
    }

    private final void stopPlayerThread() {
        Looper looper;
        Handler handler = this.mPlayerHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.mPlayerHandler;
        if (handler2 != null) {
            handler2.post(new Runnable() {
                @Override
                public final void run() {
                    VideoRenderer.this.releaseMediaPlayer();
                }
            });
        }
        Handler handler3 = this.mPlayerHandler;
        if (handler3 != null && (looper = handler3.getLooper()) != null) {
            looper.quitSafely();
        }
        this.mPlayerHandler = null;
    }

    public final void renderVideoUnDrawTexture(OnVideoPlayListener renderVideoUnDrawTextureListener) {
        Intrinsics.checkParameterIsNotNull(renderVideoUnDrawTextureListener, "renderVideoUnDrawTextureListener");
        if (this.openUnDrawMode) {
            if (this.isShowVideoCacheFrame != this.drawCacheBitmapUnCacheBitmap) {
                this.isShowVideoCacheFrame = this.drawCacheBitmapCacheBitmap;
            }
            this.mRenderVideoUnDrawTextureListener = renderVideoUnDrawTextureListener;
            Handler handler = this.mPlayerHandler;
            if (handler != null) {
                handler.post(new Runnable() {
                    @Override
                    public final void run() {
                        SimpleExoPlayer simpleExoPlayer = VideoRenderer.this.mSimpleExoPlayer;
                        if (simpleExoPlayer != null) {
                            simpleExoPlayer.seekTo(0L);
                        }
                        SimpleExoPlayer simpleExoPlayer2 = VideoRenderer.this.mSimpleExoPlayer;
                        if (simpleExoPlayer2 != null) {
                            simpleExoPlayer2.setVolume(0.0f);
                        }
                        SimpleExoPlayer simpleExoPlayer3 = VideoRenderer.this.mSimpleExoPlayer;
                        if (simpleExoPlayer3 != null) {
                            simpleExoPlayer3.setPlayWhenReady(true);
                        }
                    }
                });
            }
        }
    }

    private final void cacheBitmap() {
        if (getCurrentFURenderOutputData() != null) {
            FURenderOutputData currentFURenderOutputData = getCurrentFURenderOutputData();
            if (currentFURenderOutputData == null) {
                Intrinsics.throwNpe();
            }
            if (currentFURenderOutputData.getTexture() != null) {
                PhotoRecordHelper photoRecordHelper = this.mPhotoRecordHelper;
                int faceUnity2DTexId = getFaceUnity2DTexId();
                float[] texture_matrix = getTEXTURE_MATRIX();
                float[] texture_matrix2 = getTEXTURE_MATRIX();
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
                photoRecordHelper.sendRecordingData(faceUnity2DTexId, texture_matrix, texture_matrix2, width, texture2.getHeight(), false, false);
            }
        }
    }

    private final void drawCacheBitmap() {
        Bitmap bitmap = this.mCacheBitmap;
        if (bitmap != null) {
            deleteCacheBitmapTexId();
            int iCreateImageTexture = GlUtil.createImageTexture(bitmap);
            this.mCacheBitmapTexId = iCreateImageTexture;
            if (iCreateImageTexture > 0) {
                GLES20.glClear(16640);
                if (this.identicalDrawTextureMode) {
                    ProgramTexture2d programTexture2d = getProgramTexture2d();
                    if (programTexture2d == null) {
                        Intrinsics.throwNpe();
                    }
                    programTexture2d.drawFrame(this.mCacheBitmapTexId, getOriginTexMatrix(), getOriginMvpMatrix());
                    return;
                }
                float[] currentFUMvpMatrix = getCurrentFUMvpMatrix();
                float[] fArrCopyOf = Arrays.copyOf(currentFUMvpMatrix, currentFUMvpMatrix.length);
                Intrinsics.checkExpressionValueIsNotNull(fArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                Matrix.scaleM(fArrCopyOf, 0, 1.0f, -1.0f, 1.0f);
                ProgramTexture2d programTexture2d2 = getProgramTexture2d();
                if (programTexture2d2 == null) {
                    Intrinsics.throwNpe();
                }
                programTexture2d2.drawFrame(this.mCacheBitmapTexId, getCurrentFUTexMatrix(), fArrCopyOf);
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

    public final long getDuration() {
        long j = this.videoDuration;
        if (j != 0) {
            return j;
        }
        SimpleExoPlayer simpleExoPlayer = this.mSimpleExoPlayer;
        if (simpleExoPlayer != null) {
            return simpleExoPlayer.getDuration();
        }
        return 0L;
    }
}
