package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.arthenica.ffmpegkit.StreamInformation;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class FilterEntityDao extends AbstractDao<FilterEntity, Long> {
    public static final String TABLENAME = "FILTER_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property Index = new Property(1, Integer.TYPE, StreamInformation.KEY_INDEX, false, "INDEX");
        public static final Property FilterName = new Property(2, String.class, "filterName", false, "FILTER_NAME");
        public static final Property FilterKey = new Property(3, String.class, "filterKey", false, "FILTER_KEY");
        public static final Property FilterImagePath = new Property(4, String.class, "filterImagePath", false, "FILTER_IMAGE_PATH");
        public static final Property FilterIntensity = new Property(5, Integer.TYPE, "filterIntensity", false, "FILTER_INTENSITY");
        public static final Property FilterType = new Property(6, Integer.TYPE, "filterType", false, "FILTER_TYPE");
        public static final Property IsEnable = new Property(7, Boolean.TYPE, "isEnable", false, "IS_ENABLE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public FilterEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public FilterEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"FILTER_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"INDEX\" INTEGER NOT NULL UNIQUE ,\"FILTER_NAME\" TEXT UNIQUE ,\"FILTER_KEY\" TEXT UNIQUE ,\"FILTER_IMAGE_PATH\" TEXT,\"FILTER_INTENSITY\" INTEGER NOT NULL ,\"FILTER_TYPE\" INTEGER NOT NULL ,\"IS_ENABLE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"FILTER_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, FilterEntity filterEntity) {
        databaseStatement.clearBindings();
        Long l = filterEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        databaseStatement.bindLong(2, filterEntity.getIndex());
        String filterName = filterEntity.getFilterName();
        if (filterName != null) {
            databaseStatement.bindString(3, filterName);
        }
        String filterKey = filterEntity.getFilterKey();
        if (filterKey != null) {
            databaseStatement.bindString(4, filterKey);
        }
        String filterImagePath = filterEntity.getFilterImagePath();
        if (filterImagePath != null) {
            databaseStatement.bindString(5, filterImagePath);
        }
        databaseStatement.bindLong(6, filterEntity.getFilterIntensity());
        databaseStatement.bindLong(7, filterEntity.getFilterType());
        databaseStatement.bindLong(8, filterEntity.getIsEnable() ? 1L : 0L);
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, FilterEntity filterEntity) {
        sQLiteStatement.clearBindings();
        Long l = filterEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        sQLiteStatement.bindLong(2, filterEntity.getIndex());
        String filterName = filterEntity.getFilterName();
        if (filterName != null) {
            sQLiteStatement.bindString(3, filterName);
        }
        String filterKey = filterEntity.getFilterKey();
        if (filterKey != null) {
            sQLiteStatement.bindString(4, filterKey);
        }
        String filterImagePath = filterEntity.getFilterImagePath();
        if (filterImagePath != null) {
            sQLiteStatement.bindString(5, filterImagePath);
        }
        sQLiteStatement.bindLong(6, filterEntity.getFilterIntensity());
        sQLiteStatement.bindLong(7, filterEntity.getFilterType());
        sQLiteStatement.bindLong(8, filterEntity.getIsEnable() ? 1L : 0L);
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
    public FilterEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        Long lValueOf = cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2));
        int i3 = cursor.getInt(i + 1);
        int i4 = i + 2;
        String string = cursor.isNull(i4) ? null : cursor.getString(i4);
        int i5 = i + 3;
        String string2 = cursor.isNull(i5) ? null : cursor.getString(i5);
        int i6 = i + 4;
        return new FilterEntity(lValueOf, i3, string, string2, cursor.isNull(i6) ? null : cursor.getString(i6), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getShort(i + 7) != 0);
    }

    @Override
    public void readEntity(Cursor cursor, FilterEntity filterEntity, int i) {
        int i2 = i + 0;
        filterEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        filterEntity.setIndex(cursor.getInt(i + 1));
        int i3 = i + 2;
        filterEntity.setFilterName(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 3;
        filterEntity.setFilterKey(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 4;
        filterEntity.setFilterImagePath(cursor.isNull(i5) ? null : cursor.getString(i5));
        filterEntity.setFilterIntensity(cursor.getInt(i + 5));
        filterEntity.setFilterType(cursor.getInt(i + 6));
        filterEntity.setIsEnable(cursor.getShort(i + 7) != 0);
    }

    @Override
    public final Long updateKeyAfterInsert(FilterEntity filterEntity, long j) {
        filterEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(FilterEntity filterEntity) {
        if (filterEntity != null) {
            return filterEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(FilterEntity filterEntity) {
        return filterEntity.get_id() != null;
    }
}
