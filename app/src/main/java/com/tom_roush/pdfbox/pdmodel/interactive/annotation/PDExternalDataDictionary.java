package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDExternalDataDictionary implements COSObjectable {
    private final COSDictionary dataDictionary;

    public PDExternalDataDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dataDictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, "ExData");
    }

    public PDExternalDataDictionary(COSDictionary cOSDictionary) {
        this.dataDictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dataDictionary;
    }

    public String getType() {
        return getCOSObject().getNameAsString(COSName.TYPE, "ExData");
    }

    public String getSubtype() {
        return getCOSObject().getNameAsString(COSName.SUBTYPE);
    }

    public void setSubtype(String str) {
        getCOSObject().setName(COSName.SUBTYPE, str);
    }
}
