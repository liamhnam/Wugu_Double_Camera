package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.scan.ScanErrorException;
import com.brother.sdk.common.socket.scan.ScanState;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import kotlin.UByte;

public abstract class ScanCommand {

    enum PhaseType {
        ProcessJustContext,
        NeedDeviceAccess
    }

    protected abstract boolean continueCommand(Command.CommandResponse commandResponse);

    protected abstract Command getCommand();

    protected abstract PhaseType initializeCommand();

    protected abstract ScanState terminateCommand(Command.CommandResponse commandResponse);

    ScanCommand() {
    }

    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        Command.CommandResponse commandResponseProcessReceivedResponse = null;
        if (initializeCommand() == PhaseType.NeedDeviceAccess) {
            Command command = getCommand();
            command.setCallback(scanCommandCallback);
            do {
                byte[] byteArray = command.toByteArray();
                iSocket.write(byteArray, 0, byteArray.length);
                byte[] bArr = new byte[4096];
                do {
                    int i = iSocket.read(bArr, 0, 4096);
                    if (i < 0) {
                        break;
                    }
                    commandResponseProcessReceivedResponse = command.processReceivedResponse(bArr, 0, i);
                } while (commandResponseProcessReceivedResponse == null);
            } while (continueCommand(commandResponseProcessReceivedResponse));
        }
        return terminateCommand(commandResponseProcessReceivedResponse);
    }

    static abstract class Command {
        protected static final byte Escape = 27;

        protected static final byte f508LF = 10;
        protected static final byte Terminator = -128;
        protected static final String UTF8 = "UTF-8";
        ScanCommandCallback mCallback;
        ByteArrayOutputStream mResidueBuffer = new ByteArrayOutputStream();

        protected abstract CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, InsufficientResponseException, UnsupportedEncodingException;

        abstract byte[] toByteArray();

        protected Command() {
        }

        void setCallback(ScanCommandCallback scanCommandCallback) {
            this.mCallback = scanCommandCallback;
        }

        void dispose() throws IOException {
            disposeUnprocessedBuffer();
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.CommandResponse processReceivedResponse(byte[] r5, int r6, int r7) throws java.io.IOException {
            /*
                r4 = this;
                r0 = 0
                r1 = 0
                if (r7 <= 0) goto L9
                com.brother.sdk.common.socket.scan.scancommand.ScanCommandCallback r2 = r4.mCallback     // Catch: java.lang.Exception -> L1a com.brother.sdk.common.socket.scan.ScanErrorException -> L33 com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.InsufficientResponseException -> L4e
                r2.onNotifyProcessAlive()     // Catch: java.lang.Exception -> L1a com.brother.sdk.common.socket.scan.ScanErrorException -> L33 com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.InsufficientResponseException -> L4e
            L9:
                java.nio.ByteBuffer r5 = r4.createCurrentResponseStream(r5, r6, r7)     // Catch: java.lang.Exception -> L1a com.brother.sdk.common.socket.scan.ScanErrorException -> L33 com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.InsufficientResponseException -> L4e
                com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse r6 = r4.processReceivedResponseImplementation(r5)     // Catch: com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.InsufficientResponseException -> L15 java.lang.Exception -> L1a com.brother.sdk.common.socket.scan.ScanErrorException -> L33
                r4.dispose()     // Catch: com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.InsufficientResponseException -> L15 java.lang.Exception -> L1a com.brother.sdk.common.socket.scan.ScanErrorException -> L33
                return r6
            L15:
                r6 = move-exception
                r3 = r6
                r6 = r5
                r5 = r3
                goto L50
            L1a:
                r5 = move-exception
                com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse r6 = new com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse
                r6.<init>()
                r6.mSuccess = r0
                java.lang.String r7 = r5.getMessage()
                r6.mErrorMessage = r7
                com.brother.sdk.common.socket.scan.ScanState r7 = com.brother.sdk.common.socket.scan.ScanState.ErrorScanUnknown
                r6.mStatus = r7
                r4.dispose()
                r5.printStackTrace()
                return r6
            L33:
                r5 = move-exception
                com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse r6 = new com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse
                r6.<init>()
                r6.mSuccess = r0
                java.lang.String r7 = r5.getMessage()
                r6.mErrorMessage = r7
                com.brother.sdk.common.socket.scan.ScanState r7 = r5.error()
                r6.mStatus = r7
                r4.dispose()
                r5.printStackTrace()
                return r6
            L4e:
                r5 = move-exception
                r6 = r1
            L50:
                int r7 = r6.limit()
                int r0 = r5.mRollbackPosition
                if (r7 <= r0) goto L66
                byte[] r6 = r6.array()
                int r7 = r5.mRollbackPosition
                int r0 = r6.length
                int r5 = r5.mRollbackPosition
                int r0 = r0 - r5
                r4.storeUnprocessedBuffer(r6, r7, r0)
                goto L69
            L66:
                r4.disposeUnprocessedBuffer()
            L69:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.common.socket.scan.scancommand.ScanCommand.Command.processReceivedResponse(byte[], int, int):com.brother.sdk.common.socket.scan.scancommand.ScanCommand$Command$CommandResponse");
        }

        private void disposeUnprocessedBuffer() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = this.mResidueBuffer;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
                this.mResidueBuffer = null;
            }
        }

        private void storeUnprocessedBuffer(byte[] bArr, int i, int i2) throws IOException {
            dispose();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.mResidueBuffer = byteArrayOutputStream;
            byteArrayOutputStream.write(bArr, i, i2);
        }

        private ByteBuffer createCurrentResponseStream(byte[] bArr, int i, int i2) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = this.mResidueBuffer;
            if (byteArrayOutputStream2 != null && byteArrayOutputStream2.size() > 0) {
                byteArrayOutputStream.write(this.mResidueBuffer.toByteArray());
            }
            byteArrayOutputStream.write(bArr, i, i2);
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteArrayOutputStream.size());
            byteBufferAllocate.put(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
            return byteBufferAllocate;
        }

        protected static ScanState analyzeError(int i) {
            ScanState scanState = ScanState.ErrorScanUnknown;
            if (i == 131) {
                return ScanState.ErrorScanCancelAcknowledged;
            }
            if (i == 134) {
                return ScanState.ErrorScanStopKeyPressed;
            }
            if (i != 208) {
                switch (i) {
                    case 194:
                        return ScanState.ErrorScanNoPaper;
                    case 195:
                        return ScanState.ErrorScanDocumentJam;
                    case 196:
                    case 199:
                        return ScanState.ErrorScanCoverOpen;
                    case 197:
                        return ScanState.ErrorScanScannerBusy;
                    case 198:
                        return ScanState.ErrorScanDeviceMemoryFull;
                    case 200:
                        return ScanState.ErrorScanDuplexSizeTooLarge;
                    case 201:
                        return ScanState.ErrorScanSecureLock;
                    case 202:
                        return ScanState.ErrorScanFBCoverOpen;
                    case 203:
                    case 205:
                        return ScanState.ErrorScanCardTrayOpen;
                    case 204:
                    case 206:
                        return ScanState.ErrorScanCardTrayClose;
                    default:
                        switch (i) {
                            case 229:
                            case 230:
                            case 231:
                                return ScanState.ErrorScanSensorErr;
                            case 232:
                                return ScanState.ErrorScanADFCoverOpen;
                            default:
                                switch (i) {
                                    case 234:
                                        return ScanState.ErrorScanMultiFeed;
                                    case 235:
                                        return ScanState.ErrorScanTopCoverOpenDuringCardSlotScanning;
                                    case 236:
                                        return ScanState.ErrorScanTopCoverOpenBeforeCardSlotScanning;
                                    case 237:
                                        return ScanState.ErrorScanCardInsertDuringADFScanning;
                                    case 238:
                                        return ScanState.ErrorScanNoCardInCardSlot;
                                    case 239:
                                        return ScanState.ErrorScanJamInCardSlot;
                                    default:
                                        return ScanState.ErrorScanUnknown;
                                }
                        }
                }
            }
            return ScanState.ErrorScanInvalidCommand;
        }

        protected static int readInteger(ByteBuffer byteBuffer, int i) throws InsufficientResponseException {
            if (byteBuffer.limit() - byteBuffer.position() < i) {
                throw new InsufficientResponseException();
            }
            int i2 = 1;
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                i3 += (byteBuffer.get() & UByte.MAX_VALUE) * i2;
                i2 *= 256;
            }
            return i3;
        }

        protected static byte[] readDataBlock(ByteBuffer byteBuffer, int i) throws InsufficientResponseException {
            if (byteBuffer.limit() - byteBuffer.position() < i) {
                throw new InsufficientResponseException();
            }
            byte[] bArr = new byte[i];
            byteBuffer.get(bArr, 0, i);
            return bArr;
        }

        protected static int readUnsignedByte(ByteBuffer byteBuffer) {
            if (byteBuffer.limit() - byteBuffer.position() < 1) {
                return -1;
            }
            return byteBuffer.get() & UByte.MAX_VALUE;
        }

        protected static byte[] readPackbits(byte[] bArr) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bArr.length) {
                int i2 = i + 1;
                int i3 = bArr[i] & UByte.MAX_VALUE;
                if (i3 < 128) {
                    int i4 = i3 + 1;
                    byteArrayOutputStream.write(bArr, i2, i4);
                    i = i2 + i4;
                } else {
                    int i5 = (255 - i3) + 2;
                    int i6 = i2 + 1;
                    byte b = bArr[i2];
                    for (int i7 = 0; i7 < i5; i7++) {
                        byteArrayOutputStream.write(b);
                    }
                    i = i6;
                }
            }
            return byteArrayOutputStream.toByteArray();
        }

        protected static String convertDuplexType(Duplex duplex) {
            return duplex != Duplex.Simplex ? "DUP" : "SIN";
        }

        protected static int readByteBlockToInteger(byte[] bArr, int i, int i2) {
            int iPow = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                iPow += bArr[i3 + i] * ((int) Math.pow(255.0d, i3));
            }
            return iPow;
        }

        class CommandResponse {
            boolean mSuccess = true;
            String mErrorMessage = "";
            ScanState mStatus = ScanState.Success;

            CommandResponse() {
            }
        }

        protected static class InsufficientResponseException extends Exception {
            private static final long serialVersionUID = -6551702225872820797L;
            int mRollbackPosition;

            InsufficientResponseException() {
                this.mRollbackPosition = 0;
            }

            InsufficientResponseException(int i) {
                this.mRollbackPosition = i;
            }
        }
    }
}
