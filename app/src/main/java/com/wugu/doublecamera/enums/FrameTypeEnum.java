package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FrameTypeEnum {
    public static final int AI_FRAME = 1;
    public static final int CARD_FRAME = 2;
    public static final int CERTIFY_FRAME = 3;
    public static final int IP_FRAME = 4;
    public static final int NORMAL = 0;
    public static final int REPLACE_BG_FRAME = 6;
    public static final int UPLOAD_PRINT_FRAME = 5;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FrameType {
    }
}
