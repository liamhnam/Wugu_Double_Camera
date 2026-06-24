package com.wugu.doublecamera.listener;

public interface ICameraListener {
    void cameraEvent(int i);

    void cameraEvent(int i, int... iArr);

    void connectedDevice(boolean z);

    void onError(String str);

    void onPropertyChanged(int i, int i2);

    void recBitmap(byte[] bArr, int i, int i2, boolean z);
}
