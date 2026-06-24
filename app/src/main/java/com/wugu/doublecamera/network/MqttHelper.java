package com.wugu.doublecamera.network;

import android.content.Context;
import android.text.TextUtils;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.mqtt.MQTTManager;
import com.mqtt.callback.IMQTTCallback;
import com.mqtt.callback.IMqttRecListener;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.utils.ApkUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadHelper;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class MqttHelper {
    private final String TAG;
    private Timer heartTimer;
    private boolean isFirstConnectFail;
    private long lastMqttRecTime;
    private MQTTManager mqttManager;

    private void sendLog(String str) {
    }

    private static class InstanceHolder {
        private static final MqttHelper holder = new MqttHelper();

        private InstanceHolder() {
        }
    }

    public static MqttHelper getInstance() {
        return InstanceHolder.holder;
    }

    private MqttHelper() {
        this.TAG = "Mqtt";
        this.isFirstConnectFail = true;
        this.lastMqttRecTime = 0L;
    }

    public void initMqtt(Context context, String str, String[] strArr, int[] iArr, String str2, String str3, String str4, final IMqttRecListener iMqttRecListener) {
        MQTTManager mQTTManager = this.mqttManager;
        if (mQTTManager != null && mQTTManager.isConnected()) {
            close();
        }
        MQTTManager mQTTManager2 = new MQTTManager(context, new IMQTTCallback() {
            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }

            @Override
            public void connectionLost(Throwable th) {
                LoggerUtil.m1182e("Mqtt", "connectionLost: " + th);
                IMqttRecListener iMqttRecListener2 = iMqttRecListener;
                if (iMqttRecListener2 != null) {
                    iMqttRecListener2.connectionLost(th);
                }
            }

            @Override
            public void messageArrived(String str5, MqttMessage mqttMessage) {
                MqttHelper.this.handleRecMsg(str5, mqttMessage, iMqttRecListener);
            }

            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                MqttHelper.this.isFirstConnectFail = true;
                MqttHelper.this.startHeartTask();
                IMqttRecListener iMqttRecListener2 = iMqttRecListener;
                if (iMqttRecListener2 != null) {
                    iMqttRecListener2.onSuccess();
                }
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable th) {
                LoggerUtil.m1182e("Mqtt", "connect fail: " + iMqttToken + "------------" + th);
                if (MqttHelper.this.isFirstConnectFail) {
                    MqttHelper.this.isFirstConnectFail = false;
                }
                IMqttRecListener iMqttRecListener2 = iMqttRecListener;
                if (iMqttRecListener2 != null) {
                    iMqttRecListener2.onFail();
                }
            }
        }, strArr, iArr);
        this.mqttManager = mQTTManager2;
        mQTTManager2.setMqttOptions(str, str2, str3);
        this.mqttManager.buildClient(str4);
    }

    public void close() {
        MQTTManager mQTTManager = this.mqttManager;
        if (mQTTManager == null) {
            return;
        }
        mQTTManager.disConnect();
    }

    public boolean isConnected() {
        MQTTManager mQTTManager = this.mqttManager;
        if (mQTTManager == null) {
            return false;
        }
        return mQTTManager.isConnected();
    }

    public void mqttPublish(String str, String str2) {
        if (isConnected()) {
            String str3 = str + "|" + str2;
            if (!MqttCmdEnum.C2S_HEART.equals(str)) {
                LoggerUtil.m1181d("Mqtt", "send " + str3);
            }
            this.mqttManager.publish(App.mAppMqttTopicServer, str3);
        }
    }

    public void handleRecMsg(String str, MqttMessage mqttMessage, IMqttRecListener iMqttRecListener) {
        int i;
        String str2 = new String(mqttMessage.getPayload());
        if (TextUtils.isEmpty(str2)) {
        }
        if (str2.equals(MqttCmdEnum.S2C_HEART_REPLY)) {
            if (iMqttRecListener != null) {
                iMqttRecListener.eventCall(str2);
                return;
            }
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastMqttRecTime < 500) {
            LoggerUtil.m1181d("Mqtt", "500ms repeat rec <" + str + ">, payload = " + str2);
            return;
        }
        this.lastMqttRecTime = jCurrentTimeMillis;
        LoggerUtil.m1181d("Mqtt", "rec <" + str + ">, payload = " + str2);
        mqttPublish(str2, "Ack");
        if (str2.startsWith(MqttCmdEnum.S2C_UP_PRINT_START) || str2.startsWith(MqttCmdEnum.S2C_UP_PRINT_ERROR)) {
            if (iMqttRecListener != null) {
                iMqttRecListener.eventCall(str2);
                return;
            }
            return;
        }
        if (str2.startsWith(MqttCmdEnum.S2C_UPLOAD_MEDIA)) {
            String[] strArrSplit = str2.split("\\|");
            if (strArrSplit.length <= 1) {
                return;
            }
            uploadMediaRes(strArrSplit[1]);
            return;
        }
        if (str2.startsWith(MqttCmdEnum.S2C_PRINT_ORDER_PHOTO) || str2.startsWith(MqttCmdEnum.S2C_TEST_INPUT_COIN)) {
            if (iMqttRecListener != null) {
                iMqttRecListener.eventCall(str2);
                return;
            }
            return;
        }
        byte b = 4;
        if (str2.startsWith(MqttCmdEnum.S2C_CHANGE_EASY_BEAUTY_LEVEL)) {
            String[] strArrSplit2 = str2.split("\\|");
            if (strArrSplit2.length > 1 && (i = Integer.parseInt(strArrSplit2[1])) >= 0 && i < 4) {
                SpManager.getInstance().setDefaultBeautyLevel(i);
                if (iMqttRecListener != null) {
                    iMqttRecListener.eventCall(MqttCmdEnum.S2C_CHANGE_EASY_BEAUTY_LEVEL);
                    return;
                }
                return;
            }
            return;
        }
        if (str2.startsWith(MqttCmdEnum.S2C_UPDATE_APK)) {
            String[] strArrSplit3 = str2.split("\\|");
            if (strArrSplit3.length <= 1) {
                return;
            }
            ApkUtil.updateByApkUrl(strArrSplit3[1]);
            return;
        }
        if (str2.startsWith(MqttCmdEnum.S2C_CUSTOM_HTTP)) {
            String[] strArrSplit4 = str2.split("\\|");
            if (strArrSplit4.length <= 1) {
                SpManager.getInstance().setCustomHttpHost(null);
                return;
            } else {
                SpManager.getInstance().setCustomHttpHost(strArrSplit4[1]);
                return;
            }
        }
        if (str2.startsWith(MqttCmdEnum.S2C_CUSTOM_PE_DATA_FORMAT)) {
            String[] strArrSplit5 = str2.split("\\|");
            if (strArrSplit5.length <= 1) {
                SpManager.getInstance().setCustomPeDataFormat(null);
                return;
            } else {
                SpManager.getInstance().setCustomPeDataFormat(strArrSplit5[1]);
                return;
            }
        }
        if (str2.startsWith(MqttCmdEnum.S2C_IGNORE_PRINTER_WARNING)) {
            String[] strArrSplit6 = str2.split("\\|");
            if (strArrSplit6.length <= 1) {
                SpManager.getInstance().setIgnorePrinterWarning(false);
                return;
            } else {
                SpManager.getInstance().setIgnorePrinterWarning(strArrSplit6[1].equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE));
                return;
            }
        }
        if (str2.startsWith(MqttCmdEnum.S2C_SET_LOCK_PWD)) {
            String[] strArrSplit7 = str2.split("\\|");
            if (strArrSplit7.length <= 1) {
                SpManager.getInstance().setLockPwd("980437");
                return;
            } else {
                SpManager.getInstance().setLockPwd(strArrSplit7[1]);
                return;
            }
        }
        if (str2.startsWith(MqttCmdEnum.S2C_VIDEO_ZOOM)) {
            String[] strArrSplit8 = str2.split("\\|");
            if (strArrSplit8.length <= 1) {
                SpManager.getInstance().setVideoZoom(4);
                return;
            }
            int iStrToInt = StringUtil.strToInt(strArrSplit8[1], 4);
            if (iStrToInt == 1 || iStrToInt == 2 || iStrToInt == 4) {
                SpManager.getInstance().setVideoZoom(iStrToInt);
                return;
            }
            return;
        }
        switch (str2.hashCode()) {
            case -1097453462:
                b = !str2.equals(MqttCmdEnum.S2C_LOCK_ON) ? (byte) -1 : (byte) 0;
                break;
            case -571977588:
                b = !str2.equals(MqttCmdEnum.S2C_DNP_DEFAULT_COLOR) ? (byte) -1 : (byte) 1;
                break;
            case -58205130:
                b = !str2.equals(MqttCmdEnum.S2C_CLEAR_PIC_VIDEO) ? (byte) -1 : (byte) 2;
                break;
            case 3087:
                b = !str2.equals(MqttCmdEnum.S2C_APK_UPDATE) ? (byte) -1 : (byte) 3;
                break;
            case 3089:
                if (!str2.equals(MqttCmdEnum.S2C_PAY_SUCCESS)) {
                    b = -1;
                }
                break;
            case 3091:
                b = !str2.equals(MqttCmdEnum.S2C_REBOOT) ? (byte) -1 : (byte) 5;
                break;
            case 3092:
                b = !str2.equals(MqttCmdEnum.S2C_POWER_OFF) ? (byte) -1 : (byte) 6;
                break;
            case 3093:
                b = !str2.equals(MqttCmdEnum.S2C_EXIT_APP) ? (byte) -1 : (byte) 7;
                break;
            case 3094:
                b = !str2.equals(MqttCmdEnum.S2C_LOWER_VOLUME) ? (byte) -1 : (byte) 8;
                break;
            case 3095:
                b = !str2.equals(MqttCmdEnum.S2C_INCREASE_VOLUME) ? (byte) -1 : (byte) 9;
                break;
            case 3118:
                b = !str2.equals(MqttCmdEnum.S2C_STOP_BUSINESS) ? (byte) -1 : (byte) 10;
                break;
            case 3119:
                b = !str2.equals(MqttCmdEnum.S2C_START_BUSINESS) ? (byte) -1 : (byte) 11;
                break;
            case 3120:
                b = !str2.equals(MqttCmdEnum.S2C_TEST_PRINT) ? (byte) -1 : (byte) 12;
                break;
            case 3168:
                b = !str2.equals(MqttCmdEnum.S2C_CLEAR_TEMP) ? (byte) -1 : (byte) 13;
                break;
            case 3618:
                b = !str2.equals(MqttCmdEnum.S2C_CLOSE_APP) ? (byte) -1 : (byte) 14;
                break;
            case 95745:
                b = !str2.equals(MqttCmdEnum.S2C_MUTE) ? (byte) -1 : SnmpConstants.SNMP_ERR_UNDOFAILED;
                break;
            case 95746:
                b = !str2.equals(MqttCmdEnum.S2C_REQUEST_LOG_FILE) ? (byte) -1 : (byte) 16;
                break;
            case 95748:
                b = !str2.equals(MqttCmdEnum.S2C_PARAM_UPDATE) ? (byte) -1 : (byte) 17;
                break;
            case 112083:
                b = !str2.equals(MqttCmdEnum.S2C_CLEAR_UI_POS) ? (byte) -1 : SnmpConstants.SNMP_ERR_INCONSISTENTNAME;
                break;
            case 112084:
                b = !str2.equals(MqttCmdEnum.S2C_SYNC_FRAMES) ? (byte) -1 : SnmpConstants.SNMP_ERR_DECODING_EXC;
                break;
            case 3496293:
                b = !str2.equals(MqttCmdEnum.S2C_RESET_ADMIN_PWD) ? (byte) -1 : SnmpConstants.SNMP_ERR_DECODINGASN_EXC;
                break;
            case 338680900:
                b = !str2.equals(MqttCmdEnum.S2C_LOCK_OFF) ? (byte) -1 : SnmpConstants.SNMP_ERR_DECODINGPKTLNGTH_EXC;
                break;
            case 351608024:
                b = !str2.equals("version") ? (byte) -1 : (byte) 22;
                break;
            case 1454583670:
                b = !str2.equals(MqttCmdEnum.S2C_CLEAR_GLIDE_CACHE) ? (byte) -1 : (byte) 23;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                SpManager.getInstance().setIsLockScreen(true);
                break;
            case 1:
                SpManager.getInstance().setDnpDefaultColorParams(!SpManager.getInstance().getDnpDefaultColorParams());
                break;
            case 2:
                if (App.mIsIdle) {
                    FileUtil.deleteFolderAllFiles(App.getInstance().getFilesDir().getPath());
                    FileUtil.deleteFolderAllFiles(AppConfig.FFMPEG_DIR);
                }
                break;
            case 3:
                ApkUtil.checkAppUpdate();
                break;
            case 4:
            case 10:
            case 11:
            case 12:
            case 17:
            case 19:
                if (iMqttRecListener != null) {
                    iMqttRecListener.eventCall(str2);
                }
                break;
            case 5:
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        AppUtil.reboot();
                    }
                }, 1000L);
                break;
            case 6:
                AppUtil.powerOff();
                break;
            case 7:
            case 14:
                AppUtil.runOnUIDelayed(new Runnable() {
                    @Override
                    public final void run() {
                        MqttHelper.lambda$handleRecMsg$0();
                    }
                }, 1000L);
                break;
            case 8:
                AppUtil.setVolumeLower();
                break;
            case 9:
                AppUtil.setVolumeIncrease();
                break;
            case 13:
                ThreadHelper.getInstance().createThread(new Runnable() {
                    @Override
                    public final void run() {
                        MqttHelper.lambda$handleRecMsg$1();
                    }
                });
                break;
            case 15:
                AppUtil.setVolumeMute();
                break;
            case 16:
                uploadLogFolder();
                break;
            case 18:
                DbHelper.getInstance().clearUiPositionSheet();
                break;
            case 20:
                SpManager.getInstance().setAdminPwd(StringUtil.getSha256("246709", AppConfig.PWD_SALT));
                break;
            case 21:
                SpManager.getInstance().setIsLockScreen(false);
                break;
            case 22:
                mqttPublish("version", "versionCode=130, downCount=" + App.mDownCount.get() + ", isNonCamera=" + App.isNonCamera + ", isNonCannon=" + App.isNonCannon + ", isPrinterReady=" + PrinterHelper.getInstance().isPrinterReady() + ", dnpColor=" + SpManager.getInstance().getDnpDefaultColorParams() + ", isUpdate=" + App.mIsDownApk);
                break;
            case 23:
                AppUtil.runOnUI(new Runnable() {
                    @Override
                    public final void run() {
                        Glide.get(App.getInstance()).clearMemory();
                    }
                });
                ThreadHelper.getInstance().createThread(new Runnable() {
                    @Override
                    public final void run() {
                        Glide.get(App.getInstance()).clearDiskCache();
                    }
                });
                break;
        }
    }

    static void lambda$handleRecMsg$0() {
        AppUtil.unKeepActivity();
        AppUtil.setSystemBar(true);
        System.exit(0);
    }

    static void lambda$handleRecMsg$1() {
        FileUtil.deleteFolderPicture(App.getInstance().getFilesDir());
        FileUtil.deleteFolderAllFiles(AppConfig.FFMPEG_DIR);
    }

    public void startHeartTask() {
        if (this.heartTimer != null || TextUtils.isEmpty(App.mAppMqttTopicServer)) {
            return;
        }
        Timer timer = new Timer();
        this.heartTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MqttHelper.this.mqttPublish(MqttCmdEnum.C2S_HEART, String.valueOf(130));
            }
        }, 5000L, 30000L);
    }

    private void uploadLogFolder() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1175xb23e2a92();
            }
        });
    }

    void m1175xb23e2a92() {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        File file = new File(AppUtil.getInternalSDCardPath() + "/Android/data/com.wugu.doublecamera/files/");
        if (file.exists() && (fileArrListFiles = file.listFiles(new FilenameFilter() {
            @Override
            public final boolean accept(File file2, String str) {
                return str.toLowerCase().endsWith(".txt");
            }
        })) != null) {
            for (File file2 : fileArrListFiles) {
                arrayList.add(file2.getAbsolutePath());
            }
        }
        final File fileZipLogFiles = FileUtil.zipLogFiles(new File(AppConfig.LOGGER_DIR), new File(AppConfig.CRASH_LOG_DIR), arrayList);
        if (fileZipLogFiles == null || !fileZipLogFiles.exists()) {
            return;
        }
        HttpManager.getInstance().mHttpApi.uploadLogs(MultipartBody.Part.createFormData("file", fileZipLogFiles.getName(), RequestBody.create(MediaType.parse("application/zip"), fileZipLogFiles))).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                fileZipLogFiles.delete();
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
            }
        });
    }

    private void uploadMediaRes(String str) {
        String[] strArrSplit = str.split(",");
        ArrayList arrayList = new ArrayList();
        String str2 = App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        String str3 = AppConfig.FFMPEG_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        for (String str4 : strArrSplit) {
            if (str4.endsWith(".jpg")) {
                arrayList.add(str2 + str4);
            } else if (str4.endsWith(".mp4")) {
                arrayList.add(str3 + str4);
            }
        }
        OrderFileUtil.uploadOrderFilePaths(arrayList, null);
    }
}
