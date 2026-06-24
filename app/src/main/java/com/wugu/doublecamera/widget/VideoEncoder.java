package com.wugu.doublecamera.widget;

import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Looper;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.util.MimeTypes;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IVideoProvider;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class VideoEncoder {
    private IVideoProvider IProvider;
    private int colorFormat;
    private final long defaultTimeOutUs = 10000;
    private boolean isRunning;
    private int mFrameRate;
    private boolean mMuxerStarted;
    private int mTrackIndex;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;
    private File out;

    public VideoEncoder(IVideoProvider iVideoProvider, File file, int i) {
        this.IProvider = iVideoProvider;
        this.out = file;
        this.mFrameRate = i;
    }

    private int[] getMediaCodecList() {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        int i = 0;
        while (i < codecCount && mediaCodecInfo == null) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                boolean z = false;
                for (int i2 = 0; i2 < supportedTypes.length && !z; i2++) {
                    if (supportedTypes[i2].equals(MimeTypes.VIDEO_H264)) {
                        z = true;
                    }
                }
                if (z) {
                    i++;
                    mediaCodecInfo = codecInfoAt;
                }
            }
            i++;
        }
        return mediaCodecInfo.getCapabilitiesForType(MimeTypes.VIDEO_H264).colorFormats;
    }

    private void init(int i, int i2) {
        int[] mediaCodecList = getMediaCodecList();
        if (mediaCodecList.length > 0) {
            int i3 = mediaCodecList[0];
            if (i3 != 39) {
                switch (i3) {
                    case 19:
                        this.colorFormat = i3;
                        break;
                    case 20:
                        this.colorFormat = i3;
                        break;
                    case 21:
                        this.colorFormat = i3;
                        break;
                }
            } else {
                this.colorFormat = i3;
            }
        }
        if (this.colorFormat <= 0) {
            this.colorFormat = 21;
        }
        if (i % 2 != 0) {
            i--;
        }
        if (i2 % 2 != 0) {
            i2--;
        }
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(MimeTypes.VIDEO_MP4V, i, i2);
        mediaFormatCreateVideoFormat.setInteger("color-format", this.colorFormat);
        mediaFormatCreateVideoFormat.setInteger("bitrate", i * i2);
        mediaFormatCreateVideoFormat.setInteger("frame-rate", this.mFrameRate);
        mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 10);
        try {
            this.mediaCodec = MediaCodec.createEncoderByType(MimeTypes.VIDEO_MP4V);
            if (!this.out.exists()) {
                this.out.createNewFile();
            }
            this.mediaMuxer = new MediaMuxer(this.out.getAbsolutePath(), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mediaCodec.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, 1);
        this.mediaCodec.start();
        this.isRunning = true;
    }

    public void start() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            new Thread((Runnable) this, "VideoEncoder").start();
            return;
        }
        try {
            if (this.IProvider.size() > 0) {
                Bitmap next = this.IProvider.next();
                init(next.getWidth(), next.getHeight());
                run(next);
            }
        } finally {
            finish();
        }
    }

    private void finish() {
        this.isRunning = false;
        MediaCodec mediaCodec = this.mediaCodec;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.mediaCodec.release();
            this.mediaCodec = null;
        }
        MediaMuxer mediaMuxer = this.mediaMuxer;
        try {
            if (mediaMuxer != null) {
                try {
                    if (this.mMuxerStarted) {
                        mediaMuxer.stop();
                        this.mediaMuxer.release();
                    }
                } catch (Exception e) {
                    this.IProvider.progress(-1.0f);
                    e.printStackTrace();
                }
            }
        } finally {
            this.mediaMuxer = null;
        }
    }

    private void run(Bitmap bitmap) {
        this.isRunning = true;
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        long j = 0;
        while (this.isRunning) {
            int iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(10000L);
            if (iDequeueInputBuffer >= 0) {
                long jComputePresentationTime = computePresentationTime(j);
                this.IProvider.progress(j / r4.size());
                if (j >= this.IProvider.size()) {
                    this.mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, 0, jComputePresentationTime, 4);
                    this.isRunning = false;
                    drainEncoder(true, bufferInfo);
                } else {
                    if (bitmap == null) {
                        bitmap = this.IProvider.next();
                    }
                    int width = bitmap.getWidth();
                    int height = bitmap.getHeight();
                    if (width % 2 != 0) {
                        width--;
                    }
                    if (height % 2 != 0) {
                        height--;
                    }
                    byte[] nv12 = getNV12(width, height, bitmap);
                    bitmap.recycle();
                    ByteBuffer inputBuffer = this.mediaCodec.getInputBuffer(iDequeueInputBuffer);
                    inputBuffer.clear();
                    inputBuffer.put(nv12);
                    this.mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, nv12.length, jComputePresentationTime, 0);
                    drainEncoder(false, bufferInfo);
                    bitmap = null;
                }
                j++;
            } else {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException unused) {
                    this.IProvider.progress(-1.0f);
                }
            }
        }
    }

    private long computePresentationTime(long j) {
        return ((j * 1000000) / ((long) this.mFrameRate)) + 132;
    }

    private void drainEncoder(boolean z, MediaCodec.BufferInfo bufferInfo) {
        if (z) {
            try {
                this.mediaCodec.signalEndOfInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (true) {
            int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 10000L);
            if (iDequeueOutputBuffer == -1) {
                if (!z) {
                    return;
                }
            } else if (iDequeueOutputBuffer == -2) {
                if (this.mMuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                this.mTrackIndex = this.mediaMuxer.addTrack(this.mediaCodec.getOutputFormat());
                this.mediaMuxer.start();
                this.mMuxerStarted = true;
            } else if (iDequeueOutputBuffer < 0) {
                Log.d("YapVideoEncoder", "unexpected result from encoder.dequeueOutputBuffer: " + iDequeueOutputBuffer);
            } else {
                ByteBuffer outputBuffer = this.mediaCodec.getOutputBuffer(iDequeueOutputBuffer);
                if (outputBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + iDequeueOutputBuffer + " was null");
                }
                if ((bufferInfo.flags & 2) != 0) {
                    bufferInfo.size = 0;
                }
                if (bufferInfo.size != 0) {
                    if (!this.mMuxerStarted) {
                        Log.d("YapVideoEncoder", "error:muxer hasn't started");
                    }
                    outputBuffer.position(bufferInfo.offset);
                    outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                    try {
                        this.mediaMuxer.writeSampleData(this.mTrackIndex, outputBuffer, bufferInfo);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                this.mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                if ((bufferInfo.flags & 4) != 0) {
                    if (!z) {
                        Log.d("YapVideoEncoder", "reached end of stream unexpectedly");
                        this.IProvider.progress(-1.0f);
                        return;
                    } else {
                        Log.d("YapVideoEncoder", "end of stream reached");
                        return;
                    }
                }
            }
        }
    }

    private byte[] getNV12(int i, int i2, Bitmap bitmap) {
        int i3 = i * i2;
        int[] iArr = new int[i3];
        bitmap.getPixels(iArr, 0, i, 0, 0, i, i2);
        byte[] bArr = new byte[(i3 * 3) / 2];
        int i4 = this.colorFormat;
        if (i4 != 39) {
            switch (i4) {
                case 19:
                    encodeYUV420P(bArr, iArr, i, i2);
                    break;
                case 20:
                    encodeYUV420PP(bArr, iArr, i, i2);
                    break;
                case 21:
                    encodeYUV420SP(bArr, iArr, i, i2);
                    break;
            }
        } else {
            encodeYUV420PSP(bArr, iArr, i, i2);
        }
        return bArr;
    }

    private void encodeYUV420SP(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = 0;
            while (i7 < i) {
                int i8 = iArr[i5];
                int i9 = 255;
                int i10 = (i8 >> 16) & 255;
                int i11 = (i8 >> 8) & 255;
                int i12 = (i8 >> 0) & 255;
                int i13 = ((((i10 * 66) + (i11 * 129)) + (i12 * 25)) + 16) >> 8;
                int i14 = ((((i10 * UiPosIndexEnum.HOME_COUNTDOWN) - (i11 * 94)) - (i12 * 18)) + 128) >> 8;
                int i15 = ((((i10 * (-38)) - (i11 * 74)) + (i12 * UiPosIndexEnum.HOME_COUNTDOWN)) + 128) >> 8;
                int i16 = i4 + 1;
                if (i13 < 0) {
                    i13 = 0;
                } else if (i13 > 255) {
                    i13 = 255;
                }
                bArr[i4] = (byte) i13;
                if (i6 % 2 == 0 && i5 % 2 == 0) {
                    int i17 = i3 + 1;
                    if (i15 < 0) {
                        i15 = 0;
                    } else if (i15 > 255) {
                        i15 = 255;
                    }
                    bArr[i3] = (byte) i15;
                    i3 = i17 + 1;
                    if (i14 < 0) {
                        i9 = 0;
                    } else if (i14 <= 255) {
                        i9 = i14;
                    }
                    bArr[i17] = (byte) i9;
                }
                i5++;
                i7++;
                i4 = i16;
            }
        }
    }

    private void encodeYUV420P(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = (i3 / 4) + i3;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < i2; i7++) {
            int i8 = 0;
            while (i8 < i) {
                int i9 = iArr[i6];
                int i10 = 255;
                int i11 = (i9 >> 16) & 255;
                int i12 = (i9 >> 8) & 255;
                int i13 = (i9 >> 0) & 255;
                int i14 = ((((i11 * 66) + (i12 * 129)) + (i13 * 25)) + 16) >> 8;
                int i15 = ((((i11 * UiPosIndexEnum.HOME_COUNTDOWN) - (i12 * 94)) - (i13 * 18)) + 128) >> 8;
                int i16 = ((((i11 * (-38)) - (i12 * 74)) + (i13 * UiPosIndexEnum.HOME_COUNTDOWN)) + 128) >> 8;
                int i17 = i5 + 1;
                if (i14 < 0) {
                    i14 = 0;
                } else if (i14 > 255) {
                    i14 = 255;
                }
                bArr[i5] = (byte) i14;
                if (i7 % 2 == 0 && i6 % 2 == 0) {
                    int i18 = i4 + 1;
                    if (i16 < 0) {
                        i16 = 0;
                    } else if (i16 > 255) {
                        i16 = 255;
                    }
                    bArr[i4] = (byte) i16;
                    int i19 = i3 + 1;
                    if (i15 < 0) {
                        i10 = 0;
                    } else if (i15 <= 255) {
                        i10 = i15;
                    }
                    bArr[i3] = (byte) i10;
                    i3 = i19;
                    i4 = i18;
                }
                i6++;
                i8++;
                i5 = i17;
            }
        }
    }

    private void encodeYUV420PSP(byte[] bArr, int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = iArr[i4];
                int i8 = 255;
                int i9 = (i7 >> 16) & 255;
                int i10 = (i7 >> 8) & 255;
                int i11 = (i7 >> 0) & 255;
                int i12 = ((((i9 * 66) + (i10 * 129)) + (i11 * 25)) + 16) >> 8;
                int i13 = ((((i9 * UiPosIndexEnum.HOME_COUNTDOWN) - (i10 * 94)) - (i11 * 18)) + 128) >> 8;
                int i14 = ((((i9 * (-38)) - (i10 * 74)) + (i11 * UiPosIndexEnum.HOME_COUNTDOWN)) + 128) >> 8;
                int i15 = i3 + 1;
                if (i12 < 0) {
                    i12 = 0;
                } else if (i12 > 255) {
                    i12 = 255;
                }
                bArr[i3] = (byte) i12;
                if (i5 % 2 == 0 && i4 % 2 == 0) {
                    int i16 = i15 + 1;
                    if (i14 < 0) {
                        i14 = 0;
                    } else if (i14 > 255) {
                        i14 = 255;
                    }
                    bArr[i16] = (byte) i14;
                    int i17 = i15 + 3;
                    if (i13 < 0) {
                        i8 = 0;
                    } else if (i13 <= 255) {
                        i8 = i13;
                    }
                    bArr[i17] = (byte) i8;
                }
                if (i4 % 2 == 0) {
                    i15++;
                }
                i3 = i15;
                i4++;
            }
        }
    }

    private void encodeYUV420PP(byte[] bArr, int[] iArr, int i, int i2) {
        int length = bArr.length / 2;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            for (int i6 = 0; i6 < i; i6++) {
                int i7 = iArr[i4];
                int i8 = 255;
                int i9 = (i7 >> 16) & 255;
                int i10 = (i7 >> 8) & 255;
                int i11 = (i7 >> 0) & 255;
                int i12 = ((((i9 * 66) + (i10 * 129)) + (i11 * 25)) + 16) >> 8;
                int i13 = ((((i9 * UiPosIndexEnum.HOME_COUNTDOWN) - (i10 * 94)) - (i11 * 18)) + 128) >> 8;
                int i14 = ((((i9 * (-38)) - (i10 * 74)) + (i11 * UiPosIndexEnum.HOME_COUNTDOWN)) + 128) >> 8;
                int i15 = i5 % 2;
                if (i15 == 0 && i4 % 2 == 0) {
                    int i16 = i3 + 1;
                    if (i12 < 0) {
                        i12 = 0;
                    } else if (i12 > 255) {
                        i12 = 255;
                    }
                    bArr[i3] = (byte) i12;
                    int i17 = i16 + 1;
                    if (i14 < 0) {
                        i14 = 0;
                    } else if (i14 > 255) {
                        i14 = 255;
                    }
                    bArr[i17] = (byte) i14;
                    int i18 = length + 1;
                    if (i13 < 0) {
                        i8 = 0;
                    } else if (i13 <= 255) {
                        i8 = i13;
                    }
                    bArr[i18] = (byte) i8;
                    i3 = i17;
                } else if (i15 == 0 && i4 % 2 == 1) {
                    int i19 = i3 + 1;
                    if (i12 < 0) {
                        i8 = 0;
                    } else if (i12 <= 255) {
                        i8 = i12;
                    }
                    bArr[i3] = (byte) i8;
                    i3 = i19;
                } else if (i15 == 1 && i4 % 2 == 0) {
                    int i20 = length + 1;
                    if (i12 < 0) {
                        i8 = 0;
                    } else if (i12 <= 255) {
                        i8 = i12;
                    }
                    bArr[length] = (byte) i8;
                    length = i20 + 1;
                } else if (i15 == 1 && i4 % 2 == 1) {
                    int i21 = length + 1;
                    if (i12 < 0) {
                        i8 = 0;
                    } else if (i12 <= 255) {
                        i8 = i12;
                    }
                    bArr[length] = (byte) i8;
                    length = i21;
                }
                i4++;
            }
        }
    }
}
