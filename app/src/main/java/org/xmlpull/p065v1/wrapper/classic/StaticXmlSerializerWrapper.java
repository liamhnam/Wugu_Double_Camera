package org.xmlpull.p065v1.wrapper.classic;

import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlSerializer;
import org.xmlpull.p065v1.wrapper.XmlPullParserWrapper;
import org.xmlpull.p065v1.wrapper.XmlPullWrapperFactory;
import org.xmlpull.p065v1.wrapper.XmlSerializerWrapper;

public class StaticXmlSerializerWrapper extends XmlSerializerDelegate implements XmlSerializerWrapper {
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/features.html#xmldecl-standalone";
    private static final boolean TRACE_SIZING = false;
    protected String currentNs;
    protected XmlPullParserWrapper fragmentParser;
    protected int[] namespaceDepth;
    protected int namespaceEnd;
    protected String[] namespacePrefix;
    protected String[] namespaceUri;

    protected XmlPullWrapperFactory f3793wf;

    public StaticXmlSerializerWrapper(XmlSerializer xmlSerializer, XmlPullWrapperFactory xmlPullWrapperFactory) {
        super(xmlSerializer);
        this.namespaceEnd = 0;
        String[] strArr = new String[8];
        this.namespacePrefix = strArr;
        this.namespaceUri = new String[strArr.length];
        this.namespaceDepth = new int[strArr.length];
        this.f3793wf = xmlPullWrapperFactory;
    }

    @Override
    public String getCurrentNamespaceForElements() {
        return this.currentNs;
    }

    @Override
    public String setCurrentNamespaceForElements(String str) {
        String str2 = this.currentNs;
        this.currentNs = str;
        return str2;
    }

    @Override
    public XmlSerializerWrapper attribute(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.attribute(null, str, str2);
        return this;
    }

    @Override
    public XmlSerializerWrapper startTag(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.startTag(this.currentNs, str);
        return this;
    }

    @Override
    public XmlSerializerWrapper endTag(String str) throws IllegalStateException, IOException, IllegalArgumentException {
        endTag(this.currentNs, str);
        return this;
    }

    @Override
    public XmlSerializerWrapper element(String str, String str2) throws XmlPullParserException, IOException {
        return element(this.currentNs, str, str2);
    }

    @Override
    public XmlSerializerWrapper element(String str, String str2, String str3) throws XmlPullParserException, IOException {
        if (str2 == null) {
            throw new XmlPullParserException("name for element can not be null");
        }
        this.f3795xs.startTag(str, str2);
        if (str3 == null) {
            this.f3795xs.attribute("http://www.w3.org/2001/XMLSchema-instance", "nil", "true");
        } else {
            this.f3795xs.text(str3);
        }
        this.f3795xs.endTag(str, str2);
        return this;
    }

    private void ensureNamespacesCapacity() {
        int i = this.namespaceEnd;
        int i2 = i > 7 ? i * 2 : 8;
        String[] strArr = new String[i2];
        String[] strArr2 = new String[i2];
        int[] iArr = new int[i2];
        String[] strArr3 = this.namespacePrefix;
        if (strArr3 != null) {
            System.arraycopy(strArr3, 0, strArr, 0, i);
            System.arraycopy(this.namespaceUri, 0, strArr2, 0, this.namespaceEnd);
            System.arraycopy(this.namespaceDepth, 0, iArr, 0, this.namespaceEnd);
        }
        this.namespacePrefix = strArr;
        this.namespaceUri = strArr2;
        this.namespaceDepth = iArr;
    }

    @Override
    public void setPrefix(String str, String str2) throws IllegalStateException, IOException, IllegalArgumentException {
        this.f3795xs.setPrefix(str, str2);
        int depth = getDepth();
        for (int i = this.namespaceEnd - 1; i >= 0 && this.namespaceDepth[i] > depth; i--) {
            this.namespaceEnd--;
        }
        if (this.namespaceEnd >= this.namespacePrefix.length) {
            ensureNamespacesCapacity();
        }
        String[] strArr = this.namespacePrefix;
        int i2 = this.namespaceEnd;
        strArr[i2] = str;
        this.namespaceUri[i2] = str2;
        this.namespaceEnd = i2 + 1;
    }

    @Override
    public void fragment(String str) throws XmlPullParserException, IllegalStateException, IOException, IllegalArgumentException {
        StringBuffer stringBuffer = new StringBuffer(str.length() + (this.namespaceEnd * 30));
        stringBuffer.append("<fragment");
        for (int i = this.namespaceEnd - 1; i >= 0; i--) {
            String str2 = this.namespacePrefix[i];
            int i2 = this.namespaceEnd - 1;
            while (true) {
                if (i2 > i) {
                    if (str2.equals(this.namespacePrefix[i2])) {
                        break;
                    } else {
                        i2--;
                    }
                } else {
                    stringBuffer.append(" xmlns");
                    if (str2.length() > 0) {
                        stringBuffer.append(':').append(str2);
                    }
                    stringBuffer.append("='");
                    stringBuffer.append(escapeAttributeValue(this.namespaceUri[i]));
                    stringBuffer.append("'");
                }
            }
        }
        stringBuffer.append(">");
        stringBuffer.append(str);
        stringBuffer.append("</fragment>");
        if (this.fragmentParser == null) {
            this.fragmentParser = this.f3793wf.newPullParserWrapper();
        }
        this.fragmentParser.setInput(new StringReader(stringBuffer.toString()));
        this.fragmentParser.nextTag();
        this.fragmentParser.require(2, null, "fragment");
        while (true) {
            this.fragmentParser.nextToken();
            if (this.fragmentParser.getDepth() != 1 || this.fragmentParser.getEventType() != 3) {
                event(this.fragmentParser);
            } else {
                this.fragmentParser.require(3, null, "fragment");
                return;
            }
        }
    }

