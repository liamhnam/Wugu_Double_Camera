package org.simpleframework.xml.transform;

import java.lang.reflect.Array;

class ArrayTransform implements Transform {
    private final Transform delegate;
    private final Class entry;
    private final StringArrayTransform split = new StringArrayTransform();

    public ArrayTransform(Transform transform, Class cls) {
        this.delegate = transform;
        this.entry = cls;
    }

    @Override
    public Object read(String str) throws Exception {
        String[] strArr = this.split.read(str);
        return read(strArr, strArr.length);
    }

    private Object read(String[] strArr, int i) throws Exception {
        Object objNewInstance = Array.newInstance((Class<?>) this.entry, i);
        for (int i2 = 0; i2 < i; i2++) {
            Object obj = this.delegate.read(strArr[i2]);
            if (obj != null) {
                Array.set(objNewInstance, i2, obj);
            }
        }
        return objNewInstance;
    }

    @Override
    public String write(Object obj) throws Exception {
        return write(obj, Array.getLength(obj));
    }

    private String write(Object obj, int i) throws Exception {
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            Object obj2 = Array.get(obj, i2);
            if (obj2 != null) {
                strArr[i2] = this.delegate.write(obj2);
            }
        }
        return this.split.write(strArr);
    }
}
