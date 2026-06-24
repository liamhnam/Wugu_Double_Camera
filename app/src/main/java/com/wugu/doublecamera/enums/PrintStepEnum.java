package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PrintStepEnum {
    public static final int MODIFY_FRAME = 1;
    public static final int PRINTER_SETTING = 4;
    public static final int PRINTING = 3;
    public static final int WAIT_PAYMENT = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrintStep {
    }
}
