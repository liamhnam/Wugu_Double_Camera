package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class DTD implements DTDOutput {
    public DTDElement rootElement;
    public Hashtable elements = new Hashtable();
    public Hashtable entities = new Hashtable();
    public Hashtable notations = new Hashtable();
    public Hashtable externalDTDs = new Hashtable();
    public Vector items = new Vector();

    @Override
    public void write(PrintWriter printWriter) throws IOException {
        Enumeration enumerationElements = this.items.elements();
        while (enumerationElements.hasMoreElements()) {
            ((DTDOutput) enumerationElements.nextElement()).write(printWriter);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DTD) {
            return this.items.equals(((DTD) obj).items);
        }
        return false;
    }

    public void setItems(Object[] objArr) {
        this.items = new Vector(objArr.length);
        for (Object obj : objArr) {
            this.items.addElement(obj);
        }
    }

    public Object[] getItems() {
        return this.items.toArray();
    }

    public void setItem(Object obj, int i) {
        this.items.setElementAt(obj, i);
    }

    public Object getItem(int i) {
        return this.items.elementAt(i);
    }

    public Vector getItemsByType(Class cls) {
        Vector vector = new Vector();
        Enumeration enumerationElements = this.items.elements();
        while (enumerationElements.hasMoreElements()) {
            Object objNextElement = enumerationElements.nextElement();
            if (cls.isAssignableFrom(objNextElement.getClass())) {
                vector.addElement(objNextElement);
            }
        }
        return vector;
    }
}
