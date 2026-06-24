package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.util.ParseError;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.net.URI;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0016\u0018\u0000 \u00072\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0007\bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0004H\u0016¨\u0006\t"}, m1293d2 = {"Lcom/hp/jipp/encoding/UriType;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", "Ljava/net/URI;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class UriType extends Type<URI> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<URI> codec;

    public UriType(String name) {
        super(name, URI.class);
        Intrinsics.checkNotNullParameter(name, "name");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0005H\u0016¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/UriType$Set;", "Lcom/hp/jipp/encoding/UriType;", "Lcom/hp/jipp/encoding/AttributeSetType;", "Ljava/net/URI;", NamingTable.TAG, "", "(Ljava/lang/String;)V", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Set extends UriType implements AttributeSetType<URI> {
        public Set(String name) {
            super(name);
            Intrinsics.checkNotNullParameter(name, "name");
        }

        @Override
        public Attribute<URI> mo417of(Iterable<? extends URI> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        @Override
        public Attribute<URI> mo419of(URI value, URI... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public String toString() {
            return "UriType.Set(" + getName() + ')';
        }
    }

    @Override
    public String toString() {
        return "UriType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/UriType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Ljava/net/URI;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<URI> getCodec() {
            return UriType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.uri;
        codec = new Codec<URI>() {
            private final Class<URI> cls = URI.class;

            @Override
            public Class<URI> getCls() {
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
                String string = ((URI) value).toString();
                Intrinsics.checkNotNullExpressionValue(string, "it.toString()");
                output.writeStringValue$jipp_core(string);
            }

            @Override
            public URI readValue(IppInputStream input, ValueTag startTag) throws ParseError {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                String string$jipp_core = input.readString$jipp_core();
                try {
                    URI uriCreate = URI.create(string$jipp_core);
                    Intrinsics.checkNotNullExpressionValue(uriCreate, "try {\n                UR…String\", e)\n            }");
                    return uriCreate;
                } catch (IllegalArgumentException e) {
                    throw new ParseError("Could not parse URI " + string$jipp_core, e);
                }
            }
        };
    }
}
