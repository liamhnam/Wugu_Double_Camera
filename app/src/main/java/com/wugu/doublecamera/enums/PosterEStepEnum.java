package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PosterEStepEnum {
    public static final int HOME_IDLE = 6;
    public static final int PRINTING = 4;
    public static final int READY_PHOTO = 2;
    public static final int RETAKE_CONFIRM = 3;
    public static final int WAIT_PAY_PHOTO = 0;
    public static final int WAIT_PAY_PRINT = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PosterEStep {
    }
}
