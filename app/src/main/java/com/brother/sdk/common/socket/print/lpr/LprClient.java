package com.brother.sdk.common.socket.print.lpr;

import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.SocketClient;
import com.brother.sdk.common.socket.print.PrintState;
import java.io.IOException;

public class LprClient extends SocketClient {
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int CONNECT_TRYCOUNT = 3;
    private LprCommand mCommand;
    private ISocket mSocket = null;
    private boolean mCancel = false;

    public LprClient(LprCommand lprCommand) {
        this.mCommand = lprCommand;
    }

    public PrintState executeCommand() throws IOException {
        try {
            ISocket iSocket = this.mSocket;
            if (iSocket == null) {
                return PrintState.ErrorPrintConnectionFailure;
            }
            LprCommand lprCommand = this.mCommand;
            if (lprCommand == null) {
                return PrintState.ErrorPrintInvalidArgument;
            }
            return lprCommand.send(iSocket);
        } catch (IOException e) {
            if (this.mCancel) {
                return PrintState.ErrorPrintCancelJob;
            }
            throw e;
        }
    }

    @Override
    public SocketClient.ProtocolType getType() {
        return SocketClient.ProtocolType.LPR;
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
        try {
            LprCommand lprCommand = this.mCommand;
            if (lprCommand != null) {
                lprCommand.cancel();
            }
            e = null;
        } catch (IOException e) {
            e = e;
        }
        try {
            ISocket iSocket = this.mSocket;
            if (iSocket != null) {
                iSocket.cancel();
            }
        } catch (IOException e2) {
            e = e2;
        }
        if (e != null) {
            throw e;
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

    public static class LprContext {
        public int jobID = 0;
        public String user = "Android";
        public boolean postScriptPrint = true;
        public String hostName = "";
        public String document = "";

        public String getJobIDString() {
            return String.format("%03d", Integer.valueOf(this.jobID % 1000));
        }
    }
}
