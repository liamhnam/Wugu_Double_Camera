package org.simpleframework.xml.core;

abstract class TemplateParameter implements Parameter {
    @Override
    public boolean isAttribute() {
        return false;
    }

    @Override
    public boolean isText() {
        return false;
    }

    protected TemplateParameter() {
    }
}
