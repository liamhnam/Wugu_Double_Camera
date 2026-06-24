package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.stream.Format;

class VersionLabel extends TemplateLabel {
    private Decorator decorator;
    private Introspector detail;
    private Format format;
    private Version label;
    private String name;
    private Expression path;
    private boolean required;
    private Class type;

    @Override
    public String getEmpty(Context context) {
        return null;
    }

    @Override
    public boolean isAttribute() {
        return true;
    }

    @Override
    public boolean isData() {
        return false;
    }

    public VersionLabel(Contact contact, Version version, Format format) {
        this.detail = new Introspector(contact, this, format);
        this.decorator = new Qualifier(contact);
        this.required = version.required();
        this.type = contact.getType();
        this.name = version.name();
        this.format = format;
        this.label = version;
    }

    @Override
    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    @Override
    public Converter getConverter(Context context) throws Exception {
        String empty = getEmpty(context);
        Contact contact = getContact();
        if (!context.isFloat(contact)) {
            throw new AttributeException("Cannot use %s to represent %s", this.label, contact);
        }
        return new Primitive(context, contact, empty);
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
