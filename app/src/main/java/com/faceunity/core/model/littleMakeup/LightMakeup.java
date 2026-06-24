package com.faceunity.core.model.littleMakeup;

import com.faceunity.core.controller.littleMakeup.LightMakeupController;
import com.faceunity.core.controller.littleMakeup.LightMakeupParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\u0014\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020P0OH\u0014J\b\u0010Q\u001a\u00020RH\u0014R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR(\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R$\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000bR(\u0010\u001e\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R$\u0010!\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\t\"\u0004\b#\u0010\u000bR(\u0010$\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000f\"\u0004\b&\u0010\u0011R$\u0010'\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\t\"\u0004\b)\u0010\u000bR(\u0010*\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u000f\"\u0004\b,\u0010\u0011R$\u0010-\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\t\"\u0004\b/\u0010\u000bR(\u00100\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000f\"\u0004\b2\u0010\u0011R$\u00104\u001a\u0002032\u0006\u0010\u0005\u001a\u000203@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R(\u00109\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u000f\"\u0004\b;\u0010\u0011R$\u0010=\u001a\u00020<2\u0006\u0010\u0005\u001a\u00020<@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR$\u0010B\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\t\"\u0004\bD\u0010\u000bR$\u0010E\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\t\"\u0004\bG\u0010\u000bR$\u0010H\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\t\"\u0004\bJ\u0010\u000bR(\u0010K\u001a\u0004\u0018\u00010\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\u000f\"\u0004\bM\u0010\u0011¨\u0006S"}, m1293d2 = {"Lcom/faceunity/core/model/littleMakeup/LightMakeup;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "", "blusherIntensity", "getBlusherIntensity", "()D", "setBlusherIntensity", "(D)V", "", "blusherTex", "getBlusherTex", "()Ljava/lang/String;", "setBlusherTex", "(Ljava/lang/String;)V", "", "enableLibMask", "getEnableLibMask", "()Z", "setEnableLibMask", "(Z)V", "enableUserFixLandmark", "getEnableUserFixLandmark", "setEnableUserFixLandmark", "eyeBrowIntensity", "getEyeBrowIntensity", "setEyeBrowIntensity", "eyeBrowTex", "getEyeBrowTex", "setEyeBrowTex", "eyeLashIntensity", "getEyeLashIntensity", "setEyeLashIntensity", "eyeLashTex", "getEyeLashTex", "setEyeLashTex", "eyeLineIntensity", "getEyeLineIntensity", "setEyeLineIntensity", "eyeLinerTex", "getEyeLinerTex", "setEyeLinerTex", "eyeShadowIntensity", "getEyeShadowIntensity", "setEyeShadowIntensity", "eyeShadowTex", "getEyeShadowTex", "setEyeShadowTex", "", "fixLandmarkArray", "getFixLandmarkArray", "()[F", "setFixLandmarkArray", "([F)V", "highLightTex", "getHighLightTex", "setHighLightTex", "Lcom/faceunity/core/entity/FUColorRGBData;", "lipColor", "getLipColor", "()Lcom/faceunity/core/entity/FUColorRGBData;", "setLipColor", "(Lcom/faceunity/core/entity/FUColorRGBData;)V", "lipIntensity", "getLipIntensity", "setLipIntensity", "makeupIntensity", "getMakeupIntensity", "setMakeupIntensity", "pupilIntensity", "getPupilIntensity", "setPupilIntensity", "pupilTex", "getPupilTex", "setPupilTex", "buildParams", "Ljava/util/LinkedHashMap;", "", "getModelController", "Lcom/faceunity/core/controller/littleMakeup/LightMakeupController;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class LightMakeup extends BaseSingleModel {
    private double blusherIntensity;
    private String blusherTex;
    private boolean enableLibMask;
    private boolean enableUserFixLandmark;
    private double eyeBrowIntensity;
    private String eyeBrowTex;
    private double eyeLashIntensity;
    private String eyeLashTex;
    private double eyeLineIntensity;
    private String eyeLinerTex;
    private double eyeShadowIntensity;
    private String eyeShadowTex;
    private float[] fixLandmarkArray;
    private String highLightTex;
    private FUColorRGBData lipColor;
    private double lipIntensity;
    private double makeupIntensity;
    private double pupilIntensity;
    private String pupilTex;

    public LightMakeup(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.fixLandmarkArray = new float[0];
        this.makeupIntensity = 1.0d;
        this.lipColor = new FUColorRGBData(0.0d, 0.0d, 0.0d, 0.0d);
        this.enableLibMask = true;
    }

    @Override
    public LightMakeupController getMBgSegGreenController() {
        return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMLightMakeupController$fu_core_all_featureRelease();
    }

    public final boolean getEnableUserFixLandmark() {
        return this.enableUserFixLandmark;
    }

    public final void setEnableUserFixLandmark(boolean z) {
        this.enableUserFixLandmark = z;
        updateAttributes(LightMakeupParam.IS_USER_FIX, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final float[] getFixLandmarkArray() {
        return this.fixLandmarkArray;
    }

    public final void setFixLandmarkArray(float[] value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.fixLandmarkArray = value;
        updateAttributes(LightMakeupParam.FIX_MAKEUP_DATA, value);
    }

    public final double getMakeupIntensity() {
        return this.makeupIntensity;
    }

    public final void setMakeupIntensity(double d) {
        this.makeupIntensity = d;
        updateAttributes("makeup_intensity", Double.valueOf(d));
    }

    public final double getLipIntensity() {
        return this.lipIntensity;
    }

    public final void setLipIntensity(double d) {
        this.lipIntensity = d;
        updateAttributes("makeup_intensity_lip", Double.valueOf(d));
    }

    public final double getEyeLineIntensity() {
        return this.eyeLineIntensity;
    }

    public final void setEyeLineIntensity(double d) {
        this.eyeLineIntensity = d;
        updateAttributes("makeup_intensity_eyeLiner", Double.valueOf(d));
    }

    public final double getBlusherIntensity() {
        return this.blusherIntensity;
    }

    public final void setBlusherIntensity(double d) {
        this.blusherIntensity = d;
        updateAttributes("makeup_intensity_blusher", Double.valueOf(d));
    }

    public final double getPupilIntensity() {
        return this.pupilIntensity;
    }

    public final void setPupilIntensity(double d) {
        this.pupilIntensity = d;
        updateAttributes("makeup_intensity_pupil", Double.valueOf(d));
    }

    public final double getEyeBrowIntensity() {
        return this.eyeBrowIntensity;
    }

    public final void setEyeBrowIntensity(double d) {
        this.eyeBrowIntensity = d;
        updateAttributes("makeup_intensity_eyeBrow", Double.valueOf(d));
    }

    public final double getEyeShadowIntensity() {
        return this.eyeShadowIntensity;
    }

    public final void setEyeShadowIntensity(double d) {
        this.eyeShadowIntensity = d;
        updateAttributes("makeup_intensity_eye", Double.valueOf(d));
    }

    public final double getEyeLashIntensity() {
        return this.eyeLashIntensity;
    }

    public final void setEyeLashIntensity(double d) {
        this.eyeLashIntensity = d;
        updateAttributes("makeup_intensity_eyelash", Double.valueOf(d));
    }

    public final FUColorRGBData getLipColor() {
        return this.lipColor;
    }

    public final void setLipColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.lipColor = value;
        updateAttributes("makeup_lip_color", value.toScaleColorArray());
    }

    public final boolean getEnableLibMask() {
        return this.enableLibMask;
    }

    public final void setEnableLibMask(boolean z) {
        this.enableLibMask = z;
        updateAttributes(LightMakeupParam.MAKEUP_LIP_MASK, Double.valueOf(z ? 1.0d : 0.0d));
    }

    public final String getEyeBrowTex() {
        return this.eyeBrowTex;
    }

    public final void setEyeBrowTex(String str) {
        this.eyeBrowTex = str;
        updateItemTex("tex_brow", str);
    }

    public final String getEyeShadowTex() {
        return this.eyeShadowTex;
    }

    public final void setEyeShadowTex(String str) {
        this.eyeShadowTex = str;
        updateItemTex("tex_eye", str);
    }

    public final String getPupilTex() {
        return this.pupilTex;
    }

    public final void setPupilTex(String str) {
        this.pupilTex = str;
        updateItemTex("tex_pupil", str);
    }

    public final String getEyeLashTex() {
        return this.eyeLashTex;
    }

    public final void setEyeLashTex(String str) {
        this.eyeLashTex = str;
        updateItemTex("tex_eyeLash", str);
    }

    public final String getEyeLinerTex() {
        return this.eyeLinerTex;
    }

    public final void setEyeLinerTex(String str) {
        this.eyeLinerTex = str;
        updateItemTex("tex_eyeLiner", str);
    }

    public final String getBlusherTex() {
        return this.blusherTex;
    }

    public final void setBlusherTex(String str) {
        this.blusherTex = str;
        updateItemTex("tex_blusher", str);
    }

    public final String getHighLightTex() {
        return this.highLightTex;
    }

    public final void setHighLightTex(String str) {
        this.highLightTex = str;
        updateItemTex("tex_highlight", str);
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(LightMakeupParam.REVERSE_ALPHA, Double.valueOf(1.0d));
        linkedHashMap2.put(LightMakeupParam.IS_USER_FIX, Double.valueOf(this.enableUserFixLandmark ? 1.0d : 0.0d));
        float[] fArr = this.fixLandmarkArray;
        if (!(fArr.length == 0)) {
            linkedHashMap2.put(LightMakeupParam.FIX_MAKEUP_DATA, fArr);
        }
        linkedHashMap2.put("makeup_intensity", Double.valueOf(this.makeupIntensity));
        linkedHashMap2.put("makeup_intensity_lip", Double.valueOf(this.lipIntensity));
        linkedHashMap2.put("makeup_intensity_eyeLiner", Double.valueOf(this.eyeLineIntensity));
        linkedHashMap2.put("makeup_intensity_blusher", Double.valueOf(this.blusherIntensity));
        linkedHashMap2.put("makeup_intensity_pupil", Double.valueOf(this.pupilIntensity));
        linkedHashMap2.put("makeup_intensity_eyeBrow", Double.valueOf(this.eyeBrowIntensity));
        linkedHashMap2.put("makeup_intensity_eye", Double.valueOf(this.eyeShadowIntensity));
        linkedHashMap2.put("makeup_intensity_eyelash", Double.valueOf(this.eyeLashIntensity));
        String str = this.eyeBrowTex;
        if (str != null) {
            linkedHashMap2.put("tex_brow", str);
        }
        String str2 = this.eyeShadowTex;
        if (str2 != null) {
            linkedHashMap2.put("tex_eye", str2);
        }
        String str3 = this.pupilTex;
        if (str3 != null) {
            linkedHashMap2.put("tex_pupil", str3);
        }
        String str4 = this.eyeLashTex;
        if (str4 != null) {
            linkedHashMap2.put("tex_eyeLash", str4);
        }
        String str5 = this.eyeLinerTex;
        if (str5 != null) {
            linkedHashMap2.put("tex_eyeLiner", str5);
        }
        String str6 = this.blusherTex;
        if (str6 != null) {
            linkedHashMap2.put("tex_blusher", str6);
        }
        String str7 = this.highLightTex;
        if (str7 != null) {
            linkedHashMap2.put("tex_highlight", str7);
        }
        linkedHashMap2.put("makeup_lip_color", this.lipColor.toScaleColorArray());
        linkedHashMap2.put(LightMakeupParam.MAKEUP_LIP_MASK, Double.valueOf(this.enableLibMask ? 1.0d : 0.0d));
        return linkedHashMap;
    }
}
