package com.brother.sdk.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.os.Build;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.usb.UsbControllerManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.SocketException;

public class BrUsbSocket implements ISocket {
    private static final int BROTHER_NORMAL_INTERFACE = 0;
    private static final int BULK_TRANSFER_TIMEOUT = 5000;
    private static final int CONTROL_TRANSFER_TIMEOUT = 2000;
    private static final int USB_REQUEST_TO_DEVICE = 0;
    private static final int USB_REQUEST_TO_ENDPOINT = 2;
    private static final int USB_REQUEST_TO_INTERFACE = 1;
    private static final int VENDOR_USB_DEVICE = 64;
    private UsbDeviceConnection mConnection;
    private final UsbControllerManager.UsbController mController;
    private final UsbDevice mDevice;
    private UsbControllerManager.UsbController.UsbDeviceConnector mDeviceConnector;
    private UsbEndpoint mInEndpoint;
    private int mInMaxPacketSizes;
    private int mInTimeout;
    private UsbInterface mInterface;
    private final BrotherUsbInterface mInterfaceType;
    private UsbEndpoint mOutEndpoint;
    private int mOutMaxPacketSizes;
    private int mOutTimeout;
    private int mTimeout = 0;
    private boolean mCancel = false;

    @Override
    public boolean remainReadBuffer() {
        return false;
    }

    public BrUsbSocket(BrUsbDevice brUsbDevice, BrotherUsbInterface brotherUsbInterface) {
        this.mInterfaceType = brotherUsbInterface;
        UsbControllerManager.UsbController usbController = UsbControllerManager.getUsbController();
        this.mController = usbController;
        this.mDevice = usbController.getDevice(brUsbDevice);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.mCancel) {
            throw new SocketException("Socket could be already closed, or cancelled.");
        }
        synchronized (this) {
            if (this.mConnection.claimInterface(this.mInterface, true)) {
                byte[] byteArray = bArr;
                int i3 = 0;
                do {
                    try {
                        int i4 = this.mOutMaxPacketSizes;
                        if (i4 >= i2) {
                            i4 = i2;
                        }
                        int iBulkTransfer = this.mConnection.bulkTransfer(this.mOutEndpoint, byteArray, i4, 5000);
                        if (iBulkTransfer < 0) {
                            UsbDebug.debug("[Info] write = -1 ");
                            for (int i5 = 0; i5 < 5 && (iBulkTransfer = this.mConnection.bulkTransfer(this.mOutEndpoint, byteArray, i4, 5000)) <= 0; i5++) {
                            }
                        }
                        i3 += iBulkTransfer;
                        i2 -= iBulkTransfer;
                        if (i2 > 0) {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            byteArrayOutputStream.write(bArr, i3, i2);
                            byteArray = byteArrayOutputStream.toByteArray();
                        }
                    } catch (IndexOutOfBoundsException unused) {
                        UsbDebug.debug("[Info] IndexOutOfBoundsException ");
                    }
                } while (i2 > 0);
            }
        }
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iBulkTransfer;
        if (this.mCancel) {
            throw new SocketException("Socket could be already closed, or cancelled.");
        }
        synchronized (this) {
            if (this.mConnection.claimInterface(this.mInterface, true)) {
                int i3 = this.mInMaxPacketSizes;
                if (i3 < i2) {
                    i2 = i3;
                }
                iBulkTransfer = this.mConnection.bulkTransfer(this.mInEndpoint, bArr, i2, 5000);
            } else {
                iBulkTransfer = 0;
            }
        }
        return iBulkTransfer;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean connect(int r19, int r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 787
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.usb.BrUsbSocket.connect(int, int):boolean");
    }

    @Override
    public void close() throws Throwable {
        if (this.mConnection != null) {
            if (this.mInterfaceType == BrotherUsbInterface.Scanner) {
                UsbDebug.debug("[Info] requestType : 192");
                byte[] bArr = new byte[16];
                int iControlTransfer = this.mConnection.controlTransfer(192, 2, 2, 0, bArr, 16, 2000);
                UsbDebug.debug("[Info] controlTransfer (in): " + iControlTransfer);
                UsbDebug.debugHex(bArr, iControlTransfer);
            }
            this.mConnection.releaseInterface(this.mInterface);
            this.mConnection.close();
        }
    }

    @Override
    public void cancel() throws IOException {
        this.mCancel = true;
        UsbControllerManager.UsbController.UsbDeviceConnector usbDeviceConnector = this.mDeviceConnector;
        if (usbDeviceConnector != null) {
            usbDeviceConnector.cancelConnection();
        }
    }

    @Override
    public void setSoTimeout(int i) throws IOException {
        this.mTimeout = i;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || ISocket.f505ID.equals(str)) {
            return this;
        }
        return null;
    }

    @Override
    public ISocket.ConnectionType getConnectionType() {
        return ISocket.ConnectionType.USB;
    }

    private int getAlternateSetting(UsbInterface usbInterface) throws Throwable {
        UsbDebug.debug("[Info] Build Version : " + Build.VERSION.SDK_INT);
        int alternateSetting = usbInterface.getAlternateSetting();
        UsbDebug.debug("[Info] UsbInterface Alternate : " + alternateSetting);
        return alternateSetting;
    }

    private static class UsbInterfaceInfo {
        public int mAlternate;
        public int mClass;
        public int mIndex;

        private UsbInterfaceInfo() {
        }
    }
}
