package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SyncSettingCbEnum {
    public static final int RELEASE_BGM_RES = 2;
    public static final int UPDATE_DEVICE_INFO = 1;
    public static final int UPDATE_SOUND_RES = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncSettingCb {
    }
}
