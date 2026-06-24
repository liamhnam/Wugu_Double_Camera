package com.p020hp.mobile.common.identity;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0006\u0010\u0017\u001a\u00020\u0013J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/mobile/common/identity/DeviceIdentity;", "", "uuid", "", "model", "sku", "icon", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getIcon", "()Ljava/lang/String;", "getModel", "getSku", "getUuid", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "isEmpty", "toString", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class DeviceIdentity {
    public final String icon;
    public final String model;
    public final String sku;
    public final String uuid;

    public DeviceIdentity(String uuid, String model, String sku, String icon) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(sku, "sku");
        Intrinsics.checkNotNullParameter(icon, "icon");
        this.uuid = uuid;
        this.model = model;
        this.sku = sku;
        this.icon = icon;
    }

    public DeviceIdentity(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4);
    }

    public static DeviceIdentity copy$default(DeviceIdentity deviceIdentity, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceIdentity.uuid;
        }
        if ((i & 2) != 0) {
            str2 = deviceIdentity.model;
        }
        if ((i & 4) != 0) {
            str3 = deviceIdentity.sku;
        }
        if ((i & 8) != 0) {
            str4 = deviceIdentity.icon;
        }
        return deviceIdentity.copy(str, str2, str3, str4);
    }

    public final String getUuid() {
        return this.uuid;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSku() {
        return this.sku;
    }

    public final String getIcon() {
        return this.icon;
    }

    public final DeviceIdentity copy(String uuid, String model, String sku, String icon) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(sku, "sku");
        Intrinsics.checkNotNullParameter(icon, "icon");
        return new DeviceIdentity(uuid, model, sku, icon);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceIdentity)) {
            return false;
        }
        DeviceIdentity deviceIdentity = (DeviceIdentity) other;
        return Intrinsics.areEqual(this.uuid, deviceIdentity.uuid) && Intrinsics.areEqual(this.model, deviceIdentity.model) && Intrinsics.areEqual(this.sku, deviceIdentity.sku) && Intrinsics.areEqual(this.icon, deviceIdentity.icon);
    }

    public final String getIcon() {
        return this.icon;
    }

    public final String getModel() {
        return this.model;
    }

    public final String getSku() {
        return this.sku;
    }

    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return (((((this.uuid.hashCode() * 31) + this.model.hashCode()) * 31) + this.sku.hashCode()) * 31) + this.icon.hashCode();
    }

    public final boolean isEmpty() {
        if (this.model.length() == 0) {
            return true;
        }
        return this.uuid.length() == 0;
    }

    public String toString() {
        return "DeviceIdentity(uuid=" + this.uuid + ", model=" + this.model + ", sku=" + this.sku + ", icon=" + this.icon + ')';
    }
}
