package com.tom_roush.pdfbox.pdfparser;

import android.util.Log;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSDocument;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSObjectKey;
import com.tom_roush.pdfbox.util.Charsets;
import java.io.IOException;
import java.util.Arrays;
import kotlin.text.Typography;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

public abstract class BaseParser {

    protected static final int f2330A = 97;
    protected static final byte ASCII_CR = 13;
    protected static final byte ASCII_LF = 10;
    private static final byte ASCII_NINE = 57;
    private static final byte ASCII_SPACE = 32;
    private static final byte ASCII_ZERO = 48;

    protected static final int f2331B = 98;

    protected static final int f2332D = 100;
    public static final String DEF = "def";

    protected static final int f2333E = 101;
    protected static final String ENDOBJ_STRING = "endobj";
    protected static final String ENDSTREAM_STRING = "endstream";
    private static final String FALSE = "false";
    private static final long GENERATION_NUMBER_THRESHOLD = 65535;

    protected static final int f2334J = 106;

    protected static final int f2335M = 109;
    static final int MAX_LENGTH_LONG = Long.toString(Long.MAX_VALUE).length();

    protected static final int f2336N = 110;
    private static final String NULL = "null";

    protected static final int f2337O = 111;
    private static final long OBJECT_NUMBER_THRESHOLD = 10000000000L;

    protected static final int f2338R = 114;

    protected static final int f2339S = 115;
    protected static final String STREAM_STRING = "stream";

    protected static final int f2340T = 116;
    private static final String TRUE = "true";
    protected COSDocument document;
    protected final SequentialSource seqSource;

    private boolean isCR(int i) {
        return 13 == i;
    }

    protected static boolean isDigit(int i) {
        return i >= 48 && i <= 57;
    }

    private boolean isLF(int i) {
        return 10 == i;
    }

    protected boolean isClosing(int i) {
        return i == 93;
    }

    protected boolean isEndOfName(int i) {
        return i == 32 || i == 13 || i == 10 || i == 9 || i == 62 || i == 60 || i == 91 || i == 47 || i == 93 || i == 41 || i == 40;
    }

    protected boolean isSpace(int i) {
        return 32 == i;
    }

    protected boolean isWhitespace(int i) {
        return i == 0 || i == 9 || i == 12 || i == 10 || i == 13 || i == 32;
    }

    public BaseParser(SequentialSource sequentialSource) {
        this.seqSource = sequentialSource;
    }

    private static boolean isHexDigit(char c) {
        return isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }

