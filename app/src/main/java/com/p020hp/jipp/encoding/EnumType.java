package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.Codec;
import com.p020hp.jipp.encoding.Enum;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u0017*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0002\u0017\u0018B0\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\u000bJ\u0017\u0010\u0010\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0006\u0010\u0011\u001a\u00020\bJ\b\u0010\u0016\u001a\u00020\u0005H\u0016R,\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0004\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, m1293d2 = {"Lcom/hp/jipp/encoding/EnumType;", "T", "Lcom/hp/jipp/encoding/Enum;", "Lcom/hp/jipp/encoding/AttributeType;", NamingTable.TAG, "", "factory", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "code", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getFactory", "()Lkotlin/jvm/functions/Function1;", "getName", "()Ljava/lang/String;", "coerce", "value", "", "(Ljava/lang/Object;)Lcom/hp/jipp/encoding/Enum;", "of", "Lcom/hp/jipp/encoding/Attribute;", "toString", "Companion", "Set", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public class EnumType<T extends Enum> implements AttributeType<T> {

    public static final Companion INSTANCE = new Companion(null);
    private static final Codec<Enum> codec;
    private final Function1<Integer, T> factory;
    private final String name;

    public EnumType(String name, Function1<? super Integer, ? extends T> factory) {
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

    public final Function1<Integer, T> getFactory() {
        return this.factory;
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B0\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u0005\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\fJ \u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00020\u0011\"\u00020\tJ\b\u0010\u0012\u001a\u00020\u0006H\u0016¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/encoding/EnumType$Set;", "T", "Lcom/hp/jipp/encoding/Enum;", "Lcom/hp/jipp/encoding/EnumType;", "Lcom/hp/jipp/encoding/AttributeSetType;", NamingTable.TAG, "", "factory", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "code", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "values", "", "toString", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static class Set<T extends Enum> extends EnumType<T> implements AttributeSetType<T> {
        @Override
        public Attribute<T> mo419of(T value, T... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m422of(this, value, values);
        }

        @Override
        public Attribute<T> mo417of(Iterable<? extends T> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return AttributeSetType.DefaultImpls.m420of((AttributeSetType) this, (Iterable) values);
        }

        public Set(String name, Function1<? super Integer, ? extends T> factory) {
            super(name, factory);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(factory, "factory");
        }

        public final Attribute<T> m428of(int value, int... values) {
            Intrinsics.checkNotNullParameter(values, "values");
            List listPlus = CollectionsKt.plus((Collection) CollectionsKt.listOf(Integer.valueOf(value)), (Iterable) ArraysKt.toList(values));
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPlus, 10));
            Iterator it = listPlus.iterator();
            while (it.hasNext()) {
                arrayList.add(getFactory().invoke(Integer.valueOf(((Number) it.next()).intValue())));
            }
            return mo417of((Iterable) arrayList);
        }

        @Override
        public String toString() {
            return "EnumType.Set(" + getName() + ')';
        }
    }

    public final Attribute<T> m426of(int value) {
        return mo418of((Object) this.factory.invoke(Integer.valueOf(value)));
    }

    @Override
    public T coerce(Object value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (value instanceof UntypedEnum) {
            return this.factory.invoke(Integer.valueOf(((UntypedEnum) value).getCode()));
        }
        if (value instanceof Enum) {
            return this.factory.invoke(Integer.valueOf(((Enum) value).getCode()));
        }
        if (value instanceof Integer) {
            return this.factory.invoke(value);
        }
        return null;
    }

    public String toString() {
        return "EnumType(" + getName() + ')';
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, m1293d2 = {"Lcom/hp/jipp/encoding/EnumType$Companion;", "", "()V", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/Enum;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<Enum> getCodec() {
            return EnumType.codec;
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.enumValue;
        codec = new Codec<Enum>() {
            private final Class<Enum> cls = Enum.class;

            @Override
            public Class<Enum> getCls() {
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
                output.writeIntValue$jipp_core(((Enum) value).getCode());
            }

            @Override
            public Enum readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return new UntypedEnum(input.readIntValue$jipp_core());
            }
        };
    }
}
