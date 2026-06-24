package com.brother.sdk.common.device.scanner;

import com.brother.sdk.common.device.Resolution;
import com.p020hp.jipp.model.PowerState;

public enum ScanResolution {
    DPI_100x100(new Resolution(100, 100)) {
    },
    DPI_150x150(new Resolution(PowerState.Code.resetSoftGraceful, PowerState.Code.resetSoftGraceful)) {
    },
    DPI_200x200(new Resolution(200, 200)) {
    },
    DPI_300x300(new Resolution(300, 300)) {
    },
    DPI_600x600(new Resolution(600, 600)) {
    };

    private Resolution mResolution;

    ScanResolution(Resolution resolution) {
        this.mResolution = resolution;
    }

    public Resolution getResolution() {
        return this.mResolution;
    }
}
