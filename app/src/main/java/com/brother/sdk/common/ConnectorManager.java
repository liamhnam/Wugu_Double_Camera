package com.brother.sdk.common;

import java.util.List;

public abstract class ConnectorManager {

    public interface OnDiscoverConnectorListener {
        void onDiscover(ConnectorDescriptor connectorDescriptor);
    }

    public abstract List<ConnectorDescriptor> discover();

    public abstract void startDiscover(OnDiscoverConnectorListener onDiscoverConnectorListener);

    public abstract void stopDiscover();
}
