package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class MakeupEntityDao extends AbstractDao<MakeupEntity, Long> {
    public static final String TABLENAME = "MAKEUP_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property Index = new Property(1, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");
        public static final Property MakeupName = new Property(2, String.class, "makeupName", false, "MAKEUP_NAME");
        public static final Property MakeupBundlePath = new Property(3, String.class, "makeupBundlePath", false, "MAKEUP_BUNDLE_PATH");
        public static final Property MakeupJsonPath = new Property(4, String.class, "makeupJsonPath", false, "MAKEUP_JSON_PATH");
        public static final Property MakeupDemoPath = new Property(5, String.class, "makeupDemoPath", false, "MAKEUP_DEMO_PATH");
        public static final Property MakeupIntensity = new Property(6, Integer.TYPE, "makeupIntensity", false, "MAKEUP_INTENSITY");
        public static final Property MakeupType = new Property(7, Integer.TYPE, "makeupType", false, "MAKEUP_TYPE");
        public static final Property IsEnable = new Property(8, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public MakeupEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public MakeupEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"MAKEUP_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"INDEX\" INTEGER NOT NULL UNIQUE ,\"MAKEUP_NAME\" TEXT UNIQUE ,\"MAKEUP_BUNDLE_PATH\" TEXT UNIQUE ,\"MAKEUP_JSON_PATH\" TEXT,\"MAKEUP_DEMO_PATH\" TEXT,\"MAKEUP_INTENSITY\" INTEGER NOT NULL ,\"MAKEUP_TYPE\" INTEGER NOT NULL ,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"MAKEUP_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, MakeupEntity makeupEntity) {
        databaseStatement.clearBindings();
        Long l = makeupEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        databaseStatement.bindLong(2, makeupEntity.getIndex());
        String makeupName = makeupEntity.getMakeupName();
        if (makeupName != null) {
            databaseStatement.bindString(3, makeupName);
        }
        String makeupBundlePath = makeupEntity.getMakeupBundlePath();
        if (makeupBundlePath != null) {
            databaseStatement.bindString(4, makeupBundlePath);
        }
        String makeupJsonPath = makeupEntity.getMakeupJsonPath();
        if (makeupJsonPath != null) {
            databaseStatement.bindString(5, makeupJsonPath);
        }
        String makeupDemoPath = makeupEntity.getMakeupDemoPath();
        if (makeupDemoPath != null) {
            databaseStatement.bindString(6, makeupDemoPath);
        }
        databaseStatement.bindLong(7, makeupEntity.getMakeupIntensity());
        databaseStatement.bindLong(8, makeupEntity.getMakeupType());
        databaseStatement.bindLong(9, makeupEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, MakeupEntity makeupEntity) {
        sQLiteStatement.clearBindings();
        Long l = makeupEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        sQLiteStatement.bindLong(2, makeupEntity.getIndex());
        String makeupName = makeupEntity.getMakeupName();
        if (makeupName != null) {
            sQLiteStatement.bindString(3, makeupName);
        }
        String makeupBundlePath = makeupEntity.getMakeupBundlePath();
        if (makeupBundlePath != null) {
            sQLiteStatement.bindString(4, makeupBundlePath);
        }
        String makeupJsonPath = makeupEntity.getMakeupJsonPath();
        if (makeupJsonPath != null) {
            sQLiteStatement.bindString(5, makeupJsonPath);
        }
        String makeupDemoPath = makeupEntity.getMakeupDemoPath();
        if (makeupDemoPath != null) {
            sQLiteStatement.bindString(6, makeupDemoPath);
        }
        sQLiteStatement.bindLong(7, makeupEntity.getMakeupIntensity());
        sQLiteStatement.bindLong(8, makeupEntity.getMakeupType());
        sQLiteStatement.bindLong(9, makeupEntity.getIsEnable() ? 1L : 0L);
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
    public MakeupEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string3 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        return new MakeupEntity(lValueOf, i3, string, string2, string3, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getShort(i + 8) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, MakeupEntity makeupEntity, int i) {
        int i2 = i + 0;
        makeupEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        makeupEntity.setIndex(cursor.getInt(i + 1));
        int i3 = i + 2;
        makeupEntity.setMakeupName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        makeupEntity.setMakeupBundlePath(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        makeupEntity.setMakeupJsonPath(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 5;
        makeupEntity.setMakeupDemoPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        makeupEntity.setMakeupIntensity(cursor.getInt(i + 6));
        makeupEntity.setMakeupType(cursor.getInt(i + 7));
        makeupEntity.setIsEnable(cursor.getShort(i + 8) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(MakeupEntity makeupEntity, long j) {
        makeupEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(MakeupEntity makeupEntity) {
        if (makeupEntity != null) {
            return makeupEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(MakeupEntity makeupEntity) {
        return makeupEntity.get_id() != null;
    }
}
