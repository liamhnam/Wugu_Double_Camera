package com.brother.sdk.common.socket.devicemanagement.snmp.westhawk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AsnNull extends AsnObject {
    public int hashCode() {
        return 5;
    }

    @Override
    public String toString() {
        return "AsnNull";
    }

    public AsnNull() {
    }

    public AsnNull(InputStream inputStream, int i) {
        this();
    }

    @Override
    void write(OutputStream outputStream, int i) throws IOException {
        AsnBuildHeader(outputStream, (byte) 5, 0);
    }

    public boolean equals(Object obj) {
        return this == obj || (obj instanceof AsnNull);
    }
}
