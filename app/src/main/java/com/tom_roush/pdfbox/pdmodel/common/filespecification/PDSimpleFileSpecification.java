package com.tom_roush.pdfbox.pdmodel.common.filespecification;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSString;

public class PDSimpleFileSpecification extends PDFileSpecification {
    private COSString file;

    public PDSimpleFileSpecification() {
        this.file = new COSString("");
    }

    public PDSimpleFileSpecification(COSString cOSString) {
        this.file = cOSString;
    }

    @Override
    public String getFile() {
        return this.file.getString();
    }

    @Override
    public void setFile(String str) {
        this.file = new COSString(str);
    }

    @Override
    public COSBase getCOSObject() {
        return this.file;
    }
}
