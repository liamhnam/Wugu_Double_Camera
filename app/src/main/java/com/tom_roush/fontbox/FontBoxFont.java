package com.tom_roush.fontbox;

import android.graphics.Path;
import com.tom_roush.fontbox.util.BoundingBox;
import java.io.IOException;
import java.util.List;

public interface FontBoxFont {
    BoundingBox getFontBBox() throws IOException;

    List<Number> getFontMatrix() throws IOException;

    String getName() throws IOException;

    Path getPath(String str) throws IOException;

    float getWidth(String str) throws IOException;

    boolean hasGlyph(String str) throws IOException;
}
