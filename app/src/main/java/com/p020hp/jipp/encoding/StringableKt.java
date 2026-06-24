package com.p020hp.jipp.encoding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0010\u001e\n\u0000\u001a\u0014\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0000¨\u0006\u0003"}, m1293d2 = {"stringinate", "", "", "jipp-core"}, m1294k = 2, m1295mv = {1, 4, 0})
public final class StringableKt {
    public static final List<?> stringinate(Collection<?> stringinate) {
        Intrinsics.checkNotNullParameter(stringinate, "$this$stringinate");
        Collection<?> collection = stringinate;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection, 10));
        for (Object objAsString : collection) {
            if (objAsString instanceof Stringable) {
                objAsString = ((Stringable) objAsString).asString();
            }
            arrayList.add(objAsString);
        }
        return arrayList;
    }
}
