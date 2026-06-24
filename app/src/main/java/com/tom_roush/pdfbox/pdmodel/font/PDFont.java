package com.tom_roush.pdfbox.pdmodel.font;

import android.util.Log;
import com.tom_roush.fontbox.afm.FontMetrics;
import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public abstract class PDFont implements COSObjectable, PDFontLike {
    protected static final Matrix DEFAULT_FONT_MATRIX = new Matrix(0.001f, 0.0f, 0.0f, 0.001f, 0.0f, 0.0f);
    private final FontMetrics afmStandard14;
    private float avgFontWidth;
    protected final COSDictionary dict;
    private PDFontDescriptor fontDescriptor;
    private float fontWidthOfSpace = -1.0f;
    private final CMap toUnicodeCMap;
    private List<Float> widths;

    public abstract void addToSubset(int i);

    protected abstract byte[] encode(int i) throws IOException;

    @Override
    public abstract BoundingBox getBoundingBox() throws IOException;

    @Override
    public abstract float getHeight(int i) throws IOException;

    @Override
    public abstract String getName();

    protected abstract float getStandard14Width(int i);

    @Override
    public abstract float getWidthFromFont(int i) throws IOException;

    @Override
    public abstract boolean isDamaged();

    @Override
    public abstract boolean isEmbedded();

    public abstract boolean isVertical();

    public abstract int readCode(InputStream inputStream) throws IOException;

    public abstract void subset() throws IOException;

    public abstract boolean willBeSubset();

    PDFont() {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        this.fontDescriptor = null;
        this.afmStandard14 = null;
    }

    PDFont(String str) {
        COSDictionary cOSDictionary = new COSDictionary();
        this.dict = cOSDictionary;
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        this.toUnicodeCMap = null;
        FontMetrics afm = Standard14Fonts.getAFM(str);
        this.afmStandard14 = afm;
        if (afm == null) {
            throw new IllegalArgumentException("No AFM for font " + str);
        }
        this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(afm);
    }

    protected PDFont(COSDictionary cOSDictionary) throws IOException {
        this.dict = cOSDictionary;
        FontMetrics afm = Standard14Fonts.getAFM(getName());
        this.afmStandard14 = afm;
        COSDictionary cOSDictionary2 = (COSDictionary) cOSDictionary.getDictionaryObject(COSName.FONT_DESC);
        if (cOSDictionary2 != null) {
            this.fontDescriptor = new PDFontDescriptor(cOSDictionary2);
        } else if (afm != null) {
            this.fontDescriptor = PDType1FontEmbedder.buildFontDescriptor(afm);
        } else {
            this.fontDescriptor = null;
        }
        COSBase dictionaryObject = cOSDictionary.getDictionaryObject(COSName.TO_UNICODE);
        if (dictionaryObject != null) {
            CMap cMap = readCMap(dictionaryObject);
            this.toUnicodeCMap = cMap;
            if (cMap == null || cMap.hasUnicodeMappings()) {
                return;
            }
            Log.w("PdfBox-Android", "Invalid ToUnicode CMap in font " + getName());
            return;
        }
        this.toUnicodeCMap = null;
    }

    protected final FontMetrics getStandard14AFM() {
        return this.afmStandard14;
    }

    @Override
    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    protected final void setFontDescriptor(PDFontDescriptor pDFontDescriptor) {
        this.fontDescriptor = pDFontDescriptor;
    }

    protected final CMap readCMap(COSBase cOSBase) throws IOException {
        if (cOSBase instanceof COSName) {
            return CMapManager.getPredefinedCMap(((COSName) cOSBase).getName());
        }
        if (cOSBase instanceof COSStream) {
            COSInputStream cOSInputStreamCreateInputStream = null;
            try {
                cOSInputStreamCreateInputStream = ((COSStream) cOSBase).createInputStream();
                return CMapManager.parseCMap(cOSInputStreamCreateInputStream);
            } finally {
                IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
            }
        }
        throw new IOException("Expected Name or Stream");
    }

    @Override
    public COSDictionary getCOSObject() {
        return this.dict;
    }

    @Override
    public Vector getPositionVector(int i) {
        throw new UnsupportedOperationException("Horizontal fonts have no position vector");
    }

    public Vector getDisplacement(int i) throws IOException {
        return new Vector(getWidth(i) / 1000.0f, 0.0f);
    }

    @Override
    public float getWidth(int i) throws IOException {
        if (this.dict.containsKey(COSName.WIDTHS) || this.dict.containsKey(COSName.MISSING_WIDTH)) {
            int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
            int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
            int size = getWidths().size();
            int i4 = i - i2;
            if (size > 0 && i >= i2 && i <= i3 && i4 < size) {
                return getWidths().get(i4).floatValue();
            }
            PDFontDescriptor fontDescriptor = getFontDescriptor();
            if (fontDescriptor != null && fontDescriptor.hasMissingWidth()) {
                return fontDescriptor.getMissingWidth();
            }
        }
        if (isStandard14()) {
            return getStandard14Width(i);
        }
        return getWidthFromFont(i);
    }

    public final byte[] encode(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int iCharCount = 0;
        while (iCharCount < str.length()) {
            int iCodePointAt = str.codePointAt(iCharCount);
            byteArrayOutputStream.write(encode(iCodePointAt));
            iCharCount += Character.charCount(iCodePointAt);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public float getStringWidth(String str) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encode(str));
        float width = 0.0f;
        while (byteArrayInputStream.available() > 0) {
            width += getWidth(readCode(byteArrayInputStream));
        }
        return width;
    }

    @Override
    public float getAverageFontWidth() {
        float fFloatValue;
        float f;
        float f2 = this.avgFontWidth;
        if (f2 == 0.0f) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.WIDTHS);
            if (cOSArray != null) {
                fFloatValue = 0.0f;
                f = 0.0f;
                for (int i = 0; i < cOSArray.size(); i++) {
                    COSNumber cOSNumber = (COSNumber) cOSArray.getObject(i);
                    if (cOSNumber.floatValue() > 0.0f) {
                        fFloatValue += cOSNumber.floatValue();
                        f += 1.0f;
                    }
                }
            } else {
                fFloatValue = 0.0f;
                f = 0.0f;
            }
            f2 = fFloatValue > 0.0f ? fFloatValue / f : 0.0f;
            this.avgFontWidth = f2;
        }
        return f2;
    }

    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        return toUnicode(i);
    }

    public String toUnicode(int i) throws IOException {
        CMap cMap = this.toUnicodeCMap;
        if (cMap == null) {
            return null;
        }
        if (cMap.getName() != null && this.toUnicodeCMap.getName().startsWith("Identity-") && (this.dict.getDictionaryObject(COSName.TO_UNICODE) instanceof COSName)) {
            return new String(new char[]{(char) i});
        }
        return this.toUnicodeCMap.toUnicode(i);
    }

    public String getType() {
        return this.dict.getNameAsString(COSName.TYPE);
    }

    public String getSubType() {
        return this.dict.getNameAsString(COSName.SUBTYPE);
    }

    protected final List<Float> getWidths() {
        if (this.widths == null) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.WIDTHS);
            if (cOSArray != null) {
                this.widths = COSArrayList.convertFloatCOSArrayToList(cOSArray);
            } else {
                this.widths = Collections.emptyList();
            }
        }
        return this.widths;
    }

    @Override
    public Matrix getFontMatrix() {
        return DEFAULT_FONT_MATRIX;
    }

    public float getSpaceWidth() {
        if (this.fontWidthOfSpace == -1.0f) {
            try {
                if (this.dict.getDictionaryObject(COSName.TO_UNICODE) != null) {
                    int spaceMapping = this.toUnicodeCMap.getSpaceMapping();
                    if (spaceMapping > -1) {
                        this.fontWidthOfSpace = getWidth(spaceMapping);
                    }
                } else {
                    this.fontWidthOfSpace = getWidth(32);
                }
                if (this.fontWidthOfSpace <= 0.0f) {
                    this.fontWidthOfSpace = getAverageFontWidth();
                }
            } catch (Exception e) {
                Log.e("PdfBox-Android", "Can't determine the width of the space character, assuming 250", e);
                this.fontWidthOfSpace = 250.0f;
            }
        }
        return this.fontWidthOfSpace;
    }

    public boolean isStandard14() {
        if (isEmbedded()) {
            return false;
        }
        return Standard14Fonts.containsName(getName());
    }

    public boolean equals(Object obj) {
        return (obj instanceof PDFont) && ((PDFont) obj).getCOSObject() == getCOSObject();
    }

    public int hashCode() {
        return getCOSObject().hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + " " + getName();
    }
}
