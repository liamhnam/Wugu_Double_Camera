package com.faceunity.core.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.opengl.GLES20;
import android.os.AsyncTask;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.callback.OnReadBitmapCallback;
import com.faceunity.core.program.ProgramTexture2dWithAlpha;
import com.faceunity.core.program.ProgramTextureOES;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u001e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006J\u001a\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011H\u0002J*\u0010\u0016\u001a\u0004\u0018\u00010\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0011J&\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011J\u001e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rJ*\u0010!\u001a\u0004\u0018\u00010\u00062\u0006\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ>\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u000fJ \u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u001dJ \u00100\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010/\u001a\u00020\u001dJ\u0016\u00101\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u00102\u001a\u00020\u0011¨\u00063"}, m1293d2 = {"Lcom/faceunity/core/utils/BitmapUtils;", "", "()V", "NV21ToYUV", "", "nv21Buffer", "", "yBuffer", "uBuffer", "vBuffer", "YUVTOVN21", "bitmap2RGBA", "bitmap", "Landroid/graphics/Bitmap;", "needRecycle", "", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "decodeSampledBitmapFromResource", "resource", "Landroid/content/res/Resources;", "resId", "encodeYUV420SP", "yuv420sp", "argb", "", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "getIntRGBA", "getNV21", "inputWidth", "inputHeight", "glReadBitmap", "texId", "texMatrix", "", "mvpMatrix", "texWidth", "texHeight", "callback", "Lcom/faceunity/core/callback/OnReadBitmapCallback;", "isOes", "intRGBA2ByteNV21", "intRGBA", "intRGBA2ByteRGBA", "rotateBitmap", "orientation", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BitmapUtils {
    public static final BitmapUtils INSTANCE = new BitmapUtils();

    private BitmapUtils() {
    }

    public final void glReadBitmap(int texId, float[] texMatrix, float[] mvpMatrix, final int texWidth, final int texHeight, final OnReadBitmapCallback callback, boolean isOes) {
        Intrinsics.checkParameterIsNotNull(texMatrix, "texMatrix");
        Intrinsics.checkParameterIsNotNull(mvpMatrix, "mvpMatrix");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        GLES20.glBindTexture(3553, iArr[0]);
        GLES20.glActiveTexture(33984);
        GLES20.glTexImage2D(3553, 0, 6408, texWidth, texHeight, 0, 6408, 5121, null);
        int[] iArr2 = new int[1];
        GLES20.glGenFramebuffers(1, iArr2, 0);
        GLES20.glBindFramebuffer(36160, iArr2[0]);
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, iArr[0], 0);
        int[] iArr3 = new int[4];
        GLES20.glGetIntegerv(2978, iArr3, 0);
        GLES20.glViewport(0, 0, texWidth, texHeight);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
        if (isOes) {
            new ProgramTextureOES().drawFrame(texId, texMatrix, mvpMatrix);
        } else {
            new ProgramTexture2dWithAlpha().drawFrame(texId, texMatrix, mvpMatrix);
        }
        final ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(texWidth * texHeight * 4);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        GLES20.glFinish();
        GLES20.glReadPixels(0, 0, texWidth, texHeight, 6408, 5121, byteBufferAllocateDirect);
        GlUtil.checkGlError("glReadPixels");
        byteBufferAllocateDirect.rewind();
        GLES20.glViewport(iArr3[0], iArr3[1], iArr3[2], iArr3[3]);
        GLES20.glBindTexture(3553, 0);
        GLES20.glBindFramebuffer(36160, 0);
        GLES20.glDeleteTextures(1, iArr, 0);
        GLES20.glDeleteFramebuffers(1, iArr2, 0);
        AsyncTask.execute(new Runnable() {
            @Override
            public final void run() {
                Bitmap bmp = Bitmap.createBitmap(texWidth, texHeight, Bitmap.Config.ARGB_8888);
                bmp.copyPixelsFromBuffer(byteBufferAllocateDirect);
                Matrix matrix = new Matrix();
                matrix.preScale(1.0f, -1.0f);
                Intrinsics.checkExpressionValueIsNotNull(bmp, "bmp");
                Bitmap finalBmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, false);
                bmp.recycle();
                OnReadBitmapCallback onReadBitmapCallback = callback;
                Intrinsics.checkExpressionValueIsNotNull(finalBmp, "finalBmp");
                onReadBitmapCallback.onReadBitmap(finalBmp);
            }
        });
    }

    public final Bitmap rotateBitmap(Bitmap bitmap, int orientation) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        if (orientation != 90 && orientation != 180 && orientation != 270) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        Intrinsics.checkExpressionValueIsNotNull(bitmapCreateBitmap, "Bitmap.createBitmap(bitm…map.height, matrix, true)");
        return bitmapCreateBitmap;
    }

    public static byte[] getNV21$default(BitmapUtils bitmapUtils, int i, int i2, Bitmap bitmap, boolean z, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            z = true;
        }
        return bitmapUtils.getNV21(i, i2, bitmap, z);
    }

    public final byte[] getNV21(int inputWidth, int inputHeight, Bitmap bitmap, boolean needRecycle) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int i = inputWidth * inputHeight;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, inputWidth, 0, 0, inputWidth, inputHeight);
        double d = 2;
        byte[] bArr = new byte[i + (((int) Math.ceil(((double) inputHeight) / d)) * 2 * ((int) Math.ceil(((double) inputWidth) / d)))];
        encodeYUV420SP(bArr, iArr, inputWidth, inputHeight);
        if (needRecycle) {
            bitmap.recycle();
        }
        return bArr;
    }

    public static byte[] bitmap2RGBA$default(BitmapUtils bitmapUtils, Bitmap bitmap, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return bitmapUtils.bitmap2RGBA(bitmap, z);
    }

    public final byte[] bitmap2RGBA(Bitmap bitmap, boolean needRecycle) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        byte[] bArr = new byte[i * 4];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int i4 = i2 * 4;
            bArr[i4] = (byte) ((i3 >> 16) & 255);
            bArr[i4 + 1] = (byte) ((i3 >> 8) & 255);
            bArr[i4 + 2] = (byte) (i3 & 255);
            bArr[i4 + 3] = (byte) 255;
        }
        if (needRecycle) {
            bitmap.recycle();
        }
        return bArr;
    }

    public final int[] getIntRGBA(int width, int height, Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "bitmap");
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        return iArr;
    }

    public final byte[] intRGBA2ByteRGBA(int width, int height, int[] intRGBA) {
        Intrinsics.checkParameterIsNotNull(intRGBA, "intRGBA");
        byte[] bArr = new byte[width * height * 4];
        int length = intRGBA.length;
        for (int i = 0; i < length; i++) {
            int i2 = intRGBA[i];
            int i3 = i * 4;
            bArr[i3] = (byte) ((i2 >> 16) & 255);
            bArr[i3 + 1] = (byte) ((i2 >> 8) & 255);
            bArr[i3 + 2] = (byte) (i2 & 255);
            bArr[i3 + 3] = (byte) 255;
        }
        return bArr;
    }

    public final byte[] intRGBA2ByteNV21(int width, int height, int[] intRGBA) {
        Intrinsics.checkParameterIsNotNull(intRGBA, "intRGBA");
        double d = 2;
        byte[] bArr = new byte[(height * width) + (((int) Math.ceil(((double) height) / d)) * 2 * ((int) Math.ceil(((double) width) / d)))];
        encodeYUV420SP(bArr, intRGBA, width, height);
        return bArr;
    }

    public final byte[] YUVTOVN21(byte[] yBuffer, byte[] uBuffer, byte[] vBuffer) {
        Intrinsics.checkParameterIsNotNull(yBuffer, "yBuffer");
        Intrinsics.checkParameterIsNotNull(uBuffer, "uBuffer");
        Intrinsics.checkParameterIsNotNull(vBuffer, "vBuffer");
        int length = yBuffer.length;
        int length2 = uBuffer.length;
        int length3 = vBuffer.length;
        byte[] bArr = new byte[length + length2 + length3];
        System.arraycopy(yBuffer, 0, bArr, 0, length);
        for (int i = 0; i < length3; i++) {
            bArr[(i * 2) + length] = vBuffer[i];
        }
        for (int i2 = 0; i2 < length2; i2++) {
            bArr[(i2 * 2) + length + 1] = uBuffer[i2];
        }
        return bArr;
    }

    public final void NV21ToYUV(byte[] nv21Buffer, byte[] yBuffer, byte[] uBuffer, byte[] vBuffer) {
        Intrinsics.checkParameterIsNotNull(nv21Buffer, "nv21Buffer");
        Intrinsics.checkParameterIsNotNull(yBuffer, "yBuffer");
        Intrinsics.checkParameterIsNotNull(uBuffer, "uBuffer");
        Intrinsics.checkParameterIsNotNull(vBuffer, "vBuffer");
        System.arraycopy(nv21Buffer, 0, yBuffer, 0, yBuffer.length);
        int length = vBuffer.length;
        for (int i = 0; i < length; i++) {
            vBuffer[i] = nv21Buffer[yBuffer.length + (i * 2)];
        }
        int length2 = uBuffer.length;
        for (int i2 = 0; i2 < length2; i2++) {
            uBuffer[i2] = nv21Buffer[yBuffer.length + (i2 * 2) + 1];
        }
    }

    public final void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        Intrinsics.checkParameterIsNotNull(yuv420sp, "yuv420sp");
        Intrinsics.checkParameterIsNotNull(argb, "argb");
        int i = width * height;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            int i5 = 0;
            while (i5 < width) {
                int i6 = argb[i3];
                int i7 = (16711680 & i6) >> 16;
                int i8 = (65280 & i6) >> 8;
                int i9 = 255;
                int i10 = (i6 & 255) >> 0;
                int i11 = (((((i7 * 66) + (i8 * 129)) + (i10 * 25)) + 128) >> 8) + 16;
                int i12 = (((((i7 * (-38)) - (i8 * 74)) + (i10 * UiPosIndexEnum.HOME_COUNTDOWN)) + 128) >> 8) + 128;
                int i13 = (((((i7 * UiPosIndexEnum.HOME_COUNTDOWN) - (i8 * 94)) - (i10 * 18)) + 128) >> 8) + 128;
                int i14 = i2 + 1;
                if (i11 < 0) {
                    i11 = 0;
                } else if (i11 > 255) {
                    i11 = 255;
                }
                yuv420sp[i2] = (byte) i11;
                if (i4 % 2 == 0 && i3 % 2 == 0) {
                    int i15 = i + 1;
                    if (i13 < 0) {
                        i13 = 0;
                    } else if (i13 > 255) {
                        i13 = 255;
                    }
                    yuv420sp[i] = (byte) i13;
                    i = i15 + 1;
                    if (i12 < 0) {
                        i9 = 0;
                    } else if (i12 <= 255) {
                        i9 = i12;
                    }
                    yuv420sp[i15] = (byte) i9;
                }
                i3++;
                i5++;
                i2 = i14;
            }
        }
    }

    public final Bitmap decodeSampledBitmapFromResource(Resources resource, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resource, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resource, resId, options);
    }

    private final int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int i = options.outHeight;
        int i2 = options.outWidth;
        int i3 = 1;
        if (i > reqHeight || i2 > reqWidth) {
            int i4 = i / 2;
            int i5 = i2 / 2;
            while (i4 / i3 >= reqHeight && i5 / i3 >= reqWidth) {
                i3 *= 2;
            }
        }
        return i3;
    }
}
