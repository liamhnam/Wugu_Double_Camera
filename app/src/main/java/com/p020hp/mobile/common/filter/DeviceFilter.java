package com.p020hp.mobile.common.filter;

import com.p020hp.mobile.common.identity.DeviceIdentity;
import com.p020hp.mobile.common.utils.Logger;
import com.p020hp.mobile.common.utils.LoggerKt;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import p066do.p026do.p028if.p029do.C2070do;

@Metadata(m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B%\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ*\u0010\t\u001a\u00020\n2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000b\u001a\u00020\fJ2\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003J\u001e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J\u001e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/mobile/common/filter/DeviceFilter;", "", "filterModelList", "", "", "filterSKUList", "(Ljava/util/List;Ljava/util/List;)V", "log", "Lcom/hp/mobile/common/utils/Logger;", "filter", "", "deviceIdentity", "Lcom/hp/mobile/common/identity/DeviceIdentity;", "filter$common_lib_release", "filterSkuList", "filterBLE", "bleName", "sku", "filterBleList", "matchBleName", "matchModel", "matchProductNumber", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class DeviceFilter {
    public final List<String> filterModelList;
    public final List<String> filterSKUList;
    public final Logger log;

    public DeviceFilter() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public DeviceFilter(List<String> filterModelList, List<String> filterSKUList) {
        Intrinsics.checkNotNullParameter(filterModelList, "filterModelList");
        Intrinsics.checkNotNullParameter(filterSKUList, "filterSKUList");
        this.filterModelList = filterModelList;
        this.filterSKUList = filterSKUList;
        this.log = LoggerKt.logger(LoggerKt.toTag("DeviceFilter"));
    }

    public DeviceFilter(List FILTER_MODEL, List FILTER_SKU, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            FILTER_MODEL = C2070do.f2502if;
            Intrinsics.checkNotNullExpressionValue(FILTER_MODEL, "FILTER_MODEL");
        }
        if ((i & 2) != 0) {
            FILTER_SKU = C2070do.f3917for;
            Intrinsics.checkNotNullExpressionValue(FILTER_SKU, "FILTER_SKU");
        }
        this(FILTER_MODEL, FILTER_SKU);
    }

    private final boolean matchBleName(DeviceIdentity deviceIdentity, List<String> filterBleList) {
        if (!filterBleList.isEmpty()) {
            for (String str : filterBleList) {
                if (!StringsKt.isBlank(str)) {
                    Regex regex = new Regex(str + ".*");
                    String model = deviceIdentity.getModel();
                    Locale US = Locale.US;
                    Intrinsics.checkNotNullExpressionValue(US, "US");
                    String lowerCase = model.toLowerCase(US);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                    if (regex.matches(lowerCase)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private final boolean matchModel(DeviceIdentity deviceIdentity, List<String> filterModelList) {
        for (String str : filterModelList) {
            if (!StringsKt.isBlank(str)) {
                Regex regex = new Regex(str + ".*");
                String model = deviceIdentity.getModel();
                Locale US = Locale.US;
                Intrinsics.checkNotNullExpressionValue(US, "US");
                String lowerCase = model.toLowerCase(US);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (regex.matches(lowerCase)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean matchProductNumber(DeviceIdentity deviceIdentity, List<String> filterSKUList) {
        String sku = deviceIdentity.getSku();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = sku.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        return filterSKUList.contains(lowerCase);
    }

    public final boolean filter(List<String> filterModelList, List<String> filterSkuList, DeviceIdentity deviceIdentity) {
        Intrinsics.checkNotNullParameter(filterModelList, "filterModelList");
        Intrinsics.checkNotNullParameter(filterSkuList, "filterSkuList");
        Intrinsics.checkNotNullParameter(deviceIdentity, "deviceIdentity");
        return (filterModelList.isEmpty() && filterSkuList.isEmpty()) || matchProductNumber(deviceIdentity, filterSkuList) || matchModel(deviceIdentity, filterModelList);
    }

    public final boolean filter$common_lib_release(DeviceIdentity deviceIdentity) {
        Intrinsics.checkNotNullParameter(deviceIdentity, "deviceIdentity");
        return filter(this.filterModelList, this.filterSKUList, deviceIdentity);
    }

    public final boolean filterBLE(String bleName, String sku, List<String> filterBleList, List<String> filterSKUList) {
        Intrinsics.checkNotNullParameter(bleName, "bleName");
        Intrinsics.checkNotNullParameter(sku, "sku");
        Intrinsics.checkNotNullParameter(filterBleList, "filterBleList");
        Intrinsics.checkNotNullParameter(filterSKUList, "filterSKUList");
        DeviceIdentity deviceIdentity = new DeviceIdentity("", bleName, sku, null, 8, null);
        return (filterSKUList.isEmpty() && filterBleList.isEmpty()) || matchBleName(deviceIdentity, filterBleList) || matchProductNumber(deviceIdentity, filterSKUList);
    }
}
