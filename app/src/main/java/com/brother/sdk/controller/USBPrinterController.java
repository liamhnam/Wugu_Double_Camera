package com.brother.sdk.controller;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;

public class USBPrinterController {
    UsbDeviceConnection mConnection;
    UsbEndpoint mInEndpoint;
    UsbEndpoint mOutEndpoint;
    UsbDevice mUsbDevice;
    private final String PJL_GetPrinterStatus = "\u001b%-12345X@PJL\r\n@PJL INFO STATUS\r\n\u001b%-12345X";
    private final String PJL_Restart = "\u001b%-12345X@PJL\r\n@PJL EXECUTE MFCREBOOT\r\n\u001b%-12345X";
    private final String PJL_GetPrinterModel = "\u001b%-12345X@PJL\r\n@PJL INFO ID\r\n\u001b%-12345X\r\n";
    private final String PJL_AutoSleepOff = "\u001b%-12345X@PJL\r\n@PJL DEFAULT DEEPSLEEP=OFF\r\n\u001b%-12345X\r\n";
    private final String PJL_SERIAL_NUMBER = "\u001b%-12345X@PJL\r\n@PJL DINQUIRE SERIALNUMPRN\r\n\u001b%-12345X";
    private final String PJLRemainToner_Color_C = getDINQUIREPJLString("REMAINCTONER");
    private final String PJLRemainToner_Color_M = getDINQUIREPJLString("REMAINMTONER");
    private final String PJLRemainToner_Color_Y = getDINQUIREPJLString("REMAINYTONER");
    private final String PJLRemainToner_Color_K = getDINQUIREPJLString("REMAINKTONER");
    private final String PJLExpectedDrumLife = getDINQUIREPJLString("NEXTCAREDRM");
    private final String PJLCleanNormalALL = getEXECUTEPJLString("NORMALPURGE=ALL");

    public USBPrinterController(UsbDevice usbDevice, UsbDeviceConnection usbDeviceConnection) throws Exception {
        this.mUsbDevice = usbDevice;
        this.mConnection = usbDeviceConnection;
        init();
    }

    private void init() throws Exception {
        UsbDevice usbDevice = this.mUsbDevice;
        if (usbDevice != null) {
            UsbInterface usbInterface = usbDevice.getInterface(0);
            for (int i = 0; i < usbInterface.getEndpointCount(); i++) {
                UsbEndpoint endpoint = usbInterface.getEndpoint(i);
                if (endpoint.getType() == 2) {
                    if (endpoint.getDirection() == 128) {
                        this.mInEndpoint = endpoint;
                    } else {
                        this.mOutEndpoint = endpoint;
                    }
                }
            }
            return;
        }
        throw new Exception("USB Device is null");
    }

    private String getDINQUIREPJLString(String str) {
        return "\u001b%-12345X@PJL\r\n@PJL DINQUIRE " + str + "\r\n\u001b%-12345X";
    }

    private String getEXECUTEPJLString(String str) {
        return "\u001b%-12345X@PJL\r\n@PJL EXECUTE " + str + "\r\n\u001b%-12345X";
    }

    public String getPrinterStatus() throws Exception {
        return doPJL("\u001b%-12345X@PJL\r\n@PJL INFO STATUS\r\n\u001b%-12345X", "Get Printer Status");
    }

    public String restartPrinter() throws Exception {
        return doPJL("\u001b%-12345X@PJL\r\n@PJL EXECUTE MFCREBOOT\r\n\u001b%-12345X", "Restart Printer");
    }

    public String cleanPrintHead() throws Exception {
        return doPJL(this.PJLCleanNormalALL, "Clean Printer Head");
    }

    public String getPrinterID() throws Exception {
        return doPJL("\u001b%-12345X@PJL\r\n@PJL INFO ID\r\n\u001b%-12345X\r\n", "Get PrinterID");
    }

    public String offPrinterAutoSleep() throws Exception {
        return doPJL("\u001b%-12345X@PJL\r\n@PJL DEFAULT DEEPSLEEP=OFF\r\n\u001b%-12345X\r\n", "Off AutoSleep");
    }

    public String getPjlSerialNumber() throws Exception {
        return doPJL("\u001b%-12345X@PJL\r\n@PJL DINQUIRE SERIALNUMPRN\r\n\u001b%-12345X", "Get Printer SN");
    }

    public String getInkLevel_C() throws Exception {
        return doPJL(this.PJLRemainToner_Color_C, "Get INK LEVEL Blue");
    }

    public String getInkLevel_M() throws Exception {
        return doPJL(this.PJLRemainToner_Color_M, "Get INK LEVEL RED");
    }

    public String getInkLevel_Y() throws Exception {
        return doPJL(this.PJLRemainToner_Color_Y, "Get INK LEVEL Yellow");
    }

    public String getInkLevel_K() throws Exception {
        return doPJL(this.PJLRemainToner_Color_K, "Get INK LEVEL Black");
    }

    public String getDrumLife() throws Exception {
        return doPJL(this.PJLExpectedDrumLife, "GET Drum Life");
    }

    private String doPJL(String str, String str2) {
        try {
            sendData(str);
            return receiveData();
        } catch (Exception e) {
            e.printStackTrace();
            return str2 + " with exception";
        }
    }

    private void sendData(String str) throws Exception {
        if (this.mConnection != null) {
            synchronized (this) {
                byte[] bytes = str.getBytes();
                this.mConnection.bulkTransfer(this.mOutEndpoint, bytes, bytes.length, 100);
            }
            new waitThread().start();
            return;
        }
        throw new Exception("Connection is null");
    }

    private String receiveData() throws Exception {
        String str;
        while (true) {
            synchronized (this) {
                byte[] bArr = new byte[1024];
                UsbDeviceConnection usbDeviceConnection = this.mConnection;
                if (usbDeviceConnection != null) {
                    int iBulkTransfer = usbDeviceConnection.bulkTransfer(this.mInEndpoint, bArr, 1024, 1000);
                    if (iBulkTransfer > 0) {
                        str = new String(bArr, 0, iBulkTransfer);
                        if (str.lastIndexOf("@") != -1) {
                            str = str.substring(str.lastIndexOf("@"));
                        }
                        if (!str.isEmpty()) {
                        }
                    }
                } else {
                    str = "USB not connected";
                }
            }
        }
        return str;
    }

    static class waitThread extends Thread {
        waitThread() {
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public UsbDeviceConnection getConnection() {
        return this.mConnection;
    }

    public void setConnection(UsbDeviceConnection usbDeviceConnection) {
        this.mConnection = usbDeviceConnection;
    }

    public UsbDevice getUsbDevice() {
        return this.mUsbDevice;
    }

    public void setUsbDevice(UsbDevice usbDevice) {
        this.mUsbDevice = usbDevice;
    }
}
