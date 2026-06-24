package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

public interface SnmpContextFace extends SnmpContextBasisFace {
    public static final String version_id = "@(#)$Id: SnmpContextFace.java,v 3.10 2002/10/10 15:13:57 birgit Exp $ Copyright Westhawk Ltd";

    String getCommunity();

    void setCommunity(String str);
}
