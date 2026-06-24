package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.scanner.ScanLongLengthEdge;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.brother.sdk.common.device.scanner.Scanner;
import com.brother.sdk.common.device.scanner.ScannerModelSize;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.util.ArrayList;
import java.util.List;

public class ScanCommandDeviceConfiguration extends ScanCommandParameters {
    public Integer ADFDocumentsCapacity;
    public Integer ADFDuplexMaxScanHeight;
    public Integer ADFDuplexMaxScanWidth;
    public Integer ADFDuplexMinScanHeight;
    public Integer ADFDuplexMinScanWidth;
    public Integer ADFSimplexMaxScanHeight;
    public Integer ADFSimplexMaxScanWidth;
    public Integer ADFSimplexMinScanHeight;
    public Integer ADFSimplexMinScanWidth;
    public HorizontalAlignment ADF_H;
    public VerticalAlignment ADF_V;
    public boolean AutoDocumentSizeScanSupport;
    public ScanCommandParameters.CarrierSheet Carrier;
    public ScanCommandParameters.EdgeCoordinate EdgeBottom;
    public ScanCommandParameters.EdgeCoordinate EdgeLeft;
    public ScanCommandParameters.EdgeCoordinate EdgeRight;
    public ScanCommandParameters.EdgeCoordinate EdgeTop;
    public List<ScanCommandParameters.ErrorDetect> ErrorDetects;
    public Integer FBMaxScanHeight;
    public Integer FBMaxScanWidth;
    public HorizontalAlignment FB_H;
    public VerticalAlignment FB_V;
    public boolean GroundColorCorrectionSupport;
    public ScanLongLengthEdge LongEdgeScan;
    public Boolean PageCountsSpecify;
    public boolean PushScanSupport;
    public List<ScanCommandParameters.ScanMode> ScanModes;
    public ScanCommandParameters.ProtocolGeneration ScanProtocol;
    public ScanCommandParameters.ScanDocumentSideMargin SideMargin;
    public List<ScanSpecialMode> SpecialScanModes;
    public ScannerModelSize modelSize;

    public ScanCommandDeviceConfiguration() {
        this.ScanModes = new ArrayList();
        this.modelSize = ScannerModelSize.A4;
        this.ScanProtocol = ScanCommandParameters.ProtocolGeneration.ScanProtocol_2010;
        this.SpecialScanModes = null;
        this.AutoDocumentSizeScanSupport = false;
        this.FB_H = HorizontalAlignment.UNDEFINED;
        this.FB_V = VerticalAlignment.UNDEFINED;
        this.ADF_H = HorizontalAlignment.UNDEFINED;
        this.ADF_V = VerticalAlignment.UNDEFINED;
        this.Carrier = ScanCommandParameters.CarrierSheet.NoSupport;
        this.EdgeTop = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeBottom = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeLeft = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeRight = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.PushScanSupport = false;
        this.GroundColorCorrectionSupport = false;
        this.LongEdgeScan = ScanLongLengthEdge.NoSupport;
        this.ErrorDetects = null;
        this.SideMargin = new ScanCommandParameters.ScanDocumentSideMargin();
        this.ADFSimplexMaxScanWidth = null;
        this.ADFSimplexMaxScanHeight = null;
        this.ADFSimplexMinScanWidth = null;
        this.ADFSimplexMinScanHeight = null;
        this.ADFDuplexMaxScanWidth = null;
        this.ADFDuplexMaxScanHeight = null;
        this.ADFDuplexMinScanWidth = null;
        this.ADFDuplexMinScanHeight = null;
        this.FBMaxScanWidth = null;
        this.FBMaxScanHeight = null;
        this.ADFDocumentsCapacity = null;
        this.PageCountsSpecify = null;
    }

