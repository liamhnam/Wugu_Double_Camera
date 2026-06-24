package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class StickerEntityDao extends AbstractDao<StickerEntity, Long> {
    public static final String TABLENAME = "STICKER_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property FolderName = new Property(1, String.class, "folderName", false, "FOLDER_NAME");
        public static final Property FolderIconPath = new Property(2, String.class, "folderIconPath", false, "FOLDER_ICON_PATH");
        public static final Property NetworkUrl = new Property(3, String.class, "networkUrl", false, "NETWORK_URL");
        public static final Property LocalPath = new Property(4, String.class, "localPath", false, "LOCAL_PATH");
        public static final Property Remark = new Property(5, String.class, "remark", false, "REMARK");
        public static final Property IsEnable = new Property(6, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public StickerEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public StickerEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"STICKER_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"FOLDER_NAME\" TEXT,\"FOLDER_ICON_PATH\" TEXT,\"NETWORK_URL\" TEXT UNIQUE ,\"LOCAL_PATH\" TEXT,\"REMARK\" TEXT,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"STICKER_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, StickerEntity stickerEntity) {
        databaseStatement.clearBindings();
        Long l = stickerEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String folderName = stickerEntity.getFolderName();
        if (folderName != null) {
            databaseStatement.bindString(2, folderName);
        }
        String folderIconPath = stickerEntity.getFolderIconPath();
        if (folderIconPath != null) {
            databaseStatement.bindString(3, folderIconPath);
        }
        String networkUrl = stickerEntity.getNetworkUrl();
        if (networkUrl != null) {
            databaseStatement.bindString(4, networkUrl);
        }
        String localPath = stickerEntity.getLocalPath();
        if (localPath != null) {
            databaseStatement.bindString(5, localPath);
        }
        String remark = stickerEntity.getRemark();
        if (remark != null) {
            databaseStatement.bindString(6, remark);
        }
        databaseStatement.bindLong(7, stickerEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, StickerEntity stickerEntity) {
        sQLiteStatement.clearBindings();
        Long l = stickerEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String folderName = stickerEntity.getFolderName();
        if (folderName != null) {
            sQLiteStatement.bindString(2, folderName);
        }
        String folderIconPath = stickerEntity.getFolderIconPath();
        if (folderIconPath != null) {
            sQLiteStatement.bindString(3, folderIconPath);
        }
        String networkUrl = stickerEntity.getNetworkUrl();
        if (networkUrl != null) {
            sQLiteStatement.bindString(4, networkUrl);
        }
        String localPath = stickerEntity.getLocalPath();
        if (localPath != null) {
            sQLiteStatement.bindString(5, localPath);
        }
        String remark = stickerEntity.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(6, remark);
        }
        sQLiteStatement.bindLong(7, stickerEntity.getIsEnable() ? 1L : 0L);
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
    public StickerEntity readEntity(Cursor cursor, int i) {
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
        return new StickerEntity(lValueOf, string, string2, string3, string4, cursor.isNull(i7) ? null : cursor.getString(i7), cursor.getShort(i + 6) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, StickerEntity stickerEntity, int i) {
        int i2 = i + 0;
        stickerEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        stickerEntity.setFolderName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        stickerEntity.setFolderIconPath(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        stickerEntity.setNetworkUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        stickerEntity.setLocalPath(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        stickerEntity.setRemark(cursor.isNull(i7) ? null : cursor.getString(i7));
        stickerEntity.setIsEnable(cursor.getShort(i + 6) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(StickerEntity stickerEntity, long j) {
        stickerEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(StickerEntity stickerEntity) {
        if (stickerEntity != null) {
            return stickerEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(StickerEntity stickerEntity) {
        return stickerEntity.get_id() != null;
    }
}
