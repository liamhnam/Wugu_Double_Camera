package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;

public class SetStrokingColor extends SetColor {
    @Override
    public String getName() {
        return "SC";
    }

    @Override
    protected PDColor getColor() {
        return this.context.getGraphicsState().getStrokingColor();
    }

    @Override
    protected void setColor(PDColor pDColor) {
        this.context.getGraphicsState().setStrokingColor(pDColor);
    }

    @Override
    protected PDColorSpace getColorSpace() {
        return this.context.getGraphicsState().getStrokingColorSpace();
    }
}
