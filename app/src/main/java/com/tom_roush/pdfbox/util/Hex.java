package com.tom_roush.pdfbox.util;

import kotlin.UByte;

public final class Hex {
    private Hex() {
    }

    public static String getString(byte b) {
        return Integer.toHexString((b & UByte.MAX_VALUE) | 256).substring(1).toUpperCase();
    }

    public static byte[] getBytes(byte b) {
        return getString(b).getBytes(Charsets.US_ASCII);
    }
}
