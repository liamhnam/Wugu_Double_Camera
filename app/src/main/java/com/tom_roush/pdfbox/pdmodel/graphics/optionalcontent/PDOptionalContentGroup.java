package com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;

public class PDOptionalContentGroup extends PDPropertyList {
    public PDOptionalContentGroup(String str) {
        this.dict.setItem(COSName.TYPE, (COSBase) COSName.OCG);
        setName(str);
    }

    public PDOptionalContentGroup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        if (!cOSDictionary.getItem(COSName.TYPE).equals(COSName.OCG)) {
            throw new IllegalArgumentException("Provided dictionary is not of type '" + COSName.OCG + "'");
        }
    }

    public String getName() {
        return this.dict.getString(COSName.NAME);
    }

    public void setName(String str) {
        this.dict.setString(COSName.NAME, str);
    }

    public String toString() {
        return super.toString() + " (" + getName() + ")";
    }
}
