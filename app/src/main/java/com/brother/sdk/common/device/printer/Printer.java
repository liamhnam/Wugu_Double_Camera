package com.brother.sdk.common.device.printer;

import com.brother.sdk.common.device.DeviceModelType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Printer implements Serializable {
    private static final long serialVersionUID = -8006211468805704594L;
    public PrintCapabilities capabilities = new PrintCapabilities();
    public PrinterPDL printerPDL = PrinterPDL.PostScript;
    public PrinterModelType modelType = PrinterModelType.PRINT_LASER;
    public DeviceModelType deviceModel = DeviceModelType.GeneralBrotherDevice;
    public List<PrinterPDL> supportPDLs = new ArrayList();
}
