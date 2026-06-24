package com.tom_roush.pdfbox.contentstream.operator.color;

import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;

public class SetNonStrokingColor extends SetColor {
    @Override
    public String getName() {
        return "sc";
    }

    @Override
    protected PDColor getColor() {
        return this.context.getGraphicsState().getNonStrokingColor();
    }

    @Override
    protected void setColor(PDColor pDColor) {
        this.context.getGraphicsState().setNonStrokingColor(pDColor);
    }

    @Override
    protected PDColorSpace getColorSpace() {
        return this.context.getGraphicsState().getNonStrokingColorSpace();
    }
}
