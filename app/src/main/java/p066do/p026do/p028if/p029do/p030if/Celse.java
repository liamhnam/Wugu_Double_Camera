package p066do.p026do.p028if.p029do.p030if;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.sync.Mutex;

public final class Celse implements CoroutineScope {

    public final Logger f3939case;

    public final ConcurrentHashMap<String, ArrayList<NsdServiceInfo>> f3940else;

    public final Mutex f3941for;

    public final List<C2077do> f3942goto;

    public final CoroutineContext f2519if;

    public final NsdManager f3943new;

    public final Function2<NsdServiceInfo, Boolean, Unit> f3944try;

    public final class C2077do implements NsdManager.DiscoveryListener {

        public final String f2520do;

        public final Celse f3945for;

        public boolean f2521if;

        @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryNSD$Discoverer$onServiceFound$1", m1305f = "ServiceDiscoveryNSD.kt", m1306i = {0, 1}, m1307l = {235, 141}, m1308m = "invokeSuspend", m1309n = {"$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, m1310s = {"L$0", "L$0"})
        public static final class Cdo extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public int f3946case;

            public final Celse f3947else;

            public Object f3948for;

            public final NsdServiceInfo f3949goto;

            public Object f2522if;

            public Object f3950new;

            public final C2077do f3951this;

            public Object f3952try;

            @DebugMetadata(m1304c = "com.hp.mobile.common.browsing.ServiceDiscoveryNSD$Discoverer$onServiceFound$1$1$resolved$1", m1305f = "ServiceDiscoveryNSD.kt", m1306i = {}, m1307l = {230}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
            public static final class C3380do extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NsdServiceInfo>, Object> {

                public final NsdServiceInfo f3953case;

                public Object f3954for;

                public Object f2523if;

                public int f3955new;

                public final Celse f3956try;

                public static final class C3381do implements NsdManager.ResolveListener {

                    public final Celse f2524do;

                    public final CancellableContinuation<NsdServiceInfo> f2525if;

                    public C3381do(Celse celse, CancellableContinuation<? super NsdServiceInfo> cancellableContinuation) {
                        this.f2524do = celse;
                        this.f2525if = cancellableContinuation;
                    }

                    @Override
                    public void onResolutionStopped(NsdServiceInfo serviceInfo) {
                        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                        super.onResolutionStopped(serviceInfo);
                        this.f2524do.f3939case.m446d("DISCO-FLOW onResolutionStopped() -> " + C2076do.m1758new(serviceInfo));
                        this.f2525if.resumeWith(Result.m1772constructorimpl(null));
                    }

                    @Override
                    public void onResolveFailed(NsdServiceInfo serviceInfo, int i) {
                        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                        this.f2524do.f3939case.m447e("DISCO-FLOW onResolveFailed() -> " + C2076do.m1758new(serviceInfo) + ": error = " + i);
                        this.f2525if.resumeWith(Result.m1772constructorimpl(null));
                    }

                    @Override
                    public void onServiceResolved(NsdServiceInfo serviceInfo) {
                        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                        this.f2524do.f3939case.m446d("DISCO-FLOW onServiceResolved ->" + C2076do.m1758new(serviceInfo) + " ServiceInfo: " + serviceInfo);
                        this.f2525if.resumeWith(Result.m1772constructorimpl(serviceInfo));
                    }

                    @Override
                    public void onStopResolutionFailed(NsdServiceInfo serviceInfo, int i) {
                        Intrinsics.checkNotNullParameter(serviceInfo, "serviceInfo");
                        super.onStopResolutionFailed(serviceInfo, i);
                        this.f2524do.f3939case.m446d("DISCO-FLOW onStopResolutionFailed() -> " + C2076do.m1758new(serviceInfo) + ": error=" + i + "()");
                        this.f2525if.resumeWith(Result.m1772constructorimpl(null));
                    }
                }

                public C3380do(Celse celse, NsdServiceInfo nsdServiceInfo, Continuation<? super C3380do> continuation) {
                    super(2, continuation);
                    this.f3956try = celse;
                    this.f3953case = nsdServiceInfo;
                }

                @Override
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C3380do(this.f3956try, this.f3953case, continuation);
                }

                @Override
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super NsdServiceInfo> continuation) {
                    return new C3380do(this.f3956try, this.f3953case, continuation).invokeSuspend(Unit.INSTANCE);
                }

