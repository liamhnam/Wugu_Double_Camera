package com.p020hp.printsdk;

import java.util.concurrent.Callable;
import kotlin.Unit;

public class CallableC1678c3 implements Callable<Unit> {

    public final C1708h3[] f1132a;

    public final C1666a3 f1133b;

    public CallableC1678c3(C1666a3 c1666a3, C1708h3[] c1708h3Arr) {
        this.f1133b = c1666a3;
        this.f1132a = c1708h3Arr;
    }

    @Override
    public Unit call() {
        this.f1133b.f884a.beginTransaction();
        try {
            this.f1133b.f887d.insert(this.f1132a);
            this.f1133b.f884a.setTransactionSuccessful();
            return Unit.INSTANCE;
        } finally {
            this.f1133b.f884a.endTransaction();
        }
    }
}
