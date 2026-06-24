package org.xmlpull.mxp1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javax.xml.XMLConstants;
import kotlin.text.Typography;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;

public class MXParser implements XmlPullParser {
    protected static final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    protected static final String FEATURE_XML_ROUNDTRIP = "http://xmlpull.org/v1/doc/features.html#xml-roundtrip";
    protected static final int LOOKUP_MAX = 1024;
    protected static final char LOOKUP_MAX_CHAR = 1024;
    protected static final String PROPERTY_XMLDECL_CONTENT = "http://xmlpull.org/v1/doc/properties.html#xmldecl-content";
    protected static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    protected static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";
    protected static final int READ_CHUNK_SIZE = 8192;
    private static final boolean TRACE_SIZING = false;
    protected static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    protected static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    protected boolean allStringsInterned;
    protected int attributeCount;
    protected String[] attributeName;
    protected int[] attributeNameHash;
    protected String[] attributePrefix;
    protected String[] attributeUri;
    protected String[] attributeValue;
    protected char[] buf;
    protected int bufAbsoluteStart;
    protected int bufEnd;
    protected int bufLoadFactor = 95;
    protected int bufSoftLimit;
    protected int bufStart;
    protected char[] charRefOneCharBuf;
    protected int columnNumber;
    protected int depth;
    protected String[] elName;
    protected int[] elNamespaceCount;
    protected String[] elPrefix;
    protected char[][] elRawName;
    protected int[] elRawNameEnd;
    protected int[] elRawNameLine;
    protected String[] elUri;
    protected boolean emptyElementTag;
    protected int entityEnd;
    protected String[] entityName;
    protected char[][] entityNameBuf;
    protected int[] entityNameHash;
    protected String entityRefName;
    protected String[] entityReplacement;
    protected char[][] entityReplacementBuf;
    protected int eventType;
    protected String inputEncoding;
    protected int lineNumber;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected int[] namespacePrefixHash;
    protected String[] namespaceUri;
    protected boolean pastEndTag;

    protected char[] f3790pc;
    protected int pcEnd;
    protected int pcStart;
    protected int pos;
    protected int posEnd;
    protected int posStart;
    protected boolean preventBufferCompaction;
    protected boolean processNamespaces;
    protected boolean reachedEnd;
    protected Reader reader;
    protected boolean roundtripSupported;
    protected boolean seenAmpersand;
    protected boolean seenDocdecl;
    protected boolean seenEndTag;
    protected boolean seenMarkup;
    protected boolean seenRoot;
    protected boolean seenStartTag;
    protected String text;
    protected boolean tokenize;
    protected boolean usePC;
    protected String xmlDeclContent;
    protected Boolean xmlDeclStandalone;
    protected String xmlDeclVersion;
    protected static final char[] VERSION = {'v', 'e', 'r', 's', 'i', 'o', 'n'};
    protected static final char[] NCODING = {'n', 'c', 'o', 'd', 'i', 'n', 'g'};
    protected static final char[] TANDALONE = {'t', 'a', 'n', 'd', 'a', 'l', 'o', 'n', 'e'};
    protected static final char[] YES = {'y', 'e', 's'};

    protected static final char[] f3789NO = {'n', 'o'};
    protected static boolean[] lookupNameStartChar = new boolean[1024];
    protected static boolean[] lookupNameChar = new boolean[1024];

    protected boolean isS(char c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t';
    }

    protected void resetStringCache() {
    }

    protected String newString(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    protected String newStringIntern(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2).intern();
    }

