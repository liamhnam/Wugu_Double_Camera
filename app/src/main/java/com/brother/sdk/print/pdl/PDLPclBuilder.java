package com.brother.sdk.print.pdl;

import android.graphics.Bitmap;
import com.brother.sdk.common.Callback;
import com.brother.sdk.common.OutOfMemoryException;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.printer.PrintCollate;
import com.brother.sdk.common.device.printer.PrintCustomScaling;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.PrintScale;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.util.ShortByteArray;
import com.brother.sdk.common.util.Tool;
import com.brother.sdk.jni.print.image.NativePrintImageUtil;
import com.brother.sdk.print.PrintParameters;
import com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder;
import com.brother.sdk.print.pdl.PrintImageUtil;
import com.p020hp.jipp.model.Status;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PDLPclBuilder extends PageDescriptionLanguageBuilder {
    protected PDLPclBuilder(Printer printer, Callback callback) {
        super(printer, callback);
    }

    @Override
    protected void buildDataBlocksInternal(Printer printer, PrintParameters printParameters, List<File> list, File file, PageDescriptionLanguageBuilder.PrintDataBlockCreateCallback printDataBlockCreateCallback) throws OutOfMemoryException, IOException {
        BrotherMode1030PCL brotherMode1030PCL = new BrotherMode1030PCL(printer, printParameters);
        ArrayList arrayList = new ArrayList();
        PrintDataBlockJob printDataBlockJob = new PrintDataBlockJob(brotherMode1030PCL.jobStart());
        arrayList.add(printDataBlockJob);
        printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockJob);
        int size = 100 / list.size();
        for (int i = 0; i < list.size(); i++) {
            Tool.ValueCoordinator valueCoordinator = new Tool.ValueCoordinator(100, size * i, size);
            File file2 = list.get(i);
            if (!file2.exists() || !file2.isFile() || !file2.canRead()) {
                throw new IOException("jLpr Error opening print file");
            }
            if (isCanceled()) {
                throw new IOException("Creation should not be continued more.");
            }
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(25));
            PageDescriptionLanguageBuilder.PrintableData printableDataCreatePrintableData = createPrintableData(printer, printParameters, file2, i, file);
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(50));
            PrintDataBlockPage printDataBlockPage = new PrintDataBlockPage(brotherMode1030PCL.pageStart(printableDataCreatePrintableData.mWidth, printableDataCreatePrintableData.mHeight, printableDataCreatePrintableData.mDpi), brotherMode1030PCL.pageEnd(), printableDataCreatePrintableData.mDataPath);
            arrayList.add(printDataBlockPage);
            printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockPage);
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(100));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 1; i2 < arrayList.size(); i2++) {
            arrayList2.add((PrintDataBlock) arrayList.get(i2));
        }
        if (printParameters.collate == PrintCollate.ON) {
            for (int i3 = 1; i3 < printParameters.copyCount; i3++) {
                for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                    printDataBlockCreateCallback.OnDataBlockCreated((PrintDataBlock) arrayList2.get(i4));
                }
            }
        }
        PrintDataBlockJob printDataBlockJob2 = new PrintDataBlockJob(brotherMode1030PCL.jobEnd());
        arrayList.add(printDataBlockJob2);
        printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockJob2);
        printDataBlockCreateCallback.OnCancelBlockCreated(new PrintDataBlockCancel(brotherMode1030PCL.jobCancel()));
    }

    private static PrintImageUtil.PrintImageParameters makePrintImageParameters(PrintParameters printParameters) {
        PrintImageUtil.PrintImageParameters printImageParameters = new PrintImageUtil.PrintImageParameters(printParameters);
        if (printParameters.scale == PrintScale.NoScaling) {
            double outputResolution = ((double) BrotherMode1030PCL.getOutputResolution(printParameters)) / ((double) Math.max(printParameters.resolution.width, printParameters.resolution.height));
            printImageParameters.customScaling = new PrintCustomScaling(outputResolution, outputResolution);
            printImageParameters.scale = PrintScale.CustomScaling;
        } else if (printParameters.scale == PrintScale.NoScalingAtPrintableAreaCenter) {
            double outputResolution2 = ((double) BrotherMode1030PCL.getOutputResolution(printParameters)) / ((double) Math.max(printParameters.resolution.width, printParameters.resolution.height));
            printImageParameters.customScaling = new PrintCustomScaling(outputResolution2, outputResolution2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
            printImageParameters.scale = PrintScale.CustomScaling;
        } else if (printParameters.scale == PrintScale.CustomScaling) {
            double outputResolution3 = ((double) BrotherMode1030PCL.getOutputResolution(printParameters)) / ((double) Math.max(printParameters.resolution.width, printParameters.resolution.height));
            if (printParameters.customScaling != null) {
                printParameters.customScaling.scaleX *= outputResolution3;
                printParameters.customScaling.scaleY *= outputResolution3;
            }
        }
        return printImageParameters;
    }

    private PageDescriptionLanguageBuilder.PrintableData createPrintableData(Printer printer, PrintParameters printParameters, File file, int i, File file2) throws OutOfMemoryException, IOException {
        int outputResolution = BrotherMode1030PCL.getOutputResolution(printParameters);
        PrintMargin.PrintableArea printableArea = printParameters.margin.getPrintableArea(printParameters.paperSize, printer.modelType);
        double d = outputResolution;
        double d2 = printableArea.width * d;
        double d3 = printableArea.height * d;
        PrintImageUtil.DecodedBitmap decodedBitmapDecodeFileToDecodedBitmap = PrintImageUtil.decodeFileToDecodedBitmap(file.getPath(), printParameters);
        if (decodedBitmapDecodeFileToDecodedBitmap == null || decodedBitmapDecodeFileToDecodedBitmap.bitmap == null) {
            throw new IOException();
        }
        if (isCanceled()) {
            throw new IOException("Creation should not be continued more.");
        }
        Bitmap bitmap = decodedBitmapDecodeFileToDecodedBitmap.bitmap;
        if (decodedBitmapDecodeFileToDecodedBitmap.needRescalingToAdjust()) {
            decodedBitmapDecodeFileToDecodedBitmap.updatePrintParametersWithDecodedInformation(printParameters);
        }
        Bitmap bitmapCreatePrintableBitmap = PrintImageUtil.createPrintableBitmap(bitmap, (int) d2, (int) d3, makePrintImageParameters(printParameters));
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        if (isCanceled()) {
            throw new IOException("Creation should not be continued more.");
        }
        File fileCreateTempFile = File.createTempFile("temp", ".jpq", file2);
        try {
            int width = bitmapCreatePrintableBitmap.getWidth();
            int height = bitmapCreatePrintableBitmap.getHeight();
            FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
            bitmapCreatePrintableBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (isCanceled()) {
                throw new IOException("Creation should not be continued more.");
            }
            int iOrdinal = printParameters.quality.ordinal();
            PageDescriptionLanguageBuilder.PrintableData printableData = new PageDescriptionLanguageBuilder.PrintableData();
            printableData.mDataPath = makeCompressedFile(fileCreateTempFile, file2, iOrdinal);
            printableData.mDpi = outputResolution;
            printableData.mWidth = width;
            printableData.mHeight = height;
            return printableData;
        } finally {
            if (bitmapCreatePrintableBitmap != null) {
                bitmapCreatePrintableBitmap.recycle();
            }
        }
    }

    private File makeCompressedFile(File file, File file2, int i) throws IOException {
        File fileCreateTempFile = File.createTempFile("temp", ".jpq", file2);
        NativePrintImageUtil.compressImageFileWithMode1030(file.getPath(), fileCreateTempFile.getPath(), i);
        return fileCreateTempFile;
    }

    static class BrotherMode1030PCL {
        private static final int OUTPUT_RESOLUTION_HIGHQUALITY = 600;
        private static final int OUTPUT_RESOLUTION_NORMALQUALITY = 300;
        private static final String PJL_JOB_END = "\u001b%-12345X@PJL EOJ \n\u001b%-12345X\n";
        private static final String PJL_JOB_PARAM = "@PJL SET RESOLUTION=%d\n@PJL SET ECONOMODE=OFF\n@PJL SET FUNCTYPE=IPRINT\n@PJL SET DENSITY=+1\n@PJL ENTER LANGUAGE=PCL\n";
        private static final String PJL_JOB_START = "\u001b%-12345X@PJL\n";
        private static final String PJL_PAGE_END = "\f";
        private static final String PJL_PAGE_START = "\u001b*b1030M";
        private static final String PJL_PCL_PARAM = "\u001b&u%dD\u001b*t%dR\u001b&n8WdRegular\u001b&l7H\u001b&l%dS\u001b&l%dX\u001b&l%dA";
        private PrintParameters mParameters;
        private Printer mPrinter;

        public BrotherMode1030PCL(Printer printer, PrintParameters printParameters) {
            this.mPrinter = printer;
            this.mParameters = printParameters;
        }

        public byte[] jobStart() throws UnsupportedEncodingException {
            int outputResolution = getOutputResolution(this.mParameters);
            int i = this.mParameters.copyCount;
            int i2 = 1;
            if (this.mParameters.collate == PrintCollate.ON) {
                i = 1;
            }
            int i3 = C07511.$SwitchMap$com$brother$sdk$common$device$Duplex[this.mParameters.duplex.ordinal()];
            int i4 = i3 != 1 ? i3 != 2 ? 0 : 1 : 2;
            switch (C07511.$SwitchMap$com$brother$sdk$common$device$MediaSize[this.mParameters.paperSize.ordinal()]) {
                case 1:
                    i2 = 2;
                    break;
                case 2:
                    i2 = 3;
                    break;
                case 3:
                    i2 = Status.Code.clientErrorConflictingAttributes;
                    break;
                case 4:
                    break;
                case 5:
                    i2 = 100;
                    break;
                case 6:
                    i2 = 45;
                    break;
                case 7:
                    i2 = 1024;
                    break;
                case 8:
                    i2 = 25;
                    break;
                case 9:
                    i2 = 24;
                    break;
                default:
                    i2 = 26;
                    break;
            }
            return ((PJL_JOB_START + String.format(PJL_JOB_PARAM, Integer.valueOf(outputResolution))) + String.format(PJL_PCL_PARAM, Integer.valueOf(outputResolution), Integer.valueOf(outputResolution), Integer.valueOf(i4), Integer.valueOf(i), Integer.valueOf(i2))).getBytes("UTF-8");
        }

        public byte[] jobEnd() throws UnsupportedEncodingException {
            return PJL_JOB_END.getBytes("UTF-8");
        }

        public byte[] pageStart(int i, int i2, int i3) throws UnsupportedEncodingException {
            return PJL_PAGE_START.getBytes("UTF-8");
        }

        public byte[] pageEnd() throws UnsupportedEncodingException {
            return PJL_PAGE_END.getBytes("UTF-8");
        }

        public byte[] jobCancel() throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(jobEnd());
            return shortByteArray.toArray();
        }

        public static int getOutputResolution(PrintParameters printParameters) {
            return printParameters.quality != PrintQuality.Draft ? 600 : 300;
        }
    }

    static class C07511 {
        static final int[] $SwitchMap$com$brother$sdk$common$device$Duplex;
        static final int[] $SwitchMap$com$brother$sdk$common$device$MediaSize;

        static {
            int[] iArr = new int[MediaSize.values().length];
            $SwitchMap$com$brother$sdk$common$device$MediaSize = iArr;
            try {
                iArr[MediaSize.Letter.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Legal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Folio.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.Executive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.B5.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.JISB5.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.B6.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A5.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A6.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$MediaSize[MediaSize.A4.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            int[] iArr2 = new int[Duplex.values().length];
            $SwitchMap$com$brother$sdk$common$device$Duplex = iArr2;
            try {
                iArr2[Duplex.FlipOnShortEdge.ordinal()] = 1;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$Duplex[Duplex.FlipOnLongEdge.ordinal()] = 2;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$Duplex[Duplex.Simplex.ordinal()] = 3;
            } catch (NoSuchFieldError unused13) {
            }
        }
    }
}
