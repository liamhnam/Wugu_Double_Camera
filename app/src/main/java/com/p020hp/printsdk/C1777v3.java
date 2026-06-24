package com.p020hp.printsdk;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.p020hp.jipp.encoding.Attribute;
import com.p020hp.jipp.encoding.AttributeGroup;
import com.p020hp.jipp.encoding.IntType;
import com.p020hp.jipp.encoding.KeyValues;
import com.p020hp.jipp.encoding.KeyValuesType;
import com.p020hp.jipp.encoding.KeywordOrName;
import com.p020hp.jipp.encoding.Name;
import com.p020hp.jipp.encoding.NameType;
import com.p020hp.jipp.encoding.Resolution;
import com.p020hp.jipp.encoding.ResolutionUnit;
import com.p020hp.jipp.encoding.Tag;
import com.p020hp.jipp.encoding.Text;
import com.p020hp.jipp.model.MediaCol;
import com.p020hp.jipp.model.MediaColor;
import com.p020hp.jipp.model.Orientation;
import com.p020hp.jipp.model.PrintQuality;
import com.p020hp.jipp.model.PrinterState;
import com.p020hp.jipp.model.Types;
import com.p020hp.open.printsdk.CoreManager;
import com.p020hp.open.printsdk.HpPrintFile;
import com.p020hp.open.printsdk.HpPrinter;
import com.p020hp.open.printsdk.cartridge.Cartridge;
import com.p020hp.open.printsdk.options.ColorType;
import com.p020hp.open.printsdk.options.InputTrayType;
import com.p020hp.open.printsdk.options.MediaSize;
import com.p020hp.open.printsdk.options.MultipleDocumentHandling;
import com.p020hp.open.printsdk.options.OrientationType;
import com.p020hp.open.printsdk.options.PaperType;
import com.p020hp.open.printsdk.options.PrintOptions;
import com.p020hp.open.printsdk.options.PrintType;
import com.p020hp.open.printsdk.options.QualityType;
import com.p020hp.open.printsdk.options.ScaleType;
import com.p020hp.open.printsdk.options.SideType;
import com.p020hp.open.printsdk.options.SupportedOptions;
import com.p020hp.open.printsdk.options.Type;
import com.p020hp.open.printsdk.state.printer.HpPrinterState;
import com.p020hp.open.printsdk.state.printer.PrinterReason;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.eclipse.paho.android.service.MqttServiceConstants;

public class C1777v3 extends HpPrinter {

    public final UUID f1913a;

    public final String f1914b;

    public final URI f1915c;

    public final C1687e f1916d;

    public final SupportedOptions f1917e;

    public final Map<ColorType, String> f1918f;

    public final Map<MediaSize, KeywordOrName> f1919g;

    public final Map<ScaleType, String> f1920h;

    public final Map<QualityType, PrintQuality> f1921i;

    public final Map<SideType, String> f1922j;

    public final Map<OrientationType, Orientation> f1923k;

    public final Map<PaperType, KeywordOrName> f1924l;

    public final Map<InputTrayType, KeywordOrName> f1925m;

    public final LinkedHashMap<PrintType, List<Integer>> f1926n;

    public final Map<PrintType, String> f1927o;

    public final Map<MultipleDocumentHandling, String> f1928p;

    public final URI f1929q;

