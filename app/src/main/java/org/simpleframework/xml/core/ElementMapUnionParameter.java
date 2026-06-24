package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.stream.Format;

class ElementMapUnionParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    public ElementMapUnionParameter(Constructor constructor, ElementMapUnion elementMapUnion, ElementMap elementMap, Format format, int i) throws Exception {
        Contact contact = new Contact(elementMap, constructor, i);
        this.contact = contact;
        ElementMapUnionLabel elementMapUnionLabel = new ElementMapUnionLabel(contact, elementMapUnion, elementMap, format);
        this.label = elementMapUnionLabel;
        this.expression = elementMapUnionLabel.getExpression();
        this.path = elementMapUnionLabel.getPath();
        this.type = elementMapUnionLabel.getType();
        this.name = elementMapUnionLabel.getName();
        this.key = elementMapUnionLabel.getKey();
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
