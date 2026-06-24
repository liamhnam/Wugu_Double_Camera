package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Rect;
import java.nio.Buffer;

class EpsBandData {
    private Bitmap bandBitmap;
    private int[] bandPixels;
    private Rect bandRect;
    int height;
    private int validBand;
    int width;

    public EpsBandData(Bitmap bitmap, Rect rect, int i) {
        if (bitmap != null) {
            this.width = bitmap.getWidth();
            int height = bitmap.getHeight();
            this.height = height;
            this.bandPixels = new int[this.width * height];
        }
        this.bandBitmap = bitmap;
        this.bandRect = rect;
        this.validBand = i;
    }

    public int[] getBandPixels() {
        int[] iArr = this.bandPixels;
        if (iArr != null) {
            Bitmap bitmap = this.bandBitmap;
            int i = this.width;
            bitmap.getPixels(iArr, 0, i, 0, 0, i, this.validBand);
        }
        return this.bandPixels;
    }

    public void copyPixelsToBuffer(Buffer buffer) {
        Bitmap bitmap = this.bandBitmap;
        if (bitmap != null) {
            bitmap.copyPixelsToBuffer(buffer);
        }
    }

    public int getValidBand() {
        return this.validBand;
    }

    public void setValidBand(int i) {
        this.validBand = i;
    }

    public int getBandRectLeft() {
        return this.bandRect.left;
    }

    public int getBandRectTop() {
        return this.bandRect.top;
    }

    public int getBandRectRight() {
        return this.bandRect.right;
    }

    public int getBandRectBottom() {
        return this.bandRect.bottom;
    }
}
