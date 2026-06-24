package com.epson.isv.eprinterdriver.Ctrl;

import com.epson.isv.eprinterdriver.Common.EpsPrinter;

class SearchJobState {
    int errorCode;
    int eventType;
    EpsPrinter retEpsPrinter;
    int stopFactor;

    public static final class SearchEventType {
        public static final int SearchJob_Begin = 0;
        public static final int SearchJob_FindPrinter = 1;
        public static final int SearchJob_Finish = 2;
    }

    public static final class SearchStopFactor {
        public static final int SearchError = 2;
        public static final int SearchSuccess = 0;
        public static final int StopFactorNone = 100;
        public static final int UserCancel = 1;
    }

    public SearchJobState(int i) {
        this.eventType = i;
        this.stopFactor = 100;
        this.errorCode = 0;
        this.retEpsPrinter = null;
    }

    public SearchJobState(int i, EpsPrinter epsPrinter) {
        this.eventType = i;
        this.stopFactor = 100;
        this.errorCode = 0;
        this.retEpsPrinter = epsPrinter;
    }

    public SearchJobState(int i, int i2, int i3) {
        this.eventType = i;
        this.stopFactor = i2;
        this.errorCode = i3;
        this.retEpsPrinter = null;
    }

    public int getEventType() {
        return this.eventType;
    }

    public void setEventType(int i) {
        this.eventType = i;
    }

    public int getStopFactor() {
        return this.stopFactor;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public EpsPrinter getRetEpsPrinter() {
        return this.retEpsPrinter;
    }
}
