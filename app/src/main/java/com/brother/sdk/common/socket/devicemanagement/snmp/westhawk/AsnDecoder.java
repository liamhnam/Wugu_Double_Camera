package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;

public class AsnDecoder {
    AsnSequence getAsnSequence(InputStream inputStream) throws IOException {
        return (AsnSequence) new AsnSequence().AsnReadHeader(inputStream);
    }

    int getSNMPVersion(AsnSequence asnSequence) throws DecodingException {
        AsnObject obj = asnSequence.getObj(0);
        if (obj instanceof AsnInteger) {
            return ((AsnInteger) obj).getValue();
        }
        throw new DecodingException("SNMP version should be of type AsnInteger instead of " + obj.getRespTypeString());
    }

    String getCommunity(AsnSequence asnSequence) throws DecodingException {
        AsnObject obj = asnSequence.getObj(1);
        if (obj instanceof AsnOctets) {
            return ((AsnOctets) obj).getValue();
        }
        throw new DecodingException("community should be of type AsnOctets instead of " + obj.getRespTypeString());
    }

    public AsnPduSequence DecodeSNMP(InputStream inputStream, String str) throws DecodingException, IOException {
        AsnSequence asnSequence = getAsnSequence(inputStream);
        int sNMPVersion = getSNMPVersion(asnSequence);
        if (sNMPVersion != 0) {
            throw new DecodingException("Wrong SNMP version: expected SNMPv1, received " + SnmpUtilities.getSnmpVersionString(sNMPVersion));
        }
        String community = getCommunity(asnSequence);
        if (!community.equals(str)) {
            throw new DecodingException("Wrong community: expected " + str + ", received " + community);
        }
        return (AsnPduSequence) asnSequence.findPdu();
    }
}
