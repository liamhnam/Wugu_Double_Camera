package com.p020hp.jipp.encoding;

import com.p020hp.jipp.util.PrettyPrintable;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000  2\u00020\u00012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002:\u0001 J)\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\t\u0018\u00010\u0003\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH¦\u0002J\u0017\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eH¦\u0002J\"\u0010\u000f\u001a\u0004\u0018\u00010\u000e\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0002\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016J'\u0010\u0011\u001a\u0004\u0018\u0001H\t\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016¢\u0006\u0002\u0010\u0012J&\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\t0\u0002\"\b\b\u0000\u0010\t*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\fH\u0016J\u001b\u0010\u0014\u001a\u00020\u00002\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0002H\u0096\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000eH\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006!"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeGroup;", "Lcom/hp/jipp/util/PrettyPrintable;", "", "Lcom/hp/jipp/encoding/Attribute;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "getTag", "()Lcom/hp/jipp/encoding/DelimiterTag;", "get", "T", "", "type", "Lcom/hp/jipp/encoding/AttributeType;", NamingTable.TAG, "", "getString", "getStrings", "getValue", "(Lcom/hp/jipp/encoding/AttributeType;)Ljava/lang/Object;", "getValues", "plus", "attributes", "prettyPrint", "maxWidth", "", "indent", "print", "", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/jipp/util/PrettyPrinter;", "toMutable", "Lcom/hp/jipp/encoding/MutableAttributeGroup;", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface AttributeGroup extends PrettyPrintable, List<Attribute<?>>, KMappedMarker {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @JvmStatic
    static AttributeGroup groupOf(DelimiterTag delimiterTag, Iterable<? extends Attribute<?>> iterable) {
        return INSTANCE.groupOf(delimiterTag, iterable);
    }

    @JvmStatic
    static AttributeGroup groupOf(DelimiterTag delimiterTag, Attribute<?>... attributeArr) {
        return INSTANCE.groupOf(delimiterTag, attributeArr);
    }

    @JvmStatic
    static MutableAttributeGroup mutableGroupOf(DelimiterTag delimiterTag, Iterable<? extends Attribute<?>> iterable) {
        return INSTANCE.mutableGroupOf(delimiterTag, iterable);
    }

    @JvmStatic
    static MutableAttributeGroup mutableGroupOf(DelimiterTag delimiterTag, Attribute<?>... attributeArr) {
        return INSTANCE.mutableGroupOf(delimiterTag, attributeArr);
    }

    @Deprecated(message = "Use IppInputStream.read()", replaceWith = @ReplaceWith(expression = "readAttributeGroup()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
    @JvmStatic
    static AttributeGroup read(IppInputStream ippInputStream, DelimiterTag delimiterTag) throws IOException {
        return INSTANCE.read(ippInputStream, delimiterTag);
    }

    <T> Attribute<T> get(AttributeType<T> type);

    Attribute<?> get(String name);

    <T> String getString(AttributeType<T> type);

    <T> List<String> getStrings(AttributeType<T> type);

    DelimiterTag getTag();

    <T> T getValue(AttributeType<T> type);

    <T> List<T> getValues(AttributeType<T> type);

    AttributeGroup plus(List<? extends Attribute<?>> attributes);

    String prettyPrint(int maxWidth, String indent);

    @Override
    void print(PrettyPrinter printer);

    MutableAttributeGroup toMutable();

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static <T> List<T> getValues(AttributeGroup attributeGroup, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(type, "type");
            Attribute<T> attribute = attributeGroup.get(type);
            return attribute != null ? attribute : CollectionsKt.emptyList();
        }

        public static <T> T getValue(AttributeGroup attributeGroup, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(type, "type");
            Attribute<T> attribute = attributeGroup.get(type);
            if (attribute != null) {
                return (T) CollectionsKt.firstOrNull((List) attribute);
            }
            return null;
        }

        public static <T> List<String> getStrings(AttributeGroup attributeGroup, AttributeType<T> type) {
            List<String> listStrings;
            Intrinsics.checkNotNullParameter(type, "type");
            Attribute<T> attribute = attributeGroup.get(type);
            return (attribute == null || (listStrings = attribute.strings()) == null) ? CollectionsKt.emptyList() : listStrings;
        }

        public static <T> String getString(AttributeGroup attributeGroup, AttributeType<T> type) {
            List<String> listStrings;
            Intrinsics.checkNotNullParameter(type, "type");
            Attribute<T> attribute = attributeGroup.get(type);
            if (attribute == null || (listStrings = attribute.strings()) == null) {
                return null;
            }
            return (String) CollectionsKt.firstOrNull((List) listStrings);
        }

        public static void print(AttributeGroup attributeGroup, PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            printer.open(PrettyPrinter.OBJECT, attributeGroup.getTag().toString());
            printer.addAll(attributeGroup);
            printer.close();
        }

        public static AttributeGroup plus(AttributeGroup attributeGroup, List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            DelimiterTag tag = attributeGroup.getTag();
            AttributeGroup attributeGroup2 = attributeGroup;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(attributeGroup2, 10));
            for (Attribute<?> attribute : attributeGroup2) {
                arrayList.add(TuplesKt.m1300to(attribute.getType(), attribute));
            }
            Map map = MapsKt.toMap(arrayList);
            List<? extends Attribute<?>> list = attributes;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                Attribute attribute2 = (Attribute) it.next();
                arrayList2.add(TuplesKt.m1300to(attribute2.getType(), attribute2));
            }
            return new AttributeGroupImpl(tag, CollectionsKt.toList(MapsKt.plus(map, MapsKt.toMap(arrayList2)).values()));
        }

        public static MutableAttributeGroup toMutable(AttributeGroup attributeGroup) {
            return AttributeGroup.INSTANCE.mutableGroupOf(attributeGroup.getTag(), attributeGroup);
        }

        public static String prettyPrint(AttributeGroup attributeGroup, int i, String indent) {
            Intrinsics.checkNotNullParameter(indent, "indent");
            return new PrettyPrinter(attributeGroup.getTag().getName(), PrettyPrinter.OBJECT, indent, i).addAll(attributeGroup).print();
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J1\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u001a\u0010\u0007\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\t0\b\"\u0006\u0012\u0002\b\u00030\tH\u0007¢\u0006\u0002\u0010\nJ\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000bH\u0007J1\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u001a\u0010\u0007\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\t0\b\"\u0006\u0012\u0002\b\u00030\tH\u0007¢\u0006\u0002\u0010\u000eJ\"\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u000bH\u0007J\u0018\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006H\u0007¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/encoding/AttributeGroup$Companion;", "", "()V", "groupOf", "Lcom/hp/jipp/encoding/AttributeGroup;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "(Lcom/hp/jipp/encoding/DelimiterTag;[Lcom/hp/jipp/encoding/Attribute;)Lcom/hp/jipp/encoding/AttributeGroup;", "", "mutableGroupOf", "Lcom/hp/jipp/encoding/MutableAttributeGroup;", "(Lcom/hp/jipp/encoding/DelimiterTag;[Lcom/hp/jipp/encoding/Attribute;)Lcom/hp/jipp/encoding/MutableAttributeGroup;", "read", "input", "Lcom/hp/jipp/encoding/IppInputStream;", "groupTag", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final AttributeGroup groupOf(DelimiterTag tag, Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new AttributeGroupImpl(tag, CollectionsKt.toList(attributes));
        }

        @JvmStatic
        public final AttributeGroup groupOf(DelimiterTag tag, Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return groupOf(tag, ArraysKt.toList(attributes));
        }

        @JvmStatic
        public final MutableAttributeGroup mutableGroupOf(DelimiterTag tag, Iterable<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new MutableAttributeGroup(tag, CollectionsKt.toList(attributes));
        }

        @JvmStatic
        public final MutableAttributeGroup mutableGroupOf(DelimiterTag tag, Attribute<?>... attributes) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return mutableGroupOf(tag, ArraysKt.toList(attributes));
        }

        @Deprecated(message = "Use IppInputStream.read()", replaceWith = @ReplaceWith(expression = "readAttributeGroup()", imports = {"com.hp.jipp.encoding.IppInputStream"}))
        @JvmStatic
        public final AttributeGroup read(IppInputStream input, DelimiterTag groupTag) throws IOException {
            Intrinsics.checkNotNullParameter(input, "input");
            Intrinsics.checkNotNullParameter(groupTag, "groupTag");
            return input.readAttributeGroup$jipp_core(groupTag);
        }
    }
}
