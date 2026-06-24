package com.faceunity.core.enumeration;

import com.arthenica.ffmpegkit.StreamInformation;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, m1293d2 = {"Lcom/faceunity/core/enumeration/FUPortraitSegmentationEnum;", "", StreamInformation.KEY_INDEX, "", "(Ljava/lang/String;II)V", "getIndex", "()I", "MODE_SEG_CPU_COMMON", "MODE_SEG_GPU_COMMON", "MODE_SEG_GPU_METING", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public enum FUPortraitSegmentationEnum {
    MODE_SEG_CPU_COMMON(0),
    MODE_SEG_GPU_COMMON(1),
    MODE_SEG_GPU_METING(2);

    private final int index;

    FUPortraitSegmentationEnum(int i) {
        this.index = i;
    }

    public final int getIndex() {
        return this.index;
    }
}
