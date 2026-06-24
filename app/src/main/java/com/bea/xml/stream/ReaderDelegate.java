package com.bea.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class ReaderDelegate implements XMLStreamReader {
    private XMLStreamReader reader;

    public ReaderDelegate(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public void setDelegate(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public XMLStreamReader getDelegate() {
        return this.reader;
    }

    @Override
    public int next() throws XMLStreamException {
        return this.reader.next();
    }

    @Override
    public int nextTag() throws XMLStreamException {
        return this.reader.nextTag();
    }

    @Override
    public String getElementText() throws XMLStreamException {
        return this.reader.getElementText();
    }

    @Override
    public void require(int i, String str, String str2) throws XMLStreamException {
        this.reader.require(i, str, str2);
    }

    @Override
    public boolean hasNext() throws XMLStreamException {
        return this.reader.hasNext();
    }

    @Override
    public void close() throws XMLStreamException {
        this.reader.close();
    }

    @Override
    public String getNamespaceURI(String str) {
        return this.reader.getNamespaceURI(str);
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return this.reader.getNamespaceContext();
    }

    @Override
    public boolean isStartElement() {
        return this.reader.isStartElement();
    }

    @Override
    public boolean isEndElement() {
        return this.reader.isEndElement();
    }

    @Override
    public boolean isCharacters() {
        return this.reader.isCharacters();
    }

    @Override
    public boolean isWhiteSpace() {
        return this.reader.isWhiteSpace();
    }

    @Override
    public QName getAttributeName(int i) {
        return this.reader.getAttributeName(i);
    }

    @Override
    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        return this.reader.getTextCharacters(i, cArr, i2, i3);
    }

    @Override
    public String getAttributeValue(String str, String str2) {
        return this.reader.getAttributeValue(str, str2);
    }

    @Override
    public int getAttributeCount() {
        return this.reader.getAttributeCount();
    }

    @Override
    public String getAttributePrefix(int i) {
        return this.reader.getAttributePrefix(i);
    }

    @Override
    public String getAttributeNamespace(int i) {
        return this.reader.getAttributeNamespace(i);
    }

    @Override
    public String getAttributeLocalName(int i) {
        return this.reader.getAttributeLocalName(i);
    }

    @Override
    public String getAttributeType(int i) {
        return this.reader.getAttributeType(i);
    }

    @Override
    public String getAttributeValue(int i) {
        return this.reader.getAttributeValue(i);
    }

    @Override
    public boolean isAttributeSpecified(int i) {
        return this.reader.isAttributeSpecified(i);
    }

    @Override
    public int getNamespaceCount() {
        return this.reader.getNamespaceCount();
    }

    @Override
    public String getNamespacePrefix(int i) {
        return this.reader.getNamespacePrefix(i);
    }

    @Override
    public String getNamespaceURI(int i) {
        return this.reader.getNamespaceURI(i);
    }

    @Override
    public int getEventType() {
        return this.reader.getEventType();
    }

    @Override
    public String getText() {
        return this.reader.getText();
    }

    @Override
    public char[] getTextCharacters() {
        return this.reader.getTextCharacters();
    }

    @Override
    public int getTextStart() {
        return this.reader.getTextStart();
    }

    @Override
    public int getTextLength() {
        return this.reader.getTextLength();
    }

    @Override
    public String getEncoding() {
        return this.reader.getEncoding();
    }

    @Override
    public boolean hasText() {
        return this.reader.hasText();
    }

    @Override
    public Location getLocation() {
        return this.reader.getLocation();
    }

    @Override
    public QName getName() {
        return this.reader.getName();
    }

    @Override
    public String getLocalName() {
        return this.reader.getLocalName();
    }

    @Override
    public boolean hasName() {
        return this.reader.hasName();
    }

    @Override
    public String getNamespaceURI() {
        return this.reader.getNamespaceURI();
    }

    @Override
    public String getPrefix() {
        return this.reader.getPrefix();
    }

    @Override
    public String getVersion() {
        return this.reader.getVersion();
    }

    @Override
    public boolean isStandalone() {
        return this.reader.isStandalone();
    }

    @Override
    public boolean standaloneSet() {
        return this.reader.standaloneSet();
    }

    @Override
    public String getCharacterEncodingScheme() {
        return this.reader.getCharacterEncodingScheme();
    }

    @Override
    public String getPITarget() {
        return this.reader.getPITarget();
    }

    @Override
    public String getPIData() {
        return this.reader.getPIData();
    }

    @Override
    public Object getProperty(String str) {
        return this.reader.getProperty(str);
    }
}
