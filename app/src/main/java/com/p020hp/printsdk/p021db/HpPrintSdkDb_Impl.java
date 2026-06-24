package com.p020hp.printsdk.p021db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.p014db.SupportSQLiteDatabase;
import androidx.sqlite.p014db.SupportSQLiteOpenHelper;
import com.p020hp.printsdk.AbstractC1771u2;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class HpPrintSdkDb_Impl extends HpPrintSdkDb {

    public volatile AbstractC1771u2 f1176a;

    public class C1686a extends RoomOpenHelper.Delegate {
        public C1686a(int i) {
            super(i);
        }

        @Override
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `history_job` (`job_id` TEXT NOT NULL, `job_name` TEXT NOT NULL, `job_create_time` INTEGER NOT NULL, `job_submit_time` INTEGER NOT NULL, `job_status_info` INTEGER NOT NULL, `printer_id` TEXT NOT NULL, `job_done_time` INTEGER NOT NULL, `job_type` TEXT NOT NULL, `job_size` TEXT NOT NULL, `job_path` TEXT NOT NULL, `job_options_copies` TEXT NOT NULL, `job_options_mediaSize` TEXT NOT NULL, `job_options_color` TEXT NOT NULL, `job_options_scale` TEXT NOT NULL, `job_options_quality` TEXT NOT NULL, `job_options_sides` TEXT NOT NULL, `job_options_orientation` TEXT NOT NULL, `job_options_pageRanges` TEXT NOT NULL, `job_options_mediaMargin` INTEGER NOT NULL, `job_options_inputTray` TEXT NOT NULL, `job_options_paperType` TEXT NOT NULL, `job_options_dpi` INTEGER NOT NULL, `impressionsCompleted` INTEGER NOT NULL, PRIMARY KEY(`job_id`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `history_printer` (`printer_id` TEXT NOT NULL, `printer_name` TEXT NOT NULL, `printer_uri` TEXT NOT NULL, `printer_image` TEXT, PRIMARY KEY(`printer_id`))");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b5b02ba0eef36cd2fcecb300ad8755f2')");
        }

        @Override
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `history_job`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `history_printer`");
            if (((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                }
            }
        }

        @Override
        public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            ((RoomDatabase) HpPrintSdkDb_Impl.this).mDatabase = supportSQLiteDatabase;
            HpPrintSdkDb_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) HpPrintSdkDb_Impl.this).mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override
        public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }

        @Override
        public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
        }

        @Override
        public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap map = new HashMap(23);
            map.put("job_id", new TableInfo.Column("job_id", "TEXT", true, 1, null, 1));
            map.put("job_name", new TableInfo.Column("job_name", "TEXT", true, 0, null, 1));
            map.put("job_create_time", new TableInfo.Column("job_create_time", "INTEGER", true, 0, null, 1));
            map.put("job_submit_time", new TableInfo.Column("job_submit_time", "INTEGER", true, 0, null, 1));
            map.put("job_status_info", new TableInfo.Column("job_status_info", "INTEGER", true, 0, null, 1));
            map.put("printer_id", new TableInfo.Column("printer_id", "TEXT", true, 0, null, 1));
            map.put("job_done_time", new TableInfo.Column("job_done_time", "INTEGER", true, 0, null, 1));
            map.put("job_type", new TableInfo.Column("job_type", "TEXT", true, 0, null, 1));
            map.put("job_size", new TableInfo.Column("job_size", "TEXT", true, 0, null, 1));
            map.put("job_path", new TableInfo.Column("job_path", "TEXT", true, 0, null, 1));
            map.put("job_options_copies", new TableInfo.Column("job_options_copies", "TEXT", true, 0, null, 1));
            map.put("job_options_mediaSize", new TableInfo.Column("job_options_mediaSize", "TEXT", true, 0, null, 1));
            map.put("job_options_color", new TableInfo.Column("job_options_color", "TEXT", true, 0, null, 1));
            map.put("job_options_scale", new TableInfo.Column("job_options_scale", "TEXT", true, 0, null, 1));
            map.put("job_options_quality", new TableInfo.Column("job_options_quality", "TEXT", true, 0, null, 1));
            map.put("job_options_sides", new TableInfo.Column("job_options_sides", "TEXT", true, 0, null, 1));
            map.put("job_options_orientation", new TableInfo.Column("job_options_orientation", "TEXT", true, 0, null, 1));
            map.put("job_options_pageRanges", new TableInfo.Column("job_options_pageRanges", "TEXT", true, 0, null, 1));
            map.put("job_options_mediaMargin", new TableInfo.Column("job_options_mediaMargin", "INTEGER", true, 0, null, 1));
            map.put("job_options_inputTray", new TableInfo.Column("job_options_inputTray", "TEXT", true, 0, null, 1));
            map.put("job_options_paperType", new TableInfo.Column("job_options_paperType", "TEXT", true, 0, null, 1));
            map.put("job_options_dpi", new TableInfo.Column("job_options_dpi", "INTEGER", true, 0, null, 1));
            map.put("impressionsCompleted", new TableInfo.Column("impressionsCompleted", "INTEGER", true, 0, null, 1));
            TableInfo tableInfo = new TableInfo("history_job", map, new HashSet(0), new HashSet(0));
            TableInfo tableInfo2 = TableInfo.read(supportSQLiteDatabase, "history_job");
            if (!tableInfo.equals(tableInfo2)) {
                return new RoomOpenHelper.ValidationResult(false, "history_job(com.hp.printsdk.db.HistoryJob).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
            }
            HashMap map2 = new HashMap(4);
            map2.put("printer_id", new TableInfo.Column("printer_id", "TEXT", true, 1, null, 1));
            map2.put("printer_name", new TableInfo.Column("printer_name", "TEXT", true, 0, null, 1));
            map2.put("printer_uri", new TableInfo.Column("printer_uri", "TEXT", true, 0, null, 1));
            map2.put("printer_image", new TableInfo.Column("printer_image", "TEXT", false, 0, null, 1));
            TableInfo tableInfo3 = new TableInfo("history_printer", map2, new HashSet(0), new HashSet(0));
            TableInfo tableInfo4 = TableInfo.read(supportSQLiteDatabase, "history_printer");
            return !tableInfo3.equals(tableInfo4) ? new RoomOpenHelper.ValidationResult(false, "history_printer(com.hp.printsdk.db.HistoryPrinter).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4) : new RoomOpenHelper.ValidationResult(true, null);
        }
    }

    @Override
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `history_job`");
            writableDatabase.execSQL("DELETE FROM `history_printer`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "history_job", "history_printer");
    }

    @Override
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new C1686a(6), "b5b02ba0eef36cd2fcecb300ad8755f2", "9abfdca37f1f7d7da9128fc51615fcc0")).build());
    }

    @Override
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    @Override
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(AbstractC1771u2.class, Collections.emptyList());
        return map;
    }
}
