package com.printer.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class C1838c {
    public static <T extends Serializable> T m747a(T t) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(t);
            return (T) new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static List<PrintOrder> m748a(List<PrintOrder> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<PrintOrder> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(m747a(it.next()));
        }
        return arrayList;
    }

    public static List<PrintDetail> m749b(List<PrintDetail> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<PrintDetail> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(m747a(it.next()));
        }
        return arrayList;
    }
}
