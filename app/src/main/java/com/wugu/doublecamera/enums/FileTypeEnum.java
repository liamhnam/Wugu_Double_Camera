package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FileTypeEnum {
    public static final int AI_FRAME = 9;
    public static final int BG_FRAME = 8;
    public static final int COLOR_RAW = 4;
    public static final int DEFAULT = 0;
    public static final int EFFECT = 13;
    public static final int FILTER = 7;
    public static final int FRAME = 2;
    public static final int MAKEUP = 12;
    public static final int SOUND = 1;
    public static final int STICKER = 3;
    public static final int STICKER_BTN = 11;
    public static final int THEME_BG = 6;
    public static final int THEME_BTN = 10;

    public static final int f2456UI = 5;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FileType {
    }
}
