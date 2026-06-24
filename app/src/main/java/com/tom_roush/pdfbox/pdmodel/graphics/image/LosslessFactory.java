package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageOutputStream;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.filter.FilterFactory;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class LosslessFactory {
    private LosslessFactory() {
    }

    public static PDImageXObject createFromImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        PDColorSpace pDColorSpace;
        byte[] byteArray;
        int i;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int i2 = 0;
        if (bitmap.getConfig() == Bitmap.Config.ALPHA_8) {
            PDDeviceGray pDDeviceGray = PDDeviceGray.INSTANCE;
            int i3 = width * 8;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((i3 / 8) + ((i3 % 8 != 0 ? 1 : 0) * height));
            MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(byteArrayOutputStream);
            bitmap.getPixels(new int[width * height], 0, width, 0, 0, width, height);
            while (i2 < height) {
                int i4 = width * i2;
                while (true) {
                    i = i2 + 1;
                    if (i4 >= i * width) {
                        break;
                    }
                    memoryCacheImageOutputStream.writeBits(r15[i4] & 255, 8);
                    i4++;
                }
                int bitOffset = memoryCacheImageOutputStream.getBitOffset();
                if (bitOffset != 0) {
                    memoryCacheImageOutputStream.writeBits(0L, 8 - bitOffset);
                }
                i2 = i;
            }
            memoryCacheImageOutputStream.flush();
            memoryCacheImageOutputStream.close();
            byteArray = byteArrayOutputStream.toByteArray();
            pDColorSpace = pDDeviceGray;
        } else {
            PDDeviceRGB pDDeviceRGB = PDDeviceRGB.INSTANCE;
            int i5 = width * height;
            byte[] bArr = new byte[i5 * 3];
            int[] iArr = new int[i5];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            int i6 = 0;
            while (i2 < i5) {
                int i7 = iArr[i2];
                int i8 = i6 + 1;
                bArr[i6] = (byte) ((i7 >> 16) & 255);
                int i9 = i8 + 1;
                bArr[i8] = (byte) ((i7 >> 8) & 255);
                bArr[i9] = (byte) (i7 & 255);
                i2++;
                i6 = i9 + 1;
            }
            pDColorSpace = pDDeviceRGB;
            byteArray = bArr;
        }
        PDImageXObject pDImageXObjectPrepareImageXObject = prepareImageXObject(pDDocument, byteArray, bitmap.getWidth(), bitmap.getHeight(), 8, pDColorSpace);
        PDImageXObject pDImageXObjectCreateAlphaFromARGBImage = createAlphaFromARGBImage(pDDocument, bitmap);
        if (pDImageXObjectCreateAlphaFromARGBImage != null) {
            pDImageXObjectPrepareImageXObject.getCOSObject().setItem(COSName.SMASK, pDImageXObjectCreateAlphaFromARGBImage);
        }
        return pDImageXObjectPrepareImageXObject;
    }

    private static PDImageXObject createAlphaFromARGBImage(PDDocument pDDocument, Bitmap bitmap) throws IOException {
        if (!bitmap.hasAlpha()) {
            return null;
        }
        int height = bitmap.getHeight() * bitmap.getWidth();
        int[] iArr = new int[height];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < height; i++) {
            byteArrayOutputStream.write(Color.alpha(iArr[i]));
        }
        return prepareImageXObject(pDDocument, byteArrayOutputStream.toByteArray(), bitmap.getWidth(), bitmap.getHeight(), 8, PDDeviceGray.INSTANCE);
    }

    private static PDImageXObject prepareImageXObject(PDDocument pDDocument, byte[] bArr, int i, int i2, int i3, PDColorSpace pDColorSpace) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length / 2);
        FilterFactory.INSTANCE.getFilter(COSName.FLATE_DECODE).encode(new ByteArrayInputStream(bArr), byteArrayOutputStream, new COSDictionary(), 0);
        return new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE, i, i2, i3, pDColorSpace);
    }
}
