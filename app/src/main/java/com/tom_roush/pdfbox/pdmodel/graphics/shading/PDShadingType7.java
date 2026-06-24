package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDShadingType7 extends PDShadingType6 {
    @Override
    public int getShadingType() {
        return 7;
    }

    public PDShadingType7(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }
}
