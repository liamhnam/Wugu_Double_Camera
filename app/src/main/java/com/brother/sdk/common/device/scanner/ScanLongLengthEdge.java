package com.brother.sdk.common.device.scanner;

public enum ScanLongLengthEdge {
    NoSupport(0, 0),
    Support863mm(1, 863);

    public final int length;
    private int value;

    ScanLongLengthEdge(int i, int i2) {
        this.value = i;
        this.length = i2;
    }

    public static ScanLongLengthEdge fromValue(int i) {
        for (ScanLongLengthEdge scanLongLengthEdge : values()) {
            if (scanLongLengthEdge.value == i) {
                return scanLongLengthEdge;
            }
        }
        return NoSupport;
    }
}
