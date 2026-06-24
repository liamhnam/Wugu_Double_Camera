package com.tom_roush.pdfbox.pdmodel.interactive.form;

import com.tom_roush.pdfbox.cos.COSArray;
import com.tom_roush.pdfbox.cos.COSBase;
import com.tom_roush.pdfbox.cos.COSString;
import com.tom_roush.pdfbox.pdmodel.common.COSArrayList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class FieldUtils {

    static class KeyValue {
        private final String key;
        private final String value;

        KeyValue(String str, String str2) {
            this.key = str;
            this.value = str2;
        }

        public String getKey() {
            return this.key;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return "(" + this.key + ", " + this.value + ")";
        }
    }

    static class KeyValueKeyComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = 6715364290007167694L;

        KeyValueKeyComparator() {
        }

        @Override
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.key.compareTo(keyValue2.key);
        }
    }

    static class KeyValueValueComparator implements Serializable, Comparator<KeyValue> {
        private static final long serialVersionUID = -3984095679894798265L;

        KeyValueValueComparator() {
        }

        @Override
        public int compare(KeyValue keyValue, KeyValue keyValue2) {
            return keyValue.value.compareTo(keyValue2.value);
        }
    }

    private FieldUtils() {
    }

    static List<KeyValue> toKeyValueList(List<String> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(new KeyValue(list.get(i), list2.get(i)));
        }
        return arrayList;
    }

    static void sortByValue(List<KeyValue> list) {
        Collections.sort(list, new KeyValueValueComparator());
    }

    static void sortByKey(List<KeyValue> list) {
        Collections.sort(list, new KeyValueKeyComparator());
    }

    static List<String> getPairableItems(COSBase cOSBase, int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("Only 0 and 1 are allowed as an index into two-element arrays");
        }
        if (cOSBase instanceof COSString) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(((COSString) cOSBase).getString());
            return arrayList;
        }
        if (cOSBase instanceof COSArray) {
            COSArray cOSArray = (COSArray) cOSBase;
            if (cOSArray.get(0) instanceof COSString) {
                return COSArrayList.convertCOSStringCOSArrayToList(cOSArray);
            }
            return getItemsFromPair(cOSBase, i);
        }
        return Collections.emptyList();
    }

    private static List<String> getItemsFromPair(COSBase cOSBase, int i) {
        ArrayList arrayList = new ArrayList();
        COSArray cOSArray = (COSArray) cOSBase;
        int size = cOSArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((COSString) ((COSArray) cOSArray.get(i2)).get(i)).getString());
        }
        return arrayList;
    }
}
