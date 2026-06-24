package org.xmlpull.p065v1.builder.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.xmlpull.p065v1.XmlPullParser;
import org.xmlpull.p065v1.XmlPullParserException;
import org.xmlpull.p065v1.XmlSerializer;
import org.xmlpull.p065v1.builder.XmlAttribute;
import org.xmlpull.p065v1.builder.XmlBuilderException;
import org.xmlpull.p065v1.builder.XmlComment;
import org.xmlpull.p065v1.builder.XmlContainer;
import org.xmlpull.p065v1.builder.XmlDocument;
import org.xmlpull.p065v1.builder.XmlElement;
import org.xmlpull.p065v1.builder.XmlNamespace;
import org.xmlpull.p065v1.builder.XmlPullBuilder;
import org.xmlpull.p065v1.builder.XmlSerializable;

public class XmlPullBuilderImpl extends XmlPullBuilder {
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";

    @Override
    public XmlDocument newDocument(String str, Boolean bool, String str2) {
        return new XmlDocumentImpl(str, bool, str2);
    }

    @Override
    public XmlElement newFragment(String str) {
        return new XmlElementImpl((XmlNamespace) null, str);
    }

    @Override
    public XmlElement newFragment(String str, String str2) {
        return new XmlElementImpl(str, str2);
    }

    @Override
    public XmlElement newFragment(XmlNamespace xmlNamespace, String str) {
        return new XmlElementImpl(xmlNamespace, str);
    }

    @Override
    public XmlNamespace newNamespace(String str) {
        return new XmlNamespaceImpl(null, str);
    }

    @Override
    public XmlNamespace newNamespace(String str, String str2) {
        return new XmlNamespaceImpl(str, str2);
    }

    @Override
    public XmlDocument parse(XmlPullParser xmlPullParser) {
        XmlDocument documentStart = parseDocumentStart(xmlPullParser);
        documentStart.setDocumentElement(parseFragment(xmlPullParser));
        return documentStart;
    }

