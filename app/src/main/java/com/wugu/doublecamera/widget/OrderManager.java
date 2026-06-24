package com.wugu.doublecamera.widget;

import android.os.Looper;
import android.text.TextUtils;
import com.wugu.doublecamera.App;
import com.wugu.doublecamera.base.ABaseHttpObserver;
import com.wugu.doublecamera.bean.FrameInfo;
import com.wugu.doublecamera.bean.FramePhotoInfo;
import com.wugu.doublecamera.bean.dto.BaseDto;
import com.wugu.doublecamera.bean.dto.OrderDto;
import com.wugu.doublecamera.bean.dto.RedeemCodeDto;
import com.wugu.doublecamera.bean.p023vo.CreateOrderVo;
import com.wugu.doublecamera.bean.p023vo.OrderCompensateVo;
import com.wugu.doublecamera.bean.p023vo.OrderFilesVo;
import com.wugu.doublecamera.database.DbHelper;
import com.wugu.doublecamera.database.SpManager;
import com.wugu.doublecamera.database.dbSheet.FrameEntity;
import com.wugu.doublecamera.database.dbSheet.FramePhotoEntity;
import com.wugu.doublecamera.device.CardCashHelper;
import com.wugu.doublecamera.device.CashHelper;
import com.wugu.doublecamera.device.PrinterHelper;
import com.wugu.doublecamera.listener.IIntListener;
import com.wugu.doublecamera.listener.IStringListener;
import com.wugu.doublecamera.network.HttpManager;
import com.wugu.doublecamera.network.IHttpApi;
import com.wugu.doublecamera.utils.LoggerUtil;
import com.wugu.doublecamera.utils.MathUtil;
import com.wugu.doublecamera.utils.OrderFileUtil;
import com.wugu.doublecamera.utils.StringUtil;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OrderManager {
    private static final String TAG = "OrderManager";
    public static int mBalance;
    private static final Queue<String> mFailedFileQueue = new ConcurrentLinkedQueue();
    private static String mFrameKey;
    public static String mMeiTuanDouYinCode;
    public static String mOrderFileNames;
    private static String mOrderFrameKey;
    public static String mOrderId;
    private static int mOrderPrice;
    private static int mOrderType;
    public static int mPaymentMethod;
    public static String mPrintOrderId;
    private static int mRealAmount;
    public static RedeemCodeDto mRedeemCodeDto;
    public static String replaceModelFinalPicPath;

    private static int getPaymentType(int i) {
        if (i == 1) {
            return 10;
        }
        if (i == 2) {
            return 11;
        }
        if (i == 7) {
            return 15;
        }
        if (i == 5) {
            return 17;
        }
        if (i == 6) {
            return 18;
        }
        if (i == 4 || i == 99) {
            return 16;
        }
        if (i == 3) {
            return 12;
        }
        if (i == 8) {
            return 13;
        }
        if (i == 9) {
            return 14;
        }
        return i == 12 ? 20 : 19;
    }

    private static int getPhotoOrderType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 3;
        }
        if (i == 3) {
            return 4;
        }
        if (i == 5) {
            return 2;
        }
        return i == 6 ? 5 : 0;
    }

    private OrderManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static void setOrderFrameInfo(String str, int i, int i2) {
        LoggerUtil.m1181d(TAG, "setOrderFrameInfo " + str + ", " + i + ", " + i2);
        mFrameKey = str;
        mOrderPrice = Math.max(i, 0);
        mOrderType = getPhotoOrderType(i2);
    }

    public static FrameInfo getOrderFrameInfo() {
        if (TextUtils.isEmpty(mFrameKey)) {
            return null;
        }
        FrameEntity frameByKey = DbHelper.getInstance().getFrameByKey(mFrameKey);
        ArrayList arrayList = new ArrayList();
        Iterator<FramePhotoEntity> it = DbHelper.getInstance().getFramePhotos(frameByKey.getFrameKey()).iterator();
        while (it.hasNext()) {
            arrayList.add(new FramePhotoInfo(it.next()));
        }
        return new FrameInfo(frameByKey, arrayList);
    }

    public static void createPhotoOrder(int i, final boolean z, final IStringListener iStringListener) {
        mPaymentMethod = i;
        mOrderFrameKey = mFrameKey;
        int realAmount = getRealAmount(mOrderPrice);
        mRealAmount = realAmount;
        if (!z) {
            enablePayDevice(realAmount);
        }
        LoggerUtil.m1181d(TAG, "createPhotoOrder " + mFrameKey + ", " + mOrderPrice + ", " + mRealAmount + ", pt " + i + ", ot " + mOrderType + ", " + z);
        if (TextUtils.isEmpty(mFrameKey)) {
            return;
        }
        RedeemCodeDto redeemCodeDto = mRedeemCodeDto;
        String code = (redeemCodeDto == null || redeemCodeDto.getType() == 8) ? null : mRedeemCodeDto.getCode();
        LoggerUtil.m1181d(TAG, "createOrder redeemCode=" + code);
        HttpManager.getInstance().mHttpApi.createOrder(new CreateOrderVo(code, null, mOrderPrice, mRealAmount, getPaymentType(mPaymentMethod), mOrderType, new CreateOrderVo.OrderDetail(mFrameKey, 1, mRealAmount))).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderDto>>() {
            @Override
            public void onNext(BaseDto<OrderDto> baseDto) {
                if (baseDto == null || baseDto.data == null) {
                    return;
                }
                OrderManager.mOrderId = baseDto.data.orderId;
                OrderManager.mPrintOrderId = null;
                LoggerUtil.m1181d(OrderManager.TAG, "createOrder mOrderId=" + OrderManager.mOrderId + ", paymentUrl=" + baseDto.data.paymentUrl);
                if (z) {
                    OrderManager.orderPayFinish();
                }
                IStringListener iStringListener2 = iStringListener;
                if (iStringListener2 != null) {
                    iStringListener2.setValue(baseDto.data.paymentUrl);
                }
            }
        });
    }

    public static void createPrintOrder(int i, int i2, final boolean z, final IStringListener iStringListener) {
        LoggerUtil.m1181d(TAG, "createPrintOrder addCount=" + i + ", " + i2 + ", isPayFinish=" + z + ", mPaymentMethod=" + mPaymentMethod + ", frameKey =" + mFrameKey);
        if (i <= 0) {
            return;
        }
        int iMax = Math.max(0, i * i2);
        mOrderPrice = iMax;
        mRealAmount = iMax;
        String paymentMethod = SpManager.getInstance().getPaymentMethod();
        int i3 = mPaymentMethod;
        if (i3 == 3 || i3 == 11 || i3 == 8 || i3 == 9) {
            if (paymentMethod.contains(String.valueOf(1))) {
                mPaymentMethod = 1;
            } else if (paymentMethod.contains(String.valueOf(2))) {
                mPaymentMethod = 2;
            } else {
                mPaymentMethod = 99;
            }
        }
        if (!z) {
            enablePayDevice(mOrderPrice);
        }
        LoggerUtil.m1181d(TAG, "createPrintOrder payPrice " + mOrderPrice + " " + mPaymentMethod);
        IHttpApi iHttpApi = HttpManager.getInstance().mHttpApi;
        String str = mOrderId;
        int i4 = mOrderPrice;
        iHttpApi.createOrder(new CreateOrderVo(null, str, i4, i4, getPaymentType(mPaymentMethod), 1, new CreateOrderVo.OrderDetail(mFrameKey, i, i2))).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderDto>>() {
            @Override
            public void onNext(BaseDto<OrderDto> baseDto) {
                if (baseDto == null || baseDto.data == null) {
                    return;
                }
                OrderManager.mPrintOrderId = baseDto.data.orderId;
                LoggerUtil.m1181d(OrderManager.TAG, "createPrintOrder id " + OrderManager.mPrintOrderId + ", paymentUrl=" + baseDto.data.paymentUrl);
                IStringListener iStringListener2 = iStringListener;
                if (iStringListener2 != null) {
                    iStringListener2.setValue(baseDto.data.paymentUrl);
                }
                if (z) {
                    OrderManager.orderPayFinish();
                }
            }
        });
    }

    public static boolean isPhotoWithoutPay() {
        LoggerUtil.m1181d(TAG, "isPhotoWithoutPay " + mOrderPrice + ", " + mOrderType + ", " + mBalance);
        if (mOrderPrice <= 0) {
            createPhotoOrder(11, true, null);
            return true;
        }
        if (!checkBalance()) {
            return false;
        }
        createPhotoOrder(4, true, null);
        return true;
    }

    public static void orderPayFinish() {
        LoggerUtil.m1181d(TAG, "orderPayFinish  " + mOrderId + ", " + mPrintOrderId + ", " + mRealAmount + ", " + mPaymentMethod);
        if (!isOnlinePayment() || mRealAmount <= 0) {
            String str = mOrderId;
            if (!TextUtils.isEmpty(mPrintOrderId)) {
                str = mPrintOrderId;
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            HttpManager.getInstance().mHttpApi.cashPayOrder(str, mRealAmount / 100.0f, getPaymentType(mPaymentMethod)).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            });
        }
    }

    public static void finishOrder(final int i, int i2) {
        LoggerUtil.m1181d(TAG, "finishOrder " + mOrderId + ", " + mPrintOrderId + ", " + i + ", " + i2);
        mRedeemCodeDto = null;
        if (!TextUtils.isEmpty(mPrintOrderId)) {
            HttpManager.getInstance().mHttpApi.finishOrder(mPrintOrderId).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderDto>>() {
                @Override
                public void onNext(BaseDto<OrderDto> baseDto) {
                }
            });
        }
        final String orderFileUrl = OrderFileUtil.getOrderFileUrl();
        LoggerUtil.m1181d(TAG, "orderFileUrl " + orderFileUrl);
        HttpManager.getInstance().mHttpApi.finishOrder(mOrderId).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<OrderDto>>() {
            @Override
            public void onNext(BaseDto<OrderDto> baseDto) {
                if (baseDto != null && baseDto.errCode == 300011) {
                    OrderManager.orderCompensate(OrderManager.mOrderId, OrderManager.mFrameKey, orderFileUrl);
                    LoggerUtil.m1182e(OrderManager.TAG, "finishOrder error, id=" + baseDto.errMsg);
                } else {
                    LoggerUtil.m1181d(OrderManager.TAG, "finishOrder ok");
                }
            }
        });
        if (i >= 0) {
            SpManager.getInstance().setRemainPrintCount(i2);
            HttpManager.getInstance().mHttpApi.updatePrinterPaper(i, i2).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
                @Override
                public void onNext(BaseDto<Object> baseDto) {
                    App.mCurrentPrintSheets = i;
                }
            });
        }
        if (TextUtils.isEmpty(orderFileUrl)) {
            return;
        }
        OrderFilesVo orderFilesVo = new OrderFilesVo();
        orderFilesVo.orderId = mOrderId;
        orderFilesVo.url = orderFileUrl;
        if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
            ThreadClock.sleep(1000L);
        }
        OrderFileUtil.updateOrderFileUrl(orderFilesVo, true);
    }

    public static void orderCompensate(String str, String str2, String str3) {
        HttpManager.getInstance().mHttpApi.orderCompensate(new OrderCompensateVo(str, str2, str3)).subscribeOn(Schedulers.m1287io()).subscribe(new ABaseHttpObserver<BaseDto<Object>>() {
            @Override
            public void onNext(BaseDto<Object> baseDto) {
                LoggerUtil.m1181d(OrderManager.TAG, "orderCompensate next");
                if (baseDto != null) {
                    LoggerUtil.m1181d(OrderManager.TAG, "orderCompensate errCode " + baseDto.errCode + ", " + baseDto.errMsg);
                }
            }

            @Override
            public void onError(Throwable th) {
                LoggerUtil.m1182e(OrderManager.TAG, "orderCompensate err " + th.getMessage());
            }
        });
    }

    public static void cancelPrintOrder() {
        disablePayDevice();
        mPrintOrderId = null;
    }

    public static void clearOrderInfo() {
        LoggerUtil.m1181d(TAG, "clearOrderInfo");
        mOrderId = null;
        mPrintOrderId = null;
        mFrameKey = null;
        mPaymentMethod = 11;
        mRedeemCodeDto = null;
        mOrderFileNames = "";
        PrinterHelper.getInstance().setPrinterColorParam(null);
    }

    public static boolean isOnlinePayment() {
        int i = mPaymentMethod;
        return i == 1 || i == 2;
    }

    public static boolean addBalance(int i) {
        LoggerUtil.m1181d(TAG, "addBalance " + i);
        mBalance += i;
        return checkBalance();
    }

    public static void addBalanceOnly(int i) {
        LoggerUtil.m1181d(TAG, "addBalance " + i);
        mBalance += i;
    }

    public static boolean checkBalance() {
        LoggerUtil.m1181d(TAG, "checkBalance " + mOrderPrice + " " + mBalance);
        mRealAmount = getRealAmount(mOrderPrice);
        LoggerUtil.m1181d(TAG, "checkBalance realAmount " + mRealAmount);
        int i = mBalance;
        int i2 = mRealAmount;
        if (i < i2) {
            return false;
        }
        mBalance = i - i2;
        disablePayDevice();
        return true;
    }

    public static boolean checkAddPrintBalance(int i, int i2) {
        if (i2 <= 0) {
            return true;
        }
        LoggerUtil.m1181d(TAG, "checkAddPrintBalance " + i + " " + i2 + " " + mBalance);
        int i3 = i * i2;
        int i4 = mBalance;
        if (i4 < i3) {
            return false;
        }
        mBalance = i4 - i3;
        createPrintOrder(i2, i, true, null);
        return true;
    }

    public static String getShowPriceStr(int i) {
        mOrderPrice = i;
        LoggerUtil.m1181d(TAG, "getShowPrice price " + i + ", " + mPaymentMethod + ", " + mBalance);
        if (!isOnlinePayment()) {
            RedeemCodeDto redeemCodeDto = mRedeemCodeDto;
            if (redeemCodeDto != null && redeemCodeDto.getType() == 1) {
                LoggerUtil.m1181d(TAG, "getShowPrice discount " + mRedeemCodeDto.getAmount());
                i = MathUtil.floatToInt((i * mRedeemCodeDto.getAmount()) / 100.0f);
            }
        } else {
            RedeemCodeDto redeemCodeDto2 = mRedeemCodeDto;
            if (redeemCodeDto2 != null) {
                if (redeemCodeDto2.getType() == 1) {
                    LoggerUtil.m1181d(TAG, "getShowPrice discount " + mRedeemCodeDto.getAmount());
                    i = MathUtil.floatToInt((i * mRedeemCodeDto.getAmount()) / 100.0f);
                } else if (mRedeemCodeDto.getType() == 2) {
                    LoggerUtil.m1181d(TAG, "getShowPrice cash " + mRedeemCodeDto.getAmount());
                    i -= MathUtil.getPriceValue(mRedeemCodeDto.getAmount());
                }
            }
            i = Math.max(i - mBalance, 0);
        }
        return StringUtil.getPriceStr(i);
    }

    public static String getOrderRealPriceStr() {
        return getShowPriceStr(mOrderPrice);
    }

    public static int getFramePrice() {
        return mOrderPrice;
    }

    public static int getTotalBalance() {
        RedeemCodeDto redeemCodeDto = mRedeemCodeDto;
        if (redeemCodeDto != null && redeemCodeDto.getType() == 2) {
            return mBalance + MathUtil.getPriceValue(mRedeemCodeDto.getAmount());
        }
        return mBalance;
    }

    private static int getRealAmount(int i) {
        RedeemCodeDto redeemCodeDto = mRedeemCodeDto;
        if (redeemCodeDto != null) {
            if (redeemCodeDto.getType() == 1) {
                i = MathUtil.floatToInt((i * mRedeemCodeDto.getAmount()) / 100.0f);
            } else if (mRedeemCodeDto.getType() == 2) {
                i -= MathUtil.getPriceValue(mRedeemCodeDto.getAmount());
            } else if (mRedeemCodeDto.getType() == 6 || mRedeemCodeDto.getType() == 7 || mRedeemCodeDto.getType() == 9 || mRedeemCodeDto.getType() == 8) {
                i = 0;
            }
        }
        return Math.max(i, 0);
    }

    private static void enablePayDevice(final int i) {
        if (i <= 0) {
            return;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            ThreadHelper.getInstance().createThread(new Runnable() {
                @Override
                public final void run() {
                    OrderManager.enableDevice(i);
                }
            });
        } else {
            enableDevice(i);
        }
    }

    public static void enableDevice(int i) {
        LoggerUtil.m1181d("order", "enableDevice paymentMethod " + mPaymentMethod);
        int i2 = mPaymentMethod;
        if (i2 == 4 || i2 == 7 || i2 == 99) {
            CardCashHelper.getInstance().setPayModel(mPaymentMethod);
            CardCashHelper.getInstance().paymentCollection(i);
            CashHelper.getInstance().enableDevice();
        }
    }

    public static void disablePayDevice() {
        CardCashHelper.getInstance().paymentFinish(false);
        CashHelper.getInstance().disableDevice();
    }

    public static boolean isPhotoNotOnPayment() {
        if (TextUtils.isEmpty(mOrderFrameKey)) {
            return false;
        }
        mFrameKey = mOrderFrameKey;
        return true;
    }

    public static String getFrameKey() {
        return mFrameKey;
    }

    public static String getOrderFrameKey() {
        return mOrderFrameKey;
    }

    public static void addFailedFile(String str) {
        if (str == null || str.trim().isEmpty()) {
            return;
        }
        LoggerUtil.m1181d(TAG, "addFailedFile " + str);
        Queue<String> queue = mFailedFileQueue;
        if (queue.contains(str.trim())) {
            return;
        }
        queue.offer(str.trim());
    }

    public static void addFailedFiles(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            addFailedFile(it.next());
        }
    }

    public static void uploadFailedFiles() {
        final String strPoll = mFailedFileQueue.poll();
        if (strPoll == null) {
            return;
        }
        try {
            LoggerUtil.m1181d(TAG, "retryUpload " + strPoll);
            ArrayList arrayList = new ArrayList();
            arrayList.add(strPoll);
            OrderFileUtil.uploadOrderFilePaths(arrayList, new IIntListener() {
                @Override
                public final void setValue(int i) {
                    OrderManager.lambda$uploadFailedFiles$1(strPoll, i);
                }
            });
        } catch (Exception e) {
            LoggerUtil.m1181d(TAG, "retryUpload fail " + strPoll + ", " + e.getMessage());
            addFailedFile(strPoll);
        }
    }

    static void lambda$uploadFailedFiles$1(String str, int i) {
        if (i == 1) {
            if (App.mIsIdle) {
                uploadFailedFiles();
            }
        } else {
            LoggerUtil.m1181d(TAG, "retryUpload fail 2 " + str);
            addFailedFile(str);
        }
    }

    public static boolean isExistFailedFile() {
        return mFailedFileQueue.size() > 0;
    }
}
