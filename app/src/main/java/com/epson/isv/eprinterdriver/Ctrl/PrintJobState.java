package com.epson.isv.eprinterdriver.Ctrl;

class PrintJobState {
    private short allPageNum;
    private int cleaningTime;
    private short currentPageNum;
    private int errorCode;
    private int eventType;
    private short finishedPageNum;
    private int pauseFactor;
    private int stopFactor;

    public static final class PrintEventType {
        public static final int PrintJob_AutoContinue = 5;
        public static final int PrintJob_Begin = 0;
        public static final int PrintJob_CleaningTime = 6;
        public static final int PrintJob_Continue = 3;
        public static final int PrintJob_Finish = 4;
        public static final int PrintJob_PageFinished = 1;
        public static final int PrintJob_Pause = 2;
    }

    public static final class PrintPauseFactor {
        public static final int Pause_FactorNone = 100;
        public static final int Pause_PrinterError = 0;
    }

    public static final class PrintStopFactor {
        public static final int Stop_FactorNone = 100;
        public static final int Stop_OtherError = 5;
        public static final int Stop_PrintSuccess = 0;
        public static final int Stop_PrinterError = 1;
        public static final int Stop_PrinterStopButton = 3;
        public static final int Stop_UserCancel = 2;
    }

    public PrintJobState(int i) {
        this.eventType = i;
        this.allPageNum = (short) 0;
        this.finishedPageNum = (short) 0;
        this.currentPageNum = (short) 0;
        this.pauseFactor = 100;
        this.stopFactor = 100;
        this.errorCode = 0;
    }

    public PrintJobState(int i, int i2) {
        this.eventType = i;
        this.cleaningTime = i2;
        this.allPageNum = (short) 0;
        this.finishedPageNum = (short) 0;
        this.currentPageNum = (short) 0;
        this.pauseFactor = 100;
        this.stopFactor = 100;
        this.errorCode = 0;
    }

    public PrintJobState(int i, short s, short s2) {
        this.eventType = i;
        this.allPageNum = s;
        this.finishedPageNum = s2;
        this.currentPageNum = s2;
        this.pauseFactor = 100;
        this.stopFactor = 100;
        this.errorCode = 0;
    }

    public PrintJobState(int i, short s, short s2, int i2, int i3) {
        this.eventType = i;
        this.allPageNum = s;
        this.finishedPageNum = (short) 0;
        this.currentPageNum = s2;
        this.pauseFactor = i2;
        this.stopFactor = 100;
        this.errorCode = i3;
    }

    public PrintJobState(int i, short s, short s2, short s3) {
        this.eventType = i;
        this.allPageNum = s;
        this.finishedPageNum = s2;
        this.currentPageNum = s3;
        this.pauseFactor = 100;
        this.stopFactor = 100;
        this.errorCode = 0;
    }

    public PrintJobState(int i, short s, short s2, short s3, int i2, int i3) {
        this.eventType = i;
        this.allPageNum = s;
        this.finishedPageNum = s3;
        this.currentPageNum = s2;
        this.pauseFactor = 100;
        this.stopFactor = i2;
        this.errorCode = i3;
    }

    public int getEventType() {
        return this.eventType;
    }

    public int getCleaningTime() {
        return this.cleaningTime;
    }

    public short getAllPageNum() {
        return this.allPageNum;
    }

    public short getFinishedPageNum() {
        return this.finishedPageNum;
    }

    public short getCurrentPageNum() {
        return this.currentPageNum;
    }

    public int getPauseFactor() {
        return this.pauseFactor;
    }

    public int getStopFactor() {
        return this.stopFactor;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
