package com.faceunity.core.enumeration;

import com.faceunity.core.controller.facebeauty.FaceBeautyParam;
import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, m1293d2 = {"Lcom/faceunity/core/enumeration/FUFaceBeautyMultiModePropertyEnum;", "", "valueName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValueName", "()Ljava/lang/String;", "COLOR_INTENSITY", "REMOVE_POUCH_INTENSITY", "REMOVE_NASOLABIAL_FOLDS_INTENSITY", "CHEEK_THINNING_INTENSITY", "CHEEK_NARROW_INTENSITY", "CHEEK_SMALL_INTENSITY", "EYE_ENLARGING_INTENSITY", "CHIN_INTENSITY", "FOREHEAD_INTENSITY", "NOSE_INTENSITY", "MOUTH_INTENSITY", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public enum FUFaceBeautyMultiModePropertyEnum {
    COLOR_INTENSITY(FaceBeautyParam.COLOR_INTENSITY),
    REMOVE_POUCH_INTENSITY(FaceBeautyParam.REMOVE_POUCH_INTENSITY),
    REMOVE_NASOLABIAL_FOLDS_INTENSITY(FaceBeautyParam.REMOVE_NASOLABIAL_FOLDS_INTENSITY),
    CHEEK_THINNING_INTENSITY(FaceBeautyParam.CHEEK_THINNING_INTENSITY),
    CHEEK_NARROW_INTENSITY(FaceBeautyParam.CHEEK_NARROW_INTENSITY),
    CHEEK_SMALL_INTENSITY(FaceBeautyParam.CHEEK_SMALL_INTENSITY),
    EYE_ENLARGING_INTENSITY(FaceBeautyParam.EYE_ENLARGING_INTENSITY),
    CHIN_INTENSITY(FaceBeautyParam.CHIN_INTENSITY),
    FOREHEAD_INTENSITY(FaceBeautyParam.FOREHEAD_INTENSITY),
    NOSE_INTENSITY(FaceBeautyParam.NOSE_INTENSITY),
    MOUTH_INTENSITY(FaceBeautyParam.MOUTH_INTENSITY);

    private final String valueName;

    FUFaceBeautyMultiModePropertyEnum(String str) {
        this.valueName = str;
    }

    public final String getValueName() {
        return this.valueName;
    }
}
