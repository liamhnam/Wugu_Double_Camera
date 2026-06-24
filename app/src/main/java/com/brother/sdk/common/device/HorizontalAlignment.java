package com.brother.sdk.common.device;

public enum HorizontalAlignment {
    UNDEFINED(-1),
    LEFT(0),
    CENTER(1),
    RIGHT(2);

    private final int value;

    HorizontalAlignment(int i) {
        this.value = i;
    }

    public int toValue() {
        return this.value;
    }

    public static HorizontalAlignment fromValue(int i) {
        for (HorizontalAlignment horizontalAlignment : values()) {
            if (horizontalAlignment.toValue() == i) {
                return horizontalAlignment;
            }
        }
        return UNDEFINED;
    }
}
