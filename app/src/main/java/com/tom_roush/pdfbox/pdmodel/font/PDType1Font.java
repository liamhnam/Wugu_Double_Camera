package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.type1.Type1Font;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Type1Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.spi.LocationInfo;

public class PDType1Font extends PDSimpleFont {
    private static final Map<String, String> ALT_NAMES;
    public static final PDType1Font COURIER;
    public static final PDType1Font COURIER_BOLD;
    public static final PDType1Font COURIER_BOLD_OBLIQUE;
    public static final PDType1Font COURIER_OBLIQUE;
    public static final PDType1Font HELVETICA;
    public static final PDType1Font HELVETICA_BOLD;
    public static final PDType1Font HELVETICA_BOLD_OBLIQUE;
    public static final PDType1Font HELVETICA_OBLIQUE;
    private static final int PFB_START_MARKER = 128;
    public static final PDType1Font SYMBOL;
    public static final PDType1Font TIMES_BOLD;
    public static final PDType1Font TIMES_BOLD_ITALIC;
    public static final PDType1Font TIMES_ITALIC;
    public static final PDType1Font TIMES_ROMAN;
    public static final PDType1Font ZAPF_DINGBATS;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private final AffineTransform fontMatrixTransform;
    private final FontBoxFont genericFont;
    private final boolean isDamaged;
    private final boolean isEmbedded;
    private final Type1Font type1font;

    static {
        HashMap map = new HashMap();
        ALT_NAMES = map;
        map.put("ff", "f_f");
        map.put("ffi", "f_f_i");
        map.put("ffl", "f_f_l");
        map.put("fi", "f_i");
        map.put("fl", "f_l");
        map.put("st", "s_t");
        map.put("IJ", "I_J");
        map.put("ij", "i_j");
        map.put("ellipsis", "elipsis");
        TIMES_ROMAN = new PDType1Font("Times-Roman");
        TIMES_BOLD = new PDType1Font("Times-Bold");
        TIMES_ITALIC = new PDType1Font("Times-Italic");
        TIMES_BOLD_ITALIC = new PDType1Font("Times-BoldItalic");
        HELVETICA = new PDType1Font("Helvetica");
        HELVETICA_BOLD = new PDType1Font("Helvetica-Bold");
        HELVETICA_OBLIQUE = new PDType1Font("Helvetica-Oblique");
        HELVETICA_BOLD_OBLIQUE = new PDType1Font("Helvetica-BoldOblique");
        COURIER = new PDType1Font("Courier");
        COURIER_BOLD = new PDType1Font("Courier-Bold");
        COURIER_OBLIQUE = new PDType1Font("Courier-Oblique");
        COURIER_BOLD_OBLIQUE = new PDType1Font("Courier-BoldOblique");
        SYMBOL = new PDType1Font("Symbol");
        ZAPF_DINGBATS = new PDType1Font("ZapfDingbats");
    }

    private PDType1Font(String str) {
        String name;
        super(str);
        this.dict.setItem(COSName.SUBTYPE, (COSBase) COSName.TYPE1);
        this.dict.setName(COSName.BASE_FONT, str);
        this.encoding = new WinAnsiEncoding();
        this.dict.setItem(COSName.ENCODING, (COSBase) COSName.WIN_ANSI_ENCODING);
        this.type1font = null;
        FontMapping<FontBoxFont> fontBoxFont = FontMappers.instance().getFontBoxFont(getBaseFont(), getFontDescriptor());
        FontBoxFont font = fontBoxFont.getFont();
        this.genericFont = font;
        if (fontBoxFont.isFallback()) {
            try {
                name = font.getName();
            } catch (IOException unused) {
                name = LocationInfo.f2800NA;
            }
            Log.w("PdfBox-Android", "Using fallback font " + name + " for base font " + getBaseFont());
        }
        this.isEmbedded = false;
        this.isDamaged = false;
        this.fontMatrixTransform = new AffineTransform();
    }

    public PDType1Font(PDDocument pDDocument, InputStream inputStream) throws IOException {
        PDType1FontEmbedder pDType1FontEmbedder = new PDType1FontEmbedder(pDDocument, this.dict, inputStream, null);
        this.encoding = pDType1FontEmbedder.getFontEncoding();
        this.glyphList = pDType1FontEmbedder.getGlyphList();
        this.type1font = pDType1FontEmbedder.getType1Font();
        this.genericFont = pDType1FontEmbedder.getType1Font();
        this.isEmbedded = true;
        this.isDamaged = false;
        this.fontMatrixTransform = new AffineTransform();
    }

