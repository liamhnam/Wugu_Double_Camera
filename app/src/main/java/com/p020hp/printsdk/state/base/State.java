package com.p020hp.printsdk.state.base;

import com.p020hp.open.printsdk.state.Reason;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/printsdk/state/base/State;", "T", "Lcom/hp/open/printsdk/state/Reason;", "", "reasons", "", "(Ljava/util/List;)V", "getReasons", "()Ljava/util/List;", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public class State<T extends Reason> {

    public final List<T> f1783a;

    public State(List<? extends T> reasons) {
        Intrinsics.checkNotNullParameter(reasons, "reasons");
        this.f1783a = reasons;
    }

    public final List<T> getReasons() {
        return this.f1783a;
    }

    public String toString() {
        return getClass().getSimpleName() + "(reasons: " + this.f1783a + ')';
    }
}
