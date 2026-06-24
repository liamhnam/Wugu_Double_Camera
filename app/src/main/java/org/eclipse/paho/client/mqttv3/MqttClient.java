package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttClient implements IMqttClient {
    protected MqttAsyncClient aClient;
    protected long timeToWait;

    public MqttClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this.aClient = null;
        this.timeToWait = -1L;
        this.aClient = new MqttAsyncClient(str, str2, mqttClientPersistence);
    }

    @Override
    public void connect() throws MqttException {
        connect(new MqttConnectOptions());
    }

    @Override
    public void connect(MqttConnectOptions mqttConnectOptions) throws MqttException {
        this.aClient.connect(mqttConnectOptions, null, null).waitForCompletion(getTimeToWait());
    }

    @Override
    public IMqttToken connectWithResult(MqttConnectOptions mqttConnectOptions) throws MqttException {
        IMqttToken iMqttTokenConnect = this.aClient.connect(mqttConnectOptions, null, null);
        iMqttTokenConnect.waitForCompletion(getTimeToWait());
        return iMqttTokenConnect;
    }

    @Override
    public void disconnect() throws MqttException {
        this.aClient.disconnect().waitForCompletion();
    }

    @Override
    public void disconnect(long j) throws MqttException {
        this.aClient.disconnect(j, null, null).waitForCompletion();
    }

    @Override
    public void disconnectForcibly() throws MqttException {
        this.aClient.disconnectForcibly();
    }

    @Override
    public void disconnectForcibly(long j) throws MqttException {
        this.aClient.disconnectForcibly(j);
    }

    @Override
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.aClient.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.aClient.disconnectForcibly(j, j2, z);
    }

    @Override
    public void subscribe(String str) throws MqttException {
        subscribe(new String[]{str}, new int[]{1});
    }

    @Override
    public void subscribe(String[] strArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr);
    }

    @Override
    public void subscribe(String str, int i) throws MqttException {
        subscribe(new String[]{str}, new int[]{i});
    }

    @Override
    public void subscribe(String[] strArr, int[] iArr) throws MqttException {
        IMqttToken iMqttTokenSubscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
        iMqttTokenSubscribe.waitForCompletion(getTimeToWait());
        int[] grantedQos = iMqttTokenSubscribe.getGrantedQos();
        for (int i = 0; i < grantedQos.length; i++) {
            iArr[i] = grantedQos[i];
        }
        if (grantedQos.length == 1 && iArr[0] == 128) {
            throw new MqttException(128);
        }
    }

    @Override
    public void subscribe(String str, IMqttMessageListener iMqttMessageListener) throws MqttException {
        subscribe(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public void subscribe(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        subscribe(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override
    public void subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        subscribe(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public void subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        subscribe(strArr, iArr);
        for (int i = 0; i < strArr.length; i++) {
            this.aClient.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
    }

    @Override
    public IMqttToken subscribeWithResponse(String str) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{1});
    }

    @Override
    public IMqttToken subscribeWithResponse(String str, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{1}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public IMqttToken subscribeWithResponse(String str, int i) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{i});
    }

    @Override
    public IMqttToken subscribeWithResponse(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribeWithResponse(new String[]{str}, new int[]{i}, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public IMqttToken subscribeWithResponse(String[] strArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr);
    }

    @Override
    public IMqttToken subscribeWithResponse(String[] strArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = 1;
        }
        return subscribeWithResponse(strArr, iArr, iMqttMessageListenerArr);
    }

    @Override
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr) throws MqttException {
        IMqttToken iMqttTokenSubscribe = this.aClient.subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
        iMqttTokenSubscribe.waitForCompletion(getTimeToWait());
        return iMqttTokenSubscribe;
    }

    @Override
    public IMqttToken subscribeWithResponse(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        IMqttToken iMqttTokenSubscribeWithResponse = subscribeWithResponse(strArr, iArr);
        for (int i = 0; i < strArr.length; i++) {
            this.aClient.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
        return iMqttTokenSubscribeWithResponse;
    }

    @Override
    public void unsubscribe(String str) throws MqttException {
        unsubscribe(new String[]{str});
    }

    @Override
    public void unsubscribe(String[] strArr) throws MqttException {
        this.aClient.unsubscribe(strArr, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    @Override
    public void publish(String str, byte[] bArr, int i, boolean z) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        publish(str, mqttMessage);
    }

    @Override
    public void publish(String str, MqttMessage mqttMessage) throws MqttException {
        this.aClient.publish(str, mqttMessage, (Object) null, (IMqttActionListener) null).waitForCompletion(getTimeToWait());
    }

    public void setTimeToWait(long j) throws IllegalArgumentException {
        if (j < -1) {
            throw new IllegalArgumentException();
        }
        this.timeToWait = j;
    }

    public long getTimeToWait() {
        return this.timeToWait;
    }

    @Override
    public void close() throws MqttException {
        this.aClient.close();
    }

    @Override
    public String getClientId() {
        return this.aClient.getClientId();
    }

    @Override
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.aClient.getPendingDeliveryTokens();
    }

    @Override
    public String getServerURI() {
        return this.aClient.getServerURI();
    }

    public String getCurrentServerURI() {
        return this.aClient.getCurrentServerURI();
    }

    @Override
    public MqttTopic getTopic(String str) {
        return this.aClient.getTopic(str);
    }

    @Override
    public boolean isConnected() {
        return this.aClient.isConnected();
    }

    @Override
    public void setCallback(MqttCallback mqttCallback) {
        this.aClient.setCallback(mqttCallback);
    }

    @Override
    public void setManualAcks(boolean z) {
        this.aClient.setManualAcks(z);
    }

    @Override
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.aClient.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return MqttAsyncClient.generateClientId();
    }

    public void reconnect() throws MqttException {
        this.aClient.reconnect();
    }

    public Debug getDebug() {
        return this.aClient.getDebug();
    }
}
