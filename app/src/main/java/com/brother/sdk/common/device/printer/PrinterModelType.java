package com.brother.sdk.common.device.printer;

public enum PrinterModelType {
    UNKNOWN(2, true),
    PRINT_LED(3, false),
    PRINT_LASER(4, false),
    PRINT_INKJET(12, false),
    PRINT_MOBILE(17, false),
    UNDEFINED(-1, false);

    private final boolean mSupportScanOnly;
    private final int value;

    PrinterModelType(int i, boolean z) {
        this.value = i;
        this.mSupportScanOnly = z;
    }

    public int toValue() {
        return this.value;
    }

    public static PrinterModelType fromValue(int i) {
        for (PrinterModelType printerModelType : values()) {
            if (printerModelType.toValue() == i) {
                return printerModelType;
            }
        }
        return UNDEFINED;
    }

    public static PrinterModelType fromValue(Integer num) {
        if (num != null) {
            for (PrinterModelType printerModelType : values()) {
                if (printerModelType.toValue() == num.intValue()) {
                    return printerModelType;
                }
            }
        }
        return UNDEFINED;
    }

    public boolean isDocumentScannerModel() {
        return this.mSupportScanOnly;
    }
}
