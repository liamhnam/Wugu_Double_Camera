package org.simpleframework.xml.core;

import java.lang.reflect.Array;
import org.simpleframework.xml.strategy.Value;

class ArrayInstance implements Instance {
    private final int length;
    private final Class type;
    private final Value value;

    public ArrayInstance(Value value) {
        this.length = value.getLength();
        this.type = value.getType();
        this.value = value;
    }

    @Override
    public Object getInstance() throws Exception {
        if (this.value.isReference()) {
            return this.value.getValue();
        }
        Object objNewInstance = Array.newInstance((Class<?>) this.type, this.length);
        Value value = this.value;
        if (value != null) {
            value.setValue(objNewInstance);
        }
        return objNewInstance;
    }

    @Override
    public Object setInstance(Object obj) {
        Value value = this.value;
        if (value != null) {
            value.setValue(obj);
        }
        return obj;
    }

    @Override
    public Class getType() {
        return this.type;
    }

    @Override
    public boolean isReference() {
        return this.value.isReference();
    }
}
