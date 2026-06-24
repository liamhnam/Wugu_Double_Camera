package org.apache.log4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.ThreadLocalMap;

public class MDC {
    static final int HT_SIZE = 7;
    static Class class$java$lang$ThreadLocal;
    static final MDC mdc = new MDC();
    boolean java1;
    private Method removeMethod;
    Object tlm;

    private MDC() throws Throwable {
        boolean zIsJava1 = Loader.isJava1();
        this.java1 = zIsJava1;
        if (!zIsJava1) {
            this.tlm = new ThreadLocalMap();
        }
        try {
            Class clsClass$ = class$java$lang$ThreadLocal;
            if (clsClass$ == null) {
                clsClass$ = class$("java.lang.ThreadLocal");
                class$java$lang$ThreadLocal = clsClass$;
            }
            this.removeMethod = clsClass$.getMethod("remove", null);
        } catch (NoSuchMethodException unused) {
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public static void put(String str, Object obj) {
        MDC mdc2 = mdc;
        if (mdc2 != null) {
            mdc2.put0(str, obj);
        }
    }

    public static Object get(String str) {
        MDC mdc2 = mdc;
        if (mdc2 != null) {
            return mdc2.get0(str);
        }
        return null;
    }

    public static void remove(String str) {
        MDC mdc2 = mdc;
        if (mdc2 != null) {
            mdc2.remove0(str);
        }
    }

    public static Hashtable getContext() {
        MDC mdc2 = mdc;
        if (mdc2 != null) {
            return mdc2.getContext0();
        }
        return null;
    }

    public static void clear() {
        MDC mdc2 = mdc;
        if (mdc2 != null) {
            mdc2.clear0();
        }
    }

    private void put0(String str, Object obj) {
        Object obj2;
        if (this.java1 || (obj2 = this.tlm) == null) {
            return;
        }
        Hashtable hashtable = (Hashtable) ((ThreadLocalMap) obj2).get();
        if (hashtable == null) {
            hashtable = new Hashtable(7);
            ((ThreadLocalMap) this.tlm).set(hashtable);
        }
        hashtable.put(str, obj);
    }

    private Object get0(String str) {
        Object obj;
        Hashtable hashtable;
        if (this.java1 || (obj = this.tlm) == null || (hashtable = (Hashtable) ((ThreadLocalMap) obj).get()) == null || str == null) {
            return null;
        }
        return hashtable.get(str);
    }

    private void remove0(String str) {
        Object obj;
        Hashtable hashtable;
        if (this.java1 || (obj = this.tlm) == null || (hashtable = (Hashtable) ((ThreadLocalMap) obj).get()) == null) {
            return;
        }
        hashtable.remove(str);
        if (hashtable.isEmpty()) {
            clear0();
        }
    }

    private Hashtable getContext0() {
        Object obj;
        if (this.java1 || (obj = this.tlm) == null) {
            return null;
        }
        return (Hashtable) ((ThreadLocalMap) obj).get();
    }

    private void clear0() {
        Object obj;
        if (this.java1 || (obj = this.tlm) == null) {
            return;
        }
        Hashtable hashtable = (Hashtable) ((ThreadLocalMap) obj).get();
        if (hashtable != null) {
            hashtable.clear();
        }
        Method method = this.removeMethod;
        if (method != null) {
            try {
                method.invoke(this.tlm, null);
            } catch (IllegalAccessException | InvocationTargetException unused) {
            }
        }
    }
}
