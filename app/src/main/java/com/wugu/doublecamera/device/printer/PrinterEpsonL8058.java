package com.wugu.doublecamera.device.printer;

import android.util.Log;
import com.epson.isv.eprinterdriver.Common.EpsPrinter;
import com.epson.isv.eprinterdriver.Common.EpsStatus;
import com.epson.isv.eprinterdriver.Ctrl.EPSetting;
import com.epson.isv.eprinterdriver.Ctrl.EPrintManager;
import com.epson.isv.eprinterdriver.Ctrl.IPrintListener;
import com.epson.isv.eprinterdriver.Ctrl.ISearchPrinterListener;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.listener.IPrinterStatusListener;
import com.wugu.doublecamera.utils.LoggerUtil;

public class PrinterEpsonL8058 extends APrinter {
    private static final String TAG = "PrinterEpsonL8058";
    private boolean isPrinting = false;
    private IPrinterStatusListener listener;
    private EpsPrinter printer;

    @Override
    public int getPrintTime() {
        return 60;
    }

    @Override
    public void printFinish() {
    }

    @Override
    public void updateColorParam(Object obj) {
    }

    @Override
    public void init(final IPrinterStatusListener iPrinterStatusListener) {
        this.listener = iPrinterStatusListener;
        EPrintManager.instance().addSearchListener(new ISearchPrinterListener() {
            @Override
            public void onSearchBegin() {
            }

            @Override
            public void onFindPrinter(EpsPrinter epsPrinter) {
                PrinterEpsonL8058.this.printer = epsPrinter;
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onFindPrinter SerialNo: " + PrinterEpsonL8058.this.printer.getSerialNo());
                EPrintManager.instance().cancelFindPrinter();
                EPSetting.instance().setSelEpsPrinter(PrinterEpsonL8058.this.printer);
                EpsStatus status = EPrintManager.instance().getStatus();
                if (status == null || status.getPrinterStatus() != 4 || iPrinterStatusListener == null) {
                    return;
                }
                LoggerUtil.m1182e(PrinterEpsonL8058.TAG, "onFindPrinter: " + status.getPrinterStatus());
                iPrinterStatusListener.printerStatus(1, "打印机错误");
            }

            @Override
            public void onSearchFinished(int i) {
                if (PrinterEpsonL8058.this.printer == null && iPrinterStatusListener != null) {
                    LoggerUtil.m1182e(PrinterEpsonL8058.TAG, "onSearchFinished null printer: ");
                    iPrinterStatusListener.printerStatus(1, "未发现打印机");
                }
                if (PrinterEpsonL8058.this.printer != null) {
                    LoggerUtil.m1182e(PrinterEpsonL8058.TAG, "onSearchFinished SerialNo: " + PrinterEpsonL8058.this.printer.getSerialNo());
                    EPSetting.instance().setSelPageAttri(new PageAttribute(0, 0, 4));
                    EPSetting.instance().setPrintDirection(0);
                    EPSetting.instance().setDuplexPrint(false);
                    EPSetting.instance().setPaperSource(128);
                    EPSetting.instance().setBorderless(true);
                    iPrinterStatusListener.printerStatus(3, "");
                }
            }
        });
        EPrintManager.instance().findPrinter(20);
    }

    @Override
    public void print(final String str, int i, boolean z) {
        if (this.printer == null) {
            return;
        }
        LoggerUtil.m1181d(TAG, "print: " + str + " " + i + " " + z);
        EPSetting.instance().setTotalPages(i);
        EPSetting.instance().setColorMode(z ? 1 : 0);
        EPrintManager.instance().addPrintListener(new IPrintListener() {
            @Override
            public void onPrintBegin() {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onPrintBegin: ");
                PrinterEpsonL8058.this.isPrinting = true;
                System.gc();
            }

            @Override
            public String onPageBegin(int i2) {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onPageBegin: " + i2 + " " + str);
                System.gc();
                return str;
            }

            @Override
            public boolean onPageFinished(int i2) {
                Log.d(PrinterEpsonL8058.TAG, "onPageFinished: " + i2);
                return i2 <= EPSetting.instance().getTotalPages();
            }

            @Override
            public void onPrintFinished(int i2) {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onPrintFinished: " + i2);
                PrinterEpsonL8058.this.isPrinting = false;
                System.gc();
                switch (i2) {
                    case 131073:
                        LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onPrintFinished PrintSuccess: ");
                        break;
                    case 131074:
                    default:
                        if (PrinterEpsonL8058.this.listener != null) {
                            PrinterEpsonL8058.this.listener.printerStatus(8, App.getInstance().getString(C1910R.string.printer_error));
                        }
                        break;
                    case 131075:
                        if (PrinterEpsonL8058.this.listener != null) {
                            PrinterEpsonL8058.this.listener.printerStatus(8, "打印失败，用户取消打印");
                        }
                        break;
                    case 131076:
                        if (PrinterEpsonL8058.this.listener != null) {
                            PrinterEpsonL8058.this.listener.printerStatus(8, "打印失败，打印机暂停按钮");
                        }
                        break;
                }
            }

            @Override
            public void onPrintPause(int i2, int i3, EpsStatus epsStatus) {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, " onPrintPause " + i3 + " " + epsStatus.toString());
                PrinterEpsonL8058.this.isPrinting = false;
                if (PrinterEpsonL8058.this.listener != null) {
                    PrinterEpsonL8058.this.listener.printerStatus(8, App.getInstance().getString(C1910R.string.printer_error));
                }
            }

            @Override
            public void onPrintResume() {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, " onPrintResume: ");
                PrinterEpsonL8058.this.isPrinting = true;
            }

            @Override
            public void onPrintAutoContinue() {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onPrintAutoContinue: ");
                PrinterEpsonL8058.this.isPrinting = true;
            }

            @Override
            public void onCleaningTime(int i2) {
                LoggerUtil.m1181d(PrinterEpsonL8058.TAG, "onCleaningTime: " + i2);
            }
        });
        EPrintManager.instance().startPrint();
    }

    @Override
    public int getRemainSheets() {
        return SpManager.getInstance().getRemainPrinterSheet();
    }

    @Override
    public boolean isPrinting() {
        return this.isPrinting;
    }

    @Override
    public String getPrinterSerial() {
        EpsPrinter epsPrinter = this.printer;
        if (epsPrinter != null) {
            return epsPrinter.getSerialNo();
        }
        return null;
    }
}
