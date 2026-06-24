package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PrinterCodeEnum {
    public static final int NOT_DEVICE = 1;
    public static final int NOT_SHEET = 2;
    public static final int OTHER_ERROR = 8;
    public static final int PRINTER_READY = 3;
    public static final int PRINT_FAIL = 6;
    public static final int RETRY_PRINT = 9;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrinterCode {
    }
}
