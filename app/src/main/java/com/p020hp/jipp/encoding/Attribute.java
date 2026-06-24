package com.p020hp.jipp.encoding;

import com.p020hp.jipp.util.BytesKt;
import com.p020hp.jipp.util.PrettyPrintable;
import com.p020hp.jipp.util.PrettyPrinter;
import com.tom_roush.fontbox.ttf.NamingTable;
import com.wugu.doublecamera.enums.MqttCmdEnum;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004J\u000f\u0010\r\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0004H\u0016J\u0010\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00028\u0000H\u0002R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/jipp/encoding/Attribute;", "T", "", "Lcom/hp/jipp/util/PrettyPrintable;", "", NamingTable.TAG, "", "getName", "()Ljava/lang/String;", "type", "Lcom/hp/jipp/encoding/AttributeType;", "getType", "()Lcom/hp/jipp/encoding/AttributeType;", "getValue", "()Ljava/lang/Object;", "isNoValue", "", "isUnknown", "isUnsupported", "print", "", MqttCmdEnum.C2S_PRINTER_ERROR, "Lcom/hp/jipp/util/PrettyPrinter;", "strings", "toPrintable", "value", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public interface Attribute<T> extends PrettyPrintable, List<T>, KMappedMarker {
    String getName();

    AttributeType<T> getType();

    T getValue();

    boolean isNoValue();

    boolean isUnknown();

    boolean isUnsupported();

    @Override
    void print(PrettyPrinter printer);

    List<String> strings();

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 4, 0})
    public static final class DefaultImpls {
        public static <T> List<String> strings(Attribute<T> attribute) {
            Attribute<T> attribute2 = attribute;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(attribute2, 10));
            for (T t : attribute2) {
                arrayList.add(t instanceof Stringable ? ((Stringable) t).asString() : t.toString());
            }
            return arrayList;
        }

        public static <T> boolean isUnknown(Attribute<T> attribute) {
            AttributeType<T> type = attribute.getType();
            if (!(type instanceof EmptyAttributeType)) {
                type = null;
            }
            EmptyAttributeType emptyAttributeType = (EmptyAttributeType) type;
            return Intrinsics.areEqual(emptyAttributeType != null ? emptyAttributeType.getTag() : null, Tag.unknown);
        }

        public static <T> boolean isNoValue(Attribute<T> attribute) {
            AttributeType<T> type = attribute.getType();
            if (!(type instanceof EmptyAttributeType)) {
                type = null;
            }
            EmptyAttributeType emptyAttributeType = (EmptyAttributeType) type;
            return Intrinsics.areEqual(emptyAttributeType != null ? emptyAttributeType.getTag() : null, Tag.noValue);
        }

        public static <T> boolean isUnsupported(Attribute<T> attribute) {
            AttributeType<T> type = attribute.getType();
            if (!(type instanceof EmptyAttributeType)) {
                type = null;
            }
            EmptyAttributeType emptyAttributeType = (EmptyAttributeType) type;
            return Intrinsics.areEqual(emptyAttributeType != null ? emptyAttributeType.getTag() : null, Tag.unsupported);
        }

        public static <T> void print(Attribute<T> attribute, PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            int size = attribute.size();
            if (size == 0) {
                PrettyPrinter.Companion.Style style = PrettyPrinter.SILENT;
                StringBuilder sbAppend = new StringBuilder().append(attribute.getName());
                EmptyAttribute emptyAttribute = (EmptyAttribute) (!(attribute instanceof EmptyAttribute) ? null : attribute);
                printer.open(style, sbAppend.append(emptyAttribute != null ? " (" + emptyAttribute.getTag() + ')' : null).toString());
            } else if (size == 1) {
                printer.open(PrettyPrinter.KEY_VALUE, attribute.getName() + " =");
            } else {
                printer.open(PrettyPrinter.ARRAY, attribute.getName() + " =");
            }
            for (T t : attribute) {
                if (t instanceof PrettyPrintable) {
                    printer.add(t);
                } else {
                    printer.add(toPrintable(attribute, t));
                }
            }
            printer.close();
        }

        private static <T> String toPrintable(Attribute<T> attribute, T t) {
            if (!(t instanceof Calendar)) {
                return t instanceof byte[] ? "0x" + BytesKt.toHexString((byte[]) t) : t.toString();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Calendar calendar = (Calendar) t;
            simpleDateFormat.setTimeZone(calendar.getTimeZone());
            String str = simpleDateFormat.format(calendar.getTime());
            Intrinsics.checkNotNullExpressionValue(str, "SimpleDateFormat(\"yyyy-M…mat(value.time)\n        }");
            return str;
        }
    }
}
