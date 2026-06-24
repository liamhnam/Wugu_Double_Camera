package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.ttf.CmapSubtable;
import com.tom_roush.fontbox.ttf.GlyphData;
import com.tom_roush.fontbox.ttf.OTFParser;
import com.tom_roush.fontbox.ttf.OpenTypeFont;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.UByte;

public class PDCIDFontType2 extends PDCIDFont {
    private final int[] cid2gid;
    private final CmapSubtable cmap;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final TrueTypeFont ttf;

    public PDCIDFontType2(COSDictionary cOSDictionary, PDType0Font pDType0Font) throws IOException {
        this(cOSDictionary, pDType0Font, null);
    }

    public PDCIDFontType2(COSDictionary cOSDictionary, PDType0Font pDType0Font, TrueTypeFont trueTypeFont) throws IOException {
        boolean z;
        OpenTypeFont font;
        super(cOSDictionary, pDType0Font);
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (trueTypeFont != null) {
            this.ttf = trueTypeFont;
            this.isEmbedded = true;
            this.isDamaged = false;
        } else {
            PDStream fontFile2 = fontDescriptor.getFontFile2();
            PDStream fontFile3 = fontDescriptor.getFontFile3();
            if (fontFile2 == null && fontFile3 == null) {
                fontFile2 = fontDescriptor.getFontFile();
            }
            TrueTypeFont trueTypeFont2 = null;
            TrueTypeFont trueTypeFont3 = null;
            if (fontFile2 != null) {
                try {
                    trueTypeFont2 = new TTFParser(true).parse(fontFile2.createInputStream());
                    z = false;
                    trueTypeFont3 = trueTypeFont2;
                } catch (IOException e) {
                    Log.w("PdfBox-Android", "Could not read embedded TTF for font " + getBaseFont(), e);
                    z = true;
                } catch (NullPointerException e2) {
                    Log.w("PdfBox-Android", "Could not read embedded TTF for font " + getBaseFont(), e2);
                    z = true;
                }
            } else {
                if (fontFile3 != null) {
                    try {
                        OpenTypeFont openTypeFont = new OTFParser(true).parse((InputStream) fontFile3.createInputStream());
                        if (openTypeFont.isPostScript()) {
                            throw new IOException("Not implemented: OpenType font with CFF table " + getBaseFont());
                        }
                        boolean zHasLayoutTables = openTypeFont.hasLayoutTables();
                        trueTypeFont2 = openTypeFont;
                        if (zHasLayoutTables) {
                            Log.e("PdfBox-Android", "OpenType Layout tables used in font " + getBaseFont() + " are not implemented in PDFBox and will be ignored");
                            trueTypeFont2 = openTypeFont;
                        }
                    } catch (IOException e3) {
                        Log.w("PdfBox-Android", "Could not read embedded OTF for font " + getBaseFont(), e3);
                        z = true;
                    } catch (NullPointerException e4) {
                        Log.w("PdfBox-Android", "Could not read embedded OTF for font " + getBaseFont(), e4);
                        z = true;
                    }
                }
                z = false;
                trueTypeFont3 = trueTypeFont2;
            }
            this.isEmbedded = trueTypeFont3 != null;
            this.isDamaged = z;
            TrueTypeFont trueTypeFont4 = trueTypeFont3;
            if (trueTypeFont3 == null) {
                CIDFontMapping cIDFont = FontMappers.instance().getCIDFont(getBaseFont(), getFontDescriptor(), getCIDSystemInfo());
                if (cIDFont.isCIDFont()) {
                    font = cIDFont.getFont();
                } else {
                    font = (TrueTypeFont) cIDFont.getTrueTypeFont();
                }
                TrueTypeFont trueTypeFont5 = font;
                trueTypeFont4 = trueTypeFont5;
                if (cIDFont.isFallback()) {
                    Log.w("PdfBox-Android", "Using fallback font " + trueTypeFont5.getName() + " for CID-keyed TrueType font " + getBaseFont());
                    trueTypeFont4 = trueTypeFont5;
                }
            }
            this.ttf = trueTypeFont4;
        }
        this.cmap = this.ttf.getUnicodeCmap(false);
        this.cid2gid = readCIDToGIDMap();
    }

