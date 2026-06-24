package com.tom_roush.pdfbox.filter;

import android.util.Log;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageInputStream;
import com.tom_roush.harmony.javax.imageio.stream.MemoryCacheImageOutputStream;
import com.tom_roush.pdfbox.cos.COSDictionary;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UByte;

public class LZWFilter extends Filter {
    public static final long CLEAR_TABLE = 256;
    public static final long EOD = 257;

    private int calculateChunk(int i, int i2) {
        if (i >= 2048 - i2) {
            return 12;
        }
        if (i >= 1024 - i2) {
            return 11;
        }
        return i >= 512 - i2 ? 10 : 9;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.tom_roush.pdfbox.filter.DecodeResult decode(java.io.InputStream r10, java.io.OutputStream r11, com.tom_roush.pdfbox.cos.COSDictionary r12, int r13) throws java.io.IOException {
        /*
            r9 = this;
            com.tom_roush.pdfbox.cos.COSDictionary r13 = r9.getDecodeParams(r12, r13)
            r0 = 1
            if (r13 == 0) goto L1c
            com.tom_roush.pdfbox.cos.COSName r1 = com.tom_roush.pdfbox.cos.COSName.PREDICTOR
            int r1 = r13.getInt(r1)
            com.tom_roush.pdfbox.cos.COSName r2 = com.tom_roush.pdfbox.cos.COSName.EARLY_CHANGE
            int r2 = r13.getInt(r2, r0)
            if (r2 == 0) goto L18
            if (r2 == r0) goto L18
            goto L1d
        L18:
            r8 = r2
            r2 = r1
            r1 = r8
            goto L1f
        L1c:
            r1 = -1
        L1d:
            r2 = r1
            r1 = r0
        L1f:
            if (r2 <= r0) goto L5b
            com.tom_roush.pdfbox.cos.COSName r3 = com.tom_roush.pdfbox.cos.COSName.COLORS
            int r3 = r13.getInt(r3, r0)
            r4 = 32
            int r3 = java.lang.Math.min(r3, r4)
            com.tom_roush.pdfbox.cos.COSName r4 = com.tom_roush.pdfbox.cos.COSName.BITS_PER_COMPONENT
            r5 = 8
            int r4 = r13.getInt(r4, r5)
            com.tom_roush.pdfbox.cos.COSName r5 = com.tom_roush.pdfbox.cos.COSName.COLUMNS
            int r5 = r13.getInt(r5, r0)
            java.io.ByteArrayOutputStream r13 = new java.io.ByteArrayOutputStream
            r13.<init>()
            r9.doLZWDecode(r10, r13, r1)
            java.io.ByteArrayInputStream r10 = new java.io.ByteArrayInputStream
            byte[] r0 = r13.toByteArray()
            r10.<init>(r0)
            r6 = r10
            r7 = r11
            com.tom_roush.pdfbox.filter.Predictor.decodePredictor(r2, r3, r4, r5, r6, r7)
            r11.flush()
            r13.reset()
            r10.reset()
            goto L5e
        L5b:
            r9.doLZWDecode(r10, r11, r1)
        L5e:
            com.tom_roush.pdfbox.filter.DecodeResult r10 = new com.tom_roush.pdfbox.filter.DecodeResult
            r10.<init>(r12)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.filter.LZWFilter.decode(java.io.InputStream, java.io.OutputStream, com.tom_roush.pdfbox.cos.COSDictionary, int):com.tom_roush.pdfbox.filter.DecodeResult");
    }

    private void doLZWDecode(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        List<byte[]> arrayList = new ArrayList<>();
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(inputStream);
        loop0: while (true) {
            int iCalculateChunk = 9;
            long j = -1;
            while (true) {
                try {
                    long bits = memoryCacheImageInputStream.readBits(iCalculateChunk);
                    if (bits == 257) {
                        break loop0;
                    }
                    if (bits == 256) {
                        break;
                    }
                    if (bits < arrayList.size()) {
                        byte[] bArr = arrayList.get((int) bits);
                        byte b = bArr[0];
                        outputStream.write(bArr);
                        if (j != -1) {
                            checkIndexBounds(arrayList, j, memoryCacheImageInputStream);
                            byte[] bArr2 = arrayList.get((int) j);
                            byte[] bArrCopyOf = Arrays.copyOf(bArr2, bArr2.length + 1);
                            bArrCopyOf[bArr2.length] = b;
                            arrayList.add(bArrCopyOf);
                        }
                    } else {
                        checkIndexBounds(arrayList, j, memoryCacheImageInputStream);
                        byte[] bArr3 = arrayList.get((int) j);
                        byte[] bArrCopyOf2 = Arrays.copyOf(bArr3, bArr3.length + 1);
                        bArrCopyOf2[bArr3.length] = bArr3[0];
                        outputStream.write(bArrCopyOf2);
                        arrayList.add(bArrCopyOf2);
                    }
                    iCalculateChunk = calculateChunk(arrayList.size(), i);
                    j = bits;
                } catch (EOFException unused) {
                    Log.w("PdfBox-Android", "Premature EOF in LZW stream, EOD code missing");
                }
            }
            arrayList = createCodeTable();
        }
        outputStream.flush();
    }

    private void checkIndexBounds(List list, long j, MemoryCacheImageInputStream memoryCacheImageInputStream) throws IOException {
        if (j < 0) {
            throw new IOException("negative array index: " + j + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
        if (j >= list.size()) {
            throw new IOException("array index overflow: " + j + " >= " + list.size() + " near offset " + memoryCacheImageInputStream.getStreamPosition());
        }
    }

    @Override
    protected void encode(InputStream inputStream, OutputStream outputStream, COSDictionary cOSDictionary) throws IOException {
        List<byte[]> listCreateCodeTable = createCodeTable();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(outputStream);
        memoryCacheImageOutputStream.writeBits(256L, 9);
        byte[] bArrCopyOf = null;
        int i = -1;
        while (true) {
            int i2 = inputStream.read();
            if (i2 == -1) {
                break;
            }
            byte b = (byte) i2;
            if (bArrCopyOf == null) {
                bArrCopyOf = new byte[]{b};
            } else {
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, bArrCopyOf.length + 1);
                bArrCopyOf[bArrCopyOf.length - 1] = b;
                int iFindPatternCode = findPatternCode(listCreateCodeTable, bArrCopyOf);
                if (iFindPatternCode == -1) {
                    int iCalculateChunk = calculateChunk(listCreateCodeTable.size() - 1, 1);
                    memoryCacheImageOutputStream.writeBits(i, iCalculateChunk);
                    listCreateCodeTable.add(bArrCopyOf);
                    if (listCreateCodeTable.size() == 4096) {
                        memoryCacheImageOutputStream.writeBits(256L, iCalculateChunk);
                        listCreateCodeTable = createCodeTable();
                    }
                    bArrCopyOf = new byte[]{b};
                } else {
                    i = iFindPatternCode;
                }
            }
            i = b & UByte.MAX_VALUE;
        }
        if (i != -1) {
            memoryCacheImageOutputStream.writeBits(i, calculateChunk(listCreateCodeTable.size() - 1, 1));
        }
        memoryCacheImageOutputStream.writeBits(257L, calculateChunk(listCreateCodeTable.size(), 1));
        memoryCacheImageOutputStream.writeBits(0L, 7);
        memoryCacheImageOutputStream.flush();
        memoryCacheImageOutputStream.close();
    }

    private int findPatternCode(List<byte[]> list, byte[] bArr) {
        int length = 0;
        int i = -1;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (size <= 257) {
                if (i != -1) {
                    return i;
                }
                if (bArr.length > 1) {
                    return -1;
                }
            }
            byte[] bArr2 = list.get(size);
            if ((i != -1 || bArr2.length > length) && Arrays.equals(bArr2, bArr)) {
                length = bArr2.length;
                i = size;
            }
        }
        return i;
    }

    private List<byte[]> createCodeTable() {
        ArrayList arrayList = new ArrayList(4096);
        for (int i = 0; i < 256; i++) {
            arrayList.add(new byte[]{(byte) (i & 255)});
        }
        arrayList.add(null);
        arrayList.add(null);
        return arrayList;
    }
}
