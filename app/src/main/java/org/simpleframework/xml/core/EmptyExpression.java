package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.stream.Style;

class EmptyExpression implements Expression {
    private final List<String> list = new LinkedList();
    private final Style style;

    @Override
    public String getFirst() {
        return null;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getLast() {
        return null;
    }

    @Override
    public String getPath() {
        return "";
    }

    @Override
    public Expression getPath(int i) {
        return null;
    }

    @Override
    public Expression getPath(int i, int i2) {
        return null;
    }

    @Override
    public String getPrefix() {
        return null;
    }

    @Override
    public boolean isAttribute() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isPath() {
        return false;
    }

    public EmptyExpression(Format format) {
        this.style = format.getStyle();
    }

    @Override
    public Iterator<String> iterator() {
        return this.list.iterator();
    }

    @Override
    public String getElement(String str) {
        return this.style.getElement(str);
    }

    @Override
    public String getAttribute(String str) {
        return this.style.getAttribute(str);
    }
}
