package com.tom_roush.pdfbox.filter;

public final class Predictor {
    static int calcSetBitSeq(int i, int i2, int i3, int i4) {
        int i5 = (1 << i3) - 1;
        return (i & (~(i5 << i2))) | ((i4 & i5) << i2);
    }

    static int getBitSeq(int i, int i2, int i3) {
        return (i >>> i2) & ((1 << i3) - 1);
    }

    private Predictor() {
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void decodePredictor(int r19, int r20, int r21, int r22, java.io.InputStream r23, java.io.OutputStream r24) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.Predictor.decodePredictor(int, int, int, int, java.io.InputStream, java.io.OutputStream):void");
    }
}
