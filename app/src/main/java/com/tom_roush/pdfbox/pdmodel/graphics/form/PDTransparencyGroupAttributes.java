package com.tom_roush.pdfbox.pdmodel.graphics.form;

import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;

public final class PDTransparencyGroupAttributes implements COSObjectable {
    private PDColorSpace colorSpace;
    private final COSDictionary dictionary;

    public PDTransparencyGroupAttributes(COSDictionary cOSDictionary) {
        this.dictionary = cOSDictionary;
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dictionary;
    }

    public PDColorSpace getColorSpace() throws IOException {
        if (this.colorSpace == null) {
            this.colorSpace = PDColorSpace.create(getCOSObject().getDictionaryObject(COSName.f2247CS));
        }
        return this.colorSpace;
    }

    public boolean isIsolated() {
        return getCOSObject().getBoolean(COSName.f2267I, false);
    }

    public boolean isKnockout() {
        return getCOSObject().getBoolean(COSName.f2274K, false);
    }
}
