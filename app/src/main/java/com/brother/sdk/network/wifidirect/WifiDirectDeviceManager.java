package com.brother.sdk.network.wifidirect;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import com.brother.sdk.BrotherAndroidLib;
import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.InvalidImplementException;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.core.C0716R;
import com.brother.sdk.network.NetworkControllerManager;
import com.brother.sdk.network.discovery.mfc.BrotherMFCNetworkConnectorDescriptor;
import com.brother.sdk.network.discovery.mfc.BrotherMFCNetworkConnectorDiscovery;
import com.brother.sdk.network.wifidirect.WifiDirectDeviceManagerInterface;
import com.brother.sdk.network.wifidirect.WifiDirectNetworkController;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import kotlin.UByte;

public class WifiDirectDeviceManager extends WifiDirectDeviceManagerInterface {
    private WifiDirectNetworkController mController;
    private WifiDirectDeviceManagerInterface.ConnectionState mState;
    private WifiDirectDeviceDiscoverListener mDiscoverListener = null;
    private WFDConnectionEventListener mConnectionListener = null;

    public WifiDirectDeviceManager(WifiDirectNetworkController wifiDirectNetworkController) {
        this.mController = null;
        if (wifiDirectNetworkController == null || !wifiDirectNetworkController.isControllerStarted()) {
            throw new InvalidImplementException("You must call WifiDirectNetworkController.startControl() at first.");
        }
        this.mController = wifiDirectNetworkController;
        wifiDirectNetworkController.clearPeerEventListener();
        this.mState = WifiDirectDeviceManagerInterface.ConnectionState.NO_STATUS;
    }

    public void finalize() {
        WifiDirectNetworkController wifiDirectNetworkController = this.mController;
        if (wifiDirectNetworkController != null) {
            wifiDirectNetworkController.clearPeerEventListener();
            this.mController.stopPeerDiscovery(null);
        }
    }

    public WifiDirectDeviceManagerInterface.ConnectionState getWifiDirectConnectionState() {
        return this.mState;
    }

    public void discover(WifiDirectDeviceManagerInterface.OnDeviceQueryListener onDeviceQueryListener) {
        WifiDirectDeviceDiscoverListener wifiDirectDeviceDiscoverListener = this.mDiscoverListener;
        if (wifiDirectDeviceDiscoverListener != null) {
            this.mController.removePeerEventListener(wifiDirectDeviceDiscoverListener);
        }
        WifiDirectDeviceDiscoverListener wifiDirectDeviceDiscoverListener2 = new WifiDirectDeviceDiscoverListener(onDeviceQueryListener);
        this.mDiscoverListener = wifiDirectDeviceDiscoverListener2;
        this.mController.addPeerEventListener(wifiDirectDeviceDiscoverListener2);
        this.mController.discoverPeers(null);
    }

    public void stopDiscovery() {
        this.mController.clearPeerEventListener();
        this.mController.stopPeerDiscovery(null);
    }

    public void disconnect() {
        this.mController.removeGroup(null);
    }

    public void cancelConnect() {
        this.mController.clearPeerEventListener();
        this.mController.cancelConnect(null);
    }

    public void connect(WifiDirectInfo wifiDirectInfo, WifiDirectDeviceManagerInterface.OnDeviceConnectionListener onDeviceConnectionListener) {
        WFDConnectionEventListener wFDConnectionEventListener = this.mConnectionListener;
        if (wFDConnectionEventListener != null) {
            this.mController.removePeerEventListener(wFDConnectionEventListener);
        }
        WFDConnectionEventListener wFDConnectionEventListener2 = new WFDConnectionEventListener(wifiDirectInfo, onDeviceConnectionListener);
        this.mConnectionListener = wFDConnectionEventListener2;
        this.mController.addPeerEventListener(wFDConnectionEventListener2);
        WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
        wifiP2pConfig.deviceAddress = wifiDirectInfo.getWifiDirectDeviceAddress();
        wifiP2pConfig.wps.setup = 0;
        this.mController.connect(wifiP2pConfig, null);
    }

