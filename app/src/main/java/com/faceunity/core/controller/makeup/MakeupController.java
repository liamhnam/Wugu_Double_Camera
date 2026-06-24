package com.faceunity.core.controller.makeup;

import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.callback.OnControllerBundleLoadCallback;
import com.faceunity.core.controller.BaseSingleController;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.utils.FULogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0000¢\u0006\u0002\b\u001aJ\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0014J\u0018\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010#\u001a\u00020\u0017H\u0002J<\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\"\u0010'\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(`\u0007H\u0002J\u0018\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001d\u0010+\u001a\u00020\u00172\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019H\u0010¢\u0006\u0002\b-J\b\u0010.\u001a\u00020\u0017H\u0002J\u0018\u0010/\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u0002J\r\u00101\u001a\u00020\u0017H\u0000¢\u0006\u0002\b2J'\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u0002052\u0006\u0010\"\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0000¢\u0006\u0002\b6J \u00103\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u001dH\u0002R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\tj\b\u0012\u0004\u0012\u00020\u0006`\nX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\tj\b\u0012\u0004\u0012\u00020\u0006`\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0014\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u00068"}, m1293d2 = {"Lcom/faceunity/core/controller/makeup/MakeupController;", "Lcom/faceunity/core/controller/BaseSingleController;", "()V", "comBindHandle", "Ljava/util/LinkedHashMap;", "", "", "Lkotlin/collections/LinkedHashMap;", "comDestroyHandle", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "comHasBindHandle", "comUnbindHandle", "isMakeupItemNew", "", "()Z", "setMakeupItemNew", "(Z)V", "isSomeController", "makeupItemHandleMap", "makeupItemKeyMap", "makeupStr", "addModelUnitCache", "", "unCache", "Lkotlin/Function0;", "addModelUnitCache$fu_core_all_featureRelease", "applyAddProp", "bundle", "Lcom/faceunity/core/entity/FUBundleData;", "applyControllerBundle", "featuresData", "Lcom/faceunity/core/entity/FUFeaturesData;", "bindItemBundle", "key", "clearCompData", "loadMakeupComp", "oldHandle", "newHandle", "param", "", "realApplyBundle", "handle", "release", "unit", "release$fu_core_all_featureRelease", "releaseItems", "unbindItemBundle", "path", "updateFlipMode", "updateFlipMode$fu_core_all_featureRelease", "updateItemBundle", "sign", "", "updateItemBundle$fu_core_all_featureRelease", "oldPath", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class MakeupController extends BaseSingleController {
    private boolean isMakeupItemNew;
    private boolean isSomeController;
    private LinkedHashMap<String, Integer> makeupItemHandleMap = new LinkedHashMap<>(16);
    private LinkedHashMap<String, String> makeupItemKeyMap = new LinkedHashMap<>(16);
    private final String makeupStr = "makeup";
    private final ArrayList<Integer> comUnbindHandle = new ArrayList<>();
    private final ArrayList<Integer> comDestroyHandle = new ArrayList<>();
    private final LinkedHashMap<String, Integer> comBindHandle = new LinkedHashMap<>();
    private final LinkedHashMap<String, Integer> comHasBindHandle = new LinkedHashMap<>();

    public final boolean getIsMakeupItemNew() {
        return this.isMakeupItemNew;
    }

    public final void setMakeupItemNew(boolean z) {
        this.isMakeupItemNew = z;
    }

    @Override
    protected void applyControllerBundle(final FUFeaturesData featuresData) {
        Intrinsics.checkParameterIsNotNull(featuresData, "featuresData");
        if (getIsNeedApplyBundleGLThread()) {
            doGLThreadAction(new Function0<Unit>() {
                {
                    super(0);
                }

                @Override
                public Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                public final void invoke2() {
                    FUBundleData bundle = featuresData.getBundle();
                    int iLoadBundleFile = bundle != null ? MakeupController.this.getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath()) : 0;
                    if (iLoadBundleFile <= 0) {
                        MakeupController.this.releaseItems();
                        MakeupController.this.getMBundleManager().destroyControllerBundle(MakeupController.this.getMControllerBundleHandle());
                        MakeupController.this.setMControllerBundleHandle$fu_core_all_featureRelease(-1);
                    } else {
                        MakeupController.this.realApplyBundle(iLoadBundleFile, featuresData);
                        OnControllerBundleLoadCallback mCallback = MakeupController.this.getMCallback();
                        if (mCallback != null) {
                            mCallback.onLoadSuccess(MakeupController.this.getModelSign());
                        }
                    }
                }
            });
        } else {
            FUBundleData bundle = featuresData.getBundle();
            int iLoadBundleFile = bundle != null ? getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath()) : 0;
            if (iLoadBundleFile <= 0) {
                releaseItems();
                getMBundleManager().destroyControllerBundle(getMControllerBundleHandle());
                setMControllerBundleHandle$fu_core_all_featureRelease(-1);
                return;
            }
            realApplyBundle(iLoadBundleFile, featuresData);
        }
        setNeedApplyBundleGLThread(false);
    }

    public final void realApplyBundle(int handle, FUFeaturesData featuresData) {
        loadMakeupComp(getMControllerBundleHandle(), handle, featuresData.getParam());
        if (!this.comUnbindHandle.isEmpty()) {
            getMBundleManager().unbindControllerItem(getMControllerBundleHandle(), CollectionsKt.toIntArray(this.comUnbindHandle));
        }
        if (!this.comDestroyHandle.isEmpty()) {
            getMBundleManager().destroyBundle(CollectionsKt.toIntArray(this.comDestroyHandle));
        }
        if (featuresData.getEnable()) {
            BundleManager.updateControllerBundle$default(getMBundleManager(), getMControllerBundleHandle(), handle, false, 4, null);
        } else {
            getMBundleManager().destroyControllerBundle(getMControllerBundleHandle());
        }
        setMControllerBundleHandle$fu_core_all_featureRelease(handle);
        this.makeupItemHandleMap.clear();
        this.makeupItemHandleMap.putAll(this.comHasBindHandle);
        int[] iArr = new int[this.comBindHandle.size()];
        Iterator<Map.Entry<String, Integer>> it = this.comBindHandle.entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().getValue().intValue();
            i++;
        }
        this.makeupItemHandleMap.putAll(this.comBindHandle);
        Function0<Unit> function0 = getModelUnitCache().get(this.makeupStr);
        if (function0 != null) {
            function0.invoke();
        }
        getMBundleManager().bindControllerItem(handle, iArr);
        for (Map.Entry<String, Object> entry : featuresData.getParam().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!StringsKt.startsWith$default(key, "tex_", false, 2, (Object) null)) {
                itemSetParam(key, value);
            }
        }
        itemSetParam("is_flip_points", Double.valueOf((getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE || getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO || getMFURenderBridge().getCameraFacing() == CameraFacingEnum.CAMERA_BACK) ? 1.0d : 0.0d));
        itemSetParam(MakeupParam.IS_MAKEUP_ON, Double.valueOf(1.0d));
    }

    public final void updateItemBundle$fu_core_all_featureRelease(long sign, final String key, final FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        FULogger.m295i(getTAG(), "updateItemBundle sign:" + (sign == getModelSign()) + "  key:" + key + "  path:" + (bundle != null ? bundle.getPath() : null));
        if (sign != getModelSign()) {
            return;
        }
        BaseSingleController.doBackgroundAction$default(this, 0, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                FUBundleData fUBundleData;
                FUBundleData fUBundleData2;
                String str = (String) MakeupController.this.makeupItemKeyMap.get(key);
                if (str == null && (fUBundleData2 = bundle) != null) {
                    MakeupController.this.bindItemBundle(key, fUBundleData2);
                    return;
                }
                if (str != null && bundle == null) {
                    MakeupController.this.unbindItemBundle(key, str);
                } else {
                    if (str == null || (fUBundleData = bundle) == null || !(!Intrinsics.areEqual(str, fUBundleData.getPath()))) {
                        return;
                    }
                    MakeupController.this.updateItemBundle(key, str, bundle);
                }
            }
        }, 1, null);
    }

    public final void applyAddProp(FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        int iLoadBundleFile = getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath());
        if (iLoadBundleFile <= 0) {
            FULogger.m294e(getTAG(), "load Prop bundle failed bundle path:" + bundle.getPath());
        } else {
            BundleManager.bindControllerBundle$default(getMBundleManager(), iLoadBundleFile, false, 2, null);
        }
    }

    public final void updateFlipMode$fu_core_all_featureRelease() {
        if (getMControllerBundleHandle() <= 0) {
            return;
        }
        itemSetParam("is_flip_points", Double.valueOf((getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE || getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO || getMFURenderBridge().getCameraFacing() == CameraFacingEnum.CAMERA_BACK) ? 1.0d : 0.0d));
    }

    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void loadMakeupComp(int r6, int r7, java.util.LinkedHashMap<java.lang.String, java.lang.Object> r8) {
        /*
            Method dump skipped, instruction units count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.faceunity.core.controller.makeup.MakeupController.loadMakeupComp(int, int, java.util.LinkedHashMap):void");
    }

    private final void clearCompData() {
        this.isSomeController = false;
        this.comUnbindHandle.clear();
        this.comDestroyHandle.clear();
        this.comBindHandle.clear();
        this.comHasBindHandle.clear();
    }

    public final void unbindItemBundle(String key, String path) {
        Integer num = this.makeupItemHandleMap.get(path);
        if (num != null) {
            int iIntValue = num.intValue();
            if (getMControllerBundleHandle() > 0 && num.intValue() > 0) {
                getMBundleManager().unbindControllerItem(getMControllerBundleHandle(), iIntValue);
            }
            if (iIntValue > 0) {
                getMBundleManager().destroyBundle(iIntValue);
            }
        }
        this.makeupItemHandleMap.remove(path);
        this.makeupItemKeyMap.remove(key);
    }

    public final void bindItemBundle(String key, FUBundleData bundle) {
        int iLoadBundleFile = getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath());
        if (getMControllerBundleHandle() <= 0 || iLoadBundleFile <= 0) {
            return;
        }
        getMBundleManager().bindControllerItem(getMControllerBundleHandle(), iLoadBundleFile);
        this.makeupItemHandleMap.put(bundle.getPath(), Integer.valueOf(iLoadBundleFile));
        this.makeupItemKeyMap.put(key, bundle.getPath());
    }

    public final void updateItemBundle(String key, String oldPath, FUBundleData bundle) {
        int iLoadBundleFile = getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath());
        unbindItemBundle(key, oldPath);
        if (getMControllerBundleHandle() <= 0 || iLoadBundleFile <= 0) {
            return;
        }
        getMBundleManager().bindControllerItem(getMControllerBundleHandle(), iLoadBundleFile);
        this.makeupItemHandleMap.put(bundle.getPath(), Integer.valueOf(iLoadBundleFile));
        this.makeupItemKeyMap.put(key, bundle.getPath());
    }

    public final void addModelUnitCache$fu_core_all_featureRelease(Function0<Unit> unCache) {
        Intrinsics.checkParameterIsNotNull(unCache, "unCache");
        setNeedApplyBundleGLThread(true);
        getMFURenderBridge().getMMakeupController$fu_core_all_featureRelease().getModelUnitCache().remove(this.makeupStr);
        getMFURenderBridge().getMMakeupController$fu_core_all_featureRelease().getModelUnitCache().put(this.makeupStr, unCache);
    }

    @Override
    public void release$fu_core_all_featureRelease(Function0<Unit> unit) throws InterruptedException {
        super.release$fu_core_all_featureRelease(new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.releaseItems();
            }
        });
    }

    public final void releaseItems() {
        if (!this.makeupItemHandleMap.isEmpty()) {
            int[] iArr = new int[this.makeupItemHandleMap.size()];
            Iterator<Map.Entry<String, Integer>> it = this.makeupItemHandleMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().getValue().intValue();
                i++;
            }
            int mControllerBundleHandle$fu_core_all_featureRelease = getMControllerBundleHandle();
            if (mControllerBundleHandle$fu_core_all_featureRelease > 0) {
                getMBundleManager().unbindControllerItem(mControllerBundleHandle$fu_core_all_featureRelease, iArr);
            }
            getMBundleManager().destroyBundle(iArr);
            this.makeupItemHandleMap.clear();
        }
        this.makeupItemKeyMap.clear();
    }
}