                @Override
                public final Object invokeSuspend(Object obj) throws Throwable {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.f3955new;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Celse celse = this.f3956try;
                        NsdServiceInfo nsdServiceInfo = this.f3953case;
                        this.f2523if = celse;
                        this.f3954for = nsdServiceInfo;
                        this.f3955new = 1;
                        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
                        cancellableContinuationImpl.initCancellability();
                        celse.f3943new.resolveService(nsdServiceInfo, new C3381do(celse, cancellableContinuationImpl));
                        obj = cancellableContinuationImpl.getResult();
                        if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                            DebugProbesKt.probeCoroutineSuspended(this);
                        }
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return obj;
                }
            }

            public Cdo(Celse celse, NsdServiceInfo nsdServiceInfo, C2077do c2077do, Continuation<? super Cdo> continuation) {
                super(2, continuation);
                this.f3947else = celse;
                this.f3949goto = nsdServiceInfo;
                this.f3951this = c2077do;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new Cdo(this.f3947else, this.f3949goto, this.f3951this, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new Cdo(this.f3947else, this.f3949goto, this.f3951this, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 378
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: p066do.p026do.p028if.p029do.p030if.Celse.C2077do.Cdo.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        public C2077do(Celse celse, String serviceType) {
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            this.f3945for = celse;
            this.f2520do = serviceType;
        }

        public final void m1235do(NsdServiceInfo nsdServiceInfo) {
            ArrayList<NsdServiceInfo> arrayList = this.f3945for.f3940else.get(nsdServiceInfo.getServiceName());
            if (arrayList != null) {
                arrayList.add(nsdServiceInfo);
                return;
            }
            ConcurrentHashMap<String, ArrayList<NsdServiceInfo>> concurrentHashMap = this.f3945for.f3940else;
            String serviceName = nsdServiceInfo.getServiceName();
            Intrinsics.checkNotNullExpressionValue(serviceName, "nnInfo.serviceName");
            concurrentHashMap.put(serviceName, CollectionsKt.arrayListOf(nsdServiceInfo));
        }

        @Override
        public void onDiscoveryStarted(String str) {
            this.f3945for.f3939case.m446d("onDiscoveryStarted() " + str);
        }

        @Override
        public void onDiscoveryStopped(String str) {
            this.f3945for.f3939case.m446d("onDiscoveryStopped() " + str);
        }

        @Override
        public void onServiceFound(NsdServiceInfo nsdServiceInfo) {
            this.f3945for.f3939case.m446d("DISCO-FLOW onServiceFound() info id = " + (nsdServiceInfo != null ? C2076do.m1758new(nsdServiceInfo) : null));
            BuildersKt__Builders_commonKt.launch$default(this.f3945for, Dispatchers.getIO(), null, new Cdo(this.f3945for, nsdServiceInfo, this, null), 2, null);
        }

        @Override
        public void onServiceLost(NsdServiceInfo nsdServiceInfo) {
            Object obj = null;
            this.f3945for.f3939case.m446d("DISCO-FLOW onServiceLost() info id = " + (nsdServiceInfo != null ? C2076do.m1758new(nsdServiceInfo) : null));
            ArrayList<NsdServiceInfo> arrayList = this.f3945for.f3940else.get(nsdServiceInfo != null ? nsdServiceInfo.getServiceName() : null);
            if (arrayList != null) {
                Iterator<T> it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (Intrinsics.areEqual(C2076do.m1758new((NsdServiceInfo) next), nsdServiceInfo != null ? C2076do.m1758new(nsdServiceInfo) : null)) {
                        obj = next;
                        break;
                    }
                }
                NsdServiceInfo nsdServiceInfo2 = (NsdServiceInfo) obj;
                if (nsdServiceInfo2 != null) {
                    this.f3945for.f3944try.invoke(nsdServiceInfo2, Boolean.FALSE);
                    arrayList.remove(nsdServiceInfo2);
                }
            }
        }

        @Override
        public void onStartDiscoveryFailed(String str, int i) {
            this.f3945for.f3939case.m447e("onStartDiscoveryFailed() " + str);
        }

        @Override
        public void onStopDiscoveryFailed(String str, int i) {
            this.f3945for.f3939case.m447e("onStopDiscoveryFailed() " + str);
        }
    }

    public Celse(Context context, CoroutineContext scope, Mutex mutex, NsdManager nsdManager, Function2<? super NsdServiceInfo, ? super Boolean, Unit> onAvailable) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(mutex, "mutex");
        Intrinsics.checkNotNullParameter(nsdManager, "nsdManager");
        Intrinsics.checkNotNullParameter(onAvailable, "onAvailable");
        this.f2519if = scope;
        this.f3941for = mutex;
        this.f3943new = nsdManager;
        this.f3944try = onAvailable;
        this.f3939case = LoggerKt.logger(LoggerKt.toTag("ServiceDiscoveryNSD"));
        this.f3940else = new ConcurrentHashMap<>();
        this.f3942goto = new ArrayList();
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f2519if.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
    }
}
