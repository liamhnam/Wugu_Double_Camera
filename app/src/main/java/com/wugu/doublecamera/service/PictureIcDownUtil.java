package com.wugu.doublecamera.service;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.DefaultResConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.AllResDto;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.UiParamDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.EffectEntity;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FrameThemeEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.database.dbSheet.StickerEntity;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PictureIcDownUtil {
    private static final AtomicInteger downFrameCount = new AtomicInteger(0);

    public static void updateNetFrames(final IIntListener iIntListener) {
        downFrameCount.set(0);
        updateThemeBg(iIntListener);
        ThreadClock.sleep(1000L);
        HttpManager.getInstance().mHttpApi.getFrameList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                IIntListener iIntListener2;
                String str;
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                List<FrameEntity> allFramesWithoutAI = DbHelper.getInstance().getAllFramesWithoutAI();
                String str2 = AppConfig.FRAME_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                DbHelper.getInstance().clearFramePhotoSheet();
                ThreadClock.sleep(100L);
                Iterator<AllResDto.Data> it = baseDto.data.data.iterator();
                while (true) {
                    int i = 3;
                    if (!it.hasNext()) {
                        break;
                    }
                    AllResDto.Data next = it.next();
                    if (next != null && !TextUtils.isEmpty(next.topicId) && next.items != null && !next.items.isEmpty()) {
                        int i2 = next.model;
                        if (i2 != 2) {
                            if (i2 != 3) {
                                i = 5;
                                if (i2 != 4) {
                                    i = i2 != 5 ? i2 != 6 ? 0 : 4 : 1;
                                }
                            } else {
                                i = 6;
                            }
                        }
                        for (AllResDto.Items items : next.items) {
                            if (items != null && !TextUtils.isEmpty(items.url)) {
                                if (!TextUtils.isEmpty(items.hash)) {
                                    String str3 = next.topicId;
                                    String str4 = items.easyId;
                                    String upperCase = items.hash.trim().toUpperCase();
                                    Iterator<AllResDto.Data> it2 = it;
                                    AllResDto.Data data = next;
                                    String str5 = AppConfig.HTTP_HOST + items.url;
                                    arrayList4.add(str4);
                                    if (!TextUtils.isEmpty(upperCase) && !arrayList3.contains(upperCase)) {
                                        arrayList3.add(upperCase);
                                    }
                                    FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(upperCase);
                                    if (fileMd5ByMd5 == null && !arrayList.contains(str5)) {
                                        arrayList.add(str5);
                                        arrayList2.add(str2);
                                        PictureIcDownUtil.downFrameCount.incrementAndGet();
                                    }
                                    FrameEntity frameByKey = DbHelper.getInstance().getFrameByKey(str4);
                                    String filePath = fileMd5ByMd5 != null ? fileMd5ByMd5.getFilePath() : null;
                                    if (frameByKey != null) {
                                        str = str2;
                                        frameByKey.setFrameName(items.name);
                                        frameByKey.setThemeKey(str3);
                                        frameByKey.setFrameNetUrl(str5);
                                        frameByKey.setPrice(MathUtil.getPriceValue(items.productNowPrice));
                                        frameByKey.setAddPrice(MathUtil.getPriceValue(items.addNowPrice));
                                        frameByKey.setOPrice(MathUtil.getPriceValue(items.productPrice));
                                        frameByKey.setAddOPrice(MathUtil.getPriceValue(items.addPrice));
                                        frameByKey.setPrintCount(items.printCount);
                                        frameByKey.setIsNeedCut(items.isNeedCut == 1);
                                        frameByKey.setIndex(items.orderNo);
                                        frameByKey.setBurstModeCount(items.burstModeCount);
                                        frameByKey.setColorParam(items.printer);
                                        frameByKey.setRemark(items.getRemarkStr());
                                        frameByKey.setFrameType(i);
                                        if (filePath != null) {
                                            frameByKey.setFrameLocalPath(filePath);
                                        }
                                    } else {
                                        str = str2;
                                        frameByKey = items.getFrameEntity(str3, str5, filePath, i);
                                        LoggerUtil.m1181d("picture", "add frame " + items.name + ", frameId " + str4);
                                    }
                                    if (items.photoList != null && !items.photoList.isEmpty()) {
                                        Iterator<AllResDto.PhotoList> it3 = items.photoList.iterator();
                                        while (it3.hasNext()) {
                                            DbHelper.getInstance().insertFramePhoto(it3.next().getFramePhotoEntity(str4));
                                        }
                                    }
                                    DbHelper.getInstance().insertFrame(frameByKey);
                                    it = it2;
                                    next = data;
                                    str2 = str;
                                }
                            }
                        }
                    }
                }
                if (allFramesWithoutAI != null && !allFramesWithoutAI.isEmpty()) {
                    for (FrameEntity frameEntity : allFramesWithoutAI) {
                        if (!arrayList4.contains(frameEntity.getFrameKey())) {
                            LoggerUtil.m1181d("picture", "del frame " + frameEntity.getFrameKey() + ", " + frameEntity.getFrameName());
                            DbHelper.getInstance().deleteFrame(frameEntity);
                        }
                    }
                }
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 2);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
                if (PictureIcDownUtil.downFrameCount.get() != 0 || (iIntListener2 = iIntListener) == null) {
                    return;
                }
                iIntListener2.setValue(3);
            }
        });
    }

    private static void updateThemeBg(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getThemeBgList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                Iterator<AllResDto.Data> it;
                String str;
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ThreadClock.sleep(100L);
                List<FrameThemeEntity> allFrameThemes = DbHelper.getInstance().getAllFrameThemes();
                String str2 = AppConfig.THEME_BG_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                Iterator<AllResDto.Data> it2 = baseDto.data.data.iterator();
                String str3 = null;
                String str4 = null;
                while (it2.hasNext()) {
                    AllResDto.Data next = it2.next();
                    if (next != null && !TextUtils.isEmpty(next.topicId) && next.items != null && !next.items.isEmpty()) {
                        if (next.model == 5) {
                            str3 = next.benefitType;
                            str4 = next.topicId;
                        } else {
                            AllResDto.Items items = next.items.get(0);
                            String str5 = items.url;
                            if (!TextUtils.isEmpty(str5) && !TextUtils.isEmpty(items.topicId) && !TextUtils.isEmpty(items.hash)) {
                                String str6 = items.topicId;
                                it = it2;
                                String str7 = AppConfig.HTTP_HOST + str5;
                                if (!arrayList4.contains(str6)) {
                                    arrayList4.add(str6);
                                }
                                if (!TextUtils.isEmpty(items.hash) && !arrayList3.contains(items.hash.trim().toUpperCase())) {
                                    arrayList3.add(items.hash.trim().toUpperCase());
                                }
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(items.hash.trim().toUpperCase());
                                if (fileMd5ByMd5 == null && !arrayList.contains(str7)) {
                                    arrayList.add(str7);
                                    arrayList2.add(str2);
                                    PictureIcDownUtil.downFrameCount.incrementAndGet();
                                }
                                FrameThemeEntity frameThemeByKey = DbHelper.getInstance().getFrameThemeByKey(str6);
                                String filePath = fileMd5ByMd5 != null ? fileMd5ByMd5.getFilePath() : null;
                                if (frameThemeByKey != null) {
                                    frameThemeByKey.setThemeName(next.topicName);
                                    frameThemeByKey.setIndex(next.orderNo);
                                    frameThemeByKey.setBackgroundNetUrl(str7);
                                    if (filePath != null) {
                                        frameThemeByKey.setBackgroundPath(filePath);
                                    }
                                    str = str2;
                                } else {
                                    str = str2;
                                    frameThemeByKey = new FrameThemeEntity(null, str6, next.topicName, str7, null, filePath, null, null, null, null, next.orderNo, true);
                                    LoggerUtil.m1181d("picture", "add theme " + next.topicName + ", index " + next.orderNo);
                                }
                                DbHelper.getInstance().insertFrameTheme(frameThemeByKey);
                            }
                            it2 = it;
                            str2 = str;
                        }
                    }
                    it = it2;
                    str = str2;
                    it2 = it;
                    str2 = str;
                }
                if (str3 != null && str4 != null) {
                    SpManager.getInstance().setAiTypeAndTopic(str3, str4);
                }
                if (allFrameThemes != null && !allFrameThemes.isEmpty()) {
                    for (FrameThemeEntity frameThemeEntity : allFrameThemes) {
                        if (!arrayList4.contains(frameThemeEntity.getThemeKey())) {
                            LoggerUtil.m1181d("picture", "del theme " + frameThemeEntity.getThemeKey() + ", " + frameThemeEntity.getThemeName());
                            DbHelper.getInstance().deleteFrameTheme(frameThemeEntity);
                        }
                    }
                }
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 6);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
                PictureIcDownUtil.updateThemeBtn(iIntListener);
            }
        });
    }

    public static void updateThemeBtn(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getThemeBtnList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                ThreadClock.sleep(100L);
                String str = AppConfig.THEME_BTN_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && !TextUtils.isEmpty(data.topicId) && data.items != null && !data.items.isEmpty()) {
                        AllResDto.Items items = data.items.get(0);
                        String str2 = items.url;
                        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(items.topicId)) {
                            String str3 = items.topicId;
                            String str4 = AppConfig.HTTP_HOST + str2;
                            if (!arrayList4.contains(str3)) {
                                arrayList4.add(str3);
                            }
                            if (!TextUtils.isEmpty(items.hash) && !arrayList3.contains(items.hash.trim().toUpperCase())) {
                                arrayList3.add(items.hash.trim().toUpperCase());
                            }
                            FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(items.hash.trim().toUpperCase());
                            if (fileMd5ByMd5 == null && !arrayList.contains(str4)) {
                                arrayList.add(str4);
                                arrayList2.add(str);
                                PictureIcDownUtil.downFrameCount.incrementAndGet();
                            }
                            FrameThemeEntity frameThemeByKey = DbHelper.getInstance().getFrameThemeByKey(str3);
                            String filePath = fileMd5ByMd5 != null ? fileMd5ByMd5.getFilePath() : null;
                            if (frameThemeByKey != null) {
                                frameThemeByKey.setButtonNetUrl(str4);
                                if (filePath != null) {
                                    frameThemeByKey.setButtonPath(filePath);
                                }
                                DbHelper.getInstance().insertFrameTheme(frameThemeByKey);
                            }
                        }
                    }
                }
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 10);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
            }
        });
    }

    public static void updateNetStickers(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getStickerList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                List<StickerEntity> allStickers = DbHelper.getInstance().getAllStickers();
                String str = AppConfig.STICKER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url)) {
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                if (!arrayList4.contains(str2)) {
                                    arrayList4.add(str2);
                                }
                                String upperCase = items.hash.trim().toUpperCase();
                                if (!arrayList3.contains(upperCase)) {
                                    arrayList3.add(upperCase);
                                }
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(upperCase);
                                if (fileMd5ByMd5 == null && !arrayList.contains(str2)) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    LoggerUtil.m1181d("picture", "add sticker " + str2);
                                }
                                String filePath = fileMd5ByMd5 != null ? fileMd5ByMd5.getFilePath() : null;
                                StickerEntity stickerByNetUrl = DbHelper.getInstance().getStickerByNetUrl(str2);
                                if (stickerByNetUrl != null) {
                                    stickerByNetUrl.setFolderName(items.topicId);
                                    stickerByNetUrl.setLocalPath(filePath);
                                } else {
                                    stickerByNetUrl = new StickerEntity(null, items.topicId, null, str2, filePath, null, true);
                                }
                                DbHelper.getInstance().insertSticker(stickerByNetUrl);
                            }
                        }
                    }
                }
                if (allStickers != null && !allStickers.isEmpty()) {
                    if (arrayList4.isEmpty()) {
                        DbHelper.getInstance().clearStickerSheet();
                    } else {
                        for (StickerEntity stickerEntity : allStickers) {
                            if (!arrayList4.contains(stickerEntity.getNetworkUrl())) {
                                LoggerUtil.m1181d("picture", "del sticker " + stickerEntity.getNetworkUrl());
                                DbHelper.getInstance().deleteSticker(stickerEntity);
                            }
                        }
                    }
                }
                ThreadClock.sleep(200L);
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 3);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
                PictureIcDownUtil.updateStickerCollection(iIntListener);
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
            }
        });
    }

    public static void updateStickerCollection(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getStickerFolderList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                String str = AppConfig.STICKER_BTN + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null && !data.items.isEmpty()) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url) && !TextUtils.isEmpty(items.topicId)) {
                                String upperCase = items.hash.trim().toUpperCase();
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(upperCase);
                                if (fileMd5ByMd5 == null && !arrayList.contains(str2)) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    LoggerUtil.m1181d("picture", "add sticker collection " + str2);
                                }
                                String filePath = str + StringUtil.getFileNameFromPath(items.url);
                                if (fileMd5ByMd5 != null) {
                                    filePath = fileMd5ByMd5.getFilePath();
                                }
                                List<StickerEntity> stickerByFolder = DbHelper.getInstance().getStickerByFolder(items.topicId);
                                if (stickerByFolder != null && !stickerByFolder.isEmpty()) {
                                    for (StickerEntity stickerEntity : stickerByFolder) {
                                        stickerEntity.setFolderIconPath(filePath);
                                        DbHelper.getInstance().insertSticker(stickerEntity);
                                    }
                                }
                            }
                        }
                    }
                }
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
            }
        });
    }

    public static void updateFilter(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getFilterList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                for (FilterEntity filterEntity : DbHelper.getInstance().getAllFilter()) {
                    filterEntity.setIsEnable(false);
                    DbHelper.getInstance().insertFilter(filterEntity);
                }
                ThreadClock.sleep(500L);
                String str = AppConfig.FILTER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null && !data.items.isEmpty()) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url) && !TextUtils.isEmpty(items.hash)) {
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                String upperCase = items.hash.trim().toUpperCase();
                                if (!arrayList3.contains(upperCase)) {
                                    arrayList3.add(upperCase);
                                }
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(upperCase);
                                if (fileMd5ByMd5 == null && !arrayList.contains(str2)) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    LoggerUtil.m1181d("picture", "add filter " + str2);
                                }
                                FilterEntity filterByIndex = DbHelper.getInstance().getFilterByIndex(StringUtil.strToInt(items.rIndex, -1));
                                if (filterByIndex != null) {
                                    String filePath = str + StringUtil.getFileNameFromPath(items.url);
                                    if (fileMd5ByMd5 != null) {
                                        filePath = fileMd5ByMd5.getFilePath();
                                    }
                                    filterByIndex.setFilterImagePath(filePath);
                                    filterByIndex.setFilterName(items.name);
                                    filterByIndex.setIsEnable(true);
                                    DbHelper.getInstance().insertFilter(filterByIndex);
                                }
                            }
                        }
                    }
                }
                ThreadClock.sleep(200L);
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 7);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
            }
        });
        updateMakeup();
        updateEffect();
    }

    private static void updateMakeup() {
        HttpManager.getInstance().mHttpApi.getMakeupList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (MakeupEntity makeupEntity : DbHelper.getInstance().getAllMakeup()) {
                    makeupEntity.setIsEnable(false);
                    DbHelper.getInstance().insertMakeup(makeupEntity);
                }
                ThreadClock.sleep(500L);
                String str = AppConfig.MAKEUP_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null && !data.items.isEmpty()) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url) && !TextUtils.isEmpty(items.hash)) {
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(items.hash.trim().toUpperCase());
                                if (fileMd5ByMd5 == null && !arrayList.contains(str2)) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    LoggerUtil.m1181d("picture", "add makeup " + str2);
                                }
                                String filePath = str + StringUtil.getFileNameFromPath(items.url);
                                if (fileMd5ByMd5 != null) {
                                    filePath = fileMd5ByMd5.getFilePath();
                                }
                                MakeupEntity makeupByIndex = DbHelper.getInstance().getMakeupByIndex(StringUtil.strToInt(items.rIndex, -1));
                                if (makeupByIndex != null) {
                                    makeupByIndex.setMakeupDemoPath(filePath);
                                    makeupByIndex.setMakeupName(items.name);
                                    makeupByIndex.setIsEnable(true);
                                    DbHelper.getInstance().insertMakeup(makeupByIndex);
                                }
                            }
                        }
                    }
                }
                ThreadClock.sleep(200L);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, null);
            }
        });
    }

    private static void updateEffect() {
        HttpManager.getInstance().mHttpApi.getEffectList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (EffectEntity effectEntity : DbHelper.getInstance().getAllEffect()) {
                    effectEntity.setIsEnable(false);
                    DbHelper.getInstance().insertEffect(effectEntity);
                }
                ThreadClock.sleep(500L);
                String str = AppConfig.EFFECT_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null && !data.items.isEmpty()) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url) && !TextUtils.isEmpty(items.hash)) {
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(items.hash.trim().toUpperCase());
                                if (fileMd5ByMd5 == null && !arrayList.contains(str2)) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    LoggerUtil.m1181d("picture", "add effect " + str2);
                                }
                                String filePath = str + StringUtil.getFileNameFromPath(items.url);
                                if (fileMd5ByMd5 != null) {
                                    filePath = fileMd5ByMd5.getFilePath();
                                }
                                EffectEntity effectByIndex = DbHelper.getInstance().getEffectByIndex(StringUtil.strToInt(items.rIndex, -1));
                                if (effectByIndex != null) {
                                    effectByIndex.setEffectDemoUrl(filePath);
                                    effectByIndex.setEffectName(items.name);
                                    effectByIndex.setIsEnable(true);
                                    DbHelper.getInstance().insertEffect(effectByIndex);
                                }
                            }
                        }
                    }
                }
                ThreadClock.sleep(200L);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, null);
            }
        });
    }

    public static void updateBgFrame(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getReplaceBgList().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<AllResDto>>() {
            @Override
            public void onNext(BaseDto<AllResDto> baseDto) {
                if (baseDto == null || baseDto.errCode != 0 || baseDto.data == null || baseDto.data.data == null || baseDto.data.data.isEmpty()) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                String str = AppConfig.BG_FRAME_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                for (AllResDto.Data data : baseDto.data.data) {
                    if (data != null && data.items != null && !data.items.isEmpty()) {
                        for (AllResDto.Items items : data.items) {
                            if (items != null && !TextUtils.isEmpty(items.url) && !TextUtils.isEmpty(items.hash)) {
                                String str2 = AppConfig.HTTP_HOST + items.url;
                                String upperCase = items.hash.trim().toUpperCase();
                                arrayList3.add(upperCase);
                                FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(upperCase);
                                if (fileMd5ByMd5 == null) {
                                    arrayList.add(str2);
                                    arrayList2.add(str);
                                    fileMd5ByMd5 = new FileMd5Entity(null, null, upperCase, 8);
                                } else {
                                    fileMd5ByMd5.setFilePath(fileMd5ByMd5.getFilePath());
                                }
                                DbHelper.getInstance().insertFileMd5(fileMd5ByMd5);
                            }
                        }
                    }
                }
                ThreadClock.sleep(200L);
                PictureIcDownUtil.deleteNotUsePicture(arrayList3, 8);
                PictureIcDownUtil.startPictureDownload(arrayList, arrayList2, iIntListener);
            }
        });
    }

    public static void downUiRes(String str, IIntListener iIntListener) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (UiParamDto uiParamDto : (List) new Gson().fromJson(str, new TypeToken<List<UiParamDto>>() {
        }.getType())) {
            Integer num = DefaultResConfig.MAP_UI_KEY_INDEX.get(uiParamDto.Key);
            if (num != null) {
                UiPositionEntity uiPositionEntity = DbHelper.getInstance().getUiPositionEntity(num.intValue());
                if (uiPositionEntity == null) {
                    uiPositionEntity = new UiPositionEntity(null, num.intValue(), -1, -1, uiParamDto.Url, null, null, true);
                }
                String str2 = AppConfig.UI_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                if (TextUtils.isEmpty(uiParamDto.Hash) || TextUtils.isEmpty(uiParamDto.Url)) {
                    uiPositionEntity.setResNetUrl(null);
                    uiPositionEntity.setResPath(uiPositionEntity.getDefaultResPath());
                } else {
                    FileMd5Entity fileMd5ByMd5 = DbHelper.getInstance().getFileMd5ByMd5(uiParamDto.Hash.toUpperCase());
                    if (fileMd5ByMd5 != null) {
                        uiPositionEntity.setResPath(fileMd5ByMd5.getFilePath());
                    } else {
                        if (!arrayList.contains(AppConfig.HTTP_HOST + uiParamDto.Url)) {
                            arrayList.add(AppConfig.HTTP_HOST + uiParamDto.Url);
                            arrayList2.add(str2);
                            LoggerUtil.m1181d("picture", "add ui " + uiParamDto.Url);
                        }
                        uiPositionEntity.setResNetUrl(AppConfig.HTTP_HOST + uiParamDto.Url);
                    }
                }
                if (uiParamDto.f2445X >= 0 && uiParamDto.f2446Y >= 0) {
                    uiPositionEntity.setX(uiParamDto.f2445X);
                    uiPositionEntity.setY(uiParamDto.f2446Y);
                }
                uiPositionEntity.setIsVisible(uiParamDto.IsEnable);
                DbHelper.getInstance().insertUiPosition(uiPositionEntity);
                if (!TextUtils.isEmpty(uiParamDto.Hash)) {
                    arrayList3.add(uiParamDto.Hash.toUpperCase());
                }
            }
        }
        deleteNotUsePicture(arrayList3, 5);
        startPictureDownload(arrayList, arrayList2, iIntListener);
    }

    public static void deleteNotUsePicture(List<String> list, int i) {
        List<FileMd5Entity> fileMd5ByType = DbHelper.getInstance().getFileMd5ByType(i);
        if (fileMd5ByType == null || fileMd5ByType.isEmpty()) {
            return;
        }
        for (FileMd5Entity fileMd5Entity : fileMd5ByType) {
            if (!TextUtils.isEmpty(fileMd5Entity.getFileMD5()) && !list.contains(fileMd5Entity.getFileMD5().trim().toUpperCase())) {
                LoggerUtil.m1181d("picture", "del file " + fileMd5Entity.getFilePath());
                DbHelper.getInstance().deleteFileMd5(fileMd5Entity);
                FileUtil.deleteFileFolder(fileMd5Entity.getFilePath());
            }
        }
    }

    public static void startPictureDownload(List<String> list, List<String> list2, final IIntListener iIntListener) {
        if (list.size() != list2.size() || list.size() == 0) {
            return;
        }
        int iAddAndGet = App.mDownCount.addAndGet(list.size());
        if (iIntListener != null) {
            iIntListener.setValue(1);
        }
        LoggerUtil.m1181d("download", "startPictureDownload " + iAddAndGet);
        DownloadTask[] downloadTaskArr = new DownloadTask[list.size()];
        for (int i = 0; i < list.size(); i++) {
            downloadTaskArr[i] = new DownloadTask.Builder(list.get(i), list2.get(i), StringUtil.getFileNameFromPath(list.get(i))).build();
        }
        DownloadTask.enqueue(downloadTaskArr, new DownloadListener2() {
            @Override
            public void taskStart(DownloadTask downloadTask) {
            }

            @Override
            public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
                IIntListener iIntListener2;
                IIntListener iIntListener3;
                IIntListener iIntListener4;
                File file = downloadTask.getFile();
                int iDecrementAndGet = App.mDownCount.decrementAndGet();
                if (file == null) {
                    IIntListener iIntListener5 = iIntListener;
                    if (iIntListener5 != null && iDecrementAndGet <= 0) {
                        iIntListener5.setValue(2);
                    }
                    LoggerUtil.m1182e("download", "Down fail url=" + downloadTask.getUrl() + ", cause=" + exc);
                    return;
                }
                if (endCause != EndCause.COMPLETED && !(exc instanceof IOException)) {
                    IIntListener iIntListener6 = iIntListener;
                    if (iIntListener6 == null || iDecrementAndGet > 0) {
                        return;
                    }
                    iIntListener6.setValue(2);
                    return;
                }
                String url = downloadTask.getUrl();
                String absolutePath = file.getAbsolutePath();
                String fileMD5 = FileUtil.getFileMD5(file);
                LoggerUtil.m1181d("download", "taskEnd localPath " + absolutePath + ", cause " + exc);
                if (!TextUtils.isEmpty(fileMD5)) {
                    fileMD5 = fileMD5.trim().toUpperCase();
                }
                int i2 = 3;
                if (absolutePath.contains(AppConfig.THEME_BG_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                    int iDecrementAndGet2 = PictureIcDownUtil.downFrameCount.decrementAndGet();
                    List<FrameThemeEntity> frameThemeBgNetUrl = DbHelper.getInstance().getFrameThemeBgNetUrl(url);
                    if (frameThemeBgNetUrl != null) {
                        for (FrameThemeEntity frameThemeEntity : frameThemeBgNetUrl) {
                            frameThemeEntity.setBackgroundPath(absolutePath);
                            DbHelper.getInstance().insertFrameTheme(frameThemeEntity);
                        }
                    }
                    if (iDecrementAndGet2 <= 0 && (iIntListener4 = iIntListener) != null) {
                        iIntListener4.setValue(3);
                    }
                    i2 = 6;
                } else if (absolutePath.contains(AppConfig.THEME_BTN_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                    int iDecrementAndGet3 = PictureIcDownUtil.downFrameCount.decrementAndGet();
                    List<FrameThemeEntity> frameThemeBtnNetUrl = DbHelper.getInstance().getFrameThemeBtnNetUrl(url);
                    if (frameThemeBtnNetUrl != null) {
                        for (FrameThemeEntity frameThemeEntity2 : frameThemeBtnNetUrl) {
                            frameThemeEntity2.setButtonPath(absolutePath);
                            DbHelper.getInstance().insertFrameTheme(frameThemeEntity2);
                        }
                    }
                    if (iDecrementAndGet3 <= 0 && (iIntListener3 = iIntListener) != null) {
                        iIntListener3.setValue(3);
                    }
                    i2 = 10;
                } else {
                    if (absolutePath.contains(AppConfig.FRAME_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        int iDecrementAndGet4 = PictureIcDownUtil.downFrameCount.decrementAndGet();
                        FrameEntity frameByNetUrl = DbHelper.getInstance().getFrameByNetUrl(url);
                        if (frameByNetUrl != null) {
                            frameByNetUrl.setFrameLocalPath(absolutePath);
                            DbHelper.getInstance().insertFrame(frameByNetUrl);
                        }
                        if (iDecrementAndGet4 <= 0 && (iIntListener2 = iIntListener) != null) {
                            iIntListener2.setValue(3);
                        }
                    } else if (absolutePath.contains(AppConfig.STICKER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        StickerEntity stickerByNetUrl = DbHelper.getInstance().getStickerByNetUrl(url);
                        if (stickerByNetUrl != null) {
                            stickerByNetUrl.setLocalPath(absolutePath);
                            DbHelper.getInstance().insertSticker(stickerByNetUrl);
                        }
                    } else if (absolutePath.contains(AppConfig.STICKER_BTN + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        i2 = 11;
                    } else if (absolutePath.contains(AppConfig.FILTER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        i2 = 7;
                    } else if (absolutePath.contains(AppConfig.MAKEUP_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        i2 = 12;
                    } else if (absolutePath.contains(AppConfig.EFFECT_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        i2 = 13;
                    } else if (absolutePath.contains(AppConfig.UI_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        List<UiPositionEntity> uiPosByNetUrl = DbHelper.getInstance().getUiPosByNetUrl(url);
                        if (uiPosByNetUrl != null) {
                            for (UiPositionEntity uiPositionEntity : uiPosByNetUrl) {
                                uiPositionEntity.setResPath(absolutePath);
                                DbHelper.getInstance().insertUiPosition(uiPositionEntity);
                            }
                        }
                        i2 = 5;
                    } else if (absolutePath.contains(AppConfig.BG_FRAME_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
                        i2 = 8;
                    }
                    i2 = 2;
                }
                DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, absolutePath, fileMD5, i2));
                IIntListener iIntListener7 = iIntListener;
                if (iIntListener7 == null || iDecrementAndGet > 0) {
                    return;
                }
                iIntListener7.setValue(2);
            }
        });
    }
}
