package com.faceunity.core.faceunity;

import kotlin.Metadata;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m1293d2 = {"Lcom/faceunity/core/faceunity/AICommonData;", "", "()V", "FUAIFACE_DISABLE_ARMESHV2", "", "FUAIFACE_DISABLE_DEL_SPOT", "FUAIFACE_DISABLE_FACE_OCCU", "FUAIFACE_DISABLE_LANDMARK_HP_OCCU", "FUAIFACE_DISABLE_RACE", "FUAIFACE_DISABLE_SKIN_SEG", "FUAIFACE_ENABLE_ALL", "FUAIHUMAN_DISABLE_HUMAN_SEG", "FUAIHUMAN_ENABLE_ALL", "FUAIHUMAN_SEG_CPU_COMM", "FUAIHUMAN_SEG_GPU_COMM", "FUAIHUMAN_SEG_GPU_MEET", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class AICommonData {
    public static final int FUAIFACE_DISABLE_ARMESHV2 = 8;
    public static final int FUAIFACE_DISABLE_DEL_SPOT = 4;
    public static final int FUAIFACE_DISABLE_FACE_OCCU = 1;
    public static final int FUAIFACE_DISABLE_LANDMARK_HP_OCCU = 32;
    public static final int FUAIFACE_DISABLE_RACE = 16;
    public static final int FUAIFACE_DISABLE_SKIN_SEG = 2;
    public static final int FUAIFACE_ENABLE_ALL = 0;
    public static final int FUAIHUMAN_DISABLE_HUMAN_SEG = 1;
    public static final int FUAIHUMAN_ENABLE_ALL = 0;
    public static final int FUAIHUMAN_SEG_CPU_COMM = 0;
    public static final int FUAIHUMAN_SEG_GPU_COMM = 1;
    public static final int FUAIHUMAN_SEG_GPU_MEET = 2;
    public static final AICommonData INSTANCE = new AICommonData();

    private AICommonData() {
    }
}
