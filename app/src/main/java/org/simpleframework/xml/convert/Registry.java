package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class Registry {
    private final Cache<Converter> cache = new ConcurrentCache();
    private final RegistryBinder binder = new RegistryBinder();

    public Converter lookup(Class cls) throws Exception {
        Converter converterFetch = this.cache.fetch(cls);
        return converterFetch == null ? create(cls) : converterFetch;
    }

    private Converter create(Class cls) throws Exception {
        Converter converterLookup = this.binder.lookup(cls);
        if (converterLookup != null) {
            this.cache.cache(cls, converterLookup);
        }
        return converterLookup;
    }

    public Registry bind(Class cls, Class cls2) throws Exception {
        if (cls != null) {
            this.binder.bind(cls, cls2);
        }
        return this;
    }

    public Registry bind(Class cls, Converter converter) throws Exception {
        if (cls != null) {
            this.cache.cache(cls, converter);
        }
        return this;
    }
}
