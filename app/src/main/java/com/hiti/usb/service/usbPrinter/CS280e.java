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

public class CS280e extends HitiPrinter {
    private static SparseArray<String> ErroCodeMap;
    private static Map<Action, UsbCommand.Function> UsbPrinterFuncMap;
    private static Map<Action, UsbCommand.SubFunc> UsbPrinterSubFuncMap;

    static {
        EnumMap enumMap = new EnumMap(Action.class);
        UsbPrinterFuncMap = enumMap;
        enumMap.put(Action.USB_CHECK_PRINTER_STATUS, UsbCommand.Function.HITI_CHECK_PRINTER_STATUS);
        UsbPrinterFuncMap.put(Action.USB_PRINT_CARD, UsbCommand.Function.HITI_PRINT_ONE_CARD);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_RESET_PRINTER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_RESUME_JOB, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_IC_ENCODER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_RFID_ENCODER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_REJECT_BOX, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_HOPPER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_FLIPPER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_PRINT_FROM_FLIPPER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_STANDBY_POSITION, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_EJECT_CARD_FROM_FLIPPER, UsbCommand.Function.HITI_DO_COMMAND);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        UsbPrinterFuncMap.put(Action.USB_DEVICE_CARD_POSITION, UsbCommand.Function.HITI_GET_DEVICE_INFO);
        EnumMap enumMap2 = new EnumMap(Action.class);
        UsbPrinterSubFuncMap = enumMap2;
        enumMap2.put(Action.USB_COMMAND_RESET_PRINTER, UsbCommand.SubFunc.HITI_COMMAND_RESET_PRINTER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_RESUME_JOB, UsbCommand.SubFunc.HITI_COMMAND_RESUME_JOB);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_CLEAN_PAPER_PATH, UsbCommand.SubFunc.HITI_COMMAND_CLEAN_PAPER_PATH);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_IC_ENCODER, UsbCommand.SubFunc.MOVE_CARD_TO_IC_ENCODER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_RFID_ENCODER, UsbCommand.SubFunc.MOVE_CARD_TO_RFID_ENCODER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_REJECT_BOX, UsbCommand.SubFunc.MOVE_CARD_TO_REJECT_BOX);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_HOPPER, UsbCommand.SubFunc.MOVE_CARD_TO_HOPPER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_FLIPPER, UsbCommand.SubFunc.MOVE_CARD_TO_FLIPPER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_PRINT_FROM_FLIPPER, UsbCommand.SubFunc.MOVE_CARD_TO_PRINT_FROM_FLIPPER);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_STANDBY_POSITION, UsbCommand.SubFunc.MOVE_CARD_TO_STANDBY_POSITION);
        UsbPrinterSubFuncMap.put(Action.USB_COMMAND_MOVE_CARD_TO_EJECT_CARD_FROM_FLIPPER, UsbCommand.SubFunc.MOVE_CARD_TO_EJECT_CARD_FROM_FLIPPER);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_MODEL_NAME, UsbCommand.SubFunc.HITI_DEVINFO_MODEL_NAME);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_SERIAL_NUM, UsbCommand.SubFunc.HITI_DEVINFO_MFG_SERIAL);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_FW_VERSION, UsbCommand.SubFunc.HITI_DEVINFO_FIRMWARE_VERSION);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_RIBBON_INFO, UsbCommand.SubFunc.HITI_DEVINFO_RIBBON_INFO);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_PRINT_COUNT, UsbCommand.SubFunc.HITI_DEVINFO_PRINT_COUNT);
        UsbPrinterSubFuncMap.put(Action.USB_DEVICE_CARD_POSITION, UsbCommand.SubFunc.HITI_DEVINFO_CARD_POSITION);
        SparseArray<String> sparseArray = new SparseArray<>();
        ErroCodeMap = sparseArray;
        sparseArray.put(0, "Success");
        ErroCodeMap.put(256, "Cover open");
        ErroCodeMap.put(257, "Flipper cover open");
        ErroCodeMap.put(512, "IC chip missing");
        ErroCodeMap.put(InputDeviceCompat.SOURCE_DPAD, "Ribbon missing");
        ErroCodeMap.put(514, "Ribbon mismatch");
        ErroCodeMap.put(515, "Ribbon type error");
        ErroCodeMap.put(ViewUtils.EDGE_TO_EDGE_FLAGS, "Ribbon search fail");
        ErroCodeMap.put(769, "Ribbon out");
        ErroCodeMap.put(770, "Print fail");
        ErroCodeMap.put(771, "Print fail");
        ErroCodeMap.put(772, "Ribbon out");
        ErroCodeMap.put(1024, "Card out");
        ErroCodeMap.put(Status.Code.serverErrorInternalError, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorOperationNotSupported, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorServiceUnavailable, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorVersionNotSupported, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorDeviceError, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorTemporaryError, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorNotAcceptingJobs, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorBusy, "Card jam");
        ErroCodeMap.put(Status.Code.serverErrorJobCanceled, "Card jam");
        ErroCodeMap.put(1536, "Card mismatch");
        ErroCodeMap.put(1792, "Cam error");
        ErroCodeMap.put(2048, "Flipper error");
        ErroCodeMap.put(2049, "Flipper error");
        ErroCodeMap.put(2050, "Flipper error");
        ErroCodeMap.put(2051, "Flipper error");
        ErroCodeMap.put(2304, "NVRAM error");
        ErroCodeMap.put(4096, "Ribbon error");
        ErroCodeMap.put(4352, "RBN Take Calibration Failed");
        ErroCodeMap.put(4353, "RBN Supply Calibration Failed");
        ErroCodeMap.put(4608, "ADC error");
        ErroCodeMap.put(4864, "FW error");
        ErroCodeMap.put(4865, "FW error");
        ErroCodeMap.put(5120, "Power supply error");
        ErroCodeMap.put(32784, "Card thickness selector is not placed in the right position");
        ErroCodeMap.put(285212685, "The flipper module is not attached");
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
