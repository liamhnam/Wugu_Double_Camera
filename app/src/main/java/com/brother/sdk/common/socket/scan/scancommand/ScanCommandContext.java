package com.brother.sdk.common.socket.scan.scancommand;

import com.brother.sdk.common.device.CountrySpec;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.HorizontalAlignment;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.VerticalAlignment;
import com.brother.sdk.common.device.scanner.ScanPaperSource;
import com.brother.sdk.common.device.scanner.ScanSpecialMode;
import com.brother.sdk.common.device.scanner.ScannerModelSize;
import com.brother.sdk.common.device.scanner.ScannerModelType;
import com.brother.sdk.common.socket.scan.scancommand.ScanCommandParameters;
import java.util.ArrayList;
import java.util.List;

public class ScanCommandContext extends ScanCommandParameters {
    private static final double MM_PER_INCH = 25.4d;
    public ScanCommandParameters.ProtocolGeneration scanProtocol = ScanCommandParameters.ProtocolGeneration.ScanProtocol_2010;
    public CountrySpec Region = CountrySpec.Japan;
    public VerticalAlignment vFBAlignment = VerticalAlignment.TOP;
    public HorizontalAlignment hFBAlignment = HorizontalAlignment.CENTER;
    public VerticalAlignment vADFAlignment = VerticalAlignment.TOP;
    public HorizontalAlignment hADFAlignment = HorizontalAlignment.CENTER;
    public ScannerModelSize modelSize = ScannerModelSize.A4;
    public ScannerModelType modelType = ScannerModelType.MFCScanner;
    public ScanCommandParameters.ScanDocumentSideMargin sideMargin = new ScanCommandParameters.ScanDocumentSideMargin();
    public boolean AutoDocumentSizeScan = false;
    public MediaSize documentSize = MediaSize.A4;
    public ScanCommandParameters.ScanArea Area = new ScanCommandParameters.ScanArea();
    public ScanCommandParameters.ScanMode Mode = ScanCommandParameters.ScanMode.CGRAY;
    public Duplex duplex = Duplex.Simplex;
    public ScanPaperSource paperSource = ScanPaperSource.AUTO;
    public ScanCommandParameters.ScanResolution resolution = new ScanCommandParameters.ScanResolution(300, 300);
    public ScanCommandParameters.DataCompression Compression = ScanCommandParameters.DataCompression.JPEG;
    public ScanCommandParameters.JpegCompression Jpeg = ScanCommandParameters.JpegCompression.MID;
    public ScanSpecialMode Special = ScanSpecialMode.NORMAL_SCAN;
    public int brightness = 50;
    public int contrast = 50;
    public int groundColorCorrectionLevel = -1;
    public List<ScanCommandParameters.ErrorDetect> detectErrors = new ArrayList();

    public void validate() {
        int i = this.contrast;
        if (i < 0) {
            this.contrast = 0;
        } else if (i > 100) {
            this.contrast = 100;
        }
        int i2 = this.brightness;
        if (i2 < 0) {
            this.brightness = 0;
        } else if (i2 > 100) {
            this.brightness = 100;
        }
        if (this.groundColorCorrectionLevel > 255) {
            this.groundColorCorrectionLevel = 255;
        }
    }

    public void setDocumentSize(MediaSize mediaSize) {
        this.documentSize = mediaSize;
        this.Area.XStart = 0;
        this.Area.XEnd = (int) (this.documentSize.width * ((double) this.resolution.Width));
        this.Area.YStart = 0;
        this.Area.YEnd = (int) (this.documentSize.height * ((double) this.resolution.Height));
    }

