package com.faceunity.core.controller.prop;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.bundle.BundleManager;
import com.faceunity.core.controller.prop.ThreadQueuePool;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUFeaturesData;
import com.faceunity.core.enumeration.CameraFacingEnum;
import com.faceunity.core.enumeration.FUExternalInputEnum;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0016J5\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0017H\u0000¢\u0006\u0002\b\u0019J\u001d\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u001bJ&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00132\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0004J\u001d\u0010!\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013H\u0000¢\u0006\u0002\b#J\u001f\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013H\u0000¢\u0006\u0002\b&J\u001f\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013H\u0000¢\u0006\u0002\b)J\u001f\u0010*\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013H\u0000¢\u0006\u0002\b+J\u000e\u0010,\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010-\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006J\u001d\u0010.\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010/\u001a\u000200H\u0000¢\u0006\u0002\b1J%\u00102\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u001dH\u0000¢\u0006\u0002\b4J%\u00105\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u00103\u001a\u00020\u001dH\u0000¢\u0006\u0002\b6J\u0018\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0006H\u0002J\r\u0010:\u001a\u00020\u0004H\u0000¢\u0006\u0002\b;J\r\u0010<\u001a\u00020\u0004H\u0000¢\u0006\u0002\b=¨\u0006>"}, m1293d2 = {"Lcom/faceunity/core/controller/prop/PropContainerController;", "Lcom/faceunity/core/controller/prop/BasePropController;", "()V", "addProp", "", "fuFeaturesData", "Lcom/faceunity/core/entity/FUFeaturesData;", "applyAddProp", "applyRemoveProp", "applyReplaceProp", "oldData", "newData", "applyThreadQueue", "queue", "Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueItem;", "createTexForItem", "propId", "", NamingTable.TAG, "", "rgba", "", StreamInformation.KEY_WIDTH, "", StreamInformation.KEY_HEIGHT, "createTexForItem$fu_core_all_featureRelease", "deleteTexForItem", "deleteTexForItem$fu_core_all_featureRelease", "itemGetParam", "", "key", "clazz", "Ljava/lang/Class;", "itemGetParamDouble", "", "itemGetParamDouble$fu_core_all_featureRelease", "itemGetParamDoubleArray", "", "itemGetParamDoubleArray$fu_core_all_featureRelease", "itemGetParamFloatArray", "", "itemGetParamFloatArray$fu_core_all_featureRelease", "itemGetParamString", "itemGetParamString$fu_core_all_featureRelease", "removeProp", "replaceProp", "setBundleEnable", "enable", "", "setBundleEnable$fu_core_all_featureRelease", "setItemParam", "value", "setItemParam$fu_core_all_featureRelease", "setItemParamGL", "setItemParamGL$fu_core_all_featureRelease", "setPropParams", "handle", "data", "updateFlipMode", "updateFlipMode$fu_core_all_featureRelease", "updateRotationMode", "updateRotationMode$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class PropContainerController extends BasePropController {

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ThreadQueuePool.QueueType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ThreadQueuePool.QueueType.ADD.ordinal()] = 1;
            iArr[ThreadQueuePool.QueueType.REMOVE.ordinal()] = 2;
            iArr[ThreadQueuePool.QueueType.REPLACE.ordinal()] = 3;
            iArr[ThreadQueuePool.QueueType.UNIT.ordinal()] = 4;
        }
    }

    public final void addProp(FUFeaturesData fuFeaturesData) {
        Intrinsics.checkParameterIsNotNull(fuFeaturesData, "fuFeaturesData");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.ADD, fuFeaturesData, null, null, 12, null));
    }

    public final void replaceProp(FUFeaturesData oldData, FUFeaturesData newData) {
        Intrinsics.checkParameterIsNotNull(oldData, "oldData");
        Intrinsics.checkParameterIsNotNull(newData, "newData");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.REPLACE, oldData, newData, null, 8, null));
    }

    public final void removeProp(FUFeaturesData fuFeaturesData) {
        Intrinsics.checkParameterIsNotNull(fuFeaturesData, "fuFeaturesData");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.REMOVE, fuFeaturesData, null, null, 12, null));
    }

    public final void setBundleEnable$fu_core_all_featureRelease(final long propId, final boolean enable) {
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.UNIT, null, null, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = this.this$0.getPropIdMap().get(Long.valueOf(propId));
                if (num != null) {
                    num.intValue();
                    if (enable) {
                        BundleManager.bindControllerBundle$default(this.this$0.getMBundleManager(), num.intValue(), false, 2, null);
                    } else {
                        this.this$0.getMBundleManager().unbindControllerBundle(num.intValue());
                    }
                }
            }
        }, 6, null));
    }

    public final void updateFlipMode$fu_core_all_featureRelease() {
        for (Map.Entry<Long, Integer> entry : getPropIdMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            int iIntValue = entry.getValue().intValue();
            LinkedHashMap<String, Object> linkedHashMap = getPropTypeMap().get(Long.valueOf(jLongValue));
            if (linkedHashMap != null) {
                if (Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 5)) {
                    itemSetParam(iIntValue, "rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
                } else if (Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 10) && linkedHashMap.containsKey("is_flip_points")) {
                    itemSetParam(iIntValue, "is_flip_points", Double.valueOf((getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE || getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO || getMFURenderBridge().getCameraFacing() == CameraFacingEnum.CAMERA_BACK) ? 1.0d : 0.0d));
                }
            }
        }
    }

    public final void updateRotationMode$fu_core_all_featureRelease() {
        for (Map.Entry<Long, Integer> entry : getPropIdMap().entrySet()) {
            long jLongValue = entry.getKey().longValue();
            int iIntValue = entry.getValue().intValue();
            LinkedHashMap<String, Object> linkedHashMap = getPropTypeMap().get(Long.valueOf(jLongValue));
            if (linkedHashMap != null && Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 5)) {
                itemSetParam(iIntValue, "rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
            }
        }
    }

    public final void setItemParamGL$fu_core_all_featureRelease(long propId, String key, Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.UNIT, null, null, new PropContainerController$setItemParamGL$unit$1(this, propId, key, value), 6, null));
    }

    public final double itemGetParamDouble$fu_core_all_featureRelease(long propId, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object objItemGetParam = itemGetParam(propId, key, Double.TYPE);
        if (objItemGetParam == null || !(objItemGetParam instanceof Double)) {
            return 0.0d;
        }
        return ((Number) objItemGetParam).doubleValue();
    }

    public final double[] itemGetParamDoubleArray$fu_core_all_featureRelease(long propId, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object objItemGetParam = itemGetParam(propId, key, double[].class);
        if (objItemGetParam == null || !(objItemGetParam instanceof double[])) {
            return null;
        }
        return (double[]) objItemGetParam;
    }

    public final String itemGetParamString$fu_core_all_featureRelease(long propId, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object objItemGetParam = itemGetParam(propId, key, String.class);
        if (objItemGetParam == null || !(objItemGetParam instanceof String)) {
            return null;
        }
        return (String) objItemGetParam;
    }

    public final float[] itemGetParamFloatArray$fu_core_all_featureRelease(long propId, String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Object objItemGetParam = itemGetParam(propId, key, float[].class);
        if (objItemGetParam == null || !(objItemGetParam instanceof float[])) {
            return null;
        }
        return (float[]) objItemGetParam;
    }

    protected final Object itemGetParam(long propId, String key, Class<?> clazz) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(clazz, "clazz");
        Integer num = getPropIdMap().get(Long.valueOf(propId));
        if (num == null) {
            return null;
        }
        num.intValue();
        return SDKController.INSTANCE.itemGetParam$fu_core_all_featureRelease(num.intValue(), key, clazz);
    }

    public final void setItemParam$fu_core_all_featureRelease(final long propId, final String key, final Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.UNIT, null, null, new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                Integer num = this.this$0.getPropIdMap().get(Long.valueOf(propId));
                if (num != null) {
                    num.intValue();
                    this.this$0.itemSetParam(num.intValue(), key, value);
                }
            }
        }, 6, null));
    }

    public final void createTexForItem$fu_core_all_featureRelease(long propId, String name, byte[] rgba, int width, int height) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(rgba, "rgba");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.UNIT, null, null, new PropContainerController$createTexForItem$unit$1(this, propId, name, rgba, width, height), 6, null));
    }

    public final void deleteTexForItem$fu_core_all_featureRelease(long propId, String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        doBackgroundAction(new ThreadQueuePool.QueueItem(ThreadQueuePool.QueueType.UNIT, null, null, new PropContainerController$deleteTexForItem$unit$1(this, propId, name), 6, null));
    }

    @Override
    public void applyThreadQueue(ThreadQueuePool.QueueItem queue) {
        Function0<Unit> unit;
        Intrinsics.checkParameterIsNotNull(queue, "queue");
        int i = WhenMappings.$EnumSwitchMapping$0[queue.getType().ordinal()];
        if (i == 1) {
            FUFeaturesData data = queue.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            applyAddProp(data);
            return;
        }
        if (i == 2) {
            FUFeaturesData data2 = queue.getData();
            if (data2 == null) {
                Intrinsics.throwNpe();
            }
            applyRemoveProp(data2);
            return;
        }
        if (i != 3) {
            if (i == 4 && (unit = queue.getUnit()) != null) {
                unit.invoke();
                return;
            }
            return;
        }
        FUFeaturesData data3 = queue.getData();
        if (data3 == null) {
            Intrinsics.throwNpe();
        }
        FUFeaturesData replaceData = queue.getReplaceData();
        if (replaceData == null) {
            Intrinsics.throwNpe();
        }
        applyReplaceProp(data3, replaceData);
    }

    private final void applyAddProp(FUFeaturesData fuFeaturesData) {
        FUBundleData bundle = fuFeaturesData.getBundle();
        if (bundle == null) {
            Intrinsics.throwNpe();
        }
        int iLoadBundleFile = getMBundleManager().loadBundleFile(bundle.getName(), bundle.getPath());
        if (iLoadBundleFile <= 0) {
            FULogger.m294e(getTAG(), "load Prop bundle failed bundle path:" + bundle.getPath());
            return;
        }
        getPropIdMap().put(Long.valueOf(fuFeaturesData.getId()), Integer.valueOf(iLoadBundleFile));
        HashMap<Long, LinkedHashMap<String, Object>> propTypeMap = getPropTypeMap();
        Long lValueOf = Long.valueOf(fuFeaturesData.getId());
        Object remark = fuFeaturesData.getRemark();
        if (remark == null) {
            Intrinsics.throwNpe();
        }
        if (remark == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.LinkedHashMap<kotlin.String, kotlin.Any> /* = java.util.LinkedHashMap<kotlin.String, kotlin.Any> */");
        }
        propTypeMap.put(lValueOf, (LinkedHashMap) remark);
        if (fuFeaturesData.getEnable()) {
            BundleManager.bindControllerBundle$default(getMBundleManager(), iLoadBundleFile, false, 2, null);
        }
        setPropParams(iLoadBundleFile, fuFeaturesData);
    }

    private final void applyRemoveProp(FUFeaturesData fuFeaturesData) {
        Integer num = getPropIdMap().get(Long.valueOf(fuFeaturesData.getId()));
        if (num != null) {
            getMBundleManager().destroyControllerBundle(num.intValue());
            getPropIdMap().remove(Long.valueOf(fuFeaturesData.getId()));
            getPropTypeMap().remove(Long.valueOf(fuFeaturesData.getId()));
        }
    }

    private final void applyReplaceProp(FUFeaturesData oldData, FUFeaturesData newData) {
        FUBundleData bundle = oldData.getBundle();
        if (bundle == null) {
            Intrinsics.throwNpe();
        }
        String path = bundle.getPath();
        FUBundleData bundle2 = newData.getBundle();
        if (bundle2 == null) {
            Intrinsics.throwNpe();
        }
        if (Intrinsics.areEqual(path, bundle2.getPath())) {
            Integer num = getPropIdMap().get(Long.valueOf(oldData.getId()));
            if (num != null) {
                int iIntValue = num.intValue();
                getPropIdMap().remove(Long.valueOf(oldData.getId()));
                getPropTypeMap().remove(Long.valueOf(oldData.getId()));
                getPropIdMap().put(Long.valueOf(newData.getId()), Integer.valueOf(iIntValue));
                HashMap<Long, LinkedHashMap<String, Object>> propTypeMap = getPropTypeMap();
                Long lValueOf = Long.valueOf(newData.getId());
                Object remark = newData.getRemark();
                if (remark == null) {
                    Intrinsics.throwNpe();
                }
                if (remark == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.LinkedHashMap<kotlin.String, kotlin.Any> /* = java.util.LinkedHashMap<kotlin.String, kotlin.Any> */");
                }
                propTypeMap.put(lValueOf, (LinkedHashMap) remark);
                if (newData.getEnable()) {
                    BundleManager.bindControllerBundle$default(getMBundleManager(), iIntValue, false, 2, null);
                } else {
                    getMBundleManager().unbindControllerBundle(iIntValue);
                }
                setPropParams(iIntValue, newData);
                return;
            }
            return;
        }
        FUBundleData bundle3 = newData.getBundle();
        int iLoadBundleFile = getMBundleManager().loadBundleFile(bundle3.getName(), bundle3.getPath());
        Integer num2 = getPropIdMap().get(Long.valueOf(oldData.getId()));
        if (num2 != null) {
            getMBundleManager().destroyControllerBundle(num2.intValue());
            getPropIdMap().remove(Long.valueOf(oldData.getId()));
            getPropTypeMap().remove(Long.valueOf(oldData.getId()));
        }
        if (iLoadBundleFile <= 0) {
            FULogger.m294e(getTAG(), "load Prop bundle failed bundle path:" + bundle3.getPath());
            return;
        }
        getPropIdMap().put(Long.valueOf(newData.getId()), Integer.valueOf(iLoadBundleFile));
        HashMap<Long, LinkedHashMap<String, Object>> propTypeMap2 = getPropTypeMap();
        Long lValueOf2 = Long.valueOf(newData.getId());
        Object remark2 = newData.getRemark();
        if (remark2 == null) {
            Intrinsics.throwNpe();
        }
        if (remark2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.LinkedHashMap<kotlin.String, kotlin.Any> /* = java.util.LinkedHashMap<kotlin.String, kotlin.Any> */");
        }
        propTypeMap2.put(lValueOf2, (LinkedHashMap) remark2);
        if (newData.getEnable()) {
            BundleManager.bindControllerBundle$default(getMBundleManager(), iLoadBundleFile, false, 2, null);
        }
        setPropParams(iLoadBundleFile, newData);
    }

    private final void setPropParams(final int handle, final FUFeaturesData data) {
        Object remark = data.getRemark();
        if (remark == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.LinkedHashMap<kotlin.String, kotlin.Any> /* = java.util.LinkedHashMap<kotlin.String, kotlin.Any> */");
        }
        LinkedHashMap linkedHashMap = (LinkedHashMap) remark;
        if (Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 1)) {
            itemSetParam(handle, "is3DFlipH", 1);
            itemSetParam(handle, PropParam.FLIP_TRACK, 1);
            itemSetParam(handle, PropParam.FLIP_LIGHT, 1);
            if (data.getParam().containsKey(PropParam.FACE_FOLLOW)) {
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
                        PropContainerController propContainerController = PropContainerController.this;
                        int i = handle;
                        Object obj = data.getParam().get(PropParam.FACE_FOLLOW);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        propContainerController.itemSetParam(i, PropParam.FACE_FOLLOW, Double.valueOf(((Boolean) obj).booleanValue() ? 1.0d : 0.0d));
                        PropContainerController propContainerController2 = PropContainerController.this;
                        int i2 = handle;
                        Object obj2 = data.getParam().get(PropParam.FACE_FOLLOW);
                        if (obj2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        propContainerController2.itemSetParam(i2, PropParam.IS_FIX_X, Double.valueOf(((Boolean) obj2).booleanValue() ? 0.0d : 1.0d));
                        PropContainerController propContainerController3 = PropContainerController.this;
                        int i3 = handle;
                        Object obj3 = data.getParam().get(PropParam.FACE_FOLLOW);
                        if (obj3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        propContainerController3.itemSetParam(i3, PropParam.IS_FIX_Y, Double.valueOf(((Boolean) obj3).booleanValue() ? 0.0d : 1.0d));
                        PropContainerController propContainerController4 = PropContainerController.this;
                        int i4 = handle;
                        Object obj4 = data.getParam().get(PropParam.FACE_FOLLOW);
                        if (obj4 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        propContainerController4.itemSetParam(i4, PropParam.IS_FIX_Z, Double.valueOf(((Boolean) obj4).booleanValue() ? 0.0d : 1.0d));
                        PropContainerController propContainerController5 = PropContainerController.this;
                        int i5 = handle;
                        Object obj5 = data.getParam().get(PropParam.FACE_FOLLOW);
                        if (obj5 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Boolean");
                        }
                        propContainerController5.itemSetParam(i5, PropParam.FIX_ROTATION, Double.valueOf(((Boolean) obj5).booleanValue() ? 0.0d : 1.0d));
                    }
                });
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 5)) {
            itemSetParam(handle, "rotation_mode", Double.valueOf(getMFURenderBridge().getMRotationMode()));
            itemSetParam(handle, PropParam.BG_ALIGN_TYPE, 1);
            return;
        }
        if (Intrinsics.areEqual(linkedHashMap.get(PropParam.PROP_TYPE), (Object) 10)) {
            if (linkedHashMap.containsKey("is_flip_points")) {
                itemSetParam(handle, "is_flip_points", Double.valueOf((getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_IMAGE || getMFURenderBridge().getExternalInputType() == FUExternalInputEnum.EXTERNAL_INPUT_TYPE_VIDEO || getMFURenderBridge().getCameraFacing() == CameraFacingEnum.CAMERA_BACK) ? 1.0d : 0.0d));
            }
            if (linkedHashMap.containsKey("is3DFlipH")) {
                itemSetParam(handle, "is3DFlipH", Double.valueOf(1.0d));
            }
            if (linkedHashMap.containsKey(PropParam.FORCE_PORTRAIT)) {
                Object obj = linkedHashMap.get(PropParam.FORCE_PORTRAIT);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
                }
                itemSetParam(handle, PropParam.FORCE_PORTRAIT, (Integer) obj);
                return;
            }
            return;
        }
        for (Map.Entry<String, Object> entry : data.getParam().entrySet()) {
            itemSetParam(handle, entry.getKey(), entry.getValue());
        }
    }
}
