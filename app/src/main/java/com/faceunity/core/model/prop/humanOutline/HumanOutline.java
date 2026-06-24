package com.faceunity.core.model.prop.humanOutline;

import com.faceunity.core.controller.prop.PropParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUColorRGBData;
import com.faceunity.core.model.prop.Prop;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016H\u0010¢\u0006\u0002\b\u0019R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u001a"}, m1293d2 = {"Lcom/faceunity/core/model/prop/humanOutline/HumanOutline;", "Lcom/faceunity/core/model/prop/Prop;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "value", "Lcom/faceunity/core/entity/FUColorRGBData;", PropParam.LINE_COLOR, "getLineColor", "()Lcom/faceunity/core/entity/FUColorRGBData;", "setLineColor", "(Lcom/faceunity/core/entity/FUColorRGBData;)V", "", PropParam.LINE_GAP, "getLineGap", "()D", "setLineGap", "(D)V", PropParam.LINE_SIZE, "getLineSize", "setLineSize", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "buildParams$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class HumanOutline extends Prop {
    private FUColorRGBData lineColor;
    private double lineGap;
    private double lineSize;

    public HumanOutline(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.lineGap = 3.0d;
        this.lineSize = 1.0d;
        this.lineColor = new FUColorRGBData(0.0d, 0.0d, 255.0d, 0.0d, 8, null);
    }

    public final double getLineGap() {
        return this.lineGap;
    }

    public final void setLineGap(double d) {
        this.lineGap = d;
        updateAttributes(PropParam.LINE_GAP, Double.valueOf(d));
    }

    public final double getLineSize() {
        return this.lineSize;
    }

    public final void setLineSize(double d) {
        this.lineSize = d;
        updateAttributes(PropParam.LINE_SIZE, Double.valueOf(d));
    }

    public final FUColorRGBData getLineColor() {
        return this.lineColor;
    }

    public final void setLineColor(FUColorRGBData value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.lineColor = value;
        updateAttributes(PropParam.LINE_COLOR, value.toScaleColorArray());
    }

    @Override
    public LinkedHashMap<String, Object> buildParams$fu_core_all_featureRelease() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap;
        linkedHashMap2.put(PropParam.LINE_GAP, Double.valueOf(this.lineGap));
        linkedHashMap2.put(PropParam.LINE_SIZE, Double.valueOf(this.lineSize));
        linkedHashMap2.put(PropParam.LINE_COLOR, this.lineColor.toScaleColorArray());
        return linkedHashMap;
    }
}
