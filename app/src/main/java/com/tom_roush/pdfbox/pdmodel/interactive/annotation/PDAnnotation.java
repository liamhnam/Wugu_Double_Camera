package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.pdmodel.PDPage;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import java.io.IOException;
import java.util.Calendar;

public abstract class PDAnnotation implements COSObjectable {
    private static final int FLAG_HIDDEN = 2;
    private static final int FLAG_INVISIBLE = 1;
    private static final int FLAG_LOCKED = 128;
    private static final int FLAG_NO_ROTATE = 16;
    private static final int FLAG_NO_VIEW = 32;
    private static final int FLAG_NO_ZOOM = 8;
    private static final int FLAG_PRINTED = 4;
    private static final int FLAG_READ_ONLY = 64;
    private static final int FLAG_TOGGLE_NO_VIEW = 256;
    private final COSDictionary dictionary;

    public static PDAnnotation createAnnotation(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSDictionary) {
            COSDictionary cOSDictionary = (COSDictionary) cOSBase;
            String nameAsString = cOSDictionary.getNameAsString(COSName.SUBTYPE);
            if ("FileAttachment".equals(nameAsString)) {
                return new PDAnnotationFileAttachment(cOSDictionary);
            }
            if ("Line".equals(nameAsString)) {
                return new PDAnnotationLine(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new PDAnnotationLink(cOSDictionary);
            }
            if (PDAnnotationPopup.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationPopup(cOSDictionary);
            }
            if ("Stamp".equals(nameAsString)) {
                return new PDAnnotationRubberStamp(cOSDictionary);
            }
            if ("Square".equals(nameAsString) || "Circle".equals(nameAsString)) {
                return new PDAnnotationSquareCircle(cOSDictionary);
            }
            if ("Text".equals(nameAsString)) {
                return new PDAnnotationText(cOSDictionary);
            }
            if ("Highlight".equals(nameAsString) || "Underline".equals(nameAsString) || "Squiggly".equals(nameAsString) || "StrikeOut".equals(nameAsString)) {
                return new PDAnnotationTextMarkup(cOSDictionary);
            }
            if ("Link".equals(nameAsString)) {
                return new PDAnnotationLink(cOSDictionary);
            }
            if (PDAnnotationWidget.SUB_TYPE.equals(nameAsString)) {
                return new PDAnnotationWidget(cOSDictionary);
            }
            if ("FreeText".equals(nameAsString) || "Polygon".equals(nameAsString) || PDAnnotationMarkup.SUB_TYPE_POLYLINE.equals(nameAsString) || "Caret".equals(nameAsString) || "Ink".equals(nameAsString) || "Sound".equals(nameAsString)) {
                return new PDAnnotationMarkup(cOSDictionary);
            }
            PDAnnotationUnknown pDAnnotationUnknown = new PDAnnotationUnknown(cOSDictionary);
            Log.d("PdfBox-Android", "Unknown or unsupported annotation subtype " + nameAsString);
            return pDAnnotationUnknown;
        }
        throw new IOException("Error: Unknown annotation type " + cOSBase);
    }

