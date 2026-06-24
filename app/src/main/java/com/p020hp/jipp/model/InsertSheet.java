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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 02\u00020\u0001:\u00010B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0010\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J>\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020\u0004HÖ\u0001J\b\u0010.\u001a\u00020/H\u0016R\u001e\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0015\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00061"}, m1293d2 = {"Lcom/hp/jipp/model/InsertSheet;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "insertAfterPageNumber", "", "insertCount", "media", "Lcom/hp/jipp/encoding/KeywordOrName;", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getInsertAfterPageNumber", "()Ljava/lang/Integer;", "setInsertAfterPageNumber", "(Ljava/lang/Integer;)V", "insertAfterPageNumber$1", "Ljava/lang/Integer;", "getInsertCount", "setInsertCount", "insertCount$1", "getMedia", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setMedia", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;)Lcom/hp/jipp/model/InsertSheet;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class InsertSheet implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<InsertSheet> cls;
    public static final IntType insertAfterPageNumber;
    public static final IntType insertCount;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;

    private Integer insertAfterPageNumber;

    private Integer insertCount;

    private KeywordOrName media;

    private MediaCol mediaCol;

    public static InsertSheet copy$default(InsertSheet insertSheet, Integer num, Integer num2, KeywordOrName keywordOrName, MediaCol mediaCol2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = insertSheet.insertAfterPageNumber;
        }
        if ((i & 2) != 0) {
            num2 = insertSheet.insertCount;
        }
        if ((i & 4) != 0) {
            keywordOrName = insertSheet.media;
        }
        if ((i & 8) != 0) {
            mediaCol2 = insertSheet.mediaCol;
        }
        return insertSheet.copy(num, num2, keywordOrName, mediaCol2);
    }

    public final Integer getInsertAfterPageNumber() {
        return this.insertAfterPageNumber;
    }

    public final Integer getInsertCount() {
        return this.insertCount;
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final InsertSheet copy(Integer insertAfterPageNumber2, Integer insertCount2, KeywordOrName media2, MediaCol mediaCol2) {
        return new InsertSheet(insertAfterPageNumber2, insertCount2, media2, mediaCol2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InsertSheet)) {
            return false;
        }
        InsertSheet insertSheet = (InsertSheet) other;
        return Intrinsics.areEqual(this.insertAfterPageNumber, insertSheet.insertAfterPageNumber) && Intrinsics.areEqual(this.insertCount, insertSheet.insertCount) && Intrinsics.areEqual(this.media, insertSheet.media) && Intrinsics.areEqual(this.mediaCol, insertSheet.mediaCol);
    }

    public int hashCode() {
        Integer num = this.insertAfterPageNumber;
        int iHashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.insertCount;
        int iHashCode2 = (iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName = this.media;
        int iHashCode3 = (iHashCode2 + (keywordOrName != null ? keywordOrName.hashCode() : 0)) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        return iHashCode3 + (mediaCol2 != null ? mediaCol2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public InsertSheet(Integer num, Integer num2, KeywordOrName keywordOrName, MediaCol mediaCol2) {
        this.insertAfterPageNumber = num;
        this.insertCount = num2;
        this.media = keywordOrName;
        this.mediaCol = mediaCol2;
    }

    public InsertSheet(Integer num, Integer num2, KeywordOrName keywordOrName, MediaCol mediaCol2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        if ((i & 4) != 0) {
            keywordOrName = null;
        }
        if ((i & 8) != 0) {
            mediaCol2 = null;
        }
        this(num, num2, keywordOrName, mediaCol2);
    }

    public final Integer getInsertAfterPageNumber() {
        return this.insertAfterPageNumber;
    }

    public final void setInsertAfterPageNumber(Integer num) {
        this.insertAfterPageNumber = num;
    }

    public final Integer getInsertCount() {
        return this.insertCount;
    }

    public final void setInsertCount(Integer num) {
        this.insertCount = num;
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

    public InsertSheet() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        Integer num = this.insertAfterPageNumber;
        attributeArr[0] = num != null ? insertAfterPageNumber.mo418of(Integer.valueOf(num.intValue())) : null;
        Integer num2 = this.insertCount;
        attributeArr[1] = num2 != null ? insertCount.mo418of(Integer.valueOf(num2.intValue())) : null;
        KeywordOrName keywordOrName = this.media;
        attributeArr[2] = keywordOrName != null ? media.mo418of(keywordOrName) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[3] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/InsertSheet$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/InsertSheet;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "insertAfterPageNumber", "Lcom/hp/jipp/encoding/IntType;", "insertCount", "media", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<InsertSheet> {
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
        public InsertSheet convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new InsertSheet((Integer) extractOne(attributes, InsertSheet.insertAfterPageNumber), (Integer) extractOne(attributes, InsertSheet.insertCount), (KeywordOrName) extractOne(attributes, InsertSheet.media), (MediaCol) extractOne(attributes, InsertSheet.mediaCol));
        }

        @Override
        public Class<InsertSheet> getCls() {
            return InsertSheet.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = InsertSheet.class;
        Types = companion;
        insertAfterPageNumber = new IntType("insert-after-page-number");
        insertCount = new IntType("insert-count");
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
    }

    public String toString() {
        return "InsertSheet(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
