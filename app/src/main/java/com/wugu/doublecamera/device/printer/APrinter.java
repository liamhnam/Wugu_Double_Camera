package com.wugu.doublecamera.device.printer;

import com.wugu.doublecamera.listener.IPrinterStatusListener;

public abstract class APrinter {
    public void close() {
    }

    public abstract int getPrintTime();

    public abstract String getPrinterSerial();

    public abstract int getRemainSheets();

    public abstract void init(IPrinterStatusListener iPrinterStatusListener);

    public abstract boolean isPrinting();

    public abstract void print(String str, int i, boolean z);

    public abstract void printFinish();

    public abstract void updateColorParam(Object obj);
}