    @Override
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
        }
        return this.fontMatrix;
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
            if (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f) {
                return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
            }
        }
        return this.ttf.getFontBBox();
    }

    private int[] readCIDToGIDMap() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.CID_TO_GID_MAP);
        if (!(dictionaryObject instanceof COSStream)) {
            return null;
        }
        COSInputStream cOSInputStreamCreateInputStream = ((COSStream) dictionaryObject).createInputStream();
        byte[] byteArray = IOUtils.toByteArray(cOSInputStreamCreateInputStream);
        IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
        int length = byteArray.length / 2;
        int[] iArr = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = ((byteArray[i] & UByte.MAX_VALUE) << 8) | (byteArray[i + 1] & UByte.MAX_VALUE);
            i += 2;
        }
        return iArr;
    }

    private Map<Integer, Integer> invert(int[] iArr) {
        if (iArr == null) {
            return null;
        }
        HashMap map = new HashMap(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            map.put(Integer.valueOf(iArr[i]), Integer.valueOf(i));
        }
        return map;
    }

    @Override
    public int codeToCID(int i) {
        CMap cMap = this.parent.getCMap();
        if (!cMap.hasCIDMappings() && cMap.hasUnicodeMappings()) {
            return cMap.toUnicode(i).codePointAt(0);
        }
        return cMap.toCID(i);
    }

    @Override
    public int codeToGID(int i) throws IOException {
        if (!this.isEmbedded) {
            if (this.cid2gid != null && !this.isDamaged) {
                Log.w("PdfBox-Android", "Using non-embedded GIDs in font " + getName());
                return this.cid2gid[codeToCID(i)];
            }
            String unicode = this.parent.toUnicode(i);
            if (unicode == null) {
                Log.w("PdfBox-Android", "Failed to find a character mapping for " + i + " in " + getName());
                return codeToCID(i);
            }
            if (unicode.length() > 1) {
                Log.w("PdfBox-Android", "Trying to map multi-byte character using 'cmap', result will be poor");
            }
            return this.cmap.getGlyphId(unicode.codePointAt(0));
        }
        int iCodeToCID = codeToCID(i);
        int[] iArr = this.cid2gid;
        if (iArr != null) {
            if (iCodeToCID < iArr.length) {
                return iArr[iCodeToCID];
            }
            return 0;
        }
        if (iCodeToCID < this.ttf.getNumberOfGlyphs()) {
            return iCodeToCID;
        }
        return 0;
    }

    @Override
    public float getHeight(int i) throws IOException {
        return (this.ttf.getHorizontalHeader().getAscender() + (-this.ttf.getHorizontalHeader().getDescender())) / this.ttf.getUnitsPerEm();
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        int advanceWidth = this.ttf.getAdvanceWidth(codeToGID(i));
        int unitsPerEm = this.ttf.getUnitsPerEm();
        if (unitsPerEm != 1000) {
            advanceWidth = (int) (advanceWidth * (1000.0f / unitsPerEm));
        }
        return advanceWidth;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public byte[] encode(int r4) {
        /*
            r3 = this;
            boolean r0 = r3.isEmbedded
            r1 = 0
            if (r0 == 0) goto L39
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r3.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMap()
            java.lang.String r0 = r0.getName()
            java.lang.String r2 = "Identity-"
            boolean r0 = r0.startsWith(r2)
            r2 = -1
            if (r0 == 0) goto L21
            com.tom_roush.fontbox.ttf.CmapSubtable r0 = r3.cmap
            if (r0 == 0) goto L34
            int r0 = r0.getGlyphId(r4)
            goto L35
        L21:
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r3.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMapUCS2()
            if (r0 == 0) goto L34
            com.tom_roush.pdfbox.pdmodel.font.PDType0Font r0 = r3.parent
            com.tom_roush.fontbox.cmap.CMap r0 = r0.getCMapUCS2()
            int r0 = r0.toCID(r4)
            goto L35
        L34:
            r0 = r2
        L35:
            if (r0 != r2) goto L3f
            r0 = r1
            goto L3f
        L39:
            com.tom_roush.fontbox.ttf.CmapSubtable r0 = r3.cmap
            int r0 = r0.getGlyphId(r4)
        L3f:
            if (r0 == 0) goto L52
            r4 = 2
            byte[] r4 = new byte[r4]
            int r2 = r0 >> 8
            r2 = r2 & 255(0xff, float:3.57E-43)
            byte r2 = (byte) r2
            r4[r1] = r2
            r0 = r0 & 255(0xff, float:3.57E-43)
            byte r0 = (byte) r0
            r1 = 1
            r4[r1] = r0
            return r4
        L52:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.String r1 = r3.getName()
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r1}
            java.lang.String r1 = "No glyph for U+%04X in font %s"
            java.lang.String r4 = java.lang.String.format(r1, r4)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2.encode(int):byte[]");
    }

    @Override
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override
    public boolean isDamaged() {
        return this.isDamaged;
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    @Override
    public Path getPath(int i) throws IOException {
        TrueTypeFont trueTypeFont = this.ttf;
        if ((trueTypeFont instanceof OpenTypeFont) && ((OpenTypeFont) trueTypeFont).isPostScript()) {
            return ((OpenTypeFont) this.ttf).getCFF().getFont().getType2CharString(codeToCID(i)).getPath();
        }
        GlyphData glyph = this.ttf.getGlyph().getGlyph(codeToGID(i));
        if (glyph != null) {
            return glyph.getPath();
        }
        return new Path();
    }

    @Override
    public boolean hasGlyph(int i) throws IOException {
        return codeToGID(i) != 0;
    }
}
