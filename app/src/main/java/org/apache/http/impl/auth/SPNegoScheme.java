package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.Oid;

public class SPNegoScheme extends GGSSchemeBase {
    private static final String SPNEGO_OID = "1.3.6.1.5.5.2";

    public SPNegoScheme() {
        super(false);
    }

    public SPNegoScheme(boolean z) {
        super(z);
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return super.authenticate(credentials, httpRequest, httpContext);
    }

    @Override
    protected byte[] generateToken(byte[] bArr, String str) {
        return generateGSSToken(bArr, new Oid(SPNEGO_OID), str);
    }

    @Override
    public String getParameter(String str) {
        Args.notNull(str, "Parameter name");
        return null;
    }

    @Override
    public String getRealm() {
        return null;
    }

    @Override
    public String getSchemeName() {
        return "Negotiate";
    }

    @Override
    public boolean isConnectionBased() {
        return true;
    }
}
