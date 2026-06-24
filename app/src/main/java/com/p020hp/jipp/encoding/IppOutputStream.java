package com.p020hp.jipp.encoding;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.p020hp.jipp.model.JobAccountType;
import com.p020hp.jipp.util.BuildError;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0015\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014J\u0015\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0018J\u001f\u0010\u0019\u001a\u00020\u00062\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u001bH\u0000¢\u0006\u0002\b\u001cJ\u0015\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001eJ\u0015\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\nH\u0000¢\u0006\u0002\b!J\u001e\u0010\"\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0002¨\u0006#"}, m1293d2 = {"Lcom/hp/jipp/encoding/IppOutputStream;", "Ljava/io/DataOutputStream;", "outputStream", "Ljava/io/OutputStream;", "(Ljava/io/OutputStream;)V", "write", "", "attribute", "Lcom/hp/jipp/encoding/Attribute;", NamingTable.TAG, "", JobAccountType.group, "Lcom/hp/jipp/encoding/AttributeGroup;", "packet", "Lcom/hp/jipp/encoding/IppPacket;", "tag", "Lcom/hp/jipp/encoding/Tag;", "writeByteValue", "value", "", "writeByteValue$jipp_core", "writeBytesValue", "bytes", "", "writeBytesValue$jipp_core", "writeCollectionAttributes", "attributes", "", "writeCollectionAttributes$jipp_core", "writeIntValue", "writeIntValue$jipp_core", "writeStringValue", TypedValues.Custom.S_STRING, "writeStringValue$jipp_core", "writeValueAttribute", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class IppOutputStream extends DataOutputStream {
    public IppOutputStream(OutputStream outputStream) {
        super(outputStream);
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
    }

    public final void write(IppPacket packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        writeShort(packet.getVersionNumber());
        writeShort(packet.getCode());
        writeInt(packet.getRequestId());
        Iterator<T> it = packet.getAttributeGroups().iterator();
        while (it.hasNext()) {
            write((AttributeGroup) it.next());
        }
        write(Tag.endOfAttributes);
    }

    public final void writeBytesValue$jipp_core(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        writeShort(bytes.length);
        write(bytes);
    }

    public final void writeIntValue$jipp_core(int value) {
        writeShort(4);
        writeInt(value);
    }

    public final void writeByteValue$jipp_core(int value) {
        writeShort(1);
        writeByte(value);
    }

    public final void writeStringValue$jipp_core(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        byte[] bytes = string.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
        writeBytesValue$jipp_core(bytes);
    }

    private final void write(Tag tag) {
        writeByte(tag.getCode());
    }

    public final void write(AttributeGroup group) {
        Intrinsics.checkNotNullParameter(group, "group");
        write(group.getTag());
        Iterator<Attribute<?>> it = group.iterator();
        while (it.hasNext()) {
            write$default(this, it.next(), null, 2, null);
        }
    }

    static void write$default(IppOutputStream ippOutputStream, Attribute attribute, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = attribute.getName();
        }
        ippOutputStream.write(attribute, str);
    }

    private final void write(Attribute<?> attribute, String name) {
        AttributeType<?> type = attribute.getType();
        if (type instanceof EmptyAttributeType) {
            write(((EmptyAttributeType) type).getTag());
            writeStringValue$jipp_core(name);
            writeShort(0);
            return;
        }
        writeValueAttribute(attribute, name);
    }

    static void writeValueAttribute$default(IppOutputStream ippOutputStream, Attribute attribute, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = attribute.getName();
        }
        ippOutputStream.writeValueAttribute(attribute, str);
    }

    private final void writeValueAttribute(Attribute<?> attribute, String name) {
        ValueTag valueTagTagOf;
        Object next;
        for (Object obj : attribute) {
            Codec<? extends Object> codec = IppStreams.INSTANCE.getClsToCodec().get(obj.getClass());
            if (codec == null) {
                Iterator<T> it = IppStreams.INSTANCE.getCodecs().iterator();
                while (true) {
                    if (it.hasNext()) {
                        next = it.next();
                        if (((Codec) next).getCls().isAssignableFrom(obj.getClass())) {
                            break;
                        }
                    } else {
                        next = null;
                        break;
                    }
                }
                codec = (Codec) next;
            }
            if (codec == null) {
                throw new BuildError("Cannot handle " + obj + ": " + obj.getClass());
            }
            if (attribute.getType() instanceof StringType) {
                AttributeType<?> type = attribute.getType();
                if (type == null) {
                    throw new NullPointerException("null cannot be cast to non-null type com.hp.jipp.encoding.StringType");
                }
                valueTagTagOf = ((StringType) type).getTag();
            } else {
                valueTagTagOf = codec.tagOf(obj);
            }
            write(valueTagTagOf);
            writeStringValue$jipp_core(name);
            codec.writeValue(this, obj);
            name = "";
        }
    }

    public final void writeCollectionAttributes$jipp_core(List<? extends Attribute<?>> attributes) {
        Intrinsics.checkNotNullParameter(attributes, "attributes");
        writeShort(0);
        for (Attribute<?> attribute : attributes) {
            write(Tag.memberAttributeName);
            writeShort(0);
            writeStringValue$jipp_core(attribute.getName());
            write(attribute, "");
        }
        write(Tag.endCollection);
        writeStringValue$jipp_core("");
        writeShort(0);
    }
}
