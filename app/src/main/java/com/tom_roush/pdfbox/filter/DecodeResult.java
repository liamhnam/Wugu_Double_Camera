package com.tom_roush.pdfbox.filter;

import com.tom_roush.pdfbox.cos.COSDictionary;

public final class DecodeResult {
    public static final DecodeResult DEFAULT = new DecodeResult(new COSDictionary());
    private final COSDictionary parameters;

    DecodeResult(COSDictionary cOSDictionary) {
        this.parameters = cOSDictionary;
    }

    public COSDictionary getParameters() {
        return this.parameters;
    }
}
