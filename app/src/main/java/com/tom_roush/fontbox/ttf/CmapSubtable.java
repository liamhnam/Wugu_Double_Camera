package com.tom_roush.fontbox.ttf;

import android.util.Log;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CmapSubtable {
    private static final long LEAD_OFFSET = 55232;
    private static final long SURROGATE_OFFSET = -56613888;
    private Map<Integer, Integer> characterCodeToGlyphId;
    private int[] glyphIdToCharacterCode;
    private int platformEncodingId;
    private int platformId;
    private long subTableOffset;

    public void initData(TTFDataStream tTFDataStream) throws IOException {
        this.platformId = tTFDataStream.readUnsignedShort();
        this.platformEncodingId = tTFDataStream.readUnsignedShort();
        this.subTableOffset = tTFDataStream.readUnsignedInt();
    }

    public void initSubtable(CmapTable cmapTable, int i, TTFDataStream tTFDataStream) throws IOException {
        tTFDataStream.seek(cmapTable.getOffset() + this.subTableOffset);
        int unsignedShort = tTFDataStream.readUnsignedShort();
        if (unsignedShort < 8) {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedShort();
        } else {
            tTFDataStream.readUnsignedShort();
            tTFDataStream.readUnsignedInt();
            tTFDataStream.readUnsignedInt();
        }
        if (unsignedShort == 0) {
            processSubtype0(tTFDataStream);
            return;
        }
        if (unsignedShort == 2) {
            processSubtype2(tTFDataStream, i);
            return;
        }
        if (unsignedShort == 4) {
            processSubtype4(tTFDataStream, i);
            return;
        }
        if (unsignedShort == 6) {
            processSubtype6(tTFDataStream, i);
            return;
        }
        if (unsignedShort == 8) {
            processSubtype8(tTFDataStream, i);
            return;
        }
        if (unsignedShort == 10) {
            processSubtype10(tTFDataStream, i);
            return;
        }
        switch (unsignedShort) {
            case 12:
                processSubtype12(tTFDataStream, i);
                return;
            case 13:
                processSubtype13(tTFDataStream, i);
                return;
            case 14:
                processSubtype14(tTFDataStream, i);
                return;
            default:
                throw new IOException("Unknown cmap format:" + unsignedShort);
        }
    }

    protected void processSubtype8(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] unsignedByteArray = tTFDataStream.readUnsignedByteArray(8192);
        long unsignedInt = tTFDataStream.readUnsignedInt();
        if (unsignedInt > 65536) {
            throw new IOException("CMap ( Subtype8 ) is invalid");
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        this.characterCodeToGlyphId = new HashMap(i);
        long j = 0;
        long j2 = 0;
        while (j2 < unsignedInt) {
            long unsignedInt2 = tTFDataStream.readUnsignedInt();
            long unsignedInt3 = tTFDataStream.readUnsignedInt();
            long unsignedInt4 = tTFDataStream.readUnsignedInt();
            if (unsignedInt2 > unsignedInt3 || j > unsignedInt2) {
                throw new IOException("Range invalid");
            }
            long j3 = unsignedInt2;
            while (j3 <= unsignedInt3) {
                if (j3 > 2147483647L) {
                    throw new IOException("[Sub Format 8] Invalid Character code");
                }
                long j4 = unsignedInt;
                int i2 = (int) j3;
                if ((unsignedByteArray[i2 / 8] & (1 << (i2 % 8))) != 0) {
                    long j5 = (((j3 >> 10) + LEAD_OFFSET) << 10) + (1023 & j3) + 56320 + SURROGATE_OFFSET;
                    if (j5 > 2147483647L) {
                        throw new IOException("[Sub Format 8] Invalid Character code");
                    }
                    i2 = (int) j5;
                }
                int[] iArr = unsignedByteArray;
                long j6 = unsignedInt4 + (j3 - unsignedInt2);
                long j7 = unsignedInt2;
                if (j6 > i || j6 > 2147483647L) {
                    throw new IOException("CMap contains an invalid glyph index");
                }
                int i3 = (int) j6;
                this.glyphIdToCharacterCode[i3] = i2;
                this.characterCodeToGlyphId.put(Integer.valueOf(i2), Integer.valueOf(i3));
                j3++;
                unsignedByteArray = iArr;
                unsignedInt = j4;
                unsignedInt2 = j7;
            }
            j2++;
            unsignedInt = unsignedInt;
            j = 0;
        }
    }

    protected void processSubtype10(TTFDataStream tTFDataStream, int i) throws IOException {
        long unsignedInt = tTFDataStream.readUnsignedInt();
        long unsignedInt2 = tTFDataStream.readUnsignedInt();
        if (unsignedInt2 > 2147483647L) {
            throw new IOException("Invalid number of Characters");
        }
        if (unsignedInt >= 0 && unsignedInt <= 1114111) {
            long j = unsignedInt + unsignedInt2;
            if (j <= 1114111 && (j < 55296 || j > 57343)) {
                return;
            }
        }
        throw new IOException("Invalid Characters codes");
    }

    protected void processSubtype12(TTFDataStream tTFDataStream, int i) throws IOException {
        long j;
        int i2 = i;
        long unsignedInt = tTFDataStream.readUnsignedInt();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i2);
        this.characterCodeToGlyphId = new HashMap(i2);
        long j2 = 0;
        long j3 = 0;
        while (j3 < unsignedInt) {
            long unsignedInt2 = tTFDataStream.readUnsignedInt();
            long unsignedInt3 = tTFDataStream.readUnsignedInt();
            long unsignedInt4 = tTFDataStream.readUnsignedInt();
            if (unsignedInt2 < j2 || unsignedInt2 > 1114111 || (unsignedInt2 >= 55296 && unsignedInt2 <= 57343)) {
                throw new IOException("Invalid characters codes");
            }
            if ((unsignedInt3 > j2 && unsignedInt3 < unsignedInt2) || unsignedInt3 > 1114111 || (unsignedInt3 >= 55296 && unsignedInt3 <= 57343)) {
                throw new IOException("Invalid characters codes");
            }
            long j4 = j2;
            while (true) {
                if (j4 > unsignedInt3 - unsignedInt2) {
                    j = unsignedInt;
                    break;
                }
                long j5 = unsignedInt4 + j4;
                j = unsignedInt;
                if (j5 >= i2) {
                    Log.w("PdfBox-Android", "Format 12 cmap contains an invalid glyph index");
                    break;
                }
                long j6 = unsignedInt2 + j4;
                long j7 = unsignedInt2;
                if (j6 > 1114111) {
                    Log.w("PdfBox-Android", "Format 12 cmap contains character beyond UCS-4");
                }
                int i3 = (int) j5;
                int i4 = (int) j6;
                this.glyphIdToCharacterCode[i3] = i4;
                this.characterCodeToGlyphId.put(Integer.valueOf(i4), Integer.valueOf(i3));
                j4++;
                i2 = i;
                unsignedInt = j;
                unsignedInt2 = j7;
            }
            j3++;
            i2 = i;
            unsignedInt = j;
            j2 = 0;
        }
    }

    protected void processSubtype13(TTFDataStream tTFDataStream, int i) throws IOException {
        int i2 = i;
        long unsignedInt = tTFDataStream.readUnsignedInt();
        this.characterCodeToGlyphId = new HashMap(i2);
        long j = 0;
        long j2 = 0;
        while (j2 < unsignedInt) {
            long unsignedInt2 = tTFDataStream.readUnsignedInt();
            long unsignedInt3 = tTFDataStream.readUnsignedInt();
            long unsignedInt4 = tTFDataStream.readUnsignedInt();
            if (unsignedInt4 > i2) {
                Log.w("PdfBox-Android", "Format 13 cmap contains an invalid glyph index");
                return;
            }
            if (unsignedInt2 < j || unsignedInt2 > 1114111 || (unsignedInt2 >= 55296 && unsignedInt2 <= 57343)) {
                throw new IOException("Invalid Characters codes");
            }
            if ((unsignedInt3 > 0 && unsignedInt3 < unsignedInt2) || unsignedInt3 > 1114111 || (unsignedInt3 >= 55296 && unsignedInt3 <= 57343)) {
                throw new IOException("Invalid Characters codes");
            }
            long j3 = 0;
            while (j3 <= unsignedInt3 - unsignedInt2) {
                long j4 = unsignedInt;
                long j5 = unsignedInt2 + j3;
                if (j5 > 2147483647L) {
                    throw new IOException("Character Code greater than Integer.MAX_VALUE");
                }
                if (j5 > 1114111) {
                    Log.w("PdfBox-Android", "Format 13 cmap contains character beyond UCS-4");
                }
                int i3 = (int) unsignedInt4;
                int i4 = (int) j5;
                this.glyphIdToCharacterCode[i3] = i4;
                this.characterCodeToGlyphId.put(Integer.valueOf(i4), Integer.valueOf(i3));
                j3++;
                unsignedInt = j4;
            }
            j2++;
            i2 = i;
            j = 0;
        }
    }

    protected void processSubtype14(TTFDataStream tTFDataStream, int i) throws IOException {
        Log.w("PdfBox-Android", "Format 14 cmap table is not supported and will be ignored");
    }

    protected void processSubtype6(TTFDataStream tTFDataStream, int i) throws IOException {
        int unsignedShort = tTFDataStream.readUnsignedShort();
        int unsignedShort2 = tTFDataStream.readUnsignedShort();
        if (unsignedShort2 == 0) {
            return;
        }
        HashMap map = new HashMap(i);
        this.characterCodeToGlyphId = new HashMap(i);
        int[] unsignedShortArray = tTFDataStream.readUnsignedShortArray(unsignedShort2);
        for (int i2 = 0; i2 < unsignedShort2; i2++) {
            int i3 = unsignedShort + i2;
            map.put(Integer.valueOf(unsignedShortArray[i2]), Integer.valueOf(i3));
            this.characterCodeToGlyphId.put(Integer.valueOf(i3), Integer.valueOf(unsignedShortArray[i2]));
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(((Integer) Collections.max(map.keySet())).intValue() + 1);
        for (Map.Entry entry : map.entrySet()) {
            this.glyphIdToCharacterCode[((Integer) entry.getKey()).intValue()] = ((Integer) entry.getValue()).intValue();
        }
    }

    protected void processSubtype4(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int unsignedShort = tTFDataStream.readUnsignedShort() / 2;
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        tTFDataStream.readUnsignedShort();
        int[] unsignedShortArray = tTFDataStream.readUnsignedShortArray(unsignedShort);
        tTFDataStream.readUnsignedShort();
        int[] unsignedShortArray2 = tTFDataStream.readUnsignedShortArray(unsignedShort);
        int[] unsignedShortArray3 = tTFDataStream.readUnsignedShortArray(unsignedShort);
        int[] unsignedShortArray4 = tTFDataStream.readUnsignedShortArray(unsignedShort);
        HashMap map = new HashMap(i);
        this.characterCodeToGlyphId = new HashMap(i);
        long currentPosition = tTFDataStream.getCurrentPosition();
        int i2 = 0;
        while (i2 < unsignedShort) {
            int i3 = unsignedShortArray2[i2];
            int i4 = unsignedShortArray[i2];
            int i5 = unsignedShortArray3[i2];
            int i6 = unsignedShortArray4[i2];
            if (i3 != 65535 && i4 != 65535) {
                int i7 = i3;
                while (i7 <= i4) {
                    if (i6 == 0) {
                        int i8 = (i7 + i5) % 65536;
                        iArr = unsignedShortArray;
                        iArr2 = unsignedShortArray2;
                        map.put(Integer.valueOf(i8), Integer.valueOf(i7));
                        iArr3 = unsignedShortArray3;
                        this.characterCodeToGlyphId.put(Integer.valueOf(i7), Integer.valueOf(i8));
                    } else {
                        iArr = unsignedShortArray;
                        iArr2 = unsignedShortArray2;
                        iArr3 = unsignedShortArray3;
                        tTFDataStream.seek(((long) (((i6 / 2) + (i7 - i3) + (i2 - unsignedShort)) * 2)) + currentPosition);
                        int unsignedShort2 = tTFDataStream.readUnsignedShort();
                        if (unsignedShort2 != 0) {
                            int i9 = (unsignedShort2 + i5) % 65536;
                            if (!map.containsKey(Integer.valueOf(i9))) {
                                map.put(Integer.valueOf(i9), Integer.valueOf(i7));
                                this.characterCodeToGlyphId.put(Integer.valueOf(i7), Integer.valueOf(i9));
                            }
                        }
                    }
                    i7++;
                    unsignedShortArray = iArr;
                    unsignedShortArray2 = iArr2;
                    unsignedShortArray3 = iArr3;
                }
            }
            i2++;
            unsignedShortArray = unsignedShortArray;
            unsignedShortArray2 = unsignedShortArray2;
            unsignedShortArray3 = unsignedShortArray3;
        }
        if (map.isEmpty()) {
            Log.w("PdfBox-Android", "cmap format 4 subtable is empty");
            return;
        }
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(((Integer) Collections.max(map.keySet())).intValue() + 1);
        for (Map.Entry entry : map.entrySet()) {
            this.glyphIdToCharacterCode[((Integer) entry.getKey()).intValue()] = ((Integer) entry.getValue()).intValue();
        }
    }

    protected void processSubtype2(TTFDataStream tTFDataStream, int i) throws IOException {
        int[] iArr = new int[256];
        int iMax = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            int unsignedShort = tTFDataStream.readUnsignedShort();
            iArr[i2] = unsignedShort;
            iMax = Math.max(iMax, unsignedShort / 8);
        }
        SubHeader[] subHeaderArr = new SubHeader[iMax + 1];
        for (int i3 = 0; i3 <= iMax; i3++) {
            subHeaderArr[i3] = new SubHeader(tTFDataStream.readUnsignedShort(), tTFDataStream.readUnsignedShort(), tTFDataStream.readSignedShort(), (tTFDataStream.readUnsignedShort() - (((r11 - i3) - 1) * 8)) - 2);
        }
        long currentPosition = tTFDataStream.getCurrentPosition();
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(i);
        this.characterCodeToGlyphId = new HashMap(i);
        for (int i4 = 0; i4 <= iMax; i4++) {
            SubHeader subHeader = subHeaderArr[i4];
            int firstCode = subHeader.getFirstCode();
            int idRangeOffset = subHeader.getIdRangeOffset();
            short idDelta = subHeader.getIdDelta();
            int entryCount = subHeader.getEntryCount();
            tTFDataStream.seek(((long) idRangeOffset) + currentPosition);
            for (int i5 = 0; i5 < entryCount; i5++) {
                int i6 = (i4 << 8) + firstCode + i5;
                int unsignedShort2 = tTFDataStream.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    unsignedShort2 = (unsignedShort2 + idDelta) % 65536;
                }
                if (unsignedShort2 >= i) {
                    Log.w("PdfBox-Android", "glyphId " + unsignedShort2 + " for charcode " + i6 + " ignored, numGlyphs is " + i);
                } else {
                    this.glyphIdToCharacterCode[unsignedShort2] = i6;
                    this.characterCodeToGlyphId.put(Integer.valueOf(i6), Integer.valueOf(unsignedShort2));
                }
            }
        }
    }

    protected void processSubtype0(TTFDataStream tTFDataStream) throws IOException {
        byte[] bArr = tTFDataStream.read(256);
        this.glyphIdToCharacterCode = newGlyphIdToCharacterCode(256);
        this.characterCodeToGlyphId = new HashMap(bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] + 256) % 256;
            this.glyphIdToCharacterCode[i2] = i;
            this.characterCodeToGlyphId.put(Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private int[] newGlyphIdToCharacterCode(int i) {
        int[] iArr = new int[i];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    public int getPlatformEncodingId() {
        return this.platformEncodingId;
    }

    public void setPlatformEncodingId(int i) {
        this.platformEncodingId = i;
    }

    public int getPlatformId() {
        return this.platformId;
    }

    public void setPlatformId(int i) {
        this.platformId = i;
    }

    public int getGlyphId(int i) {
        Integer num = this.characterCodeToGlyphId.get(Integer.valueOf(i));
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public Integer getCharacterCode(int i) {
        int i2;
        if (i >= 0) {
            int[] iArr = this.glyphIdToCharacterCode;
            if (i < iArr.length && (i2 = iArr[i]) != -1) {
                return Integer.valueOf(i2);
            }
            return null;
        }
        return null;
    }

    public String toString() {
        return "{" + getPlatformId() + " " + getPlatformEncodingId() + "}";
    }

    private class SubHeader {
        private final int entryCount;
        private final int firstCode;
        private final short idDelta;
        private final int idRangeOffset;

        private SubHeader(int i, int i2, short s, int i3) {
            this.firstCode = i;
            this.entryCount = i2;
            this.idDelta = s;
            this.idRangeOffset = i3;
        }

        public int getFirstCode() {
            return this.firstCode;
        }

        public int getEntryCount() {
            return this.entryCount;
        }

        public short getIdDelta() {
            return this.idDelta;
        }

        public int getIdRangeOffset() {
            return this.idRangeOffset;
        }
    }
}
