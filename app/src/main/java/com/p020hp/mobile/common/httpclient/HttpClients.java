package com.p020hp.mobile.common.httpclient;

import com.p020hp.mobile.common.CommonLibKt;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.protocol.HTTP;

@Metadata(m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m1293d2 = {"Lcom/hp/mobile/common/httpclient/HttpClients;", "", "()V", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class HttpClients {

    public static final Companion INSTANCE = new Companion(null);
    public static final OkHttpClient lanUnsecureClient;
    public static final OkHttpClient usbClient;
    public static final X509TrustManagerCL x509;

    @Metadata(m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/mobile/common/httpclient/HttpClients$Companion;", "", "()V", "lanUnsecureClient", "Lokhttp3/OkHttpClient;", "getLanUnsecureClient", "()Lokhttp3/OkHttpClient;", "usbClient", "getUsbClient", "x509", "Lcom/hp/mobile/common/httpclient/X509TrustManagerCL;", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OkHttpClient getLanUnsecureClient() {
            return HttpClients.lanUnsecureClient;
        }

        public final OkHttpClient getUsbClient() {
            return HttpClients.usbClient;
        }
    }

    static {
        X509TrustManagerCL x509TrustManagerCL = new X509TrustManagerCL();
        x509 = x509TrustManagerCL;
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().protocols(CollectionsKt.listOf(Protocol.HTTP_1_1)).sslSocketFactory(X509TrustManagerCL.INSTANCE.getSSLFactory(x509TrustManagerCL), x509TrustManagerCL).retryOnConnectionFailure(true).hostnameVerifier(new HostnameVerifier() {
            @Override
            public final boolean verify(String str, SSLSession sSLSession) {
                return HttpClients.m1569lanUnsecureClient$lambda0(str, sSLSession);
            }
        }).build();
        lanUnsecureClient = okHttpClientBuild;
        OkHttpClient.Builder builderAddInterceptor = okHttpClientBuild.newBuilder().socketFactory(CommonLibKt.CommonLib().getServicesBrowser().usbSocketFactory()).addInterceptor(new Interceptor() {
            @Override
            public final Response intercept(Interceptor.Chain chain) {
                return HttpClients.m1570usbClient$lambda1(chain);
            }
        });
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(1);
        dispatcher.setMaxRequestsPerHost(1);
        usbClient = builderAddInterceptor.dispatcher(dispatcher).build();
    }

    public static final boolean m1569lanUnsecureClient$lambda0(String str, SSLSession sSLSession) {
        return true;
    }

    public static final Response m1570usbClient$lambda1(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        return chain.proceed(request.newBuilder().header("Connection", HTTP.CONN_CLOSE).header("User-Agent", "okhttp/4.9.2").header("Host", "localhost:" + request.url().port()).build());
    }
}
