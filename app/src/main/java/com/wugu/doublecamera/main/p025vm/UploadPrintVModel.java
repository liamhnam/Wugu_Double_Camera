package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.PictureSelectItem;
import com.wugu.doublecamera.bean.UpFrameIcItem;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.listener.IDownFileListener;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class UploadPrintVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private boolean isAllDownDone;
    private int printerRemainSheets;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<List<PictureSelectItem>> pictureDownLD = new MutableLiveData<>();
    private final MutableLiveData<List<UpFrameIcItem>> frameIcLD = new MutableLiveData<>();
    private final MutableLiveData<String> qrcodeLD = new MutableLiveData<>();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<List<PictureSelectItem>> getPictureDownLD() {
        return this.pictureDownLD;
    }

    public LiveData<List<UpFrameIcItem>> getFrameIcLD() {
        return this.frameIcLD;
    }

    public LiveData<String> getQrcodeLD() {
        return this.qrcodeLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                UploadPrintVModel.this.currentCountdown = (int) (j / 1000);
                if (UploadPrintVModel.this.currentCountdown == 0) {
                    return;
                }
                UploadPrintVModel.this.countdownLD.postValue(Integer.valueOf(UploadPrintVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                UploadPrintVModel.this.countdownLD.postValue(0);
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
        this.currentCountdown++;
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void resumeCountdown() {
        startCountdown(this.currentCountdown);
    }

    public boolean isAllDownDone() {
        return this.isAllDownDone;
    }

    public void queryFrameIcList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1171xce30035d();
            }
        });
    }

    void m1171xce30035d() {
        ArrayList arrayList = new ArrayList();
        List<FrameEntity> framesByType = DbHelper.getInstance().getFramesByType(5);
        if (framesByType != null) {
            for (FrameEntity frameEntity : framesByType) {
                arrayList.add(new UpFrameIcItem(frameEntity.getFrameLocalPath(), getFrameInfo(frameEntity)));
            }
        }
        this.frameIcLD.postValue(arrayList);
    }

    public void createOrder(int i) {
        final MutableLiveData<String> mutableLiveData = this.qrcodeLD;
        Objects.requireNonNull(mutableLiveData);
        OrderManager.createPhotoOrder(i, false, new IStringListener() {
            @Override
            public final void setValue(String str) {
                mutableLiveData.postValue(str);
            }
        });
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

    public int getPrintTime(int i) {
        return i * PrinterHelper.getInstance().getPrintTime();
    }

    public void printerPrint(String str, int i, FrameInfo frameInfo) {
        uploadImage(str);
        String printerColorParam = frameInfo.getPrinterColorParam();
        if (!TextUtils.isEmpty(printerColorParam)) {
            LoggerUtil.m1181d("UploadPrintVModel", "colorParam " + printerColorParam);
            PrinterHelper.getInstance().setPrinterColorParam(printerColorParam);
        } else {
            PrinterHelper.getInstance().setPrinterColorParam(null);
        }
        int printerRemainSheet = PrinterHelper.getInstance().getPrinterRemainSheet();
        LoggerUtil.m1181d("UploadPrintVModel", "printerPrint remainSheets " + printerRemainSheet + " printCount " + i + " frameName " + frameInfo.getFrameName());
        if (App.mSystemMode == 2) {
            PrinterHelper.getInstance().addPrintTask(str, i, App.mPosterBlackPrint);
        } else {
            PrinterHelper.getInstance().addPrintTask(str, i, frameInfo.isNeedCut());
        }
        App.mPosterBlackPrint = false;
        OrderManager.finishOrder(printerRemainSheet - i, SpManager.getInstance().getRemainPrintCount() - i);
    }

    public void uploadImage(String str) {
        OrderFileUtil.appendOrderFileName(StringUtil.getFileNameFromPath(str));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        final String orderFileQrCodeStr = OrderFileUtil.getOrderFileQrCodeStr();
        OrderFileUtil.uploadOrderFilePaths(arrayList, new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1172xa8c24b21(orderFileQrCodeStr, i);
            }
        });
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1173xa84be522(orderFileQrCodeStr);
            }
        });
    }

    void m1172xa8c24b21(String str, int i) {
        if (i == 1) {
            this.qrcodeLD.postValue(str);
        } else {
            LoggerUtil.m1182e("UploadPrintVModel", "uploadImage fail end");
        }
    }

    void m1173xa84be522(String str) {
        ThreadClock.sleep(6000L);
        this.qrcodeLD.postValue(str);
    }

    private FrameInfo getFrameInfo(FrameEntity frameEntity) {
        if (frameEntity == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<FramePhotoEntity> framePhotos = DbHelper.getInstance().getFramePhotos(frameEntity.getFrameKey());
        if (framePhotos == null || framePhotos.isEmpty()) {
            return null;
        }
        Iterator<FramePhotoEntity> it = framePhotos.iterator();
        while (it.hasNext()) {
            arrayList.add(new FramePhotoInfo(it.next()));
        }
        return new FrameInfo(frameEntity, arrayList);
    }

    public void downloadUpPictures(final String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return;
        }
        this.isAllDownDone = false;
        queryFrameIcList();
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1170xaa3ed83b(strArr);
            }
        });
    }

    void m1170xaa3ed83b(String[] strArr) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        for (final String str : strArr) {
            OrderFileUtil.appendOrderFileName(StringUtil.getFileNameFromPath(str));
            final PictureSelectItem pictureSelectItem = new PictureSelectItem(null, false);
            copyOnWriteArrayList.add(pictureSelectItem);
            final String str2 = App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + StringUtil.getFileNameFromPath(str);
            HttpManager.getInstance().downLoadFile(AppConfig.HTTP_HOST + str, str2, new IDownFileListener() {
                @Override
                public void success() {
                    pictureSelectItem.setPhotoName(str2);
                    pictureSelectItem.process = 100;
                }

                @Override
                public void progress(int i) {
                    pictureSelectItem.process = i;
                }

                @Override
                public void fail(int i) {
                    LoggerUtil.m1182e("UploadPrintVModel", "downloadUpPictures fail " + str + ", status " + i);
                    ThreadClock.sleep(3000L);
                    HttpManager.getInstance().downLoadFile(AppConfig.HTTP_HOST + str, str2, this);
                }
            });
        }
        int i = 100;
        do {
            ThreadClock.sleep(1000L);
            i--;
            this.isAllDownDone = true;
            Iterator it = copyOnWriteArrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (((PictureSelectItem) it.next()).process != 100) {
                        this.isAllDownDone = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.pictureDownLD.postValue(copyOnWriteArrayList);
            if (this.isAllDownDone) {
                return;
            }
        } while (i >= 0);
    }
}
