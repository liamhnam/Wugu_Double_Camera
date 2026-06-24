package androidx.sqlite.p014db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.p014db.SupportSQLiteStatement;

class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteStatement {
    private final SQLiteStatement mDelegate;

    FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.mDelegate = sQLiteStatement;
    }

    @Override
    public void execute() {
        this.mDelegate.execute();
    }

    @Override
    public int executeUpdateDelete() {
        return this.mDelegate.executeUpdateDelete();
    }

    @Override
    public long executeInsert() {
        return this.mDelegate.executeInsert();
    }

    @Override
    public long simpleQueryForLong() {
        return this.mDelegate.simpleQueryForLong();
    }

    @Override
    public String simpleQueryForString() {
        return this.mDelegate.simpleQueryForString();
    }
}
