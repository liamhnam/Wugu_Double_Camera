package com.brother.sdk.print;

import com.brother.sdk.common.ContentType;
import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.device.printer.PaperOrientation;
import com.brother.sdk.common.device.printer.PrintCollate;
import com.brother.sdk.common.device.printer.PrintColorMatching;
import com.brother.sdk.common.device.printer.PrintCustomScaling;
import com.brother.sdk.common.device.printer.PrintMargin;
import com.brother.sdk.common.device.printer.PrintMediaType;
import com.brother.sdk.common.device.printer.PrintOrientation;
import com.brother.sdk.common.device.printer.PrintOrigin;
import com.brother.sdk.common.device.printer.PrintQuality;
import com.brother.sdk.common.device.printer.PrintScale;

public class PrintParameters {
    public MediaSize paperSize = MediaSize.A4;
    public PrintMediaType mediaType = PrintMediaType.Plain;
    public Duplex duplex = Duplex.Simplex;
    public ColorProcessing color = ColorProcessing.FullColor;
    public PrintOrientation orientation = PrintOrientation.AutoRotation;
    public PaperOrientation paperOrientation = PaperOrientation.Portrait;
    public PrintScale scale = PrintScale.NoScalingAtPrintableAreaCenter;
    public PrintQuality quality = PrintQuality.Draft;
    public Resolution resolution = new Resolution(300, 300);
    public PrintMargin margin = PrintMargin.Normal;
    public ContentType printContent = ContentType.IMAGE_JPEG;
    public int copyCount = 1;
    public PrintCollate collate = PrintCollate.ON;
    public PrintColorMatching colorMatching = PrintColorMatching.ContentOriginal;
    public PrintOrigin origin = new PrintOrigin();
    public PrintCustomScaling customScaling = new PrintCustomScaling();
    public boolean directPrinting = false;
}
