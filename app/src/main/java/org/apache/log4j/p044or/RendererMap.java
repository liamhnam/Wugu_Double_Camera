package org.apache.log4j.p044or;

import java.util.Hashtable;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.RendererSupport;

public class RendererMap {
    static Class class$org$apache$log4j$or$ObjectRenderer;
    static ObjectRenderer defaultRenderer = new DefaultRenderer();
    Hashtable map = new Hashtable();

    public static void addRenderer(RendererSupport rendererSupport, String str, String str2) throws Throwable {
        LogLog.debug(new StringBuffer("Rendering class: [").append(str2).append("], Rendered class: [").append(str).append("].").toString());
        Class clsClass$ = class$org$apache$log4j$or$ObjectRenderer;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.or.ObjectRenderer");
            class$org$apache$log4j$or$ObjectRenderer = clsClass$;
        }
        ObjectRenderer objectRenderer = (ObjectRenderer) OptionConverter.instantiateByClassName(str2, clsClass$, null);
        if (objectRenderer == null) {
            LogLog.error(new StringBuffer("Could not instantiate renderer [").append(str2).append("].").toString());
            return;
        }
        try {
            rendererSupport.setRenderer(Loader.loadClass(str), objectRenderer);
        } catch (ClassNotFoundException e) {
            LogLog.error(new StringBuffer("Could not find class [").append(str).append("].").toString(), e);
        }
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public String findAndRender(Object obj) {
        if (obj == null) {
            return null;
        }
        return get((Class) obj.getClass()).doRender(obj);
    }

    public ObjectRenderer get(Object obj) {
        if (obj == null) {
            return null;
        }
        return get((Class) obj.getClass());
    }

    public ObjectRenderer get(Class cls) {
        while (cls != null) {
            ObjectRenderer objectRenderer = (ObjectRenderer) this.map.get(cls);
            if (objectRenderer != null) {
                return objectRenderer;
            }
            ObjectRenderer objectRendererSearchInterfaces = searchInterfaces(cls);
            if (objectRendererSearchInterfaces != null) {
                return objectRendererSearchInterfaces;
            }
            cls = cls.getSuperclass();
        }
        return defaultRenderer;
    }

    ObjectRenderer searchInterfaces(Class cls) {
        ObjectRenderer objectRenderer = (ObjectRenderer) this.map.get(cls);
        if (objectRenderer != null) {
            return objectRenderer;
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            ObjectRenderer objectRendererSearchInterfaces = searchInterfaces(cls2);
            if (objectRendererSearchInterfaces != null) {
                return objectRendererSearchInterfaces;
            }
        }
        return null;
    }

    public ObjectRenderer getDefaultRenderer() {
        return defaultRenderer;
    }

    public void clear() {
        this.map.clear();
    }

    public void put(Class cls, ObjectRenderer objectRenderer) {
        this.map.put(cls, objectRenderer);
    }
}
