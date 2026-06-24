package androidx.core.os;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(m1292d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, m1293d2 = {"<anonymous>", "", "run"}, m1294k = 3, m1295mv = {1, 7, 1}, m1297xi = 176)
public final class HandlerKt$postAtTime$runnable$1 implements Runnable {
    final Function0<Unit> $action;

    public HandlerKt$postAtTime$runnable$1(Function0<Unit> function0) {
        this.$action = function0;
    }

    @Override
    public final void run() {
        this.$action.invoke();
    }
}
