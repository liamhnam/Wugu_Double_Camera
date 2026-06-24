package com.p020hp.mobile.common.httpclient;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0001\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0017¢\u0006\u0002\u0010\nJ)\u0010\u000b\u001a\u00020\u00042\u0010\u0010\u0005\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0017¢\u0006\u0002\u0010\nJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0002\u0010\r¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/mobile/common/httpclient/X509TrustManagerCL;", "Ljavax/net/ssl/X509TrustManager;", "()V", "checkClientTrusted", "", "chain", "", "Ljava/security/cert/X509Certificate;", "authType", "", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;)V", "checkServerTrusted", "getAcceptedIssuers", "()[Ljava/security/cert/X509Certificate;", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class X509TrustManagerCL implements X509TrustManager {

    public static final Companion INSTANCE = new Companion(null);

    @Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, m1293d2 = {"Lcom/hp/mobile/common/httpclient/X509TrustManagerCL$Companion;", "", "()V", "getSSLFactory", "Ljavax/net/ssl/SSLSocketFactory;", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "getSSLSocketContext", "Ljavax/net/ssl/SSLContext;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final SSLContext getSSLSocketContext() throws NoSuchAlgorithmException {
            SSLContext sSLContext;
            Object objM1772constructorimpl;
            Provider[] providers = Security.getProviders();
            Intrinsics.checkNotNullExpressionValue(providers, "getProviders()");
            int length = providers.length;
            int i = 0;
            while (true) {
                sSLContext = null;
                if (i >= length) {
                    break;
                }
                try {
                    objM1772constructorimpl = Result.m1772constructorimpl(SSLContext.getInstance("TLSv1.3", providers[i]));
                } catch (Throwable th) {
                    objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
                }
                sSLContext = (SSLContext) (Result.m1778isFailureimpl(objM1772constructorimpl) ? null : objM1772constructorimpl);
                if (sSLContext != null) {
                    break;
                }
                i++;
            }
            if (sSLContext != null) {
                return sSLContext;
            }
            SSLContext sSLContext2 = SSLContext.getInstance("TLSv1.2");
            Intrinsics.checkNotNullExpressionValue(sSLContext2, "getInstance(\"TLSv1.2\")");
            return sSLContext2;
        }

        public final SSLSocketFactory getSSLFactory(X509TrustManager x509TrustManager) throws NoSuchAlgorithmException, KeyManagementException {
            Intrinsics.checkNotNullParameter(x509TrustManager, "x509TrustManager");
            SSLContext sSLSocketContext = getSSLSocketContext();
            sSLSocketContext.init(null, new X509TrustManager[]{x509TrustManager}, new SecureRandom());
            SSLSocketFactory socketFactory = sSLSocketContext.getSocketFactory();
            Intrinsics.checkNotNullExpressionValue(socketFactory, "getSSLSocketContext().ap…          }.socketFactory");
            return socketFactory;
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
