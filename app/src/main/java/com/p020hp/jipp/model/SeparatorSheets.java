package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.encoding.KeywordType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0011\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J3\u0010 \u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\b\u0010'\u001a\u00020\tH\u0016R\u001e\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001c\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u001b¨\u0006)"}, m1293d2 = {"Lcom/hp/jipp/model/SeparatorSheets;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "media", "Lcom/hp/jipp/encoding/KeywordOrName;", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "separatorSheetsType", "", "", "(Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;Ljava/util/List;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getMedia", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setMedia", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "getSeparatorSheetsType", "setSeparatorSheetsType", "(Ljava/util/List;)V", "separatorSheetsType$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class SeparatorSheets implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<SeparatorSheets> cls;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;
    public static final KeywordType.Set separatorSheetsType;

    private KeywordOrName media;

    private MediaCol mediaCol;

    private List<String> separatorSheetsType;

    public static SeparatorSheets copy$default(SeparatorSheets separatorSheets, KeywordOrName keywordOrName, MediaCol mediaCol2, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            keywordOrName = separatorSheets.media;
        }
        if ((i & 2) != 0) {
            mediaCol2 = separatorSheets.mediaCol;
        }
        if ((i & 4) != 0) {
            list = separatorSheets.separatorSheetsType;
        }
        return separatorSheets.copy(keywordOrName, mediaCol2, list);
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final List<String> component3() {
        return this.separatorSheetsType;
    }

    public final SeparatorSheets copy(KeywordOrName media2, MediaCol mediaCol2, List<String> separatorSheetsType2) {
        return new SeparatorSheets(media2, mediaCol2, separatorSheetsType2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SeparatorSheets)) {
            return false;
        }
        SeparatorSheets separatorSheets = (SeparatorSheets) other;
        return Intrinsics.areEqual(this.media, separatorSheets.media) && Intrinsics.areEqual(this.mediaCol, separatorSheets.mediaCol) && Intrinsics.areEqual(this.separatorSheetsType, separatorSheets.separatorSheetsType);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.media;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        int iHashCode2 = (iHashCode + (mediaCol2 != null ? mediaCol2.hashCode() : 0)) * 31;
        List<String> list = this.separatorSheetsType;
        return iHashCode2 + (list != null ? list.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public SeparatorSheets(KeywordOrName keywordOrName, MediaCol mediaCol2, List<String> list) {
        this.media = keywordOrName;
        this.mediaCol = mediaCol2;
        this.separatorSheetsType = list;
    }

    public SeparatorSheets(KeywordOrName keywordOrName, MediaCol mediaCol2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            keywordOrName = null;
        }
        if ((i & 2) != 0) {
            mediaCol2 = null;
        }
        if ((i & 4) != 0) {
            list = null;
        }
        this(keywordOrName, mediaCol2, list);
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

    public final List<String> getSeparatorSheetsType() {
        return this.separatorSheetsType;
    }

    public final void setSeparatorSheetsType(List<String> list) {
        this.separatorSheetsType = list;
    }

    public SeparatorSheets() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        KeywordOrName keywordOrName = this.media;
        attributeArr[0] = keywordOrName != null ? media.mo418of(keywordOrName) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[1] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        List<String> list = this.separatorSheetsType;
        attributeArr[2] = list != null ? separatorSheetsType.mo417of((Iterable<? extends String>) list) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00022\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/jipp/model/SeparatorSheets$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/SeparatorSheets;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "media", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "separatorSheetsType", "Lcom/hp/jipp/encoding/KeywordType$Set;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<SeparatorSheets> {
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
        public SeparatorSheets convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new SeparatorSheets((KeywordOrName) extractOne(attributes, SeparatorSheets.media), (MediaCol) extractOne(attributes, SeparatorSheets.mediaCol), extractAll(attributes, SeparatorSheets.separatorSheetsType));
        }

        @Override
        public Class<SeparatorSheets> getCls() {
            return SeparatorSheets.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = SeparatorSheets.class;
        Types = companion;
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
        separatorSheetsType = new KeywordType.Set("separator-sheets-type");
    }

    public String toString() {
        return "SeparatorSheets(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
