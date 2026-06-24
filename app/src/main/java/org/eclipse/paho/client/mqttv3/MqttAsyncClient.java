package org.eclipse.paho.client.mqttv3;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.ConnectActionListener;
import org.eclipse.paho.client.mqttv3.internal.DisconnectedMessageBuffer;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.internal.LocalNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.NetworkModule;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttSubscribe;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttUnsubscribe;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
import org.eclipse.paho.client.mqttv3.util.Debug;

public class MqttAsyncClient implements IMqttAsyncClient {
    private static final String CLASS_NAME;
    private static final String CLIENT_ID_PREFIX = "paho";
    private static final long DISCONNECT_TIMEOUT = 10000;
    private static final char MAX_HIGH_SURROGATE = 56319;
    private static final char MIN_HIGH_SURROGATE = 55296;
    private static final long QUIESCE_TIMEOUT = 30000;
    static Class class$0;
    private static Object clientLock;
    private static final Logger log;
    private static int reconnectDelay;
    private String clientId;
    protected ClientComms comms;
    private MqttConnectOptions connOpts;
    private MqttCallback mqttCallback;
    private MqttClientPersistence persistence;
    private Timer reconnectTimer;
    private boolean reconnecting;
    private String serverURI;
    private Hashtable topics;
    private Object userContext;

    protected static boolean Character_isHighSurrogate(char c) {
        return c >= 55296 && c <= 56319;
    }

