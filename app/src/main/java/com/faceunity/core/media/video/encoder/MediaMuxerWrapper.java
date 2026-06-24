package com.faceunity.core.media.video.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MediaMuxerWrapper {
    private static final boolean DEBUG = false;
    private MediaEncoder mAudioEncoder;
    private MediaEncoder mAudioFileEncoder;
    private final MediaMuxer mMediaMuxer;
    private MediaEncoder mVideoEncoder;
    private final String TAG = "Video_MediaMuxerWrapper";
    private volatile boolean mHasStopEncoder = false;
    private int mStatredCount = 0;
    private int mEncoderCount = 0;
    private boolean mIsStarted = false;

    public MediaMuxerWrapper(String str) throws IOException {
        this.mMediaMuxer = new MediaMuxer(str, 0);
    }

    public void prepare() throws IOException {
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.prepare();
        }
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.prepare();
        }
        MediaEncoder mediaEncoder3 = this.mAudioFileEncoder;
        if (mediaEncoder3 != null) {
            mediaEncoder3.prepare();
        }
    }

    public void startRecording() {
        this.mHasStopEncoder = false;
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.startRecording();
        }
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.startRecording();
        }
        MediaEncoder mediaEncoder3 = this.mAudioFileEncoder;
        if (mediaEncoder3 != null) {
            mediaEncoder3.startRecording();
        }
    }

    public void stopRecording() {
        this.mHasStopEncoder = true;
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.stopRecording();
        }
        this.mVideoEncoder = null;
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.stopRecording();
        }
        this.mAudioEncoder = null;
        MediaEncoder mediaEncoder3 = this.mAudioFileEncoder;
        if (mediaEncoder3 != null) {
            mediaEncoder3.stopRecording();
        }
        this.mAudioFileEncoder = null;
    }

    public synchronized boolean isStarted() {
        return this.mIsStarted;
    }

    public synchronized boolean hasStopEncoder() {
        return this.mHasStopEncoder;
    }

    void addEncoder(MediaEncoder mediaEncoder) {
        if (mediaEncoder instanceof MediaVideoEncoder) {
            if (this.mVideoEncoder != null) {
                throw new IllegalArgumentException("Video encoder already added.");
            }
            this.mVideoEncoder = mediaEncoder;
        } else if (mediaEncoder instanceof MediaAudioEncoder) {
            if (this.mAudioEncoder != null) {
                throw new IllegalArgumentException("Video encoder already added.");
            }
            this.mAudioEncoder = mediaEncoder;
        } else if (mediaEncoder instanceof MediaAudioFileEncoder) {
            if (this.mAudioFileEncoder != null) {
                throw new IllegalArgumentException("Video encoder already added.");
            }
            this.mAudioFileEncoder = mediaEncoder;
        } else {
            throw new IllegalArgumentException("unsupported encoder");
        }
        this.mEncoderCount = (this.mVideoEncoder != null ? 1 : 0) + (this.mAudioEncoder != null ? 1 : 0) + (this.mAudioFileEncoder == null ? 0 : 1);
    }

    synchronized boolean start() {
        int i = this.mStatredCount + 1;
        this.mStatredCount = i;
        int i2 = this.mEncoderCount;
        if (i2 > 0 && i == i2) {
            this.mMediaMuxer.start();
            this.mIsStarted = true;
            notifyAll();
        }
        return this.mIsStarted;
    }

    synchronized void stop() {
        int i = this.mStatredCount - 1;
        this.mStatredCount = i;
        if (this.mEncoderCount > 0 && i <= 0) {
            try {
                this.mMediaMuxer.stop();
                this.mMediaMuxer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.mIsStarted = false;
        }
    }

    synchronized int addTrack(MediaFormat mediaFormat) {
        if (this.mIsStarted) {
            throw new IllegalStateException("muxer already started");
        }
        return this.mMediaMuxer.addTrack(mediaFormat);
    }

    synchronized void writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.mStatredCount > 0) {
            this.mMediaMuxer.writeSampleData(i, byteBuffer, bufferInfo);
        }
    }
}
