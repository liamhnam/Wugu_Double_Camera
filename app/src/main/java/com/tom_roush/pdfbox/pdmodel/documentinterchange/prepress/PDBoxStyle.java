package com.tom_roush.pdfbox.pdmodel.documentinterchange.prepress;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDLineDashPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;

public class PDBoxStyle implements COSObjectable {
    public static final String GUIDELINE_STYLE_DASHED = "D";
    public static final String GUIDELINE_STYLE_SOLID = "S";
    private final COSDictionary dictionary;

    public PDBoxStyle() {
        this.dictionary = new COSDictionary();
    }

    public PDBoxStyle(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDColor getGuidelineColor() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.f2238C);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ZERO);
            this.dictionary.setItem(COSName.f2238C, (COSBase) cOSArray);
        }
        return new PDColor(cOSArray.toFloatArray(), PDDeviceRGB.INSTANCE);
    }

    public void setGuideLineColor(PDColor pDColor) {
        this.dictionary.setItem(COSName.f2238C, (COSBase) (pDColor != null ? pDColor.toCOSArray() : null));
    }

    public float getGuidelineWidth() {
        return this.dictionary.getFloat(COSName.f2321W, 1.0f);
    }

    public void setGuidelineWidth(float f) {
        this.dictionary.setFloat(COSName.f2321W, f);
    }

    public String getGuidelineStyle() {
        return this.dictionary.getNameAsString(COSName.f2301S, "S");
    }

    public void setGuidelineStyle(String str) {
        this.dictionary.setName(COSName.f2301S, str);
    }

    public PDLineDashPattern getLineDashPattern() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.f2248D);
        if (cOSArray == null) {
            cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.THREE);
            this.dictionary.setItem(COSName.f2248D, (COSBase) cOSArray);
        }
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) cOSArray);
        return new PDLineDashPattern(cOSArray2, 0);
    }

    public void setLineDashPattern(COSArray cOSArray) {
        if (cOSArray == null) {
            cOSArray = null;
        }
        this.dictionary.setItem(COSName.f2248D, (COSBase) cOSArray);
    }
}
