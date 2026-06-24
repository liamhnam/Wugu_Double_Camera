package com.p020hp.printsdk;

import com.p020hp.jipp.encoding.IppPacket;
import com.p020hp.jipp.encoding.MutableAttributeGroup;
import com.p020hp.jipp.encoding.Tag;
import com.p020hp.jipp.model.JobState;
import com.p020hp.jipp.model.Types;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.printsdk.C1668b;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.UUID;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

public final class C1759s0 implements CoroutineScope {

    public static final b f1711k;

    public static final Logger f1712l;

    public final InterfaceC1754r0 f1713a;

    public final C1705h0 f1714b;

    public final InterfaceC1699g f1715c;

    public C1680d f1716d;

    public final CoroutineContext f1717e;

    public C1668b f1718f;

    public final UUID f1719g;

    public final Mutex f1720h;

    public final MutableStateFlow<C1668b> f1721i;

    public C1687e f1722j;

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry$1", m1305f = "JobEntry.kt", m1306i = {}, m1307l = {64, 65}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1723a;

        @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry$1$1", m1305f = "JobEntry.kt", m1306i = {}, m1307l = {68}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class C3371a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result<? extends Unit>>, Object> {

            public int f1725a;

            public Object f1726b;

            public final C1759s0 f1727c;

