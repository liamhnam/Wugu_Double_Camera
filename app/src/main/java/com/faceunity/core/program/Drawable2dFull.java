package com.faceunity.core.program;

import com.faceunity.core.program.core.Drawable2d;

public class Drawable2dFull extends Drawable2d {
    private static final float[] FULL_RECTANGLE_COORDS = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    private static final float[] FULL_RECTANGLE_TEX_COORDS = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};

    public Drawable2dFull() {
        super(FULL_RECTANGLE_COORDS, FULL_RECTANGLE_TEX_COORDS);
    }
}
