package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.KeywordOrNameType;
import com.p020hp.jipp.util.PrettyPrinter;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003J9\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020)HÖ\u0001J\b\u0010*\u001a\u00020+H\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\f0\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0019\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006-"}, m1293d2 = {"Lcom/hp/jipp/model/JobAccountingSheets;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "jobAccountingOutputBin", "Lcom/hp/jipp/encoding/KeywordOrName;", "jobAccountingSheetsType", "media", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "(Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getJobAccountingOutputBin", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setJobAccountingOutputBin", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "jobAccountingOutputBin$1", "getJobAccountingSheetsType", "setJobAccountingSheetsType", "jobAccountingSheetsType$1", "getMedia", "setMedia", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobAccountingSheets implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<JobAccountingSheets> cls;
    public static final KeywordOrNameType jobAccountingOutputBin;
    public static final KeywordOrNameType jobAccountingSheetsType;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;

    private KeywordOrName jobAccountingOutputBin;

    private KeywordOrName jobAccountingSheetsType;

    private KeywordOrName media;

    private MediaCol mediaCol;

    public static JobAccountingSheets copy$default(JobAccountingSheets jobAccountingSheets, KeywordOrName keywordOrName, KeywordOrName keywordOrName2, KeywordOrName keywordOrName3, MediaCol mediaCol2, int i, Object obj) {
        if ((i & 1) != 0) {
            keywordOrName = jobAccountingSheets.jobAccountingOutputBin;
        }
        if ((i & 2) != 0) {
            keywordOrName2 = jobAccountingSheets.jobAccountingSheetsType;
        }
        if ((i & 4) != 0) {
            keywordOrName3 = jobAccountingSheets.media;
        }
        if ((i & 8) != 0) {
            mediaCol2 = jobAccountingSheets.mediaCol;
        }
        return jobAccountingSheets.copy(keywordOrName, keywordOrName2, keywordOrName3, mediaCol2);
    }

    public final KeywordOrName getJobAccountingOutputBin() {
        return this.jobAccountingOutputBin;
    }

    public final KeywordOrName getJobAccountingSheetsType() {
        return this.jobAccountingSheetsType;
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final JobAccountingSheets copy(KeywordOrName jobAccountingOutputBin2, KeywordOrName jobAccountingSheetsType2, KeywordOrName media2, MediaCol mediaCol2) {
        return new JobAccountingSheets(jobAccountingOutputBin2, jobAccountingSheetsType2, media2, mediaCol2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobAccountingSheets)) {
            return false;
        }
        JobAccountingSheets jobAccountingSheets = (JobAccountingSheets) other;
        return Intrinsics.areEqual(this.jobAccountingOutputBin, jobAccountingSheets.jobAccountingOutputBin) && Intrinsics.areEqual(this.jobAccountingSheetsType, jobAccountingSheets.jobAccountingSheetsType) && Intrinsics.areEqual(this.media, jobAccountingSheets.media) && Intrinsics.areEqual(this.mediaCol, jobAccountingSheets.mediaCol);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.jobAccountingOutputBin;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        KeywordOrName keywordOrName2 = this.jobAccountingSheetsType;
        int iHashCode2 = (iHashCode + (keywordOrName2 != null ? keywordOrName2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName3 = this.media;
        int iHashCode3 = (iHashCode2 + (keywordOrName3 != null ? keywordOrName3.hashCode() : 0)) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        return iHashCode3 + (mediaCol2 != null ? mediaCol2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobAccountingSheets(KeywordOrName keywordOrName, KeywordOrName keywordOrName2, KeywordOrName keywordOrName3, MediaCol mediaCol2) {
        this.jobAccountingOutputBin = keywordOrName;
        this.jobAccountingSheetsType = keywordOrName2;
        this.media = keywordOrName3;
        this.mediaCol = mediaCol2;
    }

    public JobAccountingSheets(KeywordOrName keywordOrName, KeywordOrName keywordOrName2, KeywordOrName keywordOrName3, MediaCol mediaCol2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            keywordOrName = null;
        }
        if ((i & 2) != 0) {
            keywordOrName2 = null;
        }
        if ((i & 4) != 0) {
            keywordOrName3 = null;
        }
        if ((i & 8) != 0) {
            mediaCol2 = null;
        }
        this(keywordOrName, keywordOrName2, keywordOrName3, mediaCol2);
    }

    public final KeywordOrName getJobAccountingOutputBin() {
        return this.jobAccountingOutputBin;
    }

    public final void setJobAccountingOutputBin(KeywordOrName keywordOrName) {
        this.jobAccountingOutputBin = keywordOrName;
    }

    public final KeywordOrName getJobAccountingSheetsType() {
        return this.jobAccountingSheetsType;
    }

    public final void setJobAccountingSheetsType(KeywordOrName keywordOrName) {
        this.jobAccountingSheetsType = keywordOrName;
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

    public JobAccountingSheets() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        KeywordOrName keywordOrName = this.jobAccountingOutputBin;
        attributeArr[0] = keywordOrName != null ? jobAccountingOutputBin.mo418of(keywordOrName) : null;
        KeywordOrName keywordOrName2 = this.jobAccountingSheetsType;
        attributeArr[1] = keywordOrName2 != null ? jobAccountingSheetsType.mo418of(keywordOrName2) : null;
        KeywordOrName keywordOrName3 = this.media;
        attributeArr[2] = keywordOrName3 != null ? media.mo418of(keywordOrName3) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[3] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00022\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/jipp/model/JobAccountingSheets$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobAccountingSheets;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "jobAccountingOutputBin", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "jobAccountingSheetsType", "media", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobAccountingSheets> {
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
        public JobAccountingSheets convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new JobAccountingSheets((KeywordOrName) extractOne(attributes, JobAccountingSheets.jobAccountingOutputBin), (KeywordOrName) extractOne(attributes, JobAccountingSheets.jobAccountingSheetsType), (KeywordOrName) extractOne(attributes, JobAccountingSheets.media), (MediaCol) extractOne(attributes, JobAccountingSheets.mediaCol));
        }

        @Override
        public Class<JobAccountingSheets> getCls() {
            return JobAccountingSheets.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobAccountingSheets.class;
        Types = companion;
        jobAccountingOutputBin = new KeywordOrNameType("job-accounting-output-bin");
        jobAccountingSheetsType = new KeywordOrNameType("job-accounting-sheets-type");
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
    }

    public String toString() {
        return "JobAccountingSheets(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
