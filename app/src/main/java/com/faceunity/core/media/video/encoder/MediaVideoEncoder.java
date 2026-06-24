package com.faceunity.core.media.video.encoder;

import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.opengl.EGLContext;
import android.opengl.GLES20;
import android.util.Log;
import android.view.Surface;
import com.faceunity.core.media.video.encoder.MediaEncoder;
import com.faceunity.core.program.ProgramTexture2d;
import com.faceunity.core.utils.GlUtil;

public class MediaVideoEncoder extends MediaEncoder {
    private static final float BPP = 0.25f;
    private static final boolean DEBUG = false;
    private static final int FRAME_RATE = 25;
    private static final String MIME_TYPE = "video/avc";
    protected static int[] recognizedFormats = {2130708361};
    String TAG;
    private int cropX;
    private int cropY;
    private int[] mFboId;
    private int[] mFboTex;
    private int mFrameCount;
    private final int mHeight;
    private RenderHandler mRenderHandler;
    private Surface mSurface;
    private int[] mViewPort;
    private final int mWidth;
    private ProgramTexture2d program;
    private int textureHeight;
    private int textureWidth;

    public MediaVideoEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener, int i, int i2) {
        this(mediaMuxerWrapper, mediaEncoderListener, i, i2, 0, 0, i, i2);
    }

    public MediaVideoEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener, int i, int i2, int i3, int i4, int i5, int i6) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        this.TAG = "Video_MediaVideoEncoder";
        this.mViewPort = new int[4];
        this.mWidth = i;
        this.mHeight = i2;
        this.mRenderHandler = RenderHandler.createHandler("Video_MediaVideoEncoder");
        this.cropX = i3;
        this.cropY = i4;
        this.textureWidth = i5;
        this.textureHeight = i6;
    }

    public boolean frameAvailableSoon(int i, float[] fArr, float[] fArr2) {
        if (this.program == null) {
            return false;
        }
        GLES20.glGetIntegerv(2978, this.mViewPort, 0);
        GLES20.glBindFramebuffer(36160, this.mFboId[0]);
        GLES20.glViewport(this.cropX, this.cropY, this.textureWidth, this.textureHeight);
        this.program.drawFrame(i, fArr, fArr2);
        GLES20.glBindFramebuffer(36160, 0);
        int[] iArr = this.mViewPort;
        GLES20.glViewport(iArr[0], iArr[1], iArr[2], iArr[3]);
        int i2 = this.mFrameCount;
        this.mFrameCount = i2 + 1;
        if (i2 < 3) {
            return true;
        }
        boolean zFrameAvailableSoon = super.frameAvailableSoon();
        if (zFrameAvailableSoon) {
            this.mRenderHandler.draw(this.mFboTex[0], fArr, GlUtil.IDENTITY_MATRIX);
        }
        return zFrameAvailableSoon;
    }

    @Override
    protected void prepare() {
        try {
            this.mTrackIndex = -1;
            this.mIsEOS = false;
            this.mMuxerStarted = false;
            if (selectVideoCodec("video/avc") == null) {
                Log.e(this.TAG, "Unable to find an appropriate codec for video/avc");
                return;
            }
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mWidth, this.mHeight);
            mediaFormatCreateVideoFormat.setInteger("color-format", 2130708361);
            mediaFormatCreateVideoFormat.setInteger("bitrate", calcBitRate());
            mediaFormatCreateVideoFormat.setInteger("frame-rate", 25);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 10);
            this.mMediaCodec = MediaCodec.createEncoderByType("video/avc");
            this.mMediaCodec.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
            this.mSurface = this.mMediaCodec.createInputSurface();
            this.mMediaCodec.start();
            if (this.mListener != null) {
                try {
                    this.mListener.onPrepared(this);
                } catch (Exception e) {
                    Log.e(this.TAG, "prepare:", e);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            release();
        }
    }

    public void setEglContext(EGLContext eGLContext) {
        int[] iArr = new int[1];
        this.mFboTex = iArr;
        int[] iArr2 = new int[1];
        this.mFboId = iArr2;
        GlUtil.createFrameBuffers(iArr, iArr2, this.mWidth, this.mHeight);
        this.program = new ProgramTexture2d();
        RenderHandler renderHandler = this.mRenderHandler;
        if (renderHandler != null) {
            renderHandler.setEglContext(eGLContext, this.mSurface, this.mFboTex[0]);
        }
    }

    @Override
    protected void release() {
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        RenderHandler renderHandler = this.mRenderHandler;
        if (renderHandler != null) {
            renderHandler.release();
            this.mRenderHandler = null;
        }
        this.mFrameCount = 0;
        super.release();
    }

    public void releaseGL() {
        GlUtil.deleteFrameBuffers(this.mFboId);
        int[] iArr = this.mFboId;
        if (iArr != null) {
            iArr[0] = -1;
        }
        GlUtil.deleteTextures(this.mFboTex);
        int[] iArr2 = this.mFboTex;
        if (iArr2 != null) {
            iArr2[0] = -1;
        }
        ProgramTexture2d programTexture2d = this.program;
        if (programTexture2d != null) {
            programTexture2d.release();
            this.program = null;
        }
    }

    private int calcBitRate() {
        int i = (int) (this.mWidth * 6.25f * this.mHeight);
        Log.i(this.TAG, String.format("bitrate=%5.2f[Mbps]", Float.valueOf((i / 1024.0f) / 1024.0f)));
        return i;
    }

    protected final MediaCodecInfo selectVideoCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str) && selectColorFormat(codecInfoAt, str) > 0) {
                        return codecInfoAt;
                    }
                }
            }
        }
        return null;
    }

    protected final int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        try {
            Thread.currentThread().setPriority(10);
            MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
            Thread.currentThread().setPriority(5);
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= capabilitiesForType.colorFormats.length) {
                    break;
                }
                int i3 = capabilitiesForType.colorFormats[i2];
                if (isRecognizedViewoFormat(i3)) {
                    i = i3;
                    break;
                }
                i2++;
            }
            if (i == 0) {
                Log.e(this.TAG, "couldn't find a good color format for " + mediaCodecInfo.getName() + " / " + str);
            }
            return i;
        } catch (Throwable th) {
            Thread.currentThread().setPriority(5);
            throw th;
        }
    }

    private final boolean isRecognizedViewoFormat(int i) {
        int[] iArr = recognizedFormats;
        int length = iArr != null ? iArr.length : 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (recognizedFormats[i2] == i) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void signalEndOfInputStream() {
        if (this.mMediaCodec != null) {
            try {
                this.mMediaCodec.signalEndOfInputStream();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
        this.mIsEOS = true;
    }
}
