package com.faceunity.core.model.prop;

import com.faceunity.core.callback.OnPropCallBack;
import com.faceunity.core.controller.prop.PropContainerController;
import com.faceunity.core.support.FURenderBridge;
import com.faceunity.core.utils.FULogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J\u0006\u0010\u0014\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\rJ\u001a\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cR\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006RN\u0010\t\u001aB\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0012\f\u0012\n \f*\u0004\u0018\u00010\r0\r \f* \u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0012\f\u0012\n \f*\u0004\u0018\u00010\r0\r\u0018\u00010\u000e0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, m1293d2 = {"Lcom/faceunity/core/model/prop/PropContainer;", "", "()V", "mPropController", "Lcom/faceunity/core/controller/prop/PropContainerController;", "getMPropController", "()Lcom/faceunity/core/controller/prop/PropContainerController;", "mPropController$delegate", "Lkotlin/Lazy;", "propMap", "", "", "kotlin.jvm.PlatformType", "Lcom/faceunity/core/model/prop/Prop;", "", "addProp", "", "prop", "getAllProp", "", "removeAllProp", "removeProp", "replaceProp", "oldProp", "newProp", "setOnPropCallBack", "", "onPropCallBack", "Lcom/faceunity/core/callback/OnPropCallBack;", "Companion", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
public final class PropContainer {

    public static final Companion INSTANCE = new Companion(null);
    private static volatile PropContainer INSTANCE = null;
    public static final String TAG = "KIT_PropContainer";

    private final Lazy mPropController = LazyKt.lazy(new Function0<PropContainerController>() {
        @Override
        public final PropContainerController invoke() {
            return FURenderBridge.INSTANCE.getInstance$fu_core_all_featureRelease().getMPropContainerController$fu_core_all_featureRelease();
        }
    });
    private final Map<Long, Prop> propMap = Collections.synchronizedMap(new LinkedHashMap());

    private final PropContainerController getMPropController() {
        return (PropContainerController) this.mPropController.getValue();
    }

    @Metadata(m1291bv = {1, 0, 3}, m1292d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, m1293d2 = {"Lcom/faceunity/core/model/prop/PropContainer$Companion;", "", "()V", "INSTANCE", "Lcom/faceunity/core/model/prop/PropContainer;", "TAG", "", "getInstance", "getInstance$fu_core_all_featureRelease", "fu_core_all_featureRelease"}, m1294k = 1, m1295mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PropContainer getInstance$fu_core_all_featureRelease() {
            if (PropContainer.INSTANCE == null) {
                synchronized (this) {
                    if (PropContainer.INSTANCE == null) {
                        PropContainer.INSTANCE = new PropContainer();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            PropContainer propContainer = PropContainer.INSTANCE;
            if (propContainer == null) {
                Intrinsics.throwNpe();
            }
            return propContainer;
        }
    }

    public final boolean addProp(Prop prop) {
        Intrinsics.checkParameterIsNotNull(prop, "prop");
        prop.getControlBundle().getPath();
        if (this.propMap.containsKey(Long.valueOf(prop.getPropId()))) {
            FULogger.m294e(TAG, "this prop already added ");
            return false;
        }
        Map<Long, Prop> propMap = this.propMap;
        Intrinsics.checkExpressionValueIsNotNull(propMap, "propMap");
        propMap.put(Long.valueOf(prop.getPropId()), prop);
        getMPropController().addProp(prop.buildFUFeaturesData$fu_core_all_featureRelease());
        return true;
    }

    public final boolean removeProp(Prop prop) {
        Intrinsics.checkParameterIsNotNull(prop, "prop");
        if (!this.propMap.containsKey(Long.valueOf(prop.getPropId()))) {
            FULogger.m294e(TAG, "The prop  does not exist ");
            return false;
        }
        this.propMap.remove(Long.valueOf(prop.getPropId()));
        getMPropController().removeProp(prop.buildFUFeaturesData$fu_core_all_featureRelease());
        return true;
    }

    public final boolean removeAllProp() {
        Map<Long, Prop> propMap = this.propMap;
        Intrinsics.checkExpressionValueIsNotNull(propMap, "propMap");
        Iterator<Map.Entry<Long, Prop>> it = propMap.entrySet().iterator();
        while (it.hasNext()) {
            getMPropController().removeProp(it.next().getValue().buildFUFeaturesData$fu_core_all_featureRelease());
        }
        this.propMap.clear();
        return true;
    }

    public final boolean replaceProp(Prop oldProp, Prop newProp) {
        if (oldProp == null && newProp == null) {
            FULogger.m297w(TAG, "oldProp and newProp is null");
        } else if (oldProp == null && newProp != null) {
            addProp(newProp);
        } else if (oldProp != null && newProp == null) {
            removeProp(oldProp);
        } else if (oldProp != null && newProp != null) {
            if (!this.propMap.containsKey(Long.valueOf(oldProp.getPropId()))) {
                FULogger.m294e(TAG, "The oldProp  does not exist ");
                return addProp(newProp);
            }
            if (this.propMap.containsKey(Long.valueOf(newProp.getPropId()))) {
                if (oldProp.getPropId() == newProp.getPropId()) {
                    FULogger.m297w(TAG, "oldProp and newProp   is same");
                    return false;
                }
                FULogger.m294e(TAG, "this newProp already added");
                return removeProp(oldProp);
            }
            this.propMap.remove(Long.valueOf(oldProp.getPropId()));
            Map<Long, Prop> propMap = this.propMap;
            Intrinsics.checkExpressionValueIsNotNull(propMap, "propMap");
            propMap.put(Long.valueOf(newProp.getPropId()), newProp);
            getMPropController().replaceProp(oldProp.buildFUFeaturesData$fu_core_all_featureRelease(), newProp.buildFUFeaturesData$fu_core_all_featureRelease());
            return true;
        }
        return false;
    }

    public final List<Prop> getAllProp() {
        ArrayList arrayList = new ArrayList();
        Map<Long, Prop> propMap = this.propMap;
        Intrinsics.checkExpressionValueIsNotNull(propMap, "propMap");
        Iterator<Map.Entry<Long, Prop>> it = propMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return arrayList;
    }

    public final void setOnPropCallBack(OnPropCallBack onPropCallBack) {
        getMPropController().setMOnPropCallBack(onPropCallBack);
    }
}
