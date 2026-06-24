package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.apache.log4j.net.SyslogAppender;

@Metadata(m1292d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\r\u0012\t\u0012\u0007H\u0002¢\u0006\u0002\b\u00040\u0003H\u008a@"}, m1293d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/jvm/JvmSuppressWildcards;"}, m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "androidx.room.CoroutinesRoom$Companion$createFlow$1", m1305f = "CoroutinesRoom.kt", m1306i = {}, m1307l = {110}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
final class CoroutinesRoom$Companion$createFlow$1<R> extends SuspendLambda implements Function2<FlowCollector<R>, Continuation<? super Unit>, Object> {
    final Callable<R> $callable;
    final RoomDatabase $db;
    final boolean $inTransaction;
    final String[] $tableNames;
    private Object L$0;
    int label;

    CoroutinesRoom$Companion$createFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Callable<R> callable, Continuation<? super CoroutinesRoom$Companion$createFlow$1> continuation) {
        super(2, continuation);
        this.$inTransaction = z;
        this.$db = roomDatabase;
        this.$tableNames = strArr;
        this.$callable = callable;
    }

    @Override
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CoroutinesRoom$Companion$createFlow$1 coroutinesRoom$Companion$createFlow$1 = new CoroutinesRoom$Companion$createFlow$1(this.$inTransaction, this.$db, this.$tableNames, this.$callable, continuation);
        coroutinesRoom$Companion$createFlow$1.L$0 = obj;
        return coroutinesRoom$Companion$createFlow$1;
    }

    @Override
    public final Object invoke(FlowCollector<R> flowCollector, Continuation<? super Unit> continuation) {
        return ((CoroutinesRoom$Companion$createFlow$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(m1292d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m1293d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
    @DebugMetadata(m1304c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1", m1305f = "CoroutinesRoom.kt", m1306i = {}, m1307l = {SyslogAppender.LOG_LOCAL1}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
    static final class C05611 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final FlowCollector<R> $$this$flow;
        final Callable<R> $callable;
        final RoomDatabase $db;
        final boolean $inTransaction;
        final String[] $tableNames;
        private Object L$0;
        int label;

        C05611(boolean z, RoomDatabase roomDatabase, FlowCollector<R> flowCollector, String[] strArr, Callable<R> callable, Continuation<? super C05611> continuation) {
            super(2, continuation);
            this.$inTransaction = z;
            this.$db = roomDatabase;
            this.$$this$flow = flowCollector;
            this.$tableNames = strArr;
            this.$callable = callable;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05611 c05611 = new C05611(this.$inTransaction, this.$db, this.$$this$flow, this.$tableNames, this.$callable, continuation);
            c05611.L$0 = obj;
            return c05611;
        }

        @Override
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05611) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final Channel channelChannel$default = ChannelKt.Channel$default(-1, null, null, 6, null);
                final String[] strArr = this.$tableNames;
                ?? r7 = new InvalidationTracker.Observer(strArr, channelChannel$default) {
                    final Channel<Unit> $observerChannel;
                    final String[] $tableNames;

                    {
                        super(strArr);
                        this.$tableNames = strArr;
                        this.$observerChannel = channelChannel$default;
                    }

                    @Override
                    public void onInvalidated(Set<String> tables) {
                        Intrinsics.checkNotNullParameter(tables, "tables");
                        this.$observerChannel.mo3301trySendJP2dKIU(Unit.INSTANCE);
                    }
                };
                channelChannel$default.mo3301trySendJP2dKIU(Unit.INSTANCE);
                TransactionElement transactionElement = (TransactionElement) coroutineScope.getCoroutineContext().get(TransactionElement.INSTANCE);
                CoroutineDispatcher transactionDispatcher = transactionElement == null ? null : transactionElement.getTransactionDispatcher();
                if (transactionDispatcher == null) {
                    transactionDispatcher = this.$inTransaction ? CoroutinesRoomKt.getTransactionDispatcher(this.$db) : CoroutinesRoomKt.getQueryDispatcher(this.$db);
                }
                Channel channelChannel$default2 = ChannelKt.Channel$default(0, null, null, 7, null);
                BuildersKt__Builders_commonKt.launch$default(coroutineScope, transactionDispatcher, null, new AnonymousClass1(this.$db, r7, channelChannel$default, this.$callable, channelChannel$default2, null), 2, null);
                this.label = 1;
                if (FlowKt.emitAll(this.$$this$flow, channelChannel$default2, this) == coroutine_suspended) {
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

        @Metadata(m1292d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m1293d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
        @DebugMetadata(m1304c = "androidx.room.CoroutinesRoom$Companion$createFlow$1$1$1", m1305f = "CoroutinesRoom.kt", m1306i = {}, m1307l = {127, 129}, m1308m = "invokeSuspend", m1309n = {}, m1310s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final Callable<R> $callable;
            final RoomDatabase $db;
            final CoroutinesRoom$Companion$createFlow$1$1$observer$1 $observer;
            final Channel<Unit> $observerChannel;
            final Channel<R> $resultChannel;
            Object L$0;
            int label;

            AnonymousClass1(RoomDatabase roomDatabase, CoroutinesRoom$Companion$createFlow$1$1$observer$1 coroutinesRoom$Companion$createFlow$1$1$observer$1, Channel<Unit> channel, Callable<R> callable, Channel<R> channel2, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$db = roomDatabase;
                this.$observer = coroutinesRoom$Companion$createFlow$1$1$observer$1;
                this.$observerChannel = channel;
                this.$callable = callable;
                this.$resultChannel = channel2;
            }

            @Override
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.$db, this.$observer, this.$observerChannel, this.$callable, this.$resultChannel, continuation);
            }

            @Override
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r9) throws java.lang.Throwable {
                /*
                    r8 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r8.label
                    r2 = 2
                    r3 = 1
                    if (r1 == 0) goto L29
                    if (r1 == r3) goto L1f
                    if (r1 != r2) goto L17
                    java.lang.Object r1 = r8.L$0
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L86
                    r9 = r1
                    goto L3f
                L17:
                    java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r9.<init>(r0)
                    throw r9
                L1f:
                    java.lang.Object r1 = r8.L$0
                    kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                    kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L86
                    r4 = r1
                    r1 = r8
                    goto L51
                L29:
                    kotlin.ResultKt.throwOnFailure(r9)
                    androidx.room.RoomDatabase r9 = r8.$db
                    androidx.room.InvalidationTracker r9 = r9.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r8.$observer
                    androidx.room.InvalidationTracker$Observer r1 = (androidx.room.InvalidationTracker.Observer) r1
                    r9.addObserver(r1)
                    kotlinx.coroutines.channels.Channel<kotlin.Unit> r9 = r8.$observerChannel     // Catch: java.lang.Throwable -> L86
                    kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()     // Catch: java.lang.Throwable -> L86
                L3f:
                    r1 = r8
                L40:
                    r4 = r1
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.lang.Throwable -> L84
                    r1.L$0 = r9     // Catch: java.lang.Throwable -> L84
                    r1.label = r3     // Catch: java.lang.Throwable -> L84
                    java.lang.Object r4 = r9.hasNext(r4)     // Catch: java.lang.Throwable -> L84
                    if (r4 != r0) goto L4e
                    return r0
                L4e:
                    r7 = r4
                    r4 = r9
                    r9 = r7
                L51:
                    java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L84
                    boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L84
                    if (r9 == 0) goto L74
                    r4.next()     // Catch: java.lang.Throwable -> L84
                    java.util.concurrent.Callable<R> r9 = r1.$callable     // Catch: java.lang.Throwable -> L84
                    java.lang.Object r9 = r9.call()     // Catch: java.lang.Throwable -> L84
                    kotlinx.coroutines.channels.Channel<R> r5 = r1.$resultChannel     // Catch: java.lang.Throwable -> L84
                    r6 = r1
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch: java.lang.Throwable -> L84
                    r1.L$0 = r4     // Catch: java.lang.Throwable -> L84
                    r1.label = r2     // Catch: java.lang.Throwable -> L84
                    java.lang.Object r9 = r5.send(r9, r6)     // Catch: java.lang.Throwable -> L84
                    if (r9 != r0) goto L72
                    return r0
                L72:
                    r9 = r4
                    goto L40
                L74:
                    androidx.room.RoomDatabase r9 = r1.$db
                    androidx.room.InvalidationTracker r9 = r9.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r0 = r1.$observer
                    androidx.room.InvalidationTracker$Observer r0 = (androidx.room.InvalidationTracker.Observer) r0
                    r9.removeObserver(r0)
                    kotlin.Unit r9 = kotlin.Unit.INSTANCE
                    return r9
                L84:
                    r9 = move-exception
                    goto L88
                L86:
                    r9 = move-exception
                    r1 = r8
                L88:
                    androidx.room.RoomDatabase r0 = r1.$db
                    androidx.room.InvalidationTracker r0 = r0.getInvalidationTracker()
                    androidx.room.CoroutinesRoom$Companion$createFlow$1$1$observer$1 r1 = r1.$observer
                    androidx.room.InvalidationTracker$Observer r1 = (androidx.room.InvalidationTracker.Observer) r1
                    r0.removeObserver(r1)
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.room.CoroutinesRoom$Companion$createFlow$1.C05611.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    @Override
    public final Object invokeSuspend(Object obj) throws Throwable {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new C05611(this.$inTransaction, this.$db, flowCollector, this.$tableNames, this.$callable, null), this) == coroutine_suspended) {
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
