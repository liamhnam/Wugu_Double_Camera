package androidx.room;

import androidx.sqlite.p014db.SupportSQLiteProgram;
import java.util.ArrayList;
import java.util.List;

final class QueryInterceptorProgram implements SupportSQLiteProgram {
    private List<Object> mBindArgsCache = new ArrayList();

    @Override
    public void close() {
    }

    QueryInterceptorProgram() {
    }

    @Override
    public void bindNull(int i) {
        saveArgsToCache(i, null);
    }

    @Override
    public void bindLong(int i, long j) {
        saveArgsToCache(i, Long.valueOf(j));
    }

    @Override
    public void bindDouble(int i, double d) {
        saveArgsToCache(i, Double.valueOf(d));
    }

    @Override
    public void bindString(int i, String str) {
        saveArgsToCache(i, str);
    }

    @Override
    public void bindBlob(int i, byte[] bArr) {
        saveArgsToCache(i, bArr);
    }

    @Override
    public void clearBindings() {
        this.mBindArgsCache.clear();
    }

    private void saveArgsToCache(int i, Object obj) {
        int i2 = i - 1;
        if (i2 >= this.mBindArgsCache.size()) {
            for (int size = this.mBindArgsCache.size(); size <= i2; size++) {
                this.mBindArgsCache.add(null);
            }
        }
        this.mBindArgsCache.set(i2, obj);
    }

    List<Object> getBindArgs() {
        return this.mBindArgsCache;
    }
}
