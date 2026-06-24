package com.tom_roush.fontbox.cmap;

import com.tom_roush.pdfbox.util.Charsets;
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.net.URL;
import java.util.List;

public class CMapParser {
    private static final String MARK_END_OF_ARRAY = "]";
    private static final String MARK_END_OF_DICTIONARY = ">>";
    private final byte[] tokenParserByteBuffer = new byte[512];

    private boolean isDelimiter(int i) {
        return i == 37 || i == 47 || i == 60 || i == 62 || i == 91 || i == 93 || i == 123 || i == 125 || i == 40 || i == 41;
    }

    private boolean isWhitespaceOrEOF(int i) {
        return i == -1 || i == 32 || i == 13 || i == 10;
    }

    public CMap parse(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
        }
        try {
            CMap cMap = parse(fileInputStream);
            fileInputStream.close();
            return cMap;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    public CMap parsePredefined(String str) throws Throwable {
        InputStream externalCMap;
        try {
            externalCMap = getExternalCMap(str);
        } catch (Throwable th) {
            th = th;
            externalCMap = null;
        }
        try {
            CMap cMap = parse(externalCMap);
            if (externalCMap != null) {
                externalCMap.close();
            }
            return cMap;
        } catch (Throwable th2) {
            th = th2;
            if (externalCMap != null) {
                externalCMap.close();
            }
            throw th;
        }
    }

    public CMap parse(InputStream inputStream) throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        CMap cMap = new CMap();
        Object obj = null;
        while (true) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken == null) {
                break;
            }
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (operator.f2223op.equals("usecmap")) {
                    parseUsecmap(obj, cMap);
                } else {
                    if (operator.f2223op.equals("endcmap")) {
                        break;
                    }
                    if (operator.f2223op.equals("begincodespacerange")) {
                        parseBegincodespacerange(obj, pushbackInputStream, cMap);
                    } else if (operator.f2223op.equals("beginbfchar")) {
                        parseBeginbfchar(obj, pushbackInputStream, cMap);
                    } else if (operator.f2223op.equals("beginbfrange")) {
                        parseBeginbfrange(obj, pushbackInputStream, cMap);
                    } else if (operator.f2223op.equals("begincidchar")) {
                        parseBegincidchar(obj, pushbackInputStream, cMap);
                    } else if (operator.f2223op.equals("begincidrange")) {
                        parseBegincidrange(obj, pushbackInputStream, cMap);
                    }
                }
            } else if (nextToken instanceof LiteralName) {
                parseLiteralName(nextToken, pushbackInputStream, cMap);
            }
            obj = nextToken;
        }
        return cMap;
    }

    private void parseUsecmap(Object obj, CMap cMap) throws IOException {
        cMap.useCmap(parse(getExternalCMap(((LiteralName) obj).name)));
    }

    private void parseLiteralName(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        LiteralName literalName = (LiteralName) obj;
        if ("WMode".equals(literalName.name)) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Integer) {
                cMap.setWMode(((Integer) nextToken).intValue());
                return;
            }
            return;
        }
        if ("CMapName".equals(literalName.name)) {
            Object nextToken2 = parseNextToken(pushbackInputStream);
            if (nextToken2 instanceof LiteralName) {
                cMap.setName(((LiteralName) nextToken2).name);
                return;
            }
            return;
        }
        if ("CMapVersion".equals(literalName.name)) {
            Object nextToken3 = parseNextToken(pushbackInputStream);
            if (nextToken3 instanceof Number) {
                cMap.setVersion(nextToken3.toString());
                return;
            } else {
                if (nextToken3 instanceof String) {
                    cMap.setVersion((String) nextToken3);
                    return;
                }
                return;
            }
        }
        if ("CMapType".equals(literalName.name)) {
            Object nextToken4 = parseNextToken(pushbackInputStream);
            if (nextToken4 instanceof Integer) {
                cMap.setType(((Integer) nextToken4).intValue());
                return;
            }
            return;
        }
        if ("Registry".equals(literalName.name)) {
            Object nextToken5 = parseNextToken(pushbackInputStream);
            if (nextToken5 instanceof String) {
                cMap.setRegistry((String) nextToken5);
                return;
            }
            return;
        }
        if ("Ordering".equals(literalName.name)) {
            Object nextToken6 = parseNextToken(pushbackInputStream);
            if (nextToken6 instanceof String) {
                cMap.setOrdering((String) nextToken6);
                return;
            }
            return;
        }
        if ("Supplement".equals(literalName.name)) {
            Object nextToken7 = parseNextToken(pushbackInputStream);
            if (nextToken7 instanceof Integer) {
                cMap.setSupplement(((Integer) nextToken7).intValue());
            }
        }
    }

    private void parseBegincodespacerange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (!operator.f2223op.equals("endcodespacerange")) {
                    throw new IOException("Error : ~codespacerange contains an unexpected operator : " + operator.f2223op);
                }
                return;
            } else {
                byte[] bArr = (byte[]) parseNextToken(pushbackInputStream);
                CodespaceRange codespaceRange = new CodespaceRange();
                codespaceRange.setStart((byte[]) nextToken);
                codespaceRange.setEnd(bArr);
                cMap.addCodespaceRange(codespaceRange);
            }
        }
    }

    private void parseBeginbfchar(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (!operator.f2223op.equals("endbfchar")) {
                    throw new IOException("Error : ~bfchar contains an unexpected operator : " + operator.f2223op);
                }
                return;
            }
            byte[] bArr = (byte[]) nextToken;
            Object nextToken2 = parseNextToken(pushbackInputStream);
            if (nextToken2 instanceof byte[]) {
                cMap.addCharMapping(bArr, createStringFromBytes((byte[]) nextToken2));
            } else {
                if (!(nextToken2 instanceof LiteralName)) {
                    throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + nextToken2);
                }
                cMap.addCharMapping(bArr, ((LiteralName) nextToken2).name);
            }
        }
    }

    private void parseBegincidrange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        int iIntValue = ((Integer) obj).intValue();
        for (int i = 0; i < iIntValue; i++) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (!operator.f2223op.equals("endcidrange")) {
                    throw new IOException("Error : ~cidrange contains an unexpected operator : " + operator.f2223op);
                }
                return;
            }
            byte[] bArr = (byte[]) nextToken;
            int iCreateIntFromBytes = createIntFromBytes(bArr);
            byte[] bArr2 = (byte[]) parseNextToken(pushbackInputStream);
            int iCreateIntFromBytes2 = createIntFromBytes(bArr2);
            int iIntValue2 = ((Integer) parseNextToken(pushbackInputStream)).intValue();
            if (bArr.length > 2 || bArr2.length > 2) {
                int i2 = (iCreateIntFromBytes2 + iIntValue2) - iCreateIntFromBytes;
                while (iIntValue2 <= i2) {
                    cMap.addCIDMapping(iIntValue2, createIntFromBytes(bArr));
                    increment(bArr);
                    iIntValue2++;
                }
            } else {
                cMap.addCIDRange((char) iCreateIntFromBytes, (char) iCreateIntFromBytes2, iIntValue2);
            }
        }
    }

    private void parseBegincidchar(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (!operator.f2223op.equals("endcidchar")) {
                    throw new IOException("Error : ~cidchar contains an unexpected operator : " + operator.f2223op);
                }
                return;
            }
            cMap.addCIDMapping(((Integer) parseNextToken(pushbackInputStream)).intValue(), createIntFromBytes((byte[]) nextToken));
        }
    }

    private void parseBeginbfrange(Object obj, PushbackInputStream pushbackInputStream, CMap cMap) throws IOException {
        byte[] bArr;
        List list;
        Number number = (Number) obj;
        for (int i = 0; i < number.intValue(); i++) {
            Object nextToken = parseNextToken(pushbackInputStream);
            if (nextToken instanceof Operator) {
                Operator operator = (Operator) nextToken;
                if (!operator.f2223op.equals("endbfrange")) {
                    throw new IOException("Error : ~bfrange contains an unexpected operator : " + operator.f2223op);
                }
                return;
            }
            byte[] bArr2 = (byte[]) nextToken;
            byte[] bArr3 = (byte[]) parseNextToken(pushbackInputStream);
            Object nextToken2 = parseNextToken(pushbackInputStream);
            if (nextToken2 instanceof List) {
                list = (List) nextToken2;
                bArr = (byte[]) list.get(0);
            } else {
                bArr = (byte[]) nextToken2;
                list = null;
            }
            boolean z = false;
            int i2 = 0;
            while (!z) {
                if (compare(bArr2, bArr3) >= 0) {
                    z = true;
                }
                cMap.addCharMapping(bArr2, createStringFromBytes(bArr));
                increment(bArr2);
                if (list == null) {
                    increment(bArr);
                } else {
                    i2++;
                    if (i2 < list.size()) {
                        bArr = (byte[]) list.get(i2);
                    }
                }
            }
        }
    }

    protected InputStream getExternalCMap(String str) throws IOException {
        if (PDFBoxResourceLoader.isReady()) {
            return PDFBoxResourceLoader.getStream("com/tom_roush/fontbox/resources/cmap/" + str);
        }
        URL resource = getClass().getResource("/com/tom_roush/fontbox/resources/cmap/" + str);
        if (resource == null) {
            throw new IOException("Error: Could not find referenced cmap stream " + str);
        }
        return resource.openStream();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Object parseNextToken(java.io.PushbackInputStream r11) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tom_roush.fontbox.cmap.CMapParser.parseNextToken(java.io.PushbackInputStream):java.lang.Object");
    }

    private void readUntilEndOfLine(InputStream inputStream, StringBuffer stringBuffer) throws IOException {
        int i = inputStream.read();
        while (i != -1 && i != 13 && i != 10) {
            stringBuffer.append((char) i);
            i = inputStream.read();
        }
    }

    private void increment(byte[] bArr) {
        increment(bArr, bArr.length - 1);
    }

    private void increment(byte[] bArr, int i) {
        if (i > 0 && (bArr[i] + 256) % 256 == 255) {
            bArr[i] = 0;
            increment(bArr, i - 1);
        } else {
            bArr[i] = (byte) (bArr[i] + 1);
        }
    }

    private int createIntFromBytes(byte[] bArr) {
        int i = (bArr[0] + 256) % 256;
        return bArr.length == 2 ? (i << 8) + ((bArr[1] + 256) % 256) : i;
    }

    private String createStringFromBytes(byte[] bArr) throws IOException {
        if (bArr.length == 1) {
            return new String(bArr, Charsets.ISO_8859_1);
        }
        return new String(bArr, Charsets.UTF_16BE);
    }

    private int compare(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return (b + 256) % 256 < (b2 + 256) % 256 ? -1 : 1;
            }
        }
        return 1;
    }

    private final class LiteralName {
        private String name;

        private LiteralName(String str) {
            this.name = str;
        }
    }

    private final class Operator {

        private String f2223op;

        private Operator(String str) {
            this.f2223op = str;
        }
    }
}
