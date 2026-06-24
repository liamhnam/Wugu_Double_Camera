package com.faceunity.core.faceunity;

import android.app.Application;
import android.content.Context;
import com.faceunity.core.callback.LocalOperateCallback;
import com.faceunity.core.callback.OperateCallback;
import com.faceunity.core.context.FUApplication;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aH\u0007J \u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0007J*\u0010!\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010\u001f2\u0006\u0010#\u001a\u00020\nH\u0007J \u0010$\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0007J \u0010%\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0007J(\u0010&\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020\nH\u0007J\u0010\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)H\u0007J\u0010\u0010*\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006+"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FURenderManager;", "", "()V", "mContext", "Landroid/content/Context;", "getMContext$fu_core_all_featureRelease", "()Landroid/content/Context;", "mContext$delegate", "Lkotlin/Lazy;", "mLocalOperateCallback", "Lcom/faceunity/core/callback/LocalOperateCallback;", "getMLocalOperateCallback$fu_core_all_featureRelease", "()Lcom/faceunity/core/callback/LocalOperateCallback;", "setMLocalOperateCallback$fu_core_all_featureRelease", "(Lcom/faceunity/core/callback/LocalOperateCallback;)V", "mOperateCallback", "Lcom/faceunity/core/callback/OperateCallback;", "getMOperateCallback$fu_core_all_featureRelease", "()Lcom/faceunity/core/callback/OperateCallback;", "setMOperateCallback$fu_core_all_featureRelease", "(Lcom/faceunity/core/callback/OperateCallback;)V", "openFileLog", "", "path", "", "maxFileSize", "", "maxFiles", "registerFURender", "context", "auth", "", "operateCallback", "registerFURenderDeviceLocal", "offlineBundle", "localOperateCallback", "registerFURenderInternalCheck", "registerFURenderInternalCheckPackageBind", "registerFURenderLocal", "setCoreDebug", "logLevel", "Lcom/faceunity/core/utils/FULogger$LogLevel;", "setKitDebug", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FURenderManager {
    public static final FURenderManager INSTANCE = new FURenderManager();

    private static final Lazy mContext = LazyKt.lazy(new Function0<Application>() {
        @Override
        public final Application invoke() {
            return FUApplication.getApplication();
        }
    });
    private static LocalOperateCallback mLocalOperateCallback;
    private static OperateCallback mOperateCallback;

    public final Context getMContext$fu_core_all_featureRelease() {
        return (Context) mContext.getValue();
    }

    private FURenderManager() {
    }

    public final OperateCallback getMOperateCallback$fu_core_all_featureRelease() {
        return mOperateCallback;
    }

    public final void setMOperateCallback$fu_core_all_featureRelease(OperateCallback operateCallback) {
        mOperateCallback = operateCallback;
    }

    public final LocalOperateCallback getMLocalOperateCallback$fu_core_all_featureRelease() {
        return mLocalOperateCallback;
    }

    public final void setMLocalOperateCallback$fu_core_all_featureRelease(LocalOperateCallback localOperateCallback) {
        mLocalOperateCallback = localOperateCallback;
    }

    @JvmStatic
    public static final void registerFURender(Context context, byte[] auth, OperateCallback operateCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(auth, "auth");
        Intrinsics.checkParameterIsNotNull(operateCallback, "operateCallback");
        mOperateCallback = operateCallback;
        if (!SDKController.INSTANCE.fuIsLibraryInit$fu_core_all_featureRelease()) {
            SDKController.INSTANCE.setup$fu_core_all_featureRelease(auth);
        } else {
            operateCallback.onSuccess(200, "setup");
        }
    }

    @JvmStatic
    public static final void registerFURenderLocal(Context context, byte[] auth, byte[] offlineBundle, LocalOperateCallback localOperateCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(auth, "auth");
        Intrinsics.checkParameterIsNotNull(offlineBundle, "offlineBundle");
        Intrinsics.checkParameterIsNotNull(localOperateCallback, "localOperateCallback");
        mLocalOperateCallback = localOperateCallback;
        if (!SDKController.INSTANCE.fuIsLibraryInit$fu_core_all_featureRelease()) {
            SDKController.INSTANCE.setupLocal$fu_core_all_featureRelease(auth, offlineBundle);
        } else {
            localOperateCallback.onSuccess(200, "setupLocal", auth);
        }
    }

    @JvmStatic
    public static final void registerFURenderDeviceLocal(Context context, byte[] auth, byte[] offlineBundle, LocalOperateCallback localOperateCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(auth, "auth");
        Intrinsics.checkParameterIsNotNull(localOperateCallback, "localOperateCallback");
        mLocalOperateCallback = localOperateCallback;
        if (!SDKController.INSTANCE.fuIsLibraryInit$fu_core_all_featureRelease()) {
            SDKController.INSTANCE.setupDeviceLocal$fu_core_all_featureRelease(auth, offlineBundle);
        } else {
            localOperateCallback.onSuccess(200, "setupDeviceLocal", auth);
        }
    }

    @JvmStatic
    public static final void registerFURenderInternalCheck(Context context, byte[] auth, OperateCallback operateCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(auth, "auth");
        Intrinsics.checkParameterIsNotNull(operateCallback, "operateCallback");
        mOperateCallback = operateCallback;
        if (!SDKController.INSTANCE.fuIsLibraryInit$fu_core_all_featureRelease()) {
            SDKController.INSTANCE.setupInternalCheck$fu_core_all_featureRelease(auth);
        } else {
            operateCallback.onSuccess(200, "setupInternalCheck");
        }
    }

    @JvmStatic
    public static final void registerFURenderInternalCheckPackageBind(Context context, byte[] auth, OperateCallback operateCallback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(auth, "auth");
        Intrinsics.checkParameterIsNotNull(operateCallback, "operateCallback");
        mOperateCallback = operateCallback;
        if (!SDKController.INSTANCE.fuIsLibraryInit$fu_core_all_featureRelease()) {
            SDKController.INSTANCE.setupInternalCheckPackageBind$fu_core_all_featureRelease(auth);
        } else {
            operateCallback.onSuccess(200, "setupInternalCheckPackageBind");
        }
    }

    @JvmStatic
    public static final void setKitDebug(FULogger.LogLevel logLevel) {
        Intrinsics.checkParameterIsNotNull(logLevel, "logLevel");
        FULogger.INSTANCE.setLogLevel$fu_core_all_featureRelease(logLevel);
    }

    @JvmStatic
    public static final void setCoreDebug(FULogger.LogLevel logLevel) {
        Intrinsics.checkParameterIsNotNull(logLevel, "logLevel");
        SDKController.INSTANCE.setLogLevel$fu_core_all_featureRelease(logLevel.ordinal());
    }

    @JvmStatic
    public static final void openFileLog(String path, int maxFileSize, int maxFiles) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        SDKController.INSTANCE.openFileLog$fu_core_all_featureRelease(path, maxFileSize, maxFiles);
    }
}
