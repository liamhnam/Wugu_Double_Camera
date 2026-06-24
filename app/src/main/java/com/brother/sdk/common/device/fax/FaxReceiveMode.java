package com.brother.sdk.common.device.fax;

public enum FaxReceiveMode {
    Undefined(0),
    Print(1),
    Storage(2),
    StoragePrint(3),
    Cache(4),
    CachePrint(5);

    public final int val;

    FaxReceiveMode(int i) {
        this.val = i;
    }

    public static FaxReceiveMode fromValue(int i) {
        for (FaxReceiveMode faxReceiveMode : values()) {
            if (i == faxReceiveMode.val) {
                return faxReceiveMode;
            }
        }
        return Undefined;
    }
}
