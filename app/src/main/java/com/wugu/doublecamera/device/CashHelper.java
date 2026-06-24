package com.wugu.doublecamera.device;

import android.serialport.SerialPort;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.UByte;

public class CashHelper {
    private final int[] cashValues;
    private InputStream inputStream;
    private final List<Byte> mRecBuffer;
    private OutputStream outputStream;
    private Timer recTimer;
    private SerialPort serialPort;

    public interface ICashListener {
        void recCount(int i);
    }

    private static class InstanceHolder {
        private static final CashHelper holder = new CashHelper();

        private InstanceHolder() {
        }
    }

    public static CashHelper getInstance() {
        return InstanceHolder.holder;
    }

    private CashHelper() {
        this.mRecBuffer = new ArrayList();
        this.cashValues = new int[9];
    }

    public void openUart(ICashListener iCashListener) {
        closeUart();
        SerialPort serialPort = new SerialPort(AppConfig.UART_CASH, 9600, 0);
        this.serialPort = serialPort;
        this.outputStream = serialPort.getOutputStream();
        this.inputStream = this.serialPort.getInputStream();
        updateBaseValue(SpManager.getInstance().getCashBaseValue());
        startReadThread(iCashListener);
    }

    public void updateBaseValue(String str) {
        if (this.serialPort == null) {
            return;
        }
        LoggerUtil.m1181d("uart", "cashHelper updateBaseValue " + str);
        String[] strArrSplit = str.split("-");
        int i = 0;
        while (true) {
            int[] iArr = this.cashValues;
            if (i >= iArr.length) {
                return;
            }
            if (i < strArrSplit.length) {
                iArr[i] = StringUtil.strToInt(strArrSplit[i], 0);
            } else {
                iArr[i] = 0;
            }
            i++;
        }
    }

    private void startReadThread(final ICashListener iCashListener) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1595lambda$startReadThread$0$comwugudoublecameradeviceCashHelper(iCashListener);
            }
        });
    }

    void m1595lambda$startReadThread$0$comwugudoublecameradeviceCashHelper(ICashListener iCashListener) {
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
                    startRecTimer(iCashListener);
                }
            } catch (IOException e) {
                LoggerUtil.m1182e("CashnHelper", "read Thread error" + e);
                ThreadClock.sleep(50L);
            }
        }
    }

    private void startRecTimer(final ICashListener iCashListener) {
        if (this.recTimer != null) {
            return;
        }
        Timer timer = new Timer();
        this.recTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CashHelper cashHelper = CashHelper.this;
                cashHelper.handleRecPacket(cashHelper.mRecBuffer, iCashListener);
                CashHelper.this.mRecBuffer.clear();
                CashHelper.this.recTimer.cancel();
                CashHelper.this.recTimer = null;
            }
        }, 300L);
    }

    public void sendBytes(byte[] bArr) {
        OutputStream outputStream;
        LoggerUtil.m1181d("uart", "cashHelper sendBytes " + StringUtil.bytesToHex(bArr));
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

    public void enableDevice() {
        sendBytes(new byte[]{62});
    }

    public void disableDevice() {
        sendBytes(new byte[]{94});
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

    public void handleRecPacket(List<Byte> list, ICashListener iCashListener) {
        if (list == null || list.size() == 0) {
            return;
        }
        int size = list.size();
        StringBuilder sb = new StringBuilder();
        Iterator<Byte> it = list.iterator();
        while (it.hasNext()) {
            sb.append(String.format("0x%02X ", it.next()));
        }
        LoggerUtil.m1181d("uart", "cashHelper rec " + sb.toString().trim());
        if (size == 2 && list.get(0).byteValue() == -128 && list.get(1).byteValue() == -113) {
            sendBytes(new byte[]{2});
            ThreadClock.sleep(1000L);
            disableDevice();
        } else {
            if (size >= 2 && list.get(0).byteValue() == -127) {
                sendBytes(new byte[]{2});
                int cashValue = getCashValue(list.get(1).byteValue());
                LoggerUtil.m1181d("uart", "cash rec " + Integer.toHexString(list.get(1).byteValue() & UByte.MAX_VALUE) + " value " + cashValue);
                if (iCashListener != null) {
                    iCashListener.recCount(cashValue * 100);
                    return;
                }
                return;
            }
            if (list.get(0).byteValue() == 39 || list.get(0).byteValue() == 32) {
                sendBytes(new byte[]{SnmpConstants.CONS_SEQ});
            }
        }
    }

    private int getCashValue(byte b) {
        switch (b) {
            case 64:
                return this.cashValues[0];
            case 65:
                return this.cashValues[1];
            case 66:
                return this.cashValues[2];
            case 67:
                return this.cashValues[3];
            case 68:
                return this.cashValues[4];
            case 69:
                return this.cashValues[5];
            case 70:
                return this.cashValues[6];
            case 71:
                return this.cashValues[7];
            case 72:
                return this.cashValues[8];
            default:
                return 0;
        }
    }
}
