package com.wugu.doublecamera.device.printer;

import com.printer.sdk.OnLoadIccListen;
import com.printer.sdk.OnStartPrnListen;
import com.printer.sdk.OnStartSvrListen;
import com.printer.sdk.PrintManage;
import com.printer.sdk.PrintMsg;
import com.printer.sdk.PrintUserOrder;
import com.printer.sdk.PrintUserTask;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.listener.IPrinterStatusListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;

public class PrinterCiTiZenCY extends APrinter {
    private final String TAG = "PrinterCiTiZenCY";
    private PrintManage printManage;

    static int lambda$init$1(PrintMsg printMsg) {
        return 0;
    }

    @Override
    public int getPrintTime() {
        return 20;
    }

    @Override
    public String getPrinterSerial() {
        return null;
    }

    @Override
    public void updateColorParam(Object obj) {
    }

    @Override
    public void init(final IPrinterStatusListener iPrinterStatusListener) {
        if (this.printManage != null) {
            return;
        }
        PrintManage printManageInstance = PrintManage.instance(App.getInstance());
        this.printManage = printManageInstance;
        printManageInstance.SetPrinterDebug(false);
        this.printManage.StartSvr(new OnStartSvrListen() {
            @Override
            public final int Result(PrintMsg printMsg) {
                return this.f$0.m879x2fd4b561(iPrinterStatusListener, printMsg);
            }
        });
        if (new File("/sdcard/CPreview/ICC/User/uci-cy-android.dat").exists()) {
            return;
        }
        this.printManage.LoadIccFile(new OnLoadIccListen() {
            @Override
            public final int Result(PrintMsg printMsg) {
                return PrinterCiTiZenCY.lambda$init$1(printMsg);
            }
        });
    }

    int m879x2fd4b561(IPrinterStatusListener iPrinterStatusListener, PrintMsg printMsg) {
        int ret = printMsg.getRet();
        if (ret == 1002) {
            LoggerUtil.m1181d("PrinterCiTiZenCY", "citizen 服务启动成功");
            if (iPrinterStatusListener != null) {
                iPrinterStatusListener.printerStatus(3, "");
            }
            int remainSheets = getRemainSheets();
            if (App.mCurrentPrintSheets != remainSheets) {
                updatePrinterPaper(remainSheets, true);
            }
            this.printManage.StopSvr(null);
            return 0;
        }
        LoggerUtil.m1182e("PrinterCiTiZenCY", "citizen 服务启动失败 " + ret);
        if (iPrinterStatusListener == null) {
            return 0;
        }
        iPrinterStatusListener.printerStatus(1, "未发现打印机");
        return 0;
    }

    public void updatePrinterPaper(final int i, final boolean z) {
        LoggerUtil.m1181d("PrinterCiTiZenCY", "remaining sheets: " + i);
        HttpManager.getInstance().mHttpApi.updatePrinterPaper(i, -1).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                App.mCurrentPrintSheets = i;
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                if (z) {
                    ThreadClock.sleep(10000L);
                    PrinterCiTiZenCY.this.updatePrinterPaper(i, false);
                }
            }
        });
    }

    @Override
    public void print(final String str, final int i, final boolean z) {
        PrintManage printManage = this.printManage;
        if (printManage == null) {
            return;
        }
        printManage.StartSvr(new OnStartSvrListen() {
            @Override
            public final int Result(PrintMsg printMsg) {
                return this.f$0.m881xa93d37e7(str, i, z, printMsg);
            }
        });
    }

    int m881xa93d37e7(String str, int i, boolean z, PrintMsg printMsg) {
        int ret = printMsg.getRet();
        if (ret == 1002) {
            LoggerUtil.m1181d("PrinterCiTiZenCY", "服务启动成功 print " + str);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PrintUserTask(str, i));
            final PrintUserOrder printUserOrder = new PrintUserOrder();
            printUserOrder.setTask(arrayList);
            printUserOrder.setPrns("ONE");
            printUserOrder.setBdpi("300×300");
            printUserOrder.setMode("FULL");
            printUserOrder.setOrderid(System.currentTimeMillis());
            if (z) {
                printUserOrder.setCutType("2寸裁切");
            }
            this.printManage.StartPrn(new OnStartPrnListen() {
                @Override
                public final int Result(PrintMsg printMsg2) {
                    return this.f$0.m880x7fe8e2a6(printUserOrder, printMsg2);
                }
            });
            return 0;
        }
        LoggerUtil.m1182e("PrinterCiTiZenCY", "print 服务启动失败 " + ret);
        return 0;
    }

    int m880x7fe8e2a6(PrintUserOrder printUserOrder, PrintMsg printMsg) {
        LoggerUtil.m1181d("PrinterCiTiZenCY", "StartPrn " + printMsg.getRet());
        if (printMsg.getRet() == 1006) {
            this.printManage.AddOrderDefIcc(printUserOrder, null, 0);
        }
        return 0;
    }

    @Override
    public int getRemainSheets() {
        PrintManage printManage = this.printManage;
        if (printManage == null) {
            return 0;
        }
        return printManage.getPrintRemainQuantityInt();
    }

    @Override
    public boolean isPrinting() {
        PrintManage printManage = this.printManage;
        return printManage != null && printManage.getPrintStatusInt() == 1;
    }

    @Override
    public void printFinish() {
        stopPrinter();
    }

    private void stopPrinter() {
        PrintManage printManage = this.printManage;
        if (printManage == null) {
            return;
        }
        printManage.StopPrn(null);
        this.printManage.StopSvr(null);
    }
}
