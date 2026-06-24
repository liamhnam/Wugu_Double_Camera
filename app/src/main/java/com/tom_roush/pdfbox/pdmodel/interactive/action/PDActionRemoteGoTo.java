package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class PDActionRemoteGoTo extends PDAction {
    public static final String SUB_TYPE = "GoToR";

    public PDActionRemoteGoTo() {
        this.action = new COSDictionary();
        setSubType(SUB_TYPE);
    }

    public PDActionRemoteGoTo(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getS() {
        return this.action.getNameAsString(COSName.f2301S);
    }

    public void setS(String str) {
        this.action.setName(COSName.f2301S, str);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(this.action.getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        this.action.setItem(COSName.f2260F, pDFileSpecification);
    }

    public COSBase getD() {
        return this.action.getDictionaryObject(COSName.f2248D);
    }

    public void setD(COSBase cOSBase) {
        this.action.setItem(COSName.f2248D, cOSBase);
    }

    public boolean shouldOpenInNewWindow() {
        return this.action.getBoolean("NewWindow", true);
    }

    public void setOpenInNewWindow(boolean z) {
        this.action.setBoolean("NewWindow", z);
    }
}
