package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.util.ParseError;
import com.tom_roush.fontbox.ttf.NamingTable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0016\u0018\u0000 \u0011*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u0011B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\tJ\u0017\u0010\f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0005H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/encoding/CollectionType;", "T", "Lcom/hp/jipp/encoding/AttributeCollection;", "Lcom/hp/jipp/encoding/AttributeType;", NamingTable.TAG, "", "factory", "Lkotlin/Function1;", "Lcom/hp/jipp/encoding/UntypedCollection;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "", "(Ljava/lang/Object;)Lcom/hp/jipp/encoding/AttributeCollection;", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class CollectionType<T extends AttributeCollection> implements AttributeType<T> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<AttributeCollection> codec;
    private final Function1<UntypedCollection, T> factory;
    private final String name;

    public CollectionType(String name, Function1<? super UntypedCollection, ? extends T> factory) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(factory, "factory");
        this.name = name;
        this.factory = factory;
    }

    @Override
    public Attribute<T> coerce(Attribute<?> attribute) {
        Intrinsics.checkNotNullParameter(attribute, "attribute");
        return AttributeType.DefaultImpls.coerce(this, attribute);
    }

    @Override
    public Attribute<T> empty(OutOfBandTag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return AttributeType.DefaultImpls.empty(this, tag);
    }

    @Override
    public Attribute<T> noValue() {
        return AttributeType.DefaultImpls.noValue(this);
    }

    @Override
    public Attribute<T> mo418of(T value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return AttributeType.DefaultImpls.m423of(this, value);
    }

    @Override
    public Attribute<T> unknown() {
        return AttributeType.DefaultImpls.unknown(this);
    }

    @Override
    public Attribute<T> unsupported() {
        return AttributeType.DefaultImpls.unsupported(this);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public T coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value instanceof UntypedCollection)) {
            value = null;
        }
        UntypedCollection untypedCollection = (UntypedCollection) value;
        if (untypedCollection != null) {
            return this.factory.invoke(untypedCollection);
        }
        return null;
    }

    public String toString() {
        return "CollectionType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/CollectionType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/AttributeCollection;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<AttributeCollection> getCodec() {
            return CollectionType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.beginCollection;
        codec = new Codec<AttributeCollection>() {
            private final Class<AttributeCollection> cls = AttributeCollection.class;

            @Override
            public Class<AttributeCollection> getCls() {
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
                output.writeCollectionAttributes$jipp_core(((AttributeCollection) value).getAttributes());
            }

            @Override
            public AttributeCollection readValue(IppInputStream input, ValueTag startTag) throws ParseError {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                input.skipValueBytes$jipp_core();
                return new UntypedCollection(input.readCollectionAttributes$jipp_core());
            }
        };
    }
}
