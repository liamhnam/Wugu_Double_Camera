package com.p020hp.open.printsdk.options;

import com.p020hp.jipp.model.PrinterServiceType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1292d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bë\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0010\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003\u0012\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003\u00120\b\u0002\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00030\u0019j\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0003`\u001b\u0012\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003¢\u0006\u0002\u0010\u001dJ\u000f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003HÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003HÆ\u0003J1\u00101\u001a*\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00030\u0019j\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0003`\u001bHÆ\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003HÆ\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\u0003HÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0010HÆ\u0003J\t\u00109\u001a\u00020\u0010HÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003HÆ\u0003Jï\u0001\u0010;\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u000320\b\u0002\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00030\u0019j\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0003`\u001b2\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003HÆ\u0001J\u0013\u0010<\u001a\u00020=2\b\u0010>\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010?\u001a\u00020\u0015HÖ\u0001J\t\u0010@\u001a\u00020AHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R9\u0010\u0018\u001a*\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00030\u0019j\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0003`\u001b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001fR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001f¨\u0006B"}, m1293d2 = {"Lcom/hp/open/printsdk/options/SupportedOptions;", "", "colorTypes", "", "Lcom/hp/open/printsdk/options/ColorType;", "mediaSizes", "Lcom/hp/open/printsdk/options/MediaSize;", "scaleTypes", "Lcom/hp/open/printsdk/options/ScaleType;", "qualityTypes", "Lcom/hp/open/printsdk/options/QualityType;", "sideTypes", "Lcom/hp/open/printsdk/options/SideType;", "orientationTypes", "Lcom/hp/open/printsdk/options/OrientationType;", "pageRange", "Lkotlin/ranges/IntRange;", "copiesRange", "inputTrayTypes", "Lcom/hp/open/printsdk/options/InputTrayType;", "mediaMargin", "", "paperType", "Lcom/hp/open/printsdk/options/PaperType;", "dpi", "Ljava/util/HashMap;", "Lcom/hp/open/printsdk/options/PrintType;", "Lkotlin/collections/HashMap;", "printType", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/ranges/IntRange;Lkotlin/ranges/IntRange;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/HashMap;Ljava/util/List;)V", "getColorTypes", "()Ljava/util/List;", "getCopiesRange", "()Lkotlin/ranges/IntRange;", "getDpi", "()Ljava/util/HashMap;", "getInputTrayTypes", "getMediaMargin", "getMediaSizes", "getOrientationTypes", "getPageRange", "getPaperType", "getPrintType", "getQualityTypes", "getScaleTypes", "getSideTypes", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", PrinterServiceType.copy, "equals", "", "other", "hashCode", "toString", "", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class SupportedOptions {

    public final List<ColorType> f842a;

    public final List<MediaSize> f843b;

    public final List<ScaleType> f844c;

    public final List<QualityType> f845d;

    public final List<SideType> f846e;

    public final List<OrientationType> f847f;

    public final IntRange f848g;

    public final IntRange f849h;

    public final List<InputTrayType> f850i;

    public final List<Integer> f851j;

    public final List<PaperType> f852k;

    public final HashMap<PrintType, List<Integer>> f853l;

    public final List<PrintType> f854m;

    public SupportedOptions() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public SupportedOptions(List<? extends ColorType> colorTypes, List<? extends MediaSize> mediaSizes, List<? extends ScaleType> scaleTypes, List<? extends QualityType> qualityTypes, List<? extends SideType> sideTypes, List<? extends OrientationType> orientationTypes, IntRange pageRange, IntRange copiesRange, List<? extends InputTrayType> inputTrayTypes, List<Integer> mediaMargin, List<? extends PaperType> paperType, HashMap<PrintType, List<Integer>> dpi, List<? extends PrintType> printType) {
        Intrinsics.checkNotNullParameter(colorTypes, "colorTypes");
        Intrinsics.checkNotNullParameter(mediaSizes, "mediaSizes");
        Intrinsics.checkNotNullParameter(scaleTypes, "scaleTypes");
        Intrinsics.checkNotNullParameter(qualityTypes, "qualityTypes");
        Intrinsics.checkNotNullParameter(sideTypes, "sideTypes");
        Intrinsics.checkNotNullParameter(orientationTypes, "orientationTypes");
        Intrinsics.checkNotNullParameter(pageRange, "pageRange");
        Intrinsics.checkNotNullParameter(copiesRange, "copiesRange");
        Intrinsics.checkNotNullParameter(inputTrayTypes, "inputTrayTypes");
        Intrinsics.checkNotNullParameter(mediaMargin, "mediaMargin");
        Intrinsics.checkNotNullParameter(paperType, "paperType");
        Intrinsics.checkNotNullParameter(dpi, "dpi");
        Intrinsics.checkNotNullParameter(printType, "printType");
        this.f842a = colorTypes;
        this.f843b = mediaSizes;
        this.f844c = scaleTypes;
        this.f845d = qualityTypes;
        this.f846e = sideTypes;
        this.f847f = orientationTypes;
        this.f848g = pageRange;
        this.f849h = copiesRange;
        this.f850i = inputTrayTypes;
        this.f851j = mediaMargin;
        this.f852k = paperType;
        this.f853l = dpi;
        this.f854m = printType;
    }

    public SupportedOptions(List list, List list2, List list3, List list4, List list5, List list6, IntRange intRange, IntRange intRange2, List list7, List list8, List list9, HashMap map, List list10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list, (i & 2) != 0 ? new ArrayList() : list2, (i & 4) != 0 ? new ArrayList() : list3, (i & 8) != 0 ? new ArrayList() : list4, (i & 16) != 0 ? new ArrayList() : list5, (i & 32) != 0 ? new ArrayList() : list6, (i & 64) != 0 ? new IntRange(1, 1) : intRange, (i & 128) != 0 ? new IntRange(1, 99) : intRange2, (i & 256) != 0 ? new ArrayList() : list7, (i & 512) != 0 ? new ArrayList() : list8, (i & 1024) != 0 ? new ArrayList() : list9, (i & 2048) != 0 ? new HashMap() : map, (i & 4096) != 0 ? new ArrayList() : list10);
    }

    public final List<ColorType> component1() {
        return this.f842a;
    }

    public final List<Integer> component10() {
        return this.f851j;
    }

    public final List<PaperType> component11() {
        return this.f852k;
    }

    public final HashMap<PrintType, List<Integer>> component12() {
        return this.f853l;
    }

    public final List<PrintType> component13() {
        return this.f854m;
    }

    public final List<MediaSize> component2() {
        return this.f843b;
    }

    public final List<ScaleType> component3() {
        return this.f844c;
    }

    public final List<QualityType> component4() {
        return this.f845d;
    }

    public final List<SideType> component5() {
        return this.f846e;
    }

    public final List<OrientationType> component6() {
        return this.f847f;
    }

    public final IntRange getF848g() {
        return this.f848g;
    }

    public final IntRange getF849h() {
        return this.f849h;
    }

    public final List<InputTrayType> component9() {
        return this.f850i;
    }

    public final SupportedOptions copy(List<? extends ColorType> colorTypes, List<? extends MediaSize> mediaSizes, List<? extends ScaleType> scaleTypes, List<? extends QualityType> qualityTypes, List<? extends SideType> sideTypes, List<? extends OrientationType> orientationTypes, IntRange pageRange, IntRange copiesRange, List<? extends InputTrayType> inputTrayTypes, List<Integer> mediaMargin, List<? extends PaperType> paperType, HashMap<PrintType, List<Integer>> dpi, List<? extends PrintType> printType) {
        Intrinsics.checkNotNullParameter(colorTypes, "colorTypes");
        Intrinsics.checkNotNullParameter(mediaSizes, "mediaSizes");
        Intrinsics.checkNotNullParameter(scaleTypes, "scaleTypes");
        Intrinsics.checkNotNullParameter(qualityTypes, "qualityTypes");
        Intrinsics.checkNotNullParameter(sideTypes, "sideTypes");
        Intrinsics.checkNotNullParameter(orientationTypes, "orientationTypes");
        Intrinsics.checkNotNullParameter(pageRange, "pageRange");
        Intrinsics.checkNotNullParameter(copiesRange, "copiesRange");
        Intrinsics.checkNotNullParameter(inputTrayTypes, "inputTrayTypes");
        Intrinsics.checkNotNullParameter(mediaMargin, "mediaMargin");
        Intrinsics.checkNotNullParameter(paperType, "paperType");
        Intrinsics.checkNotNullParameter(dpi, "dpi");
        Intrinsics.checkNotNullParameter(printType, "printType");
        return new SupportedOptions(colorTypes, mediaSizes, scaleTypes, qualityTypes, sideTypes, orientationTypes, pageRange, copiesRange, inputTrayTypes, mediaMargin, paperType, dpi, printType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SupportedOptions)) {
            return false;
        }
        SupportedOptions supportedOptions = (SupportedOptions) other;
        return Intrinsics.areEqual(this.f842a, supportedOptions.f842a) && Intrinsics.areEqual(this.f843b, supportedOptions.f843b) && Intrinsics.areEqual(this.f844c, supportedOptions.f844c) && Intrinsics.areEqual(this.f845d, supportedOptions.f845d) && Intrinsics.areEqual(this.f846e, supportedOptions.f846e) && Intrinsics.areEqual(this.f847f, supportedOptions.f847f) && Intrinsics.areEqual(this.f848g, supportedOptions.f848g) && Intrinsics.areEqual(this.f849h, supportedOptions.f849h) && Intrinsics.areEqual(this.f850i, supportedOptions.f850i) && Intrinsics.areEqual(this.f851j, supportedOptions.f851j) && Intrinsics.areEqual(this.f852k, supportedOptions.f852k) && Intrinsics.areEqual(this.f853l, supportedOptions.f853l) && Intrinsics.areEqual(this.f854m, supportedOptions.f854m);
    }

    public final List<ColorType> getColorTypes() {
        return this.f842a;
    }

    public final IntRange getCopiesRange() {
        return this.f849h;
    }

    public final HashMap<PrintType, List<Integer>> getDpi() {
        return this.f853l;
    }

    public final List<InputTrayType> getInputTrayTypes() {
        return this.f850i;
    }

    public final List<Integer> getMediaMargin() {
        return this.f851j;
    }

    public final List<MediaSize> getMediaSizes() {
        return this.f843b;
    }

    public final List<OrientationType> getOrientationTypes() {
        return this.f847f;
    }

    public final IntRange getPageRange() {
        return this.f848g;
    }

    public final List<PaperType> getPaperType() {
        return this.f852k;
    }

    public final List<PrintType> getPrintType() {
        return this.f854m;
    }

    public final List<QualityType> getQualityTypes() {
        return this.f845d;
    }

    public final List<ScaleType> getScaleTypes() {
        return this.f844c;
    }

    public final List<SideType> getSideTypes() {
        return this.f846e;
    }

    public int hashCode() {
        return (((((((((((((((((((((((this.f842a.hashCode() * 31) + this.f843b.hashCode()) * 31) + this.f844c.hashCode()) * 31) + this.f845d.hashCode()) * 31) + this.f846e.hashCode()) * 31) + this.f847f.hashCode()) * 31) + this.f848g.hashCode()) * 31) + this.f849h.hashCode()) * 31) + this.f850i.hashCode()) * 31) + this.f851j.hashCode()) * 31) + this.f852k.hashCode()) * 31) + this.f853l.hashCode()) * 31) + this.f854m.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("SupportedOptions(colorTypes=");
        sb.append(this.f842a).append(", mediaSizes=").append(this.f843b).append(", scaleTypes=").append(this.f844c).append(", qualityTypes=").append(this.f845d).append(", sideTypes=").append(this.f846e).append(", orientationTypes=").append(this.f847f).append(", pageRange=").append(this.f848g).append(", copiesRange=").append(this.f849h).append(", inputTrayTypes=").append(this.f850i).append(", mediaMargin=").append(this.f851j).append(", paperType=").append(this.f852k).append(", dpi=");
        sb.append(this.f853l).append(", printType=").append(this.f854m).append(')');
        return sb.toString();
    }
}
