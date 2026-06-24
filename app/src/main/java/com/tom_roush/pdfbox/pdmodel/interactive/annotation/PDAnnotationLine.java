package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;

public class PDAnnotationLine extends PDAnnotationMarkup {
    public static final String IT_LINE_ARROW = "LineArrow";
    public static final String IT_LINE_DIMENSION = "LineDimension";
    public static final String LE_BUTT = "Butt";
    public static final String LE_CIRCLE = "Circle";
    public static final String LE_CLOSED_ARROW = "ClosedArrow";
    public static final String LE_DIAMOND = "Diamond";
    public static final String LE_NONE = "None";
    public static final String LE_OPEN_ARROW = "OpenArrow";
    public static final String LE_R_CLOSED_ARROW = "RClosedArrow";
    public static final String LE_R_OPEN_ARROW = "ROpenArrow";
    public static final String LE_SLASH = "Slash";
    public static final String LE_SQUARE = "Square";
    public static final String SUB_TYPE = "Line";

    public PDAnnotationLine() {
        getCOSObject().setItem(COSName.SUBTYPE, (COSBase) COSName.getPDFName("Line"));
        setLine(new float[]{0.0f, 0.0f, 0.0f, 0.0f});
    }

    public PDAnnotationLine(COSDictionary cOSDictionary) {
        super(cOSDictionary);
    }

    public void setLine(float[] fArr) {
        COSArray cOSArray = new COSArray();
        cOSArray.setFloatArray(fArr);
        getCOSObject().setItem(COSName.f2275L, (COSBase) cOSArray);
    }

    public float[] getLine() {
        return ((COSArray) getCOSObject().getDictionaryObject(COSName.f2275L)).toFloatArray();
    }

    public void setStartPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2277LE);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            getCOSObject().setItem(COSName.f2277LE, (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(0, str);
    }

    public String getStartPointEndingStyle() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2277LE);
        return cOSArray != null ? cOSArray.getName(0) : "None";
    }

    public void setEndPointEndingStyle(String str) {
        if (str == null) {
            str = "None";
        }
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2277LE);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSName.getPDFName("None"));
            cOSArray2.add((COSBase) COSName.getPDFName(str));
            getCOSObject().setItem(COSName.f2277LE, (COSBase) cOSArray2);
            return;
        }
        cOSArray.setName(1, str);
    }

    public String getEndPointEndingStyle() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2277LE);
        return cOSArray != null ? cOSArray.getName(1) : "None";
    }

    public void setInteriorColor(PDColor pDColor) {
        getCOSObject().setItem(COSName.f2268IC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getInteriorColor() {
        return getColor(COSName.f2268IC);
    }

    public void setCaption(boolean z) {
        getCOSObject().setBoolean(COSName.CAP, z);
    }

    public boolean getCaption() {
        return getCOSObject().getBoolean(COSName.CAP, false);
    }

    @Override
    public void setBorderStyle(PDBorderStyleDictionary pDBorderStyleDictionary) {
        getCOSObject().setItem(COSName.f2237BS, pDBorderStyleDictionary);
    }

    @Override
    public PDBorderStyleDictionary getBorderStyle() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2237BS);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDBorderStyleDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public float getLeaderLineLength() {
        return getCOSObject().getFloat(COSName.f2279LL);
    }

    public void setLeaderLineLength(float f) {
        getCOSObject().setFloat(COSName.f2279LL, f);
    }

    public float getLeaderLineExtensionLength() {
        return getCOSObject().getFloat(COSName.LLE);
    }

    public void setLeaderLineExtensionLength(float f) {
        getCOSObject().setFloat(COSName.LLE, f);
    }

    public float getLeaderLineOffsetLength() {
        return getCOSObject().getFloat(COSName.LLO);
    }

    public void setLeaderLineOffsetLength(float f) {
        getCOSObject().setFloat(COSName.LLO, f);
    }

    public String getCaptionPositioning() {
        return getCOSObject().getString(COSName.f2246CP);
    }

    public void setCaptionPositioning(String str) {
        getCOSObject().setString(COSName.f2246CP, str);
    }

    public void setCaptionHorizontalOffset(float f) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2245CO);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{f, 0.0f});
            getCOSObject().setItem(COSName.f2245CO, (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(0, (COSBase) new COSFloat(f));
    }

    public float getCaptionHorizontalOffset() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2245CO);
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[0];
        }
        return 0.0f;
    }

    public void setCaptionVerticalOffset(float f) {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2245CO);
        if (cOSArray == null) {
            COSArray cOSArray2 = new COSArray();
            cOSArray2.setFloatArray(new float[]{0.0f, f});
            getCOSObject().setItem(COSName.f2245CO, (COSBase) cOSArray2);
            return;
        }
        cOSArray.set(1, (COSBase) new COSFloat(f));
    }

    public float getCaptionVerticalOffset() {
        COSArray cOSArray = (COSArray) getCOSObject().getDictionaryObject(COSName.f2245CO);
        if (cOSArray != null) {
            return cOSArray.toFloatArray()[1];
        }
        return 0.0f;
    }
}
