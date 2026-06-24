package com.wutka.dtd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

public class DTDNotationList implements DTDOutput {
    protected Vector items = new Vector();

    public void add(String str) {
        this.items.addElement(str);
    }

    public void remove(String str) {
        this.items.removeElement(str);
    }

    public String[] getItems() {
        String[] strArr = new String[this.items.size()];
        this.items.copyInto(strArr);
        return strArr;
    }

    public Vector getItemsVec() {
        return this.items;
    }

    @Override
    public void write(PrintWriter printWriter) throws IOException {
        printWriter.print("NOTATION ( ");
        Enumeration enumerationElements = getItemsVec().elements();
        boolean z = true;
        while (enumerationElements.hasMoreElements()) {
            if (!z) {
                printWriter.print(" | ");
            }
            printWriter.print(enumerationElements.nextElement());
            z = false;
        }
        printWriter.print(")");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DTDNotationList) {
            return this.items.equals(((DTDNotationList) obj).items);
        }
        return false;
    }

    public String[] getItem() {
        return getItems();
    }

    public void setItem(String[] strArr) {
        this.items = new Vector(strArr.length);
        for (String str : strArr) {
            this.items.addElement(str);
        }
    }

    public void setItem(String str, int i) {
        this.items.setElementAt(str, i);
    }

    public String getItem(int i) {
        return (String) this.items.elementAt(i);
    }
}
