package com.tom_roush.pdfbox.pdmodel.graphics.blend;

public abstract class SeparableBlendMode extends BlendMode {
    public abstract float blendChannel(float f, float f2);

    SeparableBlendMode() {
    }
}
