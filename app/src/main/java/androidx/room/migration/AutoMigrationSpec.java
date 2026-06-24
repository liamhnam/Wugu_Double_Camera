package androidx.room.migration;

import androidx.sqlite.p014db.SupportSQLiteDatabase;

public interface AutoMigrationSpec {
    default void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
    }
}
