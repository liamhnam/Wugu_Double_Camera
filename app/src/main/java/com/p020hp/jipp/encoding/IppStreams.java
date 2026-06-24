package com.p020hp.jipp.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.p020hp.jipp.encoding.Codec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R-\u0010\b\u001a\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\n\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b0\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R'\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u000e\u0012\f\u0012\u0006\b\u0001\u0012\u00020\u0001\u0018\u00010\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\u0018"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppStreams;", "", "()V", "BYTE_LENGTH", "", "INT_LENGTH", "LENGTH_LENGTH", "TAG_LEN", "clsToCodec", "", "Ljava/lang/Class;", "Lcom/hp/jipp/encoding/Codec;", "getClsToCodec", "()Ljava/util/Map;", "codecs", "", "getCodecs", "()Ljava/util/List;", "tagToCodec", "Lcom/hp/jipp/encoding/ValueTag;", "getTagToCodec", "stringLength", TypedValues.Custom.S_STRING, "", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IppStreams {
    public static final int BYTE_LENGTH = 1;
    public static final IppStreams INSTANCE = new IppStreams();
    public static final int INT_LENGTH = 4;
    public static final int LENGTH_LENGTH = 2;
    public static final int TAG_LEN = 2;
    private static final Map<Class<? extends Object>, Codec<? extends Object>> clsToCodec;
    private static final List<Codec<? extends Object>> codecs;
    private static final Map<ValueTag, Codec<? extends Object>> tagToCodec;

    static {
        Object next;
        Codec.Companion companion = Codec.INSTANCE;
        final ValueTag valueTag = Tag.octetString;
        Codec.Companion companion2 = Codec.INSTANCE;
        Codec.Companion companion3 = Codec.INSTANCE;
        List<Codec<? extends Object>> listListOf = CollectionsKt.listOf((Object[]) new Codec[]{IntType.INSTANCE.getCodec(), BooleanType.INSTANCE.getCodec(), EnumType.INSTANCE.getCodec(), new Codec<byte[]>() {
            private final Class<byte[]> cls = byte[].class;

            @Override
            public Class<byte[]> getCls() {
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
                output.writeBytesValue$jipp_core((byte[]) value);
            }

            @Override
            public byte[] readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return input.readValueBytes$jipp_core();
            }
        }, DateTimeType.INSTANCE.getCodec(), ResolutionType.INSTANCE.getCodec(), IntRangeType.INSTANCE.getCodec(), IntOrIntRangeType.INSTANCE.getCodec(), CollectionType.INSTANCE.getCodec(), TextType.INSTANCE.getCodec(), NameType.INSTANCE.getCodec(), OctetsType.INSTANCE.getCodec(), KeyValues.INSTANCE.getCodec(), new Codec<OtherOctets>() {
            private final Class<OtherOctets> cls = OtherOctets.class;

            @Override
            public Class<OtherOctets> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((OtherOctets) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                output.writeBytesValue$jipp_core(((OtherOctets) value).getValue());
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return tag.isOctetString() || tag.isInteger();
            }

            @Override
            public OtherOctets readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return new OtherOctets(startTag, input.readValueBytes$jipp_core());
            }
        }, KeywordType.INSTANCE.getCodec(), KeywordOrNameType.INSTANCE.getCodec(), UriType.INSTANCE.getCodec(), new Codec<OtherString>() {
            private final Class<OtherString> cls = OtherString.class;

            @Override
            public Class<OtherString> getCls() {
                return this.cls;
            }

            @Override
            public ValueTag tagOf(Object value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return ((OtherString) value).getTag();
            }

            @Override
            public void writeValue(IppOutputStream output, Object value) {
                Intrinsics.checkNotNullParameter(output, "output");
                Intrinsics.checkNotNullParameter(value, "value");
                output.writeStringValue$jipp_core(((OtherString) value).getValue());
            }

            @Override
            public boolean handlesTag(ValueTag tag) {
                Intrinsics.checkNotNullParameter(tag, "tag");
                return tag.isCharString();
            }

            @Override
            public OtherString readValue(IppInputStream input, ValueTag startTag) {
                Intrinsics.checkNotNullParameter(input, "input");
                Intrinsics.checkNotNullParameter(startTag, "startTag");
                return new OtherString(startTag, input.readString$jipp_core());
            }
        }});
        codecs = listListOf;
        List<Codec<? extends Object>> list = listListOf;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Codec codec = (Codec) it.next();
            arrayList.add(TuplesKt.m1300to(codec.getCls(), codec));
        }
        clsToCodec = MapsKt.toMap(arrayList);
        List<ValueTag> list2 = Tag.valueTags;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (ValueTag valueTag2 : list2) {
            Iterator<T> it2 = codecs.iterator();
            while (true) {
                if (it2.hasNext()) {
                    next = it2.next();
                    if (((Codec) next).handlesTag(valueTag2)) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            arrayList2.add(TuplesKt.m1300to(valueTag2, next));
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (((Pair) obj).getSecond() != null) {
                arrayList3.add(obj);
            }
        }
        tagToCodec = MapsKt.toMap(arrayList3);
    }

    private IppStreams() {
    }

    public final int stringLength(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes.length + 2;
    }

    public final List<Codec<? extends Object>> getCodecs() {
        return codecs;
    }

    public final Map<Class<? extends Object>, Codec<? extends Object>> getClsToCodec() {
        return clsToCodec;
    }

    public final Map<ValueTag, Codec<? extends Object>> getTagToCodec() {
        return tagToCodec;
    }
}
