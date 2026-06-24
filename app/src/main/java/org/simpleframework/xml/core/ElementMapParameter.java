package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.stream.Format;

class ElementMapParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementMapParameter(Constructor constructor, ElementMap elementMap, Format format, int i) throws Exception {
        Contact contact = new Contact(elementMap, constructor, i);
        this.contact = contact;
        ElementMapLabel elementMapLabel = new ElementMapLabel(contact, elementMap, format);
        this.label = elementMapLabel;
        this.expression = elementMapLabel.getExpression();
        this.path = elementMapLabel.getPath();
        this.type = elementMapLabel.getType();
        this.name = elementMapLabel.getName();
        this.key = elementMapLabel.getKey();
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

    private static class Contact extends ParameterContact<ElementMap> {
        public Contact(ElementMap elementMap, Constructor constructor, int i) {
            super(elementMap, constructor, i);
        }

        @Override
        public String getName() {
            return ((ElementMap) this.label).name();
        }
    }
}
