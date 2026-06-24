package com.epson.isv.eprinterdriver.Ctrl;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import com.epson.isv.eprinterdriver.Common.CommonDefine;
import com.epson.isv.eprinterdriver.Common.EPLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UsbPrintDriver {
    private static final String TAG = "UsbPrintDriver";
    private static UsbPrintDriver instance;
    private Context context;
    private UsbManager usbManager;
    private ArrayList<UsbPrinter> usbPrintersPemmited = null;

    private UsbPrintDriver(Context context) {
        this.usbManager = (UsbManager) context.getSystemService("usb");
        this.context = context;
    }

    public static synchronized UsbPrintDriver getInstance(Context context) {
        if (instance == null) {
            instance = new UsbPrintDriver(context);
        }
        return instance;
    }

    public UsbManager getUsbManager() {
        return this.usbManager;
    }

    public boolean isExistPermitedDevice() {
        synchronized (this) {
            return findDevice(0, new int[3]) != -1;
        }
    }

    public void deletePrinterPermmited(UsbDevice usbDevice) {
        synchronized (this) {
            if (usbDevice == null) {
                return;
            }
            ArrayList<UsbPrinter> arrayList = this.usbPrintersPemmited;
            if (arrayList == null) {
                return;
            }
            Iterator<UsbPrinter> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UsbPrinter next = it.next();
                if (next.getUsbDevice().getDeviceId() == usbDevice.getDeviceId()) {
                    EPLog.logInfo("TagEPSON delete usbPrinter = " + next.toString());
                    next.closePort();
                    if (this.usbPrintersPemmited.remove(next)) {
                        EPLog.logInfo("TagEPSON delete success");
                    }
                }
            }
        }
    }

    public synchronized ArrayList<UsbPrinter> findPrinters(boolean z, int i) {
        ArrayList<UsbPrinter> arrayList;
        arrayList = new ArrayList<>();
        HashMap<String, UsbDevice> deviceList = this.usbManager.getDeviceList();
        Iterator<String> it = deviceList.keySet().iterator();
        while (it.hasNext()) {
            UsbDevice usbDevice = deviceList.get(it.next());
            if (!z || this.usbManager.hasPermission(usbDevice)) {
                if (usbDevice.getVendorId() == i) {
                    int interfaceCount = usbDevice.getInterfaceCount();
                    for (int i2 = 0; i2 < interfaceCount; i2++) {
                        UsbInterface usbInterface = usbDevice.getInterface(i2);
                        if (7 == usbInterface.getInterfaceClass() && 1 == usbInterface.getInterfaceSubclass()) {
                            int i3 = 2;
                            if (2 == usbInterface.getInterfaceProtocol()) {
                                int endpointCount = usbInterface.getEndpointCount();
                                UsbEndpoint usbEndpoint = null;
                                UsbEndpoint usbEndpoint2 = null;
                                int i4 = 0;
                                while (i4 < endpointCount) {
                                    UsbEndpoint endpoint = usbInterface.getEndpoint(i4);
                                    if (usbEndpoint == null && i3 == endpoint.getType() && endpoint.getDirection() == 0) {
                                        usbEndpoint = endpoint;
                                    } else if (usbEndpoint2 == null && i3 == endpoint.getType() && 128 == endpoint.getDirection()) {
                                        usbEndpoint2 = endpoint;
                                    }
                                    i4++;
                                    i3 = 2;
                                }
                                if (usbEndpoint != null && usbEndpoint2 != null) {
                                    UsbPrinter usbPrinter = new UsbPrinter(this.usbManager, usbDevice, usbInterface, usbEndpoint, usbEndpoint2);
                                    EPLog.logInfo("EPSONFound Printer " + usbPrinter.toString());
                                    arrayList.add(usbPrinter);
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public int findDevice(int i, int[] iArr) {
        ArrayList<UsbPrinter> arrayList;
        EPLog.logInfo("UsbPrintDriverCall findDevice");
        synchronized (this) {
            if (i == 0) {
                try {
                    this.usbPrintersPemmited = findPrinters(true, CommonDefine.EPSON_VENDERID);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (i < 0 || (arrayList = this.usbPrintersPemmited) == null || i >= arrayList.size()) {
                return -1;
            }
            UsbPrinter usbPrinter = this.usbPrintersPemmited.get(i);
            iArr[0] = usbPrinter.getUsbDevice().getVendorId();
            iArr[1] = usbPrinter.getUsbDevice().getProductId();
            iArr[2] = usbPrinter.getDeviceNumbers();
            return i + 1;
        }
    }

    public int openPort(int[] iArr) {
        EPLog.logInfo("UsbPrintDriverCall openPort");
        synchronized (this) {
            for (int i = 0; i < this.usbPrintersPemmited.size(); i++) {
                UsbPrinter usbPrinter = this.usbPrintersPemmited.get(i);
                if (iArr[0] == usbPrinter.getUsbDevice().getVendorId() && iArr[1] == usbPrinter.getUsbDevice().getProductId() && iArr[2] == usbPrinter.getDeviceNumbers()) {
                    return usbPrinter.openPort();
                }
            }
            return -1;
        }
    }

    public UsbPrinter findPrinterOpened(int i) {
        synchronized (this) {
            for (int i2 = 0; i2 < this.usbPrintersPemmited.size(); i2++) {
                UsbPrinter usbPrinter = this.usbPrintersPemmited.get(i2);
                UsbDeviceConnection usbPrinter2 = usbPrinter.getUsbPrinter();
                if (usbPrinter2 != null && i == usbPrinter2.getFileDescriptor()) {
                    return usbPrinter;
                }
            }
            return null;
        }
    }

    public long readPort(int i, byte[] bArr, int i2) {
        EPLog.logInfo("UsbPrintDriverCall readPort");
        synchronized (this) {
            UsbPrinter usbPrinterFindPrinterOpened = findPrinterOpened(i);
            if (usbPrinterFindPrinterOpened == null) {
                return -1L;
            }
            return usbPrinterFindPrinterOpened.readPort(bArr, i2);
        }
    }

    public long writePort(int i, byte[] bArr, int i2) {
        EPLog.logInfo("UsbPrintDriverCall writePort");
        synchronized (this) {
            UsbPrinter usbPrinterFindPrinterOpened = findPrinterOpened(i);
            if (usbPrinterFindPrinterOpened == null) {
                return -1L;
            }
            return usbPrinterFindPrinterOpened.writePort(bArr, i2);
        }
    }

    public long getDeviceIdString(int i, byte[] bArr, int i2) {
        EPLog.logInfo("UsbPrintDriverCall getDeviceIdString");
        synchronized (this) {
            UsbPrinter usbPrinterFindPrinterOpened = findPrinterOpened(i);
            if (usbPrinterFindPrinterOpened == null) {
                return -1L;
            }
            return usbPrinterFindPrinterOpened.getDeviceIdString(bArr, i2);
        }
    }

    public int softReset(int i) {
        EPLog.logInfo("UsbPrintDriverCall softReset");
        synchronized (this) {
            UsbPrinter usbPrinterFindPrinterOpened = findPrinterOpened(i);
            if (usbPrinterFindPrinterOpened == null) {
                return -1;
            }
            return usbPrinterFindPrinterOpened.softReset();
        }
    }

    public void closePort(int i) {
        EPLog.logInfo("UsbPrintDriverCall closePort");
        synchronized (this) {
            UsbPrinter usbPrinterFindPrinterOpened = findPrinterOpened(i);
            if (usbPrinterFindPrinterOpened != null) {
                usbPrinterFindPrinterOpened.closePort();
            }
        }
    }
}
