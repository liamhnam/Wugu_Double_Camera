package com.tom_roush.pdfbox.pdmodel.interactive.measurement;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;

public class PDViewportDictionary implements COSObjectable {
    public static final String TYPE = "Viewport";
    private COSDictionary viewportDictionary;

    public String getType() {
        return TYPE;
    }

    public PDViewportDictionary() {
        this.viewportDictionary = new COSDictionary();
    }

    public PDViewportDictionary(COSDictionary cOSDictionary) {
        this.viewportDictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.viewportDictionary;
    }

    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject("BBox");
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        getCOSObject().setItem("BBox", pDRectangle);
    }

    public String getName() {
        return getCOSObject().getNameAsString(COSName.NAME);
    }

    public void setName(String str) {
        getCOSObject().setName(COSName.NAME, str);
    }

    public PDMeasureDictionary getMeasure() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(PDMeasureDictionary.TYPE);
        if (cOSDictionary != null) {
            return new PDMeasureDictionary(cOSDictionary);
        }
        return null;
    }

    public void setMeasure(PDMeasureDictionary pDMeasureDictionary) {
        getCOSObject().setItem(PDMeasureDictionary.TYPE, pDMeasureDictionary);
    }
}
