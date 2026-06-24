package org.apache.http.impl.client;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.util.Args;

public class SystemDefaultCredentialsProvider implements CredentialsProvider {
    private static final Map<String, String> SCHEME_MAP;
    private final BasicCredentialsProvider internal = new BasicCredentialsProvider();

    static {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        SCHEME_MAP = concurrentHashMap;
        concurrentHashMap.put("Basic".toUpperCase(Locale.ENGLISH), "Basic");
        concurrentHashMap.put("Digest".toUpperCase(Locale.ENGLISH), "Digest");
        concurrentHashMap.put("NTLM".toUpperCase(Locale.ENGLISH), "NTLM");
        concurrentHashMap.put("negotiate".toUpperCase(Locale.ENGLISH), "SPNEGO");
        concurrentHashMap.put("Kerberos".toUpperCase(Locale.ENGLISH), "Kerberos");
    }

    private static String translateScheme(String str) {
        if (str == null) {
            return null;
        }
        String str2 = SCHEME_MAP.get(str);
        return str2 != null ? str2 : str;
    }

    @Override
    public void clear() {
        this.internal.clear();
    }

    @Override
    public Credentials getCredentials(AuthScope authScope) {
        PasswordAuthentication passwordAuthenticationRequestPasswordAuthentication;
        Args.notNull(authScope, "Auth scope");
        Credentials credentials = this.internal.getCredentials(authScope);
        if (credentials != null) {
            return credentials;
        }
        if (authScope.getHost() == null || (passwordAuthenticationRequestPasswordAuthentication = Authenticator.requestPasswordAuthentication(authScope.getHost(), null, authScope.getPort(), HttpHost.DEFAULT_SCHEME_NAME, null, translateScheme(authScope.getScheme()))) == null) {
            return null;
        }
        return new UsernamePasswordCredentials(passwordAuthenticationRequestPasswordAuthentication.getUserName(), new String(passwordAuthenticationRequestPasswordAuthentication.getPassword()));
    }

    @Override
    public void setCredentials(AuthScope authScope, Credentials credentials) {
        this.internal.setCredentials(authScope, credentials);
    }
}