    @Override
    public void event(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        switch (xmlPullParser.getEventType()) {
            case 0:
                startDocument(xmlPullParser.getInputEncoding(), (Boolean) xmlPullParser.getProperty(PROPERTY_XMLDECL_STANDALONE));
                break;
            case 1:
                endDocument();
                break;
            case 2:
                writeStartTag(xmlPullParser);
                break;
            case 3:
                endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                break;
            case 4:
                if (xmlPullParser.getDepth() > 0) {
                    text(xmlPullParser.getText());
                } else {
                    ignorableWhitespace(xmlPullParser.getText());
                }
                break;
            case 5:
                cdsect(xmlPullParser.getText());
                break;
            case 6:
                entityRef(xmlPullParser.getName());
                break;
            case 7:
                ignorableWhitespace(xmlPullParser.getText());
                break;
            case 8:
                processingInstruction(xmlPullParser.getText());
                break;
            case 9:
                comment(xmlPullParser.getText());
                break;
            case 10:
                docdecl(xmlPullParser.getText());
                break;
        }
    }

    private void writeStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (!xmlPullParser.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES)) {
            int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth());
            for (int namespaceCount2 = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1); namespaceCount2 < namespaceCount; namespaceCount2++) {
                setPrefix(xmlPullParser.getNamespacePrefix(namespaceCount2), xmlPullParser.getNamespaceUri(namespaceCount2));
            }
        }
        startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
        }
    }

    @Override
    public String escapeAttributeValue(String str) {
        int iIndexOf = str.indexOf(60);
        int iIndexOf2 = str.indexOf(38);
        int iIndexOf3 = str.indexOf(34);
        int iIndexOf4 = str.indexOf(39);
        if (iIndexOf == -1 && iIndexOf2 == -1 && iIndexOf3 == -1 && iIndexOf4 == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 10);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"') {
                stringBuffer.append("&quot;");
            } else if (cCharAt == '<') {
                stringBuffer.append("&lt;");
            } else if (cCharAt == '&') {
                stringBuffer.append("&amp;");
            } else if (cCharAt == '\'') {
                stringBuffer.append("&apos;");
            } else {
                stringBuffer.append(cCharAt);
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public String escapeText(String str) {
        int iIndexOf = str.indexOf(60);
        int iIndexOf2 = str.indexOf(38);
        if (iIndexOf == -1 && iIndexOf2 == -1) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 10);
        int i = 0;
        while (true) {
            if (iIndexOf != -1 || iIndexOf2 != -1) {
                if (iIndexOf != -1 && (iIndexOf == -1 || iIndexOf2 == -1 || iIndexOf2 >= iIndexOf)) {
                    if (iIndexOf2 != -1 && (iIndexOf == -1 || iIndexOf2 == -1 || iIndexOf >= iIndexOf2)) {
                        break;
                    }
                    if (i < iIndexOf) {
                        stringBuffer.append(str.substring(i, iIndexOf));
                    }
                    stringBuffer.append("&lt;");
                    i = iIndexOf + 1;
                    iIndexOf = str.indexOf(60, i);
                } else {
                    if (i < iIndexOf2) {
                        stringBuffer.append(str.substring(i, iIndexOf2));
                    }
                    stringBuffer.append("&amp;");
                    i = iIndexOf2 + 1;
                    iIndexOf2 = str.indexOf(38, i);
                }
            } else {
                stringBuffer.append(str.substring(i));
                return stringBuffer.toString();
            }
        }
        throw new IllegalStateException(new StringBuffer("wrong state posLt=").append(iIndexOf).append(" posAmp=").append(iIndexOf2).append(" for ").append(str).toString());
    }

    @Override
    public void writeDouble(double d) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (d == Double.POSITIVE_INFINITY) {
            this.f3795xs.text("INF");
        } else if (d == Double.NEGATIVE_INFINITY) {
            this.f3795xs.text("-INF");
        } else {
            this.f3795xs.text(Double.toString(d));
        }
    }

    @Override
    public void writeFloat(float f) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (f == Float.POSITIVE_INFINITY) {
            this.f3795xs.text("INF");
        } else if (f == Float.NEGATIVE_INFINITY) {
            this.f3795xs.text("-INF");
        } else {
            this.f3795xs.text(Float.toString(f));
        }
    }

    @Override
    public void writeInt(int i) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.f3795xs.text(Integer.toString(i));
    }

    @Override
    public void writeString(String str) throws XmlPullParserException, IOException, IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("null string can not be written");
        }
        this.f3795xs.text(str);
    }

    @Override
    public void writeDoubleElement(String str, String str2, double d) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.f3795xs.startTag(str, str2);
        writeDouble(d);
        this.f3795xs.endTag(str, str2);
    }

    @Override
    public void writeFloatElement(String str, String str2, float f) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.f3795xs.startTag(str, str2);
        writeFloat(f);
        this.f3795xs.endTag(str, str2);
    }

    @Override
    public void writeIntElement(String str, String str2, int i) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.f3795xs.startTag(str, str2);
        writeInt(i);
        this.f3795xs.endTag(str, str2);
    }

    @Override
    public void wiriteStringElement(String str, String str2, String str3) throws XmlPullParserException, IOException, IllegalArgumentException {
        this.f3795xs.startTag(str, str2);
        writeString(str3);
        this.f3795xs.endTag(str, str2);
    }
}
