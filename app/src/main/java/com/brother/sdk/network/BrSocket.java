package com.brother.sdk.network;

import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.socket.ISocket;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class BrSocket extends Socket implements ISocket {
    private static final int SOCKET_CLOSE_WAITTIME = 30;
    private InetAddress mAddress;
    private DataInputStream mInputStream;
    private BufferedOutputStream mOutputStream;
    private int mPort;

    public BrSocket(InetAddress inetAddress, int i) {
        this.mAddress = inetAddress;
        this.mPort = i;
    }

    @Override
    public boolean connect(int i, int i2) throws IOException {
        if (isConnected()) {
            return false;
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(this.mAddress, this.mPort);
        do {
            i2--;
            if (i2 <= 0) {
                return false;
            }
            connect(inetSocketAddress, i);
        } while (!isConnected());
        setSoLinger(true, 30);
        this.mOutputStream = new BufferedOutputStream(getOutputStream());
        this.mInputStream = new DataInputStream(getInputStream());
        return true;
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.mOutputStream != null && !isOutputShutdown()) {
            this.mOutputStream.write(bArr, i, i2);
            this.mOutputStream.flush();
            return;
        }
        throw new SocketException("Socket could be already closed, or cancelled.");
    }

    @Override
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.mInputStream != null && !isInputShutdown()) {
            try {
                return this.mInputStream.read(bArr, i + 0, i2 + 0);
            } catch (SocketTimeoutException unused) {
                throw new SocketException("Socket Timeout");
            }
        }
        throw new SocketException("Socket could be already closed, or cancelled.");
    }

    @Override
    public void close() throws IOException {
        try {
            try {
                DataInputStream dataInputStream = this.mInputStream;
                if (dataInputStream != null) {
                    dataInputStream.close();
                    this.mInputStream = null;
                }
                e = null;
            } finally {
                super.close();
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            BufferedOutputStream bufferedOutputStream = this.mOutputStream;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
                this.mOutputStream = null;
            }
        } catch (IOException e2) {
            e = e2;
        }
        if (e == null) {
        } else {
            throw e;
        }
    }

    @Override
    public void cancel() throws IOException {
        try {
            shutdownInput();
            e = null;
        } catch (IOException e) {
            e = e;
        }
        try {
            shutdownOutput();
        } catch (IOException e2) {
            e = e2;
        }
        if (e != null) {
            throw e;
        }
    }

    @Override
    public boolean remainReadBuffer() throws IOException {
        return (this.mInputStream == null || isInputShutdown() || this.mInputStream.available() <= 0) ? false : true;
    }

    @Override
    public IUnknown queryInterface(String str) {
        if (IUnknown.f479ID.equals(str) || ISocket.f505ID.equals(str)) {
            return this;
        }
        return null;
    }

    @Override
    public ISocket.ConnectionType getConnectionType() {
        return ISocket.ConnectionType.Network;
    }
}
