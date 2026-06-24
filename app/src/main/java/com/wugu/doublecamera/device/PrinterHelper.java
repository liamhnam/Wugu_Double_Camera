package com.wugu.doublecamera.device;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import androidx.core.content.FileProvider;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.hiti.usb.jni.JniData;
import com.hiti.usb.printer.PrintPara;
import com.hiti.usb.printer.PrinterJob;
import com.hiti.usb.printer.PrinterStatus;
import com.hiti.usb.service.Action;
import com.hiti.usb.service.ServiceConnector;
import com.p020hp.open.printsdk.HpPrintFile;
import com.p020hp.open.printsdk.HpPrintRequest;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.SdkViewModel;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.p020hp.open.printsdk.options.SupportedOptions;
import com.p020hp.open.printsdk.state.printer.HpPrinterState;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.bean.PrintTask;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.printer.APrinter;
import com.wugu.doublecamera.device.printer.PrinterBro;
import com.wugu.doublecamera.device.printer.PrinterCiTiZenCY;
import com.wugu.doublecamera.device.printer.PrinterDNP;
import com.wugu.doublecamera.device.printer.PrinterEpsonL8058;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.listener.IPrinterStatusListener;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import java.io.File;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class PrinterHelper {
    private final String TAG;
    private int hitiJobId;
    private ServiceConnector hitiServiceConnector;
    private HpPrinter hpPrinter;
    private SdkViewModel hpPrinterVM;
    private int lastRemainSheets;
    private IIntListener listener;
    private Thread printTaskThread;
    private APrinter printer;
    private String printerColorParam;
    private final int printerModel;
    private int printerStatusCode;
    private String printerStatusDescription;
    private final BlockingQueue<PrintTask> taskQueue;

    private static class InstanceHolder {
        private static final PrinterHelper holder = new PrinterHelper();

        private InstanceHolder() {
        }
    }

    public static PrinterHelper getInstance() {
        return InstanceHolder.holder;
    }

    private PrinterHelper() {
        this.TAG = "PrinterHelper";
        this.printerModel = SpManager.getInstance().getPrinterModel();
        this.taskQueue = new LinkedBlockingQueue();
    }

    public void initHitiPrinterService(ServiceConnector serviceConnector) {
        this.hitiServiceConnector = serviceConnector;
        this.hitiJobId = 101;
    }

    private void initHitiPrinter() {
        PrinterStatus printerStatus;
        if (this.hitiServiceConnector == null) {
            LoggerUtil.m1182e("PrinterHelper", "serviceConnector == null");
            setPrinterStatusCode(8, "初始化打印机失败");
            return;
        }
        int i = this.hitiJobId;
        this.hitiJobId = i + 1;
        PrinterJob printerJob = new PrinterJob(i, Action.USB_CHECK_PRINTER_STATUS);
        this.hitiServiceConnector.m_strTablesRoot = "";
        this.hitiServiceConnector.doService(printerJob);
        if (printerJob.retData instanceof PrinterStatus) {
            printerStatus = (PrinterStatus) printerJob.retData;
            LoggerUtil.m1181d("PrinterHelper", "getPrinterInfo:" + printerStatus.statusValue);
        } else {
            printerStatus = null;
        }
        if (printerStatus == null) {
            setPrinterStatusCode(1, "打印机未连接");
            LoggerUtil.m1182e("PrinterHelper", "initHitiPrinter not device status = null");
        } else if (printerStatus.statusValue == 0) {
            setPrinterStatusCode(3, "");
        } else if (printerStatus.statusValue == 17) {
            setPrinterStatusCode(1, "打印机未连接");
        } else {
            setPrinterStatusCode(8, printerStatus.statusDescription);
        }
    }

    private void hitiPrint(String str, int i, boolean z) {
        LoggerUtil.m1181d("PrinterHelper", "hitiPrint: " + str + ", " + i + ", " + z);
        if (this.hitiServiceConnector == null) {
            LoggerUtil.m1182e("PrinterHelper", "hitiServiceConnector == null");
            return;
        }
        Bitmap hitiPrintBitmap = BitmapUtil.getHitiPrintBitmap(str);
        PrintPara printPhotoPara = PrintPara.getPrintPhotoPara(hitiPrintBitmap, (short) i, (short) 0, (short) 1, z ? PrintPara.PaperSize.PAPER_SIZE_6X4_SPLIT_2UP : PrintPara.PaperSize.PAPER_SIZE_6X4_PHOTO, "");
        int i2 = this.hitiJobId;
        this.hitiJobId = i2 + 1;
        this.hitiServiceConnector.doService(new PrinterJob(i2, Action.USB_PRINT_PHOTOS).setJobPara(printPhotoPara));
        if (hitiPrintBitmap == null || hitiPrintBitmap.isRecycled()) {
            return;
        }
        hitiPrintBitmap.recycle();
    }

    private int getHitiPrinterSheets() {
        int i = 0;
        if (this.hitiServiceConnector == null) {
            return 0;
        }
        int i2 = this.hitiJobId;
        this.hitiJobId = i2 + 1;
        PrinterJob printerJob = new PrinterJob(i2, Action.USB_DEVICE_RIBBON_INFO);
        this.hitiServiceConnector.m_strTablesRoot = "";
        this.hitiServiceConnector.doService(printerJob);
        if (printerJob.retData instanceof JniData.IntArray) {
            int i3 = 0;
            while (i < ((JniData.IntArray) printerJob.retData).getSize()) {
                if (i == 1) {
                    i3 = ((JniData.IntArray) printerJob.retData).get(i);
                }
                i++;
            }
            i = i3;
        }
        LoggerUtil.m1181d("PrinterHelper", "getHitiPrinterSheets:" + i);
        return i;
    }

    private void initHpPrinter() {
        SdkViewModel sdkViewModel = this.hpPrinterVM;
        if (sdkViewModel != null) {
            sdkViewModel.onCleared();
        }
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m868x999dd624();
            }
        }, 2000L);
    }

    void m868x999dd624() {
        LoggerUtil.m1181d("PrinterHelper", "initHpPrinter");
        if (this.hpPrinterVM == null) {
            this.hpPrinterVM = (SdkViewModel) new ViewModelProvider(new ViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(App.getInstance())).get(SdkViewModel.class);
        }
        this.hpPrinter = null;
        this.hpPrinterVM.printers().observeForever(new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m866x13c50366((List) obj);
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m867xd6b16cc5();
            }
        }, 5000L);
    }

    void m866x13c50366(List list) {
        if (list == null || list.isEmpty()) {
            this.hpPrinter = null;
            return;
        }
        HpPrinter hpPrinter = (HpPrinter) list.get(0);
        this.hpPrinter = hpPrinter;
        String string = hpPrinter.getStatus().toString();
        LoggerUtil.m1181d("PrinterHelper", "hp printer status " + string);
        if (string.contains("Stopped(reportReasons") && string.contains("errorReasons:")) {
            int iIndexOf = string.indexOf("errorReasons:");
            String strSubstring = string.substring(iIndexOf + 13, string.indexOf("],", iIndexOf) + 1);
            LoggerUtil.m1182e("PrinterHelper", "hp printer error " + strSubstring);
            setPrinterStatusCode(8, strSubstring);
        } else {
            setPrinterStatusCode(3, "");
        }
        this.hpPrinterVM.stopDiscoverPrinters();
    }

    void m867xd6b16cc5() {
        Log.d("PrinterHelper", "stopDiscoverPrinters");
        this.hpPrinterVM.stopDiscoverPrinters();
        if (this.hpPrinter == null) {
            LoggerUtil.m1182e("PrinterHelper", "hp printer null ");
            setPrinterStatusCode(1, "打印机未连接");
        }
    }

    private void hpPrint(final String str, final int i) {
        HpPrinter hpPrinter;
        LoggerUtil.m1181d("PrinterHelper", "hpPrint: " + str + ", " + i);
        SdkViewModel sdkViewModel = this.hpPrinterVM;
        if (sdkViewModel == null || (hpPrinter = this.hpPrinter) == null) {
            LoggerUtil.m1182e("PrinterHelper", "hpPrinterVM == null ");
            setPrinterStatusCode(1, "打印机未连接");
        } else {
            sdkViewModel.refreshPrinter(hpPrinter, new Function1() {
                @Override
                public final Object invoke(Object obj) {
                    return this.f$0.m1597lambda$hpPrint$3$comwugudoublecameradevicePrinterHelper(str, i, (HpPrinter) obj);
                }
            });
        }
    }

    Unit m1597lambda$hpPrint$3$comwugudoublecameradevicePrinterHelper(String str, int i, HpPrinter hpPrinter) {
        if (hpPrinter == null) {
            setPrinterStatusCode(1, "打印机未连接");
            LoggerUtil.m1181d("PrinterHelper", "startHpPrint null");
            return null;
        }
        if ((hpPrinter.getStatus() instanceof HpPrinterState.Idle) || (hpPrinter.getStatus() instanceof HpPrinterState.Processing)) {
            File file = new File(str);
            if (!file.exists()) {
                LoggerUtil.m1182e("PrinterHelper", "hpPrint file not exist");
                return null;
            }
            Uri uriForFile = FileProvider.getUriForFile(App.getInstance(), App.getInstance().getPackageName() + ".provider", file);
            SupportedOptions supportedOptions = hpPrinter.getSupportedOptions(new HpPrintFile(uriForFile));
            PrintOptions printOptions = new PrintOptions(supportedOptions);
            printOptions.setColor(supportedOptions.getColorTypes().get(0));
            printOptions.setPaperType(supportedOptions.getPaperType().get(0));
            printOptions.setMediaSize(supportedOptions.getMediaSizes().get(1));
            printOptions.setCopies(Integer.valueOf(i));
            this.hpPrinterVM.print(new HpPrintRequest(hpPrinter, new HpPrintFile(uriForFile, false), printOptions));
        } else {
            setPrinterStatusCode(1, "打印机状态错误 " + hpPrinter.getStatus());
        }
        return null;
    }

    public void init(IIntListener iIntListener) {
        this.listener = iIntListener;
        LoggerUtil.m1181d("PrinterHelper", "init printerModel: " + this.printerModel);
        int i = this.printerModel;
        if (i == 1) {
            this.printer = new PrinterDNP();
        } else if (i == 3) {
            this.printer = new PrinterBro();
        } else if (i == 4) {
            this.printer = new PrinterCiTiZenCY();
        } else if (i == 5) {
            this.printer = new PrinterEpsonL8058();
        } else if (i == 6) {
            initHpPrinter();
        } else if (i == 7) {
            initHitiPrinter();
        }
        APrinter aPrinter = this.printer;
        if (aPrinter != null) {
            aPrinter.init(new IPrinterStatusListener() {
                @Override
                public final void printerStatus(int i2, String str) {
                    this.f$0.m1598lambda$init$4$comwugudoublecameradevicePrinterHelper(i2, str);
                }
            });
        }
        startPrintTaskThread();
    }

    void m1598lambda$init$4$comwugudoublecameradevicePrinterHelper(int i, String str) {
        LoggerUtil.m1181d("PrinterHelper", "init printer code: " + this.printerStatusCode + " " + i + " msg: " + str);
        setPrinterStatusCode(i, str);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void setPrinterStatusCode(int r3, java.lang.String r4) {
        /*
            r2 = this;
            r2.printerStatusDescription = r4
            int r0 = r2.printerStatusCode
            if (r0 != r3) goto La
            boolean r0 = com.wugu.doublecamera.App.mIsIdle
            if (r0 != 0) goto L34
        La:
            r2.printerStatusCode = r3
            r0 = 1
            java.lang.String r1 = "printer"
            if (r3 == r0) goto L26
            r0 = 2
            if (r3 == r0) goto L26
            r0 = 3
            if (r3 == r0) goto L1c
            r0 = 8
            if (r3 == r0) goto L26
            goto L2d
        L1c:
            com.wugu.doublecamera.network.MqttHelper r4 = com.wugu.doublecamera.network.MqttHelper.getInstance()
            java.lang.String r0 = ""
            r4.mqttPublish(r1, r0)
            goto L2d
        L26:
            com.wugu.doublecamera.network.MqttHelper r0 = com.wugu.doublecamera.network.MqttHelper.getInstance()
            r0.mqttPublish(r1, r4)
        L2d:
            com.wugu.doublecamera.listener.IIntListener r4 = r2.listener
            if (r4 == 0) goto L34
            r4.setValue(r3)
        L34:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wugu.doublecamera.device.PrinterHelper.setPrinterStatusCode(int, java.lang.String):void");
    }

    public void sendPrinterStatus() {
        int i = this.printerStatusCode;
        if (i != 1 && i != 2) {
            if (i == 3) {
                MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_PRINTER_ERROR, "");
                return;
            } else if (i != 8) {
                return;
            }
        }
        MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_PRINTER_ERROR, this.printerStatusDescription);
    }

    private void startPrintTaskThread() {
        Thread thread = this.printTaskThread;
        if (thread == null || !thread.isAlive()) {
            LoggerUtil.m1181d("PrinterHelper", "startPrintTaskThread");
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m871x392851af();
                }
            });
            this.printTaskThread = thread2;
            thread2.start();
        }
    }

    void m871x392851af() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doPrintTask(this.taskQueue.take());
            } catch (InterruptedException unused) {
                LoggerUtil.m1182e("PrinterHelper", "打印工作线程被中断");
                Thread.currentThread().interrupt();
                return;
            } catch (Exception e) {
                LoggerUtil.m1182e("PrinterHelper", "处理打印任务时发生错误 " + e);
            }
            LoggerUtil.m1181d("PrinterHelper", "打印任务完成 remainSheets: " + getPrinterRemainSheet() + " taskQueue.size: " + this.taskQueue.size());
            if (this.taskQueue.size() == 0) {
                SoundHelper.getInstance().playSoundEffect(9);
            }
        }
    }

    private void doPrintTask(PrintTask printTask) {
        this.lastRemainSheets = getPrinterRemainSheet();
        LoggerUtil.m1181d("PrinterHelper", "获取打印任务 " + printTask.filePath + " " + printTask.count + " " + printTask.arg1 + ", remainSheets: " + this.lastRemainSheets);
        long printTime = ((long) (getPrintTime() * printTask.count)) * 1000;
        APrinter aPrinter = this.printer;
        if (aPrinter != null) {
            aPrinter.updateColorParam(this.printerColorParam);
            ThreadClock.sleep(1000L);
            this.printer.print(printTask.filePath, printTask.count, printTask.arg1);
            LoggerUtil.m1181d("PrinterHelper", "print sleep " + printTime);
            ThreadClock.sleep(printTime);
            int i = this.printerModel;
            if (i == 1 || i == 4 || i == 7) {
                checkPrintResult(printTask);
            }
            this.printer.printFinish();
            return;
        }
        int i2 = this.printerModel;
        if (i2 == 7) {
            hitiPrint(printTask.filePath, printTask.count, printTask.arg1);
            ThreadClock.sleep(printTime);
        } else if (i2 == 6) {
            hpPrint(printTask.filePath, printTask.count);
            ThreadClock.sleep(printTime);
        }
    }

    private void checkPrintResult(PrintTask printTask) {
        int i;
        int printerRemainSheet = getPrinterRemainSheet();
        LoggerUtil.m1181d("PrinterHelper", "打印结束，剩余张数 " + printerRemainSheet);
        if (printerRemainSheet <= 0 || (i = this.lastRemainSheets - printerRemainSheet) <= 0 || i >= printTask.count) {
            return;
        }
        int i2 = printTask.count - i;
        LoggerUtil.m1181d("PrinterHelper", "打印数量不够，补上 " + i2);
        this.printer.print(printTask.filePath, i2, printTask.arg1);
        ThreadClock.sleep(((long) (getPrintTime() * i2)) * 1000);
        this.printer.printFinish();
    }

    public void addPrintTask(String str, int i, boolean z) {
        LoggerUtil.m1181d("PrinterHelper", "addPrintTask filePath: " + str + " " + i + " " + z);
        try {
            this.taskQueue.offer(new PrintTask(str, i, z));
        } catch (Exception e) {
            LoggerUtil.m1182e("PrinterHelper", "addPrintTask error:" + e.getMessage());
        }
    }

    public int getPrinterRemainSheet() {
        APrinter aPrinter = this.printer;
        if (aPrinter != null) {
            return aPrinter.getRemainSheets();
        }
        int i = this.printerModel;
        if (i == 6) {
            return SpManager.getInstance().getRemainPrinterSheet();
        }
        if (i == 7) {
            return getHitiPrinterSheets();
        }
        return 0;
    }

    public int getPrintTime() {
        APrinter aPrinter = this.printer;
        if (aPrinter != null) {
            return aPrinter.getPrintTime();
        }
        int i = this.printerModel;
        return (i != 6 && i == 7) ? 18 : 30;
    }

    public void setPrinterColorParam(String str) {
        this.printerColorParam = str;
    }

    public void refreshPrinter(boolean z) {
        SdkViewModel sdkViewModel;
        boolean z2 = true;
        if (this.printerModel != 1 && !z) {
            z2 = false;
        }
        LoggerUtil.m1181d("PrinterHelper", "refreshPrinter " + z2 + " " + this.printerModel);
        if (z2) {
            APrinter aPrinter = this.printer;
            if (aPrinter != null) {
                aPrinter.init(new IPrinterStatusListener() {
                    @Override
                    public final void printerStatus(int i, String str) {
                        this.f$0.m869x68892a69(i, str);
                    }
                });
                return;
            }
            int i = this.printerModel;
            if (i == 7) {
                initHitiPrinter();
            } else {
                if (i != 6 || (sdkViewModel = this.hpPrinterVM) == null) {
                    return;
                }
                sdkViewModel.refreshPrinterList();
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m870x2b7593c8();
                    }
                }, 3000L);
            }
        }
    }

    void m869x68892a69(int i, String str) {
        LoggerUtil.m1181d("PrinterHelper", "refreshPrinter printer code: " + this.printerStatusCode + " " + i + " msg: " + str);
        setPrinterStatusCode(i, str);
    }

    void m870x2b7593c8() {
        this.hpPrinterVM.stopDiscoverPrinters();
        if (this.hpPrinter == null) {
            LoggerUtil.m1182e("PrinterHelper", "hp printer null ");
            setPrinterStatusCode(1, "打印机未连接");
        }
    }

    public boolean isPrinterReady() {
        return this.printerStatusCode == 3;
    }

    public boolean isPrintPdf() {
        return this.printerModel == 6;
    }

    public String getPrinterStatusDescription() {
        return this.printerStatusDescription;
    }
}
