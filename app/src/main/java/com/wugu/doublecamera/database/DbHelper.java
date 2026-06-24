package com.wugu.doublecamera.database;

import android.content.Context;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.database.dbSheet.BeautyEntity;
import com.wugu.doublecamera.database.dbSheet.BeautyEntityDao;
import com.wugu.doublecamera.database.dbSheet.DaoMaster;
import com.wugu.doublecamera.database.dbSheet.DaoSession;
import com.wugu.doublecamera.database.dbSheet.EffectEntity;
import com.wugu.doublecamera.database.dbSheet.EffectEntityDao;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.database.dbSheet.FileMd5EntityDao;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.FilterEntityDao;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FrameEntityDao;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntityDao;
import com.wugu.doublecamera.database.dbSheet.FrameThemeEntity;
import com.wugu.doublecamera.database.dbSheet.FrameThemeEntityDao;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntityDao;
import com.wugu.doublecamera.database.dbSheet.SoundSettingEntity;
import com.wugu.doublecamera.database.dbSheet.SoundSettingEntityDao;
import com.wugu.doublecamera.database.dbSheet.StickerEntity;
import com.wugu.doublecamera.database.dbSheet.StickerEntityDao;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntityDao;
import com.wugu.doublecamera.enums.SoundEnum;
import java.util.List;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;

public class DbHelper {
    private DaoSession mDaoSession;

    private void dbUpgrade(Database database, int i, int i2) {
    }

    private DbHelper() {
    }

    private static class InstanceHolder {
        private static final DbHelper instance = new DbHelper();

        private InstanceHolder() {
        }
    }

    public static DbHelper getInstance() {
        return InstanceHolder.instance;
    }

    public void initDatabase(Context context) {
        if (this.mDaoSession != null) {
            return;
        }
        this.mDaoSession = new DaoMaster(new DaoMaster.DevOpenHelper(context, AppConfig.DB_NAME) {
            @Override
            public void onUpgrade(Database database, int i, int i2) {
                super.onUpgrade(database, i, i2);
            }
        }.getWritableDb()).newSession();
    }

    public void closeDatabase() {
        DaoSession daoSession = this.mDaoSession;
        if (daoSession != null) {
            daoSession.clear();
            this.mDaoSession = null;
        }
    }

    public void insertFileMd5(FileMd5Entity fileMd5Entity) {
        this.mDaoSession.getFileMd5EntityDao().insertOrReplace(fileMd5Entity);
    }

    public void deleteFileMd5(FileMd5Entity fileMd5Entity) {
        this.mDaoSession.getFileMd5EntityDao().delete(fileMd5Entity);
    }

