package com.tom_roush.fontbox.ttf;

import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TTFParser {
    private boolean isEmbedded;
    private boolean parseOnDemandOnly;

    public TTFParser() {
        this(false);
    }

    public TTFParser(boolean z) {
        this(z, false);
    }

    public TTFParser(boolean z, boolean z2) {
        this.isEmbedded = z;
        this.parseOnDemandOnly = z2;
    }

    public TrueTypeFont parse(String str) throws IOException {
        return parse(new File(str));
    }

    public TrueTypeFont parse(File file) throws IOException {
        RAFDataStream rAFDataStream = new RAFDataStream(file, PDPageLabelRange.STYLE_ROMAN_LOWER);
        try {
            return parse(rAFDataStream);
        } catch (IOException e) {
            rAFDataStream.close();
            throw e;
        }
    }

    public TrueTypeFont parse(InputStream inputStream) throws IOException {
        return parse(new MemoryTTFDataStream(inputStream));
    }

    public TrueTypeFont parseEmbedded(InputStream inputStream) throws IOException {
        this.isEmbedded = true;
        return parse(new MemoryTTFDataStream(inputStream));
    }

    TrueTypeFont parse(TTFDataStream tTFDataStream) throws IOException {
        TrueTypeFont trueTypeFontNewFont = newFont(tTFDataStream);
        trueTypeFontNewFont.setVersion(tTFDataStream.read32Fixed());
        int unsignedShort = tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        for (int i = 0; i < unsignedShort; i++) {
            TTFTable tableDirectory = readTableDirectory(trueTypeFontNewFont, tTFDataStream);
            if (tableDirectory != null) {
                trueTypeFontNewFont.addTable(tableDirectory);
            }
        }
        if (!this.parseOnDemandOnly) {
            parseTables(trueTypeFontNewFont, tTFDataStream);
        }
        return trueTypeFontNewFont;
    }

    TrueTypeFont newFont(TTFDataStream tTFDataStream) {
        return new TrueTypeFont(tTFDataStream);
    }

    private void parseTables(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        for (TTFTable tTFTable : trueTypeFont.getTables()) {
            if (!tTFTable.getInitialized()) {
                trueTypeFont.readTable(tTFTable);
            }
        }
        if (trueTypeFont.getHeader() == null) {
            throw new IOException("head is mandatory");
        }
        if (trueTypeFont.getHorizontalHeader() == null) {
            throw new IOException("hhead is mandatory");
        }
        if (trueTypeFont.getMaximumProfile() == null) {
            throw new IOException("maxp is mandatory");
        }
        if (trueTypeFont.getPostScript() == null && !this.isEmbedded) {
            throw new IOException("post is mandatory");
        }
        if (trueTypeFont.getIndexToLocation() == null) {
            throw new IOException("loca is mandatory");
        }
        if (trueTypeFont.getGlyph() == null) {
            throw new IOException("glyf is mandatory");
        }
        if (trueTypeFont.getNaming() == null && !this.isEmbedded) {
            throw new IOException("name is mandatory");
        }
        if (trueTypeFont.getHorizontalMetrics() == null) {
            throw new IOException("hmtx is mandatory");
        }
        if (!this.isEmbedded && trueTypeFont.getCmap() == null) {
            throw new IOException("cmap is mandatory");
        }
    }

    private TTFTable readTableDirectory(TrueTypeFont trueTypeFont, TTFDataStream tTFDataStream) throws IOException {
        TTFTable table;
        String string = tTFDataStream.readString(4);
        if (string.equals(CmapTable.TAG)) {
            table = new CmapTable(trueTypeFont);
        } else if (string.equals(GlyphTable.TAG)) {
            table = new GlyphTable(trueTypeFont);
        } else if (string.equals("head")) {
            table = new HeaderTable(trueTypeFont);
        } else if (string.equals(HorizontalHeaderTable.TAG)) {
            table = new HorizontalHeaderTable(trueTypeFont);
        } else if (string.equals(HorizontalMetricsTable.TAG)) {
            table = new HorizontalMetricsTable(trueTypeFont);
        } else if (string.equals(IndexToLocationTable.TAG)) {
            table = new IndexToLocationTable(trueTypeFont);
        } else if (string.equals(MaximumProfileTable.TAG)) {
            table = new MaximumProfileTable(trueTypeFont);
        } else if (string.equals(NamingTable.TAG)) {
            table = new NamingTable(trueTypeFont);
        } else if (string.equals(OS2WindowsMetricsTable.TAG)) {
            table = new OS2WindowsMetricsTable(trueTypeFont);
        } else if (string.equals(PostScriptTable.TAG)) {
            table = new PostScriptTable(trueTypeFont);
        } else if (string.equals(DigitalSignatureTable.TAG)) {
            table = new DigitalSignatureTable(trueTypeFont);
        } else if (string.equals(KerningTable.TAG)) {
            table = new KerningTable(trueTypeFont);
        } else if (string.equals(VerticalHeaderTable.TAG)) {
            table = new VerticalHeaderTable(trueTypeFont);
        } else if (string.equals(VerticalMetricsTable.TAG)) {
            table = new VerticalMetricsTable(trueTypeFont);
        } else if (string.equals(VerticalOriginTable.TAG)) {
            table = new VerticalOriginTable(trueTypeFont);
        } else {
            table = readTable(trueTypeFont, string);
        }
        table.setTag(string);
        table.setCheckSum(tTFDataStream.readUnsignedInt());
        table.setOffset(tTFDataStream.readUnsignedInt());
        table.setLength(tTFDataStream.readUnsignedInt());
        if (table.getLength() == 0) {
            return null;
        }
        return table;
    }

    protected TTFTable readTable(TrueTypeFont trueTypeFont, String str) {
        return new TTFTable(trueTypeFont);
    }
}
