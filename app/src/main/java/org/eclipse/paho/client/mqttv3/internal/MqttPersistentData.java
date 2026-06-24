package org.eclipse.paho.client.mqttv3.internal;

import org.eclipse.paho.client.mqttv3.MqttPersistable;

public class MqttPersistentData implements MqttPersistable {
    private int hLength;
    private int hOffset;
    private byte[] header;
    private String key;
    private int pLength;
    private int pOffset;
    private byte[] payload;

    public MqttPersistentData(String str, byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        this.key = str;
        this.header = bArr;
        this.hOffset = i;
        this.hLength = i2;
        this.payload = bArr2;
        this.pOffset = i3;
        this.pLength = i4;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public byte[] getHeaderBytes() {
        return this.header;
    }

    @Override
    public int getHeaderLength() {
        return this.hLength;
    }

    @Override
    public int getHeaderOffset() {
        return this.hOffset;
    }

    @Override
    public byte[] getPayloadBytes() {
        return this.payload;
    }

    @Override
    public int getPayloadLength() {
        if (this.payload == null) {
            return 0;
        }
        return this.pLength;
    }

    @Override
    public int getPayloadOffset() {
        return this.pOffset;
    }
}
