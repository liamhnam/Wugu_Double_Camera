package org.greenrobot.greendao.database;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class StandardDatabase implements Database {
    private final SQLiteDatabase delegate;

    public StandardDatabase(SQLiteDatabase sQLiteDatabase) {
        this.delegate = sQLiteDatabase;
    }

    @Override
    public Cursor rawQuery(String str, String[] strArr) {
        return this.delegate.rawQuery(str, strArr);
    }

    @Override
    public void execSQL(String str) throws SQLException {
        this.delegate.execSQL(str);
    }

    @Override
    public void beginTransaction() {
        this.delegate.beginTransaction();
    }

    @Override
    public void endTransaction() {
        this.delegate.endTransaction();
    }

    @Override
    public boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override
    public void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    @Override
    public void execSQL(String str, Object[] objArr) throws SQLException {
        this.delegate.execSQL(str, objArr);
    }

    @Override
    public DatabaseStatement compileStatement(String str) {
        return new StandardDatabaseStatement(this.delegate.compileStatement(str));
    }

    @Override
    public boolean isDbLockedByCurrentThread() {
        return this.delegate.isDbLockedByCurrentThread();
    }

    @Override
    public boolean isOpen() {
        return this.delegate.isOpen();
    }

    @Override
    public void close() {
        this.delegate.close();
    }

    @Override
    public Object getRawDatabase() {
        return this.delegate;
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return this.delegate;
    }
}
