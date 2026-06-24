package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\u0006H\u0016J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0006H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/encoding/OtherString;", "Lcom/hp/jipp/encoding/TaggedValue;", "Lcom/hp/jipp/encoding/Stringable;", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "value", "", "(Lcom/hp/jipp/encoding/ValueTag;Ljava/lang/String;)V", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "getValue", "()Ljava/lang/String;", "asString", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class OtherString implements TaggedValue, Stringable {
    private final ValueTag tag;
    private final String value;

    public static OtherString copy$default(OtherString otherString, ValueTag valueTag, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            valueTag = otherString.getTag();
        }
        if ((i & 2) != 0) {
            str = otherString.getValue();
        }
        return otherString.copy(valueTag, str);
    }

    public final ValueTag component1() {
        return getTag();
    }

    public final String component2() {
        return getValue();
    }

    public final OtherString copy(ValueTag tag, String value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        return new OtherString(tag, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OtherString)) {
            return false;
        }
        OtherString otherString = (OtherString) other;
        return Intrinsics.areEqual(getTag(), otherString.getTag()) && Intrinsics.areEqual(getValue(), otherString.getValue());
    }

    public int hashCode() {
        ValueTag tag = getTag();
        int iHashCode = (tag != null ? tag.hashCode() : 0) * 31;
        String value = getValue();
        return iHashCode + (value != null ? value.hashCode() : 0);
    }

    public OtherString(ValueTag tag, String value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        this.tag = tag;
        this.value = value;
    }

    @Override
    public ValueTag getTag() {
        return this.tag;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "\"" + getValue() + "\" (" + getTag() + ')';
    }

    @Override
    public String asString() {
        return getValue();
    }
}
