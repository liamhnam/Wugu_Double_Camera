package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.CancellableFlowImpl;

@Metadata(m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "kotlinx.coroutines.flow.CancellableFlowImpl$collect$2", m1305f = "Context.kt", m1306i = {}, m1307l = {275}, m1308m = "emit", m1309n = {}, m1310s = {})
final class CancellableFlowImpl$collect$2$emit$1 extends ContinuationImpl {
    int label;
    Object result;
    final CancellableFlowImpl.C26342<T> this$0;

    CancellableFlowImpl$collect$2$emit$1(CancellableFlowImpl.C26342<? super T> c26342, Continuation<? super CancellableFlowImpl$collect$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = c26342;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
