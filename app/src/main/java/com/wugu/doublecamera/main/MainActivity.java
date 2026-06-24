package com.wugu.doublecamera.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.hardware.camera2.CameraManager;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.brother.sdk.BrotherAndroidLib;
import com.brother.sdk.usb.UsbControllerManager;
import com.bumptech.glide.Glide;
import com.epson.isv.eprinterdriver.Ctrl.EPrintManager;
import com.hiti.usb.service.ServiceConnector;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.base.ABaseActivity;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.StartPrintingInfo;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.databinding.ActivityMainBinding;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.device.camera.camerausb.CameraConnector;
import com.wugu.doublecamera.device.camera.camerausb.canon1500d.CameraCanon1500D;
import com.wugu.doublecamera.dialog.ScreenLockDialog;
import com.wugu.doublecamera.dialog.StopBusinessDialog;
import com.wugu.doublecamera.dialog.TestPrintDialog;
import com.wugu.doublecamera.enums.FragmentEnum;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.enums.UiPosIndexEnum;
import com.wugu.doublecamera.listener.IDeviceListener;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.main.p025vm.MainVModel;
import com.wugu.doublecamera.main.poster_sys.PosterEasyFragment2;
import com.wugu.doublecamera.main.poster_sys.PosterFrameFragment;
import com.wugu.doublecamera.main.poster_sys.PosterPaymentFragment;
import com.wugu.doublecamera.main.poster_sys.PosterPrePrintFragment;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.service.MainService;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.FragmentUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.utils.ViewUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.SoundHelper;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.facebeauty.FaceBeautyMain;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicLong;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class MainActivity extends ABaseActivity<ActivityMainBinding, MainVModel> implements IDeviceListener {
    private static final int ADMIN_COUNTS = 6;
    private static final long ADMIN_DURATION = 6000;
    private static final int MSG_WHAT_AUTO_TIME = 3;
    private static final int MSG_WHAT_INIT_BEAUTY = 2;
    private static final int MSG_WHAT_USB_DETACHED = 1;
    private static final String TAG = "MainActivity";
    private AlertDialog alertDialog;
    private int currentFragmentIndex;
    private int currentFrameType;
    private ServiceConnector hitiPrinterServiceConnector;
    private HomeFragment homeFragment;
    private InitBoardFragment initBoardFragment;
    private PosterEasyFragment2 posterEasyFragment;
    private ScreenLockDialog screenLockDialog;
    private StopBusinessDialog stopBusinessDialog;
    private int LOCK_TIME = 10;
    private MainService mService = null;
    private long[] adminHits = new long[6];
    private int printerModel = 1;
    private final AtomicLong lastPayFinishTime = new AtomicLong(0);
    private boolean isReopenUsbPower = true;
    private final BroadcastReceiver usbStateChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }
            LoggerUtil.m1183i(MainActivity.TAG, "rec action " + action);
            MainActivity.this.handler.removeMessages(1);
            MainActivity.this.handler.sendEmptyMessageDelayed(1, 2000L);
            if (action.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                MainActivity.this.checkCameraConnect(intent);
            }
        }
    };
    private final Handler handler = new Handler(new Handler.Callback() {
        @Override
        public final boolean handleMessage(Message message) {
            return this.f$0.m1659lambda$new$0$comwugudoublecameramainMainActivity(message);
        }
    });
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MainActivity.this.mService = ((MainService.LocalBinder) iBinder).service;
            MainActivity.this.initServiceListener();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            MainActivity.this.mService = null;
        }
    };

    boolean m1659lambda$new$0$comwugudoublecameramainMainActivity(Message message) {
        LoggerUtil.m1181d(TAG, "handler msg " + message.what);
        int i = message.what;
        if (i == 1) {
            checkUsbDevices();
            return false;
        }
        if (i == 2) {
            registerBeauty();
            return false;
        }
        if (i != 3) {
            return false;
        }
        AppUtil.switchAutoTime(true);
        return false;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        File file = new File(AppConfig.BASE_FOLDER);
        if (!file.exists() || !file.isDirectory()) {
            this.initBoardFragment = new InitBoardFragment();
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, this.initBoardFragment, false);
        } else {
            AppUtil.setSystemBar(false);
            AppUtil.setBootStartUp(getPackageName(), MainActivity.class.getName());
            AppUtil.keepActivity(getPackageName());
            initBoardOk();
        }
    }

    public void initBoardOk() {
        InitBoardFragment initBoardFragment = this.initBoardFragment;
        if (initBoardFragment != null) {
            FragmentUtil.removeFragment(this, initBoardFragment);
            this.initBoardFragment = null;
        }
        initPermission();
        initLogger();
        initData();
        initView();
        initFragments();
        initObserver();
        initReceiver();
        initListener();
        LoggerUtil.m1183i(TAG, "activity onCreate");
        checkBeautyTime();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LoggerUtil.m1183i(TAG, "activity onStart");
        OrderManager.clearOrderInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.usbStateChangeReceiver);
        MqttHelper.getInstance().close();
        UsbControllerManager.getUsbController().stopControl();
        MainService mainService = this.mService;
        if (mainService != null) {
            mainService.stopSelf();
        }
        ServiceConnector serviceConnector = this.hitiPrinterServiceConnector;
        if (serviceConnector != null) {
            serviceConnector.unregister();
        }
    }

    @Override
    public void onBackPressed() {
        LoggerUtil.m1181d(TAG, "onBackPressed " + this.currentFragmentIndex);
        int i = this.currentFragmentIndex;
        if (i == 3 || i == 9 || i == 5 || i == 4 || i == 33) {
            return;
        }
        FragmentUtil.popBackStack(this);
        ((MainVModel) this.mViewModel).stopCheckOrderTask();
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1660lambda$onBackPressed$1$comwugudoublecameramainMainActivity();
            }
        }, 200L);
    }

    void m1660lambda$onBackPressed$1$comwugudoublecameramainMainActivity() {
        if (FragmentUtil.getBackStackFragmentCount(this) == 0) {
            this.currentFragmentIndex = 0;
            App.mIsIdle = true;
            SoundHelper.getInstance().startPlayBgm();
            ((MainVModel) this.mViewModel).startCountdown(this.LOCK_TIME);
            HomeFragment homeFragment = this.homeFragment;
            if (homeFragment != null) {
                homeFragment.onHiddenChanged(false);
            }
            PosterEasyFragment2 posterEasyFragment2 = this.posterEasyFragment;
            if (posterEasyFragment2 != null) {
                posterEasyFragment2.onHiddenChanged(false);
            }
            Glide.get(this).clearMemory();
            return;
        }
        this.currentFragmentIndex = 27;
        Fragment fragmentStackTop = FragmentUtil.getFragmentStackTop(this);
        if (fragmentStackTop == null || !fragmentStackTop.isVisible()) {
            return;
        }
        fragmentStackTop.onHiddenChanged(false);
    }

    @Override
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    private void checkBeautyTime() {
        AppUtil.BlockFuAddress();
        long jCurrentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2024);
        calendar.set(2, 2);
        calendar.set(5, 1);
        long timeInMillis = calendar.getTimeInMillis();
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(jCurrentTimeMillis));
        LoggerUtil.m1181d(TAG, "currentZone " + TimeZone.getDefault().getID());
        LoggerUtil.m1181d(TAG, "currentTimeMillis " + str);
        if (jCurrentTimeMillis < timeInMillis) {
            LoggerUtil.m1181d(TAG, "set time 2024-03-01 12:00:00");
            AppUtil.setSystemTime(2024, 3, 1, 12, 0, 0);
            this.handler.sendEmptyMessageDelayed(2, 2000L);
            return;
        }
        registerBeauty();
    }

    private void registerBeauty() {
        FaceBeautyMain.registerFURender(App.getInstance(), new FaceBeautyMain.RegCallback() {
            @Override
            public final void onResult(boolean z) {
                MainActivity.lambda$registerBeauty$2(z);
            }
        });
        this.handler.sendEmptyMessageDelayed(3, 10000L);
    }

    static void lambda$registerBeauty$2(boolean z) {
        LoggerUtil.m1181d(TAG, "registerFURender " + z);
        if (z && App.mIsInitBeautySuccess == 0) {
            App.mIsInitBeautySuccess = 1;
        } else {
            if (z) {
                return;
            }
            App.mIsInitBeautySuccess = 2;
        }
    }

    private void searchSystemCamera() {
        boolean z = false;
        try {
            if (((CameraManager) getSystemService("camera")).getCameraIdList().length > 0) {
                z = true;
            }
        } catch (Exception unused) {
        }
        App.isNonCamera = !z;
    }

    @Override
    public void foundCamera() {
        ((MainVModel) this.mViewModel).digitalCameraResetLD.postValue(1);
        App.isNonCannon = false;
        updateDeviceStatus();
        if (App.mIsIdle) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() throws Throwable {
                    this.f$0.m1648lambda$foundCamera$3$comwugudoublecameramainMainActivity();
                }
            });
        }
    }

    void m1648lambda$foundCamera$3$comwugudoublecameramainMainActivity() throws Throwable {
        if (this.isReopenUsbPower) {
            this.isReopenUsbPower = false;
            AppUtil.reopenUsbPower();
            ThreadClock.sleep(1000L);
        }
        new CameraCanon1500D(this);
    }

    @Override
    public void onNoCameraFound() {
        LoggerUtil.m1182e(TAG, "onNoCameraFound ");
        App.isNonCannon = true;
        App.selectedCameraIndex = 1;
        CameraConnector.getInstance().connectionClose();
        ((MainVModel) this.mViewModel).digitalCameraResetLD.postValue(0);
        updateDeviceStatus();
    }

    private void initLogger() {
        List<String> fileList = FileUtil.getFileList(AppConfig.LOGGER_DIR);
        if (fileList.size() < 20) {
            LoggerUtil.createLoggerFile();
            return;
        }
        for (int i = 0; i < fileList.size() - 20; i++) {
            FileUtil.deleteFileFolder(AppConfig.LOGGER_DIR + MqttTopic.TOPIC_LEVEL_SEPARATOR + fileList.get(i));
        }
        LoggerUtil.createLoggerFile();
    }

    private void initView() {
        if (App.mSystemMode == 3) {
            return;
        }
        showLoadAnim(getString(C1910R.string.initializing));
        AppUtil.runOnUIDelayed(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1658lambda$initView$4$comwugudoublecameramainMainActivity();
            }
        }, 10000L);
    }

    void m1658lambda$initView$4$comwugudoublecameramainMainActivity() {
        ViewUtil.setUiLocate(((ActivityMainBinding) this.mViewBinding).ivPhotoTransit, UiPosIndexEnum.PHOTO_TRANSITION);
    }

    private void initPermission() {
        XXPermissions.with(this).permission(Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE, Permission.READ_EXTERNAL_STORAGE).request(new OnPermissionCallback() {
            @Override
            public void onGranted(List<String> list, boolean z) {
                if (z) {
                    return;
                }
                MainActivity mainActivity = MainActivity.this;
                Toast.makeText(mainActivity, mainActivity.getResources().getString(C1910R.string.need_permission), 0).show();
            }

            @Override
            public void onDenied(List<String> list, boolean z) {
                XXPermissions.startPermissionActivity((Activity) MainActivity.this, list);
            }
        });
    }

    private void initFragments() {
        if (App.mSystemMode == 3) {
            this.posterEasyFragment = new PosterEasyFragment2();
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, this.posterEasyFragment, false);
        } else {
            this.homeFragment = new HomeFragment();
            this.currentFragmentIndex = 0;
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, this.homeFragment, false);
        }
    }

    private void initObserver() {
        ((MainVModel) this.mViewModel).fragmentLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1653lambda$initObserver$5$comwugudoublecameramainMainActivity((Integer) obj);
            }
        });
        ((MainVModel) this.mViewModel).chooseFrameOkLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1654lambda$initObserver$6$comwugudoublecameramainMainActivity((String) obj);
            }
        });
        ((MainVModel) this.mViewModel).startPrintingLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1655lambda$initObserver$7$comwugudoublecameramainMainActivity((StartPrintingInfo) obj);
            }
        });
        ((MainVModel) this.mViewModel).getCountDownLD().observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1656lambda$initObserver$8$comwugudoublecameramainMainActivity((Integer) obj);
            }
        });
        ((MainVModel) this.mViewModel).getRemoteControlLD().observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1657lambda$initObserver$9$comwugudoublecameramainMainActivity((Integer) obj);
            }
        });
        ((MainVModel) this.mViewModel).uploadPrintLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1650lambda$initObserver$10$comwugudoublecameramainMainActivity((String) obj);
            }
        });
        ((MainVModel) this.mViewModel).redeemCodeInfoLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1651lambda$initObserver$11$comwugudoublecameramainMainActivity((RedeemCodeDto) obj);
            }
        });
        ((MainVModel) this.mViewModel).lockScreenLD.observe(this, new Observer() {
            @Override
            public final void onChanged(Object obj) {
                this.f$0.m1652lambda$initObserver$12$comwugudoublecameramainMainActivity((Integer) obj);
            }
        });
    }

    void m1653lambda$initObserver$5$comwugudoublecameramainMainActivity(Integer num) {
        LoggerUtil.m1181d(TAG, "fragmentLD " + FragmentEnum.getFragmentName(num.intValue()));
        ((MainVModel) this.mViewModel).cancelCountdown();
        FragmentUtil.popBackStack(this);
        showSubFragment(num.intValue());
    }

    void m1654lambda$initObserver$6$comwugudoublecameramainMainActivity(String str) {
        LoggerUtil.m1181d(TAG, "chooseFrameOkLD " + str);
        FrameInfo orderFrameInfo = OrderManager.getOrderFrameInfo();
        if (orderFrameInfo == null || orderFrameInfo.getFrameType() == 5) {
            return;
        }
        ((MainVModel) this.mViewModel).cancelCountdown();
        this.currentFrameType = orderFrameInfo.getFrameType();
        App.mIsIdle = false;
        if (OrderManager.isPhotoWithoutPay()) {
            FragmentUtil.popBackStack(this);
            startPhotoFragment();
        } else if (App.mSystemMode == 2) {
            showSubFragment(32);
        } else {
            showSubFragment(2);
        }
    }

    void m1655lambda$initObserver$7$comwugudoublecameramainMainActivity(StartPrintingInfo startPrintingInfo) {
        showSubFragment(8);
    }

    void m1656lambda$initObserver$8$comwugudoublecameramainMainActivity(Integer num) {
        if (num.intValue() == 0) {
            showScreenLockDialog();
        }
    }

    void m1657lambda$initObserver$9$comwugudoublecameramainMainActivity(Integer num) {
        if (num.intValue() == 3) {
            payFinish();
        }
    }

    void m1650lambda$initObserver$10$comwugudoublecameramainMainActivity(String str) {
        onBackPressed();
        if (AppConfig.isBelongHeadSys()) {
            this.currentFragmentIndex = 5;
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, PrePrintFragment.startOnlyPrintFragment(str), true);
        } else if (App.mSystemMode == 2) {
            this.currentFragmentIndex = 33;
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, PosterPrePrintFragment.startOnlyPrintFragment(str), true);
        }
    }

    void m1651lambda$initObserver$11$comwugudoublecameramainMainActivity(RedeemCodeDto redeemCodeDto) {
        dismissLoadAnim();
        if (redeemCodeDto == null) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.network_error));
            return;
        }
        if (redeemCodeDto.getType() == 0) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_code_fail));
            return;
        }
        if (redeemCodeDto.getType() == 3) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_code_expire));
            return;
        }
        if (redeemCodeDto.getType() == 4) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_code_used));
            return;
        }
        if (redeemCodeDto.getType() == 5) {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_code_not_device));
            return;
        }
        if (redeemCodeDto.getType() == 2) {
            if (OrderManager.checkBalance()) {
                createRedeemOrder(3);
            }
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_cash, new Object[]{StringUtil.getPriceStr(MathUtil.getPriceValue(redeemCodeDto.getAmount()))}));
            return;
        }
        if (redeemCodeDto.getType() == 1 || redeemCodeDto.getType() == 8) {
            if (OrderManager.checkBalance()) {
                createRedeemOrder(3);
            }
            if (App.mSystemMode != 4) {
                ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_discount, new Object[]{Integer.valueOf((int) redeemCodeDto.getAmount())}));
                return;
            }
            return;
        }
        if (redeemCodeDto.getType() == 6) {
            createRedeemOrder(8);
            return;
        }
        if (redeemCodeDto.getType() == 7) {
            createRedeemOrder(9);
        } else if (redeemCodeDto.getType() == 9) {
            createRedeemOrder(12);
        } else {
            ToastHelper.getInstance().showToast(getString(C1910R.string.redeem_code_fail));
        }
    }

    void m1652lambda$initObserver$12$comwugudoublecameramainMainActivity(Integer num) {
        showScreenLockDialog();
    }

    public void backToHomeFragment() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m949x508cd1ce();
            }
        });
    }

    void m949x508cd1ce() {
        while (FragmentUtil.getBackStackFragmentCount(this) > 0) {
            onBackPressed();
            ThreadClock.sleep(500L);
        }
        App.mIsIdle = true;
    }

    private void testPrint() {
        if (App.mIsIdle) {
            new TestPrintDialog(this, AppConfig.TEST_PRINT_FILE_PATH, 1).show();
            ((MainVModel) this.mViewModel).testPrint();
        }
    }

    private void printOrderPhoto(String str) {
        int iStrToInt;
        String str2;
        LoggerUtil.m1181d(TAG, "printOrderPhoto: " + str + ", isIdle " + App.mIsIdle);
        if (App.mIsIdle) {
            String[] strArrSplit = str.split(";");
            if (strArrSplit.length == 3) {
                iStrToInt = StringUtil.strToInt(strArrSplit[1], 1);
                str2 = strArrSplit[2];
            } else {
                if (strArrSplit.length != 2) {
                    return;
                }
                iStrToInt = StringUtil.strToInt(strArrSplit[0], 1);
                str2 = strArrSplit[1];
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            new TestPrintDialog(this, App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + StringUtil.getFileNameFromPath(str2), iStrToInt).show();
            return;
        }
        ToastHelper.getInstance().showToast(getString(C1910R.string.pls_return_home_page));
    }

    private void updateBarText() {
        String machineNum = SpManager.getInstance().getMachineNum();
        if (TextUtils.isEmpty(machineNum)) {
            machineNum = (TextUtils.isEmpty(App.mCPUSerial) || App.mCPUSerial.length() <= 6) ? "------" : App.mCPUSerial.substring(App.mCPUSerial.length() - 6).toUpperCase();
        }
        ((ActivityMainBinding) this.mViewBinding).statusDeviceName.setText(getString(C1910R.string.bar_name_cpu, new Object[]{machineNum, SpManager.getInstance().getMachineName()}));
        ((ActivityMainBinding) this.mViewBinding).statusPhone.setText(getString(C1910R.string.bar_phone, new Object[]{SpManager.getInstance().getMachinePhone()}));
        String wechatNum = SpManager.getInstance().getWechatNum();
        if (TextUtils.isEmpty(wechatNum)) {
            ((ActivityMainBinding) this.mViewBinding).statusWechat.setVisibility(8);
            ((ActivityMainBinding) this.mViewBinding).tvWechatNum.setText((CharSequence) null);
        } else {
            ((ActivityMainBinding) this.mViewBinding).statusWechat.setVisibility(0);
            ((ActivityMainBinding) this.mViewBinding).tvWechatNum.setText(wechatNum);
        }
    }

    private void showSubFragment(int i) {
        SoundHelper.getInstance().stopPlayBgm();
        HomeFragment homeFragment = this.homeFragment;
        if (homeFragment != null) {
            homeFragment.onHiddenChanged(true);
        }
        FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, getFragment(i), true);
    }

    private void initListener() {
        ((ActivityMainBinding) this.mViewBinding).statusBarLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                this.f$0.m1649lambda$initListener$14$comwugudoublecameramainMainActivity(view);
            }
        });
    }

    void m1649lambda$initListener$14$comwugudoublecameramainMainActivity(View view) {
        long[] jArr = this.adminHits;
        System.arraycopy(jArr, 1, jArr, 0, jArr.length - 1);
        long[] jArr2 = this.adminHits;
        jArr2[jArr2.length - 1] = SystemClock.uptimeMillis();
        if (this.adminHits[0] >= SystemClock.uptimeMillis() - ADMIN_DURATION) {
            this.adminHits = new long[6];
            ((MainVModel) this.mViewModel).cancelCountdown();
            FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, getFragment(6), true);
        }
    }

    private void initData() {
        App.mSystemMode = SpManager.getInstance().getSystemMode();
        this.printerModel = SpManager.getInstance().getPrinterModel();
        LoggerUtil.m1181d(TAG, "printModel: " + this.printerModel);
        Log.d("hhy", "printerModel: " + this.printerModel);
        int i = this.printerModel;
        if (i == 3) {
            BrotherAndroidLib.initialize(getApplicationContext());
            UsbControllerManager.getUsbController().startControl();
        } else if (i == 5) {
            EPrintManager.instance().initEscprLib(getApplicationContext());
        } else if (i == 7) {
            this.hitiPrinterServiceConnector = ServiceConnector.register(this, null);
            PrinterHelper.getInstance().initHitiPrinterService(this.hitiPrinterServiceConnector);
            this.hitiPrinterServiceConnector.StartService();
        }
        HttpManager.getInstance().init();
        searchSystemCamera();
        CameraConnector.getInstance().setDeviceListener(this);
        CameraConnector.getInstance().setActivity(this);
        bindService(new Intent(this, (Class<?>) MainService.class), this.serviceConnection, 1);
        if (App.mSystemMode == 3) {
            this.LOCK_TIME = 600;
        }
        ((MainVModel) this.mViewModel).startCountdown(this.LOCK_TIME);
    }

    public void initServiceListener() {
        MainService mainService = this.mService;
        if (mainService != null) {
            mainService.setServiceListener(new MainService.IServiceListener() {
                @Override
                public final void updateEvent(int i, String str) {
                    this.f$0.m952xcbcf9742(i, str);
                }
            });
        }
        updateBarText();
    }

    void m952xcbcf9742(int i, String str) {
        LoggerUtil.m1181d(TAG, "service listener: " + i + " " + str);
        switch (i) {
            case 0:
                updateBarText();
                break;
            case 1:
            case 2:
                HomeFragment homeFragment = this.homeFragment;
                if (homeFragment != null) {
                    homeFragment.serverStatusChange();
                }
                break;
            case 3:
                ((MainVModel) this.mViewModel).postPayStep(5);
                break;
            case 4:
                payFinish();
                break;
            case 6:
            case 7:
                HomeFragment homeFragment2 = this.homeFragment;
                if (homeFragment2 != null) {
                    homeFragment2.netStatusChange(i == 6);
                }
                PosterEasyFragment2 posterEasyFragment2 = this.posterEasyFragment;
                if (posterEasyFragment2 != null) {
                    posterEasyFragment2.updateNetStatus(i == 6);
                }
                break;
            case 8:
                if (App.mIsIdle) {
                    showLoadAnim(getString(C1910R.string.downing_res, new Object[]{Integer.valueOf(App.mDownCount.get())}));
                }
                break;
            case 9:
                dismissLoadAnim();
                break;
            case 10:
                showBusinessDialog(false);
                break;
            case 11:
                showBusinessDialog(true);
                break;
            case 12:
                testPrint();
                break;
            case 13:
                LoggerUtil.m1181d(TAG, "scanner rec " + OrderManager.mPaymentMethod + ", " + OrderManager.mRedeemCodeDto);
                if (OrderManager.mPaymentMethod == 8 || OrderManager.mPaymentMethod == 9) {
                    ((MainVModel) this.mViewModel).queryMtDyCode();
                    showLoadAnim(getString(C1910R.string.query_ing));
                    ((MainVModel) this.mViewModel).postPayStep(6);
                }
                break;
            case 15:
                LoggerUtil.m1181d(TAG, "UPLOAD_PRINT_REC " + App.mIsIdle + " " + str);
                if (App.mIsIdle) {
                    FragmentUtil.popBackStack(this);
                    this.currentFragmentIndex = 29;
                    FragmentUtil.showFragment(((ActivityMainBinding) this.mViewBinding).container.getId(), this, UploadPrintFragment.startUpPrintFragment(str), true);
                } else {
                    ((MainVModel) this.mViewModel).uploadPrintRecLD.postValue(str);
                }
                break;
            case 16:
                printOrderPhoto(str);
                break;
            case 17:
                ((MainVModel) this.mViewModel).postRemoteControl(2);
                break;
            case 18:
                ((MainVModel) this.mViewModel).postRemoteControl(4);
                break;
            case 19:
                updateDeviceStatus();
                if (!App.mIsIdle && !PrinterHelper.getInstance().isPrinterReady()) {
                    showAlertDialog(getString(C1910R.string.printer_error), PrinterHelper.getInstance().getPrinterStatusDescription());
                    break;
                }
                break;
        }
    }

    private void createRedeemOrder(int i) {
        LoggerUtil.m1181d(TAG, "createRedeemOrder " + i);
        OrderManager.createPhotoOrder(i, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                this.f$0.m951xac383846(str);
            }
        });
    }

    void m951xac383846(String str) {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.payFinish();
            }
        });
    }

    public void payFinish() {
        LoggerUtil.m1181d(TAG, "payFinish " + isPaymentFragment() + " " + Thread.currentThread().getName());
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = this.lastPayFinishTime.get();
        if (jCurrentTimeMillis - j < 2000) {
            return;
        }
        if (this.lastPayFinishTime.compareAndSet(j, jCurrentTimeMillis) || jCurrentTimeMillis - this.lastPayFinishTime.get() >= 2000) {
            LoggerUtil.m1181d(TAG, "payFinish PAYMENT_OK");
            ((MainVModel) this.mViewModel).stopCheckOrderTask();
            OrderManager.orderPayFinish();
            ((MainVModel) this.mViewModel).postPayStep(3);
            if (isPaymentFragment()) {
                ((MainVModel) this.mViewModel).cancelCountdown();
                FragmentUtil.clearBackStack(this);
                startPhotoFragment();
            } else if (App.mIsIdle && OrderManager.isPhotoNotOnPayment()) {
                FragmentUtil.clearBackStack(this);
                startPhotoFragment();
            }
        }
    }

    private void initReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        registerReceiver(this.usbStateChangeReceiver, intentFilter);
    }

    private void startPhotoFragment() {
        if (App.mSystemMode == 1 || App.mSystemMode == 4 || App.mSystemMode == 2) {
            System.gc();
            startEntryAnim();
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m955x6ebde843();
                }
            }, 200L);
        }
    }

    void m955x6ebde843() {
        if (this.currentFrameType == 3) {
            showSubFragment(9);
        } else {
            showSubFragment(3);
        }
    }

    private void startEntryAnim() {
        ((ActivityMainBinding) this.mViewBinding).ivPhotoTransit.setVisibility(0);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((ActivityMainBinding) this.mViewBinding).ivPhotoTransit, "alpha", 0.3f, 1.0f);
        objectAnimatorOfFloat.setDuration(100L);
        objectAnimatorOfFloat.addListener(new C19544());
        objectAnimatorOfFloat.start();
    }

    class C19544 extends AnimatorListenerAdapter {
        C19544() {
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            AppUtil.runOnUIDelayed(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m957xd6016f45();
                }
            }, 600L);
        }

        void m957xd6016f45() {
            MainActivity.this.endEntryAnim();
        }
    }

    public void endEntryAnim() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(((ActivityMainBinding) this.mViewBinding).ivPhotoTransit, "alpha", 1.0f, 0.3f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                ((ActivityMainBinding) MainActivity.this.mViewBinding).ivPhotoTransit.setVisibility(4);
            }
        });
        objectAnimatorOfFloat.start();
    }

    private Fragment getFragment(int i) {
        this.currentFragmentIndex = i;
        if (i != 0) {
            switch (i) {
                case 2:
                    return new PaymentFragment();
                case 3:
                    return new PhotoFragment();
                case 4:
                    return new PictureSelectFragment();
                case 5:
                    return new PrePrintFragment();
                case 6:
                    return new AdminFragment();
                case 7:
                    return new CheckFrameFragment();
                case 8:
                    return new PrintingFragment();
                case 9:
                    return new CertifyPhotoFragment();
                case 10:
                    return new CertifyPrintFragment();
                case 11:
                    return new AiPrintFragment();
                case 12:
                    return new ReplaceBgFragment();
                case 13:
                    return new PrintUsbDriverFragment();
                case 14:
                    return new PosterEasyFragment2();
                default:
                    switch (i) {
                        case 27:
                        case 28:
                            break;
                        case 29:
                            return new UploadPrintFragment();
                        default:
                            switch (i) {
                                case 31:
                                    return new PosterFrameFragment();
                                case 32:
                                    return new PosterPaymentFragment();
                                case 33:
                                    return new PosterPrePrintFragment();
                                default:
                                    switch (i) {
                                        case 210:
                                        case 212:
                                        case 213:
                                            break;
                                        case 211:
                                            return new ChooseAiFragment();
                                        default:
                                            this.currentFragmentIndex = 0;
                                            return this.homeFragment;
                                    }
                                    break;
                            }
                            break;
                    }
                    return new ChooseFrameFragment();
            }
        }
        return this.homeFragment;
    }

    private void showScreenLockDialog() {
        if (SpManager.getInstance().getIsLockScreen()) {
            if (this.screenLockDialog == null) {
                this.screenLockDialog = new ScreenLockDialog(this, new IStringListener() {
                    @Override
                    public final void setValue(String str) {
                        this.f$0.m954xb5ce5d68(str);
                    }
                });
            }
            this.screenLockDialog.show();
        }
    }

    void m954xb5ce5d68(String str) {
        if (str.equals(SpManager.getInstance().getLockPwd())) {
            this.screenLockDialog.dismiss();
            ((MainVModel) this.mViewModel).startCountdown(this.LOCK_TIME);
        } else {
            ToastHelper.getInstance().showToast(getString(C1910R.string.pwd_error));
        }
    }

    private void showBusinessDialog(boolean z) {
        if (z) {
            StopBusinessDialog stopBusinessDialog = this.stopBusinessDialog;
            if (stopBusinessDialog == null || !stopBusinessDialog.isShowing()) {
                return;
            }
            this.stopBusinessDialog.dismiss();
            return;
        }
        if (this.stopBusinessDialog == null) {
            this.stopBusinessDialog = new StopBusinessDialog(this);
        }
        this.stopBusinessDialog.show();
    }

    public void checkCameraConnect(Intent intent) {
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
        if (usbDevice != null) {
            String productName = usbDevice.getProductName();
            LoggerUtil.m1181d(TAG, "detached " + productName);
            if (this.posterEasyFragment == null || TextUtils.isEmpty(productName)) {
                return;
            }
            if ((productName.contains("StreamCam") || productName.contains("Webcam")) && "android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
                App.isNonCamera = true;
                MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_DISCONNECT, ExifInterface.GPS_MEASUREMENT_2D);
            }
        }
    }

    private void checkUsbDevices() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m950xebe7263e();
            }
        });
    }

    void m950xebe7263e() {
        CameraConnector.getInstance().research();
        ThreadClock.sleep(1000L);
        checkCamera();
        PrinterHelper.getInstance().refreshPrinter(true);
    }

    private void updateDeviceStatus() {
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m956x4791a7ca();
            }
        });
    }

    void m956x4791a7ca() {
        HomeFragment homeFragment = this.homeFragment;
        if (homeFragment != null) {
            homeFragment.updateDeviceStatus();
        }
        PosterEasyFragment2 posterEasyFragment2 = this.posterEasyFragment;
        if (posterEasyFragment2 != null) {
            posterEasyFragment2.updateDeviceStatus();
        }
    }

    private void checkCamera() {
        if (App.isNonCannon && App.isNonCamera) {
            MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_DISCONNECT, ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_DEVICE_CONNECT, ExifInterface.GPS_MEASUREMENT_2D);
        }
    }

    private boolean isPaymentFragment() {
        int i = this.currentFragmentIndex;
        return i == 2 || i == 32;
    }

    private void showAlertDialog(String str, String str2) {
        if (SpManager.getInstance().getIgnorePrinterWarning()) {
            return;
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null) {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this).setCancelable(true).setTitle(str).setMessage(str2).setPositiveButton(getString(C1910R.string.f2413ok), new DialogInterface.OnClickListener() {
                @Override
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.m953xf2cc76ef(dialogInterface, i);
                }
            }).create();
            this.alertDialog = alertDialogCreate;
            alertDialogCreate.requestWindowFeature(1);
            this.alertDialog.show();
            return;
        }
        if (alertDialog.isShowing()) {
            return;
        }
        this.alertDialog.setMessage(str2);
        this.alertDialog.show();
    }

    void m953xf2cc76ef(DialogInterface dialogInterface, int i) {
        this.alertDialog.dismiss();
    }
}
