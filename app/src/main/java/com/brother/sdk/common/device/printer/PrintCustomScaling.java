package com.brother.sdk.common.device.printer;

import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.VerticalAlignment;

public class PrintCustomScaling {
    public HorizontalAlignment hAlignment;
    public double scaleX;
    public double scaleY;
    public VerticalAlignment vAlignment;

    public PrintCustomScaling() {
        this.scaleX = 1.0d;
        this.scaleY = 1.0d;
        this.hAlignment = HorizontalAlignment.LEFT;
        this.vAlignment = VerticalAlignment.TOP;
        this.scaleX = 1.0d;
        this.scaleY = 1.0d;
        this.hAlignment = HorizontalAlignment.LEFT;
        this.vAlignment = VerticalAlignment.TOP;
    }

    public PrintCustomScaling(double d, double d2) {
        this.scaleX = 1.0d;
        this.scaleY = 1.0d;
        this.hAlignment = HorizontalAlignment.LEFT;
        this.vAlignment = VerticalAlignment.TOP;
        this.scaleX = d;
        this.scaleY = d2;
        this.hAlignment = HorizontalAlignment.LEFT;
        this.vAlignment = VerticalAlignment.TOP;
    }

    public PrintCustomScaling(double d, double d2, HorizontalAlignment horizontalAlignment, VerticalAlignment verticalAlignment) {
        this.scaleX = 1.0d;
        this.scaleY = 1.0d;
        this.hAlignment = HorizontalAlignment.LEFT;
        VerticalAlignment verticalAlignment2 = VerticalAlignment.TOP;
        this.scaleX = d;
        this.scaleY = d2;
        this.hAlignment = horizontalAlignment;
        this.vAlignment = verticalAlignment;
    }
}
