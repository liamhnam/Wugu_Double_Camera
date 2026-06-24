package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColor;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import com.tom_roush.pdfbox.pdmodel.graphics.form.PDFormXObject;

public class PDAppearanceCharacteristicsDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceCharacteristicsDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public int getRotation() {
        return getCOSObject().getInt(COSName.f2296R, 0);
    }

    public void setRotation(int i) {
        getCOSObject().setInt(COSName.f2296R, i);
    }

    public PDColor getBorderColour() {
        return getColor(COSName.f2233BC);
    }

    public void setBorderColour(PDColor pDColor) {
        getCOSObject().setItem(COSName.f2233BC, (COSBase) pDColor.toCOSArray());
    }

    public PDColor getBackground() {
        return getColor(COSName.f2235BG);
    }

    public void setBackground(PDColor pDColor) {
        getCOSObject().setItem(COSName.f2235BG, (COSBase) pDColor.toCOSArray());
    }

    public String getNormalCaption() {
        return getCOSObject().getString("CA");
    }

    public void setNormalCaption(String str) {
        getCOSObject().setString("CA", str);
    }

    public String getRolloverCaption() {
        return getCOSObject().getString("RC");
    }

    public void setRolloverCaption(String str) {
        getCOSObject().setString("RC", str);
    }

    public String getAlternateCaption() {
        return getCOSObject().getString("AC");
    }

    public void setAlternateCaption(String str) {
        getCOSObject().setString("AC", str);
    }

    public PDFormXObject getNormalIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject("I");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    public PDFormXObject getRolloverIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject("RI");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    public PDFormXObject getAlternateIcon() {
        COSBase dictionaryObject = getCOSObject().getDictionaryObject("IX");
        if (dictionaryObject instanceof COSStream) {
            return new PDFormXObject((COSStream) dictionaryObject);
        }
        return null;
    }

    private PDColor getColor(COSName cOSName) {
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
}
