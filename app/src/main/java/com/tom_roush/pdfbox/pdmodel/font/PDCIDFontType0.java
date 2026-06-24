package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.PointF;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFCIDFont;
import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.cff.CFFParser;
import com.tom_roush.fontbox.cff.CFFType1Font;
import com.tom_roush.fontbox.cff.Type2CharString;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PDCIDFontType0 extends PDCIDFont {
    private Float avgWidth;
    private final CFFCIDFont cidFont;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final Map<Integer, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final FontBoxFont t1Font;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PDCIDFontType0(com.tom_roush.pdfbox.cos.COSDictionary r7, com.tom_roush.pdfbox.pdmodel.font.PDType0Font r8) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType0.<init>(com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.pdmodel.font.PDType0Font):void");
    }

    @Override
    public final Matrix getFontMatrix() {
        List<Number> fontMatrix;
        if (this.fontMatrix == null) {
            CFFCIDFont cFFCIDFont = this.cidFont;
            if (cFFCIDFont != null) {
                fontMatrix = cFFCIDFont.getFontMatrix();
            } else {
                try {
                    fontMatrix = this.t1Font.getFontMatrix();
                } catch (IOException unused) {
                    return new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
                }
            }
            if (fontMatrix != null && fontMatrix.size() == 6) {
                this.fontMatrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
            } else {
                this.fontMatrix = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
            }
        }
        return this.fontMatrix;
    }

    private class ByteSource implements CFFParser.ByteSource {
        private ByteSource() {
        }

        @Override
        public byte[] getBytes() throws IOException {
            return IOUtils.toByteArray(PDCIDFontType0.this.getFontDescriptor().getFontFile3().createInputStream());
        }
    }

    @Override
    public BoundingBox getBoundingBox() {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() {
        if (getFontDescriptor() != null) {
            PDRectangle fontBoundingBox = getFontDescriptor().getFontBoundingBox();
            if (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f) {
                return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
            }
        }
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getFontBBox();
        }
        try {
            return this.t1Font.getFontBBox();
        } catch (IOException unused) {
            return new BoundingBox();
        }
    }

    public CFFFont getCFFFont() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont;
        }
        FontBoxFont fontBoxFont = this.t1Font;
        if (fontBoxFont instanceof CFFType1Font) {
            return (CFFType1Font) fontBoxFont;
        }
        return null;
    }

    public FontBoxFont getFontBoxFont() {
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont : this.t1Font;
    }

    public Type2CharString getType2CharString(int i) throws IOException {
        CFFCIDFont cFFCIDFont = this.cidFont;
        if (cFFCIDFont != null) {
            return cFFCIDFont.getType2CharString(i);
        }
        FontBoxFont fontBoxFont = this.t1Font;
        if (fontBoxFont instanceof CFFType1Font) {
            return ((CFFType1Font) fontBoxFont).getType2CharString(i);
        }
        return null;
    }

    private String getGlyphName(int i) throws IOException {
        String unicode = this.parent.toUnicode(i);
        return unicode == null ? ".notdef" : UniUtil.getUniNameOfCodePoint(unicode.codePointAt(0));
    }

    @Override
    public Path getPath(int i) throws IOException {
        int iCodeToCID = codeToCID(i);
        Type2CharString type2CharString = getType2CharString(iCodeToCID);
        if (type2CharString != null) {
            return type2CharString.getPath();
        }
        if (this.isEmbedded) {
            FontBoxFont fontBoxFont = this.t1Font;
            if (fontBoxFont instanceof CFFType1Font) {
                return ((CFFType1Font) fontBoxFont).getType2CharString(iCodeToCID).getPath();
            }
        }
        return this.t1Font.getPath(getGlyphName(i));
    }

    @Override
    public boolean hasGlyph(int i) throws IOException {
        int iCodeToCID = codeToCID(i);
        Type2CharString type2CharString = getType2CharString(iCodeToCID);
        if (type2CharString != null) {
            return type2CharString.getGID() != 0;
        }
        if (this.isEmbedded) {
            FontBoxFont fontBoxFont = this.t1Font;
            if (fontBoxFont instanceof CFFType1Font) {
                return ((CFFType1Font) fontBoxFont).getType2CharString(iCodeToCID).getGID() != 0;
            }
        }
        return this.t1Font.hasGlyph(getGlyphName(i));
    }

    @Override
    public int codeToCID(int i) {
        return this.parent.getCMap().toCID(i);
    }

    @Override
    public int codeToGID(int i) {
        int iCodeToCID = codeToCID(i);
        CFFCIDFont cFFCIDFont = this.cidFont;
        return cFFCIDFont != null ? cFFCIDFont.getCharset().getGIDForCID(iCodeToCID) : iCodeToCID;
    }

    @Override
    public byte[] encode(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        float width;
        int width2;
        int iCodeToCID = codeToCID(i);
        if (this.cidFont != null) {
            width2 = getType2CharString(iCodeToCID).getWidth();
        } else {
            if (this.isEmbedded) {
                FontBoxFont fontBoxFont = this.t1Font;
                if (fontBoxFont instanceof CFFType1Font) {
                    width2 = ((CFFType1Font) fontBoxFont).getType2CharString(iCodeToCID).getWidth();
                }
            }
            width = this.t1Font.getWidth(getGlyphName(i));
            PointF pointF = new PointF(width, 0.0f);
            this.fontMatrixTransform.transform(pointF, pointF);
            return pointF.x;
        }
        width = width2;
        PointF pointF2 = new PointF(width, 0.0f);
        this.fontMatrixTransform.transform(pointF2, pointF2);
        return pointF2.x;
    }

    @Override
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override
    public float getHeight(int i) throws IOException {
        int iCodeToCID = codeToCID(i);
        if (this.glyphHeights.containsKey(Integer.valueOf(iCodeToCID))) {
            return 0.0f;
        }
        float fHeight = getType2CharString(iCodeToCID).getBounds().height();
        this.glyphHeights.put(Integer.valueOf(iCodeToCID), Float.valueOf(fHeight));
        return fHeight;
    }

    @Override
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }
}
