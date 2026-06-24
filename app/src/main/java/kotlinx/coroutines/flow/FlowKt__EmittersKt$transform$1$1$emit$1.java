package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__EmittersKt;

@Metadata(m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 176)
@DebugMetadata(m1304c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1", m1305f = "Emitters.kt", m1306i = {}, m1307l = {42}, m1308m = "emit", m1309n = {}, m1310s = {})
public final class FlowKt__EmittersKt$transform$1$1$emit$1 extends ContinuationImpl {
    int label;
    Object result;
    final FlowKt__EmittersKt.C26641.AnonymousClass1<T> this$0;

    public FlowKt__EmittersKt$transform$1$1$emit$1(FlowKt__EmittersKt.C26641.AnonymousClass1<? super T> anonymousClass1, Continuation<? super FlowKt__EmittersKt$transform$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
