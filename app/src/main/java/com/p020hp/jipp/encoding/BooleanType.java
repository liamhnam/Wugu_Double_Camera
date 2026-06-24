package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\n\u000bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\f"}, m1293d2 = {"Lcom/hp/jipp/encoding/BooleanType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "coerce", "value", "", "(Ljava/lang/Object;)Ljava/lang/Boolean;", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class BooleanType extends Type<Boolean> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Boolean> codec;

    public BooleanType(String name) {
        super(name, Boolean.TYPE);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/BooleanType$Set;", "Lcom/hp/jipp/encoding/BooleanType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends BooleanType implements AttributeSetType<Boolean> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<Boolean> mo417of(Iterable<? extends Boolean> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<Boolean> mo419of(Boolean bool, Boolean[] boolArr) {
            return m424of(bool.booleanValue(), boolArr);
        }

        public Attribute<Boolean> m424of(boolean z, Boolean... values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, Boolean.valueOf(z), values);
        }

        @Override
        public String toString() {
            return "BooleanType.Set(" + getName() + ')';
        }
    }

    @Override
    public Boolean coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value instanceof Boolean)) {
            value = null;
        }
        return (Boolean) value;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/BooleanType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Boolean> getCodec() {
            return BooleanType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.booleanValue;
        codec = new Codec<Boolean>() {
            private final Class<Boolean> cls = Boolean.class;

            @Override
            public Class<Boolean> getCls() {
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
                output.writeByteValue$jipp_core(((Boolean) value).booleanValue() ? 1 : 0);
            }

            @Override
            public Boolean readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return Boolean.valueOf(input.readByteValue$jipp_core() != ((byte) 0));
            }
        };
    }
}