    static {
        Class<?> cls = class$0;
        if (cls == null) {
            try {
                cls = Class.forName("org.eclipse.paho.client.mqttv3.MqttAsyncClient");
                class$0 = cls;
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }
        }
        String name = cls.getName();
        CLASS_NAME = name;
        log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, name);
        reconnectDelay = 1000;
        clientLock = new Object();
    }

    public MqttAsyncClient(String str, String str2) throws MqttException {
        this(str, str2, new MqttDefaultFilePersistence());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence) throws MqttException {
        this(str, str2, mqttClientPersistence, new TimerPingSender());
    }

    public MqttAsyncClient(String str, String str2, MqttClientPersistence mqttClientPersistence, MqttPingSender mqttPingSender) throws MqttException {
        int i = 0;
        this.reconnecting = false;
        log.setResourceName(str2);
        if (str2 == null) {
            throw new IllegalArgumentException("Null clientId");
        }
        int i2 = 0;
        while (i < str2.length() - 1) {
            if (Character_isHighSurrogate(str2.charAt(i))) {
                i++;
            }
            i2++;
            i++;
        }
        if (i2 > 65535) {
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        MqttConnectOptions.validateURI(str);
        this.serverURI = str;
        this.clientId = str2;
        this.persistence = mqttClientPersistence;
        if (mqttClientPersistence == null) {
            this.persistence = new MemoryPersistence();
        }
        log.fine(CLASS_NAME, "MqttAsyncClient", "101", new Object[]{str2, str, mqttClientPersistence});
        this.persistence.open(str2, str);
        this.comms = new ClientComms(this, this.persistence, mqttPingSender);
        this.persistence.close();
        this.topics = new Hashtable();
    }

    protected NetworkModule[] createNetworkModules(String str, MqttConnectOptions mqttConnectOptions) throws MqttException {
        log.fine(CLASS_NAME, "createNetworkModules", "116", new Object[]{str});
        String[] serverURIs = mqttConnectOptions.getServerURIs();
        if (serverURIs == null || serverURIs.length == 0) {
            serverURIs = new String[]{str};
        }
        NetworkModule[] networkModuleArr = new NetworkModule[serverURIs.length];
        for (int i = 0; i < serverURIs.length; i++) {
            networkModuleArr[i] = createNetworkModule(serverURIs[i], mqttConnectOptions);
        }
        log.fine(CLASS_NAME, "createNetworkModules", "108");
        return networkModuleArr;
    }

    private NetworkModule createNetworkModule(String str, MqttConnectOptions mqttConnectOptions) throws MqttException {
        SSLSocketFactoryFactory sSLSocketFactoryFactory;
        String[] enabledCipherSuites;
        SSLSocketFactoryFactory sSLSocketFactoryFactory2;
        String[] enabledCipherSuites2;
        Logger logger = log;
        String str2 = CLASS_NAME;
        logger.fine(str2, "createNetworkModule", "115", new Object[]{str});
        SocketFactory socketFactory = mqttConnectOptions.getSocketFactory();
        int iValidateURI = MqttConnectOptions.validateURI(str);
        try {
            URI uri = new URI(str);
            String host = uri.getHost();
            int port = uri.getPort();
            if (iValidateURI == 0) {
                if (port == -1) {
                    port = 1883;
                }
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                TCPNetworkModule tCPNetworkModule = new TCPNetworkModule(socketFactory, host, port, this.clientId);
                tCPNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return tCPNetworkModule;
            }
            if (iValidateURI == 1) {
                if (port == -1) {
                    port = 8883;
                }
                if (socketFactory == null) {
                    sSLSocketFactoryFactory = new SSLSocketFactoryFactory();
                    Properties sSLProperties = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties != null) {
                        sSLSocketFactoryFactory.initialize(sSLProperties, null);
                    }
                    socketFactory = sSLSocketFactoryFactory.createSocketFactory(null);
                } else {
                    if (!(socketFactory instanceof SSLSocketFactory)) {
                        throw ExceptionHelper.createMqttException(32105);
                    }
                    sSLSocketFactoryFactory = null;
                }
                SSLNetworkModule sSLNetworkModule = new SSLNetworkModule((SSLSocketFactory) socketFactory, host, port, this.clientId);
                sSLNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory != null && (enabledCipherSuites = sSLSocketFactoryFactory.getEnabledCipherSuites(null)) != null) {
                    sSLNetworkModule.setEnabledCiphers(enabledCipherSuites);
                }
                return sSLNetworkModule;
            }
            if (iValidateURI == 2) {
                return new LocalNetworkModule(str.substring(8));
            }
            if (iValidateURI == 3) {
                int i = port == -1 ? 80 : port;
                if (socketFactory == null) {
                    socketFactory = SocketFactory.getDefault();
                } else if (socketFactory instanceof SSLSocketFactory) {
                    throw ExceptionHelper.createMqttException(32105);
                }
                WebSocketNetworkModule webSocketNetworkModule = new WebSocketNetworkModule(socketFactory, str, host, i, this.clientId);
                webSocketNetworkModule.setConnectTimeout(mqttConnectOptions.getConnectionTimeout());
                return webSocketNetworkModule;
            }
            if (iValidateURI == 4) {
                int i2 = port == -1 ? 443 : port;
                if (socketFactory == null) {
                    sSLSocketFactoryFactory2 = new SSLSocketFactoryFactory();
                    Properties sSLProperties2 = mqttConnectOptions.getSSLProperties();
                    if (sSLProperties2 != null) {
                        sSLSocketFactoryFactory2.initialize(sSLProperties2, null);
                    }
                    socketFactory = sSLSocketFactoryFactory2.createSocketFactory(null);
                } else {
                    if (!(socketFactory instanceof SSLSocketFactory)) {
                        throw ExceptionHelper.createMqttException(32105);
                    }
                    sSLSocketFactoryFactory2 = null;
                }
                WebSocketSecureNetworkModule webSocketSecureNetworkModule = new WebSocketSecureNetworkModule((SSLSocketFactory) socketFactory, str, host, i2, this.clientId);
                webSocketSecureNetworkModule.setSSLhandshakeTimeout(mqttConnectOptions.getConnectionTimeout());
                if (sSLSocketFactoryFactory2 != null && (enabledCipherSuites2 = sSLSocketFactoryFactory2.getEnabledCipherSuites(null)) != null) {
                    webSocketSecureNetworkModule.setEnabledCiphers(enabledCipherSuites2);
                }
                return webSocketSecureNetworkModule;
            }
            logger.fine(str2, "createNetworkModule", "119", new Object[]{str});
            return null;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(new StringBuffer("Malformed URI: ").append(str).append(", ").append(e.getMessage()).toString());
        }
    }

    @Override
    public IMqttToken connect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return connect(new MqttConnectOptions(), obj, iMqttActionListener);
    }

    @Override
    public IMqttToken connect() throws MqttException {
        return connect(null, null);
    }

    @Override
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions) throws MqttException {
        return connect(mqttConnectOptions, null, null);
    }

    @Override
    public IMqttToken connect(MqttConnectOptions mqttConnectOptions, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        if (mqttConnectOptions == null) {
            mqttConnectOptions = new MqttConnectOptions();
        }
        MqttConnectOptions mqttConnectOptions2 = mqttConnectOptions;
        this.connOpts = mqttConnectOptions2;
        this.userContext = obj;
        final boolean zIsAutomaticReconnect = mqttConnectOptions2.isAutomaticReconnect();
        Logger logger = log;
        String str = CLASS_NAME;
        Object[] objArr = new Object[8];
        objArr[0] = Boolean.valueOf(mqttConnectOptions2.isCleanSession());
        objArr[1] = new Integer(mqttConnectOptions2.getConnectionTimeout());
        objArr[2] = new Integer(mqttConnectOptions2.getKeepAliveInterval());
        objArr[3] = mqttConnectOptions2.getUserName();
        objArr[4] = mqttConnectOptions2.getPassword() == null ? "[null]" : "[notnull]";
        objArr[5] = mqttConnectOptions2.getWillMessage() != null ? "[notnull]" : "[null]";
        objArr[6] = obj;
        objArr[7] = iMqttActionListener;
        logger.fine(str, MqttServiceConstants.CONNECT_ACTION, "103", objArr);
        this.comms.setNetworkModules(createNetworkModules(this.serverURI, mqttConnectOptions2));
        this.comms.setReconnectCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean z, String str2) {
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            }

            @Override
            public void messageArrived(String str2, MqttMessage mqttMessage) throws Exception {
            }

            @Override
            public void connectionLost(Throwable th) {
                if (zIsAutomaticReconnect) {
                    MqttAsyncClient.this.comms.setRestingState(true);
                    MqttAsyncClient.this.reconnecting = true;
                    MqttAsyncClient.this.startReconnectCycle();
                }
            }
        });
        MqttToken mqttToken = new MqttToken(getClientId());
        ConnectActionListener connectActionListener = new ConnectActionListener(this, this.persistence, this.comms, mqttConnectOptions2, mqttToken, obj, iMqttActionListener, this.reconnecting);
        mqttToken.setActionCallback(connectActionListener);
        mqttToken.setUserContext(this);
        MqttCallback mqttCallback = this.mqttCallback;
        if (mqttCallback instanceof MqttCallbackExtended) {
            connectActionListener.setMqttCallbackExtended((MqttCallbackExtended) mqttCallback);
        }
        this.comms.setNetworkModuleIndex(0);
        connectActionListener.connect();
        return mqttToken;
    }

    @Override
    public IMqttToken disconnect(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return disconnect(QUIESCE_TIMEOUT, obj, iMqttActionListener);
    }

    @Override
    public IMqttToken disconnect() throws MqttException {
        return disconnect(null, null);
    }

    @Override
    public IMqttToken disconnect(long j) throws MqttException {
        return disconnect(j, null, null);
    }

    @Override
    public IMqttToken disconnect(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, MqttServiceConstants.DISCONNECT_ACTION, "104", new Object[]{new Long(j), obj, iMqttActionListener});
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        try {
            this.comms.disconnect(new MqttDisconnect(), j, mqttToken);
            logger.fine(str, MqttServiceConstants.DISCONNECT_ACTION, "108");
            return mqttToken;
        } catch (MqttException e) {
            log.fine(CLASS_NAME, MqttServiceConstants.DISCONNECT_ACTION, "105", null, e);
            throw e;
        }
    }

    @Override
    public void disconnectForcibly() throws MqttException {
        disconnectForcibly(QUIESCE_TIMEOUT, DISCONNECT_TIMEOUT);
    }

    @Override
    public void disconnectForcibly(long j) throws MqttException {
        disconnectForcibly(QUIESCE_TIMEOUT, j);
    }

    @Override
    public void disconnectForcibly(long j, long j2) throws MqttException {
        this.comms.disconnectForcibly(j, j2);
    }

    public void disconnectForcibly(long j, long j2, boolean z) throws MqttException {
        this.comms.disconnectForcibly(j, j2, z);
    }

    @Override
    public boolean isConnected() {
        return this.comms.isConnected();
    }

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public String getServerURI() {
        return this.serverURI;
    }

    public String getCurrentServerURI() {
        return this.comms.getNetworkModules()[this.comms.getNetworkModuleIndex()].getServerURI();
    }

    protected MqttTopic getTopic(String str) {
        MqttTopic.validate(str, false);
        MqttTopic mqttTopic = (MqttTopic) this.topics.get(str);
        if (mqttTopic != null) {
            return mqttTopic;
        }
        MqttTopic mqttTopic2 = new MqttTopic(str, this.comms);
        this.topics.put(str, mqttTopic2);
        return mqttTopic2;
    }

    public IMqttToken checkPing(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "ping", "117");
        MqttToken mqttTokenCheckForActivity = this.comms.checkForActivity();
        logger.fine(str, "ping", "118");
        return mqttTokenCheckForActivity;
    }

    @Override
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    @Override
    public IMqttToken subscribe(String str, int i) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null);
    }

    @Override
    public IMqttToken subscribe(String[] strArr, int[] iArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null);
    }

    @Override
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length != iArr.length) {
            throw new IllegalArgumentException();
        }
        for (String str : strArr) {
            this.comms.removeMessageListener(str);
        }
        if (log.isLoggable(5)) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("topic=").append(strArr[i]).append(" qos=").append(iArr[i]);
                MqttTopic.validate(strArr[i], true);
            }
            log.fine(CLASS_NAME, MqttServiceConstants.SUBSCRIBE_ACTION, "106", new Object[]{stringBuffer.toString(), obj, iMqttActionListener});
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttSubscribe(strArr, iArr), mqttToken);
        log.fine(CLASS_NAME, MqttServiceConstants.SUBSCRIBE_ACTION, "109");
        return mqttToken;
    }

    @Override
    public IMqttToken subscribe(String str, int i, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, obj, iMqttActionListener, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public IMqttToken subscribe(String str, int i, IMqttMessageListener iMqttMessageListener) throws MqttException {
        return subscribe(new String[]{str}, new int[]{i}, (Object) null, (IMqttActionListener) null, new IMqttMessageListener[]{iMqttMessageListener});
    }

    @Override
    public IMqttToken subscribe(String[] strArr, int[] iArr, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        return subscribe(strArr, iArr, (Object) null, (IMqttActionListener) null, iMqttMessageListenerArr);
    }

    @Override
    public IMqttToken subscribe(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, IMqttMessageListener[] iMqttMessageListenerArr) throws MqttException {
        if (iMqttMessageListenerArr.length != iArr.length || iArr.length != strArr.length) {
            throw new IllegalArgumentException();
        }
        IMqttToken iMqttTokenSubscribe = subscribe(strArr, iArr, obj, iMqttActionListener);
        for (int i = 0; i < strArr.length; i++) {
            this.comms.setMessageListener(strArr[i], iMqttMessageListenerArr[i]);
        }
        return iMqttTokenSubscribe;
    }

    @Override
    public IMqttToken unsubscribe(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return unsubscribe(new String[]{str}, obj, iMqttActionListener);
    }

    @Override
    public IMqttToken unsubscribe(String str) throws MqttException {
        return unsubscribe(new String[]{str}, (Object) null, (IMqttActionListener) null);
    }

    @Override
    public IMqttToken unsubscribe(String[] strArr) throws MqttException {
        return unsubscribe(strArr, (Object) null, (IMqttActionListener) null);
    }

    @Override
    public IMqttToken unsubscribe(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (log.isLoggable(5)) {
            String string = "";
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    string = new StringBuffer(String.valueOf(string)).append(", ").toString();
                }
                string = new StringBuffer(String.valueOf(string)).append(strArr[i]).toString();
            }
            log.fine(CLASS_NAME, MqttServiceConstants.UNSUBSCRIBE_ACTION, "107", new Object[]{string, obj, iMqttActionListener});
        }
        for (String str : strArr) {
            MqttTopic.validate(str, true);
        }
        for (String str2 : strArr) {
            this.comms.removeMessageListener(str2);
        }
        MqttToken mqttToken = new MqttToken(getClientId());
        mqttToken.setActionCallback(iMqttActionListener);
        mqttToken.setUserContext(obj);
        mqttToken.internalTok.setTopics(strArr);
        this.comms.sendNoWait(new MqttUnsubscribe(strArr), mqttToken);
        log.fine(CLASS_NAME, MqttServiceConstants.UNSUBSCRIBE_ACTION, "110");
        return mqttToken;
    }

    @Override
    public void setCallback(MqttCallback mqttCallback) {
        this.mqttCallback = mqttCallback;
        this.comms.setCallback(mqttCallback);
    }

    @Override
    public void setManualAcks(boolean z) {
        this.comms.setManualAcks(z);
    }

    @Override
    public void messageArrivedComplete(int i, int i2) throws MqttException {
        this.comms.messageArrivedComplete(i, i2);
    }

    public static String generateClientId() {
        return new StringBuffer(CLIENT_ID_PREFIX).append(System.currentTimeMillis() * 1000000).toString();
    }

    @Override
    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.comms.getPendingDeliveryTokens();
    }

    @Override
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(str, mqttMessage, obj, iMqttActionListener);
    }

    @Override
    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z) throws MqttException {
        return publish(str, bArr, i, z, null, null);
    }

    @Override
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage) throws MqttException {
        return publish(str, mqttMessage, (Object) null, (IMqttActionListener) null);
    }

    @Override
    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        Logger logger = log;
        String str2 = CLASS_NAME;
        logger.fine(str2, "publish", "111", new Object[]{str, obj, iMqttActionListener});
        MqttTopic.validate(str, false);
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(getClientId());
        mqttDeliveryToken.setActionCallback(iMqttActionListener);
        mqttDeliveryToken.setUserContext(obj);
        mqttDeliveryToken.setMessage(mqttMessage);
        mqttDeliveryToken.internalTok.setTopics(new String[]{str});
        this.comms.sendNoWait(new MqttPublish(str, mqttMessage), mqttDeliveryToken);
        logger.fine(str2, "publish", "112");
        return mqttDeliveryToken;
    }

    public void reconnect() throws MqttException {
        log.fine(CLASS_NAME, "reconnect", "500", new Object[]{this.clientId});
        if (this.comms.isConnected()) {
            throw ExceptionHelper.createMqttException(32100);
        }
        if (this.comms.isConnecting()) {
            throw new MqttException(32110);
        }
        if (this.comms.isDisconnecting()) {
            throw new MqttException(32102);
        }
        if (this.comms.isClosed()) {
            throw new MqttException(32111);
        }
        stopReconnectCycle();
        attemptReconnect();
    }

    public void attemptReconnect() {
        log.fine(CLASS_NAME, "attemptReconnect", "500", new Object[]{this.clientId});
        try {
            connect(this.connOpts, this.userContext, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "501", new Object[]{iMqttToken.getClient().getClientId()});
                    MqttAsyncClient.this.comms.setRestingState(false);
                    MqttAsyncClient.this.stopReconnectCycle();
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, "attemptReconnect", "502", new Object[]{iMqttToken.getClient().getClientId()});
                    if (MqttAsyncClient.reconnectDelay < 128000) {
                        MqttAsyncClient.reconnectDelay *= 2;
                    }
                    MqttAsyncClient.this.rescheduleReconnectCycle(MqttAsyncClient.reconnectDelay);
                }
            });
        } catch (MqttSecurityException e) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e);
        } catch (MqttException e2) {
            log.fine(CLASS_NAME, "attemptReconnect", "804", null, e2);
        }
    }

    public void startReconnectCycle() {
        log.fine(CLASS_NAME, "startReconnectCycle", "503", new Object[]{this.clientId, new Long(reconnectDelay)});
        Timer timer = new Timer();
        this.reconnectTimer = timer;
        timer.schedule(new ReconnectTask(this, null), reconnectDelay);
    }

    public void stopReconnectCycle() {
        log.fine(CLASS_NAME, "stopReconnectCycle", "504", new Object[]{this.clientId});
        synchronized (clientLock) {
            if (this.connOpts.isAutomaticReconnect()) {
                Timer timer = this.reconnectTimer;
                if (timer != null) {
                    timer.cancel();
                    this.reconnectTimer = null;
                }
                reconnectDelay = 1000;
            }
        }
    }

    public void rescheduleReconnectCycle(int i) {
        log.fine(CLASS_NAME, "rescheduleReconnectCycle", "505", new Object[]{this.clientId, new Long(reconnectDelay)});
        synchronized (clientLock) {
            if (this.connOpts.isAutomaticReconnect()) {
                Timer timer = this.reconnectTimer;
                if (timer != null) {
                    timer.schedule(new ReconnectTask(this, null), i);
                } else {
                    reconnectDelay = i;
                    startReconnectCycle();
                }
            }
        }
    }

    private class ReconnectTask extends TimerTask {
        private static final String methodName = "ReconnectTask.run";

        private ReconnectTask() {
        }

        ReconnectTask(MqttAsyncClient mqttAsyncClient, ReconnectTask reconnectTask) {
            this();
        }

        @Override
        public void run() {
            MqttAsyncClient.log.fine(MqttAsyncClient.CLASS_NAME, methodName, "506");
            MqttAsyncClient.this.attemptReconnect();
        }
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.comms.setDisconnectedMessageBuffer(new DisconnectedMessageBuffer(disconnectedBufferOptions));
    }

    public int getBufferedMessageCount() {
        return this.comms.getBufferedMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.comms.getBufferedMessage(i);
    }

    public void deleteBufferedMessage(int i) {
        this.comms.deleteBufferedMessage(i);
    }

    @Override
    public void close() throws MqttException {
        Logger logger = log;
        String str = CLASS_NAME;
        logger.fine(str, "close", "113");
        this.comms.close();
        logger.fine(str, "close", "114");
    }

    public Debug getDebug() {
        return new Debug(this.clientId, this.comms);
    }
}
