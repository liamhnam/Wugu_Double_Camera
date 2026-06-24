package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.common.socket.scan.ScanState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ScanCommandClient extends SocketClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int CONNECT_TRYCOUNT = 3;
    private ScanCommandCallback mCallback;
    private ISocket mSocket = null;
    private boolean mCancel = false;

    public ScanCommandClient(ScanCommandCallback scanCommandCallback) {
        this.mCallback = scanCommandCallback;
    }

    public ScanState requestCommand(ScanCommand scanCommand) throws IOException {
        ScanCommandCallback scanCommandCallback;
        if (this.mCancel) {
            if (this.mSocket != null) {
                new CancelThread().start();
            }
            return ScanState.ErrorScanApplicationHostError;
        }
        try {
            ISocket iSocket = this.mSocket;
            if (iSocket == null) {
                return ScanState.ErrorScanConnectionFailure;
            }
            if (scanCommand != null && (scanCommandCallback = this.mCallback) != null) {
                return scanCommand.commit(iSocket, scanCommandCallback);
            }
            return ScanState.ErrorScanInvalidArgument;
        } catch (IOException e) {
            if (this.mCancel) {
                return ScanState.ErrorScanApplicationHostError;
            }
            throw e;
        }
    }

    @Override
    public SocketClient.ProtocolType getType() {
        return SocketClient.ProtocolType.ScanCommand;
    }

    @Override
    public boolean bind(ISocket iSocket) throws IOException {
        boolean z = false;
        if (iSocket == null || !iSocket.connect(10000, 3)) {
            return false;
        }
        if (iSocket.getConnectionType() == ISocket.ConnectionType.Network) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[64];
                do {
                    int i = iSocket.read(bArr, 0, 64);
                    if (i <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } while (iSocket.remainReadBuffer());
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (new String(byteArray, 0, byteArray.length, "UTF-8").contains("+OK")) {
                    this.mSocket = iSocket;
                    z = true;
                }
                if (!z) {
                }
                return z;
            } finally {
                iSocket.close();
            }
        }
        this.mSocket = iSocket;
        return true;
    }

    @Override
    public void cancel() throws IOException {
        this.mCancel = true;
        new CancelThread().start();
    }

    @Override
    public void close() throws IOException {
        ISocket iSocket = this.mSocket;
        if (iSocket != null) {
            iSocket.close();
            this.mSocket = null;
        }
    }

    class CancelThread extends Thread {
        CancelThread() {
        }

        @Override
        public void run() {
            try {
                if (ScanCommandClient.this.mSocket != null) {
                    new ScanCommandCancelScan().commit(ScanCommandClient.this.mSocket, ScanCommandClient.this.mCallback);
                    ScanCommandClient.this.mSocket.cancel();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
