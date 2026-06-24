package com.p020hp.jipp.encoding;

import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u001c\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0016J\u0017\u0010\u0007\u001a\u0004\u0018\u00018\u00002\u0006\u0010\n\u001a\u00020\u0002H&¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u000e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u001b\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\bH\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeType;", "T", "", NamingTable.TAG, "", "getName", "()Ljava/lang/String;", "coerce", "Lcom/hp/jipp/encoding/Attribute;", "attribute", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "empty", "tag", "Lcom/hp/jipp/encoding/OutOfBandTag;", "noValue", "of", "(Ljava/lang/Object;)Lcom/hp/jipp/encoding/Attribute;", "unknown", "unsupported", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface AttributeType<T> {
    Attribute<T> coerce(Attribute<?> attribute);

    T coerce(Object value);

    Attribute<T> empty(OutOfBandTag tag);

    String getName();

    Attribute<T> noValue();

    Attribute<T> mo418of(T value);

    Attribute<T> unknown();

    Attribute<T> unsupported();

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static <T> Attribute<T> m423of(AttributeType<T> attributeType, T value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new AttributeImpl(attributeType.getName(), attributeType, CollectionsKt.listOf(value));
        }

        public static <T> Attribute<T> empty(AttributeType<T> attributeType, OutOfBandTag tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            return new EmptyAttribute(attributeType.getName(), tag);
        }

        public static <T> Attribute<T> noValue(AttributeType<T> attributeType) {
            return attributeType.empty(Tag.noValue);
        }

        public static <T> Attribute<T> unknown(AttributeType<T> attributeType) {
            return attributeType.empty(Tag.unknown);
        }

        public static <T> Attribute<T> unsupported(AttributeType<T> attributeType) {
            return attributeType.empty(Tag.unsupported);
        }

        public static <T> Attribute<T> coerce(AttributeType<T> attributeType, Attribute<?> attribute) {
            Intrinsics.checkNotNullParameter(attribute, "attribute");
            AttributeType<?> type = attribute.getType();
            if (type instanceof EmptyAttributeType) {
                return attributeType.empty(((EmptyAttributeType) type).getTag());
            }
            ArrayList arrayList = new ArrayList();
            Iterator<?> it = attribute.iterator();
            while (it.hasNext()) {
                T tCoerce = attributeType.coerce(it.next());
                if (tCoerce != null) {
                    arrayList.add(tCoerce);
                }
            }
            ArrayList arrayList2 = arrayList;
            return arrayList2.isEmpty() ^ true ? new AttributeImpl(attributeType.getName(), attributeType, arrayList2) : null;
        }
    }
}
