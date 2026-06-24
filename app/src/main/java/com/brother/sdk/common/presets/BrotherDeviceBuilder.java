package com.brother.sdk.common.presets;

import com.brother.sdk.common.ConnectorDescriptor;
import com.brother.sdk.common.ContentType;
import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.Device;
import com.brother.sdk.common.device.DeviceModelType;
import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.datacontrol.DataControl;
import com.brother.sdk.common.device.fax.Fax;
import com.brother.sdk.common.device.fax.FaxReceiveMode;
import com.brother.sdk.common.device.printer.PrintCapabilities;
import com.brother.sdk.common.device.printer.Printer;
import com.brother.sdk.common.device.printer.PrinterModelType;
import com.brother.sdk.common.device.printer.PrinterPDL;
import com.brother.sdk.common.device.scanner.ScanLongLengthEdge;
import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.device.scanner.ScanType;
import com.brother.sdk.common.device.scanner.Scanner;
import com.brother.sdk.common.device.scanner.ScannerModelSize;
import com.brother.sdk.common.device.scanner.ScannerModelType;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.common.presets.BrotherDevicePresets;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrotherDeviceBuilder {
    private static final double MM_PER_INCH = 25.4d;
    private static final double SCAN_DEVICE_MARGIN = 60.0d;
    private static final double UNIT_1PER10MM = 10.0d;

    public Device build(ConnectorDescriptor connectorDescriptor, CountrySpec countrySpec) {
        if (connectorDescriptor == null) {
            return null;
        }
        String modelName = connectorDescriptor.getModelName();
        BrotherDeviceMasterSpec brotherDeviceMasterSpec = BrotherDeviceMasterSpec.getBrotherDeviceMasterSpec(modelName);
        Device device = new Device();
        device.countrySpec = countrySpec;
        device.modelName = modelName;
        device.macaddress = connectorDescriptor.getStringValue(ConnectorDescriptor.DeviceQueryKey.InterfaceMacAddress);
        device.nodeName = connectorDescriptor.getStringValue(ConnectorDescriptor.DeviceQueryKey.NodeName);
        if (connectorDescriptor.support(ConnectorDescriptor.Function.Print)) {
            device.printer = generatePrinter(connectorDescriptor, brotherDeviceMasterSpec, countrySpec);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.Function.Scan)) {
            device.scanner = generateScanner(connectorDescriptor, brotherDeviceMasterSpec);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.Function.Phoenix)) {
            device.dataControl = generatePhoenix(connectorDescriptor, brotherDeviceMasterSpec);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.Function.Fax)) {
            device.fax = generateFax(connectorDescriptor, brotherDeviceMasterSpec);
        }
        return device;
    }

    private static Printer generatePrinter(ConnectorDescriptor connectorDescriptor, BrotherDeviceMasterSpec brotherDeviceMasterSpec, CountrySpec countrySpec) {
        Printer printer = new Printer();
        printer.deviceModel = brotherDeviceMasterSpec.deviceModelType;
        boolean z = false;
        boolean z2 = true;
        printer.capabilities.directPrintSupport = brotherDeviceMasterSpec.printDirectPrintingSupport == BrotherDeviceMasterSpec.DirectPrintingSupport.On;
        if (brotherDeviceMasterSpec.deviceModelType.mustCheckDevicePrintFunctionAdditionally()) {
            printer.modelType = PrinterModelType.fromValue(connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType));
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.PostScript.Support)) {
                printer.printerPDL = PrinterPDL.PostScript;
                printer.supportPDLs.add(PrinterPDL.PostScript);
                printer.capabilities.paperSizes = BrotherDevicePresets.PrintPaperSizes.LASER_MODELS;
                printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
                printer.capabilities.margins = BrotherDevicePresets.PrintMargins.LASER_MODELS;
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.Color)) {
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
                } else {
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
                }
                z = true;
            }
            if (z || !connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Support)) {
                z2 = z;
            } else {
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.AutoRotate)) {
                    printer.printerPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
                    printer.supportPDLs.add(PrinterPDL.BrotherCommonPDL_AutoPageFlip);
                } else {
                    printer.printerPDL = PrinterPDL.BrotherCommonPDL_ManualPageFlip;
                    printer.supportPDLs.add(PrinterPDL.BrotherCommonPDL_ManualPageFlip);
                }
                printer.capabilities.paperSizes = new ArrayList();
                if (printer.modelType == PrinterModelType.PRINT_INKJET) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.A4INK_MODELS);
                    printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.INK_MODELS;
                    printer.capabilities.margins = BrotherDevicePresets.PrintMargins.INK_MODELS;
                } else {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.LASER_MODELS);
                    printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
                    printer.capabilities.margins = BrotherDevicePresets.PrintMargins.LASER_MODELS;
                }
                if (countrySpec == CountrySpec.Japan) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_JP_A4INK_MODELS);
                } else if (countrySpec == CountrySpec.EastAsia) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_ESA_A4INK_MODELS);
                }
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.f475A3)) {
                    printer.capabilities.paperSizes.add(MediaSize.A3);
                }
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.f476B4)) {
                    printer.capabilities.paperSizes.add(MediaSize.B4);
                }
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Ledger)) {
                    printer.capabilities.paperSizes.add(MediaSize.Ledger);
                }
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.BrotherCommonPDL.Papers.Legal)) {
                    printer.capabilities.paperSizes.add(MediaSize.Legal);
                }
                if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.Color)) {
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
                } else {
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
                }
            }
            if (!z2) {
                if (brotherDeviceMasterSpec.deviceModelType == DeviceModelType.SuperLow4C) {
                    printer.printerPDL = PrinterPDL.PostScript;
                    printer.supportPDLs.add(PrinterPDL.PostScript);
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
                } else {
                    printer.printerPDL = PrinterPDL.PCL_BrotherMonochrome;
                    printer.supportPDLs.add(PrinterPDL.PCL_BrotherMonochrome);
                    printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
                }
                printer.capabilities.paperSizes = BrotherDevicePresets.PrintPaperSizes.LASER_MODELS;
                printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
                printer.capabilities.margins = BrotherDevicePresets.PrintMargins.LASER_MODELS;
            }
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.Duplex)) {
                printer.capabilities.duplices = BrotherDevicePresets.Duplices.DUPLEX_MODELS;
            } else {
                printer.capabilities.duplices = BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
            }
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Print.CopyPrint.Support)) {
                printer.capabilities.colorMatchings = BrotherDevicePresets.PrintColorMatchings.COPY_PRINT_MODELS;
            }
        } else {
            printer.modelType = brotherDeviceMasterSpec.printModelType;
            printer.printerPDL = brotherDeviceMasterSpec.printPDL;
            printer.supportPDLs.add(brotherDeviceMasterSpec.printPDL);
            if (brotherDeviceMasterSpec.printPaperSizeSupport == MediaSize.A3) {
                printer.capabilities.paperSizes = new ArrayList(BrotherDevicePresets.PrintPaperSizes.A3INK_MODELS);
                if (countrySpec == CountrySpec.Japan) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_JP_A3INK_MODELS);
                } else if (countrySpec == CountrySpec.EastAsia) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_ESA_A3INK_MODELS);
                }
            } else {
                printer.capabilities.paperSizes = new ArrayList(BrotherDevicePresets.PrintPaperSizes.A4INK_MODELS);
                if (countrySpec == CountrySpec.Japan) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_JP_A4INK_MODELS);
                } else if (countrySpec == CountrySpec.EastAsia) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_ESA_A4INK_MODELS);
                }
            }
            printer.capabilities.mediaTypes = brotherDeviceMasterSpec.printModelType == PrinterModelType.PRINT_INKJET ? BrotherDevicePresets.PrintMediaTypes.INK_MODELS : BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
            if (brotherDeviceMasterSpec.printColorSupport == BrotherDeviceMasterSpec.ColorSupport.ColorMono) {
                printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
            } else if (brotherDeviceMasterSpec.printColorSupport == BrotherDeviceMasterSpec.ColorSupport.ColorOnly) {
                printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLORONLY_MODELS;
            } else {
                printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
            }
            printer.capabilities.duplices = brotherDeviceMasterSpec.printDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On ? BrotherDevicePresets.Duplices.DUPLEX_MODELS : BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
            printer.capabilities.margins = brotherDeviceMasterSpec.printModelType == PrinterModelType.PRINT_INKJET ? BrotherDevicePresets.PrintMargins.INK_MODELS : BrotherDevicePresets.PrintMargins.LASER_MODELS;
        }
        printer.capabilities.qualities = BrotherDevicePresets.PrintQualities.DEFAULT_MODELS;
        printer.capabilities.resolutions = BrotherDevicePresets.PrintResolutions.DEFAULT_MODELS;
        printer.capabilities.colorMatchings = BrotherDevicePresets.PrintColorMatchings.DEFAULT_MODELS;
        printer.capabilities.orientations = BrotherDevicePresets.PrintOrientations.DEFAULT_MODELS;
        printer.capabilities.scales = BrotherDevicePresets.PrintScales.DEFAULT_MODELS;
        printer.capabilities.printableContents = BrotherDevicePresets.PrintContents.DEFAULT_MODELS;
        printer.capabilities.maxCopyCount = 100;
        return printer;
    }

    private static Scanner generateScanner(ConnectorDescriptor connectorDescriptor, BrotherDeviceMasterSpec brotherDeviceMasterSpec) {
        boolean z;
        boolean z2;
        if (brotherDeviceMasterSpec.deviceModelType.mustCheckDeviceScanFunctionAdditionally()) {
            Scanner scanner = new Scanner();
            scanner.protocolVersion = ScanCommandParameters.ProtocolGeneration.fromValue(connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.Protocol).intValue()).toValue();
            scanner.modelType = PrinterModelType.fromValue(connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Print.EngineType)) == PrinterModelType.UNKNOWN ? ScannerModelType.DocumentScanner : ScannerModelType.MFCScanner;
            scanner.autoDocumentSizeScanSupport = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.AutoDocumentSize.FormedSupport);
            scanner.modelHorizontalAlignment = HorizontalAlignment.LEFT;
            scanner.modelVerticalAlignment = VerticalAlignment.TOP;
            scanner.longLengthScanSupport = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.LongDocument.Support) ? ScanLongLengthEdge.Support863mm : ScanLongLengthEdge.NoSupport;
            scanner.deviceGroundColorCorrection = true;
            scanner.autoDocumentCropSupport = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.AutoCrop);
            scanner.deviceWhitePageRemove = false;
            scanner.multiPaperFeedDetect = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.MultiFeed);
            scanner.hardwareErrors = new ArrayList();
            if (brotherDeviceMasterSpec.scanHardwareError != null) {
                scanner.hardwareErrors.addAll(brotherDeviceMasterSpec.scanHardwareError);
            }
            scanner.modelSize = ScannerModelSize.A4;
            scanner.capabilities.paperSources = new ArrayList();
            scanner.capabilities.paperSources.add(ScanPaperSource.AUTO);
            scanner.capabilities.documentSizes = new HashMap<>();
            Integer integerValue = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.WidthMax);
            Integer integerValue2 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.Flatbed.HeightMax);
            if (integerValue != null && integerValue.intValue() > 0 && integerValue2 != null && integerValue2.intValue() > 0) {
                scanner.capabilities.paperSources.add(ScanPaperSource.FB);
                if (isA3Support(integerValue.doubleValue(), integerValue2.doubleValue())) {
                    scanner.modelSize = ScannerModelSize.A3;
                    scanner.capabilities.documentSizes.put(ScanType.FlatbedScan, BrotherDevicePresets.ScanDocumentSizes.A3_FB_MODELS);
                } else {
                    scanner.capabilities.documentSizes.put(ScanType.FlatbedScan, BrotherDevicePresets.ScanDocumentSizes.A4_FB_MODELS);
                }
            }
            Integer integerValue3 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMax);
            Integer integerValue4 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMax);
            if (integerValue3 != null && integerValue3.intValue() > 0 && integerValue4 != null && integerValue4.intValue() > 0) {
                scanner.capabilities.paperSources.add(ScanPaperSource.ADF);
                if (isA3Support(integerValue3.doubleValue(), integerValue4.doubleValue())) {
                    scanner.modelSize = ScannerModelSize.A3;
                    z2 = true;
                } else {
                    z2 = false;
                }
                Integer integerValue5 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMin);
                Integer integerValue6 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMin);
                scanner.capabilities.documentSizes.put(ScanType.ADFSimplexScan, getADFDocumentSize(z2, integerValue5 != null && integerValue5.intValue() > 0 && integerValue6 != null && integerValue6.intValue() > 0 && isA6Support(integerValue5.doubleValue(), integerValue6.doubleValue())));
            }
            Integer integerValue7 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.WidthMax);
            Integer integerValue8 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFBack.HeightMax);
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.Duplex) && integerValue7 != null && integerValue7.intValue() > 0 && integerValue8 != null && integerValue8.intValue() > 0) {
                scanner.capabilities.duplices = BrotherDevicePresets.Duplices.DUPLEX_MODELS;
                if (isA3Support(integerValue7.doubleValue(), integerValue8.doubleValue())) {
                    scanner.modelSize = ScannerModelSize.A3;
                    z = true;
                } else {
                    z = false;
                }
                Integer integerValue9 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.WidthMin);
                Integer integerValue10 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Scan.ADFFront.HeightMin);
                scanner.capabilities.documentSizes.put(ScanType.ADFDuplexScan, getADFDocumentSize(z, integerValue9 != null && integerValue9.intValue() > 0 && integerValue10 != null && integerValue10.intValue() > 0 && isA6Support(integerValue9.doubleValue(), integerValue10.doubleValue())));
            } else {
                scanner.capabilities.duplices = BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
            }
            scanner.capabilities.colorTypes = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.Color) ? BrotherDevicePresets.ColorTypes.COLOR_MODELS : BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
            scanner.capabilities.specialScanModes = new ArrayList();
            scanner.capabilities.specialScanModes.addAll(BrotherDevicePresets.ScanSpecialModes.DEFAULT_MODELS);
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.CornerScan.Support)) {
                scanner.capabilities.specialScanModes.addAll(BrotherDevicePresets.ScanSpecialModes.EXTRA_CORNER_SCAN_MODELS);
            }
            if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Scan.CopyScan.Support)) {
                scanner.capabilities.specialScanModes.addAll(BrotherDevicePresets.ScanSpecialModes.EXTRA_COPY_SCAN_MODELS);
            }
            scanner.capabilities.resolutions = BrotherDevicePresets.ScanResolutions.DEFAULT_MODELS;
            if (scanner.capabilities.documentSizes.size() == 0) {
                scanner.capabilities.documentSizes.put(ScanType.ADFSimplexScan, BrotherDevicePresets.ScanDocumentSizes.A4_ADF_MODELS);
                scanner.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
            }
            return scanner;
        }
        return generateScannerFromMasterSpec(brotherDeviceMasterSpec);
    }

    public static Printer generatePrinterFromMasterSpec(BrotherDeviceMasterSpec brotherDeviceMasterSpec, CountrySpec countrySpec) {
        Printer printer = new Printer();
        printer.printerPDL = brotherDeviceMasterSpec.printPDL;
        printer.modelType = brotherDeviceMasterSpec.printModelType;
        printer.deviceModel = brotherDeviceMasterSpec.deviceModelType;
        printer.supportPDLs = new ArrayList();
        printer.supportPDLs.add(printer.printerPDL);
        printer.capabilities = new PrintCapabilities();
        if (brotherDeviceMasterSpec.printColorSupport == BrotherDeviceMasterSpec.ColorSupport.ColorMono) {
            printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
        } else if (brotherDeviceMasterSpec.printColorSupport == BrotherDeviceMasterSpec.ColorSupport.ColorOnly) {
            printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.COLORONLY_MODELS;
        } else {
            printer.capabilities.colorTypes = BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
        }
        if (brotherDeviceMasterSpec.printDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On) {
            printer.capabilities.duplices = BrotherDevicePresets.Duplices.DUPLEX_MODELS;
        } else {
            printer.capabilities.duplices = BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
        }
        if (brotherDeviceMasterSpec.printModelType == PrinterModelType.PRINT_LASER) {
            printer.capabilities.paperSizes = BrotherDevicePresets.PrintPaperSizes.LASER_MODELS;
            printer.capabilities.margins = BrotherDevicePresets.PrintMargins.LASER_MODELS;
            printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.LASER_MODELS;
        } else {
            if (brotherDeviceMasterSpec.printPaperSizeSupport == MediaSize.A3) {
                printer.capabilities.paperSizes = new ArrayList(BrotherDevicePresets.PrintPaperSizes.A3INK_MODELS);
                if (countrySpec == CountrySpec.EastAsia) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_ESA_A3INK_MODELS);
                } else if (countrySpec == CountrySpec.Japan) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_JP_A3INK_MODELS);
                }
            } else {
                printer.capabilities.paperSizes = new ArrayList(BrotherDevicePresets.PrintPaperSizes.A4INK_MODELS);
                if (countrySpec == CountrySpec.EastAsia) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_ESA_A4INK_MODELS);
                } else if (countrySpec == CountrySpec.Japan) {
                    printer.capabilities.paperSizes.addAll(BrotherDevicePresets.PrintPaperSizes.EXTRA_JP_A4INK_MODELS);
                }
            }
            printer.capabilities.margins = BrotherDevicePresets.PrintMargins.INK_MODELS;
            printer.capabilities.mediaTypes = BrotherDevicePresets.PrintMediaTypes.INK_MODELS;
        }
        printer.capabilities.printableContents = new ArrayList(BrotherDevicePresets.PrintContents.DEFAULT_MODELS);
        if (brotherDeviceMasterSpec.printDirectPrintingSupport == BrotherDeviceMasterSpec.DirectPrintingSupport.On) {
            printer.capabilities.directPrintSupport = true;
            if (brotherDeviceMasterSpec.deviceModelType == DeviceModelType.DL) {
                printer.capabilities.printableContents.add(ContentType.DOC_PDF);
            }
        } else {
            printer.capabilities.directPrintSupport = true;
        }
        return printer;
    }

    public static Scanner generateScannerFromMasterSpec(BrotherDeviceMasterSpec brotherDeviceMasterSpec) {
        Scanner scanner = new Scanner();
        scanner.modelType = brotherDeviceMasterSpec.deviceModelType == DeviceModelType.ADS ? ScannerModelType.DocumentScanner : ScannerModelType.MFCScanner;
        scanner.autoDocumentSizeScanSupport = brotherDeviceMasterSpec.scanAutoDocumentSizeSupport == BrotherDeviceMasterSpec.AutoDocumentSizeScanSupport.On;
        scanner.modelHorizontalAlignment = brotherDeviceMasterSpec.scanHorizontalAlignment;
        scanner.modelVerticalAlignment = VerticalAlignment.TOP;
        scanner.longLengthScanSupport = ScanLongLengthEdge.NoSupport;
        scanner.deviceGroundColorCorrection = brotherDeviceMasterSpec.scanGroundColorRemoveSupport == BrotherDeviceMasterSpec.GroundColorRemoveScanSupport.On;
        scanner.autoDocumentCropSupport = false;
        scanner.deviceWhitePageRemove = false;
        scanner.multiPaperFeedDetect = false;
        scanner.modelSize = brotherDeviceMasterSpec.scanDocumentSizeSupport;
        scanner.hardwareErrors = new ArrayList();
        if (brotherDeviceMasterSpec.scanHardwareError != null) {
            scanner.hardwareErrors.addAll(brotherDeviceMasterSpec.scanHardwareError);
        }
        scanner.capabilities.documentSizes = new HashMap<>();
        if (scanner.modelType == ScannerModelType.DocumentScanner) {
            scanner.capabilities.paperSources = BrotherDevicePresets.ScanPaperSources.ADF_MODELS;
            scanner.capabilities.documentSizes.put(ScanType.ADFSimplexScan, BrotherDevicePresets.ScanDocumentSizes.A4_ADF_EXTRA_MODELS);
            if (brotherDeviceMasterSpec.scanDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On) {
                scanner.capabilities.documentSizes.put(ScanType.ADFDuplexScan, BrotherDevicePresets.ScanDocumentSizes.A4_ADF_EXTRA_MODELS);
            }
        } else {
            scanner.capabilities.paperSources = BrotherDevicePresets.ScanPaperSources.DEFAULT_MODELS;
            if (brotherDeviceMasterSpec.scanDocumentSizeSupport == ScannerModelSize.A3) {
                scanner.capabilities.documentSizes.put(ScanType.FlatbedScan, BrotherDevicePresets.ScanDocumentSizes.A3_FB_MODELS);
                scanner.capabilities.documentSizes.put(ScanType.ADFSimplexScan, BrotherDevicePresets.ScanDocumentSizes.A3_ADF_MODELS);
                if (brotherDeviceMasterSpec.scanDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On) {
                    scanner.capabilities.documentSizes.put(ScanType.ADFDuplexScan, BrotherDevicePresets.ScanDocumentSizes.A3_ADF_MODELS);
                }
            } else {
                scanner.capabilities.documentSizes.put(ScanType.FlatbedScan, BrotherDevicePresets.ScanDocumentSizes.A4_FB_MODELS);
                scanner.capabilities.documentSizes.put(ScanType.ADFSimplexScan, BrotherDevicePresets.ScanDocumentSizes.A4_ADF_MODELS);
                if (brotherDeviceMasterSpec.scanDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On) {
                    scanner.capabilities.documentSizes.put(ScanType.ADFDuplexScan, BrotherDevicePresets.ScanDocumentSizes.A4_ADF_MODELS);
                }
            }
        }
        scanner.capabilities.duplices = brotherDeviceMasterSpec.scanDuplexSupport == BrotherDeviceMasterSpec.DuplexSupport.On ? BrotherDevicePresets.Duplices.DUPLEX_MODELS : BrotherDevicePresets.Duplices.NO_DUPLEX_MODELS;
        scanner.capabilities.colorTypes = brotherDeviceMasterSpec.scanColorSupport == BrotherDeviceMasterSpec.ColorSupport.ColorMono ? BrotherDevicePresets.ColorTypes.COLOR_MODELS : BrotherDevicePresets.ColorTypes.MONOCHROME_MODELS;
        scanner.capabilities.specialScanModes = new ArrayList();
        scanner.capabilities.specialScanModes.addAll(BrotherDevicePresets.ScanSpecialModes.DEFAULT_MODELS);
        if (brotherDeviceMasterSpec.scanCornerScanSupport == BrotherDeviceMasterSpec.CornerScanSupport.On) {
            scanner.capabilities.specialScanModes.addAll(BrotherDevicePresets.ScanSpecialModes.EXTRA_CORNER_SCAN_MODELS);
        }
        scanner.capabilities.resolutions = BrotherDevicePresets.ScanResolutions.DEFAULT_MODELS;
        return scanner;
    }

    private static List<MediaSize> getADFDocumentSize(boolean z, boolean z2) {
        if (z) {
            if (z2) {
                return BrotherDevicePresets.ScanDocumentSizes.A3_ADF_EXTRA_MODELS;
            }
            return BrotherDevicePresets.ScanDocumentSizes.A3_ADF_MODELS;
        }
        if (z2) {
            return BrotherDevicePresets.ScanDocumentSizes.A4_ADF_EXTRA_MODELS;
        }
        return BrotherDevicePresets.ScanDocumentSizes.A4_ADF_MODELS;
    }

    private static boolean isA3Support(double d, double d2) {
        return d >= ((MediaSize.A3.width * MM_PER_INCH) * UNIT_1PER10MM) - SCAN_DEVICE_MARGIN && d2 >= ((MediaSize.A3.height * MM_PER_INCH) * UNIT_1PER10MM) - SCAN_DEVICE_MARGIN;
    }

    private static boolean isA6Support(double d, double d2) {
        return d <= (MediaSize.A6.width * MM_PER_INCH) * UNIT_1PER10MM && d2 <= (MediaSize.A6.height * MM_PER_INCH) * UNIT_1PER10MM;
    }

    private static Fax generateFax(ConnectorDescriptor connectorDescriptor, BrotherDeviceMasterSpec brotherDeviceMasterSpec) {
        Fax fax = new Fax();
        fax.capabilities.receiveModes = new ArrayList();
        if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Print)) {
            fax.capabilities.receiveModes.add(FaxReceiveMode.Print);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Storage)) {
            fax.capabilities.receiveModes.add(FaxReceiveMode.Storage);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.StoragePrint)) {
            fax.capabilities.receiveModes.add(FaxReceiveMode.StoragePrint);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.Cache)) {
            fax.capabilities.receiveModes.add(FaxReceiveMode.Cache);
        }
        if (connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Fax.ReceiveMode.CachePrint)) {
            fax.capabilities.receiveModes.add(FaxReceiveMode.CachePrint);
        }
        if (fax.capabilities.receiveModes.size() > 0) {
            Integer integerValue = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Fax.CurrentReceiveMode);
            if (integerValue != null) {
                fax.capabilities.currentReceiveMode = FaxReceiveMode.fromValue(integerValue.intValue());
            } else {
                fax.capabilities.currentReceiveMode = fax.capabilities.receiveModes.get(0);
            }
        }
        Integer integerValue2 = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Fax.MaxMemoryCount);
        if (integerValue2 != null) {
            fax.capabilities.maxMemoryCount = integerValue2.intValue();
        }
        return fax;
    }

    private static DataControl generatePhoenix(ConnectorDescriptor connectorDescriptor, BrotherDeviceMasterSpec brotherDeviceMasterSpec) {
        DataControl dataControl = new DataControl();
        dataControl.enable = connectorDescriptor.support(ConnectorDescriptor.DeviceQueryKey.Phoenix.Enabled);
        dataControl.version = 0;
        Integer integerValue = connectorDescriptor.getIntegerValue(ConnectorDescriptor.DeviceQueryKey.Phoenix.Version);
        if (integerValue != null) {
            dataControl.version = integerValue.intValue();
        }
        return dataControl;
    }
}
