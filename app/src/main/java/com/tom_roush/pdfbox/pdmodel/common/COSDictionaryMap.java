package com.tom_roush.pdfbox.pdmodel.common;

import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSBoolean;
import com.tom_roush.pdfbox.cos.COSDictionary;
import com.tom_roush.pdfbox.cos.COSFloat;
import com.tom_roush.pdfbox.cos.COSInteger;
import com.tom_roush.pdfbox.cos.COSName;
import com.tom_roush.pdfbox.cos.COSString;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class COSDictionaryMap<K, V> implements Map<K, V> {
    private final Map<K, V> actuals;
    private final COSDictionary map;

    public COSDictionaryMap(Map<K, V> map, COSDictionary cOSDictionary) {
        this.actuals = map;
        this.map = cOSDictionary;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean containsKey(Object obj) {
        return this.map.keySet().contains(obj);
    }

    @Override
    public boolean containsValue(Object obj) {
        return this.actuals.containsValue(obj);
    }

    @Override
    public V get(Object obj) {
        return this.actuals.get(obj);
    }

    @Override
    public V put(K k, V v) {
        this.map.setItem(COSName.getPDFName((String) k), ((COSObjectable) v).getCOSObject());
        return this.actuals.put(k, v);
    }

    @Override
    public V remove(Object obj) {
        this.map.removeItem(COSName.getPDFName((String) obj));
        return this.actuals.remove(obj);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void clear() {
        this.map.clear();
        this.actuals.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.actuals.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.actuals.values();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.unmodifiableSet(this.actuals.entrySet());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof COSDictionaryMap) {
            return ((COSDictionaryMap) obj).map.equals(this.map);
        }
        return false;
    }

    public String toString() {
        return this.actuals.toString();
    }

    @Override
    public int hashCode() {
        return this.map.hashCode();
    }

    public static COSDictionary convert(Map<String, ?> map) {
        COSDictionary cOSDictionary = new COSDictionary();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            cOSDictionary.setItem(COSName.getPDFName(entry.getKey()), ((COSObjectable) entry.getValue()).getCOSObject());
        }
        return cOSDictionary;
    }

    public static COSDictionaryMap<String, Object> convertBasicTypesToMap(COSDictionary cOSDictionary) throws IOException {
        Object objValueOf;
        if (cOSDictionary == null) {
            return null;
        }
        HashMap map = new HashMap();
        for (COSName cOSName : cOSDictionary.keySet()) {
            COSBase dictionaryObject = cOSDictionary.getDictionaryObject(cOSName);
            if (dictionaryObject instanceof COSString) {
                objValueOf = ((COSString) dictionaryObject).getString();
            } else if (dictionaryObject instanceof COSInteger) {
                objValueOf = Integer.valueOf(((COSInteger) dictionaryObject).intValue());
            } else if (dictionaryObject instanceof COSName) {
                objValueOf = ((COSName) dictionaryObject).getName();
            } else if (dictionaryObject instanceof COSFloat) {
                objValueOf = Float.valueOf(((COSFloat) dictionaryObject).floatValue());
            } else if (dictionaryObject instanceof COSBoolean) {
                objValueOf = ((COSBoolean) dictionaryObject).getValue() ? Boolean.TRUE : Boolean.FALSE;
            } else {
                throw new IOException("Error:unknown type of object to convert:" + dictionaryObject);
            }
            map.put(cOSName.getName(), objValueOf);
        }
        return new COSDictionaryMap<>(map, cOSDictionary);
    }
}
