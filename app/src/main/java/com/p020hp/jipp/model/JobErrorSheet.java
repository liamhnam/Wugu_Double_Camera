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

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J9\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\b\u0010-\u001a\u00020\u0006H\u0016R\u001e\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0014\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001c\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\b!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006/"}, m1293d2 = {"Lcom/hp/jipp/model/JobErrorSheet;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "jobErrorSheetType", "Lcom/hp/jipp/encoding/KeywordOrName;", "jobErrorSheetWhen", "", "media", "mediaCol", "Lcom/hp/jipp/model/MediaCol;", "(Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/MediaCol;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getJobErrorSheetType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setJobErrorSheetType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "jobErrorSheetType$1", "getJobErrorSheetWhen", "()Ljava/lang/String;", "setJobErrorSheetWhen", "(Ljava/lang/String;)V", "jobErrorSheetWhen$1", "getMedia", "setMedia", "media$1", "getMediaCol", "()Lcom/hp/jipp/model/MediaCol;", "setMediaCol", "(Lcom/hp/jipp/model/MediaCol;)V", "mediaCol$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class JobErrorSheet implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    private static final Class<JobErrorSheet> cls;
    public static final KeywordOrNameType jobErrorSheetType;
    public static final KeywordType jobErrorSheetWhen;
    public static final KeywordOrNameType media;
    public static final AttributeCollection.Type<MediaCol> mediaCol;

    private KeywordOrName jobErrorSheetType;

    private String jobErrorSheetWhen;

    private KeywordOrName media;

    private MediaCol mediaCol;

    public static JobErrorSheet copy$default(JobErrorSheet jobErrorSheet, KeywordOrName keywordOrName, String str, KeywordOrName keywordOrName2, MediaCol mediaCol2, int i, Object obj) {
        if ((i & 1) != 0) {
            keywordOrName = jobErrorSheet.jobErrorSheetType;
        }
        if ((i & 2) != 0) {
            str = jobErrorSheet.jobErrorSheetWhen;
        }
        if ((i & 4) != 0) {
            keywordOrName2 = jobErrorSheet.media;
        }
        if ((i & 8) != 0) {
            mediaCol2 = jobErrorSheet.mediaCol;
        }
        return jobErrorSheet.copy(keywordOrName, str, keywordOrName2, mediaCol2);
    }

    public final KeywordOrName getJobErrorSheetType() {
        return this.jobErrorSheetType;
    }

    public final String getJobErrorSheetWhen() {
        return this.jobErrorSheetWhen;
    }

    public final KeywordOrName getMedia() {
        return this.media;
    }

    public final MediaCol getMediaCol() {
        return this.mediaCol;
    }

    public final JobErrorSheet copy(KeywordOrName jobErrorSheetType2, String jobErrorSheetWhen2, KeywordOrName media2, MediaCol mediaCol2) {
        return new JobErrorSheet(jobErrorSheetType2, jobErrorSheetWhen2, media2, mediaCol2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JobErrorSheet)) {
            return false;
        }
        JobErrorSheet jobErrorSheet = (JobErrorSheet) other;
        return Intrinsics.areEqual(this.jobErrorSheetType, jobErrorSheet.jobErrorSheetType) && Intrinsics.areEqual(this.jobErrorSheetWhen, jobErrorSheet.jobErrorSheetWhen) && Intrinsics.areEqual(this.media, jobErrorSheet.media) && Intrinsics.areEqual(this.mediaCol, jobErrorSheet.mediaCol);
    }

    public int hashCode() {
        KeywordOrName keywordOrName = this.jobErrorSheetType;
        int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
        String str = this.jobErrorSheetWhen;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName2 = this.media;
        int iHashCode3 = (iHashCode2 + (keywordOrName2 != null ? keywordOrName2.hashCode() : 0)) * 31;
        MediaCol mediaCol2 = this.mediaCol;
        return iHashCode3 + (mediaCol2 != null ? mediaCol2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public JobErrorSheet(KeywordOrName keywordOrName, String str, KeywordOrName keywordOrName2, MediaCol mediaCol2) {
        this.jobErrorSheetType = keywordOrName;
        this.jobErrorSheetWhen = str;
        this.media = keywordOrName2;
        this.mediaCol = mediaCol2;
    }

    public JobErrorSheet(KeywordOrName keywordOrName, String str, KeywordOrName keywordOrName2, MediaCol mediaCol2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            keywordOrName = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            keywordOrName2 = null;
        }
        if ((i & 8) != 0) {
            mediaCol2 = null;
        }
        this(keywordOrName, str, keywordOrName2, mediaCol2);
    }

    public final KeywordOrName getJobErrorSheetType() {
        return this.jobErrorSheetType;
    }

    public final void setJobErrorSheetType(KeywordOrName keywordOrName) {
        this.jobErrorSheetType = keywordOrName;
    }

    public final String getJobErrorSheetWhen() {
        return this.jobErrorSheetWhen;
    }

    public final void setJobErrorSheetWhen(String str) {
        this.jobErrorSheetWhen = str;
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

    public JobErrorSheet() {
        this(null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[4];
        KeywordOrName keywordOrName = this.jobErrorSheetType;
        attributeArr[0] = keywordOrName != null ? jobErrorSheetType.mo418of(keywordOrName) : null;
        String str = this.jobErrorSheetWhen;
        attributeArr[1] = str != null ? jobErrorSheetWhen.mo418of(str) : null;
        KeywordOrName keywordOrName2 = this.media;
        attributeArr[2] = keywordOrName2 != null ? media.mo418of(keywordOrName2) : null;
        MediaCol mediaCol2 = this.mediaCol;
        attributeArr[3] = mediaCol2 != null ? mediaCol.mo418of(mediaCol2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/JobErrorSheet$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/JobErrorSheet;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "jobErrorSheetType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "jobErrorSheetWhen", "Lcom/hp/jipp/encoding/KeywordType;", "media", "mediaCol", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/MediaCol;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<JobErrorSheet> {
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
        public JobErrorSheet convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new JobErrorSheet((KeywordOrName) extractOne(attributes, JobErrorSheet.jobErrorSheetType), (String) extractOne(attributes, JobErrorSheet.jobErrorSheetWhen), (KeywordOrName) extractOne(attributes, JobErrorSheet.media), (MediaCol) extractOne(attributes, JobErrorSheet.mediaCol));
        }

        @Override
        public Class<JobErrorSheet> getCls() {
            return JobErrorSheet.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = JobErrorSheet.class;
        Types = companion;
        jobErrorSheetType = new KeywordOrNameType("job-error-sheet-type");
        jobErrorSheetWhen = new KeywordType("job-error-sheet-when");
        media = new KeywordOrNameType("media");
        mediaCol = new AttributeCollection.Type<>("media-col", MediaCol.INSTANCE);
    }

    public String toString() {
        return "JobErrorSheet(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
