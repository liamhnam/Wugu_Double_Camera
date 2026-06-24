package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import androidx.exifinterface.media.ExifInterface;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDLayoutAttributeObject;
import com.tom_roush.pdfbox.pdmodel.documentinterchange.taggedpdf.PDListAttributeObject;
import com.tom_roush.pdfbox.pdmodel.interactive.measurement.PDNumberFormatDictionary;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

public final class ClassMapperLite {
    public static final ClassMapperLite INSTANCE = new ClassMapperLite();

    private static final String f4055kotlin = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);
    private static final Map<String, String> map;

    private ClassMapperLite() {
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List listListOf = CollectionsKt.listOf((Object[]) new String[]{"Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", PDNumberFormatDictionary.FRACTIONAL_DISPLAY_FRACTION, "Long", "J", PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "D"});
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, listListOf.size() - 1, 2);
        if (progressionLastElement >= 0) {
            int i = 0;
            while (true) {
                StringBuilder sb = new StringBuilder();
                String str = f4055kotlin;
                int i2 = i + 1;
                linkedHashMap.put(sb.append(str).append('/').append((String) listListOf.get(i)).toString(), listListOf.get(i2));
                linkedHashMap.put(str + '/' + ((String) listListOf.get(i)) + "Array", "[" + ((String) listListOf.get(i2)));
                if (i == progressionLastElement) {
                    break;
                } else {
                    i += 2;
                }
            }
        }
        linkedHashMap.put(f4055kotlin + "/Unit", ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        m3068map$lambda0$add(linkedHashMap, "Any", "java/lang/Object");
        m3068map$lambda0$add(linkedHashMap, "Nothing", "java/lang/Void");
        m3068map$lambda0$add(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : CollectionsKt.listOf((Object[]) new String[]{"String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum"})) {
            m3068map$lambda0$add(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : CollectionsKt.listOf((Object[]) new String[]{"Iterator", "Collection", PDListAttributeObject.OWNER_LIST, "Set", "Map", "ListIterator"})) {
            m3068map$lambda0$add(linkedHashMap, "collections/" + str3, "java/util/" + str3);
            m3068map$lambda0$add(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        m3068map$lambda0$add(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        m3068map$lambda0$add(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        m3068map$lambda0$add(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        m3068map$lambda0$add(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i3 = 0; i3 < 23; i3++) {
            StringBuilder sb2 = new StringBuilder();
            String str4 = f4055kotlin;
            m3068map$lambda0$add(linkedHashMap, "Function" + i3, sb2.append(str4).append("/jvm/functions/Function").append(i3).toString());
            m3068map$lambda0$add(linkedHashMap, "reflect/KFunction" + i3, str4 + "/reflect/KFunction");
        }
        for (String str5 : CollectionsKt.listOf((Object[]) new String[]{"Char", "Byte", "Short", "Int", "Float", "Long", PDLayoutAttributeObject.BORDER_STYLE_DOUBLE, "String", "Enum"})) {
            m3068map$lambda0$add(linkedHashMap, str5 + ".Companion", f4055kotlin + "/jvm/internal/" + str5 + "CompanionObject");
        }
        map = linkedHashMap;
    }

    private static final void m3068map$lambda0$add(Map<String, String> map2, String str, String str2) {
        map2.put(f4055kotlin + '/' + str, "L" + str2 + ';');
    }

    @JvmStatic
    public static final String mapClass(String classId) {
        Intrinsics.checkNotNullParameter(classId, "classId");
        String str = map.get(classId);
        return str == null ? "L" + StringsKt.replace$default(classId, '.', Typography.dollar, false, 4, (Object) null) + ';' : str;
    }
}
