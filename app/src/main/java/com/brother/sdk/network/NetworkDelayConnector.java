package com.brother.sdk.network;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.network.discovery.mfc.BrotherMFCSpecificFunctionDeviceDiscovery;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public class NetworkDelayConnector implements ISocketConnector {
    private static final int VALIDATE_TRY_COUNT = 4;
    private static final long serialVersionUID = -1391113976519768251L;
    private Device mDevice;
    private ConnectorDescriptor.Function mHandoverFunction;
    private CountrySpec mSpec;
    private String mIPAddress = null;
    private transient IConnector connector = null;

    public NetworkDelayConnector(Device device, CountrySpec countrySpec) {
        this.mDevice = null;
        CountrySpec countrySpec2 = CountrySpec.Others;
        this.mHandoverFunction = null;
        this.mDevice = device;
        this.mSpec = countrySpec;
    }

    @Override
    public Device getDevice() {
        IConnector iConnector = this.connector;
        return iConnector != null ? iConnector.getDevice() : this.mDevice;
    }

    public void postHandover(String str, ConnectorDescriptor.Function function) {
        this.mHandoverFunction = function;
        this.mIPAddress = str;
        validate();
    }

    @Override
    public boolean validate() {
        if (this.mHandoverFunction != null && this.mIPAddress != null) {
            BrotherMFCSpecificFunctionDeviceDiscovery brotherMFCSpecificFunctionDeviceDiscovery = new BrotherMFCSpecificFunctionDeviceDiscovery(this.mIPAddress, this.mHandoverFunction);
            for (int i = 0; i < 4; i++) {
                List<ConnectorDescriptor> listDiscover = brotherMFCSpecificFunctionDeviceDiscovery.discover();
                if (listDiscover.size() > 0) {
                    this.connector = listDiscover.get(0).createConnector(this.mSpec);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Job.JobState submit(Job job) {
        try {
            IConnector iConnector = this.connector;
            if (iConnector == null) {
                return Job.JobState.ErrorJobConnectionFailure;
            }
            return iConnector.submit(job);
        } finally {
            this.connector = null;
        }
    }

    @Override
    public Object getConnectorIdentifier() {
        return this.mIPAddress;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || IConnector.f477ID.equals(str) || ISocketConnector.f506ID.equals(str)) {
            return this;
        }
        return null;
    }

    static class C07181 {

        static final int[] f524xb20fb28f;

        static {
            int[] iArr = new int[SocketClient.ProtocolType.values().length];
            f524xb20fb28f = iArr;
            try {
                iArr[SocketClient.ProtocolType.LPR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f524xb20fb28f[SocketClient.ProtocolType.ScanCommand.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f524xb20fb28f[SocketClient.ProtocolType.SNMP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override
    public ISocket createConnectionSocket(SocketClient socketClient) {
        try {
            int i = C07181.f524xb20fb28f[socketClient.getType().ordinal()];
            if (i == 1) {
                BrSocket brSocket = new BrSocket(InetAddress.getByName(this.mIPAddress), 515);
                brSocket.setSoTimeout(10000);
                return brSocket;
            }
            if (i == 2) {
                BrSocket brSocket2 = new BrSocket(InetAddress.getByName(this.mIPAddress), 54921);
                brSocket2.setSoTimeout(10000);
                return brSocket2;
            }
            if (i != 3) {
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
}
