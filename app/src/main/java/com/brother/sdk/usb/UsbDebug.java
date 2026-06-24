package com.brother.sdk.usb;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.brother.sdk.BrotherAndroidLib;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import kotlin.UByte;
import org.apache.http.protocol.HTTP;

public class UsbDebug {
    private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";
    private static UsbDebug mInstance;
    private File mLogFile;
    private File mRootFolder;

    private UsbDebug(File file) {
        String str = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        this.mRootFolder = file;
        this.mLogFile = new File(this.mRootFolder + "/debug_log_" + str + ".txt");
    }

    private void makeLogFileVisibleInDevice() {
        Context androidContext = BrotherAndroidLib.getAndroidContext();
        if (androidContext != null) {
            final Handler handler = Looper.myLooper() != null ? new Handler() : null;
            MediaScannerConnection.scanFile(androidContext, new String[]{mInstance.mLogFile.getPath()}, new String[]{HTTP.PLAIN_TEXT_TYPE}, new MediaScannerConnection.OnScanCompletedListener() {
                @Override
                public final void onScanCompleted(String str, Uri uri) {
                    UsbDebug.lambda$makeLogFileVisibleInDevice$1(handler, str, uri);
                }
            });
        }
    }

    static void lambda$makeLogFileVisibleInDevice$1(Handler handler, final String str, Uri uri) {
        final Context androidContext = BrotherAndroidLib.getAndroidContext();
        if (androidContext == null || handler == null) {
            return;
        }
        handler.post(new Runnable() {
            @Override
            public final void run() {
                Toast.makeText(androidContext, "File [" + str + "] is registered.", 1).show();
            }
        });
    }

    public static void initialize(File file) {
        if (file == null || !file.exists()) {
            throw new InvalidParameterException();
        }
        if (mInstance == null) {
            mInstance = new UsbDebug(file);
        }
    }

    public static void terminate() {
        if (mInstance != null) {
            registerLogFileToDeviceAsMediaContent();
            mInstance = null;
        }
    }

    public static void registerLogFileToDeviceAsMediaContent() {
        UsbDebug usbDebug = mInstance;
        if (usbDebug != null) {
            usbDebug.makeLogFileVisibleInDevice();
        }
    }

    public static void debug(String str) throws Throwable {
        UsbDebug usbDebug = mInstance;
        if (usbDebug != null) {
            usbDebug.debugOut(str);
        }
    }

    public static void debugHex(byte[] bArr, int i) throws Throwable {
        UsbDebug usbDebug = mInstance;
        if (usbDebug != null) {
            usbDebug.debugOut("[Hex] Length : " + i);
            int i2 = 1;
            String str = "[Hex] : ";
            while (i2 <= i) {
                str = str + Integer.toHexString(bArr[i2 - 1] & UByte.MAX_VALUE) + " ";
                if (i2 % 10 == 0) {
                    mInstance.debugOut(str);
                    str = "[Hex] : ";
                }
                i2++;
            }
            if (i2 % 10 != 0) {
                mInstance.debugOut(str);
            }
        }
    }

    private void debugOut(String str) throws Throwable {
        String str2;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        FileOutputStream fileOutputStream3 = null;
        fileOutputStream2 = null;
        try {
            try {
                try {
                    str2 = ((("[" + new SimpleDateFormat(DATE_PATTERN).format(new Date())) + "] : ") + str) + "\r\n";
                    fileOutputStream = new FileOutputStream(this.mLogFile, true);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
            try {
                byte[] bytes = str2.getBytes();
                int length = bytes.length;
                fileOutputStream.write(bytes, 0, length);
                fileOutputStream.flush();
                fileOutputStream.close();
                fileOutputStream2 = length;
            } catch (Exception e2) {
                e = e2;
                fileOutputStream3 = fileOutputStream;
                e.printStackTrace();
                fileOutputStream2 = fileOutputStream3;
                if (fileOutputStream3 != null) {
                    fileOutputStream3.close();
                    fileOutputStream2 = fileOutputStream3;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
            fileOutputStream2 = fileOutputStream2;
        }
    }
}
