package com.brother.sdk.network.discovery.mfc;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnOctets;
import com.brother.sdk.network.discovery.NetworkConnectorDescriptor;
import com.brother.sdk.network.discovery.NetworkConnectorDiscovery;
import java.util.Arrays;
import java.util.List;

public class BrotherMFCNetworkNodeNameDiscovery extends NetworkConnectorDiscovery {
    private static final int NODENAME_DISCOVERY_TIMEOUT = 300;
    private String mNodeName;

    @Override
    protected int getDiscoveryTimeout() {
        return 300;
    }

    public BrotherMFCNetworkNodeNameDiscovery(String str, String str2) {
        super(Arrays.asList(str2));
        this.mNodeName = str;
    }

    @Override
    protected NetworkConnectorDescriptor createWifiConnectorDescriptor(String str) {
        NodeNameValidateConnectorDescriptor nodeNameValidateConnectorDescriptor = new NodeNameValidateConnectorDescriptor(str);
        if (this.mNodeName.equals(nodeNameValidateConnectorDescriptor.getNodeName())) {
            return nodeNameValidateConnectorDescriptor;
        }
        return null;
    }

    @Override
    protected List<String> getInitialDeviceFilter() {
        return BrotherMFCNetworkConnectorFilter.Filter;
    }

    private static class NodeNameValidateConnectorDescriptor extends NetworkConnectorDescriptor {
        NodeNameValidateConnectorDescriptor(String str) {
            super(str);
            queryValue(ConnectorDescriptor.DeviceQueryKey.NodeName, BrotherMFCMIBTable.TABLE.getMIBObject(ConnectorDescriptor.DeviceQueryKey.NodeName));
        }

        String getNodeName() {
            AsnObject connectorValue = getConnectorValue(ConnectorDescriptor.DeviceQueryKey.NodeName);
            if (connectorValue == null || connectorValue.getRespType() != 4) {
                return null;
            }
            return ((AsnOctets) connectorValue).getValue();
        }
    }
}
