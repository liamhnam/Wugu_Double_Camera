package com.p020hp.printsdk;

import kotlin.coroutines.CoroutineContext;

public final class C1745p0 extends AbstractC1750q0 {

    public String f1568f;

    public final InterfaceC1754r0 f1569g;

    public final CoroutineContext f1570h;

    public final C1705h0 f1571i;

    public C1745p0(InterfaceC1693f interfaceC1693f, CoroutineContext coroutineContext, C1705h0 c1705h0) {
        super(coroutineContext);
        this.f1570h = coroutineContext;
        this.f1571i = c1705h0;
        this.f1568f = "Android User";
        this.f1569g = InterfaceC1754r0.f1659a.m622a(interfaceC1693f);
    }

    @Override
    public String mo517a() {
        return this.f1568f;
    }
}
