package com.p020hp.printsdk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.p020hp.jipp.model.JobState;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import com.p020hp.open.printsdk.HpPrintJob;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.printsdk.InterfaceC1754r0;
import java.io.Closeable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

public abstract class AbstractC1717j2 implements Closeable {

    public final CoroutineScope f1365a;

    public final InterfaceC1737n2 f1366b;

    public final Logger f1367c;

    public final HashMap<UUID, Long> f1368d;

    @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$capture$1$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {124, 143}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1369a;

        public final Function1<Continuation<? super Flow<? extends T>>, Object> f1370b;

        public final MutableLiveData<T> f1371c;

        public static final class C3362a<T> implements FlowCollector<T> {

            public final MutableLiveData f1372a;

            public C3362a(MutableLiveData mutableLiveData) {
                this.f1372a = mutableLiveData;
            }

            @Override
            public Object emit(T t, Continuation<? super Unit> continuation) {
                this.f1372a.setValue(t);
                return Unit.INSTANCE;
            }
        }

        public a(Function1<? super Continuation<? super Flow<? extends T>>, ? extends Object> function1, MutableLiveData<T> mutableLiveData, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f1370b = function1;
            this.f1371c = mutableLiveData;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new a(this.f1370b, this.f1371c, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new a(this.f1370b, this.f1371c, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.hp.printsdk.j2$a for r4v1 'this'  java.lang.Object
            	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
            	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
            	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
            	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
            	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
            */
        @Override
        public final java.lang.Object invokeSuspend(java.lang.Object r5) {
            /*
                r4 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r4.f1369a
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L1e
                if (r1 == r3) goto L1a
                if (r1 != r2) goto L12
                kotlin.ResultKt.throwOnFailure(r5)
                goto L3e
            L12:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L1a:
                kotlin.ResultKt.throwOnFailure(r5)
                goto L2c
            L1e:
                kotlin.ResultKt.throwOnFailure(r5)
                kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<? extends T>>, java.lang.Object> r5 = r4.f1370b
                r4.f1369a = r3
                java.lang.Object r5 = r5.invoke(r4)
                if (r5 != r0) goto L2c
                return r0
            L2c:
                kotlinx.coroutines.flow.Flow r5 = (kotlinx.coroutines.flow.Flow) r5
                androidx.lifecycle.MutableLiveData<T> r1 = r4.f1371c
                com.hp.printsdk.j2$a$a r3 = new com.hp.printsdk.j2$a$a
                r3.<init>(r1)
                r4.f1369a = r2
                java.lang.Object r5 = r5.collect(r3, r4)
                if (r5 != r0) goto L3e
                return r0
            L3e:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1717j2.a.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$refreshPrinter$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {114, 114, 115}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    public static final class b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        public int f1373a;

        public final UUID f1375c;

        public final List<String> f1376d;

        public final Function1<HpPrinter, Unit> f1377e;

        @DebugMetadata(m1304c = "com.hp.printsdk.base.BasePrintService$refreshPrinter$1$1", m1305f = "BasePrintService.kt", m1306i = {}, m1307l = {}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

            public final Function1<HpPrinter, Unit> f1378a;

            public final C1687e f1379b;

            public a(Function1<? super HpPrinter, Unit> function1, C1687e c1687e, Continuation<? super a> continuation) {
                super(2, continuation);
                this.f1378a = function1;
                this.f1379b = c1687e;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new a(this.f1378a, this.f1379b, continuation);
            }

            @Override
            public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return new a(this.f1378a, this.f1379b, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                ResultKt.throwOnFailure(obj);
                Function1<HpPrinter, Unit> function1 = this.f1378a;
                C1687e c1687e = this.f1379b;
                function1.invoke(c1687e != null ? InterfaceC1754r0.b.m625a(c1687e) : null);
                return Unit.INSTANCE;
            }
        }

        public b(UUID uuid, List<String> list, Function1<? super HpPrinter, Unit> function1, Continuation<? super b> continuation) {
            super(2, continuation);
            this.f1375c = uuid;
            this.f1376d = list;
            this.f1377e = function1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AbstractC1717j2.this.new b(this.f1375c, this.f1376d, this.f1377e, continuation);
        }

        @Override
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return AbstractC1717j2.this.new b(this.f1375c, this.f1376d, this.f1377e, continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.f1373a
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L25
                if (r1 == r4) goto L21
                if (r1 == r3) goto L1d
                if (r1 != r2) goto L15
                kotlin.ResultKt.throwOnFailure(r7)
                goto L59
            L15:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1d:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L42
            L21:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L33
            L25:
                kotlin.ResultKt.throwOnFailure(r7)
                com.hp.printsdk.j2 r7 = com.p020hp.printsdk.AbstractC1717j2.this
                r6.f1373a = r4
                java.lang.Object r7 = r7.mo561a(r6)
                if (r7 != r0) goto L33
                return r0
            L33:
                com.hp.printsdk.g r7 = (com.p020hp.printsdk.InterfaceC1699g) r7
                java.util.UUID r1 = r6.f1375c
                java.util.List<java.lang.String> r4 = r6.f1376d
                r6.f1373a = r3
                java.lang.Object r7 = r7.mo515a(r1, r4, r6)
                if (r7 != r0) goto L42
                return r0
            L42:
                com.hp.printsdk.e r7 = (com.p020hp.printsdk.C1687e) r7
                kotlinx.coroutines.MainCoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getMain()
                com.hp.printsdk.j2$b$a r3 = new com.hp.printsdk.j2$b$a
                kotlin.jvm.functions.Function1<com.hp.open.printsdk.HpPrinter, kotlin.Unit> r4 = r6.f1377e
                r5 = 0
                r3.<init>(r4, r7, r5)
                r6.f1373a = r2
                java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r6)
                if (r7 != r0) goto L59
                return r0
            L59:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.printsdk.AbstractC1717j2.b.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final class c extends Lambda implements Function1<HpPrinter, Unit> {

        public final Continuation<HpPrintJob> f1380a;

        public final C1668b f1381b;

        public final AbstractC1717j2 f1382c;

        public c(Continuation<? super HpPrintJob> continuation, C1668b c1668b, AbstractC1717j2 abstractC1717j2) {
            super(1);
            this.f1380a = continuation;
            this.f1381b = c1668b;
            this.f1382c = abstractC1717j2;
        }

        public final void m563a(HpPrinter hpPrinter) {
            List<String> listEmptyList;
            if (hpPrinter == null || (listEmptyList = hpPrinter.getIppStateReasons()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            Continuation<HpPrintJob> continuation = this.f1380a;
            C1668b c1668b = this.f1381b;
            UUID uuid = c1668b.f891a;
            continuation.resumeWith(Result.m1772constructorimpl(new C1772u3(uuid, c1668b, listEmptyList, this.f1382c.f1368d.get(uuid))));
        }

        @Override
        public Unit invoke(HpPrinter hpPrinter) {
            m563a(hpPrinter);
            return Unit.INSTANCE;
        }
    }

    public AbstractC1717j2(CoroutineScope scope, InterfaceC1737n2 localRepo) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(localRepo, "localRepo");
        this.f1365a = scope;
        this.f1366b = localRepo;
        this.f1367c = LoggerKt.logger(this);
        this.f1368d = new HashMap<>();
    }

    public final <T> LiveData<T> m559a(Function1<? super Continuation<? super Flow<? extends T>>, ? extends Object> function1) {
        MutableLiveData mutableLiveData = new MutableLiveData();
        BuildersKt__Builders_commonKt.launch$default(this.f1365a, null, null, new a(function1, mutableLiveData, null), 3, null);
        return mutableLiveData;
    }

    public final Object m560a(C1668b c1668b, Continuation<? super HpPrintJob> continuation) throws Throwable {
        if (!Intrinsics.areEqual(c1668b.m470e(), JobState.pendingHeld) && !Intrinsics.areEqual(c1668b.m470e(), JobState.processingStopped)) {
            UUID uuid = c1668b.f891a;
            return new C1772u3(uuid, c1668b, null, this.f1368d.get(uuid), 4);
        }
        SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt.intercepted(continuation));
        m562a(c1668b.f892b, C1740o0.f1552a, new c(safeContinuation, c1668b, this));
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }

    public abstract Object mo561a(Continuation<? super InterfaceC1699g> continuation);

    public void m562a(UUID printerId, List<String> requested, Function1<? super HpPrinter, Unit> callback) {
        Intrinsics.checkNotNullParameter(printerId, "printerId");
        Intrinsics.checkNotNullParameter(requested, "requested");
        Intrinsics.checkNotNullParameter(callback, "callback");
        BuildersKt__Builders_commonKt.launch$default(this.f1365a, Dispatchers.getIO(), null, new b(printerId, requested, callback, null), 2, null);
    }
}
