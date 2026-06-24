package com.p020hp.printsdk;

import androidx.room.util.StringUtil;
import androidx.sqlite.p014db.SupportSQLiteStatement;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.concurrent.Callable;
import kotlin.Unit;

public class CallableC1796z2 implements Callable<Unit> {

    public final HpPrintJobState[] f2062a;

    public final C1666a3 f2063b;

    public CallableC1796z2(C1666a3 c1666a3, HpPrintJobState[] hpPrintJobStateArr) {
        this.f2063b = c1666a3;
        this.f2062a = hpPrintJobStateArr;
    }

    @Override
    public Unit call() {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("Delete FROM history_job WHERE job_status_info IN (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, this.f2062a.length);
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.f2063b.f884a.compileStatement(sbNewStringBuilder.toString());
        int length = this.f2062a.length;
        int i = 1;
        for (int i2 = 0; i2 < length; i2++) {
            supportSQLiteStatementCompileStatement.bindLong(i, this.f2063b.f886c.m564a(r1[i2]));
            i++;
        }
        this.f2063b.f884a.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.f2063b.f884a.setTransactionSuccessful();
            return Unit.INSTANCE;
        } finally {
            this.f2063b.f884a.endTransaction();
        }
    }
}
