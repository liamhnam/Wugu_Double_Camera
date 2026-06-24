package com.wugu.doublecamera.device.camera.camerausb.canon1500d;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.text.TextUtils;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.C1910R;
import com.wugu.doublecamera.custom.AutoSizeGLSurface;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.camera.camerausb.AUsbCamera;
import com.wugu.doublecamera.device.camera.camerausb.CameraConnector;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.listener.ICameraListener;
import com.wugu.doublecamera.listener.ICaptureListener;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.doublecamera.widget.ToastHelper;
import com.wugu.facebeauty.ITakePhotoListener;
import com.wugu.facebeauty.SurfaceRender;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class CameraCanon1500D extends AUsbCamera implements ICameraListener, ITakePhotoListener {
    private int bitmapHeight;
    private final IBitmapListener bitmapListener;
    private int bitmapWidth;
    private final ICaptureListener captureListener;
    private Timer captureTimer;
    private int effectType;
    private String effectUrl;
    private final AutoSizeGLSurface glSurfaceView;
    private boolean isCapture;
    private boolean isPreCapture;
    private boolean isReconnect;
    private boolean isRenderOk;
    private Bitmap lastBitmap;
    private int lastBitmapHeight;
    private int lastBitmapWidth;
    private int newHeight;
    private int newWidth;
    private int newX;
    private int newY;
    private BitmapFactory.Options options;
    private final Map<Integer, Integer> ptpProperties;
    private Bitmap recBitmap;
    private final SurfaceRender surfaceRender;
    private int viewHeight;
    private int viewWidth;

    public CameraCanon1500D(Activity activity, AutoSizeGLSurface autoSizeGLSurface, BeautifyItem beautifyItem, ICaptureListener iCaptureListener, IBitmapListener iBitmapListener) {
        super(activity);
        this.ptpProperties = new HashMap();
        this.recBitmap = null;
        this.lastBitmap = null;
        this.options = null;
        this.captureListener = iCaptureListener;
        this.bitmapListener = iBitmapListener;
        this.glSurfaceView = autoSizeGLSurface;
        this.surfaceRender = new SurfaceRender(autoSizeGLSurface, BitmapFactory.decodeResource(activity.getResources(), C1910R.mipmap.bg_black), beautifyItem, SpManager.getInstance().getCameraMirror(), SpManager.getInstance().getVideoZoom(), AppConfig.TEMP_DIR, ThreadHelper.getInstance().getThreadPool(), this);
        init(false);
    }

    public CameraCanon1500D(Activity activity) {
        super(activity);
        this.ptpProperties = new HashMap();
        this.recBitmap = null;
        this.lastBitmap = null;
        this.options = null;
        this.captureListener = null;
        this.bitmapListener = null;
        this.glSurfaceView = null;
        this.surfaceRender = null;
        CameraConnector.getInstance().openDevice();
        ThreadClock.sleep(500L);
        CameraConnector.getInstance().setCameraStep(12);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getOpenSessionCommand());
        ThreadClock.sleep(500L);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getEventDataCommand());
        ThreadClock.sleep(500L);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getRemoteShootingModeCommand(0));
        ThreadClock.sleep(500L);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getEventNotifyModeCommand(0));
        ThreadClock.sleep(500L);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getCloseSessionCommand());
        ThreadClock.sleep(500L);
    }

    @Override
    public void init(boolean z) {
        this.isReconnect = z;
        if (!z) {
            this.viewWidth = 0;
            this.viewHeight = 0;
            this.isCapture = false;
        }
        CameraConnector.getInstance().setCameraListener(this);
        CameraConnector.getInstance().openDevice();
    }

    @Override
    public void setEffectItem(int i, String str) {
        this.effectType = i;
        this.effectUrl = str;
        this.surfaceRender.setEffect(i, str);
    }

    @Override
    public void setBitmapRect(int i, int i2) {
        this.viewWidth = i;
        this.viewHeight = i2;
        this.bitmapWidth = 0;
        this.bitmapHeight = 0;
        this.lastBitmapWidth = 0;
        this.lastBitmapHeight = 0;
        this.glSurfaceView.setAspectRation(i, i2);
        this.surfaceRender.setFrameWH(i, i2);
    }

    @Override
    public void setFrameWH(int i, int i2) {
        this.surfaceRender.setFrameWH(i, i2);
    }

    @Override
    public void setBeautyFace(BeautifyItem beautifyItem) {
        this.surfaceRender.setBeautyLevel(beautifyItem);
    }

    @Override
    public void preview() {
        this.isCapture = false;
        cancelCaptureTimer();
        IBitmapListener iBitmapListener = this.bitmapListener;
        if (iBitmapListener != null) {
            iBitmapListener.onBitmapResult(null, 0);
        }
        this.surfaceRender.onResume();
        if (!TextUtils.isEmpty(this.effectUrl)) {
            this.surfaceRender.setEffect(this.effectType, this.effectUrl);
        }
        CameraConnector.getInstance().setCameraStep(8);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getLiveViewCommand());
    }

    @Override
    public void capture() {
        LoggerUtil.m1182e("Cannon", "capture");
        this.isCapture = true;
        startCaptureTimer();
        if (CameraConnector.getInstance().getCameraStep() != 8) {
            CameraConnector.getInstance().clearCommandQueue();
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getTakePictureCommand());
        }
        this.surfaceRender.endRecord();
    }

    @Override
    public void close() {
        this.surfaceRender.onPause();
        this.surfaceRender.onDestroy();
        cancelCaptureTimer();
        CameraConnector.getInstance().clearCommandQueue();
        ThreadClock.sleep(100L);
        CameraConnector.getInstance().setCameraStep(11);
        CameraConnector.getInstance().setCameraListener(null);
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getEventNotifyModeCommand(0));
    }

    @Override
    public void connectedDevice(boolean z) {
        CameraConnector.getInstance().setCameraStep(1);
        if (z) {
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getDeviceInfoCommand());
        } else {
            LoggerUtil.m1182e("Cannon", "connectedDevice fail ");
            AppUtil.runOnUI(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m876x6bf2c6fb();
                }
            });
        }
    }

    void m876x6bf2c6fb() {
        ToastHelper.getInstance().showToast(this.mActivity.getString(C1910R.string.open_device_fail));
    }

    @Override
    public void cameraEvent(int i) {
        CameraConnector.getInstance().setCameraStep(i);
        if (i == 2) {
            if (!this.isReconnect) {
                this.isRenderOk = false;
            }
            CameraConnector.getInstance().clearCommandQueue();
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getOpenSessionCommand());
            return;
        }
        if (i == 3) {
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getRemoteShootingModeCommand(1));
            return;
        }
        if (i == 4) {
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getEventNotifyModeCommand(1));
            return;
        }
        if (i == 5) {
            Integer num = this.ptpProperties.get(Integer.valueOf(Constants.PROPERTY_EOS_EVF_MODE));
            if (num == null) {
                LoggerUtil.m1182e("Cannon", "INIT_SET_PROP_EVF onError ");
                return;
            } else if (num.intValue() != 1) {
                CameraConnector.getInstance().sendData(CommandHelper.getInstance().getDeviceSetPropCommand());
                CameraConnector.getInstance().sendData(CommandHelper.getInstance().getDeviceSetPropData(Constants.PROPERTY_EOS_EVF_MODE, 1));
                return;
            } else {
                CameraConnector.getInstance().setCameraStep(6);
                sendOutputCommand(true);
                return;
            }
        }
        if (i == 6) {
            sendOutputCommand(true);
            return;
        }
        if (i != 8) {
            return;
        }
        if (this.isCapture) {
            this.isCapture = false;
            LoggerUtil.m1181d("canon", "READY_GET_LIVE_VIEW isCapture ");
            CameraConnector.getInstance().clearCommandQueue();
            ThreadClock.sleep(200L);
            CameraConnector.getInstance().setCameraStep(9);
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getTakePictureCommand());
            return;
        }
        if (CameraConnector.getInstance().getCameraStep() == 8 && this.isRenderOk) {
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getLiveViewCommand());
        }
    }

    @Override
    public void cameraEvent(int i, int... iArr) {
        CameraConnector.getInstance().setCameraStep(i);
        if (i == 10 && iArr.length == 1) {
            cancelCaptureTimer();
            CameraConnector.getInstance().sendData(CommandHelper.getInstance().getGetObjectCommand(iArr[0]));
        }
    }

    @Override
    public void startRecord() {
        this.surfaceRender.startRecord();
    }

    private void sendOutputCommand(boolean z) {
        Integer numValueOf;
        Integer num = this.ptpProperties.get(Integer.valueOf(Constants.PROPERTY_EOS_EVF_OUTPUT_DEVICE));
        if (num == null) {
            LoggerUtil.m1182e("Cannon", "sendOutputCommand onError ");
            return;
        }
        if (z) {
            numValueOf = Integer.valueOf(num.intValue() | 3);
        } else {
            numValueOf = Integer.valueOf(num.intValue() & (-4));
        }
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getDeviceSetPropCommand());
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getDeviceSetPropData(Constants.PROPERTY_EOS_EVF_OUTPUT_DEVICE, numValueOf.intValue()));
    }

    @Override
    public void onPropertyChanged(int i, int i2) {
        this.ptpProperties.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override
    public void recBitmap(byte[] bArr, int i, int i2, boolean z) {
        Bitmap bitmap;
        if (z) {
            LoggerUtil.m1181d("canon", "rec photo bitmap");
            this.isPreCapture = true;
            this.surfaceRender.takePhoto();
            ThreadClock.sleep(50L);
            cancelCaptureTimer();
        }
        if (!z && this.options == null && (bitmap = this.recBitmap) != null && !bitmap.isRecycled()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            this.options = options;
            options.inSampleSize = 1;
            BitmapFactory.Options options2 = this.options;
            Bitmap bitmap2 = this.recBitmap;
            options2.inBitmap = bitmap2.copy(bitmap2.getConfig(), true);
            this.options.inTempStorage = new byte[32768];
        }
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, i, i2, this.options);
        this.recBitmap = bitmapDecodeByteArray;
        Bitmap bitmapCutBitmap = cutBitmap(bitmapDecodeByteArray);
        Bitmap bitmap3 = this.lastBitmap;
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            this.lastBitmap.recycle();
        }
        if (bitmapCutBitmap != null && !bitmapCutBitmap.isRecycled()) {
            this.lastBitmap = bitmapCutBitmap.copy(bitmapCutBitmap.getConfig(), false);
        }
        this.surfaceRender.renderBitmap(bitmapCutBitmap);
        this.recBitmap.recycle();
        this.recBitmap = null;
        if (z) {
            ThreadClock.sleep(200L);
            if (!TextUtils.isEmpty(this.effectUrl)) {
                this.surfaceRender.setEffect(this.effectType, this.effectUrl);
            }
            ThreadClock.sleep(800L);
            this.isPreCapture = false;
            this.surfaceRender.takePhoto();
        }
    }

    @Override
    public void onError(String str) {
        LoggerUtil.m1182e("Cannon", "onError " + str);
    }

    private Bitmap cutBitmap(Bitmap bitmap) {
        this.bitmapHeight = bitmap.getHeight();
        int width = bitmap.getWidth();
        this.bitmapWidth = width;
        if (this.lastBitmapWidth != width || this.lastBitmapHeight != this.bitmapHeight || this.newHeight == 0 || this.newWidth == 0) {
            int i = this.bitmapHeight;
            this.lastBitmapHeight = i;
            this.lastBitmapWidth = width;
            float f = this.viewWidth / this.viewHeight;
            if (width / i > f) {
                this.newHeight = i;
                int i2 = (int) (i * f);
                this.newWidth = i2;
                this.newY = 0;
                this.newX = (width - i2) / 2;
            } else {
                int i3 = (int) (width / f);
                this.newHeight = i3;
                this.newWidth = width;
                this.newY = (i - i3) / 2;
                this.newX = 0;
            }
        }
        if (bitmap.isRecycled() || this.newHeight == 0 || this.newWidth == 0) {
            return bitmap;
        }
        if (SpManager.getInstance().getCameraMirror()) {
            Matrix matrix = new Matrix();
            matrix.setScale(-1.0f, 1.0f);
            matrix.postTranslate(this.newWidth, 0.0f);
            return Bitmap.createBitmap(bitmap, this.newX, this.newY, this.newWidth, this.newHeight, matrix, false);
        }
        return Bitmap.createBitmap(bitmap, this.newX, this.newY, this.newWidth, this.newHeight);
    }

    @Override
    public void onSurfaceCreated() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m877x988656d1();
            }
        });
    }

    void m877x988656d1() {
        while (CameraConnector.getInstance().getCameraStep() != 8) {
            ThreadClock.sleep(10L);
        }
        this.isRenderOk = true;
        CameraConnector.getInstance().sendData(CommandHelper.getInstance().getLiveViewCommand());
    }

    @Override
    public void captureFinish(Bitmap bitmap) {
        LoggerUtil.m1182e("Cannon", "captureFinish isPreCapture " + this.isPreCapture);
        cancelCaptureTimer();
        IBitmapListener iBitmapListener = this.bitmapListener;
        if (iBitmapListener != null) {
            iBitmapListener.onBitmapResult(bitmap, 0);
        }
        AppUtil.runOnUI(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m875xa26f32f7();
            }
        });
    }

    void m875xa26f32f7() {
        ICaptureListener iCaptureListener;
        if (this.isPreCapture || (iCaptureListener = this.captureListener) == null) {
            return;
        }
        iCaptureListener.finishCapture(true);
        this.surfaceRender.onPause();
    }

    @Override
    public void recordFinish(String str) {
        ICaptureListener iCaptureListener = this.captureListener;
        if (iCaptureListener != null) {
            iCaptureListener.recordEnd(str);
        }
    }

    private void startCaptureTimer() {
        Timer timer = this.captureTimer;
        if (timer != null) {
            timer.cancel();
        }
        Timer timer2 = new Timer();
        this.captureTimer = timer2;
        timer2.schedule(new C19291(), 8000L);
    }

    class C19291 extends TimerTask {
        C19291() {
        }

        @Override
        public void run() {
            LoggerUtil.m1182e("Cannon", "capture timeout " + CameraCanon1500D.this.lastBitmap);
            CameraCanon1500D.this.cancelCaptureTimer();
            if (CameraCanon1500D.this.lastBitmap == null) {
                CameraCanon1500D.this.surfaceRender.takePhoto();
                ThreadClock.sleep(3000L);
            }
            if (CameraCanon1500D.this.captureListener != null) {
                AppUtil.runOnUI(new Runnable() {
                    @Override
                    public final void run() {
                        this.f$0.m878x13556814();
                    }
                });
            }
        }

        void m878x13556814() {
            if (CameraCanon1500D.this.bitmapListener != null) {
                CameraCanon1500D.this.bitmapListener.onBitmapResult(CameraCanon1500D.this.lastBitmap, 0);
            }
            CameraConnector.getInstance().clearCommandQueue();
            CameraCanon1500D.this.captureListener.finishCapture(true);
            CameraCanon1500D.this.surfaceRender.onPause();
        }
    }

    public void cancelCaptureTimer() {
        Timer timer = this.captureTimer;
        if (timer != null) {
            timer.cancel();
        }
    }
}
