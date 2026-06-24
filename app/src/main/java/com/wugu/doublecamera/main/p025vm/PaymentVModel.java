package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentVModel extends ViewModel {
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private CountDownTimer countdownTimer;
    private int currentCountdown;

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                PaymentVModel.this.currentCountdown = (int) (j / 1000);
                if (PaymentVModel.this.currentCountdown == 0) {
                    return;
                }
                PaymentVModel.this.countdownLD.postValue(Integer.valueOf(PaymentVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                PaymentVModel.this.countdownLD.postValue(0);
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
}
