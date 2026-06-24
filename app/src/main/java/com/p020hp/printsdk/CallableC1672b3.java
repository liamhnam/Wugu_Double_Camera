package com.p020hp.printsdk;

import java.util.concurrent.Callable;
import kotlin.Unit;

public class CallableC1672b3 implements Callable<Unit> {

    public final C1766t2[] f923a;

    public final C1666a3 f924b;

    public CallableC1672b3(C1666a3 c1666a3, C1766t2[] c1766t2Arr) {
        this.f924b = c1666a3;
        this.f923a = c1766t2Arr;
    }

    @Override
    public Unit call() {
        this.f924b.f884a.beginTransaction();
        try {
            this.f924b.f885b.insert(this.f923a);
            this.f924b.f884a.setTransactionSuccessful();
            return Unit.INSTANCE;
        } finally {
            this.f924b.f884a.endTransaction();
        }
    }
}
