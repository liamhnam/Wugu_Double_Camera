package org.eclipse.paho.android.service;

import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.paho.android.service.MessageStore;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;

class MqttConnection implements MqttCallbackExtended {
    private static final String NOT_CONNECTED = "not connected";
    private static final String TAG = "MqttConnection";
    private String clientHandle;
    private String clientId;
    private MqttConnectOptions connectOptions;
    private MqttClientPersistence persistence;
    private String serverURI;
    private MqttService service;
    private String wakeLockTag;
    private String reconnectActivityToken = null;
    private MqttAsyncClient myClient = null;
    private AlarmPingSender alarmPingSender = null;
    private volatile boolean disconnected = true;
    private boolean cleanSession = true;
    private volatile boolean isConnecting = false;
    private Map<IMqttDeliveryToken, String> savedTopics = new HashMap();
    private Map<IMqttDeliveryToken, MqttMessage> savedSentMessages = new HashMap();
    private Map<IMqttDeliveryToken, String> savedActivityTokens = new HashMap();
    private Map<IMqttDeliveryToken, String> savedInvocationContexts = new HashMap();
    private PowerManager.WakeLock wakelock = null;
    private DisconnectedBufferOptions bufferOpts = null;

    public String getServerURI() {
        return this.serverURI;
    }

