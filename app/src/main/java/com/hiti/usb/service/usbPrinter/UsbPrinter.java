package com.hiti.usb.service.usbPrinter;

import android.content.Context;
import android.util.Log;
import com.hiti.usb.jni.JniData;
import com.hiti.usb.jni.UsbCommand;
import com.hiti.usb.printer.PrinterStatus;
import com.hiti.usb.service.ErrorCode;
import com.hiti.usb.service.network.INet;
import com.hiti.usb.service.usbPrinter.UsbHost;
import hiti.p035a.EnumC2094b;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsbPrinter {
    private static final boolean localLOG = true;
    private static final String tag = "UsbPrinter";
    private Context context;
    public String m_strTablesRoot;
    private UsbHost usbHost;

    static class C15971 {
        static final int[] $SwitchMap$com$hiti$usb$jni$UsbCommand$Function;

        static {
            int[] iArr = new int[UsbCommand.Function.values().length];
            $SwitchMap$com$hiti$usb$jni$UsbCommand$Function = iArr;
            try {
                iArr[UsbCommand.Function.HITI_CHECK_PRINTER_STATUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_PRINT_ONE_PAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_DO_COMMAND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_GET_DEVICE_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_UPDATE_FIRMWARE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_PRINT_ONE_CARD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HTTI_PRINT_CARD_CALIBRATION_CHART.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HTTI_SET_CARD_CALIBRATION_VALUE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_GET_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_SET_COMMAND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$Function[UsbCommand.Function.HITI_UPDATE_FIRMWARE_P525N.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private UsbPrinter(Context context, UsbHost.DeviceIdentifier deviceIdentifier) {
        this.context = context;
        this.usbHost = new UsbHost(this.context, deviceIdentifier);
    }

    public static void disconnect(UsbPrinter usbPrinter) {
        usbPrinter.usbHost.unInstall();
    }

    public static UsbPrinter getUsbPrinter(Context context, UsbHost.DeviceIdentifier deviceIdentifier) {
        return new UsbPrinter(context, deviceIdentifier);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.hiti.usb.jni.JniData callJniUsbCommand(com.hiti.usb.jni.UsbCommand.Function r13, com.hiti.usb.jni.UsbCommand.SubFunc r14, java.lang.Object r15) {
        /*
            Method dump skipped, instruction units count: 1382
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hiti.usb.service.usbPrinter.UsbPrinter.callJniUsbCommand(com.hiti.usb.jni.UsbCommand$Function, com.hiti.usb.jni.UsbCommand$SubFunc, java.lang.Object):com.hiti.usb.jni.JniData");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.hiti.usb.printer.PrinterJob callJniUsbCommand(com.hiti.usb.printer.PrinterJob r13) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hiti.usb.service.usbPrinter.UsbPrinter.callJniUsbCommand(com.hiti.usb.printer.PrinterJob):com.hiti.usb.printer.PrinterJob");
    }

    public JniData callJniUsbCommand3(UsbCommand.Function function, UsbCommand.SubFunc subFunc, Object obj, Object obj2, Object obj3) {
        StringBuilder sbAppend;
        JniData jniData;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        simpleDateFormat.format(date);
        simpleDateFormat2.format(date);
        JniData jniDataUSB_UpdateFirmwareP525N = null;
        if (function == null) {
            return new JniData(ErrorCode.ERR_CODE_SERVICE_NOT_SUPPORT, (Object) null);
        }
        String str = tag;
        Log.v(str, "callJniUsbCommand(): Thread( " + Thread.currentThread().getId() + " ), action= " + function.name());
        if (C15971.$SwitchMap$com$hiti$usb$jni$UsbCommand$Function[function.ordinal()] == 11) {
            if (obj instanceof String) {
                String str2 = (String) obj;
                String str3 = (String) obj2;
                String str4 = (String) obj3;
                File file = new File(str2);
                Log.v(str, "UPDATE_FIRMWARE rfs: binary path: " + str2);
                Log.v(str, "UPDATE_FIRMWARE boot:  binary path: " + str3);
                Log.v(str, "UPDATE_FIRMWARE kernel:  binary path: " + str4);
                if (file.exists()) {
                    jniDataUSB_UpdateFirmwareP525N = UsbCommand.USB_UpdateFirmwareP525N(this.usbHost, str2, str3, str4);
                } else {
                    jniData = new JniData(ErrorCode.ERR_CODE_FIRMWARE_NO_BIN, (Object) null);
                }
            } else {
                jniData = new JniData(ErrorCode.ERR_CODE_INVALID_PARAMETER, (Object) null);
            }
            jniDataUSB_UpdateFirmwareP525N = jniData;
        }
        if (!(jniDataUSB_UpdateFirmwareP525N.getRetData() instanceof String)) {
            if (jniDataUSB_UpdateFirmwareP525N.getRetData() instanceof JniData.IntArray) {
                for (int i = 0; i < ((JniData.IntArray) jniDataUSB_UpdateFirmwareP525N.getRetData()).getSize(); i++) {
                    Log.i(tag, "callJniUsbCommand return data: " + ((JniData.IntArray) jniDataUSB_UpdateFirmwareP525N.getRetData()).get(i));
                }
            } else if (jniDataUSB_UpdateFirmwareP525N.getRetData() instanceof PrinterStatus) {
                sbAppend = new StringBuilder("callJniUsbCommand~ return data: ").append(((PrinterStatus) jniDataUSB_UpdateFirmwareP525N.getRetData()).statusValue);
            } else if (jniDataUSB_UpdateFirmwareP525N.getRetData() != null) {
                sbAppend = new StringBuilder("callJniUsbCommand return data: ").append(jniDataUSB_UpdateFirmwareP525N.getRetData().toString());
            }
            Log.i(tag, "callJniUsbCommand (" + function.name() + ") return error code: " + Integer.toHexString(jniDataUSB_UpdateFirmwareP525N.getErrorCode().value));
            return jniDataUSB_UpdateFirmwareP525N;
        }
        sbAppend = new StringBuilder("callJniUsbCommand return data: ").append(jniDataUSB_UpdateFirmwareP525N.getRetData());
        Log.i(str, sbAppend.toString());
        Log.i(tag, "callJniUsbCommand (" + function.name() + ") return error code: " + Integer.toHexString(jniDataUSB_UpdateFirmwareP525N.getErrorCode().value));
        return jniDataUSB_UpdateFirmwareP525N;
    }

    public EnumC2094b getProductId() {
        return this.usbHost.getUsbDeviceFd() == -1 ? EnumC2094b.UNKNOWN : EnumC2094b.m1274a(this.usbHost.getProductId());
    }

    public void setListener(INet.IUpload iUpload) {
        this.usbHost.setListener(iUpload);
    }
}
