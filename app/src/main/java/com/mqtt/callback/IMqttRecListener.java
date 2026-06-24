package com.mqtt.callback;

public interface IMqttRecListener {
    void connectionLost(Throwable th);

    void eventCall(String str);

    void onFail();

    void onSuccess();
}
