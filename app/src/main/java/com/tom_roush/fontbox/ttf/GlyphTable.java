package com.tom_roush.fontbox.ttf;

import java.io.IOException;

public class GlyphTable extends TTFTable {
    private static final int MAX_CACHED_GLYPHS = 100;
    private static final int MAX_CACHE_SIZE = 5000;
    public static final String TAG = "glyf";
    private int cached;
    private TTFDataStream data;
    private GlyphData[] glyphs;
    private IndexToLocationTable loca;
    private int numGlyphs;

    GlyphTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
        this.cached = 0;
    }

    @Override
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.loca = trueTypeFont.getIndexToLocation();
        int numberOfGlyphs = trueTypeFont.getNumberOfGlyphs();
        this.numGlyphs = numberOfGlyphs;
        if (numberOfGlyphs < 5000) {
            this.glyphs = new GlyphData[numberOfGlyphs];
        }
        this.data = tTFDataStream;
        this.initialized = true;
    }

    public GlyphData[] getGlyphs() throws IOException {
        GlyphData[] glyphDataArr;
        synchronized (this.font) {
            long[] offsets = this.loca.getOffsets();
            long j = offsets[this.numGlyphs];
            long offset = getOffset();
            if (this.glyphs == null) {
                this.glyphs = new GlyphData[this.numGlyphs];
            }
            int i = 0;
            while (i < this.numGlyphs && (j == 0 || j != offsets[i])) {
                int i2 = i + 1;
                long j2 = offsets[i2];
                long j3 = offsets[i];
                if (j2 > j3 && this.glyphs[i] == null) {
                    this.data.seek(j3 + offset);
                    GlyphData[] glyphDataArr2 = this.glyphs;
                    if (glyphDataArr2[i] == null) {
                        this.cached++;
                    }
                    glyphDataArr2[i] = getGlyphData(i);
                }
                i = i2;
            }
            this.initialized = true;
            glyphDataArr = this.glyphs;
        }
        return glyphDataArr;
    }

    public void setGlyphs(GlyphData[] glyphDataArr) {
        this.glyphs = glyphDataArr;
    }

    public GlyphData getGlyph(int i) throws IOException {
        int i2;
        GlyphData glyphData;
        if (i < 0 || i >= this.numGlyphs) {
            return null;
        }
        GlyphData[] glyphDataArr = this.glyphs;
        if (glyphDataArr != null && (glyphData = glyphDataArr[i]) != null) {
            return glyphData;
        }
        synchronized (this.font) {
            long[] offsets = this.loca.getOffsets();
            if (offsets[i] == offsets[i + 1]) {
                return null;
            }
            long currentPosition = this.data.getCurrentPosition();
            this.data.seek(getOffset() + offsets[i]);
            GlyphData glyphData2 = getGlyphData(i);
            this.data.seek(currentPosition);
            GlyphData[] glyphDataArr2 = this.glyphs;
            if (glyphDataArr2 != null && glyphDataArr2[i] == null && (i2 = this.cached) < 100) {
                glyphDataArr2[i] = glyphData2;
                this.cached = i2 + 1;
            }
            return glyphData2;
        }
    }

    private GlyphData getGlyphData(int i) throws IOException {
        GlyphData glyphData = new GlyphData();
        HorizontalMetricsTable horizontalMetrics = this.font.getHorizontalMetrics();
        glyphData.initData(this, this.data, horizontalMetrics == null ? 0 : horizontalMetrics.getLeftSideBearing(i));
        if (glyphData.getDescription().isComposite()) {
            glyphData.getDescription().resolve();
        }
        return glyphData;
    }
}
