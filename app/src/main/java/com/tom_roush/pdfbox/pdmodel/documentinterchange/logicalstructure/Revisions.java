package com.tom_roush.pdfbox.pdmodel.documentinterchange.logicalstructure;

import java.util.ArrayList;
import java.util.List;

public class Revisions<T> {
    private List<T> objects;
    private List<Integer> revisionNumbers;

    private List<T> getObjects() {
        if (this.objects == null) {
            this.objects = new ArrayList();
        }
        return this.objects;
    }

    private List<Integer> getRevisionNumbers() {
        if (this.revisionNumbers == null) {
            this.revisionNumbers = new ArrayList();
        }
        return this.revisionNumbers;
    }

    public T getObject(int i) {
        return getObjects().get(i);
    }

    public int getRevisionNumber(int i) {
        return getRevisionNumbers().get(i).intValue();
    }

    public void addObject(T t, int i) {
        getObjects().add(t);
        getRevisionNumbers().add(Integer.valueOf(i));
    }

    protected void setRevisionNumber(T t, int i) {
        int iIndexOf = getObjects().indexOf(t);
        if (iIndexOf > -1) {
            getRevisionNumbers().set(iIndexOf, Integer.valueOf(i));
        }
    }

    public int size() {
        return getObjects().size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getObjects().size(); i++) {
            if (i > 0) {
                sb.append("; ");
            }
            sb.append("object=").append(getObjects().get(i)).append(", revisionNumber=").append(getRevisionNumber(i));
        }
        return sb.toString();
    }
}
