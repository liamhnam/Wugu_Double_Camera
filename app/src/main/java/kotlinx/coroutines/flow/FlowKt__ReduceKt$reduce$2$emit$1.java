package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__ReduceKt;

@Metadata(m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "kotlinx.coroutines.flow.FlowKt__ReduceKt$reduce$2", m1305f = "Reduce.kt", m1306i = {}, m1307l = {25}, m1308m = "emit", m1309n = {}, m1310s = {})
final class FlowKt__ReduceKt$reduce$2$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    Object result;
    final FlowKt__ReduceKt.C26992<T> this$0;

    FlowKt__ReduceKt$reduce$2$emit$1(FlowKt__ReduceKt.C26992<? super T> c26992, Continuation<? super FlowKt__ReduceKt$reduce$2$emit$1> continuation) {
        super(continuation);
        this.this$0 = c26992;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
