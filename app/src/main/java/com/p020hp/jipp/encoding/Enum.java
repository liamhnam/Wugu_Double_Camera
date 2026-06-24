package com.p020hp.jipp.encoding;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\bH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/Enum;", "", "()V", "code", "", "getCode", "()I", NamingTable.TAG, "", "getName", "()Ljava/lang/String;", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public abstract class Enum {

    public static final Companion INSTANCE = new Companion(null);

    public abstract int getCode();

    public abstract String getName();

    public String toString() {
        return getName() + '(' + getCode() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0000\u0010\u0006*\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00060\t¨\u0006\n"}, m1293d2 = {"Lcom/hp/jipp/encoding/Enum$Companion;", "", "()V", "toCodeMap", "", "", "T", "Lcom/hp/jipp/encoding/Enum;", "nameCodes", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <T extends Enum> Map<Integer, T> toCodeMap(Iterable<? extends T> nameCodes) {
            Intrinsics.checkNotNullParameter(nameCodes, "nameCodes");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(nameCodes, 10));
            for (T t : nameCodes) {
                arrayList.add(TuplesKt.m1300to(Integer.valueOf(t.getCode()), t));
            }
            return MapsKt.toMap(arrayList);
        }
    }
}
