package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class FramePhotoEntityDao extends AbstractDao<FramePhotoEntity, Long> {
    public static final String TABLENAME = "FRAME_PHOTO_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property FrameKey = new Property(1, String.class, "frameKey", false, "FRAME_KEY");
        public static final Property Height = new Property(2, Integer.TYPE, StreamInformation.KEY_HEIGHT, false, "HEIGHT");
        public static final Property Width = new Property(3, Integer.TYPE, StreamInformation.KEY_WIDTH, false, "WIDTH");

        public static final Property f2450X = new Property(4, Integer.TYPE, "x", false, "X");

        public static final Property f2451Y = new Property(5, Integer.TYPE, "y", false, "Y");
        public static final Property Degrees = new Property(6, Integer.TYPE, "degrees", false, "DEGREES");
        public static final Property Remark = new Property(7, String.class, "remark", false, "REMARK");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public FramePhotoEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FramePhotoEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FRAME_PHOTO_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"FRAME_KEY\" TEXT,\"HEIGHT\" INTEGER NOT NULL ,\"WIDTH\" INTEGER NOT NULL ,\"X\" INTEGER NOT NULL ,\"Y\" INTEGER NOT NULL ,\"DEGREES\" INTEGER NOT NULL ,\"REMARK\" TEXT);");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"FRAME_PHOTO_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, FramePhotoEntity framePhotoEntity) {
        databaseStatement.clearBindings();
        Long l = framePhotoEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        String frameKey = framePhotoEntity.getFrameKey();
        if (frameKey != null) {
            databaseStatement.bindString(2, frameKey);
        }
        databaseStatement.bindLong(3, framePhotoEntity.getHeight());
        databaseStatement.bindLong(4, framePhotoEntity.getWidth());
        databaseStatement.bindLong(5, framePhotoEntity.getX());
        databaseStatement.bindLong(6, framePhotoEntity.getY());
        databaseStatement.bindLong(7, framePhotoEntity.getDegrees());
        String remark = framePhotoEntity.getRemark();
        if (remark != null) {
            databaseStatement.bindString(8, remark);
        }
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, FramePhotoEntity framePhotoEntity) {
        sQLiteStatement.clearBindings();
        Long l = framePhotoEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        String frameKey = framePhotoEntity.getFrameKey();
        if (frameKey != null) {
            sQLiteStatement.bindString(2, frameKey);
        }
        sQLiteStatement.bindLong(3, framePhotoEntity.getHeight());
        sQLiteStatement.bindLong(4, framePhotoEntity.getWidth());
        sQLiteStatement.bindLong(5, framePhotoEntity.getX());
        sQLiteStatement.bindLong(6, framePhotoEntity.getY());
        sQLiteStatement.bindLong(7, framePhotoEntity.getDegrees());
        String remark = framePhotoEntity.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(8, remark);
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
    public FramePhotoEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = i + 1;
        String string = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = cursor.getInt(i + 4);
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = i + 7;
        return new FramePhotoEntity(lValueOf, string, i4, i5, i6, i7, i8, cursor.isNull(i9) ? null : cursor.getString(i9));
    }

    @Override
    public void readEntity(Cursor cursor, FramePhotoEntity framePhotoEntity, int i) {
        int i2 = i + 0;
        framePhotoEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        framePhotoEntity.setFrameKey(cursor.isNull(i3) ? null : cursor.getString(i3));
        framePhotoEntity.setHeight(cursor.getInt(i + 2));
        framePhotoEntity.setWidth(cursor.getInt(i + 3));
        framePhotoEntity.setX(cursor.getInt(i + 4));
        framePhotoEntity.setY(cursor.getInt(i + 5));
        framePhotoEntity.setDegrees(cursor.getInt(i + 6));
        int i4 = i + 7;
        framePhotoEntity.setRemark(cursor.isNull(i4) ? null : cursor.getString(i4));
    }

    @Override
    public final Long updateKeyAfterInsert(FramePhotoEntity framePhotoEntity, long j) {
        framePhotoEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(FramePhotoEntity framePhotoEntity) {
        if (framePhotoEntity != null) {
            return framePhotoEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(FramePhotoEntity framePhotoEntity) {
        return framePhotoEntity.get_id() != null;
    }
}
