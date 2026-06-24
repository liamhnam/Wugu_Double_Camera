package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttInputStream;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubAck;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubComp;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPubRec;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

public class CommsReceiver implements Runnable {
    private static final String CLASS_NAME;
    static Class class$0;
    private static final Logger log;
    private ClientComms clientComms;
    private ClientState clientState;

    private MqttInputStream f3776in;
    private volatile boolean receiving;
    private CommsTokenStore tokenStore;
    private boolean running = false;
    private Object lifecycle = new Object();
    private Thread recThread = null;

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.internal.CommsReceiver");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
    }

    public CommsReceiver(ClientComms clientComms, ClientState clientState, CommsTokenStore commsTokenStore, InputStream inputStream) {
        this.clientState = null;
        this.clientComms = null;
        this.tokenStore = null;
        this.f3776in = new MqttInputStream(clientState, inputStream);
        this.clientComms = clientComms;
        this.clientState = clientState;
        this.tokenStore = commsTokenStore;
        log.setResourceName(clientComms.getClient().getClientId());
    }

    public void start(String str) {
        log.fine(CLASS_NAME, "start", "855");
        synchronized (this.lifecycle) {
            if (!this.running) {
                this.running = true;
                Thread thread = new Thread(this, str);
                this.recThread = thread;
                thread.start();
            }
        }
    }

    public void stop() {
        synchronized (this.lifecycle) {
            log.fine(CLASS_NAME, "stop", "850");
            if (this.running) {
                this.running = false;
                this.receiving = false;
                if (!Thread.currentThread().equals(this.recThread)) {
                    try {
                        this.recThread.join(1500L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        this.recThread = null;
        log.fine(CLASS_NAME, "stop", "851");
    }

    @Override
    public void run() {
        MqttToken token = null;
        while (this.running && this.f3776in != null) {
            try {
                try {
                    Logger logger = log;
                    String str = CLASS_NAME;
                    logger.fine(str, "run", "852");
                    this.receiving = this.f3776in.available() > 0;
                    MqttWireMessage mqttWireMessage = this.f3776in.readMqttWireMessage();
                    this.receiving = false;
                    if (mqttWireMessage instanceof MqttAck) {
                        token = this.tokenStore.getToken(mqttWireMessage);
                        if (token != null) {
                            synchronized (token) {
                                this.clientState.notifyReceivedAck((MqttAck) mqttWireMessage);
                            }
                        } else {
                            if (!(mqttWireMessage instanceof MqttPubRec) && !(mqttWireMessage instanceof MqttPubComp) && !(mqttWireMessage instanceof MqttPubAck)) {
                                throw new MqttException(6);
                            }
                            logger.fine(str, "run", "857");
                        }
                    } else if (mqttWireMessage != null) {
                        this.clientState.notifyReceivedMsg(mqttWireMessage);
                    }
                } catch (IOException e) {
                    log.fine(CLASS_NAME, "run", "853");
                    this.running = false;
                    if (!this.clientComms.isDisconnecting()) {
                        this.clientComms.shutdownConnection(token, new MqttException(32109, e));
                    }
                } catch (MqttException e2) {
                    log.fine(CLASS_NAME, "run", "856", null, e2);
                    this.running = false;
                    this.clientComms.shutdownConnection(token, e2);
                }
            } finally {
                this.receiving = false;
            }
        }
        log.fine(CLASS_NAME, "run", "854");
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isReceiving() {
        return this.receiving;
    }
}
