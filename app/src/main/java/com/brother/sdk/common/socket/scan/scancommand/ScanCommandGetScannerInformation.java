package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.scan.ScanErrorException;
import com.brother.sdk.common.socket.scan.ScanState;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommand;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ScanCommandGetScannerInformation extends ScanCommand {
    private ICommand mCommand;
    private ScanCommandParameters.ScannerInformation mScannerInformation;

    @Override
    protected boolean continueCommand(ScanCommand.Command.CommandResponse commandResponse) {
        return false;
    }

    @Override
    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        return super.commit(iSocket, scanCommandCallback);
    }

    public ScanCommandGetScannerInformation(ScanCommandContext scanCommandContext) {
        this.mCommand = new ICommand(scanCommandContext);
    }

    public ScanCommandParameters.ScannerInformation getResult() {
        return this.mScannerInformation;
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
        if (commandResponse.mSuccess) {
            this.mScannerInformation = ((ICommand.ICommandResponse) commandResponse).mScannerInformation;
        }
        return commandResponse.mStatus;
    }

    static class ICommand extends ScanCommand.Command {

        private static final byte f517I = 73;
        private ScanCommandContext mContext;

        ICommand(ScanCommandContext scanCommandContext) {
            this.mContext = scanCommandContext;
        }

        @Override
        byte[] toByteArray() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write(73);
                byteArrayOutputStream.write(10);
                byte[] bytes = (String.format("R=%d,%d", Integer.valueOf(this.mContext.resolution.Width), Integer.valueOf(this.mContext.resolution.Height)) + "\n").getBytes("UTF-8");
                byteArrayOutputStream.write(bytes, 0, bytes.length);
                byte[] bytes2 = (String.format("M=%s", this.mContext.Mode.toString()) + "\n").getBytes("UTF-8");
                byteArrayOutputStream.write(bytes2, 0, bytes2.length);
                if (this.mContext.scanProtocol.compareTo(ScanCommandParameters.ProtocolGeneration.ScanProtocol_2009) >= 0) {
                    byte[] bytes3 = (String.format("D=%s", convertDuplexType(this.mContext.duplex)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes3, 0, bytes3.length);
                }
                byteArrayOutputStream.write(-128);
                return byteArrayOutputStream.toByteArray();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override
        protected ScanCommand.Command.CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException, UnsupportedEncodingException {
            ICommandResponse iCommandResponse = new ICommandResponse();
            if (this.mContext.scanProtocol.compareTo(ScanCommandParameters.ProtocolGeneration.ScanProtocol_2009) >= 0) {
                int unsignedByte = readUnsignedByte(byteBuffer);
                if (unsignedByte == -1) {
                    throw new ScanCommand.Command.InsufficientResponseException();
                }
                if (((byte) unsignedByte) != 0) {
                    throw new ScanErrorException("Error occurred on device processing.", analyzeError(unsignedByte));
                }
            }
            byte[] dataBlock = readDataBlock(byteBuffer, readInteger(byteBuffer, 2));
            String[] strArrSplit = new String(dataBlock, 0, dataBlock.length, "UTF-8").split(",");
            if (strArrSplit.length < 7) {
                throw new ScanErrorException("Output had bad format.", ScanState.ErrorScanUnknown);
            }
            iCommandResponse.mScannerInformation.mResolution = new ScanCommandParameters.ScanResolution(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1]));
            iCommandResponse.mScannerInformation.mSource = ScanPaperSource.fromValue(Integer.parseInt(strArrSplit[2]));
            iCommandResponse.mScannerInformation.mMaxWidthMM = Integer.parseInt(strArrSplit[3]);
            iCommandResponse.mScannerInformation.mMaxWidthPixel = Integer.parseInt(strArrSplit[4]);
            iCommandResponse.mScannerInformation.mMaxHeightMM = Integer.parseInt(strArrSplit[5]);
            iCommandResponse.mScannerInformation.mMaxHeightPixel = Integer.parseInt(strArrSplit[6]);
            return iCommandResponse;
        }

        class ICommandResponse extends ScanCommand.Command.CommandResponse {
            ScanCommandParameters.ScannerInformation mScannerInformation;

            ICommandResponse() {
                super();
                this.mScannerInformation = new ScanCommandParameters.ScannerInformation();
            }
        }
    }
}
