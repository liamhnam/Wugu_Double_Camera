package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.scanner.ScanLongLengthEdge;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
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

public class ScanCommandQueryDeviceConfiguration extends ScanCommand {
    private QCommand mCommand = new QCommand();
    private ScanCommandDeviceConfiguration mConfiguration;

    @Override
    protected boolean continueCommand(ScanCommand.Command.CommandResponse commandResponse) {
        return false;
    }

    @Override
    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        return super.commit(iSocket, scanCommandCallback);
    }

    public ScanCommandDeviceConfiguration getResult() {
        return this.mConfiguration;
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
        if (commandResponse == null) {
            return ScanState.ErrorScanUnknown;
        }
        if (commandResponse.mSuccess) {
            this.mConfiguration = ((QCommand.QCommandResponse) commandResponse).mConfiguration;
        }
        return commandResponse.mStatus;
    }

    static class QCommand extends ScanCommand.Command {

        private static final byte f521Q = 81;
        private static final double UNIT1PER10MM_PER_MM = 10.0d;

        QCommand() {
        }

        @Override
        byte[] toByteArray() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write(81);
                byteArrayOutputStream.write(10);
                byteArrayOutputStream.write(-128);
                return byteArrayOutputStream.toByteArray();
            } catch (Exception unused) {
                return null;
            }
        }

        @Override
        protected ScanCommand.Command.CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException, UnsupportedEncodingException {
            QCommandResponse qCommandResponse = new QCommandResponse();
            byte[] dataBlock = readDataBlock(byteBuffer, 2);
            if (dataBlock != null && dataBlock.length == 2) {
                if (dataBlock[0] == -63 && dataBlock[1] == 0) {
                    byte[] dataBlock2 = readDataBlock(byteBuffer, readInteger(byteBuffer, 1) - 1);
                    if (dataBlock2[0] > ScanCommandParameters.ProtocolGeneration.ScanProtocol_2014_2.ordinal()) {
                        qCommandResponse.mConfiguration.ScanProtocol = ScanCommandParameters.ProtocolGeneration.ScanProtocol_2014_2;
                    } else {
                        qCommandResponse.mConfiguration.ScanProtocol = ScanCommandParameters.ProtocolGeneration.fromValue(dataBlock2[0]);
                    }
                    if ((dataBlock2[2] & 1) != 0) {
                        qCommandResponse.mConfiguration.ScanModes.add(ScanCommandParameters.ScanMode.TEXT);
                    }
                    if ((dataBlock2[2] & 2) != 0) {
                        qCommandResponse.mConfiguration.ScanModes.add(ScanCommandParameters.ScanMode.ERRDIF);
                    }
                    if ((dataBlock2[2] & 4) != 0) {
                        qCommandResponse.mConfiguration.ScanModes.add(ScanCommandParameters.ScanMode.GRAY256);
                    }
                    if ((dataBlock2[2] & 8) != 0) {
                        qCommandResponse.mConfiguration.ScanModes.add(ScanCommandParameters.ScanMode.C256);
                    }
                    if ((dataBlock2[2] & 16) != 0) {
                        qCommandResponse.mConfiguration.ScanModes.add(ScanCommandParameters.ScanMode.CGRAY);
                    }
                    qCommandResponse.mConfiguration.modelSize = ScannerModelSize.fromValue(dataBlock2[12] - 1);
                    int length = dataBlock2.length;
                    if (length > 13) {
                        qCommandResponse.mConfiguration.FB_H = HorizontalAlignment.fromValue(dataBlock2[13] - 1);
                    }
                    if (length > 14) {
                        qCommandResponse.mConfiguration.FB_V = VerticalAlignment.fromValue(dataBlock2[14] - 1);
                    }
                    if (length > 15) {
                        qCommandResponse.mConfiguration.ADF_H = HorizontalAlignment.fromValue(dataBlock2[15] - 1);
                    }
                    if (length > 16) {
                        qCommandResponse.mConfiguration.ADF_V = VerticalAlignment.fromValue(dataBlock2[16] - 1);
                    }
                    if (length > 17) {
                        ArrayList arrayList = new ArrayList();
                        if ((dataBlock2[17] & 1) != 0) {
                            arrayList.add(ScanSpecialMode.EDGE_SCAN);
                        }
                        if ((dataBlock2[17] & 4) != 0) {
                            arrayList.add(ScanSpecialMode.CORNER_SCAN);
                        }
                        if ((dataBlock2[17] & 32) != 0) {
                            arrayList.add(ScanSpecialMode.OVER_SCAN);
                        }
                        if (qCommandResponse.mConfiguration.ScanProtocol.compareTo(ScanCommandParameters.ProtocolGeneration.ScanProtocol_2014_2) >= 0) {
                            arrayList.add(ScanSpecialMode.LABEL_SCAN_CIRCLE);
                            arrayList.add(ScanSpecialMode.LABEL_SCAN_SQUARE);
                            arrayList.add(ScanSpecialMode.COPYQUALITY_SCAN);
                        }
                        if (arrayList.size() > 0) {
                            qCommandResponse.mConfiguration.SpecialScanModes = arrayList;
                        }
                        qCommandResponse.mConfiguration.AutoDocumentSizeScanSupport = (dataBlock2[17] & 2) != 0;
                    }
                    if (length > 18) {
                        qCommandResponse.mConfiguration.LongEdgeScan = ScanLongLengthEdge.fromValue(dataBlock2[18]);
                    }
                    if (length > 19) {
                        ArrayList arrayList2 = new ArrayList();
                        if ((dataBlock2[19] & 1) != 0) {
                            arrayList2.add(ScanCommandParameters.ErrorDetect.MultiPaperFeedDetect);
                        }
                        if (arrayList2.size() > 0) {
                            qCommandResponse.mConfiguration.ErrorDetects = arrayList2;
                        }
                    }
                    if (length > 20) {
                        qCommandResponse.mConfiguration.Carrier = ScanCommandParameters.CarrierSheet.fromValue(dataBlock2[20]);
                    }
                    if (length > 21) {
                        qCommandResponse.mConfiguration.EdgeTop = ScanCommandParameters.EdgeCoordinate.fromValue(dataBlock2[21]);
                    }
                    if (length > 22) {
                        qCommandResponse.mConfiguration.EdgeBottom = ScanCommandParameters.EdgeCoordinate.fromValue(dataBlock2[22]);
                    }
                    if (length > 23) {
                        qCommandResponse.mConfiguration.EdgeLeft = ScanCommandParameters.EdgeCoordinate.fromValue(dataBlock2[23]);
                    }
                    if (length > 24) {
                        qCommandResponse.mConfiguration.EdgeRight = ScanCommandParameters.EdgeCoordinate.fromValue(dataBlock2[24]);
                    }
                    if (length > 25) {
                        qCommandResponse.mConfiguration.PushScanSupport = (dataBlock2[25] & 1) != 0;
                    }
                    if (length > 26) {
                        qCommandResponse.mConfiguration.GroundColorCorrectionSupport = (dataBlock2[26] & 1) != 0;
                    }
                    if (length > 28) {
                        qCommandResponse.mConfiguration.FBMaxScanWidth = Integer.valueOf(readByteBlockToInteger(dataBlock2, 27, 2));
                    }
                    if (length > 30) {
                        qCommandResponse.mConfiguration.FBMaxScanHeight = Integer.valueOf(readByteBlockToInteger(dataBlock2, 29, 2));
                    }
                    if (length > 32) {
                        qCommandResponse.mConfiguration.ADFSimplexMaxScanWidth = Integer.valueOf(readByteBlockToInteger(dataBlock2, 31, 2));
                    }
                    if (length > 34) {
                        qCommandResponse.mConfiguration.ADFSimplexMaxScanHeight = Integer.valueOf(readByteBlockToInteger(dataBlock2, 33, 2));
                    }
                    if (length > 36) {
                        qCommandResponse.mConfiguration.ADFDuplexMaxScanWidth = Integer.valueOf(readByteBlockToInteger(dataBlock2, 35, 2));
                    }
                    if (length > 38) {
                        qCommandResponse.mConfiguration.ADFDuplexMaxScanHeight = Integer.valueOf(readByteBlockToInteger(dataBlock2, 37, 2));
                    }
                    if (length > 40) {
                        qCommandResponse.mConfiguration.ADFSimplexMinScanWidth = Integer.valueOf(readByteBlockToInteger(dataBlock2, 39, 2));
                    }
                    if (length > 42) {
                        qCommandResponse.mConfiguration.ADFSimplexMinScanHeight = Integer.valueOf(readByteBlockToInteger(dataBlock2, 41, 2));
                    }
                    if (length > 44) {
                        qCommandResponse.mConfiguration.ADFDuplexMinScanWidth = Integer.valueOf(readByteBlockToInteger(dataBlock2, 43, 2));
                    }
                    if (length > 46) {
                        qCommandResponse.mConfiguration.ADFDuplexMinScanHeight = Integer.valueOf(readByteBlockToInteger(dataBlock2, 45, 2));
                    }
                    if (length > 50) {
                        qCommandResponse.mConfiguration.SideMargin.Height = ((double) readByteBlockToInteger(dataBlock2, 47, 2)) / UNIT1PER10MM_PER_MM;
                        qCommandResponse.mConfiguration.SideMargin.Width = ((double) readByteBlockToInteger(dataBlock2, 49, 2)) / UNIT1PER10MM_PER_MM;
                    }
                    if (length > 68) {
                        qCommandResponse.mConfiguration.ADFDocumentsCapacity = Integer.valueOf(readByteBlockToInteger(dataBlock2, 67, 2));
                    }
                    if (length > 69) {
                        qCommandResponse.mConfiguration.PageCountsSpecify = Boolean.valueOf((dataBlock2[69] & 1) != 0);
                    }
                    return qCommandResponse;
                }
            }
            throw new ScanErrorException("Response had bad header.", ScanState.ErrorScanUnknown);
        }

        class QCommandResponse extends ScanCommand.Command.CommandResponse {
            ScanCommandDeviceConfiguration mConfiguration;

            QCommandResponse() {
                super();
                this.mConfiguration = new ScanCommandDeviceConfiguration();
            }
        }
    }
}
