package com.brother.sdk.common.presets;

import com.brother.sdk.common.device.DeviceModelType;
import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.printer.PrinterModelType;
import com.brother.sdk.common.device.printer.PrinterPDL;
import com.brother.sdk.common.device.scanner.ScannerModelSize;
import com.brother.sdk.common.device.scanner.ScannerModelType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrotherDeviceMasterSpec {
    public DeviceModelType deviceModelType;
    public ColorSupport printColorSupport;
    public DirectPrintingSupport printDirectPrintingSupport;
    public DuplexSupport printDuplexSupport;
    public PrinterModelType printModelType;
    public PrinterPDL printPDL;
    public MediaSize printPaperSizeSupport;
    public AutoDocumentSizeScanSupport scanAutoDocumentSizeSupport;
    public ColorSupport scanColorSupport;
    public CornerScanSupport scanCornerScanSupport;
    public ScannerModelSize scanDocumentSizeSupport;
    public DuplexSupport scanDuplexSupport;
    public GroundColorRemoveScanSupport scanGroundColorRemoveSupport;
    public List<ScanHardwareError> scanHardwareError;
    public HorizontalAlignment scanHorizontalAlignment;
    public ScannerModelType scanModelType;
    public ScanSupport scanSupport;
    private static final HashMap<String, BrotherDeviceMasterSpec> SPEC_TABLE = new HashMap<String, BrotherDeviceMasterSpec>() {
        private static final long serialVersionUID = -2683897631467002375L;

        {
            put("Brother DCP-535CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-585CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-490CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-490CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-790CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-930CDN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-990CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-6690CW", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A3, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-5890CN", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-6490CN", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A3, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-6490CW", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A3, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-6890CDW", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A3, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-6890CN", new BH9SeriesDeviceSpec(DeviceModelType.BHM9, MediaSize.A3, ScannerModelSize.A3, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-5490CN", new BH9SeriesDeviceSpec(DeviceModelType.BHL9, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-5895CW", new BH9SeriesDeviceSpec(DeviceModelType.BHL9, MediaSize.A3, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-365CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-373CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-375CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-377CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-390CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-391CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-395CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-593CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-595CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-595CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-597CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-253CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-255CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-257CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-295CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-495CN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-495CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-695CDN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-795CW", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-935CDN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J950DN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J315W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J515N", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J515W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J715N", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J715W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J265W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J270W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J410W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J415W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J615N", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J615W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J630W", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J850DN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother MFC-J855DN", new BH9SeriesDeviceSpec(DeviceModelType.BHmini9e2, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.Off));
            put("Brother DCP-J140W", new BH9SeriesDeviceSpec(DeviceModelType.BH9e3, MediaSize.A4, ScannerModelSize.A4, AutoDocumentSizeScanSupport.On));
            put("Brother MFC-J6310W", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.Off));
            put("Brother MFC-J6510DW", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.Off));
            put("Brother MFC-J6710DW", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.Off));
            put("Brother MFC-J6710CDW", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.Off));
            put("Brother MFC-J6910DW", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.On));
            put("Brother MFC-J6910CDW", new BH11SeriesDeviceSpec(DeviceModelType.BHM11, MediaSize.A3, ScannerModelSize.A3, DuplexSupport.On));
            put("Brother MFC-J5910DW", new BH11SeriesDeviceSpec(DeviceModelType.BHL11, MediaSize.A3, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J5910CDW", new BH11SeriesDeviceSpec(DeviceModelType.BHL11, MediaSize.A3, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J525N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J525W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J725N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J725DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J925N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J925DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J280W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J430W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J625DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J635DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J705D", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J810DN", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J825N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J825DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J860DN", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J955DN", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J275W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J425W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J432W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J435W", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J835DW", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J540N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11dash, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J740N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11dash, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J840N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11dash, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J940N", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11dash, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother MFC-J960DN", new BH11SeriesDeviceSpec(DeviceModelType.BHmini11dash, MediaSize.A4, ScannerModelSize.A4, DuplexSupport.Off));
            put("Brother DCP-J4110DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother DCP-J4210N", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother DCP-J4215N", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4310DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4305DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4315DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4410DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4405DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4415DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4510DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4505DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4515DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4510N", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4610DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4605DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4615DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4710DW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.On, ScanHardwareError.NoError));
            put("Brother MFC-J4810DN", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J4910CDW", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.UselessBusinessCardAdfOption));
            put("Brother MFC-J2310", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother MFC-J2510", new BHS13SeriesDeviceSpec(DeviceModelType.BHS13, DuplexSupport.Off, ScanHardwareError.NoError));
            put("Brother ADS-1050W", new ADSDeviceSpec());
            put("Brother ADS-1150W", new ADSDeviceSpec());
            put("Brother ADS-1550W", new ADSDeviceSpec());
            put("Brother ADS-1650W", new ADSDeviceSpec());
            put("Brother ADS-2000We", new ADSDeviceSpec());
            put("Brother ADS-2000e", new ADSDeviceSpec());
            put("Brother ADS-2100e", new ADSDeviceSpec());
            put("Brother ADS-2500We", new ADSDeviceSpec());
            put("Brother ADS-2600We", new ADSDeviceSpec());
            put("Brother ADS-2400N", new ADSDeviceSpec());
            put("Brother ADS-2800W", new ADSDeviceSpec());
            put("Brother ADS-3000N", new ADSDeviceSpec());
            put("Brother ADS-3600W", new ADSDeviceSpec());
            put("Brother ADS-2000", new ADSDeviceSpec());
            put("Brother ADS-2100", new ADSDeviceSpec());
            put("Brother ADS-2500W", new ADSDeviceSpec());
            put("Brother ADS-2600W", new ADSDeviceSpec());
            put("Brother ADS-1000W", new ADSDeviceSpec());
            put("Brother ADS-1100W", new ADSDeviceSpec());
            put("Brother ADS-1500W", new ADSDeviceSpec());
            put("Brother ADS-1600W", new ADSDeviceSpec());
            put("Brother DS-740D", new ADSDeviceSpec());
            put("Brother MFC-9560CDW", new LaserFBDeviceSpec(DeviceModelType.BCFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-9465CDN", new LaserFBDeviceSpec(DeviceModelType.BCFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-9970CDW", new LaserFBDeviceSpec(DeviceModelType.BCFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother DCP-9270CDN", new LaserFBDeviceSpec(DeviceModelType.BCFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother DCP-8155DN", new LaserFBDeviceSpec(DeviceModelType.BLFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother DCP-8250DN", new LaserFBDeviceSpec(DeviceModelType.BLFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-8910DW", new LaserFBDeviceSpec(DeviceModelType.BLFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-8950DW", new LaserFBDeviceSpec(DeviceModelType.BLFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-8950DWT", new LaserFBDeviceSpec(DeviceModelType.BLFB, HorizontalAlignment.LEFT, DuplexSupport.On, ColorSupport.ColorMono));
            put("Brother MFC-9130CW", new LaserFBDeviceSpec(DeviceModelType.DCLFB, HorizontalAlignment.LEFT, DuplexSupport.Off, ColorSupport.ColorMono));
            put("Brother MFC-9140CDN", new LaserFBDeviceSpec(DeviceModelType.DCLFB, HorizontalAlignment.LEFT, DuplexSupport.Off, ColorSupport.ColorMono));
            put("Brother MFC-9330CDW", new LaserFBDeviceSpec(DeviceModelType.DCLFB, HorizontalAlignment.LEFT, DuplexSupport.Off, ColorSupport.ColorMono));
            put("Brother DCP-B7535DW", new GeneralDLUSBDeviceSpec(DeviceModelType.DL, PrinterModelType.PRINT_INKJET, PrinterPDL.Raw_Jpeg, ColorSupport.ColorMono, DuplexSupport.On));
            put("Brother DCP-C1210N", new GeneralDLUSBDeviceSpec(DeviceModelType.DL, PrinterModelType.PRINT_INKJET, PrinterPDL.Raw_Jpeg, ColorSupport.ColorMono, DuplexSupport.On));
            put("Brother DCP-9020CDN", new LaserFBDeviceSpec(DeviceModelType.DCLFB, HorizontalAlignment.LEFT, DuplexSupport.Off, ColorSupport.ColorMono));
            put("Brother DCP-9020CDW", new LaserFBDeviceSpec(DeviceModelType.DCLFB, HorizontalAlignment.LEFT, DuplexSupport.Off, ColorSupport.ColorMono));
            put("Brother MFC-7225N", new LaserFBDeviceSpec(DeviceModelType.ALLSF, HorizontalAlignment.CENTER, DuplexSupport.Off, ColorSupport.Monochrome));
            put("Brother MFC-9420CN", new SuperLow4CDeviceSpec());
            put("Brother HL-S7000DN series", new CobraDeviceSpec());
            put("Brother DCP-J4120DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother DCP-J4120N", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother DCP-J4220N", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother DCP-J4225N", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J4320DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J4420DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J4620DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J4720N", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J4725N", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5320DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5620DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5620CDW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5720DW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5720CDW", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J5820DN", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J2320", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother MFC-J2720", new BHS15SeriesDeviceSpec(ScanHardwareError.UnnecessaryAreaScan));
            put("Brother HL-L5000D", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5580D", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-5580D", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5585D", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5102DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5100DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5590DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5200DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5202DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6200DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6202DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6250DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5595DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-5595DNH", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off, DirectPrintingSupport.On));
            put("Brother HL-5595DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off, DirectPrintingSupport.On));
            put("Brother HL-L6250DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6300DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6400DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6402DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6450DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5100DNT", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L5200DWT", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6200DWT", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6300DWT", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6400DWT", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother HL-L6400DWG", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother DCP-L5500D", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L5500DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L5502DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L5600DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L5650DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L5652DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother DCP-L6600DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5700DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5702DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5700DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L8530DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L8535DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5750DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5755DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L8540DN", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5800DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5802DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5850DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5900DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L5902DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6700DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6702DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6750DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6800DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6800DW CONSIP", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6800DW CSP2", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6900DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6902DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6950DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6970DW", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother MFC-L6900DWG", new DLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("Brother HL-2560DN", new DLLSeriesDeviceSpec(DuplexSupport.On, ScanSupport.On));
            put("HL-2260D", new GeneralUSBDeviceSpec(DeviceModelType.DLLPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.On));
            put("HL-2260", new GeneralUSBDeviceSpec(DeviceModelType.DLLPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.Off));
            put("DCP-7180DN", new GeneralDLLUSBDeviceSpec(DeviceModelType.DLLPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.On, ScanSupport.On));
            put("DCP-B7648DW", new GeneralDLLUSBDeviceSpec(DeviceModelType.DLLPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.Off, ScanSupport.On));
            put("MFC-L2710DW", new GeneralUSBDeviceSpec(DeviceModelType.ELLFB, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.On));
            put("MFC-L2750DW", new GeneralDLLUSBDeviceSpec(DeviceModelType.DLLPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PCL_BrotherMonochrome, ColorSupport.Monochrome, DuplexSupport.On, ScanSupport.On));
            put("DCP-L2628DW", new GeneralUSBDeviceSpec(DeviceModelType.SupportUSBAndScanPrinter, PrinterModelType.PRINT_LASER, PrinterPDL.PostScript, ColorSupport.Monochrome, DuplexSupport.On, DirectPrintingSupport.On, ScanSupport.On));
            put("HL-5585D", new GeneralDLUSBDeviceSpec(DeviceModelType.DL, PrinterModelType.PRINT_LASER, PrinterPDL.Raw_Jpeg, ColorSupport.Monochrome, DuplexSupport.On));
            put("Brother DCP-T520W", new BrotherDeviceMasterSpec(DeviceModelType.DL, MediaSize.A4, PrinterPDL.Raw_Jpeg, ColorSupport.ColorMono, PrinterModelType.PRINT_INKJET, DuplexSupport.On, ScannerModelType.DocumentScanner, HorizontalAlignment.LEFT, ScannerModelSize.A4, DuplexSupport.On, ColorSupport.ColorMono, GroundColorRemoveScanSupport.On, CornerScanSupport.On, AutoDocumentSizeScanSupport.On, ScanHardwareError.NoError));
            put("DCP-T420W", new GeneralUSBDeviceSpec(DeviceModelType.MINI19HT, PrinterModelType.PRINT_INKJET, PrinterPDL.BrotherCommonPDL_AutoPageFlip, ColorSupport.ColorMono, DuplexSupport.On));
            put("DCP-T430W", new GeneralUSBDeviceSpec(DeviceModelType.MINI19HT, PrinterModelType.PRINT_INKJET, PrinterPDL.BrotherCommonPDL_AutoPageFlip, ColorSupport.ColorMono, DuplexSupport.On));
            put("DCP-C421W", new GeneralUSBDeviceSpec(DeviceModelType.MINI19HT, PrinterModelType.PRINT_INKJET, PrinterPDL.BrotherCommonPDL_AutoPageFlip, ColorSupport.ColorMono, DuplexSupport.Off));
            put("DCP-T830DW", new GeneralUSBDeviceSpec(DeviceModelType.MINI19HT, PrinterModelType.PRINT_INKJET, PrinterPDL.BrotherCommonPDL_AutoPageFlip, ColorSupport.ColorMono, DuplexSupport.Off));
            put("Brother MFC-T930DW", new BrotherDeviceMasterSpec(DeviceModelType.DL, MediaSize.A4, PrinterPDL.Raw_Jpeg, ColorSupport.ColorMono, PrinterModelType.PRINT_INKJET, DuplexSupport.On, ScannerModelType.DocumentScanner, HorizontalAlignment.LEFT, ScannerModelSize.A4, DuplexSupport.On, ColorSupport.ColorMono, GroundColorRemoveScanSupport.On, CornerScanSupport.On, AutoDocumentSizeScanSupport.On, ScanHardwareError.NoError));
            put("HL-L5218DN", new ELSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off, DirectPrintingSupport.On));
            put("HL-L5228DW", new ELSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("DCP-L5518DN", new ELSeriesDeviceSpec(DuplexSupport.On, ScanSupport.Off));
            put("Brother MFC-J2340DW", new BHMB19SeriesDeviceSpec(MediaSize.A3));
            put("Brother MFC-J3940DW", new BHMB19SeriesDeviceSpec(MediaSize.A3));
            put("Brother HL-L3228CDW", new FCLDeviceSpec(PrinterPDL.BrotherCommonPDL_AutoPageFlip));
        }
    };
    private static final BrotherDeviceMasterSpec GENERIC_DEVICE = new BrotherDeviceMasterSpec(DeviceModelType.GeneralBrotherDevice);

    public enum AutoDocumentSizeScanSupport {
        Off,
        On
    }

    public enum ColorSupport {
        Monochrome,
        ColorOnly,
        ColorMono
    }

    public enum CornerScanSupport {
        Off,
        On
    }

    public enum DirectPrintingSupport {
        Off,
        On
    }

    public enum DuplexSupport {
        Off,
        On
    }

    public enum GroundColorRemoveScanSupport {
        Off,
        On
    }

    public enum ScanHardwareError {
        NoError,
        UnnecessaryAreaScan,
        UselessBusinessCardAdfOption
    }

    public enum ScanSupport {
        Off,
        On
    }

    public static BrotherDeviceMasterSpec getBrotherDeviceMasterSpec(String str) {
        int iIndexOf = str.indexOf("series");
        if (iIndexOf >= 0) {
            str = str.substring(0, iIndexOf).trim();
        }
        for (Map.Entry<String, BrotherDeviceMasterSpec> entry : SPEC_TABLE.entrySet()) {
            if (entry.getKey().contains(str)) {
                return entry.getValue();
            }
        }
        return GENERIC_DEVICE;
    }

    BrotherDeviceMasterSpec(DeviceModelType deviceModelType, MediaSize mediaSize, PrinterPDL printerPDL, ColorSupport colorSupport, PrinterModelType printerModelType, DuplexSupport duplexSupport, ScannerModelType scannerModelType, HorizontalAlignment horizontalAlignment, ScannerModelSize scannerModelSize, DuplexSupport duplexSupport2, ColorSupport colorSupport2, GroundColorRemoveScanSupport groundColorRemoveScanSupport, CornerScanSupport cornerScanSupport, AutoDocumentSizeScanSupport autoDocumentSizeScanSupport, ScanHardwareError scanHardwareError) {
        this.deviceModelType = deviceModelType;
        this.printPDL = printerPDL;
        this.printModelType = printerModelType;
        this.printPaperSizeSupport = mediaSize;
        this.printColorSupport = colorSupport;
        this.printDuplexSupport = duplexSupport;
        this.printDirectPrintingSupport = DirectPrintingSupport.Off;
        this.scanModelType = scannerModelType;
        this.scanDocumentSizeSupport = scannerModelSize;
        this.scanHorizontalAlignment = horizontalAlignment;
        this.scanDuplexSupport = duplexSupport2;
        this.scanColorSupport = colorSupport2;
        this.scanGroundColorRemoveSupport = groundColorRemoveScanSupport;
        this.scanCornerScanSupport = cornerScanSupport;
        this.scanAutoDocumentSizeSupport = autoDocumentSizeScanSupport;
        this.scanSupport = ScanSupport.On;
    }

    BrotherDeviceMasterSpec(DeviceModelType deviceModelType) {
        this.deviceModelType = deviceModelType;
    }

    static class BH9SeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public BH9SeriesDeviceSpec(DeviceModelType deviceModelType, MediaSize mediaSize, ScannerModelSize scannerModelSize, AutoDocumentSizeScanSupport autoDocumentSizeScanSupport) {
            super(deviceModelType);
            this.printPDL = PrinterPDL.Jpeg_BH9;
            this.printModelType = PrinterModelType.PRINT_INKJET;
            this.printPaperSizeSupport = mediaSize;
            this.printColorSupport = ColorSupport.ColorOnly;
            this.printDuplexSupport = DuplexSupport.Off;
            this.scanModelType = ScannerModelType.MFCScanner;
            this.scanDocumentSizeSupport = scannerModelSize;
            this.scanHorizontalAlignment = HorizontalAlignment.LEFT;
            this.scanDuplexSupport = DuplexSupport.Off;
            this.scanColorSupport = ColorSupport.ColorMono;
            this.scanGroundColorRemoveSupport = GroundColorRemoveScanSupport.Off;
            this.scanCornerScanSupport = CornerScanSupport.Off;
            this.scanAutoDocumentSizeSupport = autoDocumentSizeScanSupport;
        }
    }

    static class BH11SeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public BH11SeriesDeviceSpec(DeviceModelType deviceModelType, MediaSize mediaSize, ScannerModelSize scannerModelSize, DuplexSupport duplexSupport) {
            super(deviceModelType);
            this.printPDL = PrinterPDL.Jpeg_BH11;
            this.printModelType = PrinterModelType.PRINT_INKJET;
            this.printPaperSizeSupport = mediaSize;
            this.printColorSupport = ColorSupport.ColorOnly;
            this.printDuplexSupport = DuplexSupport.Off;
            this.scanModelType = ScannerModelType.MFCScanner;
            this.scanDocumentSizeSupport = scannerModelSize;
            this.scanHorizontalAlignment = HorizontalAlignment.LEFT;
            this.scanDuplexSupport = duplexSupport;
            this.scanColorSupport = ColorSupport.ColorMono;
            this.scanGroundColorRemoveSupport = GroundColorRemoveScanSupport.Off;
            this.scanCornerScanSupport = CornerScanSupport.Off;
            this.scanAutoDocumentSizeSupport = AutoDocumentSizeScanSupport.On;
        }
    }

    static class BHS13SeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public BHS13SeriesDeviceSpec(DeviceModelType deviceModelType, DuplexSupport duplexSupport, ScanHardwareError scanHardwareError) {
            super(deviceModelType);
            this.printPDL = PrinterPDL.Jpeg_BHS13;
            this.printModelType = PrinterModelType.PRINT_INKJET;
            this.printPaperSizeSupport = MediaSize.A3;
            this.printColorSupport = ColorSupport.ColorMono;
            this.printDuplexSupport = DuplexSupport.On;
            this.scanModelType = ScannerModelType.MFCScanner;
            this.scanDocumentSizeSupport = ScannerModelSize.A4;
            this.scanHorizontalAlignment = HorizontalAlignment.LEFT;
            this.scanDuplexSupport = duplexSupport;
            this.scanColorSupport = ColorSupport.ColorMono;
            this.scanGroundColorRemoveSupport = GroundColorRemoveScanSupport.On;
            this.scanCornerScanSupport = CornerScanSupport.Off;
            this.scanAutoDocumentSizeSupport = AutoDocumentSizeScanSupport.On;
            if (scanHardwareError != ScanHardwareError.NoError) {
                this.scanHardwareError = Arrays.asList(scanHardwareError);
            }
        }
    }

    static class ADSDeviceSpec extends BrotherDeviceMasterSpec {
        public ADSDeviceSpec() {
            super(DeviceModelType.ADS);
            this.scanSupport = ScanSupport.On;
            this.scanModelType = ScannerModelType.DocumentScanner;
            this.scanDocumentSizeSupport = ScannerModelSize.A4;
            this.scanHorizontalAlignment = HorizontalAlignment.CENTER;
            this.scanDuplexSupport = DuplexSupport.On;
            this.scanColorSupport = ColorSupport.ColorMono;
            this.scanGroundColorRemoveSupport = GroundColorRemoveScanSupport.On;
            this.scanCornerScanSupport = CornerScanSupport.On;
            this.scanAutoDocumentSizeSupport = AutoDocumentSizeScanSupport.On;
        }
    }

    static class LaserFBDeviceSpec extends BrotherDeviceMasterSpec {
        public LaserFBDeviceSpec(DeviceModelType deviceModelType, HorizontalAlignment horizontalAlignment, DuplexSupport duplexSupport, ColorSupport colorSupport) {
            super(deviceModelType);
            this.scanModelType = ScannerModelType.MFCScanner;
            this.scanDocumentSizeSupport = ScannerModelSize.A4;
            this.scanHorizontalAlignment = horizontalAlignment;
            this.scanDuplexSupport = duplexSupport;
            this.scanColorSupport = colorSupport;
            this.scanGroundColorRemoveSupport = GroundColorRemoveScanSupport.Off;
            this.scanCornerScanSupport = CornerScanSupport.Off;
            this.scanAutoDocumentSizeSupport = AutoDocumentSizeScanSupport.Off;
        }
    }

    static class GeneralDLUSBDeviceSpec extends BrotherDeviceMasterSpec {
        public GeneralDLUSBDeviceSpec(DeviceModelType deviceModelType, PrinterModelType printerModelType, PrinterPDL printerPDL, ColorSupport colorSupport, DuplexSupport duplexSupport) {
            super(deviceModelType);
            this.printPaperSizeSupport = MediaSize.A4;
            this.printModelType = printerModelType;
            this.printPDL = printerPDL;
            this.printColorSupport = colorSupport;
            this.printDuplexSupport = duplexSupport;
            this.printDirectPrintingSupport = DirectPrintingSupport.On;
            this.scanSupport = ScanSupport.Off;
        }
    }

    static class GeneralDLLUSBDeviceSpec extends BrotherDeviceMasterSpec {
        public GeneralDLLUSBDeviceSpec(DeviceModelType deviceModelType, PrinterModelType printerModelType, PrinterPDL printerPDL, ColorSupport colorSupport, DuplexSupport duplexSupport, ScanSupport scanSupport) {
            super(deviceModelType);
            this.printPaperSizeSupport = MediaSize.A4;
            this.printModelType = printerModelType;
            this.printPDL = printerPDL;
            this.printColorSupport = colorSupport;
            this.printDuplexSupport = duplexSupport;
            this.printDirectPrintingSupport = DirectPrintingSupport.On;
            this.scanSupport = scanSupport;
        }
    }

    static class GeneralUSBDeviceSpec extends BrotherDeviceMasterSpec {
        public GeneralUSBDeviceSpec(DeviceModelType deviceModelType, PrinterModelType printerModelType, PrinterPDL printerPDL, ColorSupport colorSupport, DuplexSupport duplexSupport) {
            super(deviceModelType);
            this.printPaperSizeSupport = MediaSize.A4;
            this.printModelType = printerModelType;
            this.printPDL = printerPDL;
            this.printColorSupport = colorSupport;
            this.printDuplexSupport = duplexSupport;
            this.printDirectPrintingSupport = DirectPrintingSupport.Off;
            this.scanSupport = ScanSupport.On;
        }

        public GeneralUSBDeviceSpec(DeviceModelType deviceModelType, PrinterModelType printerModelType, PrinterPDL printerPDL, ColorSupport colorSupport, DuplexSupport duplexSupport, DirectPrintingSupport directPrintingSupport, ScanSupport scanSupport) {
            super(deviceModelType);
            this.printPaperSizeSupport = MediaSize.A4;
            this.printModelType = printerModelType;
            this.printPDL = printerPDL;
            this.printColorSupport = colorSupport;
            this.printDuplexSupport = duplexSupport;
            this.printDirectPrintingSupport = directPrintingSupport;
            this.scanSupport = scanSupport;
        }
    }

    static class FCLDeviceSpec extends BrotherDeviceMasterSpec {
        public FCLDeviceSpec(PrinterPDL printerPDL) {
            super(DeviceModelType.FCL);
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printPDL = printerPDL;
            this.scanSupport = ScanSupport.Off;
            this.printColorSupport = ColorSupport.ColorMono;
            this.printDuplexSupport = DuplexSupport.On;
            this.printDirectPrintingSupport = DirectPrintingSupport.On;
            this.printPaperSizeSupport = MediaSize.A4;
        }
    }

    static class SuperLow4CDeviceSpec extends BrotherDeviceMasterSpec {
        public SuperLow4CDeviceSpec() {
            super(DeviceModelType.SuperLow4C);
            this.printPDL = PrinterPDL.PostScript;
            this.printColorSupport = ColorSupport.ColorMono;
        }
    }

    static class BHS15SeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public BHS15SeriesDeviceSpec(ScanHardwareError scanHardwareError) {
            super(DeviceModelType.BHS15);
            this.scanHardwareError = Arrays.asList(scanHardwareError);
        }
    }

    static class CobraDeviceSpec extends BrotherDeviceMasterSpec {
        public CobraDeviceSpec() {
            super(DeviceModelType.Cobra);
            this.scanSupport = ScanSupport.Off;
        }
    }

    static class DLSeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public DLSeriesDeviceSpec(DuplexSupport duplexSupport, ScanSupport scanSupport) {
            super(DeviceModelType.DL);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printDuplexSupport = duplexSupport;
            this.printColorSupport = ColorSupport.Monochrome;
            this.printDirectPrintingSupport = DirectPrintingSupport.Off;
            this.scanSupport = scanSupport;
        }

        public DLSeriesDeviceSpec(DuplexSupport duplexSupport, ScanSupport scanSupport, DirectPrintingSupport directPrintingSupport) {
            super(DeviceModelType.DL);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printDuplexSupport = duplexSupport;
            this.printColorSupport = ColorSupport.Monochrome;
            this.printDirectPrintingSupport = directPrintingSupport;
            this.scanSupport = scanSupport;
        }
    }

    static class ELSeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public ELSeriesDeviceSpec(DuplexSupport duplexSupport, ScanSupport scanSupport) {
            super(DeviceModelType.DL);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printDuplexSupport = duplexSupport;
            this.printColorSupport = ColorSupport.Monochrome;
            this.printDirectPrintingSupport = DirectPrintingSupport.Off;
            this.scanSupport = scanSupport;
        }

        public ELSeriesDeviceSpec(DuplexSupport duplexSupport, ScanSupport scanSupport, DirectPrintingSupport directPrintingSupport) {
            super(DeviceModelType.DL);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printDuplexSupport = duplexSupport;
            this.printColorSupport = ColorSupport.Monochrome;
            this.printDirectPrintingSupport = directPrintingSupport;
            this.scanSupport = scanSupport;
        }
    }

    static class DLLSeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public DLLSeriesDeviceSpec(DuplexSupport duplexSupport, ScanSupport scanSupport) {
            super(DeviceModelType.DL);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_LASER;
            this.printDuplexSupport = duplexSupport;
            this.printColorSupport = ColorSupport.Monochrome;
            this.printDirectPrintingSupport = DirectPrintingSupport.Off;
            this.scanSupport = scanSupport;
        }
    }

    static class BHMB19SeriesDeviceSpec extends BrotherDeviceMasterSpec {
        public BHMB19SeriesDeviceSpec(MediaSize mediaSize) {
            super(DeviceModelType.BHMB19);
            this.printPDL = PrinterPDL.BrotherCommonPDL_AutoPageFlip;
            this.printModelType = PrinterModelType.PRINT_INKJET;
            this.printDuplexSupport = DuplexSupport.On;
            this.printColorSupport = ColorSupport.ColorMono;
            this.printDirectPrintingSupport = DirectPrintingSupport.Off;
            this.scanSupport = ScanSupport.On;
            this.printPaperSizeSupport = mediaSize;
        }
    }
}
