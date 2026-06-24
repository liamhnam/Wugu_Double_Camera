package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDActionSound extends PDAction {
    public static final String SUB_TYPE = "Sound";

    public PDActionSound() {
        this.action = new COSDictionary();
        setSubType("Sound");
    }

    public PDActionSound(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getS() {
        return this.action.getNameAsString(COSName.f2301S);
    }

    public void setS(String str) {
        this.action.setName(COSName.f2301S, str);
    }
}
