package com.hiti.usb.jni;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.hiti.usb.printer.CardCalibrationValue;
import com.hiti.usb.printer.PrintPara;
import com.hiti.usb.service.usbPrinter.UsbHost;
import com.hiti.usb.utility.FileUtility;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsbCommand {
    private static final String tag = "UsbCommand";
    private static UsbCommand usbCmd = new UsbCommand();

    static class C15891 {
        static final int[] $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc;

        static {
            int[] iArr = new int[SubFunc.values().length];
            $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc = iArr;
            try {
                iArr[SubFunc.HITI_GET_OBJECT_STORAGE_ID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc[SubFunc.HITI_GET_OBJECT_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc[SubFunc.HITI_GET_OBJECT_HANDLE_ID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc[SubFunc.HITI_GET_OBJECT_INFO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc[SubFunc.HITI_GET_OBJECT_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public enum Function {
        HITI_CHECK_PRINTER_STATUS,
        HITI_DO_COMMAND,
        HITI_GET_DEVICE_INFO,
        HITI_PRINT_ONE_PAGE,
        HITI_UPDATE_FIRMWARE,
        HITI_PRINT_ONE_CARD,
        HTTI_PRINT_CARD_CALIBRATION_CHART,
        HTTI_SET_CARD_CALIBRATION_VALUE,
        HITI_GET_OBJECT,
        HITI_SET_COMMAND,
        HITI_UPDATE_FIRMWARE_P525N
    }

    public enum SubFunc {
        HITI_DEVINFO_MODEL_NAME(0),
        HITI_DEVINFO_MFG_SERIAL(1),
        HITI_DEVINFO_FIRMWARE_VERSION(2),
        HITI_DEVINFO_RIBBON_INFO(3),
        HITI_DEVINFO_PRINT_COUNT(4),
        HITI_DEVINFO_CARD_POSITION(5),
        HITI_DEVINFO_CARD_CALIBRATION_VALUE(6),
        HITI_DEVINFO_AUTO_POWER(7),
        HITI_DEVINFO_JOBS_IN_QUEUE(1000),
        HITI_GET_OBJECT_STORAGE_ID(201),
        HITI_GET_OBJECT_NUMBER(202),
        HITI_GET_OBJECT_HANDLE_ID(203),
        HITI_GET_OBJECT_INFO(204),
        HITI_GET_OBJECT_DATA(205),
        HITI_COMMAND_RESET_PRINTER(1),
        HITI_COMMAND_RESUME_JOB(2),
        HITI_COMMAND_EJECT_PAPER_JAM(3),
        HITI_COMMAND_SET_AUTO_POWER_OFF(4),
        HITI_COMMAND_CLEAN_PAPER_PATH(5),
        MOVE_CARD_TO_IC_ENCODER(101),
        MOVE_CARD_TO_RFID_ENCODER(102),
        MOVE_CARD_TO_REJECT_BOX(103),
        MOVE_CARD_TO_HOPPER(104),
        MOVE_CARD_TO_FLIPPER(ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE),
        MOVE_CARD_TO_PRINT_FROM_FLIPPER(106),
        MOVE_CARD_TO_STANDBY_POSITION(ErrorCode.PrinterError.EPS_PRNERR_JPG_LIMIT),
        MOVE_CARD_TO_EJECT_CARD_FROM_FLIPPER(108);

        private int value;

        SubFunc(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    static {
        System.loadLibrary("HiTiApi");
    }

    private UsbCommand() {
    }

    public static JniData USB_CheckPrinterStatus(Object obj, String str) {
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_CheckPrinterStatus((UsbHost) obj, str);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_CheckPrinterStatus(((Integer) obj).intValue());
        }
        return null;
    }

    public static JniData USB_DoCommand(Object obj, SubFunc subFunc) {
        int value = subFunc.getValue();
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_DoCommand((UsbHost) obj, value);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_DoCommand(((Integer) obj).intValue(), value);
        }
        return null;
    }

    public static JniData USB_GetDeviceInfo(Object obj, SubFunc subFunc, String str) {
        int value = subFunc.getValue();
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_GetDeviceInfo((UsbHost) obj, value, str);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_GetDeviceInfo(((Integer) obj).intValue(), value);
        }
        return null;
    }

    public static JniData USB_GetObject(Object obj, SubFunc subFunc, PrintPara printPara) {
        UsbCommand usbCommand;
        UsbHost usbHost;
        long storageId;
        byte format;
        long objectId;
        int value = subFunc.getValue();
        if (!(obj instanceof UsbHost)) {
            return null;
        }
        int i = C15891.$SwitchMap$com$hiti$usb$jni$UsbCommand$SubFunc[subFunc.ordinal()];
        if (i == 1) {
            usbCommand = usbCmd;
            usbHost = (UsbHost) obj;
            storageId = 0;
            format = -1;
            objectId = -1;
        } else {
            if ((i != 2 && i != 3 && i != 4 && i != 5) || printPara == null) {
                return null;
            }
            usbCommand = usbCmd;
            usbHost = (UsbHost) obj;
            storageId = printPara.getStorageId();
            format = printPara.getFormat();
            objectId = printPara.getObjectId();
        }
        return usbCommand.HITI_GetObject(usbHost, value, storageId, format, objectId);
    }

    public static JniData USB_PrintCardCalibrationChart(Object obj, CardCalibrationValue cardCalibrationValue) {
        if (!(obj instanceof UsbHost) && (obj instanceof Integer)) {
            return usbCmd.HITI_PrintCardCalibrationChart(((Integer) obj).intValue(), cardCalibrationValue.posA, cardCalibrationValue.posB, cardCalibrationValue.posC);
        }
        return null;
    }

    public static JniData USB_PrintOneCard(Object obj, PrintPara printPara) {
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_PrintOneCard((UsbHost) obj, printPara);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_PrintOneCard(((Integer) obj).intValue(), printPara);
        }
        return null;
    }

    public static JniData USB_PrintOnePage(Object obj, PrintPara printPara) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        FileUtility.WriteFile(printPara.getTableRoot() + "/debug_" + simpleDateFormat.format(date) + "_log", "[" + simpleDateFormat2.format(date) + "] printerService.aar->UsbCommand->USB_PrintOnePage() \n");
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_PrintOnePage((UsbHost) obj, printPara);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_PrintOnePage(((Integer) obj).intValue(), printPara);
        }
        return null;
    }

    public static JniData USB_SetCardCalibration(Object obj, CardCalibrationValue cardCalibrationValue) {
        if (!(obj instanceof UsbHost) && (obj instanceof Integer)) {
            return usbCmd.HITI_SetCardCalibrateValue(((Integer) obj).intValue(), cardCalibrationValue.posA, cardCalibrationValue.posB, cardCalibrationValue.posC);
        }
        return null;
    }

    public static JniData USB_SetCommand(Object obj, SubFunc subFunc, PrintPara printPara) {
        int value = subFunc.getValue();
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_SetCommand((UsbHost) obj, value, printPara.getAutoPowerOffSeconds());
        }
        return null;
    }

    public static JniData USB_UpdateFirmware(Object obj, String str) {
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_UpdateFirmware((UsbHost) obj, str);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_UpdateFirmware(((Integer) obj).intValue(), str);
        }
        return null;
    }

    public static JniData USB_UpdateFirmwareP525N(Object obj, String str, String str2, String str3) {
        if (obj instanceof UsbHost) {
            return usbCmd.HITI_UpdateFirmware3((UsbHost) obj, str, str2, str3);
        }
        if (obj instanceof Integer) {
            return usbCmd.HITI_UpdateFirmware3(((Integer) obj).intValue(), str, str2, str3);
        }
        return null;
    }

    public native JniData HITI_CheckPrinterStatus(int i);

    public native JniData HITI_CheckPrinterStatus(UsbHost usbHost, String str);

    public native JniData HITI_DoCommand(int i, int i2);

    public native JniData HITI_DoCommand(UsbHost usbHost, int i);

    public native JniData HITI_GetDeviceInfo(int i, int i2);

    public native JniData HITI_GetDeviceInfo(UsbHost usbHost, int i, String str);

    public native JniData HITI_GetObject(UsbHost usbHost, int i, long j, byte b, long j2);

    public native JniData HITI_PrintCardCalibrationChart(int i, int i2, int i3, int i4);

    public native JniData HITI_PrintCardCalibrationChart(UsbHost usbHost, int i, int i2, int i3);

    public native JniData HITI_PrintOneCard(int i, PrintPara printPara);

    public native JniData HITI_PrintOneCard(UsbHost usbHost, PrintPara printPara);

    public native JniData HITI_PrintOnePage(int i, PrintPara printPara);

    public native JniData HITI_PrintOnePage(UsbHost usbHost, PrintPara printPara);

    public native JniData HITI_SetCardCalibrateValue(int i, int i2, int i3, int i4);

    public native JniData HITI_SetCardCalibrateValue(UsbHost usbHost, int i, int i2, int i3);

    public native JniData HITI_SetCommand(UsbHost usbHost, int i, short s);

    public native JniData HITI_UpdateFirmware(int i, String str);

    public native JniData HITI_UpdateFirmware(UsbHost usbHost, String str);

    public native JniData HITI_UpdateFirmware3(int i, String str, String str2, String str3);

    public native JniData HITI_UpdateFirmware3(UsbHost usbHost, String str, String str2, String str3);
}
