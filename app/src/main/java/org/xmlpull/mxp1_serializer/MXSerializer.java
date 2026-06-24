package org.xmlpull.mxp1_serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.xml.XMLConstants;
import org.apache.log4j.spi.Configurator;
import org.xmlpull.p065v1.XmlSerializer;

public class MXSerializer implements XmlSerializer {
    private static final boolean TRACE_SIZING = false;
    protected static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    protected static final String XML_URI = "http://www.w3.org/XML/1998/namespace";
    protected static final String[] precomputedPrefixes = new String[32];
    protected boolean attributeUseApostrophe;
    protected int autoDeclaredPrefixes;
    protected boolean doIndent;
    protected String[] elName;
    protected String[] elNamespace;
    protected int[] elNamespaceCount;
    protected boolean finished;
    protected char[] indentationBuf;
    protected int indentationJump;
    protected int maxIndentLevel;
    protected boolean namesInterned;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;
    protected int offsetNewLine;
    protected Writer out;
    protected boolean pastRoot;
    protected boolean seenTag;
    protected boolean setPrefixCalled;
    protected boolean startTagIncomplete;
    protected boolean writeIndentation;
    protected boolean writeLineSepartor;
    protected final String FEATURE_SERIALIZER_ATTVALUE_USE_APOSTROPHE = "http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe";
    protected final String FEATURE_NAMES_INTERNED = "http://xmlpull.org/v1/doc/features.html#names-interned";
    protected final String PROPERTY_SERIALIZER_INDENTATION = "http://xmlpull.org/v1/doc/properties.html#serializer-indentation";
    protected final String PROPERTY_SERIALIZER_LINE_SEPARATOR = "http://xmlpull.org/v1/doc/properties.html#serializer-line-separator";
    protected String indentationString = null;
    protected String lineSeparator = "\n";
    protected int depth = 0;
    protected int namespaceEnd = 0;
    private boolean checkNamesInterned = false;

    public MXSerializer() {
        String[] strArr = new String[2];
        this.elNamespace = strArr;
        this.elName = new String[strArr.length];
        this.elNamespaceCount = new int[strArr.length];
        String[] strArr2 = new String[8];
        this.namespacePrefix = strArr2;
        this.namespaceUri = new String[strArr2.length];
    }

    static {
        int i = 0;
        while (true) {
            String[] strArr = precomputedPrefixes;
            if (i >= strArr.length) {
                return;
            }
            strArr[i] = new StringBuffer("n").append(i).toString().intern();
            i++;
        }
    }

    private void checkInterning(String str) {
        if (this.namesInterned && str != str.intern()) {
            throw new IllegalArgumentException("all names passed as arguments must be internedwhen NAMES INTERNED feature is enabled");
        }
    }

