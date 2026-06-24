package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.NotStickMutableLiveData;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;

public class PrintingVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final NotStickMutableLiveData<String> photoQrcodeLD = new NotStickMutableLiveData<>();

    public LiveData<Integer> getCountdownLD() {
        return this.countdownLD;
    }

    public LiveData<String> getPhotoQrcodeLD() {
        return this.photoQrcodeLD;
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
                PrintingVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                PrintingVModel.this.countdownLD.postValue(0);
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

    public int getPrintTime(int i) {
        return PrinterHelper.getInstance().getPrintTime() * i;
    }

    public void printAndFinishOrder(String str, int i, FrameInfo frameInfo) {
        String printerColorParam = frameInfo.getPrinterColorParam();
        if (!TextUtils.isEmpty(printerColorParam)) {
            LoggerUtil.m1181d("PrintingVModel", "colorParam " + printerColorParam);
            PrinterHelper.getInstance().setPrinterColorParam(printerColorParam);
        } else {
            PrinterHelper.getInstance().setPrinterColorParam(null);
        }
        int printerRemainSheet = PrinterHelper.getInstance().getPrinterRemainSheet();
        LoggerUtil.m1181d("PrintingVModel", "printerPrint remainSheets " + printerRemainSheet + " printCount " + i + " frameName " + frameInfo.getFrameName());
        if (App.mSystemMode == 2) {
            PrinterHelper.getInstance().addPrintTask(str, i, App.mPosterBlackPrint);
        } else {
            PrinterHelper.getInstance().addPrintTask(str, i, frameInfo.isNeedCut());
        }
        App.mPosterBlackPrint = false;
        OrderManager.finishOrder(printerRemainSheet - i, SpManager.getInstance().getRemainPrintCount() - i);
    }

    public void uploadImage(String str) {
        LoggerUtil.m1181d("PrintingVModel", "uploadImage " + str);
        OrderFileUtil.appendOrderFileName(StringUtil.getFileNameFromPath(str));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        OrderFileUtil.uploadOrderFilePaths(arrayList, new IIntListener() {
            @Override
            public final void setValue(int i) {
                this.f$0.m1163xb18589e7(i);
            }
        });
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1164x6afd1786();
            }
        });
    }

    void m1163xb18589e7(int i) {
        if (i == 1) {
            this.photoQrcodeLD.postValue(OrderFileUtil.getOrderFileQrCodeStr());
        } else {
            LoggerUtil.m1182e("PrintingVModel", "uploadImage fail end");
        }
    }

    void m1164x6afd1786() {
        ThreadClock.sleep(6000L);
        this.photoQrcodeLD.postValue(OrderFileUtil.getOrderFileQrCodeStr());
    }
}
