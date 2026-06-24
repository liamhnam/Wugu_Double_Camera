package com.p020hp.mobile.common;

import android.content.Context;
import com.p020hp.mobile.common.browsing.ServicesBrowser;
import com.p020hp.mobile.common.filter.DeviceFilter;
import com.p020hp.mobile.common.query.DataQuery;
import com.p020hp.mobile.common.usb.IppUsbInterfaceMapping;
import com.p020hp.mobile.common.utils.ExpirationChecker;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\f\u001a\u0004\b\u0014\u0010\u0015R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\f\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, m1293d2 = {"Lcom/hp/mobile/common/CommonLib;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "dataQuery", "Lcom/hp/mobile/common/query/DataQuery;", "getDataQuery", "()Lcom/hp/mobile/common/query/DataQuery;", "dataQuery$delegate", "Lkotlin/Lazy;", "deviceFilter", "Lcom/hp/mobile/common/filter/DeviceFilter;", "getDeviceFilter", "()Lcom/hp/mobile/common/filter/DeviceFilter;", "deviceFilter$delegate", "expChecker", "Lcom/hp/mobile/common/utils/ExpirationChecker;", "getExpChecker", "()Lcom/hp/mobile/common/utils/ExpirationChecker;", "expChecker$delegate", "servicesBrowser", "Lcom/hp/mobile/common/browsing/ServicesBrowser;", "getServicesBrowser", "()Lcom/hp/mobile/common/browsing/ServicesBrowser;", "servicesBrowser$delegate", "Companion", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class CommonLib {
    public static CommonLib instance;
    public final Context context;

    public final Lazy dataQuery;

    public final Lazy deviceFilter;

    public final Lazy expChecker;

    public final Lazy servicesBrowser;

    public static final Companion INSTANCE = new Companion(null);
    public static List<IppUsbInterfaceMapping> usbInterfaceWhiteList = CollectionsKt.emptyList();
    public static final Logger log = LoggerKt.logger(LoggerKt.toTag("CommonLib"));

    @Metadata(m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u0004J\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/mobile/common/CommonLib$Companion;", "", "()V", "instance", "Lcom/hp/mobile/common/CommonLib;", "log", "Lcom/hp/mobile/common/utils/Logger;", "usbInterfaceWhiteList", "", "Lcom/hp/mobile/common/usb/IppUsbInterfaceMapping;", "getUsbInterfaceWhiteList", "()Ljava/util/List;", "setUsbInterfaceWhiteList", "(Ljava/util/List;)V", "get", "init", "", "context", "Landroid/content/Context;", "init$common_lib_release", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
    public static final class Companion {
        public Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CommonLib get() {
            CommonLib commonLib = CommonLib.instance;
            Intrinsics.checkNotNull(commonLib);
            return commonLib;
        }

        public final List<IppUsbInterfaceMapping> getUsbInterfaceWhiteList() {
            return CommonLib.usbInterfaceWhiteList;
        }

        public final void init$common_lib_release(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (CommonLib.instance == null) {
                CommonLib.log.m446d("common-lib initialized");
                CommonLib.instance = new CommonLib(context, null);
            }
        }

        public final void setUsbInterfaceWhiteList(List<IppUsbInterfaceMapping> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            CommonLib.usbInterfaceWhiteList = list;
        }
    }

    public static final class C1642do extends Lambda implements Function0<DataQuery> {

        public static final C1642do f734if = new C1642do();

        public C1642do() {
            super(0);
        }

        @Override
        public DataQuery invoke() {
            return new DataQuery();
        }
    }

    public static final class Cfor extends Lambda implements Function0<ExpirationChecker> {

        public static final Cfor f735if = new Cfor();

        public Cfor() {
            super(0);
        }

        @Override
        public ExpirationChecker invoke() {
            return new ExpirationChecker();
        }
    }

    public static final class C1643if extends Lambda implements Function0<DeviceFilter> {

        public static final C1643if f736if = new C1643if();

        public C1643if() {
            super(0);
        }

        @Override
        public DeviceFilter invoke() {
            return new DeviceFilter(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }
    }

    public static final class Cnew extends Lambda implements Function0<ServicesBrowser> {
        public Cnew() {
            super(0);
        }

        @Override
        public ServicesBrowser invoke() {
            return new ServicesBrowser(CommonLib.this.getContext(), null, null, 6, null);
        }
    }

    public CommonLib(Context context) {
        this.context = context;
        this.expChecker = LazyKt.lazy(Cfor.f735if);
        this.deviceFilter = LazyKt.lazy(C1643if.f736if);
        this.servicesBrowser = LazyKt.lazy(new Cnew());
        this.dataQuery = LazyKt.lazy(C1642do.f734if);
    }

    public CommonLib(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final Context getContext() {
        return this.context;
    }

    public final DataQuery getDataQuery() {
        return (DataQuery) this.dataQuery.getValue();
    }

    public final DeviceFilter getDeviceFilter() {
        return (DeviceFilter) this.deviceFilter.getValue();
    }

    public final ExpirationChecker getExpChecker() {
        return (ExpirationChecker) this.expChecker.getValue();
    }

    public final ServicesBrowser getServicesBrowser() {
        return (ServicesBrowser) this.servicesBrowser.getValue();
    }
}
