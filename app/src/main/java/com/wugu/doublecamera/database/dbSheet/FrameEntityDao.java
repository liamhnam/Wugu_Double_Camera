package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class FrameEntityDao extends AbstractDao<FrameEntity, Long> {
    public static final String TABLENAME = "FRAME_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property FrameKey = new Property(1, String.class, "frameKey", false, "FRAME_KEY");
        public static final Property FrameName = new Property(2, String.class, "frameName", false, "FRAME_NAME");
        public static final Property FrameNetUrl = new Property(3, String.class, "frameNetUrl", false, "FRAME_NET_URL");
        public static final Property FrameLocalPath = new Property(4, String.class, "frameLocalPath", false, "FRAME_LOCAL_PATH");
        public static final Property ThemeKey = new Property(5, String.class, "themeKey", false, "THEME_KEY");
        public static final Property FrameType = new Property(6, Integer.TYPE, "frameType", false, "FRAME_TYPE");
        public static final Property PhotoCount = new Property(7, Integer.TYPE, "photoCount", false, "PHOTO_COUNT");
        public static final Property BurstModeCount = new Property(8, Integer.TYPE, "burstModeCount", false, "BURST_MODE_COUNT");
        public static final Property OPrice = new Property(9, Integer.TYPE, "oPrice", false, "O_PRICE");
        public static final Property Price = new Property(10, Integer.TYPE, "price", false, "PRICE");
        public static final Property AddOPrice = new Property(11, Integer.TYPE, "addOPrice", false, "ADD_OPRICE");
        public static final Property AddPrice = new Property(12, Integer.TYPE, "addPrice", false, "ADD_PRICE");
        public static final Property PrintCount = new Property(13, Integer.TYPE, "printCount", false, "PRINT_COUNT");
        public static final Property IsNeedCut = new Property(14, Boolean.TYPE, "isNeedCut", false, "IS_NEED_CUT");
        public static final Property IsReplaceBg = new Property(15, Boolean.TYPE, "isReplaceBg", false, "IS_REPLACE_BG");
        public static final Property ColorParam = new Property(16, String.class, "colorParam", false, "COLOR_PARAM");
        public static final Property AiModeIds = new Property(17, String.class, "aiModeIds", false, "AI_MODE_IDS");
        public static final Property Remark = new Property(18, String.class, "remark", false, "REMARK");
        public static final Property Index = new Property(19, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");
        public static final Property IsEnable = new Property(20, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public FrameEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FrameEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FRAME_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"FRAME_KEY\" TEXT UNIQUE ,\"FRAME_NAME\" TEXT,\"FRAME_NET_URL\" TEXT,\"FRAME_LOCAL_PATH\" TEXT,\"THEME_KEY\" TEXT,\"FRAME_TYPE\" INTEGER NOT NULL ,\"PHOTO_COUNT\" INTEGER NOT NULL ,\"BURST_MODE_COUNT\" INTEGER NOT NULL ,\"O_PRICE\" INTEGER NOT NULL ,\"PRICE\" INTEGER NOT NULL ,\"ADD_OPRICE\" INTEGER NOT NULL ,\"ADD_PRICE\" INTEGER NOT NULL ,\"PRINT_COUNT\" INTEGER NOT NULL ,\"IS_NEED_CUT\" INTEGER NOT NULL ,\"IS_REPLACE_BG\" INTEGER NOT NULL ,\"COLOR_PARAM\" TEXT,\"AI_MODE_IDS\" TEXT,\"REMARK\" TEXT,\"INDEX\" INTEGER NOT NULL ,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"FRAME_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, FrameEntity frameEntity) {
        databaseStatement.clearBindings();
        Long l = frameEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String frameKey = frameEntity.getFrameKey();
        if (frameKey != null) {
            databaseStatement.bindString(2, frameKey);
        }
        String frameName = frameEntity.getFrameName();
        if (frameName != null) {
            databaseStatement.bindString(3, frameName);
        }
        String frameNetUrl = frameEntity.getFrameNetUrl();
        if (frameNetUrl != null) {
            databaseStatement.bindString(4, frameNetUrl);
        }
        String frameLocalPath = frameEntity.getFrameLocalPath();
        if (frameLocalPath != null) {
            databaseStatement.bindString(5, frameLocalPath);
        }
        String themeKey = frameEntity.getThemeKey();
        if (themeKey != null) {
            databaseStatement.bindString(6, themeKey);
        }
        databaseStatement.bindLong(7, frameEntity.getFrameType());
        databaseStatement.bindLong(8, frameEntity.getPhotoCount());
        databaseStatement.bindLong(9, frameEntity.getBurstModeCount());
        databaseStatement.bindLong(10, frameEntity.getOPrice());
        databaseStatement.bindLong(11, frameEntity.getPrice());
        databaseStatement.bindLong(12, frameEntity.getAddOPrice());
        databaseStatement.bindLong(13, frameEntity.getAddPrice());
        databaseStatement.bindLong(14, frameEntity.getPrintCount());
        databaseStatement.bindLong(15, frameEntity.getIsNeedCut() ? 1L : 0L);
        databaseStatement.bindLong(16, frameEntity.getIsReplaceBg() ? 1L : 0L);
        String colorParam = frameEntity.getColorParam();
        if (colorParam != null) {
            databaseStatement.bindString(17, colorParam);
        }
        String aiModeIds = frameEntity.getAiModeIds();
        if (aiModeIds != null) {
            databaseStatement.bindString(18, aiModeIds);
        }
        String remark = frameEntity.getRemark();
        if (remark != null) {
            databaseStatement.bindString(19, remark);
        }
        databaseStatement.bindLong(20, frameEntity.getIndex());
        databaseStatement.bindLong(21, frameEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, FrameEntity frameEntity) {
        sQLiteStatement.clearBindings();
        Long l = frameEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String frameKey = frameEntity.getFrameKey();
        if (frameKey != null) {
            sQLiteStatement.bindString(2, frameKey);
        }
        String frameName = frameEntity.getFrameName();
        if (frameName != null) {
            sQLiteStatement.bindString(3, frameName);
        }
        String frameNetUrl = frameEntity.getFrameNetUrl();
        if (frameNetUrl != null) {
            sQLiteStatement.bindString(4, frameNetUrl);
        }
        String frameLocalPath = frameEntity.getFrameLocalPath();
        if (frameLocalPath != null) {
            sQLiteStatement.bindString(5, frameLocalPath);
        }
        String themeKey = frameEntity.getThemeKey();
        if (themeKey != null) {
            sQLiteStatement.bindString(6, themeKey);
        }
        sQLiteStatement.bindLong(7, frameEntity.getFrameType());
        sQLiteStatement.bindLong(8, frameEntity.getPhotoCount());
        sQLiteStatement.bindLong(9, frameEntity.getBurstModeCount());
        sQLiteStatement.bindLong(10, frameEntity.getOPrice());
        sQLiteStatement.bindLong(11, frameEntity.getPrice());
        sQLiteStatement.bindLong(12, frameEntity.getAddOPrice());
        sQLiteStatement.bindLong(13, frameEntity.getAddPrice());
        sQLiteStatement.bindLong(14, frameEntity.getPrintCount());
        sQLiteStatement.bindLong(15, frameEntity.getIsNeedCut() ? 1L : 0L);
        sQLiteStatement.bindLong(16, frameEntity.getIsReplaceBg() ? 1L : 0L);
        String colorParam = frameEntity.getColorParam();
        if (colorParam != null) {
            sQLiteStatement.bindString(17, colorParam);
        }
        String aiModeIds = frameEntity.getAiModeIds();
        if (aiModeIds != null) {
            sQLiteStatement.bindString(18, aiModeIds);
        }
        String remark = frameEntity.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(19, remark);
        }
        sQLiteStatement.bindLong(20, frameEntity.getIndex());
        sQLiteStatement.bindLong(21, frameEntity.getIsEnable() ? 1L : 0L);
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
    public FrameEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = i + 2;
        String string2 = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string3 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string4 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string5 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = cursor.getInt(i + 8);
        int i11 = cursor.getInt(i + 9);
        int i12 = cursor.getInt(i + 10);
        int i13 = cursor.getInt(i + 11);
        int i14 = cursor.getInt(i + 12);
        int i15 = cursor.getInt(i + 13);
        boolean z = cursor.getShort(i + 14) != 0;
        boolean z2 = cursor.getShort(i + 15) != 0;
        int i16 = i + 16;
        String string6 = cursor.isNull(i16) ? null : cursor.getString(i16);
        int i17 = i + 17;
        String string7 = cursor.isNull(i17) ? null : cursor.getString(i17);
        int i18 = i + 18;
        return new FrameEntity(lValueOf, string, string2, string3, string4, string5, i8, i9, i10, i11, i12, i13, i14, i15, z, z2, string6, string7, cursor.isNull(i18) ? null : cursor.getString(i18), cursor.getInt(i + 19), cursor.getShort(i + 20) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, FrameEntity frameEntity, int i) {
        int i2 = i + 0;
        frameEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        frameEntity.setFrameKey(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        frameEntity.setFrameName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        frameEntity.setFrameNetUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        frameEntity.setFrameLocalPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        frameEntity.setThemeKey(cursor.isNull(i7) ? null : cursor.getString(i7));
        frameEntity.setFrameType(cursor.getInt(i + 6));
        frameEntity.setPhotoCount(cursor.getInt(i + 7));
        frameEntity.setBurstModeCount(cursor.getInt(i + 8));
        frameEntity.setOPrice(cursor.getInt(i + 9));
        frameEntity.setPrice(cursor.getInt(i + 10));
        frameEntity.setAddOPrice(cursor.getInt(i + 11));
        frameEntity.setAddPrice(cursor.getInt(i + 12));
        frameEntity.setPrintCount(cursor.getInt(i + 13));
        frameEntity.setIsNeedCut(cursor.getShort(i + 14) != 0);
        frameEntity.setIsReplaceBg(cursor.getShort(i + 15) != 0);
        int i8 = i + 16;
        frameEntity.setColorParam(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 17;
        frameEntity.setAiModeIds(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 18;
        frameEntity.setRemark(cursor.isNull(i10) ? null : cursor.getString(i10));
        frameEntity.setIndex(cursor.getInt(i + 19));
        frameEntity.setIsEnable(cursor.getShort(i + 20) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(FrameEntity frameEntity, long j) {
        frameEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(FrameEntity frameEntity) {
        if (frameEntity != null) {
            return frameEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(FrameEntity frameEntity) {
        return frameEntity.get_id() != null;
    }
}
