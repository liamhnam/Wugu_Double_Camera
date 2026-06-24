package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class PDType3Font extends PDSimpleFont {
    private COSDictionary charProcs;
    private BoundingBox fontBBox;
    private Matrix fontMatrix;
    private PDResources resources;

    @Override
    public boolean isDamaged() {
        return false;
    }

    @Override
    public boolean isEmbedded() {
        return true;
    }

    public PDType3Font(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        readEncoding();
    }

    @Override
    public String getName() {
        return this.dict.getNameAsString(COSName.NAME);
    }

    @Override
    protected final void readEncoding() throws IOException {
        this.encoding = new DictionaryEncoding((COSDictionary) this.dict.getDictionaryObject(COSName.ENCODING));
        this.glyphList = GlyphList.getAdobeGlyphList();
    }

    @Override
    protected Encoding readEncodingFromFont() throws IOException {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override
    protected Boolean isFontSymbolic() {
        return false;
    }

    @Override
    public Path getPath(String str) throws IOException {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override
    public boolean hasGlyph(String str) throws IOException {
        return ((COSStream) getCharProcs().getDictionaryObject(COSName.getPDFName(str))) != null;
    }

    @Override
    public FontBoxFont getFontBoxFont() {
        throw new UnsupportedOperationException("not supported for Type 3 fonts");
    }

    @Override
    public Vector getDisplacement(int i) throws IOException {
        return getFontMatrix().transform(new Vector(getWidth(i), 0.0f));
    }

    @Override
    public float getWidth(int i) throws IOException {
        int i2 = this.dict.getInt(COSName.FIRST_CHAR, -1);
        int i3 = this.dict.getInt(COSName.LAST_CHAR, -1);
        if (getWidths().size() > 0 && i >= i2 && i <= i3) {
            return getWidths().get(i - i2).floatValue();
        }
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor != null) {
            return fontDescriptor.getMissingWidth();
        }
        return getWidthFromFont(i);
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        PDType3CharProc charProc = getCharProc(i);
        if (charProc == null) {
            return 0.0f;
        }
        return charProc.getWidth();
    }

    @Override
    public float getHeight(int i) throws IOException {
        PDFontDescriptor fontDescriptor = getFontDescriptor();
        if (fontDescriptor == null) {
            return 0.0f;
        }
        PDRectangle fontBoundingBox = fontDescriptor.getFontBoundingBox();
        float height = fontBoundingBox != null ? fontBoundingBox.getHeight() / 2.0f : 0.0f;
        if (height == 0.0f) {
            height = fontDescriptor.getCapHeight();
        }
        if (height == 0.0f) {
            height = fontDescriptor.getAscent();
        }
        if (height != 0.0f) {
            return height;
        }
        float xHeight = fontDescriptor.getXHeight();
        return xHeight > 0.0f ? xHeight - fontDescriptor.getDescent() : xHeight;
    }

    @Override
    protected byte[] encode(int i) throws IOException {
        throw new UnsupportedOperationException("Not implemented: Type3");
    }

    @Override
    public int readCode(InputStream inputStream) throws IOException {
        return inputStream.read();
    }

    @Override
    public Matrix getFontMatrix() {
        if (this.fontMatrix == null) {
            COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.FONT_MATRIX);
            if (cOSArray != null) {
                this.fontMatrix = new Matrix(cOSArray);
            } else {
                return super.getFontMatrix();
            }
        }
        return this.fontMatrix;
    }

    public PDResources getResources() {
        COSDictionary cOSDictionary;
        if (this.resources == null && (cOSDictionary = (COSDictionary) this.dict.getDictionaryObject(COSName.RESOURCES)) != null) {
            this.resources = new PDResources(cOSDictionary);
        }
        return this.resources;
    }

    public PDRectangle getFontBBox() {
        COSArray cOSArray = (COSArray) this.dict.getDictionaryObject(COSName.FONT_BBOX);
        if (cOSArray != null) {
            return new PDRectangle(cOSArray);
        }
        return null;
    }

    @Override
    public BoundingBox getBoundingBox() {
        if (this.fontBBox == null) {
            this.fontBBox = generateBoundingBox();
        }
        return this.fontBBox;
    }

    private BoundingBox generateBoundingBox() {
        PDRectangle fontBBox = getFontBBox();
        if (fontBBox.getLowerLeftX() == 0.0f && fontBBox.getLowerLeftY() == 0.0f && fontBBox.getUpperRightX() == 0.0f && fontBBox.getUpperRightY() == 0.0f) {
            COSDictionary charProcs = getCharProcs();
            Iterator<COSName> it = charProcs.keySet().iterator();
            while (it.hasNext()) {
                COSBase dictionaryObject = charProcs.getDictionaryObject(it.next());
                if (dictionaryObject instanceof COSStream) {
                    try {
                        PDRectangle glyphBBox = new PDType3CharProc(this, (COSStream) dictionaryObject).getGlyphBBox();
                        if (glyphBBox != null) {
                            fontBBox.setLowerLeftX(Math.min(fontBBox.getLowerLeftX(), glyphBBox.getLowerLeftX()));
                            fontBBox.setLowerLeftY(Math.min(fontBBox.getLowerLeftY(), glyphBBox.getLowerLeftY()));
                            fontBBox.setUpperRightX(Math.max(fontBBox.getUpperRightX(), glyphBBox.getUpperRightX()));
                            fontBBox.setUpperRightY(Math.max(fontBBox.getUpperRightY(), glyphBBox.getUpperRightY()));
                        }
                    } catch (IOException unused) {
                    }
                }
            }
        }
        return new BoundingBox(fontBBox.getLowerLeftX(), fontBBox.getLowerLeftY(), fontBBox.getWidth(), fontBBox.getHeight());
    }

    public COSDictionary getCharProcs() {
        if (this.charProcs == null) {
            this.charProcs = (COSDictionary) this.dict.getDictionaryObject(COSName.CHAR_PROCS);
        }
        return this.charProcs;
    }

    public PDType3CharProc getCharProc(int i) {
        COSStream cOSStream;
        String name = getEncoding().getName(i);
        if (name.equals(".notdef") || (cOSStream = (COSStream) getCharProcs().getDictionaryObject(COSName.getPDFName(name))) == null) {
            return null;
        }
        return new PDType3CharProc(this, cOSStream);
    }
}
