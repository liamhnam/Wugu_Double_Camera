package com.brother.sdk.usb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import com.brother.sdk.BrotherAndroidLib;
import com.faceunity.wrapper.faceunity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class UsbControllerManager {
    private static UsbController mInstance;

    public static UsbController getUsbController() {
        if (mInstance == null) {
            mInstance = new UsbController(BrotherAndroidLib.getAndroidContext());
        }
        return mInstance;
    }

    public static class UsbController {
        private static final String ACTION_USB_PERMISSION = "com.brother.sdk.usb.USB_PERMISSION";
        private final WeakReference<Context> mContext;
        private final PendingIntent mPermissionIntent;
        private final UsbManager mUsbManager;
        private final BroadcastReceiver mUsbReceiver;
        private UsbDevicePermissionWaiter mWait;

        private UsbController(Context context) {
            this.mUsbReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context2, Intent intent) throws Throwable {
                    UsbDevicePermissionWaiter usbDevicePermissionWaiter;
                    String action = intent.getAction();
                    UsbDebug.debug("Receive action : " + action);
                    if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                        UsbDebug.debug("ACTION_USB_DEVICE_DETACHED : android.hardware.usb.action.USB_DEVICE_DETACHED");
                        return;
                    }
                    if (UsbController.ACTION_USB_PERMISSION.equals(action)) {
                        synchronized (this) {
                            try {
                                try {
                                    usbDevice = intent.getBooleanExtra("permission", false) ? (UsbDevice) intent.getParcelableExtra("device") : null;
                                } catch (Exception e) {
                                    UsbDebug.debug(e.getMessage());
                                    if (UsbController.this.mWait != null) {
                                        usbDevicePermissionWaiter = UsbController.this.mWait;
                                    }
                                }
                                if (UsbController.this.mWait != null) {
                                    usbDevicePermissionWaiter = UsbController.this.mWait;
                                    usbDevicePermissionWaiter.notifyUsbDevicePermitted(usbDevice);
                                }
                            } catch (Throwable th) {
                                if (UsbController.this.mWait != null) {
                                    UsbController.this.mWait.notifyUsbDevicePermitted(null);
                                }
                                throw th;
                            }
                        }
                    }
                }
            };
            this.mUsbManager = (UsbManager) context.getSystemService("usb");
            if (Build.VERSION.SDK_INT >= 31) {
                this.mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), faceunity.FUAITYPE_FACEPROCESSOR_EMOTION_RECOGNIZER);
            } else {
                this.mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
            }
            this.mContext = new WeakReference<>(context);
        }

        protected void finalize() {
            stopControl();
        }

        public List<BrUsbDevice> findDevices() throws Throwable {
            HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
            if (deviceList == null || deviceList.isEmpty()) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<UsbDevice> it = deviceList.values().iterator();
            while (it.hasNext()) {
                BrUsbDevice brUsbDeviceCreate = BrUsbDevice.create(it.next());
                if (brUsbDeviceCreate != null) {
                    arrayList.add(brUsbDeviceCreate);
                }
            }
            return arrayList;
        }

        public UsbDevice getDevice(BrUsbDevice brUsbDevice) throws Throwable {
            HashMap<String, UsbDevice> deviceList = this.mUsbManager.getDeviceList();
            if (deviceList != null && brUsbDevice != null) {
                for (UsbDevice usbDevice : deviceList.values()) {
                    if (brUsbDevice.equals(BrUsbDevice.create(usbDevice))) {
                        return usbDevice;
                    }
                }
                return null;
            }
            UsbDebug.debug("[Failure] USB Service getDeviceList");
            return null;
        }

        public void startControl() {
            if (this.mContext.get() != null) {
                this.mContext.get().registerReceiver(this.mUsbReceiver, new IntentFilter(ACTION_USB_PERMISSION));
            }
        }

        public void stopControl() {
            if (this.mContext.get() != null) {
                try {
                    this.mContext.get().unregisterReceiver(this.mUsbReceiver);
                } catch (Exception unused) {
                }
            }
            UsbDevicePermissionWaiter usbDevicePermissionWaiter = this.mWait;
            if (usbDevicePermissionWaiter != null) {
                synchronized (usbDevicePermissionWaiter) {
                    this.mWait.notifyAll();
                }
            }
        }

        UsbDeviceConnector getUsbDeviceConnector(UsbDevice usbDevice) {
            return new UsbDeviceConnector(usbDevice);
        }

        class UsbDeviceConnector {
            private final UsbDevice mDevice;

            private UsbDeviceConnector(UsbDevice usbDevice) {
                this.mDevice = usbDevice;
            }

            UsbDeviceConnection connectToDevice() throws Throwable {
                UsbDebug.debug("Start connection to USB (UsbDeviceConnection::connectToDevice)");
                try {
                    if (UsbController.this.mWait == null) {
                        UsbDevice usbDevice = this.mDevice;
                        if (!UsbController.this.mUsbManager.hasPermission(this.mDevice)) {
                            UsbController.this.mUsbManager.requestPermission(this.mDevice, UsbController.this.mPermissionIntent);
                            UsbDebug.debug("Wait connection to USB (UsbDeviceConnection::connectToDevice)");
                            UsbController.this.mWait = new UsbDevicePermissionWaiter();
                            synchronized (UsbController.this.mWait) {
                                try {
                                    UsbController.this.mWait.wait();
                                    UsbDebug.debug("Wait completed");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            UsbDebug.debug("[Message] Permission Wait Complete");
                            usbDevice = UsbController.this.mWait.getUsbDevice();
                            if (usbDevice == null) {
                                throw new NullPointerException("Permission Request Failed.");
                            }
                        }
                        return UsbController.this.mUsbManager.openDevice(usbDevice);
                    }
                } catch (Exception e2) {
                    UsbDebug.debug(e2.toString());
                    UsbDebug.debug(e2.getMessage());
                } finally {
                    UsbController.this.mWait = null;
                }
                return null;
            }

            void cancelConnection() {
                if (UsbController.this.mWait != null) {
                    synchronized (UsbController.this.mWait) {
                        UsbController.this.mWait.notifyAll();
                    }
                }
            }
        }

        private static class UsbDevicePermissionWaiter {
            private UsbDevice mDevice = null;

            UsbDevicePermissionWaiter() {
            }

            synchronized void notifyUsbDevicePermitted(UsbDevice usbDevice) {
                this.mDevice = usbDevice;
                notifyAll();
            }

            UsbDevice getUsbDevice() {
                return this.mDevice;
            }
        }
    }
}
