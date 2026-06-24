package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class OverrideValue implements Value {
    private final Class type;
    private final Value value;

    public OverrideValue(Value value, Class cls) {
        this.value = value;
        this.type = cls;
    }

    @Override
    public Object getValue() {
        return this.value.getValue();
    }

    @Override
    public void setValue(Object obj) {
        this.value.setValue(obj);
    }

    @Override
    public Class getType() {
        return this.type;
    }

    @Override
    public int getLength() {
        return this.value.getLength();
    }

    @Override
    public boolean isReference() {
        return this.value.isReference();
    }
}
