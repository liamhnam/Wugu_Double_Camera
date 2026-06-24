package com.tom_roush.fontbox.cff;

import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.afm.AFMParser;
import com.tom_roush.fontbox.cff.CFFParser;
import com.tom_roush.fontbox.util.BoundingBox;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class CFFFont implements FontBoxFont {
    protected byte[][] charStrings;
    protected CFFCharset charset;
    protected String fontName;
    protected byte[][] globalSubrIndex;
    private CFFParser.ByteSource source;
    protected final Map<String, Object> topDict = new LinkedHashMap();

    @Override
    public abstract List<Number> getFontMatrix();

    public abstract Type2CharString getType2CharString(int i) throws IOException;

    @Override
    public String getName() {
        return this.fontName;
    }

    void setName(String str) {
        this.fontName = str;
    }

    public void addValueToTopDict(String str, Object obj) {
        if (obj != null) {
            this.topDict.put(str, obj);
        }
    }

    public Map<String, Object> getTopDict() {
        return this.topDict;
    }

    @Override
    public BoundingBox getFontBBox() {
        return new BoundingBox((List) this.topDict.get(AFMParser.FONT_BBOX));
    }

    public CFFCharset getCharset() {
        return this.charset;
    }

    void setCharset(CFFCharset cFFCharset) {
        this.charset = cFFCharset;
    }

    public final List<byte[]> getCharStringBytes() {
        return Arrays.asList(this.charStrings);
    }

    final void setData(CFFParser.ByteSource byteSource) {
        this.source = byteSource;
    }

    public byte[] getData() throws IOException {
        return this.source.getBytes();
    }

    public int getNumCharStrings() {
        return this.charStrings.length;
    }

    void setGlobalSubrIndex(byte[][] bArr) {
        this.globalSubrIndex = bArr;
    }

    public List<byte[]> getGlobalSubrIndex() {
        return Arrays.asList(this.globalSubrIndex);
    }

    public String toString() {
        return getClass().getSimpleName() + "[name=" + this.fontName + ", topDict=" + this.topDict + ", charset=" + this.charset + ", charStrings=" + Arrays.deepToString(this.charStrings) + "]";
    }
}
