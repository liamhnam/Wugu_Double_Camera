package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.FontBoxFont;
import org.bouncycastle.asn1.cmc.BodyPartID;

public abstract class FontInfo {
    public abstract CIDSystemInfo getCIDSystemInfo();

    public abstract int getCodePageRange1();

    public abstract int getCodePageRange2();

    public abstract int getFamilyClass();

    public abstract FontBoxFont getFont();

    public abstract FontFormat getFormat();

    public abstract int getMacStyle();

    public abstract PDPanoseClassification getPanose();

    public abstract String getPostScriptName();

    public abstract int getWeightClass();

    final int getWeightClassAsPanose() {
        int weightClass = getWeightClass();
        if (weightClass == 100) {
            return 2;
        }
        if (weightClass == 200) {
            return 3;
        }
        if (weightClass == 300) {
            return 4;
        }
        if (weightClass == 400) {
            return 5;
        }
        if (weightClass == 500) {
            return 6;
        }
        if (weightClass == 600) {
            return 7;
        }
        if (weightClass == 700) {
            return 8;
        }
        if (weightClass != 800) {
            return weightClass != 900 ? 0 : 10;
        }
        return 9;
    }

    final long getCodePageRange() {
        return (((long) getCodePageRange1()) & BodyPartID.bodyIdMax) | ((BodyPartID.bodyIdMax & ((long) getCodePageRange2())) << 32);
    }

    public String toString() {
        return getPostScriptName() + " (" + getFormat() + ", mac: 0x" + Integer.toHexString(getMacStyle()) + ", os/2: 0x" + Integer.toHexString(getFamilyClass()) + ", cid: " + getCIDSystemInfo() + ")";
    }
}
