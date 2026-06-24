package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Enumeration;

public abstract class AbstractSnmpContext implements SnmpContextBasisFace {
    private Pdu[] pdus = new Pdu[20];
    protected int maxRecvSize = SnmpContextBasisFace.MSS;

    protected abstract void ProcessIncomingMessage(AsnDecoder asnDecoder, ByteArrayInputStream byteArrayInputStream) throws DecodingException, IOException;

    @Override
    public abstract byte[] encodePacket(byte b, int i, int i2, int i3, Enumeration<varbind> enumeration) throws EncodingException, IOException;

    @Override
    public abstract int getVersion();

    public int getMaxRecvSize() {
        return this.maxRecvSize;
    }

    public void setMaxRecvSize(int i) {
        this.maxRecvSize = i;
    }

    Pdu getPdu(Integer num) {
        return getPdu(num.intValue());
    }

    Pdu getPdu(int i) {
        for (int i2 = 0; i2 < 20; i2++) {
            Pdu pdu = this.pdus[i2];
            if (pdu != null && pdu.getReqId() == i) {
                return this.pdus[i2];
            }
        }
        return null;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean removePdu(int r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            r0 = 0
            r1 = r0
        L3:
            r2 = 20
            if (r1 >= r2) goto L20
            com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.Pdu[] r2 = r3.pdus     // Catch: java.lang.Throwable -> L1d
            r2 = r2[r1]     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L1a
            int r2 = r2.getReqId()     // Catch: java.lang.Throwable -> L1d
            if (r2 != r4) goto L1a
            com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.Pdu[] r4 = r3.pdus     // Catch: java.lang.Throwable -> L1d
            r0 = 0
            r4[r1] = r0     // Catch: java.lang.Throwable -> L1d
            r0 = 1
            goto L20
        L1a:
            int r1 = r1 + 1
            goto L3
        L1d:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L20:
            monitor-exit(r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.AbstractSnmpContext.removePdu(int):boolean");
    }

    @Override
    public boolean addPdu(Pdu pdu) throws IOException, PduException {
        for (int i = 0; i < 20; i++) {
            Pdu[] pduArr = this.pdus;
            if (pduArr[i] == null) {
                pduArr[i] = pdu;
                return true;
            }
        }
        return false;
    }

    protected AbstractSnmpContext() throws IOException {
    }

    public void decStart(ByteArrayInputStream byteArrayInputStream) {
        try {
            ProcessIncomingMessage(new AsnDecoder(), byteArrayInputStream);
        } catch (DecodingException e) {
            System.out.println("AbstractSnmpContext.run(): DecodingException: " + e.getMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            Pdu[] pduArr = this.pdus;
            if (pduArr[i] != null) {
                pduArr[i] = null;
            }
        }
    }
}
