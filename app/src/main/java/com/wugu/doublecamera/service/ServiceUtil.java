package com.wugu.doublecamera.service;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.DefaultResConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.DeviceAllConfigDto;
import com.wugu.doublecamera.bean.dto.DeviceInfoDto;
import com.wugu.doublecamera.bean.dto.SettingInfoDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.BeautyEntity;
import com.wugu.doublecamera.database.dbSheet.EffectEntity;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.database.dbSheet.FilterEntity;
import com.wugu.doublecamera.database.dbSheet.MakeupEntity;
import com.wugu.doublecamera.device.CashHelper;
import com.wugu.doublecamera.device.CoinHelper;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ServiceUtil {
    public static void initDatabase() {
        initFolder();
        initFileDb();
        initEffectDb();
        initBeautyParamsDb();
        initFilterDb();
        initMakeupDb();
    }

    public static void uploadApkVersion() {
        int versionCode = AppUtil.getVersionCode();
        LoggerUtil.m1181d("main", "apkVersion:" + versionCode);
        HttpManager.getInstance().mHttpApi.uploadApkVersion(versionCode).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
        });
    }

    public static void checkDeleteFile(Context context) {
        FileUtil.deleteFolderAllFiles(AppConfig.FFMPEG_DIR);
        FileUtil.deleteFolderAllFiles(AppConfig.TEMP_DIR);
        deleteOldPic(context);
        File[] fileArrListFiles = new File(AppConfig.APK_DIR).listFiles();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (!file.getName().toLowerCase().endsWith("v130.apk".toLowerCase())) {
                    file.delete();
                }
            }
        }
    }

    private static void deleteOldPic(Context context) {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(6, -7);
            Date time = calendar.getTime();
            File[] fileArrListFiles = filesDir.listFiles();
            if (fileArrListFiles != null) {
                for (File file : fileArrListFiles) {
                    if (file.getName().toLowerCase().endsWith(".jpg") && file.lastModified() < time.getTime()) {
                        if (file.delete()) {
                            LoggerUtil.m1181d("file", "Deleted file: " + file.getName());
                        } else {
                            LoggerUtil.m1181d("file", "Failed Deleted file: " + file.getName());
                        }
                    }
                }
            }
        }
    }

    private static void initFolder() {
        FileUtil.createFolder(AppConfig.FRAME_DIR);
        FileUtil.createFolder(AppConfig.STICKER_DIR);
        FileUtil.createFolder(AppConfig.STICKER_BTN);
        FileUtil.createFolder(AppConfig.MUSIC_DIR);
        FileUtil.createFolder(AppConfig.THEME_BG_DIR);
        FileUtil.createFolder(AppConfig.THEME_BTN_DIR);
        FileUtil.createFolder(AppConfig.APK_DIR);
        FileUtil.createFolder(AppConfig.UI_DIR);
        FileUtil.createFolder(AppConfig.FILTER_DIR);
        FileUtil.createFolder(AppConfig.MAKEUP_DIR);
        FileUtil.createFolder(AppConfig.EFFECT_DIR);
        FileUtil.createFolder(AppConfig.BG_FRAME_DIR);
        FileUtil.createFolder(AppConfig.PRINTER_PARAM_DIR);
        FileUtil.createFolder(AppConfig.TEMP_DIR);
        FileUtil.createFolder(AppConfig.FFMPEG_DIR);
    }

    private static void initFileDb() {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        File[] fileArrListFiles3;
        File[] fileArrListFiles4;
        File[] fileArrListFiles5;
        File[] fileArrListFiles6;
        if (DbHelper.getInstance().isFrameEmpty()) {
            File file = new File(AppConfig.FRAME_DIR);
            if (file.exists()) {
                File[] fileArrListFiles7 = file.listFiles(new FileFilter() {
                    @Override
                    public final boolean accept(File file2) {
                        return file2.isFile();
                    }
                });
                if (fileArrListFiles7 == null) {
                    return;
                }
                for (File file2 : fileArrListFiles7) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file2.getAbsolutePath(), FileUtil.getFileMD5(file2), 2));
                }
            }
            File file3 = new File(AppConfig.STICKER_DIR);
            if (file3.exists() && (fileArrListFiles6 = file3.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) != null) {
                for (File file4 : fileArrListFiles6) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file4.getAbsolutePath(), FileUtil.getFileMD5(file4), 3));
                }
            }
            File file5 = new File(AppConfig.THEME_BG_DIR);
            if (file5.exists() && (fileArrListFiles5 = file5.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) != null) {
                for (File file6 : fileArrListFiles5) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file6.getAbsolutePath(), FileUtil.getFileMD5(file6), 6));
                }
            }
            File file7 = new File(AppConfig.THEME_BTN_DIR);
            if (file7.exists() && (fileArrListFiles4 = file7.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) != null) {
                for (File file8 : fileArrListFiles4) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file8.getAbsolutePath(), FileUtil.getFileMD5(file8), 10));
                }
            }
            File file9 = new File(AppConfig.MUSIC_DIR);
            if (file9.exists() && (fileArrListFiles3 = file9.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) != null) {
                for (File file10 : fileArrListFiles3) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file10.getAbsolutePath(), FileUtil.getFileMD5(file10), 1));
                }
            }
            File file11 = new File(AppConfig.FILTER_DIR);
            if (file11.exists() && (fileArrListFiles2 = file11.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) != null) {
                for (File file12 : fileArrListFiles2) {
                    DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file12.getAbsolutePath(), FileUtil.getFileMD5(file12), 7));
                }
            }
            File file13 = new File(AppConfig.BG_FRAME_DIR);
            if (!file13.exists() || (fileArrListFiles = file13.listFiles(new FileFilter() {
                @Override
                public final boolean accept(File file22) {
                    return file22.isFile();
                }
            })) == null) {
                return;
            }
            for (File file14 : fileArrListFiles) {
                DbHelper.getInstance().insertFileMd5(new FileMd5Entity(null, file14.getAbsolutePath(), FileUtil.getFileMD5(file14), 8));
            }
        }
    }

    private static void initEffectDb() {
        if (DbHelper.getInstance().isEffectEmpty()) {
            App app = App.getInstance();
            DbHelper.getInstance().insertEffect(new EffectEntity(null, 3, 0, app.getString(C1910R.string.none), null, AppConfig.BASE_FOLDER + "makeup/无.png", null, true));
            String[][] defaultEffectList = DefaultResConfig.getDefaultEffectList(app);
            int length = defaultEffectList.length;
            char c = 1;
            int i = 0;
            int i2 = 1;
            while (i < length) {
                String[] strArr = defaultEffectList[i];
                DbHelper.getInstance().insertEffect(new EffectEntity(null, 3, i2, strArr[0], strArr[c] + ".bundle", strArr[c] + ".png", null, true));
                i2++;
                i++;
                c = 1;
            }
            for (String[] strArr2 : DefaultResConfig.getDefaultMaskList(app)) {
                DbHelper.getInstance().insertEffect(new EffectEntity(null, 4, i2, strArr2[0], strArr2[1] + ".bundle", strArr2[1] + ".png", null, true));
                i2++;
            }
            for (String[] strArr3 : DefaultResConfig.getDefaultHahaMirrorList(app)) {
                DbHelper.getInstance().insertEffect(new EffectEntity(null, 5, i2, strArr3[0], strArr3[1] + ".bundle", strArr3[1] + ".png", null, true));
                i2++;
            }
        }
    }

    private static void initBeautyParamsDb() {
        if (DbHelper.getInstance().isBeautyEmpty()) {
            int[][] defaultBeautyParams = DefaultResConfig.getDefaultBeautyParams();
            int length = defaultBeautyParams.length;
            char c = 0;
            int i = 0;
            while (i < length) {
                int[] iArr = defaultBeautyParams[i];
                DbHelper.getInstance().insertBeauty(new BeautyEntity(null, iArr[c], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5], iArr[6], iArr[7], iArr[8], iArr[9], iArr[10], iArr[11], iArr[12], iArr[13], iArr[14], iArr[15], iArr[16], iArr[17], iArr[18], iArr[19], iArr[20], iArr[21], iArr[22], iArr[23], iArr[24], iArr[25], iArr[26], iArr[27], iArr[28], iArr[29], iArr[30], iArr[31], iArr[32], iArr[33], iArr[34], iArr[35], iArr[36]));
                i++;
                c = 0;
            }
        }
    }

    private static void initFilterDb() {
        if (DbHelper.getInstance().isFilterEmpty()) {
            Iterator<FilterEntity> it = DefaultResConfig.getDefaultFilterList().iterator();
            while (it.hasNext()) {
                DbHelper.getInstance().insertFilter(it.next());
            }
        }
    }

    private static void initMakeupDb() {
        if (DbHelper.getInstance().isMakeupEmpty()) {
            Iterator<MakeupEntity> it = DefaultResConfig.getDefaultMakeupList().iterator();
            while (it.hasNext()) {
                DbHelper.getInstance().insertMakeup(it.next());
            }
        }
    }

    public static void syncServerRes(IIntListener iIntListener) {
        if (App.mIsNetAvailable) {
            PictureIcDownUtil.updateNetFrames(iIntListener);
            ThreadClock.sleep(1000L);
            PictureIcDownUtil.updateFilter(iIntListener);
            ThreadClock.sleep(1000L);
            PictureIcDownUtil.updateNetStickers(iIntListener);
            ThreadClock.sleep(1000L);
            PictureIcDownUtil.updateBgFrame(iIntListener);
            ThreadClock.sleep(5000L);
            if (App.mDownCount.get() > 0 || iIntListener == null) {
                return;
            }
            iIntListener.setValue(2);
        }
    }

    public static void syncDeviceSetting(final IIntListener iIntListener) {
        HttpManager.getInstance().mHttpApi.getSettingInfo().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<SettingInfoDto>>() {
            @Override
            public void onNext(BaseDto<SettingInfoDto> baseDto) {
                if (baseDto.errCode != 0 || baseDto.data == null) {
                    return;
                }
                SpManager.getInstance().setRemainPrintCount(baseDto.data.buyPrinterPaper);
                SpManager.getInstance().setRemainPrinterSheet(baseDto.data.printerPaper);
                SpManager.getInstance().setMachineNum(baseDto.data.deviceWapper);
                SpManager.getInstance().setMachinePhone(baseDto.data.contactPhone);
                SpManager.getInstance().setMachineName(baseDto.data.deviceName);
                SpManager.getInstance().setWechatNum(baseDto.data.csContact);
                App.mUploadKey = baseDto.data.uploadKey;
                App.mAppId = baseDto.data.f2444id;
                SpManager.getInstance().setAppId(App.mAppId);
                SpManager.getInstance().setAppUploadKey(App.mUploadKey);
                iIntListener.setValue(1);
            }
        });
        syncDeviceConfig(iIntListener);
    }

    public static void syncDeviceConfig(final IIntListener iIntListener) {
        if (App.mIsNetAvailable) {
            HttpManager.getInstance().mHttpApi.getDeviceAllConfig().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<DeviceAllConfigDto>>() {
                @Override
                public void onNext(BaseDto<DeviceAllConfigDto> baseDto) {
                    if (baseDto.errCode != 0 || baseDto.data == null || TextUtils.isEmpty(baseDto.data.cameraInfo)) {
                        return;
                    }
                    DeviceInfoDto deviceInfoDto = (DeviceInfoDto) new Gson().fromJson(baseDto.data.cameraInfo, DeviceInfoDto.class);
                    if (deviceInfoDto.BeautyParam != null) {
                        for (DeviceInfoDto.BeautyParams beautyParams : deviceInfoDto.BeautyParam) {
                            BeautyEntity beautyByLevel = DbHelper.getInstance().getBeautyByLevel(beautyParams.Level);
                            if (beautyByLevel != null) {
                                beautyByLevel.setBlur(beautyParams.Blur);
                                beautyByLevel.setWhite(beautyParams.White);
                                beautyByLevel.setEyeBright(beautyParams.eyeBright);
                                beautyByLevel.setTooth(beautyParams.tooth);
                                beautyByLevel.setFaceThin(beautyParams.faceThin);
                                beautyByLevel.setEyeBig(beautyParams.eyeBig);
                                beautyByLevel.setEyeCircle(beautyParams.eyeCircle);
                                DbHelper.getInstance().insertBeauty(beautyByLevel);
                            }
                        }
                    }
                    if (deviceInfoDto.CameraSetting.AppModel == 0) {
                        SpManager.getInstance().setSystemMode(1);
                        App.mSystemMode = 1;
                    } else if (deviceInfoDto.CameraSetting.AppModel == 1) {
                        SpManager.getInstance().setSystemMode(2);
                        App.mSystemMode = 2;
                    } else if (deviceInfoDto.CameraSetting.AppModel == 2) {
                        SpManager.getInstance().setSystemMode(3);
                        App.mSystemMode = 3;
                    } else if (deviceInfoDto.CameraSetting.AppModel == 3) {
                        SpManager.getInstance().setSystemMode(4);
                        App.mSystemMode = 4;
                    }
                    SpManager.getInstance().setRetakeCount(deviceInfoDto.CameraSetting.RetakeCount);
                    SpManager.getInstance().setCameraMirror(deviceInfoDto.CameraSetting.IsMirror);
                    if (deviceInfoDto.CameraSetting.CameraExposure > 0) {
                        SpManager.getInstance().setCameraExposure(deviceInfoDto.CameraSetting.CameraExposure);
                    }
                    if (deviceInfoDto.CameraSetting.CameraZoom >= 10) {
                        SpManager.getInstance().setCameraZoom(deviceInfoDto.CameraSetting.CameraZoom);
                    }
                    SpManager.getInstance().setUploadPrintPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.UploadPrintPrice));
                    SpManager.getInstance().setUploadPrintAddPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.UploadPrintAddPrice));
                    SpManager.getInstance().setReplaceBgPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.ReplaceBgPrice));
                    SpManager.getInstance().setReplaceBgAddPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.ReplaceBgAddPrice));
                    SpManager.getInstance().setAiPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.AiPrice));
                    SpManager.getInstance().setAiAddPrice(MathUtil.getPriceValue(deviceInfoDto.CameraSetting.AiAddPrice));
                    SpManager.getInstance().setPrintMaxCount(deviceInfoDto.CameraSetting.PrintMaxCount);
                    SpManager.getInstance().setPrinterModel(ServiceUtil.getPrinterModelValue(deviceInfoDto.CameraSetting.PrinterModel));
                    float fStringToFloat = MathUtil.stringToFloat(deviceInfoDto.CameraSetting.SCoinBaseValue, 1.0f);
                    SpManager.getInstance().setCoinBaseValue(fStringToFloat);
                    CoinHelper.getInstance().updateBaseValue(fStringToFloat);
                    if (deviceInfoDto.CameraSetting.CashBaseValue != null && deviceInfoDto.CameraSetting.CashBaseValue.length > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (String str : deviceInfoDto.CameraSetting.CashBaseValue) {
                            sb.append(str).append("-");
                        }
                        LoggerUtil.m1183i("settings", "cashStr " + ((Object) sb));
                        SpManager.getInstance().setCashBaseValue(sb.toString());
                        CashHelper.getInstance().updateBaseValue(sb.toString());
                    }
                    SpManager.getInstance().setPhotoConfirmTime(deviceInfoDto.CountdownSetting.PhotoConfirmTime);
                    SpManager.getInstance().setAdminTime(deviceInfoDto.CountdownSetting.AdminTime);
                    SpManager.getInstance().setPaymentTime(deviceInfoDto.CountdownSetting.PaymentTime);
                    SpManager.getInstance().setBurstSelectTime(deviceInfoDto.CountdownSetting.BurstSelectTime);
                    SpManager.getInstance().setChooseFrameTime(deviceInfoDto.CountdownSetting.ChooseFrameTime);
                    SpManager.getInstance().setUploadPrintTime(deviceInfoDto.CountdownSetting.UploadPrintTime);
                    SpManager.getInstance().setPhotoPreviewTime(deviceInfoDto.CountdownSetting.PhotoPreviewTime);
                    SpManager.getInstance().setPhotoReadyTime(deviceInfoDto.CountdownSetting.PhotoReadyTime);
                    SpManager.getInstance().setReplaceBgTime(deviceInfoDto.CountdownSetting.ReplaceBgTime);
                    SpManager.getInstance().setPrintStartTime(deviceInfoDto.CountdownSetting.PrintStartTime);
                    SpManager.getInstance().setModelSelectTime(deviceInfoDto.CountdownSetting.ModelselectTime);
                    SoundDownUtil.updateSounds(deviceInfoDto.SoundSetting, iIntListener);
                    ServiceUtil.setPaymentMethod(deviceInfoDto.PaymentMethod);
                    PictureIcDownUtil.downUiRes(baseDto.data.reserve1, iIntListener);
                }
            });
        }
    }

    public static int getPrinterModelValue(String str) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        if (str.trim().equalsIgnoreCase("CITIZEN CY")) {
            return 4;
        }
        if (str.trim().equalsIgnoreCase("BRO 2340")) {
            return 3;
        }
        if (str.trim().equalsIgnoreCase("EPSON L8058")) {
            return 5;
        }
        if (str.trim().equalsIgnoreCase("HP 701N")) {
            return 6;
        }
        return str.trim().equalsIgnoreCase("HITI") ? 7 : 1;
    }

    public static void setPaymentMethod(DeviceInfoDto.PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return;
        }
        TreeMap treeMap = new TreeMap();
        if (paymentMethod.Wechat > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Wechat), 1);
        }
        if (paymentMethod.Alipay > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Alipay), 2);
        }
        if (paymentMethod.Discountcode > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Discountcode), 3);
        }
        if (paymentMethod.Cash > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Cash), 4);
        }
        if (paymentMethod.Coin > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Coin), 5);
        }
        if (paymentMethod.GameCoin > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.GameCoin), 6);
        }
        if (paymentMethod.Credit > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.Credit), 7);
        }
        if (paymentMethod.MeiTuan > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.MeiTuan), 8);
        }
        if (paymentMethod.DouYin > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.DouYin), 9);
        }
        if (paymentMethod.XieCheng > 0) {
            treeMap.put(Integer.valueOf(paymentMethod.XieCheng), 12);
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : treeMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append(entry.getValue());
        }
        LoggerUtil.m1183i("settings", "payment methods " + ((Object) sb));
        SpManager.getInstance().setPaymentMethod(sb.toString());
    }
}
