package com.wugu.doublecamera.main.p025vm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.stickerview.StickerView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PrePrintVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<String> frameSaveLD = new MutableLiveData<>();

    public LiveData<Integer> getCountdownLD() {
        return this.countdownLD;
    }

    public LiveData<String> getFrameSaveLD() {
        return this.frameSaveLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                int i2 = (int) (j / 1000);
                if (i2 == 0) {
                    return;
                }
                PrePrintVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                PrePrintVModel.this.countdownLD.postValue(0);
            }
        };
        this.countdownTimer = countDownTimer2;
        countDownTimer2.start();
    }

    public void cancelCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        this.countdownLD.postValue(-1);
    }

    public int getRemainSheets() {
        int printerRemainSheet = PrinterHelper.getInstance().getPrinterRemainSheet();
        if (printerRemainSheet <= 0) {
            return 20;
        }
        return printerRemainSheet;
    }

    public void uploadNegPhotos(List<String> list, boolean z, boolean z2) {
        LoggerUtil.m1181d("PrePrintVModel", "uploadNegPhotos isCutFrame " + z + ", " + z2);
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        int i = z ? 2 : 1;
        if (z2) {
            i *= 2;
        }
        for (int i2 = 0; i2 < list.size() && (i <= 1 || i2 != list.size() / i); i2++) {
            arrayList2.add(list.get(i2));
        }
        OrderFileUtil.appendOrderFileNames(arrayList2);
        String str = App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        for (String str2 : arrayList2) {
            if (TextUtils.isEmpty(str2)) {
                return;
            } else {
                arrayList.add(str + str2);
            }
        }
        OrderFileUtil.uploadOrderFilePaths(arrayList, null);
    }

    public void saveFinalPhoto(final FrameInfo frameInfo, final int i, final int i2, final List<String> list, final List<StickerView> list2, final Bitmap bitmap, final int i3) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m1161x6d18c103(frameInfo, i3, i, i2, list, list2, bitmap);
            }
        });
    }

    void m1161x6d18c103(FrameInfo frameInfo, int i, int i2, int i3, List list, List list2, Bitmap bitmap) throws Throwable {
        LoggerUtil.m1183i("PrePrintVModel", "saveFinalPhoto frameName = " + frameInfo.getFrameName() + ", printCount = " + i);
        String strCompoundBitmap = compoundBitmap(frameInfo, i2, i3, list, list2, bitmap);
        ThreadClock.sleep(500L);
        this.frameSaveLD.postValue(strCompoundBitmap);
    }

    private String compoundBitmap(FrameInfo frameInfo, int i, int i2, List<String> list, List<StickerView> list2, Bitmap bitmap) throws Throwable {
        App app = App.getInstance();
        File filesDir = app.getFilesDir();
        String framePath = frameInfo.getFramePath();
        LoggerUtil.m1181d("PrePrintVModel", "getFrameType " + frameInfo.getFrameType());
        if (frameInfo.getFrameType() == 6 && !TextUtils.isEmpty(OrderManager.replaceModelFinalPicPath)) {
            framePath = OrderManager.replaceModelFinalPicPath;
        }
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(app, framePath);
        LoggerUtil.m1181d("PrePrintVModel", "framePath " + framePath);
        int width = localBitmap.getWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, localBitmap.getHeight(), localBitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (frameInfo.getFrameType() != 6 && list != null && list.size() >= frameInfo.getPhotoInfoList().size()) {
            for (int i3 = 0; i3 < frameInfo.getPhotoInfoList().size(); i3++) {
                FramePhotoInfo framePhotoInfo = frameInfo.getPhotoInfoList().get(i3);
                LoggerUtil.m1181d("PrePrintVModel", "compoundBitmap photoFileList " + list.get(i3));
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(new File(filesDir, list.get(i3)).getAbsolutePath());
                if (bitmapDecodeFile != null) {
                    canvas.drawBitmap(bitmapDecodeFile, (Rect) null, new Rect(framePhotoInfo.getLocationX(), framePhotoInfo.getLocationY(), framePhotoInfo.getLocationX() + framePhotoInfo.getWidth(), framePhotoInfo.getLocationY() + framePhotoInfo.getHeight()), (Paint) null);
                    bitmapDecodeFile.recycle();
                }
            }
        }
        if (frameInfo.getFrameType() != 1 && frameInfo.getFrameType() != 3) {
            canvas.drawBitmap(localBitmap, 0.0f, 0.0f, (Paint) null);
        }
        localBitmap.recycle();
        float f = width;
        printStickers(list2, canvas, i / f);
        if (bitmap != null) {
            printSign(bitmap, canvas, bitmap.getWidth() / f);
            bitmap.recycle();
        }
        String frameName = AppConfig.getFrameName();
        FileUtil.saveBitmap(bitmapCreateBitmap, new File(filesDir, frameName), 100);
        bitmapCreateBitmap.recycle();
        return frameName;
    }

    private void printStickers(List<StickerView> list, Canvas canvas, float f) {
        int wHRatio;
        int wHRatio2;
        int i;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (StickerView stickerView : list) {
            if (!stickerView.getBitmap().isRecycled()) {
                Rect viewRect = stickerView.getViewRect();
                int[] padding = stickerView.getPadding();
                int iWidth = (viewRect.width() - padding[0]) - padding[2];
                int iHeight = (viewRect.height() - padding[1]) - padding[3];
                Rect rect = new Rect();
                if (stickerView.isBitmapText()) {
                    i = ((iWidth / 60) - 1) * 13;
                    wHRatio2 = (int) (iHeight / f);
                    wHRatio = ((int) (iWidth / f)) - i;
                } else {
                    if (stickerView.isBaseHeight()) {
                        wHRatio2 = (int) (iHeight / f);
                        wHRatio = (int) (wHRatio2 * stickerView.getWHRatio());
                    } else {
                        wHRatio = (int) (iWidth / f);
                        wHRatio2 = (int) (wHRatio / stickerView.getWHRatio());
                    }
                    i = 0;
                }
                rect.left = ((int) (((double) ((viewRect.left / f) + (padding[0] / f))) + 0.5d)) + (i / 2);
                rect.top = (int) (((double) ((viewRect.top / f) + (padding[1] / f))) + 0.5d);
                rect.right = rect.left + wHRatio;
                rect.bottom = rect.top + wHRatio2;
                canvas.save();
                canvas.rotate(stickerView.getRotation(), rect.centerX(), rect.centerY());
                canvas.drawBitmap(stickerView.getBitmap(), (Rect) null, rect, (Paint) null);
                canvas.restore();
                stickerView.getBitmap().recycle();
            }
        }
    }

    private void printSign(Bitmap bitmap, Canvas canvas, float f) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() / f), (int) (bitmap.getHeight() / f), true);
        canvas.drawBitmap(bitmapCreateScaledBitmap, 0.0f, 0.0f, (Paint) null);
        bitmapCreateScaledBitmap.recycle();
    }
}
