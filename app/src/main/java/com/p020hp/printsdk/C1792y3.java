package com.p020hp.printsdk;

import androidx.lifecycle.MutableLiveData;
import com.p020hp.open.printsdk.HpPrinter;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

public final class C1792y3 extends Lambda implements Function1<List<? extends HpPrinter>, Unit> {

    public final C1787x3 f2041a;

    public final MutableLiveData<List<HpPrinter>> f2042b;

    public C1792y3(C1787x3 c1787x3, MutableLiveData<List<HpPrinter>> mutableLiveData) {
        super(1);
        this.f2041a = c1787x3;
        this.f2042b = mutableLiveData;
    }

    @Override
    public Unit invoke(List<? extends HpPrinter> list) {
        List<? extends HpPrinter> it = list;
        Intrinsics.checkNotNullParameter(it, "it");
        this.f2041a.f1966g.m446d("Current number of printers: " + it.size());
        this.f2042b.setValue((List<HpPrinter>) it);
        return Unit.INSTANCE;
    }
}
