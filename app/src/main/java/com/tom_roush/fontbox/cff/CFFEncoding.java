package com.tom_roush.fontbox.cff;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.fontbox.encoding.Encoding;
import java.util.HashMap;
import java.util.Map;

public abstract class CFFEncoding extends Encoding {
    private final Map<Integer, String> codeToName = new HashMap(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);

    CFFEncoding() {
    }

    @Override
    public String getName(int i) {
        String str = this.codeToName.get(Integer.valueOf(i));
        return str == null ? ".notdef" : str;
    }

    public void add(int i, int i2, String str) {
        this.codeToName.put(Integer.valueOf(i), str);
        addCharacterEncoding(i, str);
    }

    protected void add(int i, int i2) {
        String name = CFFStandardString.getName(i2);
        this.codeToName.put(Integer.valueOf(i), name);
        addCharacterEncoding(i, name);
    }
}
