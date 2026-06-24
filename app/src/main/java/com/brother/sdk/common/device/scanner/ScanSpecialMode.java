package com.brother.sdk.common.device.scanner;

public enum ScanSpecialMode {
    NORMAL_SCAN(0),
    EDGE_SCAN(1),
    ORIGINAL_SCAN(2),
    CORNER_SCAN(3),
    SKEW_ADJUST(4),
    OVER_SCAN(5),
    LABEL_SCAN_CIRCLE(6),
    LABEL_SCAN_SQUARE(7),
    COPYQUALITY_SCAN(8);

    private final int value;

    ScanSpecialMode(int i) {
        this.value = i;
    }

    public int toValue() {
        return this.value;
    }

    public static ScanSpecialMode fromValue(int i) {
        for (ScanSpecialMode scanSpecialMode : values()) {
            if (scanSpecialMode.toValue() == i) {
                return scanSpecialMode;
            }
        }
        return null;
    }
}
