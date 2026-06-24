package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

public class CameraUsbInfo {
    private final UsbEndpoint bulkIn;
    private final UsbEndpoint bulkOut;
    private final UsbDeviceConnection usbDeviceConnection;
    private final UsbInterface usbInterface;

    public CameraUsbInfo(UsbInterface usbInterface, UsbDeviceConnection usbDeviceConnection, UsbEndpoint usbEndpoint, UsbEndpoint usbEndpoint2) {
        this.usbInterface = usbInterface;
        this.usbDeviceConnection = usbDeviceConnection;
        this.bulkOut = usbEndpoint;
        this.bulkIn = usbEndpoint2;
    }

    public UsbInterface getUsbInterface() {
        return this.usbInterface;
    }

    public UsbDeviceConnection getUsbDeviceConnection() {
        return this.usbDeviceConnection;
    }

    public UsbEndpoint getBulkOut() {
        return this.bulkOut;
    }

    public UsbEndpoint getBulkIn() {
        return this.bulkIn;
    }

    public int getMaxPacketInSize() {
        return this.bulkIn.getMaxPacketSize();
    }

    public int getMaxPacketOutSize() {
        return this.bulkOut.getMaxPacketSize();
    }

    public int bulkTransferOut(byte[] bArr, int i, int i2) {
        return this.usbDeviceConnection.bulkTransfer(this.bulkOut, bArr, i, i2);
    }

    public int bulkTransferIn(byte[] bArr, int i, int i2) {
        return this.usbDeviceConnection.bulkTransfer(this.bulkIn, bArr, i, i2);
    }
}
