package com.faceunity.core.entity;

import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0001H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, m1293d2 = {"Lcom/faceunity/core/entity/FUVisibleBundleData;", "Lcom/faceunity/core/entity/FUBundleData;", "path", "", "visibleList", "", NamingTable.TAG, "(Ljava/lang/String;[ILjava/lang/String;)V", "getVisibleList", "()[I", "clone", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUVisibleBundleData extends FUBundleData {
    private final int[] visibleList;

    public FUVisibleBundleData(String str, int[] iArr, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            iArr = null;
        }
        this(str, iArr, (i & 4) != 0 ? FUBundleData.INSTANCE.getFileName(str) : str2);
    }

    public final int[] getVisibleList() {
        return this.visibleList;
    }

    public FUVisibleBundleData(String path, int[] iArr, String name) {
        super(path, name);
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(name, "name");
        this.visibleList = iArr;
    }

    @Override
    public FUBundleData clone() {
        return new FUVisibleBundleData(getPath(), this.visibleList, getName());
    }
}
