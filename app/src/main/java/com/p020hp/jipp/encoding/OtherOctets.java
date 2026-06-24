package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.PrinterServiceType;
import com.p020hp.jipp.util.BytesKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/encoding/OtherOctets;", "Lcom/hp/jipp/encoding/TaggedValue;", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "value", "", "(Lcom/hp/jipp/encoding/ValueTag;[B)V", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "getValue", "()[B", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class OtherOctets implements TaggedValue {
    private static final int HASH_PRIME = 31;
    private final ValueTag tag;
    private final byte[] value;

    public static OtherOctets copy$default(OtherOctets otherOctets, ValueTag valueTag, byte[] bArr, int i, Object obj) {
        if ((i & 1) != 0) {
            valueTag = otherOctets.getTag();
        }
        if ((i & 2) != 0) {
            bArr = otherOctets.getValue();
        }
        return otherOctets.copy(valueTag, bArr);
    }

    public final ValueTag component1() {
        return getTag();
    }

    public final byte[] component2() {
        return getValue();
    }

    public final OtherOctets copy(ValueTag tag, byte[] value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        return new OtherOctets(tag, value);
    }

    public OtherOctets(ValueTag tag, byte[] value) {
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
    public byte[] getValue() {
        return this.value;
    }

    public boolean equals(Object other) {
        if (!(other instanceof OtherOctets)) {
            return false;
        }
        OtherOctets otherOctets = (OtherOctets) other;
        return Intrinsics.areEqual(otherOctets.getTag(), getTag()) && Arrays.equals(getValue(), otherOctets.getValue());
    }

    public int hashCode() {
        return (getTag().hashCode() * 31) + Arrays.hashCode(getValue());
    }

    public String toString() {
        return BytesKt.toHexString(getValue()) + " (" + getTag() + ')';
    }
}
