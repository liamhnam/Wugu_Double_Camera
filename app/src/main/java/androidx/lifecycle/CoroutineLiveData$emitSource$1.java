package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(m1294k = 3, m1295mv = {1, 8, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "androidx.lifecycle.CoroutineLiveData", m1305f = "CoroutineLiveData.kt", m1306i = {0, 0, 1}, m1307l = {228, 229}, m1308m = "emitSource$lifecycle_livedata_ktx_release", m1309n = {"this", "source", "this"}, m1310s = {"L$0", "L$1", "L$0"})
final class CoroutineLiveData$emitSource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    Object result;
    final CoroutineLiveData<T> this$0;

    CoroutineLiveData$emitSource$1(CoroutineLiveData<T> coroutineLiveData, Continuation<? super CoroutineLiveData$emitSource$1> continuation) {
        super(continuation);
        this.this$0 = coroutineLiveData;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emitSource$lifecycle_livedata_ktx_release(null, this);
    }
}
