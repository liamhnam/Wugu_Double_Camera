package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDActionResetForm extends PDAction {
    public static final String SUB_TYPE = "ResetForm";

    public PDActionResetForm() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionResetForm(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public COSArray getFields() {
        COSBase dictionaryObject = this.action.getDictionaryObject(COSName.FIELDS);
        if (dictionaryObject instanceof COSArray) {
            return (COSArray) dictionaryObject;
        }
        return null;
    }

    public void setFields(COSArray cOSArray) {
        this.action.setItem(COSName.FIELDS, (COSBase) cOSArray);
    }

    public int getFlags() {
        return this.action.getInt(COSName.FLAGS, 0);
    }

    public void setFlags(int i) {
        this.action.setInt(COSName.FLAGS, i);
    }
}
