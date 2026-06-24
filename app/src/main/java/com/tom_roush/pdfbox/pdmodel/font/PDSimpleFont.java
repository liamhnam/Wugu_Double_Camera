package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.font.encoding.DictionaryEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.Encoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.GlyphList;
import com.tom_roush.pdfbox.pdmodel.font.encoding.MacRomanEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.StandardEncoding;
import com.tom_roush.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class PDSimpleFont extends PDFont {
    protected Encoding encoding;
    protected GlyphList glyphList;
    private Map<String, Integer> invertedEncoding;
    private Boolean isSymbolic;
    private final Set<Integer> noUnicode;

    public abstract FontBoxFont getFontBoxFont();

    public abstract Path getPath(String str) throws IOException;

    public abstract boolean hasGlyph(String str) throws IOException;

    @Override
    public boolean isVertical() {
        return false;
    }

    protected abstract Encoding readEncodingFromFont() throws IOException;

    @Override
    public boolean willBeSubset() {
        return false;
    }

    PDSimpleFont() {
        this.noUnicode = new HashSet();
    }

    PDSimpleFont(String str) {
        super(str);
        this.noUnicode = new HashSet();
        this.encoding = WinAnsiEncoding.INSTANCE;
        if ("ZapfDingbats".equals(str)) {
            this.glyphList = GlyphList.getZapfDingbats();
        } else {
            this.glyphList = GlyphList.getAdobeGlyphList();
        }
    }

    PDSimpleFont(COSDictionary cOSDictionary) throws IOException {
        super(cOSDictionary);
        this.noUnicode = new HashSet();
    }

    protected void readEncoding() throws IOException {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        if (dictionaryObject != null) {
            if (dictionaryObject instanceof COSName) {
                COSName cOSName = (COSName) dictionaryObject;
                Encoding encoding = Encoding.getInstance(cOSName);
                this.encoding = encoding;
                if (encoding == null) {
                    Log.w("PdfBox-Android", "Unknown encoding: " + cOSName.getName());
                    this.encoding = readEncodingFromFont();
                }
            } else if (dictionaryObject instanceof COSDictionary) {
                COSDictionary cOSDictionary = (COSDictionary) dictionaryObject;
                Boolean symbolicFlag = getSymbolicFlag();
                Encoding encodingFromFont = (cOSDictionary.containsKey(COSName.BASE_ENCODING) || !(symbolicFlag != null && symbolicFlag.booleanValue())) ? null : readEncodingFromFont();
                if (symbolicFlag == null) {
                    symbolicFlag = false;
                }
                this.encoding = new DictionaryEncoding(cOSDictionary, !symbolicFlag.booleanValue(), encodingFromFont);
            }
        } else {
            this.encoding = readEncodingFromFont();
        }
        if ("ZapfDingbats".equals(Standard14Fonts.getMappedFontName(getName()))) {
            this.glyphList = GlyphList.getZapfDingbats();
        } else {
            this.glyphList = GlyphList.getAdobeGlyphList();
        }
    }

    private void readEncodingFromName(COSName cOSName) throws IOException {
        Encoding encoding = Encoding.getInstance(cOSName);
        this.encoding = encoding;
        if (encoding == null) {
            Log.w("PdfBox-Android", "Unknown encoding: " + cOSName.getName());
            this.encoding = readEncodingFromFont();
        }
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public GlyphList getGlyphList() {
        return this.glyphList;
    }

    public final boolean isSymbolic() {
        if (this.isSymbolic == null) {
            Boolean boolIsFontSymbolic = isFontSymbolic();
            if (boolIsFontSymbolic != null) {
                this.isSymbolic = boolIsFontSymbolic;
            } else {
                this.isSymbolic = true;
            }
        }
        return this.isSymbolic.booleanValue();
    }

    protected Boolean isFontSymbolic() {
        Boolean symbolicFlag = getSymbolicFlag();
        if (symbolicFlag != null) {
            return symbolicFlag;
        }
        if (isStandard14()) {
            String mappedFontName = Standard14Fonts.getMappedFontName(getName());
            return Boolean.valueOf(mappedFontName.equals("Symbol") || mappedFontName.equals("ZapfDingbats"));
        }
        Encoding encoding = this.encoding;
        if (encoding == null) {
            if (!(this instanceof PDTrueTypeFont)) {
                throw new IllegalStateException("PDFBox bug: encoding should not be null!");
            }
            return true;
        }
        if ((encoding instanceof WinAnsiEncoding) || (encoding instanceof MacRomanEncoding) || (encoding instanceof StandardEncoding)) {
            return false;
        }
        if (!(encoding instanceof DictionaryEncoding)) {
            return null;
        }
        for (String str : ((DictionaryEncoding) encoding).getDifferences().values()) {
            if (!str.equals(".notdef") && (!WinAnsiEncoding.INSTANCE.contains(str) || !MacRomanEncoding.INSTANCE.contains(str) || !StandardEncoding.INSTANCE.contains(str))) {
                return true;
            }
        }
        return false;
    }

    protected final Boolean getSymbolicFlag() {
        if (getFontDescriptor() != null) {
            return Boolean.valueOf(getFontDescriptor().isSymbolic());
        }
        return null;
    }

    @Override
    public String toUnicode(int i) throws IOException {
        return toUnicode(i, GlyphList.getAdobeGlyphList());
    }

    @Override
    public String toUnicode(int i, GlyphList glyphList) throws IOException {
        String name;
        if (this.glyphList != GlyphList.getAdobeGlyphList()) {
            glyphList = this.glyphList;
        }
        String unicode = super.toUnicode(i);
        if (unicode != null) {
            return unicode;
        }
        Encoding encoding = this.encoding;
        if (encoding != null) {
            name = encoding.getName(i);
            String unicode2 = glyphList.toUnicode(name);
            if (unicode2 != null) {
                return unicode2;
            }
        } else {
            name = null;
        }
        if (!this.noUnicode.contains(Integer.valueOf(i))) {
            this.noUnicode.add(Integer.valueOf(i));
            if (name != null) {
                Log.w("PdfBox-Android", "No Unicode mapping for " + name + " (" + i + ") in font " + getName());
            } else {
                Log.w("PdfBox-Android", "No Unicode mapping for character code " + i + " in font " + getName());
            }
        }
        return null;
    }

    @Override
    protected final float getStandard14Width(int i) {
        if (getStandard14AFM() != null) {
            String name = getEncoding().getName(i);
            if (name.equals(".notdef")) {
                return 250.0f;
            }
            return getStandard14AFM().getCharacterWidth(name);
        }
        throw new IllegalStateException("No AFM");
    }

    @Override
    public boolean isStandard14() {
        if (getEncoding() instanceof DictionaryEncoding) {
            DictionaryEncoding dictionaryEncoding = (DictionaryEncoding) getEncoding();
            if (dictionaryEncoding.getDifferences().size() > 0) {
                Encoding baseEncoding = dictionaryEncoding.getBaseEncoding();
                for (Map.Entry<Integer, String> entry : dictionaryEncoding.getDifferences().entrySet()) {
                    if (!entry.getValue().equals(baseEncoding.getName(entry.getKey().intValue()))) {
                        return false;
                    }
                }
            }
        }
        return super.isStandard14();
    }

    @Override
    public void addToSubset(int i) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void subset() throws IOException {
        throw new UnsupportedOperationException();
    }
}
