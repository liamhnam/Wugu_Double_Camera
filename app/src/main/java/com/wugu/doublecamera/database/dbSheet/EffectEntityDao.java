package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class EffectEntityDao extends AbstractDao<EffectEntity, Long> {
    public static final String TABLENAME = "EFFECT_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property EffectType = new Property(1, Integer.TYPE, "effectType", false, "EFFECT_TYPE");
        public static final Property Index = new Property(2, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");
        public static final Property EffectName = new Property(3, String.class, "effectName", false, "EFFECT_NAME");
        public static final Property EffectUrl = new Property(4, String.class, "effectUrl", false, "EFFECT_URL");
        public static final Property EffectDemoUrl = new Property(5, String.class, "effectDemoUrl", false, "EFFECT_DEMO_URL");
        public static final Property Remark = new Property(6, String.class, "remark", false, "REMARK");
        public static final Property IsEnable = new Property(7, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public EffectEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public EffectEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"EFFECT_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"EFFECT_TYPE\" INTEGER NOT NULL ,\"INDEX\" INTEGER NOT NULL UNIQUE ,\"EFFECT_NAME\" TEXT,\"EFFECT_URL\" TEXT,\"EFFECT_DEMO_URL\" TEXT,\"REMARK\" TEXT,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"EFFECT_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, EffectEntity effectEntity) {
        databaseStatement.clearBindings();
        Long l = effectEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        databaseStatement.bindLong(2, effectEntity.getEffectType());
        databaseStatement.bindLong(3, effectEntity.getIndex());
        String effectName = effectEntity.getEffectName();
        if (effectName != null) {
            databaseStatement.bindString(4, effectName);
        }
        String effectUrl = effectEntity.getEffectUrl();
        if (effectUrl != null) {
            databaseStatement.bindString(5, effectUrl);
        }
        String effectDemoUrl = effectEntity.getEffectDemoUrl();
        if (effectDemoUrl != null) {
            databaseStatement.bindString(6, effectDemoUrl);
        }
        String remark = effectEntity.getRemark();
        if (remark != null) {
            databaseStatement.bindString(7, remark);
        }
        databaseStatement.bindLong(8, effectEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, EffectEntity effectEntity) {
        sQLiteStatement.clearBindings();
        Long l = effectEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        sQLiteStatement.bindLong(2, effectEntity.getEffectType());
        sQLiteStatement.bindLong(3, effectEntity.getIndex());
        String effectName = effectEntity.getEffectName();
        if (effectName != null) {
            sQLiteStatement.bindString(4, effectName);
        }
        String effectUrl = effectEntity.getEffectUrl();
        if (effectUrl != null) {
            sQLiteStatement.bindString(5, effectUrl);
        }
        String effectDemoUrl = effectEntity.getEffectDemoUrl();
        if (effectDemoUrl != null) {
            sQLiteStatement.bindString(6, effectDemoUrl);
        }
        String remark = effectEntity.getRemark();
        if (remark != null) {
            sQLiteStatement.bindString(7, remark);
        }
        sQLiteStatement.bindLong(8, effectEntity.getIsEnable() ? 1L : 0L);
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
    public EffectEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = cursor.getInt(i + 2);
        int i5 = i + 3;
        String string = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        String string2 = cursor.isNull(i6) ? null : cursor.getString(i6);
        int i7 = i + 5;
        String string3 = cursor.isNull(i7) ? null : cursor.getString(i7);
        int i8 = i + 6;
        return new EffectEntity(lValueOf, i3, i4, string, string2, string3, cursor.isNull(i8) ? null : cursor.getString(i8), cursor.getShort(i + 7) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, EffectEntity effectEntity, int i) {
        int i2 = i + 0;
        effectEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        effectEntity.setEffectType(cursor.getInt(i + 1));
        effectEntity.setIndex(cursor.getInt(i + 2));
        int i3 = i + 3;
        effectEntity.setEffectName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 4;
        effectEntity.setEffectUrl(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 5;
        effectEntity.setEffectDemoUrl(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 6;
        effectEntity.setRemark(cursor.isNull(i6) ? null : cursor.getString(i6));
        effectEntity.setIsEnable(cursor.getShort(i + 7) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(EffectEntity effectEntity, long j) {
        effectEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(EffectEntity effectEntity) {
        if (effectEntity != null) {
            return effectEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(EffectEntity effectEntity) {
        return effectEntity.get_id() != null;
    }
}
