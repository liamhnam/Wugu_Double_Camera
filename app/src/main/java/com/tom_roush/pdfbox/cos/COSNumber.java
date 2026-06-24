package com.tom_roush.pdfbox.cos;

import java.io.IOException;

public abstract class COSNumber extends COSBase {

    @Deprecated
    public static final COSInteger ZERO = COSInteger.ZERO;

    @Deprecated
    public static final COSInteger ONE = COSInteger.ONE;

    public abstract double doubleValue();

    public abstract float floatValue();

    public abstract int intValue();

    public abstract long longValue();

    public static COSNumber get(String str) throws IOException {
        if (str.length() == 1) {
            char cCharAt = str.charAt(0);
            if ('0' <= cCharAt && cCharAt <= '9') {
                return COSInteger.get(cCharAt - '0');
            }
            if (cCharAt == '-' || cCharAt == '.') {
                return COSInteger.ZERO;
            }
            throw new IOException("Not a number: " + str);
        }
        if (str.indexOf(46) == -1 && str.toLowerCase().indexOf(101) == -1) {
            try {
                if (str.charAt(0) == '+') {
                    return COSInteger.get(Long.parseLong(str.substring(1)));
                }
                return COSInteger.get(Long.parseLong(str));
            } catch (NumberFormatException unused) {
                return new COSFloat(str);
            }
        }
        return new COSFloat(str);
    }
}
