package com.p020hp.printsdk;

import androidx.sqlite.p014db.SupportSQLiteStatement;
import java.util.concurrent.Callable;
import kotlin.Unit;

public class CallableC1684d3 implements Callable<Unit> {

    public final long f1171a;

    public final C1666a3 f1172b;

    public CallableC1684d3(C1666a3 c1666a3, long j) {
        this.f1172b = c1666a3;
        this.f1171a = j;
    }

    @Override
    public Unit call() {
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.f1172b.f888e.acquire();
        supportSQLiteStatementAcquire.bindLong(1, this.f1171a);
        this.f1172b.f884a.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.f1172b.f884a.setTransactionSuccessful();
            return Unit.INSTANCE;
        } finally {
            this.f1172b.f884a.endTransaction();
            this.f1172b.f888e.release(supportSQLiteStatementAcquire);
        }
    }
}