    public PDAnnotation() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public PDAnnotation(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.ANNOT);
    }

    public PDRectangle getRectangle() {
        COSArray cOSArray = (COSArray) this.dictionary.getDictionaryObject(COSName.RECT);
        if (cOSArray != null) {
            if (cOSArray.size() == 4 && (cOSArray.get(0) instanceof COSNumber) && (cOSArray.get(1) instanceof COSNumber) && (cOSArray.get(2) instanceof COSNumber) && (cOSArray.get(3) instanceof COSNumber)) {
                return new PDRectangle(cOSArray);
            }
            Log.w("PdfBox-Android", cOSArray + " is not a rectangle array, returning null");
        }
        return null;
    }

    public void setRectangle(PDRectangle pDRectangle) {
        this.dictionary.setItem(COSName.RECT, (COSBase) pDRectangle.getCOSArray());
    }

    public int getAnnotationFlags() {
        return getCOSObject().getInt(COSName.f2260F, 0);
    }

    public void setAnnotationFlags(int i) {
        getCOSObject().setInt(COSName.f2260F, i);
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public COSName getAppearanceState() {
        COSName cOSName = (COSName) getCOSObject().getDictionaryObject(COSName.f2231AS);
        if (cOSName != null) {
            return cOSName;
        }
        return null;
    }

    public void setAppearanceState(String str) {
        if (str == null) {
            getCOSObject().removeItem(COSName.f2231AS);
        } else {
            getCOSObject().setItem(COSName.f2231AS, (COSBase) COSName.getPDFName(str));
        }
    }

    public PDAppearanceDictionary getAppearance() {
        COSDictionary cOSDictionary = (COSDictionary) this.dictionary.getDictionaryObject(COSName.f2230AP);
        if (cOSDictionary != null) {
            return new PDAppearanceDictionary(cOSDictionary);
        }
        return null;
    }

    public void setAppearance(PDAppearanceDictionary pDAppearanceDictionary) {
        this.dictionary.setItem(COSName.f2230AP, (COSBase) (pDAppearanceDictionary != null ? pDAppearanceDictionary.getCOSObject() : null));
    }

    public PDAppearanceStream getNormalAppearanceStream() {
        PDAppearanceEntry normalAppearance;
        PDAppearanceDictionary appearance = getAppearance();
        if (appearance == null || (normalAppearance = appearance.getNormalAppearance()) == null) {
            return null;
        }
        if (normalAppearance.isSubDictionary()) {
            return normalAppearance.getSubDictionary().get(getAppearanceState());
        }
        return normalAppearance.getAppearanceStream();
    }

    public boolean isInvisible() {
        return getCOSObject().getFlag(COSName.f2260F, 1);
    }

    public void setInvisible(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 1, z);
    }

    public boolean isHidden() {
        return getCOSObject().getFlag(COSName.f2260F, 2);
    }

    public void setHidden(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 2, z);
    }

    public boolean isPrinted() {
        return getCOSObject().getFlag(COSName.f2260F, 4);
    }

    public void setPrinted(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 4, z);
    }

    public boolean isNoZoom() {
        return getCOSObject().getFlag(COSName.f2260F, 8);
    }

    public void setNoZoom(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 8, z);
    }

    public boolean isNoRotate() {
        return getCOSObject().getFlag(COSName.f2260F, 16);
    }

    public void setNoRotate(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 16, z);
    }

    public boolean isNoView() {
        return getCOSObject().getFlag(COSName.f2260F, 32);
    }

    public void setNoView(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 32, z);
    }

    public boolean isReadOnly() {
        return getCOSObject().getFlag(COSName.f2260F, 64);
    }

    public void setReadOnly(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 64, z);
    }

    public boolean isLocked() {
        return getCOSObject().getFlag(COSName.f2260F, 128);
    }

    public void setLocked(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 128, z);
    }

    public boolean isToggleNoView() {
        return getCOSObject().getFlag(COSName.f2260F, 256);
    }

    public void setToggleNoView(boolean z) {
        getCOSObject().setFlag(COSName.f2260F, 256, z);
    }

    public String getContents() {
        return this.dictionary.getString(COSName.CONTENTS);
    }

    public void setContents(String str) {
        this.dictionary.setString(COSName.CONTENTS, str);
    }

    public String getModifiedDate() {
        return getCOSObject().getString(COSName.f2281M);
    }

    public void setModifiedDate(String str) {
        getCOSObject().setString(COSName.f2281M, str);
    }

    public void setModifiedDate(Calendar calendar) {
        getCOSObject().setDate(COSName.f2281M, calendar);
    }

    public String getAnnotationName() {
        return getCOSObject().getString(COSName.f2285NM);
    }

    public void setAnnotationName(String str) {
        getCOSObject().setString(COSName.f2285NM, str);
    }

    public int getStructParent() {
        return getCOSObject().getInt(COSName.STRUCT_PARENT, 0);
    }

    public void setStructParent(int i) {
        getCOSObject().setInt(COSName.STRUCT_PARENT, i);
    }

    public COSArray getBorder() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.BORDER);
        if (!(dictionaryObject instanceof COSArray)) {
            COSArray cOSArray = new COSArray();
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ZERO);
            cOSArray.add((COSBase) COSInteger.ONE);
            return cOSArray;
        }
        return (COSArray) dictionaryObject;
    }

    public void setBorder(COSArray cOSArray) {
        getCOSObject().setItem(COSName.BORDER, (COSBase) cOSArray);
    }

    public void setColor(PDColor pDColor) {
        getCOSObject().setItem(COSName.f2238C, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getColor() {
        return getColor(COSName.f2238C);
    }

    protected PDColor getColor(COSName cOSName) {
        COSBase item = getCOSObject().getItem(cOSName);
        PDColorSpace pDColorSpace = null;
        if (!(item instanceof COSArray)) {
            return null;
        }
        COSArray cOSArray = (COSArray) item;
        int size = cOSArray.size();
        if (size == 1) {
            pDColorSpace = PDDeviceGray.INSTANCE;
        } else if (size == 3) {
            pDColorSpace = PDDeviceRGB.INSTANCE;
        }
        return new PDColor(cOSArray, pDColorSpace);
    }

    public String getSubtype() {
        return getCOSObject().getNameAsString(COSName.SUBTYPE);
    }

    public void setPage(PDPage pDPage) {
        getCOSObject().setItem(COSName.f2292P, pDPage);
    }

    public PDPage getPage() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.f2292P);
        if (cOSDictionary != null) {
            return new PDPage(cOSDictionary);
        }
        return null;
    }
}
