package com.brother.sdk.usb.discovery;

import android.text.TextUtils;
import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.brother.sdk.common.presets.BrotherDeviceBuilder;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandDeviceConfiguration;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import com.brother.sdk.usb.BrUsbDevice;
import com.brother.sdk.usb.BrUsbSocket;
import com.brother.sdk.usb.BrotherUsbInterface;
import com.brother.sdk.usb.UsbConnector;
import com.brother.sdk.usb.UsbDebug;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class UsbConnectorDescriptor extends ConnectorDescriptor {
    private final BrUsbDevice mDevice;

    public UsbConnectorDescriptor(BrUsbDevice brUsbDevice) {
        this.mDevice = brUsbDevice;
    }

    @Override
    public IConnector createConnector(CountrySpec countrySpec) throws Throwable {
        try {
            UsbDebug.debug("Start create UsbConnector.");
            if (this.mDevice.mProductName.equals(BrUsbDevice.USB_DEFAULT_PRODUCT)) {
                BrUsbDevice brUsbDevice = this.mDevice;
                brUsbDevice.mProductName = getModelName(brUsbDevice);
            }
            Device device = new Device();
            device.modelName = this.mDevice.mProductName;
            device.nodeName = this.mDevice.mSerialNumber;
            device.countrySpec = countrySpec;
            boolean zValidatePrint = validatePrint(device);
            if (validateScan(device)) {
                zValidatePrint = true;
            }
            if (zValidatePrint) {
                return new UsbConnector(this.mDevice, device);
            }
            return null;
        } catch (Exception e) {
            UsbDebug.debug(e.getMessage());
            return null;
        }
    }

    @Override
    public String getModelName() {
        return this.mDevice.mProductName;
    }

    @Override
    public String getDescriptorIdentifier() {
        return this.mDevice.mSerialNumber;
    }

    @Override
    public boolean support(ConnectorDescriptor.Function function) {
        if (function == ConnectorDescriptor.Function.Scan) {
            return BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(this.mDevice.mProductName).scanSupport == BrotherDeviceMasterSpec.ScanSupport.On;
        }
        if (function != ConnectorDescriptor.Function.Print) {
            return false;
        }
        BrotherDeviceMasterSpec brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(this.mDevice.mProductName);
        return brotherDeviceMasterSpec.deviceModelType.isDirectPrintSupported() || brotherDeviceMasterSpec.deviceModelType.isUsbSupported();
    }

    private String getModelName(BrUsbDevice brUsbDevice) throws Throwable {
        BrUsbSocket brUsbSocket;
        ?? r2;
        UsbDebug.debug("Start checking PJL for Model Name");
        String strSubstring = "";
        ?? r22 = 0;
        r22 = 0;
        r22 = 0;
        try {
            try {
                try {
                    UsbDebug.debug("Start connecting USB");
                    brUsbSocket = new BrUsbSocket(brUsbDevice, BrotherUsbInterface.Printer);
                    r2 = 3;
                } catch (IOException e) {
                    e.printStackTrace();
                    r22 = r22;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (brUsbSocket.connect(10000, 3)) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write("%-12345X".getBytes());
                byteArrayOutputStream.write("@PJL\r\n@PJL INFO BRFIRMWARE\r\n".getBytes());
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write("%-12345X".getBytes());
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                brUsbSocket.write(byteArray, 0, byteArray.length);
                Thread.sleep(1000L);
                UsbDebug.debug("[INFO] Start Read Buffer");
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                for (int i = 0; i < 20; i++) {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i2 = brUsbSocket.read(bArr, 0, 1024);
                        if (i2 <= 0) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, i2);
                    }
                    Thread.sleep(1000L);
                    if (byteArrayOutputStream2.size() > 0) {
                        break;
                    }
                }
                String str = new String(byteArrayOutputStream2.toByteArray(), Charset.defaultCharset());
                for (String str2 : str.split("\n")) {
                    if (str2.contains("MODEL=")) {
                        strSubstring = str2.substring(str2.indexOf("=") + 1);
                    }
                }
                UsbDebug.debug("[INFO] Selected Model : " + strSubstring);
                UsbDebug.debug(str);
                r2 = "[INFO] Selected Model : ";
            }
            brUsbSocket.close();
            r22 = r2;
        } catch (Exception e3) {
            e = e3;
            r22 = brUsbSocket;
            e.printStackTrace();
            if (r22 != 0) {
                r22.close();
                r22 = r22;
            }
            return simplyProductName(strSubstring);
        } catch (Throwable th2) {
            th = th2;
            r22 = brUsbSocket;
            if (r22 != 0) {
                try {
                    r22.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
        return simplyProductName(strSubstring);
    }

    private boolean validatePrint(Device device) throws Throwable {
        UsbDebug.debug("Validate print functions.");
        BrotherDeviceMasterSpec brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(getModelName());
        if (!brotherDeviceMasterSpec.deviceModelType.isDirectPrintSupported() && !brotherDeviceMasterSpec.deviceModelType.isUsbSupported()) {
            return false;
        }
        UsbDebug.debug("MasterSpec Device is " + brotherDeviceMasterSpec.deviceModelType.toString());
        device.printer = BrotherDeviceBuilder.generatePrinterFromMasterSpec(brotherDeviceMasterSpec, device.countrySpec);
        return true;
    }

    private boolean validateScan(Device device) throws Throwable {
        BrotherDeviceMasterSpec brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(getModelName());
        if ((brotherDeviceMasterSpec.deviceModelType.isDirectPrintSupported() || brotherDeviceMasterSpec.deviceModelType.isUsbSupported()) && brotherDeviceMasterSpec.scanSupport == BrotherDeviceMasterSpec.ScanSupport.Off) {
            return false;
        }
        UsbDebug.debug("Validate scan functions.");
        ScanCommandDeviceConfiguration scannerCapabilities = getScannerCapabilities();
        if (scannerCapabilities == null || scannerCapabilities.ScanModes == null) {
            return false;
        }
        UsbDebug.debug("Start validating with ScanCommandDeviceConfiguration.");
        UsbDebug.debug("MasterSpec Device is " + brotherDeviceMasterSpec.deviceModelType.toString());
        if (brotherDeviceMasterSpec.scanAutoDocumentSizeSupport == null) {
            brotherDeviceMasterSpec.scanAutoDocumentSizeSupport = scannerCapabilities.AutoDocumentSizeScanSupport ? BrotherDeviceMasterSpec.AutoDocumentSizeScanSupport.On : BrotherDeviceMasterSpec.AutoDocumentSizeScanSupport.Off;
        }
        if (brotherDeviceMasterSpec.scanCornerScanSupport == null) {
            brotherDeviceMasterSpec.scanCornerScanSupport = (scannerCapabilities.SpecialScanModes == null || !scannerCapabilities.SpecialScanModes.contains(ScanSpecialMode.CORNER_SCAN)) ? BrotherDeviceMasterSpec.CornerScanSupport.Off : BrotherDeviceMasterSpec.CornerScanSupport.On;
        }
        if (brotherDeviceMasterSpec.scanColorSupport == null) {
            boolean zContains = scannerCapabilities.ScanModes.contains(ScanCommandParameters.ScanMode.CGRAY);
            boolean zContains2 = scannerCapabilities.ScanModes.contains(ScanCommandParameters.ScanMode.TEXT);
            if (zContains && zContains2) {
                brotherDeviceMasterSpec.scanColorSupport = BrotherDeviceMasterSpec.ColorSupport.ColorMono;
            } else if (zContains) {
                brotherDeviceMasterSpec.scanColorSupport = BrotherDeviceMasterSpec.ColorSupport.ColorOnly;
            } else if (zContains2) {
                brotherDeviceMasterSpec.scanColorSupport = BrotherDeviceMasterSpec.ColorSupport.Monochrome;
            }
            if (brotherDeviceMasterSpec.scanColorSupport == null) {
                return false;
            }
        }
        if (brotherDeviceMasterSpec.scanGroundColorRemoveSupport == null) {
            brotherDeviceMasterSpec.scanGroundColorRemoveSupport = scannerCapabilities.GroundColorCorrectionSupport ? BrotherDeviceMasterSpec.GroundColorRemoveScanSupport.On : BrotherDeviceMasterSpec.GroundColorRemoveScanSupport.Off;
        }
        if (brotherDeviceMasterSpec.scanDocumentSizeSupport == null) {
            brotherDeviceMasterSpec.scanDocumentSizeSupport = scannerCapabilities.modelSize;
        }
        if (brotherDeviceMasterSpec.scanDuplexSupport == null) {
            brotherDeviceMasterSpec.scanDuplexSupport = isDuplexSupport(scannerCapabilities) ? BrotherDeviceMasterSpec.DuplexSupport.On : BrotherDeviceMasterSpec.DuplexSupport.Off;
            UsbDebug.debug("Scan duplex is specified");
        }
        device.scanner = BrotherDeviceBuilder.generateScannerFromMasterSpec(brotherDeviceMasterSpec);
        device.scanner.modelVerticalAlignment = scannerCapabilities.FB_V;
        device.scanner.modelHorizontalAlignment = scannerCapabilities.FB_H;
        device.scanner.modelVerticalAlignmentADF = scannerCapabilities.ADF_V;
        device.scanner.modelHorizontalAlignmentADF = scannerCapabilities.ADF_H;
        device.scanner.protocolVersion = scannerCapabilities.ScanProtocol.toValue();
        device.scanner.longLengthScanSupport = scannerCapabilities.LongEdgeScan;
        return true;
    }

    private static boolean isDuplexSupport(ScanCommandDeviceConfiguration scanCommandDeviceConfiguration) throws Throwable {
        if (scanCommandDeviceConfiguration.ADFDuplexMaxScanWidth == null || scanCommandDeviceConfiguration.ADFDuplexMaxScanWidth.intValue() <= 0 || scanCommandDeviceConfiguration.ADFDuplexMaxScanHeight == null || scanCommandDeviceConfiguration.ADFDuplexMaxScanHeight.intValue() <= 0 || scanCommandDeviceConfiguration.ADFDuplexMinScanWidth == null || scanCommandDeviceConfiguration.ADFDuplexMinScanWidth.intValue() <= 0 || scanCommandDeviceConfiguration.ADFDuplexMinScanHeight == null || scanCommandDeviceConfiguration.ADFDuplexMinScanHeight.intValue() <= 0) {
            return false;
        }
        UsbDebug.debug("Scan duplex is on.");
        return true;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.brother.sdk.common.socket.scan.scancommand.ScanCommandDeviceConfiguration getScannerCapabilities() throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = "[INFO] Start Check Scanner"
            com.brother.sdk.usb.UsbDebug.debug(r0)
            r0 = 0
            com.brother.sdk.common.socket.scan.scancommand.ScanCommandClient r1 = new com.brother.sdk.common.socket.scan.scancommand.ScanCommandClient     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            com.brother.sdk.usb.discovery.UsbConnectorDescriptor$1 r2 = new com.brother.sdk.usb.discovery.UsbConnectorDescriptor$1     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            r2.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L44
            com.brother.sdk.usb.BrUsbSocket r2 = new com.brother.sdk.usb.BrUsbSocket     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            com.brother.sdk.usb.BrUsbDevice r3 = r6.mDevice     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            com.brother.sdk.usb.BrotherUsbInterface r4 = com.brother.sdk.usb.BrotherUsbInterface.Scanner     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r2.<init>(r3, r4)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            boolean r2 = r1.bind(r2)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            if (r2 == 0) goto L39
            com.brother.sdk.common.socket.scan.scancommand.ScanCommandQueryDeviceConfiguration r2 = new com.brother.sdk.common.socket.scan.scancommand.ScanCommandQueryDeviceConfiguration     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r2.<init>()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            com.brother.sdk.common.socket.scan.ScanState r3 = r1.requestCommand(r2)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            com.brother.sdk.common.socket.scan.ScanState r4 = com.brother.sdk.common.socket.scan.ScanState.Success     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            if (r3 != r4) goto L39
            com.brother.sdk.common.socket.scan.scancommand.ScanCommandDeviceConfiguration r0 = r2.getResult()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L58
            r1.close()     // Catch: java.io.IOException -> L34
            goto L38
        L34:
            r1 = move-exception
            r1.printStackTrace()
        L38:
            return r0
        L39:
            r1.close()     // Catch: java.io.IOException -> L53
            goto L57
        L3d:
            r2 = move-exception
            goto L46
        L3f:
            r1 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L59
        L44:
            r2 = move-exception
            r1 = r0
        L46:
            java.lang.String r2 = r2.getMessage()     // Catch: java.lang.Throwable -> L58
            com.brother.sdk.usb.UsbDebug.debug(r2)     // Catch: java.lang.Throwable -> L58
            if (r1 == 0) goto L57
            r1.close()     // Catch: java.io.IOException -> L53
            goto L57
        L53:
            r1 = move-exception
            r1.printStackTrace()
        L57:
            return r0
        L58:
            r0 = move-exception
        L59:
            if (r1 == 0) goto L63
            r1.close()     // Catch: java.io.IOException -> L5f
            goto L63
        L5f:
            r1 = move-exception
            r1.printStackTrace()
        L63:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.usb.discovery.UsbConnectorDescriptor.getScannerCapabilities():com.brother.sdk.common.socket.scan.scancommand.ScanCommandDeviceConfiguration");
    }

    private String simplyProductName(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replace(" series", "").replace("Brother ", "");
    }
}
