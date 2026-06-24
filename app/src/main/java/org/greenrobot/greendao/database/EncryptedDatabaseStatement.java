package org.greenrobot.greendao.database;

import net.sqlcipher.database.SQLiteStatement;

public class EncryptedDatabaseStatement implements DatabaseStatement {
    private final SQLiteStatement delegate;

    public EncryptedDatabaseStatement(SQLiteStatement sQLiteStatement) {
        this.delegate = sQLiteStatement;
    }

    @Override
    public void execute() {
        this.delegate.execute();
    }

    @Override
    public long simpleQueryForLong() {
        return this.delegate.simpleQueryForLong();
    }

    @Override
    public void bindNull(int i) {
        this.delegate.bindNull(i);
    }

    @Override
    public long executeInsert() {
        return this.delegate.executeInsert();
    }

    @Override
    public void bindString(int i, String str) {
        this.delegate.bindString(i, str);
    }

    @Override
    public void bindBlob(int i, byte[] bArr) {
        this.delegate.bindBlob(i, bArr);
    }

    @Override
    public void bindLong(int i, long j) {
        this.delegate.bindLong(i, j);
    }

    @Override
    public void clearBindings() {
        this.delegate.clearBindings();
    }

    @Override
    public void bindDouble(int i, double d) {
        this.delegate.bindDouble(i, d);
    }

    @Override
    public void close() {
        this.delegate.close();
    }

    @Override
    public Object getRawStatement() {
        return this.delegate;
    }
}
