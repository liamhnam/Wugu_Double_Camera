package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.IOException;
import java.util.Calendar;

public class PDAnnotationMarkup extends PDAnnotation {
    public static final String RT_GROUP = "Group";
    public static final String RT_REPLY = "R";
    public static final String SUB_TYPE_CARET = "Caret";
    public static final String SUB_TYPE_FREETEXT = "FreeText";
    public static final String SUB_TYPE_INK = "Ink";
    public static final String SUB_TYPE_POLYGON = "Polygon";
    public static final String SUB_TYPE_POLYLINE = "PolyLine";
    public static final String SUB_TYPE_SOUND = "Sound";

    public PDAnnotationMarkup() {
    }

    public PDAnnotationMarkup(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public String getTitlePopup() {
        return getCOSObject().getString(COSName.f2310T);
    }

    public void setTitlePopup(String str) {
        getCOSObject().setString(COSName.f2310T, str);
    }

    public PDAnnotationPopup getPopup() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(PDAnnotationPopup.SUB_TYPE);
        if (cOSDictionary != null) {
            return new PDAnnotationPopup(cOSDictionary);
        }
        return null;
    }

    public void setPopup(PDAnnotationPopup pDAnnotationPopup) {
        getCOSObject().setItem(PDAnnotationPopup.SUB_TYPE, pDAnnotationPopup);
    }

    public float getConstantOpacity() {
        return getCOSObject().getFloat(COSName.f2241CA, 1.0f);
    }

    public void setConstantOpacity(float f) {
        getCOSObject().setFloat(COSName.f2241CA, f);
    }

    public String getRichContents() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2297RC);
        if (dictionaryObject instanceof COSString) {
            return ((COSString) dictionaryObject).getString();
        }
        if (dictionaryObject instanceof COSStream) {
            return ((COSStream) dictionaryObject).toTextString();
        }
        return null;
    }

    public void setRichContents(String str) {
        getCOSObject().setItem(COSName.f2297RC, (COSBase) new COSString(str));
    }

    public Calendar getCreationDate() throws IOException {
        return getCOSObject().getDate(COSName.CREATION_DATE);
    }

    public void setCreationDate(Calendar calendar) {
        getCOSObject().setDate(COSName.CREATION_DATE, calendar);
    }

    public PDAnnotation getInReplyTo() throws IOException {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject("IRT");
        if (dictionaryObject instanceof COSDictionary) {
            return PDAnnotation.createAnnotation(dictionaryObject);
        }
        return null;
    }

    public void setInReplyTo(PDAnnotation pDAnnotation) {
        getCOSObject().setItem("IRT", pDAnnotation);
    }

    public String getSubject() {
        return getCOSObject().getString(COSName.SUBJ);
    }

    public void setSubject(String str) {
        getCOSObject().setString(COSName.SUBJ, str);
    }

    public String getReplyType() {
        return getCOSObject().getNameAsString(StandardStructureTypes.f2379RT, "R");
    }

    public void setReplyType(String str) {
        getCOSObject().setName(StandardStructureTypes.f2379RT, str);
    }

    public String getIntent() {
        return getCOSObject().getNameAsString(COSName.f2272IT);
    }

    public void setIntent(String str) {
        getCOSObject().setName(COSName.f2272IT, str);
    }

    public PDExternalDataDictionary getExternalData() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject("ExData");
        if (dictionaryObject instanceof COSDictionary) {
            return new PDExternalDataDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setExternalData(PDExternalDataDictionary pDExternalDataDictionary) {
        getCOSObject().setItem("ExData", pDExternalDataDictionary);
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
}
