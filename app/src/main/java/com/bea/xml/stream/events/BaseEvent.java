package com.bea.xml.stream.events;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public abstract class BaseEvent implements XMLEvent, Location {
    private int characterOffset;
    private int column;
    private int eventType;
    private int line;
    private String locationURI;

    protected abstract void doWriteAsEncodedUnicode(Writer writer) throws XMLStreamException, IOException;

    @Override
    public Location getLocation() {
        return this;
    }

    @Override
    public String getPublicId() {
        return null;
    }

    @Override
    public QName getSchemaType() {
        return null;
    }

    public String getSourceName() {
        return null;
    }

    @Override
    public String getSystemId() {
        return null;
    }

    public void recycle() {
    }

    public BaseEvent() {
        this.eventType = -1;
        this.line = -1;
        this.column = -1;
        this.characterOffset = 0;
    }

    public BaseEvent(int i) {
        this.line = -1;
        this.column = -1;
        this.characterOffset = 0;
        this.eventType = i;
    }

    @Override
    public int getEventType() {
        return this.eventType;
    }

    protected void setEventType(int i) {
        this.eventType = i;
    }

    public String getTypeAsString() {
        return ElementTypeNames.getEventTypeString(this.eventType);
    }

    @Override
    public boolean isStartElement() {
        return this.eventType == 1;
    }

    @Override
    public boolean isEndElement() {
        return this.eventType == 2;
    }

    @Override
    public boolean isEntityReference() {
        return this.eventType == 9;
    }

    @Override
    public boolean isProcessingInstruction() {
        return this.eventType == 3;
    }

    @Override
    public boolean isCharacters() {
        return this.eventType == 4;
    }

    @Override
    public boolean isStartDocument() {
        return this.eventType == 7;
    }

    @Override
    public boolean isEndDocument() {
        return this.eventType == 8;
    }

    @Override
    public boolean isAttribute() {
        return this.eventType == 10;
    }

    @Override
    public boolean isNamespace() {
        return this.eventType == 13;
    }

    @Override
    public int getLineNumber() {
        return this.line;
    }

    public void setLineNumber(int i) {
        this.line = i;
    }

    @Override
    public int getColumnNumber() {
        return this.column;
    }

    public void setColumnNumber(int i) {
        this.column = i;
    }

    @Override
    public int getCharacterOffset() {
        return this.characterOffset;
    }

    public void setCharacterOffset(int i) {
        this.characterOffset = i;
    }

    public String getLocationURI() {
        return this.locationURI;
    }

    public void setLocationURI(String str) {
        this.locationURI = str;
    }

    @Override
    public StartElement asStartElement() {
        return (StartElement) this;
    }

    @Override
    public EndElement asEndElement() {
        return (EndElement) this;
    }

    @Override
    public Characters asCharacters() {
        return (Characters) this;
    }

    @Override
    public final void writeAsEncodedUnicode(Writer writer) throws XMLStreamException {
        try {
            doWriteAsEncodedUnicode(writer);
        } catch (IOException e) {
            throw new XMLStreamException(e);
        }
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter(64);
        try {
            writeAsEncodedUnicode(stringWriter);
        } catch (XMLStreamException e) {
            stringWriter.write("[ERROR: ");
            stringWriter.write(e.toString());
            stringWriter.write("]");
        }
        return stringWriter.toString();
    }
}
