package org.eclipse.paho.client.mqttv3;

import org.eclipse.paho.client.mqttv3.internal.Token;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

public class MqttToken implements IMqttToken {
    public Token internalTok;

    public MqttToken() {
        this.internalTok = null;
    }

    public MqttToken(String str) {
        this.internalTok = null;
        this.internalTok = new Token(str);
    }

    @Override
    public MqttException getException() {
        return this.internalTok.getException();
    }

    @Override
    public boolean isComplete() {
        return this.internalTok.isComplete();
    }

    @Override
    public void setActionCallback(IMqttActionListener iMqttActionListener) {
        this.internalTok.setActionCallback(iMqttActionListener);
    }

    @Override
    public IMqttActionListener getActionCallback() {
        return this.internalTok.getActionCallback();
    }

    @Override
    public void waitForCompletion() throws MqttException {
        this.internalTok.waitForCompletion(-1L);
    }

    @Override
    public void waitForCompletion(long j) throws MqttException {
        this.internalTok.waitForCompletion(j);
    }

    @Override
    public IMqttAsyncClient getClient() {
        return this.internalTok.getClient();
    }

    @Override
    public String[] getTopics() {
        return this.internalTok.getTopics();
    }

    @Override
    public Object getUserContext() {
        return this.internalTok.getUserContext();
    }

    @Override
    public void setUserContext(Object obj) {
        this.internalTok.setUserContext(obj);
    }

    @Override
    public int getMessageId() {
        return this.internalTok.getMessageID();
    }

    @Override
    public int[] getGrantedQos() {
        return this.internalTok.getGrantedQos();
    }

    @Override
    public boolean getSessionPresent() {
        return this.internalTok.getSessionPresent();
    }

    @Override
    public MqttWireMessage getResponse() {
        return this.internalTok.getResponse();
    }
}
