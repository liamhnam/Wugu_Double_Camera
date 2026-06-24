package org.apache.http.impl.auth;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.apache.log4j.spi.Configurator;

public class BasicScheme extends RFC2617Scheme {
    private final Base64 base64codec;
    private boolean complete;

    public BasicScheme() {
        this(Consts.ASCII);
    }

    public BasicScheme(Charset charset) {
        super(charset);
        this.base64codec = new Base64(0);
        this.complete = false;
    }

    @Deprecated
    public BasicScheme(ChallengeState challengeState) {
        super(challengeState);
        this.base64codec = new Base64(0);
    }

    @Deprecated
    public static Header authenticate(Credentials credentials, String str, boolean z) {
        Args.notNull(credentials, "Credentials");
        Args.notNull(str, "charset");
        StringBuilder sb = new StringBuilder();
        sb.append(credentials.getUserPrincipal().getName());
        sb.append(":");
        sb.append(credentials.getPassword() == null ? Configurator.NULL : credentials.getPassword());
        byte[] bArrEncodeBase64 = Base64.encodeBase64(EncodingUtils.getBytes(sb.toString(), str), false);
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        charArrayBuffer.append(z ? "Proxy-Authorization" : "Authorization");
        charArrayBuffer.append(": Basic ");
        charArrayBuffer.append(bArrEncodeBase64, 0, bArrEncodeBase64.length);
        return new BufferedHeader(charArrayBuffer);
    }

    @Override
    @Deprecated
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, new BasicHttpContext());
    }

    @Override
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        Args.notNull(credentials, "Credentials");
        Args.notNull(httpRequest, "HTTP request");
        StringBuilder sb = new StringBuilder();
        sb.append(credentials.getUserPrincipal().getName());
        sb.append(":");
        sb.append(credentials.getPassword() == null ? Configurator.NULL : credentials.getPassword());
        byte[] bArrEncode = this.base64codec.encode(EncodingUtils.getBytes(sb.toString(), getCredentialsCharset(httpRequest)));
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(32);
        charArrayBuffer.append(isProxy() ? "Proxy-Authorization" : "Authorization");
        charArrayBuffer.append(": Basic ");
        charArrayBuffer.append(bArrEncode, 0, bArrEncode.length);
        return new BufferedHeader(charArrayBuffer);
    }

    @Override
    public String getSchemeName() {
        return "basic";
    }

    @Override
    public boolean isComplete() {
        return this.complete;
    }

    @Override
    public boolean isConnectionBased() {
        return false;
    }

    @Override
    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        this.complete = true;
    }
}
