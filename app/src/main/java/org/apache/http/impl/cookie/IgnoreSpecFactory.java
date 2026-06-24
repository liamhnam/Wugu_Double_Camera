package org.apache.http.impl.cookie;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class IgnoreSpecFactory implements CookieSpecFactory, CookieSpecProvider {
    @Override
    public CookieSpec create(HttpContext httpContext) {
        return new IgnoreSpec();
    }

    @Override
    public CookieSpec newInstance(HttpParams httpParams) {
        return new IgnoreSpec();
    }
}
