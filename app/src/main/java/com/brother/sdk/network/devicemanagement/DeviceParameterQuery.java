package com.brother.sdk.network.devicemanagement;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.socket.ISocketConnector;

public class DeviceParameterQuery {
    private DeviceManagerDescriptor mQueryManager;

    public DeviceParameterQuery(IConnector iConnector) {
        this.mQueryManager = new DeviceManagerDescriptor(iConnector);
    }

    public boolean queryValue(String str, int i, int i2) {
        return this.mQueryManager.queryValue(str, i, i2);
    }

    private static class DeviceManagerDescriptor extends ConnectorDescriptor {
        private ISocketConnector mConnector;

        @Override
        public IConnector createConnector(CountrySpec countrySpec) {
            return null;
        }

        @Override
        public String getDescriptorIdentifier() {
            return null;
        }

        @Override
        public String getModelName() {
            return null;
        }

        @Override
        public boolean support(ConnectorDescriptor.Function function) {
            return false;
        }

        private DeviceManagerDescriptor(IConnector iConnector) {
            this.mConnector = null;
            IUnknown iUnknownQueryInterface = iConnector.queryInterface(ISocketConnector.f506ID);
            if (iUnknownQueryInterface != null) {
                this.mConnector = (ISocketConnector) iUnknownQueryInterface;
            }
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean queryValue(java.lang.String r9, int r10, int r11) throws java.lang.Throwable {
            /*
                r8 = this;
                r0 = 0
                r1 = 0
                com.brother.sdk.common.socket.ISocketConnector r2 = r8.mConnector     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lb5
                if (r2 != 0) goto L7
                return r0
            L7:
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpClient r2 = new com.brother.sdk.common.socket.devicemanagement.snmp.SnmpClient     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lb5
                r2.<init>()     // Catch: java.lang.Throwable -> La9 java.lang.Exception -> Lb5
                com.brother.sdk.common.socket.ISocketConnector r1 = r8.mConnector     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.ISocket r1 = r1.createConnectionSocket(r2)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                boolean r1 = r2.bind(r1)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r1 != 0) goto L21
                r2.close()     // Catch: java.io.IOException -> L1c
                goto L20
            L1c:
                r9 = move-exception
                r9.printStackTrace()
            L20:
                return r0
            L21:
                com.brother.sdk.network.discovery.mfc.BrotherMFCMIBTable r1 = com.brother.sdk.network.discovery.mfc.BrotherMFCMIBTable.TABLE     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject r1 = r1.getMIBObject(r9)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r3 = 1
                if (r1 == 0) goto L74
                r1.initializeQuery()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                java.util.List r1 = r1.getEntries()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
            L35:
                boolean r4 = r1.hasNext()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r4 == 0) goto La0
                java.lang.Object r4 = r1.next()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject r4 = (com.brother.sdk.common.socket.devicemanagement.mib.DeviceMIBQueryObject) r4     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                boolean r5 = r4.canQuery(r8)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r5 == 0) goto L35
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest r5 = new com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r5.<init>()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                java.lang.String r6 = r4.getEntryOID()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r5.addVarbind(r6)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult r5 = r2.requestGet(r5, r10, r11)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r6 = r5.state     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r7 = com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult.SnmpState.SuccessRequest     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r6 != r7) goto L35
                com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind[] r5 = r5.results     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r5 = r5[r0]     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject r5 = r5.getValue()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                boolean r4 = r4.endQuery(r8, r9, r5)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r4 == 0) goto L35
                r2.close()     // Catch: java.io.IOException -> L6f
                goto L73
            L6f:
                r9 = move-exception
                r9.printStackTrace()
            L73:
                return r3
            L74:
                boolean r1 = isOIDString(r9)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r1 == 0) goto La0
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest r1 = new com.brother.sdk.common.socket.devicemanagement.snmp.SnmpRequest     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r1.<init>()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r1.addVarbind(r9)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult r10 = r2.requestGet(r1, r10, r11)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r11 = r10.state     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult$SnmpState r1 = com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult.SnmpState.SuccessRequest     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                if (r11 != r1) goto La0
                com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind[] r10 = r10.results     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r10 = r10[r0]     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnObject r10 = r10.getValue()     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r8.setConnectorValue(r9, r10)     // Catch: java.lang.Throwable -> La4 java.lang.Exception -> La7
                r2.close()     // Catch: java.io.IOException -> L9b
                goto L9f
            L9b:
                r9 = move-exception
                r9.printStackTrace()
            L9f:
                return r3
            La0:
                r2.close()     // Catch: java.io.IOException -> Lbb
                goto Lbf
            La4:
                r9 = move-exception
                r1 = r2
                goto Laa
            La7:
                r1 = r2
                goto Lb5
            La9:
                r9 = move-exception
            Laa:
                if (r1 == 0) goto Lb4
                r1.close()     // Catch: java.io.IOException -> Lb0
                goto Lb4
            Lb0:
                r10 = move-exception
                r10.printStackTrace()
            Lb4:
                throw r9
            Lb5:
                if (r1 == 0) goto Lbf
                r1.close()     // Catch: java.io.IOException -> Lbb
                goto Lbf
            Lbb:
                r9 = move-exception
                r9.printStackTrace()
            Lbf:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.network.devicemanagement.DeviceParameterQuery.DeviceManagerDescriptor.queryValue(java.lang.String, int, int):boolean");
        }

        private static boolean isOIDString(String str) {
            for (String str2 : str.split(".")) {
                if (Integer.getInteger(str2) == null) {
                    return false;
                }
            }
            return true;
        }
    }
}
