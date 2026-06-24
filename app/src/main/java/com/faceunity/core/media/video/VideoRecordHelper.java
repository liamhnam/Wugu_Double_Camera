package com.faceunity.core.media.video;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaMetadataRetriever;
import android.opengl.EGL14;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import com.faceunity.core.media.video.encoder.MediaAudioEncoder;
import com.faceunity.core.media.video.encoder.MediaAudioFileEncoder;
import com.faceunity.core.media.video.encoder.MediaEncoder;
import com.faceunity.core.media.video.encoder.MediaMuxerWrapper;
import com.faceunity.core.media.video.encoder.MediaVideoEncoder;
import com.faceunity.core.utils.DecimalUtils;
import com.faceunity.core.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class VideoRecordHelper {
    private static final boolean DEBUG = true;
    public static final int MAX_VIDEO_LENGTH = 1920;
    private static final String TAG = "Video_RecordHelper";
    private Context mContext;
    private volatile CountDownLatch mCountDownLatch;
    private GLSurfaceView mGLSurfaceView;
    private MediaMuxerWrapper mMuxer;
    private OnVideoRecordingListener mOnVideoRecordingListener;
    private File mOutputFile;
    private MediaVideoEncoder mVideoEncoder;
    private int videoOrientation = 0;
    private final Object mRecordLock = new Object();
    private volatile Long frameAvailableTime = 0L;
    private volatile boolean isStopRecording = false;
    private volatile boolean isRecording = false;
    private final MediaEncoder.MediaEncoderListener mMediaEncoderListener = new C09941();

    public VideoRecordHelper(Context context, OnVideoRecordingListener onVideoRecordingListener) {
        this.mContext = context;
        this.mOnVideoRecordingListener = onVideoRecordingListener;
    }

    public void startRecording(GLSurfaceView gLSurfaceView, int i, int i2) {
        if (this.isRecording) {
            Log.e(TAG, "startRecording failed ,VideoRecordHelper has  Recording now");
            return;
        }
        this.isRecording = true;
        Log.v(TAG, "startRecording:");
        this.mGLSurfaceView = gLSurfaceView;
        this.isStopRecording = false;
        this.frameAvailableTime = 0L;
        try {
            this.mOutputFile = FileUtils.getCacheVideoFile(this.mContext);
            this.mMuxer = new MediaMuxerWrapper(this.mOutputFile.getAbsolutePath());
            this.mCountDownLatch = new CountDownLatch(2);
            Log.e(TAG, "startRecording  mCountDownLatch" + this.mCountDownLatch.getCount());
            new MediaVideoEncoder(this.mMuxer, this.mMediaEncoderListener, (i << 1) >> 1, (i2 << 1) >> 1);
            new MediaAudioEncoder(this.mMuxer, this.mMediaEncoderListener);
            this.mMuxer.prepare();
            this.mMuxer.startRecording();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startRecording(GLSurfaceView gLSurfaceView, int i, int i2, String str) {
        if (this.isRecording) {
            Log.e(TAG, "startRecording failed ,VideoRecordHelper has  Recording now");
            return;
        }
        this.isRecording = true;
        Log.v(TAG, "startRecording:");
        this.mGLSurfaceView = gLSurfaceView;
        this.isStopRecording = false;
        this.frameAvailableTime = 0L;
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                try {
                    mediaMetadataRetriever.setDataSource(str);
                    this.videoOrientation = Integer.parseInt(mediaMetadataRetriever.extractMetadata(24));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.mOutputFile = FileUtils.getCacheVideoFile(this.mContext);
                this.mMuxer = new MediaMuxerWrapper(this.mOutputFile.getAbsolutePath());
                this.mCountDownLatch = new CountDownLatch(2);
                int i3 = this.videoOrientation;
                int i4 = (i3 == 0 || i3 == 180) ? i : i2;
                if (i3 == 0 || i3 == 180) {
                    i = i2;
                }
                if (i4 > 1920 || i > 1920) {
                    int iCeil = (int) Math.ceil(Math.max(i4 / 1920.0f, i / 1920.0f));
                    i4 /= iCeil;
                    i /= iCeil;
                }
                new MediaVideoEncoder(this.mMuxer, this.mMediaEncoderListener, (i4 << 1) >> 1, (i << 1) >> 1);
                if (isHasAudio(str)) {
                    new MediaAudioFileEncoder(this.mMuxer, this.mMediaEncoderListener, str);
                } else {
                    this.mCountDownLatch = new CountDownLatch(1);
                }
                this.mMuxer.prepare();
                this.mMuxer.startRecording();
            } finally {
                mediaMetadataRetriever.release();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void stopRecording() {
        this.isStopRecording = true;
        if (this.mMuxer != null) {
            synchronized (this.mRecordLock) {
                this.mVideoEncoder = null;
            }
            this.mMuxer.stopRecording();
            this.mMuxer = null;
        }
    }

    public void frameAvailableSoon(int i, float[] fArr, float[] fArr2) {
        synchronized (this.mRecordLock) {
            if (this.mVideoEncoder != null) {
                if (this.frameAvailableTime.longValue() == 0) {
                    this.frameAvailableTime = Long.valueOf(System.currentTimeMillis());
                }
                float[] fArrCopyArray = DecimalUtils.copyArray(fArr2);
                int i2 = this.videoOrientation;
                if (i2 == 90) {
                    Matrix.rotateM(fArrCopyArray, 0, 270.0f, 0.0f, 0.0f, 1.0f);
                } else if (i2 == 180) {
                    Matrix.rotateM(fArrCopyArray, 0, 180.0f, 0.0f, 0.0f, 1.0f);
                } else if (i2 == 270) {
                    Matrix.rotateM(fArrCopyArray, 0, 90.0f, 0.0f, 0.0f, 1.0f);
                }
                this.mVideoEncoder.frameAvailableSoon(i, fArr, fArrCopyArray);
                if (!this.isStopRecording) {
                    this.mOnVideoRecordingListener.onProcess(Long.valueOf(System.currentTimeMillis() - this.frameAvailableTime.longValue()));
                }
            }
        }
    }

    class C09941 implements MediaEncoder.MediaEncoderListener {
        C09941() {
        }

        @Override
        public void onPrepared(final MediaEncoder mediaEncoder) {
            Log.v(VideoRecordHelper.TAG, "onPrepared:encoder=" + mediaEncoder);
            if (mediaEncoder instanceof MediaVideoEncoder) {
                VideoRecordHelper.this.mGLSurfaceView.queueEvent(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m283x3514bdfe(mediaEncoder);
                    }
                });
            }
        }

        void m283x3514bdfe(MediaEncoder mediaEncoder) {
            ((MediaVideoEncoder) mediaEncoder).setEglContext(EGL14.eglGetCurrentContext());
            synchronized (VideoRecordHelper.this.mRecordLock) {
                VideoRecordHelper.this.mVideoEncoder = (MediaVideoEncoder) mediaEncoder;
            }
            VideoRecordHelper.this.mOnVideoRecordingListener.onPrepared();
        }

        @Override
        public void onStopped(MediaEncoder mediaEncoder) {
            Log.v(VideoRecordHelper.TAG, "onStopped:encoder=" + mediaEncoder);
            VideoRecordHelper.this.mCountDownLatch.countDown();
            Log.e(VideoRecordHelper.TAG, "onStopped  mCountDownLatch" + VideoRecordHelper.this.mCountDownLatch.getCount());
            if (mediaEncoder instanceof MediaVideoEncoder) {
                GLSurfaceView gLSurfaceView = VideoRecordHelper.this.mGLSurfaceView;
                final MediaVideoEncoder mediaVideoEncoder = (MediaVideoEncoder) mediaEncoder;
                Objects.requireNonNull(mediaVideoEncoder);
                gLSurfaceView.queueEvent(new Runnable() {
                    @Override
                    public final void run() {
                        mediaVideoEncoder.releaseGL();
                    }
                });
            }
            if (VideoRecordHelper.this.mCountDownLatch.getCount() == 0) {
                VideoRecordHelper.this.mCountDownLatch = null;
                Log.v(VideoRecordHelper.TAG, "onStopped  mOutputFile:" + VideoRecordHelper.this.mOutputFile.getAbsolutePath());
                VideoRecordHelper.this.mOnVideoRecordingListener.onFinish(VideoRecordHelper.this.mOutputFile);
                VideoRecordHelper.this.isRecording = false;
            }
        }
    }

    private boolean isHasAudio(String str) {
        MediaExtractor mediaExtractor = new MediaExtractor();
        try {
            mediaExtractor.setDataSource(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith("audio/")) {
                return true;
            }
        }
        return false;
    }
}
