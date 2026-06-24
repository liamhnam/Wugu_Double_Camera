package com.hiti.usb.printer;

import com.hiti.usb.service.Action;
import com.hiti.usb.service.ErrorCode;

public class PrinterJob {
    public final Action action;
    public Object inputData;
    private final int printerjobId;
    public Object retData = null;
    public ErrorCode errCode = null;

    public PrinterJob(int i, Action action) {
        this.printerjobId = i;
        this.action = action;
    }

    public int getId() {
        return this.printerjobId;
    }

    public PrinterJob setJobPara(Object obj) {
        this.inputData = obj;
        return this;
    }
}
