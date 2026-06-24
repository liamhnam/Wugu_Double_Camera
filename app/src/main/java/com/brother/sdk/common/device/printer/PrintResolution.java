package com.brother.sdk.common.device.printer;

import com.brother.sdk.common.device.Resolution;
import com.p020hp.jipp.model.PowerState;
import com.wugu.doublecamera.enums.UiPosIndexEnum;

public enum PrintResolution {
    DPI_100x100(new Resolution(100, 100)) {
    },
    DPI_150x150(new Resolution(PowerState.Code.resetSoftGraceful, PowerState.Code.resetSoftGraceful)) {
    },
    DPI_200x200(new Resolution(200, 200)) {
    },
    DPI_300x300(new Resolution(300, 300)) {
    },
    DPI_600x600(new Resolution(600, 600)) {
    },
    DPI_1200x1200(new Resolution(UiPosIndexEnum.PE_BG_ROOT, UiPosIndexEnum.PE_BG_ROOT)) {
    };

    private Resolution mResolution;

    PrintResolution(Resolution resolution) {
        this.mResolution = resolution;
    }

    public Resolution getResolution() {
        return this.mResolution;
    }
}
