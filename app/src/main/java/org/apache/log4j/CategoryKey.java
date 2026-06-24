package org.apache.log4j;

class CategoryKey {
    static Class class$org$apache$log4j$CategoryKey;
    int hashCache;
    String name;

    CategoryKey(String str) {
        this.name = str;
        this.hashCache = str.hashCode();
    }

    public final int hashCode() {
        return this.hashCache;
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public final boolean equals(Object obj) throws Throwable {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        Class<?> clsClass$ = class$org$apache$log4j$CategoryKey;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.CategoryKey");
            class$org$apache$log4j$CategoryKey = clsClass$;
        }
        if (clsClass$ == obj.getClass()) {
            return this.name.equals(((CategoryKey) obj).name);
        }
        return false;
    }
}
