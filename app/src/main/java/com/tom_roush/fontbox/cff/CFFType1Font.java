package com.tom_roush.fontbox.cff;

import android.graphics.Path;
import com.tom_roush.fontbox.EncodedFont;
import com.tom_roush.fontbox.type1.Type1CharStringReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CFFType1Font extends CFFFont implements EncodedFont {
    private CFFEncoding encoding;
    private final Map<String, Object> privateDict = new LinkedHashMap();
    private final Map<Integer, Type2CharString> charStringCache = new ConcurrentHashMap();
    private final PrivateType1CharStringReader reader = new PrivateType1CharStringReader();

    private class PrivateType1CharStringReader implements Type1CharStringReader {
        private PrivateType1CharStringReader() {
        }

        @Override
        public Type1CharString getType1CharString(String str) throws IOException {
            return CFFType1Font.this.getType1CharString(str);
        }
    }

    @Override
    public Path getPath(String str) throws IOException {
        return getType1CharString(str).getPath();
    }

    @Override
    public float getWidth(String str) throws IOException {
        return getType1CharString(str).getWidth();
    }

    @Override
    public boolean hasGlyph(String str) {
        return this.charset.getGIDForSID(this.charset.getSID(str)) != 0;
    }

    @Override
    public List<Number> getFontMatrix() {
        return (List) this.topDict.get("FontMatrix");
    }

    public Type1CharString getType1CharString(String str) throws IOException {
        return getType2CharString(nameToGID(str), str);
    }

    public int nameToGID(String str) {
        return this.charset.getGIDForSID(this.charset.getSID(str));
    }

    @Override
    public Type2CharString getType2CharString(int i) throws IOException {
        return getType2CharString(i, "GID+" + i);
    }

    private Type2CharString getType2CharString(int i, String str) throws IOException {
        Type2CharString type2CharString = this.charStringCache.get(Integer.valueOf(i));
        if (type2CharString != null) {
            return type2CharString;
        }
        byte[] bArr = i < this.charStrings.length ? this.charStrings[i] : null;
        if (bArr == null) {
            bArr = this.charStrings[0];
        }
        Type2CharString type2CharString2 = new Type2CharString(this.reader, this.fontName, str, i, new Type2CharStringParser(this.fontName, str).parse(bArr, this.globalSubrIndex, getLocalSubrIndex()), getDefaultWidthX(), getNominalWidthX());
        this.charStringCache.put(Integer.valueOf(i), type2CharString2);
        return type2CharString2;
    }

    public Map<String, Object> getPrivateDict() {
        return this.privateDict;
    }

    void addToPrivateDict(String str, Object obj) {
        if (obj != null) {
            this.privateDict.put(str, obj);
        }
    }

    @Override
    public CFFEncoding getEncoding() {
        return this.encoding;
    }

    void setEncoding(CFFEncoding cFFEncoding) {
        this.encoding = cFFEncoding;
    }

    private byte[][] getLocalSubrIndex() {
        return (byte[][]) this.privateDict.get("Subrs");
    }

    private Object getProperty(String str) {
        Object obj = this.topDict.get(str);
        if (obj != null) {
            return obj;
        }
        Object obj2 = this.privateDict.get(str);
        if (obj2 != null) {
            return obj2;
        }
        return null;
    }

    private int getDefaultWidthX() {
        Number number = (Number) getProperty("defaultWidthX");
        if (number == null) {
            return 1000;
        }
        return number.intValue();
    }

    private int getNominalWidthX() {
        Number number = (Number) getProperty("nominalWidthX");
        if (number == null) {
            return 0;
        }
        return number.intValue();
    }
}
