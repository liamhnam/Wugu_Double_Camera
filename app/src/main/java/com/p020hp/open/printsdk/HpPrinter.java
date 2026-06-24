package com.p020hp.open.printsdk;

import com.p020hp.mobile.common.browsing.ConnectionTypeKt;
import com.p020hp.open.printsdk.cartridge.Cartridge;
import com.p020hp.open.printsdk.options.ColorType;
import com.p020hp.open.printsdk.options.InputTrayType;
import com.p020hp.open.printsdk.options.MediaSize;
import com.p020hp.open.printsdk.options.OrientationType;
import com.p020hp.open.printsdk.options.PaperType;
import com.p020hp.open.printsdk.options.PrintType;
import com.p020hp.open.printsdk.options.QualityType;
import com.p020hp.open.printsdk.options.ScaleType;
import com.p020hp.open.printsdk.options.SideType;
import com.p020hp.open.printsdk.options.SupportedOptions;
import com.p020hp.open.printsdk.state.printer.HpPrinterState;
import com.p020hp.printsdk.C1743o3;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(m1292d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 %2\u00020\u0001:\u0001%B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0016H\u0016J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0016H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\n\u0010\u001f\u001a\u0004\u0018\u00010\fH\u0016J\f\u0010 \u001a\u0006\u0012\u0002\b\u00030!H\u0016J\u000e\u0010\"\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010#\u001a\u00020\u001bH\u0016J\u0006\u0010$\u001a\u00020\fR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\n¨\u0006&"}, m1293d2 = {"Lcom/hp/open/printsdk/HpPrinter;", "", "()V", "id", "Ljava/util/UUID;", "getId", "()Ljava/util/UUID;", "img", "Ljava/net/URI;", "getImg", "()Ljava/net/URI;", NamingTable.TAG, "", "getName", "()Ljava/lang/String;", "uri", "getUri", "filterSupportedOptions", "Lcom/hp/open/printsdk/options/SupportedOptions;", "file", "Lcom/hp/open/printsdk/HpPrintFile;", "getCartridges", "", "Lcom/hp/open/printsdk/cartridge/Cartridge;", "getIppInputTrays", "getIppStateReasons", "getMaxCopiesCount", "", "getMediaMargin", "getPrinterIdentity", "getPrinterSupportedOptions", "getSerialNumber", "getStatus", "Lcom/hp/open/printsdk/state/printer/HpPrinterState;", "getSupportedOptions", "getType", "toString", "Companion", "print-core_thirdPartyRelease"}, m1294k = 1, m1295mv = {1, 6, 0}, m1297xi = 48)
public abstract class HpPrinter {
    public static final int LAN_PRINTER = 1;
    public static final int UNKNOWN_PRINTER = 0;
    public static final int USB_PRINTER = 2;

