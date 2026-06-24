package com.bea.xml.stream.util;

import java.util.Iterator;

public class EmptyIterator implements Iterator {
    public static final EmptyIterator emptyIterator = new EmptyIterator();

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
