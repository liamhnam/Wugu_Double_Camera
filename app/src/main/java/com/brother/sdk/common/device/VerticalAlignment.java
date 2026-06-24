package com.brother.sdk.common.device;

public enum VerticalAlignment {
    UNDEFINED(-1),
    TOP(0),
    CENTER(1),
    BOTTOM(2);

    private final int value;

    VerticalAlignment(int i) {
        this.value = i;
    }

    public int toValue() {
        return this.value;
    }

    public static VerticalAlignment fromValue(int i) {
        for (VerticalAlignment verticalAlignment : values()) {
            if (verticalAlignment.toValue() == i) {
                return verticalAlignment;
            }
        }
        return UNDEFINED;
    }
}
