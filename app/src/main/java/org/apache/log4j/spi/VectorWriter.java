package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.util.Vector;

class VectorWriter extends PrintWriter {

    private Vector f2803v;

    VectorWriter() {
        super(new NullWriter());
        this.f2803v = new Vector();
    }

    @Override
    public void print(Object obj) {
        this.f2803v.addElement(String.valueOf(obj));
    }

    @Override
    public void print(char[] cArr) {
        this.f2803v.addElement(new String(cArr));
    }

    @Override
    public void print(String str) {
        this.f2803v.addElement(str);
    }

    @Override
    public void println(Object obj) {
        this.f2803v.addElement(String.valueOf(obj));
    }

    @Override
    public void println(char[] cArr) {
        this.f2803v.addElement(new String(cArr));
    }

    @Override
    public void println(String str) {
        this.f2803v.addElement(str);
    }

    @Override
    public void write(char[] cArr) {
        this.f2803v.addElement(new String(cArr));
    }

    @Override
    public void write(char[] cArr, int i, int i2) {
        this.f2803v.addElement(new String(cArr, i, i2));
    }

    @Override
    public void write(String str, int i, int i2) {
        this.f2803v.addElement(str.substring(i, i2 + i));
    }

    @Override
    public void write(String str) {
        this.f2803v.addElement(str);
    }

    public String[] toStringArray() {
        int size = this.f2803v.size();
        String[] strArr = new String[size];
        for (int i = 0; i < size; i++) {
            strArr[i] = (String) this.f2803v.elementAt(i);
        }
        return strArr;
    }
}
