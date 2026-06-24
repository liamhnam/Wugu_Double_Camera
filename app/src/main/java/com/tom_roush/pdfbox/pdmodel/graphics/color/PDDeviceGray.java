package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;
import java.nio.ByteBuffer;

public final class PDDeviceGray extends PDDeviceColorSpace {
    public static final PDDeviceGray INSTANCE = new PDDeviceGray();
    private final PDColor initialColor = new PDColor(new float[]{0.0f}, this);

    @Override
    public int getNumberOfComponents() {
        return 1;
    }

    private PDDeviceGray() {
    }

    @Override
    public String getName() {
        return COSName.DEVICEGRAY.getName();
    }

    @Override
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f};
    }

    @Override
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override
    public float[] toRGB(float[] fArr) {
        float f = fArr[0];
        return new float[]{fArr[0], f, f};
    }

    @Override
    public Bitmap toRGBImage(Bitmap bitmap) throws IOException {
        if (bitmap.getConfig() != Bitmap.Config.ALPHA_8) {
            Log.e("PdfBox-Android", "Raster in PDDevicGrey was not ALPHA_8");
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bitmap.getRowBytes() * height);
        bitmap.copyPixelsToBuffer(byteBufferAllocate);
        byte[] bArrArray = byteBufferAllocate.array();
        int i = width * height;
        int[] iArr = new int[i];
        bitmapCreateBitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            byte b = bArrArray[i2];
            iArr[i2] = Color.argb(255, (int) b, (int) b, (int) b);
        }
        bitmapCreateBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmapCreateBitmap;
    }
}
