package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class MqttOutputStream extends OutputStream {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientState clientState;
    private BufferedOutputStream out;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.wire.MqttOutputStream");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public MqttOutputStream(ClientState clientState, OutputStream outputStream) {
        this.clientState = clientState;
        this.out = new BufferedOutputStream(outputStream);
    }

    @Override
    public void close() throws IOException {
        this.out.close();
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        this.clientState.notifySentBytes(bArr.length);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        this.clientState.notifySentBytes(i2);
    }

    @Override
    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void write(MqttWireMessage mqttWireMessage) throws MqttException, IOException {
        byte[] header = mqttWireMessage.getHeader();
        byte[] payload = mqttWireMessage.getPayload();
        int i = 0;
        this.out.write(header, 0, header.length);
        this.clientState.notifySentBytes(header.length);
        while (i < payload.length) {
            int iMin = Math.min(1024, payload.length - i);
            this.out.write(payload, i, iMin);
            i += 1024;
            this.clientState.notifySentBytes(iMin);
        }
        log.fine(CLASS_NAME, "write", "500", new Object[]{mqttWireMessage});
    }
}
