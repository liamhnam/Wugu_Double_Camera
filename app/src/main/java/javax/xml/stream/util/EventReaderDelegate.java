package javax.xml.stream.util;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

public class EventReaderDelegate implements XMLEventReader {
    private XMLEventReader reader;

    public EventReaderDelegate() {
    }

    public EventReaderDelegate(XMLEventReader xMLEventReader) {
        this.reader = xMLEventReader;
    }

    public void setParent(XMLEventReader xMLEventReader) {
        this.reader = xMLEventReader;
    }

    public XMLEventReader getParent() {
        return this.reader;
    }

    @Override
    public XMLEvent nextEvent() throws XMLStreamException {
        return this.reader.nextEvent();
    }

    @Override
    public Object next() {
        return this.reader.next();
    }

    @Override
    public boolean hasNext() {
        return this.reader.hasNext();
    }

    @Override
    public XMLEvent peek() throws XMLStreamException {
        return this.reader.peek();
    }

    @Override
    public void close() throws XMLStreamException {
        this.reader.close();
    }

    @Override
    public String getElementText() throws XMLStreamException {
        return this.reader.getElementText();
    }

    @Override
    public XMLEvent nextTag() throws XMLStreamException {
        return this.reader.nextTag();
    }

    @Override
    public Object getProperty(String str) throws IllegalArgumentException {
        return this.reader.getProperty(str);
    }

    @Override
    public void remove() {
        this.reader.remove();
    }
}
