package com.faceunity.core.avatar.model;

import com.arthenica.ffmpegkit.StreamInformation;
import com.faceunity.core.avatar.avatar.Animation;
import com.faceunity.core.avatar.avatar.BlendShape;
import com.faceunity.core.avatar.avatar.Color;
import com.faceunity.core.avatar.avatar.Deformation;
import com.faceunity.core.avatar.avatar.DynamicBone;
import com.faceunity.core.avatar.avatar.EyeFocusToCamera;
import com.faceunity.core.avatar.avatar.FacePup;
import com.faceunity.core.avatar.avatar.TransForm;
import com.faceunity.core.avatar.base.BaseAvatarAttribute;
import com.faceunity.core.avatar.control.AvatarController;
import com.faceunity.core.avatar.control.FUAAvatarData;
import com.faceunity.core.entity.FUAnimationData;
import com.faceunity.core.entity.FUBundleData;
import com.faceunity.core.entity.FUVisibleBundleData;
import com.faceunity.core.utils.FULogger;
import com.tom_roush.fontbox.ttf.NamingTable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0015\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004J\r\u0010\u001a\u001a\u00020\u001bH\u0000¢\u0006\u0002\b\u001cJ\u0006\u0010\u001d\u001a\u00020\u0000J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020 J\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 J\u001a\u0010&\u001a\u00020\u00182\b\u0010'\u001a\u0004\u0018\u00010\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u0016\u0010&\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0004J6\u0010&\u001a\u00020\u00182\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020 0\u0003j\b\u0012\u0004\u0012\u00020 `\u00052\u0016\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005J6\u0010+\u001a\u00020\u00182\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020 0\u0003j\b\u0012\u0004\u0012\u00020 `\u00052\u0016\u0010*\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005J\u0016\u0010,\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010(\u001a\u00020\u0004J\b\u0010-\u001a\u00020.H\u0002R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u000e8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00128\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00148\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00168\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, m1293d2 = {"Lcom/faceunity/core/avatar/model/Avatar;", "Lcom/faceunity/core/avatar/base/BaseAvatarAttribute;", "components", "Ljava/util/ArrayList;", "Lcom/faceunity/core/entity/FUBundleData;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "animation", "Lcom/faceunity/core/avatar/avatar/Animation;", "blendShape", "Lcom/faceunity/core/avatar/avatar/BlendShape;", "color", "Lcom/faceunity/core/avatar/avatar/Color;", "deformation", "Lcom/faceunity/core/avatar/avatar/Deformation;", "dynamicBone", "Lcom/faceunity/core/avatar/avatar/DynamicBone;", "eyeFocusToCamera", "Lcom/faceunity/core/avatar/avatar/EyeFocusToCamera;", "facePup", "Lcom/faceunity/core/avatar/avatar/FacePup;", "transForm", "Lcom/faceunity/core/avatar/avatar/TransForm;", "addComponent", "", "bundle", "buildFUAAvatarData", "Lcom/faceunity/core/avatar/control/FUAAvatarData;", "buildFUAAvatarData$fu_core_all_featureRelease", "clone", "getComponent", NamingTable.TAG, "", "getInstanceFaceVertexScreenCoordinate", "", StreamInformation.KEY_INDEX, "", "removeComponent", "replaceComponent", "oldComponent", "newComponent", "names", "newComponents", "replaceComponentGL", "replaceComponentModelOnly", "unionInvisibleList", "", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class Avatar extends BaseAvatarAttribute {
    public final Animation animation;
    public final BlendShape blendShape;
    public final Color color;
    public final ArrayList<FUBundleData> components;
    public final Deformation deformation;
    public final DynamicBone dynamicBone;
    public final EyeFocusToCamera eyeFocusToCamera;
    public final FacePup facePup;
    public final TransForm transForm;

    public Avatar(ArrayList<FUBundleData> components) {
        Intrinsics.checkParameterIsNotNull(components, "components");
        this.components = components;
        TransForm transForm = new TransForm();
        this.transForm = transForm;
        Animation animation = new Animation();
        this.animation = animation;
        BlendShape blendShape = new BlendShape();
        this.blendShape = blendShape;
        Color color = new Color(this);
        this.color = color;
        Deformation deformation = new Deformation();
        this.deformation = deformation;
        DynamicBone dynamicBone = new DynamicBone();
        this.dynamicBone = dynamicBone;
        EyeFocusToCamera eyeFocusToCamera = new EyeFocusToCamera();
        this.eyeFocusToCamera = eyeFocusToCamera;
        FacePup facePup = new FacePup();
        this.facePup = facePup;
        setAvatarId$fu_core_all_featureRelease(System.nanoTime());
        transForm.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        animation.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        blendShape.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        color.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        deformation.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        dynamicBone.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        eyeFocusToCamera.setAvatarId$fu_core_all_featureRelease(getAvatarId());
        facePup.setAvatarId$fu_core_all_featureRelease(getAvatarId());
    }

    public final float[] getInstanceFaceVertexScreenCoordinate(int index) {
        float[] fArr = new float[2];
        getMAvatarController$fu_core_all_featureRelease().getInstanceFaceVertexScreenCoordinate(getAvatarId(), index, fArr);
        return fArr;
    }

    public final void addComponent(FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Iterator<T> it = this.components.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((FUBundleData) it.next()).getPath(), bundle.getPath())) {
                FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has added bundle.name=" + bundle.getName());
                return;
            }
        }
        this.components.add(bundle);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().loadAvatarItemBundle(getAvatarId(), bundle);
            AvatarController.setInstanceBodyInvisibleList$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), unionInvisibleList(), false, 4, null);
        }
    }

    public final FUBundleData getComponent(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        for (FUBundleData fUBundleData : this.components) {
            if (Intrinsics.areEqual(fUBundleData.getName(), name)) {
                return fUBundleData;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has not find name=" + name);
        return null;
    }

    public final void removeComponent(FUBundleData bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        for (FUBundleData fUBundleData : this.components) {
            if (Intrinsics.areEqual(fUBundleData.getPath(), bundle.getPath())) {
                this.components.remove(fUBundleData);
                if (getHasLoaded()) {
                    getMAvatarController$fu_core_all_featureRelease().removeAvatarItemBundle(getAvatarId(), bundle);
                    AvatarController.setInstanceBodyInvisibleList$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), unionInvisibleList(), false, 4, null);
                    return;
                }
                return;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has not find bundle.name=" + bundle.getName());
    }

    public final void removeComponent(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        for (FUBundleData fUBundleData : this.components) {
            if (Intrinsics.areEqual(fUBundleData.getName(), name)) {
                this.components.remove(fUBundleData);
                if (getHasLoaded()) {
                    getMAvatarController$fu_core_all_featureRelease().removeAvatarItemBundle(getAvatarId(), fUBundleData);
                    AvatarController.setInstanceBodyInvisibleList$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), unionInvisibleList(), false, 4, null);
                    return;
                }
                return;
            }
        }
        FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "animation bundle has not find  name=" + name);
    }

    public final void replaceComponent(String name, FUBundleData newComponent) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(newComponent, "newComponent");
        FUBundleData fUBundleData = null;
        for (FUBundleData fUBundleData2 : this.components) {
            if (Intrinsics.areEqual(fUBundleData2.getName(), name)) {
                fUBundleData = fUBundleData2;
            }
        }
        if (fUBundleData == null) {
            addComponent(newComponent);
            return;
        }
        if (fUBundleData == null) {
            Intrinsics.throwNpe();
        }
        replaceComponent(fUBundleData, newComponent);
    }

    public final void replaceComponent(FUBundleData oldComponent, FUBundleData newComponent) {
        if (oldComponent == null && newComponent == null) {
            FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "oldComponent and newComponent is null");
            return;
        }
        if (oldComponent == null && newComponent != null) {
            addComponent(newComponent);
            return;
        }
        if (oldComponent != null && newComponent == null) {
            removeComponent(oldComponent);
            return;
        }
        if (oldComponent == null || newComponent == null) {
            return;
        }
        if (Intrinsics.areEqual(oldComponent.getPath(), newComponent.getPath())) {
            FULogger.m297w(BaseAvatarAttribute.INSTANCE.getTAG(), "oldComponent and newComponent   is same");
            return;
        }
        this.components.remove(oldComponent);
        this.components.add(newComponent);
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().replaceAvatarItemBundle(getAvatarId(), oldComponent, newComponent);
            AvatarController.setInstanceBodyInvisibleList$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), unionInvisibleList(), false, 4, null);
        }
    }

    public final void replaceComponent(ArrayList<String> names, ArrayList<FUBundleData> newComponents) {
        Intrinsics.checkParameterIsNotNull(names, "names");
        Intrinsics.checkParameterIsNotNull(newComponents, "newComponents");
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        ArrayList<FUBundleData> arrayList2 = new ArrayList<>();
        for (FUBundleData fUBundleData : newComponents) {
            if (names.contains(fUBundleData.getName())) {
                FUBundleData component = getComponent(fUBundleData.getName());
                if (component == null) {
                    names.remove(fUBundleData.getName());
                    arrayList.add(fUBundleData);
                } else if (Intrinsics.areEqual(component.getPath(), fUBundleData.getPath())) {
                    names.remove(fUBundleData.getName());
                } else {
                    arrayList.add(fUBundleData);
                }
            } else {
                arrayList.add(fUBundleData);
            }
        }
        for (FUBundleData fUBundleData2 : this.components) {
            if (names.contains(fUBundleData2.getName())) {
                arrayList2.add(fUBundleData2);
            }
        }
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            this.components.remove((FUBundleData) it.next());
        }
        Iterator<T> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.components.add((FUBundleData) it2.next());
        }
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().replaceAvatarItemBundle(getAvatarId(), arrayList2, arrayList);
            AvatarController.setInstanceBodyInvisibleList$default(getMAvatarController$fu_core_all_featureRelease(), getAvatarId(), unionInvisibleList(), false, 4, null);
        }
    }

    public final void replaceComponentGL(ArrayList<String> names, ArrayList<FUBundleData> newComponents) {
        Intrinsics.checkParameterIsNotNull(names, "names");
        Intrinsics.checkParameterIsNotNull(newComponents, "newComponents");
        ArrayList<FUBundleData> arrayList = new ArrayList<>();
        ArrayList<FUBundleData> arrayList2 = new ArrayList<>();
        for (FUBundleData fUBundleData : newComponents) {
            if (names.contains(fUBundleData.getName())) {
                FUBundleData component = getComponent(fUBundleData.getName());
                if (component == null) {
                    names.remove(fUBundleData.getName());
                    arrayList.add(fUBundleData);
                } else if (Intrinsics.areEqual(component.getPath(), fUBundleData.getPath())) {
                    names.remove(fUBundleData.getName());
                } else {
                    arrayList.add(fUBundleData);
                }
            } else {
                arrayList.add(fUBundleData);
            }
        }
        for (FUBundleData fUBundleData2 : this.components) {
            if (names.contains(fUBundleData2.getName())) {
                arrayList2.add(fUBundleData2);
            }
        }
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            this.components.remove((FUBundleData) it.next());
        }
        Iterator<T> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.components.add((FUBundleData) it2.next());
        }
        if (getHasLoaded()) {
            getMAvatarController$fu_core_all_featureRelease().replaceAvatarItemBundleGL(getAvatarId(), arrayList2, arrayList);
            getMAvatarController$fu_core_all_featureRelease().setInstanceBodyInvisibleList(getAvatarId(), unionInvisibleList(), false);
        }
    }

    public final void replaceComponentModelOnly(String name, FUBundleData newComponent) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(newComponent, "newComponent");
        FUBundleData fUBundleData = null;
        for (FUBundleData fUBundleData2 : this.components) {
            if (Intrinsics.areEqual(fUBundleData2.getName(), name)) {
                fUBundleData = fUBundleData2;
            }
        }
        if (fUBundleData != null) {
            this.components.remove(fUBundleData);
        }
        this.components.add(newComponent);
    }

    public final FUAAvatarData buildFUAAvatarData$fu_core_all_featureRelease() {
        LinkedHashMap<String, Function0<Unit>> linkedHashMap = new LinkedHashMap<>();
        ArrayList arrayList = new ArrayList();
        ArrayList<FUAnimationData> arrayList2 = new ArrayList<>();
        arrayList.addAll(this.components);
        final int[] iArrUnionInvisibleList = unionInvisibleList();
        linkedHashMap.put("setInstanceBodyInvisibleList", new Function0<Unit>() {
            {
                super(0);
            }

            @Override
            public Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            public final void invoke2() {
                this.this$0.getMAvatarController$fu_core_all_featureRelease().setInstanceBodyInvisibleList(this.this$0.getAvatarId(), iArrUnionInvisibleList, false);
            }
        });
        this.transForm.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.animation.loadParams$fu_core_all_featureRelease(linkedHashMap, arrayList2);
        this.blendShape.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.dynamicBone.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.eyeFocusToCamera.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.color.loadParams$fu_core_all_featureRelease(linkedHashMap, linkedHashMap);
        this.facePup.loadParams$fu_core_all_featureRelease(linkedHashMap);
        this.deformation.loadParams$fu_core_all_featureRelease(linkedHashMap);
        setHasLoaded(true);
        return new FUAAvatarData(getAvatarId(), arrayList, arrayList2, linkedHashMap);
    }

    public final Avatar clone() {
        ArrayList arrayList = new ArrayList();
        for (FUBundleData fUBundleData : this.components) {
            arrayList.add(new FUBundleData(fUBundleData.getPath(), fUBundleData.getName()));
        }
        Avatar avatar = new Avatar(arrayList);
        avatar.transForm.clone(this.transForm);
        avatar.animation.clone$fu_core_all_featureRelease(this.animation);
        avatar.blendShape.clone(this.blendShape);
        avatar.dynamicBone.clone(this.dynamicBone);
        avatar.eyeFocusToCamera.clone(this.eyeFocusToCamera);
        avatar.color.clone(this.color);
        avatar.facePup.clone(this.facePup);
        avatar.deformation.clone(this.deformation);
        return avatar;
    }

    private final int[] unionInvisibleList() {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ArrayList<FUBundleData> arrayList2 = this.components;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : arrayList2) {
            if (obj instanceof FUVisibleBundleData) {
                arrayList3.add(obj);
            }
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            int[] visibleList = ((FUVisibleBundleData) it.next()).getVisibleList();
            if (visibleList != null) {
                for (int i : visibleList) {
                    hashSet.add(Integer.valueOf(i));
                }
            }
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            arrayList.add(Integer.valueOf(((Number) it2.next()).intValue()));
        }
        return CollectionsKt.toIntArray(arrayList);
    }
}
