package com.tom_roush.fontbox.encoding;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Encoding {
    protected Map<Integer, String> codeToName = new HashMap(256);
    protected Map<String, Integer> nameToCode = new HashMap(256);

    protected void addCharacterEncoding(int i, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        this.nameToCode.put(str, Integer.valueOf(i));
    }

    public Integer getCode(String str) {
        return this.nameToCode.get(str);
    }

    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str != null ? str : ".notdef";
    }

    public Map<Integer, String> getCodeToNameMap() {
        return Collections.unmodifiableMap(this.codeToName);
    }
}
