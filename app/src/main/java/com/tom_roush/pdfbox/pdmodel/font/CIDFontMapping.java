package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.OpenTypeFont;

public final class CIDFontMapping extends FontMapping<OpenTypeFont> {
    private final FontBoxFont ttf;

    public CIDFontMapping(OpenTypeFont openTypeFont, FontBoxFont fontBoxFont, boolean z) {
        super(openTypeFont, z);
        this.ttf = fontBoxFont;
    }

    public FontBoxFont getTrueTypeFont() {
        return this.ttf;
    }

    public boolean isCIDFont() {
        return getFont() != null;
    }
}
