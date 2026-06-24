package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.room.RoomDatabase;
import androidx.sqlite.p014db.SupportSQLiteDatabase;
import androidx.sqlite.p014db.SupportSQLiteQuery;
import androidx.sqlite.p014db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

final class QueryInterceptorDatabase implements SupportSQLiteDatabase {
    private final SupportSQLiteDatabase mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    QueryInterceptorDatabase(SupportSQLiteDatabase supportSQLiteDatabase, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = supportSQLiteDatabase;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    @Override
    public SupportSQLiteStatement compileStatement(String str) {
        return new QueryInterceptorStatement(this.mDelegate.compileStatement(str), this.mQueryCallback, str, this.mQueryCallbackExecutor);
    }

    @Override
    public void beginTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1514lambda$beginTransaction$0$androidxroomQueryInterceptorDatabase();
            }
        });
        this.mDelegate.beginTransaction();
    }

    void m1514lambda$beginTransaction$0$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    @Override
    public void beginTransactionNonExclusive() {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m147x9d2bf0fa();
            }
        });
        this.mDelegate.beginTransactionNonExclusive();
    }

    void m147x9d2bf0fa() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    @Override
    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m148xe4527eb0();
            }
        });
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    void m148xe4527eb0() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    @Override
    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m149xa78c463e();
            }
        });
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    void m149xa78c463e() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    @Override
    public void endTransaction() {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1515lambda$endTransaction$4$androidxroomQueryInterceptorDatabase();
            }
        });
        this.mDelegate.endTransaction();
    }

    void m1515lambda$endTransaction$4$androidxroomQueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("END TRANSACTION", Collections.emptyList());
    }

    void m150x7d646086() {
        this.mQueryCallback.onQuery("TRANSACTION SUCCESSFUL", Collections.emptyList());
    }

    @Override
    public void setTransactionSuccessful() {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m150x7d646086();
            }
        });
        this.mDelegate.setTransactionSuccessful();
    }

    @Override
    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    @Override
    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    @Override
    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    @Override
    public boolean yieldIfContendedSafely(long j) {
        return this.mDelegate.yieldIfContendedSafely(j);
    }

    @Override
    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    @Override
    public void setVersion(int i) {
        this.mDelegate.setVersion(i);
    }

    @Override
    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    @Override
    public long setMaximumSize(long j) {
        return this.mDelegate.setMaximumSize(j);
    }

    @Override
    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    @Override
    public void setPageSize(long j) {
        this.mDelegate.setPageSize(j);
    }

    void m1518lambda$query$6$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, Collections.emptyList());
    }

    @Override
    public Cursor query(final String str) {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1518lambda$query$6$androidxroomQueryInterceptorDatabase(str);
            }
        });
        return this.mDelegate.query(str);
    }

    @Override
    public Cursor query(final String str, Object[] objArr) {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1519lambda$query$7$androidxroomQueryInterceptorDatabase(str, arrayList);
            }
        });
        return this.mDelegate.query(str, objArr);
    }

    void m1519lambda$query$7$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    @Override
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1520lambda$query$8$androidxroomQueryInterceptorDatabase(supportSQLiteQuery, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(supportSQLiteQuery);
    }

    void m1520lambda$query$8$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    @Override
    public Cursor query(final SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        final QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1521lambda$query$9$androidxroomQueryInterceptorDatabase(supportSQLiteQuery, queryInterceptorProgram);
            }
        });
        return this.mDelegate.query(supportSQLiteQuery);
    }

    void m1521lambda$query$9$androidxroomQueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    @Override
    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insert(str, i, contentValues);
    }

    @Override
    public int delete(String str, String str2, Object[] objArr) {
        return this.mDelegate.delete(str, str2, objArr);
    }

    @Override
    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        return this.mDelegate.update(str, i, contentValues, str2, objArr);
    }

    @Override
    public void execSQL(final String str) throws SQLException {
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1516lambda$execSQL$10$androidxroomQueryInterceptorDatabase(str);
            }
        });
        this.mDelegate.execSQL(str);
    }

    void m1516lambda$execSQL$10$androidxroomQueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, new ArrayList(0));
    }

    @Override
    public void execSQL(final String str, Object[] objArr) throws SQLException {
        final ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1517lambda$execSQL$11$androidxroomQueryInterceptorDatabase(str, arrayList);
            }
        });
        this.mDelegate.execSQL(str, arrayList.toArray());
    }

    void m1517lambda$execSQL$11$androidxroomQueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    @Override
    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    @Override
    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    @Override
    public boolean needUpgrade(int i) {
        return this.mDelegate.needUpgrade(i);
    }

    @Override
    public String getPath() {
        return this.mDelegate.getPath();
    }

    @Override
    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    @Override
    public void setMaxSqlCacheSize(int i) {
        this.mDelegate.setMaxSqlCacheSize(i);
    }

    @Override
    public void setForeignKeyConstraintsEnabled(boolean z) {
        this.mDelegate.setForeignKeyConstraintsEnabled(z);
    }

    @Override
    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    @Override
    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    @Override
    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    @Override
    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    @Override
    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    @Override
    public void close() throws IOException {
        this.mDelegate.close();
    }
}
