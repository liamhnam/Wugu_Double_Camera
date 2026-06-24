package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import org.simpleframework.xml.util.Entry;

public class Dictionary<T extends Entry> extends AbstractSet<T> {
    protected final Table<T> map = new Table<>();

    private static class Table<T> extends HashMap<String, T> {
    }

    @Override
    public boolean add(T t) {
        return this.map.put(t.getName(), t) != null;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public Iterator<T> iterator() {
        return this.map.values().iterator();
    }

    public T get(String str) {
        return this.map.get(str);
    }

    public T remove(String str) {
        return this.map.remove(str);
    }
}
