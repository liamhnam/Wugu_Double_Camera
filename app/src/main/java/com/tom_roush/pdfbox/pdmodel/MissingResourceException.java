package com.tom_roush.pdfbox.pdmodel;

import java.io.IOException;

public final class MissingResourceException extends IOException {
    public MissingResourceException(String str) {
        super(str);
    }
}
