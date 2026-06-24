package org.simpleframework.xml.strategy;

class Reference implements Value {
    private Class type;
    private Object value;

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public boolean isReference() {
        return true;
    }

    public Reference(Object obj, Class cls) {
        this.value = obj;
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
