package com.tom_roush.pdfbox.pdfwriter;

import android.util.Log;
import com.p020hp.jipp.model.Media;
import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.cos.COSUpdateInfo;
import com.tom_roush.pdfbox.cos.ICOSVisitor;
import com.tom_roush.pdfbox.p022io.IOUtils;
import com.tom_roush.pdfbox.p022io.RandomAccessBuffer;
import com.tom_roush.pdfbox.p022io.RandomAccessInputStream;
import com.tom_roush.pdfbox.p022io.RandomAccessRead;
import com.tom_roush.pdfbox.pdfparser.PDFXRefStream;
import com.tom_roush.pdfbox.pdmodel.PDDocument;
import com.tom_roush.pdfbox.pdmodel.encryption.SecurityHandler;
import com.tom_roush.pdfbox.pdmodel.fdf.FDFDocument;
import com.tom_roush.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.Hex;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class COSWriter implements ICOSVisitor, Closeable {
    private final Set<COSBase> actualsAdded;
    private long byteRangeLength;
    private long byteRangeOffset;
    private COSObjectKey currentObjectKey;
    private FDFDocument fdfDocument;
    private final NumberFormat formatDecimal;
    private final NumberFormat formatXrefGeneration;
    private final NumberFormat formatXrefOffset;
    private RandomAccessRead incrementalInput;
    private OutputStream incrementalOutput;
    private boolean incrementalUpdate;
    private final Map<COSObjectKey, COSBase> keyObject;
    private long number;
    private final Map<COSBase, COSObjectKey> objectKeys;
    private final Deque<COSBase> objectsToWrite;
    private final Set<COSBase> objectsToWriteSet;
    private OutputStream output;
    private PDDocument pdDocument;
    private boolean reachedSignature;
    private SignatureInterface signatureInterface;
    private long signatureLength;
    private long signatureOffset;
    private COSStandardOutputStream standardOutput;
    private long startxref;
    private RandomAccessRead tempIncInput;
    private boolean willEncrypt;
    private final Set<COSBase> writtenObjects;
    private final List<COSWriterXRefEntry> xRefEntries;
    public static final byte[] DICT_OPEN = "<<".getBytes(Charsets.US_ASCII);
    public static final byte[] DICT_CLOSE = ">>".getBytes(Charsets.US_ASCII);
    public static final byte[] SPACE = {32};
    public static final byte[] COMMENT = {37};
    public static final byte[] VERSION = "PDF-1.4".getBytes(Charsets.US_ASCII);
    public static final byte[] GARBAGE = {-10, -28, -4, -33};
    public static final byte[] EOF = "%%EOF".getBytes(Charsets.US_ASCII);
    public static final byte[] REFERENCE = "R".getBytes(Charsets.US_ASCII);
    public static final byte[] XREF = "xref".getBytes(Charsets.US_ASCII);
    public static final byte[] XREF_FREE = Media.f730f.getBytes(Charsets.US_ASCII);
    public static final byte[] XREF_USED = "n".getBytes(Charsets.US_ASCII);
    public static final byte[] TRAILER = "trailer".getBytes(Charsets.US_ASCII);
    public static final byte[] STARTXREF = "startxref".getBytes(Charsets.US_ASCII);
    public static final byte[] OBJ = "obj".getBytes(Charsets.US_ASCII);
    public static final byte[] ENDOBJ = "endobj".getBytes(Charsets.US_ASCII);
    public static final byte[] ARRAY_OPEN = "[".getBytes(Charsets.US_ASCII);
    public static final byte[] ARRAY_CLOSE = "]".getBytes(Charsets.US_ASCII);
    public static final byte[] STREAM = "stream".getBytes(Charsets.US_ASCII);
    public static final byte[] ENDSTREAM = "endstream".getBytes(Charsets.US_ASCII);

    public COSWriter(OutputStream outputStream) {
        this.formatXrefOffset = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatXrefGeneration = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
        NumberFormat numberInstance = NumberFormat.getNumberInstance(Locale.US);
        this.formatDecimal = numberInstance;
        this.startxref = 0L;
        this.number = 0L;
        this.objectKeys = new Hashtable();
        this.keyObject = new Hashtable();
        this.xRefEntries = new ArrayList();
        this.objectsToWriteSet = new HashSet();
        this.objectsToWrite = new LinkedList();
        this.writtenObjects = new HashSet();
        this.actualsAdded = new HashSet();
        this.currentObjectKey = null;
        this.pdDocument = null;
        this.fdfDocument = null;
        this.willEncrypt = false;
        this.incrementalUpdate = false;
        this.reachedSignature = false;
        setOutput(outputStream);
        setStandardOutput(new COSStandardOutputStream(this.output));
        numberInstance.setMaximumFractionDigits(10);
        numberInstance.setGroupingUsed(false);
    }

    public COSWriter(OutputStream outputStream, InputStream inputStream) throws IOException {
        this.formatXrefOffset = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatXrefGeneration = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatDecimal = NumberFormat.getNumberInstance(Locale.US);
        this.startxref = 0L;
        this.number = 0L;
        this.objectKeys = new Hashtable();
        this.keyObject = new Hashtable();
        this.xRefEntries = new ArrayList();
        this.objectsToWriteSet = new HashSet();
        this.objectsToWrite = new LinkedList();
        this.writtenObjects = new HashSet();
        this.actualsAdded = new HashSet();
        this.currentObjectKey = null;
        this.pdDocument = null;
        this.fdfDocument = null;
        this.willEncrypt = false;
        this.incrementalUpdate = false;
        this.reachedSignature = false;
        RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(inputStream);
        this.tempIncInput = randomAccessBuffer;
        initWriter(outputStream, randomAccessBuffer);
    }

    public COSWriter(OutputStream outputStream, RandomAccessRead randomAccessRead) throws IOException {
        this.formatXrefOffset = new DecimalFormat("0000000000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatXrefGeneration = new DecimalFormat("00000", DecimalFormatSymbols.getInstance(Locale.US));
        this.formatDecimal = NumberFormat.getNumberInstance(Locale.US);
        this.startxref = 0L;
        this.number = 0L;
        this.objectKeys = new Hashtable();
        this.keyObject = new Hashtable();
        this.xRefEntries = new ArrayList();
        this.objectsToWriteSet = new HashSet();
        this.objectsToWrite = new LinkedList();
        this.writtenObjects = new HashSet();
        this.actualsAdded = new HashSet();
        this.currentObjectKey = null;
        this.pdDocument = null;
        this.fdfDocument = null;
        this.willEncrypt = false;
        this.incrementalUpdate = false;
        this.reachedSignature = false;
        initWriter(outputStream, randomAccessRead);
    }

    private void initWriter(OutputStream outputStream, RandomAccessRead randomAccessRead) throws IOException {
        setOutput(new ByteArrayOutputStream());
        setStandardOutput(new COSStandardOutputStream(this.output, (int) randomAccessRead.length()));
        this.incrementalInput = randomAccessRead;
        this.incrementalOutput = outputStream;
        this.incrementalUpdate = true;
        this.formatDecimal.setMaximumFractionDigits(10);
        this.formatDecimal.setGroupingUsed(false);
    }

    private void prepareIncrement(PDDocument pDDocument) {
        if (pDDocument != null) {
            try {
                COSDocument document = pDDocument.getDocument();
                long j = 0;
                for (COSObjectKey cOSObjectKey : document.getXrefTable().keySet()) {
                    COSBase object = document.getObjectFromPool(cOSObjectKey).getObject();
                    if (object != null && cOSObjectKey != null && !(object instanceof COSNumber)) {
                        this.objectKeys.put(object, cOSObjectKey);
                        this.keyObject.put(cOSObjectKey, object);
                    }
                    if (cOSObjectKey != null) {
                        long number = cOSObjectKey.getNumber();
                        if (number > j) {
                            j = number;
                        }
                    }
                }
                setNumber(j);
            } catch (IOException e) {
                Log.e("PdfBox-Android", e.getMessage(), e);
            }
        }
    }

    protected void addXRefEntry(COSWriterXRefEntry cOSWriterXRefEntry) {
        getXRefEntries().add(cOSWriterXRefEntry);
    }

    @Override
    public void close() throws IOException {
        if (getStandardOutput() != null) {
            getStandardOutput().close();
        }
        if (getOutput() != null) {
            getOutput().close();
        }
        OutputStream outputStream = this.incrementalOutput;
        if (outputStream != null) {
            outputStream.close();
        }
        if (this.tempIncInput != null) {
            this.incrementalOutput.close();
        }
    }

    protected long getNumber() {
        return this.number;
    }

    public Map<COSBase, COSObjectKey> getObjectKeys() {
        return this.objectKeys;
    }

    protected OutputStream getOutput() {
        return this.output;
    }

    protected COSStandardOutputStream getStandardOutput() {
        return this.standardOutput;
    }

    protected long getStartxref() {
        return this.startxref;
    }

    protected List<COSWriterXRefEntry> getXRefEntries() {
        return this.xRefEntries;
    }

    protected void setNumber(long j) {
        this.number = j;
    }

    private void setOutput(OutputStream outputStream) {
        this.output = outputStream;
    }

    private void setStandardOutput(COSStandardOutputStream cOSStandardOutputStream) {
        this.standardOutput = cOSStandardOutputStream;
    }

    protected void setStartxref(long j) {
        this.startxref = j;
    }

    protected void doWriteBody(COSDocument cOSDocument) throws IOException {
        COSDictionary trailer = cOSDocument.getTrailer();
        COSDictionary cOSDictionary = (COSDictionary) trailer.getDictionaryObject(COSName.ROOT);
        COSDictionary cOSDictionary2 = (COSDictionary) trailer.getDictionaryObject(COSName.INFO);
        COSDictionary cOSDictionary3 = (COSDictionary) trailer.getDictionaryObject(COSName.ENCRYPT);
        if (cOSDictionary != null) {
            addObjectToWrite(cOSDictionary);
        }
        if (cOSDictionary2 != null) {
            addObjectToWrite(cOSDictionary2);
        }
        while (this.objectsToWrite.size() > 0) {
            COSBase cOSBaseRemoveFirst = this.objectsToWrite.removeFirst();
            this.objectsToWriteSet.remove(cOSBaseRemoveFirst);
            doWriteObject(cOSBaseRemoveFirst);
        }
        this.willEncrypt = false;
        if (cOSDictionary3 != null) {
            addObjectToWrite(cOSDictionary3);
        }
        while (this.objectsToWrite.size() > 0) {
            COSBase cOSBaseRemoveFirst2 = this.objectsToWrite.removeFirst();
            this.objectsToWriteSet.remove(cOSBaseRemoveFirst2);
            doWriteObject(cOSBaseRemoveFirst2);
        }
    }

    private void addObjectToWrite(COSBase cOSBase) {
        Object object = cOSBase instanceof COSObject ? ((COSObject) cOSBase).getObject() : cOSBase;
        if (this.writtenObjects.contains(cOSBase) || this.objectsToWriteSet.contains(cOSBase) || this.actualsAdded.contains(object)) {
            return;
        }
        COSObjectKey cOSObjectKey = object != null ? this.objectKeys.get(object) : null;
        Object obj = cOSObjectKey != null ? (COSBase) this.keyObject.get(cOSObjectKey) : null;
        if (object == null || !this.objectKeys.containsKey(object) || !(cOSBase instanceof COSUpdateInfo) || ((COSUpdateInfo) cOSBase).isNeedToBeUpdated() || !(obj instanceof COSUpdateInfo) || ((COSUpdateInfo) obj).isNeedToBeUpdated()) {
            this.objectsToWrite.add(cOSBase);
            this.objectsToWriteSet.add(cOSBase);
            if (object != null) {
                this.actualsAdded.add((COSBase) object);
            }
        }
    }

    public void doWriteObject(COSBase cOSBase) throws IOException {
        this.writtenObjects.add(cOSBase);
        if (cOSBase instanceof COSDictionary) {
            COSBase item = ((COSDictionary) cOSBase).getItem(COSName.TYPE);
            if (COSName.SIG.equals(item) || COSName.DOC_TIME_STAMP.equals(item)) {
                this.reachedSignature = true;
            }
        }
        this.currentObjectKey = getObjectKey(cOSBase);
        addXRefEntry(new COSWriterXRefEntry(getStandardOutput().getPos(), cOSBase, this.currentObjectKey));
        getStandardOutput().write(String.valueOf(this.currentObjectKey.getNumber()).getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(String.valueOf(this.currentObjectKey.getGeneration()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(OBJ);
        getStandardOutput().writeEOL();
        cOSBase.accept(this);
        getStandardOutput().writeEOL();
        getStandardOutput().write(ENDOBJ);
        getStandardOutput().writeEOL();
    }

    protected void doWriteHeader(COSDocument cOSDocument) throws IOException {
        String str;
        if (this.fdfDocument != null) {
            str = "%FDF-" + Float.toString(this.fdfDocument.getDocument().getVersion());
        } else {
            str = "%PDF-" + Float.toString(this.pdDocument.getDocument().getVersion());
        }
        getStandardOutput().write(str.getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
        getStandardOutput().write(COMMENT);
        getStandardOutput().write(GARBAGE);
        getStandardOutput().writeEOL();
    }

    protected void doWriteTrailer(COSDocument cOSDocument) throws IOException {
        getStandardOutput().write(TRAILER);
        getStandardOutput().writeEOL();
        COSDictionary trailer = cOSDocument.getTrailer();
        Collections.sort(getXRefEntries());
        trailer.setLong(COSName.SIZE, getXRefEntries().get(getXRefEntries().size() - 1).getKey().getNumber() + 1);
        if (!this.incrementalUpdate) {
            trailer.removeItem(COSName.PREV);
        }
        if (!cOSDocument.isXRefStream()) {
            trailer.removeItem(COSName.XREF_STM);
        }
        trailer.removeItem(COSName.DOC_CHECKSUM);
        trailer.accept(this);
    }

    private void doWriteXRefInc(COSDocument cOSDocument, long j) throws IOException {
        if (cOSDocument.isXRefStream() || j != -1) {
            PDFXRefStream pDFXRefStream = new PDFXRefStream();
            Iterator<COSWriterXRefEntry> it = getXRefEntries().iterator();
            while (it.hasNext()) {
                pDFXRefStream.addEntry(it.next());
            }
            COSDictionary trailer = cOSDocument.getTrailer();
            if (this.incrementalUpdate) {
                trailer.setLong(COSName.PREV, cOSDocument.getStartXref());
            } else {
                trailer.removeItem(COSName.PREV);
            }
            pDFXRefStream.addTrailerInfo(trailer);
            pDFXRefStream.setSize(getNumber() + 2);
            setStartxref(getStandardOutput().getPos());
            doWriteObject(pDFXRefStream.getStream());
        }
        if (cOSDocument.isXRefStream() && j == -1) {
            return;
        }
        COSDictionary trailer2 = cOSDocument.getTrailer();
        trailer2.setLong(COSName.PREV, cOSDocument.getStartXref());
        if (j != -1) {
            COSName cOSName = COSName.XREF_STM;
            trailer2.removeItem(cOSName);
            trailer2.setLong(cOSName, getStartxref());
        }
        doWriteXRefTable();
        doWriteTrailer(cOSDocument);
    }

    private void doWriteXRefTable() throws IOException {
        addXRefEntry(COSWriterXRefEntry.getNullEntry());
        Collections.sort(getXRefEntries());
        setStartxref(getStandardOutput().getPos());
        getStandardOutput().write(XREF);
        getStandardOutput().writeEOL();
        Long[] xRefRanges = getXRefRanges(getXRefEntries());
        int length = xRefRanges.length;
        int i = 0;
        for (int i2 = 0; i2 < length && length % 2 == 0; i2 += 2) {
            int i3 = i2 + 1;
            writeXrefRange(xRefRanges[i2].longValue(), xRefRanges[i3].longValue());
            int i4 = 0;
            while (i4 < xRefRanges[i3].longValue()) {
                writeXrefEntry(this.xRefEntries.get(i));
                i4++;
                i++;
            }
        }
    }

    private void doWriteIncrement() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) this.output;
        byteArrayOutputStream.flush();
        IOUtils.copy(new SequenceInputStream(new RandomAccessInputStream(this.incrementalInput), new ByteArrayInputStream(byteArrayOutputStream.toByteArray())), this.incrementalOutput);
    }

    private void doWriteSignature() throws IOException {
        long length = this.incrementalInput.length();
        long j = this.signatureOffset;
        String str = "0 " + j + " " + (this.signatureLength + j) + " " + ((getStandardOutput().getPos() - (this.signatureLength + length)) - (this.signatureOffset - length)) + "]";
        if (this.byteRangeLength - ((long) str.length()) < 0) {
            throw new IOException("Can't write new ByteRange, not enough space");
        }
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) this.output;
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bytes = str.getBytes(Charsets.ISO_8859_1);
        int i = 0;
        while (true) {
            long j2 = i;
            if (j2 >= this.byteRangeLength) {
                break;
            }
            if (i >= bytes.length) {
                byteArray[(int) ((this.byteRangeOffset + j2) - length)] = 32;
            } else {
                byteArray[(int) ((this.byteRangeOffset + j2) - length)] = bytes[i];
            }
            i++;
        }
        byte[] bArr = new byte[byteArray.length - ((int) this.signatureLength)];
        int i2 = (int) (this.signatureOffset - length);
        System.arraycopy(byteArray, 0, bArr, 0, i2);
        long j3 = this.signatureLength;
        System.arraycopy(byteArray, ((int) j3) + i2, bArr, i2, (byteArray.length - i2) - ((int) j3));
        String hexString = new COSString(this.signatureInterface.sign(new SequenceInputStream(new RandomAccessInputStream(this.incrementalInput), new ByteArrayInputStream(bArr)))).toHexString();
        if (hexString.length() > this.signatureLength - 2) {
            throw new IOException("Can't write signature, not enough space");
        }
        byte[] bytes2 = hexString.getBytes(Charsets.ISO_8859_1);
        System.arraycopy(bytes2, 0, byteArray, i2 + 1, bytes2.length);
        IOUtils.copy(new RandomAccessInputStream(this.incrementalInput), this.incrementalOutput);
        this.incrementalOutput.write(byteArray);
    }

    private void writeXrefRange(long j, long j2) throws IOException {
        getStandardOutput().write(String.valueOf(j).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(SPACE);
        getStandardOutput().write(String.valueOf(j2).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
    }

    private void writeXrefEntry(COSWriterXRefEntry cOSWriterXRefEntry) throws IOException {
        String str = this.formatXrefOffset.format(cOSWriterXRefEntry.getOffset());
        String str2 = this.formatXrefGeneration.format(cOSWriterXRefEntry.getKey().getGeneration());
        getStandardOutput().write(str.getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(str2.getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(cOSWriterXRefEntry.isFree() ? XREF_FREE : XREF_USED);
        getStandardOutput().writeCRLF();
    }

    protected Long[] getXRefRanges(List<COSWriterXRefEntry> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<COSWriterXRefEntry> it = list.iterator();
        long j = -2;
        long j2 = 1;
        while (it.hasNext()) {
            long number = (int) it.next().getKey().getNumber();
            if (number == j + 1) {
                j2++;
            } else if (j != -2) {
                arrayList.add(Long.valueOf((j - j2) + 1));
                arrayList.add(Long.valueOf(j2));
                j2 = 1;
            }
            j = number;
        }
        if (list.size() > 0) {
            arrayList.add(Long.valueOf((j - j2) + 1));
            arrayList.add(Long.valueOf(j2));
        }
        return (Long[]) arrayList.toArray(new Long[arrayList.size()]);
    }

    private COSObjectKey getObjectKey(COSBase cOSBase) {
        COSBase object = cOSBase instanceof COSObject ? ((COSObject) cOSBase).getObject() : cOSBase;
        COSObjectKey cOSObjectKey = object != null ? this.objectKeys.get(object) : null;
        if (cOSObjectKey == null) {
            cOSObjectKey = this.objectKeys.get(cOSBase);
        }
        if (cOSObjectKey == null) {
            setNumber(getNumber() + 1);
            cOSObjectKey = new COSObjectKey(getNumber(), 0);
            this.objectKeys.put(cOSBase, cOSObjectKey);
            if (object != null) {
                this.objectKeys.put(object, cOSObjectKey);
            }
        }
        return cOSObjectKey;
    }

    @Override
    public Object visitFromArray(COSArray cOSArray) throws IOException {
        getStandardOutput().write(ARRAY_OPEN);
        Iterator<COSBase> it = cOSArray.iterator();
        int i = 0;
        while (it.hasNext()) {
            COSBase next = it.next();
            if (next instanceof COSDictionary) {
                if (next.isDirect()) {
                    visitFromDictionary((COSDictionary) next);
                } else {
                    addObjectToWrite(next);
                    writeReference(next);
                }
            } else if (next instanceof COSObject) {
                COSBase object = ((COSObject) next).getObject();
                if (this.incrementalUpdate || (object instanceof COSDictionary) || object == null) {
                    addObjectToWrite(next);
                    writeReference(next);
                } else {
                    object.accept(this);
                }
            } else if (next == null) {
                COSNull.NULL.accept(this);
            } else {
                next.accept(this);
            }
            i++;
            if (it.hasNext()) {
                if (i % 10 == 0) {
                    getStandardOutput().writeEOL();
                } else {
                    getStandardOutput().write(SPACE);
                }
            }
        }
        getStandardOutput().write(ARRAY_CLOSE);
        getStandardOutput().writeEOL();
        return null;
    }

    @Override
    public Object visitFromBoolean(COSBoolean cOSBoolean) throws IOException {
        cOSBoolean.writePDF(getStandardOutput());
        return null;
    }

    @Override
    public Object visitFromDictionary(COSDictionary cOSDictionary) throws IOException {
        getStandardOutput().write(DICT_OPEN);
        getStandardOutput().writeEOL();
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSBase value = entry.getValue();
            if (value != null) {
                entry.getKey().accept(this);
                getStandardOutput().write(SPACE);
                if (value instanceof COSDictionary) {
                    COSDictionary cOSDictionary2 = (COSDictionary) value;
                    if (!this.incrementalUpdate) {
                        COSBase item = cOSDictionary2.getItem(COSName.XOBJECT);
                        if (item != null) {
                            item.setDirect(true);
                        }
                        COSBase item2 = cOSDictionary2.getItem(COSName.RESOURCES);
                        if (item2 != null) {
                            item2.setDirect(true);
                        }
                    }
                    if (cOSDictionary2.isDirect()) {
                        visitFromDictionary(cOSDictionary2);
                    } else {
                        addObjectToWrite(cOSDictionary2);
                        writeReference(cOSDictionary2);
                    }
                } else if (value instanceof COSObject) {
                    COSBase object = ((COSObject) value).getObject();
                    if (this.incrementalUpdate || (object instanceof COSDictionary) || object == null) {
                        addObjectToWrite(value);
                        writeReference(value);
                    } else {
                        object.accept(this);
                    }
                } else if (this.reachedSignature && COSName.CONTENTS.equals(entry.getKey())) {
                    this.signatureOffset = getStandardOutput().getPos();
                    value.accept(this);
                    this.signatureLength = getStandardOutput().getPos() - this.signatureOffset;
                } else if (this.reachedSignature && COSName.BYTERANGE.equals(entry.getKey())) {
                    this.byteRangeOffset = getStandardOutput().getPos() + 1;
                    value.accept(this);
                    this.byteRangeLength = (getStandardOutput().getPos() - 1) - this.byteRangeOffset;
                    this.reachedSignature = false;
                } else {
                    value.accept(this);
                }
                getStandardOutput().writeEOL();
            }
        }
        getStandardOutput().write(DICT_CLOSE);
        getStandardOutput().writeEOL();
        return null;
    }

    @Override
    public Object visitFromDocument(COSDocument cOSDocument) throws IOException {
        if (!this.incrementalUpdate) {
            doWriteHeader(cOSDocument);
        } else {
            getStandardOutput().writeCRLF();
        }
        doWriteBody(cOSDocument);
        COSDictionary trailer = cOSDocument.getTrailer();
        long j = trailer != null ? trailer.getLong(COSName.XREF_STM) : -1L;
        if (this.incrementalUpdate || cOSDocument.isXRefStream()) {
            doWriteXRefInc(cOSDocument, j);
        } else {
            doWriteXRefTable();
            doWriteTrailer(cOSDocument);
        }
        getStandardOutput().write(STARTXREF);
        getStandardOutput().writeEOL();
        getStandardOutput().write(String.valueOf(getStartxref()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().writeEOL();
        getStandardOutput().write(EOF);
        getStandardOutput().writeEOL();
        if (!this.incrementalUpdate) {
            return null;
        }
        if (this.signatureOffset == 0 || this.byteRangeOffset == 0) {
            doWriteIncrement();
            return null;
        }
        doWriteSignature();
        return null;
    }

    @Override
    public Object visitFromFloat(COSFloat cOSFloat) throws IOException {
        cOSFloat.writePDF(getStandardOutput());
        return null;
    }

    @Override
    public Object visitFromInt(COSInteger cOSInteger) throws IOException {
        cOSInteger.writePDF(getStandardOutput());
        return null;
    }

    @Override
    public Object visitFromName(COSName cOSName) throws IOException {
        cOSName.writePDF(getStandardOutput());
        return null;
    }

    @Override
    public Object visitFromNull(COSNull cOSNull) throws IOException {
        cOSNull.writePDF(getStandardOutput());
        return null;
    }

    public void writeReference(COSBase cOSBase) throws IOException {
        COSObjectKey objectKey = getObjectKey(cOSBase);
        getStandardOutput().write(String.valueOf(objectKey.getNumber()).getBytes(Charsets.ISO_8859_1));
        COSStandardOutputStream standardOutput = getStandardOutput();
        byte[] bArr = SPACE;
        standardOutput.write(bArr);
        getStandardOutput().write(String.valueOf(objectKey.getGeneration()).getBytes(Charsets.ISO_8859_1));
        getStandardOutput().write(bArr);
        getStandardOutput().write(REFERENCE);
    }

    @Override
    public Object visitFromStream(COSStream cOSStream) throws Throwable {
        Throwable th;
        InputStream inputStreamCreateRawInputStream;
        if (this.willEncrypt) {
            this.pdDocument.getEncryption().getSecurityHandler().encryptStream(cOSStream, this.currentObjectKey.getNumber(), this.currentObjectKey.getGeneration());
        }
        try {
            visitFromDictionary(cOSStream);
            getStandardOutput().write(STREAM);
            getStandardOutput().writeCRLF();
            inputStreamCreateRawInputStream = cOSStream.createRawInputStream();
            try {
                IOUtils.copy(inputStreamCreateRawInputStream, getStandardOutput());
                getStandardOutput().writeCRLF();
                getStandardOutput().write(ENDSTREAM);
                getStandardOutput().writeEOL();
                if (inputStreamCreateRawInputStream != null) {
                    inputStreamCreateRawInputStream.close();
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (inputStreamCreateRawInputStream != null) {
                    inputStreamCreateRawInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamCreateRawInputStream = null;
        }
    }

    @Override
    public Object visitFromString(COSString cOSString) throws IOException {
        if (this.willEncrypt) {
            this.pdDocument.getEncryption().getSecurityHandler().encryptString(cOSString, this.currentObjectKey.getNumber(), this.currentObjectKey.getGeneration());
        }
        writeString(cOSString, getStandardOutput());
        return null;
    }

    public void write(COSDocument cOSDocument) throws IOException {
        write(new PDDocument(cOSDocument));
    }

    public void write(PDDocument pDDocument) throws IOException {
        write(pDDocument, null);
    }

    public void write(PDDocument pDDocument, SignatureInterface signatureInterface) throws IOException {
        Long lValueOf = Long.valueOf(pDDocument.getDocumentId() == null ? System.currentTimeMillis() : pDDocument.getDocumentId().longValue());
        this.pdDocument = pDDocument;
        this.signatureInterface = signatureInterface;
        if (this.incrementalUpdate) {
            prepareIncrement(pDDocument);
        }
        boolean z = true;
        if (pDDocument.isAllSecurityToBeRemoved()) {
            this.willEncrypt = false;
            pDDocument.getDocument().getTrailer().removeItem(COSName.ENCRYPT);
        } else if (this.pdDocument.getEncryption() != null) {
            if (!this.incrementalUpdate) {
                SecurityHandler securityHandler = this.pdDocument.getEncryption().getSecurityHandler();
                if (!securityHandler.hasProtectionPolicy()) {
                    throw new IllegalStateException("PDF contains an encryption dictionary, please remove it with setAllSecurityToBeRemoved() or set a protection policy with protect()");
                }
                securityHandler.prepareDocumentForEncryption(this.pdDocument);
            }
            this.willEncrypt = true;
        } else {
            this.willEncrypt = false;
        }
        COSDocument document = this.pdDocument.getDocument();
        COSDictionary trailer = document.getTrailer();
        COSArray cOSArray = (COSArray) trailer.getDictionaryObject(COSName.f2269ID);
        if (cOSArray != null && cOSArray.size() == 2) {
            z = false;
        }
        if (z || this.incrementalUpdate) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.update(Long.toString(lValueOf.longValue()).getBytes(Charsets.ISO_8859_1));
                COSDictionary cOSDictionary = (COSDictionary) trailer.getDictionaryObject(COSName.INFO);
                if (cOSDictionary != null) {
                    Iterator<COSBase> it = cOSDictionary.getValues().iterator();
                    while (it.hasNext()) {
                        messageDigest.update(it.next().toString().getBytes(Charsets.ISO_8859_1));
                    }
                }
                COSString cOSString = z ? new COSString(messageDigest.digest()) : (COSString) cOSArray.get(0);
                COSString cOSString2 = z ? cOSString : new COSString(messageDigest.digest());
                COSArray cOSArray2 = new COSArray();
                cOSArray2.add((COSBase) cOSString);
                cOSArray2.add((COSBase) cOSString2);
                trailer.setItem(COSName.f2269ID, (COSBase) cOSArray2);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        document.accept(this);
    }

    public void write(FDFDocument fDFDocument) throws IOException {
        this.fdfDocument = fDFDocument;
        this.willEncrypt = false;
        fDFDocument.getDocument().accept(this);
    }

    public static void writeString(COSString cOSString, OutputStream outputStream) throws IOException {
        writeString(cOSString.getBytes(), cOSString.getForceHexForm(), outputStream);
    }

    public static void writeString(byte[] bArr, OutputStream outputStream) throws IOException {
        writeString(bArr, false, outputStream);
    }

    private static void writeString(byte[] bArr, boolean z, OutputStream outputStream) throws IOException {
        boolean z2;
        int i = 0;
        for (byte b : bArr) {
            if (b < 0 || b == 13 || b == 10) {
                z2 = false;
                break;
            }
        }
        z2 = true;
        if (z2 && !z) {
            outputStream.write(40);
            int length = bArr.length;
            while (i < length) {
                int i2 = bArr[i];
                if (i2 == 40 || i2 == 41 || i2 == 92) {
                    outputStream.write(92);
                    outputStream.write(i2);
                } else {
                    outputStream.write(i2);
                }
                i++;
            }
            outputStream.write(41);
            return;
        }
        outputStream.write(60);
        int length2 = bArr.length;
        while (i < length2) {
            outputStream.write(Hex.getBytes(bArr[i]));
            i++;
        }
        outputStream.write(62);
    }
}
