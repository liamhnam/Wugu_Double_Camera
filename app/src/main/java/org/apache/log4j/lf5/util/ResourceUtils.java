package org.apache.log4j.lf5.util;

import java.io.InputStream;
import java.net.URL;

public class ResourceUtils {
    public static InputStream getResourceAsStream(Object obj, Resource resource) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        if (classLoader != null) {
            return classLoader.getResourceAsStream(resource.getName());
        }
        return ClassLoader.getSystemResourceAsStream(resource.getName());
    }

    public static URL getResourceAsURL(Object obj, Resource resource) {
        ClassLoader classLoader = obj.getClass().getClassLoader();
        if (classLoader != null) {
            return classLoader.getResource(resource.getName());
        }
        return ClassLoader.getSystemResource(resource.getName());
    }
}
