package com.brother.sdk.common.socket.devicemanagement.snmp;

import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.common.socket.devicemanagement.snmp.SnmpResult;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnDecoder;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AsnSequence;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.varbind;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class SnmpClient extends SocketClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int CONNECT_TRYCOUNT = 3;
    private ISocket mSocket = null;
    private boolean mCancel = false;

    public SnmpResult requestGet(SnmpRequest snmpRequest, int i, int i2) throws IOException {
        try {
            byte[] bArrMakePacket = snmpRequest.makePacket();
            SnmpResult snmpResult = null;
            if (this.mSocket != null && bArrMakePacket != null) {
                for (int i3 = 0; i3 < i2; i3++) {
                    try {
                        this.mSocket.setSoTimeout(i);
                        this.mSocket.write(bArrMakePacket, 0, bArrMakePacket.length);
                        byte[] bArr = new byte[1024];
                        this.mSocket.setSoTimeout(i);
                        this.mSocket.read(bArr, 0, 1024);
                        snmpResult = decResPacket(bArr, snmpRequest.getCommunity());
                    } catch (SocketTimeoutException unused) {
                    }
                    if (snmpResult != null) {
                        break;
                    }
                    this.mSocket.setSoTimeout(0);
                }
            }
            if (snmpResult == null) {
                snmpResult = new SnmpResult();
                snmpResult.state = bArrMakePacket == null ? SnmpResult.SnmpState.ErrorRequestInvalid : SnmpResult.SnmpState.ErrorRequestTimeout;
            }
            return snmpResult;
        } catch (IOException e) {
            if (this.mCancel) {
                SnmpResult snmpResult2 = new SnmpResult();
                snmpResult2.state = SnmpResult.SnmpState.ErrorRequestCancel;
                return snmpResult2;
            }
            throw e;
        }
    }

    @Override
    public SocketClient.ProtocolType getType() {
        return SocketClient.ProtocolType.SNMP;
    }

    private SnmpResult decResPacket(byte[] bArr, String str) {
        ?? r0 = 0;
        try {
            ?? DecodeSNMP = new AsnDecoder().DecodeSNMP(new ByteArrayInputStream(bArr), str);
            int whatError = DecodeSNMP.getWhatError();
            int whereError = DecodeSNMP.getWhereError();
            try {
                if (whatError == 0 && whereError == 0) {
                    AsnSequence varBind = DecodeSNMP.getVarBind();
                    int objCount = varBind.getObjCount();
                    varbind[] varbindVarArr = new varbind[objCount];
                    for (int i = 0; i < objCount; i++) {
                        varbindVarArr[i] = new varbind((AsnSequence) varBind.getObj(i));
                    }
                    SnmpResult snmpResult = new SnmpResult();
                    snmpResult.state = SnmpResult.SnmpState.SuccessRequest;
                    snmpResult.results = varbindVarArr;
                    DecodeSNMP = snmpResult;
                } else {
                    SnmpResult snmpResult2 = new SnmpResult();
                    snmpResult2.state = SnmpResult.SnmpState.ErrorRequestNoSuchName;
                    snmpResult2.results = null;
                    DecodeSNMP = snmpResult2;
                }
                return DecodeSNMP;
            } catch (Exception e) {
                r0 = DecodeSNMP;
                e = e;
                e.printStackTrace();
                return r0;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override
    public boolean bind(ISocket iSocket) throws IOException {
        if (iSocket == null || !iSocket.connect(10000, 3)) {
            return false;
        }
        this.mSocket = iSocket;
        return true;
    }

    @Override
    public void cancel() throws IOException {
        this.mCancel = true;
        ISocket iSocket = this.mSocket;
        if (iSocket != null) {
            iSocket.cancel();
        }
    }

    @Override
    public void close() throws IOException {
        ISocket iSocket = this.mSocket;
        if (iSocket != null) {
            iSocket.close();
        }
    }
}
