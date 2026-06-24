package com.brother.sdk.network.discovery;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.presets.BrotherDeviceBuilder;
import com.brother.sdk.network.NetworkConnector;

public class NetworkConnectorDescriptor extends ConnectorDescriptor {
    private static final String DEFAULT_DEVICE = "Default Device";
    private static final int WIFI_CONNECTOR_PORT = 161;
    private String mIPAddress;

    @Override
    public String getModelName() {
        return DEFAULT_DEVICE;
    }

    @Override
    public boolean support(ConnectorDescriptor.Function function) {
        return false;
    }

    public NetworkConnectorDescriptor(String str) {
        this.mIPAddress = str;
    }

    @Override
    public IConnector createConnector(CountrySpec countrySpec) {
        return new NetworkConnector(this.mIPAddress, new BrotherDeviceBuilder().build(this, countrySpec));
    }

    @Override
    public String getDescriptorIdentifier() {
        return this.mIPAddress;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NetworkConnectorDescriptor networkConnectorDescriptor = (NetworkConnectorDescriptor) obj;
        String str = this.mIPAddress;
        if (str == null) {
            if (networkConnectorDescriptor.mIPAddress != null) {
                return false;
            }
        } else if (!str.equals(networkConnectorDescriptor.mIPAddress)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.mIPAddress;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void queryValue(java.lang.String r6, com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject r7) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            if (r7 == 0) goto L8d
            com.brother.sdk.common.socket.devicemanagement.snmp.SnmpClient r1 = new com.brother.sdk.common.socket.devicemanagement.snmp.SnmpClient     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77 java.lang.RuntimeException -> L7d
            r1.<init>()     // Catch: java.lang.Throwable -> L75 java.lang.Exception -> L77 java.lang.RuntimeException -> L7d
            com.brother.sdk.network.BrDatagramSocket r0 = new com.brother.sdk.network.BrDatagramSocket     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            java.lang.String r2 = r5.mIPAddress     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            java.net.InetAddress r2 = java.net.InetAddress.getByName(r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r3 = 161(0xa1, float:2.26E-43)
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r1.bind(r0)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r7.initializeQuery()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            java.util.List r7 = r7.getEntries()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
        L23:
            boolean r0 = r7.hasNext()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            if (r0 == 0) goto L6b
            java.lang.Object r0 = r7.next()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject r0 = (com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject) r0     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            boolean r2 = r0.canQuery(r5)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            if (r2 == 0) goto L23
            com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject$QueryCondition r2 = r0.getQueryCondition()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest r3 = new com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r3.<init>()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            java.lang.String r4 = r0.getEntryOID()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r3.addVarbind(r4)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            int r4 = r2.timeout     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            int r2 = r2.tryCount     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult r2 = r1.requestGet(r3, r4, r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r3 = r2.state     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r4 = com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult.SnmpState.SuccessRequest     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            if (r3 != r4) goto L23
            com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind[] r2 = r2.results     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            r3 = 0
            r2 = r2[r3]     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject r2 = r2.getValue()     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            boolean r0 = r0.endQuery(r5, r6, r2)     // Catch: java.lang.Throwable -> L6d java.lang.Exception -> L70 java.lang.RuntimeException -> L72
            if (r0 == 0) goto L23
            r1.close()     // Catch: java.io.IOException -> L66
            goto L6a
        L66:
            r6 = move-exception
            r6.printStackTrace()
        L6a:
            return
        L6b:
            r0 = r1
            goto L8d
        L6d:
            r6 = move-exception
            r0 = r1
            goto L82
        L70:
            r0 = r1
            goto L77
        L72:
            r6 = move-exception
            r0 = r1
            goto L7e
        L75:
            r6 = move-exception
            goto L82
        L77:
            if (r0 == 0) goto L97
            r0.close()     // Catch: java.io.IOException -> L93
            goto L97
        L7d:
            r6 = move-exception
        L7e:
            r6.printStackTrace()     // Catch: java.lang.Throwable -> L75
            throw r6     // Catch: java.lang.Throwable -> L75
        L82:
            if (r0 == 0) goto L8c
            r0.close()     // Catch: java.io.IOException -> L88
            goto L8c
        L88:
            r7 = move-exception
            r7.printStackTrace()
        L8c:
            throw r6
        L8d:
            if (r0 == 0) goto L97
            r0.close()     // Catch: java.io.IOException -> L93
            goto L97
        L93:
            r6 = move-exception
            r6.printStackTrace()
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.network.discovery.NetworkConnectorDescriptor.queryValue(java.lang.String, com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject):void");
    }
}
