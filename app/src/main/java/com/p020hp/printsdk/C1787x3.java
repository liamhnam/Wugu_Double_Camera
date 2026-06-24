package com.p020hp.printsdk;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.MutableLiveData;
import com.p020hp.bgp.service.BackgroundPrintService;
import com.p020hp.mobile.common.CommonLibKt;
import com.p020hp.mobile.common.browsing.Device;
import com.p020hp.mobile.common.browsing.ServiceAdapter;
import com.p020hp.mobile.common.browsing.ServicesGroup;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.HpPrinter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.p040ws.WebSocketProtocol;

public final class C1787x3 extends AbstractC1717j2 {

    public final CoroutineScope f1964e;

    public final Context f1965f;

    public final Logger f1966g;

    public BackgroundPrintService.C1607b f1967h;

    public final d f1968i;

    public MutableLiveData<List<HpPrinter>> f1969j;

    public Job f1970k;

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$discovery$1", m1305f = "PrintService.kt", m1306i = {0}, m1307l = {110, 177}, m1308m = "invokeSuspend", m1309n = {"$this$launch"}, m1310s = {"L$0"})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1971a;

        public Object f1972b;

        public final long f1973c;

        public final C1787x3 f1974d;

        public final Function1<List<? extends HpPrinter>, Unit> f1975e;

        @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$discovery$1$1$1$1$1", m1305f = "PrintService.kt", m1306i = {}, m1307l = {WebSocketProtocol.PAYLOAD_SHORT, WebSocketProtocol.PAYLOAD_SHORT, 131}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class C3372a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public int f1976a;

            public final C1787x3 f1977b;

            public final C1687e f1978c;

            public final Map<Device, HpPrinter> f1979d;

            public final Device f1980e;

            public final Function1<List<? extends HpPrinter>, Unit> f1981f;

            @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$discovery$1$1$1$1$1$1$2", m1305f = "PrintService.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
            public static final class C3373a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

                public final Map<Device, HpPrinter> f1982a;

                public final Device f1983b;

                public final C1777v3 f1984c;

                public final Function1<List<? extends HpPrinter>, Unit> f1985d;

                public C3373a(Map<Device, HpPrinter> map, Device device, C1777v3 c1777v3, Function1<? super List<? extends HpPrinter>, Unit> function1, Continuation<? super C3373a> continuation) {
                    super(2, continuation);
                    this.f1982a = map;
                    this.f1983b = device;
                    this.f1984c = c1777v3;
                    this.f1985d = function1;
                }

