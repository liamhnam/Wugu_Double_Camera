package com.tom_roush.pdfbox.util;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.List;

public final class QuickSort {
    private static final Comparator<? extends Comparable> objComp = new Comparator<Comparable>() {
        @Override
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };

    private QuickSort() {
    }

    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return;
        }
        quicksort(list, comparator);
    }

    public static <T extends Comparable> void sort(List<T> list) {
        sort(list, objComp);
    }

    private static <T> void quicksort(List<T> list, Comparator<T> comparator) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(0);
        arrayDeque.push(Integer.valueOf(list.size()));
        while (!arrayDeque.isEmpty()) {
            int iIntValue = ((Integer) arrayDeque.pop()).intValue();
            int iIntValue2 = ((Integer) arrayDeque.pop()).intValue();
            int i = iIntValue - iIntValue2;
            if (i >= 2) {
                int iPartition = partition(list, comparator, (i / 2) + iIntValue2, iIntValue2, iIntValue);
                arrayDeque.push(Integer.valueOf(iPartition + 1));
                arrayDeque.push(Integer.valueOf(iIntValue));
                arrayDeque.push(Integer.valueOf(iIntValue2));
                arrayDeque.push(Integer.valueOf(iPartition));
            }
        }
    }

    private static <T> int partition(List<T> list, Comparator<T> comparator, int i, int i2, int i3) {
        int i4 = i3 - 2;
        T t = list.get(i);
        int i5 = i3 - 1;
        swap(list, i, i5);
        while (i2 < i4) {
            if (comparator.compare(list.get(i2), t) <= 0) {
                i2++;
            } else if (comparator.compare(t, list.get(i4)) <= 0) {
                i4--;
            } else {
                swap(list, i2, i4);
            }
        }
        if (comparator.compare(list.get(i4), t) < 0) {
            i4++;
        }
        swap(list, i5, i4);
        return i4;
    }

    private static <T> void swap(List<T> list, int i, int i2) {
        T t = list.get(i);
        list.set(i, list.get(i2));
        list.set(i2, t);
    }
}
