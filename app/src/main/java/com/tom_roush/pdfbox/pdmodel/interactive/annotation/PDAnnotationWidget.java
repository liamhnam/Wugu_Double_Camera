package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAction;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDActionFactory;
import com.tom_roush.pdfbox.pdmodel.interactive.action.PDAnnotationAdditionalActions;

public class PDAnnotationWidget extends PDAnnotation {
    public static final String SUB_TYPE = "Widget";

    public PDAnnotationWidget() {
        getCOSObject().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    public PDAnnotationWidget(COSDictionary cOSDictionary) {
        super(cOSDictionary);
        getCOSObject().setName(COSName.SUBTYPE, SUB_TYPE);
    }

    public String getHighlightingMode() {
        return getCOSObject().getNameAsString(COSName.f2266H, "I");
    }

    public void setHighlightingMode(String str) {
        if (str == null || "N".equals(str) || "I".equals(str) || PDAnnotationLink.HIGHLIGHT_MODE_OUTLINE.equals(str) || "P".equals(str) || "T".equals(str)) {
            getCOSObject().setName(COSName.f2266H, str);
            return;
        }
        throw new IllegalArgumentException("Valid values for highlighting mode are 'N', 'N', 'O', 'P' or 'T'");
    }

    public PDAppearanceCharacteristicsDictionary getAppearanceCharacteristics() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject(COSName.f2282MK);
        if (dictionaryObject instanceof COSDictionary) {
            return new PDAppearanceCharacteristicsDictionary((COSDictionary) dictionaryObject);
        }
        return null;
    }

    public void setAppearanceCharacteristics(PDAppearanceCharacteristicsDictionary pDAppearanceCharacteristicsDictionary) {
        getCOSObject().setItem(COSName.f2282MK, pDAppearanceCharacteristicsDictionary);
    }

    public PDAction getAction() {
        return PDActionFactory.createAction((COSDictionary) getCOSObject().getDictionaryObject(COSName.f2228A));
    }

    public void setAction(PDAction pDAction) {
        getCOSObject().setItem(COSName.f2228A, pDAction);
    }

    public PDAnnotationAdditionalActions getActions() {
        COSDictionary cOSDictionary = (COSDictionary) getCOSObject().getDictionaryObject(COSName.f2229AA);
        if (cOSDictionary != null) {
            return new PDAnnotationAdditionalActions(cOSDictionary);
        }
        return null;
    }

    public void setActions(PDAnnotationAdditionalActions pDAnnotationAdditionalActions) {
        getCOSObject().setItem(COSName.f2229AA, pDAnnotationAdditionalActions);
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
