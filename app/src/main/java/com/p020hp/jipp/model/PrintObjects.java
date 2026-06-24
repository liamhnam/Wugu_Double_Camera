package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\b\u0018\u0000 32\u00020\u0001:\u0003345B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010'\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\nHÆ\u0003J>\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u00020\u0004HÖ\u0001J\b\u00101\u001a\u000202H\u0016R\u001e\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00066"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "documentNumber", "", "objectOffset", "Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "objectSize", "Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "objectUuid", "Ljava/net/URI;", "(Ljava/lang/Integer;Lcom/hp/jipp/model/PrintObjects$ObjectOffset;Lcom/hp/jipp/model/PrintObjects$ObjectSize;Ljava/net/URI;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getDocumentNumber", "()Ljava/lang/Integer;", "setDocumentNumber", "(Ljava/lang/Integer;)V", "documentNumber$1", "Ljava/lang/Integer;", "getObjectOffset", "()Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "setObjectOffset", "(Lcom/hp/jipp/model/PrintObjects$ObjectOffset;)V", "objectOffset$1", "getObjectSize", "()Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "setObjectSize", "(Lcom/hp/jipp/model/PrintObjects$ObjectSize;)V", "objectSize$1", "getObjectUuid", "()Ljava/net/URI;", "setObjectUuid", "(Ljava/net/URI;)V", "objectUuid$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Lcom/hp/jipp/model/PrintObjects$ObjectOffset;Lcom/hp/jipp/model/PrintObjects$ObjectSize;Ljava/net/URI;)Lcom/hp/jipp/model/PrintObjects;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "ObjectOffset", "ObjectSize", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class PrintObjects implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<PrintObjects> cls;
    public static final IntType documentNumber;
    public static final AttributeCollection.Type<ObjectOffset> objectOffset;
    public static final AttributeCollection.Type<ObjectSize> objectSize;
    public static final UriType objectUuid;

    private Integer documentNumber;

    private ObjectOffset objectOffset;

    private ObjectSize objectSize;

    private URI objectUuid;

    public static PrintObjects copy$default(PrintObjects printObjects, Integer num, ObjectOffset objectOffset2, ObjectSize objectSize2, URI uri, int i, Object obj) {
        if ((i & 1) != 0) {
            num = printObjects.documentNumber;
        }
        if ((i & 2) != 0) {
            objectOffset2 = printObjects.objectOffset;
        }
        if ((i & 4) != 0) {
            objectSize2 = printObjects.objectSize;
        }
        if ((i & 8) != 0) {
            uri = printObjects.objectUuid;
        }
        return printObjects.copy(num, objectOffset2, objectSize2, uri);
    }

    public final Integer getDocumentNumber() {
        return this.documentNumber;
    }

    public final ObjectOffset getObjectOffset() {
        return this.objectOffset;
    }

    public final ObjectSize getObjectSize() {
        return this.objectSize;
    }

    public final URI getObjectUuid() {
        return this.objectUuid;
    }

    public final PrintObjects copy(Integer documentNumber2, ObjectOffset objectOffset2, ObjectSize objectSize2, URI objectUuid2) {
        return new PrintObjects(documentNumber2, objectOffset2, objectSize2, objectUuid2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrintObjects)) {
            return false;
        }
        PrintObjects printObjects = (PrintObjects) other;
        return Intrinsics.areEqual(this.documentNumber, printObjects.documentNumber) && Intrinsics.areEqual(this.objectOffset, printObjects.objectOffset) && Intrinsics.areEqual(this.objectSize, printObjects.objectSize) && Intrinsics.areEqual(this.objectUuid, printObjects.objectUuid);
    }

    public int hashCode() {
        Integer num = this.documentNumber;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        ObjectOffset objectOffset2 = this.objectOffset;
        int iHashCode2 = (iHashCode + (objectOffset2 != null ? objectOffset2.hashCode() : 0)) * 31;
        ObjectSize objectSize2 = this.objectSize;
        int iHashCode3 = (iHashCode2 + (objectSize2 != null ? objectSize2.hashCode() : 0)) * 31;
        URI uri = this.objectUuid;
        return iHashCode3 + (uri != null ? uri.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public PrintObjects(Integer num, ObjectOffset objectOffset2, ObjectSize objectSize2, URI uri) {
        this.documentNumber = num;
        this.objectOffset = objectOffset2;
        this.objectSize = objectSize2;
        this.objectUuid = uri;
    }

    public PrintObjects(Integer num, ObjectOffset objectOffset2, ObjectSize objectSize2, URI uri, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            objectOffset2 = null;
        }
        if ((i & 4) != 0) {
            objectSize2 = null;
        }
        if ((i & 8) != 0) {
            uri = null;
        }
        this(num, objectOffset2, objectSize2, uri);
    }

    public final Integer getDocumentNumber() {
        return this.documentNumber;
    }

    public final void setDocumentNumber(Integer num) {
        this.documentNumber = num;
    }

    public final ObjectOffset getObjectOffset() {
        return this.objectOffset;
    }

    public final void setObjectOffset(ObjectOffset objectOffset2) {
        this.objectOffset = objectOffset2;
    }

    public final ObjectSize getObjectSize() {
        return this.objectSize;
    }

    public final void setObjectSize(ObjectSize objectSize2) {
        this.objectSize = objectSize2;
    }

    public final URI getObjectUuid() {
        return this.objectUuid;
    }

    public final void setObjectUuid(URI uri) {
        this.objectUuid = uri;
    }

    public PrintObjects() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        Integer num = this.documentNumber;
        attributeArr[0] = num != null ? documentNumber.mo418of(Integer.valueOf(num.intValue())) : null;
        ObjectOffset objectOffset2 = this.objectOffset;
        attributeArr[1] = objectOffset2 != null ? objectOffset.mo418of(objectOffset2) : null;
        ObjectSize objectSize2 = this.objectSize;
        attributeArr[2] = objectSize2 != null ? objectSize.mo418of(objectSize2) : null;
        URI uri = this.objectUuid;
        attributeArr[3] = uri != null ? objectUuid.mo418of(uri) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0013\u001a\u00020\u00022\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00160\u0015H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrintObjects;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "documentNumber", "Lcom/hp/jipp/encoding/IntType;", "objectOffset", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "objectSize", "Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "objectUuid", "Lcom/hp/jipp/encoding/UriType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<PrintObjects> {
        @Deprecated(message = "Remove this symbol")
        public static void getTypes$annotations() {
        }

        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Override
        public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
        }

        @Override
        public AttributeCollection convert(List list) {
            return convert((List<? extends Attribute<?>>) list);
        }

        @Override
        public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
        }

        @Override
        public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            Intrinsics.checkNotNullParameter(type, "type");
            return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
        }

        @Override
        public PrintObjects convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new PrintObjects((Integer) extractOne(attributes, PrintObjects.documentNumber), (ObjectOffset) extractOne(attributes, PrintObjects.objectOffset), (ObjectSize) extractOne(attributes, PrintObjects.objectSize), (URI) extractOne(attributes, PrintObjects.objectUuid));
        }

        @Override
        public Class<PrintObjects> getCls() {
            return PrintObjects.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = PrintObjects.class;
        Types = companion;
        documentNumber = new IntType("document-number");
        objectOffset = new AttributeCollection.Type<>("object-offset", ObjectOffset.INSTANCE);
        objectSize = new AttributeCollection.Type<>("object-size", ObjectSize.INSTANCE);
        objectUuid = new UriType("object-uuid");
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0001%B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0004HÖ\u0001J\b\u0010#\u001a\u00020$H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010¨\u0006&"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xOffset", "", "yOffset", "zOffset", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXOffset", "()Ljava/lang/Integer;", "setXOffset", "(Ljava/lang/Integer;)V", "xOffset$1", "Ljava/lang/Integer;", "getYOffset", "setYOffset", "yOffset$1", "getZOffset", "setZOffset", "zOffset$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class ObjectOffset implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<ObjectOffset> cls;
        public static final IntType xOffset;
        public static final IntType yOffset;
        public static final IntType zOffset;

        private Integer xOffset;

        private Integer yOffset;

        private Integer zOffset;

        public static ObjectOffset copy$default(ObjectOffset objectOffset, Integer num, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = objectOffset.xOffset;
            }
            if ((i & 2) != 0) {
                num2 = objectOffset.yOffset;
            }
            if ((i & 4) != 0) {
                num3 = objectOffset.zOffset;
            }
            return objectOffset.copy(num, num2, num3);
        }

        public final Integer getXOffset() {
            return this.xOffset;
        }

        public final Integer getYOffset() {
            return this.yOffset;
        }

        public final Integer getZOffset() {
            return this.zOffset;
        }

        public final ObjectOffset copy(Integer xOffset2, Integer yOffset2, Integer zOffset2) {
            return new ObjectOffset(xOffset2, yOffset2, zOffset2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ObjectOffset)) {
                return false;
            }
            ObjectOffset objectOffset = (ObjectOffset) other;
            return Intrinsics.areEqual(this.xOffset, objectOffset.xOffset) && Intrinsics.areEqual(this.yOffset, objectOffset.yOffset) && Intrinsics.areEqual(this.zOffset, objectOffset.zOffset);
        }

        public int hashCode() {
            Integer num = this.xOffset;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            Integer num2 = this.yOffset;
            int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
            Integer num3 = this.zOffset;
            return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public ObjectOffset(Integer num, Integer num2, Integer num3) {
            this.xOffset = num;
            this.yOffset = num2;
            this.zOffset = num3;
        }

        public ObjectOffset(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            if ((i & 4) != 0) {
                num3 = null;
            }
            this(num, num2, num3);
        }

        public final Integer getXOffset() {
            return this.xOffset;
        }

        public final void setXOffset(Integer num) {
            this.xOffset = num;
        }

        public final Integer getYOffset() {
            return this.yOffset;
        }

        public final void setYOffset(Integer num) {
            this.yOffset = num;
        }

        public final Integer getZOffset() {
            return this.zOffset;
        }

        public final void setZOffset(Integer num) {
            this.zOffset = num;
        }

        public ObjectOffset() {
            this(null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute<Integer> attributeOf;
            Attribute<Integer> attributeOf2;
            Attribute[] attributeArr = new Attribute[3];
            Integer num = this.xOffset;
            Attribute<Integer> attributeOf3 = null;
            if (num != null) {
                attributeOf = xOffset.mo418of(Integer.valueOf(num.intValue()));
            } else {
                attributeOf = null;
            }
            attributeArr[0] = attributeOf;
            Integer num2 = this.yOffset;
            if (num2 != null) {
                attributeOf2 = yOffset.mo418of(Integer.valueOf(num2.intValue()));
            } else {
                attributeOf2 = null;
            }
            attributeArr[1] = attributeOf2;
            Integer num3 = this.zOffset;
            if (num3 != null) {
                attributeOf3 = zOffset.mo418of(Integer.valueOf(num3.intValue()));
            }
            attributeArr[2] = attributeOf3;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects$ObjectOffset$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrintObjects$ObjectOffset;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xOffset", "Lcom/hp/jipp/encoding/IntType;", "yOffset", "zOffset", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<ObjectOffset> {
            @Deprecated(message = "Remove this symbol")
            public static void getTypes$annotations() {
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override
            public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
            }

            @Override
            public AttributeCollection convert(List list) {
                return convert((List<? extends Attribute<?>>) list);
            }

            @Override
            public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
            }

            @Override
            public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
            }

            @Override
            public ObjectOffset convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new ObjectOffset((Integer) extractOne(attributes, ObjectOffset.xOffset), (Integer) extractOne(attributes, ObjectOffset.yOffset), (Integer) extractOne(attributes, ObjectOffset.zOffset));
            }

            @Override
            public Class<ObjectOffset> getCls() {
                return ObjectOffset.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = ObjectOffset.class;
            Types = companion;
            xOffset = new IntType("x-offset");
            yOffset = new IntType("y-offset");
            zOffset = new IntType("z-offset");
        }

        public String toString() {
            return "ObjectOffset(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 %2\u00020\u0001:\u0001%B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u0004HÖ\u0001J\b\u0010#\u001a\u00020$H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0013\u0010\u000e\"\u0004\b\u0014\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010¨\u0006&"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "", "yDimension", "zDimension", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Ljava/lang/Integer;", "setXDimension", "(Ljava/lang/Integer;)V", "xDimension$1", "Ljava/lang/Integer;", "getYDimension", "setYDimension", "yDimension$1", "getZDimension", "setZDimension", "zDimension$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class ObjectSize implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<ObjectSize> cls;
        public static final IntType xDimension;
        public static final IntType yDimension;
        public static final IntType zDimension;

        private Integer xDimension;

        private Integer yDimension;

        private Integer zDimension;

        public static ObjectSize copy$default(ObjectSize objectSize, Integer num, Integer num2, Integer num3, int i, Object obj) {
            if ((i & 1) != 0) {
                num = objectSize.xDimension;
            }
            if ((i & 2) != 0) {
                num2 = objectSize.yDimension;
            }
            if ((i & 4) != 0) {
                num3 = objectSize.zDimension;
            }
            return objectSize.copy(num, num2, num3);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final Integer getZDimension() {
            return this.zDimension;
        }

        public final ObjectSize copy(Integer xDimension2, Integer yDimension2, Integer zDimension2) {
            return new ObjectSize(xDimension2, yDimension2, zDimension2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ObjectSize)) {
                return false;
            }
            ObjectSize objectSize = (ObjectSize) other;
            return Intrinsics.areEqual(this.xDimension, objectSize.xDimension) && Intrinsics.areEqual(this.yDimension, objectSize.yDimension) && Intrinsics.areEqual(this.zDimension, objectSize.zDimension);
        }

        public int hashCode() {
            Integer num = this.xDimension;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            Integer num2 = this.yDimension;
            int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
            Integer num3 = this.zDimension;
            return iHashCode2 + (num3 != null ? num3.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public ObjectSize(Integer num, Integer num2, Integer num3) {
            this.xDimension = num;
            this.yDimension = num2;
            this.zDimension = num3;
        }

        public ObjectSize(Integer num, Integer num2, Integer num3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            if ((i & 4) != 0) {
                num3 = null;
            }
            this(num, num2, num3);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final void setXDimension(Integer num) {
            this.xDimension = num;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final void setYDimension(Integer num) {
            this.yDimension = num;
        }

        public final Integer getZDimension() {
            return this.zDimension;
        }

        public final void setZDimension(Integer num) {
            this.zDimension = num;
        }

        public ObjectSize() {
            this(null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute<Integer> attributeOf;
            Attribute<Integer> attributeOf2;
            Attribute[] attributeArr = new Attribute[3];
            Integer num = this.xDimension;
            Attribute<Integer> attributeOf3 = null;
            if (num != null) {
                attributeOf = xDimension.mo418of(Integer.valueOf(num.intValue()));
            } else {
                attributeOf = null;
            }
            attributeArr[0] = attributeOf;
            Integer num2 = this.yDimension;
            if (num2 != null) {
                attributeOf2 = yDimension.mo418of(Integer.valueOf(num2.intValue()));
            } else {
                attributeOf2 = null;
            }
            attributeArr[1] = attributeOf2;
            Integer num3 = this.zDimension;
            if (num3 != null) {
                attributeOf3 = zDimension.mo418of(Integer.valueOf(num3.intValue()));
            }
            attributeArr[2] = attributeOf3;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/PrintObjects$ObjectSize$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/PrintObjects$ObjectSize;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntType;", "yDimension", "zDimension", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<ObjectSize> {
            @Deprecated(message = "Remove this symbol")
            public static void getTypes$annotations() {
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @Override
            public <T> Attribute<T> coerced(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.coerced(this, attributes, type);
            }

            @Override
            public AttributeCollection convert(List list) {
                return convert((List<? extends Attribute<?>>) list);
            }

            @Override
            public <T> List<T> extractAll(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return AttributeCollection.Converter.DefaultImpls.extractAll(this, attributes, type);
            }

            @Override
            public <T> T extractOne(List<? extends Attribute<?>> attributes, AttributeType<T> type) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                Intrinsics.checkNotNullParameter(type, "type");
                return (T) AttributeCollection.Converter.DefaultImpls.extractOne(this, attributes, type);
            }

            @Override
            public ObjectSize convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new ObjectSize((Integer) extractOne(attributes, ObjectSize.xDimension), (Integer) extractOne(attributes, ObjectSize.yDimension), (Integer) extractOne(attributes, ObjectSize.zDimension));
            }

            @Override
            public Class<ObjectSize> getCls() {
                return ObjectSize.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = ObjectSize.class;
            Types = companion;
            xDimension = new IntType("x-dimension");
            yDimension = new IntType("y-dimension");
            zDimension = new IntType("z-dimension");
        }

        public String toString() {
            return "ObjectSize(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    public String toString() {
        return "PrintObjects(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
