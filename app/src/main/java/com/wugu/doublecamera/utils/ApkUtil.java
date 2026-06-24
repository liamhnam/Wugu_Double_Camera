package com.wugu.doublecamera.utils;

import android.text.TextUtils;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.ApkPatchDto;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.listener.IDownFileListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ApkUtil {
    public static void checkAppUpdate() {
        HttpManager.getInstance().mHttpApi.getNewestBsPatchList(130).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<List<ApkPatchDto>>>() {
            @Override
            public void onNext(BaseDto<List<ApkPatchDto>> baseDto) {
                LoggerUtil.m1181d("ApkUtil", "checkAppUpdate ");
                if (baseDto != null && baseDto.data != null) {
                    ApkUtil.getNewestApk(baseDto.data);
                } else {
                    LoggerUtil.m1182e("ApkUtil", "checkAppUpdate error null data");
                    MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_HEART, "Update error ");
                }
            }
        });
    }

    public static void updateByApkUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        downloadApk(str);
    }

    public static void getNewestApk(List<ApkPatchDto> list) {
        int i;
        int i2;
        String str;
        String str2;
        String str3;
        if (list == null || list.isEmpty()) {
            return;
        }
        ApkPatchDto apkPatchDto = list.get(0);
        String name = null;
        if (apkPatchDto.downloadURL.endsWith(".apk")) {
            int i3 = apkPatchDto.versionCode;
            i2 = 0;
            str3 = apkPatchDto.downloadURL;
            i = i3;
            str2 = apkPatchDto.fileHash;
            str = null;
        } else {
            if (apkPatchDto.downloadURL.endsWith(".patch")) {
                int i4 = apkPatchDto.versionCode;
                str = apkPatchDto.downloadURL;
                i = 0;
                i2 = i4;
                str2 = null;
            } else {
                i = 0;
                i2 = 0;
                str = null;
                str2 = null;
            }
            str3 = str2;
        }
        if (list.size() > 1) {
            ApkPatchDto apkPatchDto2 = list.get(1);
            if (apkPatchDto2.downloadURL.endsWith(".apk")) {
                i = apkPatchDto2.versionCode;
                str3 = apkPatchDto2.downloadURL;
                str2 = apkPatchDto2.fileHash;
            } else if (apkPatchDto2.downloadURL.endsWith(".patch")) {
                i2 = apkPatchDto2.versionCode;
                str = apkPatchDto2.downloadURL;
            }
        }
        if (i <= 130) {
            LoggerUtil.m1182e("update", "当前版本已是最新版本 " + i + " 130");
            return;
        }
        File[] fileArrListFiles = new File(AppConfig.APK_DIR).listFiles(new FilenameFilter() {
            @Override
            public final boolean accept(File file, String str4) {
                return str4.endsWith("v130.apk");
            }
        });
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            name = fileArrListFiles[0].getName();
        }
        if (i2 >= i && !TextUtils.isEmpty(name)) {
            MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_HEART, "Update patch 130 -> " + i2);
            LoggerUtil.m1181d("ApkUtil", "down patch 130 -> " + i2);
            downloadPatch(str, str2, name, i2, str3);
        } else {
            MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_HEART, "Update apk 130 -> " + i);
            LoggerUtil.m1181d("ApkUtil", "down apk 130 -> " + str3);
            downloadApk(str3);
        }
    }

    private static void downloadPatch(String str, final String str2, final String str3, final int i, final String str4) {
        final String fileNameFromPath = StringUtil.getFileNameFromPath(str);
        if (new File(AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath).exists()) {
            return;
        }
        HttpManager.getInstance().downLoadFile(AppConfig.HTTP_HOST + str, AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath, new IDownFileListener() {
            @Override
            public void progress(int i2) {
            }

            @Override
            public void success() {
                String str5 = AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + str3;
                String str6 = AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath;
                String str7 = AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + str3.replace(String.valueOf(130), String.valueOf(i));
                LoggerUtil.m1181d("ApkUtil", "down patch ok, patch oldApkPath " + str5 + " patchPath " + str6 + " newApkPath " + str7);
                BsPatchUtil.bsPatch(str5, str6, str7);
                ThreadClock.sleep(500L);
                File file = new File(str7);
                if (file.exists()) {
                    String fileMD5 = FileUtil.getFileMD5(file);
                    if (!TextUtils.isEmpty(fileMD5) && fileMD5.equalsIgnoreCase(str2.trim())) {
                        ApkUtil.startInstallApk(str7);
                        return;
                    }
                    LoggerUtil.m1182e("ApkUtil", "合成patch后，md5不匹配，下载完整apk包安装");
                    FileUtil.deleteFileFolder(str7);
                    ThreadClock.sleep(500L);
                    ApkUtil.downloadApk(str4);
                }
            }

            @Override
            public void fail(int i2) {
                LoggerUtil.m1182e("Http ", "下载失败, s " + i2);
            }
        });
    }

    public static void downloadApk(String str) {
        final String fileNameFromPath = StringUtil.getFileNameFromPath(str);
        if (new File(AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath).exists()) {
            return;
        }
        HttpManager.getInstance().downLoadFile(AppConfig.HTTP_HOST + str, AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath, new IDownFileListener() {
            @Override
            public void progress(int i) {
            }

            @Override
            public void success() {
                LoggerUtil.m1181d("ApkUtil", "down update apk ok, App.mIsIdle " + App.mIsIdle);
                ApkUtil.startInstallApk(AppConfig.APK_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileNameFromPath);
            }

            @Override
            public void fail(int i) {
                LoggerUtil.m1182e("Http ", "下载失败, s " + i);
            }
        });
    }

    public static void startInstallApk(final String str) {
        AppUtil.unKeepActivity();
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                ApkUtil.lambda$startInstallApk$1(str);
            }
        }, 1000L);
    }

    static void lambda$startInstallApk$1(final String str) {
        LoggerUtil.m1181d("ApkUtil", "startInstallApk, + " + str + ", App.mIsIdle " + App.mIsIdle);
        if (App.mIsIdle) {
            AppUtil.installApp(str, App.getInstance().getPackageName());
        } else {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    LoggerUtil.m1181d("ApkUtil", "down update apk ok, App.mIsIdle " + App.mIsIdle);
                    if (App.mIsIdle) {
                        cancel();
                        AppUtil.installApp(str, App.getInstance().getPackageName());
                    }
                }
            }, 30000L, 30000L);
        }
    }
}
