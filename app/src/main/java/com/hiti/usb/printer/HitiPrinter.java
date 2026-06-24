package com.hiti.usb.printer;

import com.hiti.usb.jni.UsbCommand;
import com.hiti.usb.service.Action;
import com.hiti.usb.service.usbPrinter.CS200e;
import com.hiti.usb.service.usbPrinter.CS220e;
import com.hiti.usb.service.usbPrinter.CS280e;
import com.hiti.usb.service.usbPrinter.CS290e;
import com.hiti.usb.service.usbPrinter.P310w;
import com.hiti.usb.service.usbPrinter.P461;
import com.hiti.usb.service.usbPrinter.P520l;
import com.hiti.usb.service.usbPrinter.P525n;
import com.hiti.usb.service.usbPrinter.P750l;
import com.hiti.usb.service.usbPrinter.P910l;
import hiti.p035a.EnumC2094b;

public abstract class HitiPrinter {
    public static HitiPrinter getPrinter(EnumC2094b enumC2094b) {
        if (enumC2094b == EnumC2094b.P310W) {
            return new P310w();
        }
        if (enumC2094b == EnumC2094b.P520L) {
            return new P520l();
        }
        if (enumC2094b == EnumC2094b.P525N) {
            return new P525n();
        }
        if (enumC2094b == EnumC2094b.P750L) {
            return new P750l();
        }
        if (enumC2094b == EnumC2094b.P461) {
            return new P461();
        }
        if (enumC2094b == EnumC2094b.CS200E) {
            return new CS200e();
        }
        if (enumC2094b == EnumC2094b.CS220E) {
            return new CS220e();
        }
        if (enumC2094b == EnumC2094b.CS290E) {
            return new CS290e();
        }
        if (enumC2094b == EnumC2094b.CS280E) {
            return new CS280e();
        }
        if (enumC2094b == EnumC2094b.P910L) {
            return new P910l();
        }
        return null;
    }

    public abstract String getErrorCodeDescription(int i);

    public abstract UsbCommand.Function getPrinterFunc(Action action);

    public abstract UsbCommand.SubFunc getPrinterSubFunc(Action action);
}
