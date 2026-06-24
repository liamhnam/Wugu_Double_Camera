package com.wugu.doublecamera.main.poster_sys.p024vm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.stickerview.StickerView;
import java.io.File;
import java.util.List;

public class PosterPrePrintVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int printerRemainSheets;
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
                PosterPrePrintVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                PosterPrePrintVModel.this.countdownLD.postValue(0);
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
    }

    public void preGetPrinterSheets() {
        this.printerRemainSheets = PrinterHelper.getInstance().getPrinterRemainSheet();
    }

    public int getRemainSheets() {
        int i = this.printerRemainSheets;
        if (i <= 0) {
            return 20;
        }
        return i;
    }

    public void saveFinalPhoto(final FrameInfo frameInfo, final int i, final int i2, final List<StickerView> list, final Bitmap bitmap, final int i3, final boolean z) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() throws Throwable {
                this.f$0.m1146x7bdfb99e(frameInfo, i3, i, i2, list, bitmap, z);
            }
        });
    }

    void m1146x7bdfb99e(FrameInfo frameInfo, int i, int i2, int i3, List list, Bitmap bitmap, boolean z) throws Throwable {
        LoggerUtil.m1183i("PosterPrePrintVM", "startPrint frameName = " + frameInfo.getFrameName() + ", printCount = " + i);
        String strCompoundBitmap = compoundBitmap(frameInfo, i2, i3, list, bitmap, z);
        ThreadClock.sleep(500L);
        this.frameSaveLD.postValue(strCompoundBitmap);
    }

    private String compoundBitmap(FrameInfo frameInfo, int i, int i2, List<StickerView> list, Bitmap bitmap, boolean z) throws Throwable {
        App app = App.getInstance();
        File filesDir = app.getFilesDir();
        LoggerUtil.m1181d("PosterPrePrintVM", "compoundBitmap framePath = " + frameInfo.getFramePath());
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(app, frameInfo.getFramePath());
        int width = localBitmap.getWidth();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, localBitmap.getHeight(), localBitmap.getConfig());
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (frameInfo.getFrameType() != 1 && frameInfo.getFrameType() != 3) {
            canvas.drawBitmap(localBitmap, 0.0f, 0.0f, (Paint) null);
        }
        localBitmap.recycle();
        float f = i;
        float f2 = width;
        printStickers(list, canvas, f / f2);
        if (bitmap != null) {
            printSign(bitmap, canvas, bitmap.getWidth() / f2);
            bitmap.recycle();
        }
        String frameName = AppConfig.getFrameName();
        File file = new File(filesDir, frameName);
        if (z) {
            Bitmap grayBitmap = BitmapUtil.getGrayBitmap(bitmapCreateBitmap);
            FileUtil.saveBitmap(grayBitmap, file);
            grayBitmap.recycle();
        } else {
            FileUtil.saveBitmap(bitmapCreateBitmap, file);
        }
        bitmapCreateBitmap.recycle();
        LoggerUtil.m1181d("PosterPrePrintVM", "compoundBitmap final photo = " + frameName);
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
