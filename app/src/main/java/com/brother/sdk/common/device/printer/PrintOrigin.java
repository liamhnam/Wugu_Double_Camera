package com.brother.sdk.common.device.printer;

public class PrintOrigin {
    public double inchX;
    public double inchY;

    public PrintOrigin() {
        this.inchX = 0.0d;
        this.inchY = 0.0d;
    }

    public PrintOrigin(double d, double d2) {
        this.inchX = d;
        this.inchY = d2;
    }
}