    public ScanCommandDeviceConfiguration(Scanner scanner) {
        this.ScanProtocol = ScanCommandParameters.ProtocolGeneration.fromValue(scanner.protocolVersion);
        this.modelSize = scanner.modelSize;
        this.SpecialScanModes = scanner.capabilities.specialScanModes;
        this.AutoDocumentSizeScanSupport = scanner.autoDocumentSizeScanSupport;
        this.FB_H = scanner.modelHorizontalAlignment;
        this.FB_V = scanner.modelVerticalAlignment;
        this.ADF_H = scanner.modelHorizontalAlignmentADF;
        this.ADF_V = scanner.modelVerticalAlignmentADF;
        this.Carrier = ScanCommandParameters.CarrierSheet.NoSupport;
        this.EdgeTop = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeBottom = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeLeft = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.EdgeRight = ScanCommandParameters.EdgeCoordinate.NormalEdge;
        this.PushScanSupport = false;
        this.GroundColorCorrectionSupport = scanner.deviceGroundColorCorrection;
        this.LongEdgeScan = scanner.longLengthScanSupport;
        this.ErrorDetects = null;
        this.SideMargin = new ScanCommandParameters.ScanDocumentSideMargin();
        this.ADFSimplexMaxScanWidth = null;
        this.ADFSimplexMaxScanHeight = null;
        this.ADFSimplexMinScanWidth = null;
        this.ADFSimplexMinScanHeight = null;
        this.ADFDuplexMaxScanWidth = null;
        this.ADFDuplexMaxScanHeight = null;
        this.ADFDuplexMinScanWidth = null;
        this.ADFDuplexMinScanHeight = null;
        this.FBMaxScanWidth = null;
        this.FBMaxScanHeight = null;
        this.ADFDocumentsCapacity = null;
        this.PageCountsSpecify = null;
    }

    public ScanCommandContext validate(ScanCommandContext scanCommandContext) {
        try {
            VerticalAlignment verticalAlignment = scanCommandContext.vFBAlignment;
            VerticalAlignment verticalAlignment2 = this.FB_V;
            if (verticalAlignment != verticalAlignment2 && verticalAlignment2 != VerticalAlignment.UNDEFINED) {
                scanCommandContext.vFBAlignment = this.FB_V;
            }
            HorizontalAlignment horizontalAlignment = scanCommandContext.hFBAlignment;
            HorizontalAlignment horizontalAlignment2 = this.FB_H;
            if (horizontalAlignment != horizontalAlignment2 && horizontalAlignment2 != HorizontalAlignment.UNDEFINED) {
                scanCommandContext.hFBAlignment = this.FB_H;
            }
            VerticalAlignment verticalAlignment3 = scanCommandContext.vADFAlignment;
            VerticalAlignment verticalAlignment4 = this.ADF_V;
            if (verticalAlignment3 != verticalAlignment4 && verticalAlignment4 != VerticalAlignment.UNDEFINED) {
                scanCommandContext.vADFAlignment = this.ADF_V;
            }
            HorizontalAlignment horizontalAlignment3 = scanCommandContext.hADFAlignment;
            HorizontalAlignment horizontalAlignment4 = this.ADF_H;
            if (horizontalAlignment3 != horizontalAlignment4 && horizontalAlignment4 != HorizontalAlignment.UNDEFINED) {
                scanCommandContext.hADFAlignment = this.ADF_H;
            }
            ScannerModelSize scannerModelSize = scanCommandContext.modelSize;
            ScannerModelSize scannerModelSize2 = this.modelSize;
            if (scannerModelSize != scannerModelSize2) {
                scanCommandContext.modelSize = scannerModelSize2;
            }
            scanCommandContext.scanProtocol = this.ScanProtocol;
            if (!this.ScanModes.contains(scanCommandContext.Mode)) {
                scanCommandContext.Mode = this.ScanModes.get(this.ScanModes.size() - 1);
            }
            List<ScanSpecialMode> list = this.SpecialScanModes;
            if (list != null && !list.contains(scanCommandContext.Special)) {
                scanCommandContext.Special = ScanSpecialMode.NORMAL_SCAN;
            }
            if (!this.GroundColorCorrectionSupport) {
                scanCommandContext.groundColorCorrectionLevel = -1;
            }
            if (scanCommandContext.Special != ScanSpecialMode.COPYQUALITY_SCAN) {
                scanCommandContext.sideMargin = this.SideMargin;
            }
        } catch (Exception unused) {
        }
        return scanCommandContext;
    }
}
