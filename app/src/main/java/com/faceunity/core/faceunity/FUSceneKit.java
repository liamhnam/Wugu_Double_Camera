package com.faceunity.core.faceunity;

import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.avatar.control.BaseAvatarController;
import com.faceunity.core.avatar.listener.OnSceneListener;
import com.faceunity.core.avatar.model.Scene;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.utils.FULogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 *2\u00020\u0001:\u0001*B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\u001c\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0007J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aJ\u000e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u0014J\u0006\u0010\u001f\u001a\u00020\u0014J\u000e\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\nJ\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0011J\u0016\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020\u0012J\u0016\u0010$\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0012J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0011J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010)\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0011R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUSceneKit;", "", "()V", "mAvatarController", "Lcom/faceunity/core/avatar/control/AvatarController;", "getMAvatarController", "()Lcom/faceunity/core/avatar/control/AvatarController;", "mAvatarController$delegate", "Lkotlin/Lazy;", "programBinaryDirectory", "", "getProgramBinaryDirectory", "()Ljava/lang/String;", "setProgramBinaryDirectory", "(Ljava/lang/String;)V", "sceneCacheMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "Lcom/faceunity/core/avatar/model/Scene;", "addScene", "", "scene", "listener", "Lcom/faceunity/core/avatar/listener/OnSceneListener;", "addSceneGL", "getAllScene", "", "preloadBundleUnThread", "bundle", "Lcom/faceunity/core/entity/FUBundleData;", "release", "removeAllScene", "removePreLoadedBundle", "path", "removeScene", "sceneId", "replaceScene", "currentScene", "targetScene", "currentSceneId", "setCurrentScene", "setCurrentSceneGL", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class FUSceneKit {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile FUSceneKit INSTANCE = null;
    public static final String TAG = "KIT_FUSceneKit";

    private final Lazy mAvatarController;
    private String programBinaryDirectory;
    private final ConcurrentHashMap<Long, Scene> sceneCacheMap;

    @JvmStatic
    public static final FUSceneKit getInstance() {
        return INSTANCE.getInstance();
    }

    private final AvatarController getMAvatarController() {
        return (AvatarController) this.mAvatarController.getValue();
    }

    public final void addScene(Scene scene) {
        addScene$default(this, scene, null, 2, null);
    }

    public final void addSceneGL(Scene scene) {
        addSceneGL$default(this, scene, null, 2, null);
    }

    private FUSceneKit() {
        this.mAvatarController = LazyKt.lazy(new Function0<AvatarController>() {
            @Override
            public final AvatarController invoke() {
                return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMAvatarController$fu_core_all_featureRelease();
            }
        });
        this.sceneCacheMap = new ConcurrentHashMap<>();
    }

    public FUSceneKit(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, m1293d2 = {"Lcom/faceunity/core/faceunity/FUSceneKit$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/faceunity/FUSceneKit;", "TAG", "", "getInstance", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FUSceneKit getInstance() {
            if (FUSceneKit.INSTANCE == null) {
                synchronized (this) {
                    if (FUSceneKit.INSTANCE == null) {
                        FUSceneKit.INSTANCE = new FUSceneKit(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            FUSceneKit fUSceneKit = FUSceneKit.INSTANCE;
            if (fUSceneKit == null) {
                Intrinsics.throwNpe();
            }
            return fUSceneKit;
        }
    }

    public final String getProgramBinaryDirectory() {
        return this.programBinaryDirectory;
    }

    public final void setProgramBinaryDirectory(String str) {
        this.programBinaryDirectory = str;
    }

    public static void addScene$default(FUSceneKit fUSceneKit, Scene scene, OnSceneListener onSceneListener, int i, Object obj) {
        if ((i & 2) != 0) {
            onSceneListener = null;
        }
        fUSceneKit.addScene(scene, onSceneListener);
    }

    public final void addScene(Scene scene, OnSceneListener listener) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        if (this.sceneCacheMap.containsKey(Long.valueOf(scene.getSceneId()))) {
            FULogger.m297w(TAG, "addScene failed this scene has loaded");
        } else {
            this.sceneCacheMap.put(Long.valueOf(scene.getSceneId()), scene);
            getMAvatarController().doAddAvatarScene$fu_core_all_featureRelease(scene.buildFUASceneData$fu_core_all_featureRelease(), listener);
        }
    }

    public static void addSceneGL$default(FUSceneKit fUSceneKit, Scene scene, OnSceneListener onSceneListener, int i, Object obj) {
        if ((i & 2) != 0) {
            onSceneListener = null;
        }
        fUSceneKit.addSceneGL(scene, onSceneListener);
    }

    public final void addSceneGL(Scene scene, OnSceneListener listener) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        if (this.sceneCacheMap.containsKey(Long.valueOf(scene.getSceneId()))) {
            FULogger.m297w(TAG, "addScene failed this scene has loaded");
        } else {
            this.sceneCacheMap.put(Long.valueOf(scene.getSceneId()), scene);
            getMAvatarController().doAddAvatarSceneGL$fu_core_all_featureRelease(scene.buildFUASceneData$fu_core_all_featureRelease(), listener);
        }
    }

    public final void removeScene(Scene scene) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        if (!this.sceneCacheMap.containsKey(Long.valueOf(scene.getSceneId()))) {
            FULogger.m297w(TAG, "removeScene failed this scene has not loaded");
        } else {
            this.sceneCacheMap.remove(Long.valueOf(scene.getSceneId()));
            getMAvatarController().doRemoveAvatarScene$fu_core_all_featureRelease(scene.buildFUASceneData$fu_core_all_featureRelease());
        }
    }

    public final void removeScene(long sceneId) {
        if (!this.sceneCacheMap.containsKey(Long.valueOf(sceneId))) {
            FULogger.m297w(TAG, "removeScene failed this scene has not loaded");
            return;
        }
        Scene scene = this.sceneCacheMap.get(Long.valueOf(sceneId));
        this.sceneCacheMap.remove(Long.valueOf(sceneId));
        if (scene != null) {
            getMAvatarController().doRemoveAvatarScene$fu_core_all_featureRelease(scene.buildFUASceneData$fu_core_all_featureRelease());
        }
    }

    public final void replaceScene(Scene currentScene, Scene targetScene) {
        Intrinsics.checkParameterIsNotNull(currentScene, "currentScene");
        Intrinsics.checkParameterIsNotNull(targetScene, "targetScene");
        if (currentScene.getSceneId() == targetScene.getSceneId()) {
            FULogger.m297w(TAG, "replaceScene failed currentScene sceneId is equal targetScene sceneId");
            return;
        }
        if (!this.sceneCacheMap.containsKey(Long.valueOf(currentScene.getSceneId()))) {
            FULogger.m297w(TAG, "replaceScene failed currentScene has not loaded do addScene");
            addScene$default(this, targetScene, null, 2, null);
        } else if (this.sceneCacheMap.containsKey(Long.valueOf(targetScene.getSceneId()))) {
            FULogger.m297w(TAG, "replaceScene failed currentScene has  loaded do removeScene");
            removeScene(currentScene);
        } else {
            getMAvatarController().doReplaceAvatarScene$fu_core_all_featureRelease(currentScene.buildFUASceneData$fu_core_all_featureRelease(), targetScene.buildFUASceneData$fu_core_all_featureRelease());
        }
    }

    public final void replaceScene(long currentSceneId, Scene targetScene) {
        Intrinsics.checkParameterIsNotNull(targetScene, "targetScene");
        if (this.sceneCacheMap.containsKey(Long.valueOf(currentSceneId))) {
            Scene it = this.sceneCacheMap.get(Long.valueOf(currentSceneId));
            if (it != null) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                replaceScene(it, targetScene);
                return;
            }
            return;
        }
        FULogger.m297w(TAG, "replaceScene failed currentScene has  loaded do removeScene");
        removeScene(targetScene);
    }

    public final void setCurrentScene(Scene scene) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        if (!this.sceneCacheMap.containsKey(Long.valueOf(scene.getSceneId()))) {
            FULogger.m297w(TAG, "setCurrentScene failed currentScene has not loaded");
        } else {
            AvatarController.setCurrentScene$default(getMAvatarController(), scene.getSceneId(), false, 2, null);
        }
    }

    public final void setCurrentScene(long sceneId) {
        if (!this.sceneCacheMap.containsKey(Long.valueOf(sceneId))) {
            FULogger.m297w(TAG, "setCurrentScene failed currentScene has not loaded");
        } else {
            AvatarController.setCurrentScene$default(getMAvatarController(), sceneId, false, 2, null);
        }
    }

    public final void setCurrentSceneGL(Scene scene) {
        Intrinsics.checkParameterIsNotNull(scene, "scene");
        if (!this.sceneCacheMap.containsKey(Long.valueOf(scene.getSceneId()))) {
            FULogger.m297w(TAG, "setCurrentScene failed currentScene has not loaded");
        } else {
            getMAvatarController().setCurrentScene(scene.getSceneId(), false);
        }
    }

    public final void setCurrentSceneGL(long sceneId) {
        if (!this.sceneCacheMap.containsKey(Long.valueOf(sceneId))) {
            FULogger.m297w(TAG, "setCurrentScene failed currentScene has not loaded");
        } else {
            getMAvatarController().setCurrentScene(sceneId, false);
        }
    }

    public final void removeAllScene() {
        for (Map.Entry<Long, Scene> entry : this.sceneCacheMap.entrySet()) {
            entry.getKey().longValue();
            getMAvatarController().doRemoveAvatarScene$fu_core_all_featureRelease(entry.getValue().buildFUASceneData$fu_core_all_featureRelease());
        }
        this.sceneCacheMap.clear();
    }

    public final void release() throws InterruptedException {
        BaseAvatarController.release$fu_core_all_featureRelease$default(getMAvatarController(), null, 1, null);
    }

    public final List<Scene> getAllScene() {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Long, Scene>> it = this.sceneCacheMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }

    public final void preloadBundleUnThread(FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        getMAvatarController().preloadBundleUnThread(bundle);
    }

    public final void removePreLoadedBundle(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        getMAvatarController().removePreLoadedBundle(path);
    }
}