    public void setServerURI(String str) {
        this.serverURI = str;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public MqttConnectOptions getConnectOptions() {
        return this.connectOptions;
    }

    public void setConnectOptions(MqttConnectOptions mqttConnectOptions) {
        this.connectOptions = mqttConnectOptions;
    }

    public String getClientHandle() {
        return this.clientHandle;
    }

    public void setClientHandle(String str) {
        this.clientHandle = str;
    }

    MqttConnection(MqttService mqttService, String str, String str2, MqttClientPersistence mqttClientPersistence, String str3) {
        this.persistence = null;
        this.service = null;
        this.wakeLockTag = null;
        this.serverURI = str;
        this.service = mqttService;
        this.clientId = str2;
        this.persistence = mqttClientPersistence;
        this.clientHandle = str3;
        this.wakeLockTag = getClass().getCanonicalName() + " " + str2 + " on host " + str;
    }

    public void connect(MqttConnectOptions mqttConnectOptions, String str, String str2) {
        this.connectOptions = mqttConnectOptions;
        this.reconnectActivityToken = str2;
        if (mqttConnectOptions != null) {
            this.cleanSession = mqttConnectOptions.isCleanSession();
        }
        if (this.connectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        this.service.traceDebug(TAG, "Connecting {" + this.serverURI + "} as {" + this.clientId + "}");
        final Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
        try {
            if (this.persistence == null) {
                File externalFilesDir = this.service.getExternalFilesDir(TAG);
                if (externalFilesDir == null && (externalFilesDir = this.service.getDir(TAG, 0)) == null) {
                    bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, "Error! No external and internal storage available");
                    bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, new MqttPersistenceException());
                    this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
                    return;
                }
                this.persistence = new MqttDefaultFilePersistence(externalFilesDir.getAbsolutePath());
            }
            MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle) {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    MqttConnection.this.doAfterConnectSuccess(bundle);
                    MqttConnection.this.service.traceDebug(MqttConnection.TAG, "connect success!");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable th) {
                    bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
                    bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
                    MqttConnection.this.service.traceError(MqttConnection.TAG, "connect fail, call connect to reconnect.reason:" + th.getMessage());
                    MqttConnection.this.doAfterConnectFail(bundle);
                }
            };
            if (this.myClient != null) {
                if (this.isConnecting) {
                    this.service.traceDebug(TAG, "myClient != null and the client is connecting. Connect return directly.");
                    this.service.traceDebug(TAG, "Connect return:isConnecting:" + this.isConnecting + ".disconnected:" + this.disconnected);
                    return;
                } else if (!this.disconnected) {
                    this.service.traceDebug(TAG, "myClient != null and the client is connected and notify!");
                    doAfterConnectSuccess(bundle);
                    return;
                } else {
                    this.service.traceDebug(TAG, "myClient != null and the client is not connected");
                    this.service.traceDebug(TAG, "Do Real connect!");
                    setConnectingState(true);
                    this.myClient.connect(this.connectOptions, str, mqttConnectionListener);
                    return;
                }
            }
            this.alarmPingSender = new AlarmPingSender(this.service);
            MqttAsyncClient mqttAsyncClient = new MqttAsyncClient(this.serverURI, this.clientId, this.persistence, this.alarmPingSender);
            this.myClient = mqttAsyncClient;
            mqttAsyncClient.setCallback(this);
            this.service.traceDebug(TAG, "Do Real connect!");
            setConnectingState(true);
            this.myClient.connect(this.connectOptions, str, mqttConnectionListener);
        } catch (Exception e) {
            this.service.traceError(TAG, "Exception occurred attempting to connect: " + e.getMessage());
            setConnectingState(false);
            handleException(bundle, e);
        }
    }

    public void doAfterConnectSuccess(Bundle bundle) {
        acquireWakeLock();
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
        deliverBacklog();
        setConnectingState(false);
        this.disconnected = false;
        releaseWakeLock();
    }

    @Override
    public void connectComplete(boolean z, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_EXTENDED_ACTION);
        bundle.putBoolean(MqttServiceConstants.CALLBACK_RECONNECT, z);
        bundle.putString(MqttServiceConstants.CALLBACK_SERVER_URI, str);
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
    }

    public void doAfterConnectFail(Bundle bundle) {
        acquireWakeLock();
        this.disconnected = true;
        setConnectingState(false);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        releaseWakeLock();
    }

    private void handleException(Bundle bundle, Exception exc) {
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, exc.getLocalizedMessage());
        bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, exc);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    private void deliverBacklog() {
        Iterator<MessageStore.StoredMessage> allArrivedMessages = this.service.messageStore.getAllArrivedMessages(this.clientHandle);
        while (allArrivedMessages.hasNext()) {
            MessageStore.StoredMessage next = allArrivedMessages.next();
            Bundle bundleMessageToBundle = messageToBundle(next.getMessageId(), next.getTopic(), next.getMessage());
            bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
            this.service.callbackToActivity(this.clientHandle, Status.OK, bundleMessageToBundle);
        }
    }

    private Bundle messageToBundle(String str, String str2, MqttMessage mqttMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, str);
        bundle.putString(MqttServiceConstants.CALLBACK_DESTINATION_NAME, str2);
        bundle.putParcelable(MqttServiceConstants.CALLBACK_MESSAGE_PARCEL, new ParcelableMqttMessage(mqttMessage));
        return bundle;
    }

    void close() {
        this.service.traceDebug(TAG, "close()");
        try {
            MqttAsyncClient mqttAsyncClient = this.myClient;
            if (mqttAsyncClient != null) {
                mqttAsyncClient.close();
            }
        } catch (MqttException e) {
            handleException(new Bundle(), e);
        }
    }

    void disconnect(long j, String str, String str2) {
        this.service.traceDebug(TAG, "disconnect()");
        this.disconnected = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.disconnect(j, str, new MqttConnectionListener(bundle));
            } catch (Exception e) {
                handleException(bundle, e);
            }
        } else {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
            this.service.traceError(MqttServiceConstants.DISCONNECT_ACTION, NOT_CONNECTED);
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
        MqttConnectOptions mqttConnectOptions = this.connectOptions;
        if (mqttConnectOptions != null && mqttConnectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        releaseWakeLock();
    }

    void disconnect(String str, String str2) {
        this.service.traceDebug(TAG, "disconnect()");
        this.disconnected = true;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.DISCONNECT_ACTION);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.disconnect(str, new MqttConnectionListener(bundle));
            } catch (Exception e) {
                handleException(bundle, e);
            }
        } else {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
            this.service.traceError(MqttServiceConstants.DISCONNECT_ACTION, NOT_CONNECTED);
            this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        }
        MqttConnectOptions mqttConnectOptions = this.connectOptions;
        if (mqttConnectOptions != null && mqttConnectOptions.isCleanSession()) {
            this.service.messageStore.clearArrivedMessages(this.clientHandle);
        }
        releaseWakeLock();
    }

    public boolean isConnected() {
        MqttAsyncClient mqttAsyncClient = this.myClient;
        return mqttAsyncClient != null && mqttAsyncClient.isConnected();
    }

    public IMqttDeliveryToken publish(String str, byte[] bArr, int i, boolean z, String str2, String str3) {
        MqttMessage mqttMessage;
        IMqttDeliveryToken iMqttDeliveryTokenPublish;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        IMqttDeliveryToken iMqttDeliveryToken = null;
        Object[] objArr = 0;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            MqttConnectionListener mqttConnectionListener = new MqttConnectionListener(bundle);
            try {
                mqttMessage = new MqttMessage(bArr);
                mqttMessage.setQos(i);
                mqttMessage.setRetained(z);
                iMqttDeliveryTokenPublish = this.myClient.publish(str, bArr, i, z, str2, mqttConnectionListener);
            } catch (Exception e) {
                e = e;
            }
            try {
                storeSendDetails(str, mqttMessage, iMqttDeliveryTokenPublish, str2, str3);
                return iMqttDeliveryTokenPublish;
            } catch (Exception e2) {
                e = e2;
                iMqttDeliveryToken = iMqttDeliveryTokenPublish;
                handleException(bundle, e);
                return iMqttDeliveryToken;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SEND_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        return null;
    }

    public IMqttDeliveryToken publish(String str, MqttMessage mqttMessage, String str2, String str3) {
        DisconnectedBufferOptions disconnectedBufferOptions;
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        ?? Publish = 0;
        IMqttDeliveryToken iMqttDeliveryTokenPublish = null;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                iMqttDeliveryTokenPublish = this.myClient.publish(str, mqttMessage, str2, new MqttConnectionListener(bundle));
                storeSendDetails(str, mqttMessage, iMqttDeliveryTokenPublish, str2, str3);
                return iMqttDeliveryTokenPublish;
            } catch (Exception e) {
                handleException(bundle, e);
                return iMqttDeliveryTokenPublish;
            }
        }
        if (this.myClient != null && (disconnectedBufferOptions = this.bufferOpts) != null && disconnectedBufferOptions.isBufferEnabled()) {
            try {
                Publish = this.myClient.publish(str, mqttMessage, str2, new MqttConnectionListener(bundle));
                storeSendDetails(str, mqttMessage, Publish, str2, str3);
                return Publish;
            } catch (Exception e2) {
                handleException(bundle, e2);
                return Publish;
            }
        }
        Log.i(TAG, "Client is not connected, so not sending message");
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SEND_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
        return null;
    }

    public void subscribe(String str, int i, String str2, String str3) {
        this.service.traceDebug(TAG, "subscribe({" + str + "}," + i + ",{" + str2 + "}, {" + str3 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.subscribe(str, i, str2, new MqttConnectionListener(bundle));
                return;
            } catch (Exception e) {
                handleException(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    public void subscribe(String[] strArr, int[] iArr, String str, String str2) {
        this.service.traceDebug(TAG, "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.subscribe(strArr, iArr, str, new MqttConnectionListener(bundle));
                return;
            } catch (Exception e) {
                handleException(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    public void subscribe(String[] strArr, int[] iArr, String str, String str2, IMqttMessageListener[] iMqttMessageListenerArr) {
        this.service.traceDebug(TAG, "subscribe({" + Arrays.toString(strArr) + "}," + Arrays.toString(iArr) + ",{" + str + "}, {" + str2 + "}");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            new MqttConnectionListener(bundle);
            try {
                this.myClient.subscribe(strArr, iArr, iMqttMessageListenerArr);
                return;
            } catch (Exception e) {
                handleException(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    void unsubscribe(String str, String str2, String str3) {
        this.service.traceDebug(TAG, "unsubscribe({" + str + "},{" + str2 + "}, {" + str3 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str3);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str2);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.unsubscribe(str, str2, new MqttConnectionListener(bundle));
                return;
            } catch (Exception e) {
                handleException(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    void unsubscribe(String[] strArr, String str, String str2) {
        this.service.traceDebug(TAG, "unsubscribe({" + Arrays.toString(strArr) + "},{" + str + "}, {" + str2 + "})");
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION);
        bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, str2);
        bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, str);
        MqttAsyncClient mqttAsyncClient = this.myClient;
        if (mqttAsyncClient != null && mqttAsyncClient.isConnected()) {
            try {
                this.myClient.unsubscribe(strArr, str, new MqttConnectionListener(bundle));
                return;
            } catch (Exception e) {
                handleException(bundle, e);
                return;
            }
        }
        bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, NOT_CONNECTED);
        this.service.traceError(MqttServiceConstants.SUBSCRIBE_ACTION, NOT_CONNECTED);
        this.service.callbackToActivity(this.clientHandle, Status.ERROR, bundle);
    }

    public IMqttDeliveryToken[] getPendingDeliveryTokens() {
        return this.myClient.getPendingDeliveryTokens();
    }

    @Override
    public void connectionLost(Throwable th) {
        this.service.traceDebug(TAG, "connectionLost(" + th.getMessage() + ")");
        this.disconnected = true;
        try {
            if (!this.connectOptions.isAutomaticReconnect()) {
                this.myClient.disconnect(null, new IMqttActionListener() {
                    @Override
                    public void onFailure(IMqttToken iMqttToken, Throwable th2) {
                    }

                    @Override
                    public void onSuccess(IMqttToken iMqttToken) {
                    }
                });
            } else {
                this.alarmPingSender.schedule(100L);
            }
        } catch (Exception unused) {
        }
        Bundle bundle = new Bundle();
        bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.ON_CONNECTION_LOST_ACTION);
        if (th != null) {
            bundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getMessage());
            if (th instanceof MqttException) {
                bundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            }
            bundle.putString(MqttServiceConstants.CALLBACK_EXCEPTION_STACK, Log.getStackTraceString(th));
        }
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundle);
        releaseWakeLock();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        this.service.traceDebug(TAG, "deliveryComplete(" + iMqttDeliveryToken + ")");
        MqttMessage mqttMessageRemove = this.savedSentMessages.remove(iMqttDeliveryToken);
        if (mqttMessageRemove != null) {
            String strRemove = this.savedTopics.remove(iMqttDeliveryToken);
            String strRemove2 = this.savedActivityTokens.remove(iMqttDeliveryToken);
            String strRemove3 = this.savedInvocationContexts.remove(iMqttDeliveryToken);
            Bundle bundleMessageToBundle = messageToBundle(null, strRemove, mqttMessageRemove);
            if (strRemove2 != null) {
                bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.SEND_ACTION);
                bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, strRemove2);
                bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, strRemove3);
                this.service.callbackToActivity(this.clientHandle, Status.OK, bundleMessageToBundle);
            }
            bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_DELIVERED_ACTION);
            this.service.callbackToActivity(this.clientHandle, Status.OK, bundleMessageToBundle);
        }
    }

    @Override
    public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        this.service.traceDebug(TAG, "messageArrived(" + str + ",{" + mqttMessage.toString() + "})");
        String strStoreArrived = this.service.messageStore.storeArrived(this.clientHandle, str, mqttMessage);
        Bundle bundleMessageToBundle = messageToBundle(strStoreArrived, str, mqttMessage);
        bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.MESSAGE_ARRIVED_ACTION);
        bundleMessageToBundle.putString(MqttServiceConstants.CALLBACK_MESSAGE_ID, strStoreArrived);
        this.service.callbackToActivity(this.clientHandle, Status.OK, bundleMessageToBundle);
    }

    private void storeSendDetails(String str, MqttMessage mqttMessage, IMqttDeliveryToken iMqttDeliveryToken, String str2, String str3) {
        this.savedTopics.put(iMqttDeliveryToken, str);
        this.savedSentMessages.put(iMqttDeliveryToken, mqttMessage);
        this.savedActivityTokens.put(iMqttDeliveryToken, str3);
        this.savedInvocationContexts.put(iMqttDeliveryToken, str2);
    }

    private void acquireWakeLock() {
        if (this.wakelock == null) {
            this.wakelock = ((PowerManager) this.service.getSystemService("power")).newWakeLock(1, this.wakeLockTag);
        }
        this.wakelock.acquire();
    }

    private void releaseWakeLock() {
        PowerManager.WakeLock wakeLock = this.wakelock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.wakelock.release();
    }

    private class MqttConnectionListener implements IMqttActionListener {
        private final Bundle resultBundle;

        private MqttConnectionListener(Bundle bundle) {
            this.resultBundle = bundle;
        }

        @Override
        public void onSuccess(IMqttToken iMqttToken) {
            MqttConnection.this.service.callbackToActivity(MqttConnection.this.clientHandle, Status.OK, this.resultBundle);
        }

        @Override
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            this.resultBundle.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
            this.resultBundle.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
            MqttConnection.this.service.callbackToActivity(MqttConnection.this.clientHandle, Status.ERROR, this.resultBundle);
        }
    }

    void offline() {
        if (this.disconnected || this.cleanSession) {
            return;
        }
        connectionLost(new Exception("Android offline"));
    }

    synchronized void reconnect() {
        if (this.myClient == null) {
            this.service.traceError(TAG, "Reconnect myClient = null. Will not do reconnect");
            return;
        }
        if (this.isConnecting) {
            this.service.traceDebug(TAG, "The client is connecting. Reconnect return directly.");
            return;
        }
        if (!this.service.isOnline()) {
            this.service.traceDebug(TAG, "The network is not reachable. Will not do reconnect");
            return;
        }
        if (this.connectOptions.isAutomaticReconnect()) {
            Log.i(TAG, "Requesting Automatic reconnect using New Java AC");
            Bundle bundle = new Bundle();
            bundle.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.reconnectActivityToken);
            bundle.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
            bundle.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
            try {
                this.myClient.reconnect();
            } catch (MqttException e) {
                Log.e(TAG, "Exception occurred attempting to reconnect: " + e.getMessage());
                setConnectingState(false);
                handleException(bundle, e);
            }
        }
        if (this.disconnected && !this.cleanSession) {
            this.service.traceDebug(TAG, "Do Real Reconnect!");
            final Bundle bundle2 = new Bundle();
            bundle2.putString(MqttServiceConstants.CALLBACK_ACTIVITY_TOKEN, this.reconnectActivityToken);
            bundle2.putString(MqttServiceConstants.CALLBACK_INVOCATION_CONTEXT, null);
            bundle2.putString(MqttServiceConstants.CALLBACK_ACTION, MqttServiceConstants.CONNECT_ACTION);
            try {
                this.myClient.connect(this.connectOptions, null, new MqttConnectionListener(bundle2) {
                    @Override
                    public void onSuccess(IMqttToken iMqttToken) {
                        MqttConnection.this.service.traceDebug(MqttConnection.TAG, "Reconnect Success!");
                        MqttConnection.this.service.traceDebug(MqttConnection.TAG, "DeliverBacklog when reconnect.");
                        MqttConnection.this.doAfterConnectSuccess(bundle2);
                    }

                    @Override
                    public void onFailure(IMqttToken iMqttToken, Throwable th) {
                        bundle2.putString(MqttServiceConstants.CALLBACK_ERROR_MESSAGE, th.getLocalizedMessage());
                        bundle2.putSerializable(MqttServiceConstants.CALLBACK_EXCEPTION, th);
                        MqttConnection.this.service.callbackToActivity(MqttConnection.this.clientHandle, Status.ERROR, bundle2);
                        MqttConnection.this.doAfterConnectFail(bundle2);
                    }
                });
                setConnectingState(true);
            } catch (MqttException e2) {
                this.service.traceError(TAG, "Cannot reconnect to remote server." + e2.getMessage());
                setConnectingState(false);
                handleException(bundle2, e2);
            } catch (Exception e3) {
                this.service.traceError(TAG, "Cannot reconnect to remote server." + e3.getMessage());
                setConnectingState(false);
                handleException(bundle2, new MqttException(6, e3.getCause()));
            }
        }
    }

    private synchronized void setConnectingState(boolean z) {
        this.isConnecting = z;
    }

    public void setBufferOpts(DisconnectedBufferOptions disconnectedBufferOptions) {
        this.bufferOpts = disconnectedBufferOptions;
        this.myClient.setBufferOpts(disconnectedBufferOptions);
    }

    public int getBufferedMessageCount() {
        return this.myClient.getBufferedMessageCount();
    }

    public MqttMessage getBufferedMessage(int i) {
        return this.myClient.getBufferedMessage(i);
    }

    public void deleteBufferedMessage(int i) {
        this.myClient.deleteBufferedMessage(i);
    }
}
