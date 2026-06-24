package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.ttf.CmapSubtable;
import com.tom_roush.fontbox.ttf.CmapTable;
import com.tom_roush.fontbox.ttf.GlyphData;
import com.tom_roush.fontbox.ttf.PostScriptTable;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.font.encoding.BuiltInEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.MacOSRomanEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PDTrueTypeFont extends PDSimpleFont implements PDVectorFont {
    private static final Map<String, Integer> INVERTED_MACOS_ROMAN = new HashMap(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private static final int START_RANGE_F000 = 61440;
    private static final int START_RANGE_F100 = 61696;
    private static final int START_RANGE_F200 = 61952;
    private boolean cmapInitialized;
    private CmapSubtable cmapMacRoman;
    private CmapSubtable cmapWinSymbol;
    private CmapSubtable cmapWinUnicode;
    private BoundingBox fontBBox;
    private Map<Integer, Integer> gidToCode;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final TrueTypeFont ttf;

    static {
        for (Map.Entry<Integer, String> entry : MacOSRomanEncoding.INSTANCE.getCodeToNameMap().entrySet()) {
            Map<String, Integer> map = INVERTED_MACOS_ROMAN;
            if (!map.containsKey(entry.getValue())) {
                map.put(entry.getValue(), entry.getKey());
            }
        }
    }

    public static PDTrueTypeFont load(PDDocument pDDocument, File file, Encoding encoding) throws IOException {
        return new PDTrueTypeFont(pDDocument, new FileInputStream(file), encoding);
    }

    public static PDTrueTypeFont load(PDDocument pDDocument, InputStream inputStream, Encoding encoding) throws IOException {
        return new PDTrueTypeFont(pDDocument, inputStream, encoding);
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, File file) throws IOException {
        return new PDTrueTypeFont(pDDocument, new FileInputStream(file), WinAnsiEncoding.INSTANCE);
    }

    @Deprecated
    public static PDTrueTypeFont loadTTF(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return new PDTrueTypeFont(pDDocument, inputStream, WinAnsiEncoding.INSTANCE);
    }

    public PDTrueTypeFont(COSDictionary cOSDictionary) throws IOException {
        boolean z;
        PDStream fontFile2;
        super(cOSDictionary);
        TrueTypeFont trueTypeFont = null;
        this.cmapWinUnicode = null;
        this.cmapWinSymbol = null;
        this.cmapMacRoman = null;
        this.cmapInitialized = false;
        if (getFontDescriptor() == null || (fontFile2 = super.getFontDescriptor().getFontFile2()) == null) {
            z = false;
        } else {
            try {
                trueTypeFont = new TTFParser(true).parse(fontFile2.createInputStream());
                z = false;
            } catch (IOException e) {
                Log.w("PdfBox-Android", "Could not read embedded TTF for font " + getBaseFont(), e);
                z = true;
            } catch (NullPointerException e2) {
                Log.w("PdfBox-Android", "Could not read embedded TTF for font " + getBaseFont(), e2);
                z = true;
            }
        }
        this.isEmbedded = trueTypeFont != null;
        this.isDamaged = z;
        if (trueTypeFont == null) {
            FontMapping<TrueTypeFont> trueTypeFont2 = FontMappers.instance().getTrueTypeFont(getBaseFont(), getFontDescriptor());
            TrueTypeFont trueTypeFont3 = (TrueTypeFont) trueTypeFont2.getFont();
            if (trueTypeFont2.isFallback()) {
                Log.w("PdfBox-Android", "Using fallback font '" + trueTypeFont3 + "' for '" + getBaseFont() + "'");
            }
            trueTypeFont = trueTypeFont3;
        }
        this.ttf = trueTypeFont;
        readEncoding();
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override
    protected Encoding readEncodingFromFont() throws IOException {
        if (getStandard14AFM() != null) {
            return new Type1Encoding(getStandard14AFM());
        }
        if (getSymbolicFlag() != null && !getSymbolicFlag().booleanValue()) {
            return StandardEncoding.INSTANCE;
        }
        String mappedFontName = Standard14Fonts.getMappedFontName(getName());
        if (isStandard14() && !mappedFontName.equals("Symbol") && !mappedFontName.equals("ZapfDingbats")) {
            return StandardEncoding.INSTANCE;
        }
        PostScriptTable postScript = this.ttf.getPostScript();
        HashMap map = new HashMap();
        for (int i = 0; i <= 256; i++) {
            int iCodeToGID = codeToGID(i);
            if (iCodeToGID > 0) {
                String name = postScript != null ? postScript.getName(iCodeToGID) : null;
                if (name == null) {
                    name = Integer.toString(iCodeToGID);
                }
                map.put(Integer.valueOf(i), name);
            }
        }
        return new BuiltInEncoding(map);
    }

    private PDTrueTypeFont(PDDocument pDDocument, InputStream inputStream, Encoding encoding) throws IOException {
        this.cmapWinUnicode = null;
        this.cmapWinSymbol = null;
        this.cmapMacRoman = null;
        this.cmapInitialized = false;
        PDTrueTypeFontEmbedder pDTrueTypeFontEmbedder = new PDTrueTypeFontEmbedder(pDDocument, this.dict, inputStream, encoding);
        this.encoding = encoding;
        this.ttf = pDTrueTypeFontEmbedder.getTrueTypeFont();
        setFontDescriptor(pDTrueTypeFontEmbedder.getFontDescriptor());
        this.isEmbedded = true;
        this.isDamaged = false;
        this.glyphList = GlyphList.getAdobeGlyphList();
    }

    @Override
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override
    public String getName() {
        return getBaseFont();
    }

    @Override
    public BoundingBox getBoundingBox() throws IOException {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() throws IOException {
        if (getFontDescriptor() != null) {
            PDRectangle fontBoundingBox = getFontDescriptor().getFontBoundingBox();
            return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
        }
        return this.ttf.getFontBBox();
    }

    @Override
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        float advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        float unitsPerEm = this.ttf.getUnitsPerEm();
        return unitsPerEm != 1000.0f ? advanceWidth * (1000.0f / unitsPerEm) : advanceWidth;
    }

    @Override
    public float getHeight(int i) throws IOException {
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph != null) {
            return glyph.getBoundingBox().getHeight();
        }
        return 0.0f;
    }

    @Override
    protected byte[] encode(int i) throws IOException {
        if (this.encoding != null) {
            if (!this.encoding.contains(getGlyphList().codePointToName(i))) {
                throw new IllegalArgumentException(String.format("U+%04X is not available in this font's encoding: %s", Integer.valueOf(i), this.encoding.getEncodingName()));
            }
            String strCodePointToName = getGlyphList().codePointToName(i);
            Map<String, Integer> nameToCodeMap = this.encoding.getNameToCodeMap();
            if (this.ttf.hasGlyph(strCodePointToName) || this.ttf.hasGlyph(UniUtil.getUniNameOfCodePoint(i))) {
                return new byte[]{(byte) nameToCodeMap.get(strCodePointToName).intValue()};
            }
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        String strCodePointToName2 = getGlyphList().codePointToName(i);
        if (!this.ttf.hasGlyph(strCodePointToName2)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        Integer num = getGIDToCode().get(Integer.valueOf(this.ttf.nameToGID(strCodePointToName2)));
        if (num != null) {
            return new byte[]{(byte) num.intValue()};
        }
        throw new IllegalArgumentException(String.format("U+%04X is not available in this font's Encoding", Integer.valueOf(i)));
    }

    protected Map<Integer, Integer> getGIDToCode() throws IOException {
        Map<Integer, Integer> map = this.gidToCode;
        if (map != null) {
            return map;
        }
        this.gidToCode = new HashMap();
        for (int i = 0; i <= 255; i++) {
            int iCodeToGID = codeToGID(i);
            if (!this.gidToCode.containsKey(Integer.valueOf(iCodeToGID))) {
                this.gidToCode.put(Integer.valueOf(iCodeToGID), Integer.valueOf(i));
            }
        }
        return this.gidToCode;
    }

    @Override
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override
    public Path getPath(int i) throws IOException {
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph == null) {
            return new Path();
        }
        return glyph.getPath();
    }

    @Override
    public Path getPath(String str) throws IOException {
        int iNameToGID = this.ttf.nameToGID(str);
        if (iNameToGID == 0) {
            iNameToGID = 0;
            try {
                int i = Integer.parseInt(str);
                if (i <= this.ttf.getNumberOfGlyphs()) {
                    iNameToGID = i;
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (iNameToGID == 0) {
            return new Path();
        }
        GlyphData glyph = this.ttf.getGlyph().getGlyph(iNameToGID);
        if (glyph != null) {
            return glyph.getPath();
        }
        return new Path();
    }

    @Override
    public boolean hasGlyph(String str) throws IOException {
        return this.ttf.nameToGID(str) != 0;
    }

    @Override
    public FontBoxFont getFontBoxFont() {
        return this.ttf;
    }

    @Override
    public boolean hasGlyph(int i) throws IOException {
        return codeToGID(i) != 0;
    }

    public int codeToGID(int i) throws IOException {
        CmapSubtable cmapSubtable;
        Integer num;
        String unicode;
        extractCmapTable();
        int glyphId = 0;
        if (!isSymbolic()) {
            String name = this.encoding.getName(i);
            if (name.equals(".notdef")) {
                return 0;
            }
            if (this.cmapWinUnicode != null && (unicode = GlyphList.getAdobeGlyphList().toUnicode(name)) != null) {
                glyphId = this.cmapWinUnicode.getGlyphId(unicode.codePointAt(0));
            }
            if (glyphId == 0 && this.cmapMacRoman != null && (num = INVERTED_MACOS_ROMAN.get(name)) != null) {
                glyphId = this.cmapMacRoman.getGlyphId(num.intValue());
            }
            return glyphId == 0 ? this.ttf.nameToGID(name) : glyphId;
        }
        CmapSubtable cmapSubtable2 = this.cmapWinSymbol;
        if (cmapSubtable2 != null) {
            glyphId = cmapSubtable2.getGlyphId(i);
            if (i >= 0 && i <= 255) {
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F000 + i);
                }
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F100 + i);
                }
                if (glyphId == 0) {
                    glyphId = this.cmapWinSymbol.getGlyphId(START_RANGE_F200 + i);
                }
            }
        }
        return (glyphId != 0 || (cmapSubtable = this.cmapMacRoman) == null) ? glyphId : cmapSubtable.getGlyphId(i);
    }

    private void extractCmapTable() throws IOException {
        if (this.cmapInitialized) {
            return;
        }
        CmapTable cmap = this.ttf.getCmap();
        if (cmap != null) {
            for (CmapSubtable cmapSubtable : cmap.getCmaps()) {
                if (3 == cmapSubtable.getPlatformId()) {
                    if (1 == cmapSubtable.getPlatformEncodingId()) {
                        this.cmapWinUnicode = cmapSubtable;
                    } else if (cmapSubtable.getPlatformEncodingId() == 0) {
                        this.cmapWinSymbol = cmapSubtable;
                    }
                } else if (1 == cmapSubtable.getPlatformId() && cmapSubtable.getPlatformEncodingId() == 0) {
                    this.cmapMacRoman = cmapSubtable;
                }
            }
        }
        this.cmapInitialized = true;
    }
}
