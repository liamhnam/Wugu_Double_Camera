package com.brother.sdk.common.device.scanner;

public enum ScannerModelSize {
    A4(0),
    B4(1),
    A3(2);

    private final int value;

    ScannerModelSize(int i) {
        this.value = i;
    }

    public int toValue() {
        return this.value;
    }

    public static ScannerModelSize fromValue(int i) {
        for (ScannerModelSize scannerModelSize : values()) {
            if (scannerModelSize.toValue() == i) {
                return scannerModelSize;
            }
        }
        return null;
    }
}
