package com.brother.sdk.print.pdl;

import com.brother.sdk.common.Callback;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.printer.PrintColorMatching;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintMediaType;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.device.printer.PrinterPDL;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.brother.sdk.common.util.ShortByteArray;
import com.brother.sdk.common.util.Tool;
import com.brother.sdk.print.PrintParameters;
import com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encoding;

public class PDLJpegBuilder extends PageDescriptionLanguageBuilder {
    private static final int ARG = 2;
    private static final double COLOR_CHANNEL = 3.0d;
    private static final double ENOUGH_MEMORY_MB_FOR_IMAGE = 32.0d;

    protected PDLJpegBuilder(Printer printer, Callback callback) {
        super(printer, callback);
    }

    @Override
    protected void buildDataBlocksInternal(Printer printer, PrintParameters printParameters, List<File> list, File file, PageDescriptionLanguageBuilder.PrintDataBlockCreateCallback printDataBlockCreateCallback) throws Throwable {
        BrotherJpegCommand brotherJpegCommand = new BrotherJpegCommand(printer, printParameters);
        printDataBlockCreateCallback.OnDataBlockCreated(new PrintDataBlockJob(brotherJpegCommand.jobStart()));
        int size = 100 / list.size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < list.size()) {
            Tool.ValueCoordinator valueCoordinator = new Tool.ValueCoordinator(100, size * i4, size);
            File file2 = list.get(i4);
            if (!file2.exists() || !file2.isFile() || !file2.canRead()) {
                throw new IOException("jLpr Error opening print file");
            }
            if (isCanceled()) {
                throw new IOException("Creation should not be continued more.");
            }
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(25));
            PageDescriptionLanguageBuilder.PrintableData printableDataCreatePrintableData = createPrintableData(printer, printParameters, file2, i4, file);
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(50));
            PrintDataBlockPage printDataBlockPage = new PrintDataBlockPage(brotherJpegCommand.pageStart(printableDataCreatePrintableData.mWidth, printableDataCreatePrintableData.mHeight, printableDataCreatePrintableData.mDpi), brotherJpegCommand.pageEnd(), printableDataCreatePrintableData.mDataPath);
            arrayList.add(printDataBlockPage);
            printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockPage);
            i3 = printableDataCreatePrintableData.mDpi;
            int i5 = printableDataCreatePrintableData.mWidth;
            int i6 = printableDataCreatePrintableData.mHeight;
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(100));
            i4++;
            i2 = i6;
            i = i5;
        }
        if (printParameters.duplex != Duplex.Simplex && list.size() % 2 == 1) {
            if (printer.printerPDL == PrinterPDL.Jpeg_BHS13 || printer.printerPDL == PrinterPDL.BrotherCommonPDL_ManualPageFlip) {
                PrintDataBlockPage printDataBlockPage2 = new PrintDataBlockPage(brotherJpegCommand.pageStart(i, i2, i3), brotherJpegCommand.pageEnd(), PrintImageUtil.createWhiteJpegFile(i, i2, file));
                printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockPage2);
                arrayList.add(printDataBlockPage2);
            } else if (printer.printerPDL == PrinterPDL.BrotherCommonPDL_AutoPageFlip) {
                PrintDataBlockLastPage printDataBlockLastPage = new PrintDataBlockLastPage(brotherJpegCommand.lastEmptyPage(i, i2, i3));
                printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockLastPage);
                arrayList.add(printDataBlockLastPage);
            }
        }
        int size2 = arrayList.size();
        int i7 = printParameters.copyCount;
        for (int i8 = 1; i8 < i7; i8++) {
            for (int i9 = 0; i9 < size2; i9++) {
                printDataBlockCreateCallback.OnDataBlockCreated((PrintDataBlock) arrayList.get(i9));
            }
        }
        printDataBlockCreateCallback.OnDataBlockCreated(new PrintDataBlockJob(brotherJpegCommand.jobEnd()));
        printDataBlockCreateCallback.OnCancelBlockCreated(new PrintDataBlockCancel(brotherJpegCommand.jobCancel()));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder.PrintableData createPrintableData(com.brother.sdk.common.device.printer.Printer r26, com.brother.sdk.print.PrintParameters r27, java.io.File r28, int r29, java.io.File r30) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 624
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.print.pdl.PDLJpegBuilder.createPrintableData(com.brother.sdk.common.device.printer.Printer, com.brother.sdk.print.PrintParameters, java.io.File, int, java.io.File):com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder$PrintableData");
    }

    private File makeCommandJpegFile(File file, File file2) throws IOException {
        byte[] bArr = {SnmpConstants.UINTEGER32, 0, 0, 0};
        FileInputStream fileInputStream = new FileInputStream(file);
        File fileCreateTempFile = File.createTempFile("temp", ".jpq", file2);
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
        byte[] bArr2 = new byte[4096];
        while (true) {
            int i = fileInputStream.read(bArr2, 0, 4096);
            if (i <= 0) {
                return fileCreateTempFile;
            }
            bArr[1] = (byte) (i % 256);
            bArr[2] = (byte) (i / 256);
            fileOutputStream.write(bArr, 0, 4);
            fileOutputStream.write(bArr2, 0, i);
            fileOutputStream.flush();
        }
    }

    static class BrotherJpegCommand {
        private static final int JPEG_COMMAND_SEPARATORSIZE = 4096;
        private byte[] mJobSeparator;
        private int mLastDPI;
        private int mLastPageHeight;
        private int mLastPageWidth;
        private PrintParameters mParameters;
        private Printer mPrinter;
        private byte[] mJobIn = {27, 124, 2, 0, 11};
        private byte[] mPrinterInit = {27, 64};
        private byte[] mDocumentResolution = {82, 2, 2};
        private byte[] mInputResolution = {113, 1, 1};
        private byte[] mInputImagePixel = {112, SnmpConstants.SNMP_ERR_INCONSISTENTNAME, 52, 86, 120};
        private byte[] mSetColor = {73, 49};
        private byte[] mPrintDirection = {SnmpConstants.OPAQUE, 0};
        private byte[] mPaperThickness = {78, 4, 0};
        private byte[] mPaperKind = {78, 5, 0};
        private byte[] mTray = {84, 3};
        private byte[] mPaperSize = {115, 1};
        private byte[] mPaperMargin = {83, SnmpConstants.COUNTER64, 0, SnmpConstants.COUNTER64, 0, SnmpConstants.COUNTER64, 0, SnmpConstants.COUNTER64, 0};
        private byte[] mColorMatching = {104, 0};
        private byte[] mYokomePaper = {121, 0};
        private byte[] mDuplex = {50, 82, 1};
        private byte[] mDuplexOrientation = {50, 84, 0};
        private byte[] mVerticalPosition = {89, 0, 0};
        private byte[] mHorizontalPosition = {88, 0, 0};
        private byte[] mPageEnd = {SnmpConstants.UINTEGER32, 0, 0, -1};
        private int mPageIndex = 0;

        public BrotherJpegCommand(Printer printer, PrintParameters printParameters) {
            this.mPrinter = printer;
            this.mParameters = printParameters;
        }

        public byte[] jobStart() throws UnsupportedEncodingException {
            initJob();
            ShortByteArray shortByteArray = new ShortByteArray();
            if (isBrjpc(this.mPrinter) && !isJpegCommand(this.mPrinter)) {
                shortByteArray.put(BrJPCPJL.jobStart(this.mParameters));
            }
            shortByteArray.put(this.mJobSeparator);
            shortByteArray.put(this.mJobIn);
            shortByteArray.put(this.mPrinterInit);
            return shortByteArray.toArray();
        }

        public static boolean isJpegCommand(Printer printer) {
            return printer.printerPDL == PrinterPDL.Jpeg_BH11 || printer.printerPDL == PrinterPDL.Jpeg_BHmini11 || printer.printerPDL == PrinterPDL.Jpeg_BH9 || printer.printerPDL == PrinterPDL.Jpeg_BHS13;
        }

        public static boolean isBrjpc(Printer printer) {
            return printer.printerPDL == PrinterPDL.BrotherCommonPDL_ManualPageFlip || printer.printerPDL == PrinterPDL.BrotherCommonPDL_AutoPageFlip;
        }

        public byte[] jobEnd() throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(this.mPrinterInit);
            if (isJpegCommand(this.mPrinter)) {
                shortByteArray.put(this.mJobSeparator);
            } else if (isBrjpc(this.mPrinter)) {
                shortByteArray.put(BrJPCPJL.jobEnd());
            }
            return shortByteArray.toArray();
        }

        public byte[] pageStart(int i, int i2, int i3) throws UnsupportedEncodingException {
            initPage(i, i2, i3);
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(this.mJobIn);
            shortByteArray.put(this.mDocumentResolution);
            shortByteArray.put(this.mInputResolution);
            shortByteArray.put(this.mInputImagePixel);
            shortByteArray.put(this.mSetColor);
            shortByteArray.put(this.mPrintDirection);
            if (!isBrjpc(this.mPrinter)) {
                shortByteArray.put(this.mPaperThickness);
            }
            shortByteArray.put(this.mPaperKind);
            shortByteArray.put(this.mTray);
            shortByteArray.put(this.mPaperSize);
            shortByteArray.put(this.mPaperMargin);
            shortByteArray.put(this.mColorMatching);
            shortByteArray.put(this.mYokomePaper);
            if ((!isJpegCommand(this.mPrinter) || this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHS13) && this.mParameters.duplex != Duplex.Simplex) {
                shortByteArray.put(this.mDuplex);
                if (this.mPrinter.printerPDL == PrinterPDL.BrotherCommonPDL_AutoPageFlip) {
                    shortByteArray.put(this.mDuplexOrientation);
                }
            }
            shortByteArray.put(this.mVerticalPosition);
            shortByteArray.put(this.mHorizontalPosition);
            return shortByteArray.toArray();
        }

        public byte[] pageEnd() throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(this.mPageEnd);
            return shortByteArray.toArray();
        }

        public byte[] jobCancel() throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(pageEnd());
            shortByteArray.put(jobEnd());
            return shortByteArray.toArray();
        }

        public byte[] lastEmptyPage(int i, int i2, int i3) throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(pageStart(i, i2, i3));
            shortByteArray.put(pageEnd());
            return shortByteArray.toArray();
        }

        private void initJob() {
            int i = C07501.$SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[this.mPrinter.printerPDL.ordinal()];
            if (i == 2 || i == 3) {
                this.mJobIn[4] = 12;
            } else if (i == 4) {
                this.mJobIn[4] = 13;
            } else if (i != 5 && i != 6) {
                this.mJobIn[4] = 11;
            } else {
                this.mJobIn[4] = -16;
            }
            if (this.mPrinter.printerPDL == PrinterPDL.BrotherCommonPDL_ManualPageFlip || this.mPrinter.printerPDL == PrinterPDL.BrotherCommonPDL_AutoPageFlip) {
                byte[] bArr = this.mDocumentResolution;
                bArr[1] = -16;
                bArr[2] = -16;
                int i2 = this.mParameters.resolution.width;
                if (i2 == 300) {
                    this.mDocumentResolution[1] = 1;
                } else if (i2 == 600) {
                    this.mDocumentResolution[1] = 2;
                } else if (i2 == 1200) {
                    this.mDocumentResolution[1] = 3;
                } else if (i2 == 2400) {
                    this.mDocumentResolution[1] = 4;
                } else if (i2 == 6000) {
                    this.mDocumentResolution[1] = 6;
                } else {
                    this.mDocumentResolution[1] = -16;
                }
                int i3 = this.mParameters.resolution.height;
                if (i3 == 300) {
                    this.mDocumentResolution[2] = 1;
                } else if (i3 == 600) {
                    this.mDocumentResolution[2] = 2;
                } else if (i3 == 1200) {
                    this.mDocumentResolution[2] = 3;
                } else if (i3 == 2400) {
                    this.mDocumentResolution[2] = 4;
                } else if (i3 == 6000) {
                    this.mDocumentResolution[2] = 6;
                } else {
                    this.mDocumentResolution[2] = -16;
                }
                int i4 = C07501.$SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType[this.mParameters.mediaType.ordinal()];
                if (i4 == 1) {
                    this.mPaperKind[2] = 6;
                } else if (i4 == 2) {
                    this.mPaperKind[2] = 0;
                } else if (i4 == 3) {
                    this.mPaperKind[2] = 1;
                }
                this.mPrintDirection[1] = -16;
                if (this.mParameters.color == ColorProcessing.BlackAndWhite) {
                    this.mSetColor[1] = -16;
                } else {
                    this.mSetColor[1] = -15;
                }
            } else {
                int i5 = C07501.$SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType[this.mParameters.mediaType.ordinal()];
                if (i5 == 1) {
                    byte[] bArr2 = this.mDocumentResolution;
                    bArr2[1] = 3;
                    bArr2[2] = 4;
                    if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BH9) {
                        this.mPaperKind[2] = 7;
                    } else {
                        this.mPaperKind[2] = 5;
                    }
                } else if (i5 == 2) {
                    if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BH11 || this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHmini11) {
                        byte[] bArr3 = this.mDocumentResolution;
                        bArr3[1] = 3;
                        bArr3[2] = 3;
                    } else if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHS13) {
                        byte[] bArr4 = this.mDocumentResolution;
                        bArr4[1] = 3;
                        bArr4[2] = 4;
                    } else {
                        byte[] bArr5 = this.mDocumentResolution;
                        bArr5[1] = 2;
                        bArr5[2] = 2;
                    }
                    this.mPaperKind[2] = 0;
                } else if (i5 == 3) {
                    byte[] bArr6 = this.mDocumentResolution;
                    bArr6[1] = 3;
                    bArr6[2] = 4;
                    this.mPaperKind[2] = 1;
                }
                if (this.mParameters.color == ColorProcessing.BlackAndWhite) {
                    this.mSetColor[1] = SnmpConstants.CONS_SEQ;
                }
            }
            switch (C07501.$SwitchMap$com$brother$sdk$common$device$MediaSize[this.mParameters.paperSize.ordinal()]) {
                case 1:
                    this.mPaperSize[1] = SnmpConstants.SNMP_ERR_DECODINGASN_EXC;
                    break;
                case 2:
                    this.mPaperSize[1] = 24;
                    break;
                case 3:
                    if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHS13) {
                        this.mPaperSize[1] = 40;
                    } else {
                        this.mPaperSize[1] = 0;
                    }
                    break;
                case 4:
                    if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHS13) {
                        this.mPaperSize[1] = 42;
                    } else {
                        this.mPaperSize[1] = 1;
                    }
                    break;
                case 5:
                    this.mPaperSize[1] = 2;
                    break;
                case 6:
                    this.mPaperSize[1] = 30;
                    break;
                case 7:
                    this.mPaperSize[1] = 29;
                    break;
                case 8:
                case 9:
                    this.mPaperSize[1] = SnmpConstants.ASN_EXTENSION_ID;
                    break;
                case 10:
                    this.mPaperSize[1] = 17;
                    break;
                case 11:
                case 12:
                    if (this.mPrinter.printerPDL == PrinterPDL.Jpeg_BHS13) {
                        this.mPaperSize[1] = 45;
                    } else {
                        this.mPaperSize[1] = 4;
                    }
                    break;
                case 13:
                    this.mPaperSize[1] = 7;
                    break;
                case 14:
                    this.mPaperSize[1] = 11;
                    break;
                case 15:
                    this.mPaperSize[1] = 46;
                    break;
                case 16:
                    this.mPaperSize[1] = 8;
                    break;
                case 17:
                    this.mPaperSize[1] = 25;
                    break;
                case 18:
                    this.mPaperSize[1] = 3;
                    break;
                case 19:
                    this.mPaperSize[1] = 4;
                    break;
                case 20:
                    this.mPaperSize[1] = 10;
                    break;
                case 21:
                    this.mPaperSize[1] = 33;
                    break;
            }
            if (this.mParameters.margin == PrintMargin.Borderless) {
                byte[] bArr7 = this.mPaperMargin;
                bArr7[1] = -60;
                bArr7[2] = -1;
                bArr7[3] = -60;
                bArr7[4] = -1;
                bArr7[5] = -60;
                bArr7[6] = -1;
                bArr7[7] = -60;
                bArr7[8] = -1;
            } else {
                byte[] bArr8 = this.mPaperMargin;
                bArr8[1] = SnmpConstants.COUNTER64;
                bArr8[2] = 0;
                bArr8[3] = SnmpConstants.COUNTER64;
                bArr8[4] = 0;
                bArr8[5] = SnmpConstants.COUNTER64;
                bArr8[6] = 0;
                bArr8[7] = SnmpConstants.COUNTER64;
                bArr8[8] = 0;
            }
            int i6 = C07501.f526x920d9f03[this.mParameters.colorMatching.ordinal()];
            if (i6 == 2) {
                this.mColorMatching[1] = 1;
            } else if (i6 == 3) {
                this.mColorMatching[1] = 8;
            }
            if (this.mParameters.duplex == Duplex.FlipOnLongEdge) {
                this.mDuplexOrientation[2] = 0;
            } else if (this.mParameters.duplex == Duplex.FlipOnShortEdge) {
                this.mDuplexOrientation[2] = 1;
            }
            byte[] bArr9 = new byte[4096];
            this.mJobSeparator = bArr9;
            Arrays.fill(bArr9, (byte) 0);
        }

        private void initPage(int i, int i2, int i3) {
            byte[] bArr = this.mInputImagePixel;
            bArr[1] = (byte) (i % 256);
            bArr[2] = (byte) (i / 256);
            bArr[3] = (byte) (i2 % 256);
            bArr[4] = (byte) (i2 / 256);
            if (i3 == 300) {
                byte[] bArr2 = this.mInputResolution;
                bArr2[1] = 1;
                bArr2[2] = 1;
            } else if (i3 == 600) {
                byte[] bArr3 = this.mInputResolution;
                bArr3[1] = 2;
                bArr3[2] = 2;
            } else if (i3 == 1200) {
                byte[] bArr4 = this.mInputResolution;
                bArr4[1] = 3;
                bArr4[2] = 3;
            } else {
                byte[] bArr5 = this.mInputResolution;
                bArr5[1] = 0;
                bArr5[2] = 0;
            }
            if (this.mParameters.duplex != Duplex.Simplex) {
                if (this.mPageIndex % 2 == 0) {
                    this.mDuplex[2] = 1;
                } else {
                    this.mDuplex[2] = 2;
                }
            }
            this.mPageIndex++;
        }

        private static class BrJPCPJL {
            private static final String PJL_JOB_END = "\u001b%-12345X@PJL EOJ\n\u001b%-12345X";
            private static final String PJL_JOB_PARAM = "@PJL SET RENDERMODE=%s\n@PJL SET MEDIATYPE=%s\n@PJL SET SOURCETRAY=AUTO\n@PJL SET FUNCTYPE=IPRINT\n@PJL SET PAPER=%s\n@PJL SET COPIES=1\n@PJL SET DUPLEX=%s\n@PJL SET RESOLUTION=%s\n@PJL SET PRINTQUALITY=NORMAL\n@PJL ENTER LANGUAGE=BRJPC\n";
            private static final String PJL_JOB_START = "\u001b%-12345X@PJL\n@PJL JOB\n";

            private BrJPCPJL() {
            }

            static byte[] jobStart(PrintParameters printParameters) throws UnsupportedEncodingException {
                String str;
                String str2;
                String str3;
                String str4 = printParameters.color != ColorProcessing.FullColor ? "GRAYSCALE\n@PJL SET COLORADAPT=OFF" : "COLOR\n@PJL SET COLORADAPT=ON";
                if (printParameters.duplex == Duplex.FlipOnLongEdge) {
                    str = "ON\n@PJL SET BINDING=LONGEDGE";
                } else {
                    str = printParameters.duplex == Duplex.FlipOnShortEdge ? "ON\n@PJL SET BINDING=SHORTEDGE" : "OFF";
                }
                if (printParameters.mediaType == PrintMediaType.Photographic) {
                    str2 = "GLOSSY";
                } else {
                    str2 = printParameters.mediaType == PrintMediaType.InkjetPaper ? "THICK2" : "REGULAR";
                }
                switch (printParameters.paperSize) {
                    case Letter:
                        str3 = "LETTER";
                        break;
                    case A4:
                    case Ledger:
                    case JISB4:
                    case B4:
                    case Photo2L:
                    default:
                        str3 = "A4";
                        break;
                    case Legal:
                        str3 = "LEGAL";
                        break;
                    case A3:
                        str3 = "A3";
                        break;
                    case Hagaki:
                        str3 = "POSTCARD";
                        break;
                    case JISB5:
                    case B5:
                        str3 = "B5";
                        break;
                    case A5:
                        str3 = "A5";
                        break;
                    case A6:
                        str3 = "A6";
                        break;
                    case SIXTEENK:
                        str3 = "SIXTEENK195X270";
                        break;
                    case B6:
                        str3 = "B6";
                        break;
                    case Executive:
                        str3 = "EXECUTIVE";
                        break;
                    case C5Envelope:
                        str3 = "C5";
                        break;
                    case DLEnvelope:
                        str3 = ASN1Encoding.f2807DL;
                        break;
                    case Folio:
                        str3 = "FOLIO";
                        break;
                }
                return (PJL_JOB_START + String.format(PJL_JOB_PARAM, str4, str2, str3, str, Integer.valueOf(printParameters.resolution.height))).getBytes("UTF-8");
            }

            static byte[] jobEnd() throws UnsupportedEncodingException {
                return PJL_JOB_END.getBytes("UTF-8");
            }
        }
    }

    static class C07501 {

        static final int[] f526x920d9f03;
        static final int[] $SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType;
        static final int[] $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL;

        static {
            int[] iArr = new int[PrintColorMatching.values().length];
            f526x920d9f03 = iArr;
            try {
                iArr[PrintColorMatching.ContentOriginal.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f526x920d9f03[PrintColorMatching.SlowDryPrint.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f526x920d9f03[PrintColorMatching.CopyQuality.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[MediaSize.values().length];
            $SwitchMap$com$brother$sdk$common$device$MediaSize = iArr2;
            try {
                iArr2[MediaSize.Index4x6.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.PhotoL.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Letter.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A4.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Legal.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A3.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Ledger.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.JISB4.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.B4.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Hagaki.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.JISB5.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.B5.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A5.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A6.ordinal()] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.SIXTEENK.ordinal()] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.B6.ordinal()] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Photo2L.ordinal()] = 17;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Executive.ordinal()] = 18;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.C5Envelope.ordinal()] = 19;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.DLEnvelope.ordinal()] = 20;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Folio.ordinal()] = 21;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr3 = new int[PrintMediaType.values().length];
            $SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType = iArr3;
            try {
                iArr3[PrintMediaType.Photographic.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType[PrintMediaType.Plain.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrintMediaType[PrintMediaType.InkjetPaper.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
            int[] iArr4 = new int[PrinterPDL.values().length];
            $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL = iArr4;
            try {
                iArr4[PrinterPDL.Jpeg_BH9.ordinal()] = 1;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BHmini11.ordinal()] = 2;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BH11.ordinal()] = 3;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BHS13.ordinal()] = 4;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.BrotherCommonPDL_ManualPageFlip.ordinal()] = 5;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.BrotherCommonPDL_AutoPageFlip.ordinal()] = 6;
            } catch (NoSuchFieldError unused33) {
            }
        }
    }
}
