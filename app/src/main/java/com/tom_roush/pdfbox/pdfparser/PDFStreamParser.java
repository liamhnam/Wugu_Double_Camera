package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.contentstream.PDContentStream;
import com.tom_roush.pdfbox.cos.COSStream;
import com.tom_roush.pdfbox.pdmodel.common.PDStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PDFStreamParser extends BaseParser {
    private static final int MAX_BIN_CHAR_TEST_LENGTH = 10;
    private final byte[] binCharTestArr;
    private final List<Object> streamObjects;

    private boolean isSpaceOrReturn(int i) {
        return i == 10 || i == 13 || i == 32;
    }

    @Deprecated
    public PDFStreamParser(PDStream pDStream) throws IOException {
        super(new InputStreamSource(pDStream.createInputStream()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    @Deprecated
    public PDFStreamParser(COSStream cOSStream) throws IOException {
        super(new InputStreamSource(cOSStream.createInputStream()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public PDFStreamParser(PDContentStream pDContentStream) throws IOException {
        super(new InputStreamSource(pDContentStream.getContents()));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public PDFStreamParser(byte[] bArr) throws IOException {
        super(new InputStreamSource(new ByteArrayInputStream(bArr)));
        this.streamObjects = new ArrayList(100);
        this.binCharTestArr = new byte[10];
    }

    public void parse() throws IOException {
        while (true) {
            Object nextToken = parseNextToken();
            if (nextToken == null) {
                return;
            } else {
                this.streamObjects.add(nextToken);
            }
        }
    }

    public List<Object> getTokens() {
        return this.streamObjects;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object parseNextToken() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.PDFStreamParser.parseNextToken():java.lang.Object");
    }

    private boolean hasNoFollowingBinData(SequentialSource sequentialSource) throws IOException {
        int i = sequentialSource.read(this.binCharTestArr, 0, 10);
        boolean z = true;
        if (i > 0) {
            int i2 = -1;
            int i3 = -1;
            for (int i4 = 0; i4 < i; i4++) {
                byte b = this.binCharTestArr[i4];
                if (b < 9 || (b > 10 && b < 32 && b != 13)) {
                    z = false;
                    break;
                }
                if (i2 == -1 && b != 9 && b != 32 && b != 10 && b != 13) {
                    i2 = i4;
                } else if (i2 != -1 && i3 == -1 && (b == 9 || b == 32 || b == 10 || b == 13)) {
                    i3 = i4;
                }
            }
            if (i == 10) {
                int i5 = (i2 == -1 || i3 != -1) ? i3 : 10;
                if (i5 != -1 && i2 != -1 && i5 - i2 > 3) {
                    z = false;
                }
            }
            sequentialSource.unread(Arrays.copyOfRange(this.binCharTestArr, 0, i));
        }
        if (!z) {
            Log.w("PdfBox-Android", "ignoring 'EI' assumed to be in the middle of inline image");
        }
        return z;
    }

    protected String readOperator() throws IOException {
        skipSpaces();
        StringBuffer stringBuffer = new StringBuffer(4);
        int iPeek = this.seqSource.peek();
        while (iPeek != -1 && !isWhitespace(iPeek) && !isClosing(iPeek) && iPeek != 91 && iPeek != 60 && iPeek != 40 && iPeek != 47 && (iPeek < 48 || iPeek > 57)) {
            char c = (char) this.seqSource.read();
            int iPeek2 = this.seqSource.peek();
            stringBuffer.append(c);
            if (c == 'd' && (iPeek2 == 48 || iPeek2 == 49)) {
                stringBuffer.append((char) this.seqSource.read());
                iPeek = this.seqSource.peek();
            } else {
                iPeek = iPeek2;
            }
        }
        return stringBuffer.toString();
    }

    private boolean hasNextSpaceOrReturn() throws IOException {
        return isSpaceOrReturn(this.seqSource.peek());
    }
}
