package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010!\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001aJ2\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\b\u0010)\u001a\u00020*H\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R \u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006,"}, m1293d2 = {"Lcom/hp/jipp/model/ProofPrint;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "media", "Lcom/hp/jipp/encoding/KeywordOrName;", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "proofPrintCopies", "", "(Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getMedia", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setMedia", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "getProofPrintCopies", "()Ljava/lang/Integer;", "setProofPrintCopies", "(Ljava/lang/Integer;)V", "proofPrintCopies$1", "Ljava/lang/Integer;", "component1", "component2", "component3", PrinterServiceType.copy, "(Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;Ljava/lang/Integer;)Lcom/hp/jipp/model/ProofPrint;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class ProofPrint implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<ProofPrint> cls;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;
    public static final IntType proofPrintCopies;

    private KeywordOrName media;

    private MediaCol mediaCol;

    private Integer proofPrintCopies;

    public static ProofPrint copy$default(ProofPrint proofPrint, KeywordOrName keywordOrName, MediaCol mediaCol2, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            keywordOrName = proofPrint.media;
        }
        if ((i & 2) != 0) {
            mediaCol2 = proofPrint.mediaCol;
        }
        if ((i & 4) != 0) {
            num = proofPrint.proofPrintCopies;
        }
        return proofPrint.copy(keywordOrName, mediaCol2, num);
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final Integer getProofPrintCopies() {
        return this.proofPrintCopies;
    }

    public final ProofPrint copy(KeywordOrName media2, MediaCol mediaCol2, Integer proofPrintCopies2) {
        return new ProofPrint(media2, mediaCol2, proofPrintCopies2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProofPrint)) {
            return false;
        }
        ProofPrint proofPrint = (ProofPrint) other;
        return Intrinsics.areEqual(this.media, proofPrint.media) && Intrinsics.areEqual(this.mediaCol, proofPrint.mediaCol) && Intrinsics.areEqual(this.proofPrintCopies, proofPrint.proofPrintCopies);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.media;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        int iHashCode2 = (iHashCode + (mediaCol2 != null ? mediaCol2.hashCode() : 0)) * 31;
        Integer num = this.proofPrintCopies;
        return iHashCode2 + (num != null ? num.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public ProofPrint(KeywordOrName keywordOrName, MediaCol mediaCol2, Integer num) {
        this.media = keywordOrName;
        this.mediaCol = mediaCol2;
        this.proofPrintCopies = num;
    }

    public ProofPrint(KeywordOrName keywordOrName, MediaCol mediaCol2, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            keywordOrName = null;
        }
        if ((i & 2) != 0) {
            mediaCol2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        this(keywordOrName, mediaCol2, num);
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final void setMedia(KeywordOrName keywordOrName) {
        this.media = keywordOrName;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final void setMediaCol(MediaCol mediaCol2) {
        this.mediaCol = mediaCol2;
    }

    public final Integer getProofPrintCopies() {
        return this.proofPrintCopies;
    }

    public final void setProofPrintCopies(Integer num) {
        this.proofPrintCopies = num;
    }

    public ProofPrint() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        KeywordOrName keywordOrName = this.media;
        attributeArr[0] = keywordOrName != null ? media.mo418of(keywordOrName) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[1] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        Integer num = this.proofPrintCopies;
        attributeArr[2] = num != null ? proofPrintCopies.mo418of(Integer.valueOf(num.intValue())) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00022\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/jipp/model/ProofPrint$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/ProofPrint;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "media", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "proofPrintCopies", "Lcom/hp/jipp/encoding/IntType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<ProofPrint> {
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
        public ProofPrint convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new ProofPrint((KeywordOrName) extractOne(attributes, ProofPrint.media), (MediaCol) extractOne(attributes, ProofPrint.mediaCol), (Integer) extractOne(attributes, ProofPrint.proofPrintCopies));
        }

        @Override
        public Class<ProofPrint> getCls() {
            return ProofPrint.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = ProofPrint.class;
        Types = companion;
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
        proofPrintCopies = new IntType("proof-print-copies");
    }

    public String toString() {
        return "ProofPrint(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
