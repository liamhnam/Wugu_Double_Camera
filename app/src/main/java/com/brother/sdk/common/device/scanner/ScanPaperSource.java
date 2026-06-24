package com.brother.sdk.common.device.scanner;

public enum ScanPaperSource {
    AUTO(0),
    ADF(1),
    FB(2);

    private final int value;

    ScanPaperSource(int i) {
        this.value = i;
    }

    public int toValue() {
        return this.value;
    }

    public static ScanPaperSource fromValue(int i) {
        for (ScanPaperSource scanPaperSource : values()) {
            if (scanPaperSource.toValue() == i) {
                return scanPaperSource;
            }
        }
        return null;
    }
}
