package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import java.io.IOException;

public interface PDVectorFont {
    Path getPath(int i) throws IOException;

    boolean hasGlyph(int i) throws IOException;
}
