package com.tom_roush.pdfbox.pdmodel.fdf;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;

public class FDFIconFit implements COSObjectable {
    public static final String SCALE_OPTION_ALWAYS = "A";
    public static final String SCALE_OPTION_NEVER = "N";
    public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_BIGGER = "B";
    public static final String SCALE_OPTION_ONLY_WHEN_ICON_IS_SMALLER = "S";
    public static final String SCALE_TYPE_ANAMORPHIC = "A";
    public static final String SCALE_TYPE_PROPORTIONAL = "P";
    private COSDictionary fit;

    public FDFIconFit() {
        this.fit = new COSDictionary();
    }

    public FDFIconFit(COSDictionary cOSDictionary) {
        this.fit = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.fit;
    }

    public String getScaleOption() {
        String nameAsString = this.fit.getNameAsString(COSName.f2308SW);
        return nameAsString == null ? "A" : nameAsString;
    }

    public void setScaleOption(String str) {
        this.fit.setName(COSName.f2308SW, str);
    }

    public String getScaleType() {
        String nameAsString = this.fit.getNameAsString(COSName.f2301S);
        return nameAsString == null ? "P" : nameAsString;
    }

    public void setScaleType(String str) {
        this.fit.setName(COSName.f2301S, str);
    }

    public PDRange getFractionalSpaceToAllocate() {
        COSArray cOSArray = (COSArray) this.fit.getDictionaryObject(COSName.f2228A);
        if (cOSArray == null) {
            PDRange pDRange = new PDRange();
            pDRange.setMin(0.5f);
            pDRange.setMax(0.5f);
            setFractionalSpaceToAllocate(pDRange);
            return pDRange;
        }
        return new PDRange(cOSArray);
    }

    public void setFractionalSpaceToAllocate(PDRange pDRange) {
        this.fit.setItem(COSName.f2228A, pDRange);
    }

    public boolean shouldScaleToFitAnnotation() {
        return this.fit.getBoolean(COSName.f2261FB, false);
    }

    public void setScaleToFitAnnotation(boolean z) {
        this.fit.setBoolean(COSName.f2261FB, z);
    }
}
