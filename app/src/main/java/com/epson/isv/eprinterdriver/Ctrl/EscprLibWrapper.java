package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import com.epson.isv.eprinterdriver.Common.EpsCapability;
import com.epson.isv.eprinterdriver.Common.EpsInkInfo;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsProbe;
import com.epson.isv.eprinterdriver.Common.EpsProtocol;
import com.epson.isv.eprinterdriver.Common.EpsStatus;

class EscprLibWrapper {
    private static Context mContext;
    private static IPrintJobStateCallback printJobStateCB;
    private static IPrinterFindCallback printerCB;

    public static native int nativeCancelBandPrint();

    private static native int nativeCancelFindPrinter();

    private static native int nativeCancelPrint(int i);

    private static native int nativeContinuePrint();

    public static native int nativeEndBandPrint();

    private static native int nativeFindPrinter(EpsProtocol epsProtocol, int i);

    public static native int nativeGetCapability(EpsCapability epsCapability);

    private static native int nativeGetInkInfo(EpsInkInfo epsInkInfo);

    public static native int nativeGetPrintableArea(PageAttribute pageAttribute, PrintableArea printableArea);

    private static native int nativeGetStatus(EpsStatus epsStatus);

    private static native int nativeInitDriver(Context context, EpsCommMode epsCommMode, long j);

    public static native int nativeMakeBandBegin(EpsBandHeader epsBandHeader);

    public static native int nativeMakeBandCtrl(EpsBandCtrl epsBandCtrl);

    public static native int nativeMakeBandData(EpsBandData epsBandData);

    public static native int nativeMakeBandFinish(int i);

    private static native int nativeProbePrinter(EpsProbe epsProbe);

    private static native int nativeReleaseDriver();

    public static native int nativeResumeBandPrint();

    private static native int nativeSetPrintStateNtyCBFunc(String str, String str2, String str3);

    private static native int nativeSetPrinter(EpsPrinter epsPrinter);

    private static native int nativeSetPrinterFindCBFunc(String str, String str2, String str3);

    public static native int nativeStartBandPrint(JobAttribute jobAttribute, EpsStatus epsStatus, EpsBandSetting epsBandSetting);

    private static native int nativeStartMaintenance(JobAttribute jobAttribute, EpsStatus epsStatus);

    private static native int nativeStartPrint(JobAttribute jobAttribute, EpsStatus epsStatus);

    private static native int nativeStartPrintMultiPages(JobAttribute jobAttribute, EpsStatus epsStatus, IPrintListener iPrintListener);

    private static native void nativeUnsetPrintStateNtyCBFunc();

    private static native void nativeUnsetPrinterFindCBFunc();

    EscprLibWrapper() {
    }

    public static int LibInitDriver(Context context, EpsCommMode epsCommMode, IPrinterFindCallback iPrinterFindCallback, IPrintJobStateCallback iPrintJobStateCallback, long j) {
        System.loadLibrary("eprinterdriver");
        mContext = context;
        printerCB = iPrinterFindCallback;
        printJobStateCB = iPrintJobStateCallback;
        int iNativeInitDriver = nativeInitDriver(context, epsCommMode, j);
        if (iNativeInitDriver != 0) {
            return iNativeInitDriver;
        }
        int iNativeSetPrinterFindCBFunc = nativeSetPrinterFindCBFunc("com/epson/isv/eprinterdriver/Ctrl/EscprLibWrapper", "PrinterFind", "(Lcom/epson/isv/eprinterdriver/Common/EpsPrinter;)V");
        return iNativeSetPrinterFindCBFunc != 0 ? iNativeSetPrinterFindCBFunc : nativeSetPrintStateNtyCBFunc("com/epson/isv/eprinterdriver/Ctrl/EscprLibWrapper", "PrintJobStateNty", "(Lcom/epson/isv/eprinterdriver/Ctrl/PrintJobState;)V");
    }

    public static int LibReleaseDriver() {
        nativeUnsetPrintStateNtyCBFunc();
        nativeUnsetPrinterFindCBFunc();
        return nativeReleaseDriver();
    }

    public static int LibFindPrinter(EpsProtocol epsProtocol, int i) {
        return nativeFindPrinter(epsProtocol, i);
    }

    public static int LibProbePrinter(EpsProbe epsProbe) {
        return nativeProbePrinter(epsProbe);
    }

    public static int LibCancelFindPrinter() {
        return nativeCancelFindPrinter();
    }

    public static int LibSetPrinter(EpsPrinter epsPrinter) {
        return nativeSetPrinter(epsPrinter);
    }

    public static int LibGetCapability(EpsCapability epsCapability) {
        return nativeGetCapability(epsCapability);
    }

    public static int LibGetInkInfo(EpsInkInfo epsInkInfo) {
        return nativeGetInkInfo(epsInkInfo);
    }

    public static int LibGetStatus(EpsStatus epsStatus) {
        return nativeGetStatus(epsStatus);
    }

    public static int LibStartMaintenance(JobAttribute jobAttribute, EpsStatus epsStatus) {
        return nativeStartMaintenance(jobAttribute, epsStatus);
    }

    public static int LibStartPrint(JobAttribute jobAttribute, EpsStatus epsStatus) {
        return nativeStartPrint(jobAttribute, epsStatus);
    }

    public static int LibStartPrint(JobAttribute jobAttribute, EpsStatus epsStatus, IPrintListener iPrintListener) {
        return nativeStartPrintMultiPages(jobAttribute, epsStatus, iPrintListener);
    }

    public static int LibCancelPrint(int i) {
        nativeCancelBandPrint();
        return nativeCancelPrint(i);
    }

    public static int LibContinuePrint() {
        return nativeContinuePrint();
    }

    protected static void PrinterFind(EpsPrinter epsPrinter) {
        IPrinterFindCallback iPrinterFindCallback = printerCB;
        if (iPrinterFindCallback != null) {
            iPrinterFindCallback.printerFindNty(epsPrinter);
        }
    }

    protected static void PrintJobStateNty(PrintJobState printJobState) {
        IPrintJobStateCallback iPrintJobStateCallback = printJobStateCB;
        if (iPrintJobStateCallback != null) {
            iPrintJobStateCallback.printJobStateNty(printJobState);
        }
    }
}
