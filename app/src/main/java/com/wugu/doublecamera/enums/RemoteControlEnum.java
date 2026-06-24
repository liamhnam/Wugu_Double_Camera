package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RemoteControlEnum {
    public static final int ORDER_HTTP_FINISH = 3;
    public static final int POSTER_E_UPDATE_BEAUTY_LEVEL = 4;
    public static final int UPDATE_DEVICE_STATUS = 1;
    public static final int UPDATE_FRAME = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RemoteControl {
    }
}
