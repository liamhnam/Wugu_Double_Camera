package com.tom_roush.pdfbox.pdmodel.graphics.image;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.p022io.RandomAccess;
import com.tom_roush.pdfbox.p022io.RandomAccessFile;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.common.PDPageLabelRange;
import com.tom_roush.pdfbox.pdmodel.graphics.color.PDDeviceGray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public final class CCITTFactory {
    private CCITTFactory() {
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, 0);
    }

    @Deprecated
    public static PDImageXObject createFromRandomAccess(PDDocument pDDocument, RandomAccess randomAccess, int i) throws IOException {
        return createFromRandomAccessImpl(pDDocument, randomAccess, i);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file) throws IOException {
        return createFromRandomAccessImpl(pDDocument, new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER), 0);
    }

    public static PDImageXObject createFromFile(PDDocument pDDocument, File file, int i) throws IOException {
        return createFromRandomAccessImpl(pDDocument, new RandomAccessFile(file, PDPageLabelRange.STYLE_ROMAN_LOWER), i);
    }

    private static PDImageXObject createFromRandomAccessImpl(PDDocument pDDocument, RandomAccess randomAccess, int i) throws Throwable {
        COSDictionary cOSDictionary = new COSDictionary();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        extractFromTiff(randomAccess, byteArrayOutputStream, cOSDictionary, i);
        if (byteArrayOutputStream.size() == 0) {
            return null;
        }
        PDImageXObject pDImageXObject = new PDImageXObject(pDDocument, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), COSName.CCITTFAX_DECODE, cOSDictionary.getInt(COSName.COLUMNS), cOSDictionary.getInt(COSName.ROWS), 1, PDDeviceGray.INSTANCE);
        pDImageXObject.getCOSObject().setItem(COSName.DECODE_PARMS, (COSBase) cOSDictionary);
        return pDImageXObject;
    }

    private static void extractFromTiff(RandomAccess randomAccess, OutputStream outputStream, COSDictionary cOSDictionary, int i) throws Throwable {
        try {
            randomAccess.seek(0L);
            char c = (char) randomAccess.read();
            try {
                if (((char) randomAccess.read()) != c) {
                    throw new IOException("Not a valid tiff file");
                }
                if (c != 'M' && c != 'I') {
                    throw new IOException("Not a valid tiff file");
                }
                if (readshort(c, randomAccess) != 42) {
                    throw new IOException("Not a valid tiff file");
                }
                int i2 = readlong(c, randomAccess);
                randomAccess.seek(i2);
                for (int i3 = 0; i3 < i; i3++) {
                    if (readshort(c, randomAccess) > 50) {
                        throw new IOException("Not a valid tiff file");
                    }
                    randomAccess.seek(i2 + 2 + (r10 * 12));
                    i2 = readlong(c, randomAccess);
                    if (i2 != 0) {
                        randomAccess.seek(i2);
                    } else {
                        outputStream.close();
                        return;
                    }
                }
                int i4 = readshort(c, randomAccess);
                if (i4 > 50) {
                    throw new IOException("Not a valid tiff file");
                }
                int i5 = -1000;
                int i6 = 0;
                int i7 = 0;
                for (int i8 = 0; i8 < i4; i8++) {
                    int i9 = readshort(c, randomAccess);
                    int i10 = readshort(c, randomAccess);
                    int i11 = readlong(c, randomAccess);
                    int i12 = readlong(c, randomAccess);
                    if (c == 'M') {
                        if (i10 == 1) {
                            i12 >>= 24;
                        } else if (i10 == 3) {
                            i12 >>= 16;
                        }
                    }
                    if (i9 == 256) {
                        cOSDictionary.setInt(COSName.COLUMNS, i12);
                    } else if (i9 == 257) {
                        cOSDictionary.setInt(COSName.ROWS, i12);
                    } else if (i9 == 259) {
                        if (i12 == 4) {
                            i5 = -1;
                        }
                        if (i12 == 3) {
                            i5 = 0;
                        }
                    } else if (i9 != 262) {
                        if (i9 != 273) {
                            if (i9 != 279) {
                                if (i9 == 292) {
                                    if ((i12 & 1) != 0) {
                                        i5 = 50;
                                    }
                                    if ((i12 & 4) != 0) {
                                        throw new IOException("CCITT Group 3 'uncompressed mode' is not supported");
                                    }
                                    if ((i12 & 2) != 0) {
                                        throw new IOException("CCITT Group 3 'fill bits before EOL' is not supported");
                                    }
                                } else if (i9 != 324) {
                                    if (i9 == 325 && i11 == 1) {
                                        i7 = i12;
                                    }
                                } else if (i11 == 1) {
                                    i6 = i12;
                                }
                            } else if (i11 == 1) {
                                i7 = i12;
                            }
                        } else if (i11 == 1) {
                            i6 = i12;
                        }
                    } else if (i12 == 1) {
                        cOSDictionary.setBoolean(COSName.BLACK_IS_1, true);
                    }
                }
                if (i5 == -1000) {
                    throw new IOException("First image in tiff is not CCITT T4 or T6 compressed");
                }
                if (i6 == 0) {
                    throw new IOException("First image in tiff is not a single tile/strip");
                }
                cOSDictionary.setInt(COSName.f2274K, i5);
                randomAccess.seek(i6);
                byte[] bArr = new byte[8192];
                while (true) {
                    int i13 = randomAccess.read(bArr, 0, Math.min(8192, i7));
                    if (i13 > 0) {
                        i7 -= i13;
                        outputStream.write(bArr, 0, i13);
                    } else {
                        outputStream.close();
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                outputStream.close();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static int readshort(char c, RandomAccess randomAccess) throws IOException {
        int i;
        int i2;
        if (c == 'I') {
            i = randomAccess.read();
            i2 = randomAccess.read() << 8;
        } else {
            i = randomAccess.read() << 8;
            i2 = randomAccess.read();
        }
        return i | i2;
    }

    private static int readlong(char c, RandomAccess randomAccess) throws IOException {
        int i;
        int i2;
        if (c == 'I') {
            i = randomAccess.read() | (randomAccess.read() << 8) | (randomAccess.read() << 16);
            i2 = randomAccess.read() << 24;
        } else {
            i = (randomAccess.read() << 24) | (randomAccess.read() << 16) | (randomAccess.read() << 8);
            i2 = randomAccess.read();
        }
        return i | i2;
    }
}
