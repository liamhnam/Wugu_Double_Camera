package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\n\u000bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\f"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "coerce", "value", "", "(Ljava/lang/Object;)Ljava/lang/Integer;", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class IntType extends Type<Integer> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Integer> codec;

    public IntType(String name) {
        super(name, Integer.TYPE);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntType$Set;", "Lcom/hp/jipp/encoding/IntType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends IntType implements AttributeSetType<Integer> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        public Attribute<Integer> m434of(int i, Integer... values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, Integer.valueOf(i), values);
        }

        @Override
        public Attribute<Integer> mo417of(Iterable<? extends Integer> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<Integer> mo419of(Integer num, Integer[] numArr) {
            return m434of(num.intValue(), numArr);
        }

        @Override
        public String toString() {
            return "IntType.Set(" + getName() + ')';
        }
    }

    @Override
    public Integer coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value instanceof Integer)) {
            value = null;
        }
        return (Integer) value;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/IntType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Integer> getCodec() {
            return IntType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.integerValue;
        codec = new Codec<Integer>() {
            private final Class<Integer> cls = Integer.class;

            @Override
            public Class<Integer> getCls() {
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
                output.writeIntValue$jipp_core(((Integer) value).intValue());
            }

            @Override
            public Integer readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return Integer.valueOf(input.readIntValue$jipp_core());
            }
        };
    }
}
