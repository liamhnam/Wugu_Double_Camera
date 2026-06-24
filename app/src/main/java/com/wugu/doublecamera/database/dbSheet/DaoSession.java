package com.wugu.doublecamera.database.dbSheet;

import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

public class DaoSession extends AbstractDaoSession {
    private final BeautyEntityDao beautyEntityDao;
    private final DaoConfig beautyEntityDaoConfig;
    private final EffectEntityDao effectEntityDao;
    private final DaoConfig effectEntityDaoConfig;
    private final FileMd5EntityDao fileMd5EntityDao;
    private final DaoConfig fileMd5EntityDaoConfig;
    private final FilterEntityDao filterEntityDao;
    private final DaoConfig filterEntityDaoConfig;
    private final FrameEntityDao frameEntityDao;
    private final DaoConfig frameEntityDaoConfig;
    private final FramePhotoEntityDao framePhotoEntityDao;
    private final DaoConfig framePhotoEntityDaoConfig;
    private final FrameThemeEntityDao frameThemeEntityDao;
    private final DaoConfig frameThemeEntityDaoConfig;
    private final MakeupEntityDao makeupEntityDao;
    private final DaoConfig makeupEntityDaoConfig;
    private final SoundSettingEntityDao soundSettingEntityDao;
    private final DaoConfig soundSettingEntityDaoConfig;
    private final StickerEntityDao stickerEntityDao;
    private final DaoConfig stickerEntityDaoConfig;
    private final UiPositionEntityDao uiPositionEntityDao;
    private final DaoConfig uiPositionEntityDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        DaoConfig daoConfigClone = map.get(BeautyEntityDao.class).clone();
        this.beautyEntityDaoConfig = daoConfigClone;
        daoConfigClone.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone2 = map.get(EffectEntityDao.class).clone();
        this.effectEntityDaoConfig = daoConfigClone2;
        daoConfigClone2.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone3 = map.get(FileMd5EntityDao.class).clone();
        this.fileMd5EntityDaoConfig = daoConfigClone3;
        daoConfigClone3.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone4 = map.get(FilterEntityDao.class).clone();
        this.filterEntityDaoConfig = daoConfigClone4;
        daoConfigClone4.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone5 = map.get(FrameEntityDao.class).clone();
        this.frameEntityDaoConfig = daoConfigClone5;
        daoConfigClone5.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone6 = map.get(FramePhotoEntityDao.class).clone();
        this.framePhotoEntityDaoConfig = daoConfigClone6;
        daoConfigClone6.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone7 = map.get(FrameThemeEntityDao.class).clone();
        this.frameThemeEntityDaoConfig = daoConfigClone7;
        daoConfigClone7.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone8 = map.get(MakeupEntityDao.class).clone();
        this.makeupEntityDaoConfig = daoConfigClone8;
        daoConfigClone8.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone9 = map.get(SoundSettingEntityDao.class).clone();
        this.soundSettingEntityDaoConfig = daoConfigClone9;
        daoConfigClone9.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone10 = map.get(StickerEntityDao.class).clone();
        this.stickerEntityDaoConfig = daoConfigClone10;
        daoConfigClone10.initIdentityScope(identityScopeType);
        DaoConfig daoConfigClone11 = map.get(UiPositionEntityDao.class).clone();
        this.uiPositionEntityDaoConfig = daoConfigClone11;
        daoConfigClone11.initIdentityScope(identityScopeType);
        BeautyEntityDao beautyEntityDao = new BeautyEntityDao(daoConfigClone, this);
        this.beautyEntityDao = beautyEntityDao;
        EffectEntityDao effectEntityDao = new EffectEntityDao(daoConfigClone2, this);
        this.effectEntityDao = effectEntityDao;
        FileMd5EntityDao fileMd5EntityDao = new FileMd5EntityDao(daoConfigClone3, this);
        this.fileMd5EntityDao = fileMd5EntityDao;
        FilterEntityDao filterEntityDao = new FilterEntityDao(daoConfigClone4, this);
        this.filterEntityDao = filterEntityDao;
        FrameEntityDao frameEntityDao = new FrameEntityDao(daoConfigClone5, this);
        this.frameEntityDao = frameEntityDao;
        FramePhotoEntityDao framePhotoEntityDao = new FramePhotoEntityDao(daoConfigClone6, this);
        this.framePhotoEntityDao = framePhotoEntityDao;
        FrameThemeEntityDao frameThemeEntityDao = new FrameThemeEntityDao(daoConfigClone7, this);
        this.frameThemeEntityDao = frameThemeEntityDao;
        MakeupEntityDao makeupEntityDao = new MakeupEntityDao(daoConfigClone8, this);
        this.makeupEntityDao = makeupEntityDao;
        SoundSettingEntityDao soundSettingEntityDao = new SoundSettingEntityDao(daoConfigClone9, this);
        this.soundSettingEntityDao = soundSettingEntityDao;
        StickerEntityDao stickerEntityDao = new StickerEntityDao(daoConfigClone10, this);
        this.stickerEntityDao = stickerEntityDao;
        UiPositionEntityDao uiPositionEntityDao = new UiPositionEntityDao(daoConfigClone11, this);
        this.uiPositionEntityDao = uiPositionEntityDao;
        registerDao(BeautyEntity.class, beautyEntityDao);
        registerDao(EffectEntity.class, effectEntityDao);
        registerDao(FileMd5Entity.class, fileMd5EntityDao);
        registerDao(FilterEntity.class, filterEntityDao);
        registerDao(FrameEntity.class, frameEntityDao);
        registerDao(FramePhotoEntity.class, framePhotoEntityDao);
        registerDao(FrameThemeEntity.class, frameThemeEntityDao);
        registerDao(MakeupEntity.class, makeupEntityDao);
        registerDao(SoundSettingEntity.class, soundSettingEntityDao);
        registerDao(StickerEntity.class, stickerEntityDao);
        registerDao(UiPositionEntity.class, uiPositionEntityDao);
    }

    public void clear() {
        this.beautyEntityDaoConfig.clearIdentityScope();
        this.effectEntityDaoConfig.clearIdentityScope();
        this.fileMd5EntityDaoConfig.clearIdentityScope();
        this.filterEntityDaoConfig.clearIdentityScope();
        this.frameEntityDaoConfig.clearIdentityScope();
        this.framePhotoEntityDaoConfig.clearIdentityScope();
        this.frameThemeEntityDaoConfig.clearIdentityScope();
        this.makeupEntityDaoConfig.clearIdentityScope();
        this.soundSettingEntityDaoConfig.clearIdentityScope();
        this.stickerEntityDaoConfig.clearIdentityScope();
        this.uiPositionEntityDaoConfig.clearIdentityScope();
    }

    public BeautyEntityDao getBeautyEntityDao() {
        return this.beautyEntityDao;
    }

    public EffectEntityDao getEffectEntityDao() {
        return this.effectEntityDao;
    }

    public FileMd5EntityDao getFileMd5EntityDao() {
        return this.fileMd5EntityDao;
    }

    public FilterEntityDao getFilterEntityDao() {
        return this.filterEntityDao;
    }

    public FrameEntityDao getFrameEntityDao() {
        return this.frameEntityDao;
    }

    public FramePhotoEntityDao getFramePhotoEntityDao() {
        return this.framePhotoEntityDao;
    }

    public FrameThemeEntityDao getFrameThemeEntityDao() {
        return this.frameThemeEntityDao;
    }

    public MakeupEntityDao getMakeupEntityDao() {
        return this.makeupEntityDao;
    }

    public SoundSettingEntityDao getSoundSettingEntityDao() {
        return this.soundSettingEntityDao;
    }

    public StickerEntityDao getStickerEntityDao() {
        return this.stickerEntityDao;
    }

    public UiPositionEntityDao getUiPositionEntityDao() {
        return this.uiPositionEntityDao;
    }
}
