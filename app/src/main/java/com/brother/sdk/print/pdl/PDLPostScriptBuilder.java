package com.brother.sdk.print.pdl;

import android.graphics.Bitmap;
import com.brother.sdk.common.Callback;
import com.brother.sdk.common.OutOfMemoryException;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.DeviceModelType;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.printer.PrintCollate;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.util.ShortByteArray;
import com.brother.sdk.common.util.Tool;
import com.brother.sdk.print.PrintParameters;
import com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder;
import com.brother.sdk.print.pdl.PrintImageUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PDLPostScriptBuilder extends PageDescriptionLanguageBuilder {
    protected PDLPostScriptBuilder(Printer printer, Callback callback) {
        super(printer, callback);
    }

    @Override
    protected void buildDataBlocksInternal(Printer printer, PrintParameters printParameters, List<File> list, File file, PageDescriptionLanguageBuilder.PrintDataBlockCreateCallback printDataBlockCreateCallback) throws OutOfMemoryException, IOException {
        BrotherPostScript brotherPostScript = new BrotherPostScript(printer, printParameters);
        ArrayList arrayList = new ArrayList();
        PrintDataBlockJob printDataBlockJob = new PrintDataBlockJob(brotherPostScript.jobStart());
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
            PrintDataBlockPage printDataBlockPage = new PrintDataBlockPage(brotherPostScript.pageStart(printableDataCreatePrintableData.mWidth, printableDataCreatePrintableData.mHeight, printableDataCreatePrintableData.mDpi), brotherPostScript.pageEnd(), printableDataCreatePrintableData.mDataPath);
            arrayList.add(printDataBlockPage);
            printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockPage);
            notifyCreationProgress(valueCoordinator.coordinateValueInRange(100));
        }
        PrintDataBlockJob printDataBlockJob2 = new PrintDataBlockJob(brotherPostScript.jobEnd());
        arrayList.add(printDataBlockJob2);
        printDataBlockCreateCallback.OnDataBlockCreated(printDataBlockJob2);
        if (printParameters.collate == PrintCollate.ON) {
            int size2 = arrayList.size();
            int i2 = printParameters.copyCount;
            for (int i3 = 1; i3 < i2; i3++) {
                for (int i4 = 0; i4 < size2; i4++) {
                    printDataBlockCreateCallback.OnDataBlockCreated((PrintDataBlock) arrayList.get(i4));
                }
            }
        }
        printDataBlockCreateCallback.OnCancelBlockCreated(new PrintDataBlockCancel(brotherPostScript.jobCancel()));
    }

    private PageDescriptionLanguageBuilder.PrintableData createPrintableData(Printer printer, PrintParameters printParameters, File file, int i, File file2) throws OutOfMemoryException, IOException {
        int iMax = Math.max(printParameters.resolution.width, printParameters.resolution.height);
        PrintMargin.PrintableArea printableArea = printParameters.margin.getPrintableArea(printParameters.paperSize, printer.modelType);
        double d = iMax;
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
        Bitmap bitmapCreatePrintableBitmap = PrintImageUtil.createPrintableBitmap(bitmap, (int) d2, (int) d3, new PrintImageUtil.PrintImageParameters(printParameters));
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
            PageDescriptionLanguageBuilder.PrintableData printableData = new PageDescriptionLanguageBuilder.PrintableData();
            printableData.mDataPath = makeAscii85EncodeFile(fileCreateTempFile, file2);
            printableData.mDpi = iMax;
            printableData.mWidth = width;
            printableData.mHeight = height;
            return printableData;
        } finally {
            if (bitmapCreatePrintableBitmap != null) {
                bitmapCreatePrintableBitmap.recycle();
            }
        }
    }

    public static File makeAscii85EncodeFile(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        File fileCreateTempFile = File.createTempFile("temp", ".jpq", file2);
        FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
        byte[] bArr = new byte[4096];
        byte[] bArr2 = new byte[8192];
        while (true) {
            int i = fileInputStream.read(bArr, 0, 4096);
            if (i <= 0) {
                return fileCreateTempFile;
            }
            fileOutputStream.write(bArr2, 0, encodeWithAscii85(bArr, i, bArr2));
            fileOutputStream.flush();
        }
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int encodeWithAscii85(byte[] r12, int r13, byte[] r14) {
        /*
            Method dump skipped, instruction units count: 207
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.print.pdl.PDLPostScriptBuilder.encodeWithAscii85(byte[], int, byte[]):int");
    }

    static class BrotherPostScript {
        private static final double INCH_PER_POINT = 72.0d;
        private static final String PJL_JOB_END = "%%EOF\n\u001b%-12345X\u001b%-12345X@PJL EOJ \n\u001b%-12345X\n";
        private static final String PJL_JOB_PARAM = "@PJL SET COPIES=%d\n@PJL SET RENDERMODE=%s\n@PJL SET DUPLEX=%s@PJL SET FUNCTYPE=IPRINT\n@PJL ENTER LANGUAGE = POSTSCRIPT \n%%!PS-Adobe-3.0\n<< /PageSize [%d %d] /ImagingBBox null %s >> setpagedevice\n";
        private static final String PJL_JOB_START = "\u001b%-12345X@PJL JOB\n";
        private static final String PJL_PAGE_END = "~>\n%%EndBinary\nshowpage\n";
        private static final String PJL_PAGE_START = "/DeviceRGB setcolorspace\n/yoko %d def\n/tate %d def\n%d %d translate\n%d %d scale\n<<\n\t/ImageType 1\n\t/Width yoko\n\t/Height tate\n\t/BitsPerComponent 8\n\t/ImageMatrix %s\n\t/Decode [0 1 0 1 0 1]\n\t/DataSource currentfile /ASCII85Decode filter /DCTDecode filter\n>>\n%%%%BeginBinary\nimage\n";
        private PrintParameters mParameters;
        private Printer mPrinter;

        public BrotherPostScript(Printer printer, PrintParameters printParameters) {
            this.mPrinter = printer;
            this.mParameters = printParameters;
        }

        public byte[] jobStart() throws UnsupportedEncodingException {
            String str;
            String str2;
            String str3;
            int i = this.mParameters.copyCount;
            if (this.mParameters.collate == PrintCollate.ON) {
                i = 1;
            }
            String str4 = this.mParameters.color != ColorProcessing.FullColor ? "GRAYSCALE" : "COLOR";
            if (this.mParameters.duplex == Duplex.FlipOnLongEdge) {
                str = "ON\n@PJL SET BINDING=LONGEDGE\n";
            } else {
                str = this.mParameters.duplex == Duplex.FlipOnShortEdge ? "ON\n@PJL SET BINDING=SHORTEDGE\n" : "OFF\n";
            }
            String str5 = str;
            if (this.mPrinter.deviceModel == DeviceModelType.Cobra) {
                str3 = "/HWResolution [600 600]";
            } else if (this.mPrinter.capabilities.colorTypes.contains(ColorProcessing.FullColor)) {
                int i2 = C07521.$SwitchMap$com$brother$sdk$common$device$printer$PrintQuality[this.mParameters.quality.ordinal()];
                if (i2 == 1 || i2 == 2 || i2 == 3) {
                    str2 = "/BRApt 2 /HWResolution [600 600]";
                    str3 = str2;
                }
                str3 = "/HWResolution [600 600]";
            } else {
                int i3 = C07521.$SwitchMap$com$brother$sdk$common$device$printer$PrintQuality[this.mParameters.quality.ordinal()];
                if (i3 == 1 || i3 == 2 || i3 == 3) {
                    str2 = "/HWResolution [1200 1200] statusdict /true1200 known {statusdict begin false true1200 end} if";
                    str3 = str2;
                }
                str3 = "/HWResolution [600 600]";
            }
            MediaSize mediaSize = this.mParameters.paperSize;
            return (PJL_JOB_START + String.format(PJL_JOB_PARAM, Integer.valueOf(i), str4, str5, Integer.valueOf((int) ((mediaSize.width * INCH_PER_POINT) + 0.5d)), Integer.valueOf((int) ((mediaSize.height * INCH_PER_POINT) + 0.5d)), str3)).getBytes("UTF-8");
        }

        public byte[] jobEnd() throws UnsupportedEncodingException {
            return PJL_JOB_END.getBytes("UTF-8");
        }

        public byte[] pageStart(int i, int i2, int i3) throws UnsupportedEncodingException {
            int i4;
            float f;
            float f2;
            int i5;
            int i6;
            String str;
            float f3;
            float f4;
            PrintMargin.PrintableArea printableArea = this.mParameters.margin.getPrintableArea(this.mParameters.paperSize, this.mPrinter.modelType);
            int i7 = (int) (printableArea.width * INCH_PER_POINT);
            int i8 = (int) (printableArea.height * INCH_PER_POINT);
            PrintImageUtil.ConvertSetting2 convertSetting2 = new PrintImageUtil.ConvertSetting2(i, i2, i7, i8, new PrintImageUtil.PrintImageParameters(this.mParameters));
            int i9 = 12;
            if (convertSetting2.mLongSideFittingFlag) {
                if (convertSetting2.rotateFlag) {
                    i6 = ((int) (((double) ((i7 - (convertSetting2.mUseInputWidth * convertSetting2.scale)) / 2.0f)) + 0.5d)) + 12;
                    f3 = i2;
                    f4 = convertSetting2.scale;
                } else {
                    i6 = ((int) (((double) ((i7 - (convertSetting2.mUseInputWidth * convertSetting2.scale)) / 2.0f)) + 0.5d)) + 12;
                    f3 = i;
                    f4 = convertSetting2.scale;
                }
                i7 = (int) (f3 * f4);
                i5 = 12;
            } else {
                if (convertSetting2.rotateFlag) {
                    i4 = ((int) (((double) ((i8 - (convertSetting2.mUseInputHeight * convertSetting2.scale)) / 2.0f)) + 0.5d)) + 12;
                    f = i;
                    f2 = convertSetting2.scale;
                } else {
                    i4 = ((int) (((double) ((i8 - (convertSetting2.mUseInputHeight * convertSetting2.scale)) / 2.0f)) + 0.5d)) + 12;
                    f = i2;
                    f2 = convertSetting2.scale;
                }
                i8 = (int) (f * f2);
                i5 = i4;
                i6 = 12;
            }
            if (convertSetting2.rotateFlag) {
                str = String.format(Locale.getDefault(), "[0 %d neg %d neg 0 %d %d]", Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                str = String.format(Locale.getDefault(), "[%d 0 0 %d neg 0 %d]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i2));
            }
            String str2 = str;
            int i10 = 34;
            if (this.mParameters.paperSize == MediaSize.SIXTEENK) {
                i5 = 12;
                i6 = 34;
            }
            if (this.mParameters.paperSize != MediaSize.SIXTEENK) {
                i10 = i6;
                i9 = i5;
            }
            return String.format(Locale.getDefault(), PJL_PAGE_START, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i10), Integer.valueOf(i9), Integer.valueOf(i7), Integer.valueOf(i8), str2).getBytes("UTF-8");
        }

        public byte[] pageEnd() throws UnsupportedEncodingException {
            return PJL_PAGE_END.getBytes("UTF-8");
        }

        public byte[] jobCancel() throws UnsupportedEncodingException {
            ShortByteArray shortByteArray = new ShortByteArray();
            shortByteArray.put(jobEnd());
            return shortByteArray.toArray();
        }
    }

    static class C07521 {
        static final int[] $SwitchMap$com$brother$sdk$common$device$printer$PrintQuality;

        static {
            int[] iArr = new int[PrintQuality.values().length];
            $SwitchMap$com$brother$sdk$common$device$printer$PrintQuality = iArr;
            try {
                iArr[PrintQuality.Photographic.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrintQuality[PrintQuality.Document.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrintQuality[PrintQuality.WebPage.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrintQuality[PrintQuality.Draft.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
