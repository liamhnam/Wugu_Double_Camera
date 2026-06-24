package com.tom_roush.pdfbox.rendering;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.pdfbox.pdmodel.font.PDSimpleFont;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class Type1Glyph2D implements Glyph2D {
    private final Map<Integer, Path> cache = new HashMap();
    private final PDSimpleFont font;

    Type1Glyph2D(PDSimpleFont pDSimpleFont) {
        this.font = pDSimpleFont;
    }

    @Override
    public Path getPathForCharacterCode(int i) {
        Path path = this.cache.get(Integer.valueOf(i));
        if (path != null) {
            return path;
        }
        try {
            String name = this.font.getEncoding().getName(i);
            if (!this.font.hasGlyph(name)) {
                Log.w("PdfBox-Android", "No glyph for " + i + " (" + name + ") in font " + this.font.getName());
            }
            Path path2 = this.font.getPath(name);
            return path2 == null ? this.font.getPath(".notdef") : path2;
        } catch (IOException e) {
            Log.e("PdfBox-Android", "Glyph rendering failed", e);
            return new Path();
        }
    }

    @Override
    public void dispose() {
        this.cache.clear();
    }
}
