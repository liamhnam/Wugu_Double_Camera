package com.p020hp.printsdk;

import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.p020hp.mobile.common.browsing.ConnectionType;
import com.p020hp.mobile.common.browsing.ConnectionTypeKt;
import com.p020hp.mobile.common.httpclient.HttpClients;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.p037io.CloseableKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;

public final class C1709i implements InterfaceC1693f {

    public static final b f1311d;

    public static final Logger f1312e;

    public static final Set<Integer> f1313f;

    public static final MediaType f1314g;

    public static final Interceptor f1315h;

    public final OkHttpClient f1316a;

    public final OkHttpClient f1317b;

    public final C1701g1 f1318c;

    public static final class a extends RequestBody implements AutoCloseable {

        public final InputStream f1319a;

        public a(InputStream input) {
            Intrinsics.checkNotNullParameter(input, "input");
            this.f1319a = input;
        }

        @Override
        public void close() throws IOException {
            this.f1319a.close();
        }

        @Override
        public MediaType getContentType() {
            return C1709i.f1314g;
        }

        @Override
        public void writeTo(BufferedSink sink) {
            Object objM1772constructorimpl;
            Intrinsics.checkNotNullParameter(sink, "sink");
            Source source = Okio.source(this.f1319a);
            try {
                try {
                    sink.writeAll(source);
                    sink.flush();
                    objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
                } finally {
                }
            } finally {
            }
            Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
            if (thM1775exceptionOrNullimpl != null) {
                C1709i.f1312e.m447e("writeTo() failed, " + thM1775exceptionOrNullimpl);
            }
            CloseableKt.closeFinally(source, null);
        }
    }

    public static final class b {
    }

    public class c {

        public static final int[] f1320a;

        static {
            int[] iArr = new int[ConnectionType.values().length];
            iArr[ConnectionType.USB.ordinal()] = 1;
            f1320a = iArr;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ext.OkTransport$send$2", m1305f = "OkTransport.kt", m1306i = {0}, m1307l = {223}, m1308m = "invokeSuspend", m1309n = {TtmlNode.TAG_BODY}, m1310s = {"L$0"})
    public static final class d extends SuspendLambda implements Function1<Continuation<? super InputStream>, Object> {

        public Object f1321a;

        public Object f1322b;

        public Object f1323c;

        public Object f1324d;

        public int f1325e;

        public final InputStream f1326f;

        public final C1687e f1327g;

        public final C1709i f1328h;

        public final Long f1329i;

        public static final class a extends Lambda implements Function1<Throwable, Unit> {

            public final a f1330a;

            public final Call f1331b;

            public a(a aVar, Call call) {
                super(1);
                this.f1330a = aVar;
                this.f1331b = call;
            }

            @Override
            public Unit invoke(Throwable th) throws IOException {
                C1709i.f1312e.m447e("invokeOnCancellation() " + th);
                this.f1330a.f1319a.close();
                this.f1331b.cancel();
                return Unit.INSTANCE;
            }
        }

        public static final class b implements Callback {

            public final a f1332a;

            public final CancellableContinuation<InputStream> f1333b;

            public final Ref.ObjectRef<String> f1334c;

            public static final class a extends Lambda implements Function1<Throwable, Unit> {

                public final InputStream f1335a;

                public a(InputStream inputStream) {
                    super(1);
                    this.f1335a = inputStream;
                }

                @Override
                public Unit invoke(Throwable th) throws IOException {
                    Throwable it = th;
                    Intrinsics.checkNotNullParameter(it, "it");
                    this.f1335a.close();
                    return Unit.INSTANCE;
                }
            }

            public b(a aVar, CancellableContinuation<? super InputStream> cancellableContinuation, Ref.ObjectRef<String> objectRef) {
                this.f1332a = aVar;
                this.f1333b = cancellableContinuation;
                this.f1334c = objectRef;
            }

            @Override
            public void onFailure(Call call, IOException e) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                C1709i.f1312e.m447e("onFailure() " + e);
                this.f1332a.f1319a.close();
                CancellableContinuation.DefaultImpls.cancel$default(this.f1333b, null, 1, null);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                this.f1332a.f1319a.close();
                if (!response.isSuccessful()) {
                    CancellableContinuation.DefaultImpls.cancel$default(this.f1333b, null, 1, null);
                    C1709i.f1312e.m447e("Call to " + this.f1334c.element + " failed with " + response.code());
                    return;
                }
                ResponseBody responseBodyBody = response.body();
                InputStream inputStreamByteStream = responseBodyBody != null ? responseBodyBody.byteStream() : null;
                if (inputStreamByteStream != null) {
                    this.f1333b.resume(inputStreamByteStream, new a(inputStreamByteStream));
                } else {
                    CancellableContinuation.DefaultImpls.cancel$default(this.f1333b, null, 1, null);
                    C1709i.f1312e.m447e("Call to " + this.f1334c.element + " resulted in no content");
                }
            }
        }

        public d(InputStream inputStream, C1687e c1687e, C1709i c1709i, Long l, Continuation<? super d> continuation) {
            super(1, continuation);
            this.f1326f = inputStream;
            this.f1327g = c1687e;
            this.f1328h = c1709i;
            this.f1329i = l;
        }

        @Override
        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new d(this.f1326f, this.f1327g, this.f1328h, this.f1329i, continuation);
        }

