package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdfwriter.COSWriterXRefEntry;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import okhttp3.internal.p040ws.WebSocketProtocol;

public class PDFXRefStream implements PDFXRef {
    private static final int ENTRY_FREE = 0;
    private static final int ENTRY_NORMAL = 1;
    private static final int ENTRY_OBJSTREAM = 2;
    private long size = -1;
    private final COSStream stream = new COSStream();
    private final Map<Long, Object> streamData = new TreeMap();
    private final Set<Long> objectNumbers = new TreeSet();

    @Override
    public COSObject getObject(int i) {
        return null;
    }

    public COSStream getStream() throws IOException {
        this.stream.setItem(COSName.TYPE, (COSBase) COSName.XREF);
        if (this.size == -1) {
            throw new IllegalArgumentException("size is not set in xrefstream");
        }
        this.stream.setLong(COSName.SIZE, this.streamData.size() + 1);
        List<Long> indexEntry = getIndexEntry();
        COSArray cOSArray = new COSArray();
        Iterator<Long> it = indexEntry.iterator();
        while (it.hasNext()) {
            cOSArray.add((COSBase) COSInteger.get(it.next().longValue()));
        }
        this.stream.setItem(COSName.INDEX, (COSBase) cOSArray);
        int[] wEntry = getWEntry();
        COSArray cOSArray2 = new COSArray();
        for (int i : wEntry) {
            cOSArray2.add((COSBase) COSInteger.get(i));
        }
        this.stream.setItem(COSName.f2321W, (COSBase) cOSArray2);
        OutputStream outputStreamCreateOutputStream = this.stream.createOutputStream(COSName.FLATE_DECODE);
        writeStreamData(outputStreamCreateOutputStream, wEntry);
        outputStreamCreateOutputStream.flush();
        outputStreamCreateOutputStream.close();
        for (COSName cOSName : this.stream.keySet()) {
            if (!COSName.ROOT.equals(cOSName) && !COSName.INFO.equals(cOSName) && !COSName.PREV.equals(cOSName)) {
                this.stream.getDictionaryObject(cOSName).setDirect(true);
            }
        }
        return this.stream;
    }

    public void addTrailerInfo(COSDictionary cOSDictionary) {
        for (Map.Entry<COSName, COSBase> entry : cOSDictionary.entrySet()) {
            COSName key = entry.getKey();
            if (COSName.INFO.equals(key) || COSName.ROOT.equals(key) || COSName.ENCRYPT.equals(key) || COSName.f2269ID.equals(key) || COSName.PREV.equals(key)) {
                this.stream.setItem(key, entry.getValue());
            }
        }
    }

    public void addEntry(COSWriterXRefEntry cOSWriterXRefEntry) {
        this.objectNumbers.add(Long.valueOf(cOSWriterXRefEntry.getKey().getNumber()));
        if (cOSWriterXRefEntry.isFree()) {
            FreeReference freeReference = new FreeReference();
            freeReference.nextGenNumber = cOSWriterXRefEntry.getKey().getGeneration();
            freeReference.nextFree = cOSWriterXRefEntry.getKey().getNumber();
            this.streamData.put(Long.valueOf(freeReference.nextFree), freeReference);
            return;
        }
        NormalReference normalReference = new NormalReference();
        normalReference.genNumber = cOSWriterXRefEntry.getKey().getGeneration();
        normalReference.offset = cOSWriterXRefEntry.getOffset();
        this.streamData.put(Long.valueOf(cOSWriterXRefEntry.getKey().getNumber()), normalReference);
    }

