package org.simpleframework.xml.strategy;

class ArrayValue implements Value {
    private int size;
    private Class type;
    private Object value;

    @Override
    public boolean isReference() {
        return false;
    }

    public ArrayValue(Class cls, int i) {
        this.type = cls;
        this.size = i;
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

    @Override
    public int getLength() {
        return this.size;
    }
}
