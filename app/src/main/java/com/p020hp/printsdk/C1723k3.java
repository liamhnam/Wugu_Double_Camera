package com.p020hp.printsdk;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public final class C1723k3 implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
