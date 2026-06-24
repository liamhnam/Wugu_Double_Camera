package com.epson.isv.eprinterdriver.Ctrl;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Build;
import com.epson.isv.eprinterdriver.Common.EPLog;
import java.nio.ByteBuffer;

public class UsbPrinter {
    private static final String TAG = "UsbPrinter";
    protected static final int USBLP_BULK_TIMEOUT = 5000;
    protected static final int USBLP_CTL_TIMEOUT = 5000;
    protected static final int USBLP_REQ_GET_ID = 0;
    protected static final int USBLP_REQ_GET_STATUS = 1;
    protected static final int USBLP_REQ_RESET = 2;
    public static final int USB_OP_FAIL = -1;
    public static final int USB_OP_SUCCESS = 0;
    private static int manualPacketSize;
    private UsbEndpoint endPointIn;
    private UsbEndpoint endPointOut;
    private UsbDevice usbDevice;
    private UsbInterface usbInterface;
    private UsbManager usbManager;
    private UsbDeviceConnection usbPrinter = null;
    private long startTime = 0;
    private int maxPacketSize = 0;

    private class RequestType {
        public static final int DIR_IN = 128;
        public static final int DIR_OUT = 0;
        public static final int RECIPIENT_DEVICE = 0;
        public static final int RECIPIENT_ENDPOINT = 2;
        public static final int RECIPIENT_INTERFACE = 1;
        public static final int RECIPIENT_OTHER = 3;
        public static final int TYPE_CLASS = 32;
        public static final int TYPE_RESERVED = 96;
        public static final int TYPE_STANDARD = 0;
        public static final int TYPE_VENDOR = 64;

        private RequestType() {
        }
    }

    public UsbPrinter(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        this.usbManager = usbManager;
        this.usbDevice = usbDevice;
        this.usbInterface = usbInterface;
        this.endPointOut = usbEndpoint;
        this.endPointIn = usbEndpoint2;
    }

    public UsbDevice getUsbDevice() {
        return this.usbDevice;
    }

    public UsbDeviceConnection getUsbPrinter() {
        return this.usbPrinter;
    }

    public static void setManualPacketSize(int i) {
        manualPacketSize = i;
    }

    public int getDeviceNumbers() {
        int deviceId = getUsbDevice().getDeviceId();
        int i = deviceId / 1000;
        return (i << 16) + (deviceId - (i * 1000));
    }

    public synchronized int openPort() {
        try {
            UsbDeviceConnection usbDeviceConnectionOpenDevice = this.usbManager.openDevice(this.usbDevice);
            this.usbPrinter = usbDeviceConnectionOpenDevice;
            if (usbDeviceConnectionOpenDevice == null) {
                return -1;
            }
            int i = manualPacketSize;
            if (i != 0) {
                this.maxPacketSize = i;
            } else {
                this.maxPacketSize = this.endPointOut.getMaxPacketSize();
            }
            return this.usbPrinter.getFileDescriptor();
        } catch (Exception unused) {
            return -1;
        }
    }

    public synchronized long readPort(byte[] bArr, int i) {
        UsbDeviceConnection usbDeviceConnection = this.usbPrinter;
        if (usbDeviceConnection == null) {
            return -1L;
        }
        if (!usbDeviceConnection.claimInterface(this.usbInterface, true)) {
            return -1L;
        }
        setStartTime();
        EPLog.logInfo("bulkTransfer length=" + i);
        int iBulkTransfer = this.usbPrinter.bulkTransfer(this.endPointIn, bArr, i, 5000);
        EPLog.logInfo("bulkTransfer readByte=" + iBulkTransfer);
        if (iBulkTransfer < 0 && getElapsedTime() >= 4500.0d) {
            EPLog.logInfo("bulkTransfer timeout occurred");
            iBulkTransfer = 0;
        }
        this.usbPrinter.releaseInterface(this.usbInterface);
        return iBulkTransfer;
    }

