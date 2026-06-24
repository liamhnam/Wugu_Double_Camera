package com.brother.sdk.common.socket.print.lpr;

import com.brother.sdk.common.Callback;
import com.brother.sdk.common.IReadStream;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.print.PrintState;
import com.brother.sdk.common.socket.print.lpr.LprClient;
import com.brother.sdk.common.util.Tool;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.StandardStructureTypes;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LprReceiveJobCommand extends LprCommand {
    private Callback mCallback;
    private Command mCancelCommand;
    private List<Command> mCommands;
    private ISocket mConnection = null;
    private LprClient.LprContext mContext;
    private IReadStream mDataStream;

    public LprReceiveJobCommand(LprClient.LprContext lprContext, IReadStream iReadStream, Callback callback) throws UnsupportedEncodingException {
        this.mCommands = null;
        this.mCancelCommand = null;
        this.mContext = lprContext;
        this.mDataStream = iReadStream;
        this.mCallback = callback;
        this.mCommands = Arrays.asList(new ReceiveJobStart(), new ReceiveControlFile(), new ReceiveDataFile());
        this.mCancelCommand = new AbortJob();
    }

    @Override
    PrintState send(ISocket iSocket) throws IOException {
        this.mConnection = iSocket;
        PrintState printStateSend = PrintState.ErrorPrintNoResponse;
        Iterator<Command> it = this.mCommands.iterator();
        while (it.hasNext() && (printStateSend = it.next().send(iSocket)) == PrintState.Success) {
        }
        return printStateSend;
    }

    @Override
    void cancel() throws IOException {
        ISocket iSocket = this.mConnection;
        if (iSocket != null) {
            this.mCancelCommand.send(iSocket);
        }
    }

    private abstract class Command {
        protected static final int CHECK_RETRY = 10;
        protected static final int CHECK_WAIT_MS = 200;

        protected static final String f507LF = "\n";
        protected static final String UTF8 = "UTF-8";

        abstract PrintState send(ISocket iSocket) throws IOException;

        private Command() {
        }

        protected PrintState checkSendSuccess(ISocket iSocket) throws IOException {
            byte[] bArr = new byte[10];
            int i = 10;
            while (true) {
                int i2 = i - 1;
                if (i > 0) {
                    LprReceiveJobCommand.this.mCallback.onNotifyProcessAlive();
                    try {
                        int i3 = iSocket.read(bArr, 0, 10);
                        if (i3 > 0) {
                            for (int i4 = 0; i4 < i3; i4++) {
                                if (bArr[i4] == 0) {
                                    return PrintState.Success;
                                }
                            }
                        }
                        if (i2 > 0) {
                            try {
                                Thread.sleep(200L);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        i = i2;
                    } catch (SocketException unused) {
                        return PrintState.ErrorPrintConnectionFailure;
                    }
                } else {
                    return PrintState.ErrorPrint;
                }
            }
        }

        protected PrintState send(ISocket iSocket, byte[] bArr) throws IOException {
            int length = bArr.length;
            int i = 0;
            do {
                LprReceiveJobCommand.this.mCallback.onNotifyProcessAlive();
                iSocket.write(bArr, i, length);
                i += length;
            } while (i < length);
            return checkSendSuccess(iSocket);
        }
    }

    private class ReceiveJobStart extends Command {
        private byte[] mData;

        ReceiveJobStart() throws UnsupportedEncodingException {
            super();
            this.mData = null;
            this.mData = "\u0002binary_P1\n".getBytes("UTF-8");
        }

        @Override
        PrintState send(ISocket iSocket) throws IOException {
            return send(iSocket, this.mData);
        }
    }

    private class AbortJob extends Command {
        private byte[] mData;

        AbortJob() throws UnsupportedEncodingException {
            super();
            this.mData = null;
            this.mData = "\u0001\n".getBytes("UTF-8");
        }

        @Override
        PrintState send(ISocket iSocket) throws IOException {
            return send(iSocket, this.mData);
        }
    }

    private class ReceiveControlFile extends Command {
        private byte[] mData;
        private Header mHeader;

        ReceiveControlFile() throws UnsupportedEncodingException {
            super();
            this.mHeader = null;
            this.mData = null;
            this.mData = ((((((StandardStructureTypes.f2367H + LprReceiveJobCommand.this.mContext.hostName + "\n") + "P" + LprReceiveJobCommand.this.mContext.user + "\n") + (LprReceiveJobCommand.this.mContext.postScriptPrint ? "o" : TtmlNode.TAG_P) + "dfA" + LprReceiveJobCommand.this.mContext.getJobIDString() + LprReceiveJobCommand.this.mContext.hostName + "\n") + "UdfA" + LprReceiveJobCommand.this.mContext.getJobIDString() + LprReceiveJobCommand.this.mContext.hostName + "\n") + "N" + LprReceiveJobCommand.this.mContext.document + "\n") + "\u0000").getBytes("UTF-8");
            this.mHeader = new Header(this.mData);
        }

        @Override
        PrintState send(ISocket iSocket) throws IOException {
            PrintState printStateSend = send(iSocket, this.mHeader.mData);
            return printStateSend == PrintState.Success ? send(iSocket, this.mData) : printStateSend;
        }

        private class Header {
            private byte[] mData;

            Header(byte[] bArr) throws UnsupportedEncodingException {
                this.mData = null;
                this.mData = ("\u0002" + Integer.toString(bArr.length - 1) + " cfA" + LprReceiveJobCommand.this.mContext.getJobIDString() + LprReceiveJobCommand.this.mContext.hostName + "\n").getBytes("UTF-8");
            }
        }
    }

    private class ReceiveDataFile extends Command {
        private Header mHeader;

        ReceiveDataFile() throws UnsupportedEncodingException {
            super();
            this.mHeader = null;
            this.mHeader = new Header();
        }

        @Override
        PrintState send(ISocket iSocket) throws IOException {
            PrintState printStateSend = send(iSocket, this.mHeader.mData);
            return printStateSend == PrintState.Success ? sendData(iSocket) : printStateSend;
        }

        private PrintState sendData(ISocket iSocket) throws IOException {
            byte[] bArr = new byte[8192];
            int length = LprReceiveJobCommand.this.mDataStream.length();
            Tool.ValueCoordinator valueCoordinator = new Tool.ValueCoordinator(length, 100);
            int i = 0;
            do {
                LprReceiveJobCommand.this.mCallback.onNotifyProcessAlive();
                int i2 = LprReceiveJobCommand.this.mDataStream.read(bArr, 0, 8192);
                int i3 = 0;
                do {
                    LprReceiveJobCommand.this.mCallback.onNotifyProcessAlive();
                    iSocket.write(bArr, i3, i2);
                    i3 += i2;
                } while (i3 < i2);
                i += i2;
                LprReceiveJobCommand.this.mCallback.onUpdateProcessProgress(valueCoordinator.coordinateValueInRange(i));
            } while (i < length);
            bArr[0] = 0;
            iSocket.write(bArr, 0, 1);
            checkSendSuccess(iSocket);
            return PrintState.Success;
        }

        private class Header {
            private byte[] mData;

            Header() throws UnsupportedEncodingException {
                this.mData = null;
                this.mData = ("\u0003" + Integer.toString(LprReceiveJobCommand.this.mDataStream.length()) + " dfA" + LprReceiveJobCommand.this.mContext.getJobIDString() + LprReceiveJobCommand.this.mContext.hostName + "\n").getBytes("UTF-8");
            }
        }
    }
}
