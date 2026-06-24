package com.p020hp.printsdk;

import androidx.room.util.StringUtil;
import androidx.sqlite.p014db.SupportSQLiteStatement;
import com.p020hp.open.printsdk.state.printjob.HpPrintJobState;
import java.util.UUID;
import java.util.concurrent.Callable;
import kotlin.Unit;

public class CallableC1791y2 implements Callable<Unit> {

    public final UUID[] f2038a;

    public final HpPrintJobState[] f2039b;

    public final C1666a3 f2040c;

    public CallableC1791y2(C1666a3 c1666a3, UUID[] uuidArr, HpPrintJobState[] hpPrintJobStateArr) {
        this.f2040c = c1666a3;
        this.f2038a = uuidArr;
        this.f2039b = hpPrintJobStateArr;
    }

    @Override
    public Unit call() {
        StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
        sbNewStringBuilder.append("Delete FROM history_job WHERE job_id IN (");
        int length = this.f2038a.length;
        StringUtil.appendPlaceholders(sbNewStringBuilder, length);
        sbNewStringBuilder.append(") AND job_status_info IN (");
        StringUtil.appendPlaceholders(sbNewStringBuilder, this.f2039b.length);
        sbNewStringBuilder.append(")");
        SupportSQLiteStatement supportSQLiteStatementCompileStatement = this.f2040c.f884a.compileStatement(sbNewStringBuilder.toString());
        int i = 1;
        for (UUID uuid : this.f2038a) {
            String strM566a = this.f2040c.f886c.m566a(uuid);
            if (strM566a == null) {
                supportSQLiteStatementCompileStatement.bindNull(i);
            } else {
                supportSQLiteStatementCompileStatement.bindString(i, strM566a);
            }
            i++;
        }
        int i2 = length + 1;
        int length2 = this.f2039b.length;
        for (int i3 = 0; i3 < length2; i3++) {
            supportSQLiteStatementCompileStatement.bindLong(i2, this.f2040c.f886c.m564a(r2[i3]));
            i2++;
        }
        this.f2040c.f884a.beginTransaction();
        try {
            supportSQLiteStatementCompileStatement.executeUpdateDelete();
            this.f2040c.f884a.setTransactionSuccessful();
            return Unit.INSTANCE;
        } finally {
            this.f2040c.f884a.endTransaction();
        }
    }
}