    private class WifiDirectDeviceDiscoverListener implements WifiP2pManager.PeerListListener, WifiDirectNetworkController.IPeerEventListener {
        private static final String sCompanyName = "Brother ";
        private static final String sWifiAllianceDefineOIDString = "0050F204";
        private WifiDirectDeviceManagerInterface.OnDeviceQueryListener mListener;
        private List<WifiP2pDevice> mStoredDevices = new ArrayList();
        private BrotherWifiDirectDeviceChecker mDeviceChecker = new BrotherWifiDirectDeviceChecker(BrotherAndroidLib.getAndroidContext());

        public WifiDirectDeviceDiscoverListener(WifiDirectDeviceManagerInterface.OnDeviceQueryListener onDeviceQueryListener) {
            this.mListener = onDeviceQueryListener;
        }

        @Override
        public void onPeerEventNotified(WifiDirectNetworkController.WifiDirectPeerEvent wifiDirectPeerEvent) {
            if (wifiDirectPeerEvent == WifiDirectNetworkController.WifiDirectPeerEvent.PEERS_CHANGED) {
                WifiDirectDeviceManager.this.mController.requestPeers(this);
            }
        }

        @Override
        public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
            String str;
            String lowerCase;
            String brotherDevice;
            if (wifiP2pDeviceList != null) {
                for (WifiP2pDevice wifiP2pDevice : wifiP2pDeviceList.getDeviceList()) {
                    if (wifiP2pDevice.status == 3 || wifiP2pDevice.status == 0) {
                        String[] strArrSplit = wifiP2pDevice.primaryDeviceType.split("-");
                        if (strArrSplit.length == 3) {
                            boolean z = false;
                            int i = Integer.parseInt(strArrSplit[0]);
                            int i2 = Integer.parseInt(strArrSplit[2]);
                            if (strArrSplit[1].equals(sWifiAllianceDefineOIDString) && i == 3) {
                                Iterator<WifiP2pDevice> it = this.mStoredDevices.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if (it.next().deviceAddress.equals(wifiP2pDevice.deviceAddress)) {
                                            z = true;
                                            break;
                                        }
                                    } else {
                                        break;
                                    }
                                }
                                if (!z && WifiDirectDeviceManagerInterface.WifiP2pDeviceCategory.fromValue(i2) != WifiDirectDeviceManagerInterface.WifiP2pDeviceCategory.Fax && (brotherDevice = this.mDeviceChecker.parseBrotherDevice((str = wifiP2pDevice.deviceName), (lowerCase = wifiP2pDevice.deviceAddress.toLowerCase(Locale.ENGLISH)))) != null && WifiDirectDeviceManagerInterface.MFCDeviceType.isKindsOfMFC(brotherDevice)) {
                                    String str2 = sCompanyName + brotherDevice;
                                    this.mStoredDevices.add(wifiP2pDevice);
                                    WifiDirectInfo wifiDirectInfo = new WifiDirectInfo();
                                    wifiDirectInfo.setModelName(str2);
                                    wifiDirectInfo.setWifiDirectDeviceName(str);
                                    wifiDirectInfo.setWifiDirectDeviceAddress(lowerCase);
                                    this.mListener.onDeviceFound(wifiDirectInfo);
                                }
                            }
                        }
                    }
                }
            }
        }

        private String calculateBrotherWFDInterfaceAddress(String str) {
            String[] strArrSplit = str.split(":");
            strArrSplit[4] = String.format("%02x", Integer.valueOf(((byte) (((byte) (Integer.parseInt(strArrSplit[4], 16) & 255)) ^ 128)) & UByte.MAX_VALUE));
            return strArrSplit[0] + ":" + strArrSplit[1] + ":" + strArrSplit[2] + ":" + strArrSplit[3] + ":" + strArrSplit[4] + ":" + strArrSplit[5];
        }

        private class BrotherWifiDirectDeviceChecker {
            private static final String MODEL_SERIES = " series";
            private String[] mWhiteLists;
            private String[] mWhiteListsWithSeries;

            public BrotherWifiDirectDeviceChecker(Context context) {
                this.mWhiteLists = context.getResources().getStringArray(C0716R.array.WifiDirectWhiteList);
                this.mWhiteListsWithSeries = context.getResources().getStringArray(C0716R.array.WifiDirectWhiteListWithSeries);
            }

            public String parseBrotherDevice(String str, String str2) {
                if (str == null || str.equals("") || str2 == null || str2.equals("")) {
                    return null;
                }
                if (matchWithBrotherNamingRegulation(str, str2)) {
                    return parseModelName(str, true);
                }
                String modelName = parseModelName(str, false);
                if (isInWhiteList(modelName)) {
                    return modelName;
                }
                if (isInWhiteListOfDevicesWithSeries(modelName)) {
                    return modelName + MODEL_SERIES;
                }
                return null;
            }

            private String getLow2byteOfInterfaceAddressOfWifiDirect(String str) {
                String[] strArrSplit = str.split(":");
                strArrSplit[4] = String.format("%02x", Integer.valueOf(((byte) (((byte) (Integer.parseInt(strArrSplit[4], 16) & 255)) ^ 128)) & UByte.MAX_VALUE));
                return strArrSplit[4] + strArrSplit[5];
            }

            private boolean matchWithBrotherNamingRegulation(String str, String str2) {
                String low2byteOfInterfaceAddressOfWifiDirect = getLow2byteOfInterfaceAddressOfWifiDirect(str2);
                return Pattern.compile(new StringBuilder("^DIRECT-[a-zA-Z0-9]{2}.*_BR").append(low2byteOfInterfaceAddressOfWifiDirect).append("$").toString()).matcher(str).find() || Pattern.compile(new StringBuilder("_BR").append(low2byteOfInterfaceAddressOfWifiDirect).append("$").toString()).matcher(str).find();
            }

            private boolean isInWhiteList(String str) {
                for (String str2 : this.mWhiteLists) {
                    if (str2.equals(str)) {
                        return true;
                    }
                }
                return false;
            }

            private boolean isInWhiteListOfDevicesWithSeries(String str) {
                for (String str2 : this.mWhiteListsWithSeries) {
                    if (str2.equals(str)) {
                        return true;
                    }
                }
                return false;
            }

            private String parseModelName(String str, boolean z) {
                if (z) {
                    if (str.startsWith("DIRECT")) {
                        str = str.substring(str.indexOf("-") + 3);
                    }
                    return str.substring(0, str.length() - 7);
                }
                if (str.startsWith("DIRECT")) {
                    return str.substring(str.indexOf("-") + 3);
                }
                return str.substring(0, str.length() - 2);
            }
        }
    }

    private class WFDConnectionEventListener implements WifiDirectNetworkController.IPeerEventListener, WifiP2pManager.ConnectionInfoListener {
        private static final int CONNECTION_WAIT_TIME = 60000;
        private static final int VALIDATION_TRY_COUNT = 200;
        private Thread mConnectionCheckThread;
        private WifiDirectDeviceManagerInterface.OnDeviceConnectionListener mOneTimeListener;
        private Timer mTimer;
        private WifiDirectInfo mWifiDirectInfo;

        public WFDConnectionEventListener(WifiDirectInfo wifiDirectInfo, WifiDirectDeviceManagerInterface.OnDeviceConnectionListener onDeviceConnectionListener) {
            Timer timer = new Timer(true);
            this.mTimer = timer;
            this.mConnectionCheckThread = null;
            this.mOneTimeListener = onDeviceConnectionListener;
            this.mWifiDirectInfo = wifiDirectInfo;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    WFDConnectionEventListener.this.mTimer.cancel();
                    WifiDirectDeviceManager.this.cancelConnect();
                    if (WFDConnectionEventListener.this.mOneTimeListener != null) {
                        WFDConnectionEventListener.this.mOneTimeListener.onConnectionFailure();
                        WFDConnectionEventListener.this.mOneTimeListener = null;
                    }
                }
            }, 60000L);
        }

        protected void finalize() {
            this.mTimer.cancel();
        }

        @Override
        public void onPeerEventNotified(WifiDirectNetworkController.WifiDirectPeerEvent wifiDirectPeerEvent) {
            if (wifiDirectPeerEvent == WifiDirectNetworkController.WifiDirectPeerEvent.CONNECTION_SUCCEEDED) {
                this.mTimer.cancel();
                WifiDirectDeviceManager.this.mController.requestConnectionInfo(this);
            } else if (wifiDirectPeerEvent == WifiDirectNetworkController.WifiDirectPeerEvent.CONNECTION_FAILED) {
                this.mTimer.cancel();
                WifiDirectDeviceManager.this.cancelConnect();
                WifiDirectDeviceManagerInterface.OnDeviceConnectionListener onDeviceConnectionListener = this.mOneTimeListener;
                if (onDeviceConnectionListener != null) {
                    onDeviceConnectionListener.onConnectionFailure();
                    this.mOneTimeListener = null;
                }
            }
        }

        @Override
        public void onConnectionInfoAvailable(final WifiP2pInfo wifiP2pInfo) {
            if (this.mConnectionCheckThread == null) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean z;
                        ConnectorDescriptor brotherMFCNetworkConnectorDescriptor;
                        try {
                            try {
                                z = false;
                                if (wifiP2pInfo.isGroupOwner) {
                                    String hostAddress = NetworkControllerManager.NetworkController.getBroadcastAddress(wifiP2pInfo.groupOwnerAddress, Inet4Address.getByName("255.255.255.255")).getHostAddress();
                                    String wifiDirectDeviceAddress = WFDConnectionEventListener.this.mWifiDirectInfo.getWifiDirectDeviceAddress();
                                    brotherMFCNetworkConnectorDescriptor = null;
                                    int i = 0;
                                    while (true) {
                                        int i2 = i + 1;
                                        if (i >= 200) {
                                            break;
                                        }
                                        for (ConnectorDescriptor connectorDescriptor : new BrotherMFCNetworkConnectorDiscovery(Arrays.asList(hostAddress)).discover()) {
                                            if (wifiDirectDeviceAddress.equals(connectorDescriptor.getStringValue(ConnectorDescriptor.DeviceQueryKey.WidiMac))) {
                                                brotherMFCNetworkConnectorDescriptor = connectorDescriptor;
                                            }
                                        }
                                        if (brotherMFCNetworkConnectorDescriptor != null) {
                                            break;
                                        } else {
                                            i = i2;
                                        }
                                    }
                                } else {
                                    brotherMFCNetworkConnectorDescriptor = new BrotherMFCNetworkConnectorDescriptor(wifiP2pInfo.groupOwnerAddress.getHostAddress());
                                }
                                if (brotherMFCNetworkConnectorDescriptor != null) {
                                    IConnector iConnectorCreateConnector = brotherMFCNetworkConnectorDescriptor.createConnector(CountrySpec.fromISO_3166_1_Alpha2(BrotherAndroidLib.getAndroidContext().getResources().getConfiguration().locale.getCountry()));
                                    iConnectorCreateConnector.getDevice().macaddress = WFDConnectionEventListener.this.mWifiDirectInfo.getWifiDirectDeviceAddress();
                                    if (WFDConnectionEventListener.this.mOneTimeListener != null) {
                                        WFDConnectionEventListener.this.mOneTimeListener.onDeviceConnected(iConnectorCreateConnector);
                                        WFDConnectionEventListener.this.mOneTimeListener = null;
                                    }
                                    WifiDirectDeviceManager.this.mState = WifiDirectDeviceManagerInterface.ConnectionState.CONNECTED;
                                    z = true;
                                }
                            } catch (RuntimeException e) {
                                e.printStackTrace();
                                throw e;
                            } catch (Exception unused) {
                                WifiDirectDeviceManager.this.disconnect();
                                if (WFDConnectionEventListener.this.mOneTimeListener == null) {
                                    return;
                                }
                            }
                            if (z) {
                                return;
                            }
                            WifiDirectDeviceManager.this.disconnect();
                            if (WFDConnectionEventListener.this.mOneTimeListener == null) {
                                return;
                            }
                            WFDConnectionEventListener.this.mOneTimeListener.onConnectionFailure();
                            WFDConnectionEventListener.this.mOneTimeListener = null;
                        } catch (Throwable th) {
                            WifiDirectDeviceManager.this.disconnect();
                            if (WFDConnectionEventListener.this.mOneTimeListener != null) {
                                WFDConnectionEventListener.this.mOneTimeListener.onConnectionFailure();
                                WFDConnectionEventListener.this.mOneTimeListener = null;
                            }
                            throw th;
                        }
                    }
                });
                this.mConnectionCheckThread = thread;
                thread.start();
            }
        }
    }
}
