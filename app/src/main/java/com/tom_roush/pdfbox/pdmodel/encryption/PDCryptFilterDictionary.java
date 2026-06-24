package com.tom_roush.pdfbox.pdmodel.encryption;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDCryptFilterDictionary {
    protected COSDictionary cryptFilterDictionary;

    public PDCryptFilterDictionary() {
        this.cryptFilterDictionary = null;
        this.cryptFilterDictionary = new COSDictionary();
    }

    public PDCryptFilterDictionary(COSDictionary cOSDictionary) {
        this.cryptFilterDictionary = cOSDictionary;
    }

    public COSDictionary getCOSDictionary() {
        return this.cryptFilterDictionary;
    }

    public void setLength(int i) {
        this.cryptFilterDictionary.setInt(COSName.LENGTH, i);
    }

    public int getLength() {
        return this.cryptFilterDictionary.getInt(COSName.LENGTH, 40);
    }

    public void setCryptFilterMethod(COSName cOSName) {
        this.cryptFilterDictionary.setItem(COSName.CFM, (COSBase) cOSName);
    }

    public COSName getCryptFilterMethod() {
        return (COSName) this.cryptFilterDictionary.getDictionaryObject(COSName.CFM);
    }
}
