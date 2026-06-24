package com.tom_roush.pdfbox.pdmodel.font;

import com.tom_roush.fontbox.ttf.TrueTypeFont;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

final class PDCIDFontType2Embedder extends TrueTypeEmbedder {
    private final COSDictionary cidFont;
    private final COSDictionary dict;
    private final PDDocument document;
    private final Map<Integer, Integer> gidToUni;
    private final PDType0Font parent;

    enum State {
        FIRST,
        BRACKET,
        SERIAL
    }

    PDCIDFontType2Embedder(PDDocument pDDocument, COSDictionary cOSDictionary, TrueTypeFont trueTypeFont, boolean z, PDType0Font pDType0Font) throws IOException {
        super(pDDocument, cOSDictionary, trueTypeFont, z);
        this.document = pDDocument;
        this.dict = cOSDictionary;
        this.parent = pDType0Font;
        cOSDictionary.setItem(COSName.SUBTYPE, COSName.TYPE0);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.ENCODING, COSName.IDENTITY_H);
        COSDictionary cOSDictionaryCreateCIDFont = createCIDFont();
        this.cidFont = cOSDictionaryCreateCIDFont;
        COSArray cOSArray = new COSArray();
        cOSArray.add((COSBase) cOSDictionaryCreateCIDFont);
        cOSDictionary.setItem(COSName.DESCENDANT_FONTS, (COSBase) cOSArray);
        this.gidToUni = new HashMap(trueTypeFont.getMaximumProfile().getNumGlyphs());
        int numGlyphs = trueTypeFont.getMaximumProfile().getNumGlyphs();
        for (int i = 1; i <= numGlyphs; i++) {
            Integer characterCode = this.cmap.getCharacterCode(i);
            if (characterCode != null) {
                this.gidToUni.put(Integer.valueOf(i), characterCode);
            }
        }
        buildToUnicodeCMap(null);
    }

    @Override
    protected void buildSubset(InputStream inputStream, String str, Map<Integer, Integer> map) throws IOException {
        HashMap map2 = new HashMap(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            map2.put(Integer.valueOf(entry.getValue().intValue()), Integer.valueOf(entry.getKey().intValue()));
        }
        buildFontFile2(inputStream);
        addNameTag(str);
        buildWidths(map2);
        buildCIDToGIDMap(map2);
        buildCIDSet(map2);
        buildToUnicodeCMap(map);
    }

    private void buildToUnicodeCMap(Map<Integer, Integer> map) throws IOException {
        int iIntValue;
        ToUnicodeWriter toUnicodeWriter = new ToUnicodeWriter();
        int numGlyphs = this.ttf.getMaximumProfile().getNumGlyphs();
        boolean z = false;
        for (int i = 1; i <= numGlyphs; i++) {
            if (map == null) {
                iIntValue = i;
            } else if (map.containsKey(Integer.valueOf(i))) {
                iIntValue = map.get(Integer.valueOf(i)).intValue();
            }
            Integer num = this.gidToUni.get(Integer.valueOf(iIntValue));
            if (num != null) {
                if (num.intValue() > 65535) {
                    z = true;
                }
                toUnicodeWriter.add(iIntValue, new String(new int[]{num.intValue()}, 0, 1));
            }
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        toUnicodeWriter.writeTo(byteArrayOutputStream);
        PDStream pDStream = new PDStream(this.document, (InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE);
        if (z && this.document.getVersion() < 1.5d) {
            this.document.setVersion(1.5f);
        }
        this.dict.setItem(COSName.TO_UNICODE, pDStream);
    }

    private COSDictionary toCIDSystemInfo(String str, String str2, int i) {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setString(COSName.REGISTRY, str);
        cOSDictionary.setString(COSName.ORDERING, str2);
        cOSDictionary.setInt(COSName.SUPPLEMENT, i);
        return cOSDictionary;
    }

    private COSDictionary createCIDFont() throws IOException {
        COSDictionary cOSDictionary = new COSDictionary();
        cOSDictionary.setItem(COSName.TYPE, (COSBase) COSName.FONT);
        cOSDictionary.setItem(COSName.SUBTYPE, (COSBase) COSName.CID_FONT_TYPE2);
        cOSDictionary.setName(COSName.BASE_FONT, this.fontDescriptor.getFontName());
        cOSDictionary.setItem(COSName.CIDSYSTEMINFO, (COSBase) toCIDSystemInfo("Adobe", "Identity", 0));
        cOSDictionary.setItem(COSName.FONT_DESC, (COSBase) this.fontDescriptor.getCOSObject());
        buildWidths(cOSDictionary);
        cOSDictionary.setItem(COSName.CID_TO_GID_MAP, (COSBase) COSName.IDENTITY);
        return cOSDictionary;
    }

    private void addNameTag(String str) throws IOException {
        String str2 = str + this.fontDescriptor.getFontName();
        this.dict.setName(COSName.BASE_FONT, str2);
        this.fontDescriptor.setFontName(str2);
        this.cidFont.setName(COSName.BASE_FONT, str2);
    }

    private void buildCIDToGIDMap(Map<Integer, Integer> map) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int iIntValue = ((Integer) Collections.max(map.keySet())).intValue();
        for (int i = 0; i <= iIntValue; i++) {
            int iIntValue2 = map.containsKey(Integer.valueOf(i)) ? map.get(Integer.valueOf(i)).intValue() : 0;
            byteArrayOutputStream.write(new byte[]{(byte) ((iIntValue2 >> 8) & 255), (byte) (iIntValue2 & 255)});
        }
        PDStream pDStream = new PDStream(this.document, (InputStream) new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.FLATE_DECODE);
        pDStream.getCOSObject().setInt(COSName.LENGTH1, pDStream.toByteArray().length);
        this.cidFont.setItem(COSName.CID_TO_GID_MAP, pDStream);
    }

    private void buildCIDSet(Map<Integer, Integer> map) throws IOException {
        byte[] bArr = new byte[(((Integer) Collections.max(map.keySet())).intValue() / 8) + 1];
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            int i = 1 << (7 - (iIntValue % 8));
            int i2 = iIntValue / 8;
            bArr[i2] = (byte) (i | bArr[i2]);
        }
        this.fontDescriptor.setCIDSet(new PDStream(this.document, (InputStream) new ByteArrayInputStream(bArr), COSName.FLATE_DECODE));
    }

    private void buildWidths(Map<Integer, Integer> map) throws IOException {
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        COSArray cOSArray = new COSArray();
        COSArray cOSArray2 = new COSArray();
        Iterator it = new TreeSet(map.keySet()).iterator();
        int i = Integer.MIN_VALUE;
        while (it.hasNext()) {
            int iIntValue = ((Integer) it.next()).intValue();
            float advanceWidth = this.ttf.getHorizontalMetrics().getAdvanceWidth(map.get(Integer.valueOf(iIntValue)).intValue()) * unitsPerEm;
            if (i != iIntValue - 1) {
                cOSArray2 = new COSArray();
                cOSArray.add((COSBase) COSInteger.get(iIntValue));
                cOSArray.add((COSBase) cOSArray2);
            }
            cOSArray2.add((COSBase) COSInteger.get(Math.round(advanceWidth)));
            i = iIntValue;
        }
        this.cidFont.setItem(COSName.f2321W, (COSBase) cOSArray);
    }

    private void buildWidths(COSDictionary cOSDictionary) throws IOException {
        int numberOfGlyphs = this.ttf.getNumberOfGlyphs();
        int[] iArr = new int[numberOfGlyphs * 2];
        for (int i = 0; i < numberOfGlyphs; i++) {
            int i2 = i * 2;
            iArr[i2] = i;
            iArr[i2 + 1] = this.ttf.getHorizontalMetrics().getAdvanceWidth(i);
        }
        cOSDictionary.setItem(COSName.f2321W, (COSBase) getWidths(iArr));
    }

    private COSArray getWidths(int[] iArr) throws IOException {
        COSArray cOSArray;
        State state;
        if (iArr.length == 0) {
            throw new IllegalArgumentException("length of widths must be > 0");
        }
        float unitsPerEm = 1000.0f / this.ttf.getHeader().getUnitsPerEm();
        long j = iArr[0];
        int i = 1;
        long jRound = Math.round(iArr[1] * unitsPerEm);
        COSArray cOSArray2 = new COSArray();
        cOSArray2.add((COSBase) COSInteger.get(j));
        State state2 = State.FIRST;
        COSArray cOSArray3 = null;
        int i2 = 2;
        while (i2 < iArr.length) {
            long j2 = iArr[i2];
            COSArray cOSArray4 = cOSArray3;
            long jRound2 = Math.round(iArr[i2 + 1] * unitsPerEm);
            int i3 = C18851.f2387x16435826[state2.ordinal()];
            if (i3 == i) {
                cOSArray = cOSArray4;
                long j3 = j + 1;
                if (j2 == j3 && jRound2 == jRound) {
                    state = State.SERIAL;
                    state2 = state;
                } else if (j2 == j3) {
                    State state3 = State.BRACKET;
                    COSArray cOSArray5 = new COSArray();
                    cOSArray5.add((COSBase) COSInteger.get(jRound));
                    state2 = state3;
                    cOSArray = cOSArray5;
                } else {
                    COSArray cOSArray6 = new COSArray();
                    cOSArray6.add((COSBase) COSInteger.get(jRound));
                    cOSArray2.add((COSBase) cOSArray6);
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                    cOSArray = cOSArray6;
                }
            } else if (i3 != 2) {
                if (i3 == 3 && (j2 != j + 1 || jRound2 != jRound)) {
                    cOSArray2.add((COSBase) COSInteger.get(j));
                    cOSArray2.add((COSBase) COSInteger.get(jRound));
                    cOSArray2.add((COSBase) COSInteger.get(j2));
                    state2 = State.FIRST;
                }
                cOSArray = cOSArray4;
            } else {
                long j4 = j + 1;
                if (j2 == j4 && jRound2 == jRound) {
                    state = State.SERIAL;
                    cOSArray = cOSArray4;
                    cOSArray2.add((COSBase) cOSArray);
                    cOSArray2.add((COSBase) COSInteger.get(j));
                } else {
                    cOSArray = cOSArray4;
                    if (j2 == j4) {
                        cOSArray.add((COSBase) COSInteger.get(jRound));
                    } else {
                        state = State.FIRST;
                        cOSArray.add((COSBase) COSInteger.get(jRound));
                        cOSArray2.add((COSBase) cOSArray);
                        cOSArray2.add((COSBase) COSInteger.get(j2));
                    }
                }
                state2 = state;
            }
            i2 += 2;
            jRound = jRound2;
            cOSArray3 = cOSArray;
            j = j2;
            i = 1;
        }
        COSArray cOSArray7 = cOSArray3;
        int i4 = C18851.f2387x16435826[state2.ordinal()];
        if (i4 == 1) {
            COSArray cOSArray8 = new COSArray();
            cOSArray8.add((COSBase) COSInteger.get(jRound));
            cOSArray2.add((COSBase) cOSArray8);
        } else if (i4 == 2) {
            cOSArray7.add((COSBase) COSInteger.get(jRound));
            cOSArray2.add((COSBase) cOSArray7);
        } else if (i4 == 3) {
            cOSArray2.add((COSBase) COSInteger.get(j));
            cOSArray2.add((COSBase) COSInteger.get(jRound));
        }
        return cOSArray2;
    }

    static class C18851 {

        static final int[] f2387x16435826;

        static {
            int[] iArr = new int[State.values().length];
            f2387x16435826 = iArr;
            try {
                iArr[State.FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2387x16435826[State.BRACKET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2387x16435826[State.SERIAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public PDCIDFont getCIDFont() throws IOException {
        return new PDCIDFontType2(this.cidFont, this.parent, this.ttf);
    }
}
