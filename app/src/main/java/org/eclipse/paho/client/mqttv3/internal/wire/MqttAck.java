package org.eclipse.paho.client.mqttv3.internal.wire;

public abstract class MqttAck extends MqttWireMessage {
    @Override
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    public MqttAck(byte b) {
        super(b);
    }

    @Override
    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" msgId ").append(this.msgId).toString();
    }
}
