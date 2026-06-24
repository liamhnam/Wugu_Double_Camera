package org.xmlpull.p065v1.sax2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlPullParserFactory;

public class Driver implements Locator, XMLReader, Attributes {
    protected static final String APACHE_DYNAMIC_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/dynamic";
    protected static final String APACHE_SCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";
    protected static final String DECLARATION_HANDLER_PROPERTY = "http://xml.org/sax/properties/declaration-handler";
    protected static final String LEXICAL_HANDLER_PROPERTY = "http://xml.org/sax/properties/lexical-handler";
    protected static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";
    protected static final String NAMESPACE_PREFIXES_FEATURE = "http://xml.org/sax/features/namespace-prefixes";
    protected static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";
    protected ContentHandler contentHandler = new DefaultHandler();
    protected ErrorHandler errorHandler = new DefaultHandler();

    protected XmlPullParser f3791pp;
    protected String systemId;

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public String getPublicId() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler dTDHandler) {
    }

    @Override
    public void setEntityResolver(EntityResolver entityResolver) {
    }

    public Driver() throws XmlPullParserException {
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        this.f3791pp = xmlPullParserFactoryNewInstance.newPullParser();
    }

    public Driver(XmlPullParser xmlPullParser) throws XmlPullParserException {
        this.f3791pp = xmlPullParser;
    }

    @Override
    public int getLength() {
        return this.f3791pp.getAttributeCount();
    }

    @Override
    public String getURI(int i) {
        return this.f3791pp.getAttributeNamespace(i);
    }

    @Override
    public String getLocalName(int i) {
        return this.f3791pp.getAttributeName(i);
    }

    @Override
    public String getQName(int i) {
        String attributePrefix = this.f3791pp.getAttributePrefix(i);
        if (attributePrefix != null) {
            return new StringBuffer().append(attributePrefix).append(':').append(this.f3791pp.getAttributeName(i)).toString();
        }
        return this.f3791pp.getAttributeName(i);
    }

    @Override
    public String getType(int i) {
        return this.f3791pp.getAttributeType(i);
    }

    @Override
    public String getValue(int i) {
        return this.f3791pp.getAttributeValue(i);
    }

    @Override
    public int getIndex(String str, String str2) {
        for (int i = 0; i < this.f3791pp.getAttributeCount(); i++) {
            if (this.f3791pp.getAttributeNamespace(i).equals(str) && this.f3791pp.getAttributeName(i).equals(str2)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getIndex(String str) {
        for (int i = 0; i < this.f3791pp.getAttributeCount(); i++) {
            if (this.f3791pp.getAttributeName(i).equals(str)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String getType(String str, String str2) {
        for (int i = 0; i < this.f3791pp.getAttributeCount(); i++) {
            if (this.f3791pp.getAttributeNamespace(i).equals(str) && this.f3791pp.getAttributeName(i).equals(str2)) {
                return this.f3791pp.getAttributeType(i);
            }
        }
        return null;
    }

    @Override
    public String getType(String str) {
        for (int i = 0; i < this.f3791pp.getAttributeCount(); i++) {
            if (this.f3791pp.getAttributeName(i).equals(str)) {
                return this.f3791pp.getAttributeType(i);
            }
        }
        return null;
    }

    @Override
    public String getValue(String str, String str2) {
        return this.f3791pp.getAttributeValue(str, str2);
    }

    @Override
    public String getValue(String str) {
        return this.f3791pp.getAttributeValue(null, str);
    }

    @Override
    public String getSystemId() {
        return this.systemId;
    }

    @Override
    public int getLineNumber() {
        return this.f3791pp.getLineNumber();
    }

    @Override
    public int getColumnNumber() {
        return this.f3791pp.getColumnNumber();
    }

    @Override
    public boolean getFeature(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (NAMESPACES_FEATURE.equals(str)) {
            return this.f3791pp.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        }
        if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
            return this.f3791pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES);
        }
        if (VALIDATION_FEATURE.equals(str)) {
            return this.f3791pp.getFeature(XmlPullParser.FEATURE_VALIDATION);
        }
        return this.f3791pp.getFeature(str);
    }

    @Override
    public void setFeature(String str, boolean z) throws SAXNotRecognizedException, SAXNotSupportedException {
        try {
            if (NAMESPACES_FEATURE.equals(str)) {
                this.f3791pp.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", z);
                return;
            }
            if (NAMESPACE_PREFIXES_FEATURE.equals(str)) {
                if (this.f3791pp.getFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES) != z) {
                    this.f3791pp.setFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES, z);
                }
            } else if (VALIDATION_FEATURE.equals(str)) {
                this.f3791pp.setFeature(XmlPullParser.FEATURE_VALIDATION, z);
            } else {
                this.f3791pp.setFeature(str, z);
            }
        } catch (XmlPullParserException e) {
            throw new SAXNotSupportedException(new StringBuffer("problem with setting feature ").append(str).append(": ").append(e).toString());
        }
    }

    @Override
    public Object getProperty(String str) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (DECLARATION_HANDLER_PROPERTY.equals(str) || LEXICAL_HANDLER_PROPERTY.equals(str)) {
            return null;
        }
        return this.f3791pp.getProperty(str);
    }

    @Override
    public void setProperty(String str, Object obj) throws SAXNotRecognizedException, SAXNotSupportedException {
        if (DECLARATION_HANDLER_PROPERTY.equals(str)) {
            throw new SAXNotSupportedException(new StringBuffer("not supported setting property ").append(str).toString());
        }
        if (LEXICAL_HANDLER_PROPERTY.equals(str)) {
            throw new SAXNotSupportedException(new StringBuffer("not supported setting property ").append(str).toString());
        }
        try {
            this.f3791pp.setProperty(str, obj);
        } catch (XmlPullParserException e) {
            throw new SAXNotSupportedException(new StringBuffer("not supported set property ").append(str).append(": ").append(e).toString());
        }
    }

    @Override
    public void setContentHandler(ContentHandler contentHandler) {
        this.contentHandler = contentHandler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return this.contentHandler;
    }

    @Override
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override
    public void parse(InputSource inputSource) throws SAXException, IOException {
        this.systemId = inputSource.getSystemId();
        this.contentHandler.setDocumentLocator(this);
        Reader characterStream = inputSource.getCharacterStream();
        try {
            if (characterStream == null) {
                InputStream byteStream = inputSource.getByteStream();
                String encoding = inputSource.getEncoding();
                if (byteStream == null) {
                    String systemId = inputSource.getSystemId();
                    this.systemId = systemId;
                    if (systemId == null) {
                        this.errorHandler.fatalError(new SAXParseException("null source systemId", this));
                        return;
                    }
                    try {
                        try {
                            byteStream = new URL(this.systemId).openStream();
                        } catch (MalformedURLException unused) {
                            byteStream = new FileInputStream(this.systemId);
                        }
                    } catch (FileNotFoundException e) {
                        this.errorHandler.fatalError(new SAXParseException(new StringBuffer("could not open file with systemId ").append(this.systemId).toString(), this, e));
                        return;
                    }
                }
                this.f3791pp.setInput(byteStream, encoding);
            } else {
                this.f3791pp.setInput(characterStream);
            }
            try {
                this.contentHandler.startDocument();
                this.f3791pp.next();
                if (this.f3791pp.getEventType() != 2) {
                    this.errorHandler.fatalError(new SAXParseException(new StringBuffer("expected start tag not").append(this.f3791pp.getPositionDescription()).toString(), this));
                } else {
                    parseSubTree(this.f3791pp);
                    this.contentHandler.endDocument();
                }
            } catch (XmlPullParserException e2) {
                this.errorHandler.fatalError(new SAXParseException(new StringBuffer("parsing initialization error: ").append(e2).toString(), this, e2));
            }
        } catch (XmlPullParserException e3) {
            this.errorHandler.fatalError(new SAXParseException(new StringBuffer("parsing initialization error: ").append(e3).toString(), this, e3));
        }
    }

    @Override
    public void parse(String str) throws SAXException, IOException {
        parse(new InputSource(str));
    }

    public void parseSubTree(XmlPullParser xmlPullParser) throws SAXException, IOException {
        this.f3791pp = xmlPullParser;
        boolean feature = xmlPullParser.getFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces");
        try {
            if (xmlPullParser.getEventType() != 2) {
                throw new SAXException(new StringBuffer("start tag must be read before skiping subtree").append(xmlPullParser.getPositionDescription()).toString());
            }
            int[] iArr = new int[2];
            StringBuffer stringBuffer = new StringBuffer(16);
            int depth = xmlPullParser.getDepth() - 1;
            int next = 2;
            while (next != 1) {
                if (next != 2) {
                    if (next != 3) {
                        if (next == 4) {
                            this.contentHandler.characters(xmlPullParser.getTextCharacters(iArr), iArr[0], iArr[1]);
                        }
                    } else if (feature) {
                        String name = xmlPullParser.getName();
                        String prefix = xmlPullParser.getPrefix();
                        if (prefix != null) {
                            stringBuffer.setLength(0);
                            stringBuffer.append(prefix);
                            stringBuffer.append(':');
                            stringBuffer.append(name);
                        }
                        this.contentHandler.endElement(xmlPullParser.getNamespace(), name, prefix != null ? name : stringBuffer.toString());
                        int namespaceCount = depth > xmlPullParser.getDepth() ? xmlPullParser.getNamespaceCount(xmlPullParser.getDepth()) : 0;
                        for (int namespaceCount2 = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1) - 1; namespaceCount2 >= namespaceCount; namespaceCount2--) {
                            this.contentHandler.endPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount2));
                        }
                    } else {
                        this.contentHandler.endElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                    }
                } else if (feature) {
                    int depth2 = xmlPullParser.getDepth() - 1;
                    int namespaceCount3 = xmlPullParser.getNamespaceCount(depth2 + 1);
                    for (int namespaceCount4 = depth > depth2 ? xmlPullParser.getNamespaceCount(depth2) : 0; namespaceCount4 < namespaceCount3; namespaceCount4++) {
                        this.contentHandler.startPrefixMapping(xmlPullParser.getNamespacePrefix(namespaceCount4), xmlPullParser.getNamespaceUri(namespaceCount4));
                    }
                    String name2 = xmlPullParser.getName();
                    String prefix2 = xmlPullParser.getPrefix();
                    if (prefix2 != null) {
                        stringBuffer.setLength(0);
                        stringBuffer.append(prefix2);
                        stringBuffer.append(':');
                        stringBuffer.append(name2);
                    }
                    startElement(xmlPullParser.getNamespace(), name2, prefix2 != null ? name2 : stringBuffer.toString());
                } else {
                    startElement(xmlPullParser.getNamespace(), xmlPullParser.getName(), xmlPullParser.getName());
                }
                next = xmlPullParser.next();
                if (xmlPullParser.getDepth() <= depth) {
                    return;
                }
            }
        } catch (XmlPullParserException e) {
            SAXParseException sAXParseException = new SAXParseException(new StringBuffer("parsing error: ").append(e).toString(), this, e);
            e.printStackTrace();
            this.errorHandler.fatalError(sAXParseException);
        }
    }

    protected void startElement(String str, String str2, String str3) throws SAXException {
        this.contentHandler.startElement(str, str2, str3, this);
    }
}
