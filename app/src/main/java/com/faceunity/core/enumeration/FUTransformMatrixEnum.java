package com.faceunity.core.enumeration;

import com.arthenica.ffmpegkit.StreamInformation;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, m1293d2 = {"Lcom/faceunity/core/enumeration/FUTransformMatrixEnum;", "", StreamInformation.KEY_INDEX, "", "(Ljava/lang/String;II)V", "getIndex", "()I", "CCROT0", "CCROT90", "CCROT180", "CCROT270", "CCROT0_FLIPVERTICAL", "CCROT0_FLIPHORIZONTAL", "CCROT90_FLIPVERTICAL", "CCROT90_FLIPHORIZONTAL", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public enum FUTransformMatrixEnum {
    CCROT0(0),
    CCROT90(1),
    CCROT180(2),
    CCROT270(3),
    CCROT0_FLIPVERTICAL(4),
    CCROT0_FLIPHORIZONTAL(5),
    CCROT90_FLIPVERTICAL(6),
    CCROT90_FLIPHORIZONTAL(7);

    private final int index;

    FUTransformMatrixEnum(int i) {
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }
}
