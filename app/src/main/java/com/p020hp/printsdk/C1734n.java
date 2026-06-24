package com.p020hp.printsdk;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class C1734n extends Lambda implements Function0<Unit> {

    public final InterfaceC1783x f1514a;

    public C1734n(InterfaceC1783x interfaceC1783x) {
        super(0);
        this.f1514a = interfaceC1783x;
    }

    @Override
    public Unit invoke() {
        this.f1514a.close();
        return Unit.INSTANCE;
    }
}
