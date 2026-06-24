package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RedeemTypeEnum {
    public static final int ALREADY_USED = 4;
    public static final int CASH = 2;
    public static final int DEVICE_ERROR = 5;
    public static final int DISCOUNT = 1;
    public static final int DOU_YIN = 7;
    public static final int EXPIRE = 3;
    public static final int FAULT = 0;
    public static final int MEI_TUAN = 6;
    public static final int TEST_FREE = 8;
    public static final int XIE_CHENG = 9;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RedeemType {
    }
}
