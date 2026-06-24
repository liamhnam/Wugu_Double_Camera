package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class Variable implements Label {
    private final Label label;
    private final Object value;

    @Override
    public Label getLabel(Class cls) {
        return this;
    }

    public Variable(Label label, Object obj) {
        this.label = label;
        this.value = obj;
    }

    @Override
    public Type getType(Class cls) throws Exception {
        return this.label.getType(cls);
    }

    @Override
    public String[] getNames() throws Exception {
        return this.label.getNames();
    }

    @Override
    public String[] getPaths() throws Exception {
        return this.label.getPaths();
    }

    public Object getValue() {
        return this.value;
    }

    @Override
    public Decorator getDecorator() throws Exception {
        return this.label.getDecorator();
    }

    @Override
    public Converter getConverter(Context context) throws Exception {
        Converter converter = this.label.getConverter(context);
        return converter instanceof Adapter ? converter : new Adapter(converter, this.label, this.value);
    }

    @Override
    public Object getEmpty(Context context) throws Exception {
        return this.label.getEmpty(context);
    }

    @Override
    public Contact getContact() {
        return this.label.getContact();
    }

    @Override
    public Type getDependent() throws Exception {
        return this.label.getDependent();
    }

    @Override
    public Object getKey() throws Exception {
        return this.label.getKey();
    }

    @Override
    public String getEntry() throws Exception {
        return this.label.getEntry();
    }

    @Override
    public String getName() throws Exception {
        return this.label.getName();
    }

    @Override
    public Annotation getAnnotation() {
        return this.label.getAnnotation();
    }

    @Override
    public String getPath() throws Exception {
        return this.label.getPath();
    }

    @Override
    public Expression getExpression() throws Exception {
        return this.label.getExpression();
    }

    @Override
    public String getOverride() {
        return this.label.getOverride();
    }

    @Override
    public Class getType() {
        return this.label.getType();
    }

    @Override
    public boolean isData() {
        return this.label.isData();
    }

    @Override
    public boolean isInline() {
        return this.label.isInline();
    }

    @Override
    public boolean isAttribute() {
        return this.label.isAttribute();
    }

    @Override
    public boolean isCollection() {
        return this.label.isCollection();
    }

    @Override
    public boolean isRequired() {
        return this.label.isRequired();
    }

    @Override
    public boolean isText() {
        return this.label.isText();
    }

    @Override
    public boolean isTextList() {
        return this.label.isTextList();
    }

    @Override
    public boolean isUnion() {
        return this.label.isUnion();
    }

    @Override
    public String toString() {
        return this.label.toString();
    }

    private static class Adapter implements Repeater {
        private final Label label;
        private final Converter reader;
        private final Object value;

        public Adapter(Converter converter, Label label, Object obj) {
            this.reader = converter;
            this.value = obj;
            this.label = label;
        }

        @Override
        public Object read(InputNode inputNode) throws Exception {
            return read(inputNode, this.value);
        }

        @Override
        public Object read(InputNode inputNode, Object obj) throws Exception {
            Position position = inputNode.getPosition();
            String name = inputNode.getName();
            Converter converter = this.reader;
            if (converter instanceof Repeater) {
                return ((Repeater) converter).read(inputNode, obj);
            }
            throw new PersistenceException("Element '%s' is already used with %s at %s", name, this.label, position);
        }

        @Override
        public boolean validate(InputNode inputNode) throws Exception {
            Position position = inputNode.getPosition();
            String name = inputNode.getName();
            Converter converter = this.reader;
            if (converter instanceof Repeater) {
                return ((Repeater) converter).validate(inputNode);
            }
            throw new PersistenceException("Element '%s' declared twice at %s", name, position);
        }

        @Override
        public void write(OutputNode outputNode, Object obj) throws Exception {
            write(outputNode, obj);
        }
    }
}