                @Override
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C3373a(this.f1982a, this.f1983b, this.f1984c, this.f1985d, continuation);
                }

                @Override
                public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return new C3373a(this.f1982a, this.f1983b, this.f1984c, this.f1985d, continuation).invokeSuspend(Unit.INSTANCE);
                }

                @Override
                public final Object invokeSuspend(Object obj) throws Throwable {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    ResultKt.throwOnFailure(obj);
                    this.f1982a.put(this.f1983b, this.f1984c);
                    this.f1985d.invoke(CollectionsKt.toList(this.f1982a.values()));
                    return Unit.INSTANCE;
                }
            }

            public C3372a(C1787x3 c1787x3, C1687e c1687e, Map<Device, HpPrinter> map, Device device, Function1<? super List<? extends HpPrinter>, Unit> function1, Continuation<? super C3372a> continuation) {
                super(2, continuation);
                this.f1977b = c1787x3;
                this.f1978c = c1687e;
                this.f1979d = map;
                this.f1980e = device;
                this.f1981f = function1;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C3372a(this.f1977b, this.f1978c, this.f1979d, this.f1980e, this.f1981f, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C3372a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) throws java.lang.Throwable {
                /*
                    r9 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r9.f1976a
                    r2 = 3
                    r3 = 2
                    r4 = 1
                    if (r1 == 0) goto L25
                    if (r1 == r4) goto L21
                    if (r1 == r3) goto L1d
                    if (r1 != r2) goto L15
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L7f
                L15:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L1d:
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L40
                L21:
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L33
                L25:
                    kotlin.ResultKt.throwOnFailure(r10)
                    com.hp.printsdk.x3 r10 = r9.f1977b
                    r9.f1976a = r4
                    java.lang.Object r10 = r10.mo561a(r9)
                    if (r10 != r0) goto L33
                    return r0
                L33:
                    com.hp.printsdk.g r10 = (com.p020hp.printsdk.InterfaceC1699g) r10
                    com.hp.printsdk.e r1 = r9.f1978c
                    r9.f1976a = r3
                    java.lang.Object r10 = r10.mo514a(r1, r9)
                    if (r10 != r0) goto L40
                    return r0
                L40:
                    r6 = r10
                    com.hp.printsdk.v3 r6 = (com.p020hp.printsdk.C1777v3) r6
                    if (r6 == 0) goto L82
                    java.util.Map<com.hp.mobile.common.browsing.Device, com.hp.open.printsdk.HpPrinter> r4 = r9.f1979d
                    com.hp.mobile.common.browsing.Device r5 = r9.f1980e
                    com.hp.printsdk.x3 r10 = r9.f1977b
                    com.hp.printsdk.e r1 = r9.f1978c
                    kotlin.jvm.functions.Function1<java.util.List<? extends com.hp.open.printsdk.HpPrinter>, kotlin.Unit> r7 = r9.f1981f
                    java.lang.Object r3 = r4.get(r5)
                    com.hp.open.printsdk.HpPrinter r3 = (com.p020hp.open.printsdk.HpPrinter) r3
                    if (r3 == 0) goto L6b
                    com.hp.mobile.common.utils.Logger r10 = r10.f1966g
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    java.lang.String r8 = "DISCO-FLOW discovery() - Printer found: "
                    r3.<init>(r8)
                    java.lang.StringBuilder r1 = r3.append(r1)
                    java.lang.String r1 = r1.toString()
                    r10.m446d(r1)
                L6b:
                    kotlinx.coroutines.MainCoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getMain()
                    com.hp.printsdk.x3$a$a$a r1 = new com.hp.printsdk.x3$a$a$a
                    r8 = 0
                    r3 = r1
                    r3.<init>(r4, r5, r6, r7, r8)
                    r9.f1976a = r2
                    java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r1, r9)
                    if (r10 != r0) goto L7f
                    return r0
                L7f:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    goto L83
                L82:
                    r10 = 0
                L83:
                    if (r10 != 0) goto L8e
                    com.hp.printsdk.x3 r10 = r9.f1977b
                    com.hp.mobile.common.utils.Logger r10 = r10.f1966g
                    java.lang.String r0 = "DISCO-FLOW discovery() - failed to query the printer"
                    r10.m446d(r0)
                L8e:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1787x3.a.C3372a.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$discovery$1$1$2$2", m1305f = "PrintService.kt", m1306i = {0}, m1307l = {148, 148}, m1308m = "invokeSuspend", m1309n = {"it"}, m1310s = {"L$2"})
        public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public Object f1986a;

            public Object f1987b;

            public Object f1988c;

            public int f1989d;

            public final Map<Device, HpPrinter> f1990e;

            public final Device f1991f;

            public final C1787x3 f1992g;

            public final Function1<List<? extends HpPrinter>, Unit> f1993h;

            public b(Map<Device, HpPrinter> map, Device device, C1787x3 c1787x3, Function1<? super List<? extends HpPrinter>, Unit> function1, Continuation<? super b> continuation) {
                super(2, continuation);
                this.f1990e = map;
                this.f1991f = device;
                this.f1992g = c1787x3;
                this.f1993h = function1;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new b(this.f1990e, this.f1991f, this.f1992g, this.f1993h, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new b(this.f1990e, this.f1991f, this.f1992g, this.f1993h, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                HpPrinter hpPrinterRemove;
                Function1<List<? extends HpPrinter>, Unit> function1;
                Map<Device, HpPrinter> map;
                Function1<List<? extends HpPrinter>, Unit> function12;
                Map<Device, HpPrinter> map2;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.f1989d;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    hpPrinterRemove = this.f1990e.remove(this.f1991f);
                    if (hpPrinterRemove != null) {
                        C1787x3 c1787x3 = this.f1992g;
                        function1 = this.f1993h;
                        Map<Device, HpPrinter> map3 = this.f1990e;
                        this.f1986a = function1;
                        this.f1987b = map3;
                        this.f1988c = hpPrinterRemove;
                        this.f1989d = 1;
                        obj = c1787x3.mo561a(this);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        map = map3;
                    }
                    return Unit.INSTANCE;
                }
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    map2 = (Map) this.f1987b;
                    function12 = (Function1) this.f1986a;
                    ResultKt.throwOnFailure(obj);
                    function12.invoke(CollectionsKt.toList(map2.values()));
                    return Unit.INSTANCE;
                }
                hpPrinterRemove = (HpPrinter) this.f1988c;
                map = (Map) this.f1987b;
                function1 = (Function1) this.f1986a;
                ResultKt.throwOnFailure(obj);
                Function1<List<? extends HpPrinter>, Unit> function13 = function1;
                HpPrinter hpPrinter = hpPrinterRemove;
                function12 = function13;
                UUID id = hpPrinter.getId();
                this.f1986a = function12;
                this.f1987b = map;
                this.f1988c = null;
                this.f1989d = 2;
                if (((InterfaceC1699g) obj).mo516a(id, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                map2 = map;
                function12.invoke(CollectionsKt.toList(map2.values()));
                return Unit.INSTANCE;
            }
        }

        public static final class c implements FlowCollector<List<? extends Device>> {

            public final C1787x3 f1994a;

            public final Map f1995b;

            public final CoroutineScope f1996c;

            public final Map f1997d;

            public final Function1 f1998e;

            @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService$discovery$1$invokeSuspend$$inlined$collect$1", m1305f = "PrintService.kt", m1306i = {0}, m1307l = {159}, m1308m = "emit", m1309n = {"this"}, m1310s = {"L$0"})
            public static final class C3374a extends ContinuationImpl {

                public Object f1999a;

                public int f2000b;

                public Object f2002d;

                public Object f2003e;

                public C3374a(Continuation continuation) {
                    super(continuation);
                }

                @Override
                public final Object invokeSuspend(Object obj) {
                    this.f1999a = obj;
                    this.f2000b |= Integer.MIN_VALUE;
                    return c.this.emit(null, this);
                }
            }

            public c(C1787x3 c1787x3, Map map, CoroutineScope coroutineScope, Map map2, Function1 function1) {
                this.f1994a = c1787x3;
                this.f1995b = map;
                this.f1996c = coroutineScope;
                this.f1997d = map2;
                this.f1998e = function1;
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.lang.Object emit(java.util.List<? extends com.p020hp.mobile.common.browsing.Device> r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 655
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1787x3.a.c.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public a(long j, C1787x3 c1787x3, Function1<? super List<? extends HpPrinter>, Unit> function1, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f1973c = j;
            this.f1974d = c1787x3;
            this.f1975e = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            a aVar = new a(this.f1973c, this.f1974d, this.f1975e, continuation);
            aVar.f1972b = obj;
            return aVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            a aVar = new a(this.f1973c, this.f1974d, this.f1975e, continuation);
            aVar.f1972b = coroutineScope;
            return aVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1971a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.f1972b;
                long j = this.f1973c;
                this.f1972b = coroutineScope;
                this.f1971a = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                coroutineScope = (CoroutineScope) this.f1972b;
                ResultKt.throwOnFailure(obj);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            C1787x3 c1787x3 = this.f1974d;
            c1787x3.f1966g.m446d("discovery(): Flow - invocation");
            CommonLibKt.CommonLib().getServicesBrowser().start(c1787x3.f1968i);
            Flow<List<Device>> flowAsFlow = c1787x3.f1968i.asFlow();
            c cVar = new c(this.f1974d, linkedHashMap, coroutineScope, linkedHashMap2, this.f1975e);
            this.f1972b = null;
            this.f1971a = 2;
            if (flowAsFlow.collect(cVar, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService", m1305f = "PrintService.kt", m1306i = {}, m1307l = {34}, m1308m = "printerAccess", m1309n = {}, m1310s = {})
    public static final class b extends ContinuationImpl {

        public Object f2004a;

        public int f2006c;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f2004a = obj;
            this.f2006c |= Integer.MIN_VALUE;
            return C1787x3.this.mo561a(this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.ipp.PrintService", m1305f = "PrintService.kt", m1306i = {0}, m1307l = {40}, m1308m = NotificationCompat.CATEGORY_SERVICE, m1309n = {"this"}, m1310s = {"L$0"})
    public static final class c extends ContinuationImpl {

        public Object f2007a;

        public Object f2008b;

        public int f2010d;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f2008b = obj;
            this.f2010d |= Integer.MIN_VALUE;
            return C1787x3.this.m684b(this);
        }
    }

    public static final class d extends ServiceAdapter {
        @Override
        public ServicesGroup serviceGroup() {
            return ServicesGroup.INSTANCE.getPRINT();
        }
    }

    public C1787x3(CoroutineScope scope, Context context, InterfaceC1737n2 localRepo) {
        super(scope, localRepo);
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(localRepo, "localRepo");
        this.f1964e = scope;
        this.f1965f = context;
        this.f1966g = LoggerKt.logger(this);
        this.f1968i = new d();
    }

    public final MutableLiveData<List<HpPrinter>> m682a() {
        MutableLiveData<List<HpPrinter>> mutableLiveData = this.f1969j;
        if (mutableLiveData != null) {
            return mutableLiveData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("discoveryPrinter");
        return null;
    }

    public final void m683a(long j, Function1<? super List<? extends HpPrinter>, Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(this.f1964e, Dispatchers.getMain(), null, new a(j, this, callback, null), 2, null);
        Intrinsics.checkNotNullParameter(jobLaunch$default, "<set-?>");
        this.f1970k = jobLaunch$default;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m684b(kotlin.coroutines.Continuation<? super com.p020hp.bgp.service.BackgroundPrintService> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.p020hp.printsdk.C1787x3.c
            if (r0 == 0) goto L13
            r0 = r5
            com.hp.printsdk.x3$c r0 = (com.p020hp.printsdk.C1787x3.c) r0
            int r1 = r0.f2010d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2010d = r1
            goto L18
        L13:
            com.hp.printsdk.x3$c r0 = new com.hp.printsdk.x3$c
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f2008b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f2010d
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.f2007a
            com.hp.printsdk.x3 r0 = (com.p020hp.printsdk.C1787x3) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4c
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.hp.bgp.service.BackgroundPrintService$b r5 = r4.f1967h
            if (r5 != 0) goto L50
            com.hp.bgp.service.BackgroundPrintService$a r5 = com.p020hp.bgp.service.BackgroundPrintService.f699e
            android.content.Context r2 = r4.f1965f
            r0.f2007a = r4
            r0.f2010d = r3
            java.lang.Object r5 = r5.m407a(r2, r0)
            if (r5 != r1) goto L4b
            return r1
        L4b:
            r0 = r4
        L4c:
            com.hp.bgp.service.BackgroundPrintService$b r5 = (com.p020hp.bgp.service.BackgroundPrintService.C1607b) r5
            r0.f1967h = r5
        L50:
            com.hp.bgp.service.BackgroundPrintService r5 = r5.f708c
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1787x3.m684b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public void close() {
        this.f1966g.m446d("close() - print service close");
        m685b();
        BackgroundPrintService.C1607b c1607b = this.f1967h;
        if (c1607b != null) {
            c1607b.close();
        }
        this.f1967h = null;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object mo561a(kotlin.coroutines.Continuation<? super com.p020hp.printsdk.InterfaceC1699g> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.p020hp.printsdk.C1787x3.b
            if (r0 == 0) goto L13
            r0 = r5
            com.hp.printsdk.x3$b r0 = (com.p020hp.printsdk.C1787x3.b) r0
            int r1 = r0.f2006c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2006c = r1
            goto L18
        L13:
            com.hp.printsdk.x3$b r0 = new com.hp.printsdk.x3$b
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.f2004a
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f2006c
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            kotlin.ResultKt.throwOnFailure(r5)
            goto L3d
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L31:
            kotlin.ResultKt.throwOnFailure(r5)
            r0.f2006c = r3
            java.lang.Object r5 = r4.m684b(r0)
            if (r5 != r1) goto L3d
            return r1
        L3d:
            com.hp.bgp.service.BackgroundPrintService r5 = (com.p020hp.bgp.service.BackgroundPrintService) r5
            kotlin.Lazy r5 = r5.f704d
            java.lang.Object r5 = r5.getValue()
            com.hp.printsdk.g r5 = (com.p020hp.printsdk.InterfaceC1699g) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1787x3.mo561a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void m685b() {
        CommonLibKt.CommonLib().getServicesBrowser().stop(this.f1968i);
        Job job = this.f1970k;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }
}
