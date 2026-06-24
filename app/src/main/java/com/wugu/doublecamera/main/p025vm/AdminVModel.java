package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.AppUtil;
import com.wugu.doublecamera.utils.StringUtil;

public class AdminVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> pwdVerifyLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();

    public LiveData<Integer> getPwdVerifyLD() {
        return this.pwdVerifyLD;
    }

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
                int i2 = (int) (j / 1000);
                if (i2 == 0) {
                    return;
                }
                AdminVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                AdminVModel.this.countdownLD.postValue(0);
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

    public void checkPwd(String str) {
        if (TextUtils.isEmpty(str)) {
            this.pwdVerifyLD.postValue(0);
        } else {
            checkPwdLocal(str);
        }
    }

    private void checkPwdLocal(String str) {
        if (StringUtil.getSha256(str, AppConfig.PWD_SALT).equals(SpManager.getInstance().getAdminPwd())) {
            this.pwdVerifyLD.postValue(1);
        } else {
            this.pwdVerifyLD.postValue(0);
        }
    }

    public void powerOff() {
        AppUtil.powerOff();
    }

    public void reboot() {
        AppUtil.reboot();
    }
}
