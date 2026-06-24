package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FrameRemarkEnum {
    public static final String IS_ADD_AR_CODE = "isAddArCode";
    public static final String IS_ADD_QR_CODE = "isAddQrCode";
    public static final String IS_BROKEN_LINE = "isBrokenLine";

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameRemark {
    }
}
