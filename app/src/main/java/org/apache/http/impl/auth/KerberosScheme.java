package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.Oid;

public class KerberosScheme extends GGSSchemeBase {
    private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";

    public KerberosScheme() {
        super(false);
    }

    public KerberosScheme(boolean z) {
        super(z);
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return super.authenticate(credentials, httpRequest, httpContext);
    }

    @Override
    protected byte[] generateToken(byte[] bArr, String str) {
        return generateGSSToken(bArr, new Oid(KERBEROS_OID), str);
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
        return "Kerberos";
    }

    @Override
    public boolean isConnectionBased() {
        return true;
    }
}
