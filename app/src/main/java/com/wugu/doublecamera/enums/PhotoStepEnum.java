package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PhotoStepEnum {
    public static final int CONFIRM = 3;
    public static final int PREVIEW = 1;
    public static final int READY_PHOTO = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PhotoStep {
    }
}
