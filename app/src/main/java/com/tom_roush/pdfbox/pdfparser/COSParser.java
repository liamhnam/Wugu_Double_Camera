package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.p022io.RandomAccessRead;
import com.tom_roush.pdfbox.pdfparser.XrefTrailerResolver;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public class COSParser extends BaseParser {
    private static final int DEFAULT_TRAIL_BYTECOUNT = 2048;
    private static final String FDF_DEFAULT_VERSION = "1.0";
    private static final String FDF_HEADER = "%FDF-";
    private static final long MINIMUM_SEARCH_OFFSET = 6;
    private static final String PDF_DEFAULT_VERSION = "1.4";
    private static final String PDF_HEADER = "%PDF-";
    private static final int STREAMCOPYBUFLEN = 8192;
    private static final int STRMBUFLEN = 2048;
    public static final String SYSPROP_EOFLOOKUPRANGE = "com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.eofLookupRange";
    public static final String SYSPROP_PARSEMINIMAL = "com.tom_roush.pdfbox.pdfparser.nonSequentialPDFParser.parseMinimal";
    public static final String TMP_FILE_PREFIX = "tmpPDF";

    private static final int f2341X = 120;
    private Map<COSObjectKey, Long> bfSearchCOSObjectKeyOffsets;
    private List<Long> bfSearchXRefStreamsOffsets;
    private List<Long> bfSearchXRefTablesOffsets;
    protected long fileLen;
    protected boolean initialParseDone;
    private boolean isLenient;
    private int readTrailBytes;
    protected SecurityHandler securityHandler;
    protected final RandomAccessRead source;
    private final byte[] streamCopyBuf;
    private final byte[] strmBuf;
    private long trailerOffset;
    protected XrefTrailerResolver xrefTrailerResolver;
    private static final char[] XREF_TABLE = {'x', 'r', 'e', 'f'};
    private static final char[] XREF_STREAM = {'/', 'X', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'e', 'f'};
    private static final char[] STARTXREF = {'s', 't', 'a', 'r', 't', 'x', 'r', 'e', 'f'};
    private static final byte[] ENDSTREAM = {101, 110, 100, 115, 116, 114, 101, 97, 109};
    private static final byte[] ENDOBJ = {101, 110, 100, 111, 98, 106};
    protected static final char[] EOF_MARKER = {'%', '%', 'E', 'O', 'F'};
    protected static final char[] OBJ_MARKER = {'o', 'b', 'j'};

    public COSParser(RandomAccessRead randomAccessRead) {
        super(new RandomAccessSource(randomAccessRead));
        this.strmBuf = new byte[2048];
        this.isLenient = true;
        this.initialParseDone = false;
        this.bfSearchCOSObjectKeyOffsets = null;
        this.bfSearchXRefTablesOffsets = null;
        this.bfSearchXRefStreamsOffsets = null;
        this.securityHandler = null;
        this.readTrailBytes = 2048;
        this.xrefTrailerResolver = new XrefTrailerResolver();
        this.streamCopyBuf = new byte[8192];
        this.source = randomAccessRead;
    }

    public void setEOFLookupRange(int i) {
        if (i > 15) {
            this.readTrailBytes = i;
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.tom_roush.pdfbox.cos.COSDictionary parseXref(long r17) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.parseXref(long):com.tom_roush.pdfbox.cos.COSDictionary");
    }

    private long parseXrefObjStream(long j, boolean z) throws IOException {
        readObjectNumber();
        readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        COSDictionary cOSDictionary = parseCOSDictionary();
        COSStream cOSStream = parseCOSStream(cOSDictionary);
        parseXrefStream(cOSStream, (int) j, z);
        cOSStream.close();
        return cOSDictionary.getLong(COSName.PREV);
    }

    protected final long getStartxrefOffset() throws IOException {
        try {
            long j = this.fileLen;
            int i = this.readTrailBytes;
            if (j < i) {
                i = (int) j;
            }
            byte[] bArr = new byte[i];
            long j2 = j - ((long) i);
            this.source.seek(j2);
            int i2 = 0;
            while (i2 < i) {
                int i3 = i - i2;
                int i4 = this.source.read(bArr, i2, i3);
                if (i4 < 1) {
                    throw new IOException("No more bytes to read for trailing buffer, but expected: " + i3);
                }
                i2 += i4;
            }
            this.source.seek(0L);
            char[] cArr = EOF_MARKER;
            int iLastIndexOf = lastIndexOf(cArr, bArr, i);
            if (iLastIndexOf >= 0) {
                i = iLastIndexOf;
            } else if (this.isLenient) {
                Log.d("PdfBox-Android", "Missing end of file marker '" + new String(cArr) + "'");
            } else {
                throw new IOException("Missing end of file marker '" + new String(cArr) + "'");
            }
            int iLastIndexOf2 = lastIndexOf(STARTXREF, bArr, i);
            long j3 = j2 + ((long) iLastIndexOf2);
            if (iLastIndexOf2 >= 0) {
                return j3;
            }
            if (this.isLenient) {
                Log.d("PdfBox-Android", "Can't find offset for startxref");
                return -1L;
            }
            throw new IOException("Missing 'startxref' marker.");
        } catch (Throwable th) {
            this.source.seek(0L);
            throw th;
        }
    }

    protected int lastIndexOf(char[] cArr, byte[] bArr, int i) {
        int length = cArr.length - 1;
        char c = cArr[length];
        while (true) {
            int i2 = length;
            while (true) {
                i--;
                if (i < 0) {
                    return -1;
                }
                if (bArr[i] == c) {
                    i2--;
                    if (i2 < 0) {
                        return i;
                    }
                    c = cArr[i2];
                } else if (i2 < length) {
                    break;
                }
            }
            c = cArr[length];
        }
    }

    public boolean isLenient() {
        return this.isLenient;
    }

    public void setLenient(boolean z) {
        if (this.initialParseDone) {
            throw new IllegalArgumentException("Cannot change leniency after parsing");
        }
        this.isLenient = z;
    }

    private long getObjectId(COSObject cOSObject) {
        return (cOSObject.getObjectNumber() << 32) | ((long) cOSObject.getGenerationNumber());
    }

    private void addNewToList(Queue<COSBase> queue, Collection<COSBase> collection, Set<Long> set) {
        Iterator<COSBase> it = collection.iterator();
        while (it.hasNext()) {
            addNewToList(queue, it.next(), set);
        }
    }

    private void addNewToList(Queue<COSBase> queue, COSBase cOSBase, Set<Long> set) {
        if (!(cOSBase instanceof COSObject) || set.add(Long.valueOf(getObjectId((COSObject) cOSBase)))) {
            queue.add(cOSBase);
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void parseDictObjects(com.tom_roush.pdfbox.cos.COSDictionary r14, com.tom_roush.pdfbox.cos.COSName... r15) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.parseDictObjects(com.tom_roush.pdfbox.cos.COSDictionary, com.tom_roush.pdfbox.cos.COSName[]):void");
    }

    private void addExcludedToList(COSName[] cOSNameArr, COSDictionary cOSDictionary, Set<Long> set) {
        if (cOSNameArr != null) {
            for (COSName cOSName : cOSNameArr) {
                COSBase item = cOSDictionary.getItem(cOSName);
                if (item instanceof COSObject) {
                    set.add(Long.valueOf(getObjectId((COSObject) item)));
                }
            }
        }
    }

    protected final COSBase parseObjectDynamically(COSObject cOSObject, boolean z) throws IOException {
        return parseObjectDynamically(cOSObject.getObjectNumber(), cOSObject.getGenerationNumber(), z);
    }

    protected COSBase parseObjectDynamically(long j, int i, boolean z) throws IOException {
        COSObjectKey cOSObjectKey = new COSObjectKey(j, i);
        COSObject objectFromPool = this.document.getObjectFromPool(cOSObjectKey);
        if (objectFromPool.getObject() == null) {
            Long l = this.xrefTrailerResolver.getXrefTable().get(cOSObjectKey);
            if (z && (l == null || l.longValue() <= 0)) {
                throw new IOException("Object must be defined and must not be compressed object: " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration());
            }
            if (l == null && this.isLenient && this.bfSearchCOSObjectKeyOffsets == null) {
                bfSearchForObjects();
                Map<COSObjectKey, Long> map = this.bfSearchCOSObjectKeyOffsets;
                if (map != null && !map.isEmpty()) {
                    Log.d("PdfBox-Android", "Add all new read objects from brute force search to the xref table");
                    Map<COSObjectKey, Long> xrefTable = this.xrefTrailerResolver.getXrefTable();
                    for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
                        COSObjectKey key = entry.getKey();
                        if (!xrefTable.containsKey(key)) {
                            xrefTable.put(key, entry.getValue());
                        }
                    }
                    l = xrefTable.get(cOSObjectKey);
                }
            }
            if (l == null) {
                objectFromPool.setObject(COSNull.NULL);
            } else if (l.longValue() > 0) {
                parseFileObject(l, cOSObjectKey, objectFromPool);
            } else {
                parseObjectStream((int) (-l.longValue()));
            }
        }
        return objectFromPool.getObject();
    }

    private void parseFileObject(Long l, COSObjectKey cOSObjectKey, COSObject cOSObject) throws IOException {
        COSBase cOSBase;
        this.source.seek(l.longValue());
        long objectNumber = readObjectNumber();
        int generationNumber = readGenerationNumber();
        readExpectedString(OBJ_MARKER, true);
        if (objectNumber != cOSObjectKey.getNumber() || generationNumber != cOSObjectKey.getGeneration()) {
            throw new IOException("XREF for " + cOSObjectKey.getNumber() + ":" + cOSObjectKey.getGeneration() + " points to wrong object: " + objectNumber + ":" + generationNumber);
        }
        skipSpaces();
        COSBase dirObject = parseDirObject();
        String string = readString();
        if (string.equals("stream")) {
            this.source.rewind(string.getBytes(Charsets.ISO_8859_1).length);
            if (dirObject instanceof COSDictionary) {
                COSStream cOSStream = parseCOSStream((COSDictionary) dirObject);
                SecurityHandler securityHandler = this.securityHandler;
                if (securityHandler != null) {
                    securityHandler.decryptStream(cOSStream, cOSObjectKey.getNumber(), cOSObjectKey.getGeneration());
                }
                skipSpaces();
                string = readLine();
                cOSBase = cOSStream;
                if (!string.startsWith("endobj")) {
                    cOSBase = cOSStream;
                    if (string.startsWith("endstream")) {
                        string = string.substring(9).trim();
                        cOSBase = cOSStream;
                        if (string.length() == 0) {
                            string = readLine();
                            cOSBase = cOSStream;
                        }
                    }
                }
            } else {
                throw new IOException("Stream not preceded by dictionary (offset: " + l + ").");
            }
        } else {
            SecurityHandler securityHandler2 = this.securityHandler;
            cOSBase = dirObject;
            if (securityHandler2 != null) {
                securityHandler2.decrypt(dirObject, cOSObjectKey.getNumber(), cOSObjectKey.getGeneration());
                cOSBase = dirObject;
            }
        }
        cOSObject.setObject(cOSBase);
        if (string.startsWith("endobj")) {
            return;
        }
        if (this.isLenient) {
            Log.w("PdfBox-Android", "Object (" + objectNumber + ":" + generationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + string + "'");
            return;
        }
        throw new IOException("Object (" + objectNumber + ":" + generationNumber + ") at offset " + l + " does not end with 'endobj' but with '" + string + "'");
    }

    private void parseObjectStream(int i) throws IOException {
        COSBase objectDynamically = parseObjectDynamically(i, 0, true);
        if (objectDynamically instanceof COSStream) {
            PDFObjectStreamParser pDFObjectStreamParser = new PDFObjectStreamParser((COSStream) objectDynamically, this.document);
            try {
                pDFObjectStreamParser.parse();
                Set<Long> containedObjectNumbers = this.xrefTrailerResolver.getContainedObjectNumbers(i);
                for (COSObject cOSObject : pDFObjectStreamParser.getObjects()) {
                    COSObjectKey cOSObjectKey = new COSObjectKey(cOSObject);
                    if (containedObjectNumbers.contains(Long.valueOf(cOSObjectKey.getNumber()))) {
                        this.document.getObjectFromPool(cOSObjectKey).setObject(cOSObject.getObject());
                    }
                }
            } catch (IOException e) {
                if (this.isLenient) {
                    Log.d("PdfBox-Android", "Stop reading object stream " + i + " due to an exception", e);
                    return;
                }
                throw e;
            }
        }
    }

    private COSNumber getLength(COSBase cOSBase, COSName cOSName) throws IOException {
        if (cOSBase == null) {
            return null;
        }
        if (cOSBase instanceof COSNumber) {
            return (COSNumber) cOSBase;
        }
        if (cOSBase instanceof COSObject) {
            COSObject cOSObject = (COSObject) cOSBase;
            if (cOSObject.getObject() == null) {
                long position = this.source.getPosition();
                parseObjectDynamically(cOSObject, COSName.OBJ_STM.equals(cOSName));
                this.source.seek(position);
                if (cOSObject.getObject() == null) {
                    throw new IOException("Length object content was not read.");
                }
            }
            if (!(cOSObject.getObject() instanceof COSNumber)) {
                throw new IOException("Wrong type of referenced length object " + cOSObject + ": " + cOSObject.getObject().getClass().getSimpleName());
            }
            return (COSNumber) cOSObject.getObject();
        }
        throw new IOException("Wrong type of length object: " + cOSBase.getClass().getSimpleName());
    }

    protected COSStream parseCOSStream(COSDictionary cOSDictionary) throws IOException {
        COSStream cOSStreamCreateCOSStream = this.document.createCOSStream(cOSDictionary);
        readString();
        skipWhiteSpaces();
        COSNumber length = getLength(cOSDictionary.getItem(COSName.LENGTH), cOSDictionary.getCOSName(COSName.TYPE));
        if (length == null) {
            if (this.isLenient) {
                Log.w("PdfBox-Android", "The stream doesn't provide any stream length, using fallback readUntilEnd, at offset " + this.source.getPosition());
            } else {
                throw new IOException("Missing length for stream.");
            }
        }
        if (length != null && validateStreamLength(length.longValue())) {
            OutputStream outputStreamCreateRawOutputStream = cOSStreamCreateCOSStream.createRawOutputStream();
            try {
                readValidStream(outputStreamCreateRawOutputStream, length);
            } finally {
                outputStreamCreateRawOutputStream.close();
                cOSStreamCreateCOSStream.setItem(COSName.LENGTH, (COSBase) length);
            }
        } else {
            OutputStream outputStreamCreateRawOutputStream2 = cOSStreamCreateCOSStream.createRawOutputStream();
            try {
                readUntilEndStream(new EndstreamOutputStream(outputStreamCreateRawOutputStream2));
            } finally {
                outputStreamCreateRawOutputStream2.close();
                if (length != null) {
                    cOSStreamCreateCOSStream.setItem(COSName.LENGTH, (COSBase) length);
                } else {
                    cOSStreamCreateCOSStream.removeItem(COSName.LENGTH);
                }
            }
        }
        String string = readString();
        if (string.equals("endobj") && this.isLenient) {
            Log.w("PdfBox-Android", "stream ends with 'endobj' instead of 'endstream' at offset " + this.source.getPosition());
            this.source.rewind(ENDOBJ.length);
        } else if (string.length() > 9 && this.isLenient && string.substring(0, 9).equals("endstream")) {
            Log.w("PdfBox-Android", "stream ends with '" + string + "' instead of 'endstream' at offset " + this.source.getPosition());
            this.source.rewind(string.substring(9).getBytes(Charsets.ISO_8859_1).length);
        } else if (!string.equals("endstream")) {
            throw new IOException("Error reading stream, expected='endstream' actual='" + string + "' at offset " + this.source.getPosition());
        }
        return cOSStreamCreateCOSStream;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void readUntilEndStream(java.io.OutputStream r12) throws java.io.IOException {
        /*
            r11 = this;
            byte[] r0 = com.tom_roush.pdfbox.pdfparser.COSParser.ENDSTREAM
            r1 = 0
            r2 = r1
        L4:
            com.tom_roush.pdfbox.io.RandomAccessRead r3 = r11.source
            byte[] r4 = r11.strmBuf
            int r5 = 2048 - r2
            int r3 = r3.read(r4, r2, r5)
            if (r3 <= 0) goto L7b
            int r3 = r3 + r2
            int r4 = r3 + (-5)
            r5 = r2
        L14:
            if (r2 >= r3) goto L5e
            int r6 = r2 + 5
            r7 = 1
            if (r5 != 0) goto L2b
            if (r6 >= r4) goto L2b
            byte[] r8 = r11.strmBuf
            r8 = r8[r6]
            r9 = 116(0x74, float:1.63E-43)
            if (r8 > r9) goto L29
            r9 = 97
            if (r8 >= r9) goto L2b
        L29:
            r2 = r6
            goto L5c
        L2b:
            byte[] r6 = r11.strmBuf
            r6 = r6[r2]
            r8 = r0[r5]
            if (r6 != r8) goto L3b
            int r5 = r5 + 1
            int r6 = r0.length
            if (r5 != r6) goto L5c
            int r2 = r2 + 1
            goto L5e
        L3b:
            r0 = 3
            if (r5 != r0) goto L47
            byte[] r0 = com.tom_roush.pdfbox.pdfparser.COSParser.ENDOBJ
            r8 = r0[r5]
            if (r6 != r8) goto L47
            int r5 = r5 + 1
            goto L5c
        L47:
            r0 = 101(0x65, float:1.42E-43)
            if (r6 != r0) goto L4d
            r0 = r7
            goto L57
        L4d:
            r0 = 110(0x6e, float:1.54E-43)
            if (r6 != r0) goto L56
            r0 = 7
            if (r5 != r0) goto L56
            r0 = 2
            goto L57
        L56:
            r0 = r1
        L57:
            byte[] r5 = com.tom_roush.pdfbox.pdfparser.COSParser.ENDSTREAM
            r10 = r5
            r5 = r0
            r0 = r10
        L5c:
            int r2 = r2 + r7
            goto L14
        L5e:
            int r2 = r2 - r5
            int r2 = java.lang.Math.max(r1, r2)
            if (r2 <= 0) goto L6a
            byte[] r4 = r11.strmBuf
            r12.write(r4, r1, r2)
        L6a:
            int r4 = r0.length
            if (r5 != r4) goto L74
            com.tom_roush.pdfbox.io.RandomAccessRead r0 = r11.source
            int r3 = r3 - r2
            r0.rewind(r3)
            goto L7b
        L74:
            byte[] r2 = r11.strmBuf
            java.lang.System.arraycopy(r0, r1, r2, r1, r5)
            r2 = r5
            goto L4
        L7b:
            r12.flush()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.COSParser.readUntilEndStream(java.io.OutputStream):void");
    }

    private void readValidStream(OutputStream outputStream, COSNumber cOSNumber) throws IOException {
        long jLongValue = cOSNumber.longValue();
        while (jLongValue > 0) {
            int i = jLongValue > 8192 ? 8192 : (int) jLongValue;
            int i2 = this.source.read(this.streamCopyBuf, 0, i);
            if (i2 <= 0) {
                throw new IOException("read error at offset " + this.source.getPosition() + ": expected " + i + " bytes, but read() returns " + i2);
            }
            outputStream.write(this.streamCopyBuf, 0, i2);
            jLongValue -= (long) i2;
        }
    }

    private boolean validateStreamLength(long j) throws IOException {
        long position = this.source.getPosition();
        long j2 = position + j;
        boolean z = false;
        if (j2 > this.fileLen) {
            Log.w("PdfBox-Android", "The end of the stream is out of range, using workaround to read the stream, stream start position: " + position + ", length: " + j + ", expected end position: " + j2);
        } else {
            this.source.seek(j2);
            skipSpaces();
            if (isString(ENDSTREAM)) {
                z = true;
            } else {
                Log.w("PdfBox-Android", "The end of the stream doesn't point to the correct offset, using workaround to read the stream, stream start position: " + position + ", length: " + j + ", expected end position: " + j2);
            }
            this.source.seek(position);
        }
        return z;
    }

    private long checkXRefOffset(long j) throws IOException {
        if (!this.isLenient) {
            return j;
        }
        this.source.seek(j);
        if (this.source.peek() == 120 && isString(XREF_TABLE)) {
            return j;
        }
        if (j > 0) {
            long jCheckXRefStreamOffset = checkXRefStreamOffset(j, true);
            if (jCheckXRefStreamOffset > -1) {
                return jCheckXRefStreamOffset;
            }
        }
        return calculateXRefFixedOffset(j, false);
    }

    private long checkXRefStreamOffset(long j, boolean z) throws IOException {
        if (!this.isLenient || j == 0) {
            return j;
        }
        this.source.seek(j - 1);
        if (isWhitespace(this.source.read())) {
            skipSpaces();
            if (isDigit()) {
                try {
                    readObjectNumber();
                    readGenerationNumber();
                    readExpectedString(OBJ_MARKER, true);
                    this.source.seek(j);
                    return j;
                } catch (IOException unused) {
                    this.source.seek(j);
                }
            }
        }
        if (z) {
            return -1L;
        }
        return calculateXRefFixedOffset(j, true);
    }

    private long calculateXRefFixedOffset(long j, boolean z) throws IOException {
        if (j < 0) {
            Log.e("PdfBox-Android", "Invalid object offset " + j + " when searching for a xref table/stream");
            return 0L;
        }
        long jBfSearchForXRef = bfSearchForXRef(j, z);
        if (jBfSearchForXRef > -1) {
            Log.d("PdfBox-Android", "Fixed reference for xref table/stream " + j + " -> " + jBfSearchForXRef);
            return jBfSearchForXRef;
        }
        Log.e("PdfBox-Android", "Can't find the object axref table/stream at offset " + j);
        return 0L;
    }

    private void checkXrefOffsets() throws IOException {
        Map<COSObjectKey, Long> xrefTable;
        boolean z;
        if (this.isLenient && (xrefTable = this.xrefTrailerResolver.getXrefTable()) != null) {
            Iterator<Map.Entry<COSObjectKey, Long>> it = xrefTable.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Map.Entry<COSObjectKey, Long> next = it.next();
                COSObjectKey key = next.getKey();
                Long value = next.getValue();
                if (value != null && value.longValue() >= 0 && !checkObjectKeys(key, value.longValue())) {
                    Log.d("PdfBox-Android", "Stop checking xref offsets as at least one couldn't be dereferenced");
                    z = true;
                    break;
                }
            }
            if (z) {
                bfSearchForObjects();
                Map<COSObjectKey, Long> map = this.bfSearchCOSObjectKeyOffsets;
                if (map == null || map.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                Iterator<COSObjectKey> it2 = xrefTable.keySet().iterator();
                while (it2.hasNext()) {
                    Long l = xrefTable.get(it2.next());
                    if (l != null && l.longValue() < 0 && !arrayList.contains(new COSObjectKey(-l.longValue(), 0))) {
                        arrayList.add(new COSObjectKey(-l.longValue(), 0));
                    }
                }
                Iterator<COSObjectKey> it3 = this.bfSearchCOSObjectKeyOffsets.keySet().iterator();
                while (it3.hasNext()) {
                    arrayList.remove(it3.next());
                }
                Iterator it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    Iterator<Long> it5 = this.xrefTrailerResolver.getContainedObjectNumbers((int) ((COSObjectKey) it4.next()).getNumber()).iterator();
                    while (it5.hasNext()) {
                        xrefTable.remove(new COSObjectKey(it5.next().longValue(), 0));
                    }
                }
                Log.d("PdfBox-Android", "Replaced read xref table with the results of a brute force search");
                xrefTable.putAll(this.bfSearchCOSObjectKeyOffsets);
            }
        }
    }

    private boolean checkObjectKeys(COSObjectKey cOSObjectKey, long j) throws IOException {
        if (j < MINIMUM_SEARCH_OFFSET) {
            return false;
        }
        long number = cOSObjectKey.getNumber();
        int generation = cOSObjectKey.getGeneration();
        long position = this.source.getPosition();
        this.source.seek(j);
        try {
            if (isString(createObjectString(number, generation).getBytes(Charsets.ISO_8859_1))) {
                this.source.seek(position);
                this.source.seek(position);
                return true;
            }
        } catch (IOException unused) {
        } catch (Throwable th) {
            this.source.seek(position);
            throw th;
        }
        this.source.seek(position);
        return false;
    }

    private String createObjectString(long j, int i) {
        return Long.toString(j) + " " + Integer.toString(i) + " obj";
    }

    private void bfSearchForObjects() throws IOException {
        Long lValueOf;
        if (this.bfSearchCOSObjectKeyOffsets == null) {
            this.bfSearchCOSObjectKeyOffsets = new HashMap();
            long position = this.source.getPosition();
            char[] charArray = " obj".toCharArray();
            long j = MINIMUM_SEARCH_OFFSET;
            long j2 = 6;
            while (true) {
                this.source.seek(j2);
                long jFindString = findString(charArray);
                if (jFindString < 0) {
                    break;
                }
                long length = jFindString - ((long) charArray.length);
                if (jFindString >= 0) {
                    long j3 = length - 1;
                    this.source.seek(j3);
                    int iPeek = this.source.peek();
                    if (isDigit(iPeek)) {
                        int i = iPeek - 48;
                        long j4 = j3 - 1;
                        this.source.seek(j4);
                        if (isSpace()) {
                            while (j4 > j && isSpace()) {
                                j4--;
                                this.source.seek(j4);
                            }
                            int i2 = 0;
                            while (j4 > j && isDigit()) {
                                j4--;
                                this.source.seek(j4);
                                i2++;
                            }
                            if (i2 > 0) {
                                this.source.read();
                                byte[] fully = this.source.readFully(i2);
                                try {
                                    lValueOf = Long.valueOf(new String(fully, 0, fully.length, Charsets.ISO_8859_1));
                                } catch (NumberFormatException unused) {
                                    lValueOf = null;
                                }
                                if (lValueOf != null) {
                                    this.bfSearchCOSObjectKeyOffsets.put(new COSObjectKey(lValueOf.longValue(), i), Long.valueOf(j4 + 1));
                                }
                            }
                        }
                    }
                }
                j2 = jFindString + 1;
                if (this.source.isEOF()) {
                    break;
                } else {
                    j = MINIMUM_SEARCH_OFFSET;
                }
            }
            this.source.seek(position);
        }
    }

    private long bfSearchForXRef(long j, boolean z) throws IOException {
        List<Long> list;
        if (!z) {
            bfSearchForXRefTables();
        }
        bfSearchForXRefStreams();
        long jSearchNearestValue = (z || (list = this.bfSearchXRefTablesOffsets) == null) ? -1L : searchNearestValue(list, j);
        List<Long> list2 = this.bfSearchXRefStreamsOffsets;
        long jSearchNearestValue2 = list2 != null ? searchNearestValue(list2, j) : -1L;
        if (jSearchNearestValue > -1 && jSearchNearestValue2 > -1) {
            if (Math.abs(j - jSearchNearestValue) > Math.abs(j - jSearchNearestValue2)) {
                this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(jSearchNearestValue2));
                return jSearchNearestValue2;
            }
            this.bfSearchXRefTablesOffsets.remove(Long.valueOf(jSearchNearestValue));
            return jSearchNearestValue;
        }
        if (jSearchNearestValue > -1) {
            this.bfSearchXRefTablesOffsets.remove(Long.valueOf(jSearchNearestValue));
            return jSearchNearestValue;
        }
        if (jSearchNearestValue2 <= -1) {
            return -1L;
        }
        this.bfSearchXRefStreamsOffsets.remove(Long.valueOf(jSearchNearestValue2));
        return jSearchNearestValue2;
    }

    private long searchNearestValue(List<Long> list, long j) {
        int size = list.size();
        long j2 = -1;
        int i = -1;
        for (int i2 = 0; i2 < size; i2++) {
            long jLongValue = j - list.get(i2).longValue();
            if (j2 == -1 || Math.abs(j2) > Math.abs(jLongValue)) {
                i = i2;
                j2 = jLongValue;
            }
        }
        if (i > -1) {
            return list.get(i).longValue();
        }
        return -1L;
    }

    private void bfSearchForXRefTables() throws IOException {
        if (this.bfSearchXRefTablesOffsets == null) {
            this.bfSearchXRefTablesOffsets = new Vector();
            long position = this.source.getPosition();
            this.source.seek(MINIMUM_SEARCH_OFFSET);
            while (!this.source.isEOF()) {
                if (isString(XREF_TABLE)) {
                    long position2 = this.source.getPosition();
                    this.source.seek(position2 - 1);
                    if (isWhitespace()) {
                        this.bfSearchXRefTablesOffsets.add(Long.valueOf(position2));
                    }
                    this.source.seek(position2 + 4);
                }
                this.source.read();
            }
            this.source.seek(position);
        }
    }

    private void bfSearchForXRefStreams() throws IOException {
        if (this.bfSearchXRefStreamsOffsets == null) {
            this.bfSearchXRefStreamsOffsets = new Vector();
            long position = this.source.getPosition();
            this.source.seek(MINIMUM_SEARCH_OFFSET);
            char[] charArray = " obj".toCharArray();
            while (!this.source.isEOF()) {
                if (isString(XREF_STREAM)) {
                    long position2 = this.source.getPosition();
                    boolean z = false;
                    long position3 = -1;
                    for (int i = 1; i < 30 && !z; i++) {
                        long j = position2 - ((long) (i * 10));
                        if (j > 0) {
                            this.source.seek(j);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= 10) {
                                    break;
                                }
                                if (isString(charArray)) {
                                    long j2 = j - 1;
                                    this.source.seek(j2);
                                    if (isDigit(this.source.peek())) {
                                        long j3 = j2 - 1;
                                        this.source.seek(j3);
                                        if (isSpace()) {
                                            long j4 = j3 - 1;
                                            this.source.seek(j4);
                                            int i3 = 0;
                                            while (j4 > MINIMUM_SEARCH_OFFSET && isDigit()) {
                                                j4--;
                                                this.source.seek(j4);
                                                i3++;
                                            }
                                            if (i3 > 0) {
                                                this.source.read();
                                                position3 = this.source.getPosition();
                                            }
                                        }
                                    }
                                    Log.d("PdfBox-Android", "Fixed reference for xref stream " + position2 + " -> " + position3);
                                    z = true;
                                } else {
                                    j++;
                                    this.source.read();
                                    i2++;
                                }
                            }
                        }
                    }
                    if (position3 > -1) {
                        this.bfSearchXRefStreamsOffsets.add(Long.valueOf(position3));
                    }
                    this.source.seek(position2 + 5);
                }
                this.source.read();
            }
            this.source.seek(position);
        }
    }

    protected final COSDictionary rebuildTrailer() throws IOException {
        bfSearchForObjects();
        if (this.bfSearchCOSObjectKeyOffsets == null) {
            return null;
        }
        this.xrefTrailerResolver.nextXrefObj(0L, XrefTrailerResolver.XRefType.TABLE);
        for (Map.Entry<COSObjectKey, Long> entry : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
            this.xrefTrailerResolver.setXRef(entry.getKey(), entry.getValue().longValue());
        }
        this.xrefTrailerResolver.setStartxref(0L);
        COSDictionary trailer = this.xrefTrailerResolver.getTrailer();
        getDocument().setTrailer(trailer);
        for (Map.Entry<COSObjectKey, Long> entry2 : this.bfSearchCOSObjectKeyOffsets.entrySet()) {
            this.source.seek(entry2.getValue().longValue());
            readObjectNumber();
            readGenerationNumber();
            readExpectedString(OBJ_MARKER, true);
            try {
                COSDictionary cOSDictionary = parseCOSDictionary();
                if (cOSDictionary != null) {
                    if (COSName.CATALOG.equals(cOSDictionary.getCOSName(COSName.TYPE))) {
                        trailer.setItem(COSName.ROOT, (COSBase) this.document.getObjectFromPool(entry2.getKey()));
                    } else if (cOSDictionary.containsKey(COSName.MOD_DATE) && (cOSDictionary.containsKey(COSName.TITLE) || cOSDictionary.containsKey(COSName.AUTHOR) || cOSDictionary.containsKey(COSName.SUBJECT) || cOSDictionary.containsKey(COSName.KEYWORDS) || cOSDictionary.containsKey(COSName.CREATOR) || cOSDictionary.containsKey(COSName.PRODUCER) || cOSDictionary.containsKey(COSName.CREATION_DATE))) {
                        trailer.setItem(COSName.INFO, (COSBase) this.document.getObjectFromPool(entry2.getKey()));
                    }
                }
            } catch (IOException unused) {
                Log.d("PdfBox-Android", "Skipped object " + entry2.getKey() + ", either it's corrupt or not a dictionary");
            }
        }
        return trailer;
    }

    private long parseStartXref() throws IOException {
        if (!isString(STARTXREF)) {
            return -1L;
        }
        readString();
        skipSpaces();
        return readLong();
    }

    private boolean isString(byte[] bArr) throws IOException {
        if (this.source.peek() != bArr[0]) {
            return false;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        int i = this.source.read(bArr2, 0, length);
        while (i < length) {
            int i2 = this.source.read(bArr2, i, length - i);
            if (i2 < 0) {
                break;
            }
            i += i2;
        }
        boolean zEquals = Arrays.equals(bArr, bArr2);
        this.source.rewind(i);
        return zEquals;
    }

    private boolean isString(char[] cArr) throws IOException {
        long position = this.source.getPosition();
        for (char c : cArr) {
            if (this.source.read() != c) {
                this.source.seek(position);
                return false;
            }
        }
        this.source.seek(position);
        return true;
    }

    private long findString(char[] cArr) throws IOException {
        char c = cArr[0];
        while (true) {
            if (this.source.read() == c || this.source.isEOF()) {
                char c2 = cArr[1];
                int i = 1;
                while (!this.source.isEOF() && this.source.read() == c2 && i < cArr.length - 1) {
                    i++;
                    c2 = cArr[i];
                }
                if (i == cArr.length - 1) {
                    return this.source.getPosition();
                }
                c = cArr[0];
                if (this.source.isEOF()) {
                    return -1L;
                }
            }
        }
    }

    private boolean parseTrailer() throws IOException {
        if (this.source.peek() != 116) {
            return false;
        }
        long position = this.source.getPosition();
        String line = readLine();
        if (!line.trim().equals("trailer")) {
            if (!line.startsWith("trailer")) {
                return false;
            }
            this.source.seek(position + ((long) 7));
        }
        skipSpaces();
        this.xrefTrailerResolver.setTrailer(parseCOSDictionary());
        skipSpaces();
        return true;
    }

    protected boolean parsePDFHeader() throws IOException {
        return parseHeader(PDF_HEADER, PDF_DEFAULT_VERSION);
    }

    protected boolean parseFDFHeader() throws IOException {
        return parseHeader(FDF_HEADER, "1.0");
    }

    private boolean parseHeader(String str, String str2) throws IOException {
        String line = readLine();
        if (!line.contains(str)) {
            line = readLine();
            while (!line.contains(str) && (line.length() <= 0 || !Character.isDigit(line.charAt(0)))) {
                line = readLine();
            }
        }
        if (!line.contains(str)) {
            this.source.seek(0L);
            return false;
        }
        int iIndexOf = line.indexOf(str);
        if (iIndexOf > 0) {
            line = line.substring(iIndexOf, line.length());
        }
        if (line.startsWith(str) && !line.matches(str + "\\d.\\d")) {
            if (line.length() < str.length() + 3) {
                line = str + str2;
                Log.d("PdfBox-Android", "No version found, set to " + str2 + " as default.");
            }
        } else {
            String str3 = line.substring(str.length() + 3, line.length()) + "\n";
            line = line.substring(0, str.length() + 3);
            this.source.rewind(str3.getBytes(Charsets.ISO_8859_1).length);
        }
        float f = -1.0f;
        try {
            String[] strArrSplit = line.split("-");
            if (strArrSplit.length == 2) {
                f = Float.parseFloat(strArrSplit[1]);
            }
        } catch (NumberFormatException e) {
            Log.d("PdfBox-Android", "Can't parse the header version.", e);
        }
        if (f < 0.0f) {
            throw new IOException("Error getting header version: " + line);
        }
        this.document.setVersion(f);
        this.source.seek(0L);
        return true;
    }

    protected boolean parseXrefTable(long j) throws IOException {
        if (this.source.peek() != 120 || !readString().trim().equals("xref")) {
            return false;
        }
        String string = readString();
        this.source.rewind(string.getBytes(Charsets.ISO_8859_1).length);
        this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.TABLE);
        if (string.startsWith("trailer")) {
            Log.w("PdfBox-Android", "skipping empty xref table");
            return false;
        }
        do {
            long objectNumber = readObjectNumber();
            long j2 = readLong();
            skipSpaces();
            int i = 0;
            while (true) {
                if (i >= j2 || this.source.isEOF() || isEndOfName((char) this.source.peek()) || this.source.peek() == 116) {
                    break;
                }
                String line = readLine();
                String[] strArrSplit = line.split("\\s");
                if (strArrSplit.length < 3) {
                    Log.w("PdfBox-Android", "invalid xref line: " + line);
                    break;
                }
                if (strArrSplit[strArrSplit.length - 1].equals("n")) {
                    try {
                        this.xrefTrailerResolver.setXRef(new COSObjectKey(objectNumber, Integer.parseInt(strArrSplit[1])), Long.parseLong(strArrSplit[0]));
                    } catch (NumberFormatException e) {
                        throw new IOException(e);
                    }
                } else if (!strArrSplit[2].equals(Media.f730f)) {
                    throw new IOException("Corrupt XRefTable Entry - ObjID:" + objectNumber);
                }
                objectNumber++;
                skipSpaces();
                i++;
            }
            skipSpaces();
        } while (isDigit());
        return true;
    }

    private void parseXrefStream(COSStream cOSStream, long j, boolean z) throws IOException {
        if (z) {
            this.xrefTrailerResolver.nextXrefObj(j, XrefTrailerResolver.XRefType.STREAM);
            this.xrefTrailerResolver.setTrailer(cOSStream);
        }
        new PDFXrefStreamParser(cOSStream, this.document, this.xrefTrailerResolver).parse();
    }

    public COSDocument getDocument() throws IOException {
        if (this.document == null) {
            throw new IOException("You must call parse() before calling getDocument()");
        }
        return this.document;
    }

    protected COSBase parseTrailerValuesDynamically(COSDictionary cOSDictionary) throws IOException {
        for (COSBase cOSBase : cOSDictionary.getValues()) {
            if (cOSBase instanceof COSObject) {
                parseObjectDynamically((COSObject) cOSBase, false);
            }
        }
        COSObject cOSObject = (COSObject) cOSDictionary.getItem(COSName.ROOT);
        if (cOSObject == null) {
            throw new IOException("Missing root object specification in trailer.");
        }
        return parseObjectDynamically(cOSObject, false);
    }
}
