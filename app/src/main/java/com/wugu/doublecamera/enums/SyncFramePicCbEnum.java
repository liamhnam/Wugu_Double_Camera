package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SyncFramePicCbEnum {
    public static final int FINISH_DOWNLOAD = 2;
    public static final int FINISH_FRAME_DOWNLOAD = 3;
    public static final int START_DOWNLOAD = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncFramePicCb {
    }
}
