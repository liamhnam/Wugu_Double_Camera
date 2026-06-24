package com.brother.sdk.print.pdl;

import android.content.Context;
import com.brother.sdk.common.Callback;
import com.brother.sdk.common.OutOfMemoryException;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.device.printer.PrinterPDL;
import com.brother.sdk.common.util.Tool;
import com.brother.sdk.print.PrintParameters;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class PageDescriptionLanguageBuilder {
    protected static final int JPG_QUALITY = 100;
    protected static final String JPQ_EXT = ".jpq";
    protected static final int PROGRESS_END = 100;
    protected static final int PROGRESS_PHASE1 = 25;
    protected static final int PROGRESS_PHASE2 = 50;
    protected static final int PROGRESS_PHASE3 = 75;
    protected static final int PROGRESS_START = 1;
    protected static final String TEMP_PREFIX = "temp";
    protected static final String UTF8 = "UTF-8";
    private Callback mCallback;
    private boolean mCancel = false;
    private Tool.ValueCoordinator mCoordinator = null;
    private Printer mPrinter;

    public interface PrintDataBlockCreateCallback {
        void OnCancelBlockCreated(PrintDataBlockCancel printDataBlockCancel);

        void OnDataBlockCreated(PrintDataBlock printDataBlock);
    }

    protected abstract void buildDataBlocksInternal(Printer printer, PrintParameters printParameters, List<File> list, File file, PrintDataBlockCreateCallback printDataBlockCreateCallback) throws OutOfMemoryException, IOException;

    static class C07531 {
        static final int[] $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL;

        static {
            int[] iArr = new int[PrinterPDL.values().length];
            $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL = iArr;
            try {
                iArr[PrinterPDL.Jpeg_BH11.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BH9.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BHmini11.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.Jpeg_BHS13.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.BrotherCommonPDL_ManualPageFlip.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.BrotherCommonPDL_AutoPageFlip.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.PostScript.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[PrinterPDL.PCL_BrotherMonochrome.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static PageDescriptionLanguageBuilder createPDLBuilder(Printer printer, Callback callback) {
        switch (C07531.$SwitchMap$com$brother$sdk$common$device$printer$PrinterPDL[printer.printerPDL.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new PDLJpegBuilder(printer, callback);
            case 7:
                return new PDLPostScriptBuilder(printer, callback);
            default:
                return new PDLPclBuilder(printer, callback);
        }
    }

    public static PageDescriptionLanguageBuilder createDirectPrintingBuilder(Printer printer, Callback callback) {
        if (printer.capabilities.directPrintSupport) {
            return new PDLRawFileBuilder(printer, callback);
        }
        return null;
    }

    protected PageDescriptionLanguageBuilder(Printer printer, Callback callback) {
        this.mPrinter = printer;
        this.mCallback = callback;
    }

    public PDLStream buildStream(PrintParameters printParameters, List<File> list, File file, Context context) {
        C07531 c07531 = null;
        try {
            notifyCreationProgress(1);
            PDLStreamBuilder pDLStreamBuilder = new PDLStreamBuilder(c07531);
            buildDataBlocksInternal(this.mPrinter, printParameters, list, file, pDLStreamBuilder);
            notifyCreationProgress(100);
            return pDLStreamBuilder.build();
        } catch (Exception e) {
            writeLog("ErrorPrint in PageDescriptionLanguageBuilder buildStream exception\n" + exceptionToString(e), context);
            e.printStackTrace();
            return null;
        }
    }

    public static String exceptionToString(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public void writeLog(String str, Context context) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            String str2 = simpleDateFormat.format(date);
            String str3 = str2 + ":  " + str + "\n";
            File file = new File(context.getExternalFilesDir(null), simpleDateFormat2.format(date) + "log.txt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(str3.getBytes());
            fileOutputStream.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void buildDataBlocks(PrintParameters printParameters, List<File> list, File file, PrintDataBlockCreateCallback printDataBlockCreateCallback) {
        try {
            notifyCreationProgress(1);
            buildDataBlocksInternal(this.mPrinter, printParameters, list, file, printDataBlockCreateCallback);
            notifyCreationProgress(100);
        } catch (Exception unused) {
        }
    }

    public void cancel() {
        this.mCancel = true;
    }

    protected boolean isCanceled() {
        return this.mCancel;
    }

    protected void notifyCreationProgress(int i) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onUpdateProcessProgress(i);
        }
    }

    private static class PDLStreamBuilder implements PrintDataBlockCreateCallback {
        private PrintDataBlockCancel mCancelBlock;
        private List<PrintDataBlock> mDataBlocks;

        private PDLStreamBuilder() {
            this.mDataBlocks = new ArrayList();
            this.mCancelBlock = null;
        }

        PDLStreamBuilder(C07531 c07531) {
            this();
        }

        @Override
        public void OnDataBlockCreated(PrintDataBlock printDataBlock) {
            this.mDataBlocks.add(printDataBlock);
        }

        @Override
        public void OnCancelBlockCreated(PrintDataBlockCancel printDataBlockCancel) {
            this.mCancelBlock = printDataBlockCancel;
        }

        public PDLStream build() {
            return new PDLStream(this.mDataBlocks, this.mCancelBlock);
        }
    }

    protected static class PrintableData {
        File mDataPath;
        int mDpi;
        int mHeight;
        int mWidth;

        protected PrintableData() {
        }
    }

    static abstract class PrintCommand {
        protected PrintParameters mParameters;
        protected Printer mPrinter;

        public abstract PrintDataBlockCancel cancelJob() throws UnsupportedEncodingException;

        public abstract PrintDataBlockJob jobEnd() throws UnsupportedEncodingException;

        public abstract PrintDataBlockJob jobStart() throws UnsupportedEncodingException;

        public abstract PrintDataBlockLastPage lastPage() throws UnsupportedEncodingException;

        public abstract boolean needLastWhitePage();

        public abstract PrintDataBlockPage pageData(String str) throws UnsupportedEncodingException;

        protected PrintCommand(Printer printer, PrintParameters printParameters) {
            this.mPrinter = printer;
            this.mParameters = printParameters;
        }
    }
}
