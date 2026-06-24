package org.apache.log4j.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.Set;
import org.apache.log4j.pattern.LogEvent;
import org.apache.log4j.spi.LoggingEvent;

public final class MDCKeySetExtractor {
    public static final MDCKeySetExtractor INSTANCE = new MDCKeySetExtractor();
    static Class class$org$apache$log4j$pattern$LogEvent;
    static Class class$org$apache$log4j$spi$LoggingEvent;
    private final Method getKeySetMethod;

    private MDCKeySetExtractor() throws Throwable {
        Method method = null;
        try {
            Class clsClass$ = class$org$apache$log4j$spi$LoggingEvent;
            if (clsClass$ == null) {
                clsClass$ = class$("org.apache.log4j.spi.LoggingEvent");
                class$org$apache$log4j$spi$LoggingEvent = clsClass$;
            }
            method = clsClass$.getMethod("getPropertyKeySet", null);
        } catch (Exception unused) {
        }
        this.getKeySetMethod = method;
    }

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError().initCause(e);
        }
    }

    public Set getPropertyKeySet(LoggingEvent loggingEvent) throws Exception {
        Method method = this.getKeySetMethod;
        if (method != null) {
            return (Set) method.invoke(loggingEvent, null);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(loggingEvent);
        objectOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Class clsClass$ = class$org$apache$log4j$pattern$LogEvent;
        if (clsClass$ == null) {
            clsClass$ = class$("org.apache.log4j.pattern.LogEvent");
            class$org$apache$log4j$pattern$LogEvent = clsClass$;
        }
        String name = clsClass$.getName();
        if (byteArray[6] != 0 && byteArray[7] != name.length()) {
            return null;
        }
        for (int i = 0; i < name.length(); i++) {
            byteArray[i + 8] = (byte) name.charAt(i);
        }
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArray));
        Object object = objectInputStream.readObject();
        Set propertyKeySet = object instanceof LogEvent ? ((LogEvent) object).getPropertyKeySet() : null;
        objectInputStream.close();
        return propertyKeySet;
    }
}
