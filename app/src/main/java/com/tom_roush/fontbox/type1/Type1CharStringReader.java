package com.tom_roush.fontbox.type1;

import com.tom_roush.fontbox.cff.Type1CharString;
import java.io.IOException;

public interface Type1CharStringReader {
    Type1CharString getType1CharString(String str) throws IOException;
}