    @Override
    public Object parseItem(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 2) {
                return parseStartTag(xmlPullParser);
            }
            if (eventType == 4) {
                return xmlPullParser.getText();
            }
            if (eventType == 0) {
                return parseDocumentStart(xmlPullParser);
            }
            throw new XmlBuilderException(new StringBuffer("currently unsupported event type ").append(XmlPullParser.TYPES[eventType]).append(xmlPullParser.getPositionDescription()).toString());
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML item", e);
        }
    }

    private XmlDocument parseDocumentStart(XmlPullParser xmlPullParser) {
        try {
            if (xmlPullParser.getEventType() != 0) {
                throw new XmlBuilderException(new StringBuffer("parser must be positioned on beginning of document and not ").append(xmlPullParser.getPositionDescription()).toString());
            }
            xmlPullParser.next();
            return new XmlDocumentImpl((String) xmlPullParser.getProperty(PROPERTY_XMLDECL_VERSION), (Boolean) xmlPullParser.getProperty(PROPERTY_XMLDECL_STANDALONE), xmlPullParser.getInputEncoding());
        } catch (IOException e) {
            throw new XmlBuilderException("could not read XML document prolog", e);
        } catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not parse XML document prolog", e2);
        }
    }

    @Override
    public XmlElement parseFragment(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType != 2) {
                throw new XmlBuilderException(new StringBuffer("expected parser to be on start tag and not ").append(XmlPullParser.TYPES[eventType]).append(xmlPullParser.getPositionDescription()).toString());
            }
            XmlElement startTag = parseStartTag(xmlPullParser);
            while (true) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    XmlElement startTag2 = parseStartTag(xmlPullParser);
                    startTag.addChild(startTag2);
                    startTag = startTag2;
                } else if (next == 3) {
                    XmlContainer parent = startTag.getParent();
                    if (parent == null) {
                        return startTag;
                    }
                    startTag = (XmlElement) parent;
                } else if (next == 4) {
                    startTag.addChild(xmlPullParser.getText());
                }
            }
        } catch (IOException e) {
            throw new XmlBuilderException("could not read XML tree content", e);
        } catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not build tree from XML", e2);
        }
    }

    @Override
    public XmlElement parseStartTag(XmlPullParser xmlPullParser) {
        try {
            if (xmlPullParser.getEventType() != 2) {
                throw new XmlBuilderException(new StringBuffer("parser must be on START_TAG and not ").append(xmlPullParser.getPositionDescription()).toString());
            }
            XmlElementImpl xmlElementImpl = new XmlElementImpl(xmlPullParser.getNamespace(), xmlPullParser.getName());
            for (int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1); namespaceCount < xmlPullParser.getNamespaceCount(xmlPullParser.getDepth()); namespaceCount++) {
                String namespacePrefix = xmlPullParser.getNamespacePrefix(namespaceCount);
                if (namespacePrefix == null) {
                    namespacePrefix = "";
                }
                xmlElementImpl.declareNamespace(namespacePrefix, xmlPullParser.getNamespaceUri(namespaceCount));
            }
            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                xmlElementImpl.addAttribute(xmlPullParser.getAttributeType(i), xmlPullParser.getAttributePrefix(i), xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i), !xmlPullParser.isAttributeDefault(i));
            }
            return xmlElementImpl;
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML start tag", e);
        }
    }

    @Override
    public XmlDocument parseLocation(String str) {
        try {
            try {
                return parseInputStream(new URL(str).openStream());
            } catch (IOException e) {
                throw new XmlBuilderException(new StringBuffer("could not open connection to URL ").append(str).toString(), e);
            }
        } catch (MalformedURLException e2) {
            throw new XmlBuilderException(new StringBuffer("could not parse URL ").append(str).toString(), e2);
        }
    }

    @Override
    public void serialize(Object obj, XmlSerializer xmlSerializer) {
        if (obj instanceof XmlContainer) {
            serializeContainer((XmlContainer) obj, xmlSerializer);
        } else {
            serializeItem(obj, xmlSerializer);
        }
    }

    private void serializeContainer(XmlContainer xmlContainer, XmlSerializer xmlSerializer) {
        if (xmlContainer instanceof XmlSerializable) {
            ((XmlSerializable) xmlContainer).serialize(xmlSerializer);
        } else if (xmlContainer instanceof XmlDocument) {
            serializeDocument((XmlDocument) xmlContainer, xmlSerializer);
        } else {
            if (xmlContainer instanceof XmlElement) {
                serializeFragment((XmlElement) xmlContainer, xmlSerializer);
                return;
            }
            throw new IllegalArgumentException(new StringBuffer("could not serialzie unknown XML container ").append(xmlContainer.getClass()).toString());
        }
    }

    @Override
    public void serializeItem(Object obj, XmlSerializer xmlSerializer) {
        try {
            if (obj instanceof XmlSerializable) {
                ((XmlSerializable) obj).serialize(xmlSerializer);
            } else if (obj instanceof String) {
                xmlSerializer.text(obj.toString());
            } else {
                if (obj instanceof XmlComment) {
                    xmlSerializer.comment(((XmlComment) obj).getContent());
                    return;
                }
                throw new IllegalArgumentException(new StringBuffer("could not serialize ").append(obj.getClass()).toString());
            }
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }

    @Override
    public void serializeStartTag(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        try {
            if (xmlElement.hasNamespaceDeclarations()) {
                Iterator itNamespaces = xmlElement.namespaces();
                while (itNamespaces.hasNext()) {
                    XmlNamespace xmlNamespace = (XmlNamespace) itNamespaces.next();
                    xmlSerializer.setPrefix(xmlNamespace.getPrefix(), xmlNamespace.getNamespaceName());
                }
            }
            xmlSerializer.startTag(xmlElement.getNamespaceName(), xmlElement.getName());
            if (xmlElement.hasAttributes()) {
                Iterator itAttributes = xmlElement.attributes();
                while (itAttributes.hasNext()) {
                    XmlAttribute xmlAttribute = (XmlAttribute) itAttributes.next();
                    xmlSerializer.attribute(xmlAttribute.getNamespaceName(), xmlAttribute.getName(), xmlAttribute.getValue());
                }
            }
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }

    @Override
    public void serializeEndTag(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        try {
            xmlSerializer.endTag(xmlElement.getNamespaceName(), xmlElement.getName());
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML end tag failed", e);
        }
    }

    private void serializeDocument(XmlDocument xmlDocument, XmlSerializer xmlSerializer) {
        try {
            xmlSerializer.startDocument(xmlDocument.getCharacterEncodingScheme(), xmlDocument.isStandalone());
            serializeFragment(xmlDocument.getDocumentElement(), xmlSerializer);
            try {
                xmlSerializer.endDocument();
            } catch (IOException e) {
                throw new XmlBuilderException("serializing XML document end failed", e);
            }
        } catch (IOException e2) {
            throw new XmlBuilderException("serialziing XML document start failed", e2);
        }
    }

    private void serializeFragment(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        serializeStartTag(xmlElement, xmlSerializer);
        if (xmlElement.hasChildren()) {
            Iterator itChildren = xmlElement.children();
            while (itChildren.hasNext()) {
                Object next = itChildren.next();
                if (next instanceof XmlSerializable) {
                    ((XmlSerializable) next).serialize(xmlSerializer);
                } else if (next instanceof XmlElement) {
                    serializeFragment((XmlElement) next, xmlSerializer);
                } else {
                    serializeItem(next, xmlSerializer);
                }
            }
        }
        serializeEndTag(xmlElement, xmlSerializer);
    }
}
