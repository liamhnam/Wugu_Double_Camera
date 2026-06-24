package com.wugu.doublecamera.device.printer;

import com.brother.sdk.common.Callback;
import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.ConnectorManager;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintOrientation;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.PrintScale;
import com.brother.sdk.common.socket.print.PrintState;
import com.brother.sdk.print.PrintJob;
import com.brother.sdk.print.PrintParameters;
import com.brother.sdk.usb.discovery.UsbConnectorDiscovery;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.listener.IPrinterStatusListener;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PrinterBro extends APrinter {
    private IConnector connector;
    private IPrinterStatusListener listener;
    private Timer printTimeOutTimer;
    private final String TAG = "PrinterBro";
    private final PrintParameters mPrintParameters = new PrintParameters();
    private PrintJob printJob = null;
    private boolean isPrinting = false;

    @Override
    public int getPrintTime() {
        return 28;
    }

    @Override
    public String getPrinterSerial() {
        return null;
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
        this.connector = null;
        final UsbConnectorDiscovery usbConnectorDiscovery = new UsbConnectorDiscovery();
        usbConnectorDiscovery.startDiscover(new ConnectorManager.OnDiscoverConnectorListener() {
            @Override
            public final void onDiscover(ConnectorDescriptor connectorDescriptor) {
                this.f$0.m1599lambda$init$0$comwugudoublecameradeviceprinterPrinterBro(iPrinterStatusListener, usbConnectorDiscovery, connectorDescriptor);
            }
        });
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1600lambda$init$1$comwugudoublecameradeviceprinterPrinterBro(iPrinterStatusListener, usbConnectorDiscovery);
            }
        }, 5000L);
    }

    void m1599lambda$init$0$comwugudoublecameradeviceprinterPrinterBro(IPrinterStatusListener iPrinterStatusListener, UsbConnectorDiscovery usbConnectorDiscovery, ConnectorDescriptor connectorDescriptor) {
        if (connectorDescriptor.support(ConnectorDescriptor.Function.Print) || connectorDescriptor.support(ConnectorDescriptor.Function.Scan)) {
            IConnector iConnectorCreateConnector = connectorDescriptor.createConnector(CountrySpec.China);
            this.connector = iConnectorCreateConnector;
            if (iConnectorCreateConnector != null && iPrinterStatusListener != null) {
                LoggerUtil.m1181d("PrinterBro", "connector device " + this.connector.getDevice().modelName);
                iPrinterStatusListener.printerStatus(3, "");
            }
            usbConnectorDiscovery.stopDiscover();
        }
    }

    void m1600lambda$init$1$comwugudoublecameradeviceprinterPrinterBro(IPrinterStatusListener iPrinterStatusListener, UsbConnectorDiscovery usbConnectorDiscovery) {
        LoggerUtil.m1181d("PrinterBro", "connector = " + this.connector);
        if (this.connector == null && iPrinterStatusListener != null) {
            iPrinterStatusListener.printerStatus(1, "未发现打印机");
        }
        usbConnectorDiscovery.stopDiscover();
    }

    @Override
    public void print(String str, int i, boolean z) {
        LoggerUtil.m1181d("PrinterBro", "picturePath = " + str + " count = " + i + " arg1 = " + z);
        if (this.connector == null) {
            return;
        }
        this.mPrintParameters.color = z ? ColorProcessing.BlackAndWhite : ColorProcessing.FullColor;
        File file = new File(str);
        LoggerUtil.m1181d("PrinterBro", "mPrintParameters.color = " + this.mPrintParameters.color + ", file " + file.exists());
        this.mPrintParameters.paperSize = MediaSize.A3;
        this.mPrintParameters.duplex = Duplex.Simplex;
        this.mPrintParameters.quality = PrintQuality.Photographic;
        this.mPrintParameters.resolution = new Resolution(600, 600);
        this.mPrintParameters.margin = PrintMargin.Normal;
        this.mPrintParameters.scale = PrintScale.FitToPrintableArea;
        this.mPrintParameters.orientation = PrintOrientation.AutoRotation;
        ArrayList arrayList = new ArrayList();
        this.mPrintParameters.copyCount = i;
        arrayList.add(file);
        submitPrintJob(arrayList, true);
    }

    private void submitPrintJob(List<File> list, boolean z) {
        try {
            try {
                this.printJob = new PrintJob(this.mPrintParameters, App.getInstance(), list, new Callback() {
                    @Override
                    public void onNotifyProcessAlive() {
                    }

                    @Override
                    public void onUpdateProcessProgress(int i) {
                    }
                });
                LoggerUtil.m1181d("PrinterBro", "connector.submit");
                startPrintTimeoutTimer();
                this.connector.submit(this.printJob);
                if (this.printJob != null) {
                    LoggerUtil.m1181d("PrinterBro", "connector.submit finish " + this.printJob.getStatus() + " tryAgain = " + z);
                } else {
                    LoggerUtil.m1181d("PrinterBro", "connector.submit finish printJob is null");
                }
                if (this.printJob.getStatus() != PrintState.Success) {
                    this.connector.validate();
                    ThreadClock.sleep(2000L);
                    if (z) {
                        LoggerUtil.m1182e("PrinterBro", "print fail，出纸失败，重试");
                        IPrinterStatusListener iPrinterStatusListener = this.listener;
                        if (iPrinterStatusListener != null) {
                            iPrinterStatusListener.printerStatus(9, "出纸失败，正在重试");
                        }
                        submitPrintJob(list, false);
                    }
                } else {
                    cancelTimer();
                }
            } catch (Exception e) {
                LoggerUtil.m1182e("PrinterBro", "error " + e.getMessage());
            }
        } finally {
            this.printJob = null;
        }
    }

    private void startPrintTimeoutTimer() {
        cancelTimer();
        int i = this.mPrintParameters.copyCount > 1 ? 45 + ((this.mPrintParameters.copyCount - 1) * 15) : 45;
        LoggerUtil.m1181d("PrinterBro", "timeOut " + i);
        Timer timer = new Timer();
        this.printTimeOutTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LoggerUtil.m1181d("PrinterBro", "timeOut printJob.cancel");
                if (PrinterBro.this.printJob != null) {
                    PrinterBro.this.printJob.cancel();
                }
            }
        }, ((long) i) * 1000);
    }

    private void cancelTimer() {
        Timer timer = this.printTimeOutTimer;
        if (timer != null) {
            timer.cancel();
            this.printTimeOutTimer.purge();
        }
    }

    @Override
    public int getRemainSheets() {
        return SpManager.getInstance().getRemainPrinterSheet();
    }

    @Override
    public boolean isPrinting() {
        return this.isPrinting;
    }
}
