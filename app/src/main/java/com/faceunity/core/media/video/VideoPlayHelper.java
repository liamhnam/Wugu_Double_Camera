package com.faceunity.core.media.video;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.EGL14;
import android.opengl.GLSurfaceView;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.faceunity.core.utils.FileUtils;
import com.faceunity.core.utils.MediaFileUtil;
import com.faceunity.core.utils.VideoDecoder;
import java.nio.ByteBuffer;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class VideoPlayHelper {
    private static final String TAG = "VideoPlayHelper";
    private Handler mPlayerHandler;
    private VideoDecoder mVideoDecoder;
    private VideoDecoderListener mVideoDecoderListener;
    private int requestPhotoWidth = 1080;
    private int requestPhotoHeight = VideoRecordHelper.MAX_VIDEO_LENGTH;
    private VideoDecoder.OnReadPixelListener mOnReadPixelListener = new VideoDecoder.OnReadPixelListener() {
        @Override
        public void onReadVideoPixel(int i, int i2, byte[] bArr) {
            if (VideoPlayHelper.this.mVideoDecoderListener != null) {
                VideoPlayHelper.this.mVideoDecoderListener.onReadVideoPixel(bArr, i, i2);
            }
        }

        @Override
        public void onReadImagePixel(int i, int i2, byte[] bArr) {
            if (VideoPlayHelper.this.mVideoDecoderListener != null) {
                VideoPlayHelper.this.mVideoDecoderListener.onReadImagePixel(bArr, i, i2);
            }
        }
    };

    public interface VideoDecoderListener {
        void onReadImagePixel(byte[] bArr, int i, int i2);

        void onReadVideoPixel(byte[] bArr, int i, int i2);
    }

    public VideoPlayHelper(VideoDecoderListener videoDecoderListener, GLSurfaceView gLSurfaceView) {
        this.mVideoDecoderListener = videoDecoderListener;
        startPlayerThread();
        VideoDecoder videoDecoder = new VideoDecoder();
        this.mVideoDecoder = videoDecoder;
        videoDecoder.setOnReadPixelListener(this.mOnReadPixelListener);
        gLSurfaceView.queueEvent(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1546lambda$new$0$comfaceunitycoremediavideoVideoPlayHelper();
            }
        });
    }

    void m1546lambda$new$0$comfaceunitycoremediavideoVideoPlayHelper() {
        this.mVideoDecoder.create(EGL14.eglGetCurrentContext(), true);
    }

    public VideoPlayHelper(VideoDecoderListener videoDecoderListener, GLSurfaceView gLSurfaceView, final boolean z) {
        this.mVideoDecoderListener = videoDecoderListener;
        startPlayerThread();
        VideoDecoder videoDecoder = new VideoDecoder();
        this.mVideoDecoder = videoDecoder;
        videoDecoder.setOnReadPixelListener(this.mOnReadPixelListener);
        gLSurfaceView.queueEvent(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1547lambda$new$1$comfaceunitycoremediavideoVideoPlayHelper(z);
            }
        });
    }

    void m1547lambda$new$1$comfaceunitycoremediavideoVideoPlayHelper(boolean z) {
        this.mVideoDecoder.create(EGL14.eglGetCurrentContext(), z);
    }

    public void playVideo(final Context context, final String str) {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m282x9e66a5e1(str, context);
            }
        });
    }

    void m282x9e66a5e1(String str, Context context) {
        VideoDecoder videoDecoder;
        if (str == null && (videoDecoder = this.mVideoDecoder) != null) {
            videoDecoder.stop();
            return;
        }
        if (!MediaFileUtil.isImageFileType(str)) {
            if (MediaFileUtil.isVideoFileType(str)) {
                this.mVideoDecoder.stop();
                this.mVideoDecoder.start(str);
                return;
            }
            return;
        }
        Bitmap bitmapLoadBitmapFromLocal = context != null ? FileUtils.loadBitmapFromLocal(context, str) : FileUtils.loadBitmapFromExternalUnRotate(str, this.requestPhotoWidth, this.requestPhotoHeight);
        if (bitmapLoadBitmapFromLocal == null) {
            Log.e(TAG, "图片加载异常。");
            return;
        }
        Bitmap bitmapRotateBitmap = rotateBitmap(bitmapLoadBitmapFromLocal, FileUtils.INSTANCE.getPhotoOrientation(str));
        byte[] bArr = new byte[bitmapRotateBitmap.getByteCount()];
        bitmapRotateBitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        bitmapRotateBitmap.recycle();
        VideoDecoderListener videoDecoderListener = this.mVideoDecoderListener;
        if (videoDecoderListener != null) {
            videoDecoderListener.onReadImagePixel(bArr, bitmapRotateBitmap.getWidth(), bitmapRotateBitmap.getHeight());
        }
    }

    public void playVideo(String str) {
        playVideo(null, str);
    }

    public void playAssetsVideo(final Context context, final String str) {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m281x6528c663(str, context);
            }
        });
    }

    void m281x6528c663(String str, Context context) {
        VideoDecoder videoDecoder;
        if (str == null && (videoDecoder = this.mVideoDecoder) != null) {
            videoDecoder.stop();
            return;
        }
        String strCopyAssetsToExternalFilesDir = FileUtils.copyAssetsToExternalFilesDir(context, str, str.substring(str.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR) + 1));
        if (strCopyAssetsToExternalFilesDir == null) {
            return;
        }
        if (!MediaFileUtil.isImageFileType(strCopyAssetsToExternalFilesDir)) {
            if (MediaFileUtil.isVideoFileType(str)) {
                this.mVideoDecoder.stop();
                this.mVideoDecoder.start(strCopyAssetsToExternalFilesDir);
                return;
            }
            return;
        }
        Bitmap bitmapRotateBitmap = rotateBitmap(BitmapFactory.decodeFile(strCopyAssetsToExternalFilesDir), FileUtils.INSTANCE.getPhotoOrientation(strCopyAssetsToExternalFilesDir));
        byte[] bArr = new byte[bitmapRotateBitmap.getByteCount()];
        bitmapRotateBitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        bitmapRotateBitmap.recycle();
        this.mVideoDecoder.stop();
        VideoDecoderListener videoDecoderListener = this.mVideoDecoderListener;
        if (videoDecoderListener != null) {
            videoDecoderListener.onReadVideoPixel(bArr, bitmapRotateBitmap.getWidth(), bitmapRotateBitmap.getHeight());
        }
    }

    public void pausePlay() {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m280x22fdef5c();
            }
        });
    }

    void m280x22fdef5c() {
        this.mVideoDecoder.stop();
    }

    public void release() {
        this.mPlayerHandler.removeCallbacksAndMessages(null);
        this.mVideoDecoderListener = null;
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1548lambda$release$5$comfaceunitycoremediavideoVideoPlayHelper();
            }
        });
        stopPlayerThread();
    }

    void m1548lambda$release$5$comfaceunitycoremediavideoVideoPlayHelper() {
        this.mVideoDecoder.release();
        this.mVideoDecoder = null;
    }

    void m1549lambda$setFlip$6$comfaceunitycoremediavideoVideoPlayHelper(boolean z) {
        this.mVideoDecoder.setFrontCam(z);
    }

    public void setFlip(final boolean z) {
        this.mPlayerHandler.post(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1549lambda$setFlip$6$comfaceunitycoremediavideoVideoPlayHelper(z);
            }
        });
    }

    public Bitmap rotateBitmap(Bitmap bitmap, float f) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        if (bitmapCreateBitmap.equals(bitmap)) {
            return bitmapCreateBitmap;
        }
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    private void startPlayerThread() {
        HandlerThread handlerThread = new HandlerThread("video_decoder");
        handlerThread.start();
        this.mPlayerHandler = new Handler(handlerThread.getLooper());
    }

    private void stopPlayerThread() {
        this.mPlayerHandler.getLooper().quitSafely();
        this.mPlayerHandler = null;
    }

    public void setFilterFrame(int i) {
        this.mVideoDecoder.setFilterFrame(i);
    }
}
