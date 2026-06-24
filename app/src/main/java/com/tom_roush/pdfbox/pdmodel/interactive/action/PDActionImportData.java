package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class PDActionImportData extends PDAction {
    public static final String SUB_TYPE = "ImportData";

    public PDActionImportData() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionImportData(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.f2260F, pDFileSpecification);
    }
}
