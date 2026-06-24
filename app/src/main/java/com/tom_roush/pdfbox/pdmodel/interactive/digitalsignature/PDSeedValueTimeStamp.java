package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDSeedValueTimeStamp {
    private COSDictionary dictionary;

    public PDSeedValueTimeStamp() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDSeedValueTimeStamp(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public String getURL() {
        return this.dictionary.getString(COSName.URL);
    }

    public void setURL(String str) {
        this.dictionary.setString(COSName.URL, str);
    }

    public boolean isTimestampRequired() {
        return this.dictionary.getInt(COSName.f2264FT, 0) != 0;
    }

    public void setTimestampRequired(boolean z) {
        this.dictionary.setInt(COSName.f2264FT, z ? 1 : 0);
    }
}
