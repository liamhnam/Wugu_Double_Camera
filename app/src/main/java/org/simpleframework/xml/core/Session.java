package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class Session implements Map {
    private final Map map;
    private final boolean strict;

    public Session() {
        this(true);
    }

    public Session(boolean z) {
        this.map = new HashMap();
        this.strict = z;
    }

    public boolean isStrict() {
        return this.strict;
    }

    public Map getMap() {
        return this.map;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override
    public boolean containsKey(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override
    public Object get(Object obj) {
        return this.map.get(obj);
    }

    @Override
    public Object put(Object obj, Object obj2) {
        return this.map.put(obj, obj2);
    }

    @Override
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override
    public void putAll(Map map) {
        this.map.putAll(map);
    }

    @Override
    public Set keySet() {
        return this.map.keySet();
    }

    @Override
    public Collection values() {
        return this.map.values();
    }

    @Override
    public Set entrySet() {
        return this.map.entrySet();
    }

    @Override
    public void clear() {
        this.map.clear();
    }
}
