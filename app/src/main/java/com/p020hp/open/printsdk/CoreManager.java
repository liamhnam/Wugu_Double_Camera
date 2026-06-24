package com.p020hp.open.printsdk;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.p020hp.mobile.common.CommonLib;
import com.p020hp.mobile.common.usb.IppUsbInterfaceMapping;
import com.shockwave.pdfium.PdfiumCore;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\tJ'\u0010 \u001a\u0002H!\"\b\b\u0000\u0010!*\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H!0$H\u0000¢\u0006\u0004\b%\u0010&J\r\u0010'\u001a\u00020\u0007H\u0000¢\u0006\u0002\b(J)\u0010)\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014`\u0015H\u0000¢\u0006\u0002\b*J\r\u0010+\u001a\u00020,H\u0000¢\u0006\u0002\b-J\r\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b.J\r\u0010/\u001a\u00020\tH\u0000¢\u0006\u0002\b0J\u000e\u00101\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J*\u00102\u001a\u00020\u00002\"\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014`\u0015J\u000e\u00103\u001a\u00020\u00002\u0006\u00104\u001a\u00020\u0004J\u0014\u00105\u001a\u00020\u00002\f\u00106\u001a\b\u0012\u0004\u0012\u00020807R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR*\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u0018\u0010\u0019¨\u00069"}, m1293d2 = {"Lcom/hp/open/printsdk/CoreManager;", "", "()V", "MAX_JOB_HISTORY_SAVE_DAYS", "", "MIN_JOB_HISTORY_SAVE_DAYS", "context", "Landroid/app/Application;", "isPrintHistoryOpen", "", "isSaveLog", "jobHistorySaveDays", "pdfiumCore", "Lcom/shockwave/pdfium/PdfiumCore;", "getPdfiumCore$print_core_thirdPartyRelease", "()Lcom/shockwave/pdfium/PdfiumCore;", "pdfiumCore$delegate", "Lkotlin/Lazy;", "ttfMap", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "viewModelStore$delegate", "checkSdkIsInitialized", "", "checkSdkIsInitialized$print_core_thirdPartyRelease", "enablePrintHistory", "isEnable", "getAppViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "getAppViewModel$print_core_thirdPartyRelease", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getContext", "getContext$print_core_thirdPartyRelease", "getCustomTTF", "getCustomTTF$print_core_thirdPartyRelease", "getJobHistoryEarliestTime", "", "getJobHistoryEarliestTime$print_core_thirdPartyRelease", "isPrintHistoryOpen$print_core_thirdPartyRelease", "isSaveSdkLog", "isSaveSdkLog$print_core_thirdPartyRelease", "setContext", "setCustomTTF", "setJobHistorySaveDays", "days", "setUsbInterfaceWhiteList", "list", "", "Lcom/hp/mobile/common/usb/IppUsbInterfaceMapping;", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class CoreManager {

    public static Application f773a = null;

    public static int f774b = 30;

    public static boolean f775c;
    public static final CoreManager INSTANCE = new CoreManager();

    public static LinkedHashMap<String, String> f776d = new LinkedHashMap<>();

    public static final Lazy f777e = LazyKt.lazy(new Function0<ViewModelStore>() {
        @Override
        public final ViewModelStore invoke() {
            return new ViewModelStore();
        }
    });

    public static final Lazy f778f = LazyKt.lazy(new Function0<PdfiumCore>() {
        @Override
        public final PdfiumCore invoke() {
            return new PdfiumCore(CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease());
        }
    });

    public final void checkSdkIsInitialized$print_core_thirdPartyRelease() {
        if (!(f773a != null)) {
            throw new IllegalStateException("Hp print Sdk is not initialized!".toString());
        }
    }

    public final CoreManager enablePrintHistory(boolean isEnable) {
        f775c = isEnable;
        return this;
    }

    public final <T extends ViewModel> T getAppViewModel$print_core_thirdPartyRelease(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        Application application = f773a;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
            application = null;
        }
        ViewModelProvider.AndroidViewModelFactory androidViewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        Intrinsics.checkNotNullExpressionValue(androidViewModelFactory, "getInstance(context)");
        T t = (T) new ViewModelProvider((ViewModelStore) f777e.getValue(), androidViewModelFactory).get(modelClass);
        Intrinsics.checkNotNullExpressionValue(t, "ViewModelProvider(viewMo… factory).get(modelClass)");
        return t;
    }

    public final Application getContext$print_core_thirdPartyRelease() {
        checkSdkIsInitialized$print_core_thirdPartyRelease();
        Application application = f773a;
        if (application != null) {
            return application;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final LinkedHashMap<String, String> getCustomTTF$print_core_thirdPartyRelease() {
        return f776d;
    }

    public final long getJobHistoryEarliestTime$print_core_thirdPartyRelease() {
        long j = 86400000;
        return (((System.currentTimeMillis() / j) - ((long) f774b)) + 1) * j;
    }

    public final PdfiumCore getPdfiumCore$print_core_thirdPartyRelease() {
        return (PdfiumCore) f778f.getValue();
    }

    public final boolean isPrintHistoryOpen$print_core_thirdPartyRelease() {
        return f775c;
    }

    public final boolean isSaveSdkLog$print_core_thirdPartyRelease() {
        return true;
    }

    public final CoreManager setContext(Application context) {
        Intrinsics.checkNotNullParameter(context, "context");
        f773a = context;
        return this;
    }

    public final CoreManager setCustomTTF(LinkedHashMap<String, String> ttfMap) {
        Intrinsics.checkNotNullParameter(ttfMap, "ttfMap");
        f776d = ttfMap;
        return this;
    }

    public final CoreManager setJobHistorySaveDays(int days) {
        if (days < 0 || days > 365) {
            throw new IllegalStateException(("Out of range value: " + days + ", input must between 0 and 365").toString());
        }
        f774b = days;
        return this;
    }

    public final CoreManager setUsbInterfaceWhiteList(List<IppUsbInterfaceMapping> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        CommonLib.INSTANCE.setUsbInterfaceWhiteList(list);
        return this;
    }
}
