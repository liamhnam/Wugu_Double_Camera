package com.tom_roush.fontbox.ttf;

import com.tom_roush.fontbox.util.Charsets;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NamingTable extends TTFTable {
    public static final String TAG = "name";
    private String fontFamily;
    private String fontSubFamily;
    private Map<Integer, Map<Integer, Map<Integer, Map<Integer, String>>>> lookupTable;
    private List<NameRecord> nameRecords;
    private String psName;

    NamingTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.fontFamily = null;
        this.fontSubFamily = null;
        this.psName = null;
    }

    @Override
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.readUnsignedShort();
        int unsignedShort = tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        this.nameRecords = new ArrayList(unsignedShort);
        for (int i = 0; i < unsignedShort; i++) {
            NameRecord nameRecord = new NameRecord();
            nameRecord.initData(trueTypeFont, tTFDataStream);
            this.nameRecords.add(nameRecord);
        }
        for (NameRecord nameRecord2 : this.nameRecords) {
            if (nameRecord2.getStringOffset() > getLength()) {
                nameRecord2.setString(null);
            } else {
                tTFDataStream.seek(getOffset() + 6 + ((long) (unsignedShort * 2 * 6)) + ((long) nameRecord2.getStringOffset()));
                int platformId = nameRecord2.getPlatformId();
                int platformEncodingId = nameRecord2.getPlatformEncodingId();
                Charset charset = Charsets.ISO_8859_1;
                if (platformId == 3 && (platformEncodingId == 0 || platformEncodingId == 1)) {
                    charset = Charsets.UTF_16;
                } else if (platformId == 2) {
                    if (platformEncodingId == 0) {
                        charset = Charsets.US_ASCII;
                    } else if (platformEncodingId == 1) {
                        charset = Charsets.ISO_10646;
                    } else if (platformEncodingId == 2) {
                        charset = Charsets.ISO_8859_1;
                    }
                }
                nameRecord2.setString(tTFDataStream.readString(nameRecord2.getStringLength(), charset));
            }
        }
        this.lookupTable = new HashMap(this.nameRecords.size());
        for (NameRecord nameRecord3 : this.nameRecords) {
            Map<Integer, Map<Integer, Map<Integer, String>>> map = this.lookupTable.get(Integer.valueOf(nameRecord3.getNameId()));
            if (map == null) {
                map = new HashMap<>();
                this.lookupTable.put(Integer.valueOf(nameRecord3.getNameId()), map);
            }
            Map<Integer, Map<Integer, String>> map2 = map.get(Integer.valueOf(nameRecord3.getPlatformId()));
            if (map2 == null) {
                map2 = new HashMap<>();
                map.put(Integer.valueOf(nameRecord3.getPlatformId()), map2);
            }
            Map<Integer, String> map3 = map2.get(Integer.valueOf(nameRecord3.getPlatformEncodingId()));
            if (map3 == null) {
                map3 = new HashMap<>();
                map2.put(Integer.valueOf(nameRecord3.getPlatformEncodingId()), map3);
            }
            map3.put(Integer.valueOf(nameRecord3.getLanguageId()), nameRecord3.getString());
        }
        this.fontFamily = getEnglishName(1);
        this.fontSubFamily = getEnglishName(2);
        String name = getName(6, 1, 0, 0);
        this.psName = name;
        if (name == null) {
            this.psName = getName(6, 3, 1, 1033);
        }
        String str = this.psName;
        if (str != null) {
            this.psName = str.trim();
        }
        this.initialized = true;
    }

    private String getEnglishName(int i) {
        for (int i2 = 4; i2 >= 0; i2--) {
            String name = getName(i, 0, i2, 0);
            if (name != null) {
                return name;
            }
        }
        String name2 = getName(i, 3, 1, 1033);
        if (name2 != null) {
            return name2;
        }
        String name3 = getName(i, 1, 0, 0);
        if (name3 != null) {
            return name3;
        }
        return null;
    }

    public String getName(int i, int i2, int i3, int i4) {
        Map<Integer, Map<Integer, String>> map;
        Map<Integer, String> map2;
        Map<Integer, Map<Integer, Map<Integer, String>>> map3 = this.lookupTable.get(Integer.valueOf(i));
        if (map3 == null || (map = map3.get(Integer.valueOf(i2))) == null || (map2 = map.get(Integer.valueOf(i3))) == null) {
            return null;
        }
        return map2.get(Integer.valueOf(i4));
    }

    public List<NameRecord> getNameRecords() {
        return this.nameRecords;
    }

    public String getFontFamily() {
        return this.fontFamily;
    }

    public String getFontSubFamily() {
        return this.fontSubFamily;
    }

    public String getPostScriptName() {
        return this.psName;
    }
}
