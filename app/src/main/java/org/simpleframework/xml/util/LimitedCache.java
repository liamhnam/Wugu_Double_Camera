package org.simpleframework.xml.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LimitedCache<T> extends LinkedHashMap<Object, T> implements Cache<T> {
    private final int capacity;

    public LimitedCache() {
        this(50000);
    }

    public LimitedCache(int i) {
        this.capacity = i;
    }

    @Override
    public void cache(Object obj, T t) {
        put(obj, t);
    }

    @Override
    public T take(Object obj) {
        return (T) remove(obj);
    }

    @Override
    public T fetch(Object obj) {
        return get(obj);
    }

    @Override
    public boolean contains(Object obj) {
        return containsKey(obj);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Object, T> entry) {
        return size() > this.capacity;
    }
}
