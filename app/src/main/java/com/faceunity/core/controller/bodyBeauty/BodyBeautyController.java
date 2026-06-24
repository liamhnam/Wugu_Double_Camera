package com.faceunity.core.controller.bodyBeauty;

import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUFeaturesData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014¨\u0006\u0007"}, m1293d2 = {"Lcom/faceunity/core/controller/bodyBeauty/BodyBeautyController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "applyControllerBundle", "", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BodyBeautyController extends BaseSingleController {
    @Override
    protected void applyControllerBundle(final FUFeaturesData featuresData) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        applyControllerBundleAction(featuresData.getBundle(), featuresData.getEnable(), new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                BodyBeautyController.this.itemSetParam(featuresData.getParam());
            }
        });
    }
}
