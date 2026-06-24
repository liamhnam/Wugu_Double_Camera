package com.hiti.usb.service.usbPrinter;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.hiti.usb.service.network.INet;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionThread;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class UsbHost {
    private static final String ACTION_USB_PERMISSION = "hiti.usb.action.USB_PERMISSION";
    static final boolean localLOGV = true;
    private static final String tag = "UsbHost";
    private Context callerContext;
    private DeviceIdentifier identification;
    public String m_strTablesRoot;
    private UsbManager manager;
    boolean bFlag = false;
    private volatile UsbDevice targetDevice0 = null;
    private UsbDeviceConnection connection = null;
    private Object targetDeviceMonitor = new Object();
    private UsbBulkTrans deviceBulkTransferInfo = null;
    private Object deviceBulkTransferMonitor = new Object();
    private Semaphore requestPermisionlock = new Semaphore(1);
    private Semaphore BulkTransOplocker = new Semaphore(1);
    private Semaphore BulkTransOplocker2nd = new Semaphore(1);
    private UsbPermissionReceiver receiver = new UsbPermissionReceiver();

    public static class DeviceIdentifier {
        public static final int HITI_VENDOR_ID = 3350;
        public static final int TEST_VENDOR_ID = 1000;
        public int VedorId = HITI_VENDOR_ID;

        public boolean equalsDevice(UsbDevice usbDevice) {
            return this.VedorId == usbDevice.getVendorId() || 1000 == this.VedorId;
        }
    }

    class UsbBulkTrans {
        UsbInterface deviceInterface;
        UsbEndpoint mEndpointIn;
        UsbEndpoint mEndpointOut;

        public UsbBulkTrans(UsbDevice usbDevice) {
            UsbEndpoint endpoint;
            this.deviceInterface = null;
            this.mEndpointIn = null;
            this.mEndpointOut = null;
            UsbInterface usbInterface = usbDevice.getInterface(0);
            this.deviceInterface = usbInterface;
            if (usbInterface.getEndpoint(0).getDirection() == 128) {
                this.mEndpointIn = this.deviceInterface.getEndpoint(0);
                endpoint = this.deviceInterface.getEndpoint(1);
            } else {
                this.mEndpointIn = this.deviceInterface.getEndpoint(1);
                endpoint = this.deviceInterface.getEndpoint(0);
            }
            this.mEndpointOut = endpoint;
            Log.v(UsbHost.tag, "openDevice(): mEndpointIn.addr = " + this.mEndpointIn.getAddress() + ", mEndpointOut.addr = " + this.mEndpointOut.getAddress());
        }
    }

    private class UsbPermissionReceiver extends BroadcastReceiver {
        INet.IUpload listener;

        private UsbPermissionReceiver() {
            this.listener = null;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbHost.ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    notify();
                    UsbHost.this.requestPermisionlock.release();
                    if (intent.getBooleanExtra("permission", false)) {
                        Log.i(UsbHost.tag, "onReceive : get permission");
                    }
                    Log.i(UsbHost.tag, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after requestPermisionlock");
                }
                return;
            }
            if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action)) {
                Log.i(UsbHost.tag, "device disconnected");
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (!UsbHost.this.identification.equalsDevice(usbDevice) || usbDevice == null) {
                    return;
                }
                try {
                    UsbHost.this.connection.releaseInterface(UsbHost.this.deviceBulkTransferInfo.deviceInterface);
                    UsbHost.this.deviceBulkTransferInfo = null;
                    UsbHost.this.connection.close();
                    UsbHost.this.connection = null;
                } catch (NullPointerException unused) {
                }
                UsbHost.this.targetDevice0 = null;
                Log.i(UsbHost.tag, "onReceive : device disconnected.");
                INet.IUpload iUpload = this.listener;
                if (iUpload != null) {
                    iUpload.disconnect();
                }
            }
        }

        void setListener(INet.IUpload iUpload) {
            this.listener = iUpload;
        }
    }

    public UsbHost(Context context, DeviceIdentifier deviceIdentifier) {
        this.identification = null;
        this.callerContext = context.getApplicationContext();
        this.identification = deviceIdentifier;
        IntentFilter intentFilter = new IntentFilter(ACTION_USB_PERMISSION);
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.callerContext.registerReceiver(this.receiver, intentFilter);
        findDevice();
    }

    private UsbDevice findDevice() {
        if (this.targetDevice0 == null) {
            synchronized (this.targetDeviceMonitor) {
                if (this.targetDevice0 == null) {
                    String str = tag;
                    Log.e(str, "findDevice(): Start to find target device.");
                    UsbManager usbManager = (UsbManager) this.callerContext.getSystemService("usb");
                    this.manager = usbManager;
                    HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
                    if (deviceList != null) {
                        Log.e(str, "UsbManager found usb  devices no: " + String.valueOf(deviceList.size()));
                        Iterator<UsbDevice> it = deviceList.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            UsbDevice next = it.next();
                            if (this.identification.equalsDevice(next)) {
                                this.targetDevice0 = next;
                                Log.e(tag, "findDevice(): find target device.");
                                break;
                            }
                        }
                    } else {
                        Log.e(str, "UsbManager not found usb  device list.");
                    }
                }
            }
        }
        return this.targetDevice0;
    }

    private UsbDevice openDevice() {
        String str = PDActionThread.SUB_TYPE;
        String broadcast = PDActionThread.SUB_TYPE;
        if (findDevice() == null) {
            Log.e(tag, "openDevice: no device found");
            return this.targetDevice0;
        }
        if (!this.manager.hasPermission(this.targetDevice0)) {
            String str2 = null;
            usbDeviceConnection = 0;
            str2 = null;
            str2 = null;
            usbDeviceConnection = 0;
            usbDeviceConnection = 0;
            usbDeviceConnection = 0;
            UsbDeviceConnection usbDeviceConnection = 0;
            try {
                try {
                    try {
                        String str3 = tag;
                        Log.v(str3, "openDevice: Thread" + Thread.currentThread().getId() + " before requestPermisionlock");
                        this.requestPermisionlock.acquire();
                        if (this.manager.hasPermission(this.targetDevice0)) {
                            this.requestPermisionlock.release();
                            Log.v(str3, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after requestPermisionlock");
                            str = " after requestPermisionlock";
                            broadcast = broadcast;
                        } else {
                            this.connection = null;
                            Log.v(str3, "request permission");
                            UsbManager usbManager = this.manager;
                            UsbDevice usbDevice = this.targetDevice0;
                            broadcast = PendingIntent.getBroadcast(this.callerContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
                            usbManager.requestPermission(usbDevice, (PendingIntent) broadcast);
                            Log.v(str3, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " wait for permission");
                            str = this.receiver;
                            synchronized (str) {
                                this.receiver.wait();
                            }
                            Log.v(str3, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after wait permission");
                            str = " after wait permission";
                            broadcast = broadcast;
                        }
                    } catch (Throwable th) {
                        if (this.manager.hasPermission(this.targetDevice0)) {
                            this.requestPermisionlock.release();
                            Log.v(tag, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after requestPermisionlock");
                        } else {
                            this.connection = usbDeviceConnection;
                            String str4 = tag;
                            Log.v(str4, "request permission");
                            this.manager.requestPermission(this.targetDevice0, PendingIntent.getBroadcast(this.callerContext, 0, new Intent(ACTION_USB_PERMISSION), 0));
                            try {
                                Log.v(str4, broadcast + Thread.currentThread().getId() + " wait for permission");
                                synchronized (this.receiver) {
                                    this.receiver.wait();
                                    Log.v(str4, str + Thread.currentThread().getId() + " after wait permission");
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    if (this.manager.hasPermission(this.targetDevice0)) {
                        this.requestPermisionlock.release();
                        String str5 = tag;
                        String str6 = PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after requestPermisionlock";
                        Log.v(str5, str6);
                        str = str6;
                        broadcast = broadcast;
                    } else {
                        this.connection = null;
                        String str7 = tag;
                        Log.v(str7, "request permission");
                        UsbManager usbManager2 = this.manager;
                        UsbDevice usbDevice2 = this.targetDevice0;
                        Context context = this.callerContext;
                        str2 = ACTION_USB_PERMISSION;
                        broadcast = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
                        usbManager2.requestPermission(usbDevice2, (PendingIntent) broadcast);
                        Log.v(str7, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " wait for permission");
                        str = this.receiver;
                        synchronized (str) {
                            this.receiver.wait();
                            String str8 = PDActionThread.SUB_TYPE + Thread.currentThread().getId() + " after wait permission";
                            Log.v(str7, str8);
                            str = str8;
                            broadcast = broadcast;
                            usbDeviceConnection = str2;
                        }
                    }
                }
            } catch (InterruptedException e3) {
                e3.printStackTrace();
                str = str;
                broadcast = broadcast;
                usbDeviceConnection = str2;
            }
        }
        if (this.manager.hasPermission(this.targetDevice0) && this.connection == null) {
            synchronized (this.requestPermisionlock) {
                if (this.connection == null) {
                    this.connection = this.manager.openDevice(this.targetDevice0);
                    Log.v(tag, PDActionThread.SUB_TYPE + Thread.currentThread().getId() + "open device");
                }
            }
        }
        return this.targetDevice0;
    }

    public int getProductId() {
        if (this.targetDevice0 == null) {
            return 0;
        }
        return this.targetDevice0.getProductId();
    }

    public int getUsbDeviceFd() {
        UsbDeviceConnection usbDeviceConnection;
        if (openDevice() == null || (usbDeviceConnection = this.connection) == null) {
            return -1;
        }
        return usbDeviceConnection.getFileDescriptor();
    }

    public String printUsbDeviceInfo() {
        if (this.targetDevice0 == null) {
            return "not found target device..";
        }
        StringBuilder sb = new StringBuilder("\n DeviceID: ");
        sb.append(this.targetDevice0.getDeviceId()).append("\n DeviceName: ").append(this.targetDevice0.getDeviceName()).append("\n VendorID: ").append(this.targetDevice0.getVendorId()).append("\n ProductID: ").append(this.targetDevice0.getProductId());
        return sb.toString();
    }

    public int recieveData(byte[] bArr, int i) {
        int iBulkTransfer;
        int i2 = -1;
        if (bArr == null) {
            return -1;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (usbBulkTransferInit(4)) {
                    iBulkTransfer = this.connection.bulkTransfer(this.deviceBulkTransferInfo.mEndpointIn, bArr, bArr.length, i);
                }
            } catch (Exception unused) {
            }
        }
        if (usbBulkTransferInit(3)) {
            iBulkTransfer = this.connection.bulkTransfer(this.deviceBulkTransferInfo.mEndpointIn, bArr, bArr.length, i);
            i2 = iBulkTransfer;
        }
        Log.v(tag, "recieveData(): ret = " + i2);
        return i2;
    }

    public void releaseBulkOpLocker() {
        this.BulkTransOplocker.release();
        this.bFlag = false;
    }

    public void releaseBulkOpLocker2nd() {
        this.BulkTransOplocker2nd.release();
        this.bFlag = false;
    }

    public boolean requireBulkOpLocker() {
        try {
            this.BulkTransOplocker.acquire();
            this.bFlag = true;
            return true;
        } catch (InterruptedException unused) {
            Log.v(tag, "requireBulkOpLocker(): Interrupt");
            return false;
        }
    }

    public boolean requireBulkOpLocker2nd() {
        try {
            this.BulkTransOplocker2nd.acquire();
            this.bFlag = true;
            return true;
        } catch (InterruptedException unused) {
            Log.v(tag, "requireBulkOpLocker(): Interrupt");
            return false;
        }
    }

    public int sendData(byte[] bArr, int i) {
        int iBulkTransfer;
        int i2 = -1;
        if (bArr == null) {
            return -1;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Log.v(tag, "bulkTransfer error: " + e.getMessage());
                if (usbBulkTransferInit(2)) {
                    iBulkTransfer = this.connection.bulkTransfer(this.deviceBulkTransferInfo.mEndpointOut, bArr, bArr.length, i);
                }
            } catch (Exception unused) {
            }
        }
        if (!usbBulkTransferInit(1)) {
            Log.v(tag, "usbBulkTransferInit error");
            Log.v(tag, "sendData(): ret = " + i2);
            return i2;
        }
        Log.v(tag, "sendData before bulkTransfer length:" + bArr.length + " mEndpointOut: " + this.deviceBulkTransferInfo.mEndpointOut.getAddress());
        iBulkTransfer = this.connection.bulkTransfer(this.deviceBulkTransferInfo.mEndpointOut, bArr, bArr.length, i);
        i2 = iBulkTransfer;
        Log.v(tag, "sendData(): ret = " + i2);
        return i2;
    }

    public void setListener(INet.IUpload iUpload) {
        this.receiver.setListener(iUpload);
    }

    public void setStrTablesRoot(String str) {
        this.m_strTablesRoot = str;
    }

    public void unInstall() {
        try {
            this.connection.releaseInterface(this.deviceBulkTransferInfo.deviceInterface);
            this.deviceBulkTransferInfo = null;
            this.connection.close();
            this.connection = null;
        } catch (NullPointerException unused) {
        }
        this.callerContext.unregisterReceiver(this.receiver);
        Log.v(tag, "uninstall");
    }

    public boolean usbBulkTransferInit(int i) {
        if (openDevice() == null || this.connection == null) {
            return false;
        }
        if (this.deviceBulkTransferInfo == null) {
            synchronized (this.deviceBulkTransferMonitor) {
                if (this.deviceBulkTransferInfo == null) {
                    UsbBulkTrans usbBulkTrans = new UsbBulkTrans(this.targetDevice0);
                    this.deviceBulkTransferInfo = usbBulkTrans;
                    return this.connection.claimInterface(usbBulkTrans.deviceInterface, true);
                }
            }
        }
        return true;
    }
}
