package com.faceunity.core.model.musicFilter;

import com.faceunity.core.controller.musicFilter.MusicFilterController;
import com.faceunity.core.controller.musicFilter.MusicFilterParam;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.model.BaseSingleModel;
import com.faceunity.core.support.FURenderBridge;
import java.util.LinkedHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0012\u001a\u00020\u0013H\u0010¢\u0006\u0002\b\u0014J$\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018`\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0006H\u0014R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, m1293d2 = {"Lcom/faceunity/core/model/musicFilter/MusicFilter;", "Lcom/faceunity/core/model/BaseSingleModel;", "controlBundle", "Lcom/faceunity/core/entity/FUBundleData;", "(Lcom/faceunity/core/entity/FUBundleData;)V", "mMusicFilterController", "Lcom/faceunity/core/controller/musicFilter/MusicFilterController;", "getMMusicFilterController", "()Lcom/faceunity/core/controller/musicFilter/MusicFilterController;", "mMusicFilterController$delegate", "Lkotlin/Lazy;", "value", "", "musicTime", "getMusicTime", "()D", "setMusicTime", "(D)V", "buildFUFeaturesData", "Lcom/faceunity/core/entity/FUFeaturesData;", "buildFUFeaturesData$fu_core_all_featureRelease", "buildParams", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "getModelController", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class MusicFilter extends BaseSingleModel {

    private final Lazy mMusicFilterController;
    private double musicTime;

    private final MusicFilterController getMMusicFilterController() {
        return (MusicFilterController) this.mMusicFilterController.getValue();
    }

    public MusicFilter(FUBundleData controlBundle) {
        super(controlBundle);
        Intrinsics.checkParameterIsNotNull(controlBundle, "controlBundle");
        this.mMusicFilterController = LazyKt.lazy(new Function0<MusicFilterController>() {
            @Override
            public final MusicFilterController invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMMusicFilterController$fu_core_all_featureRelease();
            }
        });
    }

    @Override
    public MusicFilterController getModelController() {
        return getMMusicFilterController();
    }

    public final double getMusicTime() {
        return this.musicTime;
    }

    public final void setMusicTime(double d) {
        this.musicTime = d;
        updateAttributes(MusicFilterParam.MUSIC_TIME, Double.valueOf(d));
    }

    @Override
    protected LinkedHashMap<String, Object> buildParams() {
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(MusicFilterParam.MUSIC_TIME, Double.valueOf(this.musicTime));
        return linkedHashMap;
    }

    @Override
    public FUFeaturesData buildFUFeaturesData$fu_core_all_featureRelease() {
        return new FUFeaturesData(getControlBundle(), buildParams(), getEnable(), null, 0L, 24, null);
    }
}
