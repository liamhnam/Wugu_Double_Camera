package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeSetType;
import com.p020hp.jipp.util.PrettyPrintable;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u000b\f\rJ\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u001c\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeCollection;", "Lcom/hp/jipp/util/PrettyPrintable;", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "print", "", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/jipp/util/PrettyPrinter;", "Converter", "SetType", "Type", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface AttributeCollection extends PrettyPrintable {
    List<Attribute<?>> getAttributes();

    @Override
    void print(PrettyPrinter printer);

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static void print(AttributeCollection attributeCollection, PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            PrettyPrinter.open$default(printer, PrettyPrinter.OBJECT, null, 2, null);
            printer.addAll(attributeCollection.getAttributes());
            printer.close();
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeCollection$Type;", "T", "Lcom/hp/jipp/encoding/AttributeCollection;", "Lcom/hp/jipp/encoding/AttributeTypeImpl;", NamingTable.TAG, "", "converter", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/AttributeCollection$Converter;)V", "coerce", "value", "", "(Ljava/lang/Object;)Lcom/hp/jipp/encoding/AttributeCollection;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Type<T extends AttributeCollection> extends com.p020hp.jipp.encoding.Type<T> {
        private final Converter<T> converter;

        public Type(String name, Converter<T> converter) {
            super(name, converter.getCls());
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(converter, "converter");
            this.converter = converter;
        }

        @Override
        public T coerce(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            T t = (T) super.coerce(value);
            if (t != null) {
                return t;
            }
            if (value instanceof AttributeCollection) {
                return (T) this.converter.convert(((AttributeCollection) value).getAttributes());
            }
            return null;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0017\u0010\u000b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\f\u001a\u00020\rH\u0016¢\u0006\u0002\u0010\u000eR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeCollection$SetType;", "T", "Lcom/hp/jipp/encoding/AttributeCollection;", "Lcom/hp/jipp/encoding/AttributeSetType;", NamingTable.TAG, "", "converter", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/AttributeCollection$Converter;)V", "getName", "()Ljava/lang/String;", "coerce", "value", "", "(Ljava/lang/Object;)Lcom/hp/jipp/encoding/AttributeCollection;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class SetType<T extends AttributeCollection> implements AttributeSetType<T> {
        private final Converter<T> converter;
        private final String name;

        public SetType(String name, Converter<T> converter) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(converter, "converter");
            this.name = name;
            this.converter = converter;
        }

        @Override
        public Attribute<T> coerce(Attribute<?> attribute) {
            Intrinsics.checkNotNullParameter(attribute, "attribute");
            return AttributeSetType.DefaultImpls.coerce(this, attribute);
        }

        @Override
        public Attribute<T> empty(OutOfBandTag tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return AttributeSetType.DefaultImpls.empty(this, tag);
        }

        @Override
        public Attribute<T> noValue() {
            return AttributeSetType.DefaultImpls.noValue(this);
        }

        @Override
        public Attribute<T> mo418of(T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return AttributeSetType.DefaultImpls.m421of(this, value);
        }

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

        @Override
        public Attribute<T> unknown() {
            return AttributeSetType.DefaultImpls.unknown(this);
        }

        @Override
        public Attribute<T> unsupported() {
            return AttributeSetType.DefaultImpls.unsupported(this);
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public T coerce(Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            if (this.converter.getCls().isInstance(value)) {
                return (T) value;
            }
            if (value instanceof AttributeCollection) {
                return (T) this.converter.convert(((AttributeCollection) value).getAttributes());
            }
            return null;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003J:\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\t\"\b\b\u0001\u0010\u0001*\u00020\u00032\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00010\rH\u0016J\u001f\u0010\u000e\u001a\u00028\u00002\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000bH&¢\u0006\u0002\u0010\u000fJ:\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u000b\"\b\b\u0001\u0010\u0001*\u00020\u00032\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00010\rH\u0016J9\u0010\u0011\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0001\u0010\u0001*\u00020\u00032\u0010\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00010\rH\u0016¢\u0006\u0002\u0010\u0012R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "T", "Lcom/hp/jipp/encoding/AttributeCollection;", "", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "coerced", "Lcom/hp/jipp/encoding/Attribute;", "attributes", "", "type", "Lcom/hp/jipp/encoding/AttributeType;", "convert", "(Ljava/util/List;)Lcom/hp/jipp/encoding/AttributeCollection;", "extractAll", "extractOne", "(Ljava/util/List;Lcom/hp/jipp/encoding/AttributeType;)Ljava/lang/Object;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public interface Converter<T extends AttributeCollection> {
        <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type);

        T convert(List<? extends Attribute<?>> attributes);

        <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type);

        <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type);

        Class<T> getCls();

        @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
        public static final class DefaultImpls {
            public static <T_I1 extends AttributeCollection, T> T extractOne(Converter<T_I1> converter, List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                Attribute<T> attributeCoerced = converter.coerced(attributes, type);
                if (attributeCoerced == null || attributeCoerced.size() == 0) {
                    return null;
                }
                return attributeCoerced.get(0);
            }

            public static <T_I1 extends AttributeCollection, T> List<T> extractAll(Converter<T_I1> converter, List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                Attribute<T> attributeCoerced = converter.coerced(attributes, type);
                Attribute<T> attribute = null;
                if (attributeCoerced != null) {
                    if (attributeCoerced.size() == 0) {
                        attributeCoerced = null;
                    }
                    attribute = attributeCoerced;
                }
                return attribute;
            }

            public static <T_I1 extends AttributeCollection, T> Attribute<T> coerced(Converter<T_I1> converter, List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                T next;
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                Iterator<T> it = attributes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (Intrinsics.areEqual(((Attribute) next).getName(), type.getName())) {
                        break;
                    }
                }
                Attribute<?> attribute = (Attribute) next;
                if (attribute != null) {
                    return type.coerce(attribute);
                }
                return null;
            }
        }
    }
}
