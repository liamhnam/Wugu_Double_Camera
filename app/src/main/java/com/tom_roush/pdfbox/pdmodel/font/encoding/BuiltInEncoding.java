package com.tom_roush.pdfbox.pdmodel.font.encoding;

import com.tom_roush.pdfbox.cos.COSBase;
import java.util.Map;

public class BuiltInEncoding extends Encoding {
    @Override
    public String getEncodingName() {
        return "built-in (TTF)";
    }

    public BuiltInEncoding(Map<Integer, String> map) {
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            add(entry.getKey().intValue(), entry.getValue());
        }
    }

    @Override
    public COSBase getCOSObject() {
        throw new UnsupportedOperationException("Built-in encodings cannot be serialized");
    }
}
