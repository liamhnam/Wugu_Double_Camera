package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttConnack extends MqttAck {
    public static final String KEY = "Con";
    private int returnCode;
    private boolean sessionPresent;

    @Override
    public String getKey() {
        return "Con";
    }

    @Override
    protected byte[] getVariableHeader() throws MqttException {
        return new byte[0];
    }

    @Override
    public boolean isMessageIdRequired() {
        return false;
    }

    public MqttConnack(byte b, byte[] bArr) throws IOException {
        super((byte) 2);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.sessionPresent = (dataInputStream.readUnsignedByte() & 1) == 1;
        this.returnCode = dataInputStream.readUnsignedByte();
        dataInputStream.close();
    }

    public int getReturnCode() {
        return this.returnCode;
    }

    @Override
    public String toString() {
        return new StringBuffer(String.valueOf(super.toString())).append(" session present:").append(this.sessionPresent).append(" return code: ").append(this.returnCode).toString();
    }

    public boolean getSessionPresent() {
        return this.sessionPresent;
    }
}
