package com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;

public class PDPropertyList implements COSObjectable {
    protected final COSDictionary dict;

    public static PDPropertyList create(COSDictionary cOSDictionary) {
        if (COSName.OCG.equals(cOSDictionary.getItem(COSName.TYPE))) {
            return new PDOptionalContentGroup(cOSDictionary);
        }
        return new PDPropertyList(cOSDictionary);
    }

    protected PDPropertyList() {
        this.dict = new COSDictionary();
    }

    protected PDPropertyList(COSDictionary cOSDictionary) {
        this.dict = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dict;
    }
}
