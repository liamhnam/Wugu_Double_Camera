package com.bea.xml.stream;

import com.bea.xml.stream.filters.TypeFilter;
import java.io.FileReader;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public class EventReaderFilter implements XMLEventReader {
    private EventFilter filter;
    private XMLEventReader parent;

    public EventReaderFilter(XMLEventReader xMLEventReader) throws XMLStreamException {
        this.parent = xMLEventReader;
    }

    public EventReaderFilter(XMLEventReader xMLEventReader, EventFilter eventFilter) throws XMLStreamException {
        this.parent = xMLEventReader;
        this.filter = eventFilter;
    }

    public void setFilter(EventFilter eventFilter) {
        this.filter = eventFilter;
    }

    @Override
    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException unused) {
            return null;
        }
    }

    @Override
    public XMLEvent nextEvent() throws XMLStreamException {
        if (hasNext()) {
            return this.parent.nextEvent();
        }
        return null;
    }

    @Override
    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        if (!nextEvent().isStartElement()) {
            throw new XMLStreamException("Precondition for readText is nextEvent().getTypeEventType() == START_ELEMENT");
        }
        while (hasNext()) {
            XMLEvent xMLEventPeek = peek();
            if (xMLEventPeek.isStartElement()) {
                throw new XMLStreamException("Unexpected Element start");
            }
            if (xMLEventPeek.isCharacters()) {
                stringBuffer.append(((Characters) xMLEventPeek).getData());
            }
            if (xMLEventPeek.isEndElement()) {
                return stringBuffer.toString();
            }
            nextEvent();
        }
        throw new XMLStreamException("Unexpected end of Document");
    }

    @Override
    public XMLEvent nextTag() throws XMLStreamException {
        while (hasNext()) {
            XMLEvent xMLEventNextEvent = nextEvent();
            if (xMLEventNextEvent.isCharacters() && !((Characters) xMLEventNextEvent).isWhiteSpace()) {
                throw new XMLStreamException("Unexpected text");
            }
            if (xMLEventNextEvent.isStartElement() || xMLEventNextEvent.isEndElement()) {
                return xMLEventNextEvent;
            }
        }
        throw new XMLStreamException("Unexpected end of Document");
    }

    @Override
    public boolean hasNext() {
        while (this.parent.hasNext()) {
            try {
                if (this.filter.accept(this.parent.peek())) {
                    return true;
                }
                this.parent.nextEvent();
            } catch (XMLStreamException unused) {
            }
        }
        return false;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public XMLEvent peek() throws XMLStreamException {
        if (hasNext()) {
            return this.parent.peek();
        }
        return null;
    }

    @Override
    public void close() throws XMLStreamException {
        this.parent.close();
    }

    @Override
    public Object getProperty(String str) {
        return this.parent.getProperty(str);
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLInputFactory xMLInputFactoryNewInstance = XMLInputFactory.newInstance();
        TypeFilter typeFilter = new TypeFilter();
        typeFilter.addType(1);
        typeFilter.addType(2);
        XMLEventReader xMLEventReaderCreateFilteredReader = xMLInputFactoryNewInstance.createFilteredReader(xMLInputFactoryNewInstance.createXMLEventReader(new FileReader(strArr[0])), typeFilter);
        while (xMLEventReaderCreateFilteredReader.hasNext()) {
            System.out.println(xMLEventReaderCreateFilteredReader.nextEvent());
        }
    }
}
