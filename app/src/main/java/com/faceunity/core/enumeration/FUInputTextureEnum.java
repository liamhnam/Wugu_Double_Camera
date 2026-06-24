package com.faceunity.core.enumeration;

import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m1293d2 = {"Lcom/faceunity/core/enumeration/FUInputTextureEnum;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "FU_ADM_FLAG_COMMON_TEXTURE", "FU_ADM_FLAG_EXTERNAL_OES_TEXTURE", "FU_ADM_FLAG_NV21_TEXTURE", "FU_ADM_FLAG_I420_TEXTURE", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public enum FUInputTextureEnum {
    FU_ADM_FLAG_COMMON_TEXTURE(0),
    FU_ADM_FLAG_EXTERNAL_OES_TEXTURE(1),
    FU_ADM_FLAG_NV21_TEXTURE(4),
    FU_ADM_FLAG_I420_TEXTURE(8);

    private final int type;

    FUInputTextureEnum(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }
}
