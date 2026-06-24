package com.bea.xml.stream.events;

import com.bea.xml.stream.util.EmptyIterator;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StartElementEvent extends NamedEvent implements StartElement {
    private List attributes;
    private NamespaceContext context;
    private List namespaces;

    public StartElementEvent() {
    }

    public StartElementEvent(QName qName) {
        super(qName);
        init();
    }

    public void reset() {
        List list = this.attributes;
        if (list != null) {
            list.clear();
        }
        List list2 = this.namespaces;
        if (list2 != null) {
            list2.clear();
        }
        if (this.context != null) {
            this.context = null;
        }
    }

    public StartElementEvent(StartElement startElement) {
        super(startElement.getName());
        init();
        setName(startElement.getName());
        Iterator attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            addAttribute((Attribute) attributes.next());
        }
        startElement.getNamespaces();
        Iterator namespaces = startElement.getNamespaces();
        while (namespaces.hasNext()) {
            addNamespace((Namespace) namespaces.next());
        }
    }

    protected void init() {
        setEventType(1);
    }

    @Override
    public Iterator getAttributes() {
        List list = this.attributes;
        return list == null ? EmptyIterator.emptyIterator : list.iterator();
    }

    @Override
    public Iterator getNamespaces() {
        List list = this.namespaces;
        return list == null ? EmptyIterator.emptyIterator : list.iterator();
    }

    @Override
    public Attribute getAttributeByName(QName qName) {
        if (qName == null) {
            return null;
        }
        Iterator attributes = getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = (Attribute) attributes.next();
            if (attribute.getName().equals(qName)) {
                return attribute;
            }
        }
        return null;
    }

    public void setAttributes(List list) {
        this.attributes = list;
    }

    public void addAttribute(Attribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList();
        }
        this.attributes.add(attribute);
    }

    public void addNamespace(Namespace namespace) {
        if (this.namespaces == null) {
            this.namespaces = new ArrayList();
        }
        this.namespaces.add(namespace);
    }

    @Override
    public String getNamespaceURI(String str) {
        NamespaceContext namespaceContext = this.context;
        if (namespaceContext == null) {
            return null;
        }
        return namespaceContext.getNamespaceURI(str);
    }

    public void setNamespaceContext(NamespaceContext namespaceContext) {
        this.context = namespaceContext;
    }

    @Override
    public NamespaceContext getNamespaceContext() {
        return this.context;
    }

    @Override
    public String toString() {
        String string = new StringBuffer("<").append(nameAsString()).toString();
        Iterator attributes = getAttributes();
        while (attributes.hasNext()) {
            string = new StringBuffer().append(string).append(" ").append(attributes.next().toString()).toString();
        }
        Iterator namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            string = new StringBuffer().append(string).append(" ").append(namespaces.next().toString()).toString();
        }
        return new StringBuffer().append(string).append(">").toString();
    }

    @Override
    protected void doWriteAsEncodedUnicode(Writer writer) throws XMLStreamException, IOException {
        writer.write(60);
        QName name = getName();
        String prefix = name.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            writer.write(prefix);
            writer.write(58);
        }
        writer.write(name.getLocalPart());
        Iterator namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            writer.write(32);
            ((XMLEvent) namespaces.next()).writeAsEncodedUnicode(writer);
        }
        Iterator attributes = getAttributes();
        while (attributes.hasNext()) {
            writer.write(32);
            ((XMLEvent) attributes.next()).writeAsEncodedUnicode(writer);
        }
        writer.write(62);
    }
}
