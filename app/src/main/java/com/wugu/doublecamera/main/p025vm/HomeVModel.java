package com.wugu.doublecamera.main.p025vm;

import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.DefaultResConfig;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.UiPosition;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.DeviceAllConfigDto;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.dbSheet.UiPositionEntity;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.service.PictureIcDownUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import io.reactivex.schedulers.Schedulers;

public class HomeVModel extends ViewModel {
    private CountDownTimer countdownTimer;
    private boolean isInit = false;
    private final MutableLiveData<Integer> uiDbOkLD = new MutableLiveData<>();
    private final MutableLiveData<Integer> countdownLD = new MutableLiveData<>();

    public LiveData<Integer> getUiDbOkLD() {
        return this.uiDbOkLD;
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
                HomeVModel.this.countdownLD.postValue(Integer.valueOf((int) (j / 1000)));
            }

            @Override
            public void onFinish() {
                HomeVModel.this.countdownLD.postValue(0);
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

    public UiPosition getBtnUiInfo() {
        if (DbHelper.getInstance().isUiPositionEmpty()) {
            return new UiPosition(750, 700, AppConfig.BASE_FOLDER + "default_ui/1-待机页面按钮.png");
        }
        return new UiPosition(DbHelper.getInstance().getUiPositionEntity(11));
    }

    public UiPosition getBgUiInfo() {
        UiPosition uiPosition;
        if (DbHelper.getInstance().isUiPositionEmpty()) {
            uiPosition = new UiPosition(0, 0, AppConfig.BASE_FOLDER + "default_ui/1-待机页面背景.png");
        } else {
            uiPosition = new UiPosition(DbHelper.getInstance().getUiPositionEntity(12));
        }
        initUiPosition();
        return uiPosition;
    }

    private void initUiPosition() {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1708lambda$initUiPosition$0$comwugudoublecameramainvmHomeVModel();
            }
        });
    }

    void m1708lambda$initUiPosition$0$comwugudoublecameramainvmHomeVModel() {
        if (DbHelper.getInstance().isUiPositionEmpty()) {
            for (UiPositionEntity uiPositionEntity : DefaultResConfig.getDefaultUiPosition()) {
                if (uiPositionEntity.getIndex() == 111 || uiPositionEntity.getIndex() == 18 || uiPositionEntity.getIndex() == 16 || uiPositionEntity.getIndex() == 17 || uiPositionEntity.getIndex() == 113 || uiPositionEntity.getIndex() == 15) {
                    uiPositionEntity.setIsVisible(false);
                }
                DbHelper.getInstance().insertUiPosition(uiPositionEntity);
            }
            ThreadClock.sleep(2000L);
        }
        if (this.isInit) {
            return;
        }
        this.isInit = true;
        syncUiPosition();
        updateUiMap();
    }

    public void updateUiMap() {
        App.mUiPosMap.clear();
        for (UiPositionEntity uiPositionEntity : DbHelper.getInstance().getAllUiPositionEntity()) {
            App.mUiPosMap.put(Integer.valueOf(uiPositionEntity.getIndex()), new UiPosition(uiPositionEntity));
        }
        ThreadClock.sleep(500L);
        this.uiDbOkLD.postValue(1);
    }

    private void syncUiPosition() {
        if (App.mIsNetAvailable) {
            ThreadClock.sleep(6000L);
            HttpManager.getInstance().mHttpApi.getDeviceAllConfig().subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<DeviceAllConfigDto>>() {
                @Override
                public void onNext(BaseDto<DeviceAllConfigDto> baseDto) {
                    if (baseDto.errCode != 0 || baseDto.data == null || TextUtils.isEmpty(baseDto.data.cameraInfo)) {
                        return;
                    }
                    PictureIcDownUtil.downUiRes(baseDto.data.reserve1, null);
                    ThreadClock.sleep(30000L);
                    HomeVModel.this.updateUiMap();
                }
            });
        }
    }
}
