package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;

public class PDObjectStream extends PDStream {
    public PDObjectStream(COSStream cOSStream) {
        super(cOSStream);
    }

    public static PDObjectStream createStream(PDDocument pDDocument) {
        PDObjectStream pDObjectStream = new PDObjectStream(pDDocument.getDocument().createCOSStream());
        pDObjectStream.getCOSObject().setItem(COSName.TYPE, (COSBase) COSName.OBJ_STM);
        return pDObjectStream;
    }

    public String getType() {
        return getCOSObject().getNameAsString(COSName.TYPE);
    }

    public int getNumberOfObjects() {
        return getCOSObject().getInt(COSName.f2284N, 0);
    }

    public void setNumberOfObjects(int i) {
        getCOSObject().setInt(COSName.f2284N, i);
    }

    public int getFirstByteOffset() {
        return getCOSObject().getInt(COSName.FIRST, 0);
    }

    public void setFirstByteOffset(int i) {
        getCOSObject().setInt(COSName.FIRST, i);
    }

    public PDObjectStream getExtends() {
        COSStream cOSStream = (COSStream) getCOSObject().getDictionaryObject(COSName.EXTENDS);
        if (cOSStream != null) {
            return new PDObjectStream(cOSStream);
        }
        return null;
    }

    public void setExtends(PDObjectStream pDObjectStream) {
        getCOSObject().setItem(COSName.EXTENDS, pDObjectStream);
    }
}
