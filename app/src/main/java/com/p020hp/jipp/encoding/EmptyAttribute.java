package com.p020hp.jipp.encoding;

import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/jipp/encoding/EmptyAttribute;", "T", "", "Lcom/hp/jipp/encoding/AttributeImpl;", NamingTable.TAG, "", "tag", "Lcom/hp/jipp/encoding/OutOfBandTag;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/OutOfBandTag;)V", "getTag", "()Lcom/hp/jipp/encoding/OutOfBandTag;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class EmptyAttribute<T> extends AttributeImpl<T> {
    private final OutOfBandTag tag;

    public final OutOfBandTag getTag() {
        return this.tag;
    }

    public EmptyAttribute(String name, OutOfBandTag tag) {
        super(name, new EmptyAttributeType(name, tag), CollectionsKt.emptyList());
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.tag = tag;
    }
}
