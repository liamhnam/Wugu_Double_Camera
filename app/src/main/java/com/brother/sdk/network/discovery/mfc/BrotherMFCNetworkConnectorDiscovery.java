package com.brother.sdk.network.discovery.mfc;

import com.brother.sdk.network.discovery.NetworkConnectorDescriptor;
import com.brother.sdk.network.discovery.NetworkConnectorDiscovery;
import java.util.List;

public class BrotherMFCNetworkConnectorDiscovery extends NetworkConnectorDiscovery {
    public BrotherMFCNetworkConnectorDiscovery(List<String> list) {
        super(list);
    }

    @Override
    protected NetworkConnectorDescriptor createWifiConnectorDescriptor(String str) {
        return new BrotherMFCNetworkConnectorDescriptor(str);
    }

    @Override
    protected List<String> getInitialDeviceFilter() {
        return BrotherMFCNetworkConnectorFilter.Filter;
    }
}
