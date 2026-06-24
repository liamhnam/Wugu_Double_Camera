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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J-\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\b\u0010%\u001a\u00020&H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0015\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/model/JobSheetsCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "jobSheets", "Lcom/hp/jipp/encoding/KeywordOrName;", "media", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "(Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getJobSheets", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setJobSheets", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "jobSheets$1", "getMedia", "setMedia", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "component1", "component2", "component3", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobSheetsCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<JobSheetsCol> cls;
    public static final KeywordOrNameType jobSheets;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;

    private KeywordOrName jobSheets;

    private KeywordOrName media;

    private MediaCol mediaCol;

    public static JobSheetsCol copy$default(JobSheetsCol jobSheetsCol, KeywordOrName keywordOrName, KeywordOrName keywordOrName2, MediaCol mediaCol2, int i, Object obj) {
        if ((i & 1) != 0) {
            keywordOrName = jobSheetsCol.jobSheets;
        }
        if ((i & 2) != 0) {
            keywordOrName2 = jobSheetsCol.media;
        }
        if ((i & 4) != 0) {
            mediaCol2 = jobSheetsCol.mediaCol;
        }
        return jobSheetsCol.copy(keywordOrName, keywordOrName2, mediaCol2);
    }

    public final KeywordOrName getJobSheets() {
        return this.jobSheets;
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final JobSheetsCol copy(KeywordOrName jobSheets2, KeywordOrName media2, MediaCol mediaCol2) {
        return new JobSheetsCol(jobSheets2, media2, mediaCol2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobSheetsCol)) {
            return false;
        }
        JobSheetsCol jobSheetsCol = (JobSheetsCol) other;
        return Intrinsics.areEqual(this.jobSheets, jobSheetsCol.jobSheets) && Intrinsics.areEqual(this.media, jobSheetsCol.media) && Intrinsics.areEqual(this.mediaCol, jobSheetsCol.mediaCol);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.jobSheets;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        KeywordOrName keywordOrName2 = this.media;
        int iHashCode2 = (iHashCode + (keywordOrName2 != null ? keywordOrName2.hashCode() : 0)) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        return iHashCode2 + (mediaCol2 != null ? mediaCol2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobSheetsCol(KeywordOrName keywordOrName, KeywordOrName keywordOrName2, MediaCol mediaCol2) {
        this.jobSheets = keywordOrName;
        this.media = keywordOrName2;
        this.mediaCol = mediaCol2;
    }

    public JobSheetsCol(KeywordOrName keywordOrName, KeywordOrName keywordOrName2, MediaCol mediaCol2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            keywordOrName = null;
        }
        if ((i & 2) != 0) {
            keywordOrName2 = null;
        }
        if ((i & 4) != 0) {
            mediaCol2 = null;
        }
        this(keywordOrName, keywordOrName2, mediaCol2);
    }

    public final KeywordOrName getJobSheets() {
        return this.jobSheets;
    }

    public final void setJobSheets(KeywordOrName keywordOrName) {
        this.jobSheets = keywordOrName;
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

    public JobSheetsCol() {
        this(null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[3];
        KeywordOrName keywordOrName = this.jobSheets;
        attributeArr[0] = keywordOrName != null ? jobSheets.mo418of(keywordOrName) : null;
        KeywordOrName keywordOrName2 = this.media;
        attributeArr[1] = keywordOrName2 != null ? media.mo418of(keywordOrName2) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[2] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/JobSheetsCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobSheetsCol;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "jobSheets", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "media", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobSheetsCol> {
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
        public JobSheetsCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new JobSheetsCol((KeywordOrName) extractOne(attributes, JobSheetsCol.jobSheets), (KeywordOrName) extractOne(attributes, JobSheetsCol.media), (MediaCol) extractOne(attributes, JobSheetsCol.mediaCol));
        }

        @Override
        public Class<JobSheetsCol> getCls() {
            return JobSheetsCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobSheetsCol.class;
        Types = companion;
        jobSheets = new KeywordOrNameType("job-sheets");
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
    }

    public String toString() {
        return "JobSheetsCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
