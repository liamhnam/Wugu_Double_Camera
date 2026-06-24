package com.brother.sdk.network;

import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.socket.ISocket;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class BrDatagramSocket extends DatagramSocket implements ISocket {
    private InetAddress mInetAddress;
    private int mPort;

    @Override
    public boolean connect(int i, int i2) throws IOException {
        return true;
    }

    @Override
    public boolean remainReadBuffer() throws IOException {
        return false;
    }

    public BrDatagramSocket(InetAddress inetAddress, int i) throws SocketException {
        this.mInetAddress = inetAddress;
        this.mPort = i;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || ISocket.f505ID.equals(str)) {
            return this;
        }
        return null;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        send(new DatagramPacket(bArr, i2, this.mInetAddress, this.mPort));
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        receive(new DatagramPacket(bArr, bArr.length));
        return 0;
    }

    @Override
    public void cancel() throws IOException {
        disconnect();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public ISocket.ConnectionType getConnectionType() {
        return ISocket.ConnectionType.Network;
    }
}
