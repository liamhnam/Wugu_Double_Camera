package org.apache.http.protocol;

import java.util.HashMap;
import java.util.Map;
import org.apache.http.util.Args;

public class BasicHttpContext implements HttpContext {
    private Map<String, Object> map;
    private final HttpContext parentContext;

    public BasicHttpContext() {
        this(null);
    }

    public BasicHttpContext(HttpContext httpContext) {
        this.map = null;
        this.parentContext = httpContext;
    }

    public void clear() {
        Map<String, Object> map = this.map;
        if (map != null) {
            map.clear();
        }
    }

    @Override
    public Object getAttribute(String str) {
        HttpContext httpContext;
        Args.notNull(str, "Id");
        Map<String, Object> map = this.map;
        Object obj = map != null ? map.get(str) : null;
        return (obj != null || (httpContext = this.parentContext) == null) ? obj : httpContext.getAttribute(str);
    }

    @Override
    public Object removeAttribute(String str) {
        Args.notNull(str, "Id");
        Map<String, Object> map = this.map;
        if (map != null) {
            return map.remove(str);
        }
        return null;
    }

    @Override
    public void setAttribute(String str, Object obj) {
        Args.notNull(str, "Id");
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(str, obj);
    }

    public String toString() {
        Map<String, Object> map = this.map;
        return map != null ? map.toString() : "{}";
    }
}