    public void finalizeScanArea(ScanCommandParameters.ScannerInformation scannerInformation) {
        int i = this.modelType == ScannerModelType.DocumentScanner ? 0 : ((((int) ((this.sideMargin.Width / MM_PER_INCH) * ((double) scannerInformation.mResolution.Height))) + 1) / 2) * 2;
        int i2 = this.modelType == ScannerModelType.DocumentScanner ? 0 : ((((int) ((this.sideMargin.Height / MM_PER_INCH) * ((double) scannerInformation.mResolution.Height))) + 1) / 2) * 2;
        scannerInformation.mMaxWidthPixel = (scannerInformation.mMaxWidthPixel / 16) * 16;
        if (scannerInformation.mSource == ScanPaperSource.ADF) {
            if (scannerInformation.mMaxWidthPixel < this.Area.XEnd) {
                this.Area.XEnd = scannerInformation.mMaxWidthPixel;
            }
            int i3 = C07061.$SwitchMap$com$brother$sdk$common$device$HorizontalAlignment[this.hADFAlignment.ordinal()];
            if (i3 == 2) {
                this.Area.XStart = 0;
                ScanCommandParameters.ScanArea scanArea = this.Area;
                scanArea.XEnd = (((scanArea.XStart + this.Area.XEnd) - (i * 2)) / 16) * 16;
            } else if (i3 != 3) {
                this.Area.XStart = (((scannerInformation.mMaxWidthPixel - this.Area.XEnd) / 2) / 16) * 16;
                ScanCommandParameters.ScanArea scanArea2 = this.Area;
                scanArea2.XEnd = ((scanArea2.XStart + this.Area.XEnd) / 16) * 16;
            } else {
                this.Area.XStart = ((((scannerInformation.mMaxWidthPixel - this.Area.XEnd) + (i * 2)) + 15) / 16) * 16;
                this.Area.XEnd = scannerInformation.mMaxWidthPixel;
            }
            int i4 = C07061.$SwitchMap$com$brother$sdk$common$device$VerticalAlignment[this.vADFAlignment.ordinal()];
            this.Area.YStart = 0;
            this.Area.YEnd -= i2 * 2;
            return;
        }
        if (scannerInformation.mMaxWidthPixel < this.Area.XEnd) {
            this.Area.XEnd = scannerInformation.mMaxWidthPixel;
        }
        int i5 = C07061.$SwitchMap$com$brother$sdk$common$device$HorizontalAlignment[this.hFBAlignment.ordinal()];
        if (i5 == 2) {
            this.Area.XStart = 0;
            ScanCommandParameters.ScanArea scanArea3 = this.Area;
            scanArea3.XEnd = (((scanArea3.XStart + this.Area.XEnd) - (i * 2)) / 16) * 16;
        } else if (i5 != 3) {
            this.Area.XStart = (((scannerInformation.mMaxWidthPixel - this.Area.XEnd) / 2) / 16) * 16;
            ScanCommandParameters.ScanArea scanArea4 = this.Area;
            scanArea4.XEnd = ((scanArea4.XStart + this.Area.XEnd) / 16) * 16;
        } else {
            this.Area.XStart = ((((scannerInformation.mMaxWidthPixel - this.Area.XEnd) + (i * 2)) + 15) / 16) * 16;
            this.Area.XEnd = scannerInformation.mMaxWidthPixel;
        }
        if (scannerInformation.mMaxHeightPixel < this.Area.YEnd) {
            this.Area.YEnd = scannerInformation.mMaxHeightPixel;
        }
        int i6 = C07061.$SwitchMap$com$brother$sdk$common$device$VerticalAlignment[this.vFBAlignment.ordinal()];
        if (i6 == 2) {
            this.Area.YStart = (scannerInformation.mMaxHeightPixel - this.Area.YEnd) / 2;
            ScanCommandParameters.ScanArea scanArea5 = this.Area;
            scanArea5.YEnd = scanArea5.YStart + this.Area.YEnd;
        } else if (i6 != 3) {
            this.Area.YStart = 0;
            this.Area.YEnd -= i2 * 2;
        } else {
            this.Area.YStart = (scannerInformation.mMaxHeightPixel - this.Area.YEnd) + (i2 * 2);
            this.Area.YEnd = scannerInformation.mMaxHeightPixel;
        }
    }

    static class C07061 {
        static final int[] $SwitchMap$com$brother$sdk$common$device$HorizontalAlignment;
        static final int[] $SwitchMap$com$brother$sdk$common$device$VerticalAlignment;

        static {
            int[] iArr = new int[VerticalAlignment.values().length];
            $SwitchMap$com$brother$sdk$common$device$VerticalAlignment = iArr;
            try {
                iArr[VerticalAlignment.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$VerticalAlignment[VerticalAlignment.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$VerticalAlignment[VerticalAlignment.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[HorizontalAlignment.values().length];
            $SwitchMap$com$brother$sdk$common$device$HorizontalAlignment = iArr2;
            try {
                iArr2[HorizontalAlignment.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$HorizontalAlignment[HorizontalAlignment.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$brother$sdk$common$device$HorizontalAlignment[HorizontalAlignment.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
