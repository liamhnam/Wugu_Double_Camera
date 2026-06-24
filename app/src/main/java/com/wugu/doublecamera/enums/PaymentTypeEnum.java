package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PaymentTypeEnum {
    public static final int TYPE_ALIPAY = 11;
    public static final int TYPE_CASH = 16;
    public static final int TYPE_COIN = 17;
    public static final int TYPE_CREDIT = 15;
    public static final int TYPE_DISCOUNT = 12;
    public static final int TYPE_DOU_YIN_CODE = 14;
    public static final int TYPE_FREE = 19;
    public static final int TYPE_GAME_COIN = 18;
    public static final int TYPE_MEI_TUAN_CODE = 13;
    public static final int TYPE_WECHAT = 10;
    public static final int TYPE_XIE_CHENG = 20;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PaymentType {
    }
}
