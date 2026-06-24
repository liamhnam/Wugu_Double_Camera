package com.p020hp.printsdk;

import java.io.File;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

public final class C1715j0 extends Lambda implements Function0<Unit> {

    public final File f1357a;

    public C1715j0(File file) {
        super(0);
        this.f1357a = file;
    }

    @Override
    public Unit invoke() {
        this.f1357a.delete();
        return Unit.INSTANCE;
    }
}
