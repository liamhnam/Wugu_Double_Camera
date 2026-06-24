package com.tom_roush.pdfbox.pdmodel;

import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.pattern.PDAbstractPattern;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import java.io.IOException;

public interface ResourceCache {
    PDColorSpace getColorSpace(COSObject cOSObject) throws IOException;

    PDExtendedGraphicsState getExtGState(COSObject cOSObject);

    PDFont getFont(COSObject cOSObject) throws IOException;

    PDAbstractPattern getPattern(COSObject cOSObject) throws IOException;

    PDPropertyList getProperties(COSObject cOSObject);

    PDShading getShading(COSObject cOSObject) throws IOException;

    PDXObject getXObject(COSObject cOSObject) throws IOException;

    void put(COSObject cOSObject, PDPropertyList pDPropertyList);

    void put(COSObject cOSObject, PDFont pDFont) throws IOException;

    void put(COSObject cOSObject, PDXObject pDXObject) throws IOException;

    void put(COSObject cOSObject, PDColorSpace pDColorSpace) throws IOException;

    void put(COSObject cOSObject, PDAbstractPattern pDAbstractPattern) throws IOException;

    void put(COSObject cOSObject, PDShading pDShading) throws IOException;

    void put(COSObject cOSObject, PDExtendedGraphicsState pDExtendedGraphicsState);
}
