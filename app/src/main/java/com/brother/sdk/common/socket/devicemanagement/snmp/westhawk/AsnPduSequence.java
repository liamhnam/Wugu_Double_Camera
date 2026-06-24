package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;

public class AsnPduSequence extends AsnSequence {
    @Override
    AsnObject findPdu() {
        return this;
    }

    AsnPduSequence(InputStream inputStream, int i, int i2) throws IOException {
        super(inputStream, i, i2);
    }

    int getReqId() {
        return ((AsnInteger) getObj(0)).getValue();
    }

    public int getWhatError() {
        return ((AsnInteger) getObj(1)).getValue();
    }

    public int getWhereError() {
        return ((AsnInteger) getObj(2)).getValue();
    }

    public AsnSequence getVarBind() {
        return (AsnSequence) getObj(3);
    }

    int getValue() {
        return ((AsnInteger) ((AsnSequence) ((AsnSequence) getObj(3)).getObj(0)).getObj(1)).getValue();
    }

    boolean hadError() {
        return getWhatError() != 0;
    }
}
