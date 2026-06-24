package com.faceunity.core.controller;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.core.app.NotificationCompat;
import androidx.room.RoomDatabase;
import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.callback.OnControllerBundleLoadCallback;
import com.faceunity.core.controller.facebeauty.FaceBeautyController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.entity.TextureImage;
import com.faceunity.core.faceunity.FURenderKit;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.faceunity.core.utils.FileUtils;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\b&\u0018\u00002\u00020\u0001:\u0001yB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010;\u001a\u0002082\u0006\u0010<\u001a\u00020=H$J,\u0010>\u001a\u0002082\b\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010A\u001a\u00020\n2\u0010\b\u0002\u0010B\u001a\n\u0012\u0004\u0012\u000208\u0018\u000107H\u0004J%\u0010C\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004H\u0000¢\u0006\u0002\bGJ(\u0010C\u001a\u0002082\u0006\u0010E\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\u001c2\u0006\u0010K\u001a\u00020\u001cH\u0004J\u0018\u0010C\u001a\u0002082\u0006\u0010E\u001a\u00020\u00042\u0006\u0010F\u001a\u00020\u0004H\u0004J\u001d\u0010L\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010E\u001a\u00020\u0004H\u0000¢\u0006\u0002\bMJ\u0010\u0010L\u001a\u0002082\u0006\u0010E\u001a\u00020\u0004H\u0004J \u0010N\u001a\u0002082\b\b\u0002\u0010O\u001a\u00020\u001c2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020807H\u0004J\u0016\u0010P\u001a\u0002082\f\u0010B\u001a\b\u0012\u0004\u0012\u00020807H\u0004J!\u0010Q\u001a\u0004\u0018\u00010R2\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u0004H\u0010¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u0004H\u0010¢\u0006\u0002\bXJ\u001f\u0010Y\u001a\u0004\u0018\u00010Z2\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u0004H\u0010¢\u0006\u0002\b[J\u001f\u0010\\\u001a\u0004\u0018\u00010\u00042\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u0004H\u0010¢\u0006\u0002\b]J\u001e\u0010^\u001a\u0004\u0018\u00010\u00012\u0006\u0010S\u001a\u00020\u00042\n\u0010_\u001a\u0006\u0012\u0002\b\u00030`H\u0004J\u0010\u0010a\u001a\u00020R2\u0006\u0010S\u001a\u00020\u0004H\u0004J\u0012\u0010b\u001a\u0004\u0018\u00010W2\u0006\u0010S\u001a\u00020\u0004H\u0004J\u0012\u0010c\u001a\u0004\u0018\u00010Z2\u0006\u0010S\u001a\u00020\u0004H\u0004J\u0012\u0010d\u001a\u0004\u0018\u00010\u00042\u0006\u0010S\u001a\u00020\u0004H\u0004J\u0018\u0010e\u001a\u0002082\u0006\u0010S\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u0001H\u0004J,\u0010e\u001a\u0002082\"\u0010g\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010,j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`.H\u0004J!\u0010h\u001a\u0002082\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u0016H\u0000¢\u0006\u0002\bjJ\u001f\u0010k\u001a\u0002082\u0010\b\u0002\u0010B\u001a\n\u0012\u0004\u0012\u000208\u0018\u000107H\u0010¢\u0006\u0002\blJ\r\u0010m\u001a\u000208H\u0000¢\u0006\u0002\bnJ\u0010\u0010o\u001a\u0002082\u0006\u0010O\u001a\u00020\u001cH\u0002J\u001d\u0010p\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010A\u001a\u00020\nH\u0010¢\u0006\u0002\bqJ%\u0010r\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u0001H\u0010¢\u0006\u0002\bsJ9\u0010r\u001a\u0002082\u0006\u0010D\u001a\u0002002\"\u0010g\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010,j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`.H\u0000¢\u0006\u0002\bsJ%\u0010t\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u0001H\u0000¢\u0006\u0002\buJ9\u0010t\u001a\u0002082\u0006\u0010D\u001a\u0002002\"\u0010g\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010,j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`.H\u0000¢\u0006\u0002\buJ%\u0010v\u001a\u0002082\u0006\u0010D\u001a\u0002002\u0006\u0010S\u001a\u00020\u00042\u0006\u0010f\u001a\u00020\u0001H\u0000¢\u0006\u0002\bwJ\b\u0010x\u001a\u000208H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001b\u0010\u000f\u001a\u00020\u00108DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001b\u0010!\u001a\u00020\"8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0014\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020'8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u0014\u001a\u0004\b(\u0010)R*\u0010+\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020-0,j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020-`.X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000200X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R&\u00105\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002080706X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006z"}, m1293d2 = {"Lcom/faceunity/core/controller/BaseSingleController;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "controllerHandler", "Lcom/faceunity/core/controller/BaseSingleController$ControllerHandler;", "isBackgroundRunning", "", "isNeedApplyBundleGLThread", "()Z", "setNeedApplyBundleGLThread", "(Z)V", "mBundleManager", "Lcom/faceunity/core/bundle/BundleManager;", "getMBundleManager", "()Lcom/faceunity/core/bundle/BundleManager;", "mBundleManager$delegate", "Lkotlin/Lazy;", "mCallback", "Lcom/faceunity/core/callback/OnControllerBundleLoadCallback;", "getMCallback", "()Lcom/faceunity/core/callback/OnControllerBundleLoadCallback;", "setMCallback", "(Lcom/faceunity/core/callback/OnControllerBundleLoadCallback;)V", "mControllerBundleHandle", "", "getMControllerBundleHandle$fu_core_all_featureRelease", "()I", "setMControllerBundleHandle$fu_core_all_featureRelease", "(I)V", "mFURenderBridge", "Lcom/faceunity/core/support/FURenderBridge;", "getMFURenderBridge", "()Lcom/faceunity/core/support/FURenderBridge;", "mFURenderBridge$delegate", "mFURenderKit", "Lcom/faceunity/core/faceunity/FURenderKit;", "getMFURenderKit", "()Lcom/faceunity/core/faceunity/FURenderKit;", "mFURenderKit$delegate", "mTextureImageMap", "Ljava/util/LinkedHashMap;", "Lcom/faceunity/core/entity/TextureImage;", "Lkotlin/collections/LinkedHashMap;", "modelSign", "", "getModelSign", "()J", "setModelSign", "(J)V", "modelUnitCache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/Function0;", "", "getModelUnitCache", "()Ljava/util/concurrent/ConcurrentHashMap;", "applyControllerBundle", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "applyControllerBundleAction", "bundle", "Lcom/faceunity/core/entity/FUBundleData;", "enable", "unit", "createItemTex", "sign", NamingTable.TAG, "path", "createItemTex$fu_core_all_featureRelease", "bytes", "", StreamInformation.KEY_WIDTH, StreamInformation.KEY_HEIGHT, "deleteItemTex", "deleteItemTex$fu_core_all_featureRelease", "doBackgroundAction", "code", "doGLThreadAction", "getItemParamDouble", "", "key", "getItemParamDouble$fu_core_all_featureRelease", "(JLjava/lang/String;)Ljava/lang/Double;", "getItemParamDoubleArray", "", "getItemParamDoubleArray$fu_core_all_featureRelease", "getItemParamFloatArray", "", "getItemParamFloatArray$fu_core_all_featureRelease", "getItemParamString", "getItemParamString$fu_core_all_featureRelease", "itemGetParam", "clazz", "Ljava/lang/Class;", "itemGetParamDouble", "itemGetParamDoubleArray", "itemGetParamFloatArray", "itemGetParamString", "itemSetParam", "value", "params", "loadControllerBundle", "callback", "loadControllerBundle$fu_core_all_featureRelease", "release", "release$fu_core_all_featureRelease", "releaseThread", "releaseThread$fu_core_all_featureRelease", "removeBackgroundAction", "setBundleEnable", "setBundleEnable$fu_core_all_featureRelease", "setItemParam", "setItemParam$fu_core_all_featureRelease", "setItemParamBackground", "setItemParamBackground$fu_core_all_featureRelease", "setItemParamGL", "setItemParamGL$fu_core_all_featureRelease", "startBackgroundThread", "ControllerHandler", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public abstract class BaseSingleController {
    private ControllerHandler controllerHandler;
    private boolean isBackgroundRunning;
    private volatile boolean isNeedApplyBundleGLThread;
    private OnControllerBundleLoadCallback mCallback;
    private final String TAG = "KIT_" + getClass().getSimpleName();
    private long modelSign = -99;
    private int mControllerBundleHandle = -1;

    private final Lazy mBundleManager = LazyKt.lazy(new Function0<BundleManager>() {
        @Override
        public final BundleManager invoke() {
            return BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });

    private final Lazy mFURenderKit = LazyKt.lazy(new Function0<FURenderKit>() {
        @Override
        public final FURenderKit invoke() {
            return FURenderKit.Companion.getInstance();
        }
    });

    private final Lazy mFURenderBridge = LazyKt.lazy(new Function0<FURenderBridge>() {
        @Override
        public final FURenderBridge invoke() {
            return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });
    private final ConcurrentHashMap<String, Function0<Unit>> modelUnitCache = new ConcurrentHashMap<>();
    private final LinkedHashMap<String, TextureImage> mTextureImageMap = new LinkedHashMap<>(16);

    protected abstract void applyControllerBundle(FUFeaturesData featuresData);

    protected final BundleManager getMBundleManager() {
        return (BundleManager) this.mBundleManager.getValue();
    }

    protected final FURenderBridge getMFURenderBridge() {
        return (FURenderBridge) this.mFURenderBridge.getValue();
    }

    protected final FURenderKit getMFURenderKit() {
        return (FURenderKit) this.mFURenderKit.getValue();
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final long getModelSign() {
        return this.modelSign;
    }

    protected final void setModelSign(long j) {
        this.modelSign = j;
    }

    public final int getMControllerBundleHandle() {
        return this.mControllerBundleHandle;
    }

    public final void setMControllerBundleHandle$fu_core_all_featureRelease(int i) {
        this.mControllerBundleHandle = i;
    }

    protected final boolean getIsNeedApplyBundleGLThread() {
        return this.isNeedApplyBundleGLThread;
    }

    protected final void setNeedApplyBundleGLThread(boolean z) {
        this.isNeedApplyBundleGLThread = z;
    }

    protected final OnControllerBundleLoadCallback getMCallback() {
        return this.mCallback;
    }

    protected final void setMCallback(OnControllerBundleLoadCallback onControllerBundleLoadCallback) {
        this.mCallback = onControllerBundleLoadCallback;
    }

    protected final ConcurrentHashMap<String, Function0<Unit>> getModelUnitCache() {
        return this.modelUnitCache;
    }

    public static void loadControllerBundle$fu_core_all_featureRelease$default(BaseSingleController baseSingleController, FUFeaturesData fUFeaturesData, OnControllerBundleLoadCallback onControllerBundleLoadCallback, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: loadControllerBundle");
        }
        if ((i & 2) != 0) {
            onControllerBundleLoadCallback = null;
        }
        baseSingleController.loadControllerBundle$fu_core_all_featureRelease(fUFeaturesData, onControllerBundleLoadCallback);
    }

    public final void loadControllerBundle$fu_core_all_featureRelease(final FUFeaturesData featuresData, final OnControllerBundleLoadCallback callback) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        removeBackgroundAction(RoomDatabase.MAX_BIND_PARAMETER_CNT);
        doBackgroundAction(RoomDatabase.MAX_BIND_PARAMETER_CNT, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                OnControllerBundleLoadCallback mCallback;
                long jNanoTime = System.nanoTime();
                this.this$0.setModelSign(jNanoTime);
                this.this$0.setMCallback(callback);
                this.this$0.applyControllerBundle(featuresData);
                if (this.this$0.getIsNeedApplyBundleGLThread() || (mCallback = this.this$0.getMCallback()) == null) {
                    return;
                }
                mCallback.onLoadSuccess(jNanoTime);
            }
        });
    }

    public void setBundleEnable$fu_core_all_featureRelease(long sign, boolean enable) {
        if (sign != this.modelSign) {
            return;
        }
        FULogger.m295i(this.TAG, "setItemParam  enable:" + enable + "  ");
        if (enable) {
            getMBundleManager().bindControllerBundle(this.mControllerBundleHandle, this instanceof FaceBeautyController);
        } else {
            getMBundleManager().unbindControllerBundle(this.mControllerBundleHandle);
        }
    }

    public Double getItemParamDouble$fu_core_all_featureRelease(long sign, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (sign != this.modelSign) {
            return null;
        }
        FULogger.m295i(this.TAG, "setItemParam   key:" + key);
        return Double.valueOf(itemGetParamDouble(key));
    }

    public double[] getItemParamDoubleArray$fu_core_all_featureRelease(long sign, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (sign != this.modelSign) {
            return null;
        }
        FULogger.m295i(this.TAG, "setItemParam   key:" + key);
        return itemGetParamDoubleArray(key);
    }

    public String getItemParamString$fu_core_all_featureRelease(long sign, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (sign != this.modelSign) {
            return null;
        }
        FULogger.m295i(this.TAG, "setItemParam   key:" + key);
        return itemGetParamString(key);
    }

    public float[] getItemParamFloatArray$fu_core_all_featureRelease(long sign, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (sign != this.modelSign) {
            return null;
        }
        FULogger.m295i(this.TAG, "setItemParam   key:" + key);
        return itemGetParamFloatArray(key);
    }

    public void setItemParam$fu_core_all_featureRelease(long sign, String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (sign != this.modelSign) {
            return;
        }
        FULogger.m295i(this.TAG, "setItemParam   key:" + key + "  value:" + value);
        itemSetParam(key, value);
    }

    public final void setItemParamGL$fu_core_all_featureRelease(long sign, final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (sign != this.modelSign) {
            return;
        }
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                FULogger.m295i(this.this$0.getTAG(), "setItemParam   key:" + key + "  value:" + value);
                this.this$0.itemSetParam(key, value);
            }
        });
    }

    public final void setItemParamBackground$fu_core_all_featureRelease(long sign, final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (sign != this.modelSign) {
            return;
        }
        doBackgroundAction$default(this, 0, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                FULogger.m295i(this.this$0.getTAG(), "setItemParamBackground  key:" + key + "  value:" + value);
                this.this$0.itemSetParam(key, value);
            }
        }, 1, null);
    }

    public final void setItemParamBackground$fu_core_all_featureRelease(long sign, final LinkedHashMap<String, Object> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        if (sign != this.modelSign) {
            return;
        }
        doBackgroundAction$default(this, 0, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                FULogger.m295i(this.this$0.getTAG(), "setItemParamBackground    params.size:" + params.size());
                this.this$0.itemSetParam(params);
            }
        }, 1, null);
    }

    public final void setItemParam$fu_core_all_featureRelease(long sign, LinkedHashMap<String, Object> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        if (sign != this.modelSign) {
            return;
        }
        FULogger.m295i(this.TAG, "setItemParam    params.size:" + params.size());
        itemSetParam(params);
    }

    public final void createItemTex$fu_core_all_featureRelease(long sign, String name, String path) throws IOException {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        if (sign != this.modelSign) {
            return;
        }
        FULogger.m295i(this.TAG, "createItemTex   name:" + name + "  path:" + path);
        createItemTex(name, path);
    }

    public final void deleteItemTex$fu_core_all_featureRelease(long sign, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (sign != this.modelSign) {
            return;
        }
        FULogger.m295i(this.TAG, "deleteItemTex    name:" + name + "  ");
        deleteItemTex(name);
    }

    public static void applyControllerBundleAction$default(BaseSingleController baseSingleController, FUBundleData fUBundleData, boolean z, Function0 function0, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: applyControllerBundleAction");
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        baseSingleController.applyControllerBundleAction(fUBundleData, z, function0);
    }

    protected final void applyControllerBundleAction(FUBundleData bundle, boolean enable, Function0<Unit> unit) {
        int iLoadBundleFile = bundle != null ? getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath()) : 0;
        if (iLoadBundleFile <= 0) {
            getMBundleManager().destroyControllerBundle(this.mControllerBundleHandle);
            this.mControllerBundleHandle = -1;
            FULogger.m294e(this.TAG, "loadControllerBundle failed handle:" + iLoadBundleFile + "  path:" + (bundle != null ? bundle.getPath() : null));
            return;
        }
        if (enable) {
            getMBundleManager().updateControllerBundle(this.mControllerBundleHandle, iLoadBundleFile, this instanceof FaceBeautyController);
        } else {
            getMBundleManager().destroyControllerBundle(this.mControllerBundleHandle);
        }
        this.mControllerBundleHandle = iLoadBundleFile;
        if (unit != null) {
            unit.invoke();
        }
    }

    public final void itemSetParam(LinkedHashMap<String, Object> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        FULogger.m295i(this.TAG, "setItemParam   params.size:" + params.size());
        if (this.mControllerBundleHandle <= 0) {
            FULogger.m294e(this.TAG, "setItemParam failed handle:" + this.mControllerBundleHandle + "  ");
            return;
        }
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Double) {
                SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).doubleValue());
            } else if (value instanceof String) {
                SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, (String) value);
            } else if (value instanceof double[]) {
                SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, (double[]) value);
            } else if (value instanceof Integer) {
                SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).intValue());
            } else if (value instanceof Float) {
                SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).floatValue());
            }
        }
    }

    protected final double itemGetParamDouble(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (!(itemGetParam(key, Double.TYPE) instanceof Double)) {
            return 0.0d;
        }
        Object objItemGetParam = itemGetParam(key, Double.TYPE);
        if (objItemGetParam != null) {
            return ((Double) objItemGetParam).doubleValue();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Double");
    }

    protected final double[] itemGetParamDoubleArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (!(itemGetParam(key, double[].class) instanceof Double)) {
            return null;
        }
        Object objItemGetParam = itemGetParam(key, double[].class);
        if (objItemGetParam != null) {
            return (double[]) objItemGetParam;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.DoubleArray");
    }

    protected final String itemGetParamString(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (!(itemGetParam(key, double[].class) instanceof String)) {
            return null;
        }
        Object objItemGetParam = itemGetParam(key, String.class);
        if (objItemGetParam != null) {
            return (String) objItemGetParam;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    protected final float[] itemGetParamFloatArray(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (!(itemGetParam(key, double[].class) instanceof float[])) {
            return null;
        }
        Object objItemGetParam = itemGetParam(key, float[].class);
        if (objItemGetParam != null) {
            return (float[]) objItemGetParam;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.FloatArray");
    }

    protected final Object itemGetParam(String key, Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        FULogger.m295i(this.TAG, "setItemParam  key:" + key);
        if (this.mControllerBundleHandle <= 0) {
            FULogger.m294e(this.TAG, "setItemParam failed handle:" + this.mControllerBundleHandle + "  ");
            return null;
        }
        return SDKController.INSTANCE.itemGetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, clazz);
    }

    protected final void itemSetParam(String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        FULogger.m295i(this.TAG, "setItemParam  key:" + key + "   value:" + value);
        if (this.mControllerBundleHandle <= 0) {
            FULogger.m294e(this.TAG, "setItemParam failed handle:" + this.mControllerBundleHandle + "  ");
            return;
        }
        if (value instanceof Double) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).doubleValue());
            return;
        }
        if (value instanceof String) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, (String) value);
            return;
        }
        if (value instanceof double[]) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, (double[]) value);
        } else if (value instanceof Integer) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).intValue());
        } else if (value instanceof Float) {
            SDKController.INSTANCE.itemSetParam$fu_core_all_featureRelease(this.mControllerBundleHandle, key, ((Number) value).floatValue());
        }
    }

    protected final void createItemTex(final String name, final String path) throws IOException {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        FULogger.m295i(this.TAG, "createItemTex  name:" + name + "  path:" + path);
        if (this.mControllerBundleHandle <= 0) {
            FULogger.m294e(this.TAG, "createItemTex failed handle:" + this.mControllerBundleHandle + "  ");
            return;
        }
        final TextureImage textureImageLoadTextureImageFromLocal = this.mTextureImageMap.get(path);
        if (textureImageLoadTextureImageFromLocal == null) {
            textureImageLoadTextureImageFromLocal = FileUtils.loadTextureImageFromLocal(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), path);
        }
        if (textureImageLoadTextureImageFromLocal != null) {
            this.mTextureImageMap.put(path, textureImageLoadTextureImageFromLocal);
            doGLThreadAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    SDKController.INSTANCE.createTexForItem$fu_core_all_featureRelease(this.getMControllerBundleHandle(), name, textureImageLoadTextureImageFromLocal.getBytes(), textureImageLoadTextureImageFromLocal.getWidth(), textureImageLoadTextureImageFromLocal.getHeight());
                }
            });
        }
    }

    protected final void createItemTex(final String name, final byte[] bytes, final int width, final int height) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(bytes, "bytes");
        FULogger.m296t(this.TAG, "createItemTex   name:" + name + "  width:" + width + " height:" + height);
        doGLThreadAction(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                SDKController.INSTANCE.createTexForItem$fu_core_all_featureRelease(BaseSingleController.this.getMControllerBundleHandle(), name, bytes, width, height);
            }
        });
    }

    protected final void deleteItemTex(final String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        FULogger.m296t(this.TAG, "deleteItemTex   name:" + name + "  ");
        if (this.mControllerBundleHandle <= 0) {
            FULogger.m294e(this.TAG, "deleteItemTex failed handle:" + this.mControllerBundleHandle + "  ");
        } else {
            doGLThreadAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    SDKController.INSTANCE.deleteTexForItem$fu_core_all_featureRelease(BaseSingleController.this.getMControllerBundleHandle(), name);
                }
            });
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m1293d2 = {"Lcom/faceunity/core/controller/BaseSingleController$ControllerHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "singleController", "Lcom/faceunity/core/controller/BaseSingleController;", "(Landroid/os/Looper;Lcom/faceunity/core/controller/BaseSingleController;)V", "getSingleController", "()Lcom/faceunity/core/controller/BaseSingleController;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    private static final class ControllerHandler extends Handler {
        private final BaseSingleController singleController;

        public ControllerHandler(Looper looper, BaseSingleController singleController) {
            super(looper);
            Intrinsics.checkParameterIsNotNull(looper, "looper");
            Intrinsics.checkParameterIsNotNull(singleController, "singleController");
            this.singleController = singleController;
        }

        public final BaseSingleController getSingleController() {
            return this.singleController;
        }

        @Override
        public void handleMessage(Message msg) {
            Intrinsics.checkParameterIsNotNull(msg, "msg");
            super.handleMessage(msg);
            this.singleController.isBackgroundRunning = true;
            Object obj = msg.obj;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Runnable");
            }
            ((Runnable) obj).run();
            this.singleController.isBackgroundRunning = false;
        }
    }

    public static void doBackgroundAction$default(BaseSingleController baseSingleController, int i, Function0 function0, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: doBackgroundAction");
        }
        if ((i2 & 1) != 0) {
            i = 1;
        }
        baseSingleController.doBackgroundAction(i, function0);
    }

    protected final void doBackgroundAction(int code, final Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        Message message = new Message();
        message.what = code;
        message.obj = new Runnable() {
            @Override
            public final void run() {
                Intrinsics.checkExpressionValueIsNotNull(unit.invoke(), "invoke(...)");
            }
        };
        if (this.controllerHandler == null) {
            startBackgroundThread();
        }
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            controllerHandler.sendMessage(message);
        }
    }

    protected final void doGLThreadAction(Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        getMFURenderBridge().doGLThreadAction$fu_core_all_featureRelease(unit);
    }

    private final void removeBackgroundAction(int code) {
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            controllerHandler.removeMessages(code);
        }
    }

    private final void startBackgroundThread() {
        HandlerThread handlerThread = new HandlerThread("KIT_" + getClass().getSimpleName());
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "backgroundThread.looper");
        this.controllerHandler = new ControllerHandler(looper, this);
    }

    public final void releaseThread$fu_core_all_featureRelease() {
        Looper looper;
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null && (looper = controllerHandler.getLooper()) != null) {
            looper.quitSafely();
        }
        this.controllerHandler = null;
    }

    public static void release$fu_core_all_featureRelease$default(BaseSingleController baseSingleController, Function0 function0, int i, Object obj) throws InterruptedException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: release");
        }
        if ((i & 1) != 0) {
            function0 = null;
        }
        baseSingleController.release$fu_core_all_featureRelease(function0);
    }

    public void release$fu_core_all_featureRelease(final Function0<Unit> unit) throws InterruptedException {
        ControllerHandler controllerHandler = this.controllerHandler;
        if (controllerHandler != null) {
            if (controllerHandler != null) {
                controllerHandler.removeCallbacksAndMessages(null);
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            doBackgroundAction$default(this, 0, new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.setModelSign(-99L);
                    if (this.getMControllerBundleHandle() > 0) {
                        Function0 function0 = unit;
                        if (function0 != null) {
                        }
                        this.getMBundleManager().destroyControllerBundle(this.getMControllerBundleHandle());
                        this.setMControllerBundleHandle$fu_core_all_featureRelease(-1);
                    }
                    countDownLatch.countDown();
                }
            }, 1, null);
            countDownLatch.await();
        }
        releaseThread$fu_core_all_featureRelease();
    }
}
