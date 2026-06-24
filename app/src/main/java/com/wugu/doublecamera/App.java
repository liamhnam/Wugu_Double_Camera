package com.wugu.doublecamera;

import ZtlApi.ZtlManager;
import android.app.Application;
import android.os.Build;
import androidx.room.RoomDatabase;
import cc.uling.usdk.USDK;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.file.CustomProcessFileStrategy;
import com.p020hp.mobile.common.usb.Interface;
import com.p020hp.mobile.common.usb.IppUsbInterfaceMapping;
import com.p020hp.open.printsdk.CoreManager;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.enums.BoardModelEnum;
import com.wugu.doublecamera.widget.CrashHandler;
import com.wugu.doublecamera.widget.ToastHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class App extends Application {
    public static boolean isNonCamera = false;
    public static boolean isNonCannon = false;
    public static String mAppId = "";
    public static String mAppMqttTopicServer = null;
    public static String mBoardModel = "rk3399-all";
    public static String mCPUSerial = "";
    public static int mChooseFragmentType = 0;
    public static String mFrameVideoPath = null;
    public static boolean mIsDownApk = false;
    public static volatile boolean mIsIdle = true;
    public static int mIsInitBeautySuccess = 0;
    public static volatile boolean mIsNetAvailable = false;
    public static int mSystemMode = 0;
    public static String mUploadKey = null;
    public static int selectedCameraIndex = 2;
    public static final Map<Integer, UiPosition> mUiPosMap = new HashMap();
    public static volatile AtomicInteger mDownCount = new AtomicInteger(0);
    public static volatile AtomicInteger mSoundDownCount = new AtomicInteger(0);
    public static final StringBuffer mSbAiPath = new StringBuffer();
    public static int mCertifySelectIndex = 0;
    public static int mCurrentPrintSheets = RoomDatabase.MAX_BIND_PARAMETER_CNT;
    public static boolean mPosterBlackPrint = false;
    public static int mTestCertifyBlur = 70;
    public static int mTestCertifyWhite = 7;
    private static App mInstance = null;

    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ToastHelper.getInstance().init(this);
        DbHelper.getInstance().initDatabase(this);
        CrashHandler.getInstance().init(this);
        String paymentMethod = SpManager.getInstance().getPaymentMethod();
        if (paymentMethod.contains(String.valueOf(4)) || paymentMethod.contains(String.valueOf(7))) {
            USDK.getInstance().init(this);
        }
        initBoardModel();
        if (Build.VERSION.SDK_INT >= 29) {
            OkDownload.setSingletonInstance(new OkDownload.Builder(this).processFileStrategy(new CustomProcessFileStrategy()).build());
        }
        if (SpManager.getInstance().getPrinterModel() == 6) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new Interface(1, 255, 4, 1));
            arrayList.add(new IppUsbInterfaceMapping("HP LaserJet Pro M701n", arrayList2));
            CoreManager.INSTANCE.setContext(this).setJobHistorySaveDays(15).enablePrintHistory(true).setUsbInterfaceWhiteList(arrayList);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DbHelper.getInstance().closeDatabase();
    }

    private void initBoardModel() {
        String str = Build.MODEL;
        if (str.equals(BoardModelEnum.DING_CHANG_3568)) {
            mBoardModel = BoardModelEnum.DING_CHANG_3568;
            ZtlManager.GetInstance().setContext(this);
        } else if (str.equals(BoardModelEnum.XIANG_CHENG_3399)) {
            mBoardModel = BoardModelEnum.XIANG_CHENG_3399;
        }
    }
}
