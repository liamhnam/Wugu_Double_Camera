package com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;

public class PDPropBuildDataDict implements COSObjectable {
    private final COSDictionary dictionary;

    public PDPropBuildDataDict() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    public PDPropBuildDataDict(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
        cOSDictionary.setDirect(true);
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public String getName() {
        return this.dictionary.getNameAsString(COSName.NAME);
    }

    public void setName(String str) {
        this.dictionary.setName(COSName.NAME, str);
    }

    public String getDate() {
        return this.dictionary.getString(COSName.DATE);
    }

    public void setDate(String str) {
        this.dictionary.setString(COSName.DATE, str);
    }

    public void setVersion(String str) {
        this.dictionary.setString("REx", str);
    }

    public String getVersion() {
        return this.dictionary.getString("REx");
    }

    public long getRevision() {
        return this.dictionary.getLong(COSName.f2296R);
    }

    public void setRevision(long j) {
        this.dictionary.setLong(COSName.f2296R, j);
    }

    public long getMinimumRevision() {
        return this.dictionary.getLong(COSName.f2320V);
    }

    public void setMinimumRevision(long j) {
        this.dictionary.setLong(COSName.f2320V, j);
    }

    public boolean getPreRelease() {
        return this.dictionary.getBoolean(COSName.PRE_RELEASE, false);
    }

    public void setPreRelease(boolean z) {
        this.dictionary.setBoolean(COSName.PRE_RELEASE, z);
    }

    public String getOS() {
        COSBase item = this.dictionary.getItem(COSName.f2291OS);
        if (item instanceof COSArray) {
            return ((COSArray) item).getName(0);
        }
        return this.dictionary.getString(COSName.f2291OS);
    }

    public void setOS(String str) {
        if (str == null) {
            this.dictionary.removeItem(COSName.f2291OS);
            return;
        }
        COSBase item = this.dictionary.getItem(COSName.f2291OS);
        if (!(item instanceof COSArray)) {
            item = new COSArray();
            item.setDirect(true);
            this.dictionary.setItem(COSName.f2291OS, item);
        }
        ((COSArray) item).add(0, COSName.getPDFName(str));
    }

    public boolean getNonEFontNoWarn() {
        return this.dictionary.getBoolean(COSName.NON_EFONT_NO_WARN, true);
    }

    public boolean getTrustedMode() {
        return this.dictionary.getBoolean(COSName.TRUSTED_MODE, false);
    }

    public void setTrustedMode(boolean z) {
        this.dictionary.setBoolean(COSName.TRUSTED_MODE, z);
    }
}
