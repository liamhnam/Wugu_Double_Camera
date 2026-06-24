package com.faceunity.core.media.video.encoder;

import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.util.Log;
import com.faceunity.core.media.video.encoder.MediaEncoder;
import java.nio.ByteBuffer;

public class MediaAudioFileEncoder extends MediaEncoder {
    private static final String AUDIO = "audio/";
    private static final boolean DEBUG = false;
    String TAG;
    private String mFilepath;
    private ByteBuffer mInputBuffer;
    private MediaExtractor mMediaExtractor;

    @Override
    protected void drain() {
    }

    @Override
    protected void signalEndOfInputStream() {
    }

    public MediaAudioFileEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener, String str) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        this.TAG = "Video_MediaAudioFileEncoder";
        this.mFilepath = str;
    }

    @Override
    protected void prepare() {
        try {
            this.mTrackIndex = -1;
            int i = 0;
            this.mIsEOS = false;
            this.mMuxerStarted = false;
            MediaExtractor mediaExtractor = new MediaExtractor();
            this.mMediaExtractor = mediaExtractor;
            mediaExtractor.setDataSource(this.mFilepath);
            MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
            int trackCount = this.mMediaExtractor.getTrackCount();
            while (true) {
                if (i >= trackCount) {
                    break;
                }
                MediaFormat trackFormat = this.mMediaExtractor.getTrackFormat(i);
                if (trackFormat.getString("mime").startsWith(AUDIO)) {
                    this.mInputBuffer = ByteBuffer.allocate(trackFormat.getInteger("max-input-size"));
                    this.mMediaExtractor.selectTrack(i);
                    this.mTrackIndex = mediaMuxerWrapper.addTrack(trackFormat);
                    break;
                }
                i++;
            }
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

    @Override
    public void run() {
        synchronized (this.mSync) {
            this.mSync.notify();
        }
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (!mediaMuxerWrapper.start()) {
            synchronized (mediaMuxerWrapper) {
                while (!mediaMuxerWrapper.isStarted()) {
                    try {
                        mediaMuxerWrapper.wait(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        if (this.mTrackIndex < 0) {
            release();
            return;
        }
        this.mMuxerStarted = true;
        long jCurrentTimeMillis = 0;
        boolean z = false;
        while (!this.mRequestStop) {
            int sampleData = this.mMediaExtractor.readSampleData(this.mInputBuffer, 0);
            long sampleTime = this.mMediaExtractor.getSampleTime();
            int sampleFlags = this.mMediaExtractor.getSampleFlags();
            if (this.mMediaExtractor.advance() && sampleData > 0) {
                if (!z) {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    z = true;
                }
                try {
                    long jCurrentTimeMillis2 = (sampleTime / 1000) - (System.currentTimeMillis() - jCurrentTimeMillis);
                    if (jCurrentTimeMillis2 > 0) {
                        Thread.sleep(jCurrentTimeMillis2);
                    }
                } catch (InterruptedException unused2) {
                }
                this.mBufferInfo.set(0, sampleData, getPTSUs(), sampleFlags);
                mediaMuxerWrapper.writeSampleData(this.mTrackIndex, this.mInputBuffer, this.mBufferInfo);
                this.prevOutputPTSUs = this.mBufferInfo.presentationTimeUs;
            } else {
                release();
                return;
            }
        }
        release();
    }

    @Override
    protected void release() {
        super.release();
        MediaExtractor mediaExtractor = this.mMediaExtractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.mMediaExtractor = null;
        }
    }
}
