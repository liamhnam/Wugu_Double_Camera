package com.brother.sdk.common.presets;

import com.brother.sdk.common.ContentType;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.device.fax.FaxReceiveMode;
import com.brother.sdk.common.device.printer.PrintCollate;
import com.brother.sdk.common.device.printer.PrintColorMatching;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintMediaType;
import com.brother.sdk.common.device.printer.PrintOrientation;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.PrintScale;
import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.p020hp.jipp.model.PowerState;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BrotherDevicePresets {

    public static class ColorTypes {
        public static final List<ColorProcessing> MONOCHROME_MODELS = Collections.unmodifiableList(Arrays.asList(ColorProcessing.BlackAndWhite));
        public static final List<ColorProcessing> COLORONLY_MODELS = Collections.unmodifiableList(Arrays.asList(ColorProcessing.FullColor));
        public static final List<ColorProcessing> COLOR_MODELS = Collections.unmodifiableList(Arrays.asList(ColorProcessing.FullColor, ColorProcessing.BlackAndWhite));
    }

    public static final class Duplices {
        public static final List<Duplex> NO_DUPLEX_MODELS = Collections.unmodifiableList(Arrays.asList(Duplex.Simplex));
        public static final List<Duplex> DUPLEX_MODELS = Collections.unmodifiableList(Arrays.asList(Duplex.Simplex, Duplex.FlipOnLongEdge, Duplex.FlipOnShortEdge));
    }

    public static final class FaxReceiveModes {
        public static final List<FaxReceiveMode> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(new FaxReceiveMode[0]));
    }

    public static final class PrintCollates {
        public static final List<PrintCollate> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintCollate.ON, PrintCollate.OFF));
    }

    public static final class PrintColorMatchings {
        public static final List<PrintColorMatching> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintColorMatching.ContentOriginal, PrintColorMatching.SlowDryPrint));
        public static final List<PrintColorMatching> COPY_PRINT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintColorMatching.ContentOriginal, PrintColorMatching.SlowDryPrint, PrintColorMatching.CopyQuality));
    }

    public static final class PrintContents {
        public static final List<ContentType> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(ContentType.IMAGE_JPEG));
    }

    public static final class PrintCopyCount {
        public static final int DEFAULT_MODELS = 100;
    }

    public static final class PrintDirectPrinting {
        public static final boolean DEFAULT_MODELS = false;
    }

    public static final class PrintMargins {
        public static final List<PrintMargin> LASER_MODELS = Collections.unmodifiableList(Arrays.asList(PrintMargin.Normal));
        public static final List<PrintMargin> INK_MODELS = Collections.unmodifiableList(Arrays.asList(PrintMargin.Borderless, PrintMargin.Normal));
    }

    public static final class PrintMediaTypes {
        public static final List<PrintMediaType> LASER_MODELS = Collections.unmodifiableList(Arrays.asList(PrintMediaType.Plain, PrintMediaType.RecycledPaper, PrintMediaType.ThinPaper));
        public static final List<PrintMediaType> INK_MODELS = Collections.unmodifiableList(Arrays.asList(PrintMediaType.Plain, PrintMediaType.Photographic, PrintMediaType.InkjetPaper));
    }

    public static final class PrintOrientations {
        public static final List<PrintOrientation> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintOrientation.AutoRotation, PrintOrientation.Portrait, PrintOrientation.Landscape));
    }

    public static final class PrintPaperSizes {
        public static final List<MediaSize> LASER_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.Executive, MediaSize.B5, MediaSize.JISB5, MediaSize.A5, MediaSize.B6, MediaSize.A6, MediaSize.Folio, MediaSize.SIXTEENK));
        public static final List<MediaSize> A4INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.Executive, MediaSize.A5, MediaSize.A6, MediaSize.PhotoL, MediaSize.Index4x6, MediaSize.Photo2L, MediaSize.C5Envelope, MediaSize.DLEnvelope, MediaSize.SIXTEENK));
        public static final List<MediaSize> A3INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.A3, MediaSize.Ledger, MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.Executive, MediaSize.A5, MediaSize.A6, MediaSize.PhotoL, MediaSize.Index4x6, MediaSize.Photo2L, MediaSize.Hagaki, MediaSize.C5Envelope, MediaSize.DLEnvelope, MediaSize.SIXTEENK));
        public static final List<MediaSize> ALL_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.values()));
        public static final List<MediaSize> EXTRA_JP_A4INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.JISB5, MediaSize.Hagaki));
        public static final List<MediaSize> EXTRA_JP_A3INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.JISB4, MediaSize.JISB5, MediaSize.Hagaki));
        public static final List<MediaSize> EXTRA_ESA_A4INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.JISB5));
        public static final List<MediaSize> EXTRA_ESA_A3INK_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.JISB4, MediaSize.JISB5));
    }

    public static final class PrintQualities {
        public static final List<PrintQuality> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintQuality.Draft, PrintQuality.Document, PrintQuality.WebPage, PrintQuality.Photographic));
    }

    public static final class PrintResolutions {
        public static final List<Resolution> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(new Resolution(PowerState.Code.resetSoftGraceful, PowerState.Code.resetSoftGraceful), new Resolution(300, 300), new Resolution(600, 600)));
    }

    public static final class PrintScales {
        public static final List<PrintScale> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(PrintScale.FitToPrintableArea, PrintScale.NoScaling));
    }

    public static final class ScanDocumentSizes {
        public static final List<MediaSize> A4_FB_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.BusinessCard, MediaSize.BusinessCardLandscape, MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.SIXTEENK));
        public static final List<MediaSize> A3_FB_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.BusinessCard, MediaSize.BusinessCardLandscape, MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.JISB4, MediaSize.B4, MediaSize.Legal, MediaSize.Ledger, MediaSize.A3));
        public static final List<MediaSize> A4_ADF_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.SIXTEENK));
        public static final List<MediaSize> A3_ADF_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.JISB4, MediaSize.B4, MediaSize.Ledger, MediaSize.A3));
        public static final List<MediaSize> A4_ADF_EXTRA_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.BusinessCard, MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.SIXTEENK));
        public static final List<MediaSize> A3_ADF_EXTRA_MODELS = Collections.unmodifiableList(Arrays.asList(MediaSize.BusinessCard, MediaSize.Hagaki, MediaSize.Index4x6, MediaSize.PhotoL, MediaSize.Photo2L, MediaSize.A5, MediaSize.JISB5, MediaSize.B5, MediaSize.Executive, MediaSize.A4, MediaSize.Letter, MediaSize.Legal, MediaSize.JISB4, MediaSize.B4, MediaSize.Ledger, MediaSize.A3));
    }

    public static final class ScanPaperSources {
        public static final List<ScanPaperSource> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(ScanPaperSource.FB, ScanPaperSource.ADF, ScanPaperSource.AUTO));
        public static final List<ScanPaperSource> ADF_MODELS = Collections.unmodifiableList(Arrays.asList(ScanPaperSource.ADF, ScanPaperSource.AUTO));
        public static final List<ScanPaperSource> FB_MODELS = Collections.unmodifiableList(Arrays.asList(ScanPaperSource.FB, ScanPaperSource.AUTO));
    }

    public static final class ScanResolutions {
        public static final List<Resolution> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(new Resolution(100, 100), new Resolution(200, 200), new Resolution(300, 300), new Resolution(600, 600)));
    }

    public static final class ScanSpecialModes {
        public static final List<ScanSpecialMode> DEFAULT_MODELS = Collections.unmodifiableList(Arrays.asList(ScanSpecialMode.NORMAL_SCAN));
        public static final List<ScanSpecialMode> EXTRA_CORNER_SCAN_MODELS = Collections.unmodifiableList(Arrays.asList(ScanSpecialMode.CORNER_SCAN));
        public static final List<ScanSpecialMode> EXTRA_COPY_SCAN_MODELS = Collections.unmodifiableList(Arrays.asList(ScanSpecialMode.COPYQUALITY_SCAN));
    }
}
