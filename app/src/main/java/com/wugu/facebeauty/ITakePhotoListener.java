package com.wugu.facebeauty;

import android.graphics.Bitmap;

public interface ITakePhotoListener {
    void captureFinish(Bitmap bitmap);

    void onSurfaceCreated();

    void recordFinish(String str);
}
