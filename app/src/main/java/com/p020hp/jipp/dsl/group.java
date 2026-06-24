package com.p020hp.jipp.dsl;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.DelimiterTag;
import com.p020hp.jipp.encoding.MutableAttributeGroup;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Deprecated(message = "Use IppPacket builders")
@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0086\u0002¨\u0006\f"}, m1293d2 = {"Lcom/hp/jipp/dsl/group;", "", "()V", "invoke", "Lcom/hp/jipp/encoding/AttributeGroup;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "func", "Lkotlin/Function1;", "Lcom/hp/jipp/encoding/MutableAttributeGroup;", "", "Lkotlin/ExtensionFunctionType;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class group {
    public static final group INSTANCE = new group();

    private group() {
    }

    public final AttributeGroup invoke(DelimiterTag tag, Function1<? super MutableAttributeGroup, Unit> func) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(func, "func");
        MutableAttributeGroup mutableAttributeGroupMutableGroupOf = AttributeGroup.INSTANCE.mutableGroupOf(tag, new Attribute[0]);
        func.invoke(mutableAttributeGroupMutableGroupOf);
        return mutableAttributeGroupMutableGroupOf.toGroup();
    }
}
