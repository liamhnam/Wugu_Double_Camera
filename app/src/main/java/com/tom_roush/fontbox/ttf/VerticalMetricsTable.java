package com.tom_roush.fontbox.ttf;

import java.io.IOException;

public class VerticalMetricsTable extends TTFTable {
    public static final String TAG = "vmtx";
    private int[] advanceHeight;
    private short[] nonVerticalTopSideBearing;
    private int numVMetrics;
    private short[] topSideBearing;

    VerticalMetricsTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.numVMetrics = trueTypeFont.getVerticalHeader().getNumberOfVMetrics();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        int i = this.numVMetrics;
        this.advanceHeight = new int[i];
        this.topSideBearing = new short[i];
        int i2 = 0;
        for (int i3 = 0; i3 < this.numVMetrics; i3++) {
            this.advanceHeight[i3] = tTFDataStream.readUnsignedShort();
            this.topSideBearing[i3] = tTFDataStream.readSignedShort();
            i2 += 4;
        }
        if (i2 < getLength()) {
            int i4 = numberOfGlyphs - this.numVMetrics;
            if (i4 >= 0) {
                numberOfGlyphs = i4;
            }
            this.nonVerticalTopSideBearing = new short[numberOfGlyphs];
            for (int i5 = 0; i5 < numberOfGlyphs; i5++) {
                if (i2 < getLength()) {
                    this.nonVerticalTopSideBearing[i5] = tTFDataStream.readSignedShort();
                    i2 += 2;
                }
            }
        }
        this.initialized = true;
    }

    public int getAdvanceHeight(int i) {
        if (i < this.numVMetrics) {
            return this.advanceHeight[i];
        }
        return this.advanceHeight[r2.length - 1];
    }
}
