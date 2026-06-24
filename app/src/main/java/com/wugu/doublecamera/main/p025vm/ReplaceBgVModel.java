package com.wugu.doublecamera.main.p025vm;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.FileMd5Entity;
import com.wugu.doublecamera.listener.IBitmapListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class ReplaceBgVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<List<String>> cutoutNmLD = new MutableLiveData<>();
    private final MutableLiveData<List<String>> bgPathListLD = new MutableLiveData<>();
    private final List<String> nameCutList = new ArrayList();
    private final List<int[]> frameWHList = new ArrayList();

    public LiveData<List<String>> getCutoutNmLD() {
        return this.cutoutNmLD;
    }

    public LiveData<List<String>> getBgPathListLD() {
        return this.bgPathListLD;
    }

    public LiveData<Integer> getCountdownLD() {
        return this.countdownLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 980L) {
            @Override
            public void onTick(long j) {
                ReplaceBgVModel.this.currentCountdown = (int) ((j + 200) / 1000);
                if (ReplaceBgVModel.this.currentCountdown == 0) {
                    return;
                }
                ReplaceBgVModel.this.countdownLD.postValue(Integer.valueOf(ReplaceBgVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                ReplaceBgVModel.this.countdownLD.postValue(0);
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

    public void queryBgList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1165xb0eb141e();
            }
        });
    }

    void m1165xb0eb141e() {
        ThreadClock.sleep(2000L);
        ArrayList arrayList = new ArrayList();
        List<FileMd5Entity> fileMd5ByType = DbHelper.getInstance().getFileMd5ByType(8);
        if (fileMd5ByType != null && fileMd5ByType.size() > 0) {
            Iterator<FileMd5Entity> it = fileMd5ByType.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getFilePath());
            }
        }
        this.bgPathListLD.postValue(arrayList);
    }

    public void setFrameInfo(FrameInfo frameInfo) {
        if (frameInfo == null || frameInfo.getPhotoInfoList() == null || frameInfo.getPhotoInfoList().size() == 0) {
            return;
        }
        this.frameWHList.clear();
        for (FramePhotoInfo framePhotoInfo : frameInfo.getPhotoInfoList()) {
            this.frameWHList.add(new int[]{framePhotoInfo.getWidth(), framePhotoInfo.getHeight()});
        }
    }

    public void uploadAndCutNegPhotos(final String str, final List<String> list) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1166x541f9e79(list, str);
            }
        });
    }

    void m1166x541f9e79(List list, String str) {
        this.nameCutList.clear();
        ArrayList arrayList = new ArrayList();
        OrderFileUtil.appendOrderFileNames(list);
        this.nameCutList.addAll(list);
        String str2 = str + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str3 = (String) it.next();
            if (TextUtils.isEmpty(str3)) {
                return;
            }
            arrayList.add(str2 + str3);
            uploadCutPicture(str2, new File(str2 + str3));
        }
        ThreadClock.sleep(5000L);
        OrderFileUtil.uploadOrderFilePaths(arrayList, null);
    }

    private void uploadCutPicture(final String str, final File file) {
        if (file.exists()) {
            HttpManager.getInstance().uploadCutPicture(true, file, new IBitmapListener() {
                @Override
                public final void onBitmapResult(Bitmap bitmap, int i) {
                    this.f$0.m1167x76c84d0c(file, str, bitmap, i);
                }
            });
        }
    }

    void m1167x76c84d0c(File file, String str, Bitmap bitmap, int i) {
        if (i != 1 || bitmap == null) {
            return;
        }
        for (int i2 = 0; i2 < this.nameCutList.size(); i2++) {
            try {
                if (this.nameCutList.get(i2).equals(file.getName())) {
                    String str2 = file.getName().replaceFirst("[.][^.]+$", "") + "_cutout.png";
                    this.nameCutList.set(i2, str2);
                    String str3 = str + str2;
                    int[] addWidthHeight = getAddWidthHeight(i2);
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth() + addWidthHeight[0], bitmap.getHeight() + addWidthHeight[1], Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                    canvas.drawBitmap(bitmap, addWidthHeight[0] / 2.0f, addWidthHeight[1], (Paint) null);
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(str3);
                        try {
                            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream);
                            fileOutputStream.close();
                        } catch (Throwable th) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bitmap.recycle();
                    bitmapCreateBitmap.recycle();
                    this.cutoutNmLD.postValue(this.nameCutList);
                    return;
                }
            } catch (Exception unused) {
                return;
            }
        }
    }

    private int[] getAddWidthHeight(int i) {
        int[] iArr = {1000, 1000};
        if (!this.frameWHList.isEmpty() && i < this.frameWHList.size()) {
            float f = this.frameWHList.get(i)[0] / this.frameWHList.get(i)[1];
            double d = f;
            if (d > 1.5d) {
                iArr[0] = 7000;
                iArr[1] = 2000;
            } else if (f > 1.0f) {
                iArr[0] = 5000;
                iArr[1] = 1800;
            }
            if (f < 1.0f) {
                iArr[0] = 5000;
                iArr[1] = 5000;
            } else if (d < 0.5d) {
                iArr[0] = 4000;
                iArr[1] = 7000;
            }
        }
        return iArr;
    }
}
