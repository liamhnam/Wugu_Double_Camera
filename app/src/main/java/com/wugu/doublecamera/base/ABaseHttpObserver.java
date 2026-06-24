package com.wugu.doublecamera.base;

import com.wugu.doublecamera.enums.MqttCmdEnum;
import com.wugu.doublecamera.network.MqttHelper;
import com.wugu.doublecamera.utils.LoggerUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class ABaseHttpObserver<T> implements Observer<T> {
    @Override
    public void onComplete() {
    }

    @Override
    public void onNext(T t) {
    }

    @Override
    public void onSubscribe(Disposable disposable) {
    }

    @Override
    public void onError(Throwable th) {
        LoggerUtil.m1182e("Http", "onError " + th);
        MqttHelper.getInstance().mqttPublish(MqttCmdEnum.C2S_HEART, "http error " + th.getMessage());
    }
}
