package com.tom_roush.pdfbox.pdmodel.graphics.color;

import android.graphics.Bitmap;
import android.util.Log;
import com.tom_roush.pdfbox.cos.COSName;
import java.io.IOException;

public final class PDDeviceRGB extends PDDeviceColorSpace {
    public static final PDDeviceRGB INSTANCE = new PDDeviceRGB();
    private final PDColor initialColor = new PDColor(new float[]{0.0f, 0.0f, 0.0f}, this);

    @Override
    public int getNumberOfComponents() {
        return 3;
    }

    private PDDeviceRGB() {
    }

    private void init() {
        synchronized (this) {
        }
    }

    @Override
    public String getName() {
        return COSName.DEVICERGB.getName();
    }

    @Override
    public float[] getDefaultDecode(int i) {
        return new float[]{0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f};
    }

    @Override
    public PDColor getInitialColor() {
        return this.initialColor;
    }

    @Override
    public float[] toRGB(float[] fArr) {
        return fArr.length == 3 ? fArr : this.initialColor.getComponents();
    }

    @Override
    public Bitmap toRGBImage(Bitmap bitmap) throws IOException {
        if (bitmap.getConfig() == Bitmap.Config.ALPHA_8) {
            Log.e("PdfBox-Android", "Raster in PDDeviceRGB was ALPHA_8");
        }
        return bitmap;
    }
}
