package com.faceunity.core.media.video.encoder;

import android.media.AudioRecord;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Process;
import android.util.Log;
import android.view.Surface;
import com.faceunity.core.media.video.encoder.MediaEncoder;
import java.nio.ByteBuffer;

public class MediaAudioEncoder extends MediaEncoder {
    private static final int[] AUDIO_SOURCES = {1, 0, 5, 7, 6};
    private static final int BIT_RATE = 64000;
    private static final boolean DEBUG = false;
    public static final int FRAMES_PER_BUFFER = 25;
    private static final String MIME_TYPE = "audio/mp4a-latm";
    public static final int SAMPLES_PER_FRAME = 1024;
    private static final int SAMPLE_RATE = 44100;
    String TAG;
    private AudioThread mAudioThread;

    public MediaAudioEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoder.MediaEncoderListener mediaEncoderListener) {
        super(mediaMuxerWrapper, mediaEncoderListener);
        this.TAG = "Video_MediaAudioEncoder";
        this.mAudioThread = null;
    }

    @Override
    protected void prepare() {
        try {
            this.mTrackIndex = -1;
            this.mIsEOS = false;
            this.mMuxerStarted = false;
            if (selectAudioCodec("audio/mp4a-latm") == null) {
                Log.e(this.TAG, "Unable to find an appropriate codec for audio/mp4a-latm");
                return;
            }
            MediaFormat mediaFormatCreateAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", SAMPLE_RATE, 1);
            mediaFormatCreateAudioFormat.setInteger("aac-profile", 2);
            mediaFormatCreateAudioFormat.setInteger("channel-mask", 16);
            mediaFormatCreateAudioFormat.setInteger("bitrate", BIT_RATE);
            mediaFormatCreateAudioFormat.setInteger("channel-count", 1);
            this.mMediaCodec = MediaCodec.createEncoderByType("audio/mp4a-latm");
            this.mMediaCodec.configure(mediaFormatCreateAudioFormat, (Surface) null, (MediaCrypto) null, 1);
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

    @Override
    protected void startRecording() {
        super.startRecording();
        if (this.mAudioThread == null) {
            AudioThread audioThread = new AudioThread();
            this.mAudioThread = audioThread;
            audioThread.start();
        }
    }

    @Override
    protected void release() {
        this.mAudioThread = null;
        super.release();
    }

    private class AudioThread extends Thread {
        private AudioThread() {
        }

        @Override
        public void run() throws Throwable {
            int i;
            AudioRecord audioRecord;
            Process.setThreadPriority(-19);
            try {
                int minBufferSize = AudioRecord.getMinBufferSize(MediaAudioEncoder.SAMPLE_RATE, 16, 2);
                i = 1;
                int i2 = 25600 < minBufferSize ? ((minBufferSize / 1024) + 1) * 1024 * 2 : 25600;
                audioRecord = null;
                for (int i3 : MediaAudioEncoder.AUDIO_SOURCES) {
                    try {
                        AudioRecord audioRecord2 = new AudioRecord(i3, MediaAudioEncoder.SAMPLE_RATE, 16, 2, i2);
                        if (audioRecord2.getState() != 1) {
                            audioRecord2 = null;
                        }
                        audioRecord = audioRecord2;
                    } catch (Exception unused) {
                        audioRecord = null;
                    }
                    if (audioRecord == null) {
                    }
                }
            } catch (Exception e) {
                e = e;
                i = 0;
            }
            try {
                if (audioRecord != null) {
                    try {
                        if (MediaAudioEncoder.this.mIsCapturing) {
                            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(1024);
                            audioRecord.startRecording();
                            i = 0;
                            while (MediaAudioEncoder.this.mIsCapturing && !MediaAudioEncoder.this.mRequestStop && !MediaAudioEncoder.this.mIsEOS) {
                                try {
                                    try {
                                        byteBufferAllocateDirect.clear();
                                        int i4 = audioRecord.read(byteBufferAllocateDirect, 1024);
                                        if (i4 > 0) {
                                            byteBufferAllocateDirect.position(i4);
                                            byteBufferAllocateDirect.flip();
                                            MediaAudioEncoder mediaAudioEncoder = MediaAudioEncoder.this;
                                            mediaAudioEncoder.encode(byteBufferAllocateDirect, i4, mediaAudioEncoder.getPTSUs());
                                            MediaAudioEncoder.this.frameAvailableSoon();
                                            i++;
                                        }
                                    } catch (Throwable th) {
                                        audioRecord.stop();
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    audioRecord.release();
                                    throw th;
                                }
                            }
                            MediaAudioEncoder.this.frameAvailableSoon();
                            audioRecord.stop();
                        } else {
                            i = 0;
                        }
                        audioRecord.release();
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } else {
                    Log.e(MediaAudioEncoder.this.TAG, "failed to initialize AudioRecord");
                    i = 0;
                }
            } catch (Exception e2) {
                e = e2;
                Log.e(MediaAudioEncoder.this.TAG, "AudioThread#run", e);
            }
            if (i == 0) {
                ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(1024);
                for (int i5 = 0; MediaAudioEncoder.this.mIsCapturing && i5 < 5; i5++) {
                    byteBufferAllocateDirect2.position(1024);
                    byteBufferAllocateDirect2.flip();
                    try {
                        MediaAudioEncoder mediaAudioEncoder2 = MediaAudioEncoder.this;
                        mediaAudioEncoder2.encode(byteBufferAllocateDirect2, 1024, mediaAudioEncoder2.getPTSUs());
                        MediaAudioEncoder.this.frameAvailableSoon();
                        synchronized (this) {
                            try {
                                wait(50L);
                            } catch (InterruptedException unused2) {
                            }
                        }
                    } catch (Exception unused3) {
                        return;
                    }
                }
            }
        }
    }

    private MediaCodecInfo selectAudioCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        return codecInfoAt;
                    }
                }
            }
        }
        return null;
    }
}