    private COSBase parseCOSDictionaryValue() throws IOException {
        long position = this.seqSource.getPosition();
        COSBase dirObject = parseDirObject();
        skipSpaces();
        if (!isDigit()) {
            return dirObject;
        }
        long position2 = this.seqSource.getPosition();
        COSBase dirObject2 = parseDirObject();
        skipSpaces();
        readExpectedChar(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        if (!(dirObject instanceof COSInteger)) {
            throw new IOException("expected number, actual=" + dirObject + " at offset " + position);
        }
        if (!(dirObject2 instanceof COSInteger)) {
            throw new IOException("expected number, actual=" + dirObject + " at offset " + position2);
        }
        return getObjectFromPool(new COSObjectKey(((COSInteger) dirObject).longValue(), ((COSInteger) dirObject2).intValue()));
    }

    private COSBase getObjectFromPool(COSObjectKey cOSObjectKey) throws IOException {
        COSDocument cOSDocument = this.document;
        if (cOSDocument == null) {
            throw new IOException("object reference " + cOSObjectKey + " at offset " + this.seqSource.getPosition() + " in content stream");
        }
        return cOSDocument.getObjectFromPool(cOSObjectKey);
    }

    protected COSDictionary parseCOSDictionary() throws IOException {
        readExpectedChar(Typography.less);
        readExpectedChar(Typography.less);
        skipSpaces();
        COSDictionary cOSDictionary = new COSDictionary();
        boolean z = false;
        while (!z) {
            skipSpaces();
            char cPeek = (char) this.seqSource.peek();
            if (cPeek == '>') {
                z = true;
            } else if (cPeek == '/') {
                parseCOSDictionaryNameValuePair(cOSDictionary);
            } else {
                Log.w("PdfBox-Android", "Invalid dictionary, found: '" + cPeek + "' but expected: '/'");
                if (readUntilEndOfCOSDictionary()) {
                    return cOSDictionary;
                }
            }
        }
        readExpectedChar(Typography.greater);
        readExpectedChar(Typography.greater);
        return cOSDictionary;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean readUntilEndOfCOSDictionary() throws java.io.IOException {
        /*
            r6 = this;
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
        L6:
            r1 = -1
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L90
            r4 = 47
            if (r0 == r4) goto L90
            r4 = 62
            if (r0 == r4) goto L90
            r1 = 101(0x65, float:1.42E-43)
            if (r0 != r1) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 110(0x6e, float:1.54E-43)
            if (r0 != r4) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 100
            if (r0 != r4) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 115(0x73, float:1.61E-43)
            if (r0 != r4) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            r5 = 116(0x74, float:1.63E-43)
            if (r4 != r5) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            r5 = 114(0x72, float:1.6E-43)
            if (r4 != r5) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r6.seqSource
            int r4 = r4.read()
            if (r4 != r1) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            int r1 = r1.read()
            r4 = 97
            if (r1 != r4) goto L67
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            int r1 = r1.read()
            r4 = 109(0x6d, float:1.53E-43)
            if (r1 != r4) goto L67
            r1 = r3
            goto L68
        L67:
            r1 = r2
        L68:
            if (r1 != 0) goto L83
            r4 = 111(0x6f, float:1.56E-43)
            if (r0 != r4) goto L83
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 98
            if (r0 != r4) goto L83
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            r4 = 106(0x6a, float:1.49E-43)
            if (r0 != r4) goto L83
            r2 = r3
        L83:
            if (r1 != 0) goto L87
            if (r2 == 0) goto L88
        L87:
            return r3
        L88:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r0 = r6.seqSource
            int r0 = r0.read()
            goto L6
        L90:
            if (r0 != r1) goto L93
            return r3
        L93:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            r1.unread(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.readUntilEndOfCOSDictionary():boolean");
    }

    private void parseCOSDictionaryNameValuePair(COSDictionary cOSDictionary) throws IOException {
        COSName cOSName = parseCOSName();
        COSBase cOSDictionaryValue = parseCOSDictionaryValue();
        skipSpaces();
        if (((char) this.seqSource.peek()) == 'd') {
            String string = readString();
            if (!string.equals(DEF)) {
                this.seqSource.unread(string.getBytes(Charsets.ISO_8859_1));
            } else {
                skipSpaces();
            }
        }
        if (cOSDictionaryValue == null) {
            Log.w("PdfBox-Android", "Bad Dictionary Declaration " + this.seqSource);
        } else {
            cOSDictionaryValue.setDirect(true);
            cOSDictionary.setItem(cOSName, cOSDictionaryValue);
        }
    }

    protected void skipWhiteSpaces() throws IOException {
        int i = this.seqSource.read();
        while (32 == i) {
            i = this.seqSource.read();
        }
        if (13 != i) {
            if (10 != i) {
                this.seqSource.unread(i);
            }
        } else {
            int i2 = this.seqSource.read();
            if (10 != i2) {
                this.seqSource.unread(i2);
            }
        }
    }

    private int checkForMissingCloseParen(int i) throws IOException {
        byte b;
        byte[] bArr = new byte[3];
        int i2 = this.seqSource.read(bArr);
        if (i2 == 3 && (((b = bArr[0]) == 13 && bArr[1] == 10 && bArr[2] == 47) || (b == 13 && bArr[1] == 47))) {
            i = 0;
        }
        if (i2 > 0) {
            this.seqSource.unread(Arrays.copyOfRange(bArr, 0, i2));
        }
        return i;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.tom_roush.pdfbox.cos.COSString parseCOSString() throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSString():com.tom_roush.pdfbox.cos.COSString");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.tom_roush.pdfbox.cos.COSString parseCOSHexString() throws java.io.IOException {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r5.seqSource
            int r1 = r1.read()
            char r2 = (char) r1
            boolean r3 = isHexDigit(r2)
            if (r3 == 0) goto L16
            r0.append(r2)
            goto L5
        L16:
            r2 = 62
            if (r1 != r2) goto L1b
            goto L55
        L1b:
            java.lang.String r3 = "Missing closing bracket for hex string. Reached EOS."
            if (r1 < 0) goto L64
            r4 = 32
            if (r1 == r4) goto L5
            r4 = 10
            if (r1 == r4) goto L5
            r4 = 9
            if (r1 == r4) goto L5
            r4 = 13
            if (r1 == r4) goto L5
            r4 = 8
            if (r1 == r4) goto L5
            r4 = 12
            if (r1 != r4) goto L38
            goto L5
        L38:
            int r1 = r0.length()
            int r1 = r1 % 2
            if (r1 == 0) goto L49
            int r1 = r0.length()
            int r1 = r1 + (-1)
            r0.deleteCharAt(r1)
        L49:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r5.seqSource
            int r1 = r1.read()
            if (r1 == r2) goto L53
            if (r1 >= 0) goto L49
        L53:
            if (r1 < 0) goto L5e
        L55:
            java.lang.String r0 = r0.toString()
            com.tom_roush.pdfbox.cos.COSString r0 = com.tom_roush.pdfbox.cos.COSString.parseHex(r0)
            return r0
        L5e:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r3)
            throw r0
        L64:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSHexString():com.tom_roush.pdfbox.cos.COSString");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.tom_roush.pdfbox.cos.COSArray parseCOSArray() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 91
            r6.readExpectedChar(r0)
            com.tom_roush.pdfbox.cos.COSArray r0 = new com.tom_roush.pdfbox.cos.COSArray
            r0.<init>()
            r6.skipSpaces()
        Ld:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            int r1 = r1.peek()
            if (r1 <= 0) goto Lb0
            char r1 = (char) r1
            r2 = 93
            if (r1 == r2) goto Lb0
            com.tom_roush.pdfbox.cos.COSBase r1 = r6.parseDirObject()
            boolean r2 = r1 instanceof com.tom_roush.pdfbox.cos.COSObject
            if (r2 == 0) goto L6a
            int r1 = r0.size()
            int r1 = r1 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r1 = r0.get(r1)
            boolean r1 = r1 instanceof com.tom_roush.pdfbox.cos.COSInteger
            r2 = 0
            if (r1 == 0) goto L69
            int r1 = r0.size()
            int r1 = r1 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r1 = r0.remove(r1)
            com.tom_roush.pdfbox.cos.COSInteger r1 = (com.tom_roush.pdfbox.cos.COSInteger) r1
            int r3 = r0.size()
            int r3 = r3 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r3 = r0.get(r3)
            boolean r3 = r3 instanceof com.tom_roush.pdfbox.cos.COSInteger
            if (r3 == 0) goto L69
            int r2 = r0.size()
            int r2 = r2 + (-1)
            com.tom_roush.pdfbox.cos.COSBase r2 = r0.remove(r2)
            com.tom_roush.pdfbox.cos.COSInteger r2 = (com.tom_roush.pdfbox.cos.COSInteger) r2
            com.tom_roush.pdfbox.cos.COSObjectKey r3 = new com.tom_roush.pdfbox.cos.COSObjectKey
            long r4 = r2.longValue()
            int r1 = r1.intValue()
            r3.<init>(r4, r1)
            com.tom_roush.pdfbox.cos.COSBase r1 = r6.getObjectFromPool(r3)
            goto L6a
        L69:
            r1 = r2
        L6a:
            if (r1 == 0) goto L70
            r0.add(r1)
            goto Laa
        L70:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Corrupt object reference at offset "
            r1.<init>(r2)
            com.tom_roush.pdfbox.pdfparser.SequentialSource r2 = r6.seqSource
            long r2 = r2.getPosition()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "PdfBox-Android"
            android.util.Log.w(r2, r1)
            java.lang.String r1 = r6.readString()
            com.tom_roush.pdfbox.pdfparser.SequentialSource r2 = r6.seqSource
            java.nio.charset.Charset r3 = com.tom_roush.pdfbox.util.Charsets.ISO_8859_1
            byte[] r3 = r1.getBytes(r3)
            r2.unread(r3)
            java.lang.String r2 = "endobj"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto Laf
            java.lang.String r2 = "endstream"
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto Laa
            goto Laf
        Laa:
            r6.skipSpaces()
            goto Ld
        Laf:
            return r0
        Lb0:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r6.seqSource
            r1.read()
            r6.skipSpaces()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSArray():com.tom_roush.pdfbox.cos.COSArray");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.tom_roush.pdfbox.cos.COSName parseCOSName() throws java.io.IOException {
        /*
            r8 = this;
            r0 = 47
            r8.readExpectedChar(r0)
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r8.seqSource
            int r1 = r1.read()
        L10:
            r2 = -1
            if (r1 == r2) goto L9a
            r3 = 35
            if (r1 != r3) goto L88
            com.tom_roush.pdfbox.pdfparser.SequentialSource r3 = r8.seqSource
            int r3 = r3.read()
            com.tom_roush.pdfbox.pdfparser.SequentialSource r4 = r8.seqSource
            int r4 = r4.read()
            char r5 = (char) r3
            boolean r6 = isHexDigit(r5)
            if (r6 == 0) goto L70
            char r6 = (char) r4
            boolean r7 = isHexDigit(r6)
            if (r7 == 0) goto L70
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = ""
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r1 = r1.toString()
            r2 = 16
            int r2 = java.lang.Integer.parseInt(r1, r2)     // Catch: java.lang.NumberFormatException -> L54
            r0.write(r2)     // Catch: java.lang.NumberFormatException -> L54
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r8.seqSource
            int r3 = r1.read()
            goto L7d
        L54:
            r0 = move-exception
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Error: expected hex digit, actual='"
            r3.<init>(r4)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r3 = "'"
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1, r0)
            throw r2
        L70:
            if (r4 == r2) goto L7f
            if (r3 != r2) goto L75
            goto L7f
        L75:
            com.tom_roush.pdfbox.pdfparser.SequentialSource r2 = r8.seqSource
            r2.unread(r4)
            r0.write(r1)
        L7d:
            r1 = r3
            goto L10
        L7f:
            java.lang.String r1 = "PdfBox-Android"
            java.lang.String r3 = "Premature EOF in BaseParser#parseCOSName"
            android.util.Log.e(r1, r3)
            r1 = r2
            goto L9a
        L88:
            boolean r3 = r8.isEndOfName(r1)
            if (r3 == 0) goto L8f
            goto L9a
        L8f:
            r0.write(r1)
            com.tom_roush.pdfbox.pdfparser.SequentialSource r1 = r8.seqSource
            int r1 = r1.read()
            goto L10
        L9a:
            if (r1 == r2) goto La1
            com.tom_roush.pdfbox.pdfparser.SequentialSource r2 = r8.seqSource
            r2.unread(r1)
        La1:
            java.lang.String r1 = new java.lang.String
            byte[] r0 = r0.toByteArray()
            java.nio.charset.Charset r2 = com.tom_roush.pdfbox.util.Charsets.UTF_8
            r1.<init>(r0, r2)
            com.tom_roush.pdfbox.cos.COSName r0 = com.tom_roush.pdfbox.cos.COSName.getPDFName(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.pdfbox.pdfparser.BaseParser.parseCOSName():com.tom_roush.pdfbox.cos.COSName");
    }

    protected COSBoolean parseBoolean() throws IOException {
        char cPeek = (char) this.seqSource.peek();
        if (cPeek == f2340T) {
            String str = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
            if (!str.equals(TRUE)) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str + "' at offset " + this.seqSource.getPosition());
            }
            return COSBoolean.TRUE;
        }
        if (cPeek == 'f') {
            String str2 = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
            if (!str2.equals(FALSE)) {
                throw new IOException("Error parsing boolean: expected='true' actual='" + str2 + "' at offset " + this.seqSource.getPosition());
            }
            return COSBoolean.FALSE;
        }
        throw new IOException("Error parsing boolean expected='t or f' actual='" + cPeek + "' at offset " + this.seqSource.getPosition());
    }

    protected COSBase parseDirObject() throws IOException {
        skipSpaces();
        char cPeek = (char) this.seqSource.peek();
        if (cPeek == '(') {
            return parseCOSString();
        }
        if (cPeek == '/') {
            return parseCOSName();
        }
        if (cPeek == '<') {
            int i = this.seqSource.read();
            char cPeek2 = (char) this.seqSource.peek();
            this.seqSource.unread(i);
            if (cPeek2 == '<') {
                COSDictionary cOSDictionary = parseCOSDictionary();
                skipSpaces();
                return cOSDictionary;
            }
            return parseCOSString();
        }
        if (cPeek == 'R') {
            this.seqSource.read();
            return new COSObject(null);
        }
        if (cPeek == '[') {
            return parseCOSArray();
        }
        if (cPeek == 'f') {
            String str = new String(this.seqSource.readFully(5), Charsets.ISO_8859_1);
            if (str.equals(FALSE)) {
                return COSBoolean.FALSE;
            }
            throw new IOException("expected false actual='" + str + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
        }
        if (cPeek == 'n') {
            readExpectedString("null");
            return COSNull.NULL;
        }
        if (cPeek == f2340T) {
            String str2 = new String(this.seqSource.readFully(4), Charsets.ISO_8859_1);
            if (str2.equals(TRUE)) {
                return COSBoolean.TRUE;
            }
            throw new IOException("expected true actual='" + str2 + "' " + this.seqSource + "' at offset " + this.seqSource.getPosition());
        }
        if (cPeek == 65535) {
            return null;
        }
        if (Character.isDigit(cPeek) || cPeek == '-' || cPeek == '+' || cPeek == '.') {
            StringBuilder sb = new StringBuilder();
            int i2 = this.seqSource.read();
            while (true) {
                char c = (char) i2;
                if (!Character.isDigit(c) && c != '-' && c != '+' && c != '.' && c != 'E' && c != 'e') {
                    break;
                }
                sb.append(c);
                i2 = this.seqSource.read();
            }
            if (i2 != -1) {
                this.seqSource.unread(i2);
            }
            return COSNumber.get(sb.toString());
        }
        String string = readString();
        if (string == null || string.length() == 0) {
            int iPeek = this.seqSource.peek();
            throw new IOException("Unknown dir object c='" + cPeek + "' cInt=" + ((int) cPeek) + " peek='" + ((char) iPeek) + "' peekInt=" + iPeek + " " + this.seqSource.getPosition());
        }
        if (!ENDOBJ_STRING.equals(string) && !ENDSTREAM_STRING.equals(string)) {
            return null;
        }
        this.seqSource.unread(string.getBytes(Charsets.ISO_8859_1));
        return null;
    }

    protected String readString() throws IOException {
        skipSpaces();
        StringBuilder sb = new StringBuilder();
        int i = this.seqSource.read();
        while (true) {
            char c = (char) i;
            if (isEndOfName(c) || i == -1) {
                break;
            }
            sb.append(c);
            i = this.seqSource.read();
        }
        if (i != -1) {
            this.seqSource.unread(i);
        }
        return sb.toString();
    }

    protected void readExpectedString(String str) throws IOException {
        readExpectedString(str.toCharArray(), false);
    }

    protected final void readExpectedString(char[] cArr, boolean z) throws IOException {
        skipSpaces();
        for (char c : cArr) {
            if (this.seqSource.read() != c) {
                throw new IOException("Expected string '" + new String(cArr) + "' but missed at character '" + c + "' at offset " + this.seqSource.getPosition());
            }
        }
        skipSpaces();
    }

    protected void readExpectedChar(char c) throws IOException {
        char c2 = (char) this.seqSource.read();
        if (c2 != c) {
            throw new IOException("expected='" + c + "' actual='" + c2 + "' at offset " + this.seqSource.getPosition());
        }
    }

    protected String readString(int i) throws IOException {
        skipSpaces();
        int i2 = this.seqSource.read();
        StringBuilder sb = new StringBuilder(i);
        while (!isWhitespace(i2) && !isClosing(i2) && i2 != -1 && sb.length() < i && i2 != 91 && i2 != 60 && i2 != 40 && i2 != 47) {
            sb.append((char) i2);
            i2 = this.seqSource.read();
        }
        if (i2 != -1) {
            this.seqSource.unread(i2);
        }
        return sb.toString();
    }

    protected boolean isClosing() throws IOException {
        return isClosing(this.seqSource.peek());
    }

    protected String readLine() throws IOException {
        int i;
        if (this.seqSource.isEOF()) {
            throw new IOException("Error: End-of-File, expected line");
        }
        StringBuilder sb = new StringBuilder(11);
        while (true) {
            i = this.seqSource.read();
            if (i == -1 || isEOL(i)) {
                break;
            }
            sb.append((char) i);
        }
        if (isCR(i) && isLF(this.seqSource.peek())) {
            this.seqSource.read();
        }
        return sb.toString();
    }

    protected boolean isEOL() throws IOException {
        return isEOL(this.seqSource.peek());
    }

    protected boolean isEOL(int i) {
        return isLF(i) || isCR(i);
    }

    protected boolean isWhitespace() throws IOException {
        return isWhitespace(this.seqSource.peek());
    }

    protected boolean isSpace() throws IOException {
        return isSpace(this.seqSource.peek());
    }

    protected boolean isDigit() throws IOException {
        return isDigit(this.seqSource.peek());
    }

    protected void skipSpaces() throws IOException {
        int i = this.seqSource.read();
        while (true) {
            if (!isWhitespace(i) && i != 37) {
                break;
            }
            if (i == 37) {
                i = this.seqSource.read();
                while (!isEOL(i) && i != -1) {
                    i = this.seqSource.read();
                }
            } else {
                i = this.seqSource.read();
            }
        }
        if (i != -1) {
            this.seqSource.unread(i);
        }
    }

    protected long readObjectNumber() throws IOException {
        long j = readLong();
        if (j < 0 || j >= 10000000000L) {
            throw new IOException("Object Number '" + j + "' has more than 10 digits or is negative");
        }
        return j;
    }

    protected int readGenerationNumber() throws IOException {
        int i = readInt();
        if (i < 0 || i > 65535) {
            throw new IOException("Generation Number '" + i + "' has more than 5 digits");
        }
        return i;
    }

    protected int readInt() throws IOException {
        skipSpaces();
        StringBuilder stringNumber = readStringNumber();
        try {
            return Integer.parseInt(stringNumber.toString());
        } catch (NumberFormatException e) {
            this.seqSource.unread(stringNumber.toString().getBytes(Charsets.ISO_8859_1));
            throw new IOException("Error: Expected an integer type at offset " + this.seqSource.getPosition(), e);
        }
    }

    protected long readLong() throws IOException {
        skipSpaces();
        StringBuilder stringNumber = readStringNumber();
        try {
            return Long.parseLong(stringNumber.toString());
        } catch (NumberFormatException e) {
            this.seqSource.unread(stringNumber.toString().getBytes(Charsets.ISO_8859_1));
            throw new IOException("Error: Expected a long type at offset " + this.seqSource.getPosition() + ", instead got '" + ((Object) stringNumber) + "'", e);
        }
    }

    protected final StringBuilder readStringNumber() throws IOException {
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.seqSource.read();
            if (i == 32 || i == 10 || i == 13 || i == 60 || i == 91 || i == 40 || i == 0 || i == -1) {
                if (i != -1) {
                    this.seqSource.unread(i);
                }
                return sb;
            }
            sb.append((char) i);
        } while (sb.length() <= MAX_LENGTH_LONG);
        throw new IOException("Number '" + ((Object) sb) + "' is getting too long, stop reading at offset " + this.seqSource.getPosition());
    }
}
