package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDShadingType3 extends PDShadingType2 {
    @Override
    public int getShadingType() {
        return 3;
    }

    public PDShadingType3(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }
}
