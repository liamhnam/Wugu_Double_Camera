package com.p020hp.jipp.model;

import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeCollection;
import com.p020hp.jipp.encoding.AttributeType;
import com.p020hp.jipp.encoding.IntRangeType;
import com.p020hp.jipp.encoding.IntType;
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
import kotlin.ranges.IntRange;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bS\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\b\u0018\u0000 ~2\u00020\u0001:\u0011{|}~\u007f\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B¹\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000e¢\u0006\u0002\u0010\u001fJ\u000b\u0010e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010f\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010g\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010h\u001a\u0004\u0018\u00010\u001aHÆ\u0003J\u000b\u0010i\u001a\u0004\u0018\u00010\u001cHÆ\u0003J\u0011\u0010j\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000eHÆ\u0003J\u000b\u0010k\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010l\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010m\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010n\u001a\u0004\u0018\u00010\fHÆ\u0003J\u0011\u0010o\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eHÆ\u0003J\u000b\u0010p\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010r\u001a\u0004\u0018\u00010\u0014HÆ\u0003J½\u0001\u0010s\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0010\b\u0002\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000eHÆ\u0001J\u0013\u0010t\u001a\u00020u2\b\u0010v\u001a\u0004\u0018\u00010wHÖ\u0003J\t\u0010x\u001a\u00020yHÖ\u0001J\b\u0010z\u001a\u00020\u0018H\u0016R\u001e\u0010 \u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030!0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b(\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b-\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b2\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\b7\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\b<\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R$\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\b@\u001a\u0004\b=\u0010#\"\u0004\b>\u0010?R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\bC\u001a\u0004\bA\u00109\"\u0004\bB\u0010;R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\bH\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\bM\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u0010\n\u0002\bR\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001e\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e¢\u0006\u0010\n\u0002\bW\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u0010\n\u0002\b\\\u001a\u0004\bX\u0010Y\"\u0004\bZ\u0010[R\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u0010\n\u0002\ba\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R$\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u000eX\u0086\u000e¢\u0006\u0010\n\u0002\bd\u001a\u0004\bb\u0010#\"\u0004\bc\u0010?¨\u0006\u0086\u0001"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "baling", "Lcom/hp/jipp/model/FinishingsCol$Baling;", "binding", "Lcom/hp/jipp/model/FinishingsCol$Binding;", "coating", "Lcom/hp/jipp/model/FinishingsCol$Coating;", "covering", "Lcom/hp/jipp/model/FinishingsCol$Covering;", "finishingTemplate", "Lcom/hp/jipp/encoding/KeywordOrName;", "folding", "", "Lcom/hp/jipp/model/FinishingsCol$Folding;", "impositionTemplate", "laminating", "Lcom/hp/jipp/model/FinishingsCol$Laminating;", "mediaSheetsSupported", "Lkotlin/ranges/IntRange;", "mediaSize", "Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "mediaSizeName", "", "punching", "Lcom/hp/jipp/model/FinishingsCol$Punching;", "stitching", "Lcom/hp/jipp/model/FinishingsCol$Stitching;", "trimming", "Lcom/hp/jipp/model/FinishingsCol$Trimming;", "(Lcom/hp/jipp/model/FinishingsCol$Baling;Lcom/hp/jipp/model/FinishingsCol$Binding;Lcom/hp/jipp/model/FinishingsCol$Coating;Lcom/hp/jipp/model/FinishingsCol$Covering;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/util/List;Lcom/hp/jipp/encoding/KeywordOrName;Lcom/hp/jipp/model/FinishingsCol$Laminating;Lkotlin/ranges/IntRange;Lcom/hp/jipp/model/FinishingsCol$MediaSize;Ljava/lang/String;Lcom/hp/jipp/model/FinishingsCol$Punching;Lcom/hp/jipp/model/FinishingsCol$Stitching;Ljava/util/List;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getBaling", "()Lcom/hp/jipp/model/FinishingsCol$Baling;", "setBaling", "(Lcom/hp/jipp/model/FinishingsCol$Baling;)V", "baling$1", "getBinding", "()Lcom/hp/jipp/model/FinishingsCol$Binding;", "setBinding", "(Lcom/hp/jipp/model/FinishingsCol$Binding;)V", "binding$1", "getCoating", "()Lcom/hp/jipp/model/FinishingsCol$Coating;", "setCoating", "(Lcom/hp/jipp/model/FinishingsCol$Coating;)V", "coating$1", "getCovering", "()Lcom/hp/jipp/model/FinishingsCol$Covering;", "setCovering", "(Lcom/hp/jipp/model/FinishingsCol$Covering;)V", "covering$1", "getFinishingTemplate", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setFinishingTemplate", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "finishingTemplate$1", "getFolding", "setFolding", "(Ljava/util/List;)V", "folding$1", "getImpositionTemplate", "setImpositionTemplate", "impositionTemplate$1", "getLaminating", "()Lcom/hp/jipp/model/FinishingsCol$Laminating;", "setLaminating", "(Lcom/hp/jipp/model/FinishingsCol$Laminating;)V", "laminating$1", "getMediaSheetsSupported", "()Lkotlin/ranges/IntRange;", "setMediaSheetsSupported", "(Lkotlin/ranges/IntRange;)V", "mediaSheetsSupported$1", "getMediaSize", "()Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "setMediaSize", "(Lcom/hp/jipp/model/FinishingsCol$MediaSize;)V", "mediaSize$1", "getMediaSizeName", "()Ljava/lang/String;", "setMediaSizeName", "(Ljava/lang/String;)V", "mediaSizeName$1", "getPunching", "()Lcom/hp/jipp/model/FinishingsCol$Punching;", "setPunching", "(Lcom/hp/jipp/model/FinishingsCol$Punching;)V", "punching$1", "getStitching", "()Lcom/hp/jipp/model/FinishingsCol$Stitching;", "setStitching", "(Lcom/hp/jipp/model/FinishingsCol$Stitching;)V", "stitching$1", "getTrimming", "setTrimming", "trimming$1", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Baling", "Binding", "Coating", "Companion", "Covering", "Folding", "Laminating", "MediaSize", "Punching", "Stitching", "Trimming", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
public final class FinishingsCol implements AttributeCollection {

    public static final Companion INSTANCE;
    public static final Companion Types;
    public static final AttributeCollection.Type<Baling> baling;
    public static final AttributeCollection.Type<Binding> binding;
    private static final Class<FinishingsCol> cls;
    public static final AttributeCollection.Type<Coating> coating;
    public static final AttributeCollection.Type<Covering> covering;
    public static final KeywordOrNameType finishingTemplate;
    public static final AttributeCollection.SetType<Folding> folding;
    public static final KeywordOrNameType impositionTemplate;
    public static final AttributeCollection.Type<Laminating> laminating;
    public static final IntRangeType mediaSheetsSupported;
    public static final AttributeCollection.Type<MediaSize> mediaSize;
    public static final KeywordType mediaSizeName;
    public static final AttributeCollection.Type<Punching> punching;
    public static final AttributeCollection.Type<Stitching> stitching;
    public static final AttributeCollection.SetType<Trimming> trimming;

    private Baling baling;

    private Binding binding;

    private Coating coating;

    private Covering covering;

    private KeywordOrName finishingTemplate;

    private List<Folding> folding;

    private KeywordOrName impositionTemplate;

    private Laminating laminating;

    private IntRange mediaSheetsSupported;

    private MediaSize mediaSize;

    private String mediaSizeName;

    private Punching punching;

    private Stitching stitching;

    private List<Trimming> trimming;

    public final Baling getBaling() {
        return this.baling;
    }

    public final MediaSize getMediaSize() {
        return this.mediaSize;
    }

    public final String getMediaSizeName() {
        return this.mediaSizeName;
    }

    public final Punching getPunching() {
        return this.punching;
    }

    public final Stitching getStitching() {
        return this.stitching;
    }

    public final List<Trimming> component14() {
        return this.trimming;
    }

    public final Binding getBinding() {
        return this.binding;
    }

    public final Coating getCoating() {
        return this.coating;
    }

    public final Covering getCovering() {
        return this.covering;
    }

    public final KeywordOrName getFinishingTemplate() {
        return this.finishingTemplate;
    }

    public final List<Folding> component6() {
        return this.folding;
    }

    public final KeywordOrName getImpositionTemplate() {
        return this.impositionTemplate;
    }

    public final Laminating getLaminating() {
        return this.laminating;
    }

    public final IntRange getMediaSheetsSupported() {
        return this.mediaSheetsSupported;
    }

    public final FinishingsCol copy(Baling baling2, Binding binding2, Coating coating2, Covering covering2, KeywordOrName finishingTemplate2, List<Folding> folding2, KeywordOrName impositionTemplate2, Laminating laminating2, IntRange mediaSheetsSupported2, MediaSize mediaSize2, String mediaSizeName2, Punching punching2, Stitching stitching2, List<Trimming> trimming2) {
        return new FinishingsCol(baling2, binding2, coating2, covering2, finishingTemplate2, folding2, impositionTemplate2, laminating2, mediaSheetsSupported2, mediaSize2, mediaSizeName2, punching2, stitching2, trimming2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FinishingsCol)) {
            return false;
        }
        FinishingsCol finishingsCol = (FinishingsCol) other;
        return Intrinsics.areEqual(this.baling, finishingsCol.baling) && Intrinsics.areEqual(this.binding, finishingsCol.binding) && Intrinsics.areEqual(this.coating, finishingsCol.coating) && Intrinsics.areEqual(this.covering, finishingsCol.covering) && Intrinsics.areEqual(this.finishingTemplate, finishingsCol.finishingTemplate) && Intrinsics.areEqual(this.folding, finishingsCol.folding) && Intrinsics.areEqual(this.impositionTemplate, finishingsCol.impositionTemplate) && Intrinsics.areEqual(this.laminating, finishingsCol.laminating) && Intrinsics.areEqual(this.mediaSheetsSupported, finishingsCol.mediaSheetsSupported) && Intrinsics.areEqual(this.mediaSize, finishingsCol.mediaSize) && Intrinsics.areEqual(this.mediaSizeName, finishingsCol.mediaSizeName) && Intrinsics.areEqual(this.punching, finishingsCol.punching) && Intrinsics.areEqual(this.stitching, finishingsCol.stitching) && Intrinsics.areEqual(this.trimming, finishingsCol.trimming);
    }

    public int hashCode() {
        Baling baling2 = this.baling;
        int iHashCode = (baling2 != null ? baling2.hashCode() : 0) * 31;
        Binding binding2 = this.binding;
        int iHashCode2 = (iHashCode + (binding2 != null ? binding2.hashCode() : 0)) * 31;
        Coating coating2 = this.coating;
        int iHashCode3 = (iHashCode2 + (coating2 != null ? coating2.hashCode() : 0)) * 31;
        Covering covering2 = this.covering;
        int iHashCode4 = (iHashCode3 + (covering2 != null ? covering2.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName = this.finishingTemplate;
        int iHashCode5 = (iHashCode4 + (keywordOrName != null ? keywordOrName.hashCode() : 0)) * 31;
        List<Folding> list = this.folding;
        int iHashCode6 = (iHashCode5 + (list != null ? list.hashCode() : 0)) * 31;
        KeywordOrName keywordOrName2 = this.impositionTemplate;
        int iHashCode7 = (iHashCode6 + (keywordOrName2 != null ? keywordOrName2.hashCode() : 0)) * 31;
        Laminating laminating2 = this.laminating;
        int iHashCode8 = (iHashCode7 + (laminating2 != null ? laminating2.hashCode() : 0)) * 31;
        IntRange intRange = this.mediaSheetsSupported;
        int iHashCode9 = (iHashCode8 + (intRange != null ? intRange.hashCode() : 0)) * 31;
        MediaSize mediaSize2 = this.mediaSize;
        int iHashCode10 = (iHashCode9 + (mediaSize2 != null ? mediaSize2.hashCode() : 0)) * 31;
        String str = this.mediaSizeName;
        int iHashCode11 = (iHashCode10 + (str != null ? str.hashCode() : 0)) * 31;
        Punching punching2 = this.punching;
        int iHashCode12 = (iHashCode11 + (punching2 != null ? punching2.hashCode() : 0)) * 31;
        Stitching stitching2 = this.stitching;
        int iHashCode13 = (iHashCode12 + (stitching2 != null ? stitching2.hashCode() : 0)) * 31;
        List<Trimming> list2 = this.trimming;
        return iHashCode13 + (list2 != null ? list2.hashCode() : 0);
    }

    @Override
    public void print(PrettyPrinter printer) {
        Intrinsics.checkNotNullParameter(printer, "printer");
        AttributeCollection.DefaultImpls.print(this, printer);
    }

    public FinishingsCol(Baling baling2, Binding binding2, Coating coating2, Covering covering2, KeywordOrName keywordOrName, List<Folding> list, KeywordOrName keywordOrName2, Laminating laminating2, IntRange intRange, MediaSize mediaSize2, String str, Punching punching2, Stitching stitching2, List<Trimming> list2) {
        this.baling = baling2;
        this.binding = binding2;
        this.coating = coating2;
        this.covering = covering2;
        this.finishingTemplate = keywordOrName;
        this.folding = list;
        this.impositionTemplate = keywordOrName2;
        this.laminating = laminating2;
        this.mediaSheetsSupported = intRange;
        this.mediaSize = mediaSize2;
        this.mediaSizeName = str;
        this.punching = punching2;
        this.stitching = stitching2;
        this.trimming = list2;
    }

    public FinishingsCol(Baling baling2, Binding binding2, Coating coating2, Covering covering2, KeywordOrName keywordOrName, List list, KeywordOrName keywordOrName2, Laminating laminating2, IntRange intRange, MediaSize mediaSize2, String str, Punching punching2, Stitching stitching2, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        Baling baling3;
        Binding binding3;
        Coating coating3;
        Covering covering3;
        KeywordOrName keywordOrName3;
        List list3;
        KeywordOrName keywordOrName4;
        Laminating laminating3;
        IntRange intRange2;
        MediaSize mediaSize3;
        String str2;
        Punching punching3;
        Stitching stitching3;
        List list4 = null;
        if ((i & 1) != 0) {
            baling3 = null;
        } else {
            baling3 = baling2;
        }
        if ((i & 2) != 0) {
            binding3 = null;
        } else {
            binding3 = binding2;
        }
        if ((i & 4) != 0) {
            coating3 = null;
        } else {
            coating3 = coating2;
        }
        if ((i & 8) != 0) {
            covering3 = null;
        } else {
            covering3 = covering2;
        }
        if ((i & 16) != 0) {
            keywordOrName3 = null;
        } else {
            keywordOrName3 = keywordOrName;
        }
        if ((i & 32) != 0) {
            list3 = null;
        } else {
            list3 = list;
        }
        if ((i & 64) != 0) {
            keywordOrName4 = null;
        } else {
            keywordOrName4 = keywordOrName2;
        }
        if ((i & 128) != 0) {
            laminating3 = null;
        } else {
            laminating3 = laminating2;
        }
        if ((i & 256) != 0) {
            intRange2 = null;
        } else {
            intRange2 = intRange;
        }
        if ((i & 512) != 0) {
            mediaSize3 = null;
        } else {
            mediaSize3 = mediaSize2;
        }
        if ((i & 1024) != 0) {
            str2 = null;
        } else {
            str2 = str;
        }
        if ((i & 2048) != 0) {
            punching3 = null;
        } else {
            punching3 = punching2;
        }
        if ((i & 4096) != 0) {
            stitching3 = null;
        } else {
            stitching3 = stitching2;
        }
        if ((i & 8192) != 0) {
        } else {
            list4 = list2;
        }
        this(baling3, binding3, coating3, covering3, keywordOrName3, list3, keywordOrName4, laminating3, intRange2, mediaSize3, str2, punching3, stitching3, list4);
    }

    public final Baling getBaling() {
        return this.baling;
    }

    public final void setBaling(Baling baling2) {
        this.baling = baling2;
    }

    public final Binding getBinding() {
        return this.binding;
    }

    public final void setBinding(Binding binding2) {
        this.binding = binding2;
    }

    public final Coating getCoating() {
        return this.coating;
    }

    public final void setCoating(Coating coating2) {
        this.coating = coating2;
    }

    public final Covering getCovering() {
        return this.covering;
    }

    public final void setCovering(Covering covering2) {
        this.covering = covering2;
    }

    public final KeywordOrName getFinishingTemplate() {
        return this.finishingTemplate;
    }

    public final void setFinishingTemplate(KeywordOrName keywordOrName) {
        this.finishingTemplate = keywordOrName;
    }

    public final List<Folding> getFolding() {
        return this.folding;
    }

    public final void setFolding(List<Folding> list) {
        this.folding = list;
    }

    public final KeywordOrName getImpositionTemplate() {
        return this.impositionTemplate;
    }

    public final void setImpositionTemplate(KeywordOrName keywordOrName) {
        this.impositionTemplate = keywordOrName;
    }

    public final Laminating getLaminating() {
        return this.laminating;
    }

    public final void setLaminating(Laminating laminating2) {
        this.laminating = laminating2;
    }

    public final IntRange getMediaSheetsSupported() {
        return this.mediaSheetsSupported;
    }

    public final void setMediaSheetsSupported(IntRange intRange) {
        this.mediaSheetsSupported = intRange;
    }

    public final MediaSize getMediaSize() {
        return this.mediaSize;
    }

    public final void setMediaSize(MediaSize mediaSize2) {
        this.mediaSize = mediaSize2;
    }

    public final String getMediaSizeName() {
        return this.mediaSizeName;
    }

    public final void setMediaSizeName(String str) {
        this.mediaSizeName = str;
    }

    public final Punching getPunching() {
        return this.punching;
    }

    public final void setPunching(Punching punching2) {
        this.punching = punching2;
    }

    public final Stitching getStitching() {
        return this.stitching;
    }

    public final void setStitching(Stitching stitching2) {
        this.stitching = stitching2;
    }

    public final List<Trimming> getTrimming() {
        return this.trimming;
    }

    public final void setTrimming(List<Trimming> list) {
        this.trimming = list;
    }

    public FinishingsCol() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public List<Attribute<?>> getAttributes() {
        Attribute[] attributeArr = new Attribute[14];
        Baling baling2 = this.baling;
        attributeArr[0] = baling2 != null ? baling.mo418of(baling2) : null;
        Binding binding2 = this.binding;
        attributeArr[1] = binding2 != null ? binding.mo418of(binding2) : null;
        Coating coating2 = this.coating;
        attributeArr[2] = coating2 != null ? coating.mo418of(coating2) : null;
        Covering covering2 = this.covering;
        attributeArr[3] = covering2 != null ? covering.mo418of(covering2) : null;
        KeywordOrName keywordOrName = this.finishingTemplate;
        attributeArr[4] = keywordOrName != null ? finishingTemplate.mo418of(keywordOrName) : null;
        List<Folding> list = this.folding;
        attributeArr[5] = list != null ? folding.mo417of((Iterable) list) : null;
        KeywordOrName keywordOrName2 = this.impositionTemplate;
        attributeArr[6] = keywordOrName2 != null ? impositionTemplate.mo418of(keywordOrName2) : null;
        Laminating laminating2 = this.laminating;
        attributeArr[7] = laminating2 != null ? laminating.mo418of(laminating2) : null;
        IntRange intRange = this.mediaSheetsSupported;
        attributeArr[8] = intRange != null ? mediaSheetsSupported.mo418of(intRange) : null;
        MediaSize mediaSize2 = this.mediaSize;
        attributeArr[9] = mediaSize2 != null ? mediaSize.mo418of(mediaSize2) : null;
        String str = this.mediaSizeName;
        attributeArr[10] = str != null ? mediaSizeName.mo418of(str) : null;
        Punching punching2 = this.punching;
        attributeArr[11] = punching2 != null ? punching.mo418of(punching2) : null;
        Stitching stitching2 = this.stitching;
        attributeArr[12] = stitching2 != null ? stitching.mo418of(stitching2) : null;
        List<Trimming> list2 = this.trimming;
        attributeArr[13] = list2 != null ? trimming.mo417of((Iterable) list2) : null;
        return CollectionsKt.listOfNotNull((Object[]) attributeArr);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010'\u001a\u00020\u00022\u0010\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0)H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u00020\u001c8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol;", "()V", "Types", "getTypes$annotations", "baling", "Lcom/hp/jipp/encoding/AttributeCollection$Type;", "Lcom/hp/jipp/model/FinishingsCol$Baling;", "binding", "Lcom/hp/jipp/model/FinishingsCol$Binding;", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "coating", "Lcom/hp/jipp/model/FinishingsCol$Coating;", "covering", "Lcom/hp/jipp/model/FinishingsCol$Covering;", "finishingTemplate", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "folding", "Lcom/hp/jipp/encoding/AttributeCollection$SetType;", "Lcom/hp/jipp/model/FinishingsCol$Folding;", "impositionTemplate", "laminating", "Lcom/hp/jipp/model/FinishingsCol$Laminating;", "mediaSheetsSupported", "Lcom/hp/jipp/encoding/IntRangeType;", "mediaSize", "Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "mediaSizeName", "Lcom/hp/jipp/encoding/KeywordType;", "punching", "Lcom/hp/jipp/model/FinishingsCol$Punching;", "stitching", "Lcom/hp/jipp/model/FinishingsCol$Stitching;", "trimming", "Lcom/hp/jipp/model/FinishingsCol$Trimming;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Companion implements AttributeCollection.Converter<FinishingsCol> {
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
        public FinishingsCol convert(List<? extends Attribute<?>> attributes) {
            Intrinsics.checkNotNullParameter(attributes, "attributes");
            return new FinishingsCol((Baling) extractOne(attributes, FinishingsCol.baling), (Binding) extractOne(attributes, FinishingsCol.binding), (Coating) extractOne(attributes, FinishingsCol.coating), (Covering) extractOne(attributes, FinishingsCol.covering), (KeywordOrName) extractOne(attributes, FinishingsCol.finishingTemplate), extractAll(attributes, FinishingsCol.folding), (KeywordOrName) extractOne(attributes, FinishingsCol.impositionTemplate), (Laminating) extractOne(attributes, FinishingsCol.laminating), (IntRange) extractOne(attributes, FinishingsCol.mediaSheetsSupported), (MediaSize) extractOne(attributes, FinishingsCol.mediaSize), (String) extractOne(attributes, FinishingsCol.mediaSizeName), (Punching) extractOne(attributes, FinishingsCol.punching), (Stitching) extractOne(attributes, FinishingsCol.stitching), extractAll(attributes, FinishingsCol.trimming));
        }

        @Override
        public Class<FinishingsCol> getCls() {
            return FinishingsCol.cls;
        }
    }

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        cls = FinishingsCol.class;
        Types = companion;
        baling = new AttributeCollection.Type<>("baling", Baling.INSTANCE);
        binding = new AttributeCollection.Type<>("binding", Binding.INSTANCE);
        coating = new AttributeCollection.Type<>("coating", Coating.INSTANCE);
        covering = new AttributeCollection.Type<>("covering", Covering.INSTANCE);
        finishingTemplate = new KeywordOrNameType("finishing-template");
        folding = new AttributeCollection.SetType<>("folding", Folding.INSTANCE);
        impositionTemplate = new KeywordOrNameType("imposition-template");
        laminating = new AttributeCollection.Type<>("laminating", Laminating.INSTANCE);
        mediaSheetsSupported = new IntRangeType("media-sheets-supported");
        mediaSize = new AttributeCollection.Type<>("media-size", MediaSize.INSTANCE);
        mediaSizeName = new KeywordType("media-size-name");
        punching = new AttributeCollection.Type<>("punching", Punching.INSTANCE);
        stitching = new AttributeCollection.Type<>("stitching", Stitching.INSTANCE);
        trimming = new AttributeCollection.SetType<>("trimming", Trimming.INSTANCE);
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0006H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Baling;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "balingType", "Lcom/hp/jipp/encoding/KeywordOrName;", "balingWhen", "", "(Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getBalingType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setBalingType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "balingType$1", "getBalingWhen", "()Ljava/lang/String;", "setBalingWhen", "(Ljava/lang/String;)V", "balingWhen$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Baling implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        public static final KeywordOrNameType balingType;
        public static final KeywordType balingWhen;
        private static final Class<Baling> cls;

        private KeywordOrName balingType;

        private String balingWhen;

        public static Baling copy$default(Baling baling, KeywordOrName keywordOrName, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                keywordOrName = baling.balingType;
            }
            if ((i & 2) != 0) {
                str = baling.balingWhen;
            }
            return baling.copy(keywordOrName, str);
        }

        public final KeywordOrName getBalingType() {
            return this.balingType;
        }

        public final String getBalingWhen() {
            return this.balingWhen;
        }

        public final Baling copy(KeywordOrName balingType2, String balingWhen2) {
            return new Baling(balingType2, balingWhen2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Baling)) {
                return false;
            }
            Baling baling = (Baling) other;
            return Intrinsics.areEqual(this.balingType, baling.balingType) && Intrinsics.areEqual(this.balingWhen, baling.balingWhen);
        }

        public int hashCode() {
            KeywordOrName keywordOrName = this.balingType;
            int iHashCode = (keywordOrName != null ? keywordOrName.hashCode() : 0) * 31;
            String str = this.balingWhen;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Baling(KeywordOrName keywordOrName, String str) {
            this.balingType = keywordOrName;
            this.balingWhen = str;
        }

        public Baling(KeywordOrName keywordOrName, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                keywordOrName = null;
            }
            if ((i & 2) != 0) {
                str = null;
            }
            this(keywordOrName, str);
        }

        public final KeywordOrName getBalingType() {
            return this.balingType;
        }

        public final void setBalingType(KeywordOrName keywordOrName) {
            this.balingType = keywordOrName;
        }

        public final String getBalingWhen() {
            return this.balingWhen;
        }

        public final void setBalingWhen(String str) {
            this.balingWhen = str;
        }

        public Baling() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[2];
            KeywordOrName keywordOrName = this.balingType;
            attributeArr[0] = keywordOrName != null ? balingType.mo418of(keywordOrName) : null;
            String str = this.balingWhen;
            attributeArr[1] = str != null ? balingWhen.mo418of(str) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Baling$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Baling;", "()V", "Types", "getTypes$annotations", "balingType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "balingWhen", "Lcom/hp/jipp/encoding/KeywordType;", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Baling> {
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
            public Baling convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Baling((KeywordOrName) extractOne(attributes, Baling.balingType), (String) extractOne(attributes, Baling.balingWhen));
            }

            @Override
            public Class<Baling> getCls() {
                return Baling.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Baling.class;
            Types = companion;
            balingType = new KeywordOrNameType("baling-type");
            balingWhen = new KeywordType("baling-when");
        }

        public String toString() {
            return "Baling(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Binding;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "bindingReferenceEdge", "", "bindingType", "Lcom/hp/jipp/encoding/KeywordOrName;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getBindingReferenceEdge", "()Ljava/lang/String;", "setBindingReferenceEdge", "(Ljava/lang/String;)V", "bindingReferenceEdge$1", "getBindingType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setBindingType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "bindingType$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Binding implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        public static final KeywordType bindingReferenceEdge;
        public static final KeywordOrNameType bindingType;
        private static final Class<Binding> cls;

        private String bindingReferenceEdge;

        private KeywordOrName bindingType;

        public static Binding copy$default(Binding binding, String str, KeywordOrName keywordOrName, int i, Object obj) {
            if ((i & 1) != 0) {
                str = binding.bindingReferenceEdge;
            }
            if ((i & 2) != 0) {
                keywordOrName = binding.bindingType;
            }
            return binding.copy(str, keywordOrName);
        }

        public final String getBindingReferenceEdge() {
            return this.bindingReferenceEdge;
        }

        public final KeywordOrName getBindingType() {
            return this.bindingType;
        }

        public final Binding copy(String bindingReferenceEdge2, KeywordOrName bindingType2) {
            return new Binding(bindingReferenceEdge2, bindingType2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Binding)) {
                return false;
            }
            Binding binding = (Binding) other;
            return Intrinsics.areEqual(this.bindingReferenceEdge, binding.bindingReferenceEdge) && Intrinsics.areEqual(this.bindingType, binding.bindingType);
        }

        public int hashCode() {
            String str = this.bindingReferenceEdge;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            KeywordOrName keywordOrName = this.bindingType;
            return iHashCode + (keywordOrName != null ? keywordOrName.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Binding(String str, KeywordOrName keywordOrName) {
            this.bindingReferenceEdge = str;
            this.bindingType = keywordOrName;
        }

        public Binding(String str, KeywordOrName keywordOrName, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                keywordOrName = null;
            }
            this(str, keywordOrName);
        }

        public final String getBindingReferenceEdge() {
            return this.bindingReferenceEdge;
        }

        public final void setBindingReferenceEdge(String str) {
            this.bindingReferenceEdge = str;
        }

        public final KeywordOrName getBindingType() {
            return this.bindingType;
        }

        public final void setBindingType(KeywordOrName keywordOrName) {
            this.bindingType = keywordOrName;
        }

        public Binding() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[2];
            String str = this.bindingReferenceEdge;
            attributeArr[0] = str != null ? bindingReferenceEdge.mo418of(str) : null;
            KeywordOrName keywordOrName = this.bindingType;
            attributeArr[1] = keywordOrName != null ? bindingType.mo418of(keywordOrName) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Binding$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Binding;", "()V", "Types", "getTypes$annotations", "bindingReferenceEdge", "Lcom/hp/jipp/encoding/KeywordType;", "bindingType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Binding> {
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
            public Binding convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Binding((String) extractOne(attributes, Binding.bindingReferenceEdge), (KeywordOrName) extractOne(attributes, Binding.bindingType));
            }

            @Override
            public Class<Binding> getCls() {
                return Binding.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Binding.class;
            Types = companion;
            bindingReferenceEdge = new KeywordType("binding-reference-edge");
            bindingType = new KeywordOrNameType("binding-type");
        }

        public String toString() {
            return "Binding(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Coating;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "coatingSides", "", "coatingType", "Lcom/hp/jipp/encoding/KeywordOrName;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getCoatingSides", "()Ljava/lang/String;", "setCoatingSides", "(Ljava/lang/String;)V", "coatingSides$1", "getCoatingType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setCoatingType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "coatingType$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Coating implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Coating> cls;
        public static final KeywordType coatingSides;
        public static final KeywordOrNameType coatingType;

        private String coatingSides;

        private KeywordOrName coatingType;

        public static Coating copy$default(Coating coating, String str, KeywordOrName keywordOrName, int i, Object obj) {
            if ((i & 1) != 0) {
                str = coating.coatingSides;
            }
            if ((i & 2) != 0) {
                keywordOrName = coating.coatingType;
            }
            return coating.copy(str, keywordOrName);
        }

        public final String getCoatingSides() {
            return this.coatingSides;
        }

        public final KeywordOrName getCoatingType() {
            return this.coatingType;
        }

        public final Coating copy(String coatingSides2, KeywordOrName coatingType2) {
            return new Coating(coatingSides2, coatingType2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Coating)) {
                return false;
            }
            Coating coating = (Coating) other;
            return Intrinsics.areEqual(this.coatingSides, coating.coatingSides) && Intrinsics.areEqual(this.coatingType, coating.coatingType);
        }

        public int hashCode() {
            String str = this.coatingSides;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            KeywordOrName keywordOrName = this.coatingType;
            return iHashCode + (keywordOrName != null ? keywordOrName.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Coating(String str, KeywordOrName keywordOrName) {
            this.coatingSides = str;
            this.coatingType = keywordOrName;
        }

        public Coating(String str, KeywordOrName keywordOrName, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                keywordOrName = null;
            }
            this(str, keywordOrName);
        }

        public final String getCoatingSides() {
            return this.coatingSides;
        }

        public final void setCoatingSides(String str) {
            this.coatingSides = str;
        }

        public final KeywordOrName getCoatingType() {
            return this.coatingType;
        }

        public final void setCoatingType(KeywordOrName keywordOrName) {
            this.coatingType = keywordOrName;
        }

        public Coating() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[2];
            String str = this.coatingSides;
            attributeArr[0] = str != null ? coatingSides.mo418of(str) : null;
            KeywordOrName keywordOrName = this.coatingType;
            attributeArr[1] = keywordOrName != null ? coatingType.mo418of(keywordOrName) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Coating$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Coating;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "coatingSides", "Lcom/hp/jipp/encoding/KeywordType;", "coatingType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Coating> {
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
            public Coating convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Coating((String) extractOne(attributes, Coating.coatingSides), (KeywordOrName) extractOne(attributes, Coating.coatingType));
            }

            @Override
            public Class<Coating> getCls() {
                return Coating.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Coating.class;
            Types = companion;
            coatingSides = new KeywordType("coating-sides");
            coatingType = new KeywordOrNameType("coating-type");
        }

        public String toString() {
            return "Coating(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0011\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001e\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u000e\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0005¨\u0006\u001a"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Covering;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "coveringName", "Lcom/hp/jipp/encoding/KeywordOrName;", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getCoveringName", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setCoveringName", "coveringName$1", "component1", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Covering implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Covering> cls;
        public static final KeywordOrNameType coveringName;

        private KeywordOrName coveringName;

        public static Covering copy$default(Covering covering, KeywordOrName keywordOrName, int i, Object obj) {
            if ((i & 1) != 0) {
                keywordOrName = covering.coveringName;
            }
            return covering.copy(keywordOrName);
        }

        public final KeywordOrName getCoveringName() {
            return this.coveringName;
        }

        public final Covering copy(KeywordOrName coveringName2) {
            return new Covering(coveringName2);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof Covering) && Intrinsics.areEqual(this.coveringName, ((Covering) other).coveringName);
            }
            return true;
        }

        public int hashCode() {
            KeywordOrName keywordOrName = this.coveringName;
            if (keywordOrName != null) {
                return keywordOrName.hashCode();
            }
            return 0;
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Covering(KeywordOrName keywordOrName) {
            this.coveringName = keywordOrName;
        }

        public Covering(KeywordOrName keywordOrName, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                keywordOrName = null;
            }
            this(keywordOrName);
        }

        public final KeywordOrName getCoveringName() {
            return this.coveringName;
        }

        public final void setCoveringName(KeywordOrName keywordOrName) {
            this.coveringName = keywordOrName;
        }

        public Covering() {
            this(null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            KeywordOrName keywordOrName = this.coveringName;
            return CollectionsKt.listOfNotNull(keywordOrName != null ? coveringName.mo418of(keywordOrName) : null);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\f\u001a\u00020\u00022\u0010\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000eH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Covering$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Covering;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "coveringName", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Covering> {
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
            public Covering convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Covering((KeywordOrName) extractOne(attributes, Covering.coveringName));
            }

            @Override
            public Class<Covering> getCls() {
                return Covering.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Covering.class;
            Types = companion;
            coveringName = new KeywordOrNameType("covering-name");
        }

        public String toString() {
            return "Covering(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 '2\u00020\u0001:\u0001'B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B)\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\bJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003J2\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\u0006HÖ\u0001J\b\u0010&\u001a\u00020\u0004H\u0016R\u001e\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001b\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011¨\u0006("}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Folding;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "foldingDirection", "", "foldingOffset", "", "foldingReferenceEdge", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getFoldingDirection", "()Ljava/lang/String;", "setFoldingDirection", "(Ljava/lang/String;)V", "foldingDirection$1", "getFoldingOffset", "()Ljava/lang/Integer;", "setFoldingOffset", "(Ljava/lang/Integer;)V", "foldingOffset$1", "Ljava/lang/Integer;", "getFoldingReferenceEdge", "setFoldingReferenceEdge", "foldingReferenceEdge$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hp/jipp/model/FinishingsCol$Folding;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Folding implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Folding> cls;
        public static final KeywordType foldingDirection;
        public static final IntType foldingOffset;
        public static final KeywordType foldingReferenceEdge;

        private String foldingDirection;

        private Integer foldingOffset;

        private String foldingReferenceEdge;

        public static Folding copy$default(Folding folding, String str, Integer num, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = folding.foldingDirection;
            }
            if ((i & 2) != 0) {
                num = folding.foldingOffset;
            }
            if ((i & 4) != 0) {
                str2 = folding.foldingReferenceEdge;
            }
            return folding.copy(str, num, str2);
        }

        public final String getFoldingDirection() {
            return this.foldingDirection;
        }

        public final Integer getFoldingOffset() {
            return this.foldingOffset;
        }

        public final String getFoldingReferenceEdge() {
            return this.foldingReferenceEdge;
        }

        public final Folding copy(String foldingDirection2, Integer foldingOffset2, String foldingReferenceEdge2) {
            return new Folding(foldingDirection2, foldingOffset2, foldingReferenceEdge2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Folding)) {
                return false;
            }
            Folding folding = (Folding) other;
            return Intrinsics.areEqual(this.foldingDirection, folding.foldingDirection) && Intrinsics.areEqual(this.foldingOffset, folding.foldingOffset) && Intrinsics.areEqual(this.foldingReferenceEdge, folding.foldingReferenceEdge);
        }

        public int hashCode() {
            String str = this.foldingDirection;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            Integer num = this.foldingOffset;
            int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
            String str2 = this.foldingReferenceEdge;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Folding(String str, Integer num, String str2) {
            this.foldingDirection = str;
            this.foldingOffset = num;
            this.foldingReferenceEdge = str2;
        }

        public Folding(String str, Integer num, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                num = null;
            }
            if ((i & 4) != 0) {
                str2 = null;
            }
            this(str, num, str2);
        }

        public final String getFoldingDirection() {
            return this.foldingDirection;
        }

        public final void setFoldingDirection(String str) {
            this.foldingDirection = str;
        }

        public final Integer getFoldingOffset() {
            return this.foldingOffset;
        }

        public final void setFoldingOffset(Integer num) {
            this.foldingOffset = num;
        }

        public final String getFoldingReferenceEdge() {
            return this.foldingReferenceEdge;
        }

        public final void setFoldingReferenceEdge(String str) {
            this.foldingReferenceEdge = str;
        }

        public Folding() {
            this(null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[3];
            String str = this.foldingDirection;
            attributeArr[0] = str != null ? foldingDirection.mo418of(str) : null;
            Integer num = this.foldingOffset;
            attributeArr[1] = num != null ? foldingOffset.mo418of(Integer.valueOf(num.intValue())) : null;
            String str2 = this.foldingReferenceEdge;
            attributeArr[2] = str2 != null ? foldingReferenceEdge.mo418of(str2) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Folding$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Folding;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "foldingDirection", "Lcom/hp/jipp/encoding/KeywordType;", "foldingOffset", "Lcom/hp/jipp/encoding/IntType;", "foldingReferenceEdge", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Folding> {
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
            public Folding convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Folding((String) extractOne(attributes, Folding.foldingDirection), (Integer) extractOne(attributes, Folding.foldingOffset), (String) extractOne(attributes, Folding.foldingReferenceEdge));
            }

            @Override
            public Class<Folding> getCls() {
                return Folding.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Folding.class;
            Types = companion;
            foldingDirection = new KeywordType("folding-direction");
            foldingOffset = new IntType("folding-offset");
            foldingReferenceEdge = new KeywordType("folding-reference-edge");
        }

        public String toString() {
            return "Folding(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J!\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0004H\u0016R\u001e\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\""}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Laminating;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "laminatingSides", "", "laminatingType", "Lcom/hp/jipp/encoding/KeywordOrName;", "(Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getLaminatingSides", "()Ljava/lang/String;", "setLaminatingSides", "(Ljava/lang/String;)V", "laminatingSides$1", "getLaminatingType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setLaminatingType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "laminatingType$1", "component1", "component2", PrinterServiceType.copy, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Laminating implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Laminating> cls;
        public static final KeywordType laminatingSides;
        public static final KeywordOrNameType laminatingType;

        private String laminatingSides;

        private KeywordOrName laminatingType;

        public static Laminating copy$default(Laminating laminating, String str, KeywordOrName keywordOrName, int i, Object obj) {
            if ((i & 1) != 0) {
                str = laminating.laminatingSides;
            }
            if ((i & 2) != 0) {
                keywordOrName = laminating.laminatingType;
            }
            return laminating.copy(str, keywordOrName);
        }

        public final String getLaminatingSides() {
            return this.laminatingSides;
        }

        public final KeywordOrName getLaminatingType() {
            return this.laminatingType;
        }

        public final Laminating copy(String laminatingSides2, KeywordOrName laminatingType2) {
            return new Laminating(laminatingSides2, laminatingType2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Laminating)) {
                return false;
            }
            Laminating laminating = (Laminating) other;
            return Intrinsics.areEqual(this.laminatingSides, laminating.laminatingSides) && Intrinsics.areEqual(this.laminatingType, laminating.laminatingType);
        }

        public int hashCode() {
            String str = this.laminatingSides;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            KeywordOrName keywordOrName = this.laminatingType;
            return iHashCode + (keywordOrName != null ? keywordOrName.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Laminating(String str, KeywordOrName keywordOrName) {
            this.laminatingSides = str;
            this.laminatingType = keywordOrName;
        }

        public Laminating(String str, KeywordOrName keywordOrName, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                str = null;
            }
            if ((i & 2) != 0) {
                keywordOrName = null;
            }
            this(str, keywordOrName);
        }

        public final String getLaminatingSides() {
            return this.laminatingSides;
        }

        public final void setLaminatingSides(String str) {
            this.laminatingSides = str;
        }

        public final KeywordOrName getLaminatingType() {
            return this.laminatingType;
        }

        public final void setLaminatingType(KeywordOrName keywordOrName) {
            this.laminatingType = keywordOrName;
        }

        public Laminating() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[2];
            String str = this.laminatingSides;
            attributeArr[0] = str != null ? laminatingSides.mo418of(str) : null;
            KeywordOrName keywordOrName = this.laminatingType;
            attributeArr[1] = keywordOrName != null ? laminatingType.mo418of(keywordOrName) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Laminating$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Laminating;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "laminatingSides", "Lcom/hp/jipp/encoding/KeywordType;", "laminatingType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Laminating> {
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
            public Laminating convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Laminating((String) extractOne(attributes, Laminating.laminatingSides), (KeywordOrName) extractOne(attributes, Laminating.laminatingType));
            }

            @Override
            public Class<Laminating> getCls() {
                return Laminating.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Laminating.class;
            Types = companion;
            laminatingSides = new KeywordType("laminating-sides");
            laminatingType = new KeywordOrNameType("laminating-type");
        }

        public String toString() {
            return "Laminating(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\rJ&\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u001e\u0010\u0007\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000f¨\u0006!"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "xDimension", "", "yDimension", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getXDimension", "()Ljava/lang/Integer;", "setXDimension", "(Ljava/lang/Integer;)V", "xDimension$1", "Ljava/lang/Integer;", "getYDimension", "setYDimension", "yDimension$1", "component1", "component2", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "equals", "", "other", "", "hashCode", "toString", "", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class MediaSize implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<MediaSize> cls;
        public static final IntType xDimension;
        public static final IntType yDimension;

        private Integer xDimension;

        private Integer yDimension;

        public static MediaSize copy$default(MediaSize mediaSize, Integer num, Integer num2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = mediaSize.xDimension;
            }
            if ((i & 2) != 0) {
                num2 = mediaSize.yDimension;
            }
            return mediaSize.copy(num, num2);
        }

        public final Integer getXDimension() {
            return this.xDimension;
        }

        public final Integer getYDimension() {
            return this.yDimension;
        }

        public final MediaSize copy(Integer xDimension2, Integer yDimension2) {
            return new MediaSize(xDimension2, yDimension2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MediaSize)) {
                return false;
            }
            MediaSize mediaSize = (MediaSize) other;
            return Intrinsics.areEqual(this.xDimension, mediaSize.xDimension) && Intrinsics.areEqual(this.yDimension, mediaSize.yDimension);
        }

        public int hashCode() {
            Integer num = this.xDimension;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            Integer num2 = this.yDimension;
            return iHashCode + (num2 != null ? num2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public MediaSize(Integer num, Integer num2) {
            this.xDimension = num;
            this.yDimension = num2;
        }

        public MediaSize(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                num2 = null;
            }
            this(num, num2);
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

        public MediaSize() {
            this(null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute<Integer> attributeOf;
            Attribute[] attributeArr = new Attribute[2];
            Integer num = this.xDimension;
            Attribute<Integer> attributeOf2 = null;
            if (num != null) {
                attributeOf = xDimension.mo418of(Integer.valueOf(num.intValue()));
            } else {
                attributeOf = null;
            }
            attributeArr[0] = attributeOf;
            Integer num2 = this.yDimension;
            if (num2 != null) {
                attributeOf2 = yDimension.mo418of(Integer.valueOf(num2.intValue()));
            }
            attributeArr[1] = attributeOf2;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\r\u001a\u00020\u00022\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000fH\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$MediaSize$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$MediaSize;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "xDimension", "Lcom/hp/jipp/encoding/IntType;", "yDimension", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<MediaSize> {
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
            public MediaSize convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new MediaSize((Integer) extractOne(attributes, MediaSize.xDimension), (Integer) extractOne(attributes, MediaSize.yDimension));
            }

            @Override
            public Class<MediaSize> getCls() {
                return MediaSize.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = MediaSize.class;
            Types = companion;
            xDimension = new IntType("x-dimension");
            yDimension = new IntType("y-dimension");
        }

        public String toString() {
            return "MediaSize(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B/\u0012\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003J8\u0010 \u001a\u00020\u00002\u0010\b\u0002\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\u0005HÖ\u0001J\b\u0010'\u001a\u00020\bH\u0016R\u001e\u0010\n\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b0\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR$\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0011\u001a\u0004\b\u000e\u0010\r\"\u0004\b\u000f\u0010\u0010R \u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006)"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Punching;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "punchingLocations", "", "", "punchingOffset", "punchingReferenceEdge", "", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getPunchingLocations", "setPunchingLocations", "(Ljava/util/List;)V", "punchingLocations$1", "getPunchingOffset", "()Ljava/lang/Integer;", "setPunchingOffset", "(Ljava/lang/Integer;)V", "punchingOffset$1", "Ljava/lang/Integer;", "getPunchingReferenceEdge", "()Ljava/lang/String;", "setPunchingReferenceEdge", "(Ljava/lang/String;)V", "punchingReferenceEdge$1", "component1", "component2", "component3", PrinterServiceType.copy, "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hp/jipp/model/FinishingsCol$Punching;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Punching implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Punching> cls;
        public static final IntType.Set punchingLocations;
        public static final IntType punchingOffset;
        public static final KeywordType punchingReferenceEdge;

        private List<Integer> punchingLocations;

        private Integer punchingOffset;

        private String punchingReferenceEdge;

        public static Punching copy$default(Punching punching, List list, Integer num, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = punching.punchingLocations;
            }
            if ((i & 2) != 0) {
                num = punching.punchingOffset;
            }
            if ((i & 4) != 0) {
                str = punching.punchingReferenceEdge;
            }
            return punching.copy(list, num, str);
        }

        public final List<Integer> component1() {
            return this.punchingLocations;
        }

        public final Integer getPunchingOffset() {
            return this.punchingOffset;
        }

        public final String getPunchingReferenceEdge() {
            return this.punchingReferenceEdge;
        }

        public final Punching copy(List<Integer> punchingLocations2, Integer punchingOffset2, String punchingReferenceEdge2) {
            return new Punching(punchingLocations2, punchingOffset2, punchingReferenceEdge2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Punching)) {
                return false;
            }
            Punching punching = (Punching) other;
            return Intrinsics.areEqual(this.punchingLocations, punching.punchingLocations) && Intrinsics.areEqual(this.punchingOffset, punching.punchingOffset) && Intrinsics.areEqual(this.punchingReferenceEdge, punching.punchingReferenceEdge);
        }

        public int hashCode() {
            List<Integer> list = this.punchingLocations;
            int iHashCode = (list != null ? list.hashCode() : 0) * 31;
            Integer num = this.punchingOffset;
            int iHashCode2 = (iHashCode + (num != null ? num.hashCode() : 0)) * 31;
            String str = this.punchingReferenceEdge;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Punching(List<Integer> list, Integer num, String str) {
            this.punchingLocations = list;
            this.punchingOffset = num;
            this.punchingReferenceEdge = str;
        }

        public Punching(List list, Integer num, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                list = null;
            }
            if ((i & 2) != 0) {
                num = null;
            }
            if ((i & 4) != 0) {
                str = null;
            }
            this(list, num, str);
        }

        public final List<Integer> getPunchingLocations() {
            return this.punchingLocations;
        }

        public final void setPunchingLocations(List<Integer> list) {
            this.punchingLocations = list;
        }

        public final Integer getPunchingOffset() {
            return this.punchingOffset;
        }

        public final void setPunchingOffset(Integer num) {
            this.punchingOffset = num;
        }

        public final String getPunchingReferenceEdge() {
            return this.punchingReferenceEdge;
        }

        public final void setPunchingReferenceEdge(String str) {
            this.punchingReferenceEdge = str;
        }

        public Punching() {
            this(null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[3];
            List<Integer> list = this.punchingLocations;
            attributeArr[0] = list != null ? punchingLocations.mo417of((Iterable<? extends Integer>) list) : null;
            Integer num = this.punchingOffset;
            attributeArr[1] = num != null ? punchingOffset.mo418of(Integer.valueOf(num.intValue())) : null;
            String str = this.punchingReferenceEdge;
            attributeArr[2] = str != null ? punchingReferenceEdge.mo418of(str) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Punching$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Punching;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "punchingLocations", "Lcom/hp/jipp/encoding/IntType$Set;", "punchingOffset", "Lcom/hp/jipp/encoding/IntType;", "punchingReferenceEdge", "Lcom/hp/jipp/encoding/KeywordType;", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Punching> {
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
            public Punching convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Punching(extractAll(attributes, Punching.punchingLocations), (Integer) extractOne(attributes, Punching.punchingOffset), (String) extractOne(attributes, Punching.punchingReferenceEdge));
            }

            @Override
            public Class<Punching> getCls() {
                return Punching.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Punching.class;
            Types = companion;
            punchingLocations = new IntType.Set("punching-locations");
            punchingOffset = new IntType("punching-offset");
            punchingReferenceEdge = new KeywordType("punching-reference-edge");
        }

        public String toString() {
            return "Punching(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BG\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u000bJ\u0010\u0010%\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010)\u001a\u0004\u0018\u00010\bHÆ\u0003JP\u0010*\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010+J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u00020\u0004HÖ\u0001J\b\u00101\u001a\u00020\bH\u0016R\u001e\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R$\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u0019\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001e\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR \u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\u0015\u001a\u0004\b\u001f\u0010\u0011\"\u0004\b \u0010\u0013R\u001e\u0010\n\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b$\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001d¨\u00063"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Stitching;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "stitchingAngle", "", "stitchingLocations", "", "stitchingMethod", "", "stitchingOffset", "stitchingReferenceEdge", "(Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "attributes", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getStitchingAngle", "()Ljava/lang/Integer;", "setStitchingAngle", "(Ljava/lang/Integer;)V", "stitchingAngle$1", "Ljava/lang/Integer;", "getStitchingLocations", "setStitchingLocations", "(Ljava/util/List;)V", "stitchingLocations$1", "getStitchingMethod", "()Ljava/lang/String;", "setStitchingMethod", "(Ljava/lang/String;)V", "stitchingMethod$1", "getStitchingOffset", "setStitchingOffset", "stitchingOffset$1", "getStitchingReferenceEdge", "setStitchingReferenceEdge", "stitchingReferenceEdge$1", "component1", "component2", "component3", "component4", "component5", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/hp/jipp/model/FinishingsCol$Stitching;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Stitching implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Stitching> cls;
        public static final IntType stitchingAngle;
        public static final IntType.Set stitchingLocations;
        public static final KeywordType stitchingMethod;
        public static final IntType stitchingOffset;
        public static final KeywordType stitchingReferenceEdge;

        private Integer stitchingAngle;

        private List<Integer> stitchingLocations;

        private String stitchingMethod;

        private Integer stitchingOffset;

        private String stitchingReferenceEdge;

        public static Stitching copy$default(Stitching stitching, Integer num, List list, String str, Integer num2, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = stitching.stitchingAngle;
            }
            if ((i & 2) != 0) {
                list = stitching.stitchingLocations;
            }
            List list2 = list;
            if ((i & 4) != 0) {
                str = stitching.stitchingMethod;
            }
            String str3 = str;
            if ((i & 8) != 0) {
                num2 = stitching.stitchingOffset;
            }
            Integer num3 = num2;
            if ((i & 16) != 0) {
                str2 = stitching.stitchingReferenceEdge;
            }
            return stitching.copy(num, list2, str3, num3, str2);
        }

        public final Integer getStitchingAngle() {
            return this.stitchingAngle;
        }

        public final List<Integer> component2() {
            return this.stitchingLocations;
        }

        public final String getStitchingMethod() {
            return this.stitchingMethod;
        }

        public final Integer getStitchingOffset() {
            return this.stitchingOffset;
        }

        public final String getStitchingReferenceEdge() {
            return this.stitchingReferenceEdge;
        }

        public final Stitching copy(Integer stitchingAngle2, List<Integer> stitchingLocations2, String stitchingMethod2, Integer stitchingOffset2, String stitchingReferenceEdge2) {
            return new Stitching(stitchingAngle2, stitchingLocations2, stitchingMethod2, stitchingOffset2, stitchingReferenceEdge2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Stitching)) {
                return false;
            }
            Stitching stitching = (Stitching) other;
            return Intrinsics.areEqual(this.stitchingAngle, stitching.stitchingAngle) && Intrinsics.areEqual(this.stitchingLocations, stitching.stitchingLocations) && Intrinsics.areEqual(this.stitchingMethod, stitching.stitchingMethod) && Intrinsics.areEqual(this.stitchingOffset, stitching.stitchingOffset) && Intrinsics.areEqual(this.stitchingReferenceEdge, stitching.stitchingReferenceEdge);
        }

        public int hashCode() {
            Integer num = this.stitchingAngle;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            List<Integer> list = this.stitchingLocations;
            int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
            String str = this.stitchingMethod;
            int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            Integer num2 = this.stitchingOffset;
            int iHashCode4 = (iHashCode3 + (num2 != null ? num2.hashCode() : 0)) * 31;
            String str2 = this.stitchingReferenceEdge;
            return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Stitching(Integer num, List<Integer> list, String str, Integer num2, String str2) {
            this.stitchingAngle = num;
            this.stitchingLocations = list;
            this.stitchingMethod = str;
            this.stitchingOffset = num2;
            this.stitchingReferenceEdge = str2;
        }

        public Stitching(Integer num, List list, String str, Integer num2, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            List list2;
            String str3;
            Integer num3;
            String str4 = null;
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                list2 = null;
            } else {
                list2 = list;
            }
            if ((i & 4) != 0) {
                str3 = null;
            } else {
                str3 = str;
            }
            if ((i & 8) != 0) {
                num3 = null;
            } else {
                num3 = num2;
            }
            if ((i & 16) != 0) {
            } else {
                str4 = str2;
            }
            this(num, list2, str3, num3, str4);
        }

        public final Integer getStitchingAngle() {
            return this.stitchingAngle;
        }

        public final void setStitchingAngle(Integer num) {
            this.stitchingAngle = num;
        }

        public final List<Integer> getStitchingLocations() {
            return this.stitchingLocations;
        }

        public final void setStitchingLocations(List<Integer> list) {
            this.stitchingLocations = list;
        }

        public final String getStitchingMethod() {
            return this.stitchingMethod;
        }

        public final void setStitchingMethod(String str) {
            this.stitchingMethod = str;
        }

        public final Integer getStitchingOffset() {
            return this.stitchingOffset;
        }

        public final void setStitchingOffset(Integer num) {
            this.stitchingOffset = num;
        }

        public final String getStitchingReferenceEdge() {
            return this.stitchingReferenceEdge;
        }

        public final void setStitchingReferenceEdge(String str) {
            this.stitchingReferenceEdge = str;
        }

        public Stitching() {
            this(null, null, null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[5];
            Integer num = this.stitchingAngle;
            attributeArr[0] = num != null ? stitchingAngle.mo418of(Integer.valueOf(num.intValue())) : null;
            List<Integer> list = this.stitchingLocations;
            attributeArr[1] = list != null ? stitchingLocations.mo417of((Iterable<? extends Integer>) list) : null;
            String str = this.stitchingMethod;
            attributeArr[2] = str != null ? stitchingMethod.mo418of(str) : null;
            Integer num2 = this.stitchingOffset;
            attributeArr[3] = num2 != null ? stitchingOffset.mo418of(Integer.valueOf(num2.intValue())) : null;
            String str2 = this.stitchingReferenceEdge;
            attributeArr[4] = str2 != null ? stitchingReferenceEdge.mo418of(str2) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0012\u001a\u00020\u00022\u0010\u0010\u0013\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00150\u0014H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Stitching$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Stitching;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "stitchingAngle", "Lcom/hp/jipp/encoding/IntType;", "stitchingLocations", "Lcom/hp/jipp/encoding/IntType$Set;", "stitchingMethod", "Lcom/hp/jipp/encoding/KeywordType;", "stitchingOffset", "stitchingReferenceEdge", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Stitching> {
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
            public Stitching convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Stitching((Integer) extractOne(attributes, Stitching.stitchingAngle), extractAll(attributes, Stitching.stitchingLocations), (String) extractOne(attributes, Stitching.stitchingMethod), (Integer) extractOne(attributes, Stitching.stitchingOffset), (String) extractOne(attributes, Stitching.stitchingReferenceEdge));
            }

            @Override
            public Class<Stitching> getCls() {
                return Stitching.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Stitching.class;
            Types = companion;
            stitchingAngle = new IntType("stitching-angle");
            stitchingLocations = new IntType.Set(StitchingSupported.stitchingLocations);
            stitchingMethod = new KeywordType("stitching-method");
            stitchingOffset = new IntType(StitchingSupported.stitchingOffset);
            stitchingReferenceEdge = new KeywordType(StitchingSupported.stitchingReferenceEdge);
        }

        public String toString() {
            return "Stitching(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B5\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u0010\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0011J\u000b\u0010$\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003J>\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020\u0004HÖ\u0001J\b\u0010.\u001a\u00020\u0006H\u0016R\u001e\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\b\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\b\"\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019¨\u00060"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Trimming;", "Lcom/hp/jipp/encoding/AttributeCollection;", "()V", "trimmingOffset", "", "trimmingReferenceEdge", "", "trimmingType", "Lcom/hp/jipp/encoding/KeywordOrName;", "trimmingWhen", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/String;)V", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "getAttributes", "()Ljava/util/List;", "getTrimmingOffset", "()Ljava/lang/Integer;", "setTrimmingOffset", "(Ljava/lang/Integer;)V", "trimmingOffset$1", "Ljava/lang/Integer;", "getTrimmingReferenceEdge", "()Ljava/lang/String;", "setTrimmingReferenceEdge", "(Ljava/lang/String;)V", "trimmingReferenceEdge$1", "getTrimmingType", "()Lcom/hp/jipp/encoding/KeywordOrName;", "setTrimmingType", "(Lcom/hp/jipp/encoding/KeywordOrName;)V", "trimmingType$1", "getTrimmingWhen", "setTrimmingWhen", "trimmingWhen$1", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "(Ljava/lang/Integer;Ljava/lang/String;Lcom/hp/jipp/encoding/KeywordOrName;Ljava/lang/String;)Lcom/hp/jipp/model/FinishingsCol$Trimming;", "equals", "", "other", "", "hashCode", "toString", "Companion", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
    public static final class Trimming implements AttributeCollection {

        public static final Companion INSTANCE;
        public static final Companion Types;
        private static final Class<Trimming> cls;
        public static final IntType trimmingOffset;
        public static final KeywordType trimmingReferenceEdge;
        public static final KeywordOrNameType trimmingType;
        public static final KeywordType trimmingWhen;

        private Integer trimmingOffset;

        private String trimmingReferenceEdge;

        private KeywordOrName trimmingType;

        private String trimmingWhen;

        public static Trimming copy$default(Trimming trimming, Integer num, String str, KeywordOrName keywordOrName, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = trimming.trimmingOffset;
            }
            if ((i & 2) != 0) {
                str = trimming.trimmingReferenceEdge;
            }
            if ((i & 4) != 0) {
                keywordOrName = trimming.trimmingType;
            }
            if ((i & 8) != 0) {
                str2 = trimming.trimmingWhen;
            }
            return trimming.copy(num, str, keywordOrName, str2);
        }

        public final Integer getTrimmingOffset() {
            return this.trimmingOffset;
        }

        public final String getTrimmingReferenceEdge() {
            return this.trimmingReferenceEdge;
        }

        public final KeywordOrName getTrimmingType() {
            return this.trimmingType;
        }

        public final String getTrimmingWhen() {
            return this.trimmingWhen;
        }

        public final Trimming copy(Integer trimmingOffset2, String trimmingReferenceEdge2, KeywordOrName trimmingType2, String trimmingWhen2) {
            return new Trimming(trimmingOffset2, trimmingReferenceEdge2, trimmingType2, trimmingWhen2);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Trimming)) {
                return false;
            }
            Trimming trimming = (Trimming) other;
            return Intrinsics.areEqual(this.trimmingOffset, trimming.trimmingOffset) && Intrinsics.areEqual(this.trimmingReferenceEdge, trimming.trimmingReferenceEdge) && Intrinsics.areEqual(this.trimmingType, trimming.trimmingType) && Intrinsics.areEqual(this.trimmingWhen, trimming.trimmingWhen);
        }

        public int hashCode() {
            Integer num = this.trimmingOffset;
            int iHashCode = (num != null ? num.hashCode() : 0) * 31;
            String str = this.trimmingReferenceEdge;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            KeywordOrName keywordOrName = this.trimmingType;
            int iHashCode3 = (iHashCode2 + (keywordOrName != null ? keywordOrName.hashCode() : 0)) * 31;
            String str2 = this.trimmingWhen;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override
        public void print(PrettyPrinter printer) {
            Intrinsics.checkNotNullParameter(printer, "printer");
            AttributeCollection.DefaultImpls.print(this, printer);
        }

        public Trimming(Integer num, String str, KeywordOrName keywordOrName, String str2) {
            this.trimmingOffset = num;
            this.trimmingReferenceEdge = str;
            this.trimmingType = keywordOrName;
            this.trimmingWhen = str2;
        }

        public Trimming(Integer num, String str, KeywordOrName keywordOrName, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                num = null;
            }
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                keywordOrName = null;
            }
            if ((i & 8) != 0) {
                str2 = null;
            }
            this(num, str, keywordOrName, str2);
        }

        public final Integer getTrimmingOffset() {
            return this.trimmingOffset;
        }

        public final void setTrimmingOffset(Integer num) {
            this.trimmingOffset = num;
        }

        public final String getTrimmingReferenceEdge() {
            return this.trimmingReferenceEdge;
        }

        public final void setTrimmingReferenceEdge(String str) {
            this.trimmingReferenceEdge = str;
        }

        public final KeywordOrName getTrimmingType() {
            return this.trimmingType;
        }

        public final void setTrimmingType(KeywordOrName keywordOrName) {
            this.trimmingType = keywordOrName;
        }

        public final String getTrimmingWhen() {
            return this.trimmingWhen;
        }

        public final void setTrimmingWhen(String str) {
            this.trimmingWhen = str;
        }

        public Trimming() {
            this(null, null, null, null);
        }

        @Override
        public List<Attribute<?>> getAttributes() {
            Attribute[] attributeArr = new Attribute[4];
            Integer num = this.trimmingOffset;
            attributeArr[0] = num != null ? trimmingOffset.mo418of(Integer.valueOf(num.intValue())) : null;
            String str = this.trimmingReferenceEdge;
            attributeArr[1] = str != null ? trimmingReferenceEdge.mo418of(str) : null;
            KeywordOrName keywordOrName = this.trimmingType;
            attributeArr[2] = keywordOrName != null ? trimmingType.mo418of(keywordOrName) : null;
            String str2 = this.trimmingWhen;
            attributeArr[3] = str2 != null ? trimmingWhen.mo418of(str2) : null;
            return CollectionsKt.listOfNotNull((Object[]) attributeArr);
        }

        @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001a\u0010\u0011\u001a\u00020\u00022\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013H\u0016R\u0016\u0010\u0004\u001a\u00020\u00008\u0006X\u0087\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0003R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m1293d2 = {"Lcom/hp/jipp/model/FinishingsCol$Trimming$Companion;", "Lcom/hp/jipp/encoding/AttributeCollection$Converter;", "Lcom/hp/jipp/model/FinishingsCol$Trimming;", "()V", "Types", "getTypes$annotations", "cls", "Ljava/lang/Class;", "getCls", "()Ljava/lang/Class;", "trimmingOffset", "Lcom/hp/jipp/encoding/IntType;", "trimmingReferenceEdge", "Lcom/hp/jipp/encoding/KeywordType;", "trimmingType", "Lcom/hp/jipp/encoding/KeywordOrNameType;", "trimmingWhen", "convert", "attributes", "", "Lcom/hp/jipp/encoding/Attribute;", "jipp-core"}, m1294k = 1, m1295mv = {1, 4, 0})
        public static final class Companion implements AttributeCollection.Converter<Trimming> {
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
            public Trimming convert(List<? extends Attribute<?>> attributes) {
                Intrinsics.checkNotNullParameter(attributes, "attributes");
                return new Trimming((Integer) extractOne(attributes, Trimming.trimmingOffset), (String) extractOne(attributes, Trimming.trimmingReferenceEdge), (KeywordOrName) extractOne(attributes, Trimming.trimmingType), (String) extractOne(attributes, Trimming.trimmingWhen));
            }

            @Override
            public Class<Trimming> getCls() {
                return Trimming.cls;
            }
        }

        static {
            Companion companion = new Companion(null);
            INSTANCE = companion;
            cls = Trimming.class;
            Types = companion;
            trimmingOffset = new IntType("trimming-offset");
            trimmingReferenceEdge = new KeywordType("trimming-reference-edge");
            trimmingType = new KeywordOrNameType("trimming-type");
            trimmingWhen = new KeywordType("trimming-when");
        }

        public String toString() {
            return "Trimming(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
        }
    }

    public String toString() {
        return "FinishingsCol(" + CollectionsKt.joinToString$default(getAttributes(), null, null, null, 0, null, null, 63, null) + ')';
    }
}
