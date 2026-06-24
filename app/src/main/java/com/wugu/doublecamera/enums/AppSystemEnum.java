package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppSystemEnum {
    public static final int CS_SHANG_YANG_IDOL = 4;
    public static final int NORMAL_HEAD_SYS = 1;
    public static final int POSTER_SYS = 2;
    public static final int POSTER_SYS_EASY = 3;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AppSystem {
    }
}