    public PDType1Font(PDDocument pDDocument, InputStream inputStream, Encoding encoding) throws IOException {
        PDType1FontEmbedder pDType1FontEmbedder = new PDType1FontEmbedder(pDDocument, this.dict, inputStream, encoding);
        this.encoding = encoding;
        this.glyphList = pDType1FontEmbedder.getGlyphList();
        this.type1font = pDType1FontEmbedder.getType1Font();
        this.genericFont = pDType1FontEmbedder.getType1Font();
        this.isEmbedded = true;
        this.isDamaged = false;
        this.fontMatrixTransform = new AffineTransform();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public PDType1Font(com.tom_roush.pdfbox.cos.COSDictionary r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.PDType1Font.<init>(com.tom_roush.pdfbox.cos.COSDictionary):void");
    }

    private int repairLength1(byte[] bArr, int i) {
        int iMax = Math.max(0, i - 4);
        if (iMax <= 0 || iMax > bArr.length - 4) {
            iMax = bArr.length - 4;
        }
        while (true) {
            if (iMax > 0) {
                if (bArr[iMax + 0] == 101 && bArr[iMax + 1] == 120 && bArr[iMax + 2] == 101 && bArr[iMax + 3] == 99) {
                    iMax += 4;
                    while (iMax < i) {
                        byte b = bArr[iMax];
                        if (b != 13 && b != 10 && b != 32) {
                            break;
                        }
                        iMax++;
                    }
                } else {
                    iMax--;
                }
            } else {
                break;
            }
        }
        if (i - iMax == 0 || iMax <= 0) {
            return i;
        }
        Log.w("PdfBox-Android", "Ignored invalid Length1 " + i + " for Type 1 font " + getName());
        return iMax;
    }

    public final String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    @Override
    public float getHeight(int i) throws IOException {
        String strCodeToName = codeToName(i);
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getCharacterHeight(getEncoding().getName(i));
        }
        RectF rectF = new RectF();
        this.genericFont.getPath(strCodeToName).computeBounds(rectF, true);
        return rectF.height();
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
    public float getWidthFromFont(int i) throws IOException {
        String strCodeToName = codeToName(i);
        if (!this.isEmbedded && strCodeToName.equals(".notdef")) {
            return 250.0f;
        }
        PointF pointF = new PointF(this.genericFont.getWidth(strCodeToName), 0.0f);
        this.fontMatrixTransform.transform(pointF, pointF);
        return pointF.x;
    }

    @Override
    public boolean isEmbedded() {
        return this.isEmbedded;
    }

    @Override
    public float getAverageFontWidth() {
        if (getStandard14AFM() != null) {
            return getStandard14AFM().getAverageCharacterWidth();
        }
        return super.getAverageFontWidth();
    }

    @Override
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
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

    public Type1Font getType1Font() {
        return this.type1font;
    }

    @Override
    public FontBoxFont getFontBoxFont() {
        return this.genericFont;
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
            if (fontBoundingBox.getLowerLeftX() != 0.0f || fontBoundingBox.getLowerLeftY() != 0.0f || fontBoundingBox.getUpperRightX() != 0.0f || fontBoundingBox.getUpperRightY() != 0.0f) {
                return new BoundingBox(fontBoundingBox.getLowerLeftX(), fontBoundingBox.getLowerLeftY(), fontBoundingBox.getUpperRightX(), fontBoundingBox.getUpperRightY());
            }
        }
        return this.genericFont.getFontBBox();
    }

    public String codeToName(int i) throws IOException {
        return getNameInFont(getEncoding().getName(i));
    }

    private String getNameInFont(String str) throws IOException {
        if (isEmbedded() || this.genericFont.hasGlyph(str)) {
            return str;
        }
        String str2 = ALT_NAMES.get(str);
        if (str2 != null && !str.equals(".notdef") && this.genericFont.hasGlyph(str2)) {
            return str2;
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

    @Override
    public Path getPath(String str) throws IOException {
        if (str.equals(".notdef") && !this.isEmbedded) {
            return new Path();
        }
        return this.genericFont.getPath(getNameInFont(str));
    }

    @Override
    public boolean hasGlyph(String str) throws IOException {
        return this.genericFont.hasGlyph(getNameInFont(str));
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
}
