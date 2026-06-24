package com.faceunity.core.controller.prop;

import com.faceunity.core.entity.FUFeaturesData;
import com.p020hp.jipp.model.PrinterServiceType;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0010\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0002J\u0006\u0010\u001a\u001a\u00020\u0014J\b\u0010\u001b\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bJ\b\u0010\u001d\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00040\rj\b\u0012\u0004\u0012\u00020\u0004`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0004`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, m1293d2 = {"Lcom/faceunity/core/controller/prop/ThreadQueuePool;", "", "()V", "currentPushNode", "", "dataLock", "dataPool", "", "Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueItem;", "[Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueItem;", "poolArray1", "poolArray2", "pullNodeList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "queueArrayMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "addPoolSize", "", "applyAdd", "item", "applyAddUnit", "applyRemove", "applyReplace", "clear", "pull", "push", "updatePushNode", "QueueItem", "QueueType", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class ThreadQueuePool {
    private int currentPushNode;
    private QueueItem[] poolArray1 = new QueueItem[32];
    private QueueItem[] poolArray2 = new QueueItem[1];
    private final HashMap<Long, Integer> queueArrayMap = new HashMap<>();
    private final ArrayList<Integer> pullNodeList = new ArrayList<>();
    private final Object dataLock = new Object();
    private QueueItem[] dataPool = this.poolArray1;

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m1293d2 = {"Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueType;", "", "(Ljava/lang/String;I)V", "ADD", "REMOVE", "REPLACE", "UNIT", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public enum QueueType {
        ADD,
        REMOVE,
        REPLACE,
        UNIT
    }

    @Metadata(m1291bv = {1, 0, 3}, m1294k = 3, m1295mv = {1, 1, 16})
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[QueueType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[QueueType.ADD.ordinal()] = 1;
            iArr[QueueType.REPLACE.ordinal()] = 2;
            int[] iArr2 = new int[QueueType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[QueueType.ADD.ordinal()] = 1;
            iArr2[QueueType.REMOVE.ordinal()] = 2;
            iArr2[QueueType.REPLACE.ordinal()] = 3;
            iArr2[QueueType.UNIT.ordinal()] = 4;
        }
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, m1293d2 = {"Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueItem;", "", "type", "Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueType;", "data", "Lcom/faceunity/core/entity/FUFeaturesData;", "replaceData", "unit", "Lkotlin/Function0;", "", "(Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueType;Lcom/faceunity/core/entity/FUFeaturesData;Lcom/faceunity/core/entity/FUFeaturesData;Lkotlin/jvm/functions/Function0;)V", "getData", "()Lcom/faceunity/core/entity/FUFeaturesData;", "getReplaceData", "getType", "()Lcom/faceunity/core/controller/prop/ThreadQueuePool$QueueType;", "getUnit", "()Lkotlin/jvm/functions/Function0;", "component1", "component2", "component3", "component4", PrinterServiceType.copy, "equals", "", "other", "hashCode", "", "toString", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class QueueItem {
        private final FUFeaturesData data;
        private final FUFeaturesData replaceData;
        private final QueueType type;
        private final Function0<Unit> unit;

        public static QueueItem copy$default(QueueItem queueItem, QueueType queueType, FUFeaturesData fUFeaturesData, FUFeaturesData fUFeaturesData2, Function0 function0, int i, Object obj) {
            if ((i & 1) != 0) {
                queueType = queueItem.type;
            }
            if ((i & 2) != 0) {
                fUFeaturesData = queueItem.data;
            }
            if ((i & 4) != 0) {
                fUFeaturesData2 = queueItem.replaceData;
            }
            if ((i & 8) != 0) {
                function0 = queueItem.unit;
            }
            return queueItem.copy(queueType, fUFeaturesData, fUFeaturesData2, function0);
        }

        public final QueueType getType() {
            return this.type;
        }

        public final FUFeaturesData getData() {
            return this.data;
        }

        public final FUFeaturesData getReplaceData() {
            return this.replaceData;
        }

        public final Function0<Unit> component4() {
            return this.unit;
        }

        public final QueueItem copy(QueueType type, FUFeaturesData data, FUFeaturesData replaceData, Function0<Unit> unit) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            return new QueueItem(type, data, replaceData, unit);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof QueueItem)) {
                return false;
            }
            QueueItem queueItem = (QueueItem) other;
            return Intrinsics.areEqual(this.type, queueItem.type) && Intrinsics.areEqual(this.data, queueItem.data) && Intrinsics.areEqual(this.replaceData, queueItem.replaceData) && Intrinsics.areEqual(this.unit, queueItem.unit);
        }

        public int hashCode() {
            QueueType queueType = this.type;
            int iHashCode = (queueType != null ? queueType.hashCode() : 0) * 31;
            FUFeaturesData fUFeaturesData = this.data;
            int iHashCode2 = (iHashCode + (fUFeaturesData != null ? fUFeaturesData.hashCode() : 0)) * 31;
            FUFeaturesData fUFeaturesData2 = this.replaceData;
            int iHashCode3 = (iHashCode2 + (fUFeaturesData2 != null ? fUFeaturesData2.hashCode() : 0)) * 31;
            Function0<Unit> function0 = this.unit;
            return iHashCode3 + (function0 != null ? function0.hashCode() : 0);
        }

        public String toString() {
            return "QueueItem(type=" + this.type + ", data=" + this.data + ", replaceData=" + this.replaceData + ", unit=" + this.unit + ")";
        }

        public QueueItem(QueueType type, FUFeaturesData fUFeaturesData, FUFeaturesData fUFeaturesData2, Function0<Unit> function0) {
            Intrinsics.checkParameterIsNotNull(type, "type");
            this.type = type;
            this.data = fUFeaturesData;
            this.replaceData = fUFeaturesData2;
            this.unit = function0;
        }

        public QueueItem(QueueType queueType, FUFeaturesData fUFeaturesData, FUFeaturesData fUFeaturesData2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 2) != 0) {
                fUFeaturesData = null;
            }
            if ((i & 4) != 0) {
                fUFeaturesData2 = null;
            }
            if ((i & 8) != 0) {
                function0 = null;
            }
            this(queueType, fUFeaturesData, fUFeaturesData2, function0);
        }

        public final FUFeaturesData getData() {
            return this.data;
        }

        public final FUFeaturesData getReplaceData() {
            return this.replaceData;
        }

        public final QueueType getType() {
            return this.type;
        }

        public final Function0<Unit> getUnit() {
            return this.unit;
        }
    }

    public final QueueItem pull() {
        synchronized (this.dataLock) {
            if (this.pullNodeList.size() == 0) {
                return null;
            }
            Integer num = this.pullNodeList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(num, "pullNodeList[0]");
            int iIntValue = num.intValue();
            QueueItem queueItem = this.dataPool[iIntValue];
            if (queueItem == null) {
                Intrinsics.throwNpe();
            }
            int i = WhenMappings.$EnumSwitchMapping$0[queueItem.getType().ordinal()];
            if (i == 1) {
                HashMap<Long, Integer> map = this.queueArrayMap;
                FUFeaturesData data = queueItem.getData();
                if (data == null) {
                    Intrinsics.throwNpe();
                }
                map.remove(Long.valueOf(data.getId()));
            } else if (i == 2) {
                HashMap<Long, Integer> map2 = this.queueArrayMap;
                FUFeaturesData replaceData = queueItem.getReplaceData();
                if (replaceData == null) {
                    Intrinsics.throwNpe();
                }
                map2.remove(Long.valueOf(replaceData.getId()));
            }
            this.dataPool[iIntValue] = null;
            this.pullNodeList.remove(0);
            return queueItem;
        }
    }

    public final void push(QueueItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        synchronized (this.dataLock) {
            if (this.pullNodeList.size() == this.dataPool.length - 1) {
                addPoolSize();
            }
            int i = WhenMappings.$EnumSwitchMapping$1[item.getType().ordinal()];
            if (i == 1) {
                applyAdd(item);
            } else if (i == 2) {
                applyRemove(item);
            } else if (i == 3) {
                applyReplace(item);
            } else if (i == 4) {
                applyAddUnit(item);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void clear() {
        synchronized (this.dataLock) {
            this.dataPool = new QueueItem[this.dataPool.length];
            this.queueArrayMap.clear();
            this.pullNodeList.clear();
            this.currentPushNode = 0;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void applyAddUnit(QueueItem item) {
        updatePushNode();
        QueueItem[] queueItemArr = this.dataPool;
        int i = this.currentPushNode;
        queueItemArr[i] = item;
        this.pullNodeList.add(Integer.valueOf(i));
    }

    private final void applyAdd(QueueItem item) {
        updatePushNode();
        HashMap<Long, Integer> map = this.queueArrayMap;
        FUFeaturesData data = item.getData();
        if (data == null) {
            Intrinsics.throwNpe();
        }
        map.put(Long.valueOf(data.getId()), Integer.valueOf(this.currentPushNode));
        QueueItem[] queueItemArr = this.dataPool;
        int i = this.currentPushNode;
        queueItemArr[i] = item;
        this.pullNodeList.add(Integer.valueOf(i));
    }

    private final void applyRemove(QueueItem item) {
        FUFeaturesData data = item.getData();
        if (data == null) {
            Intrinsics.throwNpe();
        }
        long id = data.getId();
        if (this.queueArrayMap.containsKey(Long.valueOf(id))) {
            Integer num = this.queueArrayMap.get(Long.valueOf(id));
            if (num == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(num, "queueArrayMap[itemId]!!");
            int iIntValue = num.intValue();
            this.queueArrayMap.remove(Long.valueOf(id));
            this.dataPool[iIntValue] = null;
            this.pullNodeList.remove(Integer.valueOf(iIntValue));
            return;
        }
        updatePushNode();
        QueueItem[] queueItemArr = this.dataPool;
        int i = this.currentPushNode;
        queueItemArr[i] = item;
        this.pullNodeList.add(Integer.valueOf(i));
    }

    private final void applyReplace(QueueItem item) {
        QueueItem queueItem;
        FUFeaturesData data = item.getData();
        if (data == null) {
            Intrinsics.throwNpe();
        }
        long id = data.getId();
        FUFeaturesData replaceData = item.getReplaceData();
        if (replaceData == null) {
            Intrinsics.throwNpe();
        }
        long id2 = replaceData.getId();
        if (this.queueArrayMap.containsKey(Long.valueOf(id))) {
            Integer num = this.queueArrayMap.get(Long.valueOf(id));
            if (num == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(num, "queueArrayMap[itemId]!!");
            int iIntValue = num.intValue();
            this.queueArrayMap.remove(Long.valueOf(id));
            QueueItem queueItem2 = this.dataPool[iIntValue];
            if (queueItem2 == null) {
                Intrinsics.throwNpe();
            }
            if (queueItem2.getType() == QueueType.REPLACE) {
                FUFeaturesData data2 = queueItem2.getData();
                if (data2 == null) {
                    Intrinsics.throwNpe();
                }
                if (data2.getId() == item.getReplaceData().getId()) {
                    this.dataPool[iIntValue] = null;
                    this.pullNodeList.remove(Integer.valueOf(iIntValue));
                    return;
                }
                queueItem = new QueueItem(QueueType.REPLACE, queueItem2.getData(), item.getReplaceData(), null, 8, null);
            } else {
                queueItem = new QueueItem(QueueType.ADD, item.getReplaceData(), null, null, 12, null);
            }
            this.dataPool[iIntValue] = null;
            this.pullNodeList.remove(Integer.valueOf(iIntValue));
            updatePushNode();
            this.dataPool[this.currentPushNode] = queueItem;
        } else {
            updatePushNode();
            this.dataPool[this.currentPushNode] = item;
        }
        this.pullNodeList.add(Integer.valueOf(this.currentPushNode));
        this.queueArrayMap.put(Long.valueOf(id2), Integer.valueOf(this.currentPushNode));
    }

    private final void addPoolSize() {
        if (Intrinsics.areEqual(this.dataPool, this.poolArray1)) {
            QueueItem[] queueItemArr = this.poolArray1;
            QueueItem[] queueItemArr2 = new QueueItem[queueItemArr.length * 2];
            this.poolArray2 = queueItemArr2;
            System.arraycopy(queueItemArr, 0, queueItemArr2, 0, queueItemArr.length);
            this.poolArray1 = new QueueItem[0];
            this.dataPool = this.poolArray2;
            return;
        }
        QueueItem[] queueItemArr3 = this.poolArray2;
        QueueItem[] queueItemArr4 = new QueueItem[queueItemArr3.length * 2];
        this.poolArray1 = queueItemArr4;
        System.arraycopy(queueItemArr3, 0, queueItemArr4, 0, queueItemArr3.length);
        this.poolArray2 = new QueueItem[0];
        this.dataPool = this.poolArray1;
    }

    private final void updatePushNode() {
        while (true) {
            QueueItem[] queueItemArr = this.dataPool;
            int i = this.currentPushNode;
            if (queueItemArr[i] == null) {
                return;
            } else {
                this.currentPushNode = i == queueItemArr.length + (-1) ? 0 : i + 1;
            }
        }
    }
}
