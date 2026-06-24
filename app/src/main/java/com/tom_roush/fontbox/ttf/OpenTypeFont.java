package com.tom_roush.fontbox.ttf;

import android.graphics.Path;
import java.io.IOException;

public class OpenTypeFont extends TrueTypeFont {
    private boolean isPostScript;

    OpenTypeFont(TTFDataStream tTFDataStream) {
        super(tTFDataStream);
    }

    @Override
    void setVersion(float f) {
        this.isPostScript = ((double) f) != 1.0d;
        super.setVersion(f);
    }

    public synchronized CFFTable getCFF() throws IOException {
        CFFTable cFFTable;
        if (!this.isPostScript) {
            throw new UnsupportedOperationException("TTF fonts do not have a CFF table");
        }
        cFFTable = (CFFTable) this.tables.get(CFFTable.TAG);
        if (cFFTable != null && !cFFTable.getInitialized()) {
            readTable(cFFTable);
        }
        return cFFTable;
    }

    @Override
    public synchronized GlyphTable getGlyph() throws IOException {
        if (this.isPostScript) {
            throw new UnsupportedOperationException("OTF fonts do not have a glyf table");
        }
        return super.getGlyph();
    }

    @Override
    public Path getPath(String str) throws IOException {
        return getCFF().getFont().getType2CharString(nameToGID(str)).getPath();
    }

    public boolean isPostScript() {
        return this.tables.containsKey(CFFTable.TAG);
    }

    public boolean hasLayoutTables() {
        return this.tables.containsKey("BASE") || this.tables.containsKey("GDEF") || this.tables.containsKey("GPOS") || this.tables.containsKey("GSUB") || this.tables.containsKey("JSTF");
    }
}
