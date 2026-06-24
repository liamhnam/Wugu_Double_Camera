package com.tom_roush.pdfbox.pdmodel.graphics.pattern;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.shading.PDShading;
import com.tom_roush.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import java.io.IOException;

public class PDShadingPattern extends PDAbstractPattern {
    private PDExtendedGraphicsState extendedGraphicsState;
    private PDShading shading;

    @Override
    public int getPatternType() {
        return 2;
    }

    public PDShadingPattern() {
        getCOSObject().setInt(COSName.PATTERN_TYPE, 2);
    }

    public PDShadingPattern(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDExtendedGraphicsState getExtendedGraphicsState() {
        COSDictionary cOSDictionary;
        if (this.extendedGraphicsState == null && (cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.EXT_G_STATE)) != null) {
            this.extendedGraphicsState = new PDExtendedGraphicsState(cOSDictionary);
        }
        return this.extendedGraphicsState;
    }

    public void setExtendedGraphicsState(PDExtendedGraphicsState pDExtendedGraphicsState) {
        this.extendedGraphicsState = pDExtendedGraphicsState;
        getCOSObject().setItem(COSName.EXT_G_STATE, pDExtendedGraphicsState);
    }

    public PDShading getShading() throws IOException {
        COSDictionary cOSDictionary;
        if (this.shading == null && (cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.SHADING)) != null) {
            this.shading = PDShading.create(cOSDictionary);
        }
        return this.shading;
    }

    public void setShading(PDShading pDShading) {
        this.shading = pDShading;
        getCOSObject().setItem(COSName.SHADING, pDShading);
    }
}
