package com.tom_roush.pdfbox.pdmodel.graphics.shading;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.PDRange;

public class PDShadingType5 extends PDTriangleBasedShadingType {
    @Override
    public int getShadingType() {
        return 5;
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

    public PDShadingType5(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public int getVerticesPerRow() {
        return getCOSObject().getInt(COSName.VERTICES_PER_ROW, -1);
    }

    public void setVerticesPerRow(int i) {
        getCOSObject().setInt(COSName.VERTICES_PER_ROW, i);
    }
}
