package com.wugu.doublecamera.device;

import android.serialport.SerialPort;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.UByte;

public class ScannerHelper {
    private InputStream inputStream;
    private final List<Byte> mRecBuffer;
    private OutputStream outputStream;
    private Timer recTimer;
    private SerialPort serialPort;

    private static class InstanceHolder {
        private static final ScannerHelper holder = new ScannerHelper();

        private InstanceHolder() {
        }
    }

    public static ScannerHelper getInstance() {
        return InstanceHolder.holder;
    }

    private ScannerHelper() {
        this.mRecBuffer = new ArrayList();
    }

    public void openUart(IStringListener iStringListener) {
        closeUart();
        try {
            SerialPort serialPort = new SerialPort(AppConfig.UART_SCANNER, 9600, 0);
            this.serialPort = serialPort;
            this.outputStream = serialPort.getOutputStream();
            this.inputStream = this.serialPort.getInputStream();
            startReadThread(iStringListener);
        } catch (SecurityException e) {
            LoggerUtil.m1182e("ScannerHelper", "openUart error " + e);
        }
    }

    private void startReadThread(final IStringListener iStringListener) {
        new Thread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m872xb3caa88a(iStringListener);
            }
        }).start();
    }

    void m872xb3caa88a(IStringListener iStringListener) {
        while (true) {
            InputStream inputStream = this.inputStream;
            if (inputStream == null) {
                return;
            }
            try {
                byte[] bArr = new byte[64];
                int i = inputStream.read(bArr);
                ThreadClock.sleep(50L);
                if (i > 0) {
                    for (int i2 = 0; i2 < i; i2++) {
                        this.mRecBuffer.add(Byte.valueOf(bArr[i2]));
                    }
                    startRecTimer(iStringListener);
                }
            } catch (IOException e) {
                LoggerUtil.m1182e("ScannerHelper", "read Thread error" + e);
                ThreadClock.sleep(50L);
            }
        }
    }

    private void startRecTimer(final IStringListener iStringListener) {
        if (this.recTimer != null) {
            return;
        }
        Timer timer = new Timer();
        this.recTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScannerHelper scannerHelper = ScannerHelper.this;
                scannerHelper.handleRecPacket(scannerHelper.mRecBuffer, iStringListener);
                ScannerHelper.this.mRecBuffer.clear();
                ScannerHelper.this.recTimer.cancel();
                ScannerHelper.this.recTimer = null;
            }
        }, 300L);
    }

    public void sendBytes(byte[] bArr) {
        OutputStream outputStream;
        LoggerUtil.m1181d("uart", "cashHelper sendBytes " + Arrays.toString(bArr));
        if (this.serialPort == null || bArr.length == 0 || (outputStream = this.outputStream) == null) {
            LoggerUtil.m1181d("uart", "cashHelper sendBytes fail");
            return;
        }
        try {
            outputStream.write(bArr);
            this.outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeUart() {
        try {
            OutputStream outputStream = this.outputStream;
            if (outputStream != null) {
                outputStream.close();
                this.outputStream = null;
            }
            InputStream inputStream = this.inputStream;
            if (inputStream != null) {
                inputStream.close();
                this.inputStream = null;
            }
            SerialPort serialPort = this.serialPort;
            if (serialPort != null) {
                serialPort.close();
                this.serialPort = null;
            }
            ThreadClock.sleep(500L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleRecPacket(List<Byte> list, IStringListener iStringListener) {
        if (list == null || list.size() <= 1) {
            return;
        }
        List<Byte> listSubList = list.subList(0, list.size() - 1);
        StringBuilder sb = new StringBuilder();
        Iterator<Byte> it = listSubList.iterator();
        while (it.hasNext()) {
            sb.append((char) (it.next().byteValue() & UByte.MAX_VALUE));
        }
        LoggerUtil.m1181d("uart", "scannerHelper rec " + sb.toString().trim());
        if (iStringListener != null) {
            iStringListener.setValue(sb.toString().trim());
        }
    }
}
