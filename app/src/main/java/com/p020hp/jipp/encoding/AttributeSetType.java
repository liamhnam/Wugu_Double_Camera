package com.p020hp.jipp.encoding;

import com.p020hp.jipp.encoding.AttributeType;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J/\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00028\u00002\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00028\u00000\b\"\u00028\u0000H\u0016¢\u0006\u0002\u0010\tJ\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016¨\u0006\u000b"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeSetType;", "T", "", "Lcom/hp/jipp/encoding/AttributeType;", "of", "Lcom/hp/jipp/encoding/Attribute;", "value", "values", "", "(Ljava/lang/Object;[Ljava/lang/Object;)Lcom/hp/jipp/encoding/Attribute;", "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface AttributeSetType<T> extends AttributeType<T> {
    Attribute<T> mo417of(Iterable<? extends T> values);

    Attribute<T> mo419of(T value, T... values);

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static <T> Attribute<T> coerce(AttributeSetType<T> attributeSetType, Attribute<?> attribute) {
            Intrinsics.checkNotNullParameter(attribute, "attribute");
            return AttributeType.DefaultImpls.coerce(attributeSetType, attribute);
        }

        public static <T> Attribute<T> empty(AttributeSetType<T> attributeSetType, OutOfBandTag tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return AttributeType.DefaultImpls.empty(attributeSetType, tag);
        }

        public static <T> Attribute<T> noValue(AttributeSetType<T> attributeSetType) {
            return AttributeType.DefaultImpls.noValue(attributeSetType);
        }

        public static <T> Attribute<T> m421of(AttributeSetType<T> attributeSetType, T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return AttributeType.DefaultImpls.m423of(attributeSetType, value);
        }

        public static <T> Attribute<T> unknown(AttributeSetType<T> attributeSetType) {
            return AttributeType.DefaultImpls.unknown(attributeSetType);
        }

        public static <T> Attribute<T> unsupported(AttributeSetType<T> attributeSetType) {
            return AttributeType.DefaultImpls.unsupported(attributeSetType);
        }

        public static <T> Attribute<T> m420of(AttributeSetType<T> attributeSetType, Iterable<? extends T> values) {
            Intrinsics.checkNotNullParameter(values, "values");
            return new AttributeImpl(attributeSetType.getName(), attributeSetType, CollectionsKt.toList(values));
        }

        public static <T> Attribute<T> m422of(AttributeSetType<T> attributeSetType, T value, T... values) {
            Intrinsics.checkNotNullParameter(value, "value");
            Intrinsics.checkNotNullParameter(values, "values");
            return attributeSetType.mo417of((Iterable) CollectionsKt.plus((Collection) CollectionsKt.listOf(value), (Iterable) ArraysKt.toList(values)));
        }
    }
}
