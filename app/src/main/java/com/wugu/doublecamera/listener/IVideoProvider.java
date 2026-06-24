package com.wugu.doublecamera.listener;

import android.graphics.Bitmap;

public interface IVideoProvider {
    Bitmap next();

    void progress(float f);

    int size();
}
