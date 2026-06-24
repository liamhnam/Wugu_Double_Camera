package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OrderTypeEnum {
    public static final int AI_PHOTO = 3;
    public static final int ID_PHOTO = 4;
    public static final int NORMAL_PHOTO = 0;
    public static final int PRINT_ADD = 1;
    public static final int REPLACE_BG_MODEL = 5;
    public static final int UPLOAD_PRINT = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrderType {
    }
}
