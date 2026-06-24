package com.printer.sdk;

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
import com.printer.sdk.C1843h;

public abstract class AbstractC1842g implements PrintType {

    private static int f2195a = 512;

    private UsbManager f2196b = null;

    private UsbDeviceConnection f2197c = null;

    private UsbEndpoint f2198d = null;

    private UsbEndpoint f2199e = null;

    private Context f2200f;

    private final BroadcastReceiver f2201g;

    public AbstractC1842g(Context context) {
        this.f2200f = null;
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if ((usbDevice.getProductId() == 5 || usbDevice.getProductId() == 10) && usbDevice.getVendorId() == 4931) {
                    String action = intent.getAction();
                    action.hashCode();
                    switch (action) {
                        case "android.hardware.usb.action.USB_DEVICE_ATTACHED":
                            AbstractC1842g.this.mo783a(new PrintMsg(1004, "检测到打印机,请确认获取授权!"));
                            AbstractC1842g.this.m782a();
                            return;
                        case "android.hardware.usb.action.USB_DEVICE_DETACHED":
                            AbstractC1842g.this.mo783a(new PrintMsg(1001, "打印机掉线,服务已经停止!"));
                            return;
                        case "com.lcg.USB_PERMISSION":
                            synchronized (this) {
                                if (!intent.getBooleanExtra("permission", false)) {
                                    AbstractC1842g.this.mo783a(new PrintMsg(1001, "获取授权失败"));
                                } else if (usbDevice != null) {
                                    AbstractC1842g.this.m779a(usbDevice);
                                }
                                break;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        };
        this.f2201g = broadcastReceiver;
        this.f2200f = context;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.lcg.USB_PERMISSION");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        this.f2200f.registerReceiver(broadcastReceiver, intentFilter);
    }

    public boolean m779a(UsbDevice usbDevice) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i = 0; i < interfaceCount; i++) {
            UsbInterface usbInterface = usbDevice.getInterface(i);
            if (7 == usbInterface.getInterfaceClass()) {
                for (int i2 = 0; i2 < usbInterface.getEndpointCount(); i2++) {
                    UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
                    if (endpoint.getType() == 2) {
                        if (endpoint.getDirection() == 0) {
                            this.f2198d = endpoint;
                        } else if (endpoint.getDirection() == 128) {
                            this.f2199e = endpoint;
                            f2195a = endpoint.getMaxPacketSize();
                        }
                    }
                }
                UsbEndpoint usbEndpoint = this.f2198d;
                if (usbEndpoint != null && usbEndpoint != null) {
                    UsbDeviceConnection usbDeviceConnectionOpenDevice = this.f2196b.openDevice(usbDevice);
                    this.f2197c = usbDeviceConnectionOpenDevice;
                    if (usbDeviceConnectionOpenDevice.claimInterface(usbInterface, true)) {
                        mo783a(new PrintMsg(1002, "连接打印机成功,打印服务就绪"));
                    }
                    return true;
                }
                this.f2198d = null;
                this.f2199e = null;
                this.f2197c = null;
                mo783a(new PrintMsg(1001, "连接打印机失败"));
            }
        }
        return false;
    }

    public String m781a(byte[] bArr, int i) {
        byte[] bArr2;
        int iBulkTransfer;
        int i2;
        byte[] bArr3;
        int iBulkTransfer2;
        if (m787b() && bArr != null) {
            try {
                if (this.f2197c.bulkTransfer(this.f2198d, bArr, bArr.length, 1000) > 0 && (iBulkTransfer = this.f2197c.bulkTransfer(this.f2199e, (bArr2 = new byte[32]), 32, i)) > 0 && (iBulkTransfer2 = this.f2197c.bulkTransfer(this.f2199e, (bArr3 = new byte[(i2 = Integer.parseInt(new String(bArr2, 0, iBulkTransfer)))]), i2, 1000)) > 0) {
                    return new String(bArr3, 0, iBulkTransfer2);
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public void m782a() {
        UsbDeviceConnection usbDeviceConnection = this.f2197c;
        if (usbDeviceConnection != null) {
            usbDeviceConnection.close();
        }
        UsbManager usbManager = (UsbManager) this.f2200f.getSystemService("usb");
        this.f2196b = usbManager;
        this.f2198d = null;
        this.f2199e = null;
        this.f2197c = null;
        for (UsbDevice usbDevice : usbManager.getDeviceList().values()) {
            if (usbDevice.getProductId() == 5 || usbDevice.getProductId() == 10) {
                if (usbDevice.getVendorId() == 4931) {
                    if (this.f2196b.hasPermission(usbDevice)) {
                        m779a(usbDevice);
                        return;
                    } else {
                        this.f2196b.requestPermission(usbDevice, PendingIntent.getBroadcast(this.f2200f, 0, new Intent("com.lcg.USB_PERMISSION"), 0));
                        return;
                    }
                }
            }
        }
        mo783a(new PrintMsg(1001, "没有发现设备"));
    }

    public abstract void mo783a(PrintMsg printMsg);

    public boolean m784a(byte[] bArr) {
        if (m787b() && bArr != null) {
            try {
                int length = bArr.length;
                int i = 0;
                while (length > 0) {
                    int i2 = f2195a;
                    int i3 = length > i2 ? i2 : length;
                    if (this.f2197c.bulkTransfer(this.f2198d, bArr, i, i3, 1000) <= 0) {
                        return false;
                    }
                    length -= i3;
                    i += i3;
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public boolean m785a(byte[] bArr, C1843h.b bVar) {
        if (m787b() && bArr != null) {
            try {
                int length = bArr.length;
                int i = 0;
                while (length > 0) {
                    int i2 = f2195a;
                    int i3 = length > i2 ? i2 : length;
                    if (this.f2197c.bulkTransfer(this.f2198d, bArr, i, i3, 1000) <= 0) {
                        return false;
                    }
                    length -= i3;
                    i += i3;
                    if (bVar != null && !bVar.mo772a(length, i)) {
                        return false;
                    }
                }
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public String m786b(byte[] bArr) {
        return m781a(bArr, 1000);
    }

    public boolean m787b() {
        return (this.f2199e == null || this.f2198d == null || this.f2197c == null) ? false : true;
    }

    public void m788c() {
        UsbDeviceConnection usbDeviceConnection = this.f2197c;
        if (usbDeviceConnection != null) {
            usbDeviceConnection.close();
            this.f2197c = null;
        }
        try {
            this.f2200f.unregisterReceiver(this.f2201g);
        } catch (Exception unused) {
        }
    }
}
