package com.faceunity.core.controller.prop;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.callback.OnPropCallBack;
import com.faceunity.core.controller.prop.ThreadQueuePool;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u0001:\u0001>B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0004J\u0016\u00103\u001a\u00020/2\f\u00104\u001a\b\u0012\u0004\u0012\u00020/05H\u0004J \u00106\u001a\u00020/2\u0006\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0001H\u0004J\u001f\u0010:\u001a\u00020/2\u0010\b\u0002\u00104\u001a\n\u0012\u0004\u0012\u00020/\u0018\u000105H\u0010¢\u0006\u0002\b;J\b\u0010<\u001a\u00020/H\u0002J\b\u0010=\u001a\u00020/H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR&\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001e0\u001dX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"Rn\u0010#\u001aV\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010%j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`&0$j*\u0012\u0004\u0012\u00020\n\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010%j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`&`'X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, m1293d2 = {"Lcom/faceunity/core/controller/prop/BasePropController;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "controllerHandler", "Lcom/faceunity/core/controller/prop/BasePropController$ControllerHandler;", "controllerThreadId", "", "mBundleManager", "Lcom/faceunity/core/bundle/BundleManager;", "getMBundleManager", "()Lcom/faceunity/core/bundle/BundleManager;", "mBundleManager$delegate", "Lkotlin/Lazy;", "mFURenderBridge", "Lcom/faceunity/core/support/FURenderBridge;", "getMFURenderBridge", "()Lcom/faceunity/core/support/FURenderBridge;", "mFURenderBridge$delegate", "mOnPropCallBack", "Lcom/faceunity/core/callback/OnPropCallBack;", "getMOnPropCallBack", "()Lcom/faceunity/core/callback/OnPropCallBack;", "setMOnPropCallBack", "(Lcom/faceunity/core/callback/OnPropCallBack;)V", "propIdMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "getPropIdMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "setPropIdMap", "(Ljava/util/concurrent/ConcurrentHashMap;)V", "propTypeMap", "Ljava/util/HashMap;", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "Lkotlin/collections/HashMap;", "getPropTypeMap", "()Ljava/util/HashMap;", "setPropTypeMap", "(Ljava/util/HashMap;)V", "threadQueuePool", "Lcom/faceunity/core/controller/prop/ThreadQueuePool;", "applyThreadQueue", "", "queue", "Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueItem;", "doBackgroundAction", "doGLThreadAction", "unit", "Lkotlin/Function0;", "itemSetParam", "handle", "key", "value", "release", "release$fu_core_all_featureRelease", "releaseThread", "startBackgroundThread", "ControllerHandler", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class BasePropController {
    private ControllerHandler controllerHandler;
    private OnPropCallBack mOnPropCallBack;
    private final String TAG = "KIT_" + getClass().getSimpleName();

    private final Lazy mFURenderBridge = LazyKt.lazy(new Function0<FURenderBridge>() {
        @Override
        public final FURenderBridge invoke() {
            return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });

    private final Lazy mBundleManager = LazyKt.lazy(new Function0<BundleManager>() {
        @Override
        public final BundleManager invoke() {
            return BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });
    private final ThreadQueuePool threadQueuePool = new ThreadQueuePool();
    private ConcurrentHashMap<Long, Integer> propIdMap = new ConcurrentHashMap<>(16);
    private HashMap<Long, LinkedHashMap<String, Object>> propTypeMap = new HashMap<>(16);
    private long controllerThreadId = -1;

    public void applyThreadQueue(ThreadQueuePool.QueueItem queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
    }

    protected final BundleManager getMBundleManager() {
        return (BundleManager) this.mBundleManager.getValue();
    }

    protected final FURenderBridge getMFURenderBridge() {
        return (FURenderBridge) this.mFURenderBridge.getValue();
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final ConcurrentHashMap<Long, Integer> getPropIdMap() {
        return this.propIdMap;
    }

    protected final void setPropIdMap(ConcurrentHashMap<Long, Integer> concurrentHashMap) {
        Intrinsics.checkParameterIsNotNull(concurrentHashMap, "<set-?>");
        this.propIdMap = concurrentHashMap;
    }

    protected final HashMap<Long, LinkedHashMap<String, Object>> getPropTypeMap() {
        return this.propTypeMap;
    }

    protected final void setPropTypeMap(HashMap<Long, LinkedHashMap<String, Object>> map) {
        Intrinsics.checkParameterIsNotNull(map, "<set-?>");
        this.propTypeMap = map;
    }

    public final OnPropCallBack getMOnPropCallBack() {
        return this.mOnPropCallBack;
    }

    public final void setMOnPropCallBack(OnPropCallBack onPropCallBack) {
        this.mOnPropCallBack = onPropCallBack;
    }

    protected final void itemSetParam(int handle, String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        FULogger.m295i(this.TAG, "setItemParam  key:" + key + "   value:" + value);
        if (handle <= 0) {
            FULogger.m294e(this.TAG, "setItemParam failed handle:" + handle + "  ");
            return;
        }
        if (value instanceof Double) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(handle, key, ((Number) value).doubleValue());
            return;
        }
        if (value instanceof String) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(handle, key, (String) value);
            return;
        }
        if (value instanceof double[]) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(handle, key, (double[]) value);
        } else if (value instanceof Integer) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(handle, key, ((Number) value).intValue());
        } else if (value instanceof Float) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(handle, key, ((Number) value).floatValue());
        }
    }

    public static void release$fu_core_all_featureRelease$default(BasePropController basePropController, Function0 function0, int i, Object obj) throws InterruptedException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: release");
        }
        if ((i & 1) != 0) {
            function0 = null;
        }
        basePropController.release$fu_core_all_featureRelease(function0);
    }

    public void release$fu_core_all_featureRelease(Function0<Unit> unit) throws InterruptedException {
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            controllerHandler.post(new Runnable() {
                @Override
                public final void run() {
                    Iterator<Map.Entry<Long, Integer>> it = this.getPropIdMap().entrySet().iterator();
                    while (it.hasNext()) {
                        this.getMBundleManager().destroyControllerBundle(it.next().getValue().intValue());
                    }
                    this.getPropIdMap().clear();
                    this.getPropTypeMap().clear();
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        }
        releaseThread();
    }

    protected final void doBackgroundAction(ThreadQueuePool.QueueItem queue) {
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        if (this.controllerHandler == null) {
            startBackgroundThread();
        }
        this.threadQueuePool.push(queue);
        Message message = new Message();
        message.what = 1;
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            controllerHandler.removeMessages(1);
        }
        ControllerHandler controllerHandler2 = this.controllerHandler;
        if (controllerHandler2 != null) {
            controllerHandler2.sendMessage(message);
        }
    }

    protected final void doGLThreadAction(Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        getMFURenderBridge().doGLThreadAction$fu_core_all_featureRelease(unit);
    }

    private final void startBackgroundThread() {
        HandlerThread handlerThread = new HandlerThread("KIT_" + getClass().getSimpleName());
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "backgroundThread.looper");
        ControllerHandler controllerHandler = new ControllerHandler(looper, this);
        this.controllerHandler = controllerHandler;
        Looper looper2 = controllerHandler.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper2, "controllerHandler!!.looper");
        Thread thread = looper2.getThread();
        Intrinsics.checkExpressionValueIsNotNull(thread, "controllerHandler!!.looper.thread");
        this.controllerThreadId = thread.getId();
    }

    private final void releaseThread() {
        Looper looper;
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            controllerHandler.removeCallbacksAndMessages(null);
        }
        ControllerHandler controllerHandler2 = this.controllerHandler;
        if (controllerHandler2 != null && (looper = controllerHandler2.getLooper()) != null) {
            looper.quitSafely();
        }
        this.controllerHandler = null;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m1293d2 = {"Lcom/faceunity/core/controller/prop/BasePropController$ControllerHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "propController", "Lcom/faceunity/core/controller/prop/BasePropController;", "(Landroid/os/Looper;Lcom/faceunity/core/controller/prop/BasePropController;)V", "getPropController", "()Lcom/faceunity/core/controller/prop/BasePropController;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    private static final class ControllerHandler extends Handler {
        private final BasePropController propController;

        public ControllerHandler(Looper looper, BasePropController propController) {
            super(looper);
            Intrinsics.checkParameterIsNotNull(looper, "looper");
            Intrinsics.checkParameterIsNotNull(propController, "propController");
            this.propController = propController;
        }

        public final BasePropController getPropController() {
            return this.propController;
        }

        @Override
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            super.handleMessage(msg);
            while (true) {
                ThreadQueuePool.QueueItem queueItemPull = this.propController.threadQueuePool.pull();
                if (queueItemPull == null) {
                    break;
                } else {
                    this.propController.applyThreadQueue(queueItemPull);
                }
            }
            OnPropCallBack mOnPropCallBack = this.propController.getMOnPropCallBack();
            if (mOnPropCallBack != null) {
                mOnPropCallBack.onSuccess();
            }
        }
    }
}
