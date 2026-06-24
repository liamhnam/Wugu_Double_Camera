package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.cff.CFFParser;
import com.tom_roush.fontbox.cff.CFFType1Font;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PDType1CFont extends PDSimpleFont {
    private Float avgWidth;
    private final CFFType1Font cffFont;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final FontBoxFont genericFont;
    private final Map<String, Float> glyphHeights;
    private final boolean isDamaged;
    private final boolean isEmbedded;

    private float getAverageCharacterWidth() {
        return 500.0f;
    }

    public PDType1CFont(COSDictionary cOSDictionary) throws IOException {
        byte[] byteArray;
        boolean z;
        PDStream fontFile3;
        super(cOSDictionary);
        this.glyphHeights = new HashMap();
        C18861 c18861 = 0;
        CFFType1Font cFFType1Font = 0;
        this.avgWidth = null;
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor == null || (fontFile3 = fontDescriptor.getFontFile3()) == null) {
            byteArray = null;
        } else {
            byteArray = IOUtils.toByteArray(fontFile3.createInputStream());
            if (byteArray.length == 0) {
                Log.e("PdfBox-Android", "Invalid data for embedded Type1C font " + getName());
                byteArray = null;
            }
        }
        if (byteArray != null) {
            try {
                c18861 = (CFFType1Font) new CFFParser().parse(byteArray, new ByteSource()).get(0);
                z = false;
                cFFType1Font = c18861;
            } catch (IOException e) {
                Log.e("PdfBox-Android", "Can't read the embedded Type1C font " + getName(), e);
                z = true;
            }
        } else {
            z = false;
            cFFType1Font = c18861;
        }
        this.isDamaged = z;
        this.cffFont = cFFType1Font;
        if (cFFType1Font != 0) {
            this.genericFont = cFFType1Font;
            this.isEmbedded = true;
        } else {
            FontMapping<FontBoxFont> fontBoxFont = FontMappers.instance().getFontBoxFont(getBaseFont(), fontDescriptor);
            FontBoxFont font = fontBoxFont.getFont();
            this.genericFont = font;
            if (fontBoxFont.isFallback()) {
                Log.w("PdfBox-Android", "Using fallback font " + font.getName() + " for " + getBaseFont());
            }
            this.isEmbedded = false;
        }
        readEncoding();
        AffineTransform affineTransformCreateAffineTransform = getFontMatrix().createAffineTransform();
        this.fontMatrixTransform = affineTransformCreateAffineTransform;
        affineTransformCreateAffineTransform.scale(1000.0d, 1000.0d);
    }

    private class ByteSource implements CFFParser.ByteSource {
        private ByteSource() {
        }

        @Override
        public byte[] getBytes() throws IOException {
            return IOUtils.toByteArray(PDType1CFont.this.getFontDescriptor().getFontFile3().createInputStream());
        }
    }

    @Override
    public FontBoxFont getFontBoxFont() {
        return this.genericFont;
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override
    public Path getPath(String str) throws IOException {
        if (str.equals(".notdef") && !isEmbedded() && !isStandard14()) {
            return new Path();
        }
        return this.genericFont.getPath(str);
    }

    @Override
    public boolean hasGlyph(String str) throws IOException {
        return this.genericFont.hasGlyph(str);
    }

    @Override
    public final String getName() {
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
            if (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f) {
                return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
            }
        }
        return this.genericFont.getFontBBox();
    }

    public String codeToName(int i) {
        return getEncoding().getName(i);
    }

    @Override
    protected Encoding readEncodingFromFont() throws IOException {
        if (getStandard14AFM() != null) {
            return new Type1Encoding(getStandard14AFM());
        }
        FontBoxFont fontBoxFont = this.genericFont;
        if (fontBoxFont instanceof EncodedFont) {
            return Type1Encoding.fromFontBox(((EncodedFont) fontBoxFont).getEncoding());
        }
        return StandardEncoding.INSTANCE;
    }

    @Override
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override
    public final Matrix getFontMatrix() {
        List<Number> fontMatrix;
        if (this.fontMatrix == null) {
            try {
                fontMatrix = this.genericFont.getFontMatrix();
            } catch (IOException unused) {
                this.fontMatrix = DEFAULT_FONT_MATRIX;
                fontMatrix = null;
            }
            if (fontMatrix != null && fontMatrix.size() == 6) {
                this.fontMatrix = new Matrix(fontMatrix.get(0).floatValue(), fontMatrix.get(1).floatValue(), fontMatrix.get(2).floatValue(), fontMatrix.get(3).floatValue(), fontMatrix.get(4).floatValue(), fontMatrix.get(5).floatValue());
            } else {
                return super.getFontMatrix();
            }
        }
        return this.fontMatrix;
    }

    @Override
    public boolean isDamaged() {
        return this.isDamaged;
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        PointF pointF = new PointF(this.genericFont.getWidth(codeToName(i)), 0.0f);
        this.fontMatrixTransform.transform(pointF, pointF);
        return pointF.x;
    }

    @Override
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override
    public float getHeight(int i) throws IOException {
        String strCodeToName = codeToName(i);
        if (this.glyphHeights.containsKey(strCodeToName)) {
            return 0.0f;
        }
        float fHeight = this.cffFont.getType1CharString(strCodeToName).getBounds().height();
        this.glyphHeights.put(strCodeToName, Float.valueOf(fHeight));
        return fHeight;
    }

    @Override
    protected byte[] encode(int i) throws IOException {
        String strCodePointToName = getGlyphList().codePointToName(i);
        if (!this.encoding.contains(strCodePointToName)) {
            throw new IllegalArgumentException(String.format("U+%04X ('%s') is not available in this font's encoding: %s", Integer.valueOf(i), strCodePointToName, this.encoding.getEncodingName()));
        }
        String nameInFont = getNameInFont(strCodePointToName);
        Map<String, Integer> nameToCodeMap = this.encoding.getNameToCodeMap();
        if (nameInFont.equals(".notdef") || !this.genericFont.hasGlyph(nameInFont)) {
            throw new IllegalArgumentException(String.format("No glyph for U+%04X in font %s", Integer.valueOf(i), getName()));
        }
        return new byte[]{(byte) nameToCodeMap.get(strCodePointToName).intValue()};
    }

    @Override
    public float getStringWidth(String str) throws IOException {
        float width = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            width += this.cffFont.getType1CharString(getGlyphList().codePointToName(str.codePointAt(i))).getWidth();
        }
        return width;
    }

    @Override
    public float getAverageFontWidth() {
        if (this.avgWidth == null) {
            this.avgWidth = Float.valueOf(getAverageCharacterWidth());
        }
        return this.avgWidth.floatValue();
    }

    public CFFType1Font getCFFType1Font() {
        return this.cffFont;
    }

    private String getNameInFont(String str) throws IOException {
        if (isEmbedded() || this.genericFont.hasGlyph(str)) {
            return str;
        }
        String unicode = getGlyphList().toUnicode(str);
        if (unicode != null && unicode.length() == 1) {
            String uniNameOfCodePoint = UniUtil.getUniNameOfCodePoint(unicode.codePointAt(0));
            if (this.genericFont.hasGlyph(uniNameOfCodePoint)) {
                return uniNameOfCodePoint;
            }
        }
        return ".notdef";
    }
}
