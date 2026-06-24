package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;

public class PDShadingType4 extends PDTriangleBasedShadingType {
    @Override
    public int getShadingType() {
        return 4;
    }

    @Override
    public int getBitsPerComponent() {
        return super.getBitsPerComponent();
    }

    @Override
    public int getBitsPerCoordinate() {
        return super.getBitsPerCoordinate();
    }

    @Override
    public PDRange getDecodeForParameter(int i) {
        return super.getDecodeForParameter(i);
    }

    @Override
    public void setBitsPerComponent(int i) {
        super.setBitsPerComponent(i);
    }

    @Override
    public void setBitsPerCoordinate(int i) {
        super.setBitsPerCoordinate(i);
    }

    @Override
    public void setDecodeValues(COSArray cOSArray) {
        super.setDecodeValues(cOSArray);
    }

    public PDShadingType4(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getBitsPerFlag() {
        return getCOSObject().getInt(COSName.BITS_PER_FLAG, -1);
    }

    public void setBitsPerFlag(int i) {
        getCOSObject().setInt(COSName.BITS_PER_FLAG, i);
    }
}
