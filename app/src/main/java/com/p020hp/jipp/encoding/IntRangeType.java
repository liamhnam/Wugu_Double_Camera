package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.util.ParseError;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0006\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntRangeType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lkotlin/ranges/IntRange;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class IntRangeType extends Type<IntRange> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<IntRange> codec;

    public IntRangeType(String name) {
        super(name, IntRange.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntRangeType$Set;", "Lcom/hp/jipp/encoding/IntRangeType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lkotlin/ranges/IntRange;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends IntRangeType implements AttributeSetType<IntRange> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<IntRange> mo417of(Iterable<? extends IntRange> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<IntRange> mo419of(IntRange value, IntRange... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public String toString() {
            return "IntRangeType.Set(" + getName() + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntRangeType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lkotlin/ranges/IntRange;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<IntRange> getCodec() {
            return IntRangeType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.rangeOfInteger;
        codec = new Codec<IntRange>() {
            private final Class<IntRange> cls = IntRange.class;

            @Override
            public Class<IntRange> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return valueTag;
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return Intrinsics.areEqual(valueTag, tag);
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                IntRange intRange = (IntRange) value;
                output.writeShort(8);
                output.writeInt(intRange.getFirst());
                output.writeInt(intRange.getLast());
            }

            @Override
            public IntRange readValue(IppInputStream input, ValueTag startTag) throws ParseError {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                input.takeLength$jipp_core(8);
                return new IntRange(input.readInt(), input.readInt());
            }
        };
    }
}
