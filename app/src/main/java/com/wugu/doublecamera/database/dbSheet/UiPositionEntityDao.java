package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class UiPositionEntityDao extends AbstractDao<UiPositionEntity, Long> {
    public static final String TABLENAME = "UI_POSITION_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property Index = new Property(1, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");

        public static final Property f2454X = new Property(2, Integer.TYPE, "x", false, "X");

        public static final Property f2455Y = new Property(3, Integer.TYPE, "y", false, "Y");
        public static final Property ResNetUrl = new Property(4, String.class, "resNetUrl", false, "RES_NET_URL");
        public static final Property ResPath = new Property(5, String.class, "resPath", false, "RES_PATH");
        public static final Property DefaultResPath = new Property(6, String.class, "defaultResPath", false, "DEFAULT_RES_PATH");
        public static final Property IsVisible = new Property(7, Boolean.TYPE, "isVisible", false, "IS_VISIBLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public UiPositionEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public UiPositionEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"UI_POSITION_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"INDEX\" INTEGER NOT NULL UNIQUE ,\"X\" INTEGER NOT NULL ,\"Y\" INTEGER NOT NULL ,\"RES_NET_URL\" TEXT,\"RES_PATH\" TEXT,\"DEFAULT_RES_PATH\" TEXT,\"IS_VISIBLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"UI_POSITION_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, UiPositionEntity uiPositionEntity) {
        databaseStatement.clearBindings();
        Long l = uiPositionEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        databaseStatement.bindLong(2, uiPositionEntity.getIndex());
        databaseStatement.bindLong(3, uiPositionEntity.getX());
        databaseStatement.bindLong(4, uiPositionEntity.getY());
        String resNetUrl = uiPositionEntity.getResNetUrl();
        if (resNetUrl != null) {
            databaseStatement.bindString(5, resNetUrl);
        }
        String resPath = uiPositionEntity.getResPath();
        if (resPath != null) {
            databaseStatement.bindString(6, resPath);
        }
        String defaultResPath = uiPositionEntity.getDefaultResPath();
        if (defaultResPath != null) {
            databaseStatement.bindString(7, defaultResPath);
        }
        databaseStatement.bindLong(8, uiPositionEntity.getIsVisible() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, UiPositionEntity uiPositionEntity) {
        sQLiteStatement.clearBindings();
        Long l = uiPositionEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        sQLiteStatement.bindLong(2, uiPositionEntity.getIndex());
        sQLiteStatement.bindLong(3, uiPositionEntity.getX());
        sQLiteStatement.bindLong(4, uiPositionEntity.getY());
        String resNetUrl = uiPositionEntity.getResNetUrl();
        if (resNetUrl != null) {
            sQLiteStatement.bindString(5, resNetUrl);
        }
        String resPath = uiPositionEntity.getResPath();
        if (resPath != null) {
            sQLiteStatement.bindString(6, resPath);
        }
        String defaultResPath = uiPositionEntity.getDefaultResPath();
        if (defaultResPath != null) {
            sQLiteStatement.bindString(7, defaultResPath);
        }
        sQLiteStatement.bindLong(8, uiPositionEntity.getIsVisible() ? 1L : 0L);
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
    public UiPositionEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = i + 4;
        String string = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string2 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        return new UiPositionEntity(lValueOf, i3, i4, i5, string, string2, cursor.isNull(i8) ? null : cursor.getString(i8), cursor.getShort(i + 7) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, UiPositionEntity uiPositionEntity, int i) {
        int i2 = i + 0;
        uiPositionEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        uiPositionEntity.setIndex(cursor.getInt(i + 1));
        uiPositionEntity.setX(cursor.getInt(i + 2));
        uiPositionEntity.setY(cursor.getInt(i + 3));
        int i3 = i + 4;
        uiPositionEntity.setResNetUrl(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 5;
        uiPositionEntity.setResPath(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 6;
        uiPositionEntity.setDefaultResPath(cursor.isNull(i5) ? null : cursor.getString(i5));
        uiPositionEntity.setIsVisible(cursor.getShort(i + 7) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(UiPositionEntity uiPositionEntity, long j) {
        uiPositionEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(UiPositionEntity uiPositionEntity) {
        if (uiPositionEntity != null) {
            return uiPositionEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(UiPositionEntity uiPositionEntity) {
        return uiPositionEntity.get_id() != null;
    }
}
