package com.bea.xml.stream;

import com.bea.xml.stream.util.CircularQueue;
import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.util.NoSuchElementException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

public class XMLEventReaderBase implements XMLEventReader, XMLEventConsumer {
    protected XMLEventAllocator allocator;
    private ConfigurationContextBase configurationContext;
    private CircularQueue elementQ;
    private boolean open;
    private boolean reachedEOF;
    protected XMLStreamReader reader;

    public XMLEventReaderBase(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        this(xMLStreamReader, new XMLEventAllocatorBase());
    }

    public XMLEventReaderBase(XMLStreamReader xMLStreamReader, XMLEventAllocator xMLEventAllocator) throws XMLStreamException {
        this.elementQ = new CircularQueue();
        this.open = true;
        this.reachedEOF = false;
        if (xMLStreamReader == null) {
            throw new IllegalArgumentException("XMLStreamReader may not be null");
        }
        if (xMLEventAllocator == null) {
            throw new IllegalArgumentException("XMLEventAllocator may not be null");
        }
        this.reader = xMLStreamReader;
        this.open = true;
        this.allocator = xMLEventAllocator;
        if (xMLStreamReader.getEventType() == 7) {
            XMLEvent xMLEventAllocate = this.allocator.allocate(xMLStreamReader);
            xMLStreamReader.next();
            add(xMLEventAllocate);
        }
    }

    public void setAllocator(XMLEventAllocator xMLEventAllocator) {
        if (xMLEventAllocator == null) {
            throw new IllegalArgumentException("XMLEvent Allocator may not be null");
        }
        this.allocator = xMLEventAllocator;
    }

    @Override
    public String getElementText() throws XMLStreamException {
        StringBuffer stringBuffer = new StringBuffer();
        XMLEvent xMLEventNextEvent = nextEvent();
        if (!xMLEventNextEvent.isStartElement()) {
            throw new XMLStreamException(new StringBuffer("Precondition for readText is nextEvent().getTypeEventType() == START_ELEMENT (got ").append(xMLEventNextEvent.getEventType()).append(")").toString());
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
    public Object next() {
        try {
            return nextEvent();
        } catch (XMLStreamException unused) {
            return null;
        }
    }

    @Override
    public XMLEvent nextEvent() throws XMLStreamException {
        if (needsMore() && !parseSome()) {
            throw new NoSuchElementException("Attempt to call nextEvent() on a stream with no more elements");
        }
        return get();
    }

    @Override
    public boolean hasNext() {
        if (!this.open) {
            return false;
        }
        if (!this.elementQ.isEmpty()) {
            return true;
        }
        if (this.reader.hasNext()) {
            return true;
        }
        this.open = false;
        return false;
    }

    @Override
    public XMLEvent peek() throws XMLStreamException {
        if (!this.elementQ.isEmpty()) {
            return (XMLEvent) this.elementQ.peek();
        }
        if (parseSome()) {
            return (XMLEvent) this.elementQ.peek();
        }
        return null;
    }

    @Override
    public void add(XMLEvent xMLEvent) throws XMLStreamException {
        this.elementQ.add(xMLEvent);
    }

    protected boolean needsMore() {
        return this.elementQ.isEmpty();
    }

    protected XMLEvent get() throws XMLStreamException {
        return (XMLEvent) this.elementQ.remove();
    }

    protected boolean isOpen() {
        return !this.reachedEOF;
    }

    protected void internal_close() {
        this.reachedEOF = true;
    }

    @Override
    public void close() throws XMLStreamException {
        internal_close();
    }

    protected boolean parseSome() throws XMLStreamException {
        if (this.reachedEOF) {
            return false;
        }
        this.allocator.allocate(this.reader, this);
        if (this.reader.hasNext()) {
            this.reader.next();
        }
        if (this.reader.getEventType() == 8) {
            this.allocator.allocate(this.reader, this);
            this.reachedEOF = true;
        }
        return !needsMore();
    }

    public void setConfigurationContext(ConfigurationContextBase configurationContextBase) {
        this.configurationContext = configurationContextBase;
    }

    @Override
    public Object getProperty(String str) {
        return this.configurationContext.getProperty(str);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        System.setProperty("javax.xml.stream.XMLEventFactory", "com.bea.xml.stream.EventFactory");
        XMLEventReader xMLEventReaderCreateXMLEventReader = XMLInputFactory.newInstance().createXMLEventReader(new FileReader(strArr[0]));
        while (xMLEventReaderCreateXMLEventReader.hasNext()) {
            XMLEvent xMLEventNextEvent = xMLEventReaderCreateXMLEventReader.nextEvent();
            System.out.println(new StringBuffer("[").append(ElementTypeNames.getEventTypeString(xMLEventNextEvent.getEventType())).append("][").append(xMLEventNextEvent).append("]").toString());
        }
    }
}
