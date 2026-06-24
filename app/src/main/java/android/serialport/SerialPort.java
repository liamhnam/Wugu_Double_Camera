package android.serialport;

import android.util.Log;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SerialPort {
    private static final String DEFAULT_SU_PATH = "/system/bin/su";
    private static final String TAG = "SerialPort";
    private static String sSuPath = "/system/bin/su";
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;

    static {
        System.loadLibrary("serial_port");
    }

    public SerialPort(File file, int i) {
        this(file, i, 0);
    }

    public SerialPort(File file, int i, int i2) throws IOException {
        if (!file.canRead() || !file.canWrite()) {
            try {
                Process processExec = Runtime.getRuntime().exec(sSuPath);
                processExec.getOutputStream().write(("chmod 666 " + file.getAbsolutePath() + "\nexit\n").getBytes());
                if (processExec.waitFor() != 0 || !file.canRead() || !file.canWrite()) {
                    throw new SecurityException();
                }
            } catch (Exception unused) {
                throw new SecurityException();
            }
        }
        FileDescriptor fileDescriptorOpen = open(file.getAbsolutePath(), i, i2);
        this.mFd = fileDescriptorOpen;
        if (fileDescriptorOpen == null) {
            Log.e(TAG, "native open returns null");
            throw new IOException();
        }
        this.mFileInputStream = new FileInputStream(this.mFd);
        this.mFileOutputStream = new FileOutputStream(this.mFd);
    }

    public SerialPort(String str, int i) {
        this(new File(str), i, 0);
    }

    public SerialPort(String str, int i, int i2) {
        this(new File(str), i, i2);
    }

    private static native FileDescriptor open(String str, int i, int i2);

    public static void setSuPath(String str) {
        if (str == null) {
            return;
        }
        sSuPath = str;
    }

    public native void close();

    public InputStream getInputStream() {
        return this.mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return this.mFileOutputStream;
    }
}
