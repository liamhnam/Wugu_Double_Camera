package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDAnnotationTextMarkup extends PDAnnotationMarkup {
    public static final String SUB_TYPE_HIGHLIGHT = "Highlight";
    public static final String SUB_TYPE_SQUIGGLY = "Squiggly";
    public static final String SUB_TYPE_STRIKEOUT = "StrikeOut";
    public static final String SUB_TYPE_UNDERLINE = "Underline";

    private PDAnnotationTextMarkup() {
    }

    public PDAnnotationTextMarkup(String str) {
        setSubtype(str);
        setQuadPoints(new float[0]);
    }

    public PDAnnotationTextMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setQuadPoints(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getCOSObject().setItem(COSName.QUADPOINTS, (COSBase) cOSArray);
    }

    public float[] getQuadPoints() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.QUADPOINTS);
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }

    public void setSubtype(String str) {
        getCOSObject().setName(COSName.SUBTYPE, str);
    }

    @Override
    public String getSubtype() {
        return getCOSObject().getNameAsString(COSName.SUBTYPE);
    }
}
