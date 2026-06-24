package com.hiti.usb.service.usbPrinter;

import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import com.google.android.material.internal.ViewUtils;
import com.hiti.usb.jni.UsbCommand;
import com.hiti.usb.printer.HitiPrinter;
import com.hiti.usb.printer.PrinterStatus;
import com.hiti.usb.service.Action;
import com.p020hp.jipp.model.Status;
import java.util.EnumMap;
import java.util.Map;

public class P310w extends HitiPrinter {
    private static SparseArray<String> ErroCodeMap;
    private static Map<Action, UsbCommand.Function> UsbPrinterFuncMap;
    private static Map<Action, UsbCommand.SubFunc> UsbPrinterSubFuncMap;

    static {
        EnumMap enumMap = new EnumMap(Action.class);
        UsbPrinterFuncMap = enumMap;
        enumMap.put(Action.USB_CHECK_PRINTER_STATUS, UsbCommand.Function.HITI_CHECK_PRINTER_STATUS);
        UsbPrinterFuncMap.put(Action.USB_PRINT_PHOTOS, UsbCommand.Function.HITI_PRINT_ONE_PAGE);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_RESET_PRINTER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_RESUME_JOB, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_EJECT_PAPER_JAM, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_SET_AUTO_POWER_OFF, UsbCommand.Function.HITI_SET_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_JOB_IN_QUEUE, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_GET_AUTO_POWER_VALUE, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_GET_STORAGE_ID, UsbCommand.Function.HITI_GET_OBJECT);
        UsbPrinterFuncMap.put(Action.USB_GET_OBJECT_NUMBER, UsbCommand.Function.HITI_GET_OBJECT);
        UsbPrinterFuncMap.put(Action.USB_GET_OBJECT_HANDLE_ID, UsbCommand.Function.HITI_GET_OBJECT);
        UsbPrinterFuncMap.put(Action.USB_GET_OBJECT_INFO, UsbCommand.Function.HITI_GET_OBJECT);
        UsbPrinterFuncMap.put(Action.USB_GET_OBJECT_DATA, UsbCommand.Function.HITI_GET_OBJECT);
        EnumMap enumMap2 = new EnumMap(Action.class);
        UsbPrinterSubFuncMap = enumMap2;
        enumMap2.put(Action.USB_COMMAND_RESET_PRINTER, UsbCommand.SubFunc.HITI_COMMAND_RESET_PRINTER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_RESUME_JOB, UsbCommand.SubFunc.HITI_COMMAND_RESUME_JOB);
        UsbPrinterSubFuncMap.put(Action.USB_EJECT_PAPER_JAM, UsbCommand.SubFunc.HITI_COMMAND_EJECT_PAPER_JAM);
        UsbPrinterSubFuncMap.put(Action.USB_SET_AUTO_POWER_OFF, UsbCommand.SubFunc.HITI_COMMAND_SET_AUTO_POWER_OFF);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.SubFunc.HITI_COMMAND_CLEAN_PAPER_PATH);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.SubFunc.HITI_DEVINFO_MODEL_NAME);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.SubFunc.HITI_DEVINFO_MFG_SERIAL);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.SubFunc.HITI_DEVINFO_FIRMWARE_VERSION);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.SubFunc.HITI_DEVINFO_RIBBON_INFO);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.SubFunc.HITI_DEVINFO_PRINT_COUNT);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_JOB_IN_QUEUE, UsbCommand.SubFunc.HITI_DEVINFO_JOBS_IN_QUEUE);
        UsbPrinterSubFuncMap.put(Action.USB_GET_AUTO_POWER_VALUE, UsbCommand.SubFunc.HITI_DEVINFO_AUTO_POWER);
        UsbPrinterSubFuncMap.put(Action.USB_GET_STORAGE_ID, UsbCommand.SubFunc.HITI_GET_OBJECT_STORAGE_ID);
        UsbPrinterSubFuncMap.put(Action.USB_GET_OBJECT_NUMBER, UsbCommand.SubFunc.HITI_GET_OBJECT_NUMBER);
        UsbPrinterSubFuncMap.put(Action.USB_GET_OBJECT_HANDLE_ID, UsbCommand.SubFunc.HITI_GET_OBJECT_HANDLE_ID);
        UsbPrinterSubFuncMap.put(Action.USB_GET_OBJECT_INFO, UsbCommand.SubFunc.HITI_GET_OBJECT_INFO);
        UsbPrinterSubFuncMap.put(Action.USB_GET_OBJECT_DATA, UsbCommand.SubFunc.HITI_GET_OBJECT_DATA);
        SparseArray<String> sparseArray = new SparseArray<>();
        ErroCodeMap = sparseArray;
        sparseArray.put(0, "0000: Success");
        ErroCodeMap.put(256, "0100: Cover open/Ribbon missing");
        ErroCodeMap.put(512, "0200: IC chip missing");
        ErroCodeMap.put(InputDeviceCompat.SOURCE_DPAD, "0201: Ribbon missing");
        ErroCodeMap.put(514, "0202: Ribbon mismatch 01");
        ErroCodeMap.put(515, "0203: Security check fail");
        ErroCodeMap.put(516, "0204: Ribbon mismatch 02");
        ErroCodeMap.put(517, "0205: Ribbon mismatch 03");
        ErroCodeMap.put(ViewUtils.EDGE_TO_EDGE_FLAGS, "0300: Ribbon out");
        ErroCodeMap.put(769, "0301: Printing fail");
        ErroCodeMap.put(1024, "0400: Paper out");
        ErroCodeMap.put(Status.Code.serverErrorInternalError, "0500: Paper jam 01");
        ErroCodeMap.put(Status.Code.serverErrorOperationNotSupported, "0501: Paper jam 02");
        ErroCodeMap.put(Status.Code.serverErrorServiceUnavailable, "0502: Paper jam 03");
        ErroCodeMap.put(Status.Code.serverErrorVersionNotSupported, "0503: Paper jam 04");
        ErroCodeMap.put(Status.Code.serverErrorDeviceError, "0504: Paper jam 05");
        ErroCodeMap.put(4608, "1200: TPH ADC error");
        ErroCodeMap.put(4864, "1300: FW check error");
        ErroCodeMap.put(4865, "1301: FW check error");
        ErroCodeMap.put(4866, "1302: FW check error");
        ErroCodeMap.put(4867, "1303: FW check error");
        ErroCodeMap.put(4868, "1304: FW check error");
        ErroCodeMap.put(4869, "1305: FW check error");
        ErroCodeMap.put(4870, "1306: FW check error");
        ErroCodeMap.put(4871, "1307: FW check error");
        ErroCodeMap.put(4872, "1308: Low battery");
        ErroCodeMap.put(285212704, "Create print job fail");
        ErroCodeMap.put(81, "paper jam, cover is not open");
        ErroCodeMap.put(82, "paper is still open");
    }

    @Override
    public String getErrorCodeDescription(int i) {
        String str = ErroCodeMap.get(i);
        return str == null ? PrinterStatus.getDescription(i) : str;
    }

    @Override
    public UsbCommand.Function getPrinterFunc(Action action) {
        return UsbPrinterFuncMap.get(action);
    }

    @Override
    public UsbCommand.SubFunc getPrinterSubFunc(Action action) {
        return UsbPrinterSubFuncMap.get(action);
    }
}
