package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class BoardModelEnum {
    public static final String DING_CHANG_3568 = "rk3568_r";
    public static final String XIANG_CHENG_3399 = "rk3399-all";

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoardModel {
    }
}
