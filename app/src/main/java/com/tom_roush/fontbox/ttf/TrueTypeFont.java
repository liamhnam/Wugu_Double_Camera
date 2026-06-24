package com.tom_roush.fontbox.ttf;

import android.graphics.Path;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.tom_roush.fontbox.FontBoxFont;
import com.tom_roush.fontbox.util.BoundingBox;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrueTypeFont implements FontBoxFont, Closeable {
    private final TTFDataStream data;
    private Map<String, Integer> postScriptNames;
    private float version;
    private int numberOfGlyphs = -1;
    private int unitsPerEm = -1;
    protected Map<String, TTFTable> tables = new HashMap();

    TrueTypeFont(TTFDataStream tTFDataStream) {
        this.data = tTFDataStream;
    }

    @Override
    public void close() throws IOException {
        this.data.close();
    }

    public float getVersion() {
        return this.version;
    }

    void setVersion(float f) {
        this.version = f;
    }

    void addTable(TTFTable tTFTable) {
        this.tables.put(tTFTable.getTag(), tTFTable);
    }

    public Collection<TTFTable> getTables() {
        return this.tables.values();
    }

    public Map<String, TTFTable> getTableMap() {
        return this.tables;
    }

    public synchronized byte[] getTableBytes(TTFTable tTFTable) throws IOException {
        byte[] bArr;
        long currentPosition = this.data.getCurrentPosition();
        this.data.seek(tTFTable.getOffset());
        bArr = this.data.read((int) tTFTable.getLength());
        this.data.seek(currentPosition);
        return bArr;
    }

    public synchronized NamingTable getNaming() throws IOException {
        NamingTable namingTable;
        namingTable = (NamingTable) this.tables.get(NamingTable.TAG);
        if (namingTable != null && !namingTable.getInitialized()) {
            readTable(namingTable);
        }
        return namingTable;
    }

    public synchronized PostScriptTable getPostScript() throws IOException {
        PostScriptTable postScriptTable;
        postScriptTable = (PostScriptTable) this.tables.get(PostScriptTable.TAG);
        if (postScriptTable != null && !postScriptTable.getInitialized()) {
            readTable(postScriptTable);
        }
        return postScriptTable;
    }

    public synchronized OS2WindowsMetricsTable getOS2Windows() throws IOException {
        OS2WindowsMetricsTable oS2WindowsMetricsTable;
        oS2WindowsMetricsTable = (OS2WindowsMetricsTable) this.tables.get(OS2WindowsMetricsTable.TAG);
        if (oS2WindowsMetricsTable != null && !oS2WindowsMetricsTable.getInitialized()) {
            readTable(oS2WindowsMetricsTable);
        }
        return oS2WindowsMetricsTable;
    }

    public synchronized MaximumProfileTable getMaximumProfile() throws IOException {
        MaximumProfileTable maximumProfileTable;
        maximumProfileTable = (MaximumProfileTable) this.tables.get(MaximumProfileTable.TAG);
        if (maximumProfileTable != null && !maximumProfileTable.getInitialized()) {
            readTable(maximumProfileTable);
        }
        return maximumProfileTable;
    }

    public synchronized HeaderTable getHeader() throws IOException {
        HeaderTable headerTable;
        headerTable = (HeaderTable) this.tables.get("head");
        if (headerTable != null && !headerTable.getInitialized()) {
            readTable(headerTable);
        }
        return headerTable;
    }

    public synchronized HorizontalHeaderTable getHorizontalHeader() throws IOException {
        HorizontalHeaderTable horizontalHeaderTable;
        horizontalHeaderTable = (HorizontalHeaderTable) this.tables.get(HorizontalHeaderTable.TAG);
        if (horizontalHeaderTable != null && !horizontalHeaderTable.getInitialized()) {
            readTable(horizontalHeaderTable);
        }
        return horizontalHeaderTable;
    }

    public synchronized HorizontalMetricsTable getHorizontalMetrics() throws IOException {
        HorizontalMetricsTable horizontalMetricsTable;
        horizontalMetricsTable = (HorizontalMetricsTable) this.tables.get(HorizontalMetricsTable.TAG);
        if (horizontalMetricsTable != null && !horizontalMetricsTable.getInitialized()) {
            readTable(horizontalMetricsTable);
        }
        return horizontalMetricsTable;
    }

    public synchronized IndexToLocationTable getIndexToLocation() throws IOException {
        IndexToLocationTable indexToLocationTable;
        indexToLocationTable = (IndexToLocationTable) this.tables.get(IndexToLocationTable.TAG);
        if (indexToLocationTable != null && !indexToLocationTable.getInitialized()) {
            readTable(indexToLocationTable);
        }
        return indexToLocationTable;
    }

    public synchronized GlyphTable getGlyph() throws IOException {
        GlyphTable glyphTable;
        glyphTable = (GlyphTable) this.tables.get(GlyphTable.TAG);
        if (glyphTable != null && !glyphTable.getInitialized()) {
            readTable(glyphTable);
        }
        return glyphTable;
    }

    public synchronized CmapTable getCmap() throws IOException {
        CmapTable cmapTable;
        cmapTable = (CmapTable) this.tables.get(CmapTable.TAG);
        if (cmapTable != null && !cmapTable.getInitialized()) {
            readTable(cmapTable);
        }
        return cmapTable;
    }

    public synchronized VerticalHeaderTable getVerticalHeader() throws IOException {
        VerticalHeaderTable verticalHeaderTable;
        verticalHeaderTable = (VerticalHeaderTable) this.tables.get(VerticalHeaderTable.TAG);
        if (verticalHeaderTable != null && !verticalHeaderTable.getInitialized()) {
            readTable(verticalHeaderTable);
        }
        return verticalHeaderTable;
    }

    public synchronized VerticalMetricsTable getVerticalMetrics() throws IOException {
        VerticalMetricsTable verticalMetricsTable;
        verticalMetricsTable = (VerticalMetricsTable) this.tables.get(VerticalMetricsTable.TAG);
        if (verticalMetricsTable != null && !verticalMetricsTable.getInitialized()) {
            readTable(verticalMetricsTable);
        }
        return verticalMetricsTable;
    }

    public synchronized VerticalOriginTable getVerticalOrigin() throws IOException {
        VerticalOriginTable verticalOriginTable;
        verticalOriginTable = (VerticalOriginTable) this.tables.get(VerticalOriginTable.TAG);
        if (verticalOriginTable != null && !verticalOriginTable.getInitialized()) {
            readTable(verticalOriginTable);
        }
        return verticalOriginTable;
    }

    public synchronized KerningTable getKerning() throws IOException {
        KerningTable kerningTable;
        kerningTable = (KerningTable) this.tables.get(KerningTable.TAG);
        if (kerningTable != null && !kerningTable.getInitialized()) {
            readTable(kerningTable);
        }
        return kerningTable;
    }

    public InputStream getOriginalData() throws IOException {
        return this.data.getOriginalData();
    }

    void readTable(TTFTable tTFTable) throws IOException {
        long currentPosition = this.data.getCurrentPosition();
        this.data.seek(tTFTable.getOffset());
        tTFTable.read(this, this.data);
        this.data.seek(currentPosition);
    }

    public int getNumberOfGlyphs() throws IOException {
        if (this.numberOfGlyphs == -1) {
            MaximumProfileTable maximumProfile = getMaximumProfile();
            if (maximumProfile != null) {
                this.numberOfGlyphs = maximumProfile.getNumGlyphs();
            } else {
                this.numberOfGlyphs = 0;
            }
        }
        return this.numberOfGlyphs;
    }

    public int getUnitsPerEm() throws IOException {
        if (this.unitsPerEm == -1) {
            HeaderTable header = getHeader();
            if (header != null) {
                this.unitsPerEm = header.getUnitsPerEm();
            } else {
                this.unitsPerEm = 0;
            }
        }
        return this.unitsPerEm;
    }

    public int getAdvanceWidth(int i) throws IOException {
        HorizontalMetricsTable horizontalMetrics = getHorizontalMetrics();
        return horizontalMetrics != null ? horizontalMetrics.getAdvanceWidth(i) : ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    }

    public int getAdvanceHeight(int i) throws IOException {
        VerticalMetricsTable verticalMetrics = getVerticalMetrics();
        return verticalMetrics != null ? verticalMetrics.getAdvanceHeight(i) : ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    }

    @Override
    public String getName() throws IOException {
        if (getNaming() != null) {
            return getNaming().getPostScriptName();
        }
        return null;
    }

    private synchronized void readPostScriptNames() throws IOException {
        if (this.postScriptNames == null && getPostScript() != null) {
            String[] glyphNames = getPostScript().getGlyphNames();
            if (glyphNames != null) {
                this.postScriptNames = new HashMap(glyphNames.length);
                for (int i = 0; i < glyphNames.length; i++) {
                    this.postScriptNames.put(glyphNames[i], Integer.valueOf(i));
                }
            } else {
                this.postScriptNames = new HashMap();
            }
        }
    }

    public CmapSubtable getUnicodeCmap() throws IOException {
        return getUnicodeCmap(true);
    }

    public CmapSubtable getUnicodeCmap(boolean z) throws IOException {
        CmapTable cmap = getCmap();
        if (cmap == null) {
            if (z) {
                throw new IOException("The TrueType font does not contain a 'cmap' table");
            }
            return null;
        }
        CmapSubtable subtable = cmap.getSubtable(0, 4);
        if (subtable == null) {
            subtable = cmap.getSubtable(0, 3);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 1);
        }
        if (subtable == null) {
            subtable = cmap.getSubtable(3, 0);
        }
        if (subtable != null) {
            return subtable;
        }
        if (z) {
            throw new IOException("The TrueType font does not contain a Unicode cmap");
        }
        return cmap.getCmaps()[0];
    }

    public int nameToGID(String str) throws IOException {
        readPostScriptNames();
        Integer num = this.postScriptNames.get(str);
        if (num != null && num.intValue() > 0 && num.intValue() < getMaximumProfile().getNumGlyphs()) {
            return num.intValue();
        }
        int uniName = parseUniName(str);
        if (uniName > -1) {
            return getUnicodeCmap(false).getGlyphId(uniName);
        }
        return 0;
    }

    private int parseUniName(String str) throws IOException {
        if (str.startsWith("uni") && str.length() == 7) {
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            int i = 3;
            while (true) {
                int i2 = i + 4;
                if (i2 > length) {
                    break;
                }
                try {
                    int i3 = Integer.parseInt(str.substring(i, i2), 16);
                    if (i3 <= 55295 || i3 >= 57344) {
                        sb.append((char) i3);
                    }
                    i = i2;
                } catch (NumberFormatException unused) {
                }
            }
            String string = sb.toString();
            if (string.length() == 0) {
                return -1;
            }
            return string.codePointAt(0);
        }
        return -1;
    }

    @Override
    public Path getPath(String str) throws IOException {
        GlyphData glyph = getGlyph().getGlyph(nameToGID(str));
        if (glyph == null) {
            return new Path();
        }
        return glyph.getPath();
    }

    @Override
    public float getWidth(String str) throws IOException {
        return getAdvanceWidth(Integer.valueOf(nameToGID(str)).intValue());
    }

    @Override
    public boolean hasGlyph(String str) throws IOException {
        return nameToGID(str) != 0;
    }

    @Override
    public BoundingBox getFontBBox() throws IOException {
        short xMin = getHeader().getXMin();
        short xMax = getHeader().getXMax();
        float unitsPerEm = 1000.0f / getUnitsPerEm();
        return new BoundingBox(xMin * unitsPerEm, getHeader().getYMin() * unitsPerEm, xMax * unitsPerEm, getHeader().getYMax() * unitsPerEm);
    }

    @Override
    public List<Number> getFontMatrix() throws IOException {
        float unitsPerEm = (1000.0f / getUnitsPerEm()) * 0.001f;
        return Arrays.asList(Float.valueOf(unitsPerEm), 0, 0, Float.valueOf(unitsPerEm), 0, 0);
    }

    public String toString() {
        try {
            return getNaming() != null ? getNaming().getPostScriptName() : "(null)";
        } catch (IOException e) {
            return "(null - " + e.getMessage() + ")";
        }
    }
}
