package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

abstract class ParameterContact<T extends Annotation> implements Contact {
    protected final Constructor factory;
    protected final int index;
    protected final T label;
    protected final Annotation[] labels;
    protected final Class owner;

    @Override
    public Object get(Object obj) {
        return null;
    }

    @Override
    public abstract String getName();

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public void set(Object obj, Object obj2) {
    }

    public ParameterContact(T t, Constructor constructor, int i) {
        this.labels = constructor.getParameterAnnotations()[i];
        this.owner = constructor.getDeclaringClass();
        this.factory = constructor;
        this.index = i;
        this.label = t;
    }

    @Override
    public Annotation getAnnotation() {
        return this.label;
    }

    @Override
    public Class getType() {
        return this.factory.getParameterTypes()[this.index];
    }

    @Override
    public Class getDependent() {
        return Reflector.getParameterDependent(this.factory, this.index);
    }

    @Override
    public Class[] getDependents() {
        return Reflector.getParameterDependents(this.factory, this.index);
    }

    @Override
    public Class getDeclaringClass() {
        return this.owner;
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        for (Annotation annotation : this.labels) {
            A a2 = (A) annotation;
            if (a2.annotationType().equals(cls)) {
                return a2;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("parameter %s of constructor %s", Integer.valueOf(this.index), this.factory);
    }
}