    public void deleteFileMd5(String str) {
        this.mDaoSession.getFileMd5EntityDao().queryBuilder().where(FileMd5EntityDao.Properties.FilePath.m1477eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public FileMd5Entity getFileMd5ByMd5(String str) {
        return this.mDaoSession.getFileMd5EntityDao().queryBuilder().where(FileMd5EntityDao.Properties.FileMD5.m1477eq(str.trim().toUpperCase()), new WhereCondition[0]).unique();
    }

    public FileMd5Entity getFileMd5ByLikeName(String str) {
        List<FileMd5Entity> list = this.mDaoSession.getFileMd5EntityDao().queryBuilder().where(FileMd5EntityDao.Properties.FilePath.like("%/" + str), new WhereCondition[0]).list();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<FileMd5Entity> getFileMd5ByType(int i) {
        return this.mDaoSession.getFileMd5EntityDao().queryBuilder().where(FileMd5EntityDao.Properties.FileType.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).list();
    }

    public List<FileMd5Entity> getAllFileMd5() {
        return this.mDaoSession.getFileMd5EntityDao().loadAll();
    }

    public void clearFrameThemeSheet() {
        this.mDaoSession.getFrameThemeEntityDao().deleteAll();
    }

    public boolean isFrameThemeEmpty() {
        return this.mDaoSession.getFrameThemeEntityDao().count() == 0;
    }

    public void insertFrameTheme(FrameThemeEntity frameThemeEntity) {
        this.mDaoSession.getFrameThemeEntityDao().insertOrReplace(frameThemeEntity);
    }

    public void deleteFrameTheme(FrameThemeEntity frameThemeEntity) {
        this.mDaoSession.getFrameThemeEntityDao().delete(frameThemeEntity);
    }

    public void deleteNetworkFrameTheme() {
        this.mDaoSession.getFrameThemeEntityDao().queryBuilder().where(FrameThemeEntityDao.Properties.BackgroundNetUrl.isNotNull(), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public FrameThemeEntity getFrameThemeByKey(String str) {
        return this.mDaoSession.getFrameThemeEntityDao().queryBuilder().where(FrameThemeEntityDao.Properties.ThemeKey.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<FrameThemeEntity> getFrameThemeBgNetUrl(String str) {
        return this.mDaoSession.getFrameThemeEntityDao().queryBuilder().where(FrameThemeEntityDao.Properties.BackgroundNetUrl.m1477eq(str), new WhereCondition[0]).orderAsc(FrameThemeEntityDao.Properties.Index).list();
    }

    public List<FrameThemeEntity> getFrameThemeBtnNetUrl(String str) {
        return this.mDaoSession.getFrameThemeEntityDao().queryBuilder().where(FrameThemeEntityDao.Properties.ButtonNetUrl.m1477eq(str), new WhereCondition[0]).orderAsc(FrameThemeEntityDao.Properties.Index).list();
    }

    public List<FrameThemeEntity> getFrameThemeByParentId(String str) {
        return this.mDaoSession.getFrameThemeEntityDao().queryBuilder().where(FrameThemeEntityDao.Properties.ParentId.m1477eq(str), new WhereCondition[0]).orderAsc(FrameThemeEntityDao.Properties.Index).list();
    }

    public List<FrameThemeEntity> getAllFrameThemes() {
        return this.mDaoSession.getFrameThemeEntityDao().queryBuilder().orderAsc(FrameThemeEntityDao.Properties.Index).list();
    }

    public void clearFrameSheet() {
        this.mDaoSession.getFrameEntityDao().deleteAll();
    }

    public boolean isFrameEmpty() {
        return this.mDaoSession.getFrameEntityDao().count() == 0;
    }

    public void insertFrame(FrameEntity frameEntity) {
        this.mDaoSession.getFrameEntityDao().insertOrReplace(frameEntity);
    }

    public void deleteFrame(FrameEntity frameEntity) {
        this.mDaoSession.getFrameEntityDao().delete(frameEntity);
    }

    public void deleteNetworkFrame() {
        this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameNetUrl.isNotNull(), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public FrameEntity getFrameByKey(String str) {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameKey.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<FrameEntity> getFramesByTheme(String str) {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.ThemeKey.m1477eq(str), new WhereCondition[0]).orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public List<FrameEntity> getFramesByTypeAndPhotoCount(int i, int i2) {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameType.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).where(FrameEntityDao.Properties.PhotoCount.m1477eq(Integer.valueOf(i2)), new WhereCondition[0]).orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public List<FrameEntity> getFramesByType(int i) {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameType.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public FrameEntity getFrameByNetUrl(String str) {
        List<FrameEntity> list = this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameNetUrl.m1477eq(str), new WhereCondition[0]).list();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<FrameEntity> getAiFramesByThemeKey(String str) {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.ThemeKey.m1477eq(str), new WhereCondition[0]).orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public List<FrameEntity> getAllFramesWithoutAI() {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().where(FrameEntityDao.Properties.FrameType.notEq(1), new WhereCondition[0]).orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public List<FrameEntity> getAllFrames() {
        return this.mDaoSession.getFrameEntityDao().queryBuilder().orderAsc(FrameEntityDao.Properties.Index).list();
    }

    public void clearFramePhotoSheet() {
        this.mDaoSession.getFramePhotoEntityDao().deleteAll();
    }

    public boolean isFramePhotoEmpty() {
        return this.mDaoSession.getFramePhotoEntityDao().count() == 0;
    }

    public void insertFramePhoto(FramePhotoEntity framePhotoEntity) {
        this.mDaoSession.getFramePhotoEntityDao().insertOrReplace(framePhotoEntity);
    }

    public void deleteFramePhoto(FramePhotoEntity framePhotoEntity) {
        this.mDaoSession.getFramePhotoEntityDao().delete(framePhotoEntity);
    }

    public void deleteFramePhoto(String str) {
        this.mDaoSession.getFramePhotoEntityDao().queryBuilder().where(FramePhotoEntityDao.Properties.FrameKey.m1477eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public List<FramePhotoEntity> getFramePhotos(String str) {
        return this.mDaoSession.getFramePhotoEntityDao().queryBuilder().where(FramePhotoEntityDao.Properties.FrameKey.m1477eq(str), new WhereCondition[0]).list();
    }

    public void clearStickerSheet() {
        this.mDaoSession.getStickerEntityDao().deleteAll();
    }

    public boolean isStickerEmpty() {
        return this.mDaoSession.getStickerEntityDao().count() == 0;
    }

    public void insertSticker(StickerEntity stickerEntity) {
        this.mDaoSession.getStickerEntityDao().insertOrReplace(stickerEntity);
    }

    public void deleteSticker(StickerEntity stickerEntity) {
        this.mDaoSession.getStickerEntityDao().delete(stickerEntity);
    }

    public void deleteNetworkSticker() {
        this.mDaoSession.getStickerEntityDao().queryBuilder().where(StickerEntityDao.Properties.NetworkUrl.isNotNull(), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public StickerEntity getStickerByNetUrl(String str) {
        return this.mDaoSession.getStickerEntityDao().queryBuilder().where(StickerEntityDao.Properties.NetworkUrl.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<StickerEntity> getStickerByFolder(String str) {
        return this.mDaoSession.getStickerEntityDao().queryBuilder().where(StickerEntityDao.Properties.FolderName.m1477eq(str), new WhereCondition[0]).list();
    }

    public List<StickerEntity> getAllStickers() {
        return this.mDaoSession.getStickerEntityDao().loadAll();
    }

    public void clearSoundSettingSheet() {
        this.mDaoSession.getSoundSettingEntityDao().deleteAll();
    }

    public void insertSoundSetting(SoundSettingEntity soundSettingEntity) {
        this.mDaoSession.getSoundSettingEntityDao().insertOrReplace(soundSettingEntity);
    }

    public SoundSettingEntity getSoundSettingByUrl(String str) {
        return this.mDaoSession.getSoundSettingEntityDao().queryBuilder().where(SoundSettingEntityDao.Properties.NetUrl.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<SoundSettingEntity> getSoundSettingByType(String str) {
        return this.mDaoSession.getSoundSettingEntityDao().queryBuilder().where(SoundSettingEntityDao.Properties.Type.m1477eq(str), new WhereCondition[0]).list();
    }

    public void delSoundSettingByType(String str) {
        this.mDaoSession.getSoundSettingEntityDao().queryBuilder().where(SoundSettingEntityDao.Properties.Type.m1477eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public String getSoundPathByType(int i) {
        List<SoundSettingEntity> soundSettingByType = getSoundSettingByType(SoundEnum.getSoundTypeName(i));
        if (soundSettingByType == null || soundSettingByType.isEmpty()) {
            return null;
        }
        return soundSettingByType.get(0).getLocalPath();
    }

    public void clearEffectSheet() {
        this.mDaoSession.getEffectEntityDao().deleteAll();
    }

    public boolean isEffectEmpty() {
        return this.mDaoSession.getEffectEntityDao().count() == 0;
    }

    public void insertEffect(EffectEntity effectEntity) {
        this.mDaoSession.getEffectEntityDao().insertOrReplace(effectEntity);
    }

    public void deleteEffect(EffectEntity effectEntity) {
        this.mDaoSession.getEffectEntityDao().delete(effectEntity);
    }

    public EffectEntity getEffectByIndex(int i) {
        return this.mDaoSession.getEffectEntityDao().queryBuilder().where(EffectEntityDao.Properties.Index.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public EffectEntity getEffectByDemo(String str) {
        return this.mDaoSession.getEffectEntityDao().queryBuilder().where(EffectEntityDao.Properties.EffectDemoUrl.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<EffectEntity> getAllEffect() {
        return this.mDaoSession.getEffectEntityDao().loadAll();
    }

    public void clearBeautySheet() {
        this.mDaoSession.getBeautyEntityDao().deleteAll();
    }

    public boolean isBeautyEmpty() {
        return this.mDaoSession.getBeautyEntityDao().count() == 0;
    }

    public void insertBeauty(BeautyEntity beautyEntity) {
        this.mDaoSession.getBeautyEntityDao().insertOrReplace(beautyEntity);
    }

    public void deleteBeauty(BeautyEntity beautyEntity) {
        this.mDaoSession.getBeautyEntityDao().delete(beautyEntity);
    }

    public BeautyEntity getBeautyByLevel(int i) {
        return this.mDaoSession.getBeautyEntityDao().queryBuilder().where(BeautyEntityDao.Properties.Level.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public List<BeautyEntity> getAllBeauty() {
        return this.mDaoSession.getBeautyEntityDao().loadAll();
    }

    public void clearFilterSheet() {
        this.mDaoSession.getFilterEntityDao().deleteAll();
    }

    public boolean isFilterEmpty() {
        return this.mDaoSession.getFilterEntityDao().count() == 0;
    }

    public void insertFilter(FilterEntity filterEntity) {
        this.mDaoSession.getFilterEntityDao().insertOrReplace(filterEntity);
    }

    public void deleteFilter(FilterEntity filterEntity) {
        this.mDaoSession.getFilterEntityDao().delete(filterEntity);
    }

    public FilterEntity getFilterByName(String str) {
        return this.mDaoSession.getFilterEntityDao().queryBuilder().where(FilterEntityDao.Properties.FilterName.m1477eq(str), new WhereCondition[0]).unique();
    }

    public FilterEntity getFilterByIndex(int i) {
        return this.mDaoSession.getFilterEntityDao().queryBuilder().where(FilterEntityDao.Properties.Index.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public List<FilterEntity> getAllFilter() {
        return this.mDaoSession.getFilterEntityDao().loadAll();
    }

    public void clearMakeupSheet() {
        this.mDaoSession.getMakeupEntityDao().deleteAll();
    }

    public boolean isMakeupEmpty() {
        return this.mDaoSession.getMakeupEntityDao().count() == 0;
    }

    public void insertMakeup(MakeupEntity makeupEntity) {
        this.mDaoSession.getMakeupEntityDao().insertOrReplace(makeupEntity);
    }

    public void deleteMakeup(MakeupEntity makeupEntity) {
        this.mDaoSession.getMakeupEntityDao().delete(makeupEntity);
    }

    public MakeupEntity getMakeupByIndex(int i) {
        return this.mDaoSession.getMakeupEntityDao().queryBuilder().where(MakeupEntityDao.Properties.Index.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public MakeupEntity getMakeupByName(String str) {
        return this.mDaoSession.getMakeupEntityDao().queryBuilder().where(MakeupEntityDao.Properties.MakeupName.m1477eq(str), new WhereCondition[0]).unique();
    }

    public List<MakeupEntity> getAllMakeup() {
        return this.mDaoSession.getMakeupEntityDao().loadAll();
    }

    public boolean isUiPositionEmpty() {
        return this.mDaoSession.getUiPositionEntityDao().count() == 0;
    }

    public void clearUiPositionSheet() {
        this.mDaoSession.getUiPositionEntityDao().deleteAll();
    }

    public void insertUiPosition(UiPositionEntity uiPositionEntity) {
        this.mDaoSession.getUiPositionEntityDao().insertOrReplace(uiPositionEntity);
    }

    public void deleteUiPosition(UiPositionEntity uiPositionEntity) {
        this.mDaoSession.getUiPositionEntityDao().delete(uiPositionEntity);
    }

    public void deleteUiPosition(int i) {
        UiPositionEntity uiPositionEntityUnique = this.mDaoSession.getUiPositionEntityDao().queryBuilder().where(UiPositionEntityDao.Properties.Index.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
        if (uiPositionEntityUnique == null) {
            return;
        }
        this.mDaoSession.getUiPositionEntityDao().delete(uiPositionEntityUnique);
    }

    public UiPositionEntity getUiPositionEntity(int i) {
        return this.mDaoSession.getUiPositionEntityDao().queryBuilder().where(UiPositionEntityDao.Properties.Index.m1477eq(Integer.valueOf(i)), new WhereCondition[0]).unique();
    }

    public List<UiPositionEntity> getAllUiPositionEntity() {
        return this.mDaoSession.getUiPositionEntityDao().loadAll();
    }

    public List<UiPositionEntity> getUiPosByNetUrl(String str) {
        return this.mDaoSession.getUiPositionEntityDao().queryBuilder().where(UiPositionEntityDao.Properties.ResNetUrl.m1477eq(str), new WhereCondition[0]).list();
    }
}
