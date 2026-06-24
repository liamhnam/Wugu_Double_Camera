package org.simpleframework.xml.stream;

import java.util.Iterator;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class StreamReader implements EventReader {
    private EventNode peek;
    private XMLEventReader reader;

    public StreamReader(XMLEventReader xMLEventReader) {
        this.reader = xMLEventReader;
    }

    @Override
    public EventNode peek() throws Exception {
        if (this.peek == null) {
            this.peek = next();
        }
        return this.peek;
    }

    @Override
    public EventNode next() throws Exception {
        EventNode eventNode = this.peek;
        if (eventNode == null) {
            return read();
        }
        this.peek = null;
        return eventNode;
    }

    private EventNode read() throws Exception {
        XMLEvent xMLEventNextEvent = this.reader.nextEvent();
        if (xMLEventNextEvent.isEndDocument()) {
            return null;
        }
        if (xMLEventNextEvent.isStartElement()) {
            return start(xMLEventNextEvent);
        }
        if (xMLEventNextEvent.isCharacters()) {
            return text(xMLEventNextEvent);
        }
        if (xMLEventNextEvent.isEndElement()) {
            return end();
        }
        return read();
    }

    private Start start(XMLEvent xMLEvent) {
        Start start = new Start(xMLEvent);
        return start.isEmpty() ? build(start) : start;
    }

    private Start build(Start start) {
        Iterator<javax.xml.stream.events.Attribute> attributes = start.getAttributes();
        while (attributes.hasNext()) {
            Entry entryAttribute = attribute(attributes.next());
            if (!entryAttribute.isReserved()) {
                start.add(entryAttribute);
            }
        }
        return start;
    }

    private Entry attribute(javax.xml.stream.events.Attribute attribute) {
        return new Entry(attribute);
    }

    private Text text(XMLEvent xMLEvent) {
        return new Text(xMLEvent);
    }

    private End end() {
        return new End();
    }

    private static class Entry extends EventAttribute {
        private final javax.xml.stream.events.Attribute entry;

        @Override
        public boolean isReserved() {
            return false;
        }

        public Entry(javax.xml.stream.events.Attribute attribute) {
            this.entry = attribute;
        }

        @Override
        public String getName() {
            return this.entry.getName().getLocalPart();
        }

        @Override
        public String getPrefix() {
            return this.entry.getName().getPrefix();
        }

        @Override
        public String getReference() {
            return this.entry.getName().getNamespaceURI();
        }

        @Override
        public String getValue() {
            return this.entry.getValue();
        }

        @Override
        public Object getSource() {
            return this.entry;
        }
    }

    private static class Start extends EventElement {
        private final StartElement element;
        private final Location location;

        public Start(XMLEvent xMLEvent) {
            this.element = xMLEvent.asStartElement();
            this.location = xMLEvent.getLocation();
        }

        @Override
        public int getLine() {
            return this.location.getLineNumber();
        }

        @Override
        public String getName() {
            return this.element.getName().getLocalPart();
        }

        @Override
        public String getPrefix() {
            return this.element.getName().getPrefix();
        }

        @Override
        public String getReference() {
            return this.element.getName().getNamespaceURI();
        }

        public Iterator<javax.xml.stream.events.Attribute> getAttributes() {
            return this.element.getAttributes();
        }

        @Override
        public Object getSource() {
            return this.element;
        }
    }

    private static class Text extends EventToken {
        private final Characters text;

        @Override
        public boolean isText() {
            return true;
        }

        public Text(XMLEvent xMLEvent) {
            this.text = xMLEvent.asCharacters();
        }

        @Override
        public String getValue() {
            return this.text.getData();
        }

        @Override
        public Object getSource() {
            return this.text;
        }
    }

    private static class End extends EventToken {
        @Override
        public boolean isEnd() {
            return true;
        }

        private End() {
        }
    }
}
