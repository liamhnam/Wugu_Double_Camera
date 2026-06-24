package com.wugu.doublecamera.device.camera.camerausb;

import android.app.Activity;
import com.wugu.facebeauty.bean.BeautifyItem;

public abstract class AUsbCamera {
    protected final Activity mActivity;

    public abstract void capture();

    public abstract void close();

    public abstract void init(boolean z);

    public abstract void preview();

    public abstract void setBeautyFace(BeautifyItem beautifyItem);

    public abstract void setBitmapRect(int i, int i2);

    public abstract void setEffectItem(int i, String str);

    public abstract void setFrameWH(int i, int i2);

    public abstract void startRecord();

    public AUsbCamera(Activity activity) {
        this.mActivity = activity;
    }
}
