package com.tom_roush.pdfbox.pdmodel.interactive.pagenavigation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;

public enum PDTransitionDirection {
    LEFT_TO_RIGHT(0),
    BOTTOM_TO_TOP(90),
    RIGHT_TO_LEFT(180),
    TOP_TO_BOTTOM(270),
    TOP_LEFT_TO_BOTTOM_RIGHT(315),
    NONE(0) {
        @Override
        public COSBase getCOSBase() {
            return COSName.NONE;
        }
    };

    private final int degrees;

    PDTransitionDirection(int i) {
        this.degrees = i;
    }

    public COSBase getCOSBase() {
        return COSInteger.get(this.degrees);
    }
}
