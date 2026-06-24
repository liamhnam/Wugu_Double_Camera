package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;

public class PDBorderStyleDictionary implements COSObjectable {
    public static final String STYLE_BEVELED = "B";
    public static final String STYLE_DASHED = "D";
    public static final String STYLE_INSET = "I";
    public static final String STYLE_SOLID = "S";
    public static final String STYLE_UNDERLINE = "U";
    private final COSDictionary dictionary;

    public PDBorderStyleDictionary() {
        this.dictionary = new COSDictionary();
    }

    public PDBorderStyleDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public void setWidth(float f) {
        getCOSObject().setFloat("W", f);
    }

    public float getWidth() {
        return getCOSObject().getFloat("W", 1.0f);
    }

    public void setStyle(String str) {
        getCOSObject().setName("S", str);
    }

    public String getStyle() {
        return getCOSObject().getNameAsString("S", "S");
    }

    public void setDashStyle(COSArray cOSArray) {
        if (cOSArray == null) {
            cOSArray = null;
        }
        getCOSObject().setItem("D", (COSBase) cOSArray);
    }

    public PDLineDashPattern getDashStyle() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject("D");
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.THREE);
            getCOSObject().setItem("D", (COSBase) cOSArray);
        }
        return new PDLineDashPattern(cOSArray, 0);
    }
}
