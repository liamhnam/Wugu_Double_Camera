package com.tom_roush.fontbox;

import com.tom_roush.fontbox.encoding.Encoding;
import java.io.IOException;

public interface EncodedFont {
    Encoding getEncoding() throws IOException;
}
