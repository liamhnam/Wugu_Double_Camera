package com.tom_roush.pdfbox.pdmodel.common.filespecification;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class PDEmbeddedFile extends PDStream {
    public PDEmbeddedFile(PDDocument pDDocument) {
        super(pDDocument);
        getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
    }

    public PDEmbeddedFile(COSStream cOSStream) {
        super(cOSStream);
    }

    public PDEmbeddedFile(PDDocument pDDocument, InputStream inputStream) throws IOException {
        super(pDDocument, inputStream);
        getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
    }

    public PDEmbeddedFile(PDDocument pDDocument, InputStream inputStream, COSName cOSName) throws IOException {
        super(pDDocument, inputStream, cOSName);
        getCOSObject().setName(COSName.TYPE, "EmbeddedFile");
    }

    public void setSubtype(String str) {
        getCOSObject().setName(COSName.SUBTYPE, str);
    }

    public String getSubtype() {
        return getCOSObject().getNameAsString(COSName.SUBTYPE);
    }

    public int getSize() {
        return getCOSObject().getEmbeddedInt("Params", "Size");
    }

    public void setSize(int i) {
        getCOSObject().setEmbeddedInt("Params", "Size", i);
    }

    public Calendar getCreationDate() throws IOException {
        return getCOSObject().getEmbeddedDate("Params", "CreationDate");
    }

    public void setCreationDate(Calendar calendar) {
        getCOSObject().setEmbeddedDate("Params", "CreationDate", calendar);
    }

    public Calendar getModDate() throws IOException {
        return getCOSObject().getEmbeddedDate("Params", "ModDate");
    }

    public void setModDate(Calendar calendar) {
        getCOSObject().setEmbeddedDate("Params", "ModDate", calendar);
    }

    public String getCheckSum() {
        return getCOSObject().getEmbeddedString("Params", "CheckSum");
    }

    public void setCheckSum(String str) {
        getCOSObject().setEmbeddedString("Params", "CheckSum", str);
    }

    public String getMacSubtype() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "Subtype");
        }
        return null;
    }

    public void setMacSubtype(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getCOSObject().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "Subtype", str);
        }
    }

    public String getMacCreator() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "Creator");
        }
        return null;
    }

    public void setMacCreator(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getCOSObject().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "Creator", str);
        }
    }

    public String getMacResFork() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary != null) {
            return cOSDictionary.getEmbeddedString("Mac", "ResFork");
        }
        return null;
    }

    public void setMacResFork(String str) {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.PARAMS);
        if (cOSDictionary == null && str != null) {
            cOSDictionary = new COSDictionary();
            getCOSObject().setItem(COSName.PARAMS, (COSBase) cOSDictionary);
        }
        if (cOSDictionary != null) {
            cOSDictionary.setEmbeddedString("Mac", "ResFork", str);
        }
    }
}
