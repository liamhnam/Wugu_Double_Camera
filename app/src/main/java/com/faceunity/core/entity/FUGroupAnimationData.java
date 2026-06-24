package com.faceunity.core.entity;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BM\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\b\u0012\u0018\b\u0002\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\b¢\u0006\u0002\u0010\nJ\b\u0010\u000e\u001a\u00020\u0000H\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0001H\u0016R!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0007j\b\u0012\u0004\u0012\u00020\u0003`\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0012"}, m1293d2 = {"Lcom/faceunity/core/entity/FUGroupAnimationData;", "Lcom/faceunity/core/entity/FUAnimationData;", "animation", "Lcom/faceunity/core/entity/FUBundleData;", NamingTable.TAG, "", "subAnimations", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "subProps", "(Lcom/faceunity/core/entity/FUBundleData;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getSubAnimations", "()Ljava/util/ArrayList;", "getSubProps", "clone", "isEqual", "", "data", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUGroupAnimationData extends FUAnimationData {
    private final ArrayList<FUBundleData> subAnimations;
    private final ArrayList<FUBundleData> subProps;

    public FUGroupAnimationData(FUBundleData fUBundleData) {
        this(fUBundleData, null, null, null, 14, null);
    }

    public FUGroupAnimationData(FUBundleData fUBundleData, String str) {
        this(fUBundleData, str, null, null, 12, null);
    }

    public FUGroupAnimationData(FUBundleData fUBundleData, String str, ArrayList<FUBundleData> arrayList) {
        this(fUBundleData, str, arrayList, null, 8, null);
    }

    public FUGroupAnimationData(FUBundleData fUBundleData, String str, ArrayList arrayList, ArrayList arrayList2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fUBundleData, (i & 2) != 0 ? fUBundleData.getName() : str, (i & 4) != 0 ? new ArrayList() : arrayList, (i & 8) != 0 ? new ArrayList() : arrayList2);
    }

    public final ArrayList<FUBundleData> getSubAnimations() {
        return this.subAnimations;
    }

    public final ArrayList<FUBundleData> getSubProps() {
        return this.subProps;
    }

    public FUGroupAnimationData(FUBundleData animation, String name, ArrayList<FUBundleData> subAnimations, ArrayList<FUBundleData> subProps) {
        super(animation, name);
        Intrinsics.checkParameterIsNotNull(animation, "animation");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(subAnimations, "subAnimations");
        Intrinsics.checkParameterIsNotNull(subProps, "subProps");
        this.subAnimations = subAnimations;
        this.subProps = subProps;
    }

    @Override
    public FUGroupAnimationData clone() {
        FUGroupAnimationData fUGroupAnimationData = new FUGroupAnimationData(getAnimation().clone(), getName(), null, null, 12, null);
        Iterator<T> it = this.subAnimations.iterator();
        while (it.hasNext()) {
            fUGroupAnimationData.subAnimations.add(((FUBundleData) it.next()).clone());
        }
        Iterator<T> it2 = this.subProps.iterator();
        while (it2.hasNext()) {
            fUGroupAnimationData.subProps.add(((FUBundleData) it2.next()).clone());
        }
        return fUGroupAnimationData;
    }

    @Override
    public boolean isEqual(FUAnimationData data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        if (!(data instanceof FUGroupAnimationData) || (!Intrinsics.areEqual(getAnimation().getPath(), data.getAnimation().getPath())) || (!Intrinsics.areEqual(getAnimation().getName(), data.getAnimation().getName()))) {
            return false;
        }
        FUGroupAnimationData fUGroupAnimationData = (FUGroupAnimationData) data;
        ArrayList<FUBundleData> arrayList = fUGroupAnimationData.subAnimations;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator<T> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((FUBundleData) it.next()).getPath());
        }
        Set set = CollectionsKt.toSet(arrayList2);
        ArrayList<FUBundleData> arrayList3 = fUGroupAnimationData.subProps;
        ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
        Iterator<T> it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((FUBundleData) it2.next()).getPath());
        }
        Set set2 = CollectionsKt.toSet(arrayList4);
        Iterator<T> it3 = this.subAnimations.iterator();
        while (it3.hasNext()) {
            if (!set.contains(((FUBundleData) it3.next()).getPath())) {
                return false;
            }
        }
        Iterator<T> it4 = this.subProps.iterator();
        while (it4.hasNext()) {
            if (!set2.contains(((FUBundleData) it4.next()).getPath())) {
                return false;
            }
        }
        return true;
    }
}
