package com.faceunity.core.avatar.scene;

import com.faceunity.core.avatar.base.BaseSceneAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.avatar.scene.ProcessorConfig;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J=\u0010\u0017\u001a\u00020\u00182.\u0010\u0019\u001a*\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001c0\u001aj\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001c`\u001dH\u0000¢\u0006\u0002\b\u001eR*\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\u000b\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR*\u0010\u000e\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\tR(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0003\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006 "}, m1293d2 = {"Lcom/faceunity/core/avatar/scene/ProcessorConfig;", "Lcom/faceunity/core/avatar/base/BaseSceneAttribute;", "()V", "value", "", "enableARModel", "getEnableARModel", "()Ljava/lang/Boolean;", "setEnableARModel", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "enableFaceProcessor", "getEnableFaceProcessor", "setEnableFaceProcessor", "enableHumanProcessor", "getEnableHumanProcessor", "setEnableHumanProcessor", "Lcom/faceunity/core/avatar/scene/ProcessorConfig$TrackScene;", "trackScene", "getTrackScene", "()Lcom/faceunity/core/avatar/scene/ProcessorConfig$TrackScene;", "setTrackScene", "(Lcom/faceunity/core/avatar/scene/ProcessorConfig$TrackScene;)V", "loadParams", "", "params", "Ljava/util/LinkedHashMap;", "", "Lkotlin/Function0;", "Lkotlin/collections/LinkedHashMap;", "loadParams$fu_core_all_featureRelease", "TrackScene", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class ProcessorConfig extends BaseSceneAttribute {
    private Boolean enableARModel;
    private Boolean enableFaceProcessor;
    private Boolean enableHumanProcessor;
    private TrackScene trackScene;

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, m1293d2 = {"Lcom/faceunity/core/avatar/scene/ProcessorConfig$TrackScene;", "", "(Ljava/lang/String;I)V", "SceneFull", "SceneHalf", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public enum TrackScene {
        SceneFull,
        SceneHalf
    }

    public final Boolean getEnableARModel() {
        return this.enableARModel;
    }

    public final void setEnableARModel(Boolean bool) {
        this.enableARModel = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableARMode$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableHumanProcessor() {
        return this.enableHumanProcessor;
    }

    public final void setEnableHumanProcessor(Boolean bool) {
        this.enableHumanProcessor = bool;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableHumanProcessor$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
    }

    public final Boolean getEnableFaceProcessor() {
        return this.enableFaceProcessor;
    }

    public final void setEnableFaceProcessor(Boolean bool) {
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (getHasLoaded()) {
                AvatarController.enableFaceProcessor$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), zBooleanValue, false, 4, null);
            }
        }
        this.enableFaceProcessor = bool;
    }

    public final TrackScene getTrackScene() {
        return this.trackScene;
    }

    public final void setTrackScene(TrackScene trackScene) {
        this.trackScene = trackScene;
        if (getHasLoaded()) {
            AvatarController.humanProcessorSet3DScene$default(getMAvatarController$fu_core_all_featureRelease(), getSceneId(), this.trackScene == TrackScene.SceneFull, false, 4, null);
        }
    }

    public final void loadParams$fu_core_all_featureRelease(final LinkedHashMap<String, Function0<Unit>> params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Boolean bool = this.enableARModel;
        if (bool != null) {
            final boolean zBooleanValue = bool.booleanValue();
            params.put("enableARMode", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableARMode(this.getSceneId(), zBooleanValue, false);
                }
            });
        }
        Boolean bool2 = this.enableHumanProcessor;
        if (bool2 != null) {
            final boolean zBooleanValue2 = bool2.booleanValue();
            params.put("enableHumanProcessor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableHumanProcessor(this.getSceneId(), zBooleanValue2, false);
                }
            });
        }
        Boolean bool3 = this.enableFaceProcessor;
        if (bool3 != null) {
            final boolean zBooleanValue3 = bool3.booleanValue();
            params.put("enableFaceProcessor", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().enableFaceProcessor(this.getSceneId(), zBooleanValue3, false);
                }
            });
        }
        final TrackScene trackScene = this.trackScene;
        if (trackScene != null) {
            params.put("humanProcessorSet3DScene", new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    this.getMAvatarController$fu_core_all_featureRelease().humanProcessorSet3DScene(this.getSceneId(), trackScene == ProcessorConfig.TrackScene.SceneFull, false);
                }
            });
        }
        setHasLoaded(true);
    }
}
