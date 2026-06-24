package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.FileUtil;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrintUsbVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<List<String>> usbFileListLD = new MutableLiveData<>();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<List<String>> getUsbFileListLD() {
        return this.usbFileListLD;
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
                PrintUsbVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                PrintUsbVModel.this.countdownLD.postValue(0);
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

    public void queryUsbPhotoList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1162x64c9570f();
            }
        });
    }

    void m1162x64c9570f() {
        File[] fileArrListFiles;
        String usbPath = AppUtil.getUsbPath();
        if (usbPath == null || usbPath.isEmpty() || (fileArrListFiles = new File(usbPath).listFiles()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            if ((file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) && file.isFile()) {
                arrayList.add(file.getAbsolutePath());
            }
        }
        this.usbFileListLD.postValue(arrayList);
    }

    public void printUsbPhoto(final String str, final int i, final boolean z) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                PrintUsbVModel.lambda$printUsbPhoto$1(str, z, i);
            }
        });
    }

    static void lambda$printUsbPhoto$1(String str, boolean z, int i) {
        if (z) {
            FileUtil.genBlackPdfFile(str);
            str = str.replace(".jpg", ".pdf").replace(".png", ".pdf");
        }
        PrinterHelper.getInstance().addPrintTask(str, i, false);
    }
}
