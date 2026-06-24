package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.utils.OrderFileUtil;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class BurstSelectVModel extends ViewModel {
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private CountDownTimer countdownTimer;

    public LiveData<Integer> getCountdownLD() {
        return this.countdownLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 950L) {
            @Override
            public void onTick(long j) {
                int i2 = (int) (j / 1000);
                if (i2 == 0) {
                    return;
                }
                BurstSelectVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                BurstSelectVModel.this.countdownLD.postValue(0);
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

    public void uploadNegPhotos(List<String> list) {
        ArrayList arrayList = new ArrayList();
        OrderFileUtil.appendOrderFileNames(list);
        String str = App.getInstance().getFilesDir().getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR;
        for (String str2 : list) {
            if (TextUtils.isEmpty(str2)) {
                return;
            } else {
                arrayList.add(str + str2);
            }
        }
        OrderFileUtil.uploadOrderFilePaths(arrayList, null);
    }
}
