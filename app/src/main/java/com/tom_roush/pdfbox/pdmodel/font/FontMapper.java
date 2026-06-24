package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.TrueTypeFont;

public interface FontMapper {
    CIDFontMapping getCIDFont(String str, PDFontDescriptor pDFontDescriptor, PDCIDSystemInfo pDCIDSystemInfo);

    FontMapping<FontBoxFont> getFontBoxFont(String str, PDFontDescriptor pDFontDescriptor);

    FontMapping<TrueTypeFont> getTrueTypeFont(String str, PDFontDescriptor pDFontDescriptor);
}
