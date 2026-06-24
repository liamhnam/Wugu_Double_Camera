package com.wugu.doublecamera.service;

import android.text.TextUtils;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener2;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.bean.dto.DeviceInfoDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.database.dbSheet.SoundSettingEntity;
import com.wugu.doublecamera.enums.SoundEnum;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SoundDownUtil {
    public static void updateSounds(DeviceInfoDto.SoundSettings soundSettings, IIntListener iIntListener) {
        SpManager.getInstance().setBgmPaths("");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        DbHelper.getInstance().delSoundSettingByType(SpManager.SOUND_BGM);
        String str = AppConfig.MUSIC_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        for (String str2 : soundSettings.HomeBgmUrl) {
            String str3 = str + StringUtil.getFileNameFromPath(str2);
            File file = new File(str3);
            if (file.exists()) {
                DbHelper.getInstance().insertSoundSetting(new SoundSettingEntity(null, AppConfig.HTTP_HOST + str2, str3, SpManager.SOUND_BGM));
                String bgmPaths = SpManager.getInstance().getBgmPaths();
                if (!TextUtils.isEmpty(bgmPaths)) {
                    bgmPaths = bgmPaths + ",";
                }
                SpManager.getInstance().setBgmPaths(bgmPaths + str3);
                arrayList3.add(file.getName());
            } else {
                if (iIntListener != null) {
                    iIntListener.setValue(2);
                }
                soundTaskAdd(arrayList, arrayList2, str2, str, SpManager.SOUND_BGM);
            }
        }
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundSelectThemeUrl, SoundEnum.getSoundTypeName(1));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPhotoUrl, SoundEnum.getSoundTypeName(5));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundSelectFrameUrl, SoundEnum.getSoundTypeName(2));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundClickButtonUrl, SoundEnum.getSoundTypeName(3));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundSelectFilterUrl, SoundEnum.getSoundTypeName(6));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPhotoCountdownUrl, SoundEnum.getSoundTypeName(4));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPayment, SoundEnum.getSoundTypeName(8));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundChooseFrame, SoundEnum.getSoundTypeName(7));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPrintFinish, SoundEnum.getSoundTypeName(9));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundLookCamera, SoundEnum.getSoundTypeName(10));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPrePrint, SoundEnum.getSoundTypeName(11));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundStartPrint, SoundEnum.getSoundTypeName(12));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPreview, SoundEnum.getSoundTypeName(13));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPeRetake, SoundEnum.getSoundTypeName(16));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPayPrint, SoundEnum.getSoundTypeName(17));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPeUseCode, SoundEnum.getSoundTypeName(15));
        checkAddTask(arrayList, arrayList2, arrayList3, soundSettings.SoundPeChangeFrame, SoundEnum.getSoundTypeName(14));
        delNotUseSoundFile(arrayList3);
        if (arrayList.size() != 0) {
            startSoundsDownload(arrayList, arrayList2, iIntListener);
        } else if (iIntListener != null) {
            iIntListener.setValue(3);
        }
    }

    private static void checkAddTask(List<String> list, List<String> list2, List<String> list3, String str, String str2) {
        SoundSettingEntity soundSettingEntity;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str3 = AppConfig.MUSIC_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        String fileNameFromPath = StringUtil.getFileNameFromPath(str);
        File file = new File(str3 + fileNameFromPath);
        if (file.exists()) {
            List<SoundSettingEntity> soundSettingByType = DbHelper.getInstance().getSoundSettingByType(str2);
            if (soundSettingByType == null || soundSettingByType.isEmpty()) {
                soundSettingEntity = new SoundSettingEntity(null, str, str3 + fileNameFromPath, str2);
            } else {
                soundSettingEntity = soundSettingByType.get(0);
                soundSettingEntity.setNetUrl(AppConfig.HTTP_HOST + str);
                soundSettingEntity.setLocalPath(str3 + fileNameFromPath);
            }
            list3.add(file.getName());
            DbHelper.getInstance().insertSoundSetting(soundSettingEntity);
            return;
        }
        DbHelper.getInstance().delSoundSettingByType(str2);
        soundTaskAdd(list, list2, str, str3, str2);
    }

    private static void delNotUseSoundFile(List<String> list) {
        File[] fileArrListFiles;
        File file = new File(AppConfig.MUSIC_DIR);
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile() && !list.contains(file2.getName())) {
                    file2.delete();
                }
            }
        }
    }

    private static void soundTaskAdd(List<String> list, List<String> list2, String str, String str2, String str3) {
        if (list.contains(str)) {
            return;
        }
        list.add(AppConfig.HTTP_HOST + str);
        list2.add(str2);
        DbHelper.getInstance().insertSoundSetting(new SoundSettingEntity(null, AppConfig.HTTP_HOST + str, null, str3));
    }

    private static void startSoundsDownload(List<String> list, List<String> list2, final IIntListener iIntListener) {
        if (list.size() != list2.size()) {
            return;
        }
        int size = list.size();
        App.mSoundDownCount.set(size);
        LoggerUtil.m1181d("download", "startSoundsDownload " + size);
        DownloadTask[] downloadTaskArr = new DownloadTask[size];
        for (int i = 0; i < size; i++) {
            downloadTaskArr[i] = new DownloadTask.Builder(list.get(i), list2.get(i), StringUtil.getFileNameFromPath(list.get(i))).build();
        }
        DownloadTask.enqueue(downloadTaskArr, new DownloadListener2() {
            @Override
            public void taskStart(DownloadTask downloadTask) {
            }

            @Override
            public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
                File file = downloadTask.getFile();
                int iDecrementAndGet = App.mSoundDownCount.decrementAndGet();
                if (file == null) {
                    return;
                }
                if (endCause == EndCause.COMPLETED || (exc instanceof IOException)) {
                    String url = downloadTask.getUrl();
                    String absolutePath = file.getAbsolutePath();
                    LoggerUtil.m1181d("download", "taskEnd localPath " + absolutePath);
                    SoundSettingEntity soundSettingByUrl = DbHelper.getInstance().getSoundSettingByUrl(url);
                    soundSettingByUrl.setLocalPath(absolutePath);
                    DbHelper.getInstance().insertSoundSetting(soundSettingByUrl);
                    String fileMD5 = FileUtil.getFileMD5(file);
                    if (!TextUtils.isEmpty(fileMD5)) {
                        fileMD5 = fileMD5.trim().toUpperCase();
                    }
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, absolutePath, fileMD5, 1));
                    if (soundSettingByUrl.getType().equals(SpManager.SOUND_BGM)) {
                        String bgmPaths = SpManager.getInstance().getBgmPaths();
                        if (!TextUtils.isEmpty(bgmPaths)) {
                            bgmPaths = bgmPaths + ",";
                        }
                        SpManager.getInstance().setBgmPaths(bgmPaths + absolutePath);
                    }
                    IIntListener iIntListener2 = iIntListener;
                    if (iIntListener2 == null || iDecrementAndGet > 0) {
                        return;
                    }
                    iIntListener2.setValue(3);
                }
            }
        });
    }
}
