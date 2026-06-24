package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PaymentMethodEnum {
    public static final int ALIPAY = 2;
    public static final int CASH = 4;
    public static final int CASH_CREDIT = 99;
    public static final int COIN = 5;
    public static final int CREDIT = 7;
    public static final int DISCOUNT = 3;
    public static final int DOU_YIN_CODE = 9;
    public static final int FREE = 11;
    public static final int GAME_COIN = 6;
    public static final int MEI_TUAN_CODE = 8;
    public static final int WECHAT = 1;
    public static final int XIE_CHENG_CODE = 12;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PaymentMethod {
    }
}
