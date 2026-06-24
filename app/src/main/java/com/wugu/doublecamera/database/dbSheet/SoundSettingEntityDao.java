package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class SoundSettingEntityDao extends AbstractDao<SoundSettingEntity, Long> {
    public static final String TABLENAME = "SOUND_SETTING_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property NetUrl = new Property(1, String.class, "netUrl", false, "NET_URL");
        public static final Property LocalPath = new Property(2, String.class, "localPath", false, "LOCAL_PATH");
        public static final Property Type = new Property(3, String.class, "type", false, "TYPE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SoundSettingEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public SoundSettingEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SOUND_SETTING_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"NET_URL\" TEXT UNIQUE ,\"LOCAL_PATH\" TEXT,\"TYPE\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"SOUND_SETTING_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, SoundSettingEntity soundSettingEntity) {
        databaseStatement.clearBindings();
        Long l = soundSettingEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String netUrl = soundSettingEntity.getNetUrl();
        if (netUrl != null) {
            databaseStatement.bindString(2, netUrl);
        }
        String localPath = soundSettingEntity.getLocalPath();
        if (localPath != null) {
            databaseStatement.bindString(3, localPath);
        }
        String type = soundSettingEntity.getType();
        if (type != null) {
            databaseStatement.bindString(4, type);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, SoundSettingEntity soundSettingEntity) {
        sQLiteStatement.clearBindings();
        Long l = soundSettingEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String netUrl = soundSettingEntity.getNetUrl();
        if (netUrl != null) {
            sQLiteStatement.bindString(2, netUrl);
        }
        String localPath = soundSettingEntity.getLocalPath();
        if (localPath != null) {
            sQLiteStatement.bindString(3, localPath);
        }
        String type = soundSettingEntity.getType();
        if (type != null) {
            sQLiteStatement.bindString(4, type);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    @Override
    public SoundSettingEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        return new SoundSettingEntity(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override
    public void readEntity(Cursor cursor, SoundSettingEntity soundSettingEntity, int i) {
        int i2 = i + 0;
        soundSettingEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        soundSettingEntity.setNetUrl(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        soundSettingEntity.setLocalPath(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        soundSettingEntity.setType(cursor.isNull(i5) ? null : cursor.getString(i5));
    }

    @Override
    public final Long updateKeyAfterInsert(SoundSettingEntity soundSettingEntity, long j) {
        soundSettingEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(SoundSettingEntity soundSettingEntity) {
        if (soundSettingEntity != null) {
            return soundSettingEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(SoundSettingEntity soundSettingEntity) {
        return soundSettingEntity.get_id() != null;
    }
}
