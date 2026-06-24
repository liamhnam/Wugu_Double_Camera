package com.p020hp.printsdk;

import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public final class C1685d4 {

    public static final C1685d4 f1173a = new C1685d4();

    public static final C1723k3 f1174b;

    public static final Retrofit f1175c;

    public static final boolean m489a(String str, SSLSession sSLSession) {
        return true;
    }

    public final InterfaceC1742o2 m490a() {
        return (InterfaceC1742o2) f1175c.create(InterfaceC1742o2.class);
    }

    public final InterfaceC1667a4 m491b() {
        return (InterfaceC1667a4) f1175c.create(InterfaceC1667a4.class);
    }

    static {
        SSLContext sSLContext;
        Object objM1772constructorimpl;
        C1723k3 c1723k3 = new C1723k3();
        f1174b = c1723k3;
        Retrofit.Builder builder = new Retrofit.Builder();
        OkHttpClient.Builder builderRetryOnConnectionFailure = new OkHttpClient.Builder().readTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).connectTimeout(10L, TimeUnit.SECONDS).retryOnConnectionFailure(true);
        int i = 0;
        TrustManager[] trustManagerArr = {c1723k3};
        Provider[] providers = Security.getProviders();
        Intrinsics.checkNotNullExpressionValue(providers, "getProviders()");
        int length = providers.length;
        while (true) {
            if (i >= length) {
                sSLContext = null;
                break;
            }
            try {
                objM1772constructorimpl = Result.m1772constructorimpl(SSLContext.getInstance("TLSv1.3", providers[i]));
            } catch (Throwable th) {
                objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m1778isFailureimpl(objM1772constructorimpl)) {
                objM1772constructorimpl = null;
            }
            sSLContext = (SSLContext) objM1772constructorimpl;
            if (sSLContext != null) {
                break;
            } else {
                i++;
            }
        }
        if (sSLContext == null) {
            sSLContext = SSLContext.getInstance("TLSv1.2");
            Intrinsics.checkNotNullExpressionValue(sSLContext, "getInstance(\"TLSv1.2\")");
        }
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(socketFactory, "sslContext.socketFactory");
        f1175c = builder.client(builderRetryOnConnectionFailure.sslSocketFactory(socketFactory, f1174b).hostnameVerifier(new HostnameVerifier() {
            @Override
            public final boolean verify(String str, SSLSession sSLSession) {
                return C1685d4.m489a(str, sSLSession);
            }
        }).build()).baseUrl("http://127.0.0.1").addConverterFactory(new C1738n3()).build();
    }
}
