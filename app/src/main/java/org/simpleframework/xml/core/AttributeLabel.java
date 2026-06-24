package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.stream.Format;

class AttributeLabel extends TemplateLabel {
    private Decorator decorator;
    private Introspector detail;
    private String empty;
    private Format format;
    private Attribute label;
    private String name;
    private Expression path;
    private boolean required;
    private Class type;

    @Override
    public boolean isAttribute() {
        return true;
    }

    @Override
    public boolean isData() {
        return false;
    }

    public AttributeLabel(Contact contact, Attribute attribute, Format format) {
        this.detail = new Introspector(contact, this, format);
        this.decorator = new Qualifier(contact);
        this.required = attribute.required();
        this.type = contact.getType();
        this.empty = attribute.empty();
        this.name = attribute.name();
        this.format = format;
        this.label = attribute;
    }

    @Override
    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    @Override
    public Converter getConverter(Context context) throws Exception {
        return new Primitive(context, getContact(), getEmpty(context));
    }

    @Override
    public String getEmpty(Context context) {
        if (this.detail.isEmpty(this.empty)) {
            return null;
        }
        return this.empty;
    }

    @Override
    public String getName() throws Exception {
        return this.format.getStyle().getAttribute(this.detail.getName());
    }

    @Override
    public String getPath() throws Exception {
        return getExpression().getAttribute(getName());
    }

    @Override
    public Expression getExpression() throws Exception {
        if (this.path == null) {
            this.path = this.detail.getExpression();
        }
        return this.path;
    }

    @Override
    public Annotation getAnnotation() {
        return this.label;
    }

    @Override
    public String getOverride() {
        return this.name;
    }

    @Override
    public Contact getContact() {
        return this.detail.getContact();
    }

    @Override
    public Class getType() {
        return this.type;
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }

    @Override
    public String toString() {
        return this.detail.toString();
    }
}
