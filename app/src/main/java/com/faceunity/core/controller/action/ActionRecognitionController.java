package com.faceunity.core.controller.action;

import android.util.DisplayMetrics;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.utils.ScreenUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/controller/action/ActionRecognitionController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "applyControllerBundle", "", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "setParams", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class ActionRecognitionController extends BaseSingleController {
    @Override
    protected void applyControllerBundle(FUFeaturesData featuresData) {
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
                ActionRecognitionController.this.setParams();
            }
        });
    }

    public final void setParams() {
        DisplayMetrics screenInfo = ScreenUtils.INSTANCE.getScreenInfo(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease());
        if (screenInfo.heightPixels / screenInfo.widthPixels > 1.7777778f) {
            itemSetParam(ActionRecognitionParam.EDGE_DISTANCE, Double.valueOf(0.1d));
        }
    }
}
