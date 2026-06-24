package com.faceunity.core.media.video.encoder;

import android.media.MediaCodec;
import android.util.Log;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

public abstract class MediaEncoder implements Runnable {
    private static final boolean DEBUG = false;
    protected static final int MSG_FRAME_AVAILABLE = 1;
    protected static final int MSG_STOP_RECORDING = 9;
    protected static final int TIMEOUT_USEC = 10000;
    protected String TAG = "Video_MediaEncoder";
    protected MediaCodec.BufferInfo mBufferInfo;
    protected volatile boolean mIsCapturing;
    protected boolean mIsEOS;
    protected final MediaEncoderListener mListener;
    protected MediaCodec mMediaCodec;
    protected boolean mMuxerStarted;
    private int mRequestDrain;
    protected volatile boolean mRequestStop;
    protected final Object mSync;
    protected int mTrackIndex;
    protected final WeakReference<MediaMuxerWrapper> mWeakMuxer;
    protected long prevOutputPTSUs;

    public interface MediaEncoderListener {
        void onPrepared(MediaEncoder mediaEncoder);

        void onStopped(MediaEncoder mediaEncoder);
    }

    abstract void prepare() throws IOException;

    public MediaEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoderListener mediaEncoderListener) {
        Object obj = new Object();
        this.mSync = obj;
        this.prevOutputPTSUs = 0L;
        if (mediaEncoderListener == null) {
            throw new NullPointerException("MediaEncoderListener is null");
        }
        if (mediaMuxerWrapper == null) {
            throw new NullPointerException("MediaMuxerWrapper is null");
        }
        this.mWeakMuxer = new WeakReference<>(mediaMuxerWrapper);
        mediaMuxerWrapper.addEncoder(this);
        this.mListener = mediaEncoderListener;
        synchronized (obj) {
            this.mBufferInfo = new MediaCodec.BufferInfo();
            new Thread(this, getClass().getSimpleName()).start();
            try {
                obj.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public boolean frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestDrain++;
                this.mSync.notifyAll();
                return true;
            }
            return false;
        }
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            r1 = 0
            r6.mRequestStop = r1     // Catch: java.lang.Throwable -> L55
            r6.mRequestDrain = r1     // Catch: java.lang.Throwable -> L55
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L55
            r2.notify()     // Catch: java.lang.Throwable -> L55
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L55
        Le:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            boolean r0 = r6.mRequestStop     // Catch: java.lang.Throwable -> L52
            int r3 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L52
            r4 = 1
            if (r3 <= 0) goto L1a
            r5 = r4
            goto L1b
        L1a:
            r5 = r1
        L1b:
            if (r5 == 0) goto L21
            int r3 = r3 + (-1)
            r6.mRequestDrain = r3     // Catch: java.lang.Throwable -> L52
        L21:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L52
            if (r0 == 0) goto L31
            r6.drain()
            r6.signalEndOfInputStream()
            r6.drain()
            r6.release()
            goto L44
        L31:
            if (r5 == 0) goto L37
            r6.drain()
            goto Le
        L37:
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L41 java.lang.InterruptedException -> L43
            r2.wait()     // Catch: java.lang.Throwable -> L41 java.lang.InterruptedException -> L43
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L41
            goto Le
        L41:
            r1 = move-exception
            goto L50
        L43:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L41
        L44:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            r6.mRequestStop = r4     // Catch: java.lang.Throwable -> L4d
            r6.mIsCapturing = r1     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
            return
        L4d:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4d
            throw r0
        L50:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L41
            throw r1
        L52:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L52
            throw r0
        L55:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L55
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.media.video.encoder.MediaEncoder.run():void");
    }

    void startRecording() {
        synchronized (this.mSync) {
            this.mIsCapturing = true;
            this.mRequestStop = false;
            this.mSync.notifyAll();
        }
    }

    void stopRecording() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestStop = true;
                this.mSync.notifyAll();
            }
        }
    }

    protected void release() {
        this.mIsEOS = true;
        this.mIsCapturing = false;
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mMediaCodec.release();
                this.mMediaCodec = null;
            } catch (Exception e) {
                Log.e(this.TAG, "failed releasing MediaCodec", e);
            }
        }
        if (this.mMuxerStarted) {
            WeakReference<MediaMuxerWrapper> weakReference = this.mWeakMuxer;
            MediaMuxerWrapper mediaMuxerWrapper = weakReference != null ? weakReference.get() : null;
            if (mediaMuxerWrapper != null) {
                try {
                    mediaMuxerWrapper.stop();
                } catch (Exception e2) {
                    Log.e(this.TAG, "failed stopping muxer", e2);
                }
            }
        }
        this.mBufferInfo = null;
        try {
            this.mListener.onStopped(this);
        } catch (Exception e3) {
            Log.e(this.TAG, "failed onStopped", e3);
        }
    }

    protected void signalEndOfInputStream() {
        encode(null, 0, getPTSUs());
    }

    protected void encode(ByteBuffer byteBuffer, int i, long j) {
        if (this.mIsCapturing) {
            ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
            while (this.mIsCapturing) {
                int iDequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(10000L);
                if (iDequeueInputBuffer >= 0) {
                    ByteBuffer byteBuffer2 = inputBuffers[iDequeueInputBuffer];
                    byteBuffer2.clear();
                    if (byteBuffer != null) {
                        byteBuffer2.put(byteBuffer);
                    }
                    if (i <= 0) {
                        this.mIsEOS = true;
                        this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, j, 4);
                        return;
                    } else {
                        this.mMediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i, j, 0);
                        return;
                    }
                }
            }
        }
    }

    protected void drain() {
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec == null) {
            return;
        }
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (mediaMuxerWrapper == null) {
            Log.w(this.TAG, "muxer is unexpectedly null");
            return;
        }
        int i = 0;
        while (this.mIsCapturing) {
            try {
            } catch (Exception e) {
                e = e;
            }
            if (this.mIsEOS) {
                this.mIsCapturing = false;
                return;
            }
            int iDequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(this.mBufferInfo, 10000L);
            if (iDequeueOutputBuffer == -1) {
                if (!this.mIsEOS && (i = i + 1) > 5) {
                    return;
                }
            } else if (iDequeueOutputBuffer == -3) {
                outputBuffers = this.mMediaCodec.getOutputBuffers();
            } else if (iDequeueOutputBuffer == -2) {
                if (this.mMuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                this.mTrackIndex = mediaMuxerWrapper.addTrack(this.mMediaCodec.getOutputFormat());
                this.mMuxerStarted = true;
                if (mediaMuxerWrapper.start()) {
                    continue;
                } else {
                    synchronized (mediaMuxerWrapper) {
                        while (!mediaMuxerWrapper.hasStopEncoder() && !mediaMuxerWrapper.isStarted()) {
                            try {
                                mediaMuxerWrapper.wait(100L);
                            } catch (InterruptedException unused) {
                                return;
                            }
                        }
                    }
                }
            } else if (iDequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = outputBuffers[iDequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                }
                if ((this.mBufferInfo.flags & 2) != 0) {
                    this.mBufferInfo.size = 0;
                }
                if (this.mBufferInfo.size != 0) {
                    try {
                        if (!this.mMuxerStarted) {
                            throw new RuntimeException("drain:muxer hasn't started");
                        }
                        this.mBufferInfo.presentationTimeUs = getPTSUs();
                        if (mediaMuxerWrapper.isStarted()) {
                            mediaMuxerWrapper.writeSampleData(this.mTrackIndex, byteBuffer, this.mBufferInfo);
                        }
                        this.prevOutputPTSUs = this.mBufferInfo.presentationTimeUs;
                        i = 0;
                    } catch (Exception e2) {
                        e = e2;
                        i = 0;
                        e.printStackTrace();
                        release();
                    }
                }
                this.mMediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                if ((this.mBufferInfo.flags & 4) != 0) {
                    this.mIsCapturing = false;
                    return;
                }
            }
            e.printStackTrace();
            release();
        }
    }

    protected long getPTSUs() {
        long jNanoTime = System.nanoTime() / 1000;
        long j = this.prevOutputPTSUs;
        return jNanoTime < j ? jNanoTime + (j - jNanoTime) : jNanoTime;
    }
}
