package com.hiti.usb.printer;

public class CardCalibrationValue {
    public int posA;
    public int posB;
    public int posC;

    public CardCalibrationValue() {
        this(0, 0, 0);
    }

    public CardCalibrationValue(int i, int i2, int i3) {
        this.posA = i;
        this.posB = i2;
        this.posC = i3;
    }
}
