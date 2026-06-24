package com.brother.sdk.common.device;

public enum DeviceModelType {
    BHmini9(false, false, false, false),
    BHM9(false, false, false, false),
    BHL9(false, false, false, false),
    BHmini9e(false, false, false, false),
    BHmini9e2(false, false, false, false),
    BH9e3(false, false, false, false),
    BHM11(false, false, false, false),
    BHL11(false, false, false, false),
    BHmini11(false, false, false, false),
    BHmini11dash(false, false, false, false),
    BHS13(false, false, false, false),
    ADS(false, false, false, false),
    BCFB(true, false, false, false),
    BLFB(true, false, false, false),
    DCLFB(true, false, false, false),
    ALLSF(true, false, false, false),
    Cobra(true, false, false, false),
    SuperLow4C(true, true, false, false),
    BHS15(true, true, false, false),
    DL(true, true, true, false),
    DLLPrinter(true, false, false, true),
    ELLFB(true, false, false, true),
    MINI19HT(false, false, true, true),
    BHMB19(false, false, false, true),
    FCL(false, false, true, true),
    EL(false, false, false, true),
    GeneralBrotherDevice(true, true, false, false),
    SupportUSBAndScanPrinter(true, true, false, true);

    private final boolean mDirectPrint;
    private final boolean mNeedCheckPrint;
    private final boolean mNeedCheckScan;
    private final boolean mUsbSupport;

    DeviceModelType(boolean z, boolean z2, boolean z3, boolean z4) {
        this.mNeedCheckPrint = z;
        this.mNeedCheckScan = z2;
        this.mDirectPrint = z3;
        this.mUsbSupport = z4;
    }

    public boolean mustCheckDevicePrintFunctionAdditionally() {
        return this.mNeedCheckPrint;
    }

    public boolean mustCheckDeviceScanFunctionAdditionally() {
        return this.mNeedCheckScan;
    }

    public boolean isDirectPrintSupported() {
        return this.mDirectPrint;
    }

    public boolean isUsbSupported() {
        return this.mUsbSupport;
    }
}
