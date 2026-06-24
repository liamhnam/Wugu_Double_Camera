package com.hiti.usb.printer;

import android.util.SparseArray;
import androidx.core.view.InputDeviceCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.google.android.material.internal.ViewUtils;
import com.p020hp.jipp.model.Status;

public class PrinterStatus {
    private static SparseArray<String> PrinterStatusMap;
    public String statusDescription;
    public int statusValue;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        PrinterStatusMap = sparseArray;
        sparseArray.put(0, "U001: Printer is ready");
        PrinterStatusMap.put(524288, "U002: Printer is busy");
        PrinterStatusMap.put(128, "U003: Printer is disconnected or power off");
        PrinterStatusMap.put(2, "U004: Printer is printing");
        PrinterStatusMap.put(5, "U005: Driver is processing print data");
        PrinterStatusMap.put(6, "U006: Driver is sending data to printer");
        PrinterStatusMap.put(256, "Cover open");
        PrinterStatusMap.put(ErrorCode.EPRINTSVR_ERR_INV_ARG_SUPPORTED_MEDIA, "0100: Cover open/Ribbon cassette door open");
        PrinterStatusMap.put(327937, "0100: Cover open/Ribbon cassette door open");
        PrinterStatusMap.put(1025, "0401: Paper out or feeding error");
        PrinterStatusMap.put(32768, "0401: Paper out or feeding error");
        PrinterStatusMap.put(32769, "U007: Paper low");
        PrinterStatusMap.put(Status.Code.serverErrorInternalError, "0500: Paper jam");
        PrinterStatusMap.put(ProfileVerifier.CompilationStatus.f207xf2722a21, "0500: Paper jam");
        PrinterStatusMap.put(1536, "0600: Paper type mismatch");
        PrinterStatusMap.put(65790, "0600: Paper type mismatch");
        PrinterStatusMap.put(32784, "U008: Paper tray mismatch");
        PrinterStatusMap.put(32776, "U009: Paper tray missing");
        PrinterStatusMap.put(InputDeviceCompat.SOURCE_DPAD, "0201: Ribbon missing");
        PrinterStatusMap.put(524292, "0201: Ribbon missing");
        PrinterStatusMap.put(ViewUtils.EDGE_TO_EDGE_FLAGS, "0300: Out of ribbon");
        PrinterStatusMap.put(524547, "0300: Out of ribbon");
        PrinterStatusMap.put(524800, "U00A: Ribbon type mismatch");
        PrinterStatusMap.put(769, "0301: Ribbon error");
        PrinterStatusMap.put(525054, "0301: Ribbon error");
        PrinterStatusMap.put(196609, "U00B: SRAM error");
        PrinterStatusMap.put(196865, "U00C: SDRAM error");
        PrinterStatusMap.put(4608, "1200: ADC error");
        PrinterStatusMap.put(197121, "1200: ADC error");
        PrinterStatusMap.put(2304, "0900: NVRAM read/write error");
        PrinterStatusMap.put(197377, "0900: NVRAM read/write error");
        PrinterStatusMap.put(197378, "U00D: Check sum error - SDRAM");
        PrinterStatusMap.put(197634, "U00E: DSP code check sum error");
        PrinterStatusMap.put(1792, "0700: Cam Platen error");
        PrinterStatusMap.put(197889, "0700: Cam Platen error");
        PrinterStatusMap.put(198145, "U00F: Adf Cam error");
        PrinterStatusMap.put(31, "U00G: Send data to printer fail");
        PrinterStatusMap.put(47, "U00H: Get data from printer fail");
        PrinterStatusMap.put(26, "U00I: Printer has no response");
        PrinterStatusMap.put(301989889, "U00J: Printing job suspended by previous error");
        PrinterStatusMap.put(197118, "U00K: Command sequence error");
        PrinterStatusMap.put(197374, "U00L: Nand flash unformatted");
        PrinterStatusMap.put(197630, "U00M: Nand flash space is not enough");
        PrinterStatusMap.put(197886, "U00N: Heating parameter table incompatible");
        PrinterStatusMap.put(328446, "U00O: Dust box needs cleaned");
        PrinterStatusMap.put(197890, "U00P: Nvram CRC error");
        PrinterStatusMap.put(198146, "U00Q: Check sum error - SRAM");
        PrinterStatusMap.put(198402, "U00R: Check sum error - FLASH");
        PrinterStatusMap.put(198658, "U00S: Check sum error - wrong firmware");
        PrinterStatusMap.put(201217, "U00T: Nand flash error");
        PrinterStatusMap.put(201474, "U00U: Cutter error");
        PrinterStatusMap.put(525310, "U00V: Ribbon is not authenticated yet");
        PrinterStatusMap.put(525566, "U00W: Ribbon IC R/W error");
        PrinterStatusMap.put(515, "0203: Unsupported ribbon");
        PrinterStatusMap.put(526078, "0203: Unsupported ribbon");
        PrinterStatusMap.put(526590, "U00X: Unknown ribbon");
        PrinterStatusMap.put(Status.Code.serverErrorServiceUnavailable, "0502: Paper Jam in paper path (01)");
        PrinterStatusMap.put(328192, "0502: Paper Jam in paper path (01)");
        PrinterStatusMap.put(Status.Code.serverErrorVersionNotSupported, "0503: Paper Jam in paper path (02)");
        PrinterStatusMap.put(328448, "0503: Paper Jam in paper path (02)");
        PrinterStatusMap.put(Status.Code.serverErrorDeviceError, "0504: Paper Jam in paper path (03)");
        PrinterStatusMap.put(328704, "0504: Paper Jam in paper path (03)");
        PrinterStatusMap.put(Status.Code.serverErrorTemporaryError, "0505: Paper Jam in paper path (04)");
        PrinterStatusMap.put(328960, "0505: Paper Jam in paper path (04)");
        PrinterStatusMap.put(Status.Code.serverErrorNotAcceptingJobs, "0506: Paper Jam in paper path (05)");
        PrinterStatusMap.put(329216, "0506: Paper Jam in paper path (05)");
        PrinterStatusMap.put(Status.Code.serverErrorBusy, "0507: Paper Jam in paper path (06)");
        PrinterStatusMap.put(329472, "0507: Paper Jam in paper path (06)");
        PrinterStatusMap.put(Status.Code.serverErrorJobCanceled, "0508: Paper Jam in paper path (07)");
        PrinterStatusMap.put(329728, "0508: Paper Jam in paper path (07)");
        PrinterStatusMap.put(Status.Code.serverErrorMultipleDocumentJobsNotSupported, "0509: Paper Jam in paper path (08)");
        PrinterStatusMap.put(329984, "0509: Paper Jam in paper path (08)");
        PrinterStatusMap.put(Status.Code.serverErrorPrinterIsDeactivated, "050A: Paper Jam in paper path (09)");
        PrinterStatusMap.put(330240, "050A: Paper Jam in paper path (09)");
    }

    public PrinterStatus(int i) {
        this.statusValue = i;
    }

    public static String getDescription(int i) {
        return PrinterStatusMap.get(i);
    }
}
