package com.p020hp.mobile.common.usb;

import com.p020hp.jipp.model.PrinterServiceType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/mobile/common/usb/IppUsbInterfaceMapping;", "", "mProductName", "", "interfaceWhiteList", "", "Lcom/hp/mobile/common/usb/Interface;", "(Ljava/lang/String;Ljava/util/List;)V", "getInterfaceWhiteList", "()Ljava/util/List;", "getMProductName", "()Ljava/lang/String;", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "common-lib_release"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class IppUsbInterfaceMapping {
    public final List<Interface> interfaceWhiteList;
    public final String mProductName;

    public IppUsbInterfaceMapping(String mProductName, List<Interface> interfaceWhiteList) {
        Intrinsics.checkNotNullParameter(mProductName, "mProductName");
        Intrinsics.checkNotNullParameter(interfaceWhiteList, "interfaceWhiteList");
        this.mProductName = mProductName;
        this.interfaceWhiteList = interfaceWhiteList;
    }

    public static IppUsbInterfaceMapping copy$default(IppUsbInterfaceMapping ippUsbInterfaceMapping, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ippUsbInterfaceMapping.mProductName;
        }
        if ((i & 2) != 0) {
            list = ippUsbInterfaceMapping.interfaceWhiteList;
        }
        return ippUsbInterfaceMapping.copy(str, list);
    }

    public final String getMProductName() {
        return this.mProductName;
    }

    public final List<Interface> component2() {
        return this.interfaceWhiteList;
    }

    public final IppUsbInterfaceMapping copy(String mProductName, List<Interface> interfaceWhiteList) {
        Intrinsics.checkNotNullParameter(mProductName, "mProductName");
        Intrinsics.checkNotNullParameter(interfaceWhiteList, "interfaceWhiteList");
        return new IppUsbInterfaceMapping(mProductName, interfaceWhiteList);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IppUsbInterfaceMapping)) {
            return false;
        }
        IppUsbInterfaceMapping ippUsbInterfaceMapping = (IppUsbInterfaceMapping) other;
        return Intrinsics.areEqual(this.mProductName, ippUsbInterfaceMapping.mProductName) && Intrinsics.areEqual(this.interfaceWhiteList, ippUsbInterfaceMapping.interfaceWhiteList);
    }

    public final List<Interface> getInterfaceWhiteList() {
        return this.interfaceWhiteList;
    }

    public final String getMProductName() {
        return this.mProductName;
    }

    public int hashCode() {
        return (this.mProductName.hashCode() * 31) + this.interfaceWhiteList.hashCode();
    }

    public String toString() {
        return "IppUsbInterfaceMapping(mProductName=" + this.mProductName + ", interfaceWhiteList=" + this.interfaceWhiteList + ')';
    }
}
