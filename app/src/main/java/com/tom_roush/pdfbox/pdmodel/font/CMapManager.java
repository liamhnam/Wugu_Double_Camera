package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.cmap.CMapParser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class CMapManager {
    static Map<String, CMap> cMapCache = Collections.synchronizedMap(new HashMap());

    private CMapManager() {
    }

    public static CMap getPredefinedCMap(String str) throws Throwable {
        CMap cMap = cMapCache.get(str);
        if (cMap != null) {
            return cMap;
        }
        CMap predefined = new CMapParser().parsePredefined(str);
        cMapCache.put(predefined.getName(), predefined);
        return predefined;
    }

    public static CMap parseCMap(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            return new CMapParser().parse(inputStream);
        }
        return null;
    }
}
