package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class SortedKeyEnumeration implements Enumeration {

    private Enumeration f2786e;

    public SortedKeyEnumeration(Hashtable hashtable) {
        Enumeration enumerationKeys = hashtable.keys();
        Vector vector = new Vector(hashtable.size());
        int i = 0;
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            int i2 = 0;
            while (i2 < i && str.compareTo((String) vector.get(i2)) > 0) {
                i2++;
            }
            vector.add(i2, str);
            i++;
        }
        this.f2786e = vector.elements();
    }

    @Override
    public boolean hasMoreElements() {
        return this.f2786e.hasMoreElements();
    }

    @Override
    public Object nextElement() {
        return this.f2786e.nextElement();
    }
}
