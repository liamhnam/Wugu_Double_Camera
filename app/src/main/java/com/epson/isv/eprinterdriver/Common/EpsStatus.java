package com.epson.isv.eprinterdriver.Common;

public class EpsStatus {
    private int errorCode;
    private int errorValue;
    private int fatalError;
    private int hiddenError;
    private boolean jobContinue;
    private int paperJam;
    private int printedFacesBlank;
    private int printedFacesColor;
    private int printedFacesMono;
    private int printerStatus;
    private int version;
    private int warningCode;

    public static final class PrinterStatus {
        public static final int EPS_PRNST_BUSY = 2;
        public static final int EPS_PRNST_CANCELLING = 3;
        public static final int EPS_PRNST_ERROR = 4;
        public static final int EPS_PRNST_IDLE = 0;
        public static final int EPS_PRNST_PRINTING = 1;
    }

    public EpsStatus() {
        this.version = 0;
        this.printerStatus = -1;
        this.errorCode = 0;
        this.warningCode = 0;
        this.jobContinue = true;
        this.fatalError = 0;
        this.paperJam = 0;
        this.errorValue = 0;
        this.hiddenError = 0;
        this.printedFacesColor = -1;
        this.printedFacesMono = -1;
        this.printedFacesBlank = -1;
    }

    public EpsStatus(int i, int i2, int i3) {
        this.version = i;
        this.printerStatus = i2;
        this.errorCode = i3;
    }

    public EpsStatus(int i, int i2, int i3, int i4, boolean z, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
        this.version = i;
        this.printerStatus = i2;
        this.errorCode = i3;
        this.warningCode = i4;
        this.jobContinue = z;
        this.fatalError = i5;
        this.paperJam = i6;
        this.errorValue = i7;
        this.hiddenError = i8;
        this.printedFacesColor = i9;
        this.printedFacesMono = i10;
        this.printedFacesBlank = i11;
    }

    public void setEpsStatus(EpsStatus epsStatus) {
        this.version = epsStatus.version;
        this.printerStatus = epsStatus.printerStatus;
        this.errorCode = epsStatus.errorCode;
        this.warningCode = epsStatus.warningCode;
        this.jobContinue = epsStatus.jobContinue;
        this.fatalError = epsStatus.fatalError;
        this.paperJam = epsStatus.paperJam;
        this.errorValue = epsStatus.errorValue;
        this.hiddenError = epsStatus.hiddenError;
        this.printedFacesColor = epsStatus.printedFacesColor;
        this.printedFacesMono = epsStatus.printedFacesMono;
        this.printedFacesBlank = epsStatus.printedFacesBlank;
    }

    public void retEpsStatus() {
        this.version = 0;
        this.printerStatus = -1;
        this.errorCode = 0;
        this.warningCode = 0;
        this.jobContinue = true;
        this.fatalError = 0;
        this.paperJam = 0;
        this.errorValue = 0;
        this.hiddenError = 0;
        this.printedFacesColor = -1;
        this.printedFacesMono = -1;
        this.printedFacesBlank = -1;
    }

    public int getVersion() {
        return this.version;
    }

    public int getPrinterStatus() {
        return this.printerStatus;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getWarningCode() {
        return this.warningCode;
    }

    public boolean isJobContinue() {
        return this.jobContinue;
    }

    protected int getFatalError() {
        return this.fatalError;
    }

    protected int getPaperJam() {
        return this.paperJam;
    }

    protected int getErrorValue() {
        return this.errorValue;
    }

    protected int getHiddenError() {
        return this.hiddenError;
    }

    public int getPrintedFacesColor() {
        return this.printedFacesColor;
    }

    public int getPrintedFacesMono() {
        return this.printedFacesMono;
    }

    public int getPrintedFacesBlank() {
        return this.printedFacesBlank;
    }

    public String toString() {
        return ("[PrinterStatus]\nStatus : " + getPrinterStatus() + "\n") + "Error  : " + getErrorCode() + "\n";
    }
}
