package com.tom_roush.fontbox.ttf;

import com.tom_roush.fontbox.cff.CFFFont;
import com.tom_roush.fontbox.cff.CFFParser;
import java.io.IOException;

public class CFFTable extends TTFTable {
    public static final String TAG = "CFF ";
    private CFFFont cffFont;

    CFFTable(TrueTypeFont trueTypeFont) {
        super(trueTypeFont);
    }

    @Override
    public void read(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        this.cffFont = new CFFParser().parse(tTFDataStream.read((int) getLength()), new ByteSource(this.font)).get(0);
        this.initialized = true;
    }

    public CFFFont getFont() {
        return this.cffFont;
    }

    private static class ByteSource implements CFFParser.ByteSource {
        private final TrueTypeFont ttf;

        ByteSource(TrueTypeFont trueTypeFont) {
            this.ttf = trueTypeFont;
        }

        @Override
        public byte[] getBytes() throws IOException {
            TrueTypeFont trueTypeFont = this.ttf;
            return trueTypeFont.getTableBytes(trueTypeFont.getTableMap().get(CFFTable.TAG));
        }
    }
}