    public synchronized long writePort(byte[] bArr, int i) {
        UsbDeviceConnection usbDeviceConnection = this.usbPrinter;
        if (usbDeviceConnection == null) {
            return -1L;
        }
        if (!usbDeviceConnection.claimInterface(this.usbInterface, true)) {
            return -1L;
        }
        EPLog.logInfo("bulkTransfer length=" + i);
        byte[] bArr2 = new byte[this.maxPacketSize];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        int i2 = 0;
        while (true) {
            int iRemaining = byteBufferWrap.remaining();
            int iRemaining2 = this.maxPacketSize;
            if (iRemaining < iRemaining2) {
                iRemaining2 = byteBufferWrap.remaining();
            }
            byteBufferWrap.get(bArr2, 0, iRemaining2);
            setStartTime();
            int iBulkTransfer = this.usbPrinter.bulkTransfer(this.endPointOut, bArr2, iRemaining2, 5000);
            EPLog.logInfo("bulkTransfer written=" + iBulkTransfer);
            if (iBulkTransfer < 0 && getElapsedTime() >= 4500.0d) {
                EPLog.logInfo("bulkTransfer timeout occurred");
                break;
            }
            if (iBulkTransfer > 0) {
                i2 += iBulkTransfer;
            }
            if (iBulkTransfer != this.maxPacketSize || byteBufferWrap.remaining() == 0) {
                break;
            }
        }
        EPLog.logInfo("bulkTransfer writtenByte=" + i2 + " difference=" + (i - i2));
        this.usbPrinter.releaseInterface(this.usbInterface);
        return i2;
    }

    private void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    private long getElapsedTime() {
        return System.currentTimeMillis() - this.startTime;
    }

    public synchronized long getDeviceIdString(byte[] bArr, int i) {
        UsbDeviceConnection usbDeviceConnection = this.usbPrinter;
        if (usbDeviceConnection == null) {
            return -1L;
        }
        if (!usbDeviceConnection.claimInterface(this.usbInterface, true)) {
            return -1L;
        }
        int iControlTransfer = this.usbPrinter.controlTransfer(161, 0, 0, this.usbInterface.getId() << 8, bArr, i, 5000);
        if (iControlTransfer > 0) {
            EPLog.logInfo("EPSONDeviceIdString = ".concat(new String(bArr, 2, iControlTransfer - 2)));
        }
        this.usbPrinter.releaseInterface(this.usbInterface);
        return iControlTransfer;
    }

    public synchronized int softReset() {
        UsbDeviceConnection usbDeviceConnection = this.usbPrinter;
        if (usbDeviceConnection == null) {
            return -1;
        }
        if (usbDeviceConnection.claimInterface(this.usbInterface, true)) {
            int iControlTransfer = this.usbPrinter.controlTransfer(35, 2, 0, this.usbInterface.getId(), null, 0, 5000);
            this.usbPrinter.releaseInterface(this.usbInterface);
            if (iControlTransfer >= 0) {
                return 0;
            }
        }
        return -1;
    }

    public synchronized void closePort() {
        UsbDeviceConnection usbDeviceConnection = this.usbPrinter;
        if (usbDeviceConnection == null) {
            return;
        }
        usbDeviceConnection.close();
        this.usbPrinter = null;
    }

    public String toString() {
        String str = ("deviceName = " + this.usbDevice.getDeviceName() + " : endPointOut = " + Integer.toHexString(this.endPointOut.getAddress()) + " : endPointIn = " + Integer.toHexString(this.endPointIn.getAddress())) + " : productName = " + this.usbDevice.getProductName();
        return Build.VERSION.SDK_INT <= 28 ? str + " : SerialNo = " + this.usbDevice.getSerialNumber() : str;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (obj instanceof UsbPrinter) && ((UsbPrinter) obj).getUsbDevice().getDeviceId() == getUsbDevice().getDeviceId();
    }
}
