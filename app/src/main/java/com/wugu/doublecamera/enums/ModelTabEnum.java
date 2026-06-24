package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ModelTabEnum {
    public static final int TAB_AI = 5;
    public static final int TAB_CERTIFY = 2;
    public static final int TAB_HEAD = 1;
    public static final int TAB_IP = 6;
    public static final int TAB_REPLACE_BG = 3;
    public static final int TAB_UP_PRINT = 4;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ModelTab {
    }
}
