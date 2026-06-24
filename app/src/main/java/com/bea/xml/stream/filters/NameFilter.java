package com.bea.xml.stream.filters;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class NameFilter implements EventFilter, StreamFilter {
    private QName name;

    public NameFilter(QName qName) {
        this.name = qName;
    }

    @Override
    public boolean accept(XMLEvent xMLEvent) {
        QName name;
        if (!xMLEvent.isStartElement() && !xMLEvent.isEndElement()) {
            return false;
        }
        if (xMLEvent.isStartElement()) {
            name = ((StartElement) xMLEvent).getName();
        } else {
            name = ((EndElement) xMLEvent).getName();
        }
        return this.name.equals(name);
    }

    @Override
    public boolean accept(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader.isStartElement() || xMLStreamReader.isEndElement()) {
            return this.name.equals(new QName(xMLStreamReader.getNamespaceURI(), xMLStreamReader.getLocalName()));
        }
        return false;
    }
}
