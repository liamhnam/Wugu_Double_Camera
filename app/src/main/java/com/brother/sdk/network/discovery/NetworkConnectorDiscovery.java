package com.brother.sdk.network.discovery;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.ConnectorManager;
import com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnDecoder;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnPduSequence;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class NetworkConnectorDiscovery extends ConnectorManager {
    private static final int DISCOVERY_TIMEOUT = 3;
    private static final int EXECUTOR_AWAIT_TIME = 1000;
    private static final String SNMP_DISCOVERY_COMMUNITY = "public";
    private static final int SNMP_PORT = 161;
    private List<String> mBroadcastAddresses;
    private ExecutorService mExecutor;
    private List<String> mInitDeviceFilter;
    private int mRequestID = 0;
    private WifiConnectorDiscoveryTask mDiscovery = null;

    protected abstract NetworkConnectorDescriptor createWifiConnectorDescriptor(String str);

    protected int getDiscoveryTimeout() {
        return 3;
    }

    protected abstract List<String> getInitialDeviceFilter();

    public NetworkConnectorDiscovery(List<String> list) {
        this.mInitDeviceFilter = null;
        this.mExecutor = null;
        this.mBroadcastAddresses = list;
        this.mInitDeviceFilter = getInitialDeviceFilter();
        this.mExecutor = Executors.newSingleThreadExecutor();
    }

    protected void finalize() {
        WifiConnectorDiscoveryTask wifiConnectorDiscoveryTask = this.mDiscovery;
        if (wifiConnectorDiscoveryTask != null) {
            wifiConnectorDiscoveryTask.cancel();
            this.mExecutor.shutdown();
            try {
                if (this.mExecutor.awaitTermination(1000L, TimeUnit.MILLISECONDS)) {
                    return;
                }
                this.mExecutor.shutdownNow();
            } catch (Exception unused) {
                this.mExecutor.shutdownNow();
            }
        }
    }

    @Override
    public void startDiscover(ConnectorManager.OnDiscoverConnectorListener onDiscoverConnectorListener) {
        if (this.mDiscovery == null) {
            WifiConnectorDiscoveryTask wifiConnectorDiscoveryTask = new WifiConnectorDiscoveryTask(onDiscoverConnectorListener);
            this.mDiscovery = wifiConnectorDiscoveryTask;
            this.mExecutor.execute(wifiConnectorDiscoveryTask);
        }
    }

    @Override
    public void stopDiscover() {
        WifiConnectorDiscoveryTask wifiConnectorDiscoveryTask = this.mDiscovery;
        if (wifiConnectorDiscoveryTask != null) {
            wifiConnectorDiscoveryTask.cancel();
            this.mDiscovery = null;
        }
    }

    @Override
    public List<ConnectorDescriptor> discover() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.mBroadcastAddresses) {
            Iterator<String> it = getFilteredIPAddresses(str, str.endsWith(".255"), this.mInitDeviceFilter).iterator();
            while (it.hasNext()) {
                NetworkConnectorDescriptor networkConnectorDescriptorCreateWifiConnectorDescriptor = createWifiConnectorDescriptor(it.next());
                if (networkConnectorDescriptorCreateWifiConnectorDescriptor != null && !arrayList.contains(networkConnectorDescriptorCreateWifiConnectorDescriptor)) {
                    arrayList.add(networkConnectorDescriptorCreateWifiConnectorDescriptor);
                }
            }
        }
        return arrayList;
    }

    public List<String> getFilteredIPAddresses(String str, boolean z, List<String> list) throws Throwable {
        ArrayList arrayList = new ArrayList();
        DatagramSocket datagramSocket = null;
        try {
            SnmpRequest snmpRequest = new SnmpRequest(SnmpConstants.GET_REQ_MSG, this.mRequestID, 0, "public");
            snmpRequest.addVarbind(list);
            byte[] bArrMakePacket = snmpRequest.makePacket();
            DatagramSocket datagramSocket2 = new DatagramSocket();
            try {
                datagramSocket2.setBroadcast(z);
                datagramSocket2.setSoTimeout(getDiscoveryTimeout());
                send(datagramSocket2, str, bArrMakePacket);
                if (z) {
                    send(datagramSocket2, str, bArrMakePacket);
                    send(datagramSocket2, str, bArrMakePacket);
                }
                for (int i = 0; i < 16; i++) {
                    InetAddress inetAddressReceive = receive(datagramSocket2);
                    if (inetAddressReceive != null) {
                        String hostAddress = inetAddressReceive.getHostAddress();
                        if (!arrayList.contains(hostAddress)) {
                            arrayList.add(hostAddress);
                            if (!z) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                if (!datagramSocket2.isClosed()) {
                    datagramSocket2.close();
                }
            } catch (Exception unused) {
                datagramSocket = datagramSocket2;
                if (datagramSocket != null && !datagramSocket.isClosed()) {
                    datagramSocket.close();
                }
            } catch (Throwable th) {
                th = th;
                datagramSocket = datagramSocket2;
                if (datagramSocket != null && !datagramSocket.isClosed()) {
                    datagramSocket.close();
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        return arrayList;
    }

    private synchronized void send(DatagramSocket datagramSocket, String str, byte[] bArr) {
        try {
            datagramSocket.send(new DatagramPacket(bArr, bArr.length, InetAddress.getByName(str), SNMP_PORT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized InetAddress receive(DatagramSocket datagramSocket) {
        InetAddress address;
        address = null;
        try {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            datagramSocket.receive(datagramPacket);
            if (isValidPacket(new ByteArrayInputStream(datagramPacket.getData()))) {
                address = datagramPacket.getAddress();
            }
        } catch (SocketTimeoutException unused) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    private boolean isValidPacket(ByteArrayInputStream byteArrayInputStream) {
        try {
            AsnPduSequence asnPduSequenceDecodeSNMP = new AsnDecoder().DecodeSNMP(byteArrayInputStream, "public");
            int whatError = asnPduSequenceDecodeSNMP.getWhatError();
            int whereError = asnPduSequenceDecodeSNMP.getWhereError();
            if (whatError == 0 && whereError == 0) {
                return asnPduSequenceDecodeSNMP.getVarBind().getObjCount() > 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private class WifiConnectorDiscoveryTask implements Runnable {
        private boolean mCancel = false;
        private ConnectorManager.OnDiscoverConnectorListener mListener;

        public WifiConnectorDiscoveryTask(ConnectorManager.OnDiscoverConnectorListener onDiscoverConnectorListener) {
            this.mListener = onDiscoverConnectorListener;
        }

        @Override
        public void run() {
            ConnectorManager.OnDiscoverConnectorListener onDiscoverConnectorListener;
            while (!this.mCancel) {
                for (String str : NetworkConnectorDiscovery.this.mBroadcastAddresses) {
                    Iterator it = NetworkConnectorDiscovery.this.getFilteredIPAddresses(str, str.endsWith(".255"), NetworkConnectorDiscovery.this.mInitDeviceFilter).iterator();
                    while (it.hasNext()) {
                        NetworkConnectorDescriptor networkConnectorDescriptorCreateWifiConnectorDescriptor = NetworkConnectorDiscovery.this.createWifiConnectorDescriptor((String) it.next());
                        if (networkConnectorDescriptorCreateWifiConnectorDescriptor != null && (onDiscoverConnectorListener = this.mListener) != null) {
                            onDiscoverConnectorListener.onDiscover(networkConnectorDescriptorCreateWifiConnectorDescriptor);
                        }
                    }
                }
            }
        }

        public void cancel() {
            this.mCancel = true;
        }
    }
}
