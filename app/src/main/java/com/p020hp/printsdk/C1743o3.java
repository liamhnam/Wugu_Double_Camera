package com.p020hp.printsdk;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;

public final class C1743o3 {

    public static final C1743o3 f1559a = new C1743o3();

    public static final ArrayList<MediaSize> f1560b;

    public static final SupportedOptions f1561c;

    public static final SupportedOptions f1562d;

    public static final SupportedOptions f1563e;

    static {
        ArrayList<MediaSize> arrayListArrayListOf = CollectionsKt.arrayListOf(MediaSize.ISO_A4.INSTANCE, MediaSize.ISO_A3.INSTANCE, MediaSize.ISO_A5.INSTANCE, MediaSize.INCH_4x6.INSTANCE, MediaSize.INCH_5x7.INSTANCE, MediaSize.INCH_7_25x10_5.INSTANCE, MediaSize.INCH_8_5x11.INSTANCE, MediaSize.INCH_8_5x14.INSTANCE, MediaSize.INCH_8x10.INSTANCE, MediaSize.INCH_5_5x8_5.INSTANCE, MediaSize.JIS_B5.INSTANCE, MediaSize.JPN_HAGAKI.INSTANCE, MediaSize.ISO_A6.INSTANCE, MediaSize.INCH_3x5.INSTANCE, MediaSize.OM_SMALL_PHOTO.INSTANCE, MediaSize.INCH_5x8.INSTANCE, MediaSize.INCH_3_875x7_5.INSTANCE, MediaSize.INCH_4_125x9_5.INSTANCE, MediaSize.ISO_DL.INSTANCE, MediaSize.ISO_C5.INSTANCE, MediaSize.ISO_C6.INSTANCE, MediaSize.INCH_4_375x5_75.INSTANCE, MediaSize.INCH_3_625x6_5.INSTANCE, MediaSize.JPN_CHOU3.INSTANCE, MediaSize.JPN_CHOU4.INSTANCE, MediaSize.INCH_3_5x5.INSTANCE, MediaSize.JPN_PHOTO.INSTANCE, MediaSize.INCH_8_5x13.INSTANCE, MediaSize.INCH_4x5.INSTANCE, MediaSize.INCH_4x12.INSTANCE, MediaSize.INCH_8_5x13_4.INSTANCE, MediaSize.JIS_B6.INSTANCE, MediaSize.PRC_16k_195x270.INSTANCE, MediaSize.PRC_16k_184x260.INSTANCE, MediaSize.INCH_7_75x10_75.INSTANCE, MediaSize.JPN_OUFUKU.INSTANCE, MediaSize.ISO_B5.INSTANCE, MediaSize.INCH_5x5.INSTANCE, MediaSize.Custom_100_254.INSTANCE);
        f1560b = arrayListArrayListOf;
        IntRange intRange = null;
        IntRange intRange2 = null;
        List list = null;
        HashMap map = null;
        int i = 2752;
        DefaultConstructorMarker defaultConstructorMarker = null;
        f1561c = new SupportedOptions(CollectionsKt.arrayListOf(ColorType.Color.INSTANCE, ColorType.Monochrome.INSTANCE, ColorType.ProcessMonochrome.INSTANCE), arrayListArrayListOf, CollectionsKt.arrayListOf(ScaleType.AUTOFIT.INSTANCE, ScaleType.Fill.INSTANCE, ScaleType.Fit.INSTANCE, ScaleType.NONE.INSTANCE), CollectionsKt.arrayListOf(QualityType.Normal.INSTANCE, QualityType.Draft.INSTANCE, QualityType.High.INSTANCE), CollectionsKt.arrayListOf(SideType.OneSide.INSTANCE, SideType.Duplex_LONG_EDGE.INSTANCE, SideType.Duplex_SHORT_EDGE.INSTANCE), CollectionsKt.arrayListOf(OrientationType.None.INSTANCE, OrientationType.Portrait.INSTANCE, OrientationType.Landscape.INSTANCE), intRange, intRange2, CollectionsKt.arrayListOf(InputTrayType.AUTO.INSTANCE, InputTrayType.TRAY_1.INSTANCE, InputTrayType.TRAY_2.INSTANCE, InputTrayType.TRAY_3.INSTANCE, InputTrayType.TRAY_4.INSTANCE, InputTrayType.TRAY_5.INSTANCE), list, CollectionsKt.arrayListOf(PaperType.PlainPaper.INSTANCE, PaperType.PhotoGlossPaper.INSTANCE), map, CollectionsKt.arrayListOf(PrintType.PDF_Direct.INSTANCE, PrintType.JPEG_Direct.INSTANCE, PrintType.PCLm.INSTANCE, PrintType.Pwg.INSTANCE), i, defaultConstructorMarker);
        f1562d = new SupportedOptions(CollectionsKt.arrayListOf(ColorType.Color.INSTANCE, ColorType.Monochrome.INSTANCE, ColorType.ProcessMonochrome.INSTANCE), arrayListArrayListOf, CollectionsKt.arrayListOf(ScaleType.Fit.INSTANCE, ScaleType.Fill.INSTANCE), CollectionsKt.arrayListOf(QualityType.Normal.INSTANCE, QualityType.Draft.INSTANCE, QualityType.High.INSTANCE), CollectionsKt.arrayListOf(SideType.OneSide.INSTANCE), CollectionsKt.arrayListOf(OrientationType.Portrait.INSTANCE), intRange, intRange2, CollectionsKt.arrayListOf(InputTrayType.AUTO.INSTANCE, InputTrayType.TRAY_1.INSTANCE, InputTrayType.TRAY_2.INSTANCE, InputTrayType.TRAY_3.INSTANCE, InputTrayType.TRAY_4.INSTANCE, InputTrayType.TRAY_5.INSTANCE), list, CollectionsKt.arrayListOf(PaperType.PhotoGlossPaper.INSTANCE, PaperType.PlainPaper.INSTANCE), map, CollectionsKt.arrayListOf(PrintType.JPEG_Direct.INSTANCE, PrintType.PCLm.INSTANCE, PrintType.Pwg.INSTANCE), i, defaultConstructorMarker);
        f1563e = new SupportedOptions(CollectionsKt.arrayListOf(ColorType.Color.INSTANCE, ColorType.Monochrome.INSTANCE, ColorType.ProcessMonochrome.INSTANCE), arrayListArrayListOf, CollectionsKt.arrayListOf(ScaleType.AUTOFIT.INSTANCE, ScaleType.Fit.INSTANCE, ScaleType.Fill.INSTANCE, ScaleType.NONE.INSTANCE), CollectionsKt.arrayListOf(QualityType.Normal.INSTANCE, QualityType.Draft.INSTANCE, QualityType.High.INSTANCE), CollectionsKt.arrayListOf(SideType.OneSide.INSTANCE, SideType.Duplex_LONG_EDGE.INSTANCE, SideType.Duplex_SHORT_EDGE.INSTANCE), CollectionsKt.arrayListOf(OrientationType.None.INSTANCE, OrientationType.Portrait.INSTANCE, OrientationType.Landscape.INSTANCE), intRange, intRange2, CollectionsKt.arrayListOf(InputTrayType.AUTO.INSTANCE, InputTrayType.TRAY_1.INSTANCE, InputTrayType.TRAY_2.INSTANCE, InputTrayType.TRAY_3.INSTANCE, InputTrayType.TRAY_4.INSTANCE, InputTrayType.TRAY_5.INSTANCE), list, CollectionsKt.arrayListOf(PaperType.PlainPaper.INSTANCE, PaperType.PhotoGlossPaper.INSTANCE), map, CollectionsKt.arrayListOf(PrintType.PDF_Direct.INSTANCE, PrintType.PCLm.INSTANCE, PrintType.Pwg.INSTANCE), i, defaultConstructorMarker);
    }

    public final SupportedOptions m608a() {
        return f1561c;
    }
}
