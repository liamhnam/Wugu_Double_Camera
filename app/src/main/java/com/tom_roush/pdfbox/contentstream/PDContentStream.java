package com.tom_roush.pdfbox.contentstream;

import com.tom_roush.pdfbox.pdmodel.PDResources;
import com.tom_roush.pdfbox.pdmodel.common.PDRectangle;
import com.tom_roush.pdfbox.util.Matrix;
import java.io.IOException;
import java.io.InputStream;

public interface PDContentStream {
    PDRectangle getBBox();

    InputStream getContents() throws IOException;

    Matrix getMatrix();

    PDResources getResources();
}
