package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.scan.ScanErrorException;
import com.brother.sdk.common.socket.scan.ScanState;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommand;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ScanCommandSelectPaperSource extends ScanCommand {
    private SCommand mCommand;

    @Override
    protected boolean continueCommand(ScanCommand.Command.CommandResponse commandResponse) {
        return false;
    }

    @Override
    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        return super.commit(iSocket, scanCommandCallback);
    }

    public ScanCommandSelectPaperSource(ScanPaperSource scanPaperSource) {
        this.mCommand = new SCommand(scanPaperSource);
    }

    @Override
    protected ScanCommand.Command getCommand() {
        return this.mCommand;
    }

    @Override
    protected ScanCommand.PhaseType initializeCommand() {
        return ScanCommand.PhaseType.NeedDeviceAccess;
    }

    @Override
    protected ScanState terminateCommand(ScanCommand.Command.CommandResponse commandResponse) {
        return commandResponse.mStatus;
    }

    static class SCommand extends ScanCommand.Command {

        private static final byte f522S = 83;
        private ScanPaperSource mSource;

        SCommand(ScanPaperSource scanPaperSource) {
            this.mSource = scanPaperSource;
        }

        @Override
        byte[] toByteArray() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write(83);
                byteArrayOutputStream.write(10);
                byte[] bytes = (String.format("%s", this.mSource.toString()) + "\n").getBytes("UTF-8");
                byteArrayOutputStream.write(bytes, 0, bytes.length);
                byteArrayOutputStream.write(-128);
                return byteArrayOutputStream.toByteArray();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override
        protected ScanCommand.Command.CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException, UnsupportedEncodingException {
            ScanCommand.Command.CommandResponse commandResponse = new ScanCommand.Command.CommandResponse();
            int unsignedByte = readUnsignedByte(byteBuffer);
            if (unsignedByte == -1) {
                throw new ScanCommand.Command.InsufficientResponseException();
            }
            if (((byte) unsignedByte) == -128) {
                return commandResponse;
            }
            throw new ScanErrorException("Indicated PaperSource was not found.", analyzeError(unsignedByte));
        }
    }
}
