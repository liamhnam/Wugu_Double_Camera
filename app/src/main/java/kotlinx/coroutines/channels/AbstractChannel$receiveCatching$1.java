package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(m1294k = 3, m1295mv = {1, 6, 0}, m1297xi = 48)
@DebugMetadata(m1304c = "kotlinx.coroutines.channels.AbstractChannel", m1305f = "AbstractChannel.kt", m1306i = {}, m1307l = {633}, m1308m = "receiveCatching-JP2dKIU", m1309n = {}, m1310s = {})
final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {
    int label;
    Object result;
    final AbstractChannel<E> this$0;

    AbstractChannel$receiveCatching$1(AbstractChannel<E> abstractChannel, Continuation<? super AbstractChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    @Override
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo3299receiveCatchingJP2dKIU = this.this$0.mo3299receiveCatchingJP2dKIU(this);
        return objMo3299receiveCatchingJP2dKIU == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo3299receiveCatchingJP2dKIU : ChannelResult.m3306boximpl(objMo3299receiveCatchingJP2dKIU);
    }
}
