package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

public class AsnEncoder {
    static byte[] dummyFingerPrint = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    ByteArrayOutputStream EncodeSNMP(SnmpContext snmpContext, byte b, int i, int i2, int i3, Enumeration<varbind> enumeration) throws IOException {
        AsnSequence asnSequence = new AsnSequence();
        asnSequence.add(new AsnInteger(0));
        asnSequence.add(new AsnOctets(snmpContext.getCommunity()));
        asnSequence.add(EncodePdu(b, i, i2, i3, enumeration));
        if (AsnObject.debug > 10) {
            System.out.println("\nAsnEncoder.EncodeSNMP(): ");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        asnSequence.write(byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    public AsnObject EncodePdu(byte b, int i, int i2, int i3, Enumeration<varbind> enumeration) throws IOException {
        AsnSequence asnSequence = new AsnSequence(b);
        asnSequence.add(new AsnInteger(i));
        asnSequence.add(new AsnInteger(i2));
        asnSequence.add(new AsnInteger(i3));
        AsnObject asnObjectAdd = asnSequence.add(new AsnSequence());
        while (enumeration.hasMoreElements()) {
            AsnObject asnObjectAdd2 = asnObjectAdd.add(new AsnSequence());
            varbind varbindVarNextElement = enumeration.nextElement();
            asnObjectAdd2.add(varbindVarNextElement.getOid());
            asnObjectAdd2.add(varbindVarNextElement.getValue());
        }
        return asnSequence;
    }
}
