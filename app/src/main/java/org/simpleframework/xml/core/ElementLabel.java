package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.Format;

class ElementLabel extends TemplateLabel {
    private Expression cache;
    private boolean data;
    private Decorator decorator;
    private Introspector detail;
    private Class expect;
    private Format format;
    private Element label;
    private String name;
    private String override;
    private String path;
    private boolean required;
    private Class type;

    @Override
    public Object getEmpty(Context context) {
        return null;
    }

    public ElementLabel(Contact contact, Element element, Format format) {
        this.detail = new Introspector(contact, this, format);
        this.decorator = new Qualifier(contact);
        this.required = element.required();
        this.type = contact.getType();
        this.override = element.name();
        this.expect = element.type();
        this.data = element.data();
        this.format = format;
        this.label = element;
    }

    @Override
    public Decorator getDecorator() throws Exception {
        return this.decorator;
    }

    @Override
    public Type getType(Class cls) {
        Contact contact = getContact();
        return this.expect == Void.TYPE ? contact : new OverrideType(contact, this.expect);
    }

    @Override
    public Converter getConverter(Context context) throws Exception {
        Contact contact = getContact();
        if (context.isPrimitive(contact)) {
            return new Primitive(context, contact);
        }
        if (this.expect == Void.TYPE) {
            return new Composite(context, contact);
        }
        return new Composite(context, contact, this.expect);
    }

    @Override
    public String getName() throws Exception {
        if (this.name == null) {
            this.name = this.format.getStyle().getElement(this.detail.getName());
        }
        return this.name;
    }

    @Override
    public String getPath() throws Exception {
        if (this.path == null) {
            this.path = getExpression().getElement(getName());
        }
        return this.path;
    }

    @Override
    public Expression getExpression() throws Exception {
        if (this.cache == null) {
            this.cache = this.detail.getExpression();
        }
        return this.cache;
    }

    @Override
    public Annotation getAnnotation() {
        return this.label;
    }

    @Override
    public Contact getContact() {
        return this.detail.getContact();
    }

    @Override
    public String getOverride() {
        return this.override;
    }

    @Override
    public Class getType() {
        if (this.expect == Void.TYPE) {
            return this.type;
        }
        return this.expect;
    }

    @Override
    public boolean isRequired() {
        return this.required;
    }

    @Override
    public boolean isData() {
        return this.data;
    }

    @Override
    public String toString() {
        return this.detail.toString();
    }
}
