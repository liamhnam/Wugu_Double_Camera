package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPingReq extends MqttWireMessage {
    public static final String KEY = "Ping";

    @Override
    public String getKey() {
        return "Ping";
    }

    @Override
    protected byte getMessageInfo() {
        return (byte) 0;
    }

    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override
    public boolean isMessageIdRequired() {
        return false;
    }

    public MqttPingReq() {
        super((byte) 12);
    }

    public MqttPingReq(byte b, byte[] bArr) throws IOException {
        super((byte) 12);
    }
}
