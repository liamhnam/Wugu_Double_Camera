package com.tom_roush.pdfbox.filter;

import java.io.IOException;

public class MissingImageReaderException extends IOException {
    private static final long serialVersionUID = 1;

    public MissingImageReaderException(String str) {
        super(str);
    }
}
