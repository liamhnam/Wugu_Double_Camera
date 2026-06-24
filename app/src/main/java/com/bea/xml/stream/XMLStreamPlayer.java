package com.bea.xml.stream;

import com.bea.xml.stream.util.NamespaceContextImpl;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;

public class XMLStreamPlayer implements XMLStreamReader {
    NamespaceContextImpl context = new NamespaceContextImpl();
    EventScanner scanner;
    EventState state;

    @Override
    public void close() throws XMLStreamException {
    }

    @Override
    public String getAttributeType(int i) {
        return "CDATA";
    }

    @Override
    public String getCharacterEncodingScheme() {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public Object getProperty(String str) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int getTextStart() {
        return 0;
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean isAttributeSpecified(int i) {
        return false;
    }

    @Override
    public boolean isStandalone() {
        return true;
    }

    @Override
    public boolean isWhiteSpace() {
        return false;
    }

    @Override
    public void require(int i, String str, String str2) throws XMLStreamException {
    }

    @Override
    public boolean standaloneSet() {
        return false;
    }

    public XMLStreamReader subReader() throws XMLStreamException {
        return null;
    }

    public XMLStreamPlayer() {
    }

    public XMLStreamPlayer(InputStream inputStream) {
        try {
            this.scanner = new EventScanner(new InputStreamReader(inputStream));
            next();
            if (getEventType() == 7) {
                this.scanner = new EventScanner(new InputStreamReader(inputStream, getCharacterEncodingScheme()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(new StringBuffer("Unable to instantiate the XMLStreamPlayer").append(e.getMessage()).toString());
        }
    }

    public XMLStreamPlayer(Reader reader) {
        try {
            this.scanner = new EventScanner(reader);
            next();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public int next() throws XMLStreamException {
        try {
            if (!this.scanner.hasNext()) {
                this.state = null;
                return -1;
            }
            this.state = this.scanner.readElement();
            if (isStartElement()) {
                this.context.openScope();
                for (int i = 0; i < getNamespaceCount(); i++) {
                    this.context.bindNamespace(getNamespacePrefix(i), getNamespaceURI(i));
                }
            } else if (isEndElement() && this.context.getDepth() > 0) {
                this.context.closeScope();
            }
            return this.state.getType();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            throw new XMLStreamException(e.getMessage(), e);
        }
    }

    @Override
    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (getEventType() != 1) {
            throw new XMLStreamException("Precondition for readText is getEventType() == START_ELEMENT");
        }
        while (next() != 8) {
            if (isStartElement()) {
                throw new XMLStreamException("Unexpected Element start");
            }
            if (isCharacters()) {
                stringBuffer.append(getText());
            }
            if (isEndElement()) {
                return stringBuffer.toString();
            }
        }
        throw new XMLStreamException("Unexpected end of Document");
    }

    @Override
    public int nextTag() throws XMLStreamException {
        while (next() != 8) {
            if (isCharacters() && !isWhiteSpace()) {
                throw new XMLStreamException("Unexpected text");
            }
            if (isStartElement() || isEndElement()) {
                return getEventType();
            }
        }
        throw new XMLStreamException("Unexpected end of Document");
    }

    @Override
    public boolean hasNext() throws XMLStreamException {
        try {
            EventState eventState = this.state;
            if (eventState != null) {
                if (eventState.getType() != 8) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new XMLStreamException(e);
        }
    }

    @Override
    public String getNamespaceURI(String str) {
        return this.context.getNamespaceURI(str);
    }

    private Attribute getAttributeInternal(int i) {
        return (Attribute) this.state.getAttributes().get(i);
    }

    private Attribute getNamespaceInternal(int i) {
        return (Attribute) this.state.getNamespaces().get(i);
    }

    @Override
    public boolean isStartElement() {
        return (getEventType() & 1) != 0;
    }

    @Override
    public boolean isEndElement() {
        return (getEventType() & 2) != 0;
    }

    @Override
    public boolean isCharacters() {
        return (getEventType() & 4) != 0;
    }

    @Override
    public String getAttributeValue(String str, String str2) {
        for (int i = 0; i < getAttributeCount(); i++) {
            Attribute attributeInternal = getAttributeInternal(i);
            if (str2.equals(attributeInternal.getName().getLocalPart())) {
                if (str == null) {
                    return attributeInternal.getValue();
                }
                if (str.equals(attributeInternal.getName().getNamespaceURI())) {
                    return attributeInternal.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public int getAttributeCount() {
        if (isStartElement()) {
            return this.state.getAttributes().size();
        }
        return 0;
    }

    @Override
    public QName getAttributeName(int i) {
        return new QName(getAttributeNamespace(i), getAttributeLocalName(i), getAttributePrefix(i));
    }

    @Override
    public String getAttributeNamespace(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getNamespaceURI();
    }

    @Override
    public String getAttributeLocalName(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getLocalPart();
    }

    @Override
    public String getAttributePrefix(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getName().getPrefix();
    }

    @Override
    public String getAttributeValue(int i) {
        Attribute attributeInternal = getAttributeInternal(i);
        if (attributeInternal == null) {
            return null;
        }
        return attributeInternal.getValue();
    }

    @Override
    public int getNamespaceCount() {
        if (isStartElement()) {
            return this.state.getNamespaces().size();
        }
        return 0;
    }

    @Override
    public String getNamespacePrefix(int i) {
        Attribute namespaceInternal = getNamespaceInternal(i);
        if (namespaceInternal == null) {
            return null;
        }
        return namespaceInternal.getName().getLocalPart();
    }

    @Override
    public String getNamespaceURI(int i) {
        Attribute namespaceInternal = getNamespaceInternal(i);
        if (namespaceInternal == null) {
            return null;
        }
        return namespaceInternal.getValue();
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    @Override
    public int getEventType() {
        EventState eventState = this.state;
        if (eventState == null) {
            return 8;
        }
        return eventState.getType();
    }

    @Override
    public String getText() {
        return this.state.getData();
    }

    public Reader getTextStream() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char[] getTextCharacters() {
        return this.state.getData().toCharArray();
    }

    @Override
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getTextLength() {
        return this.state.getData().length();
    }

    @Override
    public String getEncoding() {
        return this.state.getData();
    }

    @Override
    public boolean hasText() {
        return (getEventType() & 15) != 0;
    }

    @Override
    public QName getName() {
        return new QName(getNamespaceURI(), getLocalName(), getPrefix());
    }

    @Override
    public String getLocalName() {
        return this.state.getLocalName();
    }

    @Override
    public boolean hasName() {
        return (getEventType() & 11) != 0;
    }

    @Override
    public String getNamespaceURI() {
        return this.state.getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return this.state.getPrefix();
    }

    @Override
    public String getPITarget() {
        return this.state.getData();
    }

    @Override
    public String getPIData() {
        return this.state.getExtraData();
    }

    public boolean endDocumentIsPresent() {
        return this.scanner.endDocumentIsPresent();
    }

    public static void main(String[] strArr) throws Exception {
        XMLStreamPlayer xMLStreamPlayer = new XMLStreamPlayer(new FileReader(strArr[0]));
        XMLStreamWriter xMLStreamWriterCreateXMLStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(System.out);
        ReaderToWriter readerToWriter = new ReaderToWriter(xMLStreamWriterCreateXMLStreamWriter);
        while (xMLStreamPlayer.hasNext()) {
            readerToWriter.write(xMLStreamPlayer);
            xMLStreamPlayer.next();
        }
        xMLStreamWriterCreateXMLStreamWriter.flush();
    }
}
