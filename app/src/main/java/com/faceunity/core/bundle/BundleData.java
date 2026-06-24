package com.faceunity.core.bundle;

import com.p020hp.jipp.model.PrinterServiceType;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001B3\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000fR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006\u001f"}, m1293d2 = {"Lcom/faceunity/core/bundle/BundleData;", "", NamingTable.TAG, "", "path", "handle", "", "isSupportARMode", "", "isSupportFollowBodyMode", "(Ljava/lang/String;Ljava/lang/String;IZZ)V", "getHandle", "()I", "setHandle", "(I)V", "()Z", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPath", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "equals", "other", "hashCode", "toString", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BundleData {
    private int handle;
    private final boolean isSupportARMode;
    private final boolean isSupportFollowBodyMode;
    private String name;
    private final String path;

    public BundleData(String str, String str2, int i) {
        this(str, str2, i, false, false, 24, null);
    }

    public BundleData(String str, String str2, int i, boolean z) {
        this(str, str2, i, z, false, 16, null);
    }

    public static BundleData copy$default(BundleData bundleData, String str, String str2, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bundleData.name;
        }
        if ((i2 & 2) != 0) {
            str2 = bundleData.path;
        }
        String str3 = str2;
        if ((i2 & 4) != 0) {
            i = bundleData.handle;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            z = bundleData.isSupportARMode;
        }
        boolean z3 = z;
        if ((i2 & 16) != 0) {
            z2 = bundleData.isSupportFollowBodyMode;
        }
        return bundleData.copy(str, str3, i3, z3, z2);
    }

    public final String getName() {
        return this.name;
    }

    public final String getPath() {
        return this.path;
    }

    public final int getHandle() {
        return this.handle;
    }

    public final boolean getIsSupportARMode() {
        return this.isSupportARMode;
    }

    public final boolean getIsSupportFollowBodyMode() {
        return this.isSupportFollowBodyMode;
    }

    public final BundleData copy(String name, String path, int handle, boolean isSupportARMode, boolean isSupportFollowBodyMode) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        return new BundleData(name, path, handle, isSupportARMode, isSupportFollowBodyMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BundleData)) {
            return false;
        }
        BundleData bundleData = (BundleData) other;
        return Intrinsics.areEqual(this.name, bundleData.name) && Intrinsics.areEqual(this.path, bundleData.path) && this.handle == bundleData.handle && this.isSupportARMode == bundleData.isSupportARMode && this.isSupportFollowBodyMode == bundleData.isSupportFollowBodyMode;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.path;
        int iHashCode2 = (((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.handle)) * 31;
        boolean z = this.isSupportARMode;
        ?? r1 = z;
        if (z) {
            r1 = 1;
        }
        int i = (iHashCode2 + r1) * 31;
        boolean z2 = this.isSupportFollowBodyMode;
        return i + (z2 ? 1 : z2);
    }

    public String toString() {
        return "BundleData(name=" + this.name + ", path=" + this.path + ", handle=" + this.handle + ", isSupportARMode=" + this.isSupportARMode + ", isSupportFollowBodyMode=" + this.isSupportFollowBodyMode + ")";
    }

    public BundleData(String name, String path, int i, boolean z, boolean z2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        this.name = name;
        this.path = path;
        this.handle = i;
        this.isSupportARMode = z;
        this.isSupportFollowBodyMode = z2;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final int getHandle() {
        return this.handle;
    }

    public final void setHandle(int i) {
        this.handle = i;
    }

    public final boolean isSupportARMode() {
        return this.isSupportARMode;
    }

    public BundleData(String str, String str2, int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, (i2 & 8) != 0 ? true : z, (i2 & 16) != 0 ? true : z2);
    }

    public final boolean isSupportFollowBodyMode() {
        return this.isSupportFollowBodyMode;
    }
}