    public C1777v3(UUID id, String name, URI uri, C1687e ippPrinter) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(ippPrinter, "ippPrinter");
        this.f1913a = id;
        this.f1914b = name;
        this.f1915c = uri;
        this.f1916d = ippPrinter;
        SupportedOptions supportedOptionsM608a = C1743o3.f1559a.m608a();
        this.f1917e = supportedOptionsM608a;
        this.f1918f = m676a(supportedOptionsM608a.getColorTypes(), ippPrinter.m503a().get(Types.printColorModeSupported));
        this.f1919g = m676a(supportedOptionsM608a.getMediaSizes(), ippPrinter.m503a().get(Types.mediaSupported));
        this.f1920h = m676a(supportedOptionsM608a.getScaleTypes(), ippPrinter.m503a().get(Types.printScalingSupported));
        this.f1921i = m676a(supportedOptionsM608a.getQualityTypes(), ippPrinter.m503a().get(Types.printQualitySupported));
        this.f1922j = m676a(supportedOptionsM608a.getSideTypes(), ippPrinter.m503a().get(Types.sidesSupported));
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(OrientationType.Portrait.INSTANCE, Orientation.portrait);
        linkedHashMap.put(OrientationType.Landscape.INSTANCE, Orientation.landscape);
        linkedHashMap.put(OrientationType.None.INSTANCE, Orientation.none);
        this.f1923k = linkedHashMap;
        this.f1924l = m676a(supportedOptionsM608a.getPaperType(), ippPrinter.m503a().get(Types.mediaTypeSupported));
        this.f1925m = m676a(supportedOptionsM608a.getInputTrayTypes(), ippPrinter.m503a().get(Types.mediaSourceSupported));
        LinkedHashMap<PrintType, List<Integer>> linkedHashMap2 = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = ippPrinter.m503a().getValues(Types.pwgRasterDocumentResolutionSupported).iterator();
        while (it.hasNext()) {
            arrayList3.add(Integer.valueOf(((Resolution) it.next()).getX()));
        }
        Iterator it2 = this.f1916d.m503a().getValues(Types.printerResolutionSupported).iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(((Resolution) it2.next()).getX()));
        }
        Iterator it3 = this.f1916d.m503a().getValues(Types.pclmSourceResolutionSupported).iterator();
        while (it3.hasNext()) {
            arrayList2.add(Integer.valueOf(((Resolution) it3.next()).getX()));
        }
        linkedHashMap2.put(PrintType.JPEG_Direct.INSTANCE, arrayList);
        linkedHashMap2.put(PrintType.PDF_Direct.INSTANCE, arrayList);
        linkedHashMap2.put(PrintType.PCLm.INSTANCE, arrayList2);
        linkedHashMap2.put(PrintType.Pwg.INSTANCE, arrayList3);
        this.f1926n = linkedHashMap2;
        this.f1927o = m676a(this.f1917e.getPrintType(), this.f1916d.m503a().get(Types.documentFormatSupported));
        this.f1928p = m676a(CollectionsKt.arrayListOf(MultipleDocumentHandling.Collated.INSTANCE, MultipleDocumentHandling.Uncollated.INSTANCE), this.f1916d.m503a().get(Types.multipleDocumentHandlingSupported));
        this.f1929q = (URI) CollectionsKt.lastOrNull(this.f1916d.m503a().getValues(Types.printerIcons));
    }

    public final AttributeGroup m672a(PrintOptions options) {
        Attribute<MediaCol> attributeOf;
        Attribute<Resolution> attributeMo418of;
        String str;
        Orientation orientation;
        String str2;
        PrintQuality printQuality;
        String str3;
        String str4;
        MediaCol mediaCol;
        KeywordOrName keywordOrName;
        Intrinsics.checkNotNullParameter(options, "options");
        Integer f829b = options.getF829b();
        Attribute<Integer> attributeOf2 = f829b != null ? Types.copies.mo418of(Integer.valueOf(f829b.intValue())) : null;
        MediaSize f830c = options.getF830c();
        Attribute<KeywordOrName> attributeOf3 = (f830c == null || (keywordOrName = this.f1919g.get(f830c)) == null) ? null : Types.media.mo418of(keywordOrName);
        Attribute attribute = this.f1916d.f1179a.get(Types.mediaColDefault);
        if (attribute == null || (mediaCol = (MediaCol) attribute.getValue()) == null) {
            attributeOf = null;
        } else {
            MediaSize f830c2 = options.getF830c();
            if (f830c2 != null) {
                mediaCol.setMediaSize(f830c2.getF827c());
                mediaCol.setMediaLeftMargin(Integer.valueOf(options.getF837j()));
                mediaCol.setMediaRightMargin(Integer.valueOf(options.getF837j()));
                mediaCol.setMediaTopMargin(Integer.valueOf(options.getF837j()));
                mediaCol.setMediaBottomMargin(Integer.valueOf(options.getF837j()));
                mediaCol.setMediaType(this.f1924l.get(options.getF839l()));
                KeywordOrName keywordOrName2 = this.f1925m.get(options.getF838k());
                if (keywordOrName2 != null) {
                    mediaCol.setMediaSource(keywordOrName2);
                }
            }
            attributeOf = Types.mediaCol.mo418of(mediaCol);
        }
        ColorType f831d = options.getF831d();
        Attribute<String> attributeOf4 = (f831d == null || (str4 = this.f1918f.get(f831d)) == null) ? null : Types.printColorMode.mo418of(str4);
        ScaleType f832e = options.getF832e();
        Attribute<String> attributeOf5 = (f832e == null || (str3 = this.f1920h.get(f832e)) == null) ? null : Types.printScaling.mo418of(str3);
        QualityType f833f = options.getF833f();
        Attribute attributeOf6 = (f833f == null || (printQuality = this.f1921i.get(f833f)) == null) ? null : Types.printQuality.mo418of((Object) printQuality);
        SideType f834g = options.getF834g();
        Attribute<String> attributeOf7 = (f834g == null || (str2 = this.f1922j.get(f834g)) == null) ? null : Types.sides.mo418of(str2);
        OrientationType f835h = options.getF835h();
        Attribute attributeOf8 = (f835h == null || (orientation = this.f1923k.get(f835h)) == null) ? null : Types.orientationRequested.mo418of((Object) orientation);
        List<IntRange> pageRanges = options.getPageRanges();
        Attribute<IntRange> attributeMo417of = pageRanges != null ? Types.pageRanges.mo417of((Iterable<? extends IntRange>) pageRanges) : null;
        Integer f841n = options.getF841n();
        if (f841n != null) {
            int iIntValue = f841n.intValue();
            attributeMo418of = Types.printerResolution.mo418of(new Resolution(iIntValue, iIntValue, ResolutionUnit.dotsPerInch));
        } else {
            attributeMo418of = null;
        }
        PrintType f840m = options.getF840m();
        Attribute<String> attributeOf9 = (f840m == null || (str = this.f1927o.get(f840m)) == null) ? null : Types.documentFormat.mo418of(str);
        String str5 = this.f1928p.get(MultipleDocumentHandling.Collated.INSTANCE);
        return AttributeGroup.INSTANCE.groupOf(Tag.jobAttributes, CollectionsKt.listOfNotNull((Object[]) new Attribute[]{attributeOf3, attributeOf, attributeOf4, attributeOf2, attributeOf5, attributeOf6, attributeOf7, attributeOf8, attributeMo417of, attributeMo418of, attributeOf9, str5 != null ? Types.multipleDocumentHandling.mo418of(str5) : null}));
    }

    public final Cartridge m673a(String str, int i, int i2) {
        if (new Regex(MediaColor.black, RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.Black(i, i2);
        }
        if (new Regex("(tri.*color|multi.*color)", RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.TriColor(i, i2);
        }
        if (new Regex(MediaColor.cyan, RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.Cyan(i, i2);
        }
        if (new Regex(MediaColor.magenta, RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.Magenta(i, i2);
        }
        if (new Regex(MediaColor.yellow, RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.Yellow(i, i2);
        }
        if (new Regex("imaging.*drum", RegexOption.IGNORE_CASE).containsMatchIn(str)) {
            return new Cartridge.ImagingDrum(i, i2);
        }
        return null;
    }

    @Override
    public SupportedOptions mo455a(HpPrintFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return new SupportedOptions(CollectionsKt.toList(this.f1918f.keySet()), CollectionsKt.toList(this.f1919g.keySet()), CollectionsKt.toList(this.f1920h.keySet()), CollectionsKt.toList(this.f1921i.keySet()), CollectionsKt.toList(this.f1922j.keySet()), CollectionsKt.toList(this.f1923k.keySet()), null, null, this.f1925m.keySet().isEmpty() ^ true ? CollectionsKt.toList(this.f1925m.keySet()) : CollectionsKt.arrayListOf(InputTrayType.AUTO.INSTANCE), null, CollectionsKt.toList(this.f1924l.keySet()), this.f1926n, !Intrinsics.areEqual(file.getMimeType(), "application/pdf") ? m675a(file, CollectionsKt.toMutableList((Collection) this.f1927o.keySet())) : CollectionsKt.toList(this.f1927o.keySet()), TypedValues.TransitionType.TYPE_AUTO_TRANSITION, null);
    }

    public final <K extends Type, V> Map<K, V> m676a(List<? extends K> list, Attribute<V> attribute) {
        String string;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Type type = (Type) it.next();
            if (attribute != null) {
                for (V v : attribute) {
                    if (new Regex(".*\\(name\\)", RegexOption.IGNORE_CASE).matches(v.toString())) {
                        string = v.toString().substring(1, StringsKt.lastIndexOf$default((CharSequence) v.toString(), Typography.quote, 0, false, 6, (Object) null));
                        Intrinsics.checkNotNullExpressionValue(string, "this as java.lang.String…ing(startIndex, endIndex)");
                    } else {
                        string = v.toString();
                    }
                    Regex f856b = type.getF856b();
                    if (!((f856b == null || f856b.matches(string)) ? false : true)) {
                        linkedHashMap.put(type, v);
                    }
                }
            }
        }
        return linkedHashMap;
    }

    @Override
    public List<Cartridge> getCartridges() {
        return m674a();
    }

    @Override
    public UUID getId() {
        return this.f1913a;
    }

    @Override
    public URI getImg() {
        return this.f1929q;
    }

    @Override
    public List<String> getIppInputTrays() {
        return this.f1916d.f1179a.getStrings(Types.printerInputTray);
    }

    @Override
    public List<String> getIppStateReasons() {
        return this.f1916d.f1179a.getStrings(Types.printerStateReasons);
    }

    @Override
    public int getMaxCopiesCount() {
        IntRange intRange;
        Attribute attribute = this.f1916d.f1179a.get(Types.copiesSupported);
        return (attribute == null || (intRange = (IntRange) attribute.get(0)) == null) ? super.getMaxCopiesCount() : intRange.getEndInclusive().intValue();
    }

    @Override
    public List<Integer> getMediaMargin() {
        List<Integer> list;
        Attribute attribute = this.f1916d.f1179a.get(Types.mediaBottomMarginSupported);
        return (attribute == null || (list = CollectionsKt.toList(attribute)) == null) ? super.getMediaMargin() : list;
    }

    @Override
    public String getName() {
        return this.f1914b;
    }

    @Override
    public String getPrinterIdentity() {
        Text text = (Text) this.f1916d.f1179a.getValue(C1748p3.f1584c);
        if (text != null) {
            return text.getValue();
        }
        return null;
    }

    @Override
    public String getSerialNumber() {
        Text text = (Text) this.f1916d.f1179a.getValue(C1748p3.f1583b);
        if (text != null) {
            return text.getValue();
        }
        return null;
    }

    @Override
    public HpPrinterState<?> getStatus() {
        Map<String, List<PrinterReason>> mapM680a = C1782w3.m680a(this.f1916d.f1179a.getStrings(Types.printerStateReasons));
        List list = (List) MapsKt.getValue(mapM680a, "warning");
        List list2 = (List) MapsKt.getValue(mapM680a, MqttServiceConstants.TRACE_ERROR);
        List list3 = (List) MapsKt.getValue(mapM680a, "report");
        List list4 = (List) MapsKt.getValue(mapM680a, "unknown");
        PrinterState printerState = (PrinterState) this.f1916d.f1179a.getValue(Types.printerState);
        return Intrinsics.areEqual(printerState, PrinterState.idle) ? new HpPrinterState.Idle(list, list2, list3, list4) : Intrinsics.areEqual(printerState, PrinterState.processing) ? new HpPrinterState.Processing(list, list2, list3, list4) : Intrinsics.areEqual(printerState, PrinterState.stopped) ? new HpPrinterState.Stopped(list, list2, list3, list4) : new HpPrinterState.Offline();
    }

    @Override
    public URI getUri() {
        return this.f1915c;
    }

    public final List<Cartridge> m674a() {
        Cartridge cartridgeM673a;
        Attribute attribute = this.f1916d.f1179a.get(new NameType.Set("marker-names"));
        Attribute attribute2 = this.f1916d.f1179a.get(new IntType.Set("marker-levels"));
        Attribute attribute3 = this.f1916d.f1179a.get(new IntType.Set("marker-high-levels"));
        if (attribute == null || attribute2 == null || attribute3 == null) {
            return CollectionsKt.emptyList();
        }
        if (attribute.size() != attribute2.size() && attribute.size() != attribute3.size()) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(attribute, 10));
        int i = 0;
        for (Object obj : attribute) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(m673a(((Name) obj).getValue(), ((Number) attribute2.get(i)).intValue(), ((Number) attribute3.get(i)).intValue()));
            i = i2;
        }
        List<Cartridge> listFilterNotNull = CollectionsKt.filterNotNull(arrayList);
        if (!listFilterNotNull.isEmpty()) {
            return listFilterNotNull;
        }
        Attribute<KeyValues> attribute4 = this.f1916d.f1179a.get(new KeyValuesType.Set("printer-supply"));
        ArrayList arrayList2 = new ArrayList();
        if (attribute4 != null) {
            for (KeyValues keyValues : attribute4) {
                String str = (String) keyValues.get((Object) "colorantname");
                if (str != null && (cartridgeM673a = m673a(str, Integer.parseInt((String) keyValues.get((Object) "level")), Integer.parseInt((String) keyValues.get((Object) "maxcapacity")))) != null) {
                    arrayList2.add(cartridgeM673a);
                }
            }
        }
        return arrayList2;
    }

    public final List<PrintType> m675a(HpPrintFile hpPrintFile, List<PrintType> list) throws FileNotFoundException {
        IntRange intRange = (IntRange) this.f1916d.f1179a.getValue(Types.jpegKOctetsSupported);
        IntRange intRange2 = (IntRange) this.f1916d.f1179a.getValue(Types.jpegXDimensionSupported);
        IntRange intRange3 = (IntRange) this.f1916d.f1179a.getValue(Types.jpegYDimensionSupported);
        List<String> values = this.f1916d.f1179a.getValues(Types.jpegFeaturesSupported);
        InputStream inputStreamOpenInputStream = CoreManager.INSTANCE.getContext$print_core_thirdPartyRelease().getContentResolver().openInputStream(hpPrintFile.getUri());
        if (intRange != null && intRange2 != null && intRange3 != null && inputStreamOpenInputStream != null && (intRange.getLast() * 1024 < inputStreamOpenInputStream.available() || new C1695f1().m511a(inputStreamOpenInputStream, values, intRange2.getLast(), intRange3.getLast()))) {
            list.add(0, PrintType.PCLm.INSTANCE);
        }
        return CollectionsKt.distinct(list);
    }
}
