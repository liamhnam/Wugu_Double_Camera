package com.faceunity.core.controller.antialiasing;

import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUFeaturesData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, m1293d2 = {"Lcom/faceunity/core/controller/antialiasing/AntialiasingController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "applyControllerBundle", "", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class AntialiasingController extends BaseSingleController {
    @Override
    protected void applyControllerBundle(FUFeaturesData featuresData) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        BaseSingleController.applyControllerBundleAction$default(this, featuresData.getBundle(), featuresData.getEnable(), null, 4, null);
    }
}
