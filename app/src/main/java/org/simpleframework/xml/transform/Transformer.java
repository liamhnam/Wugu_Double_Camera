package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class Transformer {
    private final Cache<Transform> cache = new ConcurrentCache();
    private final Cache<Object> error = new ConcurrentCache();
    private final Matcher matcher;

    public Transformer(Matcher matcher) {
        this.matcher = new DefaultMatcher(matcher);
    }

    public Object read(String str, Class cls) throws Exception {
        Transform transformLookup = lookup(cls);
        if (transformLookup == null) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return transformLookup.read(str);
    }

    public String write(Object obj, Class cls) throws Exception {
        Transform transformLookup = lookup(cls);
        if (transformLookup == null) {
            throw new TransformException("Transform of %s not supported", cls);
        }
        return transformLookup.write(obj);
    }

    public boolean valid(Class cls) throws Exception {
        return lookup(cls) != null;
    }

    private Transform lookup(Class cls) throws Exception {
        if (this.error.contains(cls)) {
            return null;
        }
        Transform transformFetch = this.cache.fetch(cls);
        return transformFetch != null ? transformFetch : match(cls);
    }

    private Transform match(Class cls) throws Exception {
        Transform transformMatch = this.matcher.match(cls);
        if (transformMatch != null) {
            this.cache.cache(cls, transformMatch);
        } else {
            this.error.cache(cls, this);
        }
        return transformMatch;
    }
}
