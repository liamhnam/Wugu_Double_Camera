package com.tom_roush.pdfbox.pdmodel.graphics.pattern;

import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.IOException;
import java.io.InputStream;

public class PDTilingPattern extends PDAbstractPattern implements PDContentStream {
    public static final int PAINT_COLORED = 1;
    public static final int PAINT_UNCOLORED = 2;
    public static final int TILING_CONSTANT_SPACING = 1;
    public static final int TILING_CONSTANT_SPACING_FASTER_TILING = 3;
    public static final int TILING_NO_DISTORTION = 2;

    @Override
    public int getPatternType() {
        return 1;
    }

    public PDTilingPattern() {
        getCOSObject().setInt(COSName.PATTERN_TYPE, 1);
    }

    public PDTilingPattern(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    @Override
    public void setPaintType(int i) {
        getCOSObject().setInt(COSName.PAINT_TYPE, i);
    }

    public int getPaintType() {
        return getCOSObject().getInt(COSName.PAINT_TYPE, 0);
    }

    public void setTilingType(int i) {
        getCOSObject().setInt(COSName.TILING_TYPE, i);
    }

    public int getTilingType() {
        return getCOSObject().getInt(COSName.TILING_TYPE, 0);
    }

    public void setXStep(float f) {
        getCOSObject().setFloat(COSName.X_STEP, f);
    }

    public float getXStep() {
        float f = getCOSObject().getFloat(COSName.X_STEP, 0.0f);
        if (f == 32767.0f) {
            return 0.0f;
        }
        return f;
    }

    public void setYStep(float f) {
        getCOSObject().setFloat(COSName.Y_STEP, f);
    }

    public float getYStep() {
        float f = getCOSObject().getFloat(COSName.Y_STEP, 0.0f);
        if (f == 32767.0f) {
            return 0.0f;
        }
        return f;
    }

    public PDStream getContentStream() {
        return new PDStream((COSStream) getCOSObject());
    }

    @Override
    public InputStream getContents() throws IOException {
        if (getCOSObject() instanceof COSStream) {
            return ((COSStream) getCOSObject()).createInputStream();
        }
        return null;
    }

    @Override
    public PDResources getResources() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.RESOURCES);
        if (cOSDictionary != null) {
            return new PDResources(cOSDictionary);
        }
        return null;
    }

    public void setResources(PDResources pDResources) {
        getCOSObject().setItem(COSName.RESOURCES, pDResources);
    }

    @Override
    public PDRectangle getBBox() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    public void setBBox(PDRectangle pDRectangle) {
        if (pDRectangle == null) {
            getCOSObject().removeItem(COSName.BBOX);
        } else {
            getCOSObject().setItem(COSName.BBOX, (COSBase) pDRectangle.getCOSArray());
        }
    }
}
