package org.apache.http.impl.auth;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public class SPNegoSchemeFactory implements AuthSchemeFactory, AuthSchemeProvider {
    private final boolean stripPort;

    public SPNegoSchemeFactory() {
        this(false);
    }

    public SPNegoSchemeFactory(boolean z) {
        this.stripPort = z;
    }

    @Override
    public AuthScheme create(HttpContext httpContext) {
        return new SPNegoScheme(this.stripPort);
    }

    public boolean isStripPort() {
        return this.stripPort;
    }

    @Override
    public AuthScheme newInstance(HttpParams httpParams) {
        return new SPNegoScheme(this.stripPort);
    }
}
