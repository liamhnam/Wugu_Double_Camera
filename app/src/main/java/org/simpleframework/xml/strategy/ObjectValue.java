package org.simpleframework.xml.strategy;

class ObjectValue implements Value {
    private Class type;
    private Object value;

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isReference() {
        return false;
    }

    public ObjectValue(Class cls) {
        this.type = cls;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setValue(Object obj) {
        this.value = obj;
    }

    @Override
    public Class getType() {
        return this.type;
    }
}
