package com.tom_roush.pdfbox.util;

import com.google.android.exoplayer2.C1041C;
import java.nio.charset.Charset;

public final class Charsets {
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName(C1041C.UTF16LE_NAME);
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Charsets() {
    }
}
