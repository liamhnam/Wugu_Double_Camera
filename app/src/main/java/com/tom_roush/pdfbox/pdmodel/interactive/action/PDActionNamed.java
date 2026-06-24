package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;

public class PDActionNamed extends PDAction {
    public static final String SUB_TYPE = "Named";

    public PDActionNamed() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionNamed(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getN() {
        return this.action.getNameAsString("N");
    }

    public void setN(String str) {
        this.action.setName("N", str);
    }
}
