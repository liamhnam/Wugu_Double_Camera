package com.tom_roush.fontbox.type1;

import java.io.IOException;

public class DamagedFontException extends IOException {
    public DamagedFontException(String str) {
        super(str);
    }
}
