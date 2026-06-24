package com.tom_roush.fontbox.ttf;

import java.io.IOException;

public class TTFTable {
    private long checkSum;
    protected final TrueTypeFont font;
    protected boolean initialized;
    private long length;
    private long offset;
    private String tag;

    void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
    }

    TTFTable(TrueTypeFont trueTypeFont) {
        this.font = trueTypeFont;
    }

    public long getCheckSum() {
        return this.checkSum;
    }

    void setCheckSum(long j) {
        this.checkSum = j;
    }

    public long getLength() {
        return this.length;
    }

    void setLength(long j) {
        this.length = j;
    }

    public long getOffset() {
        return this.offset;
    }

    void setOffset(long j) {
        this.offset = j;
    }

    public String getTag() {
        return this.tag;
    }

    void setTag(String str) {
        this.tag = str;
    }

    public boolean getInitialized() {
        return this.initialized;
    }
}
