package com.brother.sdk.common.socket.print.port9100;

import com.brother.sdk.common.Callback;
import com.brother.sdk.common.IReadStream;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.common.socket.print.PrintState;
import com.brother.sdk.common.util.Tool;
import java.io.IOException;

public class Port9100Client extends SocketClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int CONNECT_TRYCOUNT = 3;
    private static final int DATA_LENGTH = 8192;
    private static final int PROGRESS_END = 100;
    private ISocket mSocket = null;
    private boolean mCancel = false;

    public PrintState sendData(IReadStream iReadStream, Callback callback) throws IOException {
        try {
            if (this.mSocket == null) {
                return PrintState.ErrorPrintConnectionFailure;
            }
            if (iReadStream != null && callback != null) {
                byte[] bArr = new byte[8192];
                int length = iReadStream.length();
                Tool.ValueCoordinator valueCoordinator = new Tool.ValueCoordinator(length, 100);
                int i = 0;
                do {
                    callback.onNotifyProcessAlive();
                    int i2 = iReadStream.read(bArr, 0, 8192);
                    int i3 = 0;
                    do {
                        callback.onNotifyProcessAlive();
                        this.mSocket.write(bArr, i3, i2);
                        i3 += i2;
                    } while (i3 < i2);
                    i += i2;
                    callback.onUpdateProcessProgress(valueCoordinator.coordinateValueInRange(i));
                } while (i < length);
                return PrintState.Success;
            }
            return PrintState.ErrorPrintInvalidArgument;
        } catch (IOException e) {
            if (this.mCancel) {
                return PrintState.ErrorPrintCancelJob;
            }
            throw e;
        }
    }

    @Override
    public SocketClient.ProtocolType getType() {
        return SocketClient.ProtocolType.Port9100;
    }

    @Override
    public boolean bind(ISocket iSocket) throws IOException {
        if (iSocket == null || !iSocket.connect(10000, 3)) {
            return false;
        }
        this.mSocket = iSocket;
        return true;
    }

    @Override
    public void cancel() throws IOException {
        this.mCancel = true;
        ISocket iSocket = this.mSocket;
        if (iSocket != null) {
            iSocket.cancel();
        }
    }

    @Override
    public void close() throws IOException {
        ISocket iSocket = this.mSocket;
        if (iSocket != null) {
            iSocket.close();
            this.mSocket = null;
        }
    }
}
