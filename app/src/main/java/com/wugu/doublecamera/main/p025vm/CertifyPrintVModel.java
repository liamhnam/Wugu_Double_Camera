package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.OrderManager;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CertifyPrintVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<String> photoQrcodeLD = new MutableLiveData<>();

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
                CertifyPrintVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                CertifyPrintVModel.this.countdownLD.postValue(0);
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

    private void uploadOrderFiles(List<String> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(StringUtil.getFileNameFromPath(it.next()));
        }
        OrderFileUtil.appendOrderFileNames(arrayList);
        OrderFileUtil.uploadOrderFilePaths(list, null);
    }

    public void print(final List<String> list, final Integer[] numArr) {
        if (list.size() != numArr.length) {
            return;
        }
        final String[] strArr = (String[]) list.toArray(new String[0]);
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1707lambda$print$0$comwugudoublecameramainvmCertifyPrintVModel(list, numArr, strArr);
            }
        });
    }

    void m1707lambda$print$0$comwugudoublecameramainvmCertifyPrintVModel(List list, Integer[] numArr, String[] strArr) {
        uploadOrderFiles(list);
        this.photoQrcodeLD.postValue(OrderFileUtil.getOrderFileQrCodeStr());
        int iIntValue = 0;
        for (Integer num : numArr) {
            iIntValue += num.intValue();
        }
        OrderManager.finishOrder(PrinterHelper.getInstance().getPrinterRemainSheet() - iIntValue, SpManager.getInstance().getRemainPrintCount() - iIntValue);
        for (int i = 0; i < strArr.length; i++) {
            PrinterHelper.getInstance().addPrintTask(strArr[i], numArr[i].intValue(), false);
            ThreadClock.sleep(1000L);
        }
    }
}
