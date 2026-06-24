package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSNull;
import com.tom_roush.pdfbox.cos.COSNumber;
import com.tom_roush.pdfbox.cos.COSObject;
import com.tom_roush.pdfbox.cos.COSString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class COSArrayList<E> implements List<E> {
    private final List<E> actual;
    private final COSArray array;
    private COSName dictKey;
    private COSDictionary parentDict;

    public COSArrayList() {
        this.array = new COSArray();
        this.actual = new ArrayList();
    }

    public COSArrayList(List<E> list, COSArray cOSArray) {
        this.actual = list;
        this.array = cOSArray;
    }

    public COSArrayList(E e, COSBase cOSBase, COSDictionary cOSDictionary, COSName cOSName) {
        COSArray cOSArray = new COSArray();
        this.array = cOSArray;
        cOSArray.add(cOSBase);
        ArrayList arrayList = new ArrayList();
        this.actual = arrayList;
        arrayList.add(e);
        this.parentDict = cOSDictionary;
        this.dictKey = cOSName;
    }

    @Override
    public int size() {
        return this.actual.size();
    }

    @Override
    public boolean isEmpty() {
        return this.actual.isEmpty();
    }

    @Override
    public boolean contains(Object obj) {
        return this.actual.contains(obj);
    }

    @Override
    public Iterator<E> iterator() {
        return this.actual.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.actual.toArray();
    }

    @Override
    public <X> X[] toArray(X[] xArr) {
        return (X[]) this.actual.toArray(xArr);
    }

    @Override
    public boolean add(E e) {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        if (e instanceof String) {
            this.array.add((COSBase) new COSString((String) e));
        } else {
            COSArray cOSArray = this.array;
            if (cOSArray != null) {
                cOSArray.add(((COSObjectable) e).getCOSObject());
            }
        }
        return this.actual.add(e);
    }

    @Override
    public boolean remove(Object obj) {
        int iIndexOf = this.actual.indexOf(obj);
        if (iIndexOf < 0) {
            return false;
        }
        this.actual.remove(iIndexOf);
        this.array.remove(iIndexOf);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.actual.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (this.parentDict != null && collection.size() > 0) {
            this.parentDict.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        this.array.addAll(toCOSObjectList(collection));
        return this.actual.addAll(collection);
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        if (this.parentDict != null && collection.size() > 0) {
            this.parentDict.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        this.array.addAll(i, toCOSObjectList(collection));
        return this.actual.addAll(i, collection);
    }

    public static List<Integer> convertIntegerCOSArrayToList(COSArray cOSArray) {
        COSBase object;
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            if (cOSArray.get(i) instanceof COSObject) {
                object = ((COSObject) cOSArray.get(i)).getObject();
            } else {
                object = cOSArray.get(i);
            }
            arrayList.add(Integer.valueOf(((COSNumber) object).intValue()));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<Float> convertFloatCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(Float.valueOf(((COSNumber) cOSArray.getObject(i)).floatValue()));
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<String> convertCOSNameCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(((COSName) cOSArray.getObject(i)).getName());
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static List<String> convertCOSStringCOSArrayToList(COSArray cOSArray) {
        if (cOSArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < cOSArray.size(); i++) {
            arrayList.add(((COSString) cOSArray.getObject(i)).getString());
        }
        return new COSArrayList(arrayList, cOSArray);
    }

    public static COSArray convertStringListToCOSNameCOSArray(List<String> list) {
        COSArray cOSArray = new COSArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add((COSBase) COSName.getPDFName(it.next()));
        }
        return cOSArray;
    }

    public static COSArray convertStringListToCOSStringCOSArray(List<String> list) {
        COSArray cOSArray = new COSArray();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            cOSArray.add((COSBase) new COSString(it.next()));
        }
        return cOSArray;
    }

    public static COSArray converterToCOSArray(List<?> list) {
        if (list == null) {
            return null;
        }
        if (list instanceof COSArrayList) {
            return ((COSArrayList) list).array;
        }
        COSArray cOSArray = new COSArray();
        for (Object obj : list) {
            if (obj instanceof String) {
                cOSArray.add((COSBase) new COSString((String) obj));
            } else if ((obj instanceof Integer) || (obj instanceof Long)) {
                cOSArray.add((COSBase) COSInteger.get(((Number) obj).longValue()));
            } else if ((obj instanceof Float) || (obj instanceof Double)) {
                cOSArray.add((COSBase) new COSFloat(((Number) obj).floatValue()));
            } else if (obj instanceof COSObjectable) {
                cOSArray.add(((COSObjectable) obj).getCOSObject());
            } else if (obj == null) {
                cOSArray.add((COSBase) COSNull.NULL);
            } else {
                throw new IllegalArgumentException("Error: Don't know how to convert type to COSBase '" + obj.getClass().getName() + "'");
            }
        }
        return cOSArray;
    }

    private List<COSBase> toCOSObjectList(Collection<?> collection) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : collection) {
            if (obj instanceof String) {
                arrayList.add(new COSString((String) obj));
            } else {
                arrayList.add(((COSObjectable) obj).getCOSObject());
            }
        }
        return arrayList;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        this.array.removeAll(toCOSObjectList(collection));
        return this.actual.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        this.array.retainAll(toCOSObjectList(collection));
        return this.actual.retainAll(collection);
    }

    @Override
    public void clear() {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) null);
        }
        this.actual.clear();
        this.array.clear();
    }

    @Override
    public boolean equals(Object obj) {
        return this.actual.equals(obj);
    }

    @Override
    public int hashCode() {
        return this.actual.hashCode();
    }

    @Override
    public E get(int i) {
        return this.actual.get(i);
    }

    @Override
    public E set(int i, E e) {
        if (e instanceof String) {
            COSString cOSString = new COSString((String) e);
            COSDictionary cOSDictionary = this.parentDict;
            if (cOSDictionary != null && i == 0) {
                cOSDictionary.setItem(this.dictKey, (COSBase) cOSString);
            }
            this.array.set(i, (COSBase) cOSString);
        } else {
            COSDictionary cOSDictionary2 = this.parentDict;
            if (cOSDictionary2 != null && i == 0) {
                cOSDictionary2.setItem(this.dictKey, ((COSObjectable) e).getCOSObject());
            }
            this.array.set(i, ((COSObjectable) e).getCOSObject());
        }
        return this.actual.set(i, e);
    }

    @Override
    public void add(int i, E e) {
        COSDictionary cOSDictionary = this.parentDict;
        if (cOSDictionary != null) {
            cOSDictionary.setItem(this.dictKey, (COSBase) this.array);
            this.parentDict = null;
        }
        this.actual.add(i, e);
        if (e instanceof String) {
            this.array.add(i, new COSString((String) e));
        } else {
            this.array.add(i, ((COSObjectable) e).getCOSObject());
        }
    }

    @Override
    public E remove(int i) {
        this.array.remove(i);
        return this.actual.remove(i);
    }

    @Override
    public int indexOf(Object obj) {
        return this.actual.indexOf(obj);
    }

    @Override
    public int lastIndexOf(Object obj) {
        return this.actual.indexOf(obj);
    }

    @Override
    public ListIterator<E> listIterator() {
        return this.actual.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return this.actual.listIterator(i);
    }

    @Override
    public List<E> subList(int i, int i2) {
        return this.actual.subList(i, i2);
    }

    public String toString() {
        return "COSArrayList{" + this.array.toString() + "}";
    }

    public COSArray toList() {
        return this.array;
    }
}
