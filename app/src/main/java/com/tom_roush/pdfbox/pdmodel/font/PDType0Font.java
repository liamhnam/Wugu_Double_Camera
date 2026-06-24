package com.tom_roush.pdfbox.pdmodel.font;

import android.graphics.Path;
import android.util.Log;
import com.tom_roush.fontbox.cmap.CMap;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.fontbox.util.BoundingBox;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.util.Matrix;
import com.tom_roush.pdfbox.util.Vector;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PDType0Font extends PDFont implements PDVectorFont {
    private CMap cMap;
    private CMap cMapUCS2;
    private final PDCIDFont descendantFont;
    private PDCIDFontType2Embedder embedder;
    private boolean isCMapPredefined;
    private boolean isDescendantCJK;
    private final Set<Integer> noUnicode;

    @Override
    public boolean isStandard14() {
        return false;
    }

    public static PDType0Font load(PDDocument pDDocument, File file) throws IOException {
        return new PDType0Font(pDDocument, new TTFParser().parse(file), true);
    }

    public static PDType0Font load(PDDocument pDDocument, InputStream inputStream) throws IOException {
        return new PDType0Font(pDDocument, new TTFParser().parse(inputStream), true);
    }

    public static PDType0Font load(PDDocument pDDocument, InputStream inputStream, boolean z) throws IOException {
        return new PDType0Font(pDDocument, new TTFParser().parse(inputStream), z);
    }

    public static PDType0Font load(PDDocument pDDocument, TrueTypeFont trueTypeFont, boolean z) throws IOException {
        return new PDType0Font(pDDocument, trueTypeFont, z);
    }

    public PDType0Font(COSDictionary cOSDictionary) throws Throwable {
        super(cOSDictionary);
        this.noUnicode = new HashSet();
        COSDictionary cOSDictionary2 = (COSDictionary) ((COSArray) this.dict.getDictionaryObject(COSName.DESCENDANT_FONTS)).getObject(0);
        if (cOSDictionary2 == null) {
            throw new IOException("Missing descendant font dictionary");
        }
        this.descendantFont = PDFontFactory.createDescendantFont(cOSDictionary2, this);
        readEncoding();
        fetchCMapUCS2();
    }

    private PDType0Font(PDDocument pDDocument, TrueTypeFont trueTypeFont, boolean z) throws Throwable {
        this.noUnicode = new HashSet();
        PDCIDFontType2Embedder pDCIDFontType2Embedder = new PDCIDFontType2Embedder(pDDocument, this.dict, trueTypeFont, z, this);
        this.embedder = pDCIDFontType2Embedder;
        this.descendantFont = pDCIDFontType2Embedder.getCIDFont();
        readEncoding();
        fetchCMapUCS2();
    }

    @Override
    public void addToSubset(int i) {
        if (!willBeSubset()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.embedder.addToSubset(i);
    }

    @Override
    public void subset() throws IOException {
        if (!willBeSubset()) {
            throw new IllegalStateException("This font was created with subsetting disabled");
        }
        this.embedder.subset();
    }

    @Override
    public boolean willBeSubset() {
        PDCIDFontType2Embedder pDCIDFontType2Embedder = this.embedder;
        return pDCIDFontType2Embedder != null && pDCIDFontType2Embedder.needsSubset();
    }

    private void readEncoding() throws Throwable {
        COSBase dictionaryObject = this.dict.getDictionaryObject(COSName.ENCODING);
        boolean z = true;
        if (dictionaryObject instanceof COSName) {
            CMap predefinedCMap = CMapManager.getPredefinedCMap(((COSName) dictionaryObject).getName());
            this.cMap = predefinedCMap;
            if (predefinedCMap != null) {
                this.isCMapPredefined = true;
            } else {
                throw new IOException("Missing required CMap");
            }
        } else if (dictionaryObject != null) {
            CMap cMap = readCMap(dictionaryObject);
            this.cMap = cMap;
            if (cMap == null) {
                throw new IOException("Missing required CMap");
            }
            if (!cMap.hasCIDMappings()) {
                Log.w("PdfBox-Android", "Invalid Encoding CMap in font " + getName());
            }
        }
        PDCIDSystemInfo cIDSystemInfo = this.descendantFont.getCIDSystemInfo();
        if (cIDSystemInfo != null) {
            if (!cIDSystemInfo.getRegistry().equals("Adobe") || (!cIDSystemInfo.getOrdering().equals("GB1") && !cIDSystemInfo.getOrdering().equals("CNS1") && !cIDSystemInfo.getOrdering().equals("Japan1") && !cIDSystemInfo.getOrdering().equals("Korea1"))) {
                z = false;
            }
            this.isDescendantCJK = z;
        }
    }

    private void fetchCMapUCS2() throws IOException {
        String name;
        CMap predefinedCMap;
        CMap predefinedCMap2;
        COSName cOSName = this.dict.getCOSName(COSName.ENCODING);
        if ((!this.isCMapPredefined || cOSName == COSName.IDENTITY_H || cOSName == COSName.IDENTITY_V) && !this.isDescendantCJK) {
            return;
        }
        if (this.isDescendantCJK) {
            name = this.descendantFont.getCIDSystemInfo().getRegistry() + "-" + this.descendantFont.getCIDSystemInfo().getOrdering() + "-" + this.descendantFont.getCIDSystemInfo().getSupplement();
        } else {
            name = cOSName != null ? cOSName.getName() : null;
        }
        if (name == null || (predefinedCMap = CMapManager.getPredefinedCMap(name)) == null || (predefinedCMap2 = CMapManager.getPredefinedCMap(predefinedCMap.getRegistry() + "-" + predefinedCMap.getOrdering() + "-UCS2")) == null) {
            return;
        }
        this.cMapUCS2 = predefinedCMap2;
    }

    private String getCJKCMap(PDCIDSystemInfo pDCIDSystemInfo) {
        if (pDCIDSystemInfo.getOrdering().equals("GB1")) {
            return "Adobe-GB1-0";
        }
        if (pDCIDSystemInfo.getOrdering().equals("CNS1")) {
            return "Adobe-CNS1-0";
        }
        if (pDCIDSystemInfo.getOrdering().equals("Japan1")) {
            return "Adobe-Japan1-1";
        }
        if (pDCIDSystemInfo.getOrdering().equals("Korea1")) {
            return "Adobe-Korea1-0";
        }
        throw new IllegalStateException();
    }

    public String getBaseFont() {
        return this.dict.getNameAsString(COSName.BASE_FONT);
    }

    public PDCIDFont getDescendantFont() {
        return this.descendantFont;
    }

    public CMap getCMap() {
        return this.cMap;
    }

    public CMap getCMapUCS2() {
        return this.cMapUCS2;
    }

    @Override
    public PDFontDescriptor getFontDescriptor() {
        return this.descendantFont.getFontDescriptor();
    }

    @Override
    public Matrix getFontMatrix() {
        return this.descendantFont.getFontMatrix();
    }

    @Override
    public boolean isVertical() {
        return this.cMap.getWMode() == 1;
    }

    @Override
    public float getHeight(int i) throws IOException {
        return this.descendantFont.getHeight(i);
    }

    @Override
    protected byte[] encode(int i) throws IOException {
        return this.descendantFont.encode(i);
    }

    @Override
    public float getAverageFontWidth() {
        return this.descendantFont.getAverageFontWidth();
    }

    @Override
    public Vector getPositionVector(int i) {
        return this.descendantFont.getPositionVector(i).scale(-0.001f);
    }

    @Override
    public Vector getDisplacement(int i) throws IOException {
        if (isVertical()) {
            return new Vector(0.0f, this.descendantFont.getVerticalDisplacementVectorY(i) / 1000.0f);
        }
        return super.getDisplacement(i);
    }

    @Override
    public float getWidth(int i) throws IOException {
        return this.descendantFont.getWidth(i);
    }

    @Override
    protected float getStandard14Width(int i) {
        throw new UnsupportedOperationException("not suppported");
    }

    @Override
    public float getWidthFromFont(int i) throws IOException {
        return this.descendantFont.getWidthFromFont(i);
    }

    @Override
    public boolean isEmbedded() {
        return this.descendantFont.isEmbedded();
    }

    @Override
    public String toUnicode(int i) throws IOException {
        String unicode = super.toUnicode(i);
        if (unicode != null) {
            return unicode;
        }
        if ((this.isCMapPredefined || this.isDescendantCJK) && this.cMapUCS2 != null) {
            return this.cMapUCS2.toUnicode(codeToCID(i));
        }
        if (this.noUnicode.contains(Integer.valueOf(i))) {
            return null;
        }
        Log.w("PdfBox-Android", "No Unicode mapping for " + ("CID+" + codeToCID(i)) + " (" + i + ") in font " + getName());
        this.noUnicode.add(Integer.valueOf(i));
        return null;
    }

    @Override
    public String getName() {
        return getBaseFont();
    }

    @Override
    public BoundingBox getBoundingBox() throws IOException {
        return this.descendantFont.getBoundingBox();
    }

    @Override
    public int readCode(InputStream inputStream) throws IOException {
        return this.cMap.readCode(inputStream);
    }

    public int codeToCID(int i) {
        return this.descendantFont.codeToCID(i);
    }

    public int codeToGID(int i) throws IOException {
        return this.descendantFont.codeToGID(i);
    }

    @Override
    public boolean isDamaged() {
        return this.descendantFont.isDamaged();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + MqttTopic.TOPIC_LEVEL_SEPARATOR + (getDescendantFont() != null ? getDescendantFont().getClass().getSimpleName() : null) + " " + getBaseFont();
    }

    @Override
    public Path getPath(int i) throws IOException {
        return this.descendantFont.getPath(i);
    }

    @Override
    public boolean hasGlyph(int i) throws IOException {
        return this.descendantFont.hasGlyph(i);
    }
}
