package com.faceunity.core.utils;

import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.Surface;
import com.faceunity.core.program.ProgramTextureOES;
import com.faceunity.core.program.core.EglCore;
import com.faceunity.core.program.core.OffscreenSurface;
import com.p020hp.jipp.model.Status;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class VideoDecoder implements SurfaceTexture.OnFrameAvailableListener {
    private static int FILTER_FRAME = 0;
    private static final String TAG = "KIT_VideoDecoder";
    private Handler mDecodeHandler;
    private EglCore mEglCore;
    private boolean mIsFrontCam;
    private MediaPlayer mMediaPlayer;
    private float[] mMvpMatrix;
    private OffscreenSurface mOffscreenSurface;
    private OnReadPixelListener mOnReadPixelListener;
    private ProgramTextureOES mProgramTextureOES;
    private ByteBuffer mRgbaBuffer;
    private byte[] mRgbaByte;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private String mVideoPath;
    private int mVideoTexId;
    private int mVideoWidth = Status.Code.serverErrorInternalError;
    private int mVideoHeight = 720;
    private EGLContext mSharedContext = EGL14.EGL_NO_CONTEXT;
    private float[] mTexMatrix = new float[16];
    private int[] mTextures = new int[1];
    private int[] mFrameBuffers = new int[1];
    private volatile int mFilterFrame = FILTER_FRAME;

    public interface OnReadPixelListener {
        void onReadImagePixel(int i, int i2, byte[] bArr);

        void onReadVideoPixel(int i, int i2, byte[] bArr);
    }

    @Override
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        OnReadPixelListener onReadPixelListener;
        try {
            surfaceTexture.updateTexImage();
            surfaceTexture.getTransformMatrix(this.mTexMatrix);
            int i = this.mVideoWidth;
            int i2 = this.mVideoHeight;
            GLES20.glViewport(0, 0, i, i2);
            GLES20.glBindFramebuffer(36160, this.mFrameBuffers[0]);
            GLES20.glClear(16640);
            ProgramTextureOES programTextureOES = this.mProgramTextureOES;
            if (programTextureOES != null) {
                programTextureOES.drawFrame(this.mVideoTexId, this.mTexMatrix, this.mMvpMatrix);
            }
            ByteBuffer byteBuffer = this.mRgbaBuffer;
            byteBuffer.rewind();
            GLES20.glReadPixels(0, 0, i, i2, 6408, 5121, byteBuffer);
            GLES20.glBindFramebuffer(36160, 0);
            byteBuffer.rewind();
            byteBuffer.get(this.mRgbaByte);
            if (this.mFilterFrame > 0) {
                this.mFilterFrame--;
            } else {
                if (this.mFilterFrame != 0 || (onReadPixelListener = this.mOnReadPixelListener) == null) {
                    return;
                }
                onReadPixelListener.onReadVideoPixel(i, i2, this.mRgbaByte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOnReadPixelListener(OnReadPixelListener onReadPixelListener) {
        this.mOnReadPixelListener = onReadPixelListener;
    }

    public void create(EGLContext eGLContext, boolean z) {
        Log.d(TAG, "create() called with: sharedContext = [" + eGLContext + "], isFrontCam = [" + z + "]");
        this.mSharedContext = eGLContext;
        this.mIsFrontCam = z;
        HandlerThread handlerThread = new HandlerThread("video_decoder");
        handlerThread.start();
        this.mDecodeHandler = new Handler(handlerThread.getLooper());
    }

    public void setFrontCam(final boolean z) {
        this.mDecodeHandler.post(new Runnable() {
            @Override
            public void run() {
                VideoDecoder.this.mIsFrontCam = z;
                VideoDecoder.this.computeDrawParams();
            }
        });
    }

    public void computeDrawParams() {
        float[] fArrCopyOf = Arrays.copyOf(GlUtil.IDENTITY_MATRIX, GlUtil.IDENTITY_MATRIX.length);
        this.mMvpMatrix = fArrCopyOf;
        Matrix.rotateM(fArrCopyOf, 0, 180.0f, 0.0f, 0.0f, 1.0f);
        Matrix.scaleM(this.mMvpMatrix, 0, this.mIsFrontCam ? 1.0f : -1.0f, 1.0f, 1.0f);
    }

    public void start(String str) {
        Log.d(TAG, "start: ");
        this.mVideoPath = str;
        this.mDecodeHandler.post(new Runnable() {
            @Override
            public final void run() throws IOException {
                this.f$0.m1550lambda$start$0$comfaceunitycoreutilsVideoDecoder();
            }
        });
    }

    void m1550lambda$start$0$comfaceunitycoreutilsVideoDecoder() throws IOException {
        retrieveVideoInfo();
        createSurface();
        createMediaPlayer();
        this.mFilterFrame = FILTER_FRAME;
    }

    public void stop() {
        Log.d(TAG, "stop: ");
        if (this.mFilterFrame == -1) {
            return;
        }
        this.mFilterFrame = -1;
        if (this.mSurfaceTexture != null) {
            this.mSurfaceTexture.setOnFrameAvailableListener(null, this.mDecodeHandler);
        }
        this.mDecodeHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1551lambda$stop$1$comfaceunitycoreutilsVideoDecoder();
            }
        });
    }

    void m1551lambda$stop$1$comfaceunitycoreutilsVideoDecoder() {
        releaseMediaPlayer();
        releaseSurface();
    }

    public void release() {
        Log.d(TAG, "release");
        stop();
        this.mDecodeHandler.getLooper().quitSafely();
    }

    private void retrieveVideoInfo() throws IOException {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(this.mVideoPath);
                int i = Integer.parseInt(mediaMetadataRetriever.extractMetadata(18));
                int i2 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(19));
                int i3 = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
                this.mVideoWidth = (i3 == 90 || i3 == 270) ? i2 : i;
                if (i3 != 90 && i3 != 270) {
                    i = i2;
                }
                this.mVideoHeight = i;
            } catch (Exception e) {
                Log.e(TAG, "MediaMetadataRetriever extractMetadata: ", e);
            }
            mediaMetadataRetriever.release();
            int i4 = this.mVideoWidth * this.mVideoHeight * 4;
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i4);
            this.mRgbaBuffer = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            this.mRgbaByte = new byte[i4];
            computeDrawParams();
            mediaMetadataRetriever = "retrieveVideoInfo DecodeVideoTask path:" + this.mVideoPath + ", width:" + this.mVideoWidth + ", height:" + this.mVideoHeight;
            Log.d(TAG, mediaMetadataRetriever);
        } catch (Throwable th) {
            mediaMetadataRetriever.release();
            throw th;
        }
    }

    private void createSurface() {
        Log.d(TAG, "createSurface");
        releaseSurface();
        this.mEglCore = new EglCore(this.mSharedContext, 0);
        OffscreenSurface offscreenSurface = new OffscreenSurface(this.mEglCore, this.mVideoWidth, this.mVideoHeight);
        this.mOffscreenSurface = offscreenSurface;
        offscreenSurface.makeCurrent();
        this.mVideoTexId = GlUtil.createTextureObject(36197);
        this.mSurfaceTexture = new SurfaceTexture(this.mVideoTexId);
        this.mProgramTextureOES = new ProgramTextureOES();
        GlUtil.createFrameBuffers(this.mTextures, this.mFrameBuffers, this.mVideoWidth, this.mVideoHeight);
        this.mSurfaceTexture.setOnFrameAvailableListener(this, this.mDecodeHandler);
    }

    public void createMediaPlayer() {
        Log.d(TAG, "createMediaPlayer");
        releaseMediaPlayer();
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setDataSource(this.mVideoPath);
            this.mMediaPlayer.setVolume(0.0f, 0.0f);
            this.mMediaPlayer.setLooping(true);
            Surface surface = new Surface(this.mSurfaceTexture);
            this.mSurface = surface;
            this.mMediaPlayer.setSurface(surface);
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    Log.d(VideoDecoder.TAG, "onPrepared");
                    VideoDecoder.this.mMediaPlayer.start();
                }
            });
            this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                    mediaPlayer2.reset();
                    VideoDecoder.this.createMediaPlayer();
                    return true;
                }
            });
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            Log.e(TAG, "createMediaPlayer: ", e);
        }
    }

    private void releaseSurface() {
        Log.d(TAG, "releaseSurface");
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.setOnFrameAvailableListener(null);
            this.mSurfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        ProgramTextureOES programTextureOES = this.mProgramTextureOES;
        if (programTextureOES != null) {
            programTextureOES.release();
            this.mProgramTextureOES = null;
        }
        int[] iArr = this.mFrameBuffers;
        if (iArr[0] > 0) {
            GlUtil.deleteFrameBuffers(iArr);
            this.mFrameBuffers[0] = -1;
        }
        int[] iArr2 = this.mTextures;
        if (iArr2[0] > 0) {
            GlUtil.deleteTextures(iArr2);
            this.mTextures[0] = -1;
        }
        int i = this.mVideoTexId;
        if (i > 0) {
            GlUtil.deleteTextures(new int[]{i});
            this.mVideoTexId = -1;
        }
        OffscreenSurface offscreenSurface = this.mOffscreenSurface;
        if (offscreenSurface != null) {
            offscreenSurface.release();
            this.mOffscreenSurface = null;
        }
        EglCore eglCore = this.mEglCore;
        if (eglCore != null) {
            eglCore.release();
            this.mEglCore = null;
        }
        this.mSharedContext = EGL14.EGL_NO_CONTEXT;
    }

    private void releaseMediaPlayer() {
        Log.d(TAG, "releaseMediaPlayer");
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.mMediaPlayer.release();
            } catch (Exception e) {
                Log.e(TAG, "releaseMediaPlayer: ", e);
            }
            this.mMediaPlayer = null;
        }
    }

    public void setFilterFrame(int i) {
        FILTER_FRAME = i;
        this.mFilterFrame = i;
    }
}
