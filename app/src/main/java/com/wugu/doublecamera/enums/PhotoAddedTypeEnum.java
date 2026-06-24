package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PhotoAddedTypeEnum {
    public static final int EFFECT_E = 3;
    public static final int EFFECT_HAHA = 5;
    public static final int EFFECT_MASK = 4;
    public static final int FILTER = 1;
    public static final int MAKEUP = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PhotoAddedType {
    }
}
