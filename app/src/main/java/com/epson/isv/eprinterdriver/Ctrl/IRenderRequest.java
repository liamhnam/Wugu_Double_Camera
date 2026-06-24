package com.epson.isv.eprinterdriver.Ctrl;

import android.graphics.Canvas;

interface IRenderRequest {
    void OnDraw(Canvas canvas, DrawInfo drawInfo);
}
