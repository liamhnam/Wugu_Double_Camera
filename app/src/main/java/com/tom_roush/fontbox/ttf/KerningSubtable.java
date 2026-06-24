package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class KerningSubtable {
    private static final int COVERAGE_CROSS_STREAM = 4;
    private static final int COVERAGE_CROSS_STREAM_SHIFT = 2;
    private static final int COVERAGE_FORMAT = 65280;
    private static final int COVERAGE_FORMAT_SHIFT = 8;
    private static final int COVERAGE_HORIZONTAL = 1;
    private static final int COVERAGE_HORIZONTAL_SHIFT = 0;
    private static final int COVERAGE_MINIMUMS = 2;
    private static final int COVERAGE_MINIMUMS_SHIFT = 1;
    private boolean crossStream;
    private boolean horizontal;
    private boolean minimums;
    private PairData pairs;

    private static int getBits(int i, int i2, int i3) {
        return (i & i2) >> i3;
    }

    KerningSubtable() {
    }

    public void read(TTFDataStream tTFDataStream, int i) throws IOException {
        if (i == 0) {
            readSubtable0(tTFDataStream);
        } else {
            if (i == 1) {
                readSubtable1(tTFDataStream);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public boolean isHorizontalKerning() {
        return isHorizontalKerning(false);
    }

    public boolean isHorizontalKerning(boolean z) {
        if (!this.horizontal || this.minimums) {
            return false;
        }
        if (z) {
            return this.crossStream;
        }
        return !this.crossStream;
    }

    public int[] getKerning(int[] iArr) {
        int i;
        if (this.pairs != null) {
            int length = iArr.length;
            int[] iArr2 = new int[length];
            int i2 = 0;
            while (i2 < length) {
                int i3 = iArr[i2];
                int i4 = i2 + 1;
                int i5 = i4;
                while (true) {
                    if (i5 >= length) {
                        i = -1;
                        break;
                    }
                    i = iArr[i5];
                    if (i >= 0) {
                        break;
                    }
                    i5++;
                }
                iArr2[i2] = getKerning(i3, i);
                i2 = i4;
            }
            return iArr2;
        }
        Log.w("PdfBox-Android", "No kerning subtable data available due to an unsupported kerning subtable version");
        return null;
    }

    public int getKerning(int i, int i2) {
        PairData pairData = this.pairs;
        if (pairData == null) {
            Log.w("PdfBox-Android", "No kerning subtable data available due to an unsupported kerning subtable version");
            return 0;
        }
        return pairData.getKerning(i, i2);
    }

    private void readSubtable0(TTFDataStream tTFDataStream) throws IOException {
        int unsignedShort = tTFDataStream.readUnsignedShort();
        if (unsignedShort != 0) {
            throw new UnsupportedOperationException("Unsupported kerning sub-table version: " + unsignedShort);
        }
        int unsignedShort2 = tTFDataStream.readUnsignedShort();
        if (unsignedShort2 < 6) {
            throw new IOException("Kerning sub-table too short, got " + unsignedShort2 + " bytes, expect 6 or more.");
        }
        int unsignedShort3 = tTFDataStream.readUnsignedShort();
        if (isBitsSet(unsignedShort3, 1, 0)) {
            this.horizontal = true;
        }
        if (isBitsSet(unsignedShort3, 2, 1)) {
            this.minimums = true;
        }
        if (isBitsSet(unsignedShort3, 4, 2)) {
            this.crossStream = true;
        }
        int bits = getBits(unsignedShort3, 65280, 8);
        if (bits == 0) {
            readSubtable0Format0(tTFDataStream);
        } else if (bits == 2) {
            readSubtable0Format2(tTFDataStream);
        } else {
            Log.d("PdfBox-Android", "Skipped kerning subtable due to an unsupported kerning subtable version: " + bits);
        }
    }

    private void readSubtable0Format0(TTFDataStream tTFDataStream) throws IOException {
        PairData0Format0 pairData0Format0 = new PairData0Format0();
        this.pairs = pairData0Format0;
        pairData0Format0.read(tTFDataStream);
    }

    private void readSubtable0Format2(TTFDataStream tTFDataStream) throws IOException {
        throw new UnsupportedOperationException("Kerning table version 0 format 2 not yet supported.");
    }

    private void readSubtable1(TTFDataStream tTFDataStream) throws IOException {
        throw new UnsupportedOperationException("Kerning table version 1 formats not yet supported.");
    }

    private static boolean isBitsSet(int i, int i2, int i3) {
        return getBits(i, i2, i3) != 0;
    }

    private static abstract class PairData {
        public abstract int getKerning(int i, int i2);

        public abstract void read(TTFDataStream tTFDataStream) throws IOException;

        private PairData() {
        }
    }

    private static class PairData0Format0 extends PairData implements Comparator<int[]> {
        static final boolean $assertionsDisabled = false;
        private int[][] pairs;
        private int searchRange;

        private PairData0Format0() {
            super();
        }

        @Override
        public void read(TTFDataStream tTFDataStream) throws IOException {
            int unsignedShort = tTFDataStream.readUnsignedShort();
            this.searchRange = tTFDataStream.readUnsignedShort() / 6;
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
            this.pairs = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, unsignedShort, 3);
            for (int i = 0; i < unsignedShort; i++) {
                int unsignedShort2 = tTFDataStream.readUnsignedShort();
                int unsignedShort3 = tTFDataStream.readUnsignedShort();
                short signedShort = tTFDataStream.readSignedShort();
                int[] iArr = this.pairs[i];
                iArr[0] = unsignedShort2;
                iArr[1] = unsignedShort3;
                iArr[2] = signedShort;
            }
        }

        @Override
        public int getKerning(int i, int i2) {
            int[] iArr = {i, i2, 0};
            int iBinarySearch = Arrays.binarySearch(this.pairs, 0, this.searchRange, iArr, this);
            if (iBinarySearch >= 0) {
                return this.pairs[iBinarySearch][2];
            }
            int[][] iArr2 = this.pairs;
            int iBinarySearch2 = Arrays.binarySearch(iArr2, this.searchRange, iArr2.length, iArr, this);
            if (iBinarySearch2 >= 0) {
                return this.pairs[this.searchRange + iBinarySearch2][2];
            }
            return 0;
        }

        @Override
        public int compare(int[] iArr, int[] iArr2) {
            int i = iArr[0];
            int i2 = iArr2[0];
            if (i < i2) {
                return -1;
            }
            if (i > i2) {
                return 1;
            }
            int i3 = iArr[1];
            int i4 = iArr2[1];
            if (i3 < i4) {
                return -1;
            }
            return i3 > i4 ? 1 : 0;
        }
    }
}
