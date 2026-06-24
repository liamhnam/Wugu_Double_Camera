package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDShadingType2 extends PDShading {
    private COSArray coords;
    private COSArray domain;
    private COSArray extend;

    @Override
    public int getShadingType() {
        return 2;
    }

    public PDShadingType2(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.coords = null;
        this.domain = null;
        this.extend = null;
    }

    public COSArray getExtend() {
        if (this.extend == null) {
            this.extend = (COSArray) getCOSObject().getDictionaryObject(COSName.EXTEND);
        }
        return this.extend;
    }

    public void setExtend(COSArray cOSArray) {
        this.extend = cOSArray;
        getCOSObject().setItem(COSName.EXTEND, (COSBase) cOSArray);
    }

    public COSArray getDomain() {
        if (this.domain == null) {
            this.domain = (COSArray) getCOSObject().getDictionaryObject(COSName.DOMAIN);
        }
        return this.domain;
    }

    public void setDomain(COSArray cOSArray) {
        this.domain = cOSArray;
        getCOSObject().setItem(COSName.DOMAIN, (COSBase) cOSArray);
    }

    public COSArray getCoords() {
        if (this.coords == null) {
            this.coords = (COSArray) getCOSObject().getDictionaryObject(COSName.COORDS);
        }
        return this.coords;
    }

    public void setCoords(COSArray cOSArray) {
        this.coords = cOSArray;
        getCOSObject().setItem(COSName.COORDS, (COSBase) cOSArray);
    }
}
