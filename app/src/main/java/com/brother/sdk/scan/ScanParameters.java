package com.brother.sdk.scan;

import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;

public class ScanParameters {
    public MediaSize documentSize = MediaSize.A4;
    public Duplex duplex = Duplex.Simplex;
    public ColorProcessing colorType = ColorProcessing.FullColor;
    public Resolution resolution = new Resolution(200, 200);
    public ScanPaperSource paperSource = ScanPaperSource.AUTO;
    public boolean autoDocumentSizeScan = false;
    public boolean whitePageRemove = false;
    public boolean groundColorCorrection = false;
    public ScanSpecialMode specialScanMode = ScanSpecialMode.NORMAL_SCAN;
}
