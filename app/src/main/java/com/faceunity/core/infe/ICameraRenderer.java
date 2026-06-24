package com.faceunity.core.infe;

import android.graphics.Bitmap;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH&J\b\u0010\u0010\u001a\u00020\u0003H&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0006H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\rH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H&J\b\u0010\u0018\u001a\u00020\u0003H&¨\u0006\u0019"}, m1293d2 = {"Lcom/faceunity/core/infe/ICameraRenderer;", "", "closeCamera", "", "drawSmallViewport", "isShow", "", "hideImageTexture", "onDestroy", "onPause", "onResume", "onTouchEvent", "x", "", "y", "action", "reopenCamera", "setFURenderSwitch", "isOpen", "setTransitionFrameCount", "count", "showImageTexture", "bitmap", "Landroid/graphics/Bitmap;", "switchCamera", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public interface ICameraRenderer {
    void closeCamera();

    void drawSmallViewport(boolean isShow);

    void hideImageTexture();

    void onDestroy();

    void onPause();

    void onResume();

    void onTouchEvent(int x, int y, int action);

    void reopenCamera();

    void setFURenderSwitch(boolean isOpen);

    void setTransitionFrameCount(int count);

    void showImageTexture(Bitmap bitmap);

    void switchCamera();
}
