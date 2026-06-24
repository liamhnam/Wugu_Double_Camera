package androidx.room;

import androidx.sqlite.p014db.SupportSQLiteOpenHelper;

interface DelegatingOpenHelper {
    SupportSQLiteOpenHelper getDelegate();
}
