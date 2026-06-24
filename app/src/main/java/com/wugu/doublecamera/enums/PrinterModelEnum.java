package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PrinterModelEnum {
    public static final int BRO_2340 = 3;
    public static final int CITIZEN_CY = 4;
    public static final int DNP_DS_RX1 = 1;
    public static final int EPSON_L8058 = 5;
    public static final int HITI = 7;
    public static final int HP_701N = 6;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrinterModel {
    }
}
