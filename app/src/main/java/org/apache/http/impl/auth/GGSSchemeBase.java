package org.apache.http.impl.auth;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.InvalidCredentialsException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.ietf.jgss.GSSContext;
import org.ietf.jgss.GSSCredential;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.GSSManager;
import org.ietf.jgss.GSSName;
import org.ietf.jgss.Oid;

public abstract class GGSSchemeBase extends AuthSchemeBase {
    private final Base64 base64codec;
    private final Log log;
    private State state;
    private final boolean stripPort;
    private byte[] token;

    static class C28101 {
        static final int[] $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State = iArr;
            try {
                iArr[State.UNINITIATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.CHALLENGE_RECEIVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[State.TOKEN_GENERATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    enum State {
        UNINITIATED,
        CHALLENGE_RECEIVED,
        TOKEN_GENERATED,
        FAILED
    }

    GGSSchemeBase() {
        this(false);
    }

    GGSSchemeBase(boolean z) {
        this.log = LogFactory.getLog(getClass());
        this.base64codec = new Base64(0);
        this.stripPort = z;
        this.state = State.UNINITIATED;
    }

    @Override
    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, null);
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) throws AuthenticationException {
        HttpHost targetHost;
        Args.notNull(httpRequest, "HTTP request");
        int i = C28101.$SwitchMap$org$apache$http$impl$auth$GGSSchemeBase$State[this.state.ordinal()];
        if (i == 1) {
            throw new AuthenticationException(getSchemeName() + " authentication has not been initiated");
        }
        if (i == 2) {
            throw new AuthenticationException(getSchemeName() + " authentication has failed");
        }
        if (i == 3) {
            try {
                HttpRoute httpRoute = (HttpRoute) httpContext.getAttribute("http.route");
                if (httpRoute == null) {
                    throw new AuthenticationException("Connection route is not available");
                }
                if (!isProxy() || (targetHost = httpRoute.getProxyHost()) == null) {
                    targetHost = httpRoute.getTargetHost();
                }
                String hostName = (this.stripPort || targetHost.getPort() <= 0) ? targetHost.getHostName() : targetHost.toHostString();
                if (this.log.isDebugEnabled()) {
                    this.log.debug("init " + hostName);
                }
                this.token = generateToken(this.token, hostName);
                this.state = State.TOKEN_GENERATED;
            } catch (GSSException e) {
                this.state = State.FAILED;
                if (e.getMajor() == 9 || e.getMajor() == 8) {
                    throw new InvalidCredentialsException(e.getMessage(), e);
                }
                if (e.getMajor() == 13) {
                    throw new InvalidCredentialsException(e.getMessage(), e);
                }
                if (e.getMajor() == 10 || e.getMajor() == 19 || e.getMajor() == 20) {
                    throw new AuthenticationException(e.getMessage(), e);
                }
                throw new AuthenticationException(e.getMessage());
            }
        } else if (i != 4) {
            throw new IllegalStateException("Illegal state: " + this.state);
        }
        String str = new String(this.base64codec.encode(this.token));
        if (this.log.isDebugEnabled()) {
            this.log.debug("Sending response '" + str + "' back to the auth server");
        }
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        charArrayBuffer.append(isProxy() ? "Proxy-Authorization" : "Authorization");
        charArrayBuffer.append(": Negotiate ");
        charArrayBuffer.append(str);
        return new BufferedHeader(charArrayBuffer);
    }

    protected byte[] generateGSSToken(byte[] bArr, Oid oid, String str) {
        if (bArr == null) {
            bArr = new byte[0];
        }
        GSSManager manager = getManager();
        GSSContext gSSContextCreateContext = manager.createContext(manager.createName("HTTP@" + str, GSSName.NT_HOSTBASED_SERVICE).canonicalize(oid), oid, (GSSCredential) null, 0);
        gSSContextCreateContext.requestMutualAuth(true);
        gSSContextCreateContext.requestCredDeleg(true);
        return gSSContextCreateContext.initSecContext(bArr, 0, bArr.length);
    }

    protected abstract byte[] generateToken(byte[] bArr, String str);

    protected GSSManager getManager() {
        return GSSManager.getInstance();
    }

    @Override
    public boolean isComplete() {
        State state = this.state;
        return state == State.TOKEN_GENERATED || state == State.FAILED;
    }

    @Override
    protected void parseChallenge(CharArrayBuffer charArrayBuffer, int i, int i2) {
        State state;
        String strSubstringTrimmed = charArrayBuffer.substringTrimmed(i, i2);
        if (this.log.isDebugEnabled()) {
            this.log.debug("Received challenge '" + strSubstringTrimmed + "' from the auth server");
        }
        if (this.state == State.UNINITIATED) {
            this.token = Base64.decodeBase64(strSubstringTrimmed.getBytes());
            state = State.CHALLENGE_RECEIVED;
        } else {
            this.log.debug("Authentication already attempted");
            state = State.FAILED;
        }
        this.state = state;
    }
}
