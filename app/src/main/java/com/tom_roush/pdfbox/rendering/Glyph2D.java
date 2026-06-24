package com.tom_roush.pdfbox.rendering;

import android.graphics.Path;
import java.io.IOException;

interface Glyph2D {
    void dispose();

    Path getPathForCharacterCode(int i) throws IOException;
}
