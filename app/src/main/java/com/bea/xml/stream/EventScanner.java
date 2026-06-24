package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;

public class EventScanner {
    protected char currentChar;
    protected int currentLine = 0;
    private boolean readEndDocument = false;
    protected Reader reader;

    public EventScanner() {
    }

    public EventScanner(Reader reader) throws IOException {
        setReader(reader);
    }

    public void setReader(Reader reader) throws IOException {
        this.reader = reader;
        read();
        skipSpace();
    }

    protected String readString(char c) throws XMLStreamException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        while (getChar() != c) {
            if (getChar() == '[' && c == ']') {
                read();
                stringBuffer.append('[');
                if (getChar() != ']') {
                    stringBuffer.append(readString(']'));
                }
                stringBuffer.append(']');
                read(']');
            } else {
                stringBuffer.append(getChar());
                read();
            }
        }
        return stringBuffer.toString();
    }

    protected char getChar() {
        return this.currentChar;
    }

    protected void skipSpace() throws IOException {
        while (true) {
            char c = this.currentChar;
            if (!((c == ' ') | (c == '\n') | (c == '\t')) && !(c == '\r')) {
                return;
            } else {
                read();
            }
        }
    }

    protected char read() throws IOException {
        char c = (char) this.reader.read();
        this.currentChar = c;
        if (c == '\n') {
            this.currentLine++;
        }
        return c;
    }

    protected char read(char c) throws XMLStreamException, IOException {
        if (this.currentChar == c) {
            return read();
        }
        throw new XMLStreamException(new StringBuffer("Unexpected character '").append(this.currentChar).append("' , expected '").append(c).append("' at line ").append(this.currentLine).toString());
    }

    protected void read(String str) throws XMLStreamException, IOException {
        for (int i = 0; i < str.length(); i++) {
            read(str.charAt(i));
        }
    }

    protected int readType() throws XMLStreamException, IOException {
        read('[');
        int eventType = ElementTypeNames.getEventType(readString(']'));
        read(']');
        return eventType;
    }

    public EventState readStartElement() throws XMLStreamException, IOException {
        EventState eventState = new EventState(1);
        read('[');
        eventState.setName(readName());
        if (getChar() == '[') {
            for (Object obj : readAttributes()) {
                if (obj instanceof Namespace) {
                    eventState.addNamespace(obj);
                } else {
                    eventState.addAttribute(obj);
                }
            }
        }
        read(']');
        return eventState;
    }

    public EventState readEndElement() throws XMLStreamException, IOException {
        EventState eventState = new EventState(2);
        read('[');
        eventState.setName(readName());
        read(']');
        return eventState;
    }

    public EventState readProcessingInstruction() throws XMLStreamException, IOException {
        String string;
        EventState eventState = new EventState(3);
        read('[');
        String string2 = readString(']');
        read(']');
        if (getChar() == ',') {
            read(",[");
            string = readString(']');
            read(']');
        } else {
            string = null;
        }
        eventState.setData(string2);
        eventState.setExtraData(string);
        return eventState;
    }

    public EventState readCharacterData() throws XMLStreamException, IOException {
        EventState eventState = new EventState(4);
        read('[');
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public EventState readCDATA() throws XMLStreamException, IOException {
        EventState eventState = new EventState(12);
        read('[');
        readString(']');
        read(']');
        return eventState;
    }

    public EventState readStartDocument() throws XMLStreamException, IOException {
        EventState eventState = new EventState(7);
        if (getChar() != ';') {
            read('[');
            read('[');
            String string = readString(']');
            read(']');
            read(',');
            read('[');
            String string2 = readString(']');
            read(']');
            read(']');
            eventState.setData(string);
            eventState.setExtraData(string2);
        }
        return eventState;
    }

    public EventState readDTD() throws XMLStreamException, IOException {
        EventState eventState = new EventState(11);
        read('[');
        String string = readString(']');
        read(']');
        eventState.setData(string);
        return eventState;
    }

    public EventState readEndDocument() throws XMLStreamException {
        return new EventState(8);
    }

    public EventState readComment() throws XMLStreamException, IOException {
        EventState eventState = new EventState(5);
        read('[');
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public String getPrefix(String str) {
        int iIndexOf = str.indexOf(58);
        if (iIndexOf == -1) {
            return null;
        }
        return str.substring(0, iIndexOf);
    }

    public String getName(String str) {
        int iIndexOf = str.indexOf(58);
        return iIndexOf == -1 ? str : str.substring(iIndexOf + 1);
    }

    public QName readName() throws XMLStreamException, IOException {
        read('[');
        QName name = readName(']');
        read(']');
        return name;
    }

    public QName readName(char c) throws XMLStreamException, IOException {
        String string;
        if (getChar() == '\'') {
            read('\'');
            string = readString('\'');
            read('\'');
            read(':');
        } else {
            string = "";
        }
        String string2 = readString(c);
        String prefix = getPrefix(string2);
        return new QName(string, getName(string2), prefix != null ? prefix : "");
    }

    public List readAttributes() throws XMLStreamException, IOException {
        ArrayList arrayList = new ArrayList();
        while (getChar() == '[') {
            arrayList.add(readAttribute());
        }
        return arrayList;
    }

    public Attribute readAttribute() throws XMLStreamException, IOException {
        read('[');
        read('[');
        String string = readString(']');
        read(']');
        QName name = readName();
        read("=[");
        String string2 = readString(']');
        read(']');
        read(']');
        if (string.equals("ATTRIBUTE")) {
            return new AttributeBase(name, string2);
        }
        if (string.equals("DEFAULT")) {
            return new NamespaceBase(string2);
        }
        if (string.equals("NAMESPACE")) {
            return new NamespaceBase(name.getLocalPart(), string2);
        }
        throw new XMLStreamException("Parser Error expected (ATTRIBUTE||DEFAULT|NAMESPACE");
    }

    public EventState readEntityReference() throws XMLStreamException, IOException {
        EventState eventState = new EventState(9);
        read('[');
        eventState.setData(readString(']'));
        read(']');
        return eventState;
    }

    public EventState readSpace() throws XMLStreamException, IOException {
        EventState eventState = new EventState(6);
        read('[');
        String string = readString(']');
        read(']');
        eventState.setData(string);
        return eventState;
    }

    public EventState readElement() throws XMLStreamException, IOException {
        EventState startElement;
        int type = readType();
        switch (type) {
            case 1:
                startElement = readStartElement();
                break;
            case 2:
                startElement = readEndElement();
                break;
            case 3:
                startElement = readProcessingInstruction();
                break;
            case 4:
                startElement = readCharacterData();
                break;
            case 5:
                startElement = readComment();
                break;
            case 6:
                startElement = readSpace();
                break;
            case 7:
                startElement = readStartDocument();
                break;
            case 8:
                this.readEndDocument = true;
                startElement = readEndDocument();
                break;
            case 9:
                startElement = readEntityReference();
                break;
            case 10:
            default:
                throw new XMLStreamException(new StringBuffer("Attempt to read unknown element [").append(type).append("]").toString());
            case 11:
                startElement = readDTD();
                break;
            case 12:
                startElement = readCDATA();
                break;
        }
        read(';');
        skipSpace();
        return startElement;
    }

    public boolean endDocumentIsPresent() {
        return this.readEndDocument;
    }

    public boolean hasNext() throws IOException {
        return this.reader.ready() && !this.readEndDocument;
    }

    public static void main(String[] strArr) throws Exception {
        EventScanner eventScanner = new EventScanner(new FileReader(strArr[0]));
        while (eventScanner.hasNext()) {
            System.out.println(eventScanner.readElement());
        }
    }
}
