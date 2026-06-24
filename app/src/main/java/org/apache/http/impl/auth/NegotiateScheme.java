package org.apache.http.impl.auth;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

@Deprecated
public class NegotiateScheme extends GGSSchemeBase {
    private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";
    private static final String SPNEGO_OID = "1.3.6.1.5.5.2";
    private final Log log;
    private final SpnegoTokenGenerator spengoGenerator;

    public NegotiateScheme() {
        this(null, false);
    }

    public NegotiateScheme(SpnegoTokenGenerator spnegoTokenGenerator) {
        this(spnegoTokenGenerator, false);
    }

    public NegotiateScheme(SpnegoTokenGenerator spnegoTokenGenerator, boolean z) {
        super(z);
        this.log = LogFactory.getLog(NegotiateScheme.class);
        this.spengoGenerator = spnegoTokenGenerator;
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, null);
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return super.authenticate(credentials, httpRequest, httpContext);
    }

    @Override
    protected byte[] generateToken(byte[] bArr, String str) throws GSSException {
        boolean z;
        SpnegoTokenGenerator spnegoTokenGenerator;
        try {
            bArr = generateGSSToken(bArr, new Oid(SPNEGO_OID), str);
            z = false;
        } catch (GSSException e) {
            if (e.getMajor() != 2) {
                throw e;
            }
            this.log.debug("GSSException BAD_MECH, retry with Kerberos MECH");
            z = true;
        }
        if (!z) {
            return bArr;
        }
        this.log.debug("Using Kerberos MECH 1.2.840.113554.1.2.2");
        byte[] bArrGenerateGSSToken = generateGSSToken(bArr, new Oid(KERBEROS_OID), str);
        if (bArrGenerateGSSToken == null || (spnegoTokenGenerator = this.spengoGenerator) == null) {
            return bArrGenerateGSSToken;
        }
        try {
            return spnegoTokenGenerator.generateSpnegoDERObject(bArrGenerateGSSToken);
        } catch (IOException e2) {
            this.log.error(e2.getMessage(), e2);
            return bArrGenerateGSSToken;
        }
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
