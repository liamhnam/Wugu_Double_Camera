package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

public class SnmpContext extends AbstractSnmpContext implements SnmpContextFace, Cloneable {
    public static final String Default_Community = "public";
    String community = Default_Community;

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public String getCommunity() {
        return this.community;
    }

    @Override
    public void setCommunity(String str) {
        this.community = str;
    }

    @Override
    public byte[] encodePacket(byte b, int i, int i2, int i3, Enumeration<varbind> enumeration) throws EncodingException, IOException {
        ByteArrayOutputStream byteArrayOutputStreamEncodeSNMP = new AsnEncoder().EncodeSNMP(this, b, i, i2, i3, enumeration);
        int size = byteArrayOutputStreamEncodeSNMP.size();
        if (size > this.maxRecvSize) {
            throw new EncodingException("Packet size (" + size + ") is > maximum size (" + this.maxRecvSize + ")");
        }
        return byteArrayOutputStreamEncodeSNMP.toByteArray();
    }

    @Override
    protected void ProcessIncomingMessage(AsnDecoder asnDecoder, ByteArrayInputStream byteArrayInputStream) throws DecodingException, IOException {
        AsnPduSequence asnPduSequenceDecodeSNMP = asnDecoder.DecodeSNMP(byteArrayInputStream, getCommunity());
        if (asnPduSequenceDecodeSNMP != null) {
            Integer numValueOf = Integer.valueOf(asnPduSequenceDecodeSNMP.getReqId());
            Pdu pdu = getPdu(numValueOf);
            if (pdu != null) {
                pdu.fillin(asnPduSequenceDecodeSNMP);
                return;
            } else {
                if (AsnObject.debug > 3) {
                    System.out.println("ProcessIncomingMessage(): No Pdu with reqid " + numValueOf.intValue());
                    return;
                }
                return;
            }
        }
        if (AsnObject.debug > 3) {
            System.out.println("ProcessIncomingMessage(): Error - missing seq input");
        }
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        try {
            SnmpContext snmpContext = new SnmpContext();
            snmpContext.setCommunity(this.community);
            return snmpContext;
        } catch (IOException e) {
            throw new CloneNotSupportedException("IOException " + e.getMessage());
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SnmpContext[, community=");
        stringBuffer.append(this.community);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