        @Override
        public Object invoke(Continuation<? super InputStream> continuation) {
            return new d(this.f1326f, this.f1327g, this.f1328h, this.f1329i, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            OkHttpClient okHttpClient;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1325e;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            ResultKt.throwOnFailure(obj);
            a aVar = new a(this.f1326f);
            C1687e c1687e = this.f1327g;
            C1709i c1709i = this.f1328h;
            Long l = this.f1329i;
            this.f1321a = aVar;
            this.f1322b = c1687e;
            this.f1323c = c1709i;
            this.f1324d = l;
            this.f1325e = 1;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            b bVar = C1709i.f1311d;
            URI uriM505c = c1687e.m505c();
            String scheme = uriM505c.getScheme();
            ?? string = new URI(Intrinsics.areEqual(scheme, "ipp") ? HttpHost.DEFAULT_SCHEME_NAME : Intrinsics.areEqual(scheme, "ipps") ? "https" : uriM505c.getScheme(), uriM505c.getUserInfo(), uriM505c.getHost(), uriM505c.getPort() == -1 ? 631 : uriM505c.getPort(), uriM505c.getPath(), uriM505c.getQuery(), uriM505c.getFragment()).toString();
            Intrinsics.checkNotNullExpressionValue(string, "printer.uri.ippToHttpUri(/*dataType*/).toString()");
            objectRef.element = string;
            if (StringsKt.contains$default((CharSequence) string, (CharSequence) "%wlan0", false, 2, (Object) null)) {
                objectRef.element = StringsKt.replace$default((String) objectRef.element, "%wlan0", "", false, 4, (Object) null);
            }
            URI uriM505c2 = c1687e.m505c();
            c1709i.getClass();
            String host = uriM505c2.getHost();
            Intrinsics.checkNotNullExpressionValue(host, "uri.host");
            if (c.f1320a[ConnectionTypeKt.toConnectionType(host).ordinal()] == 1) {
                C1709i.f1312e.m446d("selectClient() - usb client selected");
                okHttpClient = c1709i.f1317b;
            } else {
                C1709i.f1312e.m446d("selectClient() - lan client selected");
                okHttpClient = c1709i.f1316a;
            }
            String str = (String) objectRef.element;
            if (l != null) {
                l.longValue();
                OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().callTimeout(l.longValue(), TimeUnit.MILLISECONDS).readTimeout(l.longValue(), TimeUnit.MILLISECONDS).writeTimeout(l.longValue(), TimeUnit.MILLISECONDS).build();
                if (okHttpClientBuild != null) {
                    okHttpClient = okHttpClientBuild;
                }
            }
            Call callNewCall = okHttpClient.newCall(new Request.Builder().url(str).post(aVar).build());
            cancellableContinuationImpl.invokeOnCancellation(new a(aVar, callNewCall));
            callNewCall.enqueue(new b(aVar, cancellableContinuationImpl, objectRef));
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            return result == coroutine_suspended ? coroutine_suspended : result;
        }
    }

    static {
        b bVar = new b();
        f1311d = bVar;
        f1312e = LoggerKt.logger(bVar);
        f1313f = SetsKt.setOf((Object[]) new Integer[]{301, 302, 303, 307, 308});
        f1314g = MediaType.INSTANCE.get("application/ipp");
        f1315h = new Interceptor() {
            @Override
            public final Response intercept(Interceptor.Chain chain) {
                return C1709i.m546a(chain);
            }
        };
    }

    public C1709i() {
        this(null, 1);
    }

    public C1709i(Mutex trafficMutex) {
        Intrinsics.checkNotNullParameter(trafficMutex, "trafficMutex");
        OkHttpClient.Builder builderFollowSslRedirects = HttpClients.INSTANCE.getLanUnsecureClient().newBuilder().followRedirects(false).followSslRedirects(false);
        Interceptor interceptor = f1315h;
        this.f1316a = builderFollowSslRedirects.addInterceptor(interceptor).build();
        OkHttpClient.Builder builderAddInterceptor = HttpClients.INSTANCE.getUsbClient().newBuilder().followRedirects(false).followSslRedirects(false).addInterceptor(interceptor);
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(2);
        dispatcher.setMaxRequestsPerHost(2);
        this.f1317b = builderAddInterceptor.dispatcher(dispatcher).build();
        this.f1318c = new C1701g1(trafficMutex);
    }

    public C1709i(Mutex mutex, int i) {
        this((i & 1) != 0 ? MutexKt.Mutex$default(false, 1, null) : null);
    }

    public static final Response m546a(Interceptor.Chain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Logger logger = f1312e;
        logger.invoke("--> Request(" + chain.request().url() + ')');
        long jCurrentTimeMillis = System.currentTimeMillis();
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        logger.invoke("<-- Response(" + chain.request().url() + "): " + responseProceed.code() + " (" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms)");
        if (!f1313f.contains(Integer.valueOf(responseProceed.code()))) {
            return responseProceed;
        }
        if (responseProceed.body() != null) {
            responseProceed.close();
        }
        String strHeader$default = Response.header$default(responseProceed, HttpHeaders.LOCATION, null, 2, null);
        if (strHeader$default == null) {
            throw new IOException("Location header missing");
        }
        HttpUrl httpUrlResolve = responseProceed.request().url().resolve(strHeader$default);
        if (httpUrlResolve == null) {
            throw new IOException("Location " + strHeader$default + " is not a well formed url");
        }
        logger.invoke("Repeating request: " + httpUrlResolve);
        return chain.proceed(request.newBuilder().url(httpUrlResolve).header("host", httpUrlResolve.host() + ':' + httpUrlResolve.port()).build());
    }

    @Override
    public Object mo510a(C1687e c1687e, InputStream inputStream, Long l, Continuation<? super InputStream> continuation) {
        return this.f1318c.m525a(new d(inputStream, c1687e, this, l, null), continuation);
    }
}
