package com.faceunity.core.enumeration;

import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m1293d2 = {"Lcom/faceunity/core/enumeration/FUInputBufferEnum;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "FU_FORMAT_NV21_BUFFER", "FU_FORMAT_RGBA_BUFFER", "FU_FORMAT_I420_BUFFER", "FU_FORMAT_YUV_BUFFER", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public enum FUInputBufferEnum {
    FU_FORMAT_NV21_BUFFER(2),
    FU_FORMAT_RGBA_BUFFER(4),
    FU_FORMAT_I420_BUFFER(13),
    FU_FORMAT_YUV_BUFFER(0);

    private final int type;

    FUInputBufferEnum(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }
}
