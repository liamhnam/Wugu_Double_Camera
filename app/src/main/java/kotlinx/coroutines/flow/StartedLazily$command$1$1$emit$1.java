package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.StartedLazily;

@Metadata(m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "kotlinx.coroutines.flow.StartedLazily$command$1$1", m1305f = "SharingStarted.kt", m1306i = {}, m1307l = {158}, m1308m = "emit", m1309n = {}, m1310s = {})
final class StartedLazily$command$1$1$emit$1 extends ContinuationImpl {
    int label;
    Object result;
    final StartedLazily.C27351.AnonymousClass1<T> this$0;

    StartedLazily$command$1$1$emit$1(StartedLazily.C27351.AnonymousClass1<? super T> anonymousClass1, Continuation<? super StartedLazily$command$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(0, this);
    }
}
