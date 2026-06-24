package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class RegistryMatcher implements Matcher {
    private final Cache<Transform> transforms = new ConcurrentCache();
    private final Cache<Class> types = new ConcurrentCache();

    public void bind(Class cls, Class cls2) {
        this.types.cache(cls, cls2);
    }

    public void bind(Class cls, Transform transform) {
        this.transforms.cache(cls, transform);
    }

    @Override
    public Transform match(Class cls) throws Exception {
        Transform transformFetch = this.transforms.fetch(cls);
        return transformFetch == null ? create(cls) : transformFetch;
    }

    private Transform create(Class cls) throws Exception {
        Class clsFetch = this.types.fetch(cls);
        if (clsFetch != null) {
            return create(cls, clsFetch);
        }
        return null;
    }

    private Transform create(Class cls, Class cls2) throws Exception {
        Transform transform = (Transform) cls2.newInstance();
        if (transform != null) {
            this.transforms.cache(cls, transform);
        }
        return transform;
    }
}
