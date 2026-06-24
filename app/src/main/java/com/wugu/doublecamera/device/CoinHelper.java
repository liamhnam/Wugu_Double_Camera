package com.wugu.doublecamera.device;

import android.serialport.SerialPort;
import com.brother.sdk.common.socket.devicemanagement.snmp.westhawk.SnmpConstants;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.ByteUtil;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.utils.StringUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.UByte;

public class CoinHelper {
    private final byte PACK_END;
    private final byte PACK_HEAD;
    private float baseValue;
    private InputStream inputStream;
    private final List<Byte> mRecBuffer;
    private OutputStream outputStream;
    private Timer recTimer;
    private SerialPort serialPort;

    public interface ICoinListener {
        void recCount(int i);
    }

    private static class InstanceHolder {
        private static final CoinHelper holder = new CoinHelper();

        private InstanceHolder() {
        }
    }

    public static CoinHelper getInstance() {
        return InstanceHolder.holder;
    }

    private CoinHelper() {
        this.PACK_HEAD = (byte) 90;
        this.PACK_END = SnmpConstants.GETBULK_REQ_MSG;
        this.mRecBuffer = new ArrayList();
        this.baseValue = 1.0f;
    }

    public void openUart(ICoinListener iCoinListener) {
        closeUart();
        SerialPort serialPort = new SerialPort(AppConfig.UART_COIN, 19200, 0);
        this.serialPort = serialPort;
        this.outputStream = serialPort.getOutputStream();
        this.inputStream = this.serialPort.getInputStream();
        updateBaseValue(SpManager.getInstance().getCoinBaseValue());
        startReadThread(iCoinListener);
    }

    public void updateBaseValue(float f) {
        if (this.serialPort == null) {
            return;
        }
        this.baseValue = f;
        LoggerUtil.m1181d("CoinHelper", "baseValue " + this.baseValue);
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

    public void sendBytes(byte[] bArr) {
        LoggerUtil.m1181d("uart", "coinHelper sendBytes " + Arrays.toString(bArr));
        if (bArr.length == 0 || this.outputStream == null) {
            return;
        }
        byte length = (byte) bArr.length;
        byte[] bArr2 = new byte[bArr.length + 4];
        bArr2[0] = 90;
        bArr2[1] = length;
        System.arraycopy(bArr, 0, bArr2, 2, length);
        bArr2[bArr.length + 2] = ByteUtil.check_sum_1_byte(bArr2, 1, length + 2);
        bArr2[bArr.length + 3] = SnmpConstants.GETBULK_REQ_MSG;
        try {
            this.outputStream.write(bArr2);
            this.outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startReadThread(final ICoinListener iCoinListener) {
        ThreadHelper.getInstance().createThread(new Runnable() {
            @Override
            public final void run() {
                this.f$0.m1596lambda$startReadThread$0$comwugudoublecameradeviceCoinHelper(iCoinListener);
            }
        });
    }

    void m1596lambda$startReadThread$0$comwugudoublecameradeviceCoinHelper(ICoinListener iCoinListener) {
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
                    startRecTimer(iCoinListener);
                }
            } catch (IOException e) {
                LoggerUtil.m1182e("CashnHelper", "read Thread error" + e);
                ThreadClock.sleep(50L);
            }
        }
    }

    private void startRecTimer(final ICoinListener iCoinListener) {
        if (this.recTimer != null) {
            return;
        }
        Timer timer = new Timer();
        this.recTimer = timer;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CoinHelper coinHelper = CoinHelper.this;
                coinHelper.handleRecPacket(coinHelper.mRecBuffer, iCoinListener);
                CoinHelper.this.mRecBuffer.clear();
                CoinHelper.this.recTimer.cancel();
                CoinHelper.this.recTimer = null;
            }
        }, 300L);
    }

    public void handleRecPacket(List<Byte> list, ICoinListener iCoinListener) {
        int size = list.size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < list.size(); i++) {
            bArr[i] = list.get(i).byteValue();
        }
        LoggerUtil.m1181d("uart", "coin rec " + StringUtil.bytesToHex(bArr));
        int i2 = size - 1;
        byte bCheck_sum_1_byte = ByteUtil.check_sum_1_byte(bArr, 1, Math.min(bArr[1] + 1, i2));
        if (bArr[0] == 90 && bArr[i2] == -91 && bCheck_sum_1_byte == bArr[size - 2]) {
            int i3 = bArr[2] & UByte.MAX_VALUE;
            LoggerUtil.m1181d("CoinHelper", "pulse " + i3);
            if (iCoinListener != null) {
                iCoinListener.recCount(MathUtil.getPriceValue(i3 * this.baseValue));
            }
        }
    }
}
