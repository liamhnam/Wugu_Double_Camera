package com.epson.isv.eprinterdriver.Common;

public final class ServiceIntent {
    public static final String TargetComponentName = "com.epson.isv.eprinterdriver";

    public static final class EventType {
        public static final int PrintJob_AutoContinue = 65542;
        public static final int PrintJob_Begin = 65537;
        public static final int PrintJob_CleaningTime = 65543;
        public static final int PrintJob_PageFinish = 65538;
        public static final int PrintJob_Pause = 65539;
        public static final int PrintJob_Resume = 65540;
        public static final int PrintJob_Stop = 65541;
        public static final int QueryJob_Finish = 262146;
        public static final int QueryJob_bigin = 262145;
        public static final int SearchJob_Begin = 131073;
        public static final int SearchJob_FindPrinter = 131074;
        public static final int SearchJob_Finish = 131075;
    }

    public static final class PauseFactor {
        public static final int PrinterErrorOccur = 65537;
    }

    public static final class QueryActionType {
        public static final int GetInkInfo = 2;
        public static final int GetSupportedMedia = 1;
    }

    public static final class QueryFinishFactor {
        public static final int QueryError = 524291;
        public static final int QuerySuccess = 524289;
        public static final int UserCancel = 524290;
    }

    public static final class SearchFinishFactor {
        public static final int SearchError = 262147;
        public static final int SearchSuccess = 262145;
        public static final int UserCancel = 262146;
    }

    public static final class StopFactor {
        public static final int OtherError = 131078;
        public static final int PrintSuccess = 131073;
        public static final int PrinterErrorOccur = 131074;
        public static final int PrinterStopButton = 131076;
        public static final int UserCancel = 131075;
    }

    public static final class TaskType {
        public static final int PrintJob = 1;
        public static final int QueryJob = 3;
        public static final int SearchJob = 2;
    }
}
