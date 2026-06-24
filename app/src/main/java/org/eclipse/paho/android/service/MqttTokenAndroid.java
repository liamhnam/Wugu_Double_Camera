package org.eclipse.paho.android.service;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

class MqttTokenAndroid implements IMqttToken {
    private MqttAndroidClient client;
    private IMqttToken delegate;
    private volatile boolean isComplete;
    private volatile MqttException lastException;
    private IMqttActionListener listener;
    private MqttException pendingException;
    private String[] topics;
    private Object userContext;
    private Object waitObject;

    MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener) {
        this(mqttAndroidClient, obj, iMqttActionListener, null);
    }

    MqttTokenAndroid(MqttAndroidClient mqttAndroidClient, Object obj, IMqttActionListener iMqttActionListener, String[] strArr) {
        this.waitObject = new Object();
        this.client = mqttAndroidClient;
        this.userContext = obj;
        this.listener = iMqttActionListener;
        this.topics = strArr;
    }

    @Override
    public void waitForCompletion() throws MqttException {
        synchronized (this.waitObject) {
            try {
                this.waitObject.wait();
            } catch (InterruptedException unused) {
            }
        }
        MqttException mqttException = this.pendingException;
        if (mqttException != null) {
            throw mqttException;
        }
    }

    @Override
    public void waitForCompletion(long j) throws MqttException {
        synchronized (this.waitObject) {
            try {
                this.waitObject.wait(j);
            } catch (InterruptedException unused) {
            }
            if (!this.isComplete) {
                throw new MqttException(32000);
            }
            MqttException mqttException = this.pendingException;
            if (mqttException != null) {
                throw mqttException;
            }
        }
    }

    void notifyComplete() {
        synchronized (this.waitObject) {
            this.isComplete = true;
            this.waitObject.notifyAll();
            IMqttActionListener iMqttActionListener = this.listener;
            if (iMqttActionListener != null) {
                iMqttActionListener.onSuccess(this);
            }
        }
    }

    void notifyFailure(Throwable th) {
        synchronized (this.waitObject) {
            this.isComplete = true;
            if (th instanceof MqttException) {
                this.pendingException = (MqttException) th;
            } else {
                this.pendingException = new MqttException(th);
            }
            this.waitObject.notifyAll();
            if (th instanceof MqttException) {
                this.lastException = (MqttException) th;
            }
            IMqttActionListener iMqttActionListener = this.listener;
            if (iMqttActionListener != null) {
                iMqttActionListener.onFailure(this, th);
            }
        }
    }

    @Override
    public boolean isComplete() {
        return this.isComplete;
    }

    void setComplete(boolean z) {
        this.isComplete = z;
    }

    @Override
    public MqttException getException() {
        return this.lastException;
    }

    void setException(MqttException mqttException) {
        this.lastException = mqttException;
    }

    @Override
    public IMqttAsyncClient getClient() {
        return this.client;
    }

    @Override
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.listener = iMqttActionListener;
    }

    @Override
    public IMqttActionListener getActionCallback() {
        return this.listener;
    }

    @Override
    public String[] getTopics() {
        return this.topics;
    }

    @Override
    public void setUserContext(Object obj) {
        this.userContext = obj;
    }

    @Override
    public Object getUserContext() {
        return this.userContext;
    }

    void setDelegate(IMqttToken iMqttToken) {
        this.delegate = iMqttToken;
    }

    @Override
    public int getMessageId() {
        IMqttToken iMqttToken = this.delegate;
        if (iMqttToken != null) {
            return iMqttToken.getMessageId();
        }
        return 0;
    }

    @Override
    public MqttWireMessage getResponse() {
        return this.delegate.getResponse();
    }

    @Override
    public boolean getSessionPresent() {
        return this.delegate.getSessionPresent();
    }

    @Override
    public int[] getGrantedQos() {
        return this.delegate.getGrantedQos();
    }
}
