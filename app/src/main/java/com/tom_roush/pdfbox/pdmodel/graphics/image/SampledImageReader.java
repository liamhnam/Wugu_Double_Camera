package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

final class SampledImageReader {
    private SampledImageReader() {
    }

    public static Bitmap getStencilImage(PDImage pDImage, Paint paint) throws IOException {
        Bitmap rGBImage = getRGBImage(pDImage, null);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(rGBImage.getWidth(), rGBImage.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(bitmapCreateBitmap).drawRect(0.0f, 0.0f, rGBImage.getWidth(), rGBImage.getHeight(), paint);
        int width = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        int i = width * height;
        int[] iArr = new int[i];
        bitmapCreateBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        int[] iArr2 = new int[i];
        rGBImage.getPixels(iArr2, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            if (Color.red(iArr2[i2]) == 255) {
                iArr[i2] = 0;
            }
        }
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }

    public static Bitmap getRGBImage(PDImage pDImage, COSArray cOSArray) throws IOException {
        if (pDImage.isEmpty()) {
            throw new IOException("Image stream is empty");
        }
        pDImage.getColorSpace().getNumberOfComponents();
        pDImage.getWidth();
        pDImage.getHeight();
        int bitsPerComponent = pDImage.getBitsPerComponent();
        float[] decodeArray = getDecodeArray(pDImage);
        float[] defaultDecode = pDImage.getColorSpace().getDefaultDecode(8);
        if (pDImage.getSuffix() != null && pDImage.getSuffix().equals("jpg")) {
            return BitmapFactory.decodeStream(pDImage.createInputStream());
        }
        if (bitsPerComponent == 8 && Arrays.equals(decodeArray, defaultDecode) && cOSArray == null) {
            return from8bit(pDImage);
        }
        if (bitsPerComponent == 1 && cOSArray == null) {
            return from1Bit(pDImage);
        }
        Log.e("PdfBox-Android", "Trying to create other-bit image not supported");
        return from8bit(pDImage);
    }

    private static Bitmap from1Bit(PDImage pDImage) throws Throwable {
        InputStream inputStreamCreateInputStream;
        byte b;
        boolean z;
        PDColorSpace colorSpace = pDImage.getColorSpace();
        int width = pDImage.getWidth();
        int height = pDImage.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ALPHA_8);
        float[] decodeArray = getDecodeArray(pDImage);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmapCreateBitmap.getRowBytes() * height);
        bitmapCreateBitmap.copyPixelsToBuffer(byteBufferAllocate);
        byte[] bArrArray = byteBufferAllocate.array();
        try {
            inputStreamCreateInputStream = pDImage.createInputStream();
            try {
                int i = width / 8;
                if (width % 8 > 0) {
                    i++;
                }
                int i2 = 0;
                boolean z2 = true;
                byte b2 = -1;
                if (decodeArray[0] < decodeArray[1]) {
                    b = -1;
                    b2 = 0;
                } else {
                    b = 0;
                }
                byte[] bArr = new byte[i];
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    if (i3 >= height) {
                        break;
                    }
                    int i5 = inputStreamCreateInputStream.read(bArr);
                    int i6 = i2;
                    while (i2 < i && i2 < i5) {
                        byte b3 = bArr[i2];
                        int i7 = 128;
                        int i8 = height;
                        int i9 = 0;
                        while (true) {
                            if (i9 >= 8) {
                                z = true;
                                break;
                            }
                            int i10 = b3 & i7;
                            z = true;
                            i7 >>= 1;
                            int i11 = i4 + 1;
                            bArrArray[i4] = i10 == 0 ? b2 : b;
                            int i12 = i6 + 1;
                            if (i12 == width) {
                                i6 = i12;
                                i4 = i11;
                                break;
                            }
                            i9++;
                            i6 = i12;
                            i4 = i11;
                        }
                        i2++;
                        z2 = z;
                        height = i8;
                    }
                    int i13 = height;
                    boolean z3 = z2;
                    if (i5 != i) {
                        Log.w("PdfBox-Android", "premature EOF, image will be incomplete");
                        break;
                    }
                    i3++;
                    z2 = z3;
                    height = i13;
                    i2 = 0;
                }
                byteBufferAllocate.rewind();
                bitmapCreateBitmap.copyPixelsFromBuffer(byteBufferAllocate);
                Bitmap rGBImage = colorSpace.toRGBImage(bitmapCreateBitmap);
                if (inputStreamCreateInputStream != null) {
                    inputStreamCreateInputStream.close();
                }
                return rGBImage;
            } catch (Throwable th) {
                th = th;
                if (inputStreamCreateInputStream != null) {
                    inputStreamCreateInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStreamCreateInputStream = null;
        }
    }

    private static Bitmap from8bit(PDImage pDImage) throws IOException {
        InputStream inputStreamCreateInputStream = pDImage.createInputStream();
        try {
            int width = pDImage.getWidth();
            int height = pDImage.getHeight();
            int numberOfComponents = pDImage.getColorSpace().getNumberOfComponents();
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            int i = width * height;
            int[] iArr = new int[i];
            bitmapCreateBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            for (int i2 = 0; i2 < i; i2++) {
                if (numberOfComponents == 1) {
                    int i3 = inputStreamCreateInputStream.read();
                    iArr[i2] = Color.argb(255, i3, i3, i3);
                } else {
                    iArr[i2] = Color.argb(255, inputStreamCreateInputStream.read(), inputStreamCreateInputStream.read(), inputStreamCreateInputStream.read());
                }
            }
            bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return bitmapCreateBitmap;
        } finally {
            IOUtils.closeQuietly(inputStreamCreateInputStream);
        }
    }

    private static float[] getDecodeArray(PDImage pDImage) throws IOException {
        float[] floatArray;
        COSArray decode = pDImage.getDecode();
        if (decode == null) {
            floatArray = null;
        } else if (decode.size() != pDImage.getColorSpace().getNumberOfComponents() * 2) {
            if (pDImage.isStencil() && decode.size() >= 2 && (decode.get(0) instanceof COSNumber) && (decode.get(1) instanceof COSNumber)) {
                float fFloatValue = ((COSNumber) decode.get(0)).floatValue();
                float fFloatValue2 = ((COSNumber) decode.get(1)).floatValue();
                if (fFloatValue >= 0.0f && fFloatValue <= 1.0f && fFloatValue2 >= 0.0f && fFloatValue2 <= 1.0f) {
                    Log.w("PdfBox-Android", "decode array " + decode + " not compatible with color space, using the first two entries");
                    return new float[]{fFloatValue, fFloatValue2};
                }
            }
            Log.e("PdfBox-Android", "decode array " + decode + " not compatible with color space, using default");
            floatArray = null;
        } else {
            floatArray = decode.toFloatArray();
        }
        return floatArray == null ? pDImage.getColorSpace().getDefaultDecode(pDImage.getBitsPerComponent()) : floatArray;
    }
}
