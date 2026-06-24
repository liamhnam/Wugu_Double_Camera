package com.brother.sdk.common.device.scanner;

import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.presets.BrotherDeviceMasterSpec;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Scanner implements Serializable {
    private static final long serialVersionUID = 545109429882780645L;
    public boolean autoDocumentSizeScanSupport;
    public HorizontalAlignment modelHorizontalAlignmentADF;
    public VerticalAlignment modelVerticalAlignmentADF;
    public boolean autoDocumentCropSupport = false;
    public ScannerModelSize modelSize = ScannerModelSize.A4;
    public ScannerModelType modelType = ScannerModelType.MFCScanner;
    public HorizontalAlignment modelHorizontalAlignment = HorizontalAlignment.LEFT;
    public VerticalAlignment modelVerticalAlignment = VerticalAlignment.TOP;
    public boolean multiPaperFeedDetect = false;
    public boolean deviceWhitePageRemove = false;
    public boolean deviceGroundColorCorrection = false;
    public ScanLongLengthEdge longLengthScanSupport = ScanLongLengthEdge.NoSupport;
    public List<BrotherDeviceMasterSpec.ScanHardwareError> hardwareErrors = new ArrayList();
    public ScanCapabilities capabilities = new ScanCapabilities();
    public int protocolVersion = ScanCommandParameters.ProtocolGeneration.ScanProtocol_2010.toValue();
}
