package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.stream.Format;

class AttributeParameter extends TemplateParameter {
    private final Contact contact;
    private final Expression expression;
    private final int index;
    private final Object key;
    private final Label label;
    private final String name;
    private final String path;
    private final Class type;

    @Override
    public boolean isAttribute() {
        return true;
    }

    public AttributeParameter(Constructor constructor, Attribute attribute, Format format, int i) throws Exception {
        Contact contact = new Contact(attribute, constructor, i);
        this.contact = contact;
        AttributeLabel attributeLabel = new AttributeLabel(contact, attribute, format);
        this.label = attributeLabel;
        this.expression = attributeLabel.getExpression();
        this.path = attributeLabel.getPath();
        this.type = attributeLabel.getType();
        this.name = attributeLabel.getName();
        this.key = attributeLabel.getKey();
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

    private static class Contact extends ParameterContact<Attribute> {
        public Contact(Attribute attribute, Constructor constructor, int i) {
            super(attribute, constructor, i);
        }

        @Override
        public String getName() {
            return ((Attribute) this.label).name();
        }
    }
}