    protected void ensureElementsCapacity() {
        String[] strArr = this.elName;
        int length = strArr != null ? strArr.length : 0;
        int i = this.depth;
        if (i + 1 >= length) {
            int i2 = (i >= 7 ? i * 2 : 8) + 2;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(strArr, 0, strArr2, 0, length);
            }
            this.elName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.elPrefix, 0, strArr3, 0, length);
            }
            this.elPrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.elUri, 0, strArr4, 0, length);
            }
            this.elUri = strArr4;
            int[] iArr = new int[i2];
            if (z) {
                System.arraycopy(this.elNamespaceCount, 0, iArr, 0, length);
            } else {
                iArr[0] = 0;
            }
            this.elNamespaceCount = iArr;
            int[] iArr2 = new int[i2];
            if (z) {
                System.arraycopy(this.elRawNameEnd, 0, iArr2, 0, length);
            }
            this.elRawNameEnd = iArr2;
            int[] iArr3 = new int[i2];
            if (z) {
                System.arraycopy(this.elRawNameLine, 0, iArr3, 0, length);
            }
            this.elRawNameLine = iArr3;
            char[][] cArr = new char[i2][];
            if (z) {
                System.arraycopy(this.elRawName, 0, cArr, 0, length);
            }
            this.elRawName = cArr;
        }
    }

    protected void ensureAttributesCapacity(int i) {
        String[] strArr = this.attributeName;
        int length = strArr != null ? strArr.length : 0;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            boolean z = length > 0;
            String[] strArr2 = new String[i2];
            if (z) {
                System.arraycopy(strArr, 0, strArr2, 0, length);
            }
            this.attributeName = strArr2;
            String[] strArr3 = new String[i2];
            if (z) {
                System.arraycopy(this.attributePrefix, 0, strArr3, 0, length);
            }
            this.attributePrefix = strArr3;
            String[] strArr4 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeUri, 0, strArr4, 0, length);
            }
            this.attributeUri = strArr4;
            String[] strArr5 = new String[i2];
            if (z) {
                System.arraycopy(this.attributeValue, 0, strArr5, 0, length);
            }
            this.attributeValue = strArr5;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            if (z) {
                System.arraycopy(this.attributeNameHash, 0, iArr, 0, length);
            }
            this.attributeNameHash = iArr;
        }
    }

    protected void ensureNamespacesCapacity(int i) {
        String[] strArr = this.namespacePrefix;
        if (i >= (strArr != null ? strArr.length : 0)) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr2 = new String[i2];
            String[] strArr3 = new String[i2];
            if (strArr != null) {
                System.arraycopy(strArr, 0, strArr2, 0, this.namespaceEnd);
                System.arraycopy(this.namespaceUri, 0, strArr3, 0, this.namespaceEnd);
            }
            this.namespacePrefix = strArr2;
            this.namespaceUri = strArr3;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            int[] iArr2 = this.namespacePrefixHash;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, this.namespaceEnd);
            }
            this.namespacePrefixHash = iArr;
        }
    }

    protected static final int fastHash(char[] cArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        int i3 = (cArr[i] << 7) + cArr[(i + i2) - 1];
        if (i2 > 16) {
            i3 = (i3 << 7) + cArr[(i2 / 4) + i];
        }
        return i2 > 8 ? (i3 << 7) + cArr[i + (i2 / 2)] : i3;
    }

    protected void ensureEntityCapacity() {
        char[][] cArr = this.entityReplacementBuf;
        int length = cArr != null ? cArr.length : 0;
        int i = this.entityEnd;
        if (i >= length) {
            int i2 = i > 7 ? i * 2 : 8;
            String[] strArr = new String[i2];
            char[][] cArr2 = new char[i2][];
            String[] strArr2 = new String[i2];
            char[][] cArr3 = new char[i2][];
            String[] strArr3 = this.entityName;
            if (strArr3 != null) {
                System.arraycopy(strArr3, 0, strArr, 0, i);
                System.arraycopy(this.entityReplacementBuf, 0, strArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacement, 0, strArr2, 0, this.entityEnd);
                System.arraycopy(this.entityReplacementBuf, 0, cArr3, 0, this.entityEnd);
            }
            this.entityName = strArr;
            this.entityNameBuf = cArr2;
            this.entityReplacement = strArr2;
            this.entityReplacementBuf = cArr3;
            if (this.allStringsInterned) {
                return;
            }
            int[] iArr = new int[i2];
            int[] iArr2 = this.entityNameHash;
            if (iArr2 != null) {
                System.arraycopy(iArr2, 0, iArr, 0, this.entityEnd);
            }
            this.entityNameHash = iArr;
        }
    }

    protected void reset() {
        this.lineNumber = 1;
        this.columnNumber = 0;
        this.seenRoot = false;
        this.reachedEnd = false;
        this.eventType = 0;
        this.emptyElementTag = false;
        this.depth = 0;
        this.attributeCount = 0;
        this.namespaceEnd = 0;
        this.entityEnd = 0;
        this.reader = null;
        this.inputEncoding = null;
        this.preventBufferCompaction = false;
        this.bufAbsoluteStart = 0;
        this.bufStart = 0;
        this.bufEnd = 0;
        this.posEnd = 0;
        this.posStart = 0;
        this.pos = 0;
        this.pcStart = 0;
        this.pcEnd = 0;
        this.usePC = false;
        this.seenStartTag = false;
        this.seenEndTag = false;
        this.pastEndTag = false;
        this.seenAmpersand = false;
        this.seenMarkup = false;
        this.seenDocdecl = false;
        this.xmlDeclVersion = null;
        this.xmlDeclStandalone = null;
        this.xmlDeclContent = null;
        resetStringCache();
    }

    public MXParser() {
        char[] cArr = new char[Runtime.getRuntime().freeMemory() > 1000000 ? 8192 : 256];
        this.buf = cArr;
        this.bufSoftLimit = (this.bufLoadFactor * cArr.length) / 100;
        this.f3790pc = new char[Runtime.getRuntime().freeMemory() <= 1000000 ? 64 : 8192];
        this.charRefOneCharBuf = new char[1];
    }

    @Override
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            if (this.eventType != 0) {
                throw new XmlPullParserException("namespace processing feature can only be changed before parsing", this, null);
            }
            this.processNamespaces = z;
        } else if (FEATURE_NAMES_INTERNED.equals(str)) {
            if (z) {
                throw new XmlPullParserException("interning names in this implementation is not supported");
            }
        } else if ("http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str)) {
            if (z) {
                throw new XmlPullParserException("processing DOCDECL is not supported");
            }
        } else {
            if (FEATURE_XML_ROUNDTRIP.equals(str)) {
                this.roundtripSupported = z;
                return;
            }
            throw new XmlPullParserException(new StringBuffer("unsupporte feature ").append(str).toString());
        }
    }

    @Override
    public boolean getFeature(String str) {
        if (str == null) {
            throw new IllegalArgumentException("feature name should not be nulll");
        }
        if ("http://xmlpull.org/v1/doc/features.html#process-namespaces".equals(str)) {
            return this.processNamespaces;
        }
        if (FEATURE_NAMES_INTERNED.equals(str) || "http://xmlpull.org/v1/doc/features.html#process-docdecl".equals(str) || !FEATURE_XML_ROUNDTRIP.equals(str)) {
            return false;
        }
        return this.roundtripSupported;
    }

    @Override
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException(new StringBuffer("unsupported property: '").append(str).append("'").toString());
    }

    @Override
    public Object getProperty(String str) {
        if (str == null) {
            throw new IllegalArgumentException("property name should not be nulll");
        }
        if (PROPERTY_XMLDECL_VERSION.equals(str)) {
            return this.xmlDeclVersion;
        }
        if (PROPERTY_XMLDECL_STANDALONE.equals(str)) {
            return this.xmlDeclStandalone;
        }
        if (PROPERTY_XMLDECL_CONTENT.equals(str)) {
            return this.xmlDeclContent;
        }
        return null;
    }

    @Override
    public void setInput(Reader reader) throws XmlPullParserException {
        reset();
        this.reader = reader;
    }

    @Override
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        InputStreamReader inputStreamReader;
        if (inputStream == null) {
            throw new IllegalArgumentException("input stream can not be null");
        }
        if (str != null) {
            try {
                if (str != null) {
                    inputStreamReader = new InputStreamReader(inputStream, str);
                } else {
                    inputStreamReader = new InputStreamReader(inputStream);
                }
            } catch (UnsupportedEncodingException e) {
                throw new XmlPullParserException(new StringBuffer("could not create reader for encoding ").append(str).append(" : ").append(e).toString(), this, e);
            }
        } else {
            inputStreamReader = new InputStreamReader(inputStream);
        }
        setInput(inputStreamReader);
        this.inputEncoding = str;
    }

    @Override
    public String getInputEncoding() {
        return this.inputEncoding;
    }

    @Override
    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        ensureEntityCapacity();
        this.entityName[this.entityEnd] = newString(str.toCharArray(), 0, str.length());
        this.entityNameBuf[this.entityEnd] = str.toCharArray();
        String[] strArr = this.entityReplacement;
        int i = this.entityEnd;
        strArr[i] = str2;
        this.entityReplacementBuf[i] = str2.toCharArray();
        if (!this.allStringsInterned) {
            int[] iArr = this.entityNameHash;
            int i2 = this.entityEnd;
            char[] cArr = this.entityNameBuf[i2];
            iArr[i2] = fastHash(cArr, 0, cArr.length);
        }
        this.entityEnd++;
    }

    @Override
    public int getNamespaceCount(int i) throws XmlPullParserException {
        if (!this.processNamespaces || i == 0) {
            return 0;
        }
        if (i < 0 || i > this.depth) {
            throw new IllegalArgumentException(new StringBuffer("napespace count mayt be for depth 0..").append(this.depth).append(" not ").append(i).toString());
        }
        return this.elNamespaceCount[i];
    }

    @Override
    public String getNamespacePrefix(int i) throws XmlPullParserException {
        if (i < this.namespaceEnd) {
            return this.namespacePrefix[i];
        }
        throw new XmlPullParserException(new StringBuffer("position ").append(i).append(" exceeded number of available namespaces ").append(this.namespaceEnd).toString());
    }

    @Override
    public String getNamespaceUri(int i) throws XmlPullParserException {
        if (i < this.namespaceEnd) {
            return this.namespaceUri[i];
        }
        throw new XmlPullParserException(new StringBuffer("position ").append(i).append(" exceedded number of available namespaces ").append(this.namespaceEnd).toString());
    }

    @Override
    public String getNamespace(String str) {
        if (str != null) {
            for (int i = this.namespaceEnd - 1; i >= 0; i--) {
                if (str.equals(this.namespacePrefix[i])) {
                    return this.namespaceUri[i];
                }
            }
            if (XMLConstants.XML_NS_PREFIX.equals(str)) {
                return "http://www.w3.org/XML/1998/namespace";
            }
            if (XMLConstants.XMLNS_ATTRIBUTE.equals(str)) {
                return "http://www.w3.org/2000/xmlns/";
            }
            return null;
        }
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            if (this.namespacePrefix[i2] == null) {
                return this.namespaceUri[i2];
            }
        }
        return null;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    private static int findFragment(int i, char[] cArr, int i2, int i3) {
        if (i2 < i) {
            return i > i3 ? i3 : i;
        }
        if (i3 - i2 > 65) {
            i2 = i3 - 10;
        }
        int i4 = i2 + 1;
        while (true) {
            i4--;
            if (i4 <= i || i3 - i4 > 65 || (cArr[i4] == '<' && i2 - i4 > 10)) {
                break;
            }
        }
        return i4;
    }

    @Override
    public String getPositionDescription() {
        int i = this.posStart;
        int i2 = this.pos;
        if (i <= i2) {
            int iFindFragment = findFragment(0, this.buf, i, i2);
            str = iFindFragment < this.pos ? new String(this.buf, iFindFragment, this.pos - iFindFragment) : null;
            if (this.bufAbsoluteStart > 0 || iFindFragment > 0) {
                str = new StringBuffer("...").append(str).toString();
            }
        }
        return new StringBuffer(" ").append(XmlPullParser.TYPES[this.eventType]).append(str != null ? new StringBuffer(" seen ").append(printable(str)).append("...").toString() : "").append(" @").append(getLineNumber()).append(":").append(getColumnNumber()).toString();
    }

    @Override
    public int getLineNumber() {
        return this.lineNumber;
    }

    @Override
    public int getColumnNumber() {
        return this.columnNumber;
    }

    @Override
    public boolean isWhitespace() throws XmlPullParserException {
        int i = this.eventType;
        if (i != 4 && i != 5) {
            if (i == 7) {
                return true;
            }
            throw new XmlPullParserException("no content available to check for whitespaces");
        }
        if (this.usePC) {
            for (int i2 = this.pcStart; i2 < this.pcEnd; i2++) {
                if (!isS(this.f3790pc[i2])) {
                    return false;
                }
            }
            return true;
        }
        for (int i3 = this.posStart; i3 < this.posEnd; i3++) {
            if (!isS(this.buf[i3])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getText() {
        int i = this.eventType;
        if (i == 0 || i == 1) {
            return null;
        }
        if (i == 6) {
            return this.text;
        }
        if (this.text == null) {
            if (!this.usePC || i == 2 || i == 3) {
                char[] cArr = this.buf;
                int i2 = this.posStart;
                this.text = new String(cArr, i2, this.posEnd - i2);
            } else {
                char[] cArr2 = this.f3790pc;
                int i3 = this.pcStart;
                this.text = new String(cArr2, i3, this.pcEnd - i3);
            }
        }
        return this.text;
    }

    @Override
    public char[] getTextCharacters(int[] iArr) {
        int i = this.eventType;
        if (i == 4) {
            if (this.usePC) {
                int i2 = this.pcStart;
                iArr[0] = i2;
                iArr[1] = this.pcEnd - i2;
                return this.f3790pc;
            }
            int i3 = this.posStart;
            iArr[0] = i3;
            iArr[1] = this.posEnd - i3;
            return this.buf;
        }
        if (i == 2 || i == 3 || i == 5 || i == 9 || i == 6 || i == 8 || i == 7 || i == 10) {
            int i4 = this.posStart;
            iArr[0] = i4;
            iArr[1] = this.posEnd - i4;
            return this.buf;
        }
        if (i == 0 || i == 1) {
            iArr[1] = -1;
            iArr[0] = -1;
            return null;
        }
        throw new IllegalArgumentException(new StringBuffer("unknown text eventType: ").append(this.eventType).toString());
    }

    @Override
    public String getNamespace() {
        int i = this.eventType;
        if (i == 2) {
            return this.processNamespaces ? this.elUri[this.depth] : "";
        }
        if (i == 3) {
            return this.processNamespaces ? this.elUri[this.depth] : "";
        }
        return null;
    }

    @Override
    public String getName() {
        int i = this.eventType;
        if (i == 2) {
            return this.elName[this.depth];
        }
        if (i == 3) {
            return this.elName[this.depth];
        }
        if (i != 6) {
            return null;
        }
        if (this.entityRefName == null) {
            char[] cArr = this.buf;
            int i2 = this.posStart;
            this.entityRefName = newString(cArr, i2, this.posEnd - i2);
        }
        return this.entityRefName;
    }

    @Override
    public String getPrefix() {
        int i = this.eventType;
        if (i == 2) {
            return this.elPrefix[this.depth];
        }
        if (i == 3) {
            return this.elPrefix[this.depth];
        }
        return null;
    }

    @Override
    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.eventType != 2) {
            throw new XmlPullParserException("parser must be on START_TAG to check for empty element", this, null);
        }
        return this.emptyElementTag;
    }

    @Override
    public int getAttributeCount() {
        if (this.eventType != 2) {
            return -1;
        }
        return this.attributeCount;
    }

    @Override
    public String getAttributeNamespace(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (!this.processNamespaces) {
            return "";
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeUri[i];
    }

    @Override
    public String getAttributeName(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeName[i];
    }

    @Override
    public String getAttributePrefix(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (!this.processNamespaces) {
            return null;
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributePrefix[i];
    }

    @Override
    public String getAttributeType(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return "CDATA";
    }

    @Override
    public boolean isAttributeDefault(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return false;
    }

    @Override
    public String getAttributeValue(int i) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException("only START_TAG can have attributes");
        }
        if (i < 0 || i >= this.attributeCount) {
            throw new IndexOutOfBoundsException(new StringBuffer("attribute position must be 0..").append(this.attributeCount - 1).append(" and not ").append(i).toString());
        }
        return this.attributeValue[i];
    }

    @Override
    public String getAttributeValue(String str, String str2) {
        if (this.eventType != 2) {
            throw new IndexOutOfBoundsException(new StringBuffer("only START_TAG can have attributes").append(getPositionDescription()).toString());
        }
        if (str2 == null) {
            throw new IllegalArgumentException("attribute name can not be null");
        }
        int i = 0;
        if (this.processNamespaces) {
            if (str == null) {
                str = "";
            }
            String str3 = str;
            while (i < this.attributeCount) {
                String str4 = this.attributeUri[i];
                if ((str3 == str4 || str3.equals(str4)) && str2.equals(this.attributeName[i])) {
                    return this.attributeValue[i];
                }
                i++;
            }
        } else {
            if (str != null && str.length() == 0) {
                str = null;
            }
            if (str != null) {
                throw new IllegalArgumentException("when namespaces processing is disabled attribute namespace must be null");
            }
            while (i < this.attributeCount) {
                if (str2.equals(this.attributeName[i])) {
                    return this.attributeValue[i];
                }
                i++;
            }
        }
        return null;
    }

    @Override
    public int getEventType() throws XmlPullParserException {
        return this.eventType;
    }

    @Override
    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        if (!this.processNamespaces && str != null) {
            throw new XmlPullParserException(new StringBuffer("processing namespaces must be enabled on parser (or factory) to have possible namespaces delcared on elements (postion:").append(getPositionDescription()).append(")").toString());
        }
        if (i == getEventType() && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        String string = "";
        StringBuffer stringBufferAppend = new StringBuffer("expected event ").append(XmlPullParser.TYPES[i]).append(str2 != null ? new StringBuffer(" with name '").append(str2).append("'").toString() : "").append((str == null || str2 == null) ? "" : " and").append(str != null ? new StringBuffer(" with namespace '").append(str).append("'").toString() : "").append(" but got").append(i != getEventType() ? new StringBuffer(" ").append(XmlPullParser.TYPES[getEventType()]).toString() : "").append((str2 == null || getName() == null || str2.equals(getName())) ? "" : new StringBuffer(" name '").append(getName()).append("'").toString()).append((str == null || str2 == null || getName() == null || str2.equals(getName()) || getNamespace() == null || str.equals(getNamespace())) ? "" : " and");
        if (str != null && getNamespace() != null && !str.equals(getNamespace())) {
            string = new StringBuffer(" namespace '").append(getNamespace()).append("'").toString();
        }
        throw new XmlPullParserException(stringBufferAppend.append(string).append(" (postion:").append(getPositionDescription()).append(")").toString());
    }

    @Override
    public String nextText() throws XmlPullParserException, IOException {
        if (getEventType() != 2) {
            throw new XmlPullParserException("parser must be on START_TAG to read next text", this, null);
        }
        int next = next();
        if (next != 4) {
            if (next == 3) {
                return "";
            }
            throw new XmlPullParserException("parser must be on START_TAG or TEXT to read text", this, null);
        }
        String text = getText();
        if (next() == 3) {
            return text;
        }
        throw new XmlPullParserException(new StringBuffer("TEXT must be immediately followed by END_TAG and not ").append(XmlPullParser.TYPES[getEventType()]).toString(), this, null);
    }

    @Override
    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.eventType == 4 && isWhitespace()) {
            next();
        }
        int i = this.eventType;
        if (i == 2 || i == 3) {
            return i;
        }
        throw new XmlPullParserException(new StringBuffer("expected START_TAG or END_TAG not ").append(XmlPullParser.TYPES[getEventType()]).toString(), this, null);
    }

    @Override
    public int next() throws XmlPullParserException, IOException {
        this.tokenize = false;
        return nextImpl();
    }

    @Override
    public int nextToken() throws XmlPullParserException, IOException {
        this.tokenize = true;
        return nextImpl();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected int nextImpl() throws org.xmlpull.p065v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 619
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.nextImpl():int");
    }

    protected int parseProlog() throws XmlPullParserException, IOException {
        char cMore;
        if (this.seenMarkup) {
            cMore = this.buf[this.pos - 1];
        } else {
            cMore = more();
        }
        if (this.eventType == 0) {
            if (cMore == 65534) {
                throw new XmlPullParserException("first character in input was UNICODE noncharacter (0xFFFE)- input requires int swapping", this, null);
            }
            if (cMore == 65279) {
                cMore = more();
            }
        }
        this.seenMarkup = false;
        this.posStart = this.pos - 1;
        boolean z = this.tokenize && !this.roundtripSupported;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            if (cMore == '<') {
                if (z2 && this.tokenize) {
                    this.posEnd = this.pos - 1;
                    this.seenMarkup = true;
                    this.eventType = 7;
                    return 7;
                }
                char cMore2 = more();
                if (cMore2 == '?') {
                    if (parsePI()) {
                        if (this.tokenize) {
                            this.eventType = 8;
                            return 8;
                        }
                    } else {
                        this.posStart = this.pos;
                        z2 = false;
                    }
                } else {
                    if (cMore2 != '!') {
                        if (cMore2 == '/') {
                            throw new XmlPullParserException(new StringBuffer("expected start tag name and not ").append(printable(cMore2)).toString(), this, null);
                        }
                        if (isNameStartChar(cMore2)) {
                            this.seenRoot = true;
                            return parseStartTag();
                        }
                        throw new XmlPullParserException(new StringBuffer("expected start tag name and not ").append(printable(cMore2)).toString(), this, null);
                    }
                    char cMore3 = more();
                    if (cMore3 == 'D') {
                        if (this.seenDocdecl) {
                            throw new XmlPullParserException("only one docdecl allowed in XML document", this, null);
                        }
                        this.seenDocdecl = true;
                        parseDocdecl();
                        if (this.tokenize) {
                            this.eventType = 10;
                            return 10;
                        }
                    } else if (cMore3 == '-') {
                        parseComment();
                        if (this.tokenize) {
                            this.eventType = 9;
                            return 9;
                        }
                    } else {
                        throw new XmlPullParserException(new StringBuffer("unexpected markup <!").append(printable(cMore3)).toString(), this, null);
                    }
                }
            } else {
                if (!isS(cMore)) {
                    throw new XmlPullParserException(new StringBuffer("only whitespace content allowed before start tag and not ").append(printable(cMore)).toString(), this, null);
                }
                if (!z) {
                    z2 = true;
                } else if (cMore == '\r') {
                    if (!this.usePC) {
                        int i = this.pos - 1;
                        this.posEnd = i;
                        if (i > this.posStart) {
                            joinPC();
                        } else {
                            this.usePC = true;
                            this.pcEnd = 0;
                            this.pcStart = 0;
                        }
                    }
                    int i2 = this.pcEnd;
                    if (i2 >= this.f3790pc.length) {
                        ensurePC(i2);
                    }
                    char[] cArr = this.f3790pc;
                    int i3 = this.pcEnd;
                    this.pcEnd = i3 + 1;
                    cArr[i3] = '\n';
                    z2 = true;
                    z3 = true;
                } else {
                    if (cMore == '\n') {
                        if (!z3 && this.usePC) {
                            int i4 = this.pcEnd;
                            if (i4 >= this.f3790pc.length) {
                                ensurePC(i4);
                            }
                            char[] cArr2 = this.f3790pc;
                            int i5 = this.pcEnd;
                            this.pcEnd = i5 + 1;
                            cArr2[i5] = '\n';
                        }
                    } else if (this.usePC) {
                        int i6 = this.pcEnd;
                        if (i6 >= this.f3790pc.length) {
                            ensurePC(i6);
                        }
                        char[] cArr3 = this.f3790pc;
                        int i7 = this.pcEnd;
                        this.pcEnd = i7 + 1;
                        cArr3[i7] = cMore;
                    }
                    z2 = true;
                    z3 = false;
                }
            }
            cMore = more();
        }
    }

    protected int parseEpilog() throws XmlPullParserException, IOException {
        char cMore;
        if (this.eventType == 1) {
            throw new XmlPullParserException("already reached end of XML input", this, null);
        }
        if (this.reachedEnd) {
            this.eventType = 1;
            return 1;
        }
        boolean z = false;
        boolean z2 = this.tokenize && !this.roundtripSupported;
        try {
            if (this.seenMarkup) {
                cMore = this.buf[this.pos - 1];
            } else {
                cMore = more();
            }
            this.seenMarkup = false;
            this.posStart = this.pos - 1;
            boolean z3 = false;
            boolean z4 = false;
            while (true) {
                if (cMore == '<') {
                    if (z3) {
                        try {
                            if (this.tokenize) {
                                this.posEnd = this.pos - 1;
                                this.seenMarkup = true;
                                this.eventType = 7;
                                return 7;
                            }
                        } catch (EOFException unused) {
                            z = z3;
                            this.reachedEnd = true;
                            if (!this.tokenize) {
                            }
                            this.eventType = 1;
                            return 1;
                        }
                    }
                    char cMore2 = more();
                    if (cMore2 == '?') {
                        parsePI();
                        if (this.tokenize) {
                            this.eventType = 8;
                            return 8;
                        }
                    } else {
                        if (cMore2 != '!') {
                            if (cMore2 == '/') {
                                throw new XmlPullParserException(new StringBuffer().append("end tag not allowed in epilog but got ").append(printable(cMore2)).toString(), this, null);
                            }
                            if (isNameStartChar(cMore2)) {
                                throw new XmlPullParserException(new StringBuffer().append("start tag not allowed in epilog but got ").append(printable(cMore2)).toString(), this, null);
                            }
                            throw new XmlPullParserException(new StringBuffer().append("in epilog expected ignorable content and not ").append(printable(cMore2)).toString(), this, null);
                        }
                        char cMore3 = more();
                        if (cMore3 == 'D') {
                            parseDocdecl();
                            if (this.tokenize) {
                                this.eventType = 10;
                                return 10;
                            }
                        } else if (cMore3 == '-') {
                            parseComment();
                            if (this.tokenize) {
                                this.eventType = 9;
                                return 9;
                            }
                        } else {
                            throw new XmlPullParserException(new StringBuffer().append("unexpected markup <!").append(printable(cMore3)).toString(), this, null);
                        }
                    }
                    cMore = more();
                } else {
                    if (!isS(cMore)) {
                        throw new XmlPullParserException(new StringBuffer().append("in epilog non whitespace content is not allowed but got ").append(printable(cMore)).toString(), this, null);
                    }
                    if (!z2) {
                        z3 = true;
                    } else if (cMore == '\r') {
                        try {
                            if (!this.usePC) {
                                int i = this.pos - 1;
                                this.posEnd = i;
                                if (i > this.posStart) {
                                    joinPC();
                                } else {
                                    this.usePC = true;
                                    this.pcEnd = 0;
                                    this.pcStart = 0;
                                }
                            }
                            int i2 = this.pcEnd;
                            if (i2 >= this.f3790pc.length) {
                                ensurePC(i2);
                            }
                            char[] cArr = this.f3790pc;
                            int i3 = this.pcEnd;
                            this.pcEnd = i3 + 1;
                            cArr[i3] = '\n';
                            z3 = true;
                            z4 = true;
                        } catch (EOFException unused2) {
                            z = true;
                            this.reachedEnd = true;
                            if (!this.tokenize && z) {
                                this.posEnd = this.pos;
                                this.eventType = 7;
                                return 7;
                            }
                            this.eventType = 1;
                            return 1;
                        }
                    } else {
                        if (cMore == '\n') {
                            if (!z4 && this.usePC) {
                                int i4 = this.pcEnd;
                                if (i4 >= this.f3790pc.length) {
                                    ensurePC(i4);
                                }
                                char[] cArr2 = this.f3790pc;
                                int i5 = this.pcEnd;
                                this.pcEnd = i5 + 1;
                                cArr2[i5] = '\n';
                            }
                        } else if (this.usePC) {
                            int i6 = this.pcEnd;
                            if (i6 >= this.f3790pc.length) {
                                ensurePC(i6);
                            }
                            char[] cArr3 = this.f3790pc;
                            int i7 = this.pcEnd;
                            this.pcEnd = i7 + 1;
                            cArr3[i7] = cMore;
                        }
                        z3 = true;
                        z4 = false;
                    }
                    cMore = more();
                }
            }
        } catch (EOFException unused3) {
        }
    }

    public int parseEndTag() throws XmlPullParserException, IOException {
        char cMore;
        char cMore2 = more();
        if (!isNameStartChar(cMore2)) {
            throw new XmlPullParserException(new StringBuffer("expected name start and not ").append(printable(cMore2)).toString(), this, null);
        }
        int i = this.pos;
        this.posStart = i - 3;
        int i2 = this.bufAbsoluteStart + (i - 1);
        do {
            cMore = more();
        } while (isNameChar(cMore));
        int i3 = i2 - this.bufAbsoluteStart;
        int i4 = (this.pos - 1) - i3;
        char[][] cArr = this.elRawName;
        int i5 = this.depth;
        char[] cArr2 = cArr[i5];
        if (this.elRawNameEnd[i5] != i4) {
            throw new XmlPullParserException(new StringBuffer("end tag name </").append(new String(this.buf, i3, i4)).append("> must match start tag name <").append(new String(cArr2, 0, this.elRawNameEnd[this.depth])).append("> from line ").append(this.elRawNameLine[this.depth]).toString(), this, null);
        }
        int i6 = 0;
        while (i6 < i4) {
            int i7 = i3 + 1;
            if (this.buf[i3] != cArr2[i6]) {
                throw new XmlPullParserException(new StringBuffer("end tag name </").append(new String(this.buf, (i7 - i6) - 1, i4)).append("> must be the same as start tag <").append(new String(cArr2, 0, i4)).append("> from line ").append(this.elRawNameLine[this.depth]).toString(), this, null);
            }
            i6++;
            i3 = i7;
        }
        while (isS(cMore)) {
            cMore = more();
        }
        if (cMore != '>') {
            throw new XmlPullParserException(new StringBuffer("expected > to finsh end tag not ").append(printable(cMore)).append(" from line ").append(this.elRawNameLine[this.depth]).toString(), this, null);
        }
        this.posEnd = this.pos;
        this.pastEndTag = true;
        this.eventType = 3;
        return 3;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int parseStartTag() throws org.xmlpull.p065v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 680
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseStartTag():int");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected char parseAttribute() throws org.xmlpull.p065v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 885
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseAttribute():char");
    }

    protected char[] parseEntityRef() throws XmlPullParserException, IOException {
        char c;
        char cMore;
        int i;
        int i2;
        this.entityRefName = null;
        this.posStart = this.pos;
        if (more() == '#') {
            char cMore2 = more();
            if (cMore2 == 'x') {
                c = 0;
                while (true) {
                    cMore = more();
                    if (cMore >= '0' && cMore <= '9') {
                        i = c * 16;
                        i2 = cMore - '0';
                    } else if (cMore >= 'a' && cMore <= 'f') {
                        i = c * 16;
                        i2 = cMore - 'W';
                    } else {
                        if (cMore < 'A' || cMore > 'F') {
                            break;
                        }
                        i = c * 16;
                        i2 = cMore - '7';
                    }
                    c = (char) (i + i2);
                }
                if (cMore != ';') {
                    throw new XmlPullParserException(new StringBuffer("character reference (with hex value) may not contain ").append(printable(cMore)).toString(), this, null);
                }
            } else {
                char c2 = 0;
                while (cMore2 >= '0' && cMore2 <= '9') {
                    c2 = (char) ((c2 * '\n') + (cMore2 - '0'));
                    cMore2 = more();
                }
                if (cMore2 != ';') {
                    throw new XmlPullParserException(new StringBuffer("character reference (with decimal value) may not contain ").append(printable(cMore2)).toString(), this, null);
                }
                c = c2;
            }
            this.posEnd = this.pos - 1;
            char[] cArr = this.charRefOneCharBuf;
            cArr[0] = c;
            if (this.tokenize) {
                this.text = newString(cArr, 0, 1);
            }
            return this.charRefOneCharBuf;
        }
        while (more() != ';') {
        }
        int i3 = this.pos - 1;
        this.posEnd = i3;
        int i4 = this.posStart;
        int i5 = i3 - i4;
        if (i5 == 2) {
            char[] cArr2 = this.buf;
            if (cArr2[i4] == 'l' && cArr2[i4 + 1] == 't') {
                if (this.tokenize) {
                    this.text = "<";
                }
                char[] cArr3 = this.charRefOneCharBuf;
                cArr3[0] = Typography.less;
                return cArr3;
            }
        }
        if (i5 == 3) {
            char[] cArr4 = this.buf;
            if (cArr4[i4] == 'a' && cArr4[i4 + 1] == 'm' && cArr4[i4 + 2] == 'p') {
                if (this.tokenize) {
                    this.text = "&";
                }
                char[] cArr5 = this.charRefOneCharBuf;
                cArr5[0] = Typography.amp;
                return cArr5;
            }
        }
        if (i5 == 2) {
            char[] cArr6 = this.buf;
            if (cArr6[i4] == 'g' && cArr6[i4 + 1] == 't') {
                if (this.tokenize) {
                    this.text = ">";
                }
                char[] cArr7 = this.charRefOneCharBuf;
                cArr7[0] = Typography.greater;
                return cArr7;
            }
        }
        if (i5 == 4) {
            char[] cArr8 = this.buf;
            if (cArr8[i4] == 'a' && cArr8[i4 + 1] == 'p' && cArr8[i4 + 2] == 'o' && cArr8[i4 + 3] == 's') {
                if (this.tokenize) {
                    this.text = "'";
                }
                char[] cArr9 = this.charRefOneCharBuf;
                cArr9[0] = '\'';
                return cArr9;
            }
        }
        if (i5 == 4) {
            char[] cArr10 = this.buf;
            if (cArr10[i4] == 'q' && cArr10[i4 + 1] == 'u' && cArr10[i4 + 2] == 'o' && cArr10[i4 + 3] == 't') {
                if (this.tokenize) {
                    this.text = "\"";
                }
                char[] cArr11 = this.charRefOneCharBuf;
                cArr11[0] = Typography.quote;
                return cArr11;
            }
        }
        char[] cArrLookuEntityReplacement = lookuEntityReplacement(i5);
        if (cArrLookuEntityReplacement != null) {
            return cArrLookuEntityReplacement;
        }
        if (this.tokenize) {
            this.text = null;
        }
        return null;
    }

    protected char[] lookuEntityReplacement(int i) throws XmlPullParserException, IOException {
        if (!this.allStringsInterned) {
            char[] cArr = this.buf;
            int i2 = this.posStart;
            int iFastHash = fastHash(cArr, i2, this.posEnd - i2);
            for (int i3 = this.entityEnd - 1; i3 >= 0; i3--) {
                if (iFastHash == this.entityNameHash[i3]) {
                    char[] cArr2 = this.entityNameBuf[i3];
                    if (i == cArr2.length) {
                        for (int i4 = 0; i4 < i; i4++) {
                            if (this.buf[this.posStart + i4] != cArr2[i4]) {
                                break;
                            }
                        }
                        if (this.tokenize) {
                            this.text = this.entityReplacement[i3];
                        }
                        return this.entityReplacementBuf[i3];
                    }
                    continue;
                }
            }
            return null;
        }
        char[] cArr3 = this.buf;
        int i5 = this.posStart;
        this.entityRefName = newString(cArr3, i5, this.posEnd - i5);
        for (int i6 = this.entityEnd - 1; i6 >= 0; i6--) {
            if (this.entityRefName == this.entityName[i6]) {
                if (this.tokenize) {
                    this.text = this.entityReplacement[i6];
                }
                return this.entityReplacementBuf[i6];
            }
        }
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void parseComment() throws org.xmlpull.p065v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parseComment():void");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected boolean parsePI() throws org.xmlpull.p065v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParser.parsePI():boolean");
    }

    static {
        setNameStart(':');
        for (char c = 'A'; c <= 'Z'; c = (char) (c + 1)) {
            setNameStart(c);
        }
        setNameStart('_');
        for (char c2 = 'a'; c2 <= 'z'; c2 = (char) (c2 + 1)) {
            setNameStart(c2);
        }
        for (char c3 = 192; c3 <= 767; c3 = (char) (c3 + 1)) {
            setNameStart(c3);
        }
        for (char c4 = 880; c4 <= 893; c4 = (char) (c4 + 1)) {
            setNameStart(c4);
        }
        for (char c5 = 895; c5 < 1024; c5 = (char) (c5 + 1)) {
            setNameStart(c5);
        }
        setName('-');
        setName('.');
        for (char c6 = '0'; c6 <= '9'; c6 = (char) (c6 + 1)) {
            setName(c6);
        }
        setName(Typography.middleDot);
        for (char c7 = 768; c7 <= 879; c7 = (char) (c7 + 1)) {
            setName(c7);
        }
    }

    protected void parseXmlDecl(char c) throws XmlPullParserException, IOException {
        this.preventBufferCompaction = true;
        this.bufStart = 0;
        char cSkipS = skipS(requireInput(skipS(c), VERSION));
        if (cSkipS != '=') {
            throw new XmlPullParserException(new StringBuffer("expected equals sign (=) after version and not ").append(printable(cSkipS)).toString(), this, null);
        }
        char cSkipS2 = skipS(more());
        if (cSkipS2 != '\'' && cSkipS2 != '\"') {
            throw new XmlPullParserException(new StringBuffer("expected apostrophe (') or quotation mark (\") after version and not ").append(printable(cSkipS2)).toString(), this, null);
        }
        int i = this.pos;
        char cMore = more();
        while (cMore != cSkipS2) {
            if ((cMore < 'a' || cMore > 'z') && ((cMore < 'A' || cMore > 'Z') && ((cMore < '0' || cMore > '9') && cMore != '_' && cMore != '.' && cMore != ':' && cMore != '-'))) {
                throw new XmlPullParserException(new StringBuffer("<?xml version value expected to be in ([a-zA-Z0-9_.:] | '-') not ").append(printable(cMore)).toString(), this, null);
            }
            cMore = more();
        }
        parseXmlDeclWithVersion(i, this.pos - 1);
        this.preventBufferCompaction = false;
    }

    protected void parseXmlDeclWithVersion(int i, int i2) throws XmlPullParserException, IOException {
        char cRequireInput;
        int i3 = i2 - i;
        if (i3 == 3) {
            char[] cArr = this.buf;
            if (cArr[i] == '1' && cArr[i + 1] == '.') {
                char c = '0';
                if (cArr[i + 2] == '0') {
                    this.xmlDeclVersion = newString(cArr, i, i3);
                    char cSkipS = skipS(more());
                    if (cSkipS == 'e') {
                        char cSkipS2 = skipS(requireInput(more(), NCODING));
                        if (cSkipS2 != '=') {
                            throw new XmlPullParserException(new StringBuffer("expected equals sign (=) after encoding and not ").append(printable(cSkipS2)).toString(), this, null);
                        }
                        char cSkipS3 = skipS(more());
                        if (cSkipS3 != '\'' && cSkipS3 != '\"') {
                            throw new XmlPullParserException(new StringBuffer("expected apostrophe (') or quotation mark (\") after encoding and not ").append(printable(cSkipS3)).toString(), this, null);
                        }
                        int i4 = this.pos;
                        char cMore = more();
                        if ((cMore < 'a' || cMore > 'z') && (cMore < 'A' || cMore > 'Z')) {
                            throw new XmlPullParserException(new StringBuffer("<?xml encoding name expected to start with [A-Za-z] not ").append(printable(cMore)).toString(), this, null);
                        }
                        char cMore2 = more();
                        while (cMore2 != cSkipS3) {
                            if ((cMore2 < 'a' || cMore2 > 'z') && ((cMore2 < 'A' || cMore2 > 'Z') && ((cMore2 < c || cMore2 > '9') && cMore2 != '.' && cMore2 != '_' && cMore2 != '-'))) {
                                throw new XmlPullParserException(new StringBuffer("<?xml encoding value expected to be in ([A-Za-z0-9._] | '-') not ").append(printable(cMore2)).toString(), this, null);
                            }
                            cMore2 = more();
                            c = '0';
                        }
                        this.inputEncoding = newString(this.buf, i4, (this.pos - 1) - i4);
                        cSkipS = more();
                    }
                    char cSkipS4 = skipS(cSkipS);
                    if (cSkipS4 == 's') {
                        char cSkipS5 = skipS(requireInput(more(), TANDALONE));
                        if (cSkipS5 != '=') {
                            throw new XmlPullParserException(new StringBuffer("expected equals sign (=) after standalone and not ").append(printable(cSkipS5)).toString(), this, null);
                        }
                        char cSkipS6 = skipS(more());
                        if (cSkipS6 != '\'' && cSkipS6 != '\"') {
                            throw new XmlPullParserException(new StringBuffer("expected apostrophe (') or quotation mark (\") after encoding and not ").append(printable(cSkipS6)).toString(), this, null);
                        }
                        char cMore3 = more();
                        if (cMore3 == 'y') {
                            cRequireInput = requireInput(cMore3, YES);
                            this.xmlDeclStandalone = new Boolean(true);
                        } else if (cMore3 == 'n') {
                            cRequireInput = requireInput(cMore3, f3789NO);
                            this.xmlDeclStandalone = new Boolean(false);
                        } else {
                            throw new XmlPullParserException(new StringBuffer("expected 'yes' or 'no' after standalone and not ").append(printable(cMore3)).toString(), this, null);
                        }
                        if (cRequireInput != cSkipS6) {
                            throw new XmlPullParserException(new StringBuffer("expected ").append(cSkipS6).append(" after standalone value not ").append(printable(cRequireInput)).toString(), this, null);
                        }
                        cSkipS4 = more();
                    }
                    char cSkipS7 = skipS(cSkipS4);
                    if (cSkipS7 != '?') {
                        throw new XmlPullParserException(new StringBuffer("expected ?> as last part of <?xml not ").append(printable(cSkipS7)).toString(), this, null);
                    }
                    char cMore4 = more();
                    if (cMore4 != '>') {
                        throw new XmlPullParserException(new StringBuffer("expected ?> as last part of <?xml not ").append(printable(cMore4)).toString(), this, null);
                    }
                    return;
                }
            }
        }
        throw new XmlPullParserException(new StringBuffer("only 1.0 is supported as <?xml version not '").append(printable(new String(this.buf, i, i3))).append("'").toString(), this, null);
    }

    protected void parseDocdecl() throws XmlPullParserException, IOException {
        if (more() != 'O') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        if (more() != 'C') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        if (more() != 'T') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        if (more() != 'Y') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        if (more() != 'P') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        if (more() != 'E') {
            throw new XmlPullParserException("expected <!DOCTYPE", this, null);
        }
        this.posStart = this.pos;
        boolean z = this.tokenize && !this.roundtripSupported;
        int i = 0;
        boolean z2 = false;
        while (true) {
            char cMore = more();
            if (cMore == '[') {
                i++;
            }
            if (cMore == ']') {
                i--;
            }
            if (cMore == '>' && i == 0) {
                this.posEnd = this.pos - 1;
                return;
            }
            if (z) {
                if (cMore == '\r') {
                    if (!this.usePC) {
                        int i2 = this.pos - 1;
                        this.posEnd = i2;
                        if (i2 > this.posStart) {
                            joinPC();
                        } else {
                            this.usePC = true;
                            this.pcEnd = 0;
                            this.pcStart = 0;
                        }
                    }
                    int i3 = this.pcEnd;
                    if (i3 >= this.f3790pc.length) {
                        ensurePC(i3);
                    }
                    char[] cArr = this.f3790pc;
                    int i4 = this.pcEnd;
                    this.pcEnd = i4 + 1;
                    cArr[i4] = '\n';
                    z2 = true;
                } else {
                    if (cMore == '\n') {
                        if (!z2 && this.usePC) {
                            int i5 = this.pcEnd;
                            if (i5 >= this.f3790pc.length) {
                                ensurePC(i5);
                            }
                            char[] cArr2 = this.f3790pc;
                            int i6 = this.pcEnd;
                            this.pcEnd = i6 + 1;
                            cArr2[i6] = '\n';
                        }
                    } else if (this.usePC) {
                        int i7 = this.pcEnd;
                        if (i7 >= this.f3790pc.length) {
                            ensurePC(i7);
                        }
                        char[] cArr3 = this.f3790pc;
                        int i8 = this.pcEnd;
                        this.pcEnd = i8 + 1;
                        cArr3[i8] = cMore;
                    }
                    z2 = false;
                }
            }
        }
    }

    protected void parseCDSect(boolean z) throws XmlPullParserException, IOException {
        if (more() != 'C') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        if (more() != 'D') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        if (more() != 'A') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        if (more() != 'T') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        if (more() != 'A') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        if (more() != '[') {
            throw new XmlPullParserException("expected <[CDATA[ for comment start", this, null);
        }
        int i = this.pos + this.bufAbsoluteStart;
        int i2 = this.lineNumber;
        int i3 = this.columnNumber;
        boolean z2 = (this.tokenize && this.roundtripSupported) ? false : true;
        if (z2 && z) {
            try {
                if (!this.usePC) {
                    if (this.posEnd > this.posStart) {
                        joinPC();
                    } else {
                        this.usePC = true;
                        this.pcEnd = 0;
                        this.pcStart = 0;
                    }
                }
            } catch (EOFException e) {
                throw new XmlPullParserException(new StringBuffer("CDATA section started on line ").append(i2).append(" and column ").append(i3).append(" was not closed").toString(), this, e);
            }
        }
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while (true) {
            char cMore = more();
            if (cMore == ']') {
                if (z3) {
                    z4 = true;
                } else {
                    z3 = true;
                }
            } else if (cMore != '>') {
                z3 = false;
            } else {
                if (z4) {
                    break;
                }
                z3 = false;
                z4 = false;
            }
            if (z2) {
                if (cMore == '\r') {
                    int i4 = i - this.bufAbsoluteStart;
                    this.posStart = i4;
                    int i5 = this.pos;
                    this.posEnd = i5;
                    if (!this.usePC) {
                        if (i5 > i4) {
                            joinPC();
                        } else {
                            this.usePC = true;
                            this.pcEnd = 0;
                            this.pcStart = 0;
                        }
                    }
                    int i6 = this.pcEnd;
                    if (i6 >= this.f3790pc.length) {
                        ensurePC(i6);
                    }
                    char[] cArr = this.f3790pc;
                    int i7 = this.pcEnd;
                    this.pcEnd = i7 + 1;
                    cArr[i7] = '\n';
                    z5 = true;
                } else {
                    if (cMore == '\n') {
                        if (!z5 && this.usePC) {
                            int i8 = this.pcEnd;
                            if (i8 >= this.f3790pc.length) {
                                ensurePC(i8);
                            }
                            char[] cArr2 = this.f3790pc;
                            int i9 = this.pcEnd;
                            this.pcEnd = i9 + 1;
                            cArr2[i9] = '\n';
                        }
                    } else if (this.usePC) {
                        int i10 = this.pcEnd;
                        if (i10 >= this.f3790pc.length) {
                            ensurePC(i10);
                        }
                        char[] cArr3 = this.f3790pc;
                        int i11 = this.pcEnd;
                        this.pcEnd = i11 + 1;
                        cArr3[i11] = cMore;
                    }
                    z5 = false;
                }
            }
        }
        if (z2 && this.usePC) {
            this.pcEnd -= 2;
        }
        this.posStart = i - this.bufAbsoluteStart;
        this.posEnd = this.pos - 3;
    }

    protected void fillBuf() throws XmlPullParserException, IOException {
        boolean z;
        if (this.reader == null) {
            throw new XmlPullParserException("reader must be set before parsing is started");
        }
        int i = this.bufEnd;
        int i2 = this.bufSoftLimit;
        if (i > i2) {
            int i3 = this.bufStart;
            boolean z2 = i3 > i2;
            if (this.preventBufferCompaction) {
                z = true;
                z2 = false;
            } else if (z2) {
                z = false;
            } else if (i3 < this.buf.length / 2) {
                z = true;
            } else {
                z2 = true;
                z = false;
            }
            if (z2) {
                char[] cArr = this.buf;
                System.arraycopy(cArr, i3, cArr, 0, i - i3);
            } else if (z) {
                char[] cArr2 = this.buf;
                char[] cArr3 = new char[cArr2.length * 2];
                System.arraycopy(cArr2, i3, cArr3, 0, i - i3);
                this.buf = cArr3;
                int i4 = this.bufLoadFactor;
                if (i4 > 0) {
                    this.bufSoftLimit = (i4 * cArr3.length) / 100;
                }
            } else {
                throw new XmlPullParserException("internal error in fillBuffer()");
            }
            int i5 = this.bufEnd;
            int i6 = this.bufStart;
            this.bufEnd = i5 - i6;
            this.pos -= i6;
            this.posStart -= i6;
            this.posEnd -= i6;
            this.bufAbsoluteStart += i6;
            this.bufStart = 0;
        }
        char[] cArr4 = this.buf;
        int length = cArr4.length;
        int i7 = this.bufEnd;
        int i8 = this.reader.read(cArr4, i7, length - i7 <= 8192 ? cArr4.length - i7 : 8192);
        if (i8 > 0) {
            this.bufEnd += i8;
            return;
        }
        if (i8 == -1) {
            if (this.bufAbsoluteStart == 0 && this.pos == 0) {
                throw new EOFException("input contained no data");
            }
            StringBuffer stringBuffer = new StringBuffer();
            if (this.depth > 0) {
                stringBuffer.append(" - expected end tag");
                if (this.depth > 1) {
                    stringBuffer.append("s");
                }
                stringBuffer.append(" ");
                for (int i9 = this.depth; i9 > 0; i9--) {
                    stringBuffer.append("</").append(new String(this.elRawName[i9], 0, this.elRawNameEnd[i9])).append(Typography.greater);
                }
                stringBuffer.append(" to close");
                for (int i10 = this.depth; i10 > 0; i10--) {
                    if (i10 != this.depth) {
                        stringBuffer.append(" and");
                    }
                    stringBuffer.append(new StringBuffer(" start tag <").append(new String(this.elRawName[i10], 0, this.elRawNameEnd[i10])).append(">").toString());
                    stringBuffer.append(new StringBuffer(" from line ").append(this.elRawNameLine[i10]).toString());
                }
                stringBuffer.append(", parser stopped on");
            }
            throw new EOFException(new StringBuffer("no more data available").append(stringBuffer.toString()).append(getPositionDescription()).toString());
        }
        throw new IOException(new StringBuffer("error reading input, returned ").append(i8).toString());
    }

    protected char more() throws XmlPullParserException, IOException {
        if (this.pos >= this.bufEnd) {
            fillBuf();
        }
        char[] cArr = this.buf;
        int i = this.pos;
        this.pos = i + 1;
        char c = cArr[i];
        if (c == '\n') {
            this.lineNumber++;
            this.columnNumber = 1;
        } else {
            this.columnNumber++;
        }
        return c;
    }

    protected void ensurePC(int i) {
        char[] cArr = new char[i > 8192 ? i * 2 : 16384];
        System.arraycopy(this.f3790pc, 0, cArr, 0, this.pcEnd);
        this.f3790pc = cArr;
    }

    protected void joinPC() {
        int i = this.posEnd - this.posStart;
        int i2 = this.pcEnd + i + 1;
        if (i2 >= this.f3790pc.length) {
            ensurePC(i2);
        }
        System.arraycopy(this.buf, this.posStart, this.f3790pc, this.pcEnd, i);
        this.pcEnd += i;
        this.usePC = true;
    }

    protected char requireInput(char c, char[] cArr) throws XmlPullParserException, IOException {
        for (int i = 0; i < cArr.length; i++) {
            if (c != cArr[i]) {
                throw new XmlPullParserException(new StringBuffer("expected ").append(printable(cArr[i])).append(" in ").append(new String(cArr)).append(" and not ").append(printable(c)).toString(), this, null);
            }
            c = more();
        }
        return c;
    }

    protected char requireNextS() throws XmlPullParserException, IOException {
        char cMore = more();
        if (!isS(cMore)) {
            throw new XmlPullParserException(new StringBuffer("white space is required and not ").append(printable(cMore)).toString(), this, null);
        }
        return skipS(cMore);
    }

    protected char skipS(char c) throws XmlPullParserException, IOException {
        while (isS(c)) {
            c = more();
        }
        return c;
    }

    private static final void setName(char c) {
        lookupNameChar[c] = true;
    }

    private static final void setNameStart(char c) {
        lookupNameStartChar[c] = true;
        setName(c);
    }

    protected boolean isNameStartChar(char c) {
        return (c < 1024 && lookupNameStartChar[c]) || (c >= 1024 && c <= 8231) || ((c >= 8234 && c <= 8591) || (c >= 10240 && c <= 65519));
    }

    protected boolean isNameChar(char c) {
        return (c < 1024 && lookupNameChar[c]) || (c >= 1024 && c <= 8231) || ((c >= 8234 && c <= 8591) || (c >= 10240 && c <= 65519));
    }

    protected String printable(char c) {
        if (c == '\n') {
            return "\\n";
        }
        if (c == '\r') {
            return "\\r";
        }
        if (c == '\t') {
            return "\\t";
        }
        if (c == '\'') {
            return "\\'";
        }
        if (c > 127 || c < ' ') {
            return new StringBuffer("\\u").append(Integer.toHexString(c)).toString();
        }
        return new StringBuffer("").append(c).toString();
    }

    protected String printable(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length + 10);
        for (int i = 0; i < length; i++) {
            stringBuffer.append(printable(str.charAt(i)));
        }
        return stringBuffer.toString();
    }
}
