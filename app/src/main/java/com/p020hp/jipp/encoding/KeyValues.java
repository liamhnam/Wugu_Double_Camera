package com.p020hp.jipp.encoding;

import com.arthenica.ffmpegkit.MediaInformation;
import com.p020hp.jipp.encoding.Codec;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u0000 +2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001:\u0001+B3\b\u0016\u0012*\u0010\u0003\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00050\u0004\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001b\b\u0016\u0012\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0004\"\u00020\u0002¢\u0006\u0002\u0010\bB'\u0012\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\nJ\u0006\u0010\u001e\u001a\u00020\u0002J\u0011\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0002H\u0096\u0001J\u0011\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0002H\u0096\u0001J\u0013\u0010$\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0096\u0002J\u0013\u0010'\u001a\u0004\u0018\u00010\u00022\u0006\u0010!\u001a\u00020\u0002H\u0096\u0003J\b\u0010(\u001a\u00020\u0017H\u0016J\t\u0010)\u001a\u00020 H\u0096\u0001J\b\u0010*\u001a\u00020\u0002H\u0016R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u000f0\u000eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000eX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006,"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeyValues;", "", "", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "keyValues", "([Ljava/lang/String;)V", "_encoded", "(Ljava/util/Map;Ljava/lang/String;)V", "get_encoded", "()Ljava/lang/String;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "getPairs", "()Ljava/util/Map;", MediaInformation.KEY_SIZE, "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "combine", "containsKey", "", "key", "containsValue", "value", "equals", "other", "", "get", "hashCode", "isEmpty", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class KeyValues implements Map<String, String>, KMappedMarker {

    public static final Companion INSTANCE = new Companion(null);
    private static final String ELEMENT_SEPARATOR = ";";
    private static final String PART_SEPARATOR = "=";
    private static final Codec<KeyValues> codec;
    private final String _encoded;
    private final Map<String, String> pairs;

    public KeyValues() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String compute(String str, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String compute2(String str, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String computeIfAbsent(String str, Function<? super String, ? extends String> function) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String computeIfAbsent2(String str, Function<? super String, ? extends String> function) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String computeIfPresent(String str, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String computeIfPresent2(String str, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean containsKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.pairs.containsKey(key);
    }

    public boolean containsValue(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return this.pairs.containsValue(value);
    }

    public String get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.pairs.get(key);
    }

    public Set<Map.Entry<String, String>> getEntries() {
        return this.pairs.entrySet();
    }

    public Set<String> getKeys() {
        return this.pairs.keySet();
    }

    public int getSize() {
        return this.pairs.size();
    }

    public Collection<String> getValues() {
        return this.pairs.values();
    }

    @Override
    public boolean isEmpty() {
        return this.pairs.isEmpty();
    }

    @Override
    public String merge(String str, String str2, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String merge2(String str, String str2, BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String put(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String put2(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void putAll(Map<? extends String, ? extends String> map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String putIfAbsent(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String putIfAbsent2(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public String replace(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public String replace2(String str, String str2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public boolean replace(String str, String str2, String str3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean replace2(String str, String str2, String str3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public void replaceAll(BiFunction<? super String, ? super String, ? extends String> biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public KeyValues(Map<String, String> pairs, String str) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        this.pairs = pairs;
        this._encoded = str;
    }

    @Override
    public final boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return containsKey((String) obj);
        }
        return false;
    }

    @Override
    public final boolean containsValue(Object obj) {
        if (obj instanceof String) {
            return containsValue((String) obj);
        }
        return false;
    }

    @Override
    public final Set<Map.Entry<String, String>> entrySet() {
        return getEntries();
    }

    @Override
    public final String get(Object obj) {
        if (obj instanceof String) {
            return get((String) obj);
        }
        return null;
    }

    @Override
    public final Set<String> keySet() {
        return getKeys();
    }

    @Override
    public final int size() {
        return getSize();
    }

    @Override
    public final Collection<String> values() {
        return getValues();
    }

    public KeyValues(Map map, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        map = (i & 1) != 0 ? new LinkedHashMap() : map;
        if ((i & 2) != 0) {
            str = null;
        }
        this(map, str);
    }

    public final Map<String, String> getPairs() {
        return this.pairs;
    }

    public final String get_encoded() {
        return this._encoded;
    }

    public KeyValues(Pair<String, String>... pairs) {
        this(MapsKt.toMap(pairs), null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(pairs, "pairs");
    }

    public KeyValues(String... keyValues) {
        this(INSTANCE.fromPairs(keyValues), null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(keyValues, "keyValues");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof KeyValues) {
            return Intrinsics.areEqual(this.pairs, ((KeyValues) other).pairs);
        }
        if (other instanceof Map) {
            return Intrinsics.areEqual(other, this);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.pairs.hashCode();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u000e¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/encoding/KeyValues$Companion;", "", "()V", "ELEMENT_SEPARATOR", "", "PART_SEPARATOR", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/KeyValues;", "getCodec", "()Lcom/hp/jipp/encoding/Codec;", "fromPairs", "", "keyValues", "", "([Ljava/lang/String;)Ljava/util/Map;", "parse", "combined", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Codec<KeyValues> getCodec() {
            return KeyValues.codec;
        }

        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.p020hp.jipp.encoding.KeyValues parse(java.lang.String r11) {
            /*
                Method dump skipped, instruction units count: 214
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p020hp.jipp.encoding.KeyValues.Companion.parse(java.lang.String):com.hp.jipp.encoding.KeyValues");
        }

        public final Map<String, String> fromPairs(String[] keyValues) {
            Intrinsics.checkNotNullParameter(keyValues, "keyValues");
            List<List> listWindowed$default = CollectionsKt.windowed$default(ArraysKt.toList(keyValues), 2, 2, false, 4, null);
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listWindowed$default, 10));
            for (List list : listWindowed$default) {
                arrayList.add(TuplesKt.m1300to(list.get(0), list.get(1)));
            }
            return MapsKt.toMap(arrayList);
        }
    }

    static {
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.octetString;
        codec = new Codec<KeyValues>() {
            private final Class<KeyValues> cls = KeyValues.class;

            @Override
            public Class<KeyValues> getCls() {
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
                KeyValues keyValues = (KeyValues) value;
                String strCombine = keyValues.get_encoded();
                if (strCombine == null) {
                    strCombine = keyValues.combine();
                }
                output.writeStringValue$jipp_core(strCombine);
            }

            @Override
            public KeyValues readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return KeyValues.INSTANCE.parse(input.readString$jipp_core());
            }
        };
    }

    public final String combine() {
        return CollectionsKt.joinToString$default(this.pairs.entrySet(), ELEMENT_SEPARATOR, null, null, 0, null, new Function1<Map.Entry<? extends String, ? extends String>, CharSequence>() {
            @Override
            public CharSequence invoke(Map.Entry<? extends String, ? extends String> entry) {
                return invoke2((Map.Entry<String, String>) entry);
            }

            public final CharSequence invoke2(Map.Entry<String, String> it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.getKey() + '=' + it.getValue();
            }
        }, 30, null) + ELEMENT_SEPARATOR;
    }

    public String toString() {
        return "{" + combine() + '}';
    }
}
