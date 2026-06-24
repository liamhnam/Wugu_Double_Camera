package com.p020hp.printsdk;

import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class C1669b0 extends Lambda implements Function0<Unit> {

    public final File f905a;

    public C1669b0(File file) {
        super(0);
        this.f905a = file;
    }

    @Override
    public Unit invoke() {
        this.f905a.delete();
        return Unit.INSTANCE;
    }
}
