package com.wugu.doublecamera.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SignPaintCommandEnum {
    public static final int CLEAR_ALL = 5;
    public static final int DRAW_MODE = 3;
    public static final int ERASER_MODE = 4;
    public static final int REDO = 2;
    public static final int UNDO = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PaintCommand {
    }
}
