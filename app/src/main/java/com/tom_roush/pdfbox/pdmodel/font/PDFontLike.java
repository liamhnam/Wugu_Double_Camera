package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.IOException;

public interface PDFontLike {
    float getAverageFontWidth();

    BoundingBox getBoundingBox() throws IOException;

    PDFontDescriptor getFontDescriptor();

    Matrix getFontMatrix();

    @Deprecated
    float getHeight(int i) throws IOException;

    String getName();

    Vector getPositionVector(int i);

    float getWidth(int i) throws IOException;

    float getWidthFromFont(int i) throws IOException;

    boolean isDamaged();

    boolean isEmbedded();
}
