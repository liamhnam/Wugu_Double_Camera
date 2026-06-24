package com.brother.sdk.print;

import android.content.Context;
import com.brother.sdk.common.Callback;
import com.brother.sdk.common.IConnector;
import com.brother.sdk.common.IReadStream;
import com.brother.sdk.common.IUnknown;
import com.brother.sdk.common.Job;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.device.printer.PrintCapabilities;
import com.brother.sdk.common.socket.ISocketConnector;
import com.brother.sdk.common.socket.print.PrintState;
import com.brother.sdk.common.socket.print.lpr.LprClient;
import com.brother.sdk.common.socket.print.port9100.Port9100Client;
import com.brother.sdk.print.pdl.PDLStream;
import com.brother.sdk.print.pdl.PageDescriptionLanguageBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PrintJob extends Job {
    private static final String PRINT_JOB_CACHE = "/printjob";
    protected static final int PROGRESS_PDL_CREATE_END = 10;
    protected static final int PROGRESS_SEND_JOB_END = 100;
    private static final String SERVICE_NAME = "Android";
    private static int mJobID = 1;
    private final Context mContext;
    private final List<File> mData;
    private PrintParameters mParameters;
    private final Job.ProgressInterpolator mProgressInterpolator;
    private ISocketConnector mSocketConnector = null;
    private LprClient mClient = null;
    private Port9100Client mPort9100Client = null;
    private Device mDevice = null;
    private PageDescriptionLanguageBuilder mPDLBuilder = null;
    private PDLStream mPDLStream = null;
    private boolean mCancel = false;
    private PrintState mState = PrintState.ErrorPrint;

    public PrintJob(PrintParameters printParameters, Context context, List<File> list, Callback callback) {
        this.mParameters = null;
        if (printParameters == null || list == null || callback == null || context == null) {
            throw new IllegalArgumentException();
        }
        this.mParameters = printParameters;
        this.mContext = context;
        this.mData = list;
        this.mProgressInterpolator = new Job.ProgressInterpolator(callback);
    }

    public PrintState getStatus() {
        return this.mState;
    }

    protected void updateStatus(PrintState printState) {
        this.mState = printState;
    }

    @Override
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.brother.sdk.common.Job.JobState commit() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1734
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brother.sdk.print.PrintJob.commit():com.brother.sdk.common.Job$JobState");
    }

    public static String exceptionToString(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public void writeLog(String str) {
        try {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            String str2 = simpleDateFormat.format(date);
            String str3 = str2 + ":  " + str + "\n";
            File file = new File(this.mContext.getExternalFilesDir(null), simpleDateFormat2.format(date) + "log.txt");
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

    @Override
    public boolean bind(IConnector iConnector) {
        Device device;
        if (iConnector != null) {
            this.mDevice = iConnector.getDevice();
            IUnknown iUnknownQueryInterface = iConnector.queryInterface(ISocketConnector.f506ID);
            if (iUnknownQueryInterface != null && (device = this.mDevice) != null && device.printer != null) {
                this.mSocketConnector = (ISocketConnector) iUnknownQueryInterface;
                return true;
            }
        }
        return false;
    }

    @Override
    public void cancel() {
        if (this.mCancel) {
            return;
        }
        this.mCancel = true;
        PageDescriptionLanguageBuilder pageDescriptionLanguageBuilder = this.mPDLBuilder;
        if (pageDescriptionLanguageBuilder != null) {
            pageDescriptionLanguageBuilder.cancel();
        }
        PDLStream pDLStream = this.mPDLStream;
        if (pDLStream != null) {
            pDLStream.cancel();
        }
    }

    protected File createCacheFolder(File file) {
        File file2 = new File(file + PRINT_JOB_CACHE);
        if (!file2.exists()) {
            file2.mkdirs();
        } else {
            clearFolder(file2);
        }
        return file2;
    }

    protected void clearFolder(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                file2.delete();
            }
        }
    }

    protected boolean verify(PrintCapabilities printCapabilities, PrintParameters printParameters) {
        try {
            if (printCapabilities == null || printParameters == null) {
                throw new Exception();
            }
            if (printCapabilities.directPrintSupport && printParameters.directPrinting) {
                return true;
            }
            if (!printCapabilities.paperSizes.contains(printParameters.paperSize)) {
                throw new Exception();
            }
            if (!printCapabilities.mediaTypes.contains(printParameters.mediaType)) {
                throw new Exception();
            }
            if (!printCapabilities.margins.contains(printParameters.margin)) {
                throw new Exception();
            }
            if (!printCapabilities.duplices.contains(printParameters.duplex)) {
                throw new Exception();
            }
            if (!printCapabilities.colorTypes.contains(printParameters.color)) {
                throw new Exception();
            }
            if (!printCapabilities.resolutions.contains(printParameters.resolution)) {
                throw new Exception();
            }
            if (printCapabilities.maxCopyCount >= printParameters.copyCount) {
                return true;
            }
            throw new Exception();
        } catch (Exception unused) {
            return false;
        }
    }

    private static int countUpId() {
        int i = mJobID;
        mJobID = i + 1;
        return i;
    }

    private void savePDL(Context context, IReadStream iReadStream) throws IOException {
        File externalFilesDir = context.getExternalFilesDir("savePDL");
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(File.createTempFile("PDL", ".data", externalFilesDir));
        byte[] bArr = new byte[8192];
        while (true) {
            int i = iReadStream.read(bArr, 0, 8192);
            if (i > 0) {
                fileOutputStream.write(bArr, 0, i);
            } else {
                fileOutputStream.flush();
                fileOutputStream.close();
                iReadStream.reset();
                return;
            }
        }
    }
}
