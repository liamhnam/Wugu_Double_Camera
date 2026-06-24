package com.tom_roush.pdfbox.pdfparser;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.cos.COSStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.UByte;

public class PDFXrefStreamParser extends BaseParser {
    private final COSStream stream;
    private final XrefTrailerResolver xrefTrailerResolver;

    public PDFXrefStreamParser(COSStream cOSStream, COSDocument cOSDocument, XrefTrailerResolver xrefTrailerResolver) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.document = cOSDocument;
        this.stream = cOSStream;
        this.xrefTrailerResolver = xrefTrailerResolver;
    }

    public void parse() throws IOException {
        int i;
        COSBase dictionaryObject = this.stream.getDictionaryObject(COSName.f2321W);
        if (!(dictionaryObject instanceof COSArray)) {
            throw new IOException("/W array is missing in Xref stream");
        }
        COSArray cOSArray = (COSArray) dictionaryObject;
        COSArray cOSArray2 = (COSArray) this.stream.getDictionaryObject(COSName.INDEX);
        if (cOSArray2 == null) {
            cOSArray2 = new COSArray();
            cOSArray2.add((COSBase) COSInteger.ZERO);
            cOSArray2.add(this.stream.getDictionaryObject(COSName.SIZE));
        }
        ArrayList arrayList = new ArrayList();
        Iterator<COSBase> it = cOSArray2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            long jLongValue = ((COSInteger) it.next()).longValue();
            int iIntValue = ((COSInteger) it.next()).intValue();
            for (int i2 = 0; i2 < iIntValue; i2++) {
                arrayList.add(Long.valueOf(((long) i2) + jLongValue));
            }
        }
        Iterator it2 = arrayList.iterator();
        int i3 = cOSArray.getInt(0);
        int i4 = cOSArray.getInt(1);
        int i5 = cOSArray.getInt(2);
        int i6 = i3 + i4 + i5;
        while (!this.seqSource.isEOF() && it2.hasNext()) {
            byte[] bArr = new byte[i6];
            this.seqSource.read(bArr);
            if (i3 == 0) {
                i = 1;
            } else {
                i = 0;
                for (int i7 = 0; i7 < i3; i7++) {
                    i += (bArr[i7] & UByte.MAX_VALUE) << (((i3 - i7) - 1) * 8);
                }
            }
            Long l = (Long) it2.next();
            if (i == 1) {
                int i8 = 0;
                for (int i9 = 0; i9 < i4; i9++) {
                    i8 += (bArr[i9 + i3] & UByte.MAX_VALUE) << (((i4 - i9) - 1) * 8);
                }
                int i10 = 0;
                for (int i11 = 0; i11 < i5; i11++) {
                    i10 += (bArr[(i11 + i3) + i4] & UByte.MAX_VALUE) << (((i5 - i11) - 1) * 8);
                }
                this.xrefTrailerResolver.setXRef(new COSObjectKey(l.longValue(), i10), i8);
            } else if (i == 2) {
                int i12 = 0;
                for (int i13 = 0; i13 < i4; i13++) {
                    i12 += (bArr[i13 + i3] & UByte.MAX_VALUE) << (((i4 - i13) - 1) * 8);
                }
                this.xrefTrailerResolver.setXRef(new COSObjectKey(l.longValue(), 0), -i12);
            }
        }
    }
}
