package com.tom_roush.fontbox.cff;

import com.tom_roush.fontbox.type1.Type1CharStringReader;
import java.util.List;

public class CIDKeyedType2CharString extends Type2CharString {
    private final int cid;

    public CIDKeyedType2CharString(Type1CharStringReader type1CharStringReader, String str, int i, int i2, List<Object> list, int i3, int i4) {
        super(type1CharStringReader, str, String.format("%04x", Integer.valueOf(i)), i2, list, i3, i4);
        this.cid = i;
    }

    public int getCID() {
        return this.cid;
    }
}
