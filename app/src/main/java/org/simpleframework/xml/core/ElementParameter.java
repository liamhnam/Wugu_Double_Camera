package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.stream.Format;

class ElementParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementParameter(Constructor constructor, Element element, Format format, int i) throws Exception {
        Contact contact = new Contact(element, constructor, i);
        this.contact = contact;
        ElementLabel elementLabel = new ElementLabel(contact, element, format);
        this.label = elementLabel;
        this.expression = elementLabel.getExpression();
        this.path = elementLabel.getPath();
        this.type = elementLabel.getType();
        this.name = elementLabel.getName();
        this.key = elementLabel.getKey();
        this.index = i;
    }

    @Override
    public Object getKey() {
        return this.key;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public Class getType() {
        return this.type;
    }

    @Override
    public Annotation getAnnotation() {
        return this.contact.getAnnotation();
    }

    @Override
    public int getIndex() {
        return this.index;
    }

    @Override
    public boolean isRequired() {
        return this.label.isRequired();
    }

    @Override
    public boolean isPrimitive() {
        return this.type.isPrimitive();
    }

    @Override
    public String toString() {
        return this.contact.toString();
    }

    private static class Contact extends ParameterContact<Element> {
        public Contact(Element element, Constructor constructor, int i) {
            super(element, constructor, i);
        }

        @Override
        public String getName() {
            return ((Element) this.label).name();
        }
    }
}
