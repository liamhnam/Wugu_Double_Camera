package com.p020hp.printsdk;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

public final class C1746p1 extends Lambda implements Function1<AbstractC1755r1, Iterable<? extends AbstractC1760s1>> {

    public final int f1572a;

    public final AbstractC1755r1 f1573b;

    @DebugMetadata(m1304c = "com.hp.jipp.pdl.RenderableDocument$Companion$handleCopies$1$1", m1305f = "RenderableDocument.kt", m1306i = {0}, m1307l = {54}, m1308m = "invokeSuspend", m1309n = {"$this$sequence"}, m1310s = {"L$0"})
    public static final class a extends RestrictedSuspendLambda implements Function2<SequenceScope<? super AbstractC1760s1>, Continuation<? super Unit>, Object> {

        public Object f1574a;

        public Object f1575b;

        public int f1576c;

        public Object f1577d;

        public final int f1578e;

        public final AbstractC1755r1 f1579f;

        public a(int i, AbstractC1755r1 abstractC1755r1, Continuation<? super a> continuation) {
            super(2, continuation);
            this.f1578e = i;
            this.f1579f = abstractC1755r1;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            a aVar = new a(this.f1578e, this.f1579f, continuation);
            aVar.f1577d = obj;
            return aVar;
        }

        @Override
        public Object invoke(SequenceScope<? super AbstractC1760s1> sequenceScope, Continuation<? super Unit> continuation) {
            a aVar = new a(this.f1578e, this.f1579f, continuation);
            aVar.f1577d = sequenceScope;
            return aVar.invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            SequenceScope sequenceScope;
            AbstractC1755r1 abstractC1755r1;
            Iterator<Integer> it;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.f1576c;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                sequenceScope = (SequenceScope) this.f1577d;
                IntRange intRangeUntil = RangesKt.until(0, this.f1578e);
                abstractC1755r1 = this.f1579f;
                it = intRangeUntil.iterator();
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (Iterator) this.f1575b;
                abstractC1755r1 = (AbstractC1755r1) this.f1574a;
                sequenceScope = (SequenceScope) this.f1577d;
                ResultKt.throwOnFailure(obj);
            }
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                this.f1577d = sequenceScope;
                this.f1574a = abstractC1755r1;
                this.f1575b = it;
                this.f1576c = 1;
                if (sequenceScope.yieldAll(abstractC1755r1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    public C1746p1(int i, AbstractC1755r1 abstractC1755r1) {
        super(1);
        this.f1572a = i;
        this.f1573b = abstractC1755r1;
    }

    @Override
    public final Iterable<AbstractC1760s1> invoke(AbstractC1755r1 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return SequencesKt.asIterable(SequencesKt.sequence(new a(this.f1572a, this.f1573b, null)));
    }
}
