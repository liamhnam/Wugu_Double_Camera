package com.tom_roush.pdfbox.rendering;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.ttf.HeaderTable;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.harmony.awt.geom.AffineTransform;
import com.tom_roush.pdfbox.pdmodel.font.PDCIDFontType2;
import com.tom_roush.pdfbox.pdmodel.font.PDFont;
import com.tom_roush.pdfbox.pdmodel.font.PDTrueTypeFont;
import com.tom_roush.pdfbox.pdmodel.font.PDType0Font;
import com.tom_roush.pdfbox.pdmodel.font.PDVectorFont;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

final class TTFGlyph2D implements Glyph2D {
    private final PDFont font;
    private final Map<Integer, Path> glyphs;
    private boolean hasScaling;
    private final boolean isCIDFont;
    private float scale;
    private final TrueTypeFont ttf;
    private PDVectorFont vectorFont;

    TTFGlyph2D(PDTrueTypeFont pDTrueTypeFont) throws IOException {
        this(pDTrueTypeFont.getTrueTypeFont(), pDTrueTypeFont, false);
        this.vectorFont = pDTrueTypeFont;
    }

    TTFGlyph2D(PDType0Font pDType0Font) throws IOException {
        this(((PDCIDFontType2) pDType0Font.getDescendantFont()).getTrueTypeFont(), pDType0Font, true);
        this.vectorFont = pDType0Font;
    }

    private TTFGlyph2D(TrueTypeFont trueTypeFont, PDFont pDFont, boolean z) throws IOException {
        this.scale = 1.0f;
        this.glyphs = new HashMap();
        this.font = pDFont;
        this.ttf = trueTypeFont;
        this.isCIDFont = z;
        HeaderTable header = trueTypeFont.getHeader();
        if (header == null || header.getUnitsPerEm() == 1000) {
            return;
        }
        this.scale = 1000.0f / header.getUnitsPerEm();
        this.hasScaling = true;
    }

    @Override
    public Path getPathForCharacterCode(int i) throws IOException {
        return getPathForGID(getGIDForCharacterCode(i), i);
    }

    private int getGIDForCharacterCode(int i) throws IOException {
        if (this.isCIDFont) {
            return ((PDType0Font) this.font).codeToGID(i);
        }
        return ((PDTrueTypeFont) this.font).codeToGID(i);
    }

    public Path getPathForGID(int i, int i2) throws IOException {
        Path path = this.glyphs.get(Integer.valueOf(i));
        if (path == null) {
            if (i == 0 || i >= this.ttf.getMaximumProfile().getNumGlyphs()) {
                if (this.isCIDFont) {
                    Log.w("PdfBox-Android", "No glyph for " + i2 + " (CID " + String.format("%04x", Integer.valueOf(((PDType0Font) this.font).codeToCID(i2))) + ") in font " + this.font.getName());
                } else {
                    Log.w("PdfBox-Android", "No glyph for " + i2 + " in font " + this.font.getName());
                }
            }
            path = (i != 0 || this.font.isEmbedded() || this.font.isStandard14()) ? this.vectorFont.getPath(i2) : null;
            if (path == null) {
                path = new Path();
            } else if (this.hasScaling) {
                float f = this.scale;
                path.transform(AffineTransform.getScaleInstance(f, f).toMatrix());
            }
        }
        return new Path(path);
    }

    @Override
    public void dispose() {
        this.glyphs.clear();
    }
}
