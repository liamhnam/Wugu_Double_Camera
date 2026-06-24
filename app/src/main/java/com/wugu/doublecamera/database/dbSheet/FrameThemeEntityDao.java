package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class FrameThemeEntityDao extends AbstractDao<FrameThemeEntity, Long> {
    public static final String TABLENAME = "FRAME_THEME_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property ThemeKey = new Property(1, String.class, "themeKey", false, "THEME_KEY");
        public static final Property ThemeName = new Property(2, String.class, "themeName", false, "THEME_NAME");
        public static final Property BackgroundNetUrl = new Property(3, String.class, "backgroundNetUrl", false, "BACKGROUND_NET_URL");
        public static final Property ButtonNetUrl = new Property(4, String.class, "buttonNetUrl", false, "BUTTON_NET_URL");
        public static final Property BackgroundPath = new Property(5, String.class, "backgroundPath", false, "BACKGROUND_PATH");
        public static final Property ButtonPath = new Property(6, String.class, "buttonPath", false, "BUTTON_PATH");
        public static final Property ParentId = new Property(7, String.class, "parentId", false, "PARENT_ID");
        public static final Property Remark = new Property(8, String.class, "remark", false, "REMARK");
        public static final Property LimitTime = new Property(9, String.class, "limitTime", false, "LIMIT_TIME");
        public static final Property Index = new Property(10, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");
        public static final Property IsEnable = new Property(11, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public FrameThemeEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FrameThemeEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FRAME_THEME_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"THEME_KEY\" TEXT UNIQUE ,\"THEME_NAME\" TEXT,\"BACKGROUND_NET_URL\" TEXT,\"BUTTON_NET_URL\" TEXT,\"BACKGROUND_PATH\" TEXT,\"BUTTON_PATH\" TEXT,\"PARENT_ID\" TEXT,\"REMARK\" TEXT,\"LIMIT_TIME\" TEXT,\"INDEX\" INTEGER NOT NULL ,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"FRAME_THEME_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, FrameThemeEntity frameThemeEntity) {
        databaseStatement.clearBindings();
        Long l = frameThemeEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String themeKey = frameThemeEntity.getThemeKey();
        if (themeKey != null) {
            databaseStatement.bindString(2, themeKey);
        }
        String themeName = frameThemeEntity.getThemeName();
        if (themeName != null) {
            databaseStatement.bindString(3, themeName);
        }
        String backgroundNetUrl = frameThemeEntity.getBackgroundNetUrl();
        if (backgroundNetUrl != null) {
            databaseStatement.bindString(4, backgroundNetUrl);
        }
        String buttonNetUrl = frameThemeEntity.getButtonNetUrl();
        if (buttonNetUrl != null) {
            databaseStatement.bindString(5, buttonNetUrl);
        }
        String backgroundPath = frameThemeEntity.getBackgroundPath();
        if (backgroundPath != null) {
            databaseStatement.bindString(6, backgroundPath);
        }
        String buttonPath = frameThemeEntity.getButtonPath();
        if (buttonPath != null) {
            databaseStatement.bindString(7, buttonPath);
        }
        String parentId = frameThemeEntity.getParentId();
        if (parentId != null) {
            databaseStatement.bindString(8, parentId);
        }
        String remark = frameThemeEntity.getRemark();
        if (remark != null) {
            databaseStatement.bindString(9, remark);
        }
        String limitTime = frameThemeEntity.getLimitTime();
        if (limitTime != null) {
            databaseStatement.bindString(10, limitTime);
        }
        databaseStatement.bindLong(11, frameThemeEntity.getIndex());
        databaseStatement.bindLong(12, frameThemeEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, FrameThemeEntity frameThemeEntity) {
        sQLiteStatement.clearBindings();
        Long l = frameThemeEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String themeKey = frameThemeEntity.getThemeKey();
        if (themeKey != null) {
            sQLiteStatement.bindString(2, themeKey);
        }
        String themeName = frameThemeEntity.getThemeName();
        if (themeName != null) {
            sQLiteStatement.bindString(3, themeName);
        }
        String backgroundNetUrl = frameThemeEntity.getBackgroundNetUrl();
        if (backgroundNetUrl != null) {
            sQLiteStatement.bindString(4, backgroundNetUrl);
        }
        String buttonNetUrl = frameThemeEntity.getButtonNetUrl();
        if (buttonNetUrl != null) {
            sQLiteStatement.bindString(5, buttonNetUrl);
        }
        String backgroundPath = frameThemeEntity.getBackgroundPath();
        if (backgroundPath != null) {
            sQLiteStatement.bindString(6, backgroundPath);
        }
        String buttonPath = frameThemeEntity.getButtonPath();
        if (buttonPath != null) {
            sQLiteStatement.bindString(7, buttonPath);
        }
        String parentId = frameThemeEntity.getParentId();
        if (parentId != null) {
            sQLiteStatement.bindString(8, parentId);
        }
        String remark = frameThemeEntity.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(9, remark);
        }
        String limitTime = frameThemeEntity.getLimitTime();
        if (limitTime != null) {
            sQLiteStatement.bindString(10, limitTime);
        }
        sQLiteStatement.bindLong(11, frameThemeEntity.getIndex());
        sQLiteStatement.bindLong(12, frameThemeEntity.getIsEnable() ? 1L : 0L);
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
    public FrameThemeEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        int i5 = i + 3;
        int i6 = i + 4;
        int i7 = i + 5;
        int i8 = i + 6;
        int i9 = i + 7;
        int i10 = i + 8;
        int i11 = i + 9;
        return new FrameThemeEntity(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.isNull(i5) ? null : cursor.getString(i5), cursor.isNull(i6) ? null : cursor.getString(i6), cursor.isNull(i7) ? null : cursor.getString(i7), cursor.isNull(i8) ? null : cursor.getString(i8), cursor.isNull(i9) ? null : cursor.getString(i9), cursor.isNull(i10) ? null : cursor.getString(i10), cursor.isNull(i11) ? null : cursor.getString(i11), cursor.getInt(i + 10), cursor.getShort(i + 11) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, FrameThemeEntity frameThemeEntity, int i) {
        int i2 = i + 0;
        frameThemeEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        frameThemeEntity.setThemeKey(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        frameThemeEntity.setThemeName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        frameThemeEntity.setBackgroundNetUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        frameThemeEntity.setButtonNetUrl(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        frameThemeEntity.setBackgroundPath(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        frameThemeEntity.setButtonPath(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        frameThemeEntity.setParentId(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        frameThemeEntity.setRemark(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        frameThemeEntity.setLimitTime(cursor.isNull(i11) ? null : cursor.getString(i11));
        frameThemeEntity.setIndex(cursor.getInt(i + 10));
        frameThemeEntity.setIsEnable(cursor.getShort(i + 11) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(FrameThemeEntity frameThemeEntity, long j) {
        frameThemeEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(FrameThemeEntity frameThemeEntity) {
        if (frameThemeEntity != null) {
            return frameThemeEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(FrameThemeEntity frameThemeEntity) {
        return frameThemeEntity.get_id() != null;
    }
}
