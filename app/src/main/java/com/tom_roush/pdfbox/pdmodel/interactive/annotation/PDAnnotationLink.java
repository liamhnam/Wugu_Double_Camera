package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionURI;
import com.tom_roush.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
import java.io.IOException;

public class PDAnnotationLink extends PDAnnotation {
    public static final String HIGHLIGHT_MODE_INVERT = "I";
    public static final String HIGHLIGHT_MODE_NONE = "N";
    public static final String HIGHLIGHT_MODE_OUTLINE = "O";
    public static final String HIGHLIGHT_MODE_PUSH = "P";
    public static final String SUB_TYPE = "Link";

    public PDAnnotationLink() {
        getCOSObject().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName("Link"));
    }

    public PDAnnotationLink(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) getCOSObject().getDictionaryObject(COSName.f2228A));
    }

    public void setAction(PDAction pDAction) {
        getCOSObject().setItem(COSName.f2228A, pDAction);
    }

    public void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        getCOSObject().setItem(COSName.f2237BS, pDBorderStyleDictionary);
    }

    public PDBorderStyleDictionary getBorderStyle() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2237BS);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDBorderStyleDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public PDDestination getDestination() throws IOException {
        return PDDestination.create(getCOSObject().getDictionaryObject(COSName.DEST));
    }

    public void setDestination(PDDestination pDDestination) {
        getCOSObject().setItem(COSName.DEST, pDDestination);
    }

    public String getHighlightMode() {
        return getCOSObject().getNameAsString(COSName.f2266H, "I");
    }

    public void setHighlightMode(String str) {
        getCOSObject().setName(COSName.f2266H, str);
    }

    public void setPreviousURI(PDActionURI pDActionURI) {
        getCOSObject().setItem("PA", pDActionURI);
    }

    public PDActionURI getPreviousURI() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject("PA");
        if (cOSDictionary != null) {
            return new PDActionURI(cOSDictionary);
        }
        return null;
    }

    public void setQuadPoints(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getCOSObject().setItem("QuadPoints", (COSBase) cOSArray);
    }

    public float[] getQuadPoints() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject("QuadPoints");
        if (cOSArray != null) {
            return cOSArray.toFloatArray();
        }
        return null;
    }
}
