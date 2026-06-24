package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.ttf.CmapSubtable;
import com.tom_roush.fontbox.ttf.GlyphTable;
import com.tom_roush.fontbox.ttf.HorizontalHeaderTable;
import com.tom_roush.fontbox.ttf.HorizontalMetricsTable;
import com.tom_roush.fontbox.ttf.IndexToLocationTable;
import com.tom_roush.fontbox.ttf.MaximumProfileTable;
import com.tom_roush.fontbox.ttf.OS2WindowsMetricsTable;
import com.tom_roush.fontbox.ttf.TTFParser;
import com.tom_roush.fontbox.ttf.TTFSubsetter;
import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInputStream;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class TrueTypeEmbedder implements Subsetter {
    private static final String BASE25 = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int ITALIC = 1;
    private static final int OBLIQUE = 256;
    protected final CmapSubtable cmap;
    private final PDDocument document;
    private final boolean embedSubset;
    protected PDFontDescriptor fontDescriptor;
    private final Set<Integer> subsetCodePoints = new HashSet();
    protected TrueTypeFont ttf;

    protected abstract void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException;

    TrueTypeEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, InputStream inputStream, boolean z) throws Throwable {
        this.document = pDDocument;
        this.embedSubset = z;
        buildFontFile2(inputStream);
        cOSDictionary.setName(COSName.BASE_FONT, this.ttf.getName());
        this.cmap = this.ttf.getUnicodeCmap();
    }

    TrueTypeEmbedder(PDDocument pDDocument, COSDictionary cOSDictionary, TrueTypeFont trueTypeFont, boolean z) throws IOException {
        this.document = pDDocument;
        this.embedSubset = z;
        this.ttf = trueTypeFont;
        this.fontDescriptor = createFontDescriptor(trueTypeFont);
        cOSDictionary.setName(COSName.BASE_FONT, trueTypeFont.getName());
        this.cmap = trueTypeFont.getUnicodeCmap();
    }

    public void buildFontFile2(InputStream inputStream) throws Throwable {
        COSInputStream cOSInputStreamCreateInputStream;
        PDStream pDStream = new PDStream(this.document, inputStream, COSName.FLATE_DECODE);
        pDStream.getCOSObject().setInt(COSName.LENGTH1, pDStream.toByteArray().length);
        try {
            cOSInputStreamCreateInputStream = pDStream.createInputStream();
            try {
                TrueTypeFont embedded = new TTFParser().parseEmbedded(cOSInputStreamCreateInputStream);
                this.ttf = embedded;
                if (!isEmbeddingPermitted(embedded)) {
                    throw new IOException("This font does not permit embedding");
                }
                if (this.fontDescriptor == null) {
                    this.fontDescriptor = createFontDescriptor(this.ttf);
                }
                IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
                this.fontDescriptor.setFontFile2(pDStream);
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(cOSInputStreamCreateInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cOSInputStreamCreateInputStream = null;
        }
    }

    private boolean isEmbeddingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        if (trueTypeFont.getOS2Windows() != null) {
            int fsType = trueTypeFont.getOS2Windows().getFsType() & 8;
            if ((fsType & 1) == 1 || (fsType & 512) == 512) {
                return false;
            }
        }
        return true;
    }

    private boolean isSubsettingPermitted(TrueTypeFont trueTypeFont) throws IOException {
        return trueTypeFont.getOS2Windows() == null || (trueTypeFont.getOS2Windows().getFsType() & OS2WindowsMetricsTable.FSTYPE_NO_SUBSETTING) != 256;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor createFontDescriptor(com.tom_roush.fontbox.ttf.TrueTypeFont r9) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdmodel.font.TrueTypeEmbedder.createFontDescriptor(com.tom_roush.fontbox.ttf.TrueTypeFont):com.tom_roush.pdfbox.pdmodel.font.PDFontDescriptor");
    }

    public TrueTypeFont getTrueTypeFont() {
        return this.ttf;
    }

    public PDFontDescriptor getFontDescriptor() {
        return this.fontDescriptor;
    }

    @Override
    public void addToSubset(int i) {
        this.subsetCodePoints.add(Integer.valueOf(i));
    }

    @Override
    public void subset() throws IOException {
        if (!isSubsettingPermitted(this.ttf)) {
            throw new IOException("This font does not permit subsetting");
        }
        if (!this.embedSubset) {
            throw new IllegalStateException("Subsetting is disabled");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("head");
        arrayList.add(HorizontalHeaderTable.TAG);
        arrayList.add(IndexToLocationTable.TAG);
        arrayList.add(MaximumProfileTable.TAG);
        arrayList.add("cvt ");
        arrayList.add("prep");
        arrayList.add(GlyphTable.TAG);
        arrayList.add(HorizontalMetricsTable.TAG);
        arrayList.add("fpgm");
        arrayList.add("gasp");
        TTFSubsetter tTFSubsetter = new TTFSubsetter(getTrueTypeFont(), arrayList);
        tTFSubsetter.addAll(this.subsetCodePoints);
        Map<Integer, Integer> gIDMap = tTFSubsetter.getGIDMap();
        String tag = getTag(gIDMap);
        tTFSubsetter.setPrefix(tag);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        tTFSubsetter.writeToStream(byteArrayOutputStream);
        buildSubset(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), tag, gIDMap);
        this.ttf.close();
    }

    public boolean needsSubset() {
        return this.embedSubset;
    }

    public String getTag(Map<Integer, Integer> map) {
        long jHashCode = map.hashCode();
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j = jHashCode / 25;
            sb.append(BASE25.charAt((int) (jHashCode % 25)));
            if (j == 0 || sb.length() >= 6) {
                break;
            }
            jHashCode = j;
        }
        while (sb.length() < 6) {
            sb.insert(0, 'A');
        }
        sb.append('+');
        return sb.toString();
    }
}
