package com.p020hp.jipp.encoding;

import com.p020hp.jipp.model.EnumTypes;
import com.p020hp.jipp.model.KeyValuesTypes;
import com.p020hp.jipp.util.ParseError;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Charsets;
import org.simpleframework.xml.strategy.Name;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001c\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0015\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\r\u0010\u0010\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u0012J\u0017\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0014H\u0000¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\fJ\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006H\u0002J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\r\u0010\u001f\u001a\u00020\nH\u0000¢\u0006\u0002\b J\n\u0010!\u001a\u0004\u0018\u00010\bH\u0002J0\u0010\"\u001a\u00020\u001c\"\b\b\u0000\u0010#*\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%2\u0006\u0010\r\u001a\u00020&2\u0006\u0010\t\u001a\u00020\nH\u0002J\r\u0010'\u001a\u00020(H\u0000¢\u0006\u0002\b)J\r\u0010*\u001a\u00020+H\u0000¢\u0006\u0002\b,J\u0015\u0010-\u001a\u00020+2\u0006\u0010.\u001a\u00020\u0018H\u0000¢\u0006\u0002\b/¨\u00060"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppInputStream;", "Ljava/io/DataInputStream;", "inputStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)V", "readAnyAttribute", "Lcom/hp/jipp/encoding/Attribute;", "initTag", "Lcom/hp/jipp/encoding/Tag;", "attributeName", "", "readAttributeGroup", "Lcom/hp/jipp/encoding/AttributeGroup;", "tag", "Lcom/hp/jipp/encoding/DelimiterTag;", "readAttributeGroup$jipp_core", "readByteValue", "", "readByteValue$jipp_core", "readCollectionAttributes", "", "readCollectionAttributes$jipp_core", "readGroup", "readIntValue", "", "readIntValue$jipp_core", "readNextAttribute", "readNextValue", "", "readPacket", "Lcom/hp/jipp/encoding/IppPacket;", "readString", "readString$jipp_core", "readTag", "readValue", "T", "codec", "Lcom/hp/jipp/encoding/Codec;", "Lcom/hp/jipp/encoding/ValueTag;", "readValueBytes", "", "readValueBytes$jipp_core", "skipValueBytes", "", "skipValueBytes$jipp_core", "takeLength", Name.LENGTH, "takeLength$jipp_core", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IppInputStream extends DataInputStream {
    public IppInputStream(InputStream inputStream) {
        super(new BufferedInputStream(inputStream));
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
    }

    public final IppPacket readPacket() throws IOException {
        return new IppPacket(readShort(), readShort(), readInt(), (List<? extends AttributeGroup>) SequencesKt.toList(SequencesKt.generateSequence(new Function0<AttributeGroup>() {
            {
                super(0);
            }

            @Override
            public final AttributeGroup invoke() {
                return IppInputStream.this.readGroup();
            }
        })));
    }

    public final AttributeGroup readGroup() throws ParseError {
        Tag tag = readTag();
        if (tag == null) {
            return null;
        }
        if (!(!Intrinsics.areEqual(tag, Tag.endOfAttributes))) {
            tag = null;
        }
        if (tag == null) {
            return null;
        }
        if (!(tag instanceof DelimiterTag)) {
            throw new ParseError("Illegal delimiter " + tag);
        }
        return readAttributeGroup$jipp_core((DelimiterTag) tag);
    }

    private final Tag readTag() {
        Integer numValueOf = Integer.valueOf(read());
        if (!(numValueOf.intValue() >= 0)) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            return Tag.INSTANCE.fromInt(numValueOf.intValue());
        }
        return null;
    }

    public final AttributeGroup readAttributeGroup$jipp_core(DelimiterTag tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return AttributeGroup.INSTANCE.groupOf(tag, SequencesKt.toList(SequencesKt.generateSequence(new Function0<Attribute<?>>() {
            {
                super(0);
            }

            @Override
            public final Attribute<?> invoke() {
                return this.this$0.readNextAttribute();
            }
        })));
    }

    public final Attribute<?> readNextAttribute() {
        mark(1);
        Tag tag = readTag();
        if (tag == null) {
            return null;
        }
        if (tag.isDelimiter()) {
            reset();
            return null;
        }
        return readAnyAttribute(tag);
    }

    private final Attribute<?> readAnyAttribute(Tag initTag) {
        return readAnyAttribute(readString$jipp_core(), initTag);
    }

    public final String readString$jipp_core() {
        return new String(readValueBytes$jipp_core(), Charsets.UTF_8);
    }

    private final Attribute<?> readAnyAttribute(final String attributeName, final Tag initTag) throws ParseError {
        Object next;
        if (initTag instanceof OutOfBandTag) {
            readValueBytes$jipp_core();
            return new EmptyAttribute(attributeName, (OutOfBandTag) initTag);
        }
        if (initTag instanceof ValueTag) {
            Iterator<T> it = IppStreams.INSTANCE.getCodecs().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((Codec) next).handlesTag((ValueTag) initTag)) {
                    break;
                }
            }
            Codec codec = (Codec) next;
            if (codec != null) {
                return new UnknownAttribute(attributeName, CollectionsKt.plus((Collection) CollectionsKt.listOf(readValue(codec, (ValueTag) initTag, attributeName)), SequencesKt.generateSequence(new Function0<Object>() {
                    {
                        super(0);
                    }

                    @Override
                    public final Object invoke() {
                        return this.this$0.readNextValue(attributeName);
                    }
                })));
            }
            throw new ParseError("No codec found for tag " + initTag);
        }
        throw new ParseError("invalid attribute tag " + initTag);
    }

    private final <T> Object readValue(Codec<T> codec, ValueTag tag, String attributeName) {
        if (Intrinsics.areEqual(tag, Tag.enumValue)) {
            EnumType<? extends Enum> enumType = EnumTypes.all.get(attributeName);
            if (enumType != null) {
                Enum enumCoerce = enumType.coerce((Object) Integer.valueOf(readIntValue$jipp_core()));
                Intrinsics.checkNotNull(enumCoerce);
                return enumCoerce;
            }
        } else if (Intrinsics.areEqual(tag, Tag.octetString) && KeyValuesTypes.all.get(attributeName) != null) {
            return KeyValues.INSTANCE.getCodec().readValue(this, tag);
        }
        return codec.readValue(this, tag);
    }

    public final int readIntValue$jipp_core() throws ParseError {
        takeLength$jipp_core(4);
        return readInt();
    }

    public final void takeLength$jipp_core(int length) throws ParseError {
        short s = readShort();
        if (s != length) {
            throw new ParseError("Bad attribute length: expected " + length + ", got " + ((int) s));
        }
    }

    public final byte[] readValueBytes$jipp_core() {
        int i = readShort();
        byte[] bArr = new byte[i];
        if (i > 0) {
            readFully(bArr);
        }
        return bArr;
    }

    public final Object readNextValue(String attributeName) throws ParseError {
        mark(4);
        Tag tag = readTag();
        Object obj = null;
        if (tag == null) {
            return null;
        }
        if (tag.isEndOfValueStream$jipp_core() || readShort() != 0) {
            reset();
            return null;
        }
        if (!(tag instanceof ValueTag)) {
            return null;
        }
        Codec<? extends Object> codec = IppStreams.INSTANCE.getTagToCodec().get(tag);
        if (codec == null) {
            Iterator<T> it = IppStreams.INSTANCE.getCodecs().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((Codec) next).handlesTag((ValueTag) tag)) {
                    obj = next;
                    break;
                }
            }
            codec = (Codec) obj;
        }
        if (codec == null) {
            throw new ParseError("No codec found for tag " + tag);
        }
        return readValue(codec, (ValueTag) tag, attributeName);
    }

    public final List<Attribute<?>> readCollectionAttributes$jipp_core() throws ParseError {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Tag tag = readTag();
            if (Intrinsics.areEqual(tag, Tag.endCollection)) {
                skipValueBytes$jipp_core();
                skipValueBytes$jipp_core();
                return arrayList;
            }
            if (Intrinsics.areEqual(tag, Tag.memberAttributeName)) {
                skipValueBytes$jipp_core();
                String string$jipp_core = readString$jipp_core();
                Tag tag2 = readTag();
                if (tag2 == null) {
                    throw new ParseError("Missing member tag in " + tag);
                }
                readValueBytes$jipp_core();
                arrayList.add(readAnyAttribute(string$jipp_core, tag2));
            } else {
                throw new ParseError("Bad tag in collection: " + tag);
            }
        }
    }

    public final void skipValueBytes$jipp_core() throws ParseError {
        long j = readShort();
        if (j != skip(j)) {
            throw new ParseError("Value too short");
        }
    }

    public final byte readByteValue$jipp_core() throws ParseError {
        takeLength$jipp_core(1);
        return readByte();
    }
}
