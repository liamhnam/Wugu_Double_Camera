package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FilterTypeEnum {
    public static final int ANIME_FILTER = 3;
    public static final int ARTISTIC_FILTER = 2;
    public static final int NORMAL_FILTER = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FilterType {
    }
}
