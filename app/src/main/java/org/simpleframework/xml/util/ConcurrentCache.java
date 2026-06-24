package org.simpleframework.xml.util;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache<T> extends ConcurrentHashMap<Object, T> implements Cache<T> {
    @Override
    public void cache(Object obj, T t) {
        put(obj, t);
    }

    @Override
    public T take(Object obj) {
        return remove(obj);
    }

    @Override
    public T fetch(Object obj) {
        return get(obj);
    }

    @Override
    public boolean contains(Object obj) {
        return containsKey(obj);
    }
}
