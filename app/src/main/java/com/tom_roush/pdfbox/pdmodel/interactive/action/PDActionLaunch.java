package com.tom_roush.pdfbox.pdmodel.interactive.action;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
import java.io.IOException;

public class PDActionLaunch extends PDAction {
    public static final String SUB_TYPE = "Launch";

    public PDActionLaunch() {
        setSubType(SUB_TYPE);
    }

    public PDActionLaunch(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDFileSpecification getFile() throws IOException {
        return PDFileSpecification.createFS(getCOSObject().getDictionaryObject(COSName.f2260F));
    }

    public void setFile(PDFileSpecification pDFileSpecification) {
        getCOSObject().setItem(COSName.f2260F, pDFileSpecification);
    }

    public PDWindowsLaunchParams getWinLaunchParams() {
        COSDictionary cOSDictionary = (COSDictionary) this.action.getDictionaryObject("Win");
        if (cOSDictionary != null) {
            return new PDWindowsLaunchParams(cOSDictionary);
        }
        return null;
    }

    public void setWinLaunchParams(PDWindowsLaunchParams pDWindowsLaunchParams) {
        this.action.setItem("Win", pDWindowsLaunchParams);
    }

    public String getF() {
        return this.action.getString(COSName.f2260F);
    }

    public void setF(String str) {
        this.action.setString(COSName.f2260F, str);
    }

    public String getD() {
        return this.action.getString(COSName.f2248D);
    }

    public void setD(String str) {
        this.action.setString(COSName.f2248D, str);
    }

    public String getO() {
        return this.action.getString(COSName.f2286O);
    }

    public void setO(String str) {
        this.action.setString(COSName.f2286O, str);
    }

    public String getP() {
        return this.action.getString(COSName.f2292P);
    }

    public void setP(String str) {
        this.action.setString(COSName.f2292P, str);
    }

    public boolean shouldOpenInNewWindow() {
        return this.action.getBoolean("NewWindow", true);
    }

    public void setOpenInNewWindow(boolean z) {
        this.action.setBoolean("NewWindow", z);
    }
}
