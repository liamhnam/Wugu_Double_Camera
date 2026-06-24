package com.p020hp.jipp.encoding;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bB\u000f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nB\u001f\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0013\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010 \u001a\u00020\u0006H\u0016J\b\u0010!\u001a\u00020\"H\u0016R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0002\u001a\u00020\u00038FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0014\u0010\u0017\u001a\u00020\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\t\u001a\u00020\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006#"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntOrIntRange;", "Lcom/hp/jipp/encoding/TaggedValue;", "range", "Lkotlin/ranges/IntRange;", "(Lkotlin/ranges/IntRange;)V", "start", "", "endInclusive", "(II)V", "value", "(I)V", "simpleInt", "", "(IIZ)V", "getEndInclusive", "()I", "getRange", "()Lkotlin/ranges/IntRange;", "range$delegate", "Lkotlin/Lazy;", "getSimpleInt", "()Z", "getStart", "tag", "Lcom/hp/jipp/encoding/ValueTag;", "getTag", "()Lcom/hp/jipp/encoding/ValueTag;", "", "getValue", "()Ljava/lang/Object;", "equals", "other", "hashCode", "toString", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IntOrIntRange implements TaggedValue {
    private final int endInclusive;

    private final Lazy range;
    private final boolean simpleInt;
    private final int start;

    public final IntRange getRange() {
        return (IntRange) this.range.getValue();
    }

    private IntOrIntRange(int i, int i2, boolean z) {
        this.start = i;
        this.endInclusive = i2;
        this.simpleInt = z;
        this.range = LazyKt.lazy(new Function0<IntRange>() {
            {
                super(0);
            }

            @Override
            public final IntRange invoke() {
                return new IntRange(this.this$0.getStart(), this.this$0.getEndInclusive());
            }
        });
    }

    public final int getStart() {
        return this.start;
    }

    public final int getEndInclusive() {
        return this.endInclusive;
    }

    public final boolean getSimpleInt() {
        return this.simpleInt;
    }

    public IntOrIntRange(IntRange range) {
        this(range.getFirst(), range.getLast(), false);
        Intrinsics.checkNotNullParameter(range, "range");
    }

    public IntOrIntRange(int i, int i2) {
        this(i, i2, false);
    }

    public IntOrIntRange(int i) {
        this(i, i, true);
    }

    @Override
    public ValueTag getTag() {
        return this.simpleInt ? Tag.integerValue : Tag.rangeOfInteger;
    }

    @Override
    public Object getValue() {
        return this.simpleInt ? Integer.valueOf(this.start) : getRange();
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof IntOrIntRange) {
            return Intrinsics.areEqual(getValue(), ((IntOrIntRange) other).getValue());
        }
        return false;
    }

    public int hashCode() {
        return getValue().hashCode();
    }

    public String toString() {
        return getValue().toString();
    }
}