    private int[] getWEntry() {
        long[] jArr = new long[3];
        Iterator<Object> it = this.streamData.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                int[] iArr = new int[3];
                for (int i = 0; i < 3; i++) {
                    while (true) {
                        long j = jArr[i];
                        if (j > 0) {
                            iArr[i] = iArr[i] + 1;
                            jArr[i] = j >> 8;
                        }
                    }
                }
                return iArr;
            }
            Object next = it.next();
            if (next instanceof FreeReference) {
                jArr[0] = Math.max(jArr[0], 0L);
                jArr[1] = Math.max(jArr[1], ((FreeReference) next).nextFree);
                jArr[2] = Math.max(jArr[2], r3.nextGenNumber);
            } else if (next instanceof NormalReference) {
                jArr[0] = Math.max(jArr[0], 1L);
                jArr[1] = Math.max(jArr[1], ((NormalReference) next).offset);
                jArr[2] = Math.max(jArr[2], r3.genNumber);
            } else if (next instanceof ObjectStreamReference) {
                ObjectStreamReference objectStreamReference = (ObjectStreamReference) next;
                jArr[0] = Math.max(jArr[0], 2L);
                jArr[1] = Math.max(jArr[1], objectStreamReference.offset);
                jArr[2] = Math.max(jArr[2], objectStreamReference.objectNumberOfObjectStream);
            } else {
                throw new RuntimeException("unexpected reference type");
            }
        }
    }

    public void setSize(long j) {
        this.size = j;
    }

    private List<Long> getIndexEntry() {
        LinkedList linkedList = new LinkedList();
        TreeSet<Long> treeSet = new TreeSet();
        treeSet.add(0L);
        treeSet.addAll(this.objectNumbers);
        Long l = null;
        Long lValueOf = null;
        for (Long l2 : treeSet) {
            if (l == null) {
                lValueOf = 1L;
                l = l2;
            }
            if (l.longValue() + lValueOf.longValue() == l2.longValue()) {
                lValueOf = Long.valueOf(lValueOf.longValue() + 1);
            }
            if (l.longValue() + lValueOf.longValue() < l2.longValue()) {
                linkedList.add(l);
                linkedList.add(lValueOf);
                lValueOf = 1L;
                l = l2;
            }
        }
        linkedList.add(l);
        linkedList.add(lValueOf);
        return linkedList;
    }

    private void writeNumber(OutputStream outputStream, long j, int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (255 & j);
            j >>= 8;
        }
        for (int i3 = 0; i3 < i; i3++) {
            outputStream.write(bArr[(i - i3) - 1]);
        }
    }

    private void writeStreamData(OutputStream outputStream, int[] iArr) throws IOException {
        writeNumber(outputStream, 0L, iArr[0]);
        writeNumber(outputStream, 0L, iArr[1]);
        writeNumber(outputStream, WebSocketProtocol.PAYLOAD_SHORT_MAX, iArr[2]);
        for (Object obj : this.streamData.values()) {
            if (obj instanceof FreeReference) {
                writeNumber(outputStream, 0L, iArr[0]);
                writeNumber(outputStream, ((FreeReference) obj).nextFree, iArr[1]);
                writeNumber(outputStream, r6.nextGenNumber, iArr[2]);
            } else if (obj instanceof NormalReference) {
                writeNumber(outputStream, 1L, iArr[0]);
                writeNumber(outputStream, ((NormalReference) obj).offset, iArr[1]);
                writeNumber(outputStream, r6.genNumber, iArr[2]);
            } else if (obj instanceof ObjectStreamReference) {
                ObjectStreamReference objectStreamReference = (ObjectStreamReference) obj;
                writeNumber(outputStream, 2L, iArr[0]);
                writeNumber(outputStream, objectStreamReference.offset, iArr[1]);
                writeNumber(outputStream, objectStreamReference.objectNumberOfObjectStream, iArr[2]);
            } else {
                throw new RuntimeException("unexpected reference type");
            }
        }
    }

    class ObjectStreamReference {
        long objectNumberOfObjectStream;
        long offset;

        ObjectStreamReference() {
        }
    }

    class NormalReference {
        int genNumber;
        long offset;

        NormalReference() {
        }
    }

    class FreeReference {
        long nextFree;
        int nextGenNumber;

        FreeReference() {
        }
    }
}
