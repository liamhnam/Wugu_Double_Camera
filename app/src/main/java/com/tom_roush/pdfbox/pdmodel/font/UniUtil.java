package com.tom_roush.pdfbox.pdmodel.font;

import java.util.Locale;

final class UniUtil {
    private UniUtil() {
    }

    static String getUniNameOfCodePoint(int i) {
        String upperCase = Integer.toString(i, 16).toUpperCase(Locale.US);
        int length = upperCase.length();
        if (length == 1) {
            return "uni000" + upperCase;
        }
        if (length == 2) {
            return "uni00" + upperCase;
        }
        if (length == 3) {
            return "uni0" + upperCase;
        }
        return "uni" + upperCase;
    }
}
