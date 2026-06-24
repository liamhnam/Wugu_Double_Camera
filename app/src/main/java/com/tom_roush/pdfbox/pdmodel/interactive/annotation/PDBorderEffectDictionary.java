package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDBorderEffectDictionary implements COSObjectable {
    public static final String STYLE_CLOUDY = "C";
    public static final String STYLE_SOLID = "S";
    private final COSDictionary dictionary;

    public PDBorderEffectDictionary() {
        this.dictionary = new COSDictionary();
    }

    public PDBorderEffectDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void setIntensity(float f) {
        getCOSObject().setFloat("I", f);
    }

    public float getIntensity() {
        return getCOSObject().getFloat("I", 0.0f);
    }

    public void setStyle(String str) {
        getCOSObject().setName("S", str);
    }

    public String getStyle() {
        return getCOSObject().getNameAsString("S", "S");
    }
}