    protected void reset() {
        this.out = null;
        this.autoDeclaredPrefixes = 0;
        this.depth = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.elNamespaceCount;
            if (i < iArr.length) {
                this.elName[i] = null;
                this.elNamespace[i] = null;
                iArr[i] = 2;
                i++;
            } else {
                String[] strArr = this.namespacePrefix;
                strArr[0] = XMLConstants.XMLNS_ATTRIBUTE;
                String[] strArr2 = this.namespaceUri;
                strArr2[0] = "http://www.w3.org/2000/xmlns/";
                int i2 = 0 + 1;
                strArr[i2] = XMLConstants.XML_NS_PREFIX;
                strArr2[i2] = "http://www.w3.org/XML/1998/namespace";
                this.namespaceEnd = i2 + 1;
                this.finished = false;
                this.pastRoot = false;
                this.setPrefixCalled = false;
                this.startTagIncomplete = false;
                this.seenTag = false;
                return;
            }
        }
    }

    protected void ensureElementsCapacity() {
        String[] strArr = this.elName;
        int length = strArr.length;
        int i = this.depth;
        int i2 = (i >= 7 ? i * 2 : 8) + 2;
        boolean z = length > 0;
        String[] strArr2 = new String[i2];
        if (z) {
            System.arraycopy(strArr, 0, strArr2, 0, length);
        }
        this.elName = strArr2;
        String[] strArr3 = new String[i2];
        if (z) {
            System.arraycopy(this.elNamespace, 0, strArr3, 0, length);
        }
        this.elNamespace = strArr3;
        int[] iArr = new int[i2];
        if (z) {
            System.arraycopy(this.elNamespaceCount, 0, iArr, 0, length);
        } else {
            iArr[0] = 0;
        }
        this.elNamespaceCount = iArr;
    }

    protected void ensureNamespacesCapacity() {
        int i = this.namespaceEnd;
        int i2 = i > 7 ? i * 2 : 8;
        String[] strArr = new String[i2];
        String[] strArr2 = new String[i2];
        String[] strArr3 = this.namespacePrefix;
        if (strArr3 != null) {
            System.arraycopy(strArr3, 0, strArr, 0, i);
            System.arraycopy(this.namespaceUri, 0, strArr2, 0, this.namespaceEnd);
        }
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr2;
    }

    @Override
    public void setFeature(String str, boolean z) throws IllegalStateException, IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("feature name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(str)) {
            this.namesInterned = z;
        } else {
            if ("http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(str)) {
                this.attributeUseApostrophe = z;
                return;
            }
            throw new IllegalStateException(new StringBuffer("unsupported feature ").append(str).toString());
        }
    }

    @Override
    public boolean getFeature(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("feature name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/features.html#names-interned".equals(str)) {
            return this.namesInterned;
        }
        if ("http://xmlpull.org/v1/doc/features.html#serializer-attvalue-use-apostrophe".equals(str)) {
            return this.attributeUseApostrophe;
        }
        return false;
    }

    protected void rebuildIndentationBuf() {
        int i;
        int i2;
        if (this.doIndent) {
            this.offsetNewLine = 0;
            if (this.writeLineSepartor) {
                int length = this.lineSeparator.length();
                this.offsetNewLine = length;
                i = length + 0;
            } else {
                i = 0;
            }
            this.maxIndentLevel = 0;
            if (this.writeIndentation) {
                int length2 = this.indentationString.length();
                this.indentationJump = length2;
                int i3 = 65 / length2;
                this.maxIndentLevel = i3;
                i += i3 * length2;
            }
            char[] cArr = this.indentationBuf;
            if (cArr == null || cArr.length < i) {
                this.indentationBuf = new char[i + 8];
            }
            if (this.writeLineSepartor) {
                int i4 = 0;
                i2 = 0;
                while (i4 < this.lineSeparator.length()) {
                    this.indentationBuf[i2] = this.lineSeparator.charAt(i4);
                    i4++;
                    i2++;
                }
            } else {
                i2 = 0;
            }
            if (this.writeIndentation) {
                for (int i5 = 0; i5 < this.maxIndentLevel; i5++) {
                    int i6 = 0;
                    while (i6 < this.indentationString.length()) {
                        this.indentationBuf[i2] = this.indentationString.charAt(i6);
                        i6++;
                        i2++;
                    }
                }
            }
        }
    }

    protected void writeIndent() throws IOException {
        int i = this.writeLineSepartor ? 0 : this.offsetNewLine;
        int i2 = this.depth;
        int i3 = this.maxIndentLevel;
        if (i2 > i3) {
            i2 = i3;
        }
        this.out.write(this.indentationBuf, i, (i2 * this.indentationJump) + this.offsetNewLine);
    }

    @Override
    public void setProperty(String str, Object obj) throws IllegalStateException, IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("property name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(str)) {
            this.indentationString = (String) obj;
        } else if ("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(str)) {
            this.lineSeparator = (String) obj;
        } else {
            throw new IllegalStateException(new StringBuffer("unsupported property ").append(str).toString());
        }
        String str2 = this.lineSeparator;
        boolean z = true;
        this.writeLineSepartor = str2 != null && str2.length() > 0;
        String str3 = this.indentationString;
        boolean z2 = str3 != null && str3.length() > 0;
        this.writeIndentation = z2;
        if (this.indentationString == null || (!this.writeLineSepartor && !z2)) {
            z = false;
        }
        this.doIndent = z;
        rebuildIndentationBuf();
        this.seenTag = false;
    }

    @Override
    public Object getProperty(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("property name can not be null");
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-indentation".equals(str)) {
            return this.indentationString;
        }
        if ("http://xmlpull.org/v1/doc/properties.html#serializer-line-separator".equals(str)) {
            return this.lineSeparator;
        }
        return null;
    }

    @Override
    public void setOutput(Writer writer) {
        reset();
        this.out = writer;
    }

    @Override
    public void setOutput(OutputStream outputStream, String str) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("output stream can not be null");
        }
        reset();
        if (str != null) {
            this.out = new OutputStreamWriter(outputStream, str);
        } else {
            this.out = new OutputStreamWriter(outputStream);
        }
    }

    @Override
    public void startDocument(String str, Boolean bool) throws IOException {
        this.out.write("<?xml version=\"1.0\"");
        if (str != null) {
            this.out.write(" encoding='");
            this.out.write(str);
            this.out.write(39);
        }
        if (bool != null) {
            if (bool.booleanValue()) {
                this.out.write(" standalone='yes'");
            } else {
                this.out.write(" standalone='no'");
            }
        }
        this.out.write("?>");
    }

    @Override
    public void endDocument() throws IOException {
        while (true) {
            int i = this.depth;
            if (i > 0) {
                endTag(this.elNamespace[i], this.elName[i]);
            } else {
                this.startTagIncomplete = true;
                this.pastRoot = true;
                this.finished = true;
                this.out.flush();
                return;
            }
        }
    }

    @Override
    public void setPrefix(String str, String str2) throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        if (str == null) {
            str = "";
        }
        if (!this.namesInterned) {
            str = str.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str);
        } else if (str == null) {
            throw new IllegalArgumentException("prefix must be not null");
        }
        for (int i = this.elNamespaceCount[this.depth]; i < this.namespaceEnd; i++) {
            if (str == this.namespacePrefix[i]) {
                throw new IllegalStateException(new StringBuffer("duplicated prefix ").append(printable(str)).toString());
            }
        }
        if (!this.namesInterned) {
            str2 = str2.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str2);
        } else if (str2 == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr = this.namespacePrefix;
        int i2 = this.namespaceEnd;
        strArr[i2] = str;
        this.namespaceUri[i2] = str2;
        this.namespaceEnd = i2 + 1;
        this.setPrefixCalled = true;
    }

    protected String lookupOrDeclarePrefix(String str) {
        return getPrefix(str, true);
    }

    @Override
    public String getPrefix(String str, boolean z) {
        if (!this.namesInterned) {
            str = str.intern();
        } else if (this.checkNamesInterned) {
            checkInterning(str);
        } else if (str == null) {
            throw new IllegalArgumentException("namespace must be not null");
        }
        for (int i = this.namespaceEnd - 1; i >= 0; i--) {
            if (str == this.namespaceUri[i]) {
                String str2 = this.namespacePrefix[i];
                for (int i2 = this.namespaceEnd - 1; i2 > i; i2--) {
                    String str3 = this.namespacePrefix[i2];
                }
                return str2;
            }
        }
        if (z) {
            return generatePrefix(str);
        }
        return null;
    }

    private String generatePrefix(String str) {
        int i = this.autoDeclaredPrefixes + 1;
        this.autoDeclaredPrefixes = i;
        String[] strArr = precomputedPrefixes;
        String strIntern = i < strArr.length ? strArr[i] : new StringBuffer("n").append(this.autoDeclaredPrefixes).toString().intern();
        for (int i2 = this.namespaceEnd - 1; i2 >= 0; i2--) {
            String str2 = this.namespacePrefix[i2];
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr2 = this.namespacePrefix;
        int i3 = this.namespaceEnd;
        strArr2[i3] = strIntern;
        this.namespaceUri[i3] = str;
        this.namespaceEnd = i3 + 1;
        return strIntern;
    }

    @Override
    public int getDepth() {
        return this.depth;
    }

    @Override
    public String getNamespace() {
        return this.elNamespace[this.depth];
    }

    @Override
    public String getName() {
        return this.elName[this.depth];
    }

    @Override
    public XmlSerializer startTag(String str, String str2) throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        if (this.doIndent && this.depth > 0 && this.seenTag) {
            writeIndent();
        }
        this.seenTag = true;
        this.setPrefixCalled = false;
        this.startTagIncomplete = true;
        int i = this.depth + 1;
        this.depth = i;
        if (i + 1 >= this.elName.length) {
            ensureElementsCapacity();
        }
        if (this.checkNamesInterned && this.namesInterned) {
            checkInterning(str);
        }
        this.elNamespace[this.depth] = (this.namesInterned || str == null) ? str : str.intern();
        if (this.checkNamesInterned && this.namesInterned) {
            checkInterning(str2);
        }
        this.elName[this.depth] = (this.namesInterned || str2 == null) ? str2 : str2.intern();
        this.out.write(60);
        if (str != null) {
            if (str.length() > 0) {
                String strLookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
                if (strLookupOrDeclarePrefix.length() > 0) {
                    this.out.write(strLookupOrDeclarePrefix);
                    this.out.write(58);
                }
            } else {
                int i2 = this.namespaceEnd - 1;
                while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    if (this.namespacePrefix[i2] == "") {
                        String str3 = this.namespaceUri[i2];
                        if (str3 == null) {
                            setPrefix("", "");
                        } else if (str3.length() > 0) {
                            throw new IllegalStateException(new StringBuffer("start tag can not be written in empty default namespace as default namespace is currently bound to '").append(str3).append("'").toString());
                        }
                    } else {
                        i2--;
                    }
                }
            }
        }
        this.out.write(str2);
        return this;
    }

    @Override
    public XmlSerializer attribute(String str, String str2, String str3) throws IOException {
        if (!this.startTagIncomplete) {
            throw new IllegalArgumentException("startTag() must be called before attribute()");
        }
        this.out.write(32);
        if (str != null && str.length() > 0) {
            if (!this.namesInterned) {
                str = str.intern();
            } else if (this.checkNamesInterned) {
                checkInterning(str);
            }
            String strLookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
            if (strLookupOrDeclarePrefix.length() == 0) {
                strLookupOrDeclarePrefix = generatePrefix(str);
            }
            this.out.write(strLookupOrDeclarePrefix);
            this.out.write(58);
        }
        this.out.write(str2);
        this.out.write(61);
        this.out.write(this.attributeUseApostrophe ? 39 : 34);
        writeAttributeValue(str3, this.out);
        this.out.write(this.attributeUseApostrophe ? 39 : 34);
        return this;
    }

    protected void closeStartTag() throws IOException {
        if (this.finished) {
            throw new IllegalArgumentException("trying to write past already finished output");
        }
        if (this.setPrefixCalled) {
            throw new IllegalArgumentException("startTag() must be called immediately after setPrefix()");
        }
        if (!this.startTagIncomplete) {
            throw new IllegalArgumentException("trying to close start tag that is not opened");
        }
        writeNamespaceDeclarations();
        this.out.write(62);
        this.elNamespaceCount[this.depth] = this.namespaceEnd;
        this.startTagIncomplete = false;
    }

    private void writeNamespaceDeclarations() throws IOException {
        for (int i = this.elNamespaceCount[this.depth - 1]; i < this.namespaceEnd; i++) {
            if (this.namespacePrefix[i] != "") {
                this.out.write(" xmlns:");
                this.out.write(this.namespacePrefix[i]);
                this.out.write(61);
            } else {
                this.out.write(" xmlns=");
            }
            int i2 = 39;
            this.out.write(this.attributeUseApostrophe ? 39 : 34);
            writeAttributeValue(this.namespaceUri[i], this.out);
            Writer writer = this.out;
            if (!this.attributeUseApostrophe) {
                i2 = 34;
            }
            writer.write(i2);
        }
    }

    @Override
    public XmlSerializer endTag(String str, String str2) throws IOException {
        if (str != null) {
            if (!this.namesInterned) {
                str = str.intern();
            } else if (this.checkNamesInterned) {
                checkInterning(str);
            }
        }
        if (str != this.elNamespace[this.depth]) {
            throw new IllegalArgumentException(new StringBuffer("expected namespace ").append(printable(this.elNamespace[this.depth])).append(" and not ").append(printable(str)).toString());
        }
        if (str2 == null) {
            throw new IllegalArgumentException("end tag name can not be null");
        }
        if (this.checkNamesInterned && this.namesInterned) {
            checkInterning(str2);
        }
        if ((!this.namesInterned && !str2.equals(this.elName[this.depth])) || (this.namesInterned && str2 != this.elName[this.depth])) {
            throw new IllegalArgumentException(new StringBuffer("expected element name ").append(printable(this.elName[this.depth])).append(" and not ").append(printable(str2)).toString());
        }
        if (this.startTagIncomplete) {
            writeNamespaceDeclarations();
            this.out.write(" />");
            this.depth--;
        } else {
            this.depth--;
            if (this.doIndent && this.seenTag) {
                writeIndent();
            }
            this.out.write("</");
            if (str != null && str.length() > 0) {
                String strLookupOrDeclarePrefix = lookupOrDeclarePrefix(str);
                if (strLookupOrDeclarePrefix.length() > 0) {
                    this.out.write(strLookupOrDeclarePrefix);
                    this.out.write(58);
                }
            }
            this.out.write(str2);
            this.out.write(62);
        }
        this.namespaceEnd = this.elNamespaceCount[this.depth];
        this.startTagIncomplete = false;
        this.seenTag = true;
        return this;
    }

    @Override
    public XmlSerializer text(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        writeElementContent(str, this.out);
        return this;
    }

    @Override
    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        writeElementContent(cArr, i, i2, this.out);
        return this;
    }

    @Override
    public void cdsect(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<![CDATA[");
        this.out.write(str);
        this.out.write("]]>");
    }

    @Override
    public void entityRef(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(38);
        this.out.write(str);
        this.out.write(59);
    }

    @Override
    public void processingInstruction(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<?");
        this.out.write(str);
        this.out.write("?>");
    }

    @Override
    public void comment(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!--");
        this.out.write(str);
        this.out.write("-->");
    }

    @Override
    public void docdecl(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write("<!DOCTYPE");
        this.out.write(str);
        this.out.write(">");
    }

    @Override
    public void ignorableWhitespace(String str) throws IOException {
        if (this.startTagIncomplete || this.setPrefixCalled) {
            closeStartTag();
        }
        if (this.doIndent && this.seenTag) {
            this.seenTag = false;
        }
        this.out.write(str);
    }

    @Override
    public void flush() throws IOException {
        if (this.startTagIncomplete) {
            closeStartTag();
        }
        this.out.flush();
    }

    protected void writeAttributeValue(String str, Writer writer) throws IOException {
        boolean z = this.attributeUseApostrophe;
        int i = z ? 39 : 34;
        String str2 = z ? "&apos;" : "&quot;";
        int iIndexOf = str.indexOf(60);
        int iIndexOf2 = str.indexOf(38);
        int iIndexOf3 = str.indexOf(i);
        int i2 = 0;
        while (true) {
            if (iIndexOf == -1 && iIndexOf2 == -1 && iIndexOf3 == -1) {
                if (i2 > 0) {
                    writer.write(str.substring(i2));
                    return;
                } else {
                    writer.write(str);
                    return;
                }
            }
            if (iIndexOf3 != -1 && ((iIndexOf2 == -1 || (iIndexOf2 != -1 && iIndexOf3 < iIndexOf2)) && (iIndexOf == -1 || (iIndexOf != -1 && iIndexOf3 < iIndexOf)))) {
                if (i2 < iIndexOf3) {
                    writer.write(str.substring(i2, iIndexOf3));
                }
                writer.write(str2);
                i2 = iIndexOf3 + 1;
                iIndexOf3 = str.indexOf(i, i2);
            } else if (iIndexOf2 != -1 && ((iIndexOf3 == -1 || (iIndexOf3 != -1 && iIndexOf2 < iIndexOf3)) && (iIndexOf == -1 || (iIndexOf != -1 && iIndexOf2 < iIndexOf)))) {
                if (i2 < iIndexOf2) {
                    writer.write(str.substring(i2, iIndexOf2));
                }
                writer.write("&amp;");
                i2 = iIndexOf2 + 1;
                iIndexOf2 = str.indexOf(38, i2);
            } else {
                if (iIndexOf == -1 || ((iIndexOf3 != -1 && (iIndexOf3 == -1 || iIndexOf >= iIndexOf3)) || (iIndexOf2 != -1 && (iIndexOf2 == -1 || iIndexOf >= iIndexOf2)))) {
                    break;
                }
                if (i2 < iIndexOf) {
                    writer.write(str.substring(i2, iIndexOf));
                }
                writer.write("&lt;");
                i2 = iIndexOf + 1;
                iIndexOf = str.indexOf(60, i2);
            }
        }
        throw new IllegalStateException(new StringBuffer("wrong state #1 posLt=").append(iIndexOf).append(" posAmp=").append(iIndexOf2).append(" posQuot=").append(iIndexOf3).append(" for ").append(str).toString());
    }

    protected void writeElementContent(String str, Writer writer) throws IOException {
        int iIndexOf = str.indexOf(60);
        int iIndexOf2 = str.indexOf(38);
        int i = 0;
        while (true) {
            if (iIndexOf != -1 || iIndexOf2 != -1) {
                if (iIndexOf != -1 && (iIndexOf == -1 || iIndexOf2 == -1 || iIndexOf2 >= iIndexOf)) {
                    if (iIndexOf2 != -1 && (iIndexOf == -1 || iIndexOf2 == -1 || iIndexOf >= iIndexOf2)) {
                        break;
                    }
                    if (i < iIndexOf) {
                        writer.write(str.substring(i, iIndexOf));
                    }
                    writer.write("&lt;");
                    i = iIndexOf + 1;
                    iIndexOf = str.indexOf(60, i);
                } else {
                    if (i < iIndexOf2) {
                        writer.write(str.substring(i, iIndexOf2));
                    }
                    writer.write("&amp;");
                    i = iIndexOf2 + 1;
                    iIndexOf2 = str.indexOf(38, i);
                }
            } else {
                writer.write(str.substring(i));
                return;
            }
        }
        throw new IllegalStateException(new StringBuffer("wrong state posLt=").append(iIndexOf).append(" posAmp=").append(iIndexOf2).append(" for ").append(str).toString());
    }

    protected void writeElementContent(char[] cArr, int i, int i2, Writer writer) throws IOException {
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (c == '&') {
                if (i > i4) {
                    writer.write(cArr, i4, i - i4);
                }
                writer.write("&amp;");
            } else if (c != '<') {
                i++;
            } else {
                if (i > i4) {
                    writer.write(cArr, i4, i - i4);
                }
                writer.write("&lt;");
            }
            i4 = i + 1;
            i++;
        }
        if (i3 > i4) {
            writer.write(cArr, i4, i3 - i4);
        }
    }

    protected static final String printable(String str) {
        if (str == null) {
            return Configurator.NULL;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 16);
        stringBuffer.append("'");
        for (int i = 0; i < str.length(); i++) {
            addPrintable(stringBuffer, str.charAt(i));
        }
        stringBuffer.append("'");
        return stringBuffer.toString();
    }

    protected static final String printable(char c) {
        StringBuffer stringBuffer = new StringBuffer();
        addPrintable(stringBuffer, c);
        return stringBuffer.toString();
    }

    private static void addPrintable(StringBuffer stringBuffer, char c) {
        if (c == '\f') {
            stringBuffer.append("\\f");
            return;
        }
        if (c == '\r') {
            stringBuffer.append("\\r");
            return;
        }
        if (c == '\"') {
            stringBuffer.append("\\\"");
            return;
        }
        if (c == '\'') {
            stringBuffer.append("\\'");
            return;
        }
        if (c != '\\') {
            switch (c) {
                case '\b':
                    stringBuffer.append("\\b");
                    break;
                case '\t':
                    stringBuffer.append("\\t");
                    break;
                case '\n':
                    stringBuffer.append("\\n");
                    break;
                default:
                    if (c < ' ' || c > '~') {
                        String string = new StringBuffer("0000").append(Integer.toString(c, 16)).toString();
                        stringBuffer.append(new StringBuffer("\\u").append(string.substring(string.length() - 4, string.length())).toString());
                    } else {
                        stringBuffer.append(c);
                    }
                    break;
            }
            return;
        }
        stringBuffer.append("\\\\");
    }
}
