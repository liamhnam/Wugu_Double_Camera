package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CanonStepEnum {
    public static final int CAPTURE = 9;
    public static final int END_CLOSE_SESSION = 11;
    public static final int INIT_CLOSE_SESSION = 12;
    public static final int INIT_GET_DEVICE_INFO = 1;
    public static final int INIT_NOTIFY_MODE = 4;
    public static final int INIT_OPEN_SESSION = 2;
    public static final int INIT_REMOTE_SHOOTING = 3;
    public static final int INIT_SET_PROP_EVF = 5;
    public static final int INIT_SET_PROP_FINISH = 7;
    public static final int INIT_SET_PROP_OUTPUT_DEV = 6;
    public static final int READY_GET_LIVE_VIEW = 8;
    public static final int READY_GET_OBJECT = 10;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CanonStep {
    }
}
