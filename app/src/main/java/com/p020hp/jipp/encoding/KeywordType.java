package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0016\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0006\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "", NamingTable.TAG, "(Ljava/lang/String;)V", "toString", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class KeywordType extends Type<String> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<String> codec;

    public KeywordType(String name) {
        super(name, String.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordType$Set;", "Lcom/hp/jipp/encoding/KeywordType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "", NamingTable.TAG, "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends KeywordType implements AttributeSetType<String> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<String> mo417of(Iterable<? extends String> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<String> mo419of(String value, String... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public String toString() {
            return "KeywordType.Set(" + getName() + ')';
        }
    }

    @Override
    public String toString() {
        return "KeywordType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeywordType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<String> getCodec() {
            return KeywordType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.keyword;
        codec = new Codec<String>() {
            private final Class<String> cls = String.class;

            @Override
            public Class<String> getCls() {
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
                output.writeStringValue$jipp_core((String) value);
            }

            @Override
            public String readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return input.readString$jipp_core();
            }
        };
    }
}
