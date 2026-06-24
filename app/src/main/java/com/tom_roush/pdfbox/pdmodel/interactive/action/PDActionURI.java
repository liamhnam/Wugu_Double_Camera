package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDActionURI extends PDAction {
    public static final String SUB_TYPE = "URI";

    public PDActionURI() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionURI(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getS() {
        return this.action.getNameAsString(COSName.f2301S);
    }

    public void setS(String str) {
        this.action.setName(COSName.f2301S, str);
    }

    public String getURI() {
        return this.action.getString(COSName.URI);
    }

    public void setURI(String str) {
        this.action.setString(COSName.URI, str);
    }

    public boolean shouldTrackMousePosition() {
        return this.action.getBoolean("IsMap", false);
    }

    public void setTrackMousePosition(boolean z) {
        this.action.setBoolean("IsMap", z);
    }
}
