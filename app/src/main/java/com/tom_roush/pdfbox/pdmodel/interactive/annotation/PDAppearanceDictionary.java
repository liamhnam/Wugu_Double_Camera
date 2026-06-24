package com.tom_roush.pdfbox.pdmodel.interactive.annotation;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDAppearanceDictionary implements COSObjectable {
    private final COSDictionary dictionary;

    public PDAppearanceDictionary() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setItem(COSName.f2284N, (COSBase) new COSDictionary());
    }

    public PDAppearanceDictionary(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDAppearanceEntry getNormalAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.f2284N);
        if (dictionaryObject == null) {
            return null;
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setNormalAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.f2284N, pDAppearanceEntry);
    }

    public void setNormalAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.f2284N, pDAppearanceStream);
    }

    public PDAppearanceEntry getRolloverAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.f2296R);
        if (dictionaryObject == null) {
            return getNormalAppearance();
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setRolloverAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.f2296R, pDAppearanceEntry);
    }

    public void setRolloverAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.f2296R, pDAppearanceStream);
    }

    public PDAppearanceEntry getDownAppearance() {
        COSBase dictionaryObject = this.dictionary.getDictionaryObject(COSName.f2248D);
        if (dictionaryObject == null) {
            return getNormalAppearance();
        }
        return new PDAppearanceEntry(dictionaryObject);
    }

    public void setDownAppearance(PDAppearanceEntry pDAppearanceEntry) {
        this.dictionary.setItem(COSName.f2248D, pDAppearanceEntry);
    }

    public void setDownAppearance(PDAppearanceStream pDAppearanceStream) {
        this.dictionary.setItem(COSName.f2248D, pDAppearanceStream);
    }
}
