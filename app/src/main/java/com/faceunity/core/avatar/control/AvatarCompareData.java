package com.faceunity.core.avatar.control;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002RM\u0010\u0003\u001a>\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR}\u0010\f\u001an\u0012\u0004\u0012\u00020\r\u0012,\u0012*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e`\t0\u0004j6\u0012\u0004\u0012\u00020\r\u0012,\u0012*\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004j\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e`\t`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bRM\u0010\u0011\u001a>\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000bR-\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0004j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR-\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00140\u0004j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0014`\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR!\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bRM\u0010\u001c\u001a>\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\u0006j\b\u0012\u0004\u0012\u00020\r`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\u0006j\b\u0012\u0004\u0012\u00020\r`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000bRM\u0010\u001e\u001a>\u0012\u0004\u0012\u00020\u0019\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\u0019\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u000bR!\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0006j\b\u0012\u0004\u0012\u00020\u0019`\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR-\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u0004j\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r`\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000bRM\u0010$\u001a>\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\u0006j\b\u0012\u0004\u0012\u00020\r`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\r0\u0006j\b\u0012\u0004\u0012\u00020\r`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000bRM\u0010&\u001a>\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0004j\u001e\u0012\u0004\u0012\u00020\r\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b`\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u000b¨\u0006("}, m1293d2 = {"Lcom/faceunity/core/avatar/control/AvatarCompareData;", "", "()V", "avatarBindHandleMap", "Ljava/util/LinkedHashMap;", "Lcom/faceunity/core/avatar/control/FUAAvatarData;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "Lkotlin/collections/LinkedHashMap;", "getAvatarBindHandleMap", "()Ljava/util/LinkedHashMap;", "avatarParamsMap", "", "Lkotlin/Function0;", "", "getAvatarParamsMap", "avatarUnbindHandleMap", "getAvatarUnbindHandleMap", "bundleAddMap", "", "getBundleAddMap", "bundleRemoveMap", "getBundleRemoveMap", "sceneAddList", "Lcom/faceunity/core/avatar/control/FUASceneData;", "getSceneAddList", "()Ljava/util/ArrayList;", "sceneBindAvatarMap", "getSceneBindAvatarMap", "sceneBindHandleMap", "getSceneBindHandleMap", "sceneRemoveList", "getSceneRemoveList", "sceneReplaceAvatarMap", "getSceneReplaceAvatarMap", "sceneUnbindAvatarMap", "getSceneUnbindAvatarMap", "sceneUnbindHandleMap", "getSceneUnbindHandleMap", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class AvatarCompareData {
    private final LinkedHashMap<String, Integer> bundleRemoveMap = new LinkedHashMap<>();
    private final LinkedHashMap<String, Integer> bundleAddMap = new LinkedHashMap<>();
    private final ArrayList<FUASceneData> sceneRemoveList = new ArrayList<>();
    private final ArrayList<FUASceneData> sceneAddList = new ArrayList<>();
    private final LinkedHashMap<Long, ArrayList<Long>> sceneUnbindAvatarMap = new LinkedHashMap<>();
    private final LinkedHashMap<Long, ArrayList<Long>> sceneBindAvatarMap = new LinkedHashMap<>();
    private final LinkedHashMap<Long, Long> sceneReplaceAvatarMap = new LinkedHashMap<>();
    private final LinkedHashMap<FUASceneData, ArrayList<String>> sceneBindHandleMap = new LinkedHashMap<>();
    private final LinkedHashMap<Long, ArrayList<String>> sceneUnbindHandleMap = new LinkedHashMap<>();
    private final LinkedHashMap<FUAAvatarData, ArrayList<String>> avatarBindHandleMap = new LinkedHashMap<>();
    private final LinkedHashMap<Long, ArrayList<String>> avatarUnbindHandleMap = new LinkedHashMap<>();
    private final LinkedHashMap<Long, LinkedHashMap<String, Function0<Unit>>> avatarParamsMap = new LinkedHashMap<>();

    public final LinkedHashMap<String, Integer> getBundleRemoveMap() {
        return this.bundleRemoveMap;
    }

    public final LinkedHashMap<String, Integer> getBundleAddMap() {
        return this.bundleAddMap;
    }

    public final ArrayList<FUASceneData> getSceneRemoveList() {
        return this.sceneRemoveList;
    }

    public final ArrayList<FUASceneData> getSceneAddList() {
        return this.sceneAddList;
    }

    public final LinkedHashMap<Long, ArrayList<Long>> getSceneUnbindAvatarMap() {
        return this.sceneUnbindAvatarMap;
    }

    public final LinkedHashMap<Long, ArrayList<Long>> getSceneBindAvatarMap() {
        return this.sceneBindAvatarMap;
    }

    public final LinkedHashMap<Long, Long> getSceneReplaceAvatarMap() {
        return this.sceneReplaceAvatarMap;
    }

    public final LinkedHashMap<FUASceneData, ArrayList<String>> getSceneBindHandleMap() {
        return this.sceneBindHandleMap;
    }

    public final LinkedHashMap<Long, ArrayList<String>> getSceneUnbindHandleMap() {
        return this.sceneUnbindHandleMap;
    }

    public final LinkedHashMap<FUAAvatarData, ArrayList<String>> getAvatarBindHandleMap() {
        return this.avatarBindHandleMap;
    }

    public final LinkedHashMap<Long, ArrayList<String>> getAvatarUnbindHandleMap() {
        return this.avatarUnbindHandleMap;
    }

    public final LinkedHashMap<Long, LinkedHashMap<String, Function0<Unit>>> getAvatarParamsMap() {
        return this.avatarParamsMap;
    }
}
