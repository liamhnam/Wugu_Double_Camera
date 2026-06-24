package com.p020hp.printsdk;

import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

public abstract class AbstractC1774v0 implements CoroutineScope {

    public static final b f1882g;

    public static final Logger f1883h;

    public static final Exception f1884i;

    public final InterfaceC1699g f1885a;

    public final UUID f1886b;

    public final CoroutineContext f1887c;

    public final C1676c1<UUID, C1668b> f1888d;

    public final List<C1759s0> f1889e;

    public final Channel<Boolean> f1890f;

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue$1", m1305f = "JobQueue.kt", m1306i = {}, m1307l = {47}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1891a;

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AbstractC1774v0.this.new a(continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return AbstractC1774v0.this.new a(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1891a;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AbstractC1774v0 abstractC1774v0 = AbstractC1774v0.this;
                this.f1891a = 1;
                if (abstractC1774v0.m670a(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public static final class b {
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue", m1305f = "JobQueue.kt", m1306i = {0}, m1307l = {111}, m1308m = "cancel", m1309n = {"jobId"}, m1310s = {"L$0"})
    public static final class c extends ContinuationImpl {

        public Object f1893a;

        public Object f1894b;

        public Object f1895c;

        public int f1897e;

        public c(Continuation<? super c> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1895c = obj;
            this.f1897e |= Integer.MIN_VALUE;
            return AbstractC1774v0.this.m669a((UUID) null, this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue", m1305f = "JobQueue.kt", m1306i = {0, 1, 2, 2}, m1307l = {67, 69, 74}, m1308m = "processJobQueue", m1309n = {"this", "this", "this", "entry"}, m1310s = {"L$0", "L$0", "L$0", "L$2"})
    public static final class d extends ContinuationImpl {

        public Object f1898a;

        public Object f1899b;

        public Object f1900c;

        public Object f1901d;

        public int f1903f;

        public d(Continuation<? super d> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1901d = obj;
            this.f1903f |= Integer.MIN_VALUE;
            return AbstractC1774v0.this.m670a(this);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue$processJobQueue$2$1", m1305f = "JobQueue.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class e extends SuspendLambda implements Function2<C1668b, Continuation<? super Boolean>, Object> {

        public Object f1904a;

        public e(Continuation<? super e> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            e eVar = new e(continuation);
            eVar.f1904a = obj;
            return eVar;
        }

        @Override
        public Object invoke(C1668b c1668b, Continuation<? super Boolean> continuation) {
            return ((e) create(c1668b, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(((C1668b) this.f1904a).f896f);
        }
    }

    @DebugMetadata(m1304c = "com.hp.bgp.ipp.JobQueue", m1305f = "JobQueue.kt", m1306i = {0, 1, 1, 2}, m1307l = {85, 89, 91}, m1308m = "waitIdle", m1309n = {"this", "this", MqttCmdEnum.C2S_PRINTER_ERROR, "this"}, m1310s = {"L$0", "L$0", "L$1", "L$0"})
    public static final class f extends ContinuationImpl {

        public Object f1905a;

        public Object f1906b;

        public Object f1907c;

        public int f1909e;

        public f(Continuation<? super f> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.f1907c = obj;
            this.f1909e |= Integer.MIN_VALUE;
            return AbstractC1774v0.this.m671b(this);
        }
    }

    static {
        b bVar = new b();
        f1882g = bVar;
        f1883h = LoggerKt.logger(bVar);
        f1884i = new Exception("cancelled by user");
    }

    public AbstractC1774v0(CoroutineContext context, InterfaceC1699g printers, UUID printerId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(printers, "printers");
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        this.f1885a = printers;
        this.f1886b = printerId;
        this.f1887c = context.plus(JobKt__JobKt.Job$default((Job) null, 1, (Object) null));
        this.f1888d = new C1676c1<>();
        this.f1889e = new ArrayList();
        this.f1890f = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
        BuildersKt__Builders_commonKt.launch$default(this, null, null, new a(null), 3, null);
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m669a(java.util.UUID r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.p020hp.printsdk.AbstractC1774v0.c
            if (r0 == 0) goto L13
            r0 = r9
            com.hp.printsdk.v0$c r0 = (com.p020hp.printsdk.AbstractC1774v0.c) r0
            int r1 = r0.f1897e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1897e = r1
            goto L18
        L13:
            com.hp.printsdk.v0$c r0 = new com.hp.printsdk.v0$c
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.f1895c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1897e
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r8 = r0.f1894b
            com.hp.printsdk.s0 r8 = (com.p020hp.printsdk.C1759s0) r8
            java.lang.Object r0 = r0.f1893a
            java.util.UUID r0 = (java.util.UUID) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L84
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.List<com.hp.printsdk.s0> r9 = r7.f1889e
            java.util.Iterator r9 = r9.iterator()
        L42:
            boolean r2 = r9.hasNext()
            r4 = 0
            if (r2 == 0) goto L59
            java.lang.Object r2 = r9.next()
            r5 = r2
            com.hp.printsdk.s0 r5 = (com.p020hp.printsdk.C1759s0) r5
            java.util.UUID r5 = r5.f1719g
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r8)
            if (r5 == 0) goto L42
            goto L5a
        L59:
            r2 = r4
        L5a:
            r9 = r2
            com.hp.printsdk.s0 r9 = (com.p020hp.printsdk.C1759s0) r9
            if (r9 == 0) goto L87
            com.hp.mobile.common.utils.Logger r2 = com.p020hp.printsdk.AbstractC1774v0.f1883h
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "cancel() "
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r5 = r5.toString()
            r2.invoke(r5)
            java.lang.Exception r2 = com.p020hp.printsdk.AbstractC1774v0.f1884i
            r0.f1893a = r8
            r0.f1894b = r9
            r0.f1897e = r3
            java.lang.Object r0 = com.p020hp.printsdk.C1759s0.m642a(r9, r4, r2, r0, r3)
            if (r0 != r1) goto L82
            return r1
        L82:
            r0 = r8
            r8 = r9
        L84:
            if (r8 != 0) goto L9b
            r8 = r0
        L87:
            com.hp.mobile.common.utils.Logger r9 = com.p020hp.printsdk.AbstractC1774v0.f1883h
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "cancel(): Nothing in queue for "
            r0.<init>(r1)
            java.lang.StringBuilder r8 = r0.append(r8)
            java.lang.String r8 = r8.toString()
            r9.invoke(r8)
        L9b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1774v0.m669a(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m671b(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.p020hp.printsdk.AbstractC1774v0.f
            if (r0 == 0) goto L13
            r0 = r10
            com.hp.printsdk.v0$f r0 = (com.p020hp.printsdk.AbstractC1774v0.f) r0
            int r1 = r0.f1909e
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f1909e = r1
            goto L18
        L13:
            com.hp.printsdk.v0$f r0 = new com.hp.printsdk.v0$f
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.f1907c
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.f1909e
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L48
            if (r2 == r5) goto L40
            if (r2 == r4) goto L34
            if (r2 != r3) goto L2c
            goto L40
        L2c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L34:
            java.lang.Object r2 = r0.f1906b
            com.hp.printsdk.e r2 = (com.p020hp.printsdk.C1687e) r2
            java.lang.Object r6 = r0.f1905a
            com.hp.printsdk.v0 r6 = (com.p020hp.printsdk.AbstractC1774v0) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L8b
        L40:
            java.lang.Object r2 = r0.f1905a
            com.hp.printsdk.v0 r2 = (com.p020hp.printsdk.AbstractC1774v0) r2
            kotlin.ResultKt.throwOnFailure(r10)
            goto L5d
        L48:
            kotlin.ResultKt.throwOnFailure(r10)
            com.hp.printsdk.g r10 = r9.f1885a
            java.util.UUID r2 = r9.f1886b
            java.util.List<java.lang.String> r6 = com.p020hp.printsdk.C1740o0.f1552a
            r0.f1905a = r9
            r0.f1909e = r5
            java.lang.Object r10 = r10.mo515a(r2, r6, r0)
            if (r10 != r1) goto L5c
            return r1
        L5c:
            r2 = r9
        L5d:
            com.hp.printsdk.e r10 = (com.p020hp.printsdk.C1687e) r10
            r6 = r2
            r2 = r10
        L61:
            java.util.List<com.hp.printsdk.s0> r10 = r6.f1889e
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r5
            if (r10 == 0) goto Laa
            if (r2 == 0) goto L7c
            com.hp.jipp.encoding.AttributeGroup r10 = r2.f1179a
            com.hp.jipp.model.PrinterState$Type r7 = com.p020hp.jipp.model.Types.printerState
            java.lang.Object r10 = r10.getValue(r7)
            com.hp.jipp.model.PrinterState r7 = com.p020hp.jipp.model.PrinterState.idle
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r7)
            if (r10 != 0) goto Laa
        L7c:
            r0.f1905a = r6
            r0.f1906b = r2
            r0.f1909e = r4
            r7 = 2000(0x7d0, double:9.88E-321)
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r7, r0)
            if (r10 != r1) goto L8b
            return r1
        L8b:
            java.util.List<com.hp.printsdk.s0> r10 = r6.f1889e
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r5
            if (r10 == 0) goto L61
            com.hp.printsdk.g r10 = r6.f1885a
            java.util.UUID r2 = r6.f1886b
            java.util.List<java.lang.String> r7 = com.p020hp.printsdk.C1740o0.f1552a
            r0.f1905a = r6
            r8 = 0
            r0.f1906b = r8
            r0.f1909e = r3
            java.lang.Object r10 = r10.mo515a(r2, r7, r0)
            if (r10 != r1) goto La8
            return r1
        La8:
            r2 = r6
            goto L5d
        Laa:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1774v0.m671b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override
    public CoroutineContext getCoroutineContext() {
        return this.f1887c;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m670a(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1774v0.m670a(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
