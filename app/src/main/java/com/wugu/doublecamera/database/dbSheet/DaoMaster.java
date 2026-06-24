package com.wugu.doublecamera.database.dbSheet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    public static void createAllTables(Database database, boolean z) {
        BeautyEntityDao.createTable(database, z);
        EffectEntityDao.createTable(database, z);
        FileMd5EntityDao.createTable(database, z);
        FilterEntityDao.createTable(database, z);
        FrameEntityDao.createTable(database, z);
        FramePhotoEntityDao.createTable(database, z);
        FrameThemeEntityDao.createTable(database, z);
        MakeupEntityDao.createTable(database, z);
        SoundSettingEntityDao.createTable(database, z);
        StickerEntityDao.createTable(database, z);
        UiPositionEntityDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        BeautyEntityDao.dropTable(database, z);
        EffectEntityDao.dropTable(database, z);
        FileMd5EntityDao.dropTable(database, z);
        FilterEntityDao.dropTable(database, z);
        FrameEntityDao.dropTable(database, z);
        FramePhotoEntityDao.dropTable(database, z);
        FrameThemeEntityDao.dropTable(database, z);
        MakeupEntityDao.dropTable(database, z);
        SoundSettingEntityDao.dropTable(database, z);
        StickerEntityDao.dropTable(database, z);
        UiPositionEntityDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 4);
        registerDaoClass(BeautyEntityDao.class);
        registerDaoClass(EffectEntityDao.class);
        registerDaoClass(FileMd5EntityDao.class);
        registerDaoClass(FilterEntityDao.class);
        registerDaoClass(FrameEntityDao.class);
        registerDaoClass(FramePhotoEntityDao.class);
        registerDaoClass(FrameThemeEntityDao.class);
        registerDaoClass(MakeupEntityDao.class);
        registerDaoClass(SoundSettingEntityDao.class);
        registerDaoClass(StickerEntityDao.class);
        registerDaoClass(UiPositionEntityDao.class);
    }

    @Override
    public DaoSession newSession() {
        return new DaoSession(this.f3781db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.f3781db, identityScopeType, this.daoConfigMap);
    }

    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 4);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 4);
        }

        @Override
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 4");
            DaoMaster.createAllTables(database, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }
}
