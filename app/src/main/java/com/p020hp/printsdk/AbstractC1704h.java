package com.p020hp.printsdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

public abstract class AbstractC1704h extends AbstractC1755r1 implements AutoCloseable {

    public final List<Function0<Unit>> f1222b = new ArrayList();

    public final AbstractC1704h m526a(List<IntRange> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        return values.isEmpty() ? this : new C1719k(this, values);
    }

    public final void m527a(Function0<Unit> func) {
        Intrinsics.checkNotNullParameter(func, "func");
        this.f1222b.add(func);
    }

    @Override
    public final void close() {
        Iterator<T> it = this.f1222b.iterator();
        while (it.hasNext()) {
            ((Function0) it.next()).invoke();
        }
    }

    public abstract int getPageCount();
}
