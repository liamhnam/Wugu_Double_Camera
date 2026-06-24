package com.p020hp.printsdk;

import com.epson.isv.eprinterdriver.Common.ErrorCode;
import com.epson.isv.eprinterdriver.Ctrl.PageAttribute;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.encoding.MutableAttributeGroup;
import com.p020hp.jipp.encoding.Tag;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.browsing.ConnectionTypeKt;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.printsdk.AbstractC1774v0;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__MergeKt;

public abstract class AbstractC1750q0 implements InterfaceC1699g, CoroutineScope {

    public static final a f1585d;

    public static final Logger f1586e;

    public final CoroutineContext f1587a;

    public final C1676c1<UUID, C1687e> f1588b;

    public final C1676c1<UUID, AbstractC1774v0> f1589c;

    public static final class a {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters", m1305f = "IppPrinters.kt", m1306i = {0}, m1307l = {123}, m1308m = "cancelJob$suspendImpl", m1309n = {"jobId"}, m1310s = {"L$0"})
    public static final class b extends ContinuationImpl {

        public Object f1590a;

        public Object f1591b;

        public Object f1592c;

        public int f1594e;

        public b(Continuation<? super b> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1592c = obj;
            this.f1594e |= Integer.MIN_VALUE;
            return AbstractC1750q0.m615a(AbstractC1750q0.this, (UUID) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters$jobs$1", m1305f = "IppPrinters.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class c extends SuspendLambda implements Function2<AbstractC1774v0, Continuation<? super Flow<? extends C1668b>>, Object> {

        public Object f1595a;

        public final UUID f1597c;

        @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters$jobs$1$1$1", m1305f = "IppPrinters.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<FlowCollector<? super C1668b>, Continuation<? super Unit>, Object> {
            public a(Continuation<? super a> continuation) {
                super(2, continuation);
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(continuation);
            }

            @Override
            public Object invoke(FlowCollector<? super C1668b> flowCollector, Continuation<? super Unit> continuation) {
                return new a(continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
        }

        public c(UUID uuid, Continuation<? super c> continuation) {
            super(2, continuation);
            this.f1597c = uuid;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            c cVar = AbstractC1750q0.this.new c(this.f1597c, continuation);
            cVar.f1595a = obj;
            return cVar;
        }

        @Override
        public Object invoke(AbstractC1774v0 abstractC1774v0, Continuation<? super Flow<? extends C1668b>> continuation) {
            c cVar = AbstractC1750q0.this.new c(this.f1597c, continuation);
            cVar.f1595a = abstractC1774v0;
            return cVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            AbstractC1774v0 abstractC1774v0 = (AbstractC1774v0) this.f1595a;
            if (abstractC1774v0 != null) {
                C1676c1<UUID, C1668b> c1676c1 = abstractC1774v0.f1888d;
                c1676c1.getClass();
                Flow flowFilterNotNull = FlowKt.filterNotNull(FlowKt.distinctUntilChanged(new C1682d1(FlowKt.flow(new C1670b1(c1676c1, null)), c1676c1)));
                if (flowFilterNotNull != null) {
                    return flowFilterNotNull;
                }
            }
            AbstractC1750q0.f1586e.m447e("The printer with uuid " + this.f1597c + " is not found!");
            return FlowKt.flow(new a(null));
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters", m1305f = "IppPrinters.kt", m1306i = {0, 0}, m1307l = {TsExtractor.TS_STREAM_TYPE_E_AC3, PageAttribute.MediaTypeID.EPS_MTID_PLOOFING_WHITE_MAT}, m1308m = "query", m1309n = {"this", MqttCmdEnum.C2S_PRINTER_ERROR}, m1310s = {"L$0", "L$1"})
    public static final class e extends ContinuationImpl {

        public Object f1604a;

        public Object f1605b;

        public Object f1606c;

        public int f1608e;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1606c = obj;
            this.f1608e |= Integer.MIN_VALUE;
            return AbstractC1750q0.m611a(AbstractC1750q0.this, (C1687e) null, (List) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters$query$2", m1305f = "IppPrinters.kt", m1306i = {0, 0, 0}, m1307l = {157}, m1308m = "invokeSuspend", m1309n = {"$this$withContext", "oldPrinter", "newPrinter"}, m1310s = {"L$0", "L$1", "L$2"})
    public static final class f extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super C1687e>, Object> {

        public Object f1609a;

        public Object f1610b;

        public int f1611c;

        public Object f1612d;

        public final C1687e f1614f;

        public final IppPacket f1615g;

        public f(C1687e c1687e, IppPacket ippPacket, Continuation<? super f> continuation) {
            super(2, continuation);
            this.f1614f = c1687e;
            this.f1615g = ippPacket;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            f fVar = AbstractC1750q0.this.new f(this.f1614f, this.f1615g, continuation);
            fVar.f1612d = obj;
            return fVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super C1687e> continuation) {
            f fVar = AbstractC1750q0.this.new f(this.f1614f, this.f1615g, continuation);
            fVar.f1612d = coroutineScope;
            return fVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            C1687e c1687e;
            Object next;
            C1687e c1687e2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1611c;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.f1612d;
                C1676c1<UUID, C1687e> c1676c1 = AbstractC1750q0.this.f1588b;
                UUID key = this.f1614f.m504b();
                c1676c1.getClass();
                Intrinsics.checkNotNullParameter(key, "key");
                c1687e = c1676c1.f969a.get(key);
                if (c1687e == null) {
                    c1687e = this.f1614f;
                }
                Iterator<T> it = this.f1615g.getAttributeGroups().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(((AttributeGroup) next).getTag(), Tag.printerAttributes)) {
                        break;
                    }
                }
                AttributeGroup group = (AttributeGroup) next;
                if (group != null) {
                    C1687e c1687e3 = this.f1614f;
                    AbstractC1750q0 abstractC1750q0 = AbstractC1750q0.this;
                    String host = c1687e3.m505c().getHost();
                    Intrinsics.checkNotNullExpressionValue(host, "printer.uri.host");
                    if (ConnectionTypeKt.isUsbHost(host)) {
                        MutableAttributeGroup mutable = group.toMutable();
                        mutable.drop(Types.printerUriSupported);
                        mutable.drop(Types.printerUuid);
                        group = mutable.toGroup();
                    }
                    AttributeGroup attributeGroupM597a = C1740o0.m597a(c1687e.f1179a, group);
                    if (attributeGroupM597a != null) {
                        group = attributeGroupM597a;
                    }
                    C1687e c1687e4 = new C1687e(group);
                    if (!Intrinsics.areEqual(c1687e, c1687e4)) {
                        C1676c1<UUID, C1687e> c1676c12 = abstractC1750q0.f1588b;
                        UUID uuidM504b = c1687e4.m504b();
                        this.f1612d = coroutineScope;
                        this.f1609a = c1687e;
                        this.f1610b = c1687e4;
                        this.f1611c = 1;
                        if (c1676c12.m477a(uuidM504b, c1687e4, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    c1687e2 = c1687e4;
                }
                return c1687e;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c1687e2 = (C1687e) this.f1610b;
            c1687e = (C1687e) this.f1609a;
            ResultKt.throwOnFailure(obj);
            if (c1687e2 != null) {
                return c1687e2;
            }
            return c1687e;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters", m1305f = "IppPrinters.kt", m1306i = {0}, m1307l = {78}, m1308m = "refreshPrinter$suspendImpl", m1309n = {"printerId"}, m1310s = {"L$0"})
    public static final class g extends ContinuationImpl {

        public Object f1616a;

        public Object f1617b;

        public int f1619d;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1617b = obj;
            this.f1619d |= Integer.MIN_VALUE;
            return AbstractC1750q0.m614a(AbstractC1750q0.this, (UUID) null, (List) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters", m1305f = "IppPrinters.kt", m1306i = {}, m1307l = {64}, m1308m = "toIppPrinter$suspendImpl", m1309n = {}, m1310s = {})
    public static final class h extends ContinuationImpl {

        public Object f1620a;

        public int f1622c;

        public h(Continuation<? super h> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1620a = obj;
            this.f1622c |= Integer.MIN_VALUE;
            return AbstractC1750q0.m613a(AbstractC1750q0.this, (C1687e) null, this);
        }
    }

    static {
        a aVar = new a();
        f1585d = aVar;
        f1586e = LoggerKt.logger(aVar);
    }

    public AbstractC1750q0(CoroutineContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f1587a = context.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
        this.f1588b = new C1676c1<>();
        this.f1589c = new C1676c1<>();
    }

    public static final Object m611a(AbstractC1750q0 abstractC1750q0, C1687e c1687e, List list, Continuation continuation) {
        return abstractC1750q0.m617a((C1687e) null, (List<String>) null, (Continuation<? super C1687e>) continuation);
    }

    public static Object m612a(AbstractC1750q0 abstractC1750q0, C1687e c1687e, List list, Continuation continuation, int i, Object obj) {
        return abstractC1750q0.m617a(c1687e, (i & 2) != 0 ? CollectionsKt.listOf("all") : null, (Continuation<? super C1687e>) continuation);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object m613a(com.p020hp.printsdk.AbstractC1750q0 r7, com.p020hp.printsdk.C1687e r8, kotlin.coroutines.Continuation r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof com.p020hp.printsdk.AbstractC1750q0.h
            if (r0 == 0) goto L13
            r0 = r9
            com.hp.printsdk.q0$h r0 = (com.p020hp.printsdk.AbstractC1750q0.h) r0
            int r1 = r0.f1622c
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1622c = r1
            goto L18
        L13:
            com.hp.printsdk.q0$h r0 = new com.hp.printsdk.q0$h
            r0.<init>(r9)
        L18:
            r4 = r0
            java.lang.Object r9 = r4.f1620a
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.f1622c
            r2 = 1
            if (r1 == 0) goto L32
            if (r1 != r2) goto L2a
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L4a
            goto L43
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            kotlin.ResultKt.throwOnFailure(r9)
            r3 = 0
            r5 = 2
            r6 = 0
            r4.f1622c = r2     // Catch: java.lang.Throwable -> L4a
            r1 = r7
            r2 = r8
            java.lang.Object r9 = m612a(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L4a
            if (r9 != r0) goto L43
            return r0
        L43:
            com.hp.printsdk.e r9 = (com.p020hp.printsdk.C1687e) r9     // Catch: java.lang.Throwable -> L4a
            java.lang.Object r7 = kotlin.Result.m1772constructorimpl(r9)     // Catch: java.lang.Throwable -> L4a
            goto L53
        L4a:
            r7 = move-exception
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r7)
            java.lang.Object r7 = kotlin.Result.m1772constructorimpl(r7)
        L53:
            java.lang.Throwable r8 = kotlin.Result.m1775exceptionOrNullimpl(r7)
            if (r8 == 0) goto L60
            com.hp.mobile.common.utils.Logger r9 = com.p020hp.printsdk.AbstractC1750q0.f1586e
            java.lang.String r0 = "Covert Printer to HpIppPrinter query failed"
            r9.m448e(r0, r8)
        L60:
            boolean r8 = kotlin.Result.m1778isFailureimpl(r7)
            r9 = 0
            if (r8 == 0) goto L68
            r7 = r9
        L68:
            com.hp.printsdk.e r7 = (com.p020hp.printsdk.C1687e) r7
            if (r7 == 0) goto L70
            com.hp.printsdk.v3 r9 = com.p020hp.printsdk.InterfaceC1754r0.b.m625a(r7)
        L70:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1750q0.m613a(com.hp.printsdk.q0, com.hp.printsdk.e, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object m615a(com.p020hp.printsdk.AbstractC1750q0 r4, java.util.UUID r5, kotlin.coroutines.Continuation r6) throws java.lang.Throwable {
        /*
            boolean r0 = r6 instanceof com.p020hp.printsdk.AbstractC1750q0.b
            if (r0 == 0) goto L13
            r0 = r6
            com.hp.printsdk.q0$b r0 = (com.p020hp.printsdk.AbstractC1750q0.b) r0
            int r1 = r0.f1594e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1594e = r1
            goto L18
        L13:
            com.hp.printsdk.q0$b r0 = new com.hp.printsdk.q0$b
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.f1592c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1594e
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r4 = r0.f1591b
            java.util.Iterator r4 = (java.util.Iterator) r4
            java.lang.Object r5 = r0.f1590a
            java.util.UUID r5 = (java.util.UUID) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L31:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L39:
            kotlin.ResultKt.throwOnFailure(r6)
            com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.v0> r4 = r4.f1589c
            java.util.List r4 = r4.m482b()
            java.util.Iterator r4 = r4.iterator()
        L46:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L5f
            java.lang.Object r6 = r4.next()
            com.hp.printsdk.v0 r6 = (com.p020hp.printsdk.AbstractC1774v0) r6
            r0.f1590a = r5
            r0.f1591b = r4
            r0.f1594e = r3
            java.lang.Object r6 = r6.m669a(r5, r0)
            if (r6 != r1) goto L46
            return r1
        L5f:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1750q0.m615a(com.hp.printsdk.q0, java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static Object m616b(AbstractC1750q0 abstractC1750q0, UUID uuid, Continuation continuation) {
        Object objM478a = abstractC1750q0.f1588b.m478a(uuid, continuation);
        return objM478a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM478a : Unit.INSTANCE;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m617a(com.p020hp.printsdk.C1687e r12, java.util.List<java.lang.String> r13, kotlin.coroutines.Continuation<? super com.p020hp.printsdk.C1687e> r14) throws java.lang.Throwable {
        /*
            r11 = this;
            boolean r0 = r14 instanceof com.p020hp.printsdk.AbstractC1750q0.e
            if (r0 == 0) goto L13
            r0 = r14
            com.hp.printsdk.q0$e r0 = (com.p020hp.printsdk.AbstractC1750q0.e) r0
            int r1 = r0.f1608e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1608e = r1
            goto L18
        L13:
            com.hp.printsdk.q0$e r0 = new com.hp.printsdk.q0$e
            r0.<init>(r14)
        L18:
            java.lang.Object r14 = r0.f1606c
            java.lang.Object r9 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.f1608e
            r2 = 1
            r10 = 2
            if (r1 == 0) goto L41
            if (r1 == r2) goto L35
            if (r1 != r10) goto L2d
            kotlin.ResultKt.throwOnFailure(r14)
            goto Lb3
        L2d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L35:
            java.lang.Object r12 = r0.f1605b
            com.hp.printsdk.e r12 = (com.p020hp.printsdk.C1687e) r12
            java.lang.Object r13 = r0.f1604a
            com.hp.printsdk.q0 r13 = (com.p020hp.printsdk.AbstractC1750q0) r13
            kotlin.ResultKt.throwOnFailure(r14)
            goto L9a
        L41:
            kotlin.ResultKt.throwOnFailure(r14)
            com.hp.mobile.common.utils.Logger r14 = com.p020hp.printsdk.AbstractC1750q0.f1586e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "query() "
            r1.<init>(r3)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r1 = r1.toString()
            r14.invoke(r1)
            r14 = r11
            com.hp.printsdk.p0 r14 = (com.p020hp.printsdk.C1745p0) r14
            com.hp.printsdk.r0 r1 = r14.f1569g
            com.hp.jipp.encoding.IppPacket$Companion r14 = com.p020hp.jipp.encoding.IppPacket.INSTANCE
            java.net.URI r3 = r12.m505c()
            r4 = 0
            com.hp.jipp.encoding.AttributeType[] r5 = new com.p020hp.jipp.encoding.AttributeType[r4]
            com.hp.jipp.encoding.IppPacket$Builder r14 = r14.getPrinterAttributes(r3, r5)
            com.hp.jipp.encoding.Attribute[] r3 = new com.p020hp.jipp.encoding.Attribute[r10]
            com.hp.jipp.encoding.NameType r5 = com.p020hp.jipp.model.Types.requestingUserName
            java.lang.String r6 = r11.mo517a()
            com.hp.jipp.encoding.Attribute r5 = r5.m440of(r6)
            r3[r4] = r5
            com.hp.jipp.encoding.KeywordType$Set r4 = com.p020hp.jipp.model.Types.requestedAttributes
            com.hp.jipp.encoding.Attribute r13 = r4.mo417of(r13)
            r3[r2] = r13
            com.hp.jipp.encoding.IppPacket$Builder r3 = r14.putOperationAttributes(r3)
            r0.f1604a = r11
            r0.f1605b = r12
            r0.f1608e = r2
            r4 = 0
            r5 = 0
            r7 = 12
            r8 = 0
            r2 = r12
            r6 = r0
            java.lang.Object r14 = com.p020hp.printsdk.InterfaceC1754r0.b.m626a(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r14 != r9) goto L99
            return r9
        L99:
            r13 = r11
        L9a:
            com.hp.jipp.encoding.IppPacket r14 = (com.p020hp.jipp.encoding.IppPacket) r14
            kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
            com.hp.printsdk.q0$f r2 = new com.hp.printsdk.q0$f
            r3 = 0
            r2.<init>(r12, r14, r3)
            r0.f1604a = r3
            r0.f1605b = r3
            r0.f1608e = r10
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r1, r2, r0)
            if (r14 != r9) goto Lb3
            return r9
        Lb3:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1750q0.m617a(com.hp.printsdk.e, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public Object mo514a(C1687e c1687e, Continuation<? super C1777v3> continuation) {
        return m613a(this, c1687e, continuation);
    }

    @Override
    public Object mo515a(UUID uuid, List<String> list, Continuation<? super C1687e> continuation) {
        return m614a(this, uuid, list, continuation);
    }

    @Override
    public Object mo516a(UUID uuid, Continuation<? super Unit> continuation) {
        return m616b(this, uuid, continuation);
    }

    @Override
    public Flow<C1668b> mo518a(C1680d request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return FlowKt.flow(new d(request, null));
    }

    @Override
    public Flow<C1668b> mo519a(UUID printerId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        return FlowKt__MergeKt.flatMapMerge$default(this.f1589c.m481a(printerId), 0, new c(printerId, null), 1, null);
    }

    @Override
    public Object mo520b(UUID uuid, Continuation<? super Unit> continuation) {
        return m615a(this, uuid, continuation);
    }

    @Override
    public Flow<C1687e> mo521b(UUID printerId) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        return FlowKt.distinctUntilChanged(FlowKt.filterNotNull(this.f1588b.m481a(printerId)));
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1587a;
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters$print$1", m1305f = "IppPrinters.kt", m1306i = {0, 1}, m1307l = {ErrorCode.PrinterError.EPS_PRNERR_TRAYCLOSE, 106, 110}, m1308m = "invokeSuspend", m1309n = {"$this$flow", "$this$flow"}, m1310s = {"L$0", "L$0"})
    public static final class d extends SuspendLambda implements Function2<FlowCollector<? super C1668b>, Continuation<? super Unit>, Object> {

        public int f1598a;

        public Object f1599b;

        public final C1680d f1601d;

        public d(C1680d c1680d, Continuation<? super d> continuation) {
            super(2, continuation);
            this.f1601d = c1680d;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            d dVar = AbstractC1750q0.this.new d(this.f1601d, continuation);
            dVar.f1599b = obj;
            return dVar;
        }

        @Override
        public Object invoke(FlowCollector<? super C1668b> flowCollector, Continuation<? super Unit> continuation) {
            d dVar = AbstractC1750q0.this.new d(this.f1601d, continuation);
            dVar.f1599b = flowCollector;
            return dVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.f1598a
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2f
                if (r1 == r4) goto L27
                if (r1 == r3) goto L1f
                if (r1 != r2) goto L17
                kotlin.ResultKt.throwOnFailure(r14)
                goto Lbb
            L17:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L1f:
                java.lang.Object r1 = r13.f1599b
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r14)
                goto L70
            L27:
                java.lang.Object r1 = r13.f1599b
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r14)
                goto L54
            L2f:
                kotlin.ResultKt.throwOnFailure(r14)
                java.lang.Object r14 = r13.f1599b
                kotlinx.coroutines.flow.FlowCollector r14 = (kotlinx.coroutines.flow.FlowCollector) r14
                com.hp.printsdk.q0 r1 = com.p020hp.printsdk.AbstractC1750q0.this
                com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.e> r1 = r1.f1588b
                com.hp.printsdk.d r6 = r13.f1601d
                java.util.UUID r6 = r6.f1147a
                kotlinx.coroutines.flow.Flow r1 = r1.m481a(r6)
                kotlinx.coroutines.flow.Flow r1 = kotlinx.coroutines.flow.FlowKt.filterNotNull(r1)
                r13.f1599b = r14
                r13.f1598a = r4
                java.lang.Object r1 = kotlinx.coroutines.flow.FlowKt.first(r1, r13)
                if (r1 != r0) goto L51
                return r0
            L51:
                r12 = r1
                r1 = r14
                r14 = r12
            L54:
                com.hp.printsdk.e r14 = (com.p020hp.printsdk.C1687e) r14
                com.hp.printsdk.q0 r4 = com.p020hp.printsdk.AbstractC1750q0.this
                com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.v0> r4 = r4.f1589c
                java.util.UUID r6 = r14.m504b()
                com.hp.printsdk.q0$d$a r7 = new com.hp.printsdk.q0$d$a
                com.hp.printsdk.q0 r8 = com.p020hp.printsdk.AbstractC1750q0.this
                r7.<init>(r8, r14, r5)
                r13.f1599b = r1
                r13.f1598a = r3
                java.lang.Object r14 = r4.m479a(r6, r7, r13)
                if (r14 != r0) goto L70
                return r0
            L70:
                com.hp.printsdk.v0 r14 = (com.p020hp.printsdk.AbstractC1774v0) r14
                com.hp.printsdk.d r3 = r13.f1601d
                r14.getClass()
                java.lang.String r4 = "request"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
                r6 = r14
                com.hp.printsdk.w0 r6 = (com.p020hp.printsdk.C1779w0) r6
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
                com.hp.printsdk.s0 r4 = new com.hp.printsdk.s0
                kotlin.coroutines.CoroutineContext r7 = r6.f1887c
                com.hp.printsdk.r0 r8 = r6.f1947k
                com.hp.printsdk.h0 r9 = r6.f1948l
                com.hp.printsdk.g r10 = r6.f1946j
                r6 = r4
                r11 = r3
                r6.<init>(r7, r8, r9, r10, r11)
                com.hp.printsdk.x0 r9 = new com.hp.printsdk.x0
                r9.<init>(r3, r14, r4, r5)
                r7 = 0
                r8 = 0
                r10 = 3
                r11 = 0
                r6 = r14
                kotlinx.coroutines.BuildersKt.launch$default(r6, r7, r8, r9, r10, r11)
                java.util.UUID r3 = r4.f1719g
                java.lang.String r4 = "jobId"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
                com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.b> r14 = r14.f1888d
                kotlinx.coroutines.flow.Flow r14 = r14.m481a(r3)
                kotlinx.coroutines.flow.Flow r14 = kotlinx.coroutines.flow.FlowKt.filterNotNull(r14)
                r13.f1599b = r5
                r13.f1598a = r2
                java.lang.Object r14 = kotlinx.coroutines.flow.FlowKt.emitAll(r1, r14, r13)
                if (r14 != r0) goto Lbb
                return r0
            Lbb:
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1750q0.d.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @DebugMetadata(m1304c = "com.hp.bgp.ipp.IppPrinters$print$1$queue$1", m1305f = "IppPrinters.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function1<Continuation<? super AbstractC1774v0>, Object> {

            public final AbstractC1750q0 f1602a;

            public final C1687e f1603b;

            public a(AbstractC1750q0 abstractC1750q0, C1687e c1687e, Continuation<? super a> continuation) {
                super(1, continuation);
                this.f1602a = abstractC1750q0;
                this.f1603b = c1687e;
            }

            @Override
            public final Continuation<Unit> create(Continuation<?> continuation) {
                return new a(this.f1602a, this.f1603b, continuation);
            }

            @Override
            public Object invoke(Continuation<? super AbstractC1774v0> continuation) {
                return new a(this.f1602a, this.f1603b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                AbstractC1750q0 abstractC1750q0 = this.f1602a;
                UUID printerId = this.f1603b.m504b();
                C1745p0 printers = (C1745p0) abstractC1750q0;
                printers.getClass();
                Intrinsics.checkNotNullParameter(printerId, "printerId");
                AbstractC1774v0.b bVar = AbstractC1774v0.f1882g;
                CoroutineContext context = printers.f1570h;
                InterfaceC1754r0 transport = printers.f1569g;
                C1705h0 formatConverter = printers.f1571i;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(printers, "printers");
                Intrinsics.checkNotNullParameter(transport, "transport");
                Intrinsics.checkNotNullParameter(formatConverter, "formatConverter");
                Intrinsics.checkNotNullParameter(printerId, "printerId");
                return new C1779w0(printers, printerId, transport, formatConverter, context.plus(Dispatchers.getIO()));
            }
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object m614a(com.p020hp.printsdk.AbstractC1750q0 r6, java.util.UUID r7, java.util.List r8, kotlin.coroutines.Continuation r9) throws java.lang.Throwable {
        /*
            boolean r0 = r9 instanceof com.p020hp.printsdk.AbstractC1750q0.g
            if (r0 == 0) goto L13
            r0 = r9
            com.hp.printsdk.q0$g r0 = (com.p020hp.printsdk.AbstractC1750q0.g) r0
            int r1 = r0.f1619d
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1619d = r1
            goto L18
        L13:
            com.hp.printsdk.q0$g r0 = new com.hp.printsdk.q0$g
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f1617b
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1619d
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            java.lang.Object r6 = r0.f1616a
            r7 = r6
            java.util.UUID r7 = (java.util.UUID) r7
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L85
            goto L7e
        L2f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            com.hp.mobile.common.utils.Logger r9 = com.p020hp.printsdk.AbstractC1750q0.f1586e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "refreshPrinter() "
            r2.<init>(r5)
            java.lang.StringBuilder r2 = r2.append(r7)
            java.lang.String r5 = " available for refresh "
            java.lang.StringBuilder r2 = r2.append(r5)
            com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.e> r5 = r6.f1588b
            java.util.List r5 = r5.m480a()
            java.lang.StringBuilder r2 = r2.append(r5)
            java.lang.String r2 = r2.toString()
            r9.m446d(r2)
            com.hp.printsdk.c1<java.util.UUID, com.hp.printsdk.e> r9 = r6.f1588b
            r9.getClass()
            java.lang.String r2 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r2)
            java.util.Map<T, U> r9 = r9.f969a
            java.lang.Object r9 = r9.get(r7)
            com.hp.printsdk.e r9 = (com.p020hp.printsdk.C1687e) r9
            if (r9 == 0) goto Lb8
            r0.f1616a = r7     // Catch: java.lang.Throwable -> L85
            r0.f1619d = r4     // Catch: java.lang.Throwable -> L85
            java.lang.Object r9 = r6.m617a(r9, r8, r0)     // Catch: java.lang.Throwable -> L85
            if (r9 != r1) goto L7e
            return r1
        L7e:
            com.hp.printsdk.e r9 = (com.p020hp.printsdk.C1687e) r9     // Catch: java.lang.Throwable -> L85
            java.lang.Object r6 = kotlin.Result.m1772constructorimpl(r9)     // Catch: java.lang.Throwable -> L85
            goto L8e
        L85:
            r6 = move-exception
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m1772constructorimpl(r6)
        L8e:
            java.lang.Throwable r8 = kotlin.Result.m1775exceptionOrNullimpl(r6)
            if (r8 == 0) goto Lae
            com.hp.mobile.common.utils.Logger r9 = com.p020hp.printsdk.AbstractC1750q0.f1586e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Refresh printer with uuid "
            r0.<init>(r1)
            java.lang.StringBuilder r7 = r0.append(r7)
            java.lang.String r0 = " failed"
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            r9.m448e(r7, r8)
        Lae:
            boolean r7 = kotlin.Result.m1778isFailureimpl(r6)
            if (r7 == 0) goto Lb5
            goto Lb6
        Lb5:
            r3 = r6
        Lb6:
            com.hp.printsdk.e r3 = (com.p020hp.printsdk.C1687e) r3
        Lb8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1750q0.m614a(com.hp.printsdk.q0, java.util.UUID, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