            public C3371a(C1759s0 c1759s0, Continuation<? super C3371a> continuation) {
                super(2, continuation);
                this.f1727c = c1759s0;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C3371a c3371a = new C3371a(this.f1727c, continuation);
                c3371a.f1726b = obj;
                return c3371a;
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Result<? extends Unit>> continuation) {
                C3371a c3371a = new C3371a(this.f1727c, continuation);
                c3371a.f1726b = coroutineScope;
                return c3371a.invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                Object objM1772constructorimpl;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.f1725a;
                try {
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        C1759s0 c1759s0 = this.f1727c;
                        if (!c1759s0.f1718f.f896f) {
                            this.f1725a = 1;
                            if (c1759s0.m652b(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    objM1772constructorimpl = Result.m1772constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    objM1772constructorimpl = Result.m1772constructorimpl(ResultKt.createFailure(th));
                }
                C1759s0 c1759s02 = this.f1727c;
                Throwable thM1775exceptionOrNullimpl = Result.m1775exceptionOrNullimpl(objM1772constructorimpl);
                if (thM1775exceptionOrNullimpl != null) {
                    c1759s02.m650a(JobState.aborted, thM1775exceptionOrNullimpl);
                }
                return Result.m1771boximpl(objM1772constructorimpl);
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return C1759s0.this.new a(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return C1759s0.this.new a(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) throws java.lang.Throwable {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.f1723a
                r2 = 2
                r3 = 0
                r4 = 1
                if (r1 == 0) goto L1f
                if (r1 == r4) goto L1b
                if (r1 != r2) goto L13
                kotlin.ResultKt.throwOnFailure(r6)
                goto L42
            L13:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L1b:
                kotlin.ResultKt.throwOnFailure(r6)
                goto L2f
            L1f:
                kotlin.ResultKt.throwOnFailure(r6)
                com.hp.printsdk.s0 r6 = com.p020hp.printsdk.C1759s0.this
                kotlinx.coroutines.sync.Mutex r6 = r6.f1720h
                r5.f1723a = r4
                java.lang.Object r6 = kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(r6, r3, r5, r4, r3)
                if (r6 != r0) goto L2f
                return r0
            L2f:
                com.hp.printsdk.s0$a$a r6 = new com.hp.printsdk.s0$a$a
                com.hp.printsdk.s0 r1 = com.p020hp.printsdk.C1759s0.this
                r6.<init>(r1, r3)
                r5.f1723a = r2
                r1 = 3600000(0x36ee80, double:1.7786363E-317)
                java.lang.Object r6 = kotlinx.coroutines.TimeoutKt.withTimeout(r1, r6, r5)
                if (r6 != r0) goto L42
                return r0
            L42:
                kotlin.Result r6 = (kotlin.Result) r6
                java.lang.Object r6 = r6.getValue()
                com.hp.printsdk.s0 r0 = com.p020hp.printsdk.C1759s0.this
                java.lang.Throwable r6 = kotlin.Result.m1775exceptionOrNullimpl(r6)
                if (r6 == 0) goto L55
                com.hp.jipp.model.JobState r1 = com.p020hp.jipp.model.JobState.canceled
                r0.m650a(r1, r6)
            L55:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1759s0.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class b {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry$cancelJob$1", m1305f = "JobEntry.kt", m1306i = {0}, m1307l = {127}, m1308m = "invokeSuspend", m1309n = {"$this$launch"}, m1310s = {"L$0"})
    public static final class c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1728a;

        public Object f1729b;

        public final JobState f1731d;

        public final Throwable f1732e;

        public c(JobState jobState, Throwable th, Continuation<? super c> continuation) {
            super(2, continuation);
            this.f1731d = jobState;
            this.f1732e = th;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            c cVar = C1759s0.this.new c(this.f1731d, this.f1732e, continuation);
            cVar.f1729b = obj;
            return cVar;
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            c cVar = C1759s0.this.new c(this.f1731d, this.f1732e, continuation);
            cVar.f1729b = coroutineScope;
            return cVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1728a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope2 = (CoroutineScope) this.f1729b;
                try {
                    C1759s0.this.f1716d.f1148b.f870a.close();
                    Result.m1772constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.m1772constructorimpl(ResultKt.createFailure(th));
                }
                C1759s0 c1759s0 = C1759s0.this;
                if (!c1759s0.f1718f.f896f) {
                    this.f1729b = coroutineScope2;
                    this.f1728a = 1;
                    if (c1759s0.m649a(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                coroutineScope = coroutineScope2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.f1729b;
                ResultKt.throwOnFailure(obj);
            }
            C1759s0 c1759s02 = C1759s0.this;
            c1759s02.m651a(C1668b.m465a(c1759s02.f1718f, null, null, null, null, new C1668b.a(this.f1731d, this.f1732e, null, 4), 15));
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry", m1305f = "JobEntry.kt", m1306i = {0, 0, 0}, m1307l = {149}, m1308m = "cancelJobForResult", m1309n = {"this", "state", MqttServiceConstants.TRACE_EXCEPTION}, m1310s = {"L$0", "L$1", "L$2"})
    public static final class d extends ContinuationImpl {

        public Object f1733a;

        public Object f1734b;

        public Object f1735c;

        public Object f1736d;

        public int f1738f;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1736d = obj;
            this.f1738f |= Integer.MIN_VALUE;
            return C1759s0.this.m648a(null, null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry", m1305f = "JobEntry.kt", m1306i = {}, m1307l = {239, 244}, m1308m = "exchange", m1309n = {}, m1310s = {})
    public static final class e extends ContinuationImpl {

        public Object f1739a;

        public Object f1740b;

        public Object f1741c;

        public int f1742d;

        public Object f1743e;

        public int f1745g;

        public e(Continuation<? super e> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1743e = obj;
            this.f1745g |= Integer.MIN_VALUE;
            return C1759s0.m640a(C1759s0.this, (IppPacket.Builder) null, (C1662a) null, 0, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry", m1305f = "JobEntry.kt", m1306i = {0}, m1307l = {167}, m1308m = "sendCancel", m1309n = {"this"}, m1310s = {"L$0"})
    public static final class f extends ContinuationImpl {

        public Object f1746a;

        public Object f1747b;

        public Object f1748c;

        public int f1750e;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1748c = obj;
            this.f1750e |= Integer.MIN_VALUE;
            return C1759s0.this.m649a(this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobEntry", m1305f = "JobEntry.kt", m1306i = {0, 1, 2, 3, 4, 6}, m1307l = {89, 91, 92, 97, 102, 103, 108, 109}, m1308m = "startPrinting", m1309n = {"this", "this", "this", "this", "polling", "this"}, m1310s = {"L$0", "L$0", "L$0", "L$0", "L$0", "L$0"})
    public static final class g extends ContinuationImpl {

        public Object f1751a;

        public Object f1752b;

        public Object f1753c;

        public int f1755e;

        public g(Continuation<? super g> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1753c = obj;
            this.f1755e |= Integer.MIN_VALUE;
            return C1759s0.this.m652b(this);
        }
    }

    static {
        b bVar = new b();
        f1711k = bVar;
        f1712l = LoggerKt.logger(bVar);
    }

    public C1759s0(CoroutineContext context, InterfaceC1754r0 transport, C1705h0 formatter, InterfaceC1699g printers, C1680d request) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(transport, "transport");
        Intrinsics.checkNotNullParameter(formatter, "formatter");
        Intrinsics.checkNotNullParameter(printers, "printers");
        Intrinsics.checkNotNullParameter(request, "request");
        this.f1713a = transport;
        this.f1714b = formatter;
        this.f1715c = printers;
        this.f1716d = request;
        this.f1717e = context.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
        UUID uuidM486b = this.f1716d.m486b();
        MutableAttributeGroup mutableAttributeGroup = new MutableAttributeGroup(Tag.jobAttributes, null, 2, null);
        mutableAttributeGroup.put(Types.jobName.m440of(this.f1716d.m485a().m459a()));
        C1668b c1668b = new C1668b(null, uuidM486b, mutableAttributeGroup, null, null, 25);
        this.f1718f = c1668b;
        this.f1719g = c1668b.m467b();
        this.f1720h = MutexKt.Mutex(true);
        this.f1721i = StateFlowKt.MutableStateFlow(this.f1718f);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new a(null), 3, null);
    }

    public static final Object m640a(C1759s0 c1759s0, IppPacket.Builder builder, C1662a c1662a, int i, Continuation continuation) {
        return c1759s0.m647a((IppPacket.Builder) null, (C1662a) null, i, (Continuation<? super Boolean>) continuation);
    }

    public static Object m641a(C1759s0 c1759s0, IppPacket.Builder builder, C1662a c1662a, int i, Continuation continuation, int i2) {
        if ((i2 & 2) != 0) {
            c1662a = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return c1759s0.m647a(builder, c1662a, i, (Continuation<? super Boolean>) continuation);
    }

    public static Object m642a(C1759s0 c1759s0, JobState jobState, Throwable th, Continuation continuation, int i) {
        JobState jobState2 = (i & 1) != 0 ? JobState.canceled : null;
        if ((i & 2) != 0) {
            th = null;
        }
        return c1759s0.m648a(jobState2, th, continuation);
    }

    public static void m644a(C1759s0 c1759s0, JobState jobState, Throwable th, int i) {
        if ((i & 1) != 0) {
            jobState = JobState.canceled;
        }
        c1759s0.m650a(jobState, (Throwable) null);
    }

    public final C1687e m646a() {
        C1687e c1687e = this.f1722j;
        if (c1687e != null) {
            return c1687e;
        }
        Intrinsics.throwUninitializedPropertyAccessException(MqttCmdEnum.C2S_PRINTER_ERROR);
        return null;
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m648a(com.p020hp.jipp.model.JobState r12, java.lang.Throwable r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1759s0.m648a(com.hp.jipp.model.JobState, java.lang.Throwable, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void m650a(JobState state, Throwable th) {
        Intrinsics.checkNotNullParameter(state, "state");
        f1712l.invoke("cancel() " + this.f1718f + "; " + state + ' ' + th);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new c(state, th, null), 3, null);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m652b(kotlin.coroutines.Continuation<? super kotlin.Unit> r23) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 864
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1759s0.m652b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1717e;
    }

    public String toString() {
        return "PrintJobHandler(" + this.f1718f + ')';
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m647a(com.hp.jipp.encoding.IppPacket.Builder r32, com.p020hp.printsdk.C1662a r33, int r34, kotlin.coroutines.Continuation<? super java.lang.Boolean> r35) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 600
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1759s0.m647a(com.hp.jipp.encoding.IppPacket$Builder, com.hp.printsdk.a, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m649a(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) throws java.lang.Throwable {
        /*
            r11 = this;
            boolean r0 = r12 instanceof com.p020hp.printsdk.C1759s0.f
            if (r0 == 0) goto L13
            r0 = r12
            com.hp.printsdk.s0$f r0 = (com.p020hp.printsdk.C1759s0.f) r0
            int r1 = r0.f1750e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1750e = r1
            goto L18
        L13:
            com.hp.printsdk.s0$f r0 = new com.hp.printsdk.s0$f
            r0.<init>(r12)
        L18:
            r6 = r0
            java.lang.Object r12 = r6.f1748c
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.f1750e
            r2 = 1
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            java.lang.Object r0 = r6.f1747b
            java.lang.Integer r0 = (java.lang.Integer) r0
            java.lang.Object r0 = r6.f1746a
            com.hp.printsdk.s0 r0 = (com.p020hp.printsdk.C1759s0) r0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L32
            goto L85
        L32:
            r12 = move-exception
            goto L8e
        L34:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L3c:
            kotlin.ResultKt.throwOnFailure(r12)
            com.hp.printsdk.b r12 = r11.f1718f
            com.hp.jipp.encoding.AttributeGroup r12 = r12.f893c
            if (r12 == 0) goto Lb2
            com.hp.jipp.encoding.IntType r1 = com.p020hp.jipp.model.Types.jobId
            java.lang.Object r12 = r12.getValue(r1)
            java.lang.Integer r12 = (java.lang.Integer) r12
            if (r12 == 0) goto Lb2
            int r1 = r12.intValue()
            com.hp.printsdk.r0 r3 = r11.f1713a     // Catch: java.lang.Throwable -> L8c
            com.hp.printsdk.e r4 = r11.m646a()     // Catch: java.lang.Throwable -> L8c
            com.hp.printsdk.e r5 = r11.m646a()     // Catch: java.lang.Throwable -> L8c
            java.net.URI r5 = r5.m505c()     // Catch: java.lang.Throwable -> L8c
            com.hp.printsdk.g r7 = r11.f1715c     // Catch: java.lang.Throwable -> L8c
            java.lang.String r7 = r7.mo517a()     // Catch: java.lang.Throwable -> L8c
            com.hp.jipp.encoding.IppPacket$Builder r5 = com.p020hp.printsdk.InterfaceC1754r0.b.m624a(r5, r7, r1)     // Catch: java.lang.Throwable -> L8c
            r7 = 0
            r8 = 0
            r9 = 12
            r10 = 0
            r6.f1746a = r11     // Catch: java.lang.Throwable -> L8c
            r6.f1747b = r12     // Catch: java.lang.Throwable -> L8c
            r6.f1750e = r2     // Catch: java.lang.Throwable -> L8c
            r1 = r3
            r2 = r4
            r3 = r5
            r4 = r7
            r5 = r8
            r7 = r9
            r8 = r10
            java.lang.Object r12 = com.p020hp.printsdk.InterfaceC1754r0.b.m626a(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L8c
            if (r12 != r0) goto L84
            return r0
        L84:
            r0 = r11
        L85:
            com.hp.jipp.encoding.IppPacket r12 = (com.p020hp.jipp.encoding.IppPacket) r12     // Catch: java.lang.Throwable -> L32
            java.lang.Object r12 = kotlin.Result.m1772constructorimpl(r12)     // Catch: java.lang.Throwable -> L32
            goto L96
        L8c:
            r12 = move-exception
            r0 = r11
        L8e:
            java.lang.Object r12 = kotlin.ResultKt.createFailure(r12)
            java.lang.Object r12 = kotlin.Result.m1772constructorimpl(r12)
        L96:
            java.lang.Throwable r12 = kotlin.Result.m1775exceptionOrNullimpl(r12)
            if (r12 == 0) goto Lb2
            com.hp.mobile.common.utils.Logger r1 = com.p020hp.printsdk.C1759s0.f1712l
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Could not cancel job "
            r2.<init>(r3)
            com.hp.printsdk.b r0 = r0.f1718f
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            r1.m448e(r0, r12)
        Lb2:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.C1759s0.m649a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void m651a(C1668b c1668b) {
        if (Intrinsics.areEqual(c1668b, this.f1718f) || this.f1718f.f896f) {
            return;
        }
        this.f1718f = c1668b;
        f1712l.invoke("update() " + this.f1718f);
        this.f1721i.setValue(c1668b);
    }
}
