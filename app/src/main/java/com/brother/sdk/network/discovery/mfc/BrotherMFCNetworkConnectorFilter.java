package com.brother.sdk.network.discovery.mfc;

import com.brother.sdk.common.ConnectorDescriptor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BrotherMFCNetworkConnectorFilter {
    static final List<String> Filter = Collections.unmodifiableList(new ArrayList<String>() {
        private static final long serialVersionUID = 1;

        {
            add(BrotherMFCMIBTable.TABLE.getMIBObject(ConnectorDescriptor.DeviceQueryKey.SerialNumber).getEntryOID());
            add(BrotherMFCMIBTable.TABLE.getMIBObject(ConnectorDescriptor.DeviceQueryKey.VendorID).getEntryOID());
            add(BrotherMFCMIBTable.TABLE.getMIBObject(ConnectorDescriptor.DeviceQueryKey.StatusDisplay).getEntryOID());
            add(BrotherMFCMIBTable.TABLE.getMIBObject(ConnectorDescriptor.DeviceQueryKey.InterfaceMacAddress).getEntryOID());
        }
    });

    BrotherMFCNetworkConnectorFilter() {
    }
}
