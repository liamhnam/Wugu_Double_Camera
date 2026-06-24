package com.mqtt;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mqtt.callback.IMQTTCallback;
import com.mqtt.params.MqttOption;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MQTTManager {
    private static final String TAG = "MQTTManager";
    private IMQTTCallback iMQTTCallback;
    private final Context mContext;
    private MqttAndroidClient mqttAndroidClient;
    private MqttConnectOptions mqttConnectOptions;
    private String mqttHost;
    private String mqttPassword;
    private String mqttUsername;
    private int[] qos;
    private ScheduledExecutorService reconnectPool;
    private String[] topic;
    private final IMqttActionListener connectListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken iMqttToken) {
            MQTTManager.this.m702v("connect-onSuccess");
            if (MQTTManager.this.iMQTTCallback != null) {
                MQTTManager.this.iMQTTCallback.onSuccess(iMqttToken);
            }
            if (MQTTManager.this.topic == null || MQTTManager.this.qos == null) {
                MQTTManager.this.subscribeDefaultTopic();
            } else {
                MQTTManager mQTTManager = MQTTManager.this;
                mQTTManager.subscribeToTopic(mQTTManager.topic, MQTTManager.this.qos);
            }
        }

        @Override
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            MQTTManager.this.m702v("connect-onFailure->" + th);
            if (MQTTManager.this.iMQTTCallback != null) {
                MQTTManager.this.iMQTTCallback.onFailure(iMqttToken, th);
            }
        }
    };
    private final MqttCallback messageCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable th) {
            if (th == null) {
                return;
            }
            MQTTManager.this.m702v("close-connectionLost->" + th);
            if (MQTTManager.this.iMQTTCallback != null) {
                MQTTManager.this.iMQTTCallback.connectionLost(th);
            }
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            if (iMqttDeliveryToken == null) {
                return;
            }
            try {
                MQTTManager.this.m702v("deliveryComplete-token->" + iMqttDeliveryToken.isComplete());
                MQTTManager.this.m702v("deliveryComplete-message->" + iMqttDeliveryToken.getMessage().toString());
            } catch (MqttException e) {
                e.printStackTrace();
            }
            if (MQTTManager.this.iMQTTCallback != null) {
                MQTTManager.this.iMQTTCallback.deliveryComplete(iMqttDeliveryToken);
            }
        }

        @Override
        public void messageArrived(String str, MqttMessage mqttMessage) {
            if (mqttMessage == null || mqttMessage.isRetained()) {
                return;
            }
            MQTTManager.this.m702v("messageArrived-topic->" + str);
            MQTTManager.this.m702v("messageArrived-Qos->" + mqttMessage.getQos());
            MQTTManager.this.m702v("messageArrived-message->".concat(new String(mqttMessage.getPayload())));
            if (MQTTManager.this.iMQTTCallback != null) {
                MQTTManager.this.iMQTTCallback.messageArrived(str, mqttMessage);
            }
        }
    };

    public MQTTManager(Context context, IMQTTCallback iMQTTCallback) {
        this.mContext = context;
        this.iMQTTCallback = iMQTTCallback;
    }

    public MQTTManager(Context context, IMQTTCallback iMQTTCallback, String[] strArr, int[] iArr) {
        this.mContext = context;
        this.iMQTTCallback = iMQTTCallback;
        this.topic = strArr;
        this.qos = iArr;
    }

    public void setMqttOptions(String str, String str2, String str3) {
        this.mqttHost = str;
        this.mqttUsername = str2;
        this.mqttPassword = str3;
    }

    public void buildClient(String str) {
        disConnect();
        buildMQTTClient(str);
    }

    private void buildMQTTClient(String str) {
        String str2 = !TextUtils.isEmpty(this.mqttUsername) ? this.mqttUsername : MqttOption.MQTT_USER;
        String str3 = !TextUtils.isEmpty(this.mqttPassword) ? this.mqttPassword : MqttOption.MQTT_PASSWORD;
        MqttAndroidClient mqttAndroidClient = new MqttAndroidClient(this.mContext, this.mqttHost, str);
        this.mqttAndroidClient = mqttAndroidClient;
        mqttAndroidClient.setCallback(this.messageCallback);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        this.mqttConnectOptions = mqttConnectOptions;
        mqttConnectOptions.setConnectionTimeout(20);
        this.mqttConnectOptions.setKeepAliveInterval(0);
        this.mqttConnectOptions.setAutomaticReconnect(false);
        this.mqttConnectOptions.setCleanSession(false);
        try {
            this.mqttConnectOptions.setUserName(str2);
            this.mqttConnectOptions.setPassword(str3.toCharArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        connect();
    }

    private synchronized void startReconnectTask() {
        if (this.reconnectPool != null) {
            return;
        }
        ScheduledExecutorService scheduledExecutorServiceNewScheduledThreadPool = Executors.newScheduledThreadPool(1);
        this.reconnectPool = scheduledExecutorServiceNewScheduledThreadPool;
        scheduledExecutorServiceNewScheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                MQTTManager.this.connect();
            }
        }, 0L, 5000L, TimeUnit.MILLISECONDS);
    }

    private synchronized void closeReconnectTask() {
        ScheduledExecutorService scheduledExecutorService = this.reconnectPool;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.reconnectPool = null;
        }
    }

    public synchronized void connect() {
        if (!this.mqttAndroidClient.isConnected()) {
            try {
                this.mqttAndroidClient.connect(this.mqttConnectOptions, null, this.connectListener);
                m702v("connectMQTT->" + this.mqttAndroidClient.getClientId());
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void disConnect() {
        MqttAndroidClient mqttAndroidClient = this.mqttAndroidClient;
        if (mqttAndroidClient != null) {
            try {
                mqttAndroidClient.unregisterResources();
                this.mqttAndroidClient.disconnect();
                m702v("closeMQTT->" + this.mqttAndroidClient.getClientId());
                this.mqttAndroidClient = null;
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public void publish(String str, final String str2) {
        try {
            if (this.mqttAndroidClient == null) {
                return;
            }
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setPayload(str2.getBytes());
            mqttMessage.setRetained(false);
            mqttMessage.setQos(1);
            this.mqttAndroidClient.publish(str, mqttMessage, (Object) null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    MQTTManager.this.m702v("sendMQTT-success->" + str2);
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MQTTManager.this.m702v("sendMQTT-failed->" + str2);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String str, int i, byte[] bArr) {
        publish(str, i, bArr, false);
    }

    public void publish(String str, int i, byte[] bArr, boolean z) {
        publish(str, i, bArr, z, null);
    }

    public void publish(String str, int i, byte[] bArr, boolean z, IMqttActionListener iMqttActionListener) {
        MqttAndroidClient mqttAndroidClient = this.mqttAndroidClient;
        if (mqttAndroidClient == null) {
            return;
        }
        try {
            mqttAndroidClient.publish(str, bArr, i, z, null, iMqttActionListener);
        } catch (Exception e) {
            if (iMqttActionListener != null) {
                iMqttActionListener.onFailure(null, e.getCause());
            }
            m702v("publish-->>Exception:" + e.toString());
        }
    }

    private void unSubscribe(String[] strArr) {
        try {
            this.mqttAndroidClient.unsubscribe(strArr, (Object) null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    MQTTManager.this.m702v("unsubscribe->success");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MQTTManager.this.m702v("unsubscribe-failed->" + th);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe(String[] strArr, int[] iArr) {
        try {
            this.mqttAndroidClient.subscribe(strArr, iArr, (Object) null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    MQTTManager.this.m702v("subscribe->success");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MQTTManager.this.m702v("subscribe-failed->" + th);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        try {
            MqttAndroidClient mqttAndroidClient = this.mqttAndroidClient;
            if (mqttAndroidClient != null) {
                return mqttAndroidClient.isConnected();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public void subscribeDefaultTopic() {
        String[] strArr = {MqttOption.DEFAULT_TOPIC};
        unSubscribe(strArr);
        subscribe(strArr, new int[]{0});
    }

    public void subscribeToTopic(String[] strArr, int[] iArr) {
        unSubscribe(strArr);
        subscribe(strArr, iArr);
    }

    public void m702v(String str) {
        Log.v(TAG, str);
    }
}
