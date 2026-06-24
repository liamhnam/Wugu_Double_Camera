package com.p020hp.open.printsdk.options;

import com.p020hp.open.printsdk.PrintOptionException;
import com.p020hp.open.printsdk.options.InputTrayType;
import com.p020hp.open.printsdk.options.PrintType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1292d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001a\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010\u00012\u0006\u0010U\u001a\u00020\u0001H\u0002J\b\u0010V\u001a\u00020SH\u0016R(\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R*\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R(\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0005\u001a\u0004\u0018\u00010\u0016@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R(\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010\u0005\u001a\u0004\u0018\u00010!@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R(\u0010(\u001a\u0004\u0018\u00010'2\b\u0010\u0005\u001a\u0004\u0018\u00010'@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R4\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-2\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010-@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R(\u00105\u001a\u0004\u0018\u0001042\b\u0010\u0005\u001a\u0004\u0018\u000104@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R(\u0010;\u001a\u0004\u0018\u00010:2\b\u0010\u0005\u001a\u0004\u0018\u00010:@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R(\u0010A\u001a\u0004\u0018\u00010@2\b\u0010\u0005\u001a\u0004\u0018\u00010@@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER(\u0010G\u001a\u0004\u0018\u00010F2\b\u0010\u0005\u001a\u0004\u0018\u00010F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR(\u0010M\u001a\u0004\u0018\u00010L2\b\u0010\u0005\u001a\u0004\u0018\u00010L@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006W"}, m1293d2 = {"Lcom/hp/open/printsdk/options/PrintOptions;", "", "supportedOptions", "Lcom/hp/open/printsdk/options/SupportedOptions;", "(Lcom/hp/open/printsdk/options/SupportedOptions;)V", "value", "Lcom/hp/open/printsdk/options/ColorType;", "color", "getColor", "()Lcom/hp/open/printsdk/options/ColorType;", "setColor", "(Lcom/hp/open/printsdk/options/ColorType;)V", "", "copies", "getCopies", "()Ljava/lang/Integer;", "setCopies", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "dpi", "getDpi", "setDpi", "Lcom/hp/open/printsdk/options/InputTrayType;", "inputTray", "getInputTray", "()Lcom/hp/open/printsdk/options/InputTrayType;", "setInputTray", "(Lcom/hp/open/printsdk/options/InputTrayType;)V", "mediaMargin", "getMediaMargin", "()I", "setMediaMargin", "(I)V", "Lcom/hp/open/printsdk/options/MediaSize;", "mediaSize", "getMediaSize", "()Lcom/hp/open/printsdk/options/MediaSize;", "setMediaSize", "(Lcom/hp/open/printsdk/options/MediaSize;)V", "Lcom/hp/open/printsdk/options/OrientationType;", "orientation", "getOrientation", "()Lcom/hp/open/printsdk/options/OrientationType;", "setOrientation", "(Lcom/hp/open/printsdk/options/OrientationType;)V", "", "Lkotlin/ranges/IntRange;", "pageRanges", "getPageRanges", "()Ljava/util/List;", "setPageRanges", "(Ljava/util/List;)V", "Lcom/hp/open/printsdk/options/PaperType;", "paperType", "getPaperType", "()Lcom/hp/open/printsdk/options/PaperType;", "setPaperType", "(Lcom/hp/open/printsdk/options/PaperType;)V", "Lcom/hp/open/printsdk/options/PrintType;", "printType", "getPrintType", "()Lcom/hp/open/printsdk/options/PrintType;", "setPrintType", "(Lcom/hp/open/printsdk/options/PrintType;)V", "Lcom/hp/open/printsdk/options/QualityType;", "quality", "getQuality", "()Lcom/hp/open/printsdk/options/QualityType;", "setQuality", "(Lcom/hp/open/printsdk/options/QualityType;)V", "Lcom/hp/open/printsdk/options/ScaleType;", "scale", "getScale", "()Lcom/hp/open/printsdk/options/ScaleType;", "setScale", "(Lcom/hp/open/printsdk/options/ScaleType;)V", "Lcom/hp/open/printsdk/options/SideType;", "sides", "getSides", "()Lcom/hp/open/printsdk/options/SideType;", "setSides", "(Lcom/hp/open/printsdk/options/SideType;)V", "getPrintOptionErrorMessage", "", "input", "supported", "toString", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public final class PrintOptions {

    public final SupportedOptions f828a;

    public Integer f829b;

    public MediaSize f830c;

    public ColorType f831d;

    public ScaleType f832e;

    public QualityType f833f;

    public SideType f834g;

    public OrientationType f835h;

    public List<IntRange> f836i;

    public int f837j;

    public InputTrayType f838k;

    public PaperType f839l;

    public PrintType f840m;

    public Integer f841n;

    public PrintOptions(SupportedOptions supportedOptions) {
        Intrinsics.checkNotNullParameter(supportedOptions, "supportedOptions");
        this.f828a = supportedOptions;
        this.f829b = 1;
        this.f830c = (MediaSize) CollectionsKt.firstOrNull((List) supportedOptions.getMediaSizes());
        this.f831d = (ColorType) CollectionsKt.firstOrNull((List) supportedOptions.getColorTypes());
        this.f832e = (ScaleType) CollectionsKt.firstOrNull((List) supportedOptions.getScaleTypes());
        this.f833f = (QualityType) CollectionsKt.firstOrNull((List) supportedOptions.getQualityTypes());
        this.f834g = (SideType) CollectionsKt.firstOrNull((List) supportedOptions.getSideTypes());
        this.f835h = (OrientationType) CollectionsKt.firstOrNull((List) supportedOptions.getOrientationTypes());
        Integer num = (Integer) CollectionsKt.firstOrNull((List) supportedOptions.getMediaMargin());
        this.f837j = num != null ? num.intValue() : 0;
        this.f838k = true ^ supportedOptions.getInputTrayTypes().isEmpty() ? (InputTrayType) CollectionsKt.firstOrNull((List) supportedOptions.getInputTrayTypes()) : InputTrayType.AUTO.INSTANCE;
        this.f839l = (PaperType) CollectionsKt.firstOrNull((List) supportedOptions.getPaperType());
        this.f840m = (PrintType) CollectionsKt.firstOrNull((List) supportedOptions.getPrintType());
        List<Integer> list = supportedOptions.getDpi().get(this.f840m);
        this.f841n = list != null ? (Integer) CollectionsKt.firstOrNull((List) list) : null;
    }

    public final String m456a(Object obj, Object obj2) {
        return obj + " is not supported, supported values: " + obj2;
    }

    public final ColorType getF831d() {
        return this.f831d;
    }

    public final Integer getF829b() {
        return this.f829b;
    }

    public final Integer getF841n() {
        return this.f841n;
    }

    public final InputTrayType getF838k() {
        return this.f838k;
    }

    public final int getF837j() {
        return this.f837j;
    }

    public final MediaSize getF830c() {
        return this.f830c;
    }

    public final OrientationType getF835h() {
        return this.f835h;
    }

    public final List<IntRange> getPageRanges() {
        return this.f836i;
    }

    public final PaperType getF839l() {
        return this.f839l;
    }

    public final PrintType getF840m() {
        return this.f840m;
    }

    public final QualityType getF833f() {
        return this.f833f;
    }

    public final ScaleType getF832e() {
        return this.f832e;
    }

    public final SideType getF834g() {
        return this.f834g;
    }

    public final void setColor(ColorType colorType) {
        if (!CollectionsKt.contains(this.f828a.getColorTypes(), colorType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getColorError(), m456a(colorType, this.f828a.getColorTypes()), null, 4, null);
        }
        this.f831d = colorType;
    }

    public final void setCopies(Integer num) {
        IntRange copiesRange = this.f828a.getCopiesRange();
        if (num == null || num.intValue() > copiesRange.getLast() || num.intValue() < copiesRange.getFirst()) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getCopiesError(), m456a(num, "[" + copiesRange + ']'), null, 4, null);
        }
        this.f829b = num;
    }

    public final void setDpi(Integer num) {
        List<Integer> list = this.f828a.getDpi().get(this.f840m);
        if ((list == null || CollectionsKt.contains(list, num)) ? false : true) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getDPIError(), m456a(num, String.valueOf(this.f828a.getDpi().get(this.f840m))), null, 4, null);
        }
        this.f841n = num;
    }

    public final void setInputTray(InputTrayType inputTrayType) {
        if (!CollectionsKt.contains(this.f828a.getInputTrayTypes(), inputTrayType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getInputTrayError(), m456a(inputTrayType, this.f828a.getInputTrayTypes()), null, 4, null);
        }
        this.f838k = inputTrayType;
    }

    public final void setMediaMargin(int i) {
        if (!this.f828a.getMediaMargin().contains(Integer.valueOf(i))) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getMediaMarginError(), m456a(Integer.valueOf(i), String.valueOf(this.f828a.getMediaMargin())), null, 4, null);
        }
        this.f837j = i;
    }

    public final void setMediaSize(MediaSize mediaSize) {
        if (!CollectionsKt.contains(this.f828a.getMediaSizes(), mediaSize)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getMediaSizeError(), m456a(mediaSize, this.f828a.getMediaSizes()), null, 4, null);
        }
        this.f830c = mediaSize;
    }

    public final void setOrientation(OrientationType orientationType) {
        if (!CollectionsKt.contains(this.f828a.getOrientationTypes(), orientationType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getOrientationError(), m456a(orientationType, this.f828a.getOrientationTypes()), null, 4, null);
        }
        this.f835h = orientationType;
    }

    public final void setPageRanges(List<IntRange> list) {
        if (list != null) {
            if (Intrinsics.areEqual(PrintType.JPEG_Direct.INSTANCE, this.f840m)) {
                throw new PrintOptionException(PrintOptionException.INSTANCE.getPageRangeError(), "PageRanges is not supported for JPEG_Direct", null, 4, null);
            }
            IntRange pageRange = this.f828a.getPageRange();
            for (IntRange intRange : list) {
                int first = intRange.getFirst();
                int last = intRange.getLast();
                if (first <= last) {
                    while (pageRange.contains(first)) {
                        if (first != last) {
                            first++;
                        }
                    }
                    throw new PrintOptionException(PrintOptionException.INSTANCE.getPageRangeError(), m456a("[" + intRange + ']', "[" + pageRange + ']'), null, 4, null);
                }
            }
        }
        this.f836i = list;
    }

    public final void setPaperType(PaperType paperType) {
        if (!CollectionsKt.contains(this.f828a.getPaperType(), paperType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getPaperTypeError(), m456a(paperType, this.f828a.getPaperType()), null, 4, null);
        }
        this.f839l = paperType;
    }

    public final void setPrintType(PrintType printType) {
        if (!CollectionsKt.contains(this.f828a.getPrintType(), printType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getPrintTypeError(), m456a(printType, String.valueOf(this.f828a.getPrintType())), null, 4, null);
        }
        this.f840m = printType;
    }

    public final void setQuality(QualityType qualityType) {
        if (!CollectionsKt.contains(this.f828a.getQualityTypes(), qualityType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getQualityError(), m456a(qualityType, this.f828a.getQualityTypes()), null, 4, null);
        }
        this.f833f = qualityType;
    }

    public final void setScale(ScaleType scaleType) {
        if (!CollectionsKt.contains(this.f828a.getScaleTypes(), scaleType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getScaleError(), m456a(scaleType, this.f828a.getScaleTypes()), null, 4, null);
        }
        this.f832e = scaleType;
    }

    public final void setSides(SideType sideType) {
        if (!CollectionsKt.contains(this.f828a.getSideTypes(), sideType)) {
            throw new PrintOptionException(PrintOptionException.INSTANCE.getSidesError(), m456a(sideType, this.f828a.getSideTypes()), null, 4, null);
        }
        this.f834g = sideType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PrintOptions(copies: ");
        sb.append(this.f829b).append(", mediaSize: ").append(this.f830c).append(", color: ").append(this.f831d).append(", scale: ").append(this.f832e).append(", quality: ").append(this.f833f).append(", sides: ").append(this.f834g).append(", mediaMargin: ").append(this.f837j).append(", orientation: ").append(this.f835h).append(", pageRanges: ").append(this.f836i).append(", inputTray: ").append(this.f838k).append(", paperType: ").append(this.f839l).append(", dpi: ");
        sb.append(this.f841n).append(", printType: ").append(this.f840m).append(")");
        return sb.toString();
    }
}
