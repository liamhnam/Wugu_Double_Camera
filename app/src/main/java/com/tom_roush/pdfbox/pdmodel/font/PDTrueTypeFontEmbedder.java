package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.ttf.HorizontalMetricsTable;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

final class PDTrueTypeFontEmbedder extends TrueTypeEmbedder {
    private final Encoding fontEncoding;

    PDTrueTypeFontEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, Encoding encoding) throws IOException {
        super(pDDocument, cOSDictionary, inputStream, false);
        cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.TRUE_TYPE);
        GlyphList adobeGlyphList = GlyphList.getAdobeGlyphList();
        this.fontEncoding = encoding;
        cOSDictionary.setItem(COSName.ENCODING, encoding.getCOSObject());
        this.fontDescriptor.setSymbolic(false);
        this.fontDescriptor.setNonSymbolic(true);
        cOSDictionary.setItem(COSName.FONT_DESC, this.fontDescriptor);
        setWidths(cOSDictionary, adobeGlyphList);
    }

    private void setWidths(COSDictionary cOSDictionary, GlyphList glyphList) throws IOException {
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        HorizontalMetricsTable horizontalMetrics = this.ttf.getHorizontalMetrics();
        Map<Integer, String> codeToNameMap = getFontEncoding().getCodeToNameMap();
        int iIntValue = ((Integer) Collections.min(codeToNameMap.keySet())).intValue();
        int iIntValue2 = ((Integer) Collections.max(codeToNameMap.keySet())).intValue();
        int i = (iIntValue2 - iIntValue) + 1;
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(0);
        }
        for (Map.Entry<Integer, String> entry : codeToNameMap.entrySet()) {
            int iIntValue3 = entry.getKey().intValue();
            String value = entry.getValue();
            if (iIntValue3 >= iIntValue && iIntValue3 <= iIntValue2) {
                arrayList.set(entry.getKey().intValue() - iIntValue, Integer.valueOf(Math.round(horizontalMetrics.getAdvanceWidth(this.cmap.getGlyphId(glyphList.toUnicode(value).codePointAt(0))) * unitsPerEm)));
            }
        }
        cOSDictionary.setInt(COSName.FIRST_CHAR, iIntValue);
        cOSDictionary.setInt(COSName.LAST_CHAR, iIntValue2);
        cOSDictionary.setItem(COSName.WIDTHS, (COSBase) COSArrayList.converterToCOSArray(arrayList));
    }

    public Encoding getFontEncoding() {
        return this.fontEncoding;
    }

    @Override
    protected void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException {
        throw new UnsupportedOperationException();
    }
}
