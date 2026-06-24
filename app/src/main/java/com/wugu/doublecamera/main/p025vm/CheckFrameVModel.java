package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.database.dbSheet.FrameThemeEntity;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CheckFrameVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();
    private final MutableLiveData<List<FrameThemeInfo>> frameThemeListLD = new MutableLiveData<>();

    public LiveData<Integer> getCountDownLD() {
        return this.countdownLD;
    }

    public LiveData<List<FrameThemeInfo>> getFrameThemeListLD() {
        return this.frameThemeListLD;
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
                CheckFrameVModel.this.countdownLD.postValue(Integer.valueOf(i2));
            }

            @Override
            public void onFinish() {
                CheckFrameVModel.this.countdownLD.postValue(0);
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

    public void queryFrameThemeList() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1149xf0d8bf2f();
            }
        });
    }

    void m1149xf0d8bf2f() {
        this.frameThemeListLD.postValue(getFrameListFromDb());
    }

    private List<FrameThemeInfo> getFrameListFromDb() {
        ArrayList arrayList = new ArrayList();
        for (FrameThemeEntity frameThemeEntity : DbHelper.getInstance().getAllFrameThemes()) {
            FrameThemeInfo frameThemeInfo = new FrameThemeInfo();
            frameThemeInfo.setBackgroundPath(frameThemeEntity.getBackgroundPath());
            frameThemeInfo.setThemeKey(frameThemeEntity.getThemeKey());
            frameThemeInfo.setThemeName(frameThemeEntity.getButtonPath());
            frameThemeInfo.setEnable(frameThemeEntity.getIsEnable());
            frameThemeInfo.setRemark(frameThemeEntity.getRemark());
            frameThemeInfo.setLimitTime(frameThemeEntity.getLimitTime());
            ArrayList arrayList2 = new ArrayList();
            for (FrameEntity frameEntity : DbHelper.getInstance().getFramesByTheme(frameThemeEntity.getThemeKey())) {
                if (frameEntity.getFrameType() != 1) {
                    ArrayList arrayList3 = new ArrayList();
                    Iterator<FramePhotoEntity> it = DbHelper.getInstance().getFramePhotos(frameEntity.getFrameKey()).iterator();
                    while (it.hasNext()) {
                        arrayList3.add(new FramePhotoInfo(it.next()));
                    }
                    arrayList2.add(new FrameInfo(frameEntity, arrayList3));
                }
            }
            if (!arrayList2.isEmpty()) {
                frameThemeInfo.setFrameInfoList(arrayList2);
                arrayList.add(frameThemeInfo);
            }
        }
        return arrayList;
    }
}
