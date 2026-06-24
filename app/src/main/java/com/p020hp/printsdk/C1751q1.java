package com.p020hp.printsdk;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1751q1 extends Lambda implements Function1<AbstractC1755r1, Iterable<? extends AbstractC1760s1>> {

    public static final C1751q1 f1623a = new C1751q1();

    public C1751q1() {
        super(1);
    }

    @Override
    public final Iterable<AbstractC1760s1> invoke(AbstractC1755r1 it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return CollectionsKt.reversed(it);
    }
}
