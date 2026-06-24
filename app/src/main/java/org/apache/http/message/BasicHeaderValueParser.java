package org.apache.http.message;

import java.util.ArrayList;
import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderValueParser implements HeaderValueParser {

    @Deprecated
    public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
    public static final BasicHeaderValueParser INSTANCE = new BasicHeaderValueParser();
    private static final char PARAM_DELIMITER = ';';
    private static final char ELEM_DELIMITER = ',';
    private static final char[] ALL_DELIMITERS = {PARAM_DELIMITER, ELEM_DELIMITER};

    private static boolean isOneOf(char c, char[] cArr) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c == c2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static HeaderElement[] parseElements(String str, HeaderValueParser headerValueParser) {
        Args.notNull(str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (headerValueParser == null) {
            headerValueParser = INSTANCE;
        }
        return headerValueParser.parseElements(charArrayBuffer, parserCursor);
    }

    public static HeaderElement parseHeaderElement(String str, HeaderValueParser headerValueParser) {
        Args.notNull(str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (headerValueParser == null) {
            headerValueParser = INSTANCE;
        }
        return headerValueParser.parseHeaderElement(charArrayBuffer, parserCursor);
    }

    public static NameValuePair parseNameValuePair(String str, HeaderValueParser headerValueParser) {
        Args.notNull(str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (headerValueParser == null) {
            headerValueParser = INSTANCE;
        }
        return headerValueParser.parseNameValuePair(charArrayBuffer, parserCursor);
    }

    public static NameValuePair[] parseParameters(String str, HeaderValueParser headerValueParser) {
        Args.notNull(str, "Value");
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, str.length());
        if (headerValueParser == null) {
            headerValueParser = INSTANCE;
        }
        return headerValueParser.parseParameters(charArrayBuffer, parserCursor);
    }

    protected HeaderElement createHeaderElement(String str, String str2, NameValuePair[] nameValuePairArr) {
        return new BasicHeaderElement(str, str2, nameValuePairArr);
    }

    protected NameValuePair createNameValuePair(String str, String str2) {
        return new BasicNameValuePair(str, str2);
    }

    @Override
    public HeaderElement[] parseElements(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(parserCursor, "Parser cursor");
        ArrayList arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            HeaderElement headerElement = parseHeaderElement(charArrayBuffer, parserCursor);
            if (headerElement.getName().length() != 0 || headerElement.getValue() != null) {
                arrayList.add(headerElement);
            }
        }
        return (HeaderElement[]) arrayList.toArray(new HeaderElement[arrayList.size()]);
    }

    @Override
    public HeaderElement parseHeaderElement(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(parserCursor, "Parser cursor");
        NameValuePair nameValuePair = parseNameValuePair(charArrayBuffer, parserCursor);
        return createHeaderElement(nameValuePair.getName(), nameValuePair.getValue(), (parserCursor.atEnd() || charArrayBuffer.charAt(parserCursor.getPos() + (-1)) == ',') ? null : parseParameters(charArrayBuffer, parserCursor));
    }

    @Override
    public NameValuePair parseNameValuePair(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        return parseNameValuePair(charArrayBuffer, parserCursor, ALL_DELIMITERS);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.apache.http.NameValuePair parseNameValuePair(org.apache.http.util.CharArrayBuffer r13, org.apache.http.message.ParserCursor r14, char[] r15) {
        /*
            r12 = this;
            java.lang.String r0 = "Char array buffer"
            org.apache.http.util.Args.notNull(r13, r0)
            java.lang.String r0 = "Parser cursor"
            org.apache.http.util.Args.notNull(r14, r0)
            int r0 = r14.getPos()
            int r1 = r14.getPos()
            int r2 = r14.getUpperBound()
        L16:
            r3 = 1
            r4 = 0
            if (r0 >= r2) goto L2e
            char r5 = r13.charAt(r0)
            r6 = 61
            if (r5 != r6) goto L23
            goto L2e
        L23:
            boolean r5 = isOneOf(r5, r15)
            if (r5 == 0) goto L2b
            r5 = r3
            goto L2f
        L2b:
            int r0 = r0 + 1
            goto L16
        L2e:
            r5 = r4
        L2f:
            if (r0 != r2) goto L37
            java.lang.String r1 = r13.substringTrimmed(r1, r2)
            r5 = r3
            goto L3d
        L37:
            java.lang.String r1 = r13.substringTrimmed(r1, r0)
            int r0 = r0 + 1
        L3d:
            if (r5 == 0) goto L48
            r14.updatePos(r0)
            r13 = 0
        L43:
            org.apache.http.NameValuePair r13 = r12.createNameValuePair(r1, r13)
            return r13
        L48:
            r6 = r0
            r7 = r4
            r8 = r7
        L4b:
            r9 = 34
            if (r6 >= r2) goto L73
            char r10 = r13.charAt(r6)
            if (r10 != r9) goto L59
            if (r7 != 0) goto L59
            r8 = r8 ^ 1
        L59:
            if (r8 != 0) goto L64
            if (r7 != 0) goto L64
            boolean r11 = isOneOf(r10, r15)
            if (r11 == 0) goto L64
            goto L74
        L64:
            if (r7 == 0) goto L67
            goto L6f
        L67:
            if (r8 == 0) goto L6f
            r7 = 92
            if (r10 != r7) goto L6f
            r7 = r3
            goto L70
        L6f:
            r7 = r4
        L70:
            int r6 = r6 + 1
            goto L4b
        L73:
            r3 = r5
        L74:
            if (r0 >= r6) goto L83
            char r15 = r13.charAt(r0)
            boolean r15 = org.apache.http.protocol.HTTP.isWhitespace(r15)
            if (r15 == 0) goto L83
            int r0 = r0 + 1
            goto L74
        L83:
            r15 = r6
        L84:
            if (r15 <= r0) goto L95
            int r2 = r15 + (-1)
            char r2 = r13.charAt(r2)
            boolean r2 = org.apache.http.protocol.HTTP.isWhitespace(r2)
            if (r2 == 0) goto L95
            int r15 = r15 + (-1)
            goto L84
        L95:
            int r2 = r15 - r0
            r4 = 2
            if (r2 < r4) goto Lac
            char r2 = r13.charAt(r0)
            if (r2 != r9) goto Lac
            int r2 = r15 + (-1)
            char r2 = r13.charAt(r2)
            if (r2 != r9) goto Lac
            int r0 = r0 + 1
            int r15 = r15 + (-1)
        Lac:
            java.lang.String r13 = r13.substring(r0, r15)
            if (r3 == 0) goto Lb4
            int r6 = r6 + 1
        Lb4:
            r14.updatePos(r6)
            goto L43
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.http.message.BasicHeaderValueParser.parseNameValuePair(org.apache.http.util.CharArrayBuffer, org.apache.http.message.ParserCursor, char[]):org.apache.http.NameValuePair");
    }

    @Override
    public NameValuePair[] parseParameters(CharArrayBuffer charArrayBuffer, ParserCursor parserCursor) {
        Args.notNull(charArrayBuffer, "Char array buffer");
        Args.notNull(parserCursor, "Parser cursor");
        int pos = parserCursor.getPos();
        int upperBound = parserCursor.getUpperBound();
        while (pos < upperBound && HTTP.isWhitespace(charArrayBuffer.charAt(pos))) {
            pos++;
        }
        parserCursor.updatePos(pos);
        if (parserCursor.atEnd()) {
            return new NameValuePair[0];
        }
        ArrayList arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            arrayList.add(parseNameValuePair(charArrayBuffer, parserCursor));
            if (charArrayBuffer.charAt(parserCursor.getPos() - 1) == ',') {
                break;
            }
        }
        return (NameValuePair[]) arrayList.toArray(new NameValuePair[arrayList.size()]);
    }
}
