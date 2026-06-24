package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class FileMd5EntityDao extends AbstractDao<FileMd5Entity, Long> {
    public static final String TABLENAME = "FILE_MD5_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property FilePath = new Property(1, String.class, "filePath", false, "FILE_PATH");
        public static final Property FileMD5 = new Property(2, String.class, "fileMD5", false, "FILE_MD5");
        public static final Property FileType = new Property(3, Integer.TYPE, "fileType", false, "FILE_TYPE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public FileMd5EntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FileMd5EntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FILE_MD5_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"FILE_PATH\" TEXT UNIQUE ,\"FILE_MD5\" TEXT UNIQUE ,\"FILE_TYPE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"FILE_MD5_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, FileMd5Entity fileMd5Entity) {
        databaseStatement.clearBindings();
        Long l = fileMd5Entity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String filePath = fileMd5Entity.getFilePath();
        if (filePath != null) {
            databaseStatement.bindString(2, filePath);
        }
        String fileMD5 = fileMd5Entity.getFileMD5();
        if (fileMD5 != null) {
            databaseStatement.bindString(3, fileMD5);
        }
        databaseStatement.bindLong(4, fileMd5Entity.getFileType());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, FileMd5Entity fileMd5Entity) {
        sQLiteStatement.clearBindings();
        Long l = fileMd5Entity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String filePath = fileMd5Entity.getFilePath();
        if (filePath != null) {
            sQLiteStatement.bindString(2, filePath);
        }
        String fileMD5 = fileMd5Entity.getFileMD5();
        if (fileMD5 != null) {
            sQLiteStatement.bindString(3, fileMD5);
        }
        sQLiteStatement.bindLong(4, fileMd5Entity.getFileType());
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
    public FileMd5Entity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 1;
        int i4 = i + 2;
        return new FileMd5Entity(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.isNull(i3) ? null : cursor.getString(i3), cursor.isNull(i4) ? null : cursor.getString(i4), cursor.getInt(i + 3));
    }

    @Override
    public void readEntity(Cursor cursor, FileMd5Entity fileMd5Entity, int i) {
        int i2 = i + 0;
        fileMd5Entity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        fileMd5Entity.setFilePath(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        fileMd5Entity.setFileMD5(cursor.isNull(i4) ? null : cursor.getString(i4));
        fileMd5Entity.setFileType(cursor.getInt(i + 3));
    }

    @Override
    public final Long updateKeyAfterInsert(FileMd5Entity fileMd5Entity, long j) {
        fileMd5Entity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(FileMd5Entity fileMd5Entity) {
        if (fileMd5Entity != null) {
            return fileMd5Entity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(FileMd5Entity fileMd5Entity) {
        return fileMd5Entity.get_id() != null;
    }
}
