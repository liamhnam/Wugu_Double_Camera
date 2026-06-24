package com.wugu.doublecamera.main.p025vm;

import android.content.Context;
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
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.BitmapUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import com.wugu.facebeauty.bean.BeautifyItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpHost;

public class CertifyPhotoVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private Bitmap[] outputBitmaps;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> saveImageFinishLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> cutoutFinishLD = new MutableLiveData<>();
    private int retryCount = 0;
    private final List<String[]> finalFileList = new ArrayList();
    private final List<String> imageList = new ArrayList();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<Integer> getSaveImageFinishLD() {
        return this.saveImageFinishLD;
    }

    public LiveData<Integer> getCutoutFinishLD() {
        return this.cutoutFinishLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                CertifyPhotoVModel.this.currentCountdown = (int) ((j + 200) / 1000);
                if (CertifyPhotoVModel.this.currentCountdown == 0) {
                    return;
                }
                CertifyPhotoVModel.this.countdownLD.postValue(Integer.valueOf(CertifyPhotoVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                CertifyPhotoVModel.this.countdownLD.postValue(0);
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

    public void pauseCountdown() {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void resumeCountdown() {
        startCountdown(this.currentCountdown);
    }

    public BeautifyItem getBeautyLevel() {
        BeautifyItem beautifyItem = new BeautifyItem();
        beautifyItem.filterParams.filterName = "origin";
        beautifyItem.filterParams.filterIntensity = 0.8f;
        beautifyItem.beautyParams.blur = App.mTestCertifyBlur / 10.0f;
        beautifyItem.beautyParams.white = App.mTestCertifyWhite / 10.0f;
        beautifyItem.beautyParams.eyeBig = 0.5f;
        beautifyItem.beautyParams.eyeCircle = 0.3f;
        beautifyItem.beautyParams.faceThin = 0.5f;
        beautifyItem.beautyParams.noseThin = 0.1f;
        beautifyItem.beautyParams.eyeBright = 0.4f;
        beautifyItem.beautyParams.tooth = 0.4f;
        return beautifyItem;
    }

    public List<String> getImageList() {
        return this.imageList;
    }

    public void saveImage(final Context context, final Bitmap bitmap, final Bitmap[] bitmapArr, final int i) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1147x415eae08(context, bitmap, bitmapArr, i);
            }
        });
    }

    void m1147x415eae08(Context context, Bitmap bitmap, Bitmap[] bitmapArr, int i) {
        File filesDir = context.getFilesDir();
        String subFrameName = AppConfig.getSubFrameName();
        OrderFileUtil.appendOrderFileName(subFrameName);
        OrderFileUtil.uploadOrderBitmap(bitmap, subFrameName, null);
        int length = bitmapArr.length;
        Bitmap[] bitmapArr2 = new Bitmap[length];
        for (int i2 = 0; i2 < bitmapArr.length; i2++) {
            Bitmap bitmap2 = bitmapArr[i2];
            bitmapArr2[i2] = bitmap2.copy(bitmap2.getConfig(), true);
        }
        for (int i3 = 0; i3 < length; i3++) {
            Bitmap bitmap3 = bitmapArr2[i3];
            String str = "plate_" + AppUtil.getSystemTime("MMdd_HHmmss_") + StringUtil.getRandomNumber(4) + ".jpg";
            if (FileUtil.saveBitmap(bitmap3, new File(filesDir, str))) {
                this.imageList.add(str);
            }
        }
        for (int i4 = 0; i4 < this.finalFileList.size(); i4++) {
            for (int i5 = 0; i5 < this.finalFileList.get(i4).length; i5++) {
                if (i4 == i) {
                    this.imageList.add(this.finalFileList.get(i4)[i5]);
                } else {
                    FileUtil.deleteFileFolder(this.finalFileList.get(i4)[i5]);
                }
            }
        }
        for (int i6 = 0; i6 < length; i6++) {
            bitmapArr2[i6].recycle();
        }
        this.finalFileList.clear();
        this.saveImageFinishLD.postValue(1);
    }

    public void cutPictureByNet(Bitmap bitmap, String[] strArr, Context context, FrameInfo frameInfo) throws Throwable {
        LoggerUtil.m1181d("fragment", "cutPictureByNet");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int length = strArr.length;
        Bitmap[] bitmapArr = new Bitmap[length];
        for (int i = 0; i < length; i++) {
            bitmapArr[i] = BitmapUtil.getColorBitmap(strArr[i], width, height);
        }
        File file = new File(context.getFilesDir(), "co_" + AppUtil.getSystemTime("yyyyMMdd_HHmmss_") + StringUtil.getRandomNumber(4) + ".jpg");
        FileUtil.saveBitmap(bitmap, file);
        ThreadClock.sleep(100L);
        this.retryCount = 3;
        uploadCutPicture(context, file, bitmapArr, frameInfo);
    }

    private void uploadCutPicture(final Context context, final File file, final Bitmap[] bitmapArr, final FrameInfo frameInfo) {
        LoggerUtil.m1181d(HttpHost.DEFAULT_SCHEME_NAME, "cutPicture " + this.retryCount);
        int i = this.retryCount;
        if (i <= 0) {
            return;
        }
        this.retryCount = i - 1;
        HttpManager.getInstance().uploadCutPicture(true, file, new IBitmapListener() {
            @Override
            public final void onBitmapResult(Bitmap bitmap, int i2) {
                this.f$0.m1148xb4f4344e(context, file, bitmapArr, frameInfo, bitmap, i2);
            }
        });
    }

    void m1148xb4f4344e(Context context, File file, Bitmap[] bitmapArr, FrameInfo frameInfo, Bitmap bitmap, int i) {
        if (i == 0 || bitmap == null) {
            uploadCutPicture(context, file, bitmapArr, frameInfo);
            return;
        }
        if (i == 1) {
            this.outputBitmaps = new Bitmap[bitmapArr.length];
            for (int i2 = 0; i2 < bitmapArr.length; i2++) {
                this.outputBitmaps[i2] = BitmapUtil.combineBitmaps(bitmapArr[i2], bitmap);
                bitmapArr[i2].recycle();
            }
            bitmap.recycle();
            this.cutoutFinishLD.postValue(1);
            compoundBitmap(context, this.outputBitmaps, frameInfo);
        }
    }

    public Bitmap[] getOutputBitmaps() {
        return this.outputBitmaps;
    }

    private void compoundBitmap(Context context, Bitmap[] bitmapArr, FrameInfo frameInfo) {
        String[] strArr = new String[bitmapArr.length];
        Bitmap localBitmap = BitmapUtil.getLocalBitmap(context, frameInfo.getFramePath());
        for (int i = 0; i < bitmapArr.length; i++) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(localBitmap.getWidth(), localBitmap.getHeight(), localBitmap.getConfig());
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            canvas.drawBitmap(localBitmap, 0.0f, 0.0f, (Paint) null);
            for (int i2 = 0; i2 < frameInfo.getPhotoInfoList().size(); i2++) {
                FramePhotoInfo framePhotoInfo = frameInfo.getPhotoInfoList().get(i2);
                canvas.drawBitmap(bitmapArr[i], (Rect) null, new Rect(framePhotoInfo.getLocationX(), framePhotoInfo.getLocationY(), framePhotoInfo.getLocationX() + framePhotoInfo.getWidth(), framePhotoInfo.getLocationY() + framePhotoInfo.getHeight()), (Paint) null);
            }
            File filesDir = context.getFilesDir();
            String frameName = AppConfig.getFrameName(String.valueOf(i));
            File file = new File(filesDir, frameName);
            LoggerUtil.m1181d("file", "certify compoundBitmap fileName" + frameName);
            if (FileUtil.saveBitmap(bitmapCreateBitmap, file)) {
                strArr[i] = frameName;
                FileUtil.notifyFile(file);
            }
            bitmapCreateBitmap.recycle();
        }
        localBitmap.recycle();
        this.finalFileList.add(strArr);
    }
}
