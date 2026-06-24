package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.PDXObject;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import com.tom_roush.pdfbox.pdmodel.interactive.annotation.PDAnnotationUnknown;
import java.io.IOException;

public class PDObjectReference implements COSObjectable {
    public static final String TYPE = "OBJR";
    private final COSDictionary dictionary;

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDObjectReference() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setName(COSName.TYPE, TYPE);
    }

    public PDObjectReference(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    public COSObjectable getReferencedObject() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.OBJ);
        if (!(dictionaryObject instanceof COSDictionary)) {
            return null;
        }
        try {
            PDXObject pDXObjectCreateXObject = PDXObject.createXObject(dictionaryObject, null);
            if (pDXObjectCreateXObject != null) {
                return pDXObjectCreateXObject;
            }
            COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
            PDAnnotation pDAnnotationCreateAnnotation = PDAnnotation.createAnnotation(dictionaryObject);
            if (pDAnnotationCreateAnnotation instanceof PDAnnotationUnknown) {
                if (COSName.ANNOT.equals(cOSDictionary.getDictionaryObject(COSName.TYPE))) {
                }
            }
            return pDAnnotationCreateAnnotation;
        } catch (IOException unused) {
        }
        return null;
    }

    public void setReferencedObject(PDAnnotation pDAnnotation) {
        getCOSObject().setItem(COSName.OBJ, pDAnnotation);
    }

    public void setReferencedObject(PDXObject pDXObject) {
        getCOSObject().setItem(COSName.OBJ, pDXObject);
    }
}
