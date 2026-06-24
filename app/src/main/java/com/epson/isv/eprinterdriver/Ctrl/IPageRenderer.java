package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Bitmap;

interface IPageRenderer {
    Bitmap getPage(int i, int i2, int i3);
}
