package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.UriType;
import com.p020hp.jipp.model.TransmissionStatus;
import com.p020hp.jipp.util.PrettyPrinter;
import java.net.URI;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003J2\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\u0006HÖ\u0001J\b\u0010)\u001a\u00020*H\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006,"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationStatuses;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "destinationUri", "Ljava/net/URI;", "imagesCompleted", "", "transmissionStatus", "Lcom/hp/jipp/model/TransmissionStatus;", "(Ljava/net/URI;Ljava/lang/Integer;Lcom/hp/jipp/model/TransmissionStatus;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getDestinationUri", "()Ljava/net/URI;", "setDestinationUri", "(Ljava/net/URI;)V", "destinationUri$1", "getImagesCompleted", "()Ljava/lang/Integer;", "setImagesCompleted", "(Ljava/lang/Integer;)V", "imagesCompleted$1", "Ljava/lang/Integer;", "getTransmissionStatus", "()Lcom/hp/jipp/model/TransmissionStatus;", "setTransmissionStatus", "(Lcom/hp/jipp/model/TransmissionStatus;)V", "transmissionStatus$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/net/URI;Ljava/lang/Integer;Lcom/hp/jipp/model/TransmissionStatus;)Lcom/hp/jipp/model/DestinationStatuses;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class DestinationStatuses implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<DestinationStatuses> cls;
    public static final UriType destinationUri;
    public static final IntType imagesCompleted;
    public static final TransmissionStatus.Type transmissionStatus;

    private URI destinationUri;

    private Integer imagesCompleted;

    private TransmissionStatus transmissionStatus;

    public static DestinationStatuses copy$default(DestinationStatuses destinationStatuses, URI uri, Integer num, TransmissionStatus transmissionStatus2, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = destinationStatuses.destinationUri;
        }
        if ((i & 2) != 0) {
            num = destinationStatuses.imagesCompleted;
        }
        if ((i & 4) != 0) {
            transmissionStatus2 = destinationStatuses.transmissionStatus;
        }
        return destinationStatuses.copy(uri, num, transmissionStatus2);
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final Integer getImagesCompleted() {
        return this.imagesCompleted;
    }

    public final TransmissionStatus getTransmissionStatus() {
        return this.transmissionStatus;
    }

    public final DestinationStatuses copy(URI destinationUri2, Integer imagesCompleted2, TransmissionStatus transmissionStatus2) {
        return new DestinationStatuses(destinationUri2, imagesCompleted2, transmissionStatus2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DestinationStatuses)) {
            return false;
        }
        DestinationStatuses destinationStatuses = (DestinationStatuses) other;
        return Intrinsics.areEqual(this.destinationUri, destinationStatuses.destinationUri) && Intrinsics.areEqual(this.imagesCompleted, destinationStatuses.imagesCompleted) && Intrinsics.areEqual(this.transmissionStatus, destinationStatuses.transmissionStatus);
    }

    public int hashCode() {
        URI uri = this.destinationUri;
        int iHashCode = (uri != null ? uri.hashCode() : 0) * 31;
        Integer num = this.imagesCompleted;
        int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
        TransmissionStatus transmissionStatus2 = this.transmissionStatus;
        return iHashCode2 + (transmissionStatus2 != null ? transmissionStatus2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public DestinationStatuses(URI uri, Integer num, TransmissionStatus transmissionStatus2) {
        this.destinationUri = uri;
        this.imagesCompleted = num;
        this.transmissionStatus = transmissionStatus2;
    }

    public DestinationStatuses(URI uri, Integer num, TransmissionStatus transmissionStatus2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            uri = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        if ((i & 4) != 0) {
            transmissionStatus2 = null;
        }
        this(uri, num, transmissionStatus2);
    }

    public final URI getDestinationUri() {
        return this.destinationUri;
    }

    public final void setDestinationUri(URI uri) {
        this.destinationUri = uri;
    }

    public final Integer getImagesCompleted() {
        return this.imagesCompleted;
    }

    public final void setImagesCompleted(Integer num) {
        this.imagesCompleted = num;
    }

    public final TransmissionStatus getTransmissionStatus() {
        return this.transmissionStatus;
    }

    public final void setTransmissionStatus(TransmissionStatus transmissionStatus2) {
        this.transmissionStatus = transmissionStatus2;
    }

    public DestinationStatuses() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        URI uri = this.destinationUri;
        attributeArr[0] = uri != null ? destinationUri.mo418of(uri) : null;
        Integer num = this.imagesCompleted;
        attributeArr[1] = num != null ? imagesCompleted.mo418of(Integer.valueOf(num.intValue())) : null;
        TransmissionStatus transmissionStatus2 = this.transmissionStatus;
        attributeArr[2] = transmissionStatus2 != null ? transmissionStatus.mo418of((Object) transmissionStatus2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/DestinationStatuses$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/DestinationStatuses;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "destinationUri", "Lcom/hp/jipp/encoding/UriType;", "imagesCompleted", "Lcom/hp/jipp/encoding/IntType;", "transmissionStatus", "Lcom/hp/jipp/model/TransmissionStatus$Type;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<DestinationStatuses> {
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
        public DestinationStatuses convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new DestinationStatuses((URI) extractOne(attributes, DestinationStatuses.destinationUri), (Integer) extractOne(attributes, DestinationStatuses.imagesCompleted), (TransmissionStatus) extractOne(attributes, DestinationStatuses.transmissionStatus));
        }

        @Override
        public Class<DestinationStatuses> getCls() {
            return DestinationStatuses.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = DestinationStatuses.class;
        Types = companion;
        destinationUri = new UriType("destination-uri");
        imagesCompleted = new IntType("images-completed");
        transmissionStatus = new TransmissionStatus.Type("transmission-status");
    }

    public String toString() {
        return "DestinationStatuses(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
