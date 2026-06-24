package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Type;

abstract class TemplateLabel implements Label {
    private final KeyBuilder builder = new KeyBuilder(this);

    @Override
    public Type getDependent() throws Exception {
        return null;
    }

    @Override
    public String getEntry() throws Exception {
        return null;
    }

    @Override
    public Label getLabel(Class cls) throws Exception {
        return this;
    }

    @Override
    public boolean isAttribute() {
        return false;
    }

    @Override
    public boolean isCollection() {
        return false;
    }

    @Override
    public boolean isInline() {
        return false;
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isTextList() {
        return false;
    }

    @Override
    public boolean isUnion() {
        return false;
    }

    protected TemplateLabel() {
    }

    @Override
    public Type getType(Class cls) throws Exception {
        return getContact();
    }

    @Override
    public String[] getNames() throws Exception {
        return new String[]{getPath(), getName()};
    }

    @Override
    public String[] getPaths() throws Exception {
        return new String[]{getPath()};
    }

    @Override
    public Object getKey() throws Exception {
        return this.builder.getKey();
    }
}
