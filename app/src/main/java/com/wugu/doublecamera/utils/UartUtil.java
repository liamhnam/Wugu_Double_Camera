package com.wugu.doublecamera.utils;

import android.os.Looper;
import com.wugu.doublecamera.device.CardCashHelper;
import com.wugu.doublecamera.device.CashHelper;
import com.wugu.doublecamera.widget.ThreadHelper;

public class UartUtil {
    public static void enablePayDevice(final int i, final int i2) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    UartUtil.enableDevice(i, i2);
                }
            });
        } else {
            enableDevice(i, i2);
        }
    }

    public static void enableDevice(int i, int i2) {
        LoggerUtil.m1181d("order", "enableDevice paymentMethod " + i2);
        if (i2 == 4 || i2 == 7 || i2 == 99) {
            CardCashHelper.getInstance().setPayModel(i2);
            CardCashHelper.getInstance().paymentCollection(i);
            CashHelper.getInstance().enableDevice();
        }
    }

    public static void disablePayDevice() {
        CardCashHelper.getInstance().paymentFinish(false);
        CashHelper.getInstance().disableDevice();
    }

    public static void finishPay(boolean z) {
        CardCashHelper.getInstance().paymentFinish(true);
    }
}
