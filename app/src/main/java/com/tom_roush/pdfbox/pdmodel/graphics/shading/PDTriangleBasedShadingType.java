package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;

abstract class PDTriangleBasedShadingType extends PDShading {
    private COSArray decode;

    PDTriangleBasedShadingType(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        this.decode = null;
    }

    public int getBitsPerComponent() {
        return getCOSObject().getInt(COSName.BITS_PER_COMPONENT, -1);
    }

    public void setBitsPerComponent(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COMPONENT, i);
    }

    public int getBitsPerCoordinate() {
        return getCOSObject().getInt(COSName.BITS_PER_COORDINATE, -1);
    }

    public void setBitsPerCoordinate(int i) {
        getCOSObject().setInt(COSName.BITS_PER_COORDINATE, i);
    }

    private COSArray getDecodeValues() {
        if (this.decode == null) {
            this.decode = (COSArray) getCOSObject().getDictionaryObject(COSName.DECODE);
        }
        return this.decode;
    }

    public void setDecodeValues(COSArray cOSArray) {
        this.decode = cOSArray;
        getCOSObject().setItem(COSName.DECODE, (COSBase) cOSArray);
    }

    public PDRange getDecodeForParameter(int i) {
        COSArray decodeValues = getDecodeValues();
        if (decodeValues == null || decodeValues.size() < (i * 2) + 1) {
            return null;
        }
        return new PDRange(decodeValues, i);
    }
}
