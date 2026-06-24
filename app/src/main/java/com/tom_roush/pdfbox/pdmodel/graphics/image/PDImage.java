package com.tom_roush.pdfbox.pdmodel.graphics.image;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.pdmodel.common.COSObjectable;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDColorSpace;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface PDImage extends COSObjectable {
    InputStream createInputStream() throws IOException;

    InputStream createInputStream(List<String> list) throws IOException;

    int getBitsPerComponent();

    PDColorSpace getColorSpace() throws IOException;

    COSArray getDecode();

    int getHeight();

    Bitmap getImage() throws IOException;

    boolean getInterpolate();

    Bitmap getStencilImage(Paint paint) throws IOException;

    String getSuffix();

    int getWidth();

    boolean isEmpty();

    boolean isStencil();

    void setBitsPerComponent(int i);

    void setColorSpace(PDColorSpace pDColorSpace);

    void setDecode(COSArray cOSArray);

    void setHeight(int i);

    void setInterpolate(boolean z);

    void setStencil(boolean z);

    void setWidth(int i);
}
