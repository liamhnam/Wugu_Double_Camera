package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.util.Enumeration;

public interface SnmpContextBasisFace {
    public static final int MAXPDU = 20;
    public static final int MSS = 1300;
    public static final String version_id = "@(#)$Id: SnmpContextBasisFace.java,v 3.3 2002/10/10 15:13:57 birgit Exp $ Copyright Westhawk Ltd";

    boolean addPdu(Pdu pdu) throws IOException, PduException;

    byte[] encodePacket(byte b, int i, int i2, int i3, Enumeration<varbind> enumeration) throws EncodingException, IOException;

    int getVersion();

    boolean removePdu(int i);
}
