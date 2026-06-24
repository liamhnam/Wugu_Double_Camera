package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.FrameThemeInfo;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.database.dbSheet.FrameThemeEntity;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChooseFrameVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private int currentCountdown;
    private final MutableLiveData<List<FrameThemeInfo>> frameThemeListLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();

    public LiveData<List<FrameThemeInfo>> getFrameThemeListLD() {
        return this.frameThemeListLD;
    }

    public LiveData<Integer> getCountDownLD() {
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
                ChooseFrameVModel.this.currentCountdown = (int) (j / 1000);
                if (ChooseFrameVModel.this.currentCountdown == 0) {
                    return;
                }
                ChooseFrameVModel.this.countdownLD.postValue(Integer.valueOf(ChooseFrameVModel.this.currentCountdown));
            }

            @Override
            public void onFinish() {
                ChooseFrameVModel.this.countdownLD.postValue(0);
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
                this.f$0.m1152xba36eb32();
            }
        });
    }

    void m1152xba36eb32() {
        this.frameThemeListLD.postValue(getFrameListFromDb());
    }

    private List<FrameThemeInfo> getFrameListFromDb() {
        int i;
        ArrayList arrayList = new ArrayList();
        int i2 = 213;
        if (App.mChooseFragmentType == 28) {
            i = 3;
        } else if (App.mChooseFragmentType == 210) {
            i = 2;
        } else if (App.mChooseFragmentType == 212) {
            i = 4;
        } else {
            i = App.mChooseFragmentType == 213 ? 6 : 0;
        }
        int replaceBgPrice = SpManager.getInstance().getReplaceBgPrice();
        int replaceBgAddPrice = SpManager.getInstance().getReplaceBgAddPrice();
        for (FrameThemeEntity frameThemeEntity : DbHelper.getInstance().getAllFrameThemes()) {
            if (frameThemeEntity.getIsEnable()) {
                FrameThemeInfo frameThemeInfo = new FrameThemeInfo();
                frameThemeInfo.setBackgroundPath(frameThemeEntity.getBackgroundPath());
                frameThemeInfo.setThemeKey(frameThemeEntity.getThemeKey());
                frameThemeInfo.setThemeName(frameThemeEntity.getButtonPath());
                frameThemeInfo.setEnable(frameThemeEntity.getIsEnable());
                frameThemeInfo.setRemark(frameThemeEntity.getRemark());
                frameThemeInfo.setLimitTime(frameThemeEntity.getLimitTime());
                ArrayList arrayList2 = new ArrayList();
                boolean z = false;
                for (FrameEntity frameEntity : DbHelper.getInstance().getFramesByTheme(frameThemeEntity.getThemeKey())) {
                    if (!isErrorFrameType(frameEntity, i)) {
                        if (App.mChooseFragmentType == i2) {
                            frameEntity.setPrice(replaceBgPrice);
                            frameEntity.setOPrice(replaceBgPrice);
                            frameEntity.setAddOPrice(replaceBgAddPrice);
                            frameEntity.setAddPrice(replaceBgAddPrice);
                        }
                        if (frameEntity.getFrameType() == 5) {
                            if (!z) {
                                z = true;
                            }
                        }
                        ArrayList arrayList3 = new ArrayList();
                        Iterator<FramePhotoEntity> it = DbHelper.getInstance().getFramePhotos(frameEntity.getFrameKey()).iterator();
                        while (it.hasNext()) {
                            arrayList3.add(new FramePhotoInfo(it.next()));
                        }
                        arrayList2.add(new FrameInfo(frameEntity, arrayList3));
                        i2 = 213;
                    }
                }
                if (!arrayList2.isEmpty()) {
                    frameThemeInfo.setFrameInfoList(arrayList2);
                    arrayList.add(frameThemeInfo);
                }
                i2 = 213;
            }
        }
        return arrayList;
    }

    private boolean isErrorFrameType(FrameEntity frameEntity, int i) {
        if (!frameEntity.getIsEnable() || TextUtils.isEmpty(frameEntity.getFrameLocalPath())) {
            return true;
        }
        int frameType = frameEntity.getFrameType();
        if (i != 0) {
            return frameEntity.getFrameType() != i;
        }
        UiPosition uiPosition = App.mUiPosMap.get(15);
        UiPosition uiPosition2 = App.mUiPosMap.get(16);
        boolean z = uiPosition != null && uiPosition.isVisible;
        boolean z2 = uiPosition2 != null && uiPosition2.isVisible;
        if (frameType == 0) {
            return false;
        }
        if (frameType != 3 || z) {
            return frameType != 5 || z2;
        }
        return false;
    }
}
