package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class FieldContact implements Contact {
    private final Cache<Annotation> cache = new ConcurrentCache();
    private final Field field;
    private final Annotation label;
    private final Annotation[] list;
    private final int modifier;
    private final String name;

    public FieldContact(Field field, Annotation annotation, Annotation[] annotationArr) {
        this.modifier = field.getModifiers();
        this.name = field.getName();
        this.label = annotation;
        this.field = field;
        this.list = annotationArr;
    }

    @Override
    public boolean isReadOnly() {
        return !isStatic() && isFinal();
    }

    public boolean isStatic() {
        return Modifier.isStatic(this.modifier);
    }

    public boolean isFinal() {
        return Modifier.isFinal(this.modifier);
    }

    @Override
    public Class getType() {
        return this.field.getType();
    }

    @Override
    public Class getDependent() {
        return Reflector.getDependent(this.field);
    }

    @Override
    public Class[] getDependents() {
        return Reflector.getDependents(this.field);
    }

    @Override
    public Class getDeclaringClass() {
        return this.field.getDeclaringClass();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Annotation getAnnotation() {
        return this.label;
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> cls) {
        if (cls == this.label.annotationType()) {
            return (T) this.label;
        }
        return (T) getCache(cls);
    }

    private <T extends Annotation> T getCache(Class<T> cls) {
        if (this.cache.isEmpty()) {
            for (Annotation annotation : this.list) {
                this.cache.cache(annotation.annotationType(), annotation);
            }
        }
        return (T) this.cache.fetch(cls);
    }

    @Override
    public void set(Object obj, Object obj2) throws Exception {
        if (isFinal()) {
            return;
        }
        this.field.set(obj, obj2);
    }

    @Override
    public Object get(Object obj) throws Exception {
        return this.field.get(obj);
    }

    @Override
    public String toString() {
        return String.format("field '%s' %s", getName(), this.field.toString());
    }
}
