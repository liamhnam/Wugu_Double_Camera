package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.bean.AiThemeItem;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChooseAiVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<List<AiThemeItem>> frameThemeListLD = new MutableLiveData<>();
    private final MutableLiveData<List<FrameInfo>> frameListLD = new MutableLiveData<>();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<List<AiThemeItem>> getFrameThemeListLD() {
        return this.frameThemeListLD;
    }

    public LiveData<List<FrameInfo>> getFrameListLD() {
        return this.frameListLD;
    }

    public void startCountdown(int i) {
        CountDownTimer countDownTimer = this.countdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(200 + (((long) i) * 1000), 1000L) {
            @Override
            public void onTick(long j) {
                ChooseAiVModel.this.currentCountdown = (int) (j / 1000);
                if (ChooseAiVModel.this.currentCountdown == 0) {
                    return;
                }
                ChooseAiVModel.this.countdownLD.postValue(Integer.valueOf(ChooseAiVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                ChooseAiVModel.this.countdownLD.postValue(0);
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

    public void queryFrameThemeList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1151x59c3d0c9();
            }
        });
    }

    void m1151x59c3d0c9() {
        List<FrameEntity> framesByType = DbHelper.getInstance().getFramesByType(1);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (FrameEntity frameEntity : framesByType) {
            String themeKey = frameEntity.getThemeKey();
            if (!arrayList.contains(themeKey)) {
                arrayList.add(themeKey);
                arrayList2.add(new AiThemeItem(frameEntity.getAiModeIds(), frameEntity.getThemeKey()));
            }
        }
        this.frameThemeListLD.postValue(arrayList2);
    }

    public void queryFrameList(final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1150x3782f86d(str);
            }
        });
    }

    void m1150x3782f86d(String str) {
        List<FrameEntity> aiFramesByThemeKey = DbHelper.getInstance().getAiFramesByThemeKey(str);
        ArrayList arrayList = new ArrayList();
        int aiPrice = SpManager.getInstance().getAiPrice();
        int aiAddPrice = SpManager.getInstance().getAiAddPrice();
        Iterator<FrameEntity> it = aiFramesByThemeKey.iterator();
        while (it.hasNext()) {
            FrameInfo frameInfo = new FrameInfo(it.next(), null);
            frameInfo.setPrice(aiPrice);
            frameInfo.setoPrice(aiPrice);
            frameInfo.setAddPrice(aiAddPrice);
            frameInfo.setAddOPrice(aiAddPrice);
            arrayList.add(frameInfo);
        }
        this.frameListLD.postValue(arrayList);
    }
}
