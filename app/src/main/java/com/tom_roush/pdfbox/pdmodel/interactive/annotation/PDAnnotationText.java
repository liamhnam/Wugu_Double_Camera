package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDAnnotationText extends PDAnnotationMarkup {
    public static final String NAME_COMMENT = "Comment";
    public static final String NAME_HELP = "Help";
    public static final String NAME_INSERT = "Insert";
    public static final String NAME_KEY = "Key";
    public static final String NAME_NEW_PARAGRAPH = "NewParagraph";
    public static final String NAME_NOTE = "Note";
    public static final String NAME_PARAGRAPH = "Paragraph";
    public static final String SUB_TYPE = "Text";

    public PDAnnotationText() {
        getCOSObject().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName("Text"));
    }

    public PDAnnotationText(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setOpen(boolean z) {
        getCOSObject().setBoolean(COSName.getPDFName("Open"), z);
    }

    public boolean getOpen() {
        return getCOSObject().getBoolean(COSName.getPDFName("Open"), false);
    }

    public void setName(String str) {
        getCOSObject().setName(COSName.NAME, str);
    }

    public String getName() {
        return getCOSObject().getNameAsString(COSName.NAME, "Note");
    }

    public String getState() {
        return getCOSObject().getString(COSName.STATE);
    }

    public void setState(String str) {
        getCOSObject().setString(COSName.STATE, str);
    }

    public String getStateModel() {
        return getCOSObject().getString(COSName.STATE_MODEL);
    }

    public void setStateModel(String str) {
        getCOSObject().setString(COSName.STATE_MODEL, str);
    }
}
