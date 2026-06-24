package com.faceunity.core.bundle;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.callback.OperateCallback;
import com.faceunity.core.faceunity.FURenderConfig;
import com.faceunity.core.faceunity.FURenderManager;
import com.faceunity.core.support.SDKController;
import com.faceunity.core.utils.FULogger;
import com.faceunity.core.utils.FileUtils;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u001c\u0018\u0000 /2\u00020\u0001:\u0001/B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u0010J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bJ\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0004J\u0010\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\bH\u0002J\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0004J\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010 \u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\bJ\u0010\u0010!\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\bJ\r\u0010$\u001a\u00020\u0014H\u0000¢\u0006\u0002\b%J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\b\u0010(\u001a\u00020\u0014H\u0002J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0002J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010+\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bJ\u0016\u0010+\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u0004J \u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, m1293d2 = {"Lcom/faceunity/core/bundle/BundleManager;", "", "()V", "_renderBindBundles", "", "listLock", "mBundleItemMap", "Ljava/util/HashMap;", "", "Lcom/faceunity/core/bundle/BundleData;", "mBundleItemPathMap", "", "renderBindBundles", "getRenderBindBundles$fu_core_all_featureRelease", "()[I", "renderBundleUpdateFlag", "", "renderBundlesList", "Ljava/util/LinkedList;", "bindControllerBundle", "", "handle", "isFaceBeauty", "bindControllerItem", "controlHandle", "item", "items", "createItemFromPackage", "path", "destroyBundle", "handles", "destroyControllerBundle", "getBundleHandle", "getBundlePath", "loadBundleFile", NamingTable.TAG, "release", "release$fu_core_all_featureRelease", "renderBundlesAdd", StreamInformation.KEY_INDEX, "renderBundlesClear", "renderBundlesRemove", "unbindControllerBundle", "unbindControllerItem", "updateControllerBundle", "oldHandle", "newHandle", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class BundleManager {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile BundleManager INSTANCE = null;
    public static final String TAG = "KIT_BundleManager";
    private int[] _renderBindBundles;
    private final Object listLock;
    private final HashMap<String, BundleData> mBundleItemMap;
    private final HashMap<Integer, String> mBundleItemPathMap;
    private boolean renderBundleUpdateFlag;
    private LinkedList<Integer> renderBundlesList;

    private BundleManager() {
        this._renderBindBundles = new int[0];
        this.renderBundlesList = new LinkedList<>();
        this.listLock = new Object();
        this.mBundleItemMap = new HashMap<>();
        this.mBundleItemPathMap = new HashMap<>();
    }

    public BundleManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\u0004H\u0001¢\u0006\u0002\b\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1293d2 = {"Lcom/faceunity/core/bundle/BundleManager$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/bundle/BundleManager;", "TAG", "", "getInstance", "getInstance$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BundleManager getInstance$fu_core_all_featureRelease() {
            if (BundleManager.INSTANCE == null) {
                synchronized (this) {
                    if (BundleManager.INSTANCE == null) {
                        BundleManager.INSTANCE = new BundleManager(null);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            BundleManager bundleManager = BundleManager.INSTANCE;
            if (bundleManager == null) {
                Intrinsics.throwNpe();
            }
            return bundleManager;
        }
    }

    public final int[] getRenderBindBundles$fu_core_all_featureRelease() {
        if (!this.renderBundleUpdateFlag) {
            return this._renderBindBundles;
        }
        synchronized (this.listLock) {
            this.renderBundleUpdateFlag = false;
            this._renderBindBundles = CollectionsKt.toIntArray(this.renderBundlesList);
            Unit unit = Unit.INSTANCE;
        }
        return this._renderBindBundles;
    }

    private final void renderBundlesAdd(int handle) {
        synchronized (this.listLock) {
            if (!this.renderBundlesList.contains(Integer.valueOf(handle))) {
                this.renderBundlesList.add(Integer.valueOf(handle));
                this.renderBundleUpdateFlag = true;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void renderBundlesAdd(int index, int handle) {
        synchronized (this.listLock) {
            if (!this.renderBundlesList.contains(Integer.valueOf(handle))) {
                this.renderBundlesList.add(index, Integer.valueOf(handle));
                this.renderBundleUpdateFlag = true;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void renderBundlesRemove(int handle) {
        synchronized (this.listLock) {
            if (this.renderBundlesList.contains(Integer.valueOf(handle))) {
                this.renderBundlesList.remove(Integer.valueOf(handle));
                this.renderBundleUpdateFlag = true;
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void renderBundlesClear() {
        synchronized (this.listLock) {
            this.renderBundlesList.clear();
            this.renderBundleUpdateFlag = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public static void bindControllerBundle$default(BundleManager bundleManager, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        bundleManager.bindControllerBundle(i, z);
    }

    public final void bindControllerBundle(int handle, boolean isFaceBeauty) {
        FULogger.m293d(TAG, "bindControllerBundle  handle:" + handle + "  ");
        if (isFaceBeauty) {
            renderBundlesAdd(0, handle);
        } else {
            renderBundlesAdd(handle);
        }
    }

    public final void unbindControllerBundle(int handle) {
        FULogger.m293d(TAG, "unbindControllerBundle  handle:" + handle + "  ");
        renderBundlesRemove(handle);
    }

    public static void updateControllerBundle$default(BundleManager bundleManager, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            z = false;
        }
        bundleManager.updateControllerBundle(i, i2, z);
    }

    public final void updateControllerBundle(int oldHandle, int newHandle, boolean isFaceBeauty) {
        FULogger.m293d(TAG, "bindControllerBundle  oldHandle:" + oldHandle + "  newHandle:" + newHandle);
        if (oldHandle != newHandle) {
            if (oldHandle > 0) {
                destroyBundle(oldHandle);
                renderBundlesRemove(oldHandle);
            }
            if (newHandle > 0) {
                if (isFaceBeauty) {
                    renderBundlesAdd(0, newHandle);
                } else {
                    renderBundlesAdd(newHandle);
                }
            }
        }
    }

    public final void destroyControllerBundle(int handle) {
        FULogger.m293d(TAG, "destroyControllerBundle  handle:" + handle + "  ");
        if (handle > 0) {
            destroyBundle(handle);
            renderBundlesRemove(handle);
        }
    }

    public final void bindControllerItem(int controlHandle, int[] items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        StringBuilder sbAppend = new StringBuilder("bindControllerItem  controlHandle:").append(controlHandle).append("  items:");
        String string = Arrays.toString(items);
        Intrinsics.checkExpressionValueIsNotNull(string, "java.util.Arrays.toString(this)");
        FULogger.m293d(TAG, sbAppend.append(string).toString());
        if (controlHandle > 0) {
            if (!(items.length == 0)) {
                SDKController.INSTANCE.bindItems$fu_core_all_featureRelease(controlHandle, items);
            }
        }
    }

    public final void bindControllerItem(int controlHandle, int item) {
        bindControllerItem(controlHandle, new int[]{item});
    }

    public final void unbindControllerItem(int controlHandle, int[] items) {
        Intrinsics.checkParameterIsNotNull(items, "items");
        StringBuilder sbAppend = new StringBuilder("unbindControllerItem  controlHandle:").append(controlHandle).append("  items:");
        String string = Arrays.toString(items);
        Intrinsics.checkExpressionValueIsNotNull(string, "java.util.Arrays.toString(this)");
        FULogger.m293d(TAG, sbAppend.append(string).toString());
        SDKController.INSTANCE.unBindItems$fu_core_all_featureRelease(controlHandle, items);
    }

    public final void unbindControllerItem(int controlHandle, int item) {
        unbindControllerItem(controlHandle, new int[]{item});
    }

    public final int loadBundleFile(String name, String path) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(path, "path");
        FULogger.m293d(TAG, "createItemFromPackage  name:" + name + "  path:" + path);
        BundleData bundleData = this.mBundleItemMap.get(path);
        int handle = bundleData != null ? bundleData.getHandle() : 0;
        if (handle <= 0) {
            handle = createItemFromPackage(path);
            if (handle > 0) {
                this.mBundleItemMap.put(path, new BundleData(name, path, handle, false, false, 24, null));
                this.mBundleItemPathMap.put(Integer.valueOf(handle), path);
            } else {
                FULogger.m294e(TAG, "createItemFromPackage failed  name:" + name + "  path:" + path);
            }
        }
        return handle;
    }

    public final void destroyBundle(int handle) {
        String str = this.mBundleItemPathMap.get(Integer.valueOf(handle));
        FULogger.m293d(TAG, "destroyBundle  path:" + str + "    handle:" + handle);
        if (str != null) {
            this.mBundleItemMap.remove(str);
            this.mBundleItemPathMap.remove(Integer.valueOf(handle));
        }
        SDKController.INSTANCE.destroyItem$fu_core_all_featureRelease(handle);
    }

    public final String getBundlePath(int handle) {
        return this.mBundleItemPathMap.get(Integer.valueOf(handle));
    }

    public final int getBundleHandle(String path) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        BundleData bundleData = this.mBundleItemMap.get(path);
        if (bundleData != null) {
            return bundleData.getHandle();
        }
        return 0;
    }

    private final int createItemFromPackage(String path) {
        FULogger.m295i(TAG, "createItemFromPackage   path=" + path);
        int iCreateItemFromPackage$fu_core_all_featureRelease = 0;
        if (path.length() == 0) {
            return 0;
        }
        byte[] bArrLoadBundleFromLocal = FileUtils.loadBundleFromLocal(FURenderManager.INSTANCE.getMContext$fu_core_all_featureRelease(), path);
        if (bArrLoadBundleFromLocal != null) {
            iCreateItemFromPackage$fu_core_all_featureRelease = SDKController.INSTANCE.createItemFromPackage$fu_core_all_featureRelease(bArrLoadBundleFromLocal, path);
            if (iCreateItemFromPackage$fu_core_all_featureRelease > 0) {
                OperateCallback mOperateCallback$fu_core_all_featureRelease = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
                if (mOperateCallback$fu_core_all_featureRelease != null) {
                    mOperateCallback$fu_core_all_featureRelease.onSuccess(202, "load bundle success path: " + path + "  handleId: " + iCreateItemFromPackage$fu_core_all_featureRelease);
                }
            } else {
                OperateCallback mOperateCallback$fu_core_all_featureRelease2 = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
                if (mOperateCallback$fu_core_all_featureRelease2 != null) {
                    mOperateCallback$fu_core_all_featureRelease2.onFail(FURenderConfig.OPERATE_FAILED_LOAD_BUNDLE, "load bundle failed path: " + path);
                }
            }
        } else {
            OperateCallback mOperateCallback$fu_core_all_featureRelease3 = FURenderManager.INSTANCE.getMOperateCallback$fu_core_all_featureRelease();
            if (mOperateCallback$fu_core_all_featureRelease3 != null) {
                mOperateCallback$fu_core_all_featureRelease3.onFail(FURenderConfig.OPERATE_FAILED_FILE_NOT_FOUND, "file not found: " + path);
            }
            FULogger.m293d(TAG, "createItemFromPackage failed   file not found: " + path);
        }
        return iCreateItemFromPackage$fu_core_all_featureRelease;
    }

    public final void release$fu_core_all_featureRelease() {
        FULogger.m293d(TAG, "release");
        renderBundlesClear();
        this.mBundleItemMap.clear();
        this.mBundleItemPathMap.clear();
        SDKController.INSTANCE.destroyAllItems$fu_core_all_featureRelease();
    }

    public final void destroyBundle(int[] handles) {
        Intrinsics.checkParameterIsNotNull(handles, "handles");
        for (int i : handles) {
            if (i > 0) {
                destroyBundle(i);
            }
        }
    }
}
