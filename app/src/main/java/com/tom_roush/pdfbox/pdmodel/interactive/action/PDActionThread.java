package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class PDActionThread extends PDAction {
    public static final String SUB_TYPE = "Thread";

    public PDActionThread() {
        setSubType(SUB_TYPE);
    }

    public PDActionThread(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public COSBase getD() {
        return this.action.getDictionaryObject(COSName.f2248D);
    }

    public void setD(COSBase cOSBase) {
        this.action.setItem(COSName.f2248D, cOSBase);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.f2260F, pDFileSpecification);
    }

    public COSBase getB() {
        return this.action.getDictionaryObject(COSName.f2232B);
    }

    public void setB(COSBase cOSBase) {
        this.action.setItem(COSName.f2232B, cOSBase);
    }
}
