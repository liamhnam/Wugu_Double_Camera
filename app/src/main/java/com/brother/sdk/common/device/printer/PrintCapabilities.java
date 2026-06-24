package com.brother.sdk.common.device.printer;

import com.brother.sdk.common.ContentType;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.presets.BrotherDevicePresets;
import java.io.Serializable;
import java.util.List;

public class PrintCapabilities implements Serializable {
    private static final long serialVersionUID = -4555038282822611309L;
    public List<MediaSize> paperSizes = BrotherDevicePresets.PrintPaperSizes.LASER_MODELS;
    public List<PrintMediaType> mediaTypes = BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
    public List<ColorProcessing> colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
    public List<Duplex> duplices = BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
    public List<PrintQuality> qualities = BrotherDevicePresets.PrintQualities.DEFAULT_MODELS;
    public List<Resolution> resolutions = BrotherDevicePresets.PrintResolutions.DEFAULT_MODELS;
    public List<PrintMargin> margins = BrotherDevicePresets.PrintMargins.LASER_MODELS;
    public List<PrintColorMatching> colorMatchings = BrotherDevicePresets.PrintColorMatchings.DEFAULT_MODELS;
    public List<PrintOrientation> orientations = BrotherDevicePresets.PrintOrientations.DEFAULT_MODELS;
    public List<PrintScale> scales = BrotherDevicePresets.PrintScales.DEFAULT_MODELS;
    public List<ContentType> printableContents = BrotherDevicePresets.PrintContents.DEFAULT_MODELS;
    public int maxCopyCount = 100;
    public boolean directPrintSupport = false;
}
