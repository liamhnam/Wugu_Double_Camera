package com.brother.sdk.common.device.scanner;

import com.brother.sdk.common.device.ColorProcessing;
import com.brother.sdk.common.device.Duplex;
import com.brother.sdk.common.device.MediaSize;
import com.brother.sdk.common.device.Resolution;
import com.brother.sdk.common.presets.BrotherDevicePresets;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class ScanCapabilities implements Serializable {
    private static final long serialVersionUID = -7142749027872502552L;
    public HashMap<ScanType, List<MediaSize>> documentSizes = new HashMap<ScanType, List<MediaSize>>() {
        private static final long serialVersionUID = 1;

        {
            put(ScanType.FlatbedScan, BrotherDevicePresets.ScanDocumentSizes.A3_FB_MODELS);
            put(ScanType.ADFSimplexScan, BrotherDevicePresets.ScanDocumentSizes.A3_ADF_MODELS);
            put(ScanType.ADFDuplexScan, BrotherDevicePresets.ScanDocumentSizes.A3_ADF_MODELS);
        }
    };
    public List<ColorProcessing> colorTypes = BrotherDevicePresets.ColorTypes.COLOR_MODELS;
    public List<Duplex> duplices = BrotherDevicePresets.Duplices.DUPLEX_MODELS;
    public List<Resolution> resolutions = BrotherDevicePresets.ScanResolutions.DEFAULT_MODELS;
    public List<ScanPaperSource> paperSources = BrotherDevicePresets.ScanPaperSources.DEFAULT_MODELS;
    public List<ScanSpecialMode> specialScanModes = BrotherDevicePresets.ScanSpecialModes.DEFAULT_MODELS;
}
