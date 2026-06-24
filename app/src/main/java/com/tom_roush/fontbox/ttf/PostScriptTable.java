package com.tom_roush.fontbox.ttf;

import java.io.IOException;

public class PostScriptTable extends TTFTable {
    public static final String TAG = "post";
    private float formatType;
    private String[] glyphNames;
    private long isFixedPitch;
    private float italicAngle;
    private long maxMemType1;
    private long maxMemType42;
    private long minMemType1;
    private long minMemType42;
    private short underlinePosition;
    private short underlineThickness;

    PostScriptTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.glyphNames = null;
    }

    @Override
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        String[] strArr;
        this.formatType = tTFDataStream.read32Fixed();
        this.italicAngle = tTFDataStream.read32Fixed();
        this.underlinePosition = tTFDataStream.readSignedShort();
        this.underlineThickness = tTFDataStream.readSignedShort();
        this.isFixedPitch = tTFDataStream.readUnsignedInt();
        this.minMemType42 = tTFDataStream.readUnsignedInt();
        this.maxMemType42 = tTFDataStream.readUnsignedInt();
        this.minMemType1 = tTFDataStream.readUnsignedInt();
        this.maxMemType1 = tTFDataStream.readUnsignedInt();
        float f = this.formatType;
        int i = 0;
        if (f == 1.0f) {
            this.glyphNames = new String[WGL4Names.NUMBER_OF_MAC_GLYPHS];
            System.arraycopy(WGL4Names.MAC_GLYPH_NAMES, 0, this.glyphNames, 0, WGL4Names.NUMBER_OF_MAC_GLYPHS);
        } else if (f == 2.0f) {
            int unsignedShort = tTFDataStream.readUnsignedShort();
            int[] iArr = new int[unsignedShort];
            this.glyphNames = new String[unsignedShort];
            int iMax = Integer.MIN_VALUE;
            for (int i2 = 0; i2 < unsignedShort; i2++) {
                int unsignedShort2 = tTFDataStream.readUnsignedShort();
                iArr[i2] = unsignedShort2;
                if (unsignedShort2 <= 32767) {
                    iMax = Math.max(iMax, unsignedShort2);
                }
            }
            if (iMax >= 258) {
                int i3 = (iMax - WGL4Names.NUMBER_OF_MAC_GLYPHS) + 1;
                strArr = new String[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    strArr[i4] = tTFDataStream.readString(tTFDataStream.readUnsignedByte());
                }
            } else {
                strArr = null;
            }
            while (i < unsignedShort) {
                int i5 = iArr[i];
                if (i5 < 258) {
                    this.glyphNames[i] = WGL4Names.MAC_GLYPH_NAMES[i5];
                } else if (i5 >= 258 && i5 <= 32767) {
                    this.glyphNames[i] = strArr[i5 - 258];
                } else {
                    this.glyphNames[i] = ".undefined";
                }
                i++;
            }
        } else if (f == 2.5f) {
            int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
            int[] iArr2 = new int[numberOfGlyphs];
            int i6 = 0;
            while (i6 < numberOfGlyphs) {
                int i7 = i6 + 1;
                iArr2[i6] = tTFDataStream.readSignedByte() + i7;
                i6 = i7;
            }
            this.glyphNames = new String[numberOfGlyphs];
            while (i < this.glyphNames.length) {
                String str = WGL4Names.MAC_GLYPH_NAMES[iArr2[i]];
                if (str != null) {
                    this.glyphNames[i] = str;
                }
                i++;
            }
        }
        this.initialized = true;
    }

    public float getFormatType() {
        return this.formatType;
    }

    public void setFormatType(float f) {
        this.formatType = f;
    }

    public long getIsFixedPitch() {
        return this.isFixedPitch;
    }

    public void setIsFixedPitch(long j) {
        this.isFixedPitch = j;
    }

    public float getItalicAngle() {
        return this.italicAngle;
    }

    public void setItalicAngle(float f) {
        this.italicAngle = f;
    }

    public long getMaxMemType1() {
        return this.maxMemType1;
    }

    public void setMaxMemType1(long j) {
        this.maxMemType1 = j;
    }

    public long getMaxMemType42() {
        return this.maxMemType42;
    }

    public void setMaxMemType42(long j) {
        this.maxMemType42 = j;
    }

    public long getMinMemType1() {
        return this.minMemType1;
    }

    public void setMimMemType1(long j) {
        this.minMemType1 = j;
    }

    public long getMinMemType42() {
        return this.minMemType42;
    }

    public void setMinMemType42(long j) {
        this.minMemType42 = j;
    }

    public short getUnderlinePosition() {
        return this.underlinePosition;
    }

    public void setUnderlinePosition(short s) {
        this.underlinePosition = s;
    }

    public short getUnderlineThickness() {
        return this.underlineThickness;
    }

    public void setUnderlineThickness(short s) {
        this.underlineThickness = s;
    }

    public String[] getGlyphNames() {
        return this.glyphNames;
    }

    public void setGlyphNames(String[] strArr) {
        this.glyphNames = strArr;
    }

    public String getName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this.glyphNames) == null || i > strArr.length) {
            return null;
        }
        return strArr[i];
    }
}
