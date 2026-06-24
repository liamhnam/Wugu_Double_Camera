package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;

public class PDSeedValueMDP {
    private COSDictionary dictionary;

    public PDSeedValueMDP() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDSeedValueMDP(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public int getP() {
        return this.dictionary.getInt(COSName.f2292P);
    }

    public void setP(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Only values between 0 and 3 nare allowed.");
        }
        this.dictionary.setInt(COSName.f2292P, i);
    }
}
