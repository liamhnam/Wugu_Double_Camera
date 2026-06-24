package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttDisconnect extends MqttWireMessage {
    public static final String KEY = "Disc";

    @Override
    public String getKey() {
        return "Disc";
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

    public MqttDisconnect() {
        super((byte) 14);
    }

    public MqttDisconnect(byte b, byte[] bArr) throws IOException {
        super((byte) 14);
    }
}
