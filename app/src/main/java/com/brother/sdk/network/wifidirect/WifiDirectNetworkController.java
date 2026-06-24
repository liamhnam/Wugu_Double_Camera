package com.brother.sdk.network.wifidirect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import com.brother.sdk.common.InvalidImplementException;
import com.brother.sdk.network.NetworkControllerManager;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WifiDirectNetworkController extends NetworkControllerManager.NetworkController {
    private static final IntentFilter mIntentFilter;
    private final BroadcastReceiver mBroadcastReceiver;
    private WifiP2pManager.Channel mChannel;
    private WifiP2pManager.ConnectionInfoListener mConnectionInfoListener;
    private boolean mControllerStarted;
    private int mLatestState;
    private List<IPeerEventListener> mListeners;
    private WifiP2pManager mManager;
    private WifiP2pDevice mP2pDevice;
    private WifiP2pInfo mP2pInfo;

    interface IPeerEventListener {
        void onPeerEventNotified(WifiDirectPeerEvent wifiDirectPeerEvent);
    }

    enum WifiDirectPeerEvent {
        STATE_CHANGED,
        PEERS_CHANGED,
        CONNECTION_FAILED,
        CONNECTION_SUCCEEDED,
        THIS_DEVICE_CHANGED
    }

    static {
        IntentFilter intentFilter = new IntentFilter();
        mIntentFilter = intentFilter;
        intentFilter.addAction("android.net.wifi.p2p.STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.PEERS_CHANGED");
        intentFilter.addAction("android.net.wifi.p2p.CONNECTION_STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.p2p.THIS_DEVICE_CHANGED");
    }

    public WifiDirectNetworkController(Context context) {
        super(context);
        this.mManager = null;
        this.mChannel = null;
        this.mP2pInfo = null;
        this.mP2pDevice = null;
        this.mLatestState = 1;
        this.mConnectionInfoListener = null;
        this.mControllerStarted = false;
        this.mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if ("android.net.wifi.p2p.STATE_CHANGED".equals(action)) {
                    WifiDirectNetworkController.this.mLatestState = intent.getIntExtra("wifi_p2p_state", 1);
                    if (WifiDirectNetworkController.this.mLatestState != 2) {
                        WifiDirectNetworkController.this.mP2pInfo = null;
                    }
                    WifiDirectNetworkController.this.notifyPeerEvent(WifiDirectPeerEvent.STATE_CHANGED);
                    return;
                }
                if ("android.net.wifi.p2p.CONNECTION_STATE_CHANGE".equals(action)) {
                    if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).isConnected()) {
                        WifiDirectNetworkController.this.updateP2pInfo();
                        WifiDirectNetworkController.this.notifyPeerEvent(WifiDirectPeerEvent.CONNECTION_SUCCEEDED);
                        return;
                    } else {
                        WifiDirectNetworkController.this.mP2pInfo = null;
                        WifiDirectNetworkController.this.notifyPeerEvent(WifiDirectPeerEvent.CONNECTION_FAILED);
                        return;
                    }
                }
                if ("android.net.wifi.p2p.THIS_DEVICE_CHANGED".equals(action)) {
                    WifiDirectNetworkController.this.mP2pDevice = (WifiP2pDevice) intent.getParcelableExtra("wifiP2pDevice");
                    WifiDirectNetworkController.this.notifyPeerEvent(WifiDirectPeerEvent.THIS_DEVICE_CHANGED);
                } else if ("android.net.wifi.p2p.PEERS_CHANGED".equals(action)) {
                    WifiDirectNetworkController.this.notifyPeerEvent(WifiDirectPeerEvent.PEERS_CHANGED);
                }
            }
        };
        this.mListeners = new ArrayList();
        this.mConnectionInfoListener = new WifiP2pManager.ConnectionInfoListener() {
            @Override
            public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
                WifiDirectNetworkController.this.mP2pInfo = wifiP2pInfo;
            }
        };
        this.mManager = (WifiP2pManager) context.getSystemService("wifip2p");
    }

    @Override
    public void startControl() {
        WifiP2pManager wifiP2pManager;
        Context context = this.mContext.get();
        if (context == null || this.mConnectionInfoListener == null || (wifiP2pManager = this.mManager) == null || this.mControllerStarted) {
            return;
        }
        this.mControllerStarted = true;
        this.mChannel = wifiP2pManager.initialize(context, context.getMainLooper(), null);
        context.registerReceiver(this.mBroadcastReceiver, mIntentFilter);
        requestConnectionInfo(new WifiP2pManager.ConnectionInfoListener() {
            @Override
            public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
                synchronized (WifiDirectNetworkController.this) {
                    WifiDirectNetworkController.this.notifyAll();
                }
            }
        });
    }

    @Override
    public void stopControl() {
        if (this.mContext.get() == null || !this.mControllerStarted) {
            return;
        }
        this.mControllerStarted = false;
        this.mContext.get().unregisterReceiver(this.mBroadcastReceiver);
    }

    @Override
    public boolean isConnected() {
        return this.mLatestState == 2;
    }

    @Override
    public InetAddress getBroadcastAddress() {
        WifiP2pInfo wifiP2pInfo;
        if (this.mLatestState != 2 || (wifiP2pInfo = this.mP2pInfo) == null) {
            return null;
        }
        if (!wifiP2pInfo.isGroupOwner) {
            return this.mP2pInfo.groupOwnerAddress;
        }
        return getBroadcastAddress(this.mP2pInfo.groupOwnerAddress, null);
    }

    boolean isControllerStarted() {
        return this.mControllerStarted;
    }

    void requestConnectionInfo(WifiP2pManager.ConnectionInfoListener connectionInfoListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.requestConnectionInfo(channel, connectionInfoListener);
    }

    void requestGroupInfo(WifiP2pManager.GroupInfoListener groupInfoListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.requestGroupInfo(channel, groupInfoListener);
    }

    void requestPeers(WifiP2pManager.PeerListListener peerListListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.requestPeers(channel, peerListListener);
    }

    void discoverPeers(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.discoverPeers(channel, actionListener);
    }

    void stopPeerDiscovery(WifiP2pManager.ActionListener actionListener) {
        if (this.mChannel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.stopPeerDiscovery(this.mChannel, actionListener);
    }

    void connect(WifiP2pConfig wifiP2pConfig, WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.connect(channel, wifiP2pConfig, actionListener);
    }

    void cancelConnect(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.cancelConnect(channel, actionListener);
    }

    void createGroup(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.createGroup(channel, actionListener);
    }

    void removeGroup(WifiP2pManager.ActionListener actionListener) {
        WifiP2pManager.Channel channel = this.mChannel;
        if (channel == null) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first, which can get by NetworkControllerManager.getWifiDirectNetworkController().");
        }
        this.mManager.removeGroup(channel, actionListener);
    }

    void addPeerEventListener(IPeerEventListener iPeerEventListener) {
        if (this.mListeners.contains(iPeerEventListener)) {
            return;
        }
        this.mListeners.add(iPeerEventListener);
    }

    void removePeerEventListener(IPeerEventListener iPeerEventListener) {
        if (this.mListeners.contains(iPeerEventListener)) {
            this.mListeners.remove(iPeerEventListener);
        }
    }

    void clearPeerEventListener() {
        this.mListeners.clear();
    }

    public void updateP2pInfo() {
        WifiP2pManager.Channel channel;
        WifiP2pManager.ConnectionInfoListener connectionInfoListener;
        WifiP2pManager wifiP2pManager = this.mManager;
        if (wifiP2pManager == null || (channel = this.mChannel) == null || (connectionInfoListener = this.mConnectionInfoListener) == null) {
            return;
        }
        wifiP2pManager.requestConnectionInfo(channel, connectionInfoListener);
    }

    public void notifyPeerEvent(WifiDirectPeerEvent wifiDirectPeerEvent) {
        Iterator<IPeerEventListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPeerEventNotified(wifiDirectPeerEvent);
        }
    }
}
