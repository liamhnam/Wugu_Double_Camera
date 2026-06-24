package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.brother.sdk.common.socket.ISocket;
import com.brother.sdk.common.socket.scan.ScanErrorException;
import com.brother.sdk.common.socket.scan.ScanImage;
import com.brother.sdk.common.socket.scan.ScanState;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommand;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import com.brother.sdk.common.util.Tool;
import com.google.android.exoplayer2.extractor.p018ts.TsExtractor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ScanCommandExecuteScan extends ScanCommand {
    private XCommand mCommand;

    @Override
    public ScanState commit(ISocket iSocket, ScanCommandCallback scanCommandCallback) throws IOException {
        return super.commit(iSocket, scanCommandCallback);
    }

    public ScanCommandExecuteScan(ScanCommandContext scanCommandContext) {
        this.mCommand = new XCommand(scanCommandContext);
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
        return commandResponse != null ? commandResponse.mStatus : ScanState.ErrorScanUnknown;
    }

    @Override
    protected boolean continueCommand(ScanCommand.Command.CommandResponse commandResponse) {
        if (commandResponse == null || !commandResponse.mSuccess || !((XCommand.XCommandResponse) commandResponse).PageContinued) {
            return false;
        }
        this.mCommand.proceedToNextPage();
        return true;
    }

    static class XCommand extends ScanCommand.Command {
        private static final int PROGRESS_COMMAND_END = 100;
        private static final int PROGRESS_COMMAND_START = 1;

        private static final byte f513X = 88;
        private ScanCommandContext mContext;
        private ScanImageContainer mImageContainer;
        private boolean mProtocolOver2010;
        private boolean mCommandStarted = false;
        private boolean ADFContinue = false;
        private int PageIndex = 0;

        XCommand(ScanCommandContext scanCommandContext) {
            this.mProtocolOver2010 = false;
            this.mContext = scanCommandContext;
            this.mProtocolOver2010 = scanCommandContext.scanProtocol.compareTo(ScanCommandParameters.ProtocolGeneration.ScanProtocol_2010) >= 0;
            this.mImageContainer = new ScanImageContainer();
        }

        void proceedToNextPage() {
            this.PageIndex++;
            this.ADFContinue = true;
        }

        @Override
        byte[] toByteArray() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(27);
                byteArrayOutputStream.write(88);
                if (!this.ADFContinue) {
                    byteArrayOutputStream.write(10);
                    byte[] bytes = (String.format("R=%d,%d", Integer.valueOf(this.mContext.resolution.Width), Integer.valueOf(this.mContext.resolution.Height)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes, 0, bytes.length);
                    byte[] bytes2 = (String.format("M=%s", this.mContext.Mode.toString()) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes2, 0, bytes2.length);
                    byte[] bytes3 = (String.format("C=%s", this.mContext.Compression.toString()) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes3, 0, bytes3.length);
                    if (this.mContext.Compression == ScanCommandParameters.DataCompression.JPEG) {
                        byte[] bytes4 = (String.format("J=%s", this.mContext.Jpeg.toString()) + "\n").getBytes("UTF-8");
                        byteArrayOutputStream.write(bytes4, 0, bytes4.length);
                    }
                    byte[] bytes5 = (String.format("B=%d", Integer.valueOf(this.mContext.brightness)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes5, 0, bytes5.length);
                    byte[] bytes6 = (String.format("N=%d", Integer.valueOf(this.mContext.contrast)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes6, 0, bytes6.length);
                    byte[] bytes7 = (String.format("A=%d,%d,%d,%d", Integer.valueOf(this.mContext.Area.XStart), Integer.valueOf(this.mContext.Area.YStart), Integer.valueOf(this.mContext.Area.XEnd), Integer.valueOf(this.mContext.Area.YEnd)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes7, 0, bytes7.length);
                    byte[] bytes8 = (String.format("D=%s", convertDuplexType(this.mContext.duplex)) + "\n").getBytes("UTF-8");
                    byteArrayOutputStream.write(bytes8, 0, bytes8.length);
                    if (this.mContext.Special != ScanSpecialMode.NORMAL_SCAN) {
                        byte[] bytes9 = (String.format("S=%s", this.mContext.Special.toString()) + "\n").getBytes("UTF-8");
                        byteArrayOutputStream.write(bytes9, 0, bytes9.length);
                    }
                    if (this.mContext.scanProtocol.compareTo(ScanCommandParameters.ProtocolGeneration.ScanProtocol_2012) > 0) {
                        if (this.mContext.detectErrors != null && !this.mContext.detectErrors.isEmpty()) {
                            byte[] bytes10 = (String.format("E=%d", Integer.valueOf(ScanCommandParameters.ErrorDetect.calculateSum(this.mContext.detectErrors))) + "\n").getBytes("UTF-8");
                            byteArrayOutputStream.write(bytes10, 0, bytes10.length);
                        }
                        if (this.mContext.groundColorCorrectionLevel >= 0) {
                            byte[] bytes11 = (String.format("G=1", new Object[0]) + "\n").getBytes("UTF-8");
                            byteArrayOutputStream.write(bytes11, 0, bytes11.length);
                            byte[] bytes12 = (String.format("L=%d", Integer.valueOf(this.mContext.groundColorCorrectionLevel)) + "\n").getBytes("UTF-8");
                            byteArrayOutputStream.write(bytes12, 0, bytes12.length);
                        } else {
                            byte[] bytes13 = (String.format("G=0", new Object[0]) + "\n").getBytes("UTF-8");
                            byteArrayOutputStream.write(bytes13, 0, bytes13.length);
                            byte[] bytes14 = (String.format("L=0", new Object[0]) + "\n").getBytes("UTF-8");
                            byteArrayOutputStream.write(bytes14, 0, bytes14.length);
                        }
                    }
                }
                byteArrayOutputStream.write(-128);
                return byteArrayOutputStream.toByteArray();
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw e;
            } catch (Exception unused) {
                return null;
            }
        }

        @Override
        protected ScanCommand.Command.CommandResponse processReceivedResponseImplementation(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException, UnsupportedEncodingException {
            if (!this.mCommandStarted) {
                this.mCommandStarted = true;
                this.mCallback.onUpdateProcessProgress(1);
            }
            if (this.mProtocolOver2010) {
                while (byteBuffer.position() < byteBuffer.limit()) {
                    this.mImageContainer.Add(new ScannedBlock2010(byteBuffer));
                }
            } else {
                while (byteBuffer.position() < byteBuffer.limit()) {
                    this.mImageContainer.Add(new ScannedBlockBefore2010(byteBuffer));
                }
            }
            if (!this.mImageContainer.DataEnded) {
                throw new ScanCommand.Command.InsufficientResponseException(byteBuffer.position());
            }
            this.mCallback.onUpdateProcessProgress(100);
            this.mCommandStarted = false;
            XCommandResponse xCommandResponse = new XCommandResponse();
            xCommandResponse.PageContinued = this.mImageContainer.PageContinued;
            return xCommandResponse;
        }

        class XCommandResponse extends ScanCommand.Command.CommandResponse {
            boolean PageContinued;

            XCommandResponse() {
                super();
            }
        }

        enum ColorType {
            Monochrome(0),
            R(1),
            G(2),
            B(3),
            RGB(4),
            BGR(5),
            ColorIndices16(6),
            ColorIndices256(7),
            TrueGray8bits(8),
            Color24bits(9);

            private final int value;

            ColorType(int i) {
                this.value = i;
            }

            int toValue() {
                return this.value;
            }

            static ColorType fromValue(int i) {
                for (ColorType colorType : values()) {
                    if (colorType.toValue() == i) {
                        return colorType;
                    }
                }
                return null;
            }
        }

        enum HeaderType {
            BlockDataHeader(0),
            AllPagesEnd(1),
            ThisPageEnd(2),
            NormalOrderBlockPage(3),
            ReverseOrderBlockPage(4),
            StatusCode(5),
            EdgeInformation(6);

            private final int value;

            HeaderType(int i) {
                this.value = i;
            }

            int toValue() {
                return this.value;
            }

            static HeaderType fromValue(int i) {
                for (HeaderType headerType : values()) {
                    if (headerType.toValue() == i) {
                        return headerType;
                    }
                }
                return null;
            }
        }

        class ScannedBlock {
            BlockHeader Header;
            protected int StreamStartPos;
            int Length = -1;
            byte[] BlockData = null;

            ScannedBlock(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException {
                this.StreamStartPos = byteBuffer.position();
                this.Header = null;
                int unsignedByte = ScanCommand.Command.readUnsignedByte(byteBuffer);
                if (unsignedByte == -1) {
                    throw new ScanCommand.Command.InsufficientResponseException(this.StreamStartPos);
                }
                this.Header = new BlockHeader(unsignedByte);
            }

            class BlockHeader {
                ColorType Color;
                ScanCommandParameters.DataCompression Compression;
                boolean FullWhite;
                ScanState Status;
                HeaderType Type;

                BlockHeader(int i) throws ScanErrorException {
                    if ((i & 128) == 0) {
                        int i2 = i & 3;
                        int i3 = (i >> 2) & 7;
                        int i4 = (i >> 6) & 3;
                        if (((i >> 5) & 1) == 0) {
                            this.Compression = ScanCommandParameters.DataCompression.fromValue(i2);
                            this.Color = ColorType.fromValue(i3);
                            this.FullWhite = (i4 & 1) == 0;
                        } else {
                            this.Compression = ScanCommandParameters.DataCompression.JPEG;
                            this.Color = ColorType.fromValue(i3 + 8);
                            this.FullWhite = false;
                        }
                        this.Type = HeaderType.BlockDataHeader;
                        return;
                    }
                    switch (i) {
                        case 128:
                            this.Type = HeaderType.AllPagesEnd;
                            return;
                        case 129:
                        case 130:
                            this.Type = HeaderType.ThisPageEnd;
                            return;
                        case 131:
                        case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO:
                        default:
                            this.Status = ScanCommand.Command.analyzeError(i);
                            throw new ScanErrorException("Error occurred in scanning.", this.Status);
                        case 132:
                            this.Type = HeaderType.NormalOrderBlockPage;
                            return;
                        case 133:
                            this.Type = HeaderType.ReverseOrderBlockPage;
                            return;
                        case TsExtractor.TS_STREAM_TYPE_E_AC3:
                            this.Type = HeaderType.EdgeInformation;
                            return;
                    }
                }
            }
        }

        class ScannedBlockBefore2010 extends ScannedBlock {
            ScannedBlockBefore2010(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException {
                super(byteBuffer);
                if (this.Header != null) {
                    if (this.Header.Type == HeaderType.BlockDataHeader && !this.Header.FullWhite) {
                        try {
                            this.Length = ScanCommand.Command.readInteger(byteBuffer, 2);
                            if (this.Header.Compression != ScanCommandParameters.DataCompression.JPEG && this.Header.Compression != ScanCommandParameters.DataCompression.NONE) {
                                if (this.Header.Compression == ScanCommandParameters.DataCompression.RLENGTH) {
                                    this.BlockData = ScanCommand.Command.readPackbits(ScanCommand.Command.readDataBlock(byteBuffer, this.Length));
                                    return;
                                }
                                throw new ScanErrorException("Unexpected data format was received.", ScanState.ErrorScanUnknown);
                            }
                            this.BlockData = ScanCommand.Command.readDataBlock(byteBuffer, this.Length);
                            return;
                        } catch (ScanCommand.Command.InsufficientResponseException unused) {
                            throw new ScanCommand.Command.InsufficientResponseException(this.StreamStartPos);
                        }
                    }
                    this.Length = 0;
                    this.BlockData = null;
                }
            }
        }

        class ScannedBlock2010 extends ScannedBlock {
            ExtraHeader Extra;

            ScannedBlock2010(ByteBuffer byteBuffer) throws ScanErrorException, ScanCommand.Command.InsufficientResponseException {
                super(byteBuffer);
                if (this.Header != null) {
                    if (this.Header.Type == HeaderType.BlockDataHeader) {
                        try {
                            this.Extra = new ExtraHeader(byteBuffer);
                            if (!this.Header.FullWhite) {
                                this.Length = ScanCommand.Command.readInteger(byteBuffer, 2);
                                if (this.Header.Compression != ScanCommandParameters.DataCompression.JPEG && this.Header.Compression != ScanCommandParameters.DataCompression.NONE) {
                                    if (this.Header.Compression == ScanCommandParameters.DataCompression.RLENGTH) {
                                        this.BlockData = ScanCommand.Command.readPackbits(ScanCommand.Command.readDataBlock(byteBuffer, this.Length));
                                        return;
                                    }
                                    throw new ScanErrorException("Unexpected data format was received.", ScanState.ErrorScanUnknown);
                                }
                                this.BlockData = ScanCommand.Command.readDataBlock(byteBuffer, this.Length);
                                return;
                            }
                            this.Length = 0;
                            this.BlockData = null;
                            return;
                        } catch (ScanCommand.Command.InsufficientResponseException unused) {
                            throw new ScanCommand.Command.InsufficientResponseException(this.StreamStartPos);
                        }
                    }
                    if (this.Header.Type == HeaderType.ThisPageEnd) {
                        try {
                            this.Extra = new ExtraHeader(byteBuffer);
                            this.Length = 0;
                            this.BlockData = null;
                            return;
                        } catch (ScanCommand.Command.InsufficientResponseException unused2) {
                            throw new ScanCommand.Command.InsufficientResponseException(this.StreamStartPos);
                        }
                    }
                    if (this.Header.Type == HeaderType.EdgeInformation) {
                        try {
                            this.Extra = new ExtraHeader(byteBuffer);
                            this.Length = ScanCommand.Command.readInteger(byteBuffer, 2);
                            this.BlockData = ScanCommand.Command.readDataBlock(byteBuffer, this.Length);
                            return;
                        } catch (ScanCommand.Command.InsufficientResponseException unused3) {
                            throw new ScanCommand.Command.InsufficientResponseException(this.StreamStartPos);
                        }
                    }
                    this.Extra = null;
                    this.Length = 0;
                    this.BlockData = null;
                }
            }

            class ExtraHeader {
                int Length;
                int LineCounts;
                int PageNumber;
                int StatusCode;

                ExtraHeader(ByteBuffer byteBuffer) throws ScanCommand.Command.InsufficientResponseException {
                    int integer = ScanCommand.Command.readInteger(byteBuffer, 2);
                    this.Length = integer;
                    if (integer > 1) {
                        this.PageNumber = ScanCommand.Command.readInteger(byteBuffer, 2);
                        if (this.Length > 2) {
                            this.StatusCode = ScanCommand.Command.readUnsignedByte(byteBuffer);
                            int i = this.Length;
                            if (i > 3) {
                                this.LineCounts = ScanCommand.Command.readInteger(byteBuffer, i - 3);
                            } else {
                                this.LineCounts = 0;
                            }
                        }
                    }
                }
            }
        }

        private static class ProgressContainer {
            private static final int END_VALUE = 100;
            private HashMap<Integer, Integer> mProgress = new HashMap<>();

            public void updateProgress(int i, int i2, boolean z) {
                if (this.mProgress.containsKey(Integer.valueOf(i)) && z) {
                    i2 += this.mProgress.get(Integer.valueOf(i)).intValue();
                }
                this.mProgress.put(Integer.valueOf(i), Integer.valueOf(i2));
            }

            public void popProgress(int i) {
                if (this.mProgress.containsKey(Integer.valueOf(i))) {
                    this.mProgress.remove(Integer.valueOf(i));
                }
            }

            public int getCurrentProgress(int i) {
                Iterator<Integer> it = this.mProgress.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Integer next = it.next();
                    if (next.intValue() < i) {
                        i = next.intValue();
                        break;
                    }
                }
                if (this.mProgress.containsKey(Integer.valueOf(i))) {
                    return this.mProgress.get(Integer.valueOf(i)).intValue();
                }
                return 100;
            }
        }

        class ScanImageContainer {
            private static final int PROGRESS_RANGE = 99;
            private boolean ContainerReverseOrder;
            private Tool.ValueCoordinator mProgressCalculator;
            private ProgressContainer mProgressContainer;
            private int mCurrentValue = 0;
            boolean DataEnded = false;
            boolean PageContinued = false;
            HashMap<Integer, List<ScannedBlock>> _container = new HashMap<>();
            HashMap<Integer, ScanImage.ImageCornerInfo> EdgeCoordinates = new HashMap<>();

            ScanImageContainer() {
                if (XCommand.this.mProtocolOver2010) {
                    if (XCommand.this.mContext.Compression == ScanCommandParameters.DataCompression.JPEG) {
                        this.mProgressCalculator = new Tool.ValueCoordinator(getHeight(), 99);
                    } else {
                        this.mProgressCalculator = new Tool.ValueCoordinator(((XCommand.this.mContext.Area.XEnd - XCommand.this.mContext.Area.XStart) * (XCommand.this.mContext.Area.YEnd - XCommand.this.mContext.Area.YStart)) / 8, 99);
                    }
                } else {
                    int i = XCommand.this.mContext.Area.XEnd - XCommand.this.mContext.Area.XStart;
                    int i2 = XCommand.this.mContext.Area.YEnd - XCommand.this.mContext.Area.YStart;
                    if (XCommand.this.mContext.Compression == ScanCommandParameters.DataCompression.JPEG) {
                        this.mProgressCalculator = new Tool.ValueCoordinator(i * i2, 99);
                    } else {
                        this.mProgressCalculator = new Tool.ValueCoordinator((i * i2) / 8, 99);
                    }
                }
                this.mProgressContainer = new ProgressContainer();
            }

            void Add(ScannedBlock2010 scannedBlock2010) throws ScanErrorException {
                if (scannedBlock2010.Header.Type == HeaderType.BlockDataHeader) {
                    if (!this._container.containsKey(Integer.valueOf(scannedBlock2010.Extra.PageNumber))) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(scannedBlock2010);
                        this._container.put(Integer.valueOf(scannedBlock2010.Extra.PageNumber), arrayList);
                    } else {
                        this._container.get(Integer.valueOf(scannedBlock2010.Extra.PageNumber)).add(scannedBlock2010);
                    }
                    if (XCommand.this.mContext.Compression == ScanCommandParameters.DataCompression.JPEG) {
                        this.mProgressContainer.updateProgress(scannedBlock2010.Extra.PageNumber, scannedBlock2010.Extra.LineCounts, false);
                    } else {
                        this.mProgressContainer.updateProgress(scannedBlock2010.Extra.PageNumber, scannedBlock2010.Length, true);
                    }
                    this.mCurrentValue = this.mProgressContainer.getCurrentProgress(scannedBlock2010.Extra.PageNumber);
                } else if (scannedBlock2010.Header.Type == HeaderType.ThisPageEnd) {
                    int i = scannedBlock2010.Extra.PageNumber;
                    boolean z = scannedBlock2010.Extra.StatusCode != 132;
                    DataInfo dataInfo = getDataInfo(this._container.get(Integer.valueOf(i)));
                    int i2 = ((ScannedBlock2010) this._container.get(Integer.valueOf(i)).get(this._container.get(Integer.valueOf(i)).size() - 1)).Extra.LineCounts;
                    byte[] bArrCreateBuffer = createBuffer(this._container.get(Integer.valueOf(i)), dataInfo);
                    ScanImage.ImageCornerInfo imageCornerInfo = this.EdgeCoordinates.get(Integer.valueOf(i));
                    ScanImage scanImage = new ScanImage();
                    scanImage.LineCount = i2;
                    scanImage.PageIndex = i;
                    scanImage.ReverseOrder = z;
                    scanImage.Width = getWidth();
                    scanImage.Height = getHeight();
                    scanImage.Stride = dataInfo.Stride;
                    scanImage.Format = dataInfo.Format;
                    scanImage.Data = bArrCreateBuffer;
                    scanImage.documentSize = XCommand.this.mContext.documentSize;
                    scanImage.CornerInfo = imageCornerInfo;
                    XCommand.this.mCallback.onScanImageRead(scanImage);
                    this._container.remove(Integer.valueOf(i));
                    this.mProgressContainer.popProgress(i);
                } else if (scannedBlock2010.Header.Type == HeaderType.AllPagesEnd) {
                    this.DataEnded = true;
                } else if (scannedBlock2010.Header.Type == HeaderType.EdgeInformation) {
                    try {
                        ByteBuffer byteBufferWrap = ByteBuffer.wrap(scannedBlock2010.BlockData);
                        ScanImage.ImageCornerInfo imageCornerInfo2 = new ScanImage.ImageCornerInfo();
                        imageCornerInfo2.LeftTopX = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.LeftTopY = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.RightTopX = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.RightTopY = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.LeftBottomX = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.LeftBottomY = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.RightBottomX = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        imageCornerInfo2.RightBottomY = ScanCommand.Command.readInteger(byteBufferWrap, 4);
                        this.EdgeCoordinates.put(Integer.valueOf(scannedBlock2010.Extra.PageNumber), imageCornerInfo2);
                    } catch (Exception unused) {
                        throw new ScanErrorException("Unexpected error occurred.", ScanState.ErrorScanUnknown);
                    }
                }
                XCommand.this.mCallback.onUpdateProcessProgress(this.mProgressCalculator.coordinateValueInRange(this.mCurrentValue));
            }

            void Add(ScannedBlockBefore2010 scannedBlockBefore2010) throws ScanErrorException {
                if (this._container.size() == 0) {
                    this.DataEnded = false;
                    this.ContainerReverseOrder = false;
                    this._container.put(0, new ArrayList());
                    this.mCurrentValue = 0;
                }
                if (scannedBlockBefore2010.Header.Type == HeaderType.BlockDataHeader) {
                    this._container.get(0).add(scannedBlockBefore2010);
                    this.mCurrentValue += scannedBlockBefore2010.BlockData != null ? scannedBlockBefore2010.BlockData.length : 0;
                } else if (scannedBlockBefore2010.Header.Type == HeaderType.ThisPageEnd || scannedBlockBefore2010.Header.Type == HeaderType.AllPagesEnd) {
                    DataInfo dataInfo = getDataInfo(this._container.get(0));
                    byte[] bArrCreateBuffer = createBuffer(this._container.get(0), dataInfo);
                    ScanImage scanImage = new ScanImage();
                    scanImage.LineCount = -1;
                    scanImage.PageIndex = XCommand.this.PageIndex;
                    scanImage.ReverseOrder = this.ContainerReverseOrder;
                    scanImage.Width = getWidth();
                    scanImage.Height = getHeight();
                    scanImage.Stride = dataInfo.Stride;
                    scanImage.Format = dataInfo.Format;
                    scanImage.Data = bArrCreateBuffer;
                    scanImage.documentSize = XCommand.this.mContext.documentSize;
                    scanImage.CornerInfo = null;
                    XCommand.this.mCallback.onScanImageRead(scanImage);
                    this._container.clear();
                    this.DataEnded = true;
                    this.PageContinued = scannedBlockBefore2010.Header.Type == HeaderType.ThisPageEnd;
                } else if (scannedBlockBefore2010.Header.Type == HeaderType.ReverseOrderBlockPage) {
                    this.ContainerReverseOrder = true;
                }
                XCommand.this.mCallback.onUpdateProcessProgress(this.mProgressCalculator.coordinateValueInRange(this.mCurrentValue));
            }

            private int getWidth() {
                return XCommand.this.mContext.Area.XEnd - XCommand.this.mContext.Area.XStart;
            }

            private int getHeight() {
                return XCommand.this.mContext.Area.YEnd - XCommand.this.mContext.Area.YStart;
            }

            private byte[] createBuffer(List<ScannedBlock> list, DataInfo dataInfo) throws ScanErrorException {
                ArrayList<ScannedBlock> arrayList = new ArrayList();
                ArrayList<ScannedBlock> arrayList2 = new ArrayList();
                ArrayList<ScannedBlock> arrayList3 = new ArrayList();
                ArrayList<ScannedBlock> arrayList4 = new ArrayList();
                for (ScannedBlock scannedBlock : list) {
                    int i = C07071.f512xc8789798[scannedBlock.Header.Color.ordinal()];
                    if (i == 1) {
                        arrayList.add(scannedBlock);
                    } else if (i == 2) {
                        arrayList2.add(scannedBlock);
                    } else if (i == 3) {
                        arrayList3.add(scannedBlock);
                    } else {
                        arrayList4.add(scannedBlock);
                    }
                }
                int i2 = dataInfo.Stride;
                if (arrayList4.size() > 0) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[i2];
                    for (int i3 = 0; i3 < i2; i3++) {
                        bArr[i3] = -1;
                    }
                    boolean z = false;
                    for (ScannedBlock scannedBlock2 : arrayList4) {
                        if (scannedBlock2.Header.FullWhite) {
                            byteArrayOutputStream.write(bArr, 0, i2);
                        } else {
                            byteArrayOutputStream.write(scannedBlock2.BlockData, 0, scannedBlock2.BlockData.length);
                            if (scannedBlock2.Header.Color == ColorType.BGR) {
                                z = true;
                            }
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    if (!dataInfo.JpegCompression) {
                        dataInfo.Format = XCommand.this.mContext.Mode.convertToImageFormat(z);
                        return byteArray;
                    }
                    dataInfo.Format = ScanCommandParameters.ScanImageDataFormat.Jpeg;
                    return byteArray;
                }
                if (arrayList.size() != arrayList2.size() || arrayList2.size() != arrayList3.size()) {
                    throw new ScanErrorException("Error occurred in scanning.", ScanState.ErrorScanUnknown);
                }
                dataInfo.Format = ScanCommandParameters.ScanImageDataFormat.Bitmap_R_G_B_24;
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                for (ScannedBlock scannedBlock3 : arrayList) {
                    byteArrayOutputStream2.write(scannedBlock3.BlockData, 0, scannedBlock3.BlockData.length);
                }
                for (ScannedBlock scannedBlock4 : arrayList2) {
                    byteArrayOutputStream2.write(scannedBlock4.BlockData, 0, scannedBlock4.BlockData.length);
                }
                for (ScannedBlock scannedBlock5 : arrayList3) {
                    byteArrayOutputStream2.write(scannedBlock5.BlockData, 0, scannedBlock5.BlockData.length);
                }
                return byteArrayOutputStream2.toByteArray();
            }

            private DataInfo getDataInfo(List<ScannedBlock> list) {
                DataInfo dataInfo = new DataInfo();
                Iterator<ScannedBlock> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ScannedBlock next = it.next();
                    if (next.BlockData.length > 0) {
                        dataInfo.JpegCompression = next.Header.Compression == ScanCommandParameters.DataCompression.JPEG;
                        dataInfo.Stride = next.BlockData.length;
                    }
                }
                return dataInfo;
            }

            class DataInfo {
                ScanCommandParameters.ScanImageDataFormat Format;
                boolean JpegCompression;
                int Stride;

                DataInfo() {
                }
            }
        }
    }

    static class C07071 {

        static final int[] f512xc8789798;

        static {
            int[] iArr = new int[XCommand.ColorType.values().length];
            f512xc8789798 = iArr;
            try {
                iArr[XCommand.ColorType.R.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f512xc8789798[XCommand.ColorType.G.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f512xc8789798[XCommand.ColorType.B.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
