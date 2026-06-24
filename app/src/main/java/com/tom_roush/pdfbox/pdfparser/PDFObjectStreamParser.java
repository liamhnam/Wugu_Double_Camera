package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSStream;
import java.io.IOException;
import java.util.List;

public class PDFObjectStreamParser extends BaseParser {
    private final COSStream stream;
    private List<COSObject> streamObjects;

    public PDFObjectStreamParser(COSStream cOSStream, COSDocument cOSDocument) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.streamObjects = null;
        this.stream = cOSStream;
        this.document = cOSDocument;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parse() throws java.io.IOException {
        /*
            r9 = this;
            com.tom_roush.pdfbox.cos.COSStream r0 = r9.stream     // Catch: java.lang.Throwable -> L9d
            java.lang.String r1 = "N"
            int r0 = r0.getInt(r1)     // Catch: java.lang.Throwable -> L9d
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L9d
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L9d
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L9d
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L9d
            r9.streamObjects = r2     // Catch: java.lang.Throwable -> L9d
            r2 = 0
            r3 = r2
        L16:
            if (r3 >= r0) goto L29
            long r4 = r9.readObjectNumber()     // Catch: java.lang.Throwable -> L9d
            r9.readLong()     // Catch: java.lang.Throwable -> L9d
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L9d
            r1.add(r4)     // Catch: java.lang.Throwable -> L9d
            int r3 = r3 + 1
            goto L16
        L29:
            r3 = r2
        L2a:
            com.tom_roush.pdfbox.cos.COSBase r4 = r9.parseDirObject()     // Catch: java.lang.Throwable -> L9d
            if (r4 == 0) goto L97
            com.tom_roush.pdfbox.cos.COSObject r5 = new com.tom_roush.pdfbox.cos.COSObject     // Catch: java.lang.Throwable -> L9d
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L9d
            r5.setGenerationNumber(r2)     // Catch: java.lang.Throwable -> L9d
            int r4 = r1.size()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r6 = "PdfBox-Android"
            if (r3 < r4) goto L57
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r1.<init>()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r2 = "/ObjStm (object stream) has more objects than /N "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L9d
            android.util.Log.e(r6, r0)     // Catch: java.lang.Throwable -> L9d
            goto L97
        L57:
            java.lang.Object r4 = r1.get(r3)     // Catch: java.lang.Throwable -> L9d
            java.lang.Long r4 = (java.lang.Long) r4     // Catch: java.lang.Throwable -> L9d
            long r7 = r4.longValue()     // Catch: java.lang.Throwable -> L9d
            r5.setObjectNumber(r7)     // Catch: java.lang.Throwable -> L9d
            java.util.List<com.tom_roush.pdfbox.cos.COSObject> r4 = r9.streamObjects     // Catch: java.lang.Throwable -> L9d
            r4.add(r5)     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9d
            r4.<init>()     // Catch: java.lang.Throwable -> L9d
            java.lang.String r7 = "parsed="
            java.lang.StringBuilder r4 = r4.append(r7)     // Catch: java.lang.Throwable -> L9d
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L9d
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Throwable -> L9d
            android.util.Log.d(r6, r4)     // Catch: java.lang.Throwable -> L9d
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r9.seqSource     // Catch: java.lang.Throwable -> L9d
            boolean r4 = r4.isEOF()     // Catch: java.lang.Throwable -> L9d
            if (r4 != 0) goto L94
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r9.seqSource     // Catch: java.lang.Throwable -> L9d
            int r4 = r4.peek()     // Catch: java.lang.Throwable -> L9d
            r5 = 101(0x65, float:1.42E-43)
            if (r4 != r5) goto L94
            r9.readLine()     // Catch: java.lang.Throwable -> L9d
        L94:
            int r3 = r3 + 1
            goto L2a
        L97:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r9.seqSource
            r0.close()
            return
        L9d:
            r0 = move-exception
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r9.seqSource
            r1.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.PDFObjectStreamParser.parse():void");
    }

    public List<COSObject> getObjects() {
        return this.streamObjects;
    }
}
