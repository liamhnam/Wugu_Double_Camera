package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class PDActionSubmitForm extends PDAction {
    public static final String SUB_TYPE = "SubmitForm";

    public PDActionSubmitForm() {
        setSubType(SUB_TYPE);
    }

    public PDActionSubmitForm(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.f2260F, pDFileSpecification);
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
