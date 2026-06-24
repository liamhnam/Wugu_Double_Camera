package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;
import android.graphics.Rect;

interface IBandRenderer {
    Bitmap getBand(int i, int i2, int i3, Rect rect);
}
