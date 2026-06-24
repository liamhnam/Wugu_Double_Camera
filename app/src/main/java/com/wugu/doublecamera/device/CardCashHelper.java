package com.wugu.doublecamera.device;

import android.os.Looper;
import android.text.TextUtils;
import cc.uling.usdk.USDK;
import cc.uling.usdk.board.UBoard;
import cc.uling.usdk.board.mdb.para.HCReplyPara;
import cc.uling.usdk.board.mdb.para.IPReplyPara;
import cc.uling.usdk.board.mdb.para.MPReplyPara;
import cc.uling.usdk.board.mdb.para.PCReplyPara;
import cc.uling.usdk.board.mdb.para.PMReplyPara;
import cc.uling.usdk.board.mdb.para.PayReplyPara;
import cc.uling.usdk.board.mdb.para.SVReplyPara;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.wugu.doublecamera.AppConfig;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.widget.ThreadClock;
import com.wugu.doublecamera.widget.ThreadHelper;
import java.util.Timer;
import java.util.TimerTask;

public class CardCashHelper {
    private int baseValue;
    private Timer checkPayValueTimer;
    private volatile boolean isPayFinish;
    private ICardCashListener listener;
    private float minValue;
    private int requestMoney;
    private UBoard uBoard;

    public interface ICardCashListener {
        void posPayFinish(int i);

        void recCount(int i);
    }

    private static class InstanceHolder {
        private static final CardCashHelper holder = new CardCashHelper();

        private InstanceHolder() {
        }
    }

    public static CardCashHelper getInstance() {
        return InstanceHolder.holder;
    }

    private CardCashHelper() {
        this.baseValue = 100;
        this.isPayFinish = false;
    }

    public boolean openUart(ICardCashListener iCardCashListener) {
        this.listener = iCardCashListener;
        if (this.uBoard != null) {
            return false;
        }
        UBoard uBoardCreate = USDK.getInstance().create(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        this.uBoard = uBoardCreate;
        uBoardCreate.EF_OpenDev(AppConfig.UART_CREDIT, 9600);
        HCReplyPara hCReplyPara = new HCReplyPara();
        this.uBoard.readHardwareConfig(hCReplyPara);
        String code = hCReplyPara.getCode();
        if (TextUtils.isEmpty(code)) {
            this.uBoard.EF_CloseDev();
            this.uBoard = null;
            return false;
        }
        LoggerUtil.m1181d("CardCashHelper", "moneyCode=" + code);
        if (code.equals("11 56") || code.equals("00 01")) {
            this.baseValue = 100;
        }
        SVReplyPara sVReplyPara = new SVReplyPara();
        this.uBoard.getSoftwareVersion(sVReplyPara);
        LoggerUtil.m1181d("CardCashHelper", "svReplyPara=" + sVReplyPara.getVersion());
        LoggerUtil.m1181d("CardCashHelper", "hcReplyPara=" + hCReplyPara.getVersion());
        this.uBoard.getMinPayoutAmount(new MPReplyPara());
        this.minValue = r6.getValue();
        LoggerUtil.m1181d("CardCashHelper", "minValue=" + this.minValue);
        this.uBoard.setPayChannel(new PCReplyPara(0));
        return true;
    }

    public void setPayModel(int i) {
        PCReplyPara pCReplyPara;
        if (this.uBoard == null) {
            return;
        }
        LoggerUtil.m1181d("CardCashHelper", "setPayModel=" + i);
        if (i == 99) {
            return;
        }
        if (i == 4) {
            pCReplyPara = new PCReplyPara(1);
        } else if (i == 7) {
            pCReplyPara = new PCReplyPara(2);
        } else {
            pCReplyPara = new PCReplyPara(0);
        }
        this.uBoard.setPayChannel(pCReplyPara);
        ThreadClock.sleep(1500L);
    }

    public void paymentCollection(int i) {
        if (this.uBoard == null || this.minValue == 0.0f) {
            return;
        }
        this.isPayFinish = false;
        this.requestMoney = i;
        LoggerUtil.m1181d("CardCashHelper", "paymentCollection=" + i);
        int iFloatToInt = MathUtil.floatToInt((i * this.baseValue) / (100.0f / this.minValue));
        LoggerUtil.m1181d("CardCashHelper", "requestMoney=" + iFloatToInt);
        this.uBoard.initPayment(new IPReplyPara((short) 1, iFloatToInt));
        startTimeoutTask();
    }

    public void paymentFinish(final boolean z) {
        if (this.isPayFinish || this.uBoard == null || this.minValue == 0.0f) {
            return;
        }
        this.isPayFinish = true;
        LoggerUtil.m1181d("CardCashHelper", "paymentFinish=" + z);
        Timer timer = this.checkPayValueTimer;
        if (timer != null) {
            timer.cancel();
            this.checkPayValueTimer = null;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    this.f$0.m865xf4517bea(z);
                }
            });
        } else {
            this.uBoard.notifyPayment(new PayReplyPara(z));
        }
    }

    void m865xf4517bea(boolean z) {
        this.uBoard.notifyPayment(new PayReplyPara(z));
    }

    private void startTimeoutTask() {
        Timer timer = this.checkPayValueTimer;
        if (timer != null) {
            timer.cancel();
            this.checkPayValueTimer = null;
        }
        long paymentTime = (((long) SpManager.getInstance().getPaymentTime()) * 1000) - 1000;
        this.checkPayValueTimer = new Timer();
        this.checkPayValueTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                PMReplyPara pMReplyPara = new PMReplyPara();
                CardCashHelper.this.uBoard.getPayAmount(pMReplyPara);
                int payType = pMReplyPara.getPayType();
                int iFloatToInt = MathUtil.floatToInt(((pMReplyPara.getMultiple() * 100) * CardCashHelper.this.minValue) / CardCashHelper.this.baseValue);
                LoggerUtil.m1181d("CardCashHelper", "POS pmReplyPara payType = " + payType + ", recMoney=" + iFloatToInt);
                if (payType == 4 && iFloatToInt > 0) {
                    LoggerUtil.m1181d("CardCashHelper", "POS pay finish, recMoney=" + iFloatToInt + ", requestMoney=" + CardCashHelper.this.requestMoney);
                    CardCashHelper.this.paymentFinish(true);
                    if (CardCashHelper.this.listener != null) {
                        CardCashHelper.this.listener.posPayFinish(CardCashHelper.this.requestMoney);
                    }
                    CardCashHelper.this.checkPayValueTimer.cancel();
                    return;
                }
                if (payType == 2) {
                    if (CardCashHelper.this.listener != null) {
                        CardCashHelper.this.listener.recCount(iFloatToInt);
                    }
                } else {
                    if (payType != 0 || CardCashHelper.this.listener == null) {
                        return;
                    }
                    CardCashHelper.this.listener.recCount(0);
                }
            }
        }, 1000L, 1000L);
        this.checkPayValueTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                CardCashHelper.this.paymentFinish(false);
                cancel();
            }
        }, paymentTime);
    }
}
