package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.scanner.ScannerModelSize;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.scan.ScanErrorException;
import com.brother.sdk.common.socket.scan.ScanState;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommand;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ScanCommandEstimateDocumentSize extends ScanCommand {
    private ECommand mCommand;
    private ScanCommandContext mContext;
    private MediaSize mDocumentSize;

    @Override
    protected boolean continueCommand(ScanCommand.Command.CommandResponse commandResponse) {
        return false;
    }

    @Override
    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        return super.commit(iSocket, scanCommandCallback);
    }

    public ScanCommandEstimateDocumentSize(ScanCommandContext scanCommandContext) {
        this.mCommand = new ECommand(scanCommandContext);
        this.mContext = scanCommandContext;
    }

    public MediaSize getResult() {
        return this.mDocumentSize;
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
            this.mDocumentSize = CalculatePaperSize((ECommand.ECommandResponse) commandResponse);
        }
        return commandResponse.mStatus;
    }

    private MediaSize CalculatePaperSize(ECommand.ECommandResponse eCommandResponse) {
        MediaSize mediaSize = MediaSize.Letter;
        double d = (((double) (eCommandResponse.mVertex2.f519x - eCommandResponse.mVertex1.f519x)) / ((double) eCommandResponse.mResolution.Width)) * 25.4d;
        if (d < 69.0d) {
            if (this.mContext.modelSize == ScannerModelSize.A4) {
                return MediaSize.Letter;
            }
            return MediaSize.A3;
        }
        if (69.0d <= d && d < 94.0d) {
            return MediaSize.PhotoL;
        }
        if (94.0d <= d && d < 104.0d) {
            if (this.mContext.Region == CountrySpec.Japan) {
                return MediaSize.Hagaki;
            }
            return MediaSize.Index4x6;
        }
        if (104.0d <= d && d < 132.0d) {
            return MediaSize.Photo2L;
        }
        if (132.0d <= d && d < 153.0d) {
            return MediaSize.A5;
        }
        if (153.0d <= d && d < 187.0d) {
            if (this.mContext.Region == CountrySpec.Japan) {
                return MediaSize.JISB5;
            }
            return MediaSize.Executive;
        }
        if (187.0d <= d && d < 213.0d) {
            return MediaSize.A4;
        }
        if (213.0d <= d && d < 220.9d) {
            return MediaSize.Letter;
        }
        if (220.9d <= d && d < 262.0d) {
            return MediaSize.B4;
        }
        if (262.0d > d || d >= 284.4d) {
            return 284.4d <= d ? MediaSize.A3 : mediaSize;
        }
        return MediaSize.Ledger;
    }

    static class ECommand extends ScanCommand.Command {

        private static final byte f511E = 69;
        private ScanCommandContext mContext;

        ECommand(ScanCommandContext scanCommandContext) {
            this.mContext = scanCommandContext;
        }

        @Override
        byte[] toByteArray() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write(69);
                byteArrayOutputStream.write(10);
                byte[] bytes = (String.format("R=%d,%d", Integer.valueOf(this.mContext.resolution.Width), Integer.valueOf(this.mContext.resolution.Height)) + "\n").getBytes("UTF-8");
                byteArrayOutputStream.write(bytes, 0, bytes.length);
                byte[] bytes2 = (String.format("M=%s", this.mContext.Mode.toString()) + "\n").getBytes("UTF-8");
                byteArrayOutputStream.write(bytes2, 0, bytes2.length);
                byteArrayOutputStream.write(-128);
                return byteArrayOutputStream.toByteArray();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override
        protected ScanCommand.Command.CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException, UnsupportedEncodingException {
            int iIndexOf;
            ECommandResponse eCommandResponse = new ECommandResponse();
            int unsignedByte = readUnsignedByte(byteBuffer);
            if (unsignedByte == -1) {
                throw new ScanCommand.Command.InsufficientResponseException();
            }
            if (((byte) unsignedByte) != 0) {
                throw new ScanErrorException("Auto Document Detect was failed.", analyzeError(unsignedByte));
            }
            byte[] dataBlock = readDataBlock(byteBuffer, readInteger(byteBuffer, 2));
            String str = new String(dataBlock, 0, dataBlock.length, "UTF-8");
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < str.length() && (iIndexOf = str.indexOf("=", i)) >= 0) {
                int i2 = iIndexOf + 1;
                int iIndexOf2 = str.indexOf("\n", i2);
                String[] strArrSplit = str.substring(i2, iIndexOf2).split(",");
                if (strArrSplit.length != 2) {
                    throw new ScanErrorException("Response had bad format.", ScanState.ErrorScanUnknown);
                }
                arrayList.add(new ScanCommandParameters.Point(Integer.parseInt(strArrSplit[0]), Integer.parseInt(strArrSplit[1])));
                i = iIndexOf2;
            }
            if (arrayList.size() < 3) {
                throw new ScanErrorException("Response had bad format.", ScanState.ErrorScanUnknown);
            }
            ScanCommandParameters.Point point = (ScanCommandParameters.Point) arrayList.get(0);
            eCommandResponse.mResolution = new ScanCommandParameters.ScanResolution(point.f519x, point.f520y);
            eCommandResponse.mVertex1 = (ScanCommandParameters.Point) arrayList.get(1);
            eCommandResponse.mVertex2 = (ScanCommandParameters.Point) arrayList.get(2);
            return eCommandResponse;
        }

        class ECommandResponse extends ScanCommand.Command.CommandResponse {
            ScanCommandParameters.ScanResolution mResolution;
            ScanCommandParameters.Point mVertex1;
            ScanCommandParameters.Point mVertex2;

            ECommandResponse() {
                super();
            }
        }
    }
}