    public SupportedOptions mo455a(HpPrintFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return new SupportedOptions(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public List<Cartridge> getCartridges() {
        return CollectionsKt.emptyList();
    }

    public abstract UUID getId();

    public abstract URI getImg();

    public List<String> getIppInputTrays() {
        return CollectionsKt.emptyList();
    }

    public List<String> getIppStateReasons() {
        return CollectionsKt.emptyList();
    }

    public int getMaxCopiesCount() {
        return 99;
    }

    public List<Integer> getMediaMargin() {
        return CollectionsKt.listOf(0);
    }

    public abstract String getName();

    public String getPrinterIdentity() {
        return null;
    }

    public String getSerialNumber() {
        return null;
    }

    public HpPrinterState<?> getStatus() {
        return new HpPrinterState.Offline();
    }

    public final SupportedOptions getSupportedOptions(HpPrintFile file) {
        SupportedOptions supportedOptions;
        Intrinsics.checkNotNullParameter(file, "file");
        C1743o3 c1743o3 = C1743o3.f1559a;
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.isImageFile()) {
            supportedOptions = C1743o3.f1562d;
        } else {
            if (!file.isPdfFile()) {
                throw new IllegalStateException(("unknown file: " + file).toString());
            }
            supportedOptions = C1743o3.f1563e;
        }
        SupportedOptions supportedOptions2 = supportedOptions;
        SupportedOptions supportedOptionsCopy = supportedOptions2.copy((8127 & 1) != 0 ? supportedOptions2.f842a : null, (8127 & 2) != 0 ? supportedOptions2.f843b : null, (8127 & 4) != 0 ? supportedOptions2.f844c : null, (8127 & 8) != 0 ? supportedOptions2.f845d : null, (8127 & 16) != 0 ? supportedOptions2.f846e : null, (8127 & 32) != 0 ? supportedOptions2.f847f : null, (8127 & 64) != 0 ? supportedOptions2.f848g : new IntRange(1, file.getF780b()), (8127 & 128) != 0 ? supportedOptions2.f849h : null, (8127 & 256) != 0 ? supportedOptions2.f850i : null, (8127 & 512) != 0 ? supportedOptions2.f851j : null, (8127 & 1024) != 0 ? supportedOptions2.f852k : null, (8127 & 2048) != 0 ? supportedOptions2.f853l : null, (8127 & 4096) != 0 ? supportedOptions2.f854m : null);
        SupportedOptions supportedOptionsMo455a = mo455a(file);
        List<ColorType> colorTypes = supportedOptionsCopy.getColorTypes();
        ArrayList arrayList = new ArrayList();
        for (Object obj : colorTypes) {
            if (supportedOptionsMo455a.getColorTypes().contains((ColorType) obj)) {
                arrayList.add(obj);
            }
        }
        List<MediaSize> mediaSizes = supportedOptionsCopy.getMediaSizes();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : mediaSizes) {
            if (supportedOptionsMo455a.getMediaSizes().contains((MediaSize) obj2)) {
                arrayList2.add(obj2);
            }
        }
        List<ScaleType> scaleTypes = supportedOptionsCopy.getScaleTypes();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : scaleTypes) {
            if (supportedOptionsMo455a.getScaleTypes().contains((ScaleType) obj3)) {
                arrayList3.add(obj3);
            }
        }
        List<QualityType> qualityTypes = supportedOptionsCopy.getQualityTypes();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : qualityTypes) {
            if (supportedOptionsMo455a.getQualityTypes().contains((QualityType) obj4)) {
                arrayList4.add(obj4);
            }
        }
        List<SideType> sideTypes = supportedOptionsCopy.getSideTypes();
        ArrayList arrayList5 = new ArrayList();
        for (Object obj5 : sideTypes) {
            if (supportedOptionsMo455a.getSideTypes().contains((SideType) obj5)) {
                arrayList5.add(obj5);
            }
        }
        List<OrientationType> orientationTypes = supportedOptionsCopy.getOrientationTypes();
        ArrayList arrayList6 = new ArrayList();
        for (Object obj6 : orientationTypes) {
            if (supportedOptionsMo455a.getOrientationTypes().contains((OrientationType) obj6)) {
                arrayList6.add(obj6);
            }
        }
        IntRange intRange = new IntRange(1, getMaxCopiesCount());
        IntRange pageRange = supportedOptionsCopy.getPageRange();
        List<InputTrayType> inputTrayTypes = supportedOptionsCopy.getInputTrayTypes();
        ArrayList arrayList7 = new ArrayList();
        for (Object obj7 : inputTrayTypes) {
            if (supportedOptionsMo455a.getInputTrayTypes().contains((InputTrayType) obj7)) {
                arrayList7.add(obj7);
            }
        }
        List<Integer> mediaMargin = getMediaMargin();
        ArrayList arrayList8 = new ArrayList();
        Iterator it = mediaMargin.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            Iterator it2 = it;
            if ((supportedOptionsCopy.getOrientationTypes().contains(OrientationType.Landscape.INSTANCE) && ((Number) next).intValue() != 0) || !supportedOptionsCopy.getOrientationTypes().contains(OrientationType.Landscape.INSTANCE)) {
                arrayList8.add(next);
            }
            it = it2;
        }
        List<PaperType> paperType = supportedOptionsCopy.getPaperType();
        ArrayList arrayList9 = new ArrayList();
        Iterator it3 = paperType.iterator();
        while (it3.hasNext()) {
            Object next2 = it3.next();
            Iterator it4 = it3;
            if (supportedOptionsMo455a.getPaperType().contains((PaperType) next2)) {
                arrayList9.add(next2);
            }
            it3 = it4;
        }
        HashMap<PrintType, List<Integer>> dpi = supportedOptionsMo455a.getDpi();
        List<PrintType> printType = supportedOptionsMo455a.getPrintType();
        ArrayList arrayList10 = new ArrayList();
        Iterator it5 = printType.iterator();
        while (it5.hasNext()) {
            Object next3 = it5.next();
            Iterator it6 = it5;
            HashMap<PrintType, List<Integer>> map = dpi;
            if (supportedOptionsCopy.getPrintType().contains((PrintType) next3)) {
                arrayList10.add(next3);
            }
            it5 = it6;
            dpi = map;
        }
        return new SupportedOptions(arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, pageRange, intRange, arrayList7, arrayList8, arrayList9, dpi, arrayList10);
    }

    public int getType() {
        String host = getUri().getHost();
        Intrinsics.checkNotNullExpressionValue(host, "uri.host");
        return ConnectionTypeKt.isUsbHost(host) ? 2 : 1;
    }

    public abstract URI getUri();

    public final String toString() {
        return "HpPrinter(id: " + getId() + ", name: " + getName() + ", ip: " + getUri().getHost() + ", status: " + getStatus() + ", cartridges: " + getCartridges() + ')';
    }
}
