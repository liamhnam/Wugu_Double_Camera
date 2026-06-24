package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\r\u000eB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u0007\u001a\u00020\u000bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u0007\u001a\u00020\f¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntOrIntRangeType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Lcom/hp/jipp/encoding/IntOrIntRange;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "coerce", "value", "", "of", "Lcom/hp/jipp/encoding/Attribute;", "", "Lkotlin/ranges/IntRange;", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class IntOrIntRangeType extends Type<IntOrIntRange> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<IntOrIntRange> codec;

    public IntOrIntRangeType(String name) {
        super(name, IntOrIntRange.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\n2\n\u0010\u000b\u001a\u00020\f\"\u00020\nJ-\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b2\u0006\u0010\t\u001a\u00020\r2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\u000e\"\u00020\r¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0005H\u0016¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntOrIntRangeType$Set;", "Lcom/hp/jipp/encoding/IntOrIntRangeType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Lcom/hp/jipp/encoding/IntOrIntRange;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "", "values", "", "Lkotlin/ranges/IntRange;", "", "(Lkotlin/ranges/IntRange;[Lkotlin/ranges/IntRange;)Lcom/hp/jipp/encoding/Attribute;", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends IntOrIntRangeType implements AttributeSetType<IntOrIntRange> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<IntOrIntRange> mo419of(IntOrIntRange value, IntOrIntRange... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<IntOrIntRange> mo417of(Iterable<? extends IntOrIntRange> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        public final Attribute<IntOrIntRange> m433of(IntRange value, IntRange... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new IntOrIntRange((IntRange) it.next()));
            }
            return mo417of((Iterable<? extends IntOrIntRange>) arrayList);
        }

        public final Attribute<IntOrIntRange> m432of(int value, int... values) {
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(Integer.valueOf(value)), (Iterable) ArraysKt.toList(values));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(new IntOrIntRange(((Number) it.next()).intValue()));
            }
            return mo417of((Iterable<? extends IntOrIntRange>) arrayList);
        }

        @Override
        public String toString() {
            return "IntOrIntRangeType.Set(" + getName() + ')';
        }
    }

    public final Attribute<IntOrIntRange> m430of(int value) {
        return mo418of(new IntOrIntRange(value));
    }

    public final Attribute<IntOrIntRange> m431of(IntRange value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return mo418of(new IntOrIntRange(value));
    }

    @Override
    public IntOrIntRange coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof Integer) {
            return new IntOrIntRange(((Number) value).intValue());
        }
        if (value instanceof IntRange) {
            return new IntOrIntRange((IntRange) value);
        }
        if (value instanceof IntOrIntRange) {
            return (IntOrIntRange) value;
        }
        return null;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntOrIntRangeType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/IntOrIntRange;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<IntOrIntRange> getCodec() {
            return IntOrIntRangeType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        codec = new Codec<IntOrIntRange>() {
            private final Class<IntOrIntRange> cls = IntOrIntRange.class;

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return false;
            }

            @Override
            public Class<IntOrIntRange> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((IntOrIntRange) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                IntOrIntRange intOrIntRange = (IntOrIntRange) value;
                if (Intrinsics.areEqual(intOrIntRange.getTag(), Tag.integerValue)) {
                    output.writeIntValue$jipp_core(intOrIntRange.getStart());
                    return;
                }
                output.writeShort(8);
                output.writeInt(intOrIntRange.getStart());
                output.writeInt(intOrIntRange.getEndInclusive());
            }

            @Override
            public IntOrIntRange readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                throw new IllegalArgumentException("Cannot read");
            }
        };
    }
}
