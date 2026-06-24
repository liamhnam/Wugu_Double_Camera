package com.hiti.usb.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.hiti.usb.jni.JniData;
import com.hiti.usb.jni.UsbCommand;
import com.hiti.usb.printer.PrintPara;
import com.hiti.usb.printer.PrinterJob;
import com.hiti.usb.printer.PrinterStatus;
import com.hiti.usb.service.network.FirmwareUtility;
import com.hiti.usb.service.network.INet;
import com.hiti.usb.service.usbPrinter.UsbHost;
import com.hiti.usb.service.usbPrinter.UsbPrinter;
import com.hiti.usb.utility.FileUtility;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import p000a.p001a.p002a.p005c.AbstractRunnableC0008a;
import p000a.p001a.p002a.p005c.RunnableC0009b;

public class PrinterService extends Service {
    private static final boolean localLOG = true;
    private static Status printerServiceStatus = Status.destroy;
    private static final String tag = "PrinterService";
    private PrintPhotosManager mGetThumbnailManager;
    private PrintPhotosManager mPrintPhotosManager;
    public String m_strTablesRoot;
    public String str1;
    public String str2;
    RunnableC0009b taskManager;
    private AtomicBoolean isFirmwareUpdate = new AtomicBoolean(false);
    final String networkExeTime = "02:00:00";
    UsbPrinter usbPrinter = null;
    NetworkThread mNetworkThread = null;
    INet.IThumb mListener = null;

    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public PrinterService getService() {
            return PrinterService.this;
        }
    }

    class PrintPhotosManager extends AbstractRunnableC0008a {
        private List<PrinterJob> printJobList = Collections.synchronizedList(new LinkedList());
        private List<PrinterJob> printJobListResult = Collections.synchronizedList(new LinkedList());
        private boolean hasNewJob = false;

        PrintPhotosManager() {
        }

        void add(PrinterJob printerJob) {
            FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.add(job) \n");
            this.printJobList.add(printerJob);
            synchronized (this) {
                FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.add(job) synchronized hasNewJob = true\n");
                this.hasNewJob = true;
                Log.v(PrinterService.tag, "PrintPhotosThread: prepare to notify print thread to go");
                notify();
                FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.add(job) synchronized hasNewJob = true after notify\n");
            }
        }

        boolean getResult(PrinterJob printerJob) {
            while (!this.printJobListResult.remove(printerJob)) {
                try {
                    if (printerJob.action == Action.USB_PRINT_PHOTOS || printerJob.action == Action.USB_PRINT_CARD) {
                        Thread.sleep(3000L);
                    } else if (printerJob.action == Action.USB_GET_OBJECT_DATA || printerJob.action == Action.USB_GET_OBJECT_HANDLE_ID) {
                        Thread.sleep(200L);
                    } else {
                        Thread.sleep(500L);
                    }
                } catch (InterruptedException unused) {
                    if (printerJob.action.name() != "USB_PRINT_PHOTOS" && printerJob.action.name() != "USB_PRINT_CARD") {
                        return false;
                    }
                    FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.getResult(job) false \n");
                    return false;
                }
            }
            if (printerJob.action.name() != "USB_PRINT_PHOTOS" && printerJob.action.name() != "USB_PRINT_CARD") {
                return true;
            }
            FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.getResult(job) true \n");
            return true;
        }

        boolean isPrintJobClear() {
            if (!this.printJobList.isEmpty()) {
                return false;
            }
            JniData jniDataCallJniUsbCommand = PrinterService.this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_JOBS_IN_QUEUE, null);
            return jniDataCallJniUsbCommand == null || ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand.getErrorCode()) || ((JniData.IntArray) jniDataCallJniUsbCommand.getRetData()).get(0) <= 0;
        }

        @Override
        public void run() {
            String str;
            String str2;
            try {
                Log.v(PrinterService.tag, "PrintPhotosThread: start.");
                synchronized (this) {
                    FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() synchronized wait\n");
                    wait();
                }
                while (!Thread.interrupted()) {
                    FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() !Thread.interrupted() \n");
                    while (true) {
                        boolean z = false;
                        if (this.printJobList.isEmpty()) {
                            break;
                        }
                        PrinterJob printerJobRemove = this.printJobList.remove(0);
                        FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() !printJobList.isEmpty() \n");
                        while (true) {
                            PrinterService.this.usbPrinter.m_strTablesRoot = PrinterService.this.m_strTablesRoot;
                            JniData jniDataCallJniUsbCommand = PrinterService.this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_JOBS_IN_QUEUE, null);
                            if (jniDataCallJniUsbCommand == null) {
                                printerJobRemove.errCode = ErrorCode.ERR_CODE_NO_RETURN_RESULT;
                                str = PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log";
                                str2 = "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() ERR_CODE_NO_RETURN_RESULT\n";
                                break;
                            } else if (ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand.getErrorCode())) {
                                String str3 = PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log";
                                str2 = "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() ret.getErrorCode() " + jniDataCallJniUsbCommand.getErrorCode().description + " \n";
                                str = str3;
                                break;
                            } else {
                                FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() ((IntArray)ret.getRetData()).get(0)=" + ((JniData.IntArray) jniDataCallJniUsbCommand.getRetData()).get(0) + " \n");
                                if (((JniData.IntArray) jniDataCallJniUsbCommand.getRetData()).get(0) < 2) {
                                    str = PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log";
                                    str2 = "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() ((IntArray)ret.getRetData()).get(0) < 2 less then 2 jobs in queue \n";
                                    z = true;
                                    break;
                                }
                                Thread.sleep(3000L);
                            }
                        }
                        FileUtility.WriteFile(str, str2);
                        if (z) {
                            printerJobRemove = PrinterService.this.usbPrinter.callJniUsbCommand(printerJobRemove);
                        }
                        this.printJobListResult.add(printerJobRemove);
                    }
                    synchronized (this) {
                        FileUtility.WriteFile(PrinterService.this.m_strTablesRoot + "/debug_" + PrinterService.this.str1 + "_log", "[" + PrinterService.this.str2 + "] printerService.aar->PrinterService->service()->mPrintPhotosManager.run() synchronized Check if no more job then idle and wait \n");
                        if (this.hasNewJob) {
                            Log.v(PrinterService.tag, "PrintPhotosThread: still has job..");
                            this.hasNewJob = false;
                        } else {
                            Log.v(PrinterService.tag, "PrintPhotosThread: no more job, go to sleep");
                            wait();
                            Log.v(PrinterService.tag, "PrintPhotosThread: new print job notify.");
                        }
                    }
                }
            } catch (InterruptedException unused) {
            }
        }
    }

    private class ServiceHandler extends Handler {
        private ServiceHandler() {
        }

        @Override
        public void handleMessage(Message message) {
            if (message.what != 10) {
                return;
            }
            Status unused = PrinterService.printerServiceStatus = Status.stop;
            PrinterService.this.callbackReceiver(new PrinterJob(0, Action.SERVICE_STOP_SERVICE));
            PrinterService.this.stopSelf();
        }
    }

    public enum Status {
        init,
        running,
        stopping,
        stop,
        destroy
    }

    public void callbackReceiver(PrinterJob printerJob) {
        if (printerJob == null || printerJob.errCode == null) {
            Log.e(tag, "callbackReceiver: return Data is null - action=" + printerJob.action.name());
            printerJob.errCode = ErrorCode.ERR_CODE_NO_RETURN_RESULT;
        }
        Log.e(tag, "callbackReceiver action=" + printerJob.action.name());
    }

    public static Status getPrinterServiceStatus() {
        return printerServiceStatus;
    }

    private void terminate() {
        printerServiceStatus = Status.stopping;
        Log.i(tag, "prepare stop threads in service");
        this.taskManager.m50a();
    }

    private ErrorCode updateFirmware(String str, String str2) {
        Log.v(tag, "updateFirmware(): FwUpdate start");
        try {
            if (str == null || str2 == null) {
                return ErrorCode.ERR_CODE_INVALID_PARAMETER;
            }
            while (true) {
                JniData jniDataCallJniUsbCommand = this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_CHECK_PRINTER_STATUS, null, null);
                if (jniDataCallJniUsbCommand == null || !(((PrinterStatus) jniDataCallJniUsbCommand.getRetData()).statusValue == 2 || ((PrinterStatus) jniDataCallJniUsbCommand.getRetData()).statusValue == 524288)) {
                    break;
                }
                Log.v(tag, "updateFirmware(): printer status is busy or printing , so firmware update delayed.");
                Thread.sleep(5000L);
            }
            this.isFirmwareUpdate.set(true);
            if (!this.mPrintPhotosManager.isPrintJobClear()) {
                this.isFirmwareUpdate.set(false);
                return ErrorCode.ERR_CODE_SERVICE_IS_BUSY;
            }
            String str3 = tag;
            Log.v(str3, "updateFirmware(): GET locker");
            JniData jniDataCallJniUsbCommand2 = this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_FIRMWARE_VERSION, null);
            if (jniDataCallJniUsbCommand2 != null && !ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand2.getErrorCode())) {
                Log.v(str3, "firmware update: oldVer= " + FirmwareUtility.simpleFwFormat((String) jniDataCallJniUsbCommand2.getRetData()) + " ,newVer= " + str + "binFilePath= " + str2);
                jniDataCallJniUsbCommand2 = this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_UPDATE_FIRMWARE, null, str2);
                Log.v(str3, new StringBuilder().append("updateFirmware(): FwUpdate status= ").append(jniDataCallJniUsbCommand2).toString() != null ? Integer.toHexString(jniDataCallJniUsbCommand2.getErrorCode().value) : "not success!");
            }
            Log.v(str3, "updateFirmware(): release locker");
            this.isFirmwareUpdate.set(false);
            return jniDataCallJniUsbCommand2 == null ? ErrorCode.ERR_CODE_NO_RETURN_RESULT : jniDataCallJniUsbCommand2.getErrorCode();
        } catch (InterruptedException unused) {
            Log.v(tag, "updateFirmware(): stop");
            return ErrorCode.ERR_CODE_USB_CLAIM_INTERFACE_FAIL;
        }
    }

    private ErrorCode updateFirmwareP525N(String str, String str2, String str3, String str4) {
        Log.v(tag, "updateFirmware(): FwUpdate start");
        try {
            if (str == null || str2 == null) {
                return ErrorCode.ERR_CODE_INVALID_PARAMETER;
            }
            while (true) {
                JniData jniDataCallJniUsbCommand = this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_CHECK_PRINTER_STATUS, null, null);
                if (jniDataCallJniUsbCommand == null || !(((PrinterStatus) jniDataCallJniUsbCommand.getRetData()).statusValue == 2 || ((PrinterStatus) jniDataCallJniUsbCommand.getRetData()).statusValue == 524288)) {
                    break;
                }
                Log.v(tag, "updateFirmware(): printer status is busy or printing , so firmware update delayed.");
                Thread.sleep(5000L);
            }
            this.isFirmwareUpdate.set(true);
            if (!this.mPrintPhotosManager.isPrintJobClear()) {
                this.isFirmwareUpdate.set(false);
                return ErrorCode.ERR_CODE_SERVICE_IS_BUSY;
            }
            String str5 = tag;
            Log.v(str5, "updateFirmware(): GET locker");
            JniData jniDataCallJniUsbCommand2 = this.usbPrinter.callJniUsbCommand(UsbCommand.Function.HITI_GET_DEVICE_INFO, UsbCommand.SubFunc.HITI_DEVINFO_FIRMWARE_VERSION, null);
            if (jniDataCallJniUsbCommand2 != null && !ErrorCode.HITI_ERROR(jniDataCallJniUsbCommand2.getErrorCode())) {
                Log.v(str5, "firmware update: oldVer= " + FirmwareUtility.simpleFwFormat((String) jniDataCallJniUsbCommand2.getRetData()) + " ,newVer= " + str + "binFilePath= " + str2);
                jniDataCallJniUsbCommand2 = this.usbPrinter.callJniUsbCommand3(UsbCommand.Function.HITI_UPDATE_FIRMWARE_P525N, null, str2, str3, str4);
                Log.v(str5, new StringBuilder().append("updateFirmware(): FwUpdate status= ").append(jniDataCallJniUsbCommand2).toString() != null ? Integer.toHexString(jniDataCallJniUsbCommand2.getErrorCode().value) : "not success!");
            }
            Log.v(str5, "updateFirmware(): release locker");
            this.isFirmwareUpdate.set(false);
            return jniDataCallJniUsbCommand2 == null ? ErrorCode.ERR_CODE_NO_RETURN_RESULT : jniDataCallJniUsbCommand2.getErrorCode();
        } catch (InterruptedException unused) {
            Log.v(tag, "updateFirmware(): stop");
            return ErrorCode.ERR_CODE_USB_CLAIM_INTERFACE_FAIL;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(tag, "onBind");
        return new LocalBinder();
    }

    @Override
    public void onCreate() {
        printerServiceStatus = Status.init;
        Log.v(tag, "onCreate");
        Toast.makeText(this, "Service create: ", 0).show();
        UsbHost.DeviceIdentifier deviceIdentifier = new UsbHost.DeviceIdentifier();
        deviceIdentifier.VedorId = UsbHost.DeviceIdentifier.HITI_VENDOR_ID;
        this.usbPrinter = UsbPrinter.getUsbPrinter(getApplicationContext(), deviceIdentifier);
    }

    @Override
    public void onDestroy() {
        Log.v(tag, "HITI Service stop!!!");
        if (printerServiceStatus != Status.stopping) {
            terminate();
        }
        UsbPrinter.disconnect(this.usbPrinter);
        Toast.makeText(this, "Service onDestroy: ", 0).show();
        printerServiceStatus = Status.destroy;
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        String str = tag;
        Log.v(str, "onStartCommand");
        if (printerServiceStatus == Status.running) {
            return 2;
        }
        Log.v(str, " Service start!!!HITI");
        printerServiceStatus = Status.running;
        RunnableC0009b runnableC0009b = new RunnableC0009b(32, new ServiceHandler());
        this.taskManager = runnableC0009b;
        PrintPhotosManager printPhotosManager = (PrintPhotosManager) new PrintPhotosManager().setInitDelay(1000L);
        this.mPrintPhotosManager = printPhotosManager;
        runnableC0009b.m49a(printPhotosManager);
        RunnableC0009b runnableC0009b2 = this.taskManager;
        PrintPhotosManager printPhotosManager2 = (PrintPhotosManager) new PrintPhotosManager().setInitDelay(200L);
        this.mGetThumbnailManager = printPhotosManager2;
        runnableC0009b2.m49a(printPhotosManager2);
        NetworkThread networkThread = new NetworkThread(this);
        this.mNetworkThread = networkThread;
        this.taskManager.m49a(networkThread.setTimer(2000L, AbstractRunnableC0008a.EXEC_ONCE));
        return 2;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(tag, "onUnbind");
        return false;
    }

    void service(final PrinterJob printerJob) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.str1 = simpleDateFormat.format(date);
        this.str2 = simpleDateFormat2.format(date);
        if (printerJob.action.name() == "USB_PRINT_PHOTOS" || printerJob.action.name() == "USB_PRINT_CARD") {
            FileUtility.WriteFile(((PrintPara) printerJob.inputData).getTableRoot() + "/debug_" + this.str1 + "_log", "[" + this.str2 + "] printerService.aar->PrinterService->service(USB_PRINT_PHOTOS) \n");
            this.m_strTablesRoot = ((PrintPara) printerJob.inputData).getTableRoot();
        }
        INet.IThumb iThumb = this.mListener;
        if (iThumb != null && iThumb.isStop()) {
            if (printerJob.action.name() == "USB_PRINT_PHOTOS" || printerJob.action.name() == "USB_PRINT_CARD") {
                FileUtility.WriteFile(this.m_strTablesRoot + "/debug_" + this.str1 + "_log", "[" + this.str2 + "] printerService.aar->PrinterService->service()->mListener.isStop() \n");
                return;
            }
            return;
        }
        this.usbPrinter.m_strTablesRoot = this.m_strTablesRoot;
        printerJob.retData = null;
        printerJob.errCode = null;
        Log.d("printerService", "service: " + printerJob.action);
        Action action = printerJob.action;
        if (action == Action.USB_PRINT_PHOTOS || action == Action.USB_PRINT_CARD) {
            this.mPrintPhotosManager.add(printerJob);
            this.mPrintPhotosManager.getResult(printerJob);
            return;
        }
        if (action == Action.USB_GET_OBJECT_DATA || action == Action.USB_GET_OBJECT_HANDLE_ID) {
            this.mGetThumbnailManager.add(printerJob);
            this.mGetThumbnailManager.getResult(printerJob);
            return;
        }
        try {
            this.taskManager.m49a(new AbstractRunnableC0008a() {
                @Override
                public void run() {
                    if (PrinterService.this.isFirmwareUpdate.get()) {
                        printerJob.errCode = ErrorCode.ERR_CODE_SERVICE_IS_BUSY;
                    } else {
                        PrinterService printerService = PrinterService.this;
                        UsbPrinter usbPrinter = printerService.usbPrinter;
                        usbPrinter.m_strTablesRoot = printerService.m_strTablesRoot;
                        usbPrinter.callJniUsbCommand(printerJob);
                    }
                }
            }).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
        Log.v(tag, "service() end");
    }

    public void setThumbListener(INet.IThumb iThumb) {
        this.mListener = iThumb;
    }

    public void setUploadListener(INet.IUpload iUpload) {
        this.usbPrinter.setListener(iUpload);
        this.mNetworkThread.setListener(iUpload);
    }

    void stopService() {
        terminate();
    }

    public ErrorCode updateFirmwareToPrinter(String str, String str2) {
        return updateFirmware(str, str2);
    }

    public ErrorCode updateFirmwareToPrinter(String str, String str2, String str3, String str4) {
        return updateFirmwareP525N(str, str2, str3, str4);
    }
}
