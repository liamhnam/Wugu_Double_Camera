package com.brother.sdk.common.socket;

import com.brother.sdk.common.IUnknown;
import java.io.IOException;

public interface ISocket extends IUnknown {

    public static final String f505ID = "ISocket";

    public enum ConnectionType {
        Network,
        USB,
        Bluetooth
    }

    void cancel() throws IOException;

    void close() throws IOException;

    boolean connect(int i, int i2) throws IOException;

    ConnectionType getConnectionType();

    int read(byte[] bArr, int i, int i2) throws IOException;

    boolean remainReadBuffer() throws IOException;

    void setSoTimeout(int i) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
