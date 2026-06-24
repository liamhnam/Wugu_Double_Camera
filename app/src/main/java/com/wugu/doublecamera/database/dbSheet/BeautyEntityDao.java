package com.wugu.doublecamera.database.dbSheet;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.faceunity.core.controller.facebeauty.FaceBeautyParam;
import com.p020hp.jipp.model.MediaColor;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

public class BeautyEntityDao extends AbstractDao<BeautyEntity, Long> {
    public static final String TABLENAME = "BEAUTY_ENTITY";

    public static class Properties {
        public static final Property _id = new Property(0, Long.class, "_id", true, "_id");
        public static final Property Level = new Property(1, Integer.TYPE, "level", false, "LEVEL");
        public static final Property Blur = new Property(2, Integer.TYPE, "blur", false, "BLUR");
        public static final Property White = new Property(3, Integer.TYPE, MediaColor.white, false, "WHITE");
        public static final Property Red = new Property(4, Integer.TYPE, MediaColor.red, false, "RED");
        public static final Property Sharpen = new Property(5, Integer.TYPE, FaceBeautyParam.SHARPEN_INTENSITY, false, "SHARPEN");
        public static final Property Clarity = new Property(6, Integer.TYPE, FaceBeautyParam.CLARITY, false, "CLARITY");
        public static final Property EyeBright = new Property(7, Integer.TYPE, "eyeBright", false, "EYE_BRIGHT");
        public static final Property Tooth = new Property(8, Integer.TYPE, "tooth", false, "TOOTH");
        public static final Property RemovePouch = new Property(9, Integer.TYPE, "removePouch", false, "REMOVE_POUCH");
        public static final Property RemoveLawPat = new Property(10, Integer.TYPE, "removeLawPat", false, "REMOVE_LAW_PAT");
        public static final Property RemoveSpot = new Property(11, Integer.TYPE, "removeSpot", false, "REMOVE_SPOT");
        public static final Property FaceThin = new Property(12, Integer.TYPE, "faceThin", false, "FACE_THIN");
        public static final Property FaceV = new Property(13, Integer.TYPE, "faceV", false, "FACE_V");
        public static final Property FaceLong = new Property(14, Integer.TYPE, "faceLong", false, "FACE_LONG");
        public static final Property FaceShort = new Property(15, Integer.TYPE, "faceShort", false, "FACE_SHORT");
        public static final Property FaceCircle = new Property(16, Integer.TYPE, "faceCircle", false, "FACE_CIRCLE");
        public static final Property FaceNarrow = new Property(17, Integer.TYPE, "faceNarrow", false, "FACE_NARROW");
        public static final Property FaceSmall = new Property(18, Integer.TYPE, "faceSmall", false, "FACE_SMALL");
        public static final Property Bones = new Property(19, Integer.TYPE, "bones", false, "BONES");
        public static final Property LowerJaw = new Property(20, Integer.TYPE, "lowerJaw", false, "LOWER_JAW");
        public static final Property EyeBig = new Property(21, Integer.TYPE, "eyeBig", false, "EYE_BIG");
        public static final Property EyeCircle = new Property(22, Integer.TYPE, "eyeCircle", false, "EYE_CIRCLE");
        public static final Property Nose = new Property(23, Integer.TYPE, "nose", false, "NOSE");
        public static final Property NoseLong = new Property(24, Integer.TYPE, "noseLong", false, "NOSE_LONG");
        public static final Property Chin = new Property(25, Integer.TYPE, "chin", false, "CHIN");
        public static final Property Forehead = new Property(26, Integer.TYPE, "forehead", false, "FOREHEAD");
        public static final Property Mouth = new Property(27, Integer.TYPE, "mouth", false, "MOUTH");
        public static final Property LipThick = new Property(28, Integer.TYPE, "lipThick", false, "LIP_THICK");
        public static final Property EyeCanthus = new Property(29, Integer.TYPE, "eyeCanthus", false, "EYE_CANTHUS");
        public static final Property EyeSpace = new Property(30, Integer.TYPE, "eyeSpace", false, "EYE_SPACE");
        public static final Property EyeRotate = new Property(31, Integer.TYPE, "eyeRotate", false, "EYE_ROTATE");
        public static final Property Philtrum = new Property(32, Integer.TYPE, "philtrum", false, "PHILTRUM");
        public static final Property Smile = new Property(33, Integer.TYPE, "smile", false, "SMILE");
        public static final Property BrowY = new Property(34, Integer.TYPE, "browY", false, "BROW_Y");
        public static final Property BrowSpace = new Property(35, Integer.TYPE, "browSpace", false, "BROW_SPACE");
        public static final Property BrowThick = new Property(36, Integer.TYPE, "browThick", false, "BROW_THICK");
        public static final Property FaceThree = new Property(37, Integer.TYPE, "faceThree", false, "FACE_THREE");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public BeautyEntityDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public BeautyEntityDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"BEAUTY_ENTITY\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"LEVEL\" INTEGER NOT NULL UNIQUE ,\"BLUR\" INTEGER NOT NULL ,\"WHITE\" INTEGER NOT NULL ,\"RED\" INTEGER NOT NULL ,\"SHARPEN\" INTEGER NOT NULL ,\"CLARITY\" INTEGER NOT NULL ,\"EYE_BRIGHT\" INTEGER NOT NULL ,\"TOOTH\" INTEGER NOT NULL ,\"REMOVE_POUCH\" INTEGER NOT NULL ,\"REMOVE_LAW_PAT\" INTEGER NOT NULL ,\"REMOVE_SPOT\" INTEGER NOT NULL ,\"FACE_THIN\" INTEGER NOT NULL ,\"FACE_V\" INTEGER NOT NULL ,\"FACE_LONG\" INTEGER NOT NULL ,\"FACE_SHORT\" INTEGER NOT NULL ,\"FACE_CIRCLE\" INTEGER NOT NULL ,\"FACE_NARROW\" INTEGER NOT NULL ,\"FACE_SMALL\" INTEGER NOT NULL ,\"BONES\" INTEGER NOT NULL ,\"LOWER_JAW\" INTEGER NOT NULL ,\"EYE_BIG\" INTEGER NOT NULL ,\"EYE_CIRCLE\" INTEGER NOT NULL ,\"NOSE\" INTEGER NOT NULL ,\"NOSE_LONG\" INTEGER NOT NULL ,\"CHIN\" INTEGER NOT NULL ,\"FOREHEAD\" INTEGER NOT NULL ,\"MOUTH\" INTEGER NOT NULL ,\"LIP_THICK\" INTEGER NOT NULL ,\"EYE_CANTHUS\" INTEGER NOT NULL ,\"EYE_SPACE\" INTEGER NOT NULL ,\"EYE_ROTATE\" INTEGER NOT NULL ,\"PHILTRUM\" INTEGER NOT NULL ,\"SMILE\" INTEGER NOT NULL ,\"BROW_Y\" INTEGER NOT NULL ,\"BROW_SPACE\" INTEGER NOT NULL ,\"BROW_THICK\" INTEGER NOT NULL ,\"FACE_THREE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        database.execSQL("DROP TABLE " + (z ? "IF EXISTS " : "") + "\"BEAUTY_ENTITY\"");
    }

    @Override
    public final void bindValues(DatabaseStatement databaseStatement, BeautyEntity beautyEntity) {
        databaseStatement.clearBindings();
        Long l = beautyEntity.get_id();
        if (l != null) {
            databaseStatement.bindLong(1, l.longValue());
        }
        databaseStatement.bindLong(2, beautyEntity.getLevel());
        databaseStatement.bindLong(3, beautyEntity.getBlur());
        databaseStatement.bindLong(4, beautyEntity.getWhite());
        databaseStatement.bindLong(5, beautyEntity.getRed());
        databaseStatement.bindLong(6, beautyEntity.getSharpen());
        databaseStatement.bindLong(7, beautyEntity.getClarity());
        databaseStatement.bindLong(8, beautyEntity.getEyeBright());
        databaseStatement.bindLong(9, beautyEntity.getTooth());
        databaseStatement.bindLong(10, beautyEntity.getRemovePouch());
        databaseStatement.bindLong(11, beautyEntity.getRemoveLawPat());
        databaseStatement.bindLong(12, beautyEntity.getRemoveSpot());
        databaseStatement.bindLong(13, beautyEntity.getFaceThin());
        databaseStatement.bindLong(14, beautyEntity.getFaceV());
        databaseStatement.bindLong(15, beautyEntity.getFaceLong());
        databaseStatement.bindLong(16, beautyEntity.getFaceShort());
        databaseStatement.bindLong(17, beautyEntity.getFaceCircle());
        databaseStatement.bindLong(18, beautyEntity.getFaceNarrow());
        databaseStatement.bindLong(19, beautyEntity.getFaceSmall());
        databaseStatement.bindLong(20, beautyEntity.getBones());
        databaseStatement.bindLong(21, beautyEntity.getLowerJaw());
        databaseStatement.bindLong(22, beautyEntity.getEyeBig());
        databaseStatement.bindLong(23, beautyEntity.getEyeCircle());
        databaseStatement.bindLong(24, beautyEntity.getNose());
        databaseStatement.bindLong(25, beautyEntity.getNoseLong());
        databaseStatement.bindLong(26, beautyEntity.getChin());
        databaseStatement.bindLong(27, beautyEntity.getForehead());
        databaseStatement.bindLong(28, beautyEntity.getMouth());
        databaseStatement.bindLong(29, beautyEntity.getLipThick());
        databaseStatement.bindLong(30, beautyEntity.getEyeCanthus());
        databaseStatement.bindLong(31, beautyEntity.getEyeSpace());
        databaseStatement.bindLong(32, beautyEntity.getEyeRotate());
        databaseStatement.bindLong(33, beautyEntity.getPhiltrum());
        databaseStatement.bindLong(34, beautyEntity.getSmile());
        databaseStatement.bindLong(35, beautyEntity.getBrowY());
        databaseStatement.bindLong(36, beautyEntity.getBrowSpace());
        databaseStatement.bindLong(37, beautyEntity.getBrowThick());
        databaseStatement.bindLong(38, beautyEntity.getFaceThree());
    }

    @Override
    public final void bindValues(SQLiteStatement sQLiteStatement, BeautyEntity beautyEntity) {
        sQLiteStatement.clearBindings();
        Long l = beautyEntity.get_id();
        if (l != null) {
            sQLiteStatement.bindLong(1, l.longValue());
        }
        sQLiteStatement.bindLong(2, beautyEntity.getLevel());
        sQLiteStatement.bindLong(3, beautyEntity.getBlur());
        sQLiteStatement.bindLong(4, beautyEntity.getWhite());
        sQLiteStatement.bindLong(5, beautyEntity.getRed());
        sQLiteStatement.bindLong(6, beautyEntity.getSharpen());
        sQLiteStatement.bindLong(7, beautyEntity.getClarity());
        sQLiteStatement.bindLong(8, beautyEntity.getEyeBright());
        sQLiteStatement.bindLong(9, beautyEntity.getTooth());
        sQLiteStatement.bindLong(10, beautyEntity.getRemovePouch());
        sQLiteStatement.bindLong(11, beautyEntity.getRemoveLawPat());
        sQLiteStatement.bindLong(12, beautyEntity.getRemoveSpot());
        sQLiteStatement.bindLong(13, beautyEntity.getFaceThin());
        sQLiteStatement.bindLong(14, beautyEntity.getFaceV());
        sQLiteStatement.bindLong(15, beautyEntity.getFaceLong());
        sQLiteStatement.bindLong(16, beautyEntity.getFaceShort());
        sQLiteStatement.bindLong(17, beautyEntity.getFaceCircle());
        sQLiteStatement.bindLong(18, beautyEntity.getFaceNarrow());
        sQLiteStatement.bindLong(19, beautyEntity.getFaceSmall());
        sQLiteStatement.bindLong(20, beautyEntity.getBones());
        sQLiteStatement.bindLong(21, beautyEntity.getLowerJaw());
        sQLiteStatement.bindLong(22, beautyEntity.getEyeBig());
        sQLiteStatement.bindLong(23, beautyEntity.getEyeCircle());
        sQLiteStatement.bindLong(24, beautyEntity.getNose());
        sQLiteStatement.bindLong(25, beautyEntity.getNoseLong());
        sQLiteStatement.bindLong(26, beautyEntity.getChin());
        sQLiteStatement.bindLong(27, beautyEntity.getForehead());
        sQLiteStatement.bindLong(28, beautyEntity.getMouth());
        sQLiteStatement.bindLong(29, beautyEntity.getLipThick());
        sQLiteStatement.bindLong(30, beautyEntity.getEyeCanthus());
        sQLiteStatement.bindLong(31, beautyEntity.getEyeSpace());
        sQLiteStatement.bindLong(32, beautyEntity.getEyeRotate());
        sQLiteStatement.bindLong(33, beautyEntity.getPhiltrum());
        sQLiteStatement.bindLong(34, beautyEntity.getSmile());
        sQLiteStatement.bindLong(35, beautyEntity.getBrowY());
        sQLiteStatement.bindLong(36, beautyEntity.getBrowSpace());
        sQLiteStatement.bindLong(37, beautyEntity.getBrowThick());
        sQLiteStatement.bindLong(38, beautyEntity.getFaceThree());
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
    public BeautyEntity readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        return new BeautyEntity(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getInt(i + 1), cursor.getInt(i + 2), cursor.getInt(i + 3), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getInt(i + 6), cursor.getInt(i + 7), cursor.getInt(i + 8), cursor.getInt(i + 9), cursor.getInt(i + 10), cursor.getInt(i + 11), cursor.getInt(i + 12), cursor.getInt(i + 13), cursor.getInt(i + 14), cursor.getInt(i + 15), cursor.getInt(i + 16), cursor.getInt(i + 17), cursor.getInt(i + 18), cursor.getInt(i + 19), cursor.getInt(i + 20), cursor.getInt(i + 21), cursor.getInt(i + 22), cursor.getInt(i + 23), cursor.getInt(i + 24), cursor.getInt(i + 25), cursor.getInt(i + 26), cursor.getInt(i + 27), cursor.getInt(i + 28), cursor.getInt(i + 29), cursor.getInt(i + 30), cursor.getInt(i + 31), cursor.getInt(i + 32), cursor.getInt(i + 33), cursor.getInt(i + 34), cursor.getInt(i + 35), cursor.getInt(i + 36), cursor.getInt(i + 37));
    }

    @Override
    public void readEntity(Cursor cursor, BeautyEntity beautyEntity, int i) {
        int i2 = i + 0;
        beautyEntity.set_id(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        beautyEntity.setLevel(cursor.getInt(i + 1));
        beautyEntity.setBlur(cursor.getInt(i + 2));
        beautyEntity.setWhite(cursor.getInt(i + 3));
        beautyEntity.setRed(cursor.getInt(i + 4));
        beautyEntity.setSharpen(cursor.getInt(i + 5));
        beautyEntity.setClarity(cursor.getInt(i + 6));
        beautyEntity.setEyeBright(cursor.getInt(i + 7));
        beautyEntity.setTooth(cursor.getInt(i + 8));
        beautyEntity.setRemovePouch(cursor.getInt(i + 9));
        beautyEntity.setRemoveLawPat(cursor.getInt(i + 10));
        beautyEntity.setRemoveSpot(cursor.getInt(i + 11));
        beautyEntity.setFaceThin(cursor.getInt(i + 12));
        beautyEntity.setFaceV(cursor.getInt(i + 13));
        beautyEntity.setFaceLong(cursor.getInt(i + 14));
        beautyEntity.setFaceShort(cursor.getInt(i + 15));
        beautyEntity.setFaceCircle(cursor.getInt(i + 16));
        beautyEntity.setFaceNarrow(cursor.getInt(i + 17));
        beautyEntity.setFaceSmall(cursor.getInt(i + 18));
        beautyEntity.setBones(cursor.getInt(i + 19));
        beautyEntity.setLowerJaw(cursor.getInt(i + 20));
        beautyEntity.setEyeBig(cursor.getInt(i + 21));
        beautyEntity.setEyeCircle(cursor.getInt(i + 22));
        beautyEntity.setNose(cursor.getInt(i + 23));
        beautyEntity.setNoseLong(cursor.getInt(i + 24));
        beautyEntity.setChin(cursor.getInt(i + 25));
        beautyEntity.setForehead(cursor.getInt(i + 26));
        beautyEntity.setMouth(cursor.getInt(i + 27));
        beautyEntity.setLipThick(cursor.getInt(i + 28));
        beautyEntity.setEyeCanthus(cursor.getInt(i + 29));
        beautyEntity.setEyeSpace(cursor.getInt(i + 30));
        beautyEntity.setEyeRotate(cursor.getInt(i + 31));
        beautyEntity.setPhiltrum(cursor.getInt(i + 32));
        beautyEntity.setSmile(cursor.getInt(i + 33));
        beautyEntity.setBrowY(cursor.getInt(i + 34));
        beautyEntity.setBrowSpace(cursor.getInt(i + 35));
        beautyEntity.setBrowThick(cursor.getInt(i + 36));
        beautyEntity.setFaceThree(cursor.getInt(i + 37));
    }

    @Override
    public final Long updateKeyAfterInsert(BeautyEntity beautyEntity, long j) {
        beautyEntity.set_id(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override
    public Long getKey(BeautyEntity beautyEntity) {
        if (beautyEntity != null) {
            return beautyEntity.get_id();
        }
        return null;
    }

    @Override
    public boolean hasKey(BeautyEntity beautyEntity) {
        return beautyEntity.get_id() != null;
    }
}
