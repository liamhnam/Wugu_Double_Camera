package com.tom_roush.pdfbox.pdmodel.font.encoding;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Encoding implements COSObjectable {
    protected final Map<Integer, String> codeToName = new HashMap(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    protected final Map<String, Integer> inverted = new HashMap(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    private Set<String> names;

    public abstract String getEncodingName();

    public static Encoding getInstance(COSName cOSName) {
        if (COSName.STANDARD_ENCODING.equals(cOSName)) {
            return StandardEncoding.INSTANCE;
        }
        if (COSName.WIN_ANSI_ENCODING.equals(cOSName)) {
            return WinAnsiEncoding.INSTANCE;
        }
        if (COSName.MAC_ROMAN_ENCODING.equals(cOSName)) {
            return MacRomanEncoding.INSTANCE;
        }
        return null;
    }

    public Map<Integer, String> getCodeToNameMap() {
        return Collections.unmodifiableMap(this.codeToName);
    }

    public Map<String, Integer> getNameToCodeMap() {
        return Collections.unmodifiableMap(this.inverted);
    }

    protected void add(int i, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        this.inverted.put(str, Integer.valueOf(i));
    }

    public boolean contains(String str) {
        if (this.names == null) {
            HashSet hashSet = new HashSet(this.codeToName.size());
            this.names = hashSet;
            hashSet.addAll(this.codeToName.values());
        }
        return this.names.contains(str);
    }

    public boolean contains(int i) {
        return this.codeToName.containsKey(Integer.valueOf(i));
    }

    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str != null ? str : ".notdef";
    }
}
