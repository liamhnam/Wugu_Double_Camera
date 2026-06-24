package com.brother.sdk.network;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.brother.sdk.BrotherAndroidLib;
import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.network.discovery.mfc.BrotherMFCNetworkNodeNameDiscovery;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NetworkConnector implements ISocketConnector {
    private static final int DEFAULT_SUBNET_LENGTH = 24;
    private static final int VALIDATE_TRY_COUNT = 4;
    private static final long serialVersionUID = 6114680802727127598L;
    private Device mDevice;
    private String mIPAddress;

    public NetworkConnector(String str, Device device) {
        this.mIPAddress = str;
        this.mDevice = device;
    }

    static class C07171 {

        static final int[] f523xb20fb28f;

        static {
            int[] iArr = new int[SocketClient.ProtocolType.values().length];
            f523xb20fb28f = iArr;
            try {
                iArr[SocketClient.ProtocolType.LPR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f523xb20fb28f[SocketClient.ProtocolType.Port9100.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f523xb20fb28f[SocketClient.ProtocolType.ScanCommand.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f523xb20fb28f[SocketClient.ProtocolType.SNMP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override
    public ISocket createConnectionSocket(SocketClient socketClient) {
        try {
            int i = C07171.f523xb20fb28f[socketClient.getType().ordinal()];
            if (i == 1) {
                BrSocket brSocket = new BrSocket(InetAddress.getByName(this.mIPAddress), 515);
                brSocket.setSoTimeout(10000);
                return brSocket;
            }
            if (i == 2) {
                BrSocket brSocket2 = new BrSocket(InetAddress.getByName(this.mIPAddress), 9100);
                brSocket2.setSoTimeout(10000);
                return brSocket2;
            }
            if (i == 3) {
                BrSocket brSocket3 = new BrSocket(InetAddress.getByName(this.mIPAddress), 54921);
                brSocket3.setSoTimeout(10000);
                return brSocket3;
            }
            if (i != 4) {
                return null;
            }
            BrDatagramSocket brDatagramSocket = new BrDatagramSocket(InetAddress.getByName(this.mIPAddress), 161);
            brDatagramSocket.setSoTimeout(10000);
            return brDatagramSocket;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getConnectorIdentifier() {
        return this.mIPAddress;
    }

    @Override
    public Device getDevice() {
        return this.mDevice;
    }

    @Override
    public boolean validate() {
        Iterator<String> it = getBroadcastAddress(this.mIPAddress).iterator();
        while (it.hasNext()) {
            BrotherMFCNetworkNodeNameDiscovery brotherMFCNetworkNodeNameDiscovery = new BrotherMFCNetworkNodeNameDiscovery(this.mDevice.nodeName, it.next());
            for (int i = 0; i < 4; i++) {
                List<ConnectorDescriptor> listDiscover = brotherMFCNetworkNodeNameDiscovery.discover();
                if (listDiscover.size() > 0) {
                    this.mIPAddress = listDiscover.get(0).getDescriptorIdentifier();
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || IConnector.f477ID.equals(str) || ISocketConnector.f506ID.equals(str)) {
            return this;
        }
        return null;
    }

    @Override
    public Job.JobState submit(Job job) {
        if (job.bind(this)) {
            return job.commit();
        }
        return Job.JobState.ErrorJobConnectionFailure;
    }

    private List<String> getBroadcastAddress(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        String broadcastAddressForSubnet = getBroadcastAddressForSubnet(str, getSubnetLength());
        if (broadcastAddressForSubnet != null && broadcastAddressForSubnet.length() > 1) {
            arrayList.add(broadcastAddressForSubnet);
        }
        arrayList.add("255.255.255.255");
        return arrayList;
    }

    private static int getSubnetLength() {
        WifiManager wifiManager;
        try {
            Context androidContext = BrotherAndroidLib.getAndroidContext();
            if (androidContext == null || (wifiManager = (WifiManager) androidContext.getSystemService("wifi")) == null || wifiManager.getWifiState() != 3) {
                return 24;
            }
            return wifiManager.getDhcpInfo().netmask;
        } catch (Exception unused) {
            return 24;
        }
    }

    private static String getBroadcastAddressForSubnet(String str, int i) {
        try {
            InetAddress byName = InetAddress.getByName(str);
            if (byName == null) {
                return null;
            }
            byte[] address = byName.getAddress();
            int i2 = i / 8;
            int i3 = i % 8;
            for (int i4 = 0; i4 < address.length; i4++) {
                if (i2 == i4) {
                    address[i4] = (byte) (address[i4] | ((byte) ((255 << i3) >> i3)));
                } else if (i4 > i2) {
                    address[i4] = -1;
                }
            }
            return InetAddress.getByAddress(address).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
