package androidx.sqlite.p014db.framework;

import android.database.sqlite.SQLiteProgram;
import androidx.sqlite.p014db.SupportSQLiteProgram;

class FrameworkSQLiteProgram implements SupportSQLiteProgram {
    private final SQLiteProgram mDelegate;

    FrameworkSQLiteProgram(SQLiteProgram sQLiteProgram) {
        this.mDelegate = sQLiteProgram;
    }

    @Override
    public void bindNull(int i) {
        this.mDelegate.bindNull(i);
    }

    @Override
    public void bindLong(int i, long j) {
        this.mDelegate.bindLong(i, j);
    }

    @Override
    public void bindDouble(int i, double d) {
        this.mDelegate.bindDouble(i, d);
    }

    @Override
    public void bindString(int i, String str) {
        this.mDelegate.bindString(i, str);
    }

    @Override
    public void bindBlob(int i, byte[] bArr) {
        this.mDelegate.bindBlob(i, bArr);
    }

    @Override
    public void clearBindings() {
        this.mDelegate.clearBindings();
    }

    @Override
    public void close() {
        this.mDelegate.close();
    }
}
