package com.brother.sdk.common.device.fax;

import com.brother.sdk.common.presets.BrotherDevicePresets;
import java.io.Serializable;
import java.util.List;

public class FaxCapabilities implements Serializable {
    private static final long serialVersionUID = -2570717817662806635L;
    public List<FaxReceiveMode> receiveModes = BrotherDevicePresets.FaxReceiveModes.DEFAULT_MODELS;
    public FaxReceiveMode currentReceiveMode = FaxReceiveMode.Undefined;
    public int maxMemoryCount = 0;
}
