package com.wugu.doublecamera.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.mqtt.callback.IMqttRecListener;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.BuildConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.MqttInfoDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.CardCashHelper;
import com.wugu.doublecamera.device.CashHelper;
import com.wugu.doublecamera.device.CoinHelper;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.device.ScannerHelper;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.listener.IDownFileListener;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.utils.AesUtil;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.NetworkMonitor;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;

public class MainService extends Service {
    private static final String TAG = "MainService";
    private boolean isCheckingDown;
    private IServiceListener mServiceListener;
    private CountDownTimer syncResCountDownTimer;
    private CountDownTimer syncSettingCountDownTimer;
    private CountDownTimer timerServerConnected;
    private final LocalBinder mBinder = new LocalBinder();
    private int lastRecMoney = 0;
    private int initStep = 0;
    private boolean isSendStartup = false;

    public interface IServiceListener {
        void updateEvent(int i, String str);
    }

    private void test() {
    }

    @Override
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public class LocalBinder extends Binder {
        public MainService service;

        public LocalBinder() {
            this.service = MainService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String cpuSerial = SpManager.getInstance().getCpuSerial();
        if (TextUtils.isEmpty(cpuSerial)) {
            App.mCPUSerial = AppUtil.getCPUSerial();
            if (TextUtils.isEmpty(App.mCPUSerial)) {
                App.mCPUSerial = StringUtil.getRandomNumber(16);
            }
            SpManager.getInstance().setCpuSerial(App.mCPUSerial);
        } else {
            App.mCPUSerial = cpuSerial;
        }
        App.mUploadKey = SpManager.getInstance().getAppUploadKey();
        App.mAppId = SpManager.getInstance().getAppId();
        LoggerUtil.m1181d(TAG, "mCpu " + App.mCPUSerial);
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1720lambda$onCreate$1$comwugudoublecameraserviceMainService();
            }
        });
        if (TextUtils.isEmpty(SpManager.getInstance().getAdminPwd())) {
            SpManager.getInstance().setAdminPwd(StringUtil.getSha256("246709", AppConfig.PWD_SALT));
        }
        startCheckThread();
    }

    void m1720lambda$onCreate$1$comwugudoublecameraserviceMainService() {
        ServiceUtil.initDatabase();
        ServiceUtil.checkDeleteFile(this);
        ThreadClock.sleep(1000L);
        new NetworkMonitor(this, new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1719lambda$onCreate$0$comwugudoublecameraserviceMainService(i);
            }
        }).startNetworkMonitoring();
        initUart();
        LoggerUtil.m1181d(TAG, "hhy cpu temp " + AppUtil.getCpuTemp() + ", cHttp " + SpManager.getInstance().getCustomHttpHost());
        ThreadClock.sleep(5000L);
        initPrinter();
        ThreadClock.sleep(10000L);
        postServiceEvent(9);
        if (this.initStep != 1) {
            SoundHelper.getInstance().resetSoundRes();
        }
        UiPosition uiPosition = App.mUiPosMap.get(18);
        if (uiPosition != null && uiPosition.isVisible) {
            AiModelDownUtil.syncAiModels();
        }
        test();
    }

    void m1719lambda$onCreate$0$comwugudoublecameraserviceMainService(int i) {
        netStatusChange(i == 1);
    }

    private void initPrinter() {
        PrinterHelper.getInstance().init(new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1715lambda$initPrinter$2$comwugudoublecameraserviceMainService(i);
            }
        });
    }

    void m1715lambda$initPrinter$2$comwugudoublecameraserviceMainService(int i) {
        LoggerUtil.m1181d(TAG, "initPrinter status " + i);
        postServiceEvent(19);
    }

    private void startCheckThread() {
        new Thread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1180xde560da();
            }
        }).start();
    }

    void m1180xde560da() {
        ThreadClock.sleep(60000L);
        while (true) {
            checkMqtt();
            if (App.mIsIdle) {
                PrinterHelper.getInstance().refreshPrinter(false);
            }
            ThreadClock.sleep(180000L);
        }
    }

    private void checkMqtt() {
        if (!App.mIsNetAvailable || MqttHelper.getInstance().isConnected()) {
            return;
        }
        LoggerUtil.m1183i(TAG, "net enable but mqtt not connected, initMqtt ");
        initMqtt();
        ThreadClock.sleep(20000L);
        if (App.mIsNetAvailable) {
            MqttHelper.getInstance().isConnected();
        }
    }

    private void netStatusChange(boolean z) {
        LoggerUtil.m1181d(TAG, "netStatusChange " + z);
        App.mIsNetAvailable = z;
        if (z) {
            if (this.initStep != 1) {
                registerHttp();
                PrinterHelper.getInstance().sendPrinterStatus();
            } else {
                OrderManager.uploadFailedFiles();
            }
        }
        postServiceEvent(z ? 6 : 7);
    }

    public void registerHttp() {
        HttpManager.getInstance().mHttpApi.register().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                if (baseDto == null) {
                    return;
                }
                if (baseDto.errCode == 0) {
                    MainService.this.initStep = 1;
                    MainService.this.initNetParam();
                    return;
                }
                LoggerUtil.m1182e(MainService.TAG, "register Error: " + baseDto.errMsg + " " + MainService.this.initStep);
                if (MainService.this.initStep == 2) {
                    return;
                }
                MainService.this.initStep = 2;
                ThreadClock.sleep(60000L);
                MainService.this.registerHttp();
            }

            @Override
            public void onError(Throwable th) {
                super.onError(th);
                LoggerUtil.m1182e(MainService.TAG, "register Error: " + th);
                if (MainService.this.initStep == 2) {
                    return;
                }
                MainService.this.initStep = 2;
                ThreadClock.sleep(60000L);
                MainService.this.registerHttp();
            }
        });
    }

    public void initNetParam() {
        initMqtt();
        ServiceUtil.uploadApkVersion();
        ServiceUtil.syncServerRes(new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.syncFramePictureCallback(i);
            }
        });
        ServiceUtil.syncDeviceSetting(new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.syncSettingCallback(i);
            }
        });
    }

    public void setServiceListener(IServiceListener iServiceListener) {
        this.mServiceListener = iServiceListener;
    }

    public void postServiceEvent(final int i) {
        if (this.mServiceListener != null) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1177x8c050a50(i);
                }
            });
        }
    }

    void m1177x8c050a50(int i) {
        this.mServiceListener.updateEvent(i, null);
    }

    public void postServiceEvent(final int i, int i2) {
        if (this.mServiceListener != null) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1178x71467911(i);
                }
            }, i2);
        }
    }

    void m1178x71467911(int i) {
        this.mServiceListener.updateEvent(i, null);
    }

    private void postServiceEvent(final int i, final String str) {
        if (this.mServiceListener != null) {
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1179x5687e7d2(i, str);
                }
            });
        }
    }

    void m1179x5687e7d2(int i, String str) {
        this.mServiceListener.updateEvent(i, str);
    }

    private void initMqtt() {
        LoggerUtil.m1181d(TAG, "initMqtt isConnected " + MqttHelper.getInstance().isConnected());
        if (MqttHelper.getInstance().isConnected()) {
            return;
        }
        HttpManager.getInstance().mHttpApi.getMqttInfo().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<MqttInfoDto>>() {
            @Override
            public void onNext(BaseDto<MqttInfoDto> baseDto) {
                if (baseDto == null || baseDto.data == null || baseDto.data.subscribeTypeDtos == null || baseDto.data.subscribeTypeDtos.isEmpty() || TextUtils.isEmpty(baseDto.data.userName) || TextUtils.isEmpty(baseDto.data.password)) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (MqttInfoDto.MqttSubscribe mqttSubscribe : baseDto.data.subscribeTypeDtos) {
                    if (mqttSubscribe.flag == 1 && mqttSubscribe.type == 2) {
                        arrayList.add(mqttSubscribe.topic);
                    } else if (mqttSubscribe.flag == 2 && mqttSubscribe.type == 2) {
                        App.mAppMqttTopicServer = mqttSubscribe.topic;
                    }
                }
                String strDecrypt = AesUtil.decrypt(baseDto.data.userName.replace('_', '+').replace('~', '/'), AppConfig.MQTT_KEY);
                String strDecrypt2 = AesUtil.decrypt(baseDto.data.password.replace('_', '+').replace('~', '/'), AppConfig.MQTT_KEY);
                if (TextUtils.isEmpty(strDecrypt) || TextUtils.isEmpty(strDecrypt2)) {
                    return;
                }
                String[] strArr = new String[arrayList.size()];
                int[] iArr = new int[arrayList.size()];
                for (int i = 0; i < arrayList.size(); i++) {
                    strArr[i] = (String) arrayList.get(i);
                    iArr[i] = 1;
                }
                LoggerUtil.m1181d(MainService.TAG, "start connect mqtt ");
                MainService.this.connectMqtt(strArr, iArr, strDecrypt.trim(), strDecrypt2.trim());
            }

            @Override
            public void onError(Throwable th) {
                LoggerUtil.m1182e(MainService.TAG, "MqttInfoDto error " + th);
                MainService.this.connectMqttUseDefault();
            }
        });
    }

    public void connectMqttUseDefault() {
        App.mAppMqttTopicServer = "camera/s/s/" + App.mCPUSerial;
        connectMqtt(new String[]{"camera/s/r/" + App.mCPUSerial}, new int[]{1}, AppConfig.MQTT_NAME, AppConfig.MQTT_PWD);
    }

    public void connectMqtt(String[] strArr, int[] iArr, String str, String str2) {
        if (MqttHelper.getInstance().isConnected()) {
            return;
        }
        MqttHelper.getInstance().initMqtt(App.getInstance(), AppConfig.MQTT_HOST, strArr, iArr, str, str2, App.mCPUSerial, new IMqttRecListener() {
            @Override
            public void connectionLost(Throwable th) {
                MainService.this.postServiceEvent(2);
            }

            @Override
            public void eventCall(String str3) {
                str3.hashCode();
                switch (str3) {
                    case "change_easy_beauty_level":
                        MainService.this.postServiceEvent(18);
                        break;
                    case "a2":
                        MainService.this.startServerHeartCountdown();
                        break;
                    case "b3":
                        LoggerUtil.m1181d(MainService.TAG, "send PAYMENT_OK " + OrderManager.isOnlinePayment());
                        if (OrderManager.isOnlinePayment()) {
                            MainService.this.postServiceEvent(4, 1000);
                            break;
                        }
                        break;
                    case "c1":
                        MainService.this.postServiceEvent(10);
                        break;
                    case "c2":
                        MainService.this.postServiceEvent(11);
                        break;
                    case "c3":
                        MainService.this.postServiceEvent(12);
                        break;
                    case "b13":
                        MainService.this.setSyncSettingCountdown(5);
                        break;
                    case "s12":
                        MainService.this.setSyncResCountdown(5);
                        break;
                    default:
                        MainService.this.mqttEventWithPayload(str3);
                        break;
                }
            }

            @Override
            public void onSuccess() {
                LoggerUtil.m1182e(MainService.TAG, "connect success");
                MainService.this.postServiceEvent(1);
                if (App.isNonCannon && App.isNonCamera) {
                    MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_DISCONNECT, ExifInterface.GPS_MEASUREMENT_2D);
                } else {
                    MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_CONNECT, ExifInterface.GPS_MEASUREMENT_2D);
                }
                if (MainService.this.isSendStartup) {
                    return;
                }
                MainService.this.isSendStartup = true;
                MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_SW_STARTUP, BuildConfig.VERSION_NAME);
            }

            @Override
            public void onFail() {
                LoggerUtil.m1182e(MainService.TAG, "connect fail");
            }
        });
    }

    public void mqttEventWithPayload(String str) {
        if (str.startsWith(MqttCmdEnum.S2C_TEST_INPUT_COIN)) {
            testInputCoin(str);
            return;
        }
        if (str.startsWith(MqttCmdEnum.S2C_PAY_SUCCESS2)) {
            String[] strArrSplit = str.split("\\|");
            if (strArrSplit.length < 2) {
                return;
            }
            TextUtils.isEmpty(strArrSplit[1]);
            return;
        }
        if (str.startsWith(MqttCmdEnum.S2C_UP_PRINT_START)) {
            postServiceEvent(15, str.replace(MqttCmdEnum.S2C_UP_PRINT_START, ""));
        } else if (str.startsWith(MqttCmdEnum.S2C_PRINT_ORDER_PHOTO)) {
            printOrderPhoto(str.replace(MqttCmdEnum.S2C_PRINT_ORDER_PHOTO, ""));
        }
    }

    private void initUart() {
        String paymentMethod = SpManager.getInstance().getPaymentMethod();
        if (paymentMethod.contains(String.valueOf(4)) || paymentMethod.contains(String.valueOf(5)) || paymentMethod.contains(String.valueOf(6)) || paymentMethod.contains(String.valueOf(7)) || paymentMethod.contains(String.valueOf(8)) || paymentMethod.contains(String.valueOf(9))) {
            try {
                System.loadLibrary("serial_port");
                ThreadClock.sleep(500L);
            } catch (UnsatisfiedLinkError e) {
                LoggerUtil.m1182e(TAG, "serial_port library error " + e.getMessage());
            }
            if (paymentMethod.contains(String.valueOf(7))) {
                LoggerUtil.m1181d(TAG, "openUart CardCashHelper");
                CardCashHelper.getInstance().openUart(new CardCashHelper.ICardCashListener() {
                    @Override
                    public void recCount(int i) {
                        if (MainService.this.lastRecMoney != i) {
                            if (i == 0) {
                                MainService.this.lastRecMoney = 0;
                                return;
                            }
                            boolean zAddBalance = OrderManager.addBalance(i - MainService.this.lastRecMoney);
                            LoggerUtil.m1181d(MainService.TAG, "rec cash " + (i - MainService.this.lastRecMoney));
                            MainService.this.lastRecMoney = i;
                            MainService.this.postServiceEvent(zAddBalance ? 4 : 3);
                        }
                    }

                    @Override
                    public void posPayFinish(int i) {
                        LoggerUtil.m1181d(MainService.TAG, "posPayFinish");
                        MainService.this.postServiceEvent(4);
                    }
                });
            }
            if (paymentMethod.contains(String.valueOf(4))) {
                LoggerUtil.m1181d(TAG, "openUart CashHelper");
                CashHelper.getInstance().openUart(new CashHelper.ICashListener() {
                    @Override
                    public final void recCount(int i) {
                        this.f$0.m1716lambda$initUart$7$comwugudoublecameraserviceMainService(i);
                    }
                });
            }
            if (paymentMethod.contains(String.valueOf(5)) || paymentMethod.contains(String.valueOf(6))) {
                LoggerUtil.m1181d(TAG, "openUart CoinHelper");
                CoinHelper.getInstance().openUart(new CoinHelper.ICoinListener() {
                    @Override
                    public final void recCount(int i) {
                        this.f$0.m1717lambda$initUart$8$comwugudoublecameraserviceMainService(i);
                    }
                });
            }
            if (paymentMethod.contains(String.valueOf(8)) || paymentMethod.contains(String.valueOf(9))) {
                LoggerUtil.m1181d(TAG, "openUart ScannerHelper");
                ScannerHelper.getInstance().openUart(new IStringListener() {
                    @Override
                    public final void setValue(String str) {
                        this.f$0.m1718lambda$initUart$9$comwugudoublecameraserviceMainService(str);
                    }
                });
            }
        }
    }

    void m1716lambda$initUart$7$comwugudoublecameraserviceMainService(int i) {
        LoggerUtil.m1181d(TAG, "rec cash " + i);
        postServiceEvent(OrderManager.addBalance(i) ? 4 : 3);
    }

    void m1717lambda$initUart$8$comwugudoublecameraserviceMainService(int i) {
        LoggerUtil.m1181d(TAG, "rec coin " + i);
        if (TextUtils.isEmpty(OrderManager.getOrderFrameKey())) {
            OrderManager.addBalanceOnly(i);
            postServiceEvent(3);
        } else {
            postServiceEvent(OrderManager.addBalance(i) ? 4 : 3);
        }
    }

    void m1718lambda$initUart$9$comwugudoublecameraserviceMainService(String str) {
        LoggerUtil.m1181d(TAG, "rec scanner " + str);
        OrderManager.mMeiTuanDouYinCode = str;
        postServiceEvent(13);
    }

    public void setSyncResCountdown(int i) {
        if (i <= 0) {
            CountDownTimer countDownTimer = this.syncResCountDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                return;
            }
            return;
        }
        CountDownTimer countDownTimer2 = this.syncResCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        CountDownTimerC20095 countDownTimerC20095 = new CountDownTimerC20095(1000 * ((long) i), 1000L);
        this.syncResCountDownTimer = countDownTimerC20095;
        countDownTimerC20095.start();
    }

    class CountDownTimerC20095 extends CountDownTimer {
        @Override
        public void onTick(long j) {
        }

        CountDownTimerC20095(long j, long j2) {
            super(j, j2);
        }

        void m1721lambda$onFinish$0$comwugudoublecameraserviceMainService$5(int i) {
            MainService.this.syncFramePictureCallback(i);
        }

        void m1722lambda$onFinish$1$comwugudoublecameraserviceMainService$5() {
            ServiceUtil.syncServerRes(new IIntListener() {
                @Override
                public final void setValue(int i) {
                    this.f$0.m1721lambda$onFinish$0$comwugudoublecameraserviceMainService$5(i);
                }
            });
        }

        @Override
        public void onFinish() {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1722lambda$onFinish$1$comwugudoublecameraserviceMainService$5();
                }
            });
        }
    }

    public void setSyncSettingCountdown(int i) {
        if (i <= 0) {
            CountDownTimer countDownTimer = this.syncSettingCountDownTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
                return;
            }
            return;
        }
        CountDownTimer countDownTimer2 = this.syncSettingCountDownTimer;
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        CountDownTimerC20106 countDownTimerC20106 = new CountDownTimerC20106(1000 * ((long) i), 1000L);
        this.syncSettingCountDownTimer = countDownTimerC20106;
        countDownTimerC20106.start();
    }

    class CountDownTimerC20106 extends CountDownTimer {
        @Override
        public void onTick(long j) {
        }

        CountDownTimerC20106(long j, long j2) {
            super(j, j2);
        }

        void m1723lambda$onFinish$0$comwugudoublecameraserviceMainService$6(int i) {
            MainService.this.syncSettingCallback(i);
        }

        void m1724lambda$onFinish$1$comwugudoublecameraserviceMainService$6() {
            ServiceUtil.syncDeviceSetting(new IIntListener() {
                @Override
                public final void setValue(int i) {
                    this.f$0.m1723lambda$onFinish$0$comwugudoublecameraserviceMainService$6(i);
                }
            });
        }

        @Override
        public void onFinish() {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m1724lambda$onFinish$1$comwugudoublecameraserviceMainService$6();
                }
            });
        }
    }

    public void syncSettingCallback(int i) {
        if (i == 1) {
            postServiceEvent(0);
        } else if (i == 2) {
            SoundHelper.getInstance().releaseMusicPlayer();
        } else if (i == 3) {
            SoundHelper.getInstance().resetSoundRes();
        }
    }

    public void syncFramePictureCallback(int i) {
        if (i == 1) {
            postServiceEvent(8);
            checkPicDowningCount();
        } else if (i == 2) {
            App.mDownCount.set(0);
            postServiceEvent(9);
        } else if (i == 3) {
            postServiceEvent(17);
        }
    }

    private void checkPicDowningCount() {
        if (this.isCheckingDown) {
            return;
        }
        this.isCheckingDown = true;
        ThreadClock.sleep(1000L);
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1176xc84abdf3();
            }
        });
    }

    void m1176xc84abdf3() {
        int i = App.mDownCount.get();
        if (i == 0) {
            postServiceEvent(9);
        }
        int i2 = i;
        int i3 = 20;
        while (true) {
            if (i <= 0) {
                break;
            }
            ThreadClock.sleep(1000L);
            i = App.mDownCount.get();
            i3--;
            if (i3 <= 0) {
                if (i2 == i) {
                    LoggerUtil.m1182e(TAG, "timeout");
                    App.mDownCount.set(0);
                    postServiceEvent(9);
                    break;
                }
                i2 = i;
                i3 = 20;
            }
            postServiceEvent(8);
        }
        ThreadClock.sleep(1000L);
        postServiceEvent(9);
        this.isCheckingDown = false;
    }

    public void startServerHeartCountdown() {
        CountDownTimer countDownTimer = this.timerServerConnected;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.timerServerConnected = new CountDownTimer(80000L, 1000L) {
            @Override
            public void onTick(long j) {
            }

            @Override
            public void onFinish() {
                MainService.this.postServiceEvent(2);
            }
        };
    }

    private void printOrderPhoto(String str) {
        int iStrToInt;
        String str2;
        boolean z;
        LoggerUtil.m1181d(TAG, "printOrderPhoto " + str);
        if (App.mIsIdle) {
            String[] strArrSplit = str.split(";");
            if (strArrSplit.length == 3) {
                iStrToInt = StringUtil.strToInt(strArrSplit[1], 1);
                z = StringUtil.strToInt(strArrSplit[0], 0) == 1;
                str2 = strArrSplit[2];
            } else {
                if (strArrSplit.length != 2) {
                    return;
                }
                iStrToInt = StringUtil.strToInt(strArrSplit[0], 1);
                str2 = strArrSplit[1];
                z = false;
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            final File file = new File(App.getInstance().getFilesDir(), StringUtil.getFileNameFromPath(str2));
            if (file.exists()) {
                PrinterHelper.getInstance().addPrintTask(file.getAbsolutePath(), iStrToInt, z);
                postServiceEvent(16, str);
                return;
            } else {
                LoggerUtil.m1182e(TAG, "printOrderPhoto file not exist " + file.getAbsolutePath());
                ToastHelper.getInstance().showToast(getString(C1910R.string.file_not_exist));
                App.mIsIdle = false;
                HttpManager.getInstance().downLoadFile(str2, file.getAbsolutePath(), new IDownFileListener() {
                    @Override
                    public void progress(int i) {
                    }

                    @Override
                    public void success() {
                        LoggerUtil.m1181d(MainService.TAG, "printOrderPhoto down ok");
                        if (PrinterHelper.getInstance().isPrintPdf()) {
                            FileUtil.genBlackPdfFile(file.getAbsolutePath());
                        }
                        App.mIsIdle = true;
                    }

                    @Override
                    public void fail(int i) {
                        App.mIsIdle = true;
                        LoggerUtil.m1181d(MainService.TAG, "printOrderPhoto down fail " + i);
                    }
                });
                return;
            }
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.pls_return_home_page));
    }

    private void testInputCoin(String str) {
        if (SpManager.getInstance().getIsLockScreen()) {
            String[] strArrSplit = str.split("\\|");
            if (strArrSplit.length < 2) {
                return;
            }
            if (strArrSplit[1].equals(PDLayoutAttributeObject.GLYPH_ORIENTATION_VERTICAL_ZERO_DEGREES)) {
                postServiceEvent(OrderManager.addBalance(Integer.parseInt(strArrSplit[2])) ? 4 : 3);
            } else if (strArrSplit[1].equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                OrderManager.mMeiTuanDouYinCode = "38234124349812";
                postServiceEvent(13);
            }
        }
    }
}
