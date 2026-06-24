package com.faceunity.core.avatar.control;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUGroupAnimationData;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.utils.FULogger;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u00104\u001a\u0002052\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0004J.\u0010;\u001a\u0002052\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010=\u001a\u00020\u00042\b\b\u0002\u0010>\u001a\u00020\u000fH\u0004J>\u0010;\u001a\u0002052\"\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0?j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f`@2\u0006\u0010=\u001a\u00020\u00042\b\b\u0002\u0010>\u001a\u00020\u000fH\u0004J\u0018\u0010A\u001a\u0002052\u0006\u0010B\u001a\u00020C2\u0006\u00109\u001a\u00020:H\u0004J\u0010\u0010D\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020\u0004H\u0004J\u0010\u0010F\u001a\u0002052\u0006\u0010E\u001a\u00020\u0004H\u0004J\u0010\u0010G\u001a\u0002052\u0006\u00109\u001a\u00020:H\u0002J\u0016\u0010H\u001a\u0002052\f\u0010I\u001a\b\u0012\u0004\u0012\u0002050JH\u0004J\u0016\u0010K\u001a\u0002052\f\u0010I\u001a\b\u0012\u0004\u0012\u0002050JH\u0004J \u0010L\u001a\u0012\u0012\u0004\u0012\u00020N0Mj\b\u0012\u0004\u0012\u00020N`O2\u0006\u0010P\u001a\u000208H\u0002J\u0010\u0010Q\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0004J \u0010R\u001a\u0012\u0012\u0004\u0012\u00020N0Mj\b\u0012\u0004\u0012\u00020N`O2\u0006\u0010S\u001a\u00020CH\u0002J\u0010\u0010T\u001a\u0002052\u0006\u0010S\u001a\u00020CH\u0004J\u001f\u0010U\u001a\u0002052\u0010\b\u0002\u0010I\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010JH\u0010¢\u0006\u0002\bVJ\b\u0010W\u001a\u000205H\u0002J \u0010X\u001a\u0002052\u0006\u00106\u001a\u00020\t2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0004J.\u0010Y\u001a\u0002052\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010=\u001a\u00020\u00042\b\b\u0002\u0010>\u001a\u00020\u000fH\u0004J>\u0010Y\u001a\u0002052\"\u0010<\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0?j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f`@2\u0006\u0010=\u001a\u00020\u00042\b\b\u0002\u0010>\u001a\u00020\u000fH\u0004J\u0018\u0010Z\u001a\u0002052\u0006\u0010B\u001a\u00020C2\u0006\u00109\u001a\u00020:H\u0004J \u0010[\u001a\u0002052\u0006\u0010\\\u001a\u0002082\u0006\u0010]\u001a\u0002082\u0006\u00109\u001a\u00020:H\u0004J \u0010^\u001a\u0002052\u0006\u0010_\u001a\u00020C2\u0006\u0010`\u001a\u00020C2\u0006\u00109\u001a\u00020:H\u0004J\b\u0010a\u001a\u000205H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u001b\u0010\u001a\u001a\u00020\u001b8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010 \u001a\u00020!8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u001f\u001a\u0004\b\"\u0010#R\u001a\u0010%\u001a\u00020\u000fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001b\u0010*\u001a\u00020+8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u001f\u001a\u0004\b,\u0010-R$\u0010/\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\fR&\u00101\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0011\"\u0004\b3\u0010\u0013¨\u0006b"}, m1293d2 = {"Lcom/faceunity/core/avatar/control/BaseAvatarController;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "avatarBackgroundSet", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "getAvatarBackgroundSet", "()Ljava/util/HashSet;", "avatarIdMap", "Ljava/util/concurrent/ConcurrentHashMap;", "", "getAvatarIdMap", "()Ljava/util/concurrent/ConcurrentHashMap;", "setAvatarIdMap", "(Ljava/util/concurrent/ConcurrentHashMap;)V", "controllerHandler", "Landroid/os/Handler;", "controllerThreadId", "handleReferenceCountMap", "getHandleReferenceCountMap", "setHandleReferenceCountMap", "mBundleManager", "Lcom/faceunity/core/bundle/BundleManager;", "getMBundleManager", "()Lcom/faceunity/core/bundle/BundleManager;", "mBundleManager$delegate", "Lkotlin/Lazy;", "mCachedThreadPool", "Ljava/util/concurrent/ThreadPoolExecutor;", "getMCachedThreadPool", "()Ljava/util/concurrent/ThreadPoolExecutor;", "mCachedThreadPool$delegate", "mControllerBundleHandle", "getMControllerBundleHandle", "()I", "setMControllerBundleHandle", "(I)V", "mFURenderBridge", "Lcom/faceunity/core/support/FURenderBridge;", "getMFURenderBridge", "()Lcom/faceunity/core/support/FURenderBridge;", "mFURenderBridge$delegate", "sceneBackgroundSet", "getSceneBackgroundSet", "sceneIdMap", "getSceneIdMap", "setSceneIdMap", "addAvatar", "", "sceneId", "fuaAvatarData", "Lcom/faceunity/core/avatar/control/FUAAvatarData;", "compareData", "Lcom/faceunity/core/avatar/control/AvatarCompareData;", "addReferenceCount", "cacheMap", "key", "count", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "addScene", "fuaSceneData", "Lcom/faceunity/core/avatar/control/FUASceneData;", "createBundle", "path", "destroyBundle", "diffBundleMap", "doBackgroundAction", "unit", "Lkotlin/Function0;", "doGLThreadAction", "getAvatarBundles", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUBundleData;", "Lkotlin/collections/ArrayList;", "avatarData", "getFileName", "getSceneBundles", "sceneData", "loadControllerBundle", "release", "release$fu_core_all_featureRelease", "releaseThread", "removeAvatar", "removeReferenceCount", "removeScene", "replaceAvatar", "oldAvatar", "targetAvatar", "replaceScene", "oldScene", "newScene", "startBackgroundThread", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public class BaseAvatarController {
    private Handler controllerHandler;
    private final String TAG = "KIT_AvatarController";

    private final Lazy mBundleManager = LazyKt.lazy(new Function0<BundleManager>() {
        @Override
        public final BundleManager invoke() {
            return BundleManager.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });

    private final Lazy mFURenderBridge = LazyKt.lazy(new Function0<FURenderBridge>() {
        @Override
        public final FURenderBridge invoke() {
            return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease();
        }
    });
    private int mControllerBundleHandle = -1;
    private ConcurrentHashMap<String, Integer> handleReferenceCountMap = new ConcurrentHashMap<>(16);
    private ConcurrentHashMap<Long, Integer> sceneIdMap = new ConcurrentHashMap<>(16);
    private final HashSet<Long> sceneBackgroundSet = new HashSet<>();
    private ConcurrentHashMap<Long, Integer> avatarIdMap = new ConcurrentHashMap<>(16);
    private final HashSet<Long> avatarBackgroundSet = new HashSet<>();

    private final Lazy mCachedThreadPool = LazyKt.lazy(new Function0<ThreadPoolExecutor>() {
        @Override
        public final ThreadPoolExecutor invoke() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 30L, TimeUnit.SECONDS, new SynchronousQueue());
        }
    });
    private long controllerThreadId = -1;

    private final FURenderBridge getMFURenderBridge() {
        return (FURenderBridge) this.mFURenderBridge.getValue();
    }

    protected final BundleManager getMBundleManager() {
        return (BundleManager) this.mBundleManager.getValue();
    }

    protected final ThreadPoolExecutor getMCachedThreadPool() {
        return (ThreadPoolExecutor) this.mCachedThreadPool.getValue();
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final int getMControllerBundleHandle() {
        return this.mControllerBundleHandle;
    }

    protected final void setMControllerBundleHandle(int i) {
        this.mControllerBundleHandle = i;
    }

    protected final ConcurrentHashMap<String, Integer> getHandleReferenceCountMap() {
        return this.handleReferenceCountMap;
    }

    protected final void setHandleReferenceCountMap(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        Intrinsics.checkParameterIsNotNull(concurrentHashMap, "<set-?>");
        this.handleReferenceCountMap = concurrentHashMap;
    }

    protected final ConcurrentHashMap<Long, Integer> getSceneIdMap() {
        return this.sceneIdMap;
    }

    protected final void setSceneIdMap(ConcurrentHashMap<Long, Integer> concurrentHashMap) {
        Intrinsics.checkParameterIsNotNull(concurrentHashMap, "<set-?>");
        this.sceneIdMap = concurrentHashMap;
    }

    protected final HashSet<Long> getSceneBackgroundSet() {
        return this.sceneBackgroundSet;
    }

    protected final ConcurrentHashMap<Long, Integer> getAvatarIdMap() {
        return this.avatarIdMap;
    }

    protected final void setAvatarIdMap(ConcurrentHashMap<Long, Integer> concurrentHashMap) {
        Intrinsics.checkParameterIsNotNull(concurrentHashMap, "<set-?>");
        this.avatarIdMap = concurrentHashMap;
    }

    protected final HashSet<Long> getAvatarBackgroundSet() {
        return this.avatarBackgroundSet;
    }

    protected final void addAvatar(long sceneId, FUAAvatarData fuaAvatarData, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(fuaAvatarData, "fuaAvatarData");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        ArrayList<String> arrayList = new ArrayList<>();
        for (FUBundleData fUBundleData : getAvatarBundles(fuaAvatarData)) {
            if (!arrayList.contains(fUBundleData.getPath())) {
                addReferenceCount$default(this, compareData.getBundleAddMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                arrayList.add(fUBundleData.getPath());
            }
        }
        compareData.getAvatarParamsMap().put(Long.valueOf(fuaAvatarData.getId()), fuaAvatarData.getParam());
        compareData.getAvatarBindHandleMap().put(fuaAvatarData, arrayList);
        compareData.getSceneBindAvatarMap().put(Long.valueOf(sceneId), CollectionsKt.arrayListOf(Long.valueOf(fuaAvatarData.getId())));
    }

    protected final void removeAvatar(long sceneId, FUAAvatarData fuaAvatarData, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(fuaAvatarData, "fuaAvatarData");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        ArrayList<String> arrayList = new ArrayList<>();
        for (FUBundleData fUBundleData : getAvatarBundles(fuaAvatarData)) {
            if (!arrayList.contains(fUBundleData.getPath())) {
                addReferenceCount$default(this, compareData.getBundleRemoveMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                arrayList.add(fUBundleData.getPath());
            }
        }
        compareData.getAvatarUnbindHandleMap().put(Long.valueOf(fuaAvatarData.getId()), arrayList);
        compareData.getSceneUnbindAvatarMap().put(Long.valueOf(sceneId), CollectionsKt.arrayListOf(Long.valueOf(fuaAvatarData.getId())));
    }

    protected final void replaceAvatar(FUAAvatarData oldAvatar, FUAAvatarData targetAvatar, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(oldAvatar, "oldAvatar");
        Intrinsics.checkParameterIsNotNull(targetAvatar, "targetAvatar");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        compareData.getSceneReplaceAvatarMap().put(Long.valueOf(oldAvatar.getId()), Long.valueOf(targetAvatar.getId()));
        ArrayList<String> arrayList = new ArrayList<>();
        for (FUBundleData fUBundleData : getAvatarBundles(oldAvatar)) {
            if (!arrayList.contains(fUBundleData.getPath())) {
                addReferenceCount$default(this, compareData.getBundleRemoveMap(), fUBundleData.getPath(), 0, 4, (Object) null);
                arrayList.add(fUBundleData.getPath());
            }
        }
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (FUBundleData fUBundleData2 : getAvatarBundles(targetAvatar)) {
            if (arrayList.contains(fUBundleData2.getPath())) {
                arrayList.remove(fUBundleData2.getPath());
                removeReferenceCount$default(this, compareData.getBundleRemoveMap(), fUBundleData2.getPath(), 0, 4, (Object) null);
            } else {
                arrayList2.add(fUBundleData2.getPath());
                addReferenceCount$default(this, compareData.getBundleAddMap(), fUBundleData2.getPath(), 0, 4, (Object) null);
            }
        }
        compareData.getAvatarParamsMap().put(Long.valueOf(targetAvatar.getId()), targetAvatar.getParam());
        compareData.getAvatarUnbindHandleMap().put(Long.valueOf(oldAvatar.getId()), arrayList);
        compareData.getAvatarBindHandleMap().put(targetAvatar, arrayList2);
    }

    protected final void addScene(FUASceneData fuaSceneData, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(fuaSceneData, "fuaSceneData");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        ArrayList<String> arrayList = new ArrayList<>();
        for (FUBundleData fUBundleData : getSceneBundles(fuaSceneData)) {
            if (!arrayList.contains(fUBundleData.getPath())) {
                arrayList.add(fUBundleData.getPath());
                addReferenceCount$default(this, compareData.getBundleAddMap(), fUBundleData.getPath(), 0, 4, (Object) null);
            }
        }
        if (!compareData.getSceneAddList().contains(fuaSceneData)) {
            compareData.getSceneAddList().add(fuaSceneData);
        }
        compareData.getSceneBindHandleMap().put(fuaSceneData, arrayList);
        Iterator<T> it = fuaSceneData.getAvatars().iterator();
        while (it.hasNext()) {
            addAvatar(fuaSceneData.getId(), (FUAAvatarData) it.next(), compareData);
        }
    }

    protected final void removeScene(FUASceneData fuaSceneData, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(fuaSceneData, "fuaSceneData");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        ArrayList<String> arrayList = new ArrayList<>();
        for (FUBundleData fUBundleData : getSceneBundles(fuaSceneData)) {
            if (!arrayList.contains(fUBundleData.getPath())) {
                arrayList.add(fUBundleData.getPath());
                addReferenceCount$default(this, compareData.getBundleRemoveMap(), fUBundleData.getPath(), 0, 4, (Object) null);
            }
        }
        if (!compareData.getSceneRemoveList().contains(fuaSceneData)) {
            compareData.getSceneRemoveList().add(fuaSceneData);
        }
        compareData.getSceneUnbindHandleMap().put(Long.valueOf(fuaSceneData.getId()), arrayList);
        Iterator<T> it = fuaSceneData.getAvatars().iterator();
        while (it.hasNext()) {
            removeAvatar(fuaSceneData.getId(), (FUAAvatarData) it.next(), compareData);
        }
    }

    protected final void replaceScene(FUASceneData oldScene, FUASceneData newScene, AvatarCompareData compareData) {
        Intrinsics.checkParameterIsNotNull(oldScene, "oldScene");
        Intrinsics.checkParameterIsNotNull(newScene, "newScene");
        Intrinsics.checkParameterIsNotNull(compareData, "compareData");
        removeScene(oldScene, compareData);
        addScene(newScene, compareData);
        diffBundleMap(compareData);
    }

    private final ArrayList<FUBundleData> getAvatarBundles(FUAAvatarData avatarData) {
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        arrayList.addAll(avatarData.getItemBundles());
        for (FUAnimationData fUAnimationData : avatarData.getAnimationData()) {
            arrayList.add(fUAnimationData.getAnimation());
            if (fUAnimationData instanceof FUGroupAnimationData) {
                FUGroupAnimationData fUGroupAnimationData = (FUGroupAnimationData) fUAnimationData;
                arrayList.addAll(fUGroupAnimationData.getSubAnimations());
                arrayList.addAll(fUGroupAnimationData.getSubProps());
            }
        }
        return arrayList;
    }

    private final ArrayList<FUBundleData> getSceneBundles(FUASceneData sceneData) {
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        arrayList.addAll(sceneData.getItemBundles());
        for (FUAnimationData fUAnimationData : sceneData.getAnimationData()) {
            arrayList.add(fUAnimationData.getAnimation());
            if (fUAnimationData instanceof FUGroupAnimationData) {
                FUGroupAnimationData fUGroupAnimationData = (FUGroupAnimationData) fUAnimationData;
                arrayList.addAll(fUGroupAnimationData.getSubAnimations());
                arrayList.addAll(fUGroupAnimationData.getSubProps());
            }
        }
        return arrayList;
    }

    protected final void loadControllerBundle(FUASceneData sceneData) {
        Intrinsics.checkParameterIsNotNull(sceneData, "sceneData");
        FUBundleData controller = sceneData.getController();
        int iLoadBundleFile = getMBundleManager().loadBundleFile(controller.getName(), controller.getPath());
        if (iLoadBundleFile <= 0) {
            getMBundleManager().destroyControllerBundle(this.mControllerBundleHandle);
            this.mControllerBundleHandle = -1;
            FULogger.m294e(this.TAG, "loadControllerBundle failed handle:" + iLoadBundleFile + "  path:" + controller.getPath());
        } else {
            if (sceneData.getEnable()) {
                getMBundleManager().updateControllerBundle(this.mControllerBundleHandle, iLoadBundleFile, false);
            } else {
                getMBundleManager().destroyControllerBundle(this.mControllerBundleHandle);
            }
            this.mControllerBundleHandle = iLoadBundleFile;
        }
    }

    protected final void destroyBundle(String path) {
        int bundleHandle;
        Intrinsics.checkParameterIsNotNull(path, "path");
        if (this.handleReferenceCountMap.containsKey(path) || (bundleHandle = getMBundleManager().getBundleHandle(path)) <= 0) {
            return;
        }
        getMBundleManager().destroyBundle(new int[]{bundleHandle});
    }

    protected final int createBundle(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return getMBundleManager().loadBundleFile(getFileName(path), path);
    }

    public static void addReferenceCount$default(BaseAvatarController baseAvatarController, ConcurrentHashMap concurrentHashMap, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addReferenceCount");
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        baseAvatarController.addReferenceCount((ConcurrentHashMap<String, Integer>) concurrentHashMap, str, i);
    }

    protected final void addReferenceCount(ConcurrentHashMap<String, Integer> cacheMap, String key, int count) {
        Intrinsics.checkParameterIsNotNull(cacheMap, "cacheMap");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (cacheMap.containsKey(key)) {
            ConcurrentHashMap<String, Integer> concurrentHashMap = cacheMap;
            Integer num = cacheMap.get(key);
            if (num == null) {
                Intrinsics.throwNpe();
            }
            concurrentHashMap.put(key, Integer.valueOf(num.intValue() + count));
            return;
        }
        cacheMap.put(key, Integer.valueOf(count));
    }

    public static void addReferenceCount$default(BaseAvatarController baseAvatarController, LinkedHashMap linkedHashMap, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addReferenceCount");
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        baseAvatarController.addReferenceCount((LinkedHashMap<String, Integer>) linkedHashMap, str, i);
    }

    protected final void addReferenceCount(LinkedHashMap<String, Integer> cacheMap, String key, int count) {
        Intrinsics.checkParameterIsNotNull(cacheMap, "cacheMap");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (cacheMap.containsKey(key)) {
            LinkedHashMap<String, Integer> linkedHashMap = cacheMap;
            Integer num = cacheMap.get(key);
            if (num == null) {
                Intrinsics.throwNpe();
            }
            linkedHashMap.put(key, Integer.valueOf(num.intValue() + count));
            return;
        }
        cacheMap.put(key, Integer.valueOf(count));
    }

    public static void removeReferenceCount$default(BaseAvatarController baseAvatarController, ConcurrentHashMap concurrentHashMap, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeReferenceCount");
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        baseAvatarController.removeReferenceCount((ConcurrentHashMap<String, Integer>) concurrentHashMap, str, i);
    }

    protected final void removeReferenceCount(ConcurrentHashMap<String, Integer> cacheMap, String key, int count) {
        Intrinsics.checkParameterIsNotNull(cacheMap, "cacheMap");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (cacheMap.containsKey(key)) {
            Integer num = cacheMap.get(key);
            if (num == null) {
                Intrinsics.throwNpe();
            }
            if (Intrinsics.compare(num.intValue(), count) > 0) {
                ConcurrentHashMap<String, Integer> concurrentHashMap = cacheMap;
                Integer num2 = cacheMap.get(key);
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                concurrentHashMap.put(key, Integer.valueOf(num2.intValue() - count));
                return;
            }
            cacheMap.remove(key);
        }
    }

    public static void removeReferenceCount$default(BaseAvatarController baseAvatarController, LinkedHashMap linkedHashMap, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: removeReferenceCount");
        }
        if ((i2 & 4) != 0) {
            i = 1;
        }
        baseAvatarController.removeReferenceCount((LinkedHashMap<String, Integer>) linkedHashMap, str, i);
    }

    protected final void removeReferenceCount(LinkedHashMap<String, Integer> cacheMap, String key, int count) {
        Intrinsics.checkParameterIsNotNull(cacheMap, "cacheMap");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (cacheMap.containsKey(key)) {
            Integer num = cacheMap.get(key);
            if (num == null) {
                Intrinsics.throwNpe();
            }
            if (Intrinsics.compare(num.intValue(), count) > 0) {
                LinkedHashMap<String, Integer> linkedHashMap = cacheMap;
                Integer num2 = cacheMap.get(key);
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                linkedHashMap.put(key, Integer.valueOf(num2.intValue() - count));
                return;
            }
            cacheMap.remove(key);
        }
    }

    private final void diffBundleMap(AvatarCompareData compareData) {
        Iterator<Map.Entry<String, Integer>> it = compareData.getBundleAddMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> next = it.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "iterator.next()");
            Map.Entry<String, Integer> entry = next;
            if (compareData.getBundleRemoveMap().containsKey(entry.getKey())) {
                Integer num = compareData.getBundleRemoveMap().get(entry.getKey());
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(num, "compareData.bundleRemoveMap[item.key]!!");
                int iIntValue = num.intValue();
                Integer value = entry.getValue();
                Intrinsics.checkExpressionValueIsNotNull(value, "item.value");
                if (Intrinsics.compare(iIntValue, value.intValue()) < 0) {
                    compareData.getBundleRemoveMap().remove(entry.getKey());
                    Intrinsics.checkExpressionValueIsNotNull(entry.setValue(Integer.valueOf(entry.getValue().intValue() - iIntValue)), "item.setValue(item.value - removeCount)");
                } else {
                    Integer value2 = entry.getValue();
                    if (value2 != null && iIntValue == value2.intValue()) {
                        compareData.getBundleRemoveMap().remove(entry.getKey());
                        it.remove();
                    } else {
                        LinkedHashMap<String, Integer> bundleRemoveMap = compareData.getBundleRemoveMap();
                        String key = entry.getKey();
                        Intrinsics.checkExpressionValueIsNotNull(key, "item.key");
                        Integer value3 = entry.getValue();
                        Intrinsics.checkExpressionValueIsNotNull(value3, "item.value");
                        bundleRemoveMap.put(key, Integer.valueOf(iIntValue - value3.intValue()));
                        it.remove();
                    }
                }
            }
        }
    }

    protected final String getFileName(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        String string = StringsKt.trim((CharSequence) path).toString();
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) string, str, 0, false, 6, (Object) null) + 1;
        if (string == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = string.substring(iLastIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        String str2 = strSubstring;
        if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) ".bundle", false, 2, (Object) null)) {
            return strSubstring;
        }
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, ".bundle", 0, false, 6, (Object) null);
        if (strSubstring == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring2 = strSubstring.substring(0, iIndexOf$default);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring2;
    }

    public static void release$fu_core_all_featureRelease$default(BaseAvatarController baseAvatarController, Function0 function0, int i, Object obj) throws InterruptedException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: release");
        }
        if ((i & 1) != 0) {
            function0 = null;
        }
        baseAvatarController.release$fu_core_all_featureRelease(function0);
    }

    public void release$fu_core_all_featureRelease(final Function0<Unit> unit) throws InterruptedException {
        if (this.controllerHandler != null) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            doBackgroundAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    if (this.getMControllerBundleHandle() > 0) {
                        Function0 function0 = unit;
                        if (function0 != null) {
                        }
                        this.getMBundleManager().destroyControllerBundle(this.getMControllerBundleHandle());
                        this.setMControllerBundleHandle(-1);
                    }
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
        }
        releaseThread();
    }

    protected final void doBackgroundAction(final Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        if (this.controllerHandler == null) {
            startBackgroundThread();
        }
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        if (threadCurrentThread.getId() == this.controllerThreadId) {
            unit.invoke();
            return;
        }
        Handler handler = this.controllerHandler;
        if (handler == null) {
            Intrinsics.throwNpe();
        }
        handler.post(new Runnable() {
            @Override
            public final void run() {
                Intrinsics.checkExpressionValueIsNotNull(unit.invoke(), "invoke(...)");
            }
        });
    }

    protected final void doGLThreadAction(Function0<Unit> unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        getMFURenderBridge().doGLThreadAction$fu_core_all_featureRelease(unit);
    }

    private final void startBackgroundThread() {
        HandlerThread handlerThread = new HandlerThread("KIT_" + getClass().getSimpleName());
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.controllerHandler = handler;
        Looper looper = handler.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "controllerHandler!!.looper");
        Thread thread = looper.getThread();
        Intrinsics.checkExpressionValueIsNotNull(thread, "controllerHandler!!.looper.thread");
        this.controllerThreadId = thread.getId();
    }

    private final void releaseThread() {
        Looper looper;
        Handler handler = this.controllerHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.controllerHandler;
        if (handler2 != null && (looper = handler2.getLooper()) != null) {
            looper.quitSafely();
        }
        this.controllerHandler = null;
    }
}
