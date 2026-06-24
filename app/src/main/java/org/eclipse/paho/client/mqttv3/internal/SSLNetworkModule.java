package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class SSLNetworkModule extends TCPNetworkModule {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private String[] enabledCiphers;
    private int handshakeTimeoutSecs;
    private String host;
    private int port;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public SSLNetworkModule(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        this.host = str;
        this.port = i;
        log.setResourceName(str2);
    }

    public String[] getEnabledCiphers() {
        return this.enabledCiphers;
    }

    public void setEnabledCiphers(String[] strArr) {
        this.enabledCiphers = strArr;
        if (this.socket == null || strArr == null) {
            return;
        }
        if (log.isLoggable(5)) {
            String string = "";
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    string = new StringBuffer(String.valueOf(string)).append(",").toString();
                }
                string = new StringBuffer(String.valueOf(string)).append(strArr[i]).toString();
            }
            log.fine(CLASS_NAME, "setEnabledCiphers", "260", new Object[]{string});
        }
        ((SSLSocket) this.socket).setEnabledCipherSuites(strArr);
    }

    public void setSSLhandshakeTimeout(int i) {
        super.setConnectTimeout(i);
        this.handshakeTimeoutSecs = i;
    }

    @Override
    public void start() throws MqttException, IOException {
        super.start();
        setEnabledCiphers(this.enabledCiphers);
        int soTimeout = this.socket.getSoTimeout();
        this.socket.setSoTimeout(this.handshakeTimeoutSecs * 1000);
        ((SSLSocket) this.socket).startHandshake();
        this.socket.setSoTimeout(soTimeout);
    }

    @Override
    public String getServerURI() {
        return new StringBuffer("ssl://").append(this.host).append(":").append(this.port).toString();
    }
}
