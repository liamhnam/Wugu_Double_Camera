package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.stream.Format;

class TextLabel extends TemplateLabel {
    private Contact contact;
    private boolean data;
    private Introspector detail;
    private String empty;
    private Text label;
    private Expression path;
    private boolean required;
    private Class type;

    @Override
    public Decorator getDecorator() throws Exception {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public boolean isInline() {
        return true;
    }

    @Override
    public boolean isText() {
        return true;
    }

    public TextLabel(Contact contact, Text text, Format format) {
        this.detail = new Introspector(contact, this, format);
        this.required = text.required();
        this.type = contact.getType();
        this.empty = text.empty();
        this.data = text.data();
        this.contact = contact;
        this.label = text;
    }

    @Override
    public Converter getConverter(Context context) throws Exception {
        String empty = getEmpty(context);
        Contact contact = getContact();
        if (!context.isPrimitive(contact)) {
            throw new TextException("Cannot use %s to represent %s", contact, this.label);
        }
        return new Primitive(context, contact, empty);
    }

    @Override
    public String getEmpty(Context context) {
        if (this.detail.isEmpty(this.empty)) {
            return null;
        }
        return this.empty;
    }

    @Override
    public String getPath() throws Exception {
        return getExpression().getPath();
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
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public String getOverride() {
        return this.contact.toString();
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
    public boolean isData() {
        return this.data;
    }

    @Override
    public String toString() {
        return this.detail.toString();
    }
}
