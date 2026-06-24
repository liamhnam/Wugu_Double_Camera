package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

public class DecodingException extends PduException {
    private static final long serialVersionUID = -2440685299510554136L;

    public DecodingException() {
    }

    public DecodingException(String str) {
        super(str);
    }
}
