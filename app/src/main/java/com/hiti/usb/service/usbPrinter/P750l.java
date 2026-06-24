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

public class P750l extends HitiPrinter {
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
        UsbPrinterFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_JOB_IN_QUEUE, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        EnumMap enumMap2 = new EnumMap(Action.class);
        UsbPrinterSubFuncMap = enumMap2;
        enumMap2.put(Action.USB_COMMAND_RESET_PRINTER, UsbCommand.SubFunc.HITI_COMMAND_RESET_PRINTER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_RESUME_JOB, UsbCommand.SubFunc.HITI_COMMAND_RESUME_JOB);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.SubFunc.HITI_COMMAND_CLEAN_PAPER_PATH);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.SubFunc.HITI_DEVINFO_MODEL_NAME);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.SubFunc.HITI_DEVINFO_MFG_SERIAL);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.SubFunc.HITI_DEVINFO_FIRMWARE_VERSION);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.SubFunc.HITI_DEVINFO_RIBBON_INFO);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.SubFunc.HITI_DEVINFO_PRINT_COUNT);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_JOB_IN_QUEUE, UsbCommand.SubFunc.HITI_DEVINFO_JOBS_IN_QUEUE);
        SparseArray<String> sparseArray = new SparseArray<>();
        ErroCodeMap = sparseArray;
        sparseArray.put(0, "Success");
        ErroCodeMap.put(256, "Cover open");
        ErroCodeMap.put(257, "Cover open fail");
        ErroCodeMap.put(512, "IC chip missing");
        ErroCodeMap.put(InputDeviceCompat.SOURCE_DPAD, "Ribbon missing");
        ErroCodeMap.put(514, "Ribbon mismatch 01");
        ErroCodeMap.put(515, "Security check fail");
        ErroCodeMap.put(516, "Ribbon mismatch 02");
        ErroCodeMap.put(517, "Ribbon mismatch 03");
        ErroCodeMap.put(ViewUtils.EDGE_TO_EDGE_FLAGS, "Ribbon out 01");
        ErroCodeMap.put(769, "Ribbon out 02");
        ErroCodeMap.put(770, "Printing fail");
        ErroCodeMap.put(1024, "Paper out 01");
        ErroCodeMap.put(1025, "Paper out 02");
        ErroCodeMap.put(Status.Code.clientErrorNotAuthenticated, "Paper not ready");
        ErroCodeMap.put(Status.Code.serverErrorInternalError, "Paper jam 01");
        ErroCodeMap.put(Status.Code.serverErrorOperationNotSupported, "Paper jam 02");
        ErroCodeMap.put(Status.Code.serverErrorServiceUnavailable, "Paper jam 03");
        ErroCodeMap.put(Status.Code.serverErrorVersionNotSupported, "Paper jam 04");
        ErroCodeMap.put(Status.Code.serverErrorDeviceError, "Paper jam 05");
        ErroCodeMap.put(1536, "Paper mismatch");
        ErroCodeMap.put(1792, "Cam error 01");
        ErroCodeMap.put(2048, "Cam error 02");
        ErroCodeMap.put(2304, "NVRAM error");
        ErroCodeMap.put(4096, "IC chip error");
        ErroCodeMap.put(4608, "ADC error");
        ErroCodeMap.put(4864, "FW check error");
        ErroCodeMap.put(5376, "Cutter Error");
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
