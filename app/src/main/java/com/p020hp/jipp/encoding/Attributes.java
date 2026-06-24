package com.p020hp.jipp.encoding;

import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/encoding/Attributes;", "", "()V", "noValue", "Lcom/hp/jipp/encoding/Attribute;", NamingTable.TAG, "", "unknown", "unsupported", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class Attributes {
    public static final Attributes INSTANCE = new Attributes();

    private Attributes() {
    }

    @JvmStatic
    public static final Attribute<?> unknown(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new EmptyAttribute(name, Tag.unknown);
    }

    @JvmStatic
    public static final Attribute<?> unsupported(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new EmptyAttribute(name, Tag.unsupported);
    }

    @JvmStatic
    public static final Attribute<?> noValue(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new EmptyAttribute(name, Tag.noValue);
    }
}
