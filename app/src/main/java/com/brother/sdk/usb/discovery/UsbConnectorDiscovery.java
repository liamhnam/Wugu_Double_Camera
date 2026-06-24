package com.brother.sdk.usb.discovery;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.ConnectorManager;
import com.brother.sdk.common.util.Tool;
import com.brother.sdk.usb.BrUsbDevice;
import com.brother.sdk.usb.UsbControllerManager;
import com.brother.sdk.usb.UsbDebug;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class UsbConnectorDiscovery extends ConnectorManager {
    private static final int EXECUTOR_AWAIT_TIME = 1000;
    private USBConnectorDiscoveryTask mDiscovery = null;
    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    private final UsbControllerManager.UsbController mController = UsbControllerManager.getUsbController();

    @Override
    public List<ConnectorDescriptor> discover() throws Throwable {
        UsbDebug.debug("USB discovery starts.");
        ArrayList arrayList = new ArrayList();
        for (BrUsbDevice brUsbDevice : Tool.emptyIfNull(this.mController.findDevices())) {
            UsbDebug.debug("[Vendor ID] : " + brUsbDevice.mVendorID);
            UsbDebug.debug("[Product ID] : " + brUsbDevice.mProductID);
            UsbDebug.debug("[Product Name] : " + brUsbDevice.mProductName);
            UsbDebug.debug("[Manufacturer Name] : " + brUsbDevice.mManufacturerName);
            UsbDebug.debug("[Serial Number] : " + brUsbDevice.mSerialNumber);
            arrayList.add(new UsbConnectorDescriptor(brUsbDevice));
        }
        return arrayList;
    }

    protected void finalize() {
        USBConnectorDiscoveryTask uSBConnectorDiscoveryTask = this.mDiscovery;
        if (uSBConnectorDiscoveryTask != null) {
            uSBConnectorDiscoveryTask.cancel();
            this.mExecutor.shutdown();
            try {
                if (this.mExecutor.awaitTermination(1000L, TimeUnit.MILLISECONDS)) {
                    return;
                }
                this.mExecutor.shutdownNow();
            } catch (Exception unused) {
                this.mExecutor.shutdownNow();
            }
        }
    }

    @Override
    public void startDiscover(ConnectorManager.OnDiscoverConnectorListener onDiscoverConnectorListener) {
        if (this.mDiscovery == null) {
            USBConnectorDiscoveryTask uSBConnectorDiscoveryTask = new USBConnectorDiscoveryTask(onDiscoverConnectorListener);
            this.mDiscovery = uSBConnectorDiscoveryTask;
            this.mExecutor.execute(uSBConnectorDiscoveryTask);
        }
    }

    @Override
    public void stopDiscover() {
        USBConnectorDiscoveryTask uSBConnectorDiscoveryTask = this.mDiscovery;
        if (uSBConnectorDiscoveryTask != null) {
            uSBConnectorDiscoveryTask.cancel();
            this.mDiscovery = null;
        }
    }

    private class USBConnectorDiscoveryTask implements Runnable {
        private final ConnectorManager.OnDiscoverConnectorListener mListener;
        private final List<BrUsbDevice> mDevices = new ArrayList();
        private boolean mCancel = false;

        public USBConnectorDiscoveryTask(ConnectorManager.OnDiscoverConnectorListener onDiscoverConnectorListener) {
            this.mListener = onDiscoverConnectorListener;
        }

        @Override
        public void run() throws Throwable {
            while (!this.mCancel) {
                try {
                    for (BrUsbDevice brUsbDevice : Tool.emptyIfNull(UsbConnectorDiscovery.this.mController.findDevices())) {
                        if (!this.mDevices.contains(brUsbDevice)) {
                            UsbDebug.debug("[Vendor ID] : " + brUsbDevice.mVendorID);
                            UsbDebug.debug("[Product ID] : " + brUsbDevice.mProductID);
                            UsbDebug.debug("[Product Name] : " + brUsbDevice.mProductName);
                            UsbDebug.debug("[Manufacturer Name] : " + brUsbDevice.mManufacturerName);
                            UsbDebug.debug("[Serial Number] : " + brUsbDevice.mSerialNumber);
                            this.mListener.onDiscover(new UsbConnectorDescriptor(brUsbDevice));
                            this.mDevices.add(brUsbDevice);
                        }
                    }
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            this.mCancel = true;
        }
    }
}
