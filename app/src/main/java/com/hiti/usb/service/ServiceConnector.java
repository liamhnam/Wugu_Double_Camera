package com.hiti.usb.service;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.hiti.usb.printer.PrintPara;
import com.hiti.usb.printer.PrinterJob;
import com.hiti.usb.service.PrinterService;
import com.hiti.usb.service.network.INet;
import com.hiti.usb.utility.FileUtility;
import com.p020hp.jipp.model.TimeoutPredicate;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceConnector implements ServiceConnection {
    static final String BROADCAST_FILTER = "BROADCAST_FILTER";
    private static volatile ServiceConnector instance = null;
    private static final boolean localLOG = true;
    private static final String tag = "ServiceConnector";
    private Context callerContext;
    private BroadcastReceiver callerReceiver;
    private ServiceConnectorReceiver connectReceiver;
    public String m_strTablesRoot;
    private PrinterService m_Service = null;
    INet.IThumb mThumbListener = null;
    INet.IUpload mUploadListenr = null;

    static class C15941 {
        static final int[] $SwitchMap$com$hiti$usb$service$Action;

        static {
            int[] iArr = new int[Action.values().length];
            $SwitchMap$com$hiti$usb$service$Action = iArr;
            try {
                iArr[Action.SERVICE_STOP_SERVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    private class ServiceConnectorReceiver extends BroadcastReceiver {
        private ServiceConnectorReceiver() {
        }

        ServiceConnectorReceiver(ServiceConnector serviceConnector, C15941 c15941) {
            this();
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (C15941.$SwitchMap$com$hiti$usb$service$Action[((ActionParcel) intent.getParcelableExtra("ActionParcel")).action.ordinal()] == 1) {
                Log.v(ServiceConnector.tag, "Hiti service stopped.");
            } else if (ServiceConnector.this.callerReceiver != null) {
                ServiceConnector.this.callerReceiver.onReceive(context, intent);
            }
        }
    }

    private ServiceConnector(Context context, BroadcastReceiver broadcastReceiver) {
        C15941 c15941 = null;
        this.callerContext = null;
        this.callerReceiver = null;
        this.connectReceiver = null;
        this.callerContext = context.getApplicationContext();
        if (broadcastReceiver != null) {
            this.callerReceiver = broadcastReceiver;
            ServiceConnectorReceiver serviceConnectorReceiver = new ServiceConnectorReceiver(this, c15941);
            this.connectReceiver = serviceConnectorReceiver;
            registerReceiver(serviceConnectorReceiver, this.callerContext);
        }
        Log.v(tag, "ServiceConnector: bindService status= " + this.callerContext.bindService(new Intent(this.callerContext, (Class<?>) PrinterService.class), this, 0));
    }

    public static ServiceConnector register(Context context, BroadcastReceiver broadcastReceiver) {
        if (context == null) {
            throw new IllegalArgumentException("Parameter should not be null");
        }
        if (instance == null) {
            synchronized (ServiceConnector.class) {
                if (instance == null) {
                    instance = new ServiceConnector(context, broadcastReceiver);
                }
            }
        }
        return instance;
    }

    public static void sendBroadcast(Intent intent, Context context) {
        intent.setAction(BROADCAST_FILTER);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public ErrorCode StartService() {
        Intent intent = new Intent(this.callerContext, (Class<?>) PrinterService.class);
        ComponentName componentNameStartService = this.callerContext.startService(intent);
        if (componentNameStartService == null) {
            return ErrorCode.ERR_CODE_SERVICE_NOT_START;
        }
        Log.v(tag, "StartService: " + componentNameStartService.toString());
        return (this.m_Service != null || this.callerContext.bindService(intent, this, 0)) ? ErrorCode.ERR_CODE_SUCCESS : ErrorCode.ERR_CODE_SERVICE_NOT_BIND;
    }

    public ErrorCode StopService() {
        if (this.m_Service == null) {
            return ErrorCode.ERR_CODE_SERVICE_NOT_START;
        }
        if (PrinterService.getPrinterServiceStatus() == PrinterService.Status.stopping || PrinterService.getPrinterServiceStatus() == PrinterService.Status.stop) {
            return ErrorCode.ERR_CODE_SERVICE_IS_STOPPING;
        }
        this.m_Service.stopService();
        return ErrorCode.ERR_CODE_SUCCESS;
    }

    public ErrorCode doService(PrinterJob printerJob) {
        ErrorCode errorCode;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String str = simpleDateFormat.format(date);
        String str2 = simpleDateFormat2.format(date);
        if (printerJob.action.name() == "USB_PRINT_PHOTOS" || printerJob.action.name() == "USB_PRINT_CARD") {
            FileUtility.WriteFile(((PrintPara) printerJob.inputData).getTableRoot() + "/debug_" + str + "_log", "\n\n[" + str2 + "] printerService.aar->ServiceConnector->doService() USB_PRINT_PHOTOS\n");
        }
        if (printerJob.action.name() == "USB_CHECK_PRINTER_STATUS") {
            FileUtility.WriteFile(this.m_strTablesRoot + "/debug_" + str + "_log", "\n\n[" + str2 + "] USB_CHECK_PRINTER_STATUS printerService.aar->ServiceConnector->doService() USB_CHECK_PRINTER_STATUS\n");
        }
        if (printerJob.action.name() == "USB_COMMAND_RESUME_JOB") {
            FileUtility.WriteFile(this.m_strTablesRoot + "/debug_" + str + "_log", "\n\n[" + str2 + "] USB_COMMAND_RESUME_JOB printerService.aar->ServiceConnector->doService() USB_COMMAND_RESUME_JOB\n");
        }
        if (printerJob == null) {
            throw new NullPointerException("parameter should not null");
        }
        if (this.m_Service == null) {
            errorCode = ErrorCode.ERR_CODE_SERVICE_NOT_START;
        } else {
            if (PrinterService.getPrinterServiceStatus() != PrinterService.Status.stopping && PrinterService.getPrinterServiceStatus() != PrinterService.Status.stop) {
                String str3 = tag;
                Log.v(str3, "service: action: " + printerJob.action.name());
                PrinterService printerService = this.m_Service;
                printerService.m_strTablesRoot = this.m_strTablesRoot;
                printerService.service(printerJob);
                Log.v(str3, "back from service");
                return printerJob.errCode;
            }
            errorCode = ErrorCode.ERR_CODE_SERVICE_IS_STOPPING;
        }
        printerJob.errCode = errorCode;
        return errorCode;
    }

    public String getHitiServiceStatus() {
        PrinterService.Status printerServiceStatus = PrinterService.Status.destroy;
        boolean z = false;
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) this.callerContext.getSystemService(TimeoutPredicate.activity)).getRunningServices(Integer.MAX_VALUE)) {
            if (PrinterService.class.getName().equals(runningServiceInfo.service.getClassName())) {
                Log.v(tag, "get service: " + runningServiceInfo.service.getClassName());
                z = true;
            }
        }
        if (z) {
            if (this.m_Service == null) {
                Log.v(tag, "ServiceConnector: bindService status= " + this.callerContext.bindService(new Intent(this.callerContext, (Class<?>) PrinterService.class), this, 0));
            }
            printerServiceStatus = PrinterService.getPrinterServiceStatus();
        } else {
            Log.v(tag, "system service not found");
            this.m_Service = null;
        }
        return printerServiceStatus.name();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Log.i(tag, "onServiceConnected");
        PrinterService service = ((PrinterService.LocalBinder) iBinder).getService();
        this.m_Service = service;
        INet.IThumb iThumb = this.mThumbListener;
        if (iThumb != null) {
            service.setThumbListener(iThumb);
        }
        INet.IUpload iUpload = this.mUploadListenr;
        if (iUpload != null) {
            this.m_Service.setUploadListener(iUpload);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(tag, "onServiceDisconnected");
        this.m_Service = null;
    }

    void registerReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(broadcastReceiver, new IntentFilter(BROADCAST_FILTER));
    }

    public void setThumbListener(INet.IThumb iThumb) {
        this.mThumbListener = iThumb;
    }

    public void setUploadListener(INet.IUpload iUpload) {
        this.mUploadListenr = iUpload;
    }

    public void unregister() {
        ServiceConnectorReceiver serviceConnectorReceiver = this.connectReceiver;
        if (serviceConnectorReceiver != null) {
            unregisterReceiver(serviceConnectorReceiver, this.callerContext);
        }
        if (this.m_Service != null) {
            this.callerContext.unbindService(this);
        }
        Log.v(tag, "unregister ServiceConnector");
        instance = null;
    }

    void unregisterReceiver(BroadcastReceiver broadcastReceiver, Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(broadcastReceiver);
    }

    public ErrorCode updateFirmware(String str, String str2) {
        PrinterService printerService = this.m_Service;
        return printerService != null ? printerService.updateFirmwareToPrinter(str, str2) : ErrorCode.ERR_CODE_SERVICE_NOT_START;
    }

    public ErrorCode updateFirmwareP525N(String str, String str2, String str3, String str4) {
        PrinterService printerService = this.m_Service;
        return printerService != null ? printerService.updateFirmwareToPrinter(str, str2, str3, str4) : ErrorCode.ERR_CODE_SERVICE_NOT_START;
    }
}
