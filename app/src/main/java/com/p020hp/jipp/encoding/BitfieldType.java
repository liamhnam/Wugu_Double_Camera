package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/jipp/encoding/BitfieldType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "", "(Ljava/lang/Object;)Ljava/lang/Integer;", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class BitfieldType extends Type<Integer> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Long> codec;
    private final String name;

    @Override
    public String getName() {
        return this.name;
    }

    public BitfieldType(String name) {
        super(name, Integer.TYPE);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @Override
    public Integer coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof UntypedEnum) {
            return Integer.valueOf(((UntypedEnum) value).getCode());
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        System.out.println((Object) ("Value: " + value + ", " + value.getClass()));
        return (Integer) super.coerce(value);
    }

    @Override
    public String toString() {
        return "BitwiseType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/BitfieldType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Long> getCodec() {
            return BitfieldType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.enumValue;
        codec = new Codec<Long>() {
            private final Class<Long> cls = Long.class;

            @Override
            public Class<Long> getCls() {
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
                output.writeIntValue$jipp_core((int) ((Long) value).longValue());
            }

            @Override
            public Long readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return Long.valueOf(input.readIntValue$jipp_core());
            }
        };
    }
}
